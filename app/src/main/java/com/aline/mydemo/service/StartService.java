package com.aline.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.aline.mydemo.activity.test.StartServiceActivity;

/**
 * 测试通过startService启动service的执行情况
 *
 * @author SuTongsheng
 * @create 2019/3/6
 * @Describe
 */
public class StartService extends Service {
    private static final String TAG = "StartService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        new MyThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    Message message = new Message();
                    message.what = i;
                    StartServiceActivity.serviceHandler.sendMessage(message);
                    Log.i(TAG, "run: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            super.run();
        }
    }

}
