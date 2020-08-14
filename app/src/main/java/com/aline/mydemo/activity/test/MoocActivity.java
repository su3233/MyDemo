package com.aline.mydemo.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MoocActivity extends AppCompatActivity {

    @BindView(R.id.bt_async_load)
    Button btAsyncLoad;
    @BindView(R.id.bt_async_)
    Button btAsync;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mooc);
        ButterKnife.bind(this);
        mContext = MoocActivity.this;
    }

    @OnClick({R.id.bt_async_load, R.id.bt_async_})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_async_load:
                startActivity(new Intent(mContext, AsyncLoadActivity.class));
                break;
            case R.id.bt_async_:
                break;
        }
    }
}
