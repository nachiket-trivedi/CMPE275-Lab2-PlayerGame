package com.cmpe275_lab2.lab2.controller;

import java.util.List;
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

import com.cmpe275_lab2.lab2.model.Address;
import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.model.Sponsor;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.serviceImpl.PlayerServiceImpl;
import com.cmpe275_lab2.lab2.serviceImpl.SponsorServiceImpl;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerServiceImpl playerServiceImpl;
	
	@Autowired
	private SponsorServiceImpl sponsorServiceImpl;
	
	@Autowired
	private PlayerRepository playerRepository;

	
	
	
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
		
		//String new_description=description.trim();
		List<Player> ex_player = playerRepository.findByEmail(email.trim());
		if(ex_player.size() > 0){
			 return new ResponseEntity(HttpStatus.CONFLICT);
		}
		else {
			Player player = new Player();
			player.setFirstname(firstname.trim());
			player.setLastname(lastname.trim());
			player.setEmail(email.trim());
			if(description!=null) {
				String new_description=description.trim();
				player.setDescription(new_description);
			}
			Address add=new Address();
			if(street!=null) {
				add.setStreet(street.trim());
			}
			if(city!=null) {
				add.setCity(city.trim());
			}
			if(state!=null) {
				add.setState(state.trim());
			}
			if(zip!=null) {
				add.setZip(zip.trim());
			}
			player.setAddress(add);
		
		
	if(sponsor_id!=null) {
		Optional<Sponsor> tempSponsor = sponsorServiceImpl.getSponsor(sponsor_id);
		if (tempSponsor.isPresent()) {
			Sponsor s=tempSponsor.get();
			player.setSponsor(s);
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	playerServiceImpl.addPlayer(player);
	
	
	return ResponseEntity.ok(player);
		}
	}
	
	@RequestMapping(value = "/player/{id}", produces = { "application/json", "application/xml"})
	public ResponseEntity<Object> getPlayer(@PathVariable Long id){
		
			
			Optional<Player> player = playerServiceImpl.getPlayer(id);
		
			if(player.isPresent()) {
				
				return ResponseEntity.ok(player);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}			
		
	}
}
