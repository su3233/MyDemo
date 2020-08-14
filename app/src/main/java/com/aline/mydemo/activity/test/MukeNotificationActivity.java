package com.aline.mydemo.activity.test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.aline.mydemo.R;


public class MukeNotificationActivity extends AppCompatActivity {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muke_notification);

//        sendNormalNotification();

//        sendProgressNotification();

        sendCustomNotification();
    }

    /**
     * 自定义通知
     */
    private void sendCustomNotification() {
        NotificationCompat.Builder builder = getNotificationBuilder();

    }

    private void sendNormalNotification() {
        NotificationCompat.Builder builder = getNotificationBuilder();
        getManager().notify(1, builder.build());
    }


    private NotificationCompat.Builder getNotificationBuilder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//通知被关闭时，手动设置里面打开
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
            getManager().createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setChannelId("channel_id")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setNumber(3)
                .setAutoCancel(true);
        return builder;
    }

    /**
     * 创建带进度条的notification
     */
    private void sendProgressNotification() {
        final NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
        getManager().notify(2, builder.build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                        builder.setProgress(100, i, false);
                        getManager().notify(2, builder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

}
