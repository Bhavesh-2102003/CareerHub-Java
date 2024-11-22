package org.hexaware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnUtil {
	
	public static Connection getConnectionObject(String url)
	{
		Connection connection=null;
		
		try {
			connection=DriverManager.getConnection(url);
			System.out.println("Connection Established Successfully");
		}
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}
		
		return connection;
	}
}
