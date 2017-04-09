package ecse321.mcgill.ca.tamas;

/**
 * Created by AdeebIbne on 4/4/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1ViewCourses extends Fragment {
    private String [] arraySpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1viewcourse, container, false);
        // Create an ArrayAdapter using the string array and a default spinner layout

        
        return rootView;

    }
}
