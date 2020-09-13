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


public class DemoActivity extends AppCompatActivity {

    @BindView(R.id.bt_onsaveinstance)
    Button btOnsaveinstance;
    @BindView(R.id.bt_read_assets_json)
    Button btReadAssetsJson;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_1);
        ButterKnife.bind(this);
        mContext = DemoActivity.this;

    }


    @OnClick({R.id.bt_onsaveinstance, R.id.bt_read_assets_json})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_onsaveinstance:
                startActivity(new Intent(mContext, OnSaveInstanceActivity.class));
                break;
            case R.id.bt_read_assets_json:
                startActivity(new Intent(mContext, ReadAssetsJsonActivity.class));
                break;
        }
    }
}
