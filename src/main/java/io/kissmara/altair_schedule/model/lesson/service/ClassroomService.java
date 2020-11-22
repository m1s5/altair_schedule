package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;


    public List<Classroom> getClassrooms(){
        List<Classroom> classrooms = new ArrayList<>();
        classroomRepository.findAll().forEach(classrooms::add);
        return classrooms;
    }

    public boolean addClassroom(Classroom classroom){
        if (isAdded(classroom))  return false;
        classroomRepository.save(classroom);
        return true;
    }

    public void removeClassroom(Classroom classroom){
        classroomRepository.delete(classroom);
    }

    public boolean isAdded(Classroom classroom){
        return getClassrooms().stream().anyMatch(classroom::equals);


    }
}
