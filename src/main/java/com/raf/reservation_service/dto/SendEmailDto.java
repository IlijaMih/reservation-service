package com.raf.reservation_service.dto;

public class SendEmailDto {

    private String email;

    public SendEmailDto() {

    }

    public SendEmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
