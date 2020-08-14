package com.aline.mydemo.activity.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.aline.mydemo.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class PermissionActivity extends AppCompatActivity {
    private static final String TAG = "PermissionActivity";
    @BindView(R.id.bt_permission)
    Button btPermission;
    @BindView(R.id.bt_rxpermission)
    Button btRxpermission;
    @BindView(R.id.bt_rxpermission_mul)
    Button btRxpermissionMul;
    @BindView(R.id.bt_rxpermission_mul_detail)
    Button btRxpermissionMulDetail;
    @BindView(R.id.bt_permission_is)
    Button btPermissionIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.bt_permission, R.id.bt_rxpermission, R.id.bt_rxpermission_mul, R.id.bt_rxpermission_mul_detail, R.id.bt_permission_is})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.bt_permission_is:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.setLogging(true);
                if (rxPermissions.isGranted(Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(this, "已授权", Toast.LENGTH_LONG);
                }
                break;
            case R.id.bt_permission:
                int i = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
                if (i != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                callPhone();
                break;

            case R.id.bt_rxpermission:
                rxPermissions = new RxPermissions(this);
                rxPermissions.setLogging(true);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Log.e(TAG, "accept: " + "同意");
                        } else {
                            Log.e(TAG, "accept: " + "拒绝");
                        }
                    }
                });
                //ensure效果与request相同
//                Observable.timer(100, TimeUnit.MILLISECONDS)
//                        .compose(rxPermissions
//                                .<Long>ensure(Manifest.permission.READ_EXTERNAL_STORAGE))
//                        .subscribe(new Consumer<Boolean>() {
//                            @Override
//                            public void accept(Boolean aBoolean) throws Exception {
//                                if (aBoolean) {
//                                    Log.e(TAG, "accept: " + "同意");
//                                } else {
//                                    Log.e(TAG, "accept: " + "拒绝");
//                                }
//                            }
//                        });
                break;
            case R.id.bt_rxpermission_mul:
                rxPermissions = new RxPermissions(this);
                rxPermissions.setLogging(true);
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    Log.e(TAG, "accept: " + "同意");
                                } else {
                                    Log.e(TAG, "accept: " + "拒绝");
                                }
                            }
                        });
                break;
            case R.id.bt_rxpermission_mul_detail:
                rxPermissions = new RxPermissions(this);
                rxPermissions.setLogging(true);
                rxPermissions.requestEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.BIND_NFC_SERVICE).subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.name.equalsIgnoreCase(Manifest.permission.READ_PHONE_STATE)) {
                            if (permission.granted) {
                                Log.e(TAG, "accept: " + "允许");
                            } else if (permission.shouldShowRequestPermissionRationale) {//未选中不再提示
                                Log.e(TAG, "accept: " + "不允许，。。");
                            } else {//选中不再提示
                                Log.e(TAG, "accept: " + "不允许，不再提示");
                            }
                        }
                    }
                });
                break;
        }
    }

    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:18801190176"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        }
    }


}
