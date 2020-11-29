package io.kissmara.altair_schedule.model.user;


import io.kissmara.altair_schedule.model.lesson.entities.Tutor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private String username;
    private String password;
    private String authority;
    private boolean enabled = true;


    public boolean equals(User user){
        return this.username.equals(user.username);
    }
}
