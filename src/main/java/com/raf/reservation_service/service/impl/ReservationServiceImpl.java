package com.raf.reservation_service.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.reservation_service.dto.DiscountDto;
import com.raf.reservation_service.dto.IncrementReservationCount;
import com.raf.reservation_service.domain.Hotel;
import com.raf.reservation_service.domain.Reservation;
import com.raf.reservation_service.dto.ReservationCreateDto;
import com.raf.reservation_service.exception.NotFoundException;
import com.raf.reservation_service.mapper.ReservationMapper;
import com.raf.reservation_service.repository.HotelRepository;
import com.raf.reservation_service.repository.ReservationRepository;
import com.raf.reservation_service.service.impl.ReservationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private HotelRepository hotelRepository;
    private RestTemplate userServiceRestTemplate;
    private JmsTemplate jmsTemplate;
	private ObjectMapper objectMapper;
	private String incrementReservationCountDestination;
	private String decrementReservationCountDestination;
	private ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, RestTemplate userServiceRestTemplate,
    		HotelRepository hotelRepository,ReservationMapper reservationMapper,
    		JmsTemplate jmsTemplate, ObjectMapper objectMapper, @Value("${destination.increment.reservation.count}") String incrementReservationCountDestination,
    		@Value("${destination.decrement.reservation.count}") String decrementReservationCountDestination) {
        this.reservationRepository = reservationRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.hotelRepository = hotelRepository;
        this.jmsTemplate = jmsTemplate;
		this.objectMapper = objectMapper;
		this.incrementReservationCountDestination = incrementReservationCountDestination;
		this.decrementReservationCountDestination = decrementReservationCountDestination;
		this.reservationMapper = reservationMapper;
    }

    @Override
    public void addReservation(ReservationCreateDto reservationCreateDto) {
    	Hotel hotel = hotelRepository.findById(reservationCreateDto.getHotelId())
    			.orElseThrow(()->new NotFoundException(String.format("Hotel with id %d is not found.", reservationCreateDto.getHotelId())));
    	Integer rooms = hotel.getNumOfArooms();
    	hotel.setNumOfArooms(rooms-1);
    	hotelRepository.save(hotel);
    	
    	
    	ResponseEntity<DiscountDto> discountDtoResponseEntity = userServiceRestTemplate.exchange("/user/"
    			+reservationCreateDto.getUserId() + "/discount", HttpMethod.GET, null, DiscountDto.class);
    	
    	Integer discount = 100 - discountDtoResponseEntity.getBody().getDiscount();
    	
    	Double price = (double) (hotel.getPricePerDay() * reservationCreateDto.getNumberOfDays() * discount / 100);    	
    	Reservation reservation = reservationMapper.reservationCreateDtoToReservation(reservationCreateDto);
    	reservation.setPrice(price);
    	reservationRepository.save(reservation);
		///send message to user service

			try {
				jmsTemplate.convertAndSend(incrementReservationCountDestination, objectMapper.writeValueAsString(
						new IncrementReservationCount(reservationCreateDto.getUserId())));
			} catch (JmsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }

	@Override
	public void deleteById(Long id) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(()-> new NotFoundException(String.format("Reservation with id %d is not found.", id)));
		Hotel hotel = hotelRepository.findById(reservation.getHotelId())
				.orElseThrow(()-> new NotFoundException(String.format("Hotel with id %d is not found.", reservation.getHotelId())));
		Integer currNumOfRooms = hotel.getNumOfArooms();
		hotel.setNumOfArooms(currNumOfRooms+1);
		try {
			jmsTemplate.convertAndSend(decrementReservationCountDestination, objectMapper.writeValueAsString(
					new IncrementReservationCount(reservation.getUserId())));
		} catch (JmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reservationRepository.deleteById(id);
		
	}
}
