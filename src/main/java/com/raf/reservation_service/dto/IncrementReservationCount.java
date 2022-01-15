package com.raf.reservation_service.dto;

public class IncrementReservationCount {

	
	private Long userId;
	
	public IncrementReservationCount() {
		
	}

	public IncrementReservationCount(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
