package io.kissmara.altair_schedule.model.lesson.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

public enum Domain {
    CHEMISTRY("Химия"),
    MATH("Математика"),
    PHYSICS("Физика"),
    CODE("Программирование"),
    RADIO("Радиоэлектроника"),
    SCHEDULED("");

    @Id
    private final String name;
    Domain(String name){
        this.name = name;
    }

    Domain() { name = "";}

    public String getName() {
        return name;
    }
}
