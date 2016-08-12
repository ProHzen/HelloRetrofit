package com.bbk.open.helloretrofit.retrofit.http;

import com.bbk.open.helloretrofit.retrofit.entity.BaseEnity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/12.
 */
public class HttpManager {

    public static final String BASE_URL = "http://www.izaodao.com/Api/";
    private static final int DEFAULT_TIMEOUT = 6;
    private HttpService httpService;
    private volatile static HttpManager INSTANCE;

    //构造方法私有
    private HttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService =  retrofit.create(HttpService.class);
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 处理http请求
     *
     * @param baseEnity 封装的请求数据
     */
    public void doHttpDeal(BaseEnity baseEnity) {
        Observable observable = baseEnity.getObservable(httpService)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseEnity);
        observable.subscribe(baseEnity.getSubscirber());
    }
}
