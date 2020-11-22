package io.kissmara.altair_schedule;

import io.kissmara.altair_schedule.model.lesson.entities.Domain;
import io.kissmara.altair_schedule.model.lesson.entities.Lesson;
import io.kissmara.altair_schedule.model.lesson.service.LessonService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AltairScheduleApplicationTests {

    private final LessonService lessonService = new LessonService();



    @Test
    void contextLoads() {
    }



    /*
    *  public Lesson(Integer id, String subject, Domain domain,
           String tutor, String assistant, String classroom,
           Date date, LocalDateTime dateTime, Integer duration, boolean isAccepted){
    * */
    @DisplayName("isOverlapped Test")
    @RepeatedTest(4)
    void isOverlappedTest(RepetitionInfo repetitionInfo){
/*
        Lesson lesson = new Lesson(1, "", Domain.CHEMISTRY, "А", "", "A100",
                LocalDateTime.of(LocalDate.of(2020, 8, 14),
                        LocalTime.of(12, 0)),
                1, true);


        List<Lesson> lessons;
        switch (repetitionInfo.getCurrentRepetition()) {
            //////////////////
            //return false
            /////////////////
            case 1: {
                lessons = new ArrayList<>() {{
                    //equal id and assistant "" ignored
                    add(new Lesson(
                            1, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(12, 0)),
                            1, true));
                    //different dates(far)
                    add(new Lesson(
                            2, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2021, 8, 14),
                                    LocalTime.of(12, 0)),
                            1, true));
                    //1 different dates(close Before)
                    add(new Lesson(
                            3, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(11, 15)),
                            1, true));
                    //1 different dates(close After)
                    add(new Lesson(
                            4, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(12, 45)),
                            1, true));
                    //2 different dates(close Before)
                    add(new Lesson(
                            5, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(10, 30)),
                            2, true));
                    //2 different dates(close After)
                    add(new Lesson(
                            6, "", Domain.CHEMISTRY, "А", "", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(13, 30)),
                            2, true));
                }};
                assertFalse(lessonService.isOverlapped(lesson, lessons));
            }
            break;
            //////////////////
            //Date x Tutor
            /////////////////
            case 2: {

                lessons = new ArrayList<>() {{
                    add(new Lesson(2, "1", Domain.CHEMISTRY, "А", "5", "A200",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(12, 0)),
                            1, true));
                }};
                assertTrue(lessonService.isOverlapped(lesson, lessons));
            }
            break;
            //////////////////
            //Date x Classroom
            /////////////////
            case 3: {

                lessons = new ArrayList<>() {{
                    add(new Lesson(2, "", Domain.CHEMISTRY, "B", "s", "A100",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(12, 0)),
                            1, true));
                }};
                assertTrue(lessonService.isOverlapped(lesson, lessons));
            }
            break;
            //////////////////
            //Date x Assistant
            /////////////////
            case 4: {
                lesson.setAssistant("A");
                lessons = new ArrayList<>() {{
                    add(new Lesson(2, "", Domain.CHEMISTRY, "B", "A", "A200",
                            LocalDateTime.of(LocalDate.of(2020, 8, 14),
                                    LocalTime.of(12, 0)),
                            1, true));
                }};
                assertTrue(lessonService.isOverlapped(lesson, lessons));
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + repetitionInfo.getCurrentRepetition());
        }
    */}


}
