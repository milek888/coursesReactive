package org.milosz.courses.repository;

import org.milosz.courses.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends ReactiveMongoRepository<Course, String> {
}
