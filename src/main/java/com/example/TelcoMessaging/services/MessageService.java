package com.example.TelcoMessaging.services;


import com.example.TelcoMessaging.entities.MessageEntity;
import com.example.TelcoMessaging.repositories.MessageRepository;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.List;
import java.util.Optional;

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

        //initialize the repositry
        messageRepository = m;

        //activemq connection on port 61616
        connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection = connectionFactory.createConnection();
        connection.start();

        //context between producer and consumer
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //queue with the given name
        destination = session.createQueue("jms.destination");

        //create producer for the session
        messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //create consumer for the session
        consumer = session.createConsumer(destination);

    }

    public List<MessageEntity> getAllMessages(){
        return messageRepository.findAll();
    }

    public MessageEntity findByText(String text){
        MessageEntity temp;
        temp = messageRepository.findByText(text);
        return temp;
    }

    public Optional<MessageEntity> findById(Long id){
        Optional<MessageEntity> temp;
        temp = messageRepository.findById(id);
        return temp;
    }

    public void addMessage(MessageEntity messageEntity){messageRepository.save(messageEntity);}

    public MessageEntity receiveMessage() throws JMSException {
        Message message = consumer.receive(1000);
        //if the user want to receive a message with the receivebutton but there is no more in the queue
        if(message == null)
            return null;
        return new MessageEntity(((TextMessage)message).getText());
    }

    public void sendMessage(String message) throws JMSException {
        //create a textmessage with the parameter
        TextMessage textmessage = session.createTextMessage(message);

        //send the created textmessage
        messageProducer.send(textmessage);
    }

    public void deleteAll(){
        messageRepository.deleteAll();
    }

    public Optional<MessageEntity> deleteById(Long id){
        Optional<MessageEntity> temp = this.findById(id);
        if(temp.isEmpty())
            return null;
        messageRepository.deleteById(id);
        return temp;
    }

}
