package org.hexaware.dao;
import org.hexaware.entity.*;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CompanyImpl implements CompanyDAO {
	
	public void insertNewCompany(Connection connection,Company company)
	{
		String query="INSERT INTO COMPANIES VALUES(?,?,?)";
		
		try(PreparedStatement stmt =connection.prepareStatement(query))
		{
			stmt.setInt(1, company.getCompanyID());
			stmt.setString(2,company.getCompanyName());
			stmt.setString(3,company.getLocation());
			
			stmt.executeUpdate();
			System.out.println("Company Inserted Successfully");
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		
	}
	
	public List<Company> getCompany(Connection connection)
	{
		String query="SELECT * FROM COMPANIES";
		List<Company> company=new ArrayList<>();
		
		try(Statement stmt=connection.createStatement())
		{
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				Company comp=new Company(
						rs.getInt("CompanyID"),
						rs.getString("CompanyName"),
						rs.getString("location"));
				company.add(comp);			}
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		return company;
	}
	
	
	
}
