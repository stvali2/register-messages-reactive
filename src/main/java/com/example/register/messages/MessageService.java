package com.example.register.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessagesRepository messagesRepository;
    private final Transformer transformer;

    @Autowired
    public MessageService(MessagesRepository messagesRepository, Transformer transformer) {
        this.messagesRepository = messagesRepository;
        this.transformer = transformer;
    }

    public Flux<MessageDto> getAllLive() {
        return messagesRepository
                .findAllBy()
                .map(transformer::convertToDto);
    }

    public Flux<MessageDto> getAll() {
        return messagesRepository
                .findAll()
                .map(transformer::convertToDto);
    }

    public Mono<MessageDto> create(MessageDto message) {
        return messagesRepository
                .insert(transformer.convertFromDto(message))
                .map(transformer::convertToDto);
    }
}
