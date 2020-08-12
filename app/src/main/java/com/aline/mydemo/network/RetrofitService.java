package com.aline.mydemo.network;

import com.aline.mydemo.bean.MessageBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RetrofitService {

    @GET("group_user.php?act=group_index")
    Call<MessageBean> getMessage();

    @GET("group_user.php")
    Observable<MessageBean> getData();

    @FormUrlEncoded
    @POST
    Call<MessageBean> getMessagePost(@Field("token") String token);
}
