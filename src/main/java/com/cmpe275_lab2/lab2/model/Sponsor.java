package com.cmpe275_lab2.lab2.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sponsor {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		@Column(unique=true)
	 	private String name;  // primary key, >= two characters after trimming white spaces
		private String description;
		@Embedded
	    private Address address;
	    private List<Player> beneficiaries;
	    
	    public Sponsor() {
	    	
	    }
	    public Sponsor(String name, String description, Address address, List<Player> beneficiaries) {
			super();
			this.name = name;
			this.description = description;
			this.address = address;
			this.beneficiaries = beneficiaries;
		}
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public List<Player> getBeneficiaries() {
			return beneficiaries;
		}
		public void setBeneficiaries(List<Player> beneficiaries) {
			this.beneficiaries = beneficiaries;
		}
	    
	    


}
