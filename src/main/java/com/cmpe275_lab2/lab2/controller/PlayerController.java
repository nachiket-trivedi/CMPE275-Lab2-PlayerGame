package com.cmpe275_lab2.lab2.controller;

import com.cmpe275_lab2.lab2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;
}
