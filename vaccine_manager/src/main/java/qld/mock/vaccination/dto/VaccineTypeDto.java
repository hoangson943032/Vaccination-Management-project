package qld.mock.vaccination.dto;

import javax.validation.constraints.NotEmpty;

public class VaccineTypeDto {
	
	@NotEmpty(message ="this field cannot be empty.")
	private String vaccineTypeId;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String vaccineTypeName;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String Description;
	

	
	private boolean status;



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



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
