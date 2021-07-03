package com.example.demo.DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.lang.Integer;

import javax.sql.DataSource;
 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.demo.models.Guest;
import com.example.demo.models.Hotel;
import com.example.demo.models.HotelsList;
import com.example.demo.models.ReservationConfirmation;
import com.example.demo.models.ReservationDetails;

public class OtherDB 
{
     
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://dbcourse.cs.smu.ca:3306/u53";
    private static final String dbUsername = "u53";
    private static final String dbPassword = "sleptHAVANA10";
 
    //private static final String selectSql = "SELECT * FROM employee";
 
    private static DataSource dataSource;
     
    public static List<ReservationConfirmation> getIdList(String query) 
    {
     
        dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
         
        List<Map<String, Object>> rows = template.queryForList(query);
        
        List<ReservationConfirmation> cl= new ArrayList<ReservationConfirmation>();
        
         
        if (rows!=null && !rows.isEmpty()) 
        {
        
             
            for (Map<String, Object> row : rows) 
            {
            	ReservationConfirmation c = new ReservationConfirmation();
            	
            	
                for (Iterator<Map.Entry<String, Object>> it = row.entrySet().iterator(); it.hasNext();) 
                {
                	
                	
                    Map.Entry<String, Object> entry = it.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    c.setConfirmation_number(""+value);
                    
                }
                
                cl.add(c);
                 
            }
            
            
            
             
        }
        
        
        return(cl);
         
    }
    
    public static ReservationDetails getRD(String id)
    {
    	ReservationDetails rd = new ReservationDetails();
    	dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String query=null;
        List<Map<String, Object>> rows = null;
        
        
        
        //STEP 1
        query="select hotel_name, checkin, checkout from reservation where confirmation_number="+"\""+id+"\"";
        rows = template.queryForList(query);
         
        if (rows==null || rows.isEmpty()) return(null);
        
        //STEP 1: Set 'hotel_name', 'checkin', 'checkout'
        if (rows!=null && !rows.isEmpty()) 
        {
            for (Map<String, Object> row : rows) 
            {
                for (Iterator<Map.Entry<String, Object>> it = row.entrySet().iterator(); it.hasNext();) 
                {
                	
                	
                    Map.Entry<String, Object> entry = it.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    if(key.equals("hotel_name")) rd.setHotel_name(""+value);
                    else if(key.equals("checkin")) rd.setCheckin(""+value);
                    else if(key.equals("checkout")) rd.setCheckout(""+value);
                    
                    
                }
            }
        }
        
        
        
        //STEP 2
        query="select guest_name, gender from guest where confirmation_number="+"\""+id+"\"";
        rows = template.queryForList(query);
        
        List<Guest> gl= new ArrayList<Guest>();
         
        if (rows==null || rows.isEmpty()) return(null);
        
        //STEP 2: Set 'guest_name', 'gender'
        if (rows!=null && !rows.isEmpty()) 
        {
            for (Map<String, Object> row : rows) 
            {
            	Guest g = new Guest();
                for (Iterator<Map.Entry<String, Object>> it = row.entrySet().iterator(); it.hasNext();) 
                {
                	
                	
                    Map.Entry<String, Object> entry = it.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    if(key.equals("guest_name")) g.setGuest_name(""+value);
                    else if(key.equals("gender")) g.setGender(""+value);
                    
                }
                
                gl.add(g);
            }
        }
        
        rd.setGuests_list(gl);
    	return(rd);
    }
    public static DriverManagerDataSource getDataSource() 
    {
 
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 
		  dataSource.setDriverClassName(driverClassName);
		 
		  dataSource.setUrl(url);
		 
		  dataSource.setUsername(dbUsername);
		 
		  dataSource.setPassword(dbPassword);
		 
		  return dataSource;
    }
 
}