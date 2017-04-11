package ecse321.mcgill.ca.tamas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
