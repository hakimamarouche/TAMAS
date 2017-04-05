package ca.mcgill.ecse321.teachingassistantmanagementsystem.controller;

import java.util.List;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Applicant;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Application;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.GraderOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.TaOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Application.Status;

public class TeachingAssistantManagementSystemController {
	private JobManager jm;
	private Department dp;
	public TeachingAssistantManagementSystemController(Department dp) {
		  this.dp = dp;
		  this.jm = dp.getTaManager();
	}
	public List<Course> ViewCourses(){
		return jm.getCourses();
	}
	public Course createJobPosting(int taHours, int graderHours, int courseCredit, String courseID, int budget, int studentsEnrolled, Instructor courseInstructor) throws InvalidInputException{
		String error = "";
		
		if(courseID == null){
			error = error + "Course ID can not be empty. ";
		}
		
		if(((taHours* jm.getHourlyRate() + graderHours* jm.getHourlyRate())>budget)){
			error = error + "Budget not sufficient to support all offers. ";
		}
		if(courseCredit<1 || courseCredit > 5){
			error = error + "Course credit invalid. ";
		}
		if (courseInstructor == null){
			error = error + "Course instructor selection invalid. ";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		if(studentsEnrolled<40){
			error = error + "Course size is too small. ";
		}
		Course newCourse = new Course(taHours, graderHours,courseCredit, courseID, budget, studentsEnrolled, jm, courseInstructor);
		TaOffer newTaJob = new TaOffer(taHours,null,0, newCourse, newCourse.getBudget()/taHours);
		GraderOffer newGraderJob = new GraderOffer(graderHours,null,0, newCourse, newCourse.getBudget()/graderHours);
		newTaJob.setCapacity(((budget/2)/jm.getHourlyRate())/taHours);
		newGraderJob.setCapacity(((budget/2)/jm.getHourlyRate())/graderHours);
		newCourse.addJob(newTaJob);
		newCourse.addJob(newGraderJob);
		jm.addCourse(newCourse);
		PersistenceXStream.saveToXMLwithXStream(dp);
		return newCourse;
	}
	public void deleteCourse(int index){
		jm.getCourse(index).delete();
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	public void applyForJob(int mcgillID, String experience, JobOffer job) throws InvalidInputException{
		String error = "";
		if(mcgillID<10000000){
			error = error + "Invalid mcgill ID. ";
		}
		if(experience == null || experience.length()<5){
			error = error + "Experience must be atleast 5 characters long. ";
		}
		if(job == null){
			error = error + "Select a job to apply to. ";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		Applicant newApplicant = new Applicant(mcgillID);
		Application newApplication = new Application(experience, newApplicant, job );
		newApplication.setStatus(Status.UnderReview);
		job.addApplication(newApplication);
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	public void writeReview(String courseID, String review, int studentID) throws InvalidInputException{
		boolean isValid = false;
		String error = "";
		if (courseID == null){
			error = error + "Course ID can not be empty. ";			
		}
		if(review.length()<25){
			error = error + "Review must be atleast 25 characters. ";
		}
		if(studentID <10000000){
			error = error + "Student ID must be valid. ";
		}
		if (error.length()>0){
			throw new InvalidInputException(error);
		}
		for (Course nextCourse : jm.getCourses()){
			if (nextCourse.getCourseId().equals(courseID)){
				for (JobOffer nextOffer : nextCourse.getJob()){
					if (nextOffer.getAcceptedApplicantId() == studentID){
						nextOffer.setReview(review);
						isValid = true;
					}
				}
			}
		}
		if(!isValid){
			throw new InvalidInputException("Job Not Found. ");
		}
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	public void offerJob(String courseID, int studentID) throws InvalidInputException{
		boolean isValid = false;
		String error = "";
		if(courseID == null){
			error = error + "Course ID can not be empty. ";
		}
		if(studentID< 10000000){
			error = error + "Student ID invalid. ";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		for (Course nextCourse : jm.getCourses()){
			if(nextCourse.getCourseId().equals(courseID)){
				for (JobOffer nextOffer : nextCourse.getJob()){
					for (Application nextApp : nextOffer.getApplications()){
						if(nextApp.getApplicant().getMcgillId() == studentID){
							nextApp.setStatus(Status.Offered);
							isValid = true;
						}
					}
				}
			}
		}
		if(!isValid){
			throw new InvalidInputException("Job Not Found. ");
		}
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	public void acceptOffer(String courseID, int studentID) throws InvalidInputException{
		boolean isValid = false;
		String error = "";
		if(courseID == null){
			error = error + "Course ID can not be empty. ";
		}
		if(studentID< 10000000){
			error = error + "Student ID invalid. ";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		for(Course nextCourse: jm.getCourses()){
			if(nextCourse.getCourseId().equals(courseID)){
				for (JobOffer nextOffer : nextCourse.getJob()){
					for (Application nextApp : nextOffer.getApplications()){
						if (nextApp.getApplicant().getMcgillId() == studentID && nextApp.getStatus() != Status.UnderReview){
							nextOffer.setAcceptedApplicantId(studentID);
							nextApp.setStatus(Status.Accepted);
							isValid = true;;
						}
					}
				}
			}
		}
		if(!isValid){
			throw new InvalidInputException("Offer not found. ");
		}
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	
	public void declineOffer(String courseID, int studentID) throws InvalidInputException{
		String error = "";
		boolean isValid = false;
		if(courseID == null){
			error = error + "Course ID can not be empty. ";
		}
		if(studentID< 10000000){
			error = error + "Student ID invalid. ";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		for(Course nextCourse: jm.getCourses()){
			if(nextCourse.getCourseId().equals(courseID)){
				for (JobOffer nextOffer : nextCourse.getJob()){
					for (Application nextApp : nextOffer.getApplications()){
						if (nextApp.getApplicant().getMcgillId() == studentID && nextApp.getStatus() != Status.UnderReview){
							nextApp.setStatus(Status.Declined);
							isValid = true;
						}
					}
				}
			}
		}
		if (!isValid){
			throw new InvalidInputException("Offer not found. ");
		}
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
	
}
