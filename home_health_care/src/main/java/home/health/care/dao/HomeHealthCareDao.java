package home.health.care.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import home.health.care.entity.HomeHealthCare;

public interface HomeHealthCareDao extends JpaRepository<HomeHealthCare, Long> {

	Optional<HomeHealthCare> findByHomeHealthCareEmail(String homeHealthCareEmail);

}
