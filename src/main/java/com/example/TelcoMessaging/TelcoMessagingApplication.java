package com.example.TelcoMessaging;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@SpringBootApplication
public class TelcoMessagingApplication {

	public static void main(String[] args) {

		SpringApplication.run(TelcoMessagingApplication.class, args);
	}



}
