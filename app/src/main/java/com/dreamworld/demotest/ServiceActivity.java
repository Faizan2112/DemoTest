package com.dreamworld.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
Intent serviceIntent ;
Boolean mCheckService ;
Button mStart ,mStop , mBound ,mUnBound , mGetRandom ;
// for binding the srvice we have to initialize these


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
  Log.i(getString(R.string.getstring),"thread id"+Thread.currentThread().getId());
       mStart = (Button)findViewById(R.id.sStartService);
        mStop = (Button)findViewById(R.id.sStopService);
        mBound= (Button)findViewById(R.id.sBoundService);
        mUnBound = (Button)findViewById(R.id.sunBoundService);
        mGetRandom = (Button)findViewById(R.id.sget_rbdom_no_Service);
        // started intent here and declare it in manifest file

        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mBound.setOnClickListener(this);
        mUnBound.setOnClickListener(this);
        mGetRandom.setOnClickListener(this);
        serviceIntent = new Intent(getApplicationContext(),MyService.class);

        // how we     mStart = (Button)findViewById(R.id.sStartService); sstart a service using startservice omn click

    }


    @Override
    public void onClick(View v) {
        int id =v.getId();
        switch (id)
        {
            case R.id.sStartService:
                mCheckService = true ;

                startService(serviceIntent);

                break;


            case R.id.sStopService:
             stopService(serviceIntent);
                break;

            case R.id.sBoundService:
                break;

            case R.id.sunBoundService:
                break;

            case R.id.sget_rbdom_no_Service:

                break;

        }

    }
}
