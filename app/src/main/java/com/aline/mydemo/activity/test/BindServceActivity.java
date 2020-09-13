package com.aline.mydemo.activity.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.service.BindService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindServceActivity extends AppCompatActivity {
    private static final String TAG = "BindServceActivity";
    @BindView(R.id.bt_bindservice)
    Button btBindservice;
    private ServiceConnection serviceConnection;
    private Intent serviceIntent;
    private BindService bindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_servce);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_bindservice)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_bindservice:
                bindService();
                break;
        }
    }

    private void bindService() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bindService = ((BindService.MyBinder) service).getBindservice();//通过Binder得到service示例，调用service方法
                Log.e(TAG, "onServiceConnected: 连接成功");
                bindService.excute();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bindService = null;
            }
        };
        serviceIntent = new Intent(this, BindService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
