package io.kissmara.altair_schedule.model.lesson.entities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Data
public class Lesson {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Integer id;
    @NotNull private String subject;
    @NotNull private Domain domain;
    @NotNull @ManyToOne(optional = false) private Tutor tutor;
    @ManyToOne private Assistant assistant;
    @NotNull @ManyToOne(optional = false) private Classroom classroom;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @NotNull private LocalDateTime dateTime;
    @NotNull private Integer duration;
    private boolean isAccepted = false;
    private boolean isNotActive = false;




    public boolean getIsNotAccepted() {
        return !isAccepted;
    }

    public void setIsNotAccepted(boolean accepted) {
        isAccepted = !accepted;
    }
}