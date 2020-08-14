package com.aline.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
/**
 * @author SuTongsheng
 * @create 2019/3/5
 * @Describe
 */
public class MessengerService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
