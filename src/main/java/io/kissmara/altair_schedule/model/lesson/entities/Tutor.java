package io.kissmara.altair_schedule.model.lesson.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tutor {
    @Id
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
