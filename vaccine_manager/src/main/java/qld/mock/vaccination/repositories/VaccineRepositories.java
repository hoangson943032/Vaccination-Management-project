package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.entities.Vaccine;

@Repository
public interface VaccineRepositories extends JpaRepository<Vaccine, String>{
	
	Vaccine findByVaccineName(String vaccineName);
	
	@Query("SELECT vc FROM Vaccine vc WHERE vc.vaccineName LIKE %?1%")
	public List<Vaccine> Search(String keyWord);
	
	




	
	
//	@Query("SELECT v.vaccineName FROM Vaccine v")
//	List<String> getVaccineName();

}
