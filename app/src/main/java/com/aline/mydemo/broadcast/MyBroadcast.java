package com.aline.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author SuTongsheng
 * @create 2019/3/7
 * @Describe
 */
public class MyBroadcast extends BroadcastReceiver {
    private static final String TAG = "MyBroadcast";


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("MSG_DONG");
        Log.e(TAG, "onReceive: " + name);

    }
}
