package qld.mock.vaccination.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPLOYEE", schema = "dbo")
public class Employee {
	
	@Id
	@Column(name = "EMPLOYEE_ID", length = 36)
	private String employeeId;
	
	@Column(name = "ADDRESS", length = 255)
	private String address;
	
	@Column(name = "DATE_OF_BIRTH", length = 255)
	private LocalDate dateOfBirth;
	
	@Column(name = "EMAIL", length = 255)
	private String email;
	
	@Column(name = "EMPLOYEE_NAME", length = 255)
	private String name;
	
	@Column(name = "GENDER", length = 255)
	private Integer gender;
	
	@Column(name = "IMAGE", length = 255)
	private String image;
	
	@Column(name = "PHONE", length = 20)
	private String phone;
	
	@Column(name = "POSITION", length = 255)
	private String position;
	
	@Column(name = "WORKING_PLACE", length = 255)
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	 @Transient
	    public String getPhotosImagePath() {
	        if (image == null ) return null;
	         
	        return "/employee-photos/" + employeeId + "/" + image;
	    }	
	
}
