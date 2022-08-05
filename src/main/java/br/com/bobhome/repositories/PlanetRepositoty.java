package br.com.bobhome.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.bobhome.entities.Planet;

public interface PlanetRepositoty extends JpaRepository<Planet, Long>{
	
	@Query("SELECT obj FROM Planet obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT(:name,'%'))")
	List<Planet> findByName(String name); 

}
