package com.aline.mydemo.activity.test;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.aline.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnSaveInstanceActivity extends Activity {
    private static final String TAG = "OnSaveInstanceActivity";
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.bt_finish)
    Button btFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_save_instance);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            Log.e(TAG, "onCreate:OOOOOOO ");
            String account = savedInstanceState.getString("ACCOUNT");
            String password = savedInstanceState.getString("PASSWORD");
            if (!TextUtils.isEmpty(account)) {
                etAccount.setText(account + "OOOOOOO");
            }
            if (!TextUtils.isEmpty(password)) {
                etPsw.setText(password + "OOOOOOO");
            }
        }

        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String account = etAccount.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        if (!TextUtils.isEmpty(account)) {
            outState.putString("ACCOUNT", account);
        }
        if (!TextUtils.isEmpty(account)) {
            outState.putString("PASSWORD", psw);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState:PPPPPPPPPPP");
        String account = savedInstanceState.getString("ACCOUNT");
        String password = savedInstanceState.getString("PASSWORD");
        if (!TextUtils.isEmpty(account)) {
            etAccount.setText(account + "PPPPPPPPPPP");
        }
        if (!TextUtils.isEmpty(password)) {
            etPsw.setText(password + "PPPPPPPPPPP");
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        Log.e(TAG, "onPostResume: ");
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
