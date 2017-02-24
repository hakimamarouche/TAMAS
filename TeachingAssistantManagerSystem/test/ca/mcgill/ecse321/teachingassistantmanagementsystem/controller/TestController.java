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

public class TestController {

	private Department dpt;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dpt = new Department(4);
	}

	@After
	public void tearDown() throws Exception {
		dpt.delete();
	}

	@Test
	public void testCreateCourses() {
		fail("Not yet implemented");
	}

}
