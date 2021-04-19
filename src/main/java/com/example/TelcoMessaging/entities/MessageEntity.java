package com.example.TelcoMessaging.entities;


import javax.persistence.*;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @SequenceGenerator(name="MessageSequence", sequenceName="seqmessageid", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="MessageSequence")
    @Column(name = "id")
    private Long Id;

    @Column(name = "text")
    private String Text;


    public MessageEntity(String t){
        this.Text = t;
    }

    public MessageEntity() {}

    public String getText() {
        return Text;
    }

    public String toString(){
        return "[" + this.Id + "]" + "  " + this.Text;
    }

}
