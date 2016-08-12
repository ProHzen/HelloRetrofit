package com.bbk.open.helloretrofit.retrofit.http;

import com.bbk.open.helloretrofit.retrofit.entity.BaseResultEntity;
import com.bbk.open.helloretrofit.retrofit.entity.RetrofitEnity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.Call;
import rx.Observable;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/8/12.
 */
public interface HttpService {

    @retrofit2.http.POST("AppFiftyToneGraph/videoLink")
    Call<RetrofitEnity> getAllVideo(@retrofit2.http.Body boolean once_no);

    @retrofit2.http.POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEnity> getAllVideoBy(@Body boolean once_no);

    @retrofit2.http.POST("AppFiftyToneGraph/videoLink")
    Observable<BaseResultEntity<List<Subject>>> getAllVedioBys(@Body boolean once_no);
}
