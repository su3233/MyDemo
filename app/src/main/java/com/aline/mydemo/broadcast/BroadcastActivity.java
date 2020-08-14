package com.aline.mydemo.broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

/**
 * 动态注册广播，静态注册广播，并发送接收广播
 */
public class BroadcastActivity extends AppCompatActivity {
    private static final String BROASTCAST_ACTION_DONG = "com.regist.broadcast";
    private MyBroadcast myBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        dymnaBroastcast();
        initView();


    }

    private void initView() {
        findViewById(R.id.bt_send_broadcast_dong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMyBroadcastDynamic();
            }
        });

        findViewById(R.id.bt_send_broadcast_jing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMyBroadcastStatic();
            }
        });
    }

    /**
     * 发送静态注册广播
     */
    private void sendMyBroadcastStatic() {
        Intent intent = new Intent("com.broastcast.jing");
        intent.setComponent(new ComponentName("com.test.testcode", "com.test.testcode.broadcast.MyReceiverJing"));
        intent.putExtra("MSG_JING", "Jing123");

        sendBroadcast(intent);
    }

    private void sendMyBroadcastDynamic() {
        Intent intent = new Intent();
        intent.setAction(BROASTCAST_ACTION_DONG);
        intent.putExtra("MSG_DONG", "Dong123");
        sendBroadcast(intent);
    }

    //动态注册广播
    private void dymnaBroastcast() {
        myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROASTCAST_ACTION_DONG);
        registerReceiver(myBroadcast, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcast);
    }
}
