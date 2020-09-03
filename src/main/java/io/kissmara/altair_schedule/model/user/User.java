package io.kissmara.altair_schedule.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    public String getLogin() {
        return login;
    }
}
