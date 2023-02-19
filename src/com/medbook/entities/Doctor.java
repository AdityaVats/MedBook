package com.medbook.entities;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
	private String name;
	private String speciality;
	private double rating;
	private List<Slot> slots;
	
	public Doctor(String name, String speciality) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.rating = 3.0;
		this.slots = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public List<Slot> getSlots() {
		return slots;
	}
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	} 
	
}
