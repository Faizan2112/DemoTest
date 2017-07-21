package com.dreamworld.demotest;

import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class LoaderActitvity extends AppCompatActivity{
 private LoaderAdapter loaderAdapter ;
    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_actitvity);
       loaderAdapter = new LoaderAdapter(this);
        listView = (ListView)findViewById(R.id.l_load_items);
        listView.setAdapter(loaderAdapter);
// loaderAdapter.swapData(Arrays.asList(getResources().getStringArray(R.array.my_books)));
getSupportLoaderManager().initLoader(1,null,loaderCallbacks);
    }

private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {
    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new StringLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {

        loaderAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
      loaderAdapter.swapData(Collections.<String>emptyList());
    }
};


}
