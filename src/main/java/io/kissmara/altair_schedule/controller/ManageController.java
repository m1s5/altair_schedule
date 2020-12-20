package io.kissmara.altair_schedule.controller;

import io.kissmara.altair_schedule.model.lesson.LessonsDto;
import io.kissmara.altair_schedule.model.lesson.entities.*;
import io.kissmara.altair_schedule.model.lesson.service.AssistantService;
import io.kissmara.altair_schedule.model.lesson.service.ClassroomService;
import io.kissmara.altair_schedule.model.lesson.service.LessonService;
import io.kissmara.altair_schedule.model.lesson.service.TutorService;
import io.kissmara.altair_schedule.model.user.User;
import io.kissmara.altair_schedule.model.user.UserService;
import org.apache.poi.util.NotImplemented;
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
import java.util.List;

@Controller
public class ManageController {
    @Autowired private TutorService tutorService;
    @Autowired private AssistantService assistantService;
    @Autowired private ClassroomService classroomService;
    @Autowired private LessonService lessonService;
    @Autowired private UserService userService;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime time;

    @GetMapping("/admin/manageLessons")
    public String manageLessons(Model model){
        LessonsDto requests = new LessonsDto(lessonService.getRequests());
        LessonsDto schedule = new LessonsDto(lessonService.getSchedule());
        //BaseScheduleDto baseScheduleDto = new BaseScheduleDto();

        model.addAttribute("requests", requests);
        model.addAttribute("schedule", schedule);
        return "manage/manageLessons";
    }

    //TODO: дописать обработку файлов
    @NotImplemented
    @PostMapping("/admin/manageLessons")
    public String confirmBaseSchedule(@RequestParam("baseSchedule") MultipartFile[] files){
        System.out.println(files.length);
        return "manage/manageLessons";
    }

    //TUTOR
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

    //ASSISTANT
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

    //CLASSROOM
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

    //USER
    /*TODO*/
    @GetMapping("/admin/addUser")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "manage/user/addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") User user){
        if(userService.addUser(user))
            return "manage/user/okAddUser";
        return "manage/user/failedAddUser";
    }

    //LESSON
    @GetMapping("/admin/addLesson")
    public String addLesson(Model model){
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
        return "manage/lesson/add/addLesson";
    }
    @PostMapping("/admin/addLesson")
    public String addLesson(@ModelAttribute("request") Lesson request) {
        if (lessonService.addRequest(request)){
            lessonService.addLesson(request.getId());
            lessonService.addRequest(request);
            return "manage/lesson/add/okAddLesson";
        }
        return "manage/lesson/add/failedAddLesson";
    }

}