package io.kissmara.altair_schedule.model;

import java.util.ArrayList;
import java.util.List;

public class LessonDto {
    private List<Lesson> lessons;

    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public LessonDto(){
        lessons = new ArrayList<>();
    }
    public LessonDto(List<Lesson> lessons){
        this.lessons=lessons;
    }
    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }

}
