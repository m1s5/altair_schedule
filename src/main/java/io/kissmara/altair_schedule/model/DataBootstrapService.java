package io.kissmara.altair_schedule.model;


import io.kissmara.altair_schedule.model.user.User;
import io.kissmara.altair_schedule.model.user.UserRepository;
import io.kissmara.altair_schedule.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataBootstrapService implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserService userService;




    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        insertUser();
    }

    private void insertUser(){
        if(userService.getUsers().size() == 0){
            User user = new User();
            user.setUsername("admin");
            user.setActive(true);
            user.setRole("ROLE_ADMIN");
            user.setPassword("1");
            userService.addUser(user);

        }
    }
}
