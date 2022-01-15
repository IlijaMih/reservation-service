package com.raf.reservation_service.mapper;

import org.springframework.stereotype.Component;

import com.raf.reservation_service.domain.Hotel;
import com.raf.reservation_service.domain.Reservation;
import com.raf.reservation_service.dto.HotelCreateDto;
import com.raf.reservation_service.dto.HotelDto;
import com.raf.reservation_service.dto.ReservationCreateDto;
import com.raf.reservation_service.dto.ReservationDto;

@Component
public class ReservationMapper {
	
	
	public ReservationDto reservationToReservationDto(Reservation reservation) {
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setHotelId(reservation.getHotelId());
		reservationDto.setUserId(reservation.getUserId());
		reservationDto.setNumberOfDays(reservation.getNumberOfDays());
		reservationDto.setTypeOfRoom(reservation.getTypeOfRoom());
		reservationDto.setPrice(reservation.getPrice());
		return reservationDto;
	}
	
	
	public Reservation reservationCreateDtoToReservation(ReservationCreateDto reservationCreateDto) {
		Reservation reservation = new Reservation();
		reservation.setHotelId(reservationCreateDto.getHotelId());
		reservation.setUserId(reservationCreateDto.getUserId());
		reservation.setNumberOfDays(reservationCreateDto.getNumberOfDays());
		reservation.setPrice(reservationCreateDto.getPrice());
		reservation.setTypeOfRoom(reservationCreateDto.getTypeOfRoom());
		return reservation;
	}

}
