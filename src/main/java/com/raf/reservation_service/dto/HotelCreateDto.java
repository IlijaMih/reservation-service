package com.raf.reservation_service.dto;

import java.math.BigDecimal;

public class HotelCreateDto {

	private Long id;
	private String hotelName;
	private Integer numOfArooms;
	private Integer numOfBrooms;
	private Integer pricePerDay;
	
	
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
