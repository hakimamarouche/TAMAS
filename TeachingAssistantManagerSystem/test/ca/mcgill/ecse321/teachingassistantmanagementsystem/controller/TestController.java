package ca.mcgill.ecse321.teachingassistantmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;

public class TestController {

	private Department dpt;
	private JobManager jm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// should it be the opposite? --> using constructor Department(JobManager)
		dpt = new Department(4);
		jm = new JobManager(4, dpt);
		//however, it seems redundant... something is wrong with the class diagram I think
	}

	@After
	public void tearDown() throws Exception {
		jm.delete();
	}

	@Test
	public void testCreateCourses() throws InvalidInputException {
		assertEquals(0, jm.getCourses().size());

		int taHours = 40;
		int graderHours = 40;
		int courseCredit = 4;
		String courseID = "ECSE 321";
		int budget = 10000000;
		int studentsEnrolled = 50;
		Instructor courseInstructor = new Instructor();
		int taOfferCapacity = 50;
		int graderOfferCapacity = 50;

		TeachingAssistantManagementSystemController tamsc = new TeachingAssistantManagementSystemController(jm);
		tamsc.createJobPosting(taHours, graderHours, courseCredit, courseID, budget, studentsEnrolled, courseInstructor, taOfferCapacity, graderOfferCapacity);

		  // check model in memory
		  checkResultJobPosting(courseID);

		  jm = (JobManager) PersistenceXStream.loadFromXMLwithXStream();
		  
		// check file contents
		  checkResultJobPosting(courseID);
	}

	private void checkResultJobPosting(String courseID) {
		assertEquals(1, jm.getCourses().size());
		  assertEquals(courseID, jm.getCourse(0).getCourseId());
	}

}
