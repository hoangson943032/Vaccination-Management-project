package qld.mock.vaccination.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


public class EmployeeDto {
	
	@NotEmpty(message ="this field cannot be empty.")
	private String employeeId;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String address;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@NotNull(message ="this field cannot be empty.")
	private LocalDate dateOfBirth;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String email;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String name;
	
	@NotNull(message ="this field cannot be empty.")
	private Integer gender;
	
	
	@NotEmpty(message ="this field cannot be empty.")
	private String phone;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String position;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String workingPlace;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}
	
	
	
	

}
