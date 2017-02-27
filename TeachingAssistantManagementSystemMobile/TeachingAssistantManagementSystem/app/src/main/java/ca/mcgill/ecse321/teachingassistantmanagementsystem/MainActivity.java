package ca.mcgill.ecse321.teachingassistantmanagementsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.application.TeachingAssistantManagementSystem;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.TeachingAssistantManagementSystemController;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.*;

import static android.R.attr.id;
import static ca.mcgill.ecse321.teachingassistantmanagementsystem.R.string.course_description;
import static ca.mcgill.ecse321.teachingassistantmanagementsystem.R.string.experience;

public class MainActivity extends AppCompatActivity {

    public JobManager jm = null;
    public Course c;
    private String fileName;
    String error = null;
    private Department dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempViewForSpinner();

    }
    //setting up a temp spinner to test
    private void tempViewForSpinner() {
        Spinner temp = (Spinner) findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter <String> tempAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String [] stringArray = {"ECSE321", "ECSE421", "ECSE223"};
        for (String e: stringArray) {
            tempAdapter.add(e);
        }
        temp.setAdapter(tempAdapter);
    }

    //App crashes if I use this method. Still need to work on it.
    private void createJobPosting() throws InvalidInputException {
        TeachingAssistantManagementSystemController crp = new TeachingAssistantManagementSystemController(dp);
        crp.createJobPosting(1,2,2, "202", 23, 32, new Instructor());

        Spinner viewCourses = (Spinner) findViewById(R.id.viewCoursesSpinner);

        ArrayAdapter<CharSequence> viewCoursesAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        viewCoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Course e: crp.ViewCourses()) {
           viewCoursesAdapter.add((CharSequence) e.getCourseId());
        }
        viewCourses.setAdapter(viewCoursesAdapter);
    }

    public void refreshData() {
        EditText id = (EditText) findViewById(R.id.mcgillID);
        id.setText("");
        EditText exp = (EditText) findViewById(R.id.exp);
        exp.setText("");
    }

    //App runs properly when apply button is pressed

    public void applyForJob(View view) throws InvalidInputException {

        TextView exp = (TextView) findViewById(R.id.exp);
        TextView e = (TextView) findViewById(R.id.errorBox);
        TextView mcgillID = (TextView) findViewById(R.id.mcgillID);

        String error = "";
        int id = 0;

        if (mcgillID.getText().toString().length() == 0 && exp.getText().toString().length() < 30) {
            e.setText("Please enter a valid McGill ID and experience must be atleast 30 characters long");
        }

        else if (mcgillID.getText().toString().length() != 0 && exp.getText().toString().length() < 30) {
            id = Integer.parseInt(mcgillID.getText().toString());
            e.setText("Experience must be atleast 30 characters long");
            if (id < 10000000) {
                e.setText(error + "Invalid McGill ID");
            }
        }
        else { //The user is applying to the job that is selected in the from the drop down list (spinner)
            e.setText("");
            Spinner course = (Spinner) findViewById(R.id.viewCoursesSpinner);
            int pPosition = course.getSelectedItemPosition();
            Course c = jm.getCourse(pPosition);
            JobOffer j = new JobOffer(1, c);
            TeachingAssistantManagementSystemController aj = new TeachingAssistantManagementSystemController(dp);

            aj.applyForJob(Integer.parseInt(mcgillID.getText().toString()), exp.getText().toString(), j);
            refreshData();
        }
    }
}