package jsloup.r2dbc.controller;

import jsloup.r2dbc.model.Person;
import jsloup.r2dbc.repository.ReactiveRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {ControllerTest.Initializer.class})
public class ControllerTest {

    private static PostgreSQLContainer sqlContainer;

    static {
        sqlContainer = new PostgreSQLContainer<>("postgres")
                .withDatabaseName("person-db")
                .withUsername("sa")
                .withPassword("sa");
        sqlContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    ReactiveRepository reactiveRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void savePersonAndGet() {
        reactiveRepository.save(new Person(null, "John", 21)).block();

        webTestClient.get()
                .uri("/api/person")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());

    }
}
