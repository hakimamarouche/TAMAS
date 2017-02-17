package ca.mcgill.ecse321.teachingassistantmanagementsystem.controller;

import java.util.List;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;

public class TeachingAssistantManagementSystemController {
	private JobManager jm;
	public List<Course> ViewCourses() throws InvalidInputException{
		return jm.getCourses();
	}
}
