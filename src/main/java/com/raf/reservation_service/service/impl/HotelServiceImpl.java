package com.raf.reservation_service.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.reservation_service.dto.IncrementReservationCount;
import com.raf.reservation_service.domain.Hotel;
import com.raf.reservation_service.dto.HotelCreateDto;
import com.raf.reservation_service.dto.HotelDto;
import com.raf.reservation_service.exception.NotFoundException;
import com.raf.reservation_service.mapper.HotelMapper;
import com.raf.reservation_service.repository.HotelRepository;
import com.raf.reservation_service.service.impl.HotelService;



@Service
public class HotelServiceImpl implements HotelService{

	
	private HotelRepository hotelRepository;
	private HotelMapper hotelMapper;
	
	
	
	public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
		this.hotelRepository = hotelRepository;
		this.hotelMapper = hotelMapper;
		
	}




	@Override
	public HotelDto addHotel(HotelCreateDto hotelCreateDto) {
		Hotel hotel = hotelMapper.hotelCreateDtoToHotel(hotelCreateDto);
		hotelRepository.save(hotel);
		return hotelMapper.hotelToHotelDto(hotel);

	}




	@Override
	public HotelDto editProfile(HotelCreateDto hotelCreateDto) {
		Hotel hotel = hotelRepository.findById(hotelCreateDto.getId())
				.orElseThrow(() -> new NotFoundException(String.format("Hotel with id %d is not found.",hotelCreateDto.getId() )));
		String newName = hotelCreateDto.getHotelName() == null ? hotel.getHotelName():hotelCreateDto.getHotelName();
		hotel.setHotelName(newName);
		Integer newARooms = hotelCreateDto.getNumOfArooms() == null ? hotel.getNumOfArooms():hotelCreateDto.getNumOfArooms();
		hotel.setNumOfArooms(newARooms);
		Integer newBRooms = hotelCreateDto.getNumOfBrooms() == null ? hotel.getNumOfBrooms():hotelCreateDto.getNumOfBrooms();
		hotel.setNumOfBrooms(newBRooms);
		Integer newPrice = hotelCreateDto.getPricePerDay() == null ? hotel.getPricePerDay():hotelCreateDto.getPricePerDay();
		hotel.setPricePerDay(newPrice);
		return hotelMapper.hotelToHotelDto(hotelRepository.save(hotel));
	}

	
	
	
}
