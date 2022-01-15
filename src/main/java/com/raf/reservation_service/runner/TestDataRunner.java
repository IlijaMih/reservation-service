package com.raf.reservation_service.runner;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.raf.reservation_service.domain.Hotel;
import com.raf.reservation_service.repository.HotelRepository;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private HotelRepository hotelRepository;
 

    public TestDataRunner(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Hotel hotel = new Hotel();
        hotel.setHotelName("Grand");
        hotel.setNumOfArooms(100);
        hotel.setNumOfBrooms(200);
        hotel.setPricePerDay(50);
        hotelRepository.save(hotel);
        
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Palisad");
        hotel1.setNumOfArooms(30);
        hotel1.setNumOfBrooms(110);
        hotel1.setPricePerDay(45);
        hotelRepository.save(hotel1);
        
    }
}
