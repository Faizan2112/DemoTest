package com.dreamworld.demotest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by faizan on 13/07/2017.
 */

// to recieve this we have to register it in manifest file or for runtime using <code></code>
public class BroadcastTest extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"service bound ",Toast.LENGTH_LONG).show();
    }
}
