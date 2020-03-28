package com.cmpe275_lab2.lab2.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import java.util.List;


@Entity
@Table(name = "player")
public class Player {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="PL_ID")
	    private long id;  // primary key
	    @NotNull
	    private String firstname;
	    @NotNull
	    private String lastname;
	    @NotNull
	    @Column(unique=true)
	    private String email;
	    private String description;
	    @Embedded
	    private Address address;
	    @ManyToOne(fetch=FetchType.EAGER,optional=true)
	    @JsonIgnoreProperties({"beneficiaries"})
	    @JoinColumn(name="sponsor_id", nullable=true)
	    private Sponsor sponsor;
	    @ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	    @JoinTable(name="opponent",
		joinColumns={@JoinColumn(name="Player", referencedColumnName="PL_ID")},
		inverseJoinColumns={@JoinColumn(name="Opponent", referencedColumnName="PL_ID")})
	    private List<Player> opponents;
	    
	    public Player() {
	    	
	    }
		
		public Player(String firstname, String lastname, String email, String description, Address address,
				Sponsor sponsor) {
			
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.description = description;
			this.address = address;
			this.sponsor = sponsor;
			//this.opponents = opponents;
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
		public void setOpponents(Player opponents) {
			if(this.opponents.contains(opponents))
				return;
			this.opponents.add(opponents);
		}
	    


}
