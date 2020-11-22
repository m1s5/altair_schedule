package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.lesson.entities.Assistant;
import io.kissmara.altair_schedule.model.lesson.entities.Classroom;
import io.kissmara.altair_schedule.model.lesson.entities.Domain;
import io.kissmara.altair_schedule.model.lesson.entities.Tutor;
import io.kissmara.altair_schedule.model.lesson.service.AssistantService;
import io.kissmara.altair_schedule.model.lesson.service.ClassroomService;
import io.kissmara.altair_schedule.model.lesson.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManageController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AssistantService assistantService;
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/admin/addTutor")
    public String addTutor(Model model){
        Tutor tutor = new Tutor();
        model.addAttribute("tutor", tutor);
        model.addAttribute("domains", Domain.values());
        return "manage/tutor/addTutor";
    }

    @PostMapping("/admin/addTutor")
    public String addTutor(@ModelAttribute("tutor") Tutor tutor){
        if(tutorService.addTutor(tutor))
            return "manage/tutor/okAddTutor";
        return "manage/tutor/failedAddTutor";
    }

    @GetMapping("/admin/addAssistant")
    public String addAssistant(Model model){
        Assistant assistant = new Assistant();
        model.addAttribute("assistant", assistant);
        return "manage/assistant/addAssistant";
    }

    @PostMapping("/admin/addAssistant")
    public String addAssistant(@ModelAttribute("assistant") Assistant assistant){
        if(assistantService.addAssistant(assistant))
            return "manage/assistant/okAddAssistant";
        return "manage/assistant/failedAddAssistant";
    }

    @GetMapping("/admin/addClassroom")
    public String addClassroom(Model model){
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        model.addAttribute("domains", Domain.values());
        return "manage/classroom/addClassroom";
    }

    @PostMapping("/admin/addClassroom")
    public String addClassroom(@ModelAttribute("classroom") Classroom classroom){
        if(classroomService.addClassroom(classroom))
            return "manage/classroom/okAddClassroom";
        return "manage/classroom/failedAddClassroom";
    }
}
