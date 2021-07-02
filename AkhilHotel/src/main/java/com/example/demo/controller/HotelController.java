package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
			public String getListOfHotels()
			{
				return("List Of Hotels");
			}
			
}
