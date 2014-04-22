package com.twitter.university.android.yamba;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;


public class TweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_tweet);

        if (state == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.container, new TweetFragment())
                .commit();
        }
    }
}
