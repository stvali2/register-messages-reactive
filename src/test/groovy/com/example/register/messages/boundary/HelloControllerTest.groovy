package com.example.register.messages.boundary


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@AutoConfigureWebTestClient
@WebFluxTest(controllers = HelloController)
@ContextConfiguration(classes = [HelloController])
@EnableAutoConfiguration
class HelloControllerTest extends Specification {
    @Autowired
    private WebTestClient webTestClient

    def "finding hello world message should work"() {
        given: 'an test controller'

        when: 'get request is performed'
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri('/api/v1/hello')
                .exchange()

        then: 'response status should be ok'
        response.expectStatus().isOk()
        and: 'result is successful'
        response.expectBody(String).isEqualTo("Hello world!")
    }
}
