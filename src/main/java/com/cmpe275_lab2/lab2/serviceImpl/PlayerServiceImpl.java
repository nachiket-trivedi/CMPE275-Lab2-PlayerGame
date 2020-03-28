package com.cmpe275_lab2.lab2.serviceImpl;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    
    public void addPlayer(Player player) {
    	playerRepository.save(player);
	}
    
}
