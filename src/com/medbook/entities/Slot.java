package com.medbook.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class Slot {
	private Doctor doctor;
	private SlotStatus status;
	private TimeSlot timeSlot;
	private Queue<Patient> waitlist;
	private Patient patient;
	public Slot(Doctor doctor, TimeSlot timeSlot) {
		super();
		this.doctor = doctor;
		this.status = SlotStatus.AVAILABLE;
		this.timeSlot = timeSlot;
		this.setPatient(null);
		this.setWaitlist(new LinkedList<>());
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public SlotStatus getStatus() {
		return status;
	}
	public void setStatus(SlotStatus status) {
		this.status = status;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
	public Queue<Patient> getWaitlist() {
		return waitlist;
	}
	public void setWaitlist(Queue<Patient> waitlist) {
		this.waitlist = waitlist;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void bookSlot(List<Slot> slots,Slot newBooking) {
		TreeSet<Slot> sortedSlots = new TreeSet<Slot>(
				(slot1,slot2) -> (slot1.getTimeSlot().getStartTime()).compareTo(slot2.getTimeSlot().getStartTime())
		);
		sortedSlots.addAll(slots);
		
		Slot higherSlot = sortedSlots.ceiling(newBooking);
		Slot lowerSlot = sortedSlots.floor(newBooking);
		
		boolean slotFree = lowerSlot.getTimeSlot().getEndTime().isBefore(newBooking.getTimeSlot().getStartTime())
				&&
			newBooking.getTimeSlot().getEndTime().isBefore(higherSlot.getTimeSlot().getStartTime())
			
				;
		
		if(slotFree) {
			// book the slot
			synchronized (lowerSlot) {
				slotFree = lowerSlot.getTimeSlot().getEndTime().isBefore(newBooking.getTimeSlot().getStartTime())
						&&
					newBooking.getTimeSlot().getEndTime().isBefore(higherSlot.getTimeSlot().getStartTime())
					
						;
				if(slotFree) {
					// actually book it
					
				} else {
					System.out.println("CANNOT BOOK ..THERE ARE OVERLAPPING SLOTS!!!!");
				}
			}
			
		} else {
			System.out.println("CANNOT BOOK ..THERE ARE OVERLAPPING SLOTS!!!!");
		}
		
		
		
		
	}
}
