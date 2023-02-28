package qld.mock.vaccination.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VACCINE_TYPE", schema = "dbo")
public class VaccineType {
	
	@Id
	@Column(name = "VACCINE_TYPE_ID", length = 36)
	private String vaccineTypeId;
	
	@Column(name = "VACCINE_TYPE_NAME", length = 50, unique = true)
	private String vaccineTypeName;
	
	@Column(name = "DESCRIPTION", length = 200)
	private String Description;
	

	@Column(name = "VACCINE_STATUS")
	private boolean status;
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(mappedBy = "vaccineType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Vaccine> vaccines;

	public VaccineType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getVaccineTypeId() {
		return vaccineTypeId;
	}

	public void setVaccineTypeId(String vaccineTypeId) {
		this.vaccineTypeId = vaccineTypeId;
	}

	public String getVaccineTypeName() {
		return vaccineTypeName;
	}

	public void setVaccineTypeName(String vaccineTypeName) {
		this.vaccineTypeName = vaccineTypeName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	
	

}
