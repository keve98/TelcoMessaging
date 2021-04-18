package com.example.TelcoMessaging.controllers;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.services.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;

@RestController
public class MessageController {


    MessageService messageService;


    public MessageController(MessageService m){
        this.messageService = m;
    }


    @GetMapping("/")
    public Long setIDS(){
        List<MessageEntity> tmp = messageService.getAllMessages();
        Long t = 0L;
        for(MessageEntity m : tmp){
            t++;
        }
        messageService.setIDS(t);
        return t;
    }



    @GetMapping("/messages")
    public String getMessages() {
        setIDS();
        List<MessageEntity> messages = messageService.getAllMessages();
        String ret = "";
        for (MessageEntity m : messages) {
            ret += m.toString();
        }
        return ret;
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) throws JMSException {
        setIDS();
        messageService.sendMessage(message);


        if(messageService.findByText(message) == null){
            messageService.addMessage(new MessageEntity(message));
        }

        return "'" + message+"'" +" sent";
    }

    @GetMapping("/receive")
    public String receiveMessage() throws JMSException {
        setIDS();
       return "Received message:    " + messageService.receiveMessage();
    }


}
