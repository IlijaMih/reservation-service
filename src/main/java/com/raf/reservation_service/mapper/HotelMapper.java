package com.raf.reservation_service.mapper;

import org.springframework.stereotype.Component;

import com.raf.reservation_service.domain.Hotel;
import com.raf.reservation_service.dto.HotelCreateDto;
import com.raf.reservation_service.dto.HotelDto;



@Component
public class HotelMapper {

	
	
	
	public HotelDto hotelToHotelDto(Hotel hotel) {
		HotelDto hotelDto= new HotelDto();
		hotelDto.setHotelName(hotel.getHotelName());
		hotelDto.setNumOfArooms(hotel.getNumOfArooms());
		hotelDto.setNumOfBrooms(hotel.getNumOfBrooms());
		hotelDto.setPricePerDay(hotel.getPricePerDay());
		return hotelDto;
	}
	
	public Hotel hotelCreateDtoToHotel(HotelCreateDto hotelCreateDto) {
		Hotel hotel=new Hotel();
		hotel.setHotelName(hotelCreateDto.getHotelName());
		hotel.setNumOfArooms(hotelCreateDto.getNumOfArooms());
		hotel.setNumOfBrooms(hotelCreateDto.getNumOfBrooms());
		hotel.setPricePerDay(hotelCreateDto.getPricePerDay());
		return hotel;
	}
}
