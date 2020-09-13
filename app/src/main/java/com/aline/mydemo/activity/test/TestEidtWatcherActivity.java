package com.aline.mydemo.activity.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestEidtWatcherActivity extends AppCompatActivity {
    private static final String TAG = "TestEidtWatcherActivity";
    @BindView(R.id.et_test_watcher_1)
    EditText etTestWatcher1;
    @BindView(R.id.et_test_watcher_2)
    EditText etTestWatcher2;
    @BindView(R.id.tv_test_array_null)
    TextView tvTestArrayNull;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_eidt_watcher);
        ButterKnife.bind(this);
        initView();
//        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
//        sharedPreferences.edit().putString("IN","in").commit();
//        sharedPreferences.getString("IN");
    }

    private void initView() {
        double[] doubles = new double[4];
        doubles[2] = 0.534;
        doubles[0] = 0.8;
//        tvTestArrayNull.setText(Arrays.asList(doubles).toString());
//        boolean isNumm = doubles[1] >= (-1e-6) && doubles[1] <= (1e-6);//是否是double值
        boolean isNumm = Double.doubleToLongBits(doubles[1]) == Double.doubleToLongBits(0.0);
        tvTestArrayNull.setText(isNumm + "---------" + doubles[2] + doubles[1]);
        etTestWatcher1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged: " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                Log.e(TAG, "onTextChanged: " + s);
//                Log.e(TAG, "onTextChanged: " + (str.equals("")));
//                Log.e(TAG, "onTextChanged: " + (str == null));
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged: " + s);
            }
        });

        etTestWatcher2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged..............: " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged..............: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged..............: " + s);
            }
        });
    }
}
