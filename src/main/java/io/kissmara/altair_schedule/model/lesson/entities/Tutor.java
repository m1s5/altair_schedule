package io.kissmara.altair_schedule.model.lesson.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tutor {
    @Id
    private String name;
    @Column
    @Enumerated
    @ElementCollection(targetClass = Domain.class)
    private List<Domain> domains;
    public boolean equals(Tutor tutor){
        return this.name.equals(tutor.name);
    }
}