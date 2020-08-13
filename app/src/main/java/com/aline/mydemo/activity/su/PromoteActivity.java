package com.aline.mydemo.activity.su;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.adapter.PromoteAllAdapter;
import com.aline.mydemo.view.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 进阶
 */
public class PromoteActivity extends AppCompatActivity {

    private MyGridView gv_promote;
    private String[] itemsArr = {"慕课", "开发进阶", "从小工到专家", "爱上Android", "设计模式"};
    private List<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
    }


    private void initView() {
        gv_promote = (MyGridView) findViewById(R.id.gv_promote_all);
        List<String> itemList = Arrays.asList(itemsArr);//此处转换的list不能add和remove操作，只是预览视图
        PromoteAllAdapter promoteAllAdapter = new PromoteAllAdapter(PromoteActivity.this, itemList);
        gv_promote.setAdapter(promoteAllAdapter);
    }

}
