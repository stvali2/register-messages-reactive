package com.example.register.messages.control;

import com.example.register.messages.entity.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessagesRepository extends ReactiveMongoRepository<Message, String> {
    @Tailable
    Flux<Message> findAllBy();
}
