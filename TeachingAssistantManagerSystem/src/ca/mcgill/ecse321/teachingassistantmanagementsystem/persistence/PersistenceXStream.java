package ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence;

import java.io.File;	
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;


public abstract class PersistenceXStream {
	
	private static XStream xstream = new XStream();
    private static String filename = "data.xml";
    
    public static JobManager initializeModelManager(String fileName){
    	JobManager jm;
    	setFilename(fileName);
    	setAlias("course", Course.class);
    	File file = new File(fileName);
        if (file.exists()) {
            jm = (JobManager) loadFromXMLwithXStream();
        } else {
        	try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            jm = new JobManager(4);
            saveToXMLwithXStream(jm);
        }
        return jm;
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

