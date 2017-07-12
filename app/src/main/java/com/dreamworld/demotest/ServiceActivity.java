package com.dreamworld.demotest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
Intent serviceIntent ;
Boolean mCheckService ;
Button mStart ,mStop , mBound ,mUnBound , mGetRandom ;
TextView mRandom ;
    // for binding the srvice we have to initialize these
private MyService mMyService;
public ServiceConnection mServiceConnection;
public boolean mIsBound ;

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
        mRandom = (TextView)findViewById(R.id.sRandom_no);
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
                onBind();
                break;

            case R.id.sunBoundService:
                onUnBind();
                break;

            case R.id.sget_rbdom_no_Service:
                getRandomnumber();
                break;

        }

    }

    private void onBind() {
        // here we have to get i binder
       // mCheckService = true;

        if(mServiceConnection == null)
        {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
               MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder)service;
                mMyService = myServiceBinder.getService();
                mCheckService = true ;


            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
             mCheckService = false ;
            }


        };

        }
 bindService(serviceIntent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void onUnBind(){
      if(mCheckService)
      {
          unbindService(mServiceConnection);
          mCheckService = false ;
      }
    }

    public void getRandomnumber()
    {
        if(mCheckService)
        {
            mRandom.setText("Random number"+mMyService.getRandomNumber());

        }
        else
            {

                mRandom.setText("service not bound");
            }


    }

}
