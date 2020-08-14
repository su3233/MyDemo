package com.aline.mydemo.activity.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aline.mydemo.R;
import com.aline.mydemo.adapter.AsyncBaseAdapter;
import com.aline.mydemo.base.Logger;
import com.aline.mydemo.bean.NewsBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 慕课-Android必学-异步加载
 */
public class AsyncLoadActivity extends AppCompatActivity {
    private static final String TAG = "AsyncLoadActivity";
    private static final String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.lv_async_load)
    ListView lvAsyncLoad;
    private List<NewsBean.DataBean> datas = new ArrayList<>();
    private AsyncBaseAdapter asyncAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_load);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        asyncAdapter = new AsyncBaseAdapter(datas, AsyncLoadActivity.this, lvAsyncLoad);
//        LinearLayoutManager lm = new LinearLayoutManager(AsyncLoadActivity.this);
//        lm.setOrientation(LinearLayoutManager.VERTICAL);
//        rvAsyncLoad.setLayoutManager(lm);
//        lvAsyncLoad.setAdapter(asyncAdapter);
        new NewsAsyncTask().execute(URL);
    }

    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean.DataBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            lvAsyncLoad.setVisibility(View.GONE);
        }

        @Override
        protected List<NewsBean.DataBean> doInBackground(String... strings) {
            return getDatas(strings[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean.DataBean> newsBeans) {
            super.onPostExecute(newsBeans);

            progressBar.setVisibility(View.GONE);
            lvAsyncLoad.setVisibility(View.VISIBLE);
            if (newsBeans.size() != 0) {
                datas.clear();
                datas.addAll(newsBeans);
                asyncAdapter = new AsyncBaseAdapter(datas, AsyncLoadActivity.this, lvAsyncLoad);
                lvAsyncLoad.setAdapter(asyncAdapter);
            } else {
                Toast.makeText(AsyncLoadActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<NewsBean.DataBean> getDatas(String url) {
        List<NewsBean.DataBean> newsBeans = new ArrayList<>();
        try {
            String json = readStream(new URL(url).openStream());
            Logger.message(json);
            NewsBean newsbean = new Gson().fromJson(json, NewsBean.class);
            newsBeans.addAll(newsbean.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBeans;
    }

    /**
     * 读取网络流为 String Json
     *
     * @param inputStream
     */
    private String readStream(InputStream inputStream) {
        String result = "";
        try {
            InputStreamReader isr = new InputStreamReader(inputStream, "utf-8");
            BufferedReader buf = new BufferedReader(isr);
            String line = "";
            while ((line = buf.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
