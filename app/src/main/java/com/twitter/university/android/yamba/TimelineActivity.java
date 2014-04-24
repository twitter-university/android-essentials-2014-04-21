package com.twitter.university.android.yamba;

import android.os.Bundle;


public class TimelineActivity extends YambaActivity {


    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_timeline);

        if (state == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.container, new TimelineFragment())
                .commit();
        }
    }
}
