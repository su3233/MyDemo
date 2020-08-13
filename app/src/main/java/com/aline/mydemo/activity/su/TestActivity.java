package com.aline.mydemo.activity.su;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.service.MyService;


/**
 * 测试clone代码后，提交了
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = TestActivity.this;
        initView();
        setData();
    }

    private void setData() {

    }

    private void initView() {
        this.findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                startService(new Intent(mContext, MyService.class));
                break;
        }
    }
}
