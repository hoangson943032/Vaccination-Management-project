package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Schedule;

@Repository
public interface ScheduleRepositories extends JpaRepository<Schedule, Integer>{
	
	@Query("SELECT s FROM Schedule s WHERE CONCAT(s.description, ' ', s.place, ' ', s.vaccine.vaccineName) LIKE %?1%")
	public List<Schedule> Search(String keyWord);

}
