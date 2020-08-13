package com.aline.mydemo.network;

import com.aline.mydemo.base.ConstantString;
import com.aline.mydemo.bean.MessageBean;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static RetrofitService retrofitService;
    private static RetrofitUtils instance;
    private static OkHttpClient okHttpClient;

    public static void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("ConstantString.BASE_URL")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);

    }

    public static OkHttpClient getOkHttpClient() {
        //okhttp cache缓存
        Cache cache = new Cache(new File(ConstantString.CACHE_PATH), ConstantString.CACHE_SIZE);  //
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new LoggingInterceptor())//日志拦截器
                    .cache(cache)//设置缓存
                    .build();
        }
        return okHttpClient;
    }

    public static void rxRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantString.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<MessageBean> obserable = service.getData();
//        obserable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MessageBean>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(MessageBean messageBean) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private RetrofitUtils() {
        init();
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
}
