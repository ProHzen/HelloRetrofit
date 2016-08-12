package com.bbk.open.helloretrofit.retrofit.subscribers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.bbk.open.helloretrofit.retrofit.listener.HttpOnNextListener;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/12.
 */
public class ProgressSubscriber<T> extends Subscriber<T> {

    //回调接口
    private HttpOnNextListener mSubscriberOnNextListener;
    //弱引用 防止内存泄漏
    private WeakReference<Context> mActivity;
    //是否能取消请求
    private boolean cancel;
    //加载框自己定义
    private ProgressDialog pd;

    public ProgressSubscriber(HttpOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.mActivity = new WeakReference<>(context);
        this.cancel = false;
        initProgressDialog();
    }

    public ProgressSubscriber(boolean cancel, WeakReference<Context> mActivity, HttpOnNextListener mSubscriberOnNextListener) {
        this.cancel = cancel;
        this.mActivity = mActivity;
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        initProgressDialog();
    }


    /**
     * 初始化加载数据
     */
    private void initProgressDialog() {
        Context context = mActivity.get();
        if (pd == null && context != null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        onCancelProgress();
                    }
                });
            }
        }
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        Context context = mActivity.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }

    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    private void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("tag", "error----------->" + e.toString());
        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}
