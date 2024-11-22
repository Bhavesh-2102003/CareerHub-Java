package org.hexaware.dao;
import java.sql.Connection;
import org.hexaware.entity.*;
import java.sql.*;
import java.util.*;
public class JobApplicationImpl implements JobApplicationDAO{
	
	public void insertJobApplication(Connection connection,JobApplication application)
	{
		String query="INSERT INTO APPLICATIONS VALUES(?,?,?,?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(query))
		{
			stmt.setInt(1, application.getApplicationID());
	        stmt.setInt(2, application.getJobID());      
	        stmt.setInt(3, application.getApplicantID());  
	        stmt.setString(4,application.getApplicationDate());
	        stmt.setString(5, application.getCoverLetter()); 
	        
	        stmt.executeUpdate();
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
	}
	
	public List<JobApplication> getJobApplication(Connection connection)
	{
		String query="SELECT * FROM APPLICATIONS";
		List<JobApplication> application=new ArrayList<>();
		
		try(PreparedStatement stmt=connection.prepareStatement(query))
		{
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				JobApplication app=new JobApplication(
						    rs.getInt("ApplicationID"),           
						    rs.getInt("JobID"),                   
						    rs.getInt("ApplicantID"),             
						    rs.getString("ApplicationDate"), 
						    rs.getString("CoverLetter") 
						);
				application.add(app);
			}
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		return application;
		
	}
}
