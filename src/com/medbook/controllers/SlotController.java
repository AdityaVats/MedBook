package com.medbook.controllers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;

import com.medbook.data.DataHolder;
import com.medbook.entities.Patient;
import com.medbook.entities.Slot;
import com.medbook.entities.SlotStatus;

public class SlotController {
	
	public static void bookAppointment(String doctorName,String patientName,String start,boolean waitlist) {
		if(!DataHolder.doctors.containsKey(doctorName)) {
			System.out.println("Doctor Doesnt exist");
			return;
		}
		if(!DataHolder.patients.containsKey(patientName)) {
			System.out.println("Patient Doesnt exist");
			return;
		}
		Optional<Slot> optSlot =  DataHolder.doctors.get(doctorName).getSlots()
											.parallelStream()
											.filter(
													slot -> 
													start.equals(slot.getTimeSlot().getStartTime().getHour()+":"
													+slot.getTimeSlot().getStartTime().getMinute())
													
													)
											.findFirst();
		if(!optSlot.isPresent()) {
			System.out.println("Slot doesnt exist");
		}
		Patient patient = DataHolder.patients.get(patientName);
		Slot slot = optSlot.get();
		boolean alreadyBooked = patient.getSlots()
				.parallelStream()
				.filter(myBookedSlot -> myBookedSlot.getStatus().equals(SlotStatus.BOOKED))
				.anyMatch( (myBookedSlot) -> {
					return myBookedSlot.getTimeSlot().getStartTime().isAfter(
							slot.getTimeSlot().getEndTime())
					||
					myBookedSlot.getTimeSlot().getEndTime().isBefore(
							slot.getTimeSlot().getStartTime());
					

					
				}
				);
		if(alreadyBooked) {
			System.out.println("PATIENT HAS AN OVERLAPPING BOOKING");
			return;
		}
		synchronized (slot) {
			if(slot.getStatus().equals(SlotStatus.BOOKED)) {
				if(!waitlist) {
					System.out.println("ALREADY BOOKED");
					return;
				}
				slot.getWaitlist().offer(patient);
				return;		
			}
			
			slot.setPatient(patient);
			slot.setStatus(SlotStatus.BOOKED);
			int bId = new Random().nextInt();
			while(DataHolder.bookings.containsKey(bId)) {
				bId = new Random().nextInt();
			}
			DataHolder.bookings.put(bId,slot);
			System.out.println("BOOKED WITH ID"+bId);
			patient.getSlots().add(slot);
			
		}

	}
	public static void cancelAppointment(Integer bookingId) {
		if(!DataHolder.bookings.containsKey(bookingId)) {
			System.out.println("NO SUCH BOOKING EXISTS");
			return;
		}
		Slot slot = DataHolder.bookings.get(bookingId);
		slot.getPatient().getSlots().remove(slot);
		
		if(slot.getWaitlist().isEmpty()) {
			slot.setStatus(SlotStatus.AVAILABLE);
			slot.setPatient(null);
			DataHolder.bookings.remove(bookingId);
			System.out.println("BOOKING CANCELED");
			return;
		} else {
			Patient newPatient = slot.getWaitlist().poll();
			slot.setPatient(newPatient);
			System.out.println("BOOKING CANCELLED");
			System.out.println("BOOKING OCCUPIED TO WAITLIST PERSON "+newPatient.getName());
			newPatient.getSlots().add(slot);
			return;
		}
	}

}
