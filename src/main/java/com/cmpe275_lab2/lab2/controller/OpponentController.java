package com.cmpe275_lab2.lab2.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275_lab2.lab2.model.Player;
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
	@RequestMapping(method=RequestMethod.PUT, value="/opponent/{id1}/{id2}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> addOpponent(@PathVariable Long id1, @PathVariable Long id2)
            {
		
		try {
			Player player1 = playerServiceImpl.getPlayer(id1);
			Player player2 = playerServiceImpl.getPlayer(id2);
			if(player1 == null || player2 == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			player1.setOpponents(player2);
			player2.setOpponents(player1);
			playerServiceImpl.addOpponents(player1, player2);
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

}
