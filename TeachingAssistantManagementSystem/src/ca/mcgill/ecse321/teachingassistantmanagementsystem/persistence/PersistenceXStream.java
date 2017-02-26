package ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence;

import java.io.File;	
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Applicant;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Application;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.GraderOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.TaOffer;


public abstract class PersistenceXStream {
	
	private static XStream xstream = new XStream();
    private static String filename = "data.xml";
    
    public static Department initializeModelManager(String fileName){
    	Department dpt;
    	setFilename(fileName);
    	setAlias("course", Course.class);
    	setAlias("applicant", Applicant.class);
    	setAlias("application", Application.class);
    	setAlias("department", Department.class);
    	setAlias("graderoffer", GraderOffer.class);
    	setAlias("instructor", Instructor.class);
    	setAlias("jobManager", JobManager.class);
    	setAlias("jobOffer", JobOffer.class);
    	setAlias("TAOffer", TaOffer.class);
    	
    	File file = new File(fileName);
        if (file.exists()) {
            dpt=(Department) loadFromXMLwithXStream();
        } else {
        	try {
	                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            dpt = new Department(4);
            saveToXMLwithXStream(dpt);
        }
        return dpt;
        }
    
    public static boolean saveToXMLwithXStream(Object obj) {
        xstream.setMode(XStream.ID_REFERENCES);
        String xml = xstream.toXML(obj); // save our xml file

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(xml);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object loadFromXMLwithXStream() {
        xstream.setMode(XStream.ID_REFERENCES);
        try {
            FileReader fileReader = new FileReader(filename); // load our xml file
            return xstream.fromXML(fileReader);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void setAlias(String xmlTagName, Class<?> className) {
        xstream.alias(xmlTagName, className);
    }

    public static void setFilename(String fn) {
        filename = fn;
    }

}