package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.Domain;
import io.kissmara.altair_schedule.model.Lesson;
import io.kissmara.altair_schedule.model.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class DefaultController {

    public final LessonService lessonService;
    public DefaultController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/")
    public String getSchedule(Model model){
        List<Lesson> schedule = lessonService.getSchedule();
        schedule.sort(Comparator.comparing(Lesson::getDate)
                .thenComparing(Lesson::getTime));
        model.addAttribute("lessons", schedule);
        return "schedule";
    }


    @GetMapping("/requestLesson")
    public String requestLesson(Model model){
        Lesson request = new Lesson();
        model.addAttribute("request", request);
        model.addAttribute("domains", Domain.values());
        model.addAttribute("now", LocalDate.now());
        return "requestLesson";
    }
    @PostMapping("/requestLesson")
    public String requestLesson(@ModelAttribute("request") Lesson request) {
        if(lessonService.addLesson(request))
        return "okRequest";
        return "failedRequest";
    }


    @GetMapping("confirmRequests")
    public String confirmLessons(Model model){
        model.addAttribute("requests", lessonService.getRequests());
        model.addAttribute("checked", new ArrayList<Integer>());
        return "confirmRequests";
    }
    @PostMapping("confirmRequests")
    public String confirmLessons(@ModelAttribute("checked") ArrayList<Integer> ids){
        return "okConfirm";
    }


}