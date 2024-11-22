package org.hexaware.dao;
import java.sql.Connection;
import org.hexaware.entity.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import org.hexaware.entity.*;

public class ApplicantImpl implements ApplicantDAO{
	
	public void insertApplicant(Connection connection,Applicant applicant)
	{
		String query="INSERT INTO APPLICANTS VALUES(?,?,?,?,?,?,?)";
		
		try(PreparedStatement stmt=connection.prepareStatement(query))
		{
			stmt.setInt(1, applicant.getApplicantID());
            stmt.setString(2, applicant.getFirstName());
            stmt.setString(3, applicant.getLastName());
            stmt.setString(4, applicant.getEmail());
            stmt.setString(5, applicant.getPhone());
            stmt.setString(6, applicant.getResume());
            
			stmt.executeUpdate();
			System.out.println("Applicant inserted Successfully");
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
	}
	
	public List<Applicant> getApplicants(Connection connection)
	{
		String query="SELECT * FROM APPLICANTS";
		List <Applicant> applicant=new ArrayList<>();
		
		try(PreparedStatement stmt=connection.prepareStatement(query))
		{
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				Applicant app = new Applicant(
					    rs.getInt("ApplicantID"),
					    rs.getString("FirstName"),
					    rs.getString("LastName"),
					    rs.getString("Email"),
					    rs.getString("Phone"),
					    rs.getString("Resume")
					);
				applicant.add(app);
			}
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		return applicant;
	}
}
