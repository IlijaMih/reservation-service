package com.raf.reservation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raf.reservation_service.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

	
}
