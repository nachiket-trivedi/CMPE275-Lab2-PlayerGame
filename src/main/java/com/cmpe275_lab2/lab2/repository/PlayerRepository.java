package com.cmpe275_lab2.lab2.repository;

import com.cmpe275_lab2.lab2.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
	
	List<Player> findByEmail(String email);
	public Optional<Player> findById(Long id);

	void deleteById(Long id);

//	@Query(value = "DELETE FROM Opponent WHERE (player = :id1 AND opponent=:id2) OR (player = :id2 AND opponent=:id1)")
//	void deleteOpponent(@Param("id1") Long id1, @Param("id2") Long id2);
}
