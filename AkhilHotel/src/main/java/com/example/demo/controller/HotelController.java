package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DB.DB;
import com.example.demo.models.HotelsList;
import com.example.demo.models.ReservationConfirmation;
import com.example.demo.models.ReservationDetails;


@RestController
public class HotelController 
{

			//API CALL
			@RequestMapping("/")
			public String home()
			{
				
				String str="<h3>HOME</h3><br>\n";
				String str1="<h3>This API is developed by:</h3><br><br>\n";
				String str2="<h1>AKHIL ROY - A00443079</h1><br>\n";
						
				return(str+str1+str2);
			}
			
			//API CALL
			@RequestMapping("/about")
			public String about()
			{
				return("MCDA 5550\t\t\n\nAssignmmnet 1:\t\t\nBuilding Rest API for HOTEL RESERVATION ");
			}
			
			//API CALL-1
			@RequestMapping(value="/hotels", method=RequestMethod.GET, produces="application/json")
			public HotelsList getListOfHotels()
			{
				HotelsList HL=null;
				
				try
				{
					HL=DB.getRecords("select * from hotel");
				}
				catch(Exception e) {}
				
				return(HL);
			}
			
			//API CALL-2
			@RequestMapping(value="/bookingConfirmation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
			public Object reservationConfirmation(@RequestBody ReservationDetails rd)
			{
				
				return(generateConfirmationNumber(rd));	
			}
			
			public Object generateConfirmationNumber(ReservationDetails rd)
			{
				UUID uuid = UUID.randomUUID();
		        String id = uuid.toString();
				
				ReservationConfirmation rc = new ReservationConfirmation();
				rc.setConfirmation_number(id);
				
				return((Object)rc);
				
				
				
			}
			
}
