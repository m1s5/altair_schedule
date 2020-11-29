package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.lesson.entities.*;
import io.kissmara.altair_schedule.model.lesson.service.AssistantService;
import io.kissmara.altair_schedule.model.lesson.service.ClassroomService;
import io.kissmara.altair_schedule.model.lesson.service.LessonService;
import io.kissmara.altair_schedule.model.lesson.LessonsDto;
import io.kissmara.altair_schedule.model.lesson.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Controller
public class RequestController {
    @Autowired public LessonService lessonService;
    @Autowired public TutorService tutorService;
    @Autowired public ClassroomService classroomService;
    @Autowired public AssistantService assistantService;


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime time;


    @GetMapping("/")
    public String getSchedule(Model model){
        {
            Tutor tutor = new Tutor();
            tutor.setName("Дмитрий Марач");
            tutorService.addTutor(tutor);
            Classroom classroom = new Classroom();
            classroom.setClassroom("д206");
            classroomService.addClassroom(classroom);
            Assistant assistant = new Assistant();
            assistant.setName("Добби");
            assistantService.addAssistant(assistant);
        }
        List<Lesson> schedule = lessonService.getSchedule();
        schedule.sort(Comparator.comparing(Lesson::getDateTime));
        model.addAttribute("lessons", schedule);
        return "schedule";
    }


    @GetMapping("/user/requestLesson")
    public String requestLesson(Model model){
        Lesson request = new Lesson();
        List<Assistant> assistants = assistantService.getAssistants();
        /*Assistant empty_assistant = new Assistant();
        empty_assistant.setName("Без ассистента");
        assistants.add(empty_assistant);*/
        assistants.add(null);
        model.addAttribute("request", request);
        model.addAttribute("domains", Domain.values());
        model.addAttribute("tutors", tutorService.getTutors());
        model.addAttribute("assistants", assistants);
        model.addAttribute("classrooms", classroomService.getClassrooms());

        time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        model.addAttribute("minTime", time);
        time = LocalDateTime.now().plusMonths(6).truncatedTo(ChronoUnit.MINUTES);
        model.addAttribute("maxTime", time);
        return "request/requestLesson";
    }
    @PostMapping("/user/requestLesson")
    public String requestLesson(@ModelAttribute("request") Lesson request) {
        if (lessonService.addRequest(request))
            return "request/okRequest";
        return "request/failedRequest";
    }

    @PostMapping("/admin/confirmRequests")
    public String confirmRequests(Model model, @ModelAttribute("form") LessonsDto form){
        int size = form.getLessons().size();
        if(form.getLessons().stream().noneMatch(Lesson::getIsAccepted)) return "/manage/lesson/confirm/nothingToConfirm";
        List<Integer> failedList = lessonService.lessonTransactionByObject(form.getLessons());
        if(failedList.isEmpty())
            return "/manage/lesson/confirm/okConfirm";
        model.addAttribute("failedList", failedList);
        return "/manage/lesson/confirm/failedConfirm";
    }

    @PostMapping("/admin/discardRequests")
    public String discardRequest(@ModelAttribute("form") LessonsDto form){

        if(form.getLessons().stream().noneMatch(Lesson::getIsNotActive)) return "/manage/lesson/discard/nothingToDiscard";

        for(Lesson lesson: form.getLessons()){
            if(lesson.getIsNotActive()) lessonService.removeLesson(lesson);
        }
            return "/manage/lessons/discard/okDiscard";
    }

    @PostMapping("/admin/discardLessons")
    public String discardLessons(@ModelAttribute("form") LessonsDto form){

        if(form.getLessons().stream().allMatch(Lesson::getIsAccepted)) return "/manage/lesson/discard/nothingToDiscard";
        for(Lesson lesson: form.getLessons()){
            if(!lesson.getIsAccepted()) lessonService.addRequest(lesson);
        }
            return "/manage/lesson/discard/okDiscard";
    }


}