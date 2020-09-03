package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
