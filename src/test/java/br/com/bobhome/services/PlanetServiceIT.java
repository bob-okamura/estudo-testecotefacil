package br.com.bobhome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import br.com.bobhome.dtos.PlanetDTO;
import br.com.bobhome.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@Transactional
public class PlanetServiceIT { // IT Integration Test
	
	private Long countTotalPlanets;
	private Long existingId;
	private Long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception {
		countTotalPlanets = 5L;
		existingId = 1L;
		nonExistingId = 1000L;
	}

	@Autowired
	private PlanetService service;

	@Test
	public void findAllPagedShouldReturnPageWhenPage0Size10() {
		PageRequest pageRequest = PageRequest.of(0, 10);

		Page<PlanetDTO> result = service.findAllPaged(pageRequest);

		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(10, result.getSize());
		Assertions.assertEquals(countTotalPlanets, result.getTotalElements());
	}

	@Test
	public void findByIdShouldReturnPlanetWhenIdExists() {

		PlanetDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(existingId, result.getId());
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}

}
