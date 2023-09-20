package home.health.care.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data  

public class HomeHealthCare {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long homeHealthCareId;
	private String homeHealthCareName;
	private String homeHealthCareAddress;
	private String homeHealthCareCity;
	private String homeHealthCareState;
	private String homeHealthCareZip;
	private String homeHealthCarePhone;
	@Column(unique = true)
	private String homeHealthCareEmail;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "home_health_care_client", 
	 joinColumns = @JoinColumn(name = "home_health_care_id"),
	 inverseJoinColumns = @JoinColumn(name = "client_id"))
	@EqualsAndHashCode.Exclude 
	@ToString.Exclude 
	private Set<Client> clients = new HashSet<>();
	
	@OneToMany(mappedBy = "homeHealthCare", cascade = CascadeType.ALL,
			orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Staff> staffs = new HashSet<>();
	
	

}
