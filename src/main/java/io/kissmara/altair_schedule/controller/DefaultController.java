package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    public LessonService lessonService;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime time;


    @GetMapping("/")
    public String getSchedule(Model model){
        List<Lesson> schedule = lessonService.getSchedule();
        schedule.sort(Comparator.comparing(Lesson::getDateTime));
        model.addAttribute("lessons", schedule);
        return "schedule";
    }


    @GetMapping("/requestLesson")
    public String requestLesson(Model model){
        Lesson request = new Lesson();
        model.addAttribute("request", request);
        model.addAttribute("domains", Domain.values());
        time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        model.addAttribute("minTime", time);
        time = LocalDateTime.now().plusMonths(6).truncatedTo(ChronoUnit.MINUTES);
        model.addAttribute("maxTime", time);
        return "requestLesson";
    }
    @PostMapping("/requestLesson")
    public String requestLesson(@ModelAttribute("request") Lesson request) {
        if(lessonService.addRequest(request))
            return "okRequest";
        return "failedRequest";
    }


    @GetMapping("/manageLessons")
    public String manageLessons(Model model){
        LessonDto requests = new LessonDto(lessonService.getRequests());
        LessonDto schedule = new LessonDto(lessonService.getSchedule());

        model.addAttribute("requests", requests);
        model.addAttribute("schedule", schedule);
        return "manageLessons";
    }

    @PostMapping("/confirmRequests")
    public String confirmRequests(Model model, @ModelAttribute("form") LessonDto form){
        int size = form.getLessons().size();
        System.out.println(size);
        if(form.getLessons().stream().noneMatch(Lesson::getIsAccepted)) return "nothingToConfirm";
        List<Integer> failedList = lessonService.lessonTransactionByObject(form.getLessons());
        if(failedList.isEmpty())
            return "okConfirm";
        model.addAttribute("failedList", failedList);
        return "failedConfirm";
    }

    @PostMapping("/discardRequests")
    public String discardRequest(Model model, @ModelAttribute("form") LessonDto form){

        if(form.getLessons().stream().allMatch(Lesson::getIsActive)) return "nothingToDiscard";

        for(Lesson lesson: form.getLessons()){
            if(!lesson.getIsActive()) lessonService.removeLesson(lesson);
        }
            return "okDiscard";
    }

    @PostMapping("/discardLessons")
    public String discardLessons(Model model, @ModelAttribute("form") LessonDto form){

        if(form.getLessons().stream().allMatch(Lesson::getIsAccepted)) return "nothingToDiscard";
        for(Lesson lesson: form.getLessons()){
            if(!lesson.getIsAccepted()) lessonService.addRequest(lesson);
        }
            return "okDiscard";
    }


}