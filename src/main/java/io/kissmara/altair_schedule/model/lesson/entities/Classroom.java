package io.kissmara.altair_schedule.model.lesson.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Classroom {
    @Id
    private String classroom;
    //TODO: РАЗОБРАТЬСЯ ЧТО ЗДЕСЬ ПРОИСХОДИТ

    @Column
    @Enumerated
    @ElementCollection(targetClass = Domain.class)
    private List<Domain> domains;

}
