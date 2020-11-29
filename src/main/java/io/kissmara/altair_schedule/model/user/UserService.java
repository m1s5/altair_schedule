package io.kissmara.altair_schedule.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired UserRepository userRepository;


    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public boolean addUser(User user){
        if (isAdded(user))  return false;
        userRepository.save(user);
        return true;
    }

    public void removeUser(User user){
        userRepository.delete(user);
    }

    public boolean isAdded(User user){
        return getUsers().stream().anyMatch(user::equals);


    }
}
