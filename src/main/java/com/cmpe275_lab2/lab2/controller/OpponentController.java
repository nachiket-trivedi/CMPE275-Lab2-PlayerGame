package com.cmpe275_lab2.lab2.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.model.Sponsor;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.serviceImpl.PlayerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OpponentController {
	@Autowired
	private PlayerServiceImpl playerServiceImpl;
	@Autowired
	private PlayerRepository playerRepository;
	
	@Transactional
	@RequestMapping(method=RequestMethod.PUT, value="/opponents/{id1}/{id2}", produces = { "application/json", "application/xml" })
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
		return ResponseEntity.ok().body(new ObjectMapper().createObjectNode().put("msg", "Collaboration added"));
	}

	@DeleteMapping("/opponents/{id1}/{id2}")
	public ResponseEntity<?> deleteSponsor(@PathVariable(value = "id1") Long id1, @PathVariable(value = "id2") Long id2) {
//		List<Sponsor> ex_sponsors = sponsorRepository.findByName(name.trim());
//
//		if(name.equals("") || name.equals(null)){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//		if(ex_sponsors.size() == 0){
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//		else {
//			sponsorRepository.delete(name);
			return ResponseEntity.ok().build();
		//}
	}

}
