package home.health.care.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import home.health.care.controller.model.HomeHealthCareData;
import home.health.care.controller.model.HomeHealthCareData.ClientData;
import home.health.care.controller.model.HomeHealthCareData.StaffData;
import home.health.care.service.HomeHealthCareService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/home_health_care")
@Slf4j 
public class HomeHealthCareController {
@Autowired
private HomeHealthCareService homeHealthCareService;

@PostMapping("/homeHealthCare")
@ResponseStatus(code = HttpStatus.CREATED)
public HomeHealthCareData insertHomeHealthCare(@RequestBody HomeHealthCareData homeHealthCareData) {
	log.info("Create home health care {}", homeHealthCareData);
	return homeHealthCareService.saveHomeHealthCare(homeHealthCareData); 
}  
@PutMapping("/homeHealthCare/{homeHealthCareId}")
public HomeHealthCareData updateHomeHealthCare(@PathVariable Long homeHealthCareId,
		@RequestBody HomeHealthCareData homeHealthCareData) {
	homeHealthCareData.setHomeHealthCareId(homeHealthCareId);
	log.info("Update home health care {}", homeHealthCareData);
	return homeHealthCareService.saveHomeHealthCare(homeHealthCareData);
}
@PostMapping("/homeHealthCare/{homeHealthCareId}/staff")
@ResponseStatus(code = HttpStatus.CREATED)
public StaffData insertStaffToHomeHealthCare(@PathVariable Long homeHealthCareId,
		@RequestBody StaffData staffData) {
	log.info("Adding staff {} to home health care with ID={}", staffData, homeHealthCareId);
	return homeHealthCareService.saveStaff(homeHealthCareId, staffData);
}
@PostMapping("/homeHealthCare/{homeHealthCareId}/client")
@ResponseStatus(code = HttpStatus.CREATED)
public ClientData insertClientToHomeHealthCare(@PathVariable Long homeHealthCareId,
		@RequestBody ClientData clientData) {
	log.info("Adding client {} to home health care with ID={}", clientData, homeHealthCareId);
	return homeHealthCareService.saveClient(homeHealthCareId, clientData);
}
@GetMapping("/homeHealthCare")
public List<HomeHealthCareData> retrieveAllHomeHealthCare(){
	log.info("Retrieve all home health care");
	return homeHealthCareService.retrieveAllHomeHealthCare();	
}
@GetMapping("/homeHealthCare/{homeHealthCareId}")
public HomeHealthCareData retrieveHomeHealthCareById(@PathVariable Long homeHealthCareId) {
	log.info("Retrieving all home health care with ID={}", homeHealthCareId);
	return homeHealthCareService.retrieveHomeHealthCareById(homeHealthCareId);
}
@DeleteMapping("/homeHealthCare/{homeHealthCareId}")
public Map<String, String> deleteHomeHealthCareById(@PathVariable Long homeHealthCareId){
	log.info("Deleting home health care with ID={}" + homeHealthCareId + ".");
	homeHealthCareService.deleteHomeHealthCareById(homeHealthCareId);
	return Map.of("message", "Home Health Care with ID=" + homeHealthCareId + " was deleted successfully.");
	
}







}