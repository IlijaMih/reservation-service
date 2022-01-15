package com.raf.reservation_service.JMSListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.reservation_service.dto.SendEmailDto;

@Component
public class SendEmailListener {

	private ObjectMapper objectMapper;

	public SendEmailListener(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}
	
	@JmsListener(destination = "${destination.send.email}", concurrency = "5-10")
	public void sendEmail(Message message) throws JMSException, JsonMappingException, JsonProcessingException {
		String json = ((TextMessage)message).getText();
		SendEmailDto email= objectMapper.readValue(json, SendEmailDto.class);
		System.out.println("OVO JE EMAIL REGISTROVANOG KORISNIKA "+email.getEmail());
	}
}
