package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import qld.mock.vaccination.Exception.NotFountException;
import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.repositories.ScheduleRepositories;
import qld.mock.vaccination.repositories.VaccineRepositories;
import qld.mock.vaccination.service.ScheduleService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;




@Service
public class ScheduleServideImpl implements ScheduleService{
	
	@Autowired
	private ScheduleRepositories scheduleRepositories;
	
	@Autowired
	private VaccineRepositories vaccineRepositories;

	@Override
	public Page<Schedule> getListSchedules(Integer pageNo, Integer pageSize) {
		
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return scheduleRepositories.findAll(pageable);
	}

	@Override
	public Page<Schedule> getListSchedulesByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Schedule> schedules = scheduleRepositories.Search(keyWord);
		int total = schedules.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<Schedule> output = new ArrayList<>();

		if (start <= end) {
		    output = schedules.subList(start, end);
		}

		return new PageImpl<>(
		    output,
		    pageable,
		    total
		);
		
	}

	@Override
	public boolean createSchedule(Schedule schedule, String vacciName) {
		// TODO Auto-generated method stub
		
		Vaccine vaccine = vaccineRepositories.findByVaccineName(vacciName);
		if(vaccine == null) {
			return false;
		}
		
		schedule.setVaccine(vaccine);
		
		try {
			return scheduleRepositories.save(schedule) != null;
		} catch (Exception e) {
			throw new NotFountException("503");
		}
			
	}

	@Override
	public Schedule getById(Integer id) {
		// TODO Auto-generated method stub
		return scheduleRepositories.findById(id).get();
	}

	@Override
	public boolean updateSchedule(Schedule schedule, Integer id) {
		// TODO Auto-generated method stub
		
		Schedule scheduleData = scheduleRepositories.findById(id).get();
		if(scheduleData != null) {
			scheduleData.setDescription(schedule.getDescription());
			scheduleData.setEndDate(schedule.getEndDate());
			scheduleData.setPlace(schedule.getPlace());
			scheduleData.setStartDate(schedule.getStartDate());
			scheduleData.setVaccine(schedule.getVaccine());
			
			scheduleRepositories.save(scheduleData);
			
			return true;
			
			
		}
		return false;
		
	}

	

}
