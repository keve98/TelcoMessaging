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

    @Autowired
    public MessageService(MessageRepository m){this.messageRepository = m;}

    public List<MessageEntity> getAllMessages(){
        return messageRepository.findAll();
    }

    public MessageEntity findByText(String text){
        MessageEntity temp = null;
        temp = messageRepository.findByText(text);
        return temp;
    }

    public void addMessage(MessageEntity messageEntity){messageRepository.save(messageEntity);}

    public String receiveMessage() throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("jms.destination");

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive(1000);
        if(message != null)
            return ((TextMessage) message).getText();
        return "No received message";
    }

    public void sendMessage(String message) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("jms.destination");

        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage textmessage = session.createTextMessage(message);

        messageProducer.send(textmessage);


        session.close();
    }

    public void setIDS(Long l){
        MessageEntity m = new MessageEntity();
        m.setIds(l);
    }


}
