package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Garage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String gid;
	private String location;
	private int budget;
	@OneToMany(mappedBy = "garage")
    @JsonManagedReference // Marks this as the parent side of the relationship for serialization.
	private List<Mechanic> mechanics = new ArrayList<Mechanic>();
		
	public Garage() {
		super();
	}



	public Garage(String gid, String location, int budget, List<Mechanic> mechanics) {
		super();
		this.gid = gid;
		this.location = location;
		this.budget = budget;
		this.mechanics = mechanics;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getGid() {
		return gid;
	}



	public void setGid(String gid) {
		this.gid = gid;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public int getBudget() {
		return budget;
	}



	public void setBudget(int budget) {
		this.budget = budget;
	}



	public List<Mechanic> getMechanics() {
		return mechanics;
	}



	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}


}
