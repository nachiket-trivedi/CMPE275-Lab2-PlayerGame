package com.cmpe275_lab2.lab2.controller;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.serviceImpl.PlayerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class OpponentController {

	@Autowired
	private PlayerServiceImpl playerServiceImpl;
	@Autowired
	private PlayerRepository playerRepository;

	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/opponents/{id1}/{id2}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> addOpponent(@PathVariable Long id1, @PathVariable Long id2)
            {

		try {
			Optional<Player> player1 = playerServiceImpl.getPlayer(id1);
			Optional<Player> player2 = playerServiceImpl.getPlayer(id2);
			if (player1.isPresent() &&  player2.isPresent()){
				Player p1=player1.get();
				Player p2=player2.get();
				id1=p1.getId();
				id2=p2.getId();
				if(id1==id2) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
				else {
					p1.setOpponents(p2);
					p2.setOpponents(p1);
					playerServiceImpl.addOpponents(p1, p2);
				}

			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.EmptyResultDataAccessException(0).getClass())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

		}
		return ResponseEntity.ok().body(new ObjectMapper().createObjectNode().put("msg", "Opponent added"));
	}

	@DeleteMapping("/opponents/{id1}/{id2}")
	public ResponseEntity<?> deleteOpponent(@PathVariable(value = "id1") Long id1, @PathVariable(value = "id2") Long id2) {
		try {

			Optional<Player> player1 = playerRepository.findById(id1);
			Optional<Player> player2 = playerRepository.findById(id2);
			Player p1=player1.get();
			Player p2=player2.get();

			if(id1==id2)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			if(p1 == null || p2 == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			if(!p1.getOpponents().contains(p2) || !p2.getOpponents().contains(p1))
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			p1.getOpponents().remove(p2);
			p2.getOpponents().remove(p1);
			playerServiceImpl.addOpponents(p1, p2);
		}
		catch(Exception e) {
			if (e.getClass().equals(new org.springframework.dao.EmptyResultDataAccessException(0).getClass())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			if (e.getClass().equals(new org.springframework.dao.DataIntegrityViolationException(null).getClass())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.ok().body(new ObjectMapper().createObjectNode().put("msg", "Opponent removed"));

	}

}
