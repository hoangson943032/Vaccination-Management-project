package qld.mock.vaccination.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


public class ScheduleDto {
	
	@NotEmpty(message ="Note cannot be empty.")
	private String description;
	
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@NotNull(message = "End date not empty.")
	private LocalDate endDate;
	
	@NotEmpty(message = "Place cannot be empty.")
	private String place;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Start date not empty.")
	private LocalDate startDate;
	
	@NotEmpty(message = "Vaccine name cannot be empty.")
	private String vaccineName;

	public ScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	

}
