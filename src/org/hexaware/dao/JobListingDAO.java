package org.hexaware.dao;
import org.hexaware.entity.jobListing;
import java.util.List;
import java.sql.Connection;

public interface JobListingDAO {
	void insertJobListing(Connection connection,jobListing job);
	List<jobListing> getJobListing(Connection connection);
}
