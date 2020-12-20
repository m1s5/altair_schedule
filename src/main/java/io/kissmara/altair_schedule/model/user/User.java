package io.kissmara.altair_schedule.model.user;


import io.kissmara.altair_schedule.model.lesson.entities.Tutor;
import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private String username;
    private String password;
    private String role;
    private boolean active = true;


    User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public boolean equals(User user){
        return this.username.equals(user.username);
    }
}
