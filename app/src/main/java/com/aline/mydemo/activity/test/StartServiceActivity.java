package com.aline.mydemo.activity.test;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.service.StartService;

public class StartServiceActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection;
    private Intent serviceIntent;
    private Intent startIntent;
    private TextView tv_show_service_num;
    public static ServiceHandler serviceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
//        startService();
        initView();

//        startService();

    }

    private void initView() {
        serviceHandler = new ServiceHandler();

        tv_show_service_num = this.findViewById(R.id.tv_show_service_num);
        this.findViewById(R.id.bt_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
    }

    public class ServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != 0) {
                int num = msg.what;
                tv_show_service_num.setText(num + "");
            }
            super.handleMessage(msg);
        }
    }

    private void startService() {
        startIntent = new Intent(this, StartService.class);
        startService(startIntent);

    }



    @Override
    protected void onDestroy() {
//        unbindService(serviceConnection);
        stopService(startIntent);
        super.onDestroy();
    }
}
