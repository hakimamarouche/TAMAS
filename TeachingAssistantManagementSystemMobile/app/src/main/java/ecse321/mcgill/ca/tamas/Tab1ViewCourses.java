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
import android.widget.TextView;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.TeachingAssistantManagementSystemController;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;

public class Tab1ViewCourses extends Fragment {
    private Department dpt = null;
    private String fileName;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileName = getActivity().getApplicationContext().getFilesDir().getAbsolutePath() + "/output.xml";
        dpt = PersistenceXStream.initializeModelManager(fileName);
        View rootView = inflater.inflate(R.layout.tab1viewcourse, container, false);

        TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
        try {

            tac.createJobPosting(20, 20, 3, "ECSE321", 500, 100, new Instructor());
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        Spinner spinner = (Spinner) rootView.findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<CharSequence> courseAdapter = new ArrayAdapter<CharSequence>(this.getActivity(), android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TextView courseDes = (TextView) rootView.findViewById(R.id.textView10);
        TextView credView = (TextView) rootView.findViewById(R.id.textView11);
        TextView taView = (TextView) rootView.findViewById(R.id.textView12);
        TextView graderView = (TextView) rootView.findViewById(R.id.textView13);

        for (Course c : tac.ViewCourses()) {
            while (courseAdapter.isEmpty()) {
                courseAdapter.add(c.getCourseId());

            }
            if (spinner.getSelectedItem() != null && spinner.getSelectedItem().toString().equals(c.getCourseId())){
                courseDes.setText(c.getCourseId());
                credView.setText("" + c.getCoursCredit());
                taView.setText("" + c.getTaWorkHours());
                graderView.setText("" + c.getGraderWorkHours());
        }
        spinner.setAdapter(courseAdapter);

        }
        return rootView;
    }
}
               /* creditText.setText(String.valueOf(nextCourse.getCoursCredit()));
                taJobText.setText(String.valueOf(nextCourse.getTaWorkHours())+ " hours");
                graderJobText.setText(String.valueOf(nextCourse.getGraderWorkHours())+" hours");

        }





    }
}*/
