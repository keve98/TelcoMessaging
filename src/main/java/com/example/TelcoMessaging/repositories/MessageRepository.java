package com.example.TelcoMessaging.repositories;


import com.example.TelcoMessaging.entities.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

    @Query(value ="select m from MessageEntity m order by  m.Id")
    List<MessageEntity> findAll();


    @Query(value = "select m from MessageEntity m where m.Text like :#{#txt}")
    MessageEntity findByText(@Param("txt") String txt);


}
