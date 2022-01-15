package com.raf.reservation_service.controller;

import com.raf.reservation_service.dto.ReservationCreateDto;
import com.raf.reservation_service.service.impl.ReservationService;

import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Void> addReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        reservationService.addReservation(reservationCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Cancle reservation")
    @DeleteMapping("/{id}")		
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
