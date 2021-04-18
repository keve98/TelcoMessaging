package com.example.TelcoMessaging.entities;


import com.example.TelcoMessaging.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class MessageEntity {


    static Long ids = 0L;

    @Id
    @Column(name = "id")
    private Long Id;

    @Column(name = "text")
    private String Text;


    public MessageEntity(String t){
        ids += 1;
        this.Id = ids;
        this.Text = t;
    }

    public MessageEntity() {}


    public void setIds(Long ids) {
        MessageEntity.ids = ids;
    }

    public String toString(){
        return "[" + this.Id + "]" + "  " + this.Text;
    }

}
