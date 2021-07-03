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
import com.example.demo.models.ReservationDetails;

public class PostDB 
{
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://dbcourse.cs.smu.ca:3306/u53";
    private static final String dbUsername = "u53";
    private static final String dbPassword = "sleptHAVANA10";
 
 
    private static DataSource dataSource;
    
    public static boolean insertNewReservation(String id, ReservationDetails rd)
    {
    	dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        
        String hotel_name=rd.getHotel_name();
        String checkin=rd.getCheckin();
        String checkout=rd.getCheckout();
        String query= "insert into reservation values("+"\""+id+"\", "+"\""+hotel_name+"\", "+"\""+checkin+"\", "+"\""+checkout+"\""+")";
        
        int result = template.update(query);	//insert into 1st table 'reservation'
        if(result==0) return(false);	//if 1st insertion fails, then rest of the operations are aborted, overall status= false
        
        
        List<Guest> gl = rd.getGuests_list();
        for(Guest g: gl)
        {
        	String guest_name = g.getGuest_name();
        	String gender = g.getGender();
        	
        	query=  "insert into guest values("+"\""+id+"\", "+"\""+guest_name+"\", "+"\""+gender+"\""+")";
        	
        	result = template.update(query);	//insert into 2st table 'guest'
            if(result==0) return(false);	//if above insertion fails, then rest of the operations are aborted, overall status= false
        
        }
        
        
        
    	return(true);
    }
    
    
    public static String generateConfirmationNumber()
    {
    	
    	dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        
        //int counter=0;
        
        String query=null;
        UUID uuid = null;
        String id = null;
        List<Map<String, Object>> rows=null;
        
        
        //Check if a UUID is present in database, if present then create new UUID and check again
        do
        {
        	uuid = UUID.randomUUID();
        	id = uuid.toString();
        	
        	
        	query="select * from reservation where confirmation_number=\""+id+"\"";
            
            rows = template.queryForList(query);
        }
        while(PostDB.existsId(rows));
        
        return(id);
        
    }
    
    //to check if rows have been returned for the particular UUID (to avoid duplication)
    public static boolean existsId(List<Map<String, Object>> rows)
    {
    	
        
        if (rows!=null && !rows.isEmpty()) return(true);
        else if(rows==null) return(true);
        else if(rows!=null && rows.isEmpty()) return(false);
        
        return(true);
     
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

