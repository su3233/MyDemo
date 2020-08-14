package com.aline.mydemo.activity.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.google.android.material.snackbar.Snackbar;


public class MaterialMesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_mesign);
        View view = LayoutInflater.from(MaterialMesignActivity.this).inflate(R.layout.activity_material_mesign, null);
        showSnackBar(view);
    }


    public void showSnackBar(View view) {
        LinearLayout ll_show_snackbar = this.findViewById(R.id.ll_show_snackbar);
        Snackbar.make(ll_show_snackbar, "已删除", Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialMesignActivity.this, "已撤销", Toast.LENGTH_LONG).show();
            }
        }).setDuration(Snackbar.LENGTH_LONG).show();
    }
}
