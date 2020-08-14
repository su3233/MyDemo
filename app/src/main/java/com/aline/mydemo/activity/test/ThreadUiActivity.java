package com.aline.mydemo.activity.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

import java.util.concurrent.ArrayBlockingQueue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SuTongsheng
 * @create 2019/1/22
 * @Describe 在线程中更新UI
 */
public class ThreadUiActivity extends AppCompatActivity {

    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_stop)
    Button btStop;
    private static final int NUM1 = 0x21;
    private static final int NUM2 = 0x22;
    private int tempNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_ui);
        ButterKnife.bind(this);
        new ArrayBlockingQueue<String>(200, true);//阻塞队列

    }

    @SuppressLint("HandlerLeak")
    private Handler myHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NUM1:
                    tvNum.setText(msg.obj + "");
                    break;
                case NUM2:
                    tempNum++;
                    tvNum.setText(tempNum + "");
                    break;
                default:
                    break;
            }

        }
    };

    @OnClick({R.id.tv_num, R.id.bt_start, R.id.bt_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_num:
                break;
            case R.id.bt_start:
//                new MyThread().start();//1
//                new Thread(new Runnable() {//2 匿名内部类
//                    @Override
//                    public void run() {
//
//                    }
//                }).start();
                new Thread(new MyRunnable()).start();//3
                break;
            case R.id.bt_stop:
                new MyThread().start();//1
                break;
        }
    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
//                    Message message = new Message();//传递数据
                    Message message = Message.obtain();
                    message.what = NUM1;
                    message.obj = i;
                    myHandle.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                    myHandle.sendEmptyMessage(NUM2);//记录标志，区分标记更新UI
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
