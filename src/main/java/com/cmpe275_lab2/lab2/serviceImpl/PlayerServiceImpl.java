package com.cmpe275_lab2.lab2.serviceImpl;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.service.PlayerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    
    public void addPlayer(Player player) {
    	playerRepository.save(player);
	}
    
    public Optional<Player> getPlayer(Long id) {
		if(id != null)
			return playerRepository.findById(id);
		
		else
			return null;
	}
    
    public void addOpponents(Player player1, Player player2) {
    	playerRepository.save(player1);
    	playerRepository.save(player2);
		
	}

	public boolean updatePlayer(Player player, long id)
	{
	Optional<Player> p = playerRepository.findById(id);
	p.ifPresent(player1 -> {
		player1.setFirstname(player.getFirstname());
		player1.setLastname(player.getLastname());
		player1.setDescription(player.getDescription());
		player1.setEmail(player.getEmail());
		player1.setAddress(player.getAddress());
		player1.setSponsor(player.getSponsor());
	});
	if(p.isPresent()){
		playerRepository.save(p.get());
		return true;
	}
	return false;
	}

    
}
