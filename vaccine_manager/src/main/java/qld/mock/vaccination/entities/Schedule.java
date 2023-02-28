package qld.mock.vaccination.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;


@Entity
@Table(name = "INJECTION_SCHEDULE", schema = "dbo")
@Check(constraints = "START_DATE < END_DATE ")
public class Schedule {
	
	@Id
	@Column(name = "INJECTION_SCHEDULE_ID", length = 36)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer injectionScheduleId;
	
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "PLACE", length = 255)
	private String place;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VACCINE_ID", referencedColumnName = "VACCINE_ID")
	private Vaccine vaccine;

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Schedule(Integer injectionScheduleId, String description, LocalDate endDate, String place,
			LocalDate startDate, Vaccine vaccine) {
		super();
		this.injectionScheduleId = injectionScheduleId;
		this.description = description;
		this.endDate = endDate;
		this.place = place;
		this.startDate = startDate;
		this.vaccine = vaccine;
	}

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getInjectionScheduleId() {
		return injectionScheduleId;
	}

	public void setInjectionScheduleId(Integer injectionScheduleId) {
		this.injectionScheduleId = injectionScheduleId;
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

	
	
}
