package com.aline.mydemo.activity.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.view.MyGridView;

import java.util.ArrayList;
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
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

    }



}
