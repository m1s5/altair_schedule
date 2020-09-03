package io.kissmara.altair_schedule.model.lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    TutorRepository tutorRepository;
}
