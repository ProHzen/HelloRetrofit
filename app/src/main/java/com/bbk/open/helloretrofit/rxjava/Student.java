package com.bbk.open.helloretrofit.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class Student {

    private String name;
    private List<Course> mList;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Course> mList) {
        this.name = name;
        this.mList = mList;
    }

    public List<Course> getmList() {
        return mList;
    }


    public String getName() {
        return name;
    }
}
