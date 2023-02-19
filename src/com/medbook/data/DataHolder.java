package com.medbook.data;

import java.util.HashMap;
import java.util.Map;

import com.medbook.entities.Doctor;
import com.medbook.entities.Patient;
import com.medbook.entities.Slot;

public class DataHolder {
	public static Map<String,Doctor>	doctors = new HashMap<>();
	public static Map<String,Patient>	patients = new HashMap<>();
	public static Map<Integer,Slot>		bookings = new HashMap<>();
	public static final double slotDurationInMin = 60.0; 
	
	
	
}
