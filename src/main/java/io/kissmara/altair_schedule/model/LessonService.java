package io.kissmara.altair_schedule.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    private static void accept(Lesson lesson) {
        lesson.setIsAccepted(true);
    }


    public List<Lesson> getRequests(){
        List<Lesson> requests = new ArrayList<>();
        lessonRepository.findAll().forEach(requests::add);
        return requests.stream().filter(lesson -> !lesson.getIsAccepted()).collect(Collectors.toList());
    }

    public List<Lesson> getSchedule(){
        List<Lesson> lessons = new ArrayList<>();
        lessonRepository.findAll().forEach(lessons::add);
        return lessons.stream().filter(Lesson::getIsAccepted).collect(Collectors.toList());
    }

    public boolean addRequest(Lesson lesson){
        if(!isOverlapped(lesson, getSchedule())) {
            lessonRepository.save(lesson);
            return true;
        }   return false;
    }

    public Optional<Lesson> getLesson(Integer id){
        return lessonRepository.findById(id);
    }

    public List<Integer> lessonTransactionById(List<Integer> ids){
        List<Integer> failed = new ArrayList<>();
        for (int id : ids)
            if (getLesson(id).isEmpty() || isOverlapped(getLesson(id).get(), getSchedule())) failed.add(id);
        if(failed.isEmpty())
            lessonRepository.findAllById(ids).forEach(LessonService::accept);
        return failed;
    }
    public List<Integer> lessonTransactionByObject(List<Lesson> requests){
        List<Integer> failed = new ArrayList<>();
        requests = requests.stream().filter(Lesson::getIsAccepted).collect(Collectors.toList());
        for (Lesson request : requests)
            if (isOverlapped(request, getSchedule()) || isOverlapped(request, requests)) failed.add(request.getId());
        if(failed.isEmpty())
            lessonRepository.saveAll(requests);
        return failed;
    }

    public void addLesson(Integer id){
        lessonRepository.findById(id).ifPresent(LessonService::accept);
    }


    public boolean isOverlapped(Lesson lesson, List<Lesson> lessonsList){


        return lessonsList.stream()
                .filter(lesson1 -> !lesson1.getId().equals(lesson.getId()))

                .filter(lesson1 -> !(lesson1.getDateTime().isAfter(lesson.getDateTime()
                                            .plusMinutes(45*lesson.getDuration())) ||
                                     lesson1.getDateTime().plusMinutes(45*lesson1.getDuration())
                                            .isBefore(lesson.getDateTime())))

                .anyMatch(lesson1 ->  lesson1.getClassroom().equals(lesson.getClassroom()) ||
                                    lesson1.getTutor().equals(lesson.getTutor())         ||
                                   (lesson1.getAssistant().equals(lesson.getAssistant()) &&
                                   !lesson1.getAssistant().isEmpty()));
    }

}
