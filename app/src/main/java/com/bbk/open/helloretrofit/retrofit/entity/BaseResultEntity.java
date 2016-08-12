package com.bbk.open.helloretrofit.retrofit.entity;

/**
 * Created by Administrator on 2016/8/12.
 */
public class BaseResultEntity<T> {
    //判断标示
    private int ret;
    //提示信息
    private String msg;
    //显示数据(用户关心的数据)
    private T data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
