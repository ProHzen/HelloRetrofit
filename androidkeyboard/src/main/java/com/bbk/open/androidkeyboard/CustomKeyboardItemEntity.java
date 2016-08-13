package com.bbk.open.androidkeyboard;

/**
 * 存放圆形circle数据
 * Created by Administrator on 2016/8/13.
 */
public class CustomKeyboardItemEntity {

    private String mTopText;

    private String mBottomText;

    private String mLeftText;

    private String mRightText;

    private String mCenterText;

    public CustomKeyboardItemEntity(String mTopText, String mBottomText, String mLeftText, String mRightText, String mCenterText) {
        this.mTopText = mTopText;
        this.mBottomText = mBottomText;
        this.mLeftText = mLeftText;
        this.mRightText = mRightText;
        this.mCenterText = mCenterText;
    }
}
