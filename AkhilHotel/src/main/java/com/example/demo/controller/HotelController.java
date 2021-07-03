package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DB.GetDB;
import com.example.demo.DB.OtherDB;
import com.example.demo.DB.PostDB;
import com.example.demo.models.Guest;
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
					HL=GetDB.getRecords("select * from hotel");
				}
				catch(Exception e) {}
				
				return(HL);
			}
			
			//API CALL-2
			@RequestMapping(value="/bookingConfirmation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
			public Object reservationConfirmation(@RequestBody ReservationDetails rd)
			{
				
				//checks in the database and creates a UNIQUE confirmation_number
				String id = PostDB.generateConfirmationNumber();
				
				//status of insertion, true if success, false if failure
				boolean status= PostDB.insertNewReservation(id, rd);
				
				//If insertion fails
				if(!status)
				{
					id=id+"  ==>  insertion failed";
				}
				
				ReservationConfirmation rc = new ReservationConfirmation();
				rc.setConfirmation_number(id);
				return((Object)rc);
			}
			
			
			//Extra API one
			@RequestMapping("/getListOfConfirmationNumbers")
			public List<ReservationConfirmation> getListOfConfirmationNumbers()
			{
				String query = "select confirmation_number from reservation";
				List<ReservationConfirmation> l = OtherDB.getIdList(query);
				return(l);
			}
			
			//Extra API two
			@RequestMapping("/getReservationDetails")
			public Object getReservationDetails()
			{
				String id="0f1a05d8-cafe-445f-a827-809e653ecc6c";
				
				
				ReservationDetails rd = OtherDB.getRD(id);
				
				
				
				
				//if no such confirmation_number exists
				if(rd==null)
					{
						// Inline class definition
						class MyObject extends Object
						{
							private String error;
							public String getError() {
								return error;
							}
							public void setError(String error) {
								this.error = error;
							}
							
						}
						MyObject o= new MyObject();
						o.setError("confirmation_number = "+id+"  DOES NOT EXIST !!!");
						return((Object)o);
					}
				
				
				
				return((Object)rd);
			}
			
		
			
}
