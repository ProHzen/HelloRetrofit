package com.bbk.open.helloretrofit;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2016/8/11.
 */
public class WelcomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView imageView = (ImageView) findViewById(R.id.iv_welcome);
        imageView.setImageResource(R.mipmap.ic_launcher);

        rx.Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).map(l->{
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return null;
        }).subscribe();
    }
}
