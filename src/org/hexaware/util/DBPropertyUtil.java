package org.hexaware.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DBPropertyUtil {
	public static String getConnectionString(String propName)
	{
		propName="./src/"+propName;
		
		Properties properties=new Properties();
		
		String protocol=null;
		String dbname=null;
		String user=null;
		String password=null;
		
		try(FileInputStream input=new FileInputStream(propName))
		{
			properties.load(input);
			
			protocol=properties.getProperty("protocol");
			dbname=properties.getProperty("dbname");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
			
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		if(protocol!=null && dbname!=null && user!=null && password!=null)
		{
			return protocol+"/"+dbname+"?user="+user+"&password="+password;
		}
		
		return null;
	}
	
	
}
