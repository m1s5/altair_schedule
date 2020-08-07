package io.kissmara.altair_schedule.model;


import org.springframework.stereotype.Service;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

@Service
public class LessonService {

    private final LessonRepository requestRepository;
    private final LessonRepository scheduleRepository;

    public LessonService(LessonRepository requestRepository, LessonRepository scheduleRepository) {
        this.requestRepository = requestRepository;
        this.scheduleRepository = scheduleRepository;
    }

    /*Integer getLastId(){
        return (int) lessonRepository.count();
    }*/

    public List<Lesson> getRequests(){
        List<Lesson> requests = new ArrayList<>();
        requestRepository.findAll().forEach(requests::add);
        return requests;
    }

    public Optional<Lesson> getLesson(Integer id){
        return requestRepository.findById(id);
    }

    public boolean addLesson(Lesson lesson){
        if(!isOverlapped(lesson)) {
            requestRepository.save(lesson);
            return true;
        }   return false;
    }

    public void deleteLesson(Integer id){
        requestRepository.deleteById(id);
    }

    public void updateLesson(Integer id, Lesson lesson){
        requestRepository.save(lesson);
    }


    /*Integer getLastIdInSchedule(){
        return (int) lessonRepository.count();
    }*/

    public List<Lesson> getSchedule(){
        List<Lesson> lessons = new ArrayList<>();
        scheduleRepository.findAll().forEach(lessons::add);
        return lessons;
    }

    public Optional<Lesson> getLessonFromSchedule(Integer id){
        return scheduleRepository.findById(id);
    }

    public boolean addLessonToSchedule(Lesson lesson){
        if(!isOverlapped(lesson)) {
            scheduleRepository.save(lesson);
            return true;
        }   return false;
    }

    public boolean deleteLessonFromSchedule(Integer id){
        scheduleRepository.deleteById(id);
        return true;
    }

    public boolean updateLessonInSchedule(Integer id, Lesson lesson){
        scheduleRepository.save(lesson);
        return true;
    }

    private boolean isOverlapped(Lesson lesson){
        return getSchedule().stream()
                .filter(lesson1 -> lesson1.getDate().equals(lesson.getDate()))

                .filter(lesson1 -> (!(lesson1.getTime().isAfter(lesson.getTime()
                                             .plusMinutes(45*lesson.getDuration())) ||
                                      lesson1.getTime().plusMinutes(45*lesson1.getDuration())
                                             .isBefore(lesson.getTime()))))

                .filter(lesson1 ->  lesson1.getClassroom().equals(lesson.getClassroom()) ||
                                    lesson1.getTutor().equals(lesson.getTutor())         ||
                                   (lesson1.getAssistant().equals(lesson.getAssistant()) &&
                                   !lesson1.getAssistant().equals("-")))
                .count() >= 1;
    }

}
