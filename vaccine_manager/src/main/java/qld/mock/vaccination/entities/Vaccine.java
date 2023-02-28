package qld.mock.vaccination.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;


@Entity
@Table(name = "VACCINE", schema = "dbo")
@Check(constraints = "TIME_BEGIN_NEXT_INJECTION < TIME_END_NEXT_ỊNECTION")
public class Vaccine {
	
	@Column(name = "VACCINE_ID", length = 36)
	@Id
	private String vaccineId;
	
	@Column(name = "CONTRAINDICATION", length = 200)
	private String contraindication;
	
	@Column(name = "INDICATION", length = 200)
	private String indication;
	
	@Column(name = "NUMBER_OF_INJECTION")
	private Integer numberOfInjecttion;
	
	@Column(name = "ORIGIN", length = 50)
	private String origin;
	
	@Column(name = "TIME_BEGIN_NEXT_INJECTION")
	private LocalDate timeBeginNextInjection;
	
	@Column(name = "TIME_END_NEXT_ỊNECTION")
	private LocalDate timeEndNextInjection;
	
	@Column(name = "USAGE", length = 200)
	private String usage;
	
	@Column(name = "VACCINE_NAME", unique = true, length = 100)
	private String vaccineName;
	
	@Column(name = "VACCINE_STATUS")
	private boolean status;
	
	
	@OneToMany(mappedBy = "vaccine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Schedule> schedule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VACCINE_TYPE_ID", referencedColumnName = "VACCINE_TYPE_ID")
	private VaccineType vaccineType;
	
	

	public VaccineType getVaccineType() {
		return vaccineType;
	}



	public void setVaccineType(VaccineType vaccineType) {
		this.vaccineType = vaccineType;
	}



	public Vaccine() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public List<Schedule> getSchedule() {
		return schedule;
	}



	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}



	public Vaccine(String vaccineId, String contraindication, String indication, Integer numberOfInjecttion,
			String origin, LocalDate timeBeginNextInjection, LocalDate timeEndNextInjection, String usage,
			String vaccineName, boolean status, List<Schedule> schedule) {
		super();
		this.vaccineId = vaccineId;
		this.contraindication = contraindication;
		this.indication = indication;
		this.numberOfInjecttion = numberOfInjecttion;
		this.origin = origin;
		this.timeBeginNextInjection = timeBeginNextInjection;
		this.timeEndNextInjection = timeEndNextInjection;
		this.usage = usage;
		this.vaccineName = vaccineName;
		this.status = status;
		this.schedule = schedule;
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



	@Override
	public String toString() {
		return "Vaccine [vaccineId=" + vaccineId + ", contraindication=" + contraindication + ", indication="
				+ indication + ", numberOfInjecttion=" + numberOfInjecttion + ", origin=" + origin
				+ ", timeBeginNextInjection=" + timeBeginNextInjection + ", timeEndNextInjection="
				+ timeEndNextInjection + ", usage=" + usage + ", vaccineName=" + vaccineName + ", status=" + status
				+ "]";
	}
	
	
	
	
}
