package com.example.register.messages;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@OpenAPIDefinition(
        info = @Info(title = "Register messages API",
                description = "Register messages reactive Spring WebFlux",
                termsOfService = "Copyright - 2019",
                version = "0.0.1"
        ),
        tags = @Tag(name = "Register messages")
        // http://localhost:8080/swagger-ui.html
)
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(tags = "Register messages", description = "View a list of available messages")
    @GetMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MessageDto> getAll() {
        log.info("get all messages");
        return messageService.getAll();
    }

    @Operation(tags = "Register messages", description = "View a list of available messages live ...")
    @GetMapping(value = "/messages/live", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageDto> getAllLive() {
        log.info("get all messages live ...");
        return messageService.getAllLive();
    }

    @Operation(tags = "Register messages", description = "Create a message")
    @PostMapping(value = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MessageDto> create(@RequestBody MessageDto messageDto) {
        log.info("create message: {}", messageDto);
        return messageService.create(messageDto);
    }
}
