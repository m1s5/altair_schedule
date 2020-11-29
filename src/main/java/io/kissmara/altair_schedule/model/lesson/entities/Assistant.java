package io.kissmara.altair_schedule.model.lesson.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Assistant {
    @Id
    private String name;

    boolean equals(Assistant assistant){
        return this.name.equals(assistant.name);
    }
}
