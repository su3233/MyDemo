
package com.aline.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private static final String TAG = "TimerService";
    private Timer timer;

    public TimerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String time = getSystemTime();
                Log.e(TAG, "run: " + time);
            }
        };
        timer.schedule(timerTask, 0, 3000);
        return super.onStartCommand(intent, flags, startId);

    }

    private String getSystemTime() {
        String time = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            time = simpleDateFormat.format(date);
        }
        return time;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind: ");
        super.onRebind(intent);
    }
}
