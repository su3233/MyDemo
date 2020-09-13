package com.aline.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author SuTongsheng
 * @create 2018/11/2
 * @Describe
 */
public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doMyJob(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 开启子线程完成耗时操作
     *
     * @param intent
     */
    private void doMyJob(Intent intent) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.e("TEST_LOG", "123..." + i);
                }
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
