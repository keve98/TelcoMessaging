package com.example.TelcoMessaging.services;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.repositories.MessageRepository;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    ActiveMQConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;
    MessageConsumer consumer;
    MessageProducer messageProducer;


    @Autowired
    public MessageService(MessageRepository m) throws JMSException {
        messageRepository = m;
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("jms.destination");
        messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        consumer = session.createConsumer(destination);

    }

    public List<MessageEntity> getAllMessages(){
        return messageRepository.findAll();
    }

    public MessageEntity findByText(String text){
        MessageEntity temp = null;
        temp = messageRepository.findByText(text);
        return temp;
    }

    public void addMessage(MessageEntity messageEntity){messageRepository.save(messageEntity);}

    public MessageEntity receiveMessage() throws JMSException {

        Message message = consumer.receive(1000);
        if(message == null)
            return null;
        return new MessageEntity(((TextMessage)message).getText());
    }

    public void sendMessage(String message) throws JMSException {
        TextMessage textmessage = session.createTextMessage(message);
        messageProducer.send(textmessage);
    }

    public void deleteAll(){
        messageRepository.deleteAll();
    }



}
