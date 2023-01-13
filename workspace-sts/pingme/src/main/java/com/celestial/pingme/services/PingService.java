package com.celestial.pingme.services;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class PingService
{
	public	String	getTime()
	{
		LocalTime time = LocalTime.now();
		
		String theTime = "[pingMe time: " + time.toString() + "]";

		return theTime;
	}
}
