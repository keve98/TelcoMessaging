package com.example.TelcoMessaging.controllers;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;

import java.util.List;

@RestController
public class MessageController {


    MessageService messageService;


    public MessageController(MessageService m){
        this.messageService = m;
    }


    @GetMapping("/messages")
    public ResponseEntity<List<MessageEntity>> getMessages() {
        List<MessageEntity> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) throws JMSException {
        messageService.sendMessage(message);

        if(messageService.findByText(message) == null){
            messageService.addMessage(new MessageEntity(message));
        }
        return new ResponseEntity<>("Sent: " + message, HttpStatus.OK);
    }

    @GetMapping("/receive")
    public ResponseEntity<String> receiveMessage() throws JMSException {
       return new ResponseEntity<>("Received message:    " + messageService.receiveMessage(), HttpStatus.OK);
    }


}
