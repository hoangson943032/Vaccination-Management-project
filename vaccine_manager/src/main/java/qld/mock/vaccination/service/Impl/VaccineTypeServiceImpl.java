package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.entities.VaccineType;
import qld.mock.vaccination.repositories.VaccineTypeRepositories;
import qld.mock.vaccination.service.VaccineTypeService;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService{
	
	@Autowired
	private VaccineTypeRepositories vaccineTypeRepositories;

	@Override
	public List<VaccineType> getAll() {
		// TODO Auto-generated method stub
		return vaccineTypeRepositories.findAll();
		
	}

	@Override
	public VaccineType getByVaccineTypeName(String VaccineTypeName) {
		// TODO Auto-generated method stub
		return vaccineTypeRepositories.findByVaccineTypeName(VaccineTypeName);
	}

	@Override
	public Page<VaccineType> getListVaccineType(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return vaccineTypeRepositories.findAll(pageable);
	}

	@Override
	public Page<VaccineType> getListVaccineTypeByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<VaccineType> vaccines = vaccineTypeRepositories.Search(keyWord);
		int total = vaccines.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<VaccineType> output = new ArrayList<>();

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
	public Boolean createVaccineType(VaccineType vaccineType) {
		// TODO Auto-generated method stub
		return vaccineTypeRepositories.save(vaccineType) != null;
	}

	@Override
	public VaccineType getById(String Id) {
		// TODO Auto-generated method stub
		try {
			return vaccineTypeRepositories.findById(Id).get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void MakeInactive(List<String> ids) {
		// TODO Auto-generated method stub
		for(String id : ids) {
			VaccineType vaccineType = vaccineTypeRepositories.findById(id).get();
			vaccineType.setStatus(false);
			vaccineTypeRepositories.save(vaccineType);
		}
		
	}

}
