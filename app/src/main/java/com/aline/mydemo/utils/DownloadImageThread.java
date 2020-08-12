package com.aline.mydemo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImageThread extends Thread {
    private ImageView imageView;
    private String url;
    private Activity mContext;

    public DownloadImageThread(Activity context, String url, ImageView imageView) {
        this.mContext = context;
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
