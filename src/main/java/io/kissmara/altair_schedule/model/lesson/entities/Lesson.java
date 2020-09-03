package io.kissmara.altair_schedule.model.lesson.entities;


import io.kissmara.altair_schedule.model.lesson.entities.Domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Lesson {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull private String subject;
    @NotNull private Domain domain;
    @NotNull private String tutor;
    private String assistant;
    @NotNull private String classroom;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull private LocalDateTime dateTime;
    @NotNull private Integer duration;
    private boolean isAccepted = false;
    private boolean isNotActive = false;

    public Lesson(){}
    public Lesson(Integer id, String subject, Domain domain,
           String tutor, String assistant, String classroom,
           LocalDateTime dateTime, Integer duration, boolean isAccepted){
        super();
        this.id = id; this.subject=subject;
        this.domain = domain; this.tutor = tutor;
        this.assistant = assistant; this.classroom = classroom;
        this.dateTime = dateTime;
        this.duration = duration;
        this.isAccepted = isAccepted;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }
 
    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean getIsNotAccepted() {
        return !isAccepted;
    }

    public void setIsNotAccepted(boolean accepted) {
        isAccepted = !accepted;
    }

    public boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(boolean active) {
        isNotActive = active;
    }
}