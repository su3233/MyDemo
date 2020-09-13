package com.aline.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverJing extends BroadcastReceiver {
    private static final String TAG = "MyReceiverJing";

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("MSG_JING");
        Log.e(TAG, "onReceive: " + name);
    }
}
