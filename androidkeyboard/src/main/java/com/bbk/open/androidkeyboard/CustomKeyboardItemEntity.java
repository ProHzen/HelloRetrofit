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

    public CustomKeyboardItemEntity(final String center, final String left, final String top, final String right) {
        this(center, left, top, right, null);
    }

    /**
     * 备份数据
     * @return
     */
    public CustomKeyboardItemEntity backup() {
        return new CustomKeyboardItemEntity(mCenterText, mLeftText, mTopText, mRightText, mBottomText);
    }

    public void setLeft() {
       // switchString(mLeftText, mCenterText);
        if (mLeftText == null) {
            return;
        }
        String temp = mLeftText;
        mLeftText = mCenterText;
        mCenterText = temp;
    }

    public void setTop() {
        switchString(mTopText, mCenterText);
        if (mTopText == null) {
            return;
        }
        String temp = mTopText;
        mTopText = mCenterText;
        mCenterText = temp;
    }

    public void setBottom() {
        switchString(mBottomText, mCenterText);
    }

    public void setRight() {
        switchString(mRightText, mCenterText);
    }

    public String getNumText() {
        return mCenterText;
    }

    public String getLettersText() {
        String str;
        str = mLeftText + mTopText + mRightText;
        if (mBottomText != null) {
            str += mBottomText;
        }
        return str;
    }

    public String getTopText() {
        return mTopText;
    }

    public void setTopText(String mTopText) {
        this.mTopText = mTopText;
    }

    public String getBottomText() {
        return mBottomText;
    }

    public void setBottomText(String mBottomText) {
        this.mBottomText = mBottomText;
    }

    public String getLeftText() {
        return mLeftText;
    }

    public void setLeftText(String mLeftText) {
        this.mLeftText = mLeftText;
    }

    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String mRightText) {
        this.mRightText = mRightText;
    }

    public String getCenterText() {
        return mCenterText;
    }

    public void setCenterText(String mCenterText) {
        this.mCenterText = mCenterText;
    }

    /**
     * 交换字符串
     * @param direction
     * @param center
     */
    private void switchString(String direction, String center) {
        if (direction == null) {
            return;
        }
        String temp = direction;
        direction = center;
        center = temp;
    }


}
