package com.raf.reservation_service.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		private String hotelName;
		private Integer numOfArooms;
		private Integer numOfBrooms;
		private Integer pricePerDay;
		
		public Hotel() {
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getHotelName() {
			return hotelName;
		}

		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}

		public Integer getNumOfArooms() {
			return numOfArooms;
		}

		public void setNumOfArooms(Integer numOfArooms) {
			this.numOfArooms = numOfArooms;
		}

		public Integer getNumOfBrooms() {
			return numOfBrooms;
		}

		public void setNumOfBrooms(Integer numOfBrooms) {
			this.numOfBrooms = numOfBrooms;
		}

		public Integer getPricePerDay() {
			return pricePerDay;
		}

		public void setPricePerDay(Integer pricePerDay) {
			this.pricePerDay = pricePerDay;
		}
		
		
		
	
}
