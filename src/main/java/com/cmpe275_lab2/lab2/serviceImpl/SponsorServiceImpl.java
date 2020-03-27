package com.cmpe275_lab2.lab2.serviceImpl;

import com.cmpe275_lab2.lab2.repository.SponsorRepository;
import com.cmpe275_lab2.lab2.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorServiceImpl implements SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;
}
