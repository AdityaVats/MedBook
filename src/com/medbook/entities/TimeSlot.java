package com.medbook.entities;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TimeSlot {
	private LocalTime	startTime;
	private LocalTime	endTime;
	
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public double getDurationInMinutes() {
		return (endTime.toSecondOfDay() - startTime.toSecondOfDay())/60.0;
	}
	
	
}
