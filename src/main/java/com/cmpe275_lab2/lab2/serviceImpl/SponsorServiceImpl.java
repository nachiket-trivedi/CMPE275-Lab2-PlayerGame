package com.cmpe275_lab2.lab2.serviceImpl;

import com.cmpe275_lab2.lab2.model.Sponsor;
import com.cmpe275_lab2.lab2.repository.SponsorRepository;
import com.cmpe275_lab2.lab2.service.SponsorService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorServiceImpl implements SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;
    
    
    public Optional<Sponsor> getSponsor(long id) {
		return sponsorRepository.findById(id);
	}
    
    public Optional<Sponsor> getSponsor(String name){
    	return sponsorRepository.findOneByName(name);
    }
    public void addSponsor(Sponsor sponsor) {
    	sponsorRepository.save(sponsor);
	}

    
	
    
    
}
