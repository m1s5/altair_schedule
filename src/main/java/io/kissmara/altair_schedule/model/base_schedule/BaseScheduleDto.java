package io.kissmara.altair_schedule.model.base_schedule;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseScheduleDto {
    List<XSSFWorkbook> schedules = new ArrayList<>(){
        {
            add(new XSSFWorkbook());
            add(new XSSFWorkbook());
            add(new XSSFWorkbook());
            add(new XSSFWorkbook());
        }
    };
    public List<XSSFWorkbook> getSchedules(){
        return schedules;
    }
    public void setSchedules(List<XSSFWorkbook> schedules){
        this.schedules = schedules;
    }

}
