package com.medbook.entities;

import java.util.ArrayList;
import java.util.List;

public class Patient {
	private String name;
	private List<Slot> slots;
	public Patient(String name) {
		super();
		this.name = name;
		this.slots = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Slot> getSlots() {
		return slots;
	}
	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	
	
	

}
