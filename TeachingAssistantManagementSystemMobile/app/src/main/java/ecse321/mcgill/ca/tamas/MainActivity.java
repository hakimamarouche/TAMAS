package ecse321.mcgill.ca.tamas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Applicant;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Application;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.GraderOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Instructor;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobManager;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.JobOffer;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.TaOffer;

public class MainActivity extends AppCompatActivity {
    private Department dp = null;
    private String fileName;
    String error = null;
    private JobManager jm;

    private String [] arraySpinner;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = getFilesDir().getAbsolutePath() + "/output.xml";
        dp = PersistenceXStream.initializeModelManager(fileName);
        jm = dp.getTaManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        Spinner2();
    }

    public List<Course> ViewCourses(){
        return jm.getCourses();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public Course viewCourse() {

        Instructor prof = new Instructor();
        Course newCourse = new Course(20, 20, 3, "ECSE321", 200, 200, jm, prof);
        TaOffer newTaJob = new TaOffer(20, null, 0, newCourse, newCourse.getBudget() / 20);
        GraderOffer newGraderJob = new GraderOffer(20, null, 0, newCourse, newCourse.getBudget() / 20);
        newTaJob.setCapacity(((200 / 2) / jm.getHourlyRate()) / 20);
        newGraderJob.setCapacity(((200 / 2) / jm.getHourlyRate()) / 20);
        newCourse.addJob(newTaJob);
        newCourse.addJob(newGraderJob);
        jm.addCourse(newCourse);
        PersistenceXStream.saveToXMLwithXStream(dp);

        return newCourse;
    }
    public void Spinner2() {
        viewCourse();
        this.arraySpinner = new String[]{viewCourse().getCourseId()};

        Spinner s = (Spinner) findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
    }
    public void refreshData() {
        EditText id = (EditText) findViewById(R.id.mcgillID);
        id.setText("");
        EditText exp = (EditText) findViewById(R.id.exp);
        exp.setText("");
    }

    //App runs properly when apply button is pressed

    public void applyForJob(View view) throws InvalidInputException {
        TextView mcgillID = (TextView) findViewById(R.id.mcgillID);
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView errorBox = (TextView) findViewById(R.id.errorBox);
        String error = "";
        int id = 0;
        if (mcgillID.getText().toString().length() == 0 && exp.getText().toString().length() < 30) {
            errorBox.setText("Please enter a valid McGill ID and experience must be atleast 30 characters long");
        }

        else if (mcgillID.getText().toString().length() != 0 && exp.getText().toString().length() < 30) {
            id = Integer.parseInt(mcgillID.getText().toString());
            errorBox.setText("Experience must be atleast 30 characters long");
            if (id < 10000000) {
                errorBox.setText(error + "Invalid McGill ID");
            }
        }

        else { //The user is applying to the job that is selected in the from the drop down list (spinner)

        Applicant newApplicant = new Applicant(Integer.parseInt(mcgillID.toString()));
        JobOffer job  = new JobOffer(2,"hello", 260625272, (Course) ViewCourses());
        Application newApplication = new Application(exp.toString(), newApplicant, job );
        newApplication.setStatus(Application.Status.UnderReview);
        job.addApplication(newApplication);
        PersistenceXStream.saveToXMLwithXStream(dp);
            refreshData();
    }

}


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1ViewCourses tab1 = new Tab1ViewCourses();
                    return tab1;
                case 1:
                   Tab2AcceptJob tab2 = new Tab2AcceptJob();
                    return tab2;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "VIEW COURSES";
                case 1:
                    return "OFFERS";

            }
            return null;
        }
    }
}
