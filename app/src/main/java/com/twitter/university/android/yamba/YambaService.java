package com.twitter.university.android.yamba;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by bmeike on 4/23/14.
 */
public class YambaService extends IntentService {
    public static final String TAG = "SVC";

    public static final String PARAM_TWEET = "YambaService.TWEET";

    public static void post(Context ctxt, String tweet) {
        Intent i = new Intent(ctxt, YambaService.class);
        i.putExtra(PARAM_TWEET, tweet);
        ctxt.startService(i);
    }


    private volatile Handler hdlr;

    public YambaService() { super(TAG); }

    @Override
    public void onCreate() {
        super.onCreate();
        hdlr = new Handler();
    }

    public void onHandleIntent(Intent i) {
        String tweet = i.getStringExtra(PARAM_TWEET);

        int status = R.string.tweet_failed;
        try {
            Thread.sleep(2 * 1000); // replace with actual network call
            status = R.string.tweet_succeeded;
        }
        catch (Exception e) { }

        final int safeStatus = status;
        hdlr.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(YambaService.this, safeStatus, Toast.LENGTH_LONG).show();
            }
        });
    }


}




//static class Poster extends AsyncTask<String, Void, Integer> {
//    private Activity ctxt;
//
//    public Poster(Activity ctxt) { this.ctxt = ctxt; }
//
//    @Override
//    protected Integer doInBackground(String... tweet) {
//        int status = R.string.tweet_failed;
//
//        try {
//            Thread.sleep(2 * 1000); // replace with actual network call
//            status = R.string.tweet_succeeded;
//        }
//        catch (Exception e) { }
//
//        return Integer.valueOf(status);
//    }
//
//    @Override
//    protected void onPostExecute(Integer status) {
//        finish(status.intValue());
//    }
//
//    @Override
//    protected void onCancelled() {
//        finish(R.string.tweet_failed);
//    }
//
//    private void finish(int status) {
//        poster = null;
//        Toast.makeText(ctxt, status, Toast.LENGTH_LONG).show();
//    }
//}

