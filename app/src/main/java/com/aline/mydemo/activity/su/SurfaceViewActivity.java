package com.aline.mydemo.activity.su;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.view.MySurfaceView;

public class SurfaceViewActivity extends AppCompatActivity implements View.OnClickListener {

    private MySurfaceView mysurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);

        initView();
    }

    private void initView() {
        mysurface = (MySurfaceView) this.findViewById(R.id.my_sv);
        this.findViewById(R.id.bt_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_clear:
                mysurface.reDraw();
                break;
        }
    }
}
