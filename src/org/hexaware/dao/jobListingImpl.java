package org.hexaware.dao;
import org.hexaware.dao.JobListingDAO;
import org.hexaware.entity.jobListing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;	
import java.util.*;

public class jobListingImpl implements JobListingDAO {
	
	public void insertJobListing(Connection connection,jobListing job)
	{
		String query="INSERT INTO JOBS VALUES(?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt=connection.prepareStatement(query))
		{
			stmt.setInt(1, job.getJobID());
            stmt.setInt(2, job.getCompanyID());
            stmt.setString(3, job.getJobTitle());
            stmt.setString(4, job.getJobDescription());
            stmt.setDouble(5, job.getSalary());
            stmt.setString(6, job.getJobType());
            stmt.setString(7, job.getPostedDate()); 
            stmt.setString(8, job.getJobLocation());

            stmt.executeUpdate();
            System.out.println("Job listing inserted successfully!");
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
	}
	
	public List<jobListing> getJobListing(Connection connection)
	{
		List<jobListing> jobListing = new ArrayList<>();
		String query="SELECT * FROM JOBS";
		
		try(Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);)
		{
			while(rs.next())
			{
				jobListing job=new jobListing(
						rs.getInt("jobID"),
		                rs.getInt("companyID"),
		                rs.getString("jobTitle"),
		                rs.getString("jobDescription"),
		                rs.getString("jobLocation"),
		                rs.getDouble("salary"), 
		                rs.getString("jobType"),
		                rs.getString("postedDate")
						);
				
				jobListing.add(job);
			}
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		return jobListing;

}
}
