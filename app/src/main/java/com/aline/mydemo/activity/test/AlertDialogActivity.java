package com.aline.mydemo.activity.test;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.aline.mydemo.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertDialogActivity extends AppCompatActivity {
    private static final String TAG = "AlertDialogActivity";
    @BindView(R.id.bt_normal)
    Button btNormal;
    @BindView(R.id.bt_list)
    Button btList;
    @BindView(R.id.bt_single_choice)
    Button btSingleChoice;
    @BindView(R.id.bt_multi_choice)
    Button btMultiChoice;
    @BindView(R.id.bt_my)
    Button btMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_normal, R.id.bt_list, R.id.bt_single_choice, R.id.bt_multi_choice, R.id.bt_my, R.id.bt_waiting, R.id.bt_progress
            , R.id.bt_date, R.id.bt_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_normal:
                showNormalDialog();
                break;
            case R.id.bt_list:
                showListDialog();
                break;
            case R.id.bt_single_choice:
                showSingleChoiceDialog();
                break;
            case R.id.bt_multi_choice:
                showMulChoiceDialog();
                break;
            case R.id.bt_my:
                showMyDialog();
                break;
            case R.id.bt_waiting:
                showWaitingDialog();
                break;
            case R.id.bt_progress:
                showProgressDialog();
                break;
            case R.id.bt_date:
                showDateDialog();
                break;
            case R.id.bt_time:
                showTimeDialog();
                break;
        }
    }

    private void showTimeDialog() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);//当前年
        int month = c.get(Calendar.MONTH);//当前月
        int day = c.get(Calendar.DAY_OF_MONTH);//当前日
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e(TAG, "onTimeSet: " + hourOfDay + "时" + minute);
            }
        }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
        timePickerDialog.show();
    }

    private void showDateDialog() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);//当前年
        int month = c.get(Calendar.MONTH);//当前月
        int day = c.get(Calendar.DAY_OF_MONTH);//当前日
        DatePickerDialog datePickerDialog = new DatePickerDialog(AlertDialogActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.e(TAG, "onDateSet: " + year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));//现实的默认日期
        datePickerDialog.show();
    }

    private void showWaitingDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("进行中...");
        progressDialog.setIndeterminate(true);//模糊进度
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void showProgressDialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("进度条");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < MAX_PROGRESS) {
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();
            }
        }).start();

    }

    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否退出");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showListDialog() {
        final String items[] = {"AA", "BB", "CC"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("列表对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void showSingleChoiceDialog() {
        final String items[] = {"AA", "BB", "CC"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选");
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showMulChoiceDialog() {
        final String items[] = {"篮球", "足球", "排球"};
        final boolean selected[] = {true, false, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选");
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(AlertDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我的对话框");
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher);
        View view = getLayoutInflater().inflate(R.layout.view_my_dialog, null);
        builder.setView(view);
        EditText et_name = view.findViewById(R.id.et_name);
        final String name = et_name.getText().toString().trim();
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this, name, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
