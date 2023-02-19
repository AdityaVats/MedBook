package com.medbook.entities;

import java.util.LinkedList;
import java.util.Queue;

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
	

}
