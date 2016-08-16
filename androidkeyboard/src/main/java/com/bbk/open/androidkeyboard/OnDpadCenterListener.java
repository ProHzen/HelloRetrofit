package com.bbk.open.androidkeyboard;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface OnDpadCenterListener {
    /**
     * @Description 按中心键的时候
     * @param selectString 选中的字符
     */
    void onDpadCenter(String selectString);
    /**
     * @Description 清除按钮被点击
     */
    void onClearPressed();
    /**
     * @Description 当删除按钮被点击
     */
    void onDeletePressed();
}
