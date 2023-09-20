package home.health.care.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity 
@Data     
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
	private Long clientId;
	private String clientFirstName;
	private String clientLastName;
	private String clientAddress;
	private String clientCity;
	private String clientState;
	private String clientZip;
	private String clientPhone;
	private String clientCarePackage;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "clients", cascade = CascadeType.PERSIST)
	private Set<HomeHealthCare> homeHealthCares;
	
}
