package home.health.care.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import home.health.care.entity.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

}
