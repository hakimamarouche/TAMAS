package ecse321.mcgill.ca.tamas;

/**
 * Created by AdeebIbne on 4/4/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.TeachingAssistantManagementSystemController;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.GraderOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.TaOffer;

public class Tab1ViewCourses extends Fragment {
    public Department dpt = null;
    private String fileName;
    private JobManager jm;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileName = getActivity().getApplicationContext().getFilesDir().getAbsolutePath() + "/output.xml";
        dpt = PersistenceXStream.initializeModelManager(fileName);
        View rootView = inflater.inflate(R.layout.tab1viewcourse, container, false);

        Instructor prof = new Instructor();
        TeachingAssistantManagementSystemController tac1 = new TeachingAssistantManagementSystemController(dpt);
        Course course1 = new Course(20,20, 3,"ECSE321", 200, 300, jm, prof);
        //Course course1 = tac.createJobPosting(20,20, 3,"ECSE321", 200, 300, prof);
        TaOffer newTaJob = new TaOffer(20 ,null,0, course1, course1.getBudget()/2);
        GraderOffer newGraderJob = new GraderOffer(20,null,0, course1, course1.getBudget()/2);
        newTaJob.setCapacity(((200/2)/jm.getHourlyRate())/20);
        newGraderJob.setCapacity(((200/2)/jm.getHourlyRate())/20);
        course1.addJob(newTaJob);
        course1.addJob(newGraderJob);
        jm.addCourse(course1);
        PersistenceXStream.saveToXMLwithXStream(dpt);

        TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
        try {
            tac.createJobPosting(1, 2, 2, "ECSE 321", 23, 32, new Instructor());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        Spinner spinner = (Spinner) rootView.findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<CharSequence> courseAdapter = new ArrayAdapter<CharSequence>(this.getActivity(), android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Course c: tac.ViewCourses()) {
            while (courseAdapter.isEmpty()) {
                courseAdapter.add(c.getCourseId());
            }
        }
        spinner.setAdapter(courseAdapter);


        return rootView;

    }
}
