package com.medbook;

import java.util.Scanner;

import com.medbook.controllers.DcotorController;
import com.medbook.controllers.PatientController;
import com.medbook.controllers.SearchController;
import com.medbook.controllers.SlotController;

public class MainDriver {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			int n = sc.nextInt();
			switch(n) {
				case 0:
					// register doc
					String name = sc.next();
					String speciality = sc.next();
					DcotorController.registerDoc(name, speciality);
					break;
				case 1:
					// make Doctor available
					 name = sc.next();
					String start = sc.next();
					String end = sc.next();
					DcotorController.markDocAvail(name, start, end);
					break;
				case 3:
					// register Patient
					name = sc.next();
					PatientController.registerPatient(name);
					break;
				case 4:
					// slot by speciality
					speciality = sc.next();
					SearchController.slotBySpeciality(speciality);
					break;
				case 5:
					// show my bookings
					name = sc.next();
					PatientController.myBookings(name);
					break;
				case 6:
					// book appointment
					String dName = sc.next();
					String pName = sc.next();
					start = sc.next();
					boolean waitlist = sc.nextBoolean();
					
					SlotController.bookAppointment(dName, pName, start, waitlist);
					break;
				case 7:
					// cancel appointment
					Integer bookingId = sc.nextInt();
					SlotController.cancelAppointment(bookingId);
					break;
				case -1:
					System.exit(0);
			
			}
			
			
			
		}

	}
}
