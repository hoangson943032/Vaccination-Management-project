package qld.mock.vaccination.service;

import java.util.List;

import org.springframework.data.domain.Page;

import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.entities.Vaccine;

public interface VaccineService {
	
	List<Vaccine> getVaccines();
	
	Vaccine getVaccineByVaccineName(String vaccineName);
	
	Page<Vaccine> getListVaccine(Integer pageNo, Integer pageSize);

	void MakeInactive(List<String> ids);
	
	boolean createVaccine(Vaccine vaccine, String vaccineTypeName);
	
	Vaccine getById(String Id);
	
	Page<Vaccine> getListVaccineByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	boolean SaveAll(List<Vaccine> vaccines);

}
