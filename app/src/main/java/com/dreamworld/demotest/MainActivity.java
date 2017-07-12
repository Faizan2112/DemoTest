package com.dreamworld.demotest;

import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button mIntent , mService, mBroadcast , mContent , mLoaders ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIds();



    }

    private void findIds() {
        mIntent = (Button)findViewById(R.id.hIntent);
        mService = (Button)findViewById(R.id.hSevice);
        mBroadcast = (Button)findViewById(R.id.hBroadcast);
        mContent = (Button)findViewById(R.id.hContent);
        mLoaders = (Button)findViewById(R.id.hLoaders);

        mIntent.setOnClickListener(this);
        mService.setOnClickListener(this);
        mBroadcast.setOnClickListener(this);
        mContent.setOnClickListener(this);
        mLoaders.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
   int id = v.getId();
        switch(id)
        {
            case R.id.hIntent :
            {
                Intent i = new Intent(MainActivity.this,IntentActivtiy.class);
                startActivity(i);

            }
            break;

            case R.id.hSevice :
            {
                Intent S = new Intent();
                S.addCategory("android.intent.category.DEFAULT");
                S.setAction("com.dreamworld.demotest.ServicActivity");
                startActivity(S);

            }
            break;
            case R.id.hBroadcast :
            {
                Intent H = new Intent(MainActivity.this,BroadcastActivity.class);
                startActivity(H);

            }
            break;
            case R.id.hContent :
            {
                Intent c = new Intent(MainActivity.this,ContentActitvity.class);
                startActivity(c);

            }
            break;
            case R.id.hLoaders :
            {
                Intent l = new Intent(MainActivity.this,LoaderActitvity.class);
                startActivity(l);

            }
            break;
        }
    }
}
