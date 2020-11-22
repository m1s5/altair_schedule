package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Assistant;
import io.kissmara.altair_schedule.model.lesson.entities.Classroom;
import org.springframework.data.repository.CrudRepository;

public interface AssistantRepository extends CrudRepository<Assistant, String> {
}