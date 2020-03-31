package com.cmpe275_lab2.lab2.serviceImpl;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.model.Sponsor;
import com.cmpe275_lab2.lab2.repository.SponsorRepository;
import com.cmpe275_lab2.lab2.service.SponsorService;

import java.util.List;
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

    public boolean updateSponsor(Sponsor sponsor, String name) {
        List<Sponsor> sponsor1 = sponsorRepository.findByName(name);
        if(!sponsor1.isEmpty()){
            sponsor1.get(0).setName(sponsor.getName());
            sponsor1.get(0).setDescription(sponsor.getDescription());
            sponsor1.get(0).setAddress(sponsor.getAddress());
            sponsorRepository.save(sponsor1.get(0));
            return true;
        }
        return false;
    }
    public void deleteSponsor(Long id) {
        System.out.println("----->"+id);
        sponsorRepository.deleteById(id);
    }


}
