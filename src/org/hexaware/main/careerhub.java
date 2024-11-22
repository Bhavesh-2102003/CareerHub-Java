package org.hexaware.main;
import org.hexaware.util.DBPropertyUtil;
import org.hexaware.util.DBConnUtil;
import java.sql.DriverManager;
import org.hexaware.entity.*;
import org.hexaware.dao.*;
import java.util.Scanner;
import java.sql.Connection;
import org.hexaware.exceptions.*;


public class careerhub {
	
	public static void main(String[] args) 
	{
		
		Scanner scanner=new Scanner(System.in);
		
		String url=DBPropertyUtil.getConnectionString("db.properties");
		
		if(url==null)
		{
			System.err.println("Invalid URL");
			return;
		}
		
		Connection connection=null;
		try {
			 connection =DBConnUtil.getConnectionObject(url);
			if(connection == null)
			{
				throw new DatabaseConnectionException("Database Connection Failed");
			}
			}catch(DatabaseConnectionException db)
			{
				db.getMessage();
			}
		
		
		System.out.println("Connection Established Successfully");
		
		
		CompanyDAO companydao=new CompanyImpl();
		ApplicantDAO applicantdao=new ApplicantImpl();
		JobApplicationDAO JobApplicationdao=new JobApplicationImpl();
		JobListingDAO JobListingdao=new jobListingImpl();
		
		
		while(true)
		{
			System.out.println("\n--- CareerHub Menu ---");
            System.out.println("1. Add Company");
            System.out.println("2. Add Job Listing");
            System.out.println("3. View All Companies");
            System.out.println("4. View All Job Listings");
            System.out.println("5. Add Applicant");
            System.out.println("6. Apply for Job");
            System.out.println("7. View All Applicants");
            System.out.println("8. Exit");
            
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice)
            {
            case 1:
            	// Add Company
            	System.out.print("Enter Company ID: ");
            	int companyID = scanner.nextInt();
            	scanner.nextLine();  
            	System.out.print("Enter Company Name: ");
            	String companyName = scanner.nextLine();
            	System.out.print("Enter Company Location: ");
            	String companyLocation = scanner.nextLine();
            	
            	Company company = new Company(companyID, companyName, companyLocation);
            	companydao.insertNewCompany(connection, company);
            	System.out.println("Company added successfully!");
            	break;
            	
            case 2:
            	// Add Job Listing
            	System.out.print("Enter Job ID: ");
            	int jobID = scanner.nextInt();
            	System.out.print("Enter Company ID: ");
            	int companyIDForJob = scanner.nextInt();
            	scanner.nextLine();  // Consume newline
            	System.out.print("Enter Job Title: ");
            	String jobTitle = scanner.nextLine();
            	System.out.print("Enter Job Description: ");
            	String jobDescription = scanner.nextLine();
            	System.out.print("Enter Job Location: ");
            	String jobLocation = scanner.nextLine();
            	System.out.print("Enter Salary: ");
            	double salary = scanner.nextDouble();
            	scanner.nextLine();  // Consume newline
            	System.out.print("Enter Job Type: ");
            	String jobType = scanner.nextLine();
            	System.out.print("Enter Posted Date (yyyy-mm-dd): ");
            	String postedDate = scanner.nextLine();
            	
            	jobListing jobListing = new jobListing(jobID, companyIDForJob, jobTitle, jobDescription, jobLocation, salary, jobType, postedDate);
            	JobListingdao.insertJobListing(connection, jobListing);
            	System.out.println("Job Listing added successfully!");
            	break;
            	
            case 3:
            	// View All Companies
            	System.out.println("Fetching all companies...");
            	companydao.getCompany(connection).forEach(c -> {
            		System.out.println("Company ID: " + c.getCompanyID() + ", Name: " + c.getCompanyName() + ", Location: " + c.getLocation());
            	});
            	break;
            	
            case 4:
            	// View All Job Listings
            	System.out.println("Fetching all job listings...");
            	JobListingdao.getJobListing(connection).forEach(j -> {
            		System.out.println("Job ID: " + j.getJobID() + ", Title: " + j.getJobTitle() + ", Company ID: " + j.getCompanyID());
            	});
            	break;
            	
            case 5:
            	// Add Applicant
            	System.out.print("Enter Applicant ID: ");
            	int applicantID = scanner.nextInt();
            	scanner.nextLine();  // Consume newline
            	System.out.print("Enter First Name: ");
            	String firstName = scanner.nextLine();
            	System.out.print("Enter Last Name: ");
            	String lastName = scanner.nextLine();
            	System.out.print("Enter Email: ");
            	String email = scanner.nextLine();
            	System.out.print("Enter Phone: ");
            	String phone = scanner.nextLine();
            	System.out.print("Enter Resume: ");
            	String resume = scanner.nextLine();
            	
            	try {
            	    
            	    if (!email.contains("@")) {
            	        throw new InvalidEmailFormatException("Invalid email format");
            	        
            	    }
            	    Applicant applicant = new Applicant(applicantID, firstName, lastName, email, phone, resume);
                	applicantdao.insertApplicant(connection, applicant);
                	System.out.println("Applicant added successfully!");
            	} catch (InvalidEmailFormatException e) {
            	    System.out.println("Error: " + e.getMessage());
            	}
            	
            	
            	break;
            	
            case 6:
            	// Apply for Job
            	System.out.print("Enter Application ID: ");
            	int applicationID = scanner.nextInt();
            	System.out.print("Enter Job ID to apply for: ");
            	int jobIDForApplication = scanner.nextInt();
            	System.out.print("Enter Applicant ID: ");
            	int applicantIDForApplication = scanner.nextInt();
            	String applicationDate = "2024-11-22";  // Example date
            	String coverLetter = "This is my cover letter";
            	
            	JobApplication jobApplication = new JobApplication(applicationID, jobIDForApplication, applicantIDForApplication, applicationDate, coverLetter);
            	JobApplicationdao.insertJobApplication(connection, jobApplication);
            	System.out.println("Application submitted successfully!");
            	break;
            	
            case 7:
            	// View All Applicants
            	System.out.println("Fetching all applicants...");
            	applicantdao.getApplicants(connection).forEach(a -> {
            		System.out.println("Applicant ID: " + a.getApplicantID() + ", Name: " + a.getFirstName() + " " + a.getLastName());
            	});
            	break;
            	
            case 8:
            	// Exit
            	System.out.println("Exiting...");
            	scanner.close();
            	System.exit(0);
            	break;
            	
            default:
            	System.out.println("Invalid choice. Please try again.");
            	break;
            	
            }
		}
		
	}

}
