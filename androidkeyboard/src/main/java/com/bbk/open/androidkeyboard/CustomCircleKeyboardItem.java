package com.bbk.open.androidkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/13.
 */
public class CustomCircleKeyboardItem extends RelativeLayout {

    private static final String TAG = "CustomCircleKeyboardItem";

    private TextView topTextView;
    private TextView bottomTextView;
    private TextView leftTextView;
    private TextView rightTextView;
    private Button centerButton;
    private CustomKeyboardItemEntity mDatas;
    private CustomKeyboardItemEntity mDatasBackup;
    private OnKeyWorkListener mOnKeyWorkListener;

    public CustomCircleKeyboardItem(Context context) {
        super(context);
        load();
    }

    public CustomCircleKeyboardItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        load();
    }

    public CustomCircleKeyboardItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        load();
    }

    /**
     * 加载数据
     */
    private void load() {
        if (isInEditMode()) {
            return;
        }
        inflateLayout();
        initUI();
        initData();
    }

    /**
     * 初始化布局界面
     */
    private void inflateLayout() {
        try {
            LayoutInflater.from(getContext()).inflate(R.layout.custom_keyboard_item_circle, this, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化UI界面
     */
    private void initUI() {

    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * @param onKeyWorkListener the mOnKeyWorkListener to set
     */
    public void setOnKeyWorkListener(final OnKeyWorkListener onKeyWorkListener) {
        this.mOnKeyWorkListener = onKeyWorkListener;
    }

}
