package com.raf.reservation_service.service.impl;

import com.raf.reservation_service.dto.ReservationCreateDto;

public interface ReservationService {

    void addReservation(ReservationCreateDto reservationCreateDto);
    
    void deleteById(Long id);
}
