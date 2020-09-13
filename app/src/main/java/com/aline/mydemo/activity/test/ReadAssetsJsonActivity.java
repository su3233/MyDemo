package com.aline.mydemo.activity.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aline.mydemo.R;
import com.aline.mydemo.adapter.JsonAdapter;
import com.aline.mydemo.bean.TestNullBean;
import com.aline.mydemo.utils.ReadAssetsJsonUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadAssetsJsonActivity extends AppCompatActivity {
    private static final String TAG = "ReadAssetsJsonActivity";
    private List<String> datas = new ArrayList<>();
    private JsonAdapter jsonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_assets_json);
        initView();
        initData();
    }

    private void initView() {
        for (int i = 0; i < 25; i++) {
            datas.add("测试" + i);
        }
        RecyclerView rvJson = findViewById(R.id.rv_show_josn);
        LinearLayoutManager lm = new LinearLayoutManager(ReadAssetsJsonActivity.this, LinearLayoutManager.VERTICAL, false);
        jsonAdapter = new JsonAdapter(ReadAssetsJsonActivity.this, datas);
        rvJson.setAdapter(jsonAdapter);
        rvJson.setLayoutManager(lm);

        findViewById(R.id.bt_change_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datas.size() != 0) {
                    int index = new Random().nextInt(datas.size());
                    String item = datas.get(index);
                    item += "..增加";
                    datas.set(index, item);
                    jsonAdapter.notifyItemChanged(index);
                }
            }
        });
    }

    private void initData() {
        String result = "";
        result = new ReadAssetsJsonUtils().readJsonFromAsset(ReadAssetsJsonActivity.this);
        if (!TextUtils.isEmpty(result)) {
            datas.clear();
            TestNullBean testNullBean = new Gson().fromJson(result, TestNullBean.class);
            for (int i = 0; i < testNullBean.getData().size(); i++) {
                datas.add(testNullBean.getData().get(i).getFileName());
                jsonAdapter.notifyDataSetChanged();
                Log.e(TAG, "initData: " + testNullBean.getData().get(i).getFileMd5());
            }
        }
    }


}
