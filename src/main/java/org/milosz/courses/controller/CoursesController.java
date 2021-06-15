package org.milosz.courses.controller;

import lombok.AllArgsConstructor;
import org.milosz.courses.model.Course;
import org.milosz.courses.repository.CoursesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CoursesController {

    private final CoursesRepository coursesRepository;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Course> getCourses() {
        return coursesRepository.findAll();
    }

    @GetMapping("/{name}")
    public Mono<ResponseEntity<Course>> getCourse(@PathVariable String name) {
        return coursesRepository.findById(name)
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }
}
