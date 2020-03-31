package com.cmpe275_lab2.lab2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cmpe275_lab2.lab2.model.Player;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
	
	List<Player> findByEmail(String email);
	public Optional<Player> findById(Long id);

	void deleteById(Long id);

	@Query(value = "DELETE FROM Opponent WHERE player = :id1 AND player2=:id2")
	public void deleteOpponent(@Param("id1") Long id1, @Param("id2") Long id2);
}
