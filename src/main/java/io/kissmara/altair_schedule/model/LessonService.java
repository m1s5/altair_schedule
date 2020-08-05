package io.kissmara.altair_schedule.model;


import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final LessonRepository scheduleRepository;

    public LessonService(LessonRepository lessonRepository, LessonRepository scheduleRepository) {
        this.lessonRepository = lessonRepository;
        this.scheduleRepository = scheduleRepository;
    }

    Integer getLastId(){
        return (int) lessonRepository.count();
    }

    public List<Lesson> getAllLessons(){
        List<Lesson> lessons = new ArrayList<>();
        lessonRepository.findAll().forEach(lessons::add);
        return lessons;
    }

    public Optional<Lesson> getLesson(Integer id){
        return lessonRepository.findById(id);
    }

    public void addLesson(Lesson lesson){
        lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer id){
        lessonRepository.deleteById(id);
    }

    public void updateLesson(Integer id, Lesson lesson){
        lessonRepository.save(lesson);
    }


    Integer getLastIdInSchedule(){
        return (int) lessonRepository.count();
    }

    public List<Lesson> getSchedule(){
        List<Lesson> lessons = new ArrayList<>();
        scheduleRepository.findAll().forEach(lessons::add);
        return lessons;
    }

    public Optional<Lesson> getLessonFromSchedule(Integer id){
        return scheduleRepository.findById(id);
    }

    public boolean addLessonToSchedule(Lesson lesson){
        scheduleRepository.save(lesson);
        return true;
    }

    public boolean deleteLessonFromSchedule(Integer id){
        scheduleRepository.deleteById(id);
        return true;
    }

    public boolean updateLessonInSchedule(Integer id, Lesson lesson){
        scheduleRepository.save(lesson);
        return true;
    }




}
