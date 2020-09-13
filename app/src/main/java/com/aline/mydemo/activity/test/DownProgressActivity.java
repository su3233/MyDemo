package com.aline.mydemo.activity.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aline.mydemo.R;

import java.io.File;
import java.io.IOException;

import io.github.lizhangqu.coreprogress.ProgressHelper;
import io.github.lizhangqu.coreprogress.ProgressUIListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class DownProgressActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0x12;
    private Button bt_down;
    private ProgressBar progressBar_down;
    private Button bt_upload;
    private ProgressBar progressBar_upload;
    private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_progress);
        initView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_download:
                checkPermission();
                break;
        }
    }

    /**
     * 下载
     */
    private void initData() {
        //client
        OkHttpClient okHttpClient = new OkHttpClient();
//request builder
        Request.Builder builder = new Request.Builder();
        builder.url("http://211.136.65.147/cache/api.mydown.com/download/6882986f18067f6acfe674d91e01b514/473303021_6/" +
                "newsoft/youkuclient_setup_7.6.7.11220.exe?ich_args2=117-02180406062527_55173939465b1c89e92d46b15ae3d77a_10001002_9c8960" +
                "2ad3c1f2d59339518939a83798_fe1aa67272828952ffabee4679bc7dc1");
        builder.get();
//call
        Call call = okHttpClient.newCall(builder.build());
//enqueue
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "=============onFailure===============");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "=============onResponse===============");
                Log.e("TAG", "request headers:" + response.request().headers());
                Log.e("TAG", "response headers:" + response.headers());

                //your original response body
                ResponseBody body = response.body();
                //wrap the original response body with progress
                ResponseBody responseBody = ProgressHelper.withProgress(body, new ProgressUIListener() {

                    //if you don't need this method, don't override this methd. It isn't an abstract method, just an empty method.
                    @Override
                    public void onUIProgressStart(long totalBytes) {
                        super.onUIProgressStart(totalBytes);
                        Log.e("TAG", "onUIProgressStart:" + totalBytes);
                    }

                    @Override
                    public void onUIProgressChanged(long numBytes, long totalBytes, float percent, float speed) {
                        Log.e("TAG", "=============start===============");
                        Log.e("TAG", "numBytes:" + numBytes);
                        Log.e("TAG", "totalBytes:" + totalBytes);
                        Log.e("TAG", "percent:" + percent);
                        Log.e("TAG", "speed:" + speed);
                        Log.e("TAG", "============= end ===============");
                        progressBar_down.setProgress((int) (100 * percent));
                        tv_info.setText("numBytes:" + numBytes + " bytes" + "\ntotalBytes:" + totalBytes + " bytes" + "\npercent:" + percent * 100 + " %" + "\nspeed:" + speed * 1000 / 1024 / 1024 + " MB/秒");
                    }

                    //if you don't need this method, don't override this methd. It isn't an abstract method, just an empty method.
                    @Override
                    public void onUIProgressFinish() {
                        super.onUIProgressFinish();
                        Log.e("TAG", "onUIProgressFinish:");
                        Toast.makeText(getApplicationContext(), "结束上传", Toast.LENGTH_SHORT).show();
                    }

                });
                //read the body to file
                BufferedSource source = responseBody.source();
                File outFile = new File("sdcard/temp.file");
                outFile.delete();
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();
                BufferedSink sink = Okio.buffer(Okio.sink(outFile));
                source.readAll(sink);
                sink.flush();
                source.close();
            }
        });
//        call.cancel();
    }

    private void initView() {
        progressBar_upload = findViewById(R.id.upload_progress);
        bt_upload = findViewById(R.id.bt_upload);
        progressBar_down = findViewById(R.id.download_progress);
        bt_down = findViewById(R.id.bt_download);
        tv_info = findViewById(R.id.tv_info);
        bt_down.setOnClickListener(this);
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            initData();
            Log.e("TAG", "checkPermission: 已经授权！");
        }
    }
}
