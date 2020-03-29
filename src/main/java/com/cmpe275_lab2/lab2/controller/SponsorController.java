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
import com.cmpe275_lab2.lab2.repository.SponsorRepository;
import com.cmpe275_lab2.lab2.serviceImpl.SponsorServiceImpl;

@RestController
public class SponsorController {
	
	
	@Autowired
	private SponsorServiceImpl sponsorServiceImpl;
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/sponsor", produces = { "application/json", "application/xml" })
	public ResponseEntity<Sponsor> addSponsor(@RequestParam String name
            , @RequestParam(required = false) String description
            , @RequestParam(required = false) String street
            , @RequestParam(required = false) String city
            , @RequestParam(required = false) String state
            , @RequestParam(required = false) String zip) {
		List<Sponsor> ex_sponsors = sponsorRepository.findByName(name.trim());
		if(ex_sponsors.size() > 0){
			 return new ResponseEntity(HttpStatus.CONFLICT);
		}
		else {
		Sponsor sponsor = new Sponsor();
		sponsor.setName(name.trim());
		if(description!=null) {
			sponsor.setDescription(description.trim());
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
		sponsor.setAddress(add);
		sponsorServiceImpl.addSponsor(sponsor);
		return ResponseEntity.ok(sponsor);
		}
	}
	
	@RequestMapping(value = "/sponsor/{name}", produces = { "application/json", "application/xml"})
	public ResponseEntity<Object> getSponsor(@PathVariable String name){
			
			Optional<Sponsor> sponsor = sponsorServiceImpl.getSponsor(name);
			
			if(sponsor.isPresent()) {
				
				return ResponseEntity.ok(sponsor);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}			
		
	}

}
