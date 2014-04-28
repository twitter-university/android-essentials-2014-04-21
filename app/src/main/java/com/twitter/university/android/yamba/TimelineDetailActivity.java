package com.twitter.university.android.yamba;

import android.os.Bundle;


public class TimelineDetailActivity extends YambaActivity {
    private static final String TAG = "ACT_DETAILS";

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_timeline_detail);

        if (state == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.details_container, TimelineDetailFragment.newInstance(getIntent().getExtras()))
                .commit();
        }
    }
}
