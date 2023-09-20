package home.health.care.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.health.care.controller.model.HomeHealthCareData.StaffData;
import home.health.care.entity.Staff;

public interface StaffDao extends JpaRepository<Staff, Long> {

	Optional<StaffData> findBystaffEmail(String staffEmail);


}
