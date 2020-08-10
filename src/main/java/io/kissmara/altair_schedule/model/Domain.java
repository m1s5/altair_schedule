package io.kissmara.altair_schedule.model;

public enum Domain {
    CHEMISTRY("Химия"),
    MATH("Математика"),
    PHYSICS("Физика"),
    CODE("Программирование"),
    RADIO("Радиоэлектроника"),
    SCHEDULED("");

    private final String name;
    Domain(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
