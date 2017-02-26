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
	public Course createJobPosting(int taHours, int graderHours, int courseCredit, String courseID, int budget, int studentsEnrolled, Instructor courseInstructor, int taOfferCapacity, int graderOfferCapacity) throws InvalidInputException{
		String error = "";
		if(taOfferCapacity <1){
			error = error + "TA offer capacity must atleast be one.";
		}
		if(graderOfferCapacity<1){
			error = error + "Grader offer capcity must be atleast one.";
		}
		if(courseID == null){
			error = error + "Course ID can not be empty.";
		}
		
		if(((taHours* jm.getHourlyRate() + graderHours* jm.getHourlyRate())>budget)){
			error = error + "Budget not sufficient to support all offers.";
		}
		if(courseCredit<1 || courseCredit > 5){
			error = error + "Course credit invalid.";
		}
		if (courseInstructor == null){
			error = error + "Course instructor selection invalid.";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		if(studentsEnrolled<40){
			error = error + "Course size is too small.";
		}
		Course newCourse = new Course(taHours, graderHours,courseCredit, courseID, budget, studentsEnrolled, jm, courseInstructor);
		TaOffer newTaJob = new TaOffer(taHours/taOfferCapacity,newCourse, taOfferCapacity);
		GraderOffer newGraderJob = new GraderOffer(graderHours/graderOfferCapacity, newCourse, taOfferCapacity);
		newCourse.addJob(newTaJob);
		newCourse.addJob(newGraderJob);
		jm.addCourse(newCourse);
		PersistenceXStream.saveToXMLwithXStream(dp);
		return newCourse;
	}
	public void applyForJob(int mcgillID, String experience, JobOffer job) throws InvalidInputException{
		String error = "";
		if(mcgillID<10000000){
			error = error + "Invalid mcgill ID.";
		}
		if(experience == null){
			error = error + "Experience can not be empty";
		}
		if(job == null){
			error = error + "Select a job to apply to.";
		}
		if(error.length()>0){
			throw new InvalidInputException(error);
		}
		Applicant newApplicant = new Applicant(mcgillID);
		Application newApplication = new Application(experience, newApplicant, job );
		job.addApplication(newApplication);
		PersistenceXStream.saveToXMLwithXStream(dp);
	}
}
