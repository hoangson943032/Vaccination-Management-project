package qld.mock.vaccination.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Schedule;


public interface ScheduleService {

	Page<Schedule> getListSchedules(Integer pageNo, Integer pageSize);
	
	Page<Schedule> getListSchedulesByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	boolean createSchedule(Schedule schedule, String vaccinName);
	
	Schedule getById(Integer id);
	
	boolean updateSchedule(Schedule schedule, Integer id);
}
