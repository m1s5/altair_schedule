package io.kissmara.altair_schedule.model;

import java.util.List;

public class RequestConfirmDto {
    private List<Lesson> requests;

    public List<Lesson> getRequests() {
        return requests;
    }
    public void setRequests(List<Lesson> requests) {
        this.requests = requests;
    }

    public RequestConfirmDto(){}
    public RequestConfirmDto(List<Lesson> requests){
        this.requests=requests;
    }
    public void addRequest(Lesson request){
        this.requests.add(request);
    }

}
