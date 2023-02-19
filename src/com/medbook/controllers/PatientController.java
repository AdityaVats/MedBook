package com.medbook.controllers;

import com.medbook.data.DataHolder;
import com.medbook.entities.Patient;
import com.medbook.entities.SlotStatus;

public class PatientController {

	public static void registerPatient(String name) {
		if(DataHolder.patients.containsKey(name)) {
			System.out.println("PATIENT ALREADY EXISTS");
			return;
		}
		Patient patient = new Patient(name);
		DataHolder.patients.put(name, patient);
		System.out.println("PATIENT REGISTERED");
	}
	public static  void myBookings(String name) {
		if(!DataHolder.patients.containsKey(name)) {
			System.out.println("PATIENT DOEST EXIST");
			return;
		}
		DataHolder.patients.get(name)
				.getSlots().stream()
				.filter(slot -> slot.getStatus().equals(SlotStatus.BOOKED))
				.sorted(
						(s1,s2) -> s1.getTimeSlot().getStartTime().compareTo(
								  s2.getTimeSlot().getStartTime())
						)
				.forEach( slot ->
						System.out.println("Dr " + slot.getDoctor().getName() + " Start "+
									slot.getTimeSlot().getStartTime()
									+" END "+slot.getTimeSlot().getEndTime())
						
						);
		
	}
}
