package com.aline.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    private static final String TAG = "BindService";
    private final IBinder binder = new MyBinder();

    public BindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void excute() {
        Log.e(TAG, "excute: 通过Binder得到Service的引用来调用Service内部的方法" );
    }

    public class MyBinder extends Binder {
        public BindService getBindservice() {
            return BindService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // 当调用者退出(即使没有调用unbindService)或者主动停止服务时会调用
        System.out.println("调用者退出了");
        return super.onUnbind(intent);
    }
}
