package com.aline.mydemo.activity.su;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;


public class RadioButtonActivity extends AppCompatActivity {

    private TextView textView;
    private RadioGroup radiogroup;
    private RadioButton radiobutton1, radiobutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        initView();
    }

    private void initView() {
        //通过控件的ID来得到代表控件的对象
        textView = (TextView) this.findViewById(R.id.text_view);
        radiogroup = (RadioGroup) findViewById(R.id.radio_group);
        radiobutton1 = (RadioButton) findViewById(R.id.rd1);
        radiobutton2 = (RadioButton) findViewById(R.id.rd2);

        //调用setOnCheckedChangeListener来对RadioGroup进行监听的代码
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == radiobutton1.getId()){
                    textView.setText("北京");
                }else if(checkedId == radiobutton2.getId()){
                    textView.setText("上海");
                }
            }
        });
    }
}
