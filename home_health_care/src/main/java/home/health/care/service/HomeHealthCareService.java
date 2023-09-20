package home.health.care.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.health.care.controller.model.HomeHealthCareData;
import home.health.care.controller.model.HomeHealthCareData.ClientData;
import home.health.care.controller.model.HomeHealthCareData.StaffData;
import home.health.care.dao.ClientDao;
import home.health.care.dao.HomeHealthCareDao;
import home.health.care.dao.StaffDao;
import home.health.care.entity.Client;
import home.health.care.entity.HomeHealthCare;
import home.health.care.entity.Staff;

@Service 
public class HomeHealthCareService {   
@Autowired
private HomeHealthCareDao homeHealthCareDao;

@Transactional(readOnly = false)
public HomeHealthCareData saveHomeHealthCare(HomeHealthCareData homeHealthCareData) {
Long homeHealthCareId = homeHealthCareData.getHomeHealthCareId();
HomeHealthCare homeHealthCare = findOrCreateHomeHealthCare(homeHealthCareId, 
homeHealthCareData.getHomeHealthCareEmail());
HomeHealthCare dbHomeHealthCare = homeHealthCareDao.save(homeHealthCare);
copyHomeHealthCareFields(homeHealthCare, homeHealthCareData);
return new HomeHealthCareData(dbHomeHealthCare);
}
private void copyHomeHealthCareFields(HomeHealthCare homeHealthCare, HomeHealthCareData homeHealthCareData) {
	homeHealthCare.setHomeHealthCareName(homeHealthCareData.getHomeHealthCareName());
	homeHealthCare.setHomeHealthCareAddress(homeHealthCareData.getHomeHealthCareAddress());
	homeHealthCare.setHomeHealthCareCity(homeHealthCareData.getHomeHealthCareCity());
	homeHealthCare.setHomeHealthCareState(homeHealthCareData.getHomeHealthCareState());
	homeHealthCare.setHomeHealthCareZip(homeHealthCareData.getHomeHealthCareZip());
	homeHealthCare.setHomeHealthCarePhone(homeHealthCareData.getHomeHealthCarePhone());
	homeHealthCare.setHomeHealthCareEmail(homeHealthCareData.getHomeHealthCareEmail());
}

private HomeHealthCare findOrCreateHomeHealthCare(Long homeHealthCareId, String homeHealthCareEmail) {
	if(Objects.isNull(homeHealthCareId)) {
		Optional<HomeHealthCare> ophhCare  = homeHealthCareDao.findByHomeHealthCareEmail(homeHealthCareEmail);
		if(ophhCare.isPresent()) {
		throw new DuplicateKeyException(
				"Home Health Care with email" + homeHealthCareEmail + "already exists.");	
		}
		
		return new HomeHealthCare();
	}
	else {
		return findHomeHealthCareById(homeHealthCareId);	
	}
}
private HomeHealthCare findHomeHealthCareById(Long homeHealthCareId) {
	return homeHealthCareDao.findById(homeHealthCareId)
			.orElseThrow(() -> new NoSuchElementException(
					"Home Health Care with ID=" + homeHealthCareId + "was not found."));
}
@Transactional(readOnly = true)
public List<HomeHealthCareData> retrieveAllHomeHealthCare() {
List<HomeHealthCare> homeHealthCares = homeHealthCareDao.findAll();
List<HomeHealthCareData> result = new LinkedList<>();

for(HomeHealthCare homeHealthCare : homeHealthCares) {
HomeHealthCareData hhcd = new HomeHealthCareData(homeHealthCare);

hhcd.getClients().clear();
hhcd.getStaffs().clear();

result.add(hhcd);
}
return result;
}
@Transactional(readOnly = true)
public HomeHealthCareData retrieveHomeHealthCareById(Long homeHealthCareId) {
HomeHealthCare homeHealthCare = findHomeHealthCareById(homeHealthCareId);
return new HomeHealthCareData(homeHealthCare);
}	
@Autowired
private StaffDao staffDao;
@Transactional(readOnly = false)
public StaffData saveStaff(Long homeHealthCareId, StaffData staffData) {
HomeHealthCare homeHealthCare = findHomeHealthCareById(homeHealthCareId);
Long staffId = staffData.getStaffId();
Staff staff = findOrCreateStaff(homeHealthCareId, staffId, staffData.getStaffEmail());
copyStaffFields(staff, staffData);
staff.setHomeHealthCare(homeHealthCare);
homeHealthCare.getStaffs().add(staff);
Staff dbStaff = staffDao.save(staff);
	return new StaffData(dbStaff);
}
private void copyStaffFields(Staff staff, StaffData staffData) {
	staff.setStaffFirstName(staffData.getStaffFirstName());
	staff.setStaffLastName(staffData.getStaffLastName());
	staff.setStaffPhone(staffData.getStaffPhone());
	staff.setStaffEmail(staffData.getStaffEmail());
	staff.setStaffJobTitle(staffData.getStaffJobTitle());
	staff.setStaffShiftHours(staffData.getStaffShiftHours());
}
private Staff findOrCreateStaff(Long homeHealthCareId, Long staffId, String staffEmail) {
if(Objects.isNull(staffId)) {
	Optional<StaffData> opStaff  = staffDao.findBystaffEmail(staffEmail);
	if(opStaff.isPresent()) {
	throw new DuplicateKeyException(
			"Home Health Care staff with email" + staffEmail + "already exists.");	
	}
	return new Staff();
}
	return findStaffById(homeHealthCareId, staffId);

}
private Staff findStaffById(Long homeHealthCareId, Long staffId) {
	Staff staff = staffDao.findById(staffId).orElseThrow(() -> new NoSuchElementException(
			"Staff with ID=" + staffId + "was not found."));
	if(staff.getHomeHealthCare().getHomeHealthCareId()!= homeHealthCareId) {
		throw new IllegalArgumentException("The staff with ID=" + staffId 
				+ "does not work for the home health care with ID=" + homeHealthCareId + ".");
	}
	return staff;
}
@Autowired
private ClientDao clientDao;
@Transactional
public ClientData saveClient(Long homeHealthCareId, ClientData clientData) {
	HomeHealthCare homeHealthCare = findHomeHealthCareById(homeHealthCareId);
	Long clientId = clientData.getClientId();
	Client client = findOrCreateClient(homeHealthCareId, clientId);
	copyClientFields(client, clientData);
	client.getHomeHealthCares().add(homeHealthCare);
	homeHealthCare.getClients().add(client);
	Client dbClient = clientDao.save(client);
	return new ClientData(dbClient);
}
private void copyClientFields(Client client, ClientData ClientData) {
	client.setClientFirstName(ClientData.getClientFirstName());
	client.setClientLastName(ClientData.getClientLastName());
	client.setClientAddress(ClientData.getClientAddress());
	client.setClientCity(ClientData.getClientCity());
	client.setClientState(ClientData.getClientState());
	client.setClientZip(ClientData.getClientZip());
	client.setClientPhone(ClientData.getClientPhone());
	client.setClientCarePackage(ClientData.getClientCarePackage());
}
private Client findOrCreateClient(Long homeHealthCareId, Long clientId) {
	if(Objects.isNull(clientId)) {
		return new Client();
	}
	return findClientById(homeHealthCareId, clientId);
}
private Client findClientById(Long homeHealthCareId, Long clientId) {
	Client client = clientDao.findById(clientId).orElseThrow(() -> new NoSuchElementException(
			"Client with ID=" + clientId + "was not found"));
	boolean found = false;
	for(HomeHealthCare homeHealthCare : client.getHomeHealthCares()) {
		if(homeHealthCare.getHomeHealthCareId() == homeHealthCareId) {
			found = true;
			break;
		}
	}
	if(!found) {
		throw new IllegalArgumentException("The Client with ID=" + clientId + 
				"is not a client of the Home Health Care with ID=" + homeHealthCareId);
	}
	return client;
}


@Transactional(readOnly = false)
public void deleteHomeHealthCareById(Long homeHealthCareId) {
	HomeHealthCare homeHealthCare = findHomeHealthCareById(homeHealthCareId);
	homeHealthCareDao.delete(homeHealthCare);
}


	
}
