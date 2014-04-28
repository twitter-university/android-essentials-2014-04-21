package com.twitter.university.android.yamba;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;


public class TimelineActivity extends YambaActivity {


    private boolean showDetails;

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int code) {
        if (showDetails) { setDetails(intent); }
        else { super.startActivityFromFragment(fragment, intent, code); }
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_timeline);

        showDetails = (null != findViewById(R.id.details_container));

        if (state == null) {
            FragmentTransaction xact = getFragmentManager().beginTransaction();
            xact.add(R.id.timeline_container, new TimelineFragment());
            if (showDetails) {
                xact.add(R.id.details_container, TimelineDetailFragment.newInstance(null));
            }
            xact.commit();
        }
    }

    private void setDetails(Intent intent) {
        FragmentTransaction xact = getFragmentManager().beginTransaction();
        xact.replace(
                R.id.details_container,
                TimelineDetailFragment.newInstance(intent.getExtras()));
        xact.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        xact.addToBackStack(null);
        xact.commit();
    }
}
