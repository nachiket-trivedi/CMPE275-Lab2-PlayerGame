package com.cmpe275_lab2.lab2.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275_lab2.lab2.model.Address;
import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.model.Sponsor;
import com.cmpe275_lab2.lab2.serviceImpl.PlayerServiceImpl;
import com.cmpe275_lab2.lab2.serviceImpl.SponsorServiceImpl;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerServiceImpl playerServiceImpl;
	
	@Autowired
	private SponsorServiceImpl sponsorServiceImpl;

	
	
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/player", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> addPlayer(@RequestParam String firstname
			, @RequestParam String lastname
            , @RequestParam String email
            , @RequestParam(required = false) String description
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip
            , @RequestParam(required = false) Long sponsor_id
            ) {
		Player player = new Player();
		player.setFirstname(firstname);
		player.setLastname(lastname);
		player.setEmail(email);
		player.setDescription(description);
		player.setAddress(new Address(street, city, state, zip));
		if(sponsor_id!=null) {
			Sponsor tempSponsor = sponsorServiceImpl.getSponsor(sponsor_id);
			if (tempSponsor != null) {
				player.setSponsor(tempSponsor);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
		playerServiceImpl.addPlayer(player);
		return ResponseEntity.ok(player);
	}
}
