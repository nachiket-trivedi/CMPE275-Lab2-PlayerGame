package com.cmpe275_lab2.lab2.controller;

import com.cmpe275_lab2.lab2.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;
}
