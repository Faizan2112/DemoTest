package com.dreamworld.demotest;

import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by faizan on 20/07/2017.
 */

public class StringLoader extends AsyncTaskLoader<List<String>> {


    public StringLoader(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.my_books));
        return data ;
    }

    @Override
    protected void onStartLoading() {
      // we have to forcfull load data in background
        forceLoad();

    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);
    }
}
