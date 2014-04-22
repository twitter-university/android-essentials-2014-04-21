package com.twitter.university.android.yamba;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
* Created by bmeike on 4/22/14.
*/
public class TweetFragment extends Fragment {

    private int okColor;
    private int warnColor;
    private int errColor;

    private int tweetLenMax;
    private int warnMax;
    private int errMax;

    private EditText tweetView;
    private TextView countView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources rez = getResources();
        okColor = rez.getColor(R.color.ok);
        tweetLenMax = rez.getInteger(R.integer.tweet_max);
        warnColor = rez.getColor(R.color.warn);
        warnMax = rez.getInteger(R.integer.warn_max);
        errColor = rez.getColor(R.color.error);
        errMax = rez.getInteger(R.integer.tweet_min);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle state) {
        View v = inflater.inflate(R.layout.fragment_tweet, root, false);

        countView = (TextView) v.findViewById(R.id.tweet_count);

        tweetView = (EditText) v.findViewById(R.id.tweet_tweet);
        tweetView.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable s) { updateCount(); }

                    @Override
                    public void beforeTextChanged(CharSequence s, int b, int n, int a) { }

                    @Override
                    public void onTextChanged(CharSequence s, int b, int p, int n) { }
                });

        return v;
    }

    void updateCount() {
        int n = tweetView.getText().length();

        n = tweetLenMax - n;

        int color;
        if (n > warnMax) { color = okColor; }
        else if (n > errMax) { color = warnColor; }
        else  { color = errColor; }

        countView.setText(String.valueOf(n));
        countView.setTextColor(color);
    }
}
