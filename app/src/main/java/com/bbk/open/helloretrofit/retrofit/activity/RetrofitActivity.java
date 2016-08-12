package com.bbk.open.helloretrofit.retrofit.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bbk.open.helloretrofit.R;
import com.bbk.open.helloretrofit.retrofit.subscribers.ProgressSubscriber;
import com.bbk.open.helloretrofit.retrofit.entity.RetrofitEnity;
import com.bbk.open.helloretrofit.retrofit.entity.Subject;
import com.bbk.open.helloretrofit.retrofit.entity.SubjectPost;
import com.bbk.open.helloretrofit.retrofit.http.HttpManager;
import com.bbk.open.helloretrofit.retrofit.http.HttpService;
import com.bbk.open.helloretrofit.retrofit.listener.HttpOnNextListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/12.
 */
public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMsg;
    private static final String TAG = "RetrofitActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        ((Button)findViewById(R.id.btn_simple)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_rx)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                onButtonClick();
                break;
            case R.id.btn_rx:
                simpleDo();
                break;
        }
    }

    /**
     * Retrofit 加入RxJava實現http请求
     */
    private void onButtonClick() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpManager.BASE_URL)
                .build();

        final ProgressDialog pd = new ProgressDialog(this);

        HttpService apiService = retrofit.create(HttpService.class);
        Observable<RetrofitEnity> observable = apiService.getAllVideoBy(true);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RetrofitEnity>() {
                    @Override
                    public void onCompleted() {
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString() );
                        if (pd != null && pd.isShowing()) {
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onNext(RetrofitEnity retrofitEnity) {
                        tvMsg.setText("无封装：\n" + retrofitEnity.getData().toString());
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        pd.show();
                    }
                });
    }

    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<Subject>>() {
        @Override
        public void onNext(List<Subject> subjects) {
            tvMsg.setText("已封装：\n" + subjects.toString());
        }
    };

    /**
     *  完美封装简化版
     */
    private void simpleDo() {
        SubjectPost postEntity = new SubjectPost(
                new ProgressSubscriber(simpleOnNextListener, this), true);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(postEntity);
    }
}
