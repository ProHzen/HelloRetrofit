package com.bbk.open.helloretrofit.retrofit.entity;

import com.bbk.open.helloretrofit.retrofit.http.HttpService;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/12.
 */
public class SubjectPost extends BaseEnity {

    private Subscriber mSubscriber;
    private boolean all;

    public SubjectPost(Subscriber mSubscriber, boolean all) {
        this.mSubscriber = mSubscriber;
        this.all = all;
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.getAllVedioBys(all);
    }

    @Override
    public Subscriber getSubscirber() {
        return mSubscriber;
    }


}
