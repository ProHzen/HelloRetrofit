package com.bbk.open.helloretrofit.retrofit.entity;

import java.util.List;

import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/8/12.
 */
public class RetrofitEnity {

    private int ret;
    private String msg;
    private List<Subject> data;

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
    public List<Subject> getData() {
        return data;
    }
    public void setData(List<Subject> data) {
        this.data = data;
    }
}
