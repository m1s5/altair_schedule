package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.Domain;
import io.kissmara.altair_schedule.model.Lesson;
import io.kissmara.altair_schedule.model.LessonService;
import io.kissmara.altair_schedule.model.RequestConfirmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
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


    @GetMapping("confirmRequests")
    public String confirmLessons(Model model){
        RequestConfirmDto form = new RequestConfirmDto(lessonService.getRequests());
        model.addAttribute("requests", form);
        model.addAttribute("lessons", lessonService.getSchedule());
        model.addAttribute("form", form);
        return "confirmRequests";
    }
    @PostMapping("confirmRequests")
    public String confirmLessons(Model model, @ModelAttribute("form") RequestConfirmDto form){

        if(form.getRequests().stream().noneMatch(Lesson::getIsAccepted)) return "nothingToConfirm";
        List<Integer> failedList = lessonService.lessonTransactionByObject(form.getRequests());
        if(failedList.isEmpty())
            return "okConfirm";
        model.addAttribute("failedList", failedList);
        return "failedConfirm";
    }


}