package com.medbook.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.medbook.data.DataHolder;
import com.medbook.entities.Slot;

public class SearchController {
	
	public static void slotBySpeciality(String speciality){
		List<Slot> list = new ArrayList<>();
		DataHolder.doctors.entrySet()
						  .stream()
						  .map( entrySet -> entrySet.getValue() )
						  .filter(doctor -> doctor.getSpeciality().equals(speciality))
						  .flatMap(doctor -> doctor.getSlots().stream())
						  // sort based on ratings
						  .sorted(
								  (s1,s2) ->
								  (int)(s2.getDoctor().getRating()-s1.getDoctor().getRating())
								  )
						  .forEach(slot -> System.out.println(
								  				slot.getDoctor().getName()
								  				+ " "
								  				+ slot.getTimeSlot().getStartTime()
								  				+ " "
								  				+ slot.getTimeSlot().getEndTime()
								  			)
						 );
	}

}
