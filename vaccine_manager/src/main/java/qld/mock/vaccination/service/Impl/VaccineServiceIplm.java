package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;
import qld.mock.vaccination.repositories.VaccineRepositories;
import qld.mock.vaccination.repositories.VaccineTypeRepositories;
import qld.mock.vaccination.service.VaccineService;

@Service
public class VaccineServiceIplm implements VaccineService{
	
	@Autowired
	private VaccineRepositories vaccineRepositories;
	
	@Autowired
	private VaccineTypeRepositories vaccineTypeRepositories;

	@Override
	public List<Vaccine> getVaccines() {
		// TODO Auto-generated method stub
		return vaccineRepositories.findAll();
	}

	@Override
	public Vaccine getVaccineByVaccineName(String vaccineName) {
		// TODO Auto-generated method stub
		return vaccineRepositories.findByVaccineName(vaccineName);
	}

	@Override
	public Page<Vaccine> getListVaccine(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return vaccineRepositories.findAll(pageable);
	}

	@Override
	public void MakeInactive(List<String> ids) {
		for(String id : ids) {
			Vaccine vaccine = vaccineRepositories.findById(id).get();
			vaccine.setStatus(false);
			vaccineRepositories.save(vaccine);
		}
	}

	@Override
	public boolean createVaccine(Vaccine vaccine, String vaccineTypeName) {
		// TODO Auto-generated method stub
		VaccineType vaccineType = vaccineTypeRepositories.findByVaccineTypeName(vaccineTypeName);
		if(vaccineType == null) {
			return false;
		}
		
		vaccine.setVaccineType(vaccineType);
		 return vaccineRepositories.save(vaccine) != null;
		
	}

	@Override
	public Vaccine getById(String Id) {
		// TODO Auto-generated method stub
		try {
			return vaccineRepositories.findById(Id).get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<Vaccine> getListVaccineByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<Vaccine> vaccines = vaccineRepositories.Search(keyWord);
		int total = vaccines.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<Vaccine> output = new ArrayList<>();

		if (start <= end) {
		    output = vaccines.subList(start, end);
		}

		return new PageImpl<>(
		    output,
		    pageable,
		    total
		);
	}

	@Override
	public boolean SaveAll(List<Vaccine> vaccines) {
		// TODO Auto-generated method stub
		return vaccineRepositories.saveAll(vaccines) != null;
	}


}
