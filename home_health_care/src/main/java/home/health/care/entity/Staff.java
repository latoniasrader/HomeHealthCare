package home.health.care.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity 
@Data   

public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long staffId;
	private String staffFirstName;
	private String staffLastName;
	private String staffPhone;
	@Column(unique = true)
	private String staffEmail;
	private String staffJobTitle;
	private String staffShiftHours;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_health_care_id")
	
	private HomeHealthCare homeHealthCare;

}
