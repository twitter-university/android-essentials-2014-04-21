package com.twitter.university.android.yamba;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.twitter.university.android.yamba.service.YambaContract;

import org.w3c.dom.Text;

/**
 * Created by bmeike on 4/24/14.
 */
public class TimelineFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int TIMELINE_LOADER = 42;

    private static final String[] FROM = new String[] {
            YambaContract.Timeline.Columns.HANDLE,
            YambaContract.Timeline.Columns.TIMESTAMP,
            YambaContract.Timeline.Columns.TWEET
    };

    private static final int[] TO = new int[] {
            R.id.timeline_handle,
            R.id.timeline_timestamp,
            R.id.timeline_tweet
    };

     class TimelineBinder implements SimpleCursorAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Cursor cursor, int colId) {
            if (view.getId() != R.id.timeline_timestamp) { return false; }

            CharSequence tStr = "long ago";
            long t = cursor.getLong(colId);
            if (0 < t) { tStr = DateUtils.getRelativeTimeSpanString(t); }
            ((TextView) view).setText(tStr);

            return true;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View v = super.onCreateView(inflater, container, state);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.timeline_row,
                null,
                FROM,
                TO,
                0);
        adapter.setViewBinder(new TimelineBinder());
        setListAdapter(adapter);

        getLoaderManager().initLoader(TIMELINE_LOADER, null, this);

        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                getActivity(),
                YambaContract.Timeline.URI,
                null,
                null,
                null,
                YambaContract.Timeline.Columns.TIMESTAMP + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        Log.d("LOADER", "Got cursor: " + c);
        ((SimpleCursorAdapter) getListAdapter()).swapCursor(c);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter) getListAdapter()).swapCursor(null);
    }
}



