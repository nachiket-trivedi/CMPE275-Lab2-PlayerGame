package com.cmpe275_lab2.lab2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cmpe275_lab2.lab2.model.Sponsor;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long>{
	
	List<Sponsor> findByName(String name);
	Optional<Sponsor> findById(Long id);
	Optional<Sponsor> findOneByName(String name);
	

	void delete(Sponsor s);
}
