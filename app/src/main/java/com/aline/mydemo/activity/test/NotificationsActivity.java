package com.aline.mydemo.activity.test;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.aline.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Notification.VISIBILITY_SECRET;

public class NotificationsActivity extends AppCompatActivity {

    private static final String TAG = "NotificationsActivity";
    @BindView(R.id.bt_normal)
    Button btNormal;
    @BindView(R.id.bt_folder)
    Button btFolder;
    @BindView(R.id.bt_hang)
    Button btHang;
    @BindView(R.id.bt_chat)
    Button btChat;
    @BindView(R.id.bt_subscribe)
    Button btSubscribe;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        checkChannel();
    }

    private void checkChannel() {
        //8.0限制通知权限后，创建通道的代码只执行一次
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }


    @OnClick({R.id.bt_normal, R.id.bt_folder, R.id.bt_hang, R.id.bt_chat, R.id.bt_subscribe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_normal:
                showNormal();
                break;
            case R.id.bt_folder:
                showFolderNotification();
                break;
            case R.id.bt_hang:
                break;
            case R.id.bt_chat:
                sendChatMsg();
                break;
            case R.id.bt_subscribe:
                sendSubscribeMsg();
                break;
        }
    }

    private void sendSubscribeMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

    private void sendChatMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//打开重要通知设置
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            } else {
                Notification notification = new NotificationCompat.Builder(this, "chat")
                        .setContentTitle("收到一条聊天消息")
                        .setContentText("今天中午吃什么？")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setNumber(2)////允许有角标，提示未读
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .build();
                manager.notify(1, notification);
            }
        }
    }

    private void showFolderNotification() {
//        Notification.Builder builder = new Notification.Builder(this);
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        builder.setContentIntent(pendingIntent);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//        builder.setAutoCancel(true);
//        builder.setContentTitle("普通通知");
//        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.view_folder_notification);
//        Notification notification=builder.build();
//        notification.bigContentView=remoteViews;
//        notificationManager.notify(1,builder.build());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationsActivity.this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sina.com"));
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        // 未下拉的样式 R.layout.collapsed
        RemoteViews collapsed = new RemoteViews(getPackageName(), R.layout.view_folder_notification_off);
        collapsed.setTextViewText(R.id.tv_folder_noti_off, "关闭状态");
        //下拉后的样式R.layout.show
        RemoteViews show = new RemoteViews(getPackageName(), R.layout.view_folder_notification);
        Notification notify = builder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setContentText("新浪微博")
                .setCustomContentView(collapsed)//下拉前
                .setCustomBigContentView(show)//下拉后
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, notify);
    }


    private void showNormal() {
//        Notification.Builder builder = new Notification.Builder(this);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationsActivity.this);
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        builder.setContentIntent(pendingIntent);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//        builder.setAutoCancel(true);
//        builder.setContentTitle("普通通知");
//        mNotificationManager.notify(1, builder.build());


    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
//        channel.setShowBadge(true);//允许有角标，提示未读
        channel.canBypassDnd();//可否染过勿扰模式
        channel.enableLights(true);//闪光
        channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);//震动
        channel.getGroup();
        channel.setVibrationPattern(new long[]{
                100, 100, 300
        });
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}
