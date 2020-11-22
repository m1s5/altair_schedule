package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Lesson;
import io.kissmara.altair_schedule.model.lesson.entities.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TutorService {
    @Autowired
    TutorRepository tutorRepository;

    public List<Tutor> getTutors(){
        List<Tutor> tutors = new ArrayList<>();
        tutorRepository.findAll().forEach(tutors::add);
        return tutors;
    }

    public boolean addTutor(Tutor tutor){
        if (isAdded(tutor))  return false;
        tutorRepository.save(tutor);
        return true;
    }

    public void removeTutor(Tutor tutor){
        tutorRepository.delete(tutor);
    }

    public boolean isAdded(Tutor tutor){
        return getTutors().stream().anyMatch(tutor::equals);


        /*StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        tutorRepository.findAll().iterator(),
                        Spliterator.ORDERED
                ),
                false
        ).anyMatch(tutor::equals);*/
    }
}
