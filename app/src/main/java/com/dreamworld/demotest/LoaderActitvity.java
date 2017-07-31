package com.dreamworld.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;


public class LoaderActitvity extends AppCompatActivity {
    private LoaderAdapter loaderAdapter;
    private ListView listView;
    private StringLoader stringLoader ;
  private RepoLoader repoLoader ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_actitvity);
        loaderAdapter = new LoaderAdapter(this);
        listView = (ListView) findViewById(R.id.l_load_items);
        listView.setAdapter(loaderAdapter);

        findViewById(R.id.button_force).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to force load he data
   /*             Intent intent = new Intent(StringLoader.ACTION);
                LocalBroadcastManager.getInstance(LoaderActitvity.this).sendBroadcast(intent);
   */
            // for directly calling
                stringLoader.loadNewStrings();
            }
        });

        //=(StringLoader)getSupportLoaderManager().initLoader()
// loaderAdapter.swapData(Arrays.asList(getResources().getStringArray(R.array.my_books)));
       stringLoader = (StringLoader) getSupportLoaderManager().initLoader(1, null, loaderCallbacks);
    //     repoLoader = (RepoLoader)getSupportLoaderManager().initLoader(1, null, loaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {
        @Override
        public Loader<List<String>> onCreateLoader(int id, Bundle args) {
           return new StringLoader(getApplicationContext());
    // return  new RepoLoader(getApplicationContext());
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
