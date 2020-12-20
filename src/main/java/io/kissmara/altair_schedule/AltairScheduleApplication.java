package io.kissmara.altair_schedule;

import io.kissmara.altair_schedule.model.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AltairScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AltairScheduleApplication.class, args);
    }

}
