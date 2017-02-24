package ca.mcgill.ecse321.teachingassistantmanagementsystem.application;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.view.ViewCoursePage;

public class TeachingAssistantManagementSystem {
	private static String fileName = "output/tams.xml";
	
	public static void main(String[] args) {
	      // load model
			  final Department dpt = PersistenceXStream.initializeModelManager(fileName);

	      // start UI
	      java.awt.EventQueue.invokeLater(new Runnable() {
	          public void run() {
	              new ViewCoursePage(dpt).setVisible(true);
	          }
	      });
		}
}
