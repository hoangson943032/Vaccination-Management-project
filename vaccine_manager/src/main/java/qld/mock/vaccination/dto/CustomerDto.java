package qld.mock.vaccination.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



public class CustomerDto {
	
	@NotEmpty(message ="this field cannot be empty.")
	private String address;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@NotNull(message ="this field cannot be empty.")
	private LocalDate dateOfBirth;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String fullName;
	
	@NotNull(message ="this field cannot be empty.")
	private Integer gender;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String identityCard;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	
	
	

}
