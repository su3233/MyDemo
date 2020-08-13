package com.aline.mydemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.aline.mydemo.R;

/**
 * @author SuTongsheng
 * @create 2018/11/23
 * @Describe
 */
public class WeatherService extends Service {
    private static final int NOTIFY_ID = 123;
    private Context mContext;

    public WeatherService(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotifycation();
    }

    /**
     * 通知栏显示天气
     */
    private void showNotifycation() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("晴天")
                .setContentText("今天有雨");
        //创建通知被电击时的出发时间intent
//        Intent resultIntent = new Intent(this, (Activity) mContext);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
