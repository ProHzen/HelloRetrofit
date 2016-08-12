package com.bbk.open.helloretrofit.retrofit.entity;

import com.bbk.open.helloretrofit.retrofit.exception.HttpTimeException;
import com.bbk.open.helloretrofit.retrofit.http.HttpService;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/8/12.
 */
public abstract class BaseEnity<T> implements Func1<BaseResultEntity<T>, T> {

    /**
     * 设置参数
     * @param methods
     * @return
     */
    public abstract Observable getObservable(HttpService methods);

    /**
     * 设置回调sub
     * @return
     */
    public abstract Subscriber getSubscirber();

    @Override
    public T call(BaseResultEntity<T> tBaseResultEntity) {
        if (tBaseResultEntity.getRet() == 0) {
            throw new HttpTimeException(tBaseResultEntity.getMsg());
        }
        return tBaseResultEntity.getData();
    }
}
