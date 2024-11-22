package org.hexaware.dao;
import java.sql.Connection;
import org.hexaware.entity.*;
import java.util.List;

public interface ApplicantDAO {
	public void insertApplicant(Connection connection,Applicant applicant);
	public List<Applicant> getApplicants(Connection connection);
	
}
