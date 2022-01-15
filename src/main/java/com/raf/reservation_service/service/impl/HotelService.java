package com.raf.reservation_service.service.impl;

import com.raf.reservation_service.dto.HotelCreateDto;
import com.raf.reservation_service.dto.HotelDto;


public interface HotelService {
	
	HotelDto addHotel(HotelCreateDto hotelCreateDto);
	
	HotelDto editProfile(HotelCreateDto hotelCreateDto);

}
