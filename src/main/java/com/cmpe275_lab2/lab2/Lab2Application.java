package com.cmpe275_lab2.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cmpe275_lab2.lab2.model.Player;
import com.cmpe275_lab2.lab2.repository.PlayerRepository;
import com.cmpe275_lab2.lab2.repository.SponsorRepository;

@SpringBootApplication
public class Lab2Application implements CommandLineRunner{

	/*@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private SponsorRepository sponsorRepository;
	*/
	public static void main (String[] args) {
		SpringApplication.run(Lab2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("hey");
		
	}

}
