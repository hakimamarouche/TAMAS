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
    private String [] arraySpinner;
    private String fileName;
    public JobManager jm = null;
    public Course c;
    String error = null;
    private Department dpt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize file name and XStream
        fileName = getFilesDir().getAbsolutePath() + "/tams.xml";
        dpt = PersistenceXStream.initializeModelManager(fileName);

        try {
            createJobPosting();
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        refreshCoursesData();
        /* Commented this out because trying to implement (making tests)
        refreshData();
        viewCoursesSpinner();
        */

    }
    //setting up a temp spinner to test
    private void viewCoursesSpinner() {
       this.arraySpinner = new String [] {"ECSE321"};
        //added this comment to see if commit is working
        Spinner s = (Spinner) findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

    }

    private void createJobPosting() throws InvalidInputException {
        TeachingAssistantManagementSystemController crp = new TeachingAssistantManagementSystemController(dpt);
        crp.createJobPosting(1, 2, 2, "COMP 202", 23, 32, new Instructor());
        crp.createJobPosting(1, 2, 2, "ECSE 321", 23, 32, new Instructor());
        crp.createJobPosting(1, 2, 2, "MATH 264", 23, 32, new Instructor());
    }

    //App crashes if I use this method. Still need to work on it.
    /*private void createJobPosting() throws InvalidInputException {
        TeachingAssistantManagementSystemController crp = new TeachingAssistantManagementSystemController(dp);
        crp.createJobPosting(1,2,2, "202", 23, 32, new Instructor());

        Spinner viewCourses = (Spinner) findViewById(R.id.viewCoursesSpinner);

        ArrayAdapter<CharSequence> viewCoursesAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        viewCoursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Course e: crp.ViewCourses()) {
           viewCoursesAdapter.add((CharSequence) e.getCourseId());
        }
        viewCourses.setAdapter(viewCoursesAdapter);
    }*/

    public void refreshData() {
        EditText id = (EditText) findViewById(R.id.mcgillID);
        id.setText("");
        EditText exp = (EditText) findViewById(R.id.exp);
        exp.setText("");
    }

    private void refreshCoursesData() {
        /* ccomment this because it was from the event registration but might be useful
        TextView tv = (TextView) findViewById(R.id.event_name);
        tv.setText("");
        */



        // Initialize the data in the course spinner
        Spinner spinner = (Spinner) findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<CharSequence> courseAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Course c: dpt.getTaManager().getCourses()) {
            courseAdapter.add(c.getCourseId());
        }
        spinner.setAdapter(courseAdapter);
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
            TeachingAssistantManagementSystemController aj = new TeachingAssistantManagementSystemController(dpt);

            aj.applyForJob(Integer.parseInt(mcgillID.getText().toString()), exp.getText().toString(), j);
            refreshData();
        }
    }
}