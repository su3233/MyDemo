package com.aline.mydemo.activity.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
    private static final String TAG = "TimerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        initView();
    }

    private void initView() {
        this.findViewById(R.id.bt_start_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: " + "time");
                    }
                };
                timer.schedule(timerTask, 0, 5000);
            }
        });

        TextView textView = this.findViewById(R.id.tv_show_random);
        textView.setText(Math.random() + "....." + Math.random());
    }
}
