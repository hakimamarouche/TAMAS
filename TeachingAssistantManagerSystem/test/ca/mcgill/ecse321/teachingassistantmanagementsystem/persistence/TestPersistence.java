package ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;

public class TestPersistence {

	private Department dpt;
	@Before
	public void setUp() throws Exception {
		dpt = new Department(4);
		Instructor myInstructor = new Instructor();
		
		Course comp202 = new Course(80, 60, 3, "COMP202", 200, 5000, dpt.getTaManager(), myInstructor);
		
		dpt.getTaManager().addCourse(comp202);
	}

	@After
	public void tearDown() throws Exception {
		dpt.delete();
	}

	@Test
	public void test() {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	    // save model that is loaded during test setup
	    if (!PersistenceXStream.saveToXMLwithXStream(dpt))
	        fail("Could not save file.");



	    // load model
	    dpt = (Department) PersistenceXStream.loadFromXMLwithXStream();
	    if (dpt == null)
	        fail("Could not load file.");
	}

}
