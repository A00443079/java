package com.example.demo.models;

import java.util.List;

public class ReservationDetails 
{
	
	private String hotel_name;
	private String checkin;
	private String checkout;
	private List<Guest> guests_list;
	
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public List<Guest> getGuests_list() {
		return guests_list;
	}
	public void setGuests_list(List<Guest> guests_list) {
		this.guests_list = guests_list;
	}
	

}
