package ca.mcgill.ecse321.teachingassistantmanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.*;

public class MainActivity extends AppCompatActivity {

    private JobManager jm;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.courseSpinner);
        ArrayAdapter courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        courseAdapter.add("a");

        spinner.setAdapter(courseAdapter);
    }



}
