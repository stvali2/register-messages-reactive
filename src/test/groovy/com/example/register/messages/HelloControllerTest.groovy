package com.example.register.messages


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
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
