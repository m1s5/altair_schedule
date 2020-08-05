package io.kissmara.altair_schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import io.kissmara.altair_schedule.model.Domain;
import io.kissmara.altair_schedule.model.Lesson;
import io.kissmara.altair_schedule.model.LessonService;

@Controller
public class DefaultController {
    @Autowired
    public LessonService lessonService;



    @GetMapping("/addLesson")
    public String addLesson(Model model){
        Domain domains;
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);
        model.addAttribute("domains", Domain.values());
        return "addLesson";
    }
    @PostMapping("/addLesson")
    public String addLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.addLesson(lesson);
        return "ok";
    }



    @GetMapping("/")
    public String getAllLessons(Model model){
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "all_lessons";
    }

    @GetMapping("/getAllLessons")
    public String geLesson(Model model){
        model.addAttribute("lesson", lessonService.getLesson(1));
        return "all_lessons";
    }
}
