package com.cmpe275_lab2.lab2.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Player {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;  // primary key
	    private String firstname;
	    private String lastname;
	    @Column(unique=true)
	    private String email;
	    private String description;
	    @Embedded
	    private Address address;
	    @ManyToOne
	    private Sponsor sponsor;
	    @ManyToMany
	    private List<Player> opponents;
	    
	    public Player() {
	    	
	    }
		
		public Player(long id, String firstname, String lastname, String email, String description, Address address,
				Sponsor sponsor, List<Player> opponents) {
			
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.description = description;
			this.address = address;
			this.sponsor = sponsor;
			this.opponents = opponents;
		}
		
		
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public Sponsor getSponsor() {
			return sponsor;
		}
		public void setSponsor(Sponsor sponsor) {
			this.sponsor = sponsor;
		}
		public List<Player> getOpponents() {
			return opponents;
		}
		public void setOpponents(List<Player> opponents) {
			this.opponents = opponents;
		}
	    


}
