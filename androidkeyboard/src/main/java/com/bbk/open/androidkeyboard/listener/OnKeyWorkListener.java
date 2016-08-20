package com.bbk.open.androidkeyboard.listener;

import android.view.View;

/**
 * Created by Administrator on 2016/8/13.
 */
public interface OnKeyWorkListener {
    /**
     * @Description 按中心键的时候
     * @param view 监听事件的view
     */
    void onDpadCenter(View view, boolean flag);

    /**
     * @Description 按返回的时候
     * @param view 监听事件的view
     * @return 返回true，停止返回事件，false，继续传返回事件到下一层
     */
    boolean onBack(View view);
}
