package com.aline.mydemo.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.service.TimerService;

public class TimerServiceActivity extends AppCompatActivity {

    private Context mContext;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_service);
        mContext = TimerServiceActivity.this;
        initView();
    }

    private void initView() {
        this.findViewById(R.id.bt_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(mContext, TimerService.class);
                startService(serviceIntent);
            }
        });

        this.findViewById(R.id.bt_stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(mContext, TimerService.class);
                stopService(serviceIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        super.onDestroy();
    }
}
