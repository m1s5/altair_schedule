package io.kissmara.altair_schedule.model.lesson;

import io.kissmara.altair_schedule.model.lesson.entities.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonsDto {
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public LessonsDto(){
        lessons = new ArrayList<>();
    }
    public LessonsDto(List<Lesson> lessons){
        this.lessons=lessons;
    }
    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

}
