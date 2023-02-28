package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;

@Repository
public interface VaccineTypeRepositories extends JpaRepository<VaccineType, String>{
	
	VaccineType findByVaccineTypeName(String vaccineTypeName);
	

	@Query("SELECT vc FROM VaccineType vc WHERE vc.vaccineTypeName LIKE %?1%")
	public List<VaccineType> Search(String keyWord);

}
