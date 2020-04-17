package com.example.register.messages.boundary

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import spock.lang.Ignore
import spock.lang.Specification

@WebFluxTest(controllers = MessageController)
class MessageControllerTest extends Specification {
  @Autowired
  private WebTestClient testClient

  @SpringBean
  private final MessageService service = Mock(MessageService)

  @Ignore
  def "finding all messages should work"() {
    given: ''

    when: 'GET request is performed'
    WebTestClient.ResponseSpec response = testClient
        .get()
        .uri('/api/v1/messages')
        .accept(MediaType.APPLICATION_JSON)
        .exchange()

    then: ''
    1 * service.getAll() >> Flux.empty()

    and: 'messages are retrieved from messagesRepository'
    response.expectStatus().isOk()

    and: 'result is successful'
    response.expectBody(String).isEqualTo("")
  }
}
