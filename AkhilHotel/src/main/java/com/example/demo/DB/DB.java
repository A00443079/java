package com.example.demo.DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import java.lang.Integer;

import javax.sql.DataSource;
 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.demo.models.Hotel;
import com.example.demo.models.HotelsList;
 
public class DB 
{
     
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://dbcourse.cs.smu.ca:3306/u53";
    private static final String dbUsername = "u53";
    private static final String dbPassword = "sleptHAVANA10";
 
    //private static final String selectSql = "SELECT * FROM employee";
 
    private static DataSource dataSource;
     
    public static HotelsList getRecords(String query) throws Exception 
    {
     
        dataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
         
        List<Map<String, Object>> rows = template.queryForList(query);
        
        List<Hotel> hl= new ArrayList<Hotel>();
        
         
        if (rows!=null && !rows.isEmpty()) 
        {
        
             
            for (Map<String, Object> row : rows) 
            {
            	Hotel h = new Hotel();
            	
            	
                for (Iterator<Map.Entry<String, Object>> it = row.entrySet().iterator(); it.hasNext();) 
                {
                	
                	
                    Map.Entry<String, Object> entry = it.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    
                     if(key.equals("hotel_name"))
                     {
                    	 h.setHotel_name(""+value);
                     }
                     else if (key.equals("price"))
                     {
                    	 h.setPrice(Integer.parseInt(""+value));
                     }
                     else if(key.equals("availability"))
                     {
                    	 
                    	 if(Integer.parseInt(""+value)==1)
                    		 h.setAvailability(true);
                    	 else
                    		 h.setAvailability(false);
                    	 
                     }
                     
                    
                }
                
                hl.add(h);
                 
            }
            
            
            
             
        }
        HotelsList HL = new HotelsList();
        HL.setHotels_list(hl);
        
        return(HL);
         
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