package com.bbk.open.interpolator;

import android.view.animation.Interpolator;

/**
 * 自定义差值器
 * Created by Administrator on 2016/8/20.
 */
public class HesitateInterPolator implements Interpolator {

    public HesitateInterPolator() {
    }

    @Override
    public float getInterpolation(float t) {
        float x=2.0f*t-1.0f;
        return 0.5f*(x*x*x + 1.0f);
    }
}
