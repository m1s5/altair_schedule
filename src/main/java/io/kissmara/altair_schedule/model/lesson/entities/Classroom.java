package io.kissmara.altair_schedule.model.lesson.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {
    @Id
    private String name;
    //TODO: РАЗОБРАТЬСЯ ЧТО ЗДЕСЬ ПРОИСХОДИТ
    @Column
    @Enumerated
    @ElementCollection(targetClass = Domain.class)
    private List<Domain> domains;



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
}
