package io.kissmara.altair_schedule.model.lesson.service;

import io.kissmara.altair_schedule.model.lesson.entities.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssistantService {
    @Autowired private AssistantRepository assistantRepository;


    public List<Assistant> getAssistants(){
        List<Assistant> assistants = new ArrayList<>();
        assistantRepository.findAll().forEach(assistants::add);
        return assistants;
    }

    public boolean addAssistant(Assistant assistant){
        if (isAdded(assistant))  return false;
        assistantRepository.save(assistant);
        return true;
    }

    public void removeAssistant(Assistant assistant){
        assistantRepository.delete(assistant);
    }

    public boolean isAdded(Assistant assistant){
        return getAssistants().stream().anyMatch(assistant::equals);


    }
}
