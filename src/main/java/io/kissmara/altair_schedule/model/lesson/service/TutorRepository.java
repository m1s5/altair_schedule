package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Tutor;
import org.springframework.data.repository.CrudRepository;

public interface TutorRepository extends CrudRepository<Tutor, String> {
}
