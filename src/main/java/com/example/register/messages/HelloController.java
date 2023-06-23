package com.example.register.messages;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @Operation(tags = "Hello world message", description = "View hello world message")
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getHelloWorld() {
        log.info("get hello world message");
        return Mono.just("Hello world!");
    }
}
