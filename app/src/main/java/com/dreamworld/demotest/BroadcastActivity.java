package com.dreamworld.demotest;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BroadcastActivity extends AppCompatActivity {
// here we have to create instance of boadcast reciever
    BroadcastReceiver mBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        //** initialize with main class
        mBroadcastReceiver = new BroadcastTest();
    }

    // overide life cycle method


    @Override
    protected void onStart() {
        super.onStart();
    // here we have to register broadcast componetn
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//        sendBroadcast(mBroadcastReceiver,intentFilter);
registerReceiver(mBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mBroadcastReceiver);
    }
}
