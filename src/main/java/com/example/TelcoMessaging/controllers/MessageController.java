package com.example.TelcoMessaging.controllers;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/send/{message}")
    public ResponseEntity<MessageEntity> sendMessage(@PathVariable String message) throws JMSException {
        messageService.sendMessage(message);
        MessageEntity newEntity = new MessageEntity(message);
        if(messageService.findByText(message) == null){
            messageService.addMessage(newEntity);
        }
        return new ResponseEntity<>(newEntity, HttpStatus.OK);
    }

    @GetMapping("/receive")
    public ResponseEntity<MessageEntity> receiveMessage() throws JMSException {
       return new ResponseEntity<>( messageService.receiveMessage(), HttpStatus.OK);
    }

    @DeleteMapping("/messages/delete")
    public ResponseEntity<?> deleteAllMessages(){
        messageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/messages/delete/{id}")
    public ResponseEntity<Optional<MessageEntity>> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(messageService.deleteById(id), HttpStatus.OK);
    }

}
