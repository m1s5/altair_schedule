package io.kissmara.altair_schedule.model.lesson.entities;

public enum Domain {
    CHEMISTRY("Химия"),
    MATH("Математика"),
    PHYSICS("Физика"),
    CODE("Программирование"),
    RADIO("Радиоэлектроника"),
    SCHEDULED("Из расписания");

    //@Id
    private final String domain;
    Domain(String name){
        this.domain = name;
    }

    Domain() { domain = "";}

    public String getDomain() {
        return domain;
    }
}
