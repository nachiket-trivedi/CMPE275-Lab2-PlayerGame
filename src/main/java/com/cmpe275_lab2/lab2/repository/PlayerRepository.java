package com.cmpe275_lab2.lab2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cmpe275_lab2.lab2.model.Player;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
	
	List<Player> findByEmail(String email);
	public Optional<Player> findById(Long id);

}
