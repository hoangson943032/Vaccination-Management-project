package qld.mock.vaccination.service;

import java.util.List;

import org.springframework.data.domain.Page;

import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;

public interface VaccineTypeService {
	List<VaccineType> getAll();
	
	VaccineType getByVaccineTypeName(String VaccineTypeName);
	
	Page<VaccineType> getListVaccineType(Integer pageNo, Integer pageSize);
	
	Page<VaccineType> getListVaccineTypeByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	Boolean createVaccineType(VaccineType vaccineType);
	
	VaccineType getById(String Id);
	
	void MakeInactive(List<String> ids);

}
