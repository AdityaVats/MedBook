package com.medbook.controllers;
import java.time.LocalTime;

import com.medbook.data.DataHolder;
import com.medbook.data.DataHolder.*;
import com.medbook.entities.Doctor;
import com.medbook.entities.Slot;
import com.medbook.entities.TimeSlot;
public class DcotorController {
	
	public static void registerDoc(String name,String speciality){
		if(DataHolder.doctors.containsKey(name)) {
			System.out.println("Doctor already registered");
			return;
		} 
		Doctor doctor = new Doctor(name, speciality);
		DataHolder.doctors.put(name, doctor);
		System.out.println("Welcome "+name);
	}
	public static void markDocAvail(String name,String start,String end) {
		if(!DataHolder.doctors.containsKey(name)) {
			System.out.println("DCOTOR DOESNT EXISTS");
			return;
		}
		Doctor doctor = DataHolder.doctors.get(name);
		LocalTime startTime = LocalTime.of(
								Integer.parseInt(start.split(":")[0]), 
								Integer.parseInt(start.split(":")[1])
								);
		LocalTime endTime = LocalTime.of(
								Integer.parseInt(end.split(":")[0]), 
								Integer.parseInt(end.split(":")[1])
								);
		TimeSlot timeSlot = new TimeSlot(startTime, endTime);
		if(timeSlot.getDurationInMinutes() != DataHolder.slotDurationInMin)	{
			System.out.println("Slot size invalid");
			return;
		}
		Slot slot = new Slot(doctor, timeSlot);
		doctor.getSlots().add(slot);

	}

}
