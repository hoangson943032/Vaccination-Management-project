package qld.mock.vaccination.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class VaccineDTO {
	
	@NotEmpty(message ="vaccine Id cannot be empty.")
	private String vaccineId;
	
	@NotEmpty(message ="contraindication cannot be empty.")
	private String contraindication;
	
	@NotEmpty(message ="indication cannot be empty.")
	private String indication;
	
	@NotNull(message ="number Of Injecttion cannot be empty.")
	private Integer numberOfInjecttion;
	
	@NotEmpty(message ="origin cannot be empty.")
	private String origin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "date not empty.")
	private LocalDate timeBeginNextInjection;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "date not empty.")
	private LocalDate timeEndNextInjection;
	
	@NotEmpty(message ="Note cannot be empty.")
	private String usage;
	
	@NotEmpty(message ="Note cannot be empty.")
	private String vaccineName;
	
	@NotEmpty(message ="Note cannot be empty.")
	private String vaccineTypeName;
	

	public String getVaccineTypeName() {
		return vaccineTypeName;
	}


	public void setVaccineTypeName(String vaccineTypeName) {
		this.vaccineTypeName = vaccineTypeName;
	}


	private boolean status;


	public VaccineDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getVaccineId() {
		return vaccineId;
	}


	public void setVaccineId(String vaccineId) {
		this.vaccineId = vaccineId;
	}


	public String getContraindication() {
		return contraindication;
	}


	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}


	public String getIndication() {
		return indication;
	}


	public void setIndication(String indication) {
		this.indication = indication;
	}


	public Integer getNumberOfInjecttion() {
		return numberOfInjecttion;
	}


	public void setNumberOfInjecttion(Integer numberOfInjecttion) {
		this.numberOfInjecttion = numberOfInjecttion;
	}


	


	@Override
	public String toString() {
		return "VaccineDTO [vaccineId=" + vaccineId + ", contraindication=" + contraindication + ", indication="
				+ indication + ", numberOfInjecttion=" + numberOfInjecttion + ", origin=" + origin
				+ ", timeBeginNextInjection=" + timeBeginNextInjection + ", timeEndNextInjection="
				+ timeEndNextInjection + ", usage=" + usage + ", vaccineName=" + vaccineName + ", vaccineTypeName="
				+ vaccineTypeName + ", status=" + status + "]";
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public LocalDate getTimeBeginNextInjection() {
		return timeBeginNextInjection;
	}


	public void setTimeBeginNextInjection(LocalDate timeBeginNextInjection) {
		this.timeBeginNextInjection = timeBeginNextInjection;
	}


	public LocalDate getTimeEndNextInjection() {
		return timeEndNextInjection;
	}


	public void setTimeEndNextInjection(LocalDate timeEndNextInjection) {
		this.timeEndNextInjection = timeEndNextInjection;
	}


	public String getUsage() {
		return usage;
	}


	public void setUsage(String usage) {
		this.usage = usage;
	}


	public String getVaccineName() {
		return vaccineName;
	}


	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	
	


	
}
