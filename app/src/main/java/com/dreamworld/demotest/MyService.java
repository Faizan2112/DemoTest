package com.dreamworld.demotest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by faizan on 12/07/2017.
 */

public class MyService extends Service {
    private static final int MIN = 0;

    private static final int MAX = 100;
    Boolean mIsRandomNoGeneraterIsOn;
    private int mRandomNumber;
 //To register a onBinder to be implemented make a class which extends  Binder

    class MyServiceBinder extends Binder
    {
        // here we have to retrn our class

        public MyService getService()
        {
            return MyService.this;

        }

    }

    // now in initiate the i binder to return from Ibinder

    private IBinder mBinder = new MyServiceBinder();

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomNumber();
        Log.i(getString(R.string.getstring), "service destroyed");

    }

    // calls at very first
    // how do we call it using intent
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.getstring), "inside on start commond " + Thread.currentThread().getId());
        //stopSelf();
        mIsRandomNoGeneraterIsOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomNumber();
            }
        }).start();


        return START_STICKY;
    }

    private void startRandomNumber() {

        while (mIsRandomNoGeneraterIsOn) {
            try {
                Thread.sleep(1000);
                if (mIsRandomNoGeneraterIsOn) {
                    mRandomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(getString(R.string.getstring), "thred id "+Thread.currentThread().getId()+"random no."+mRandomNumber );

                }
            } catch (InterruptedException e) {
                Log.i(getString(R.string.getstring), "Service intruppted");
                //e.printStackTrace();
            }

        }
    }

    private void stopRandomNumber()
    {
        mIsRandomNoGeneraterIsOn = false ;

    }

    public int getRandomNumber()
    {
        return mRandomNumber ;

    }
}
