package com.aline.mydemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;


import com.aline.mydemo.R;
import com.aline.mydemo.adapter.AsyncBaseAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author SuTs
 * @create 2020/5/7 16:11
 * @Describe
 */
public class ImageLoader {
    private ImageView mImageview;
    private String mUrl;
    private LruCache<String, Bitmap> lruCache;
    private Set<ImageAsyncLoad> mTask;
    private ListView mListView;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageview.getTag().equals(mUrl)) {
                mImageview.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public ImageLoader(ListView listView) {
        mListView = listView;
        mTask = new HashSet<>();
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //每次存入缓存时，使用
                return value.getByteCount();
            }
        };
    }

    public Bitmap getLruCache(String url) {
        return lruCache.get(url);
    }

    public void setLruCache(String url, Bitmap bitmap) {
        if (getLruCache(url) == null) {
            lruCache.put(url, bitmap);
        }
    }

    public void showImageByThread(final ImageView imageView, final String url) {
        mImageview = imageView;
        mUrl = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 读取流中的数据为Bitmap
     *
     * @param urlString
     */
    private static Bitmap getBitmapFromUrl(String urlString) {
        Bitmap bitmap;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(bis);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showImageByAsync(ImageView imageView, String url) {
        //从缓存中取出图片
        Bitmap bitmap = getLruCache(url);
        if (bitmap == null) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 加载可见项的图片URL，
     *
     * @param start
     * @param end
     */
    public void loadIamgesWhenVisibility(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = AsyncBaseAdapter.URLS[i];
            Bitmap bitmap = getLruCache(url);
            if (bitmap == null) {
                ImageAsyncLoad task = new ImageAsyncLoad(url);
                task.execute(url);
                mTask.add(task);
            } else {
                ImageView imageView = mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 滚动时取消加载图片
     */
    public void cancleAllTask() {
        if (mTask != null) {
            for (ImageAsyncLoad task : mTask) {
                task.cancel(false);
            }
        }
    }

    class ImageAsyncLoad extends AsyncTask<String, Void, Bitmap> {
        private String url;
//        private ImageView imageview;

        public ImageAsyncLoad(String sourceUrl) {//ImageView sourceImageView,
//            imageview = sourceImageView;
            url = sourceUrl;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            //从网络获取图片，并加入缓存
            Bitmap bitmap = getBitmapFromUrl(strings[0]);
            if (bitmap != null) {
                setLruCache(strings[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView1 = mListView.findViewWithTag(url);
            if (imageView1 != null && bitmap != null) {
                imageView1.setImageBitmap(bitmap);
            }
            mTask.remove(this);
//            if (imageview.getTag().equals(url)) {
//                imageview.setImageBitmap(bitmap);
//            }
        }
    }
}
