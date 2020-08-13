package com.aline.mydemo.activity.su;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.adapter.MyDbAdapter;
import com.aline.mydemo.bean.RenBean;
import com.aline.mydemo.db.xutils.RenDao;

import java.util.ArrayList;
import java.util.List;


public class MyDbActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv_all;
    List<RenBean> rens = new ArrayList<RenBean>();
    private Context context;
    private RenDao renDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_db);
        context = MyDbActivity.this;
        renDao = new RenDao(MyDbActivity.this);
        initView();
    }

    private void initView() {
        this.findViewById(R.id.bt_add).setOnClickListener(this);
        this.findViewById(R.id.bt_show).setOnClickListener(this);
        lv_all = (ListView) this.findViewById(R.id.lv_all);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                renDao.add(new RenBean("laoli", 5, "56513"));
                break;

            case R.id.bt_show:
                rens = renDao.getAll();
                lv_all.setAdapter(new MyDbAdapter(context, rens));
                break;
        }
    }
}
