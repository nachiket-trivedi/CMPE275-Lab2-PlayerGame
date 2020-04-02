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
import org.springframework.web.bind.annotation.*;

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
		System.out.println("post");
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

	@GetMapping(value = "/player/{id}", produces = { "application/json", "application/xml"})
	public ResponseEntity<Object> getPlayer(@PathVariable Long id){

		System.out.println("get");
			Optional<Player> player = playerServiceImpl.getPlayer(id);

			if(player.isPresent()) {

				return ResponseEntity.ok(player);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
	}

	@DeleteMapping("/player/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Long id) {
		System.out.println("delete");
		Optional<Player> playersList = playerRepository.findById(id);

		if (playersList.isPresent()) {
			playerServiceImpl.deletePlayer(id);
            return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping(value="/player/{id}", produces = { "application/json", "application/xml" })
	ResponseEntity<Object> updatePlayer(@PathVariable(name = "id") long id,@RequestParam(required = false) String firstname
			, @RequestParam(required = false) String lastname
			, @RequestParam String email
			, @RequestParam(required = false) String description
			, @RequestParam(required = false) String street
			, @RequestParam(required = false) String city
			, @RequestParam(required = false) String state
			, @RequestParam(required = false) String zip
			, @RequestParam(required = false) Long sponsor_id
			, @RequestParam(required = false) String opponents
	) {
		System.out.println("---------------put");
		if(email==null || email.equals(""))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		Player player = new Player();
		if(opponents!=null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Opponents are not supposed to be passed as parameters");
		Optional<Player> player_old = playerServiceImpl.getPlayer(id);
		if(!player_old.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		List<Player> ex_player = playerRepository.findByEmail(email.trim());
		if(ex_player.size() >0 && id!=ex_player.get(0).getId()){
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		if(firstname!=null) {
			player.setFirstname(firstname.trim());
		}
		else {
			player.setFirstname(player_old.get().getFirstname());
		}
		if(lastname!=null) {
			player.setLastname(lastname.trim());
		}
		else {
			player.setLastname(player_old.get().getLastname());
		}
		player.setEmail(email.trim());
		if(description!=null) {
			String new_description=description.trim();
			player.setDescription(new_description);
		}
		else {
			player.setDescription(player_old.get().getDescription());
		}
		Address address=new Address();
		Address address_old = new Address();
		address_old = player_old.get().getAddress();
		if(address_old==null){
			address_old = new Address();
			address_old.setStreet(null);
			address_old.setCity(null);
			address_old.setState(null);
			address_old.setZip(null);
		}
		if(street!=null) {
			address.setStreet(street.trim());
		}
		else {
			address.setStreet(address_old.getStreet());
		}
		if(city!=null) {
			address.setCity(city.trim());
		}
		else {
			address.setCity(address_old.getCity());
		}
		if(state!=null) {
			address.setState(state.trim());
		}
		else {
			address.setState(address_old.getState());
		}
		if(zip!=null) {
			address.setZip(zip.trim());
		}
		else {
			address.setZip(address_old.getZip());
		}
		player.setAddress(address);
		if(sponsor_id!=null) {
			Optional<Sponsor> tempSponsor = sponsorServiceImpl.getSponsor(sponsor_id);
			if (tempSponsor.isPresent()) {
				Sponsor s = tempSponsor.get();
				player.setSponsor(s);
			} else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sponsor Id doesn't exist");
			}
		}
		else{
			player.setSponsor(null);
		}
		boolean response=playerServiceImpl.updatePlayer(player, id);
		if(response)
			return ResponseEntity.ok(playerServiceImpl.getPlayer(id));
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
}
