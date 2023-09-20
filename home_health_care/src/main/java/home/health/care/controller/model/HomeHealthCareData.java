package home.health.care.controller.model;

import java.util.HashSet;
import java.util.Set;

import home.health.care.entity.Client;
import home.health.care.entity.HomeHealthCare;
import home.health.care.entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor 
public class HomeHealthCareData {
	private Long homeHealthCareId;
	private String homeHealthCareName;
	private String homeHealthCareAddress;
	private String homeHealthCareCity;
	private String homeHealthCareState;
	private String homeHealthCareZip;
	private String homeHealthCarePhone;
	private String homeHealthCareEmail; 
	private Set<ClientData> clients = new HashSet<>(); 
	private Set<StaffData> staffs = new HashSet<>();
	
	
	public HomeHealthCareData(HomeHealthCare homeHealthCare) {
	homeHealthCareId = homeHealthCare.getHomeHealthCareId();
    homeHealthCareName = homeHealthCare.getHomeHealthCareName();
	homeHealthCareAddress = homeHealthCare.getHomeHealthCareAddress();
	homeHealthCareCity = homeHealthCare.getHomeHealthCareCity();
	homeHealthCareState = homeHealthCare.getHomeHealthCareState();
	homeHealthCareZip = homeHealthCare.getHomeHealthCareZip();
    homeHealthCarePhone = homeHealthCare.getHomeHealthCarePhone();
	homeHealthCareEmail = homeHealthCare.getHomeHealthCareEmail();
		
	for(Client client : homeHealthCare.getClients()) {
		this.clients.add(new ClientData(client));
	}
	
	for(Staff staff : homeHealthCare.getStaffs()) {
		this.staffs.add(new StaffData(staff));
	}
	}
	@Data
	@NoArgsConstructor
	public static class ClientData{
		private Long clientId;
		private String clientFirstName;
		private String clientLastName;
		private String clientAddress;
		private String clientCity;
		private String clientState;
		private String clientZip;
		private String clientPhone;
		private String clientCarePackage;
		
		
	public ClientData(Client client) {
	clientId = client.getClientId();
	clientFirstName = client.getClientFirstName();
	clientLastName = client.getClientLastName();
	clientAddress = client.getClientAddress();
	clientCity = client.getClientCity();
	clientState = client.getClientState();
	clientZip = client.getClientZip();
	clientPhone = client.getClientPhone();
	clientCarePackage = client.getClientCarePackage();		
	}
	}
    @Data
	@NoArgsConstructor
	public static class StaffData{
		private Long staffId;
		private String staffFirstName;
		private String staffLastName;
		private String staffPhone;
		private String staffEmail;
		private String staffJobTitle;
		private String staffShiftHours;
		
		public StaffData(Staff staff) {
		staffId = staff.getStaffId();
		staffFirstName = staff.getStaffFirstName();
	    staffLastName = staff.getStaffLastName();
		staffPhone = staff.getStaffPhone();
		staffEmail = staff.getStaffEmail();
		staffJobTitle = staff.getStaffJobTitle();
		staffShiftHours = staff.getStaffShiftHours();
		}
		
    }


}
