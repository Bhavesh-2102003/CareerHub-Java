package org.hexaware.dao;
import java.sql.Connection;
import org.hexaware.entity.*;
import java.util.List;


public interface JobApplicationDAO {
	public void insertJobApplication(Connection connection,JobApplication application);
	public List<JobApplication> getJobApplication(Connection connection);
}
