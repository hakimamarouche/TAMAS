package ecse321.mcgill.ca.tamas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.TeachingAssistantManagementSystemController;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Course;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.Department;

public class MainActivity extends AppCompatActivity {
    private Department dpt;

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



    /*public void refreshData() {

        /*EditText id = (EditText) findViewById(R.id.mcgillID);
        id.setText("");
        EditText exp = (EditText) findViewById(R.id.exp);
        exp.setText("");

        Spinner spinner = (Spinner) findViewById(R.id.viewCoursesSpinner);
        ArrayAdapter<CharSequence> courseAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Course c: dpt.getTaManager().getCourses()) {
            courseAdapter.add(c.getCourseId());
        }
        spinner.setAdapter(courseAdapter);
    }*/

    public void applyForJob(View view) throws InvalidInputException {
        Spinner spinner2 = (Spinner) findViewById(R.id.viewCoursesSpinner);
        TextView mcgillID = (TextView) findViewById(R.id.mcgillID);
        TextView exp = (TextView) findViewById(R.id.exp);
        TextView errorBox = (TextView) findViewById(R.id.errorBox);
        String error = "";
        int id = 0;
        Spinner s = (Spinner) findViewById(R.id.taGraderSpinner);
        TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
        if (mcgillID.getText().toString().length() == 0 && exp.getText().toString().length() < 30) {
            errorBox.setText("Please enter a valid McGill ID and experience must be atleast 30 characters long");
        } else if (mcgillID.getText().toString().length() != 0 && exp.getText().toString().length() < 30) {
            id = Integer.parseInt(mcgillID.getText().toString());
            errorBox.setText("Experience must be atleast 30 characters long");
            if (id < 10000000) {
                errorBox.setText(error + "Invalid McGill ID");
            }
        } else { //The user is applying to the job that is selected in the from the drop down list (spinner)
            for (Course nextCourse : dpt.getTaManager().getCourses()) {
                if (nextCourse.getCourseId().equals(spinner2.getSelectedItem().toString())) {
                    if (s.getSelectedItemPosition() == 1) {
                        tac.applyForJob(Integer.parseInt(mcgillID.getText().toString()), exp.getText().toString(), nextCourse.getJob(1));
                    }
                    if (s.getSelectedItemPosition() == 2) {
                        tac.applyForJob(Integer.parseInt(mcgillID.getText().toString()), exp.getText().toString(), nextCourse.getJob(0));
                    }

                }
            }
        }
    }
}



