package io.kissmara.altair_schedule.model.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private String login;
    private String password;
    private Role role;

}
