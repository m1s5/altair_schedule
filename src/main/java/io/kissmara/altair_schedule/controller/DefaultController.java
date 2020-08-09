package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.Domain;
import io.kissmara.altair_schedule.model.Lesson;
import io.kissmara.altair_schedule.model.LessonService;
import io.kissmara.altair_schedule.model.RequestConfirmDto;
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
    @Autowired
    public LessonService lessonService;


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
        /*if(form.getRequests().stream().noneMatch(Lesson::getIsAccepted)) return "nothingToConfirm";
        for(int i = 0; i < lessonService.getRequests().size(); i++){
            lessonService.getRequests().get(i).setIsAccepted(form.getRequests().get(i).getIsAccepted());
        }

        List<Integer> failedList = lessonService.lessonTransactionByObject(form.getRequests());
        if(failedList.isEmpty())
            return "okConfirm";
        else model.addAttribute("failedList", failedList);
        return "failedConfirm";*/

        if(form.getRequests().stream().noneMatch(Lesson::getIsAccepted)) return "nothingToConfirm";
        List<Integer> failedList = lessonService.lessonTransactionByObject(form.getRequests());
        if(failedList.isEmpty())
            return "okConfirm";
        return "failedConfirm";
    }


}