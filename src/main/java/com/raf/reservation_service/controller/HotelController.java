package com.raf.reservation_service.controller;

import javax.validation.Valid;

import org.hibernate.annotations.Check;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raf.reservation_service.dto.HotelCreateDto;
import com.raf.reservation_service.dto.HotelDto;
import com.raf.reservation_service.dto.ReservationCreateDto;
import com.raf.reservation_service.security.CheckSecurity;
import com.raf.reservation_service.service.impl.HotelService;


@RestController
@RequestMapping("/hotel")
public class HotelController {

	private HotelService hotelService;

	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	@PostMapping
    public ResponseEntity<Void> addReservation(@RequestBody @Valid HotelCreateDto hotelCreateDto) {
        hotelService.addHotel(hotelCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PutMapping("/edit")
	@CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<HotelDto> editProfile(@RequestHeader("Authorization") String authorization,HotelCreateDto userEditDto) {

        return new ResponseEntity<>(hotelService.editProfile( userEditDto), HttpStatus.OK);
    }
	
}
