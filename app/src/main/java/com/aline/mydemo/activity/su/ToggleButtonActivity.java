package com.aline.mydemo.activity.su;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.view.MyToggleButton;

public class ToggleButtonActivity extends AppCompatActivity {

    private MyToggleButton myToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button);

        initView();
    }

    private void initView() {
        //初始化mytogglebutton
        myToggleButton =(MyToggleButton) findViewById(R.id.my_togglebut);
        //设置打开背景图
        myToggleButton.setToggle_bkg_on(R.mipmap.switch_btn_on);
        //设置关闭背景图
        myToggleButton.setToggle_bkg_off(R.mipmap.switch_btn_off);
        //设置滑动块背景图
        myToggleButton.setToggle_slip(R.mipmap.switch_btn_slipper);
        //设置ToggleButton初始状态
        myToggleButton.setToggleState(false);
        //mytogglebutton设置监听
        myToggleButton.setOnToggleStateChangeListener(new MyToggleButton.OnToggleStateChangeListener() {

            @Override
            public void onToggleStateChange(Boolean state) {
                if(state){
                    Toast.makeText(ToggleButtonActivity.this, "打开", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ToggleButtonActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
