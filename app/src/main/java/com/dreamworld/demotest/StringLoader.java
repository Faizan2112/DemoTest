package com.dreamworld.demotest;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faizan on 20/07/2017.
 */

public class StringLoader extends AsyncTaskLoader<List<String>> {
// for caching data
    private List<String> cachedData ;
  public static final String ACTION  = "com.loaders.FORCE";

    public StringLoader(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
// for cecking some delay
        Log.d("TE","loading new data");
        try{
            Thread.sleep(4000);

        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.my_books));
        return data ;
    }

    @Override
    protected void onStartLoading() {
// regiserting local broadcast to listen for changes
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter = new IntentFilter(ACTION);
        manager.registerReceiver(broadcastReceiver,filter);



      // we have to forcfull load data in background

    if(cachedData == null) {
        forceLoad();
    }

    else
        {
            super.deliverResult(cachedData);

        }
    }
//// for caching data
    @Override
    public void deliverResult(List<String> data) {
       cachedData = data ;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        // stop wacthing over changing dataset
        super.onReset();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    public void loadNewStrings()
    {
        forceLoad();

    }
}
