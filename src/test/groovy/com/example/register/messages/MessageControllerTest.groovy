package com.example.register.messages


import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import spock.lang.Specification

@AutoConfigureWebTestClient
@WebFluxTest(controllers = MessageController)
@ContextConfiguration(classes = [MessageService, MessageController])
@EnableAutoConfiguration
class MessageControllerTest extends Specification {

    private WebTestClient webTestClient

    @Autowired
    private ApplicationContext context

    @SpringBean
    private MessageService messageService = Mock(MessageService)

    def setup() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build()
    }

    def "finding all messages should work"() {
        given: 'an message controller'

        when: 'get request is performed'
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri('/api/v1/messages')
                .exchange()

        then: 'response status should be ok'
        response.expectStatus().isOk()
        and: 'result is successful'
        response.expectBodyList(Message).isEqualTo([])
        and: 'message services get all should be called'
        1 * messageService.getAll() >> Flux.fromIterable(new ArrayList<MessageDto>())
    }
}
