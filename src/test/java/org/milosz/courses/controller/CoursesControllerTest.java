package org.milosz.courses.controller;

import org.junit.jupiter.api.Test;
import org.milosz.courses.model.Course;
import org.milosz.courses.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureWebTestClient
class CoursesControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    CoursesRepository coursesRepository;

    @Test
    void getStringsTest() {
        webTestClient.get()
                .uri("/courses")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_STREAM_JSON_VALUE)
                .expectBodyList(Course.class)
                .hasSize(5);
    }

    @Test
    void getStringsTest2() {
        webTestClient.get()
                .uri("/courses/math")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Course.class)
                .value(Course::getName, equalTo("math"));
    }
}