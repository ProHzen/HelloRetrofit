package com.bbk.open.androidkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/13.
 */
public class CustomCircleKeyboardItem extends RelativeLayout implements View.OnTouchListener {

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
        topTextView = (TextView) findViewById(R.id.topTextView);
        bottomTextView = (TextView) findViewById(R.id.bottomTextView);
        leftTextView = (TextView) findViewById(R.id.leftTextView);
        rightTextView = (TextView) findViewById(R.id.rightTextView);
        centerButton = (Button) findViewById(R.id.centerButton);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        centerButton.setOnTouchListener(this);
        //初始化数据
        mDatas = new CustomKeyboardItemEntity("A", "B", "C", "D", "E");
        setData(mDatas);
    }

    /**
     * @param onKeyWorkListener the mOnKeyWorkListener to set
     */
    public void setOnKeyWorkListener(final OnKeyWorkListener onKeyWorkListener) {
        this.mOnKeyWorkListener = onKeyWorkListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.e(TAG, "onTouchEvent: event.getX = " + x + "event.getY = " + y);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (x > topTextView.getLeft() && x < topTextView.getRight()) {
                if (y > topTextView.getTop() && y < topTextView.getBottom()) {
                    mDatasBackup.setTop();
                    Log.e(TAG, "topTextView: " + topTextView.getText());
                }
            }
        }
        updateDataBackup();
        return true;
    }

    /**
     * @Description 设置数据
     * @param data 自定义键盘item实体类
     */
    public void setData(final CustomKeyboardItemEntity data) {
        mDatas = data;
        mDatasBackup = mDatas.backup();
        leftTextView.setText(data.getLeftText());
        topTextView.setText(data.getTopText());
        rightTextView.setText(data.getRightText());
        bottomTextView.setText(data.getBottomText());
        centerButton.setText(data.getCenterText());
    }

    /**
     * 更新数据
     */
    public void updateDataBackup() {
        leftTextView.setText(mDatasBackup.getLeftText());
        topTextView.setText(mDatasBackup.getTopText());
        rightTextView.setText(mDatasBackup.getRightText());
        bottomTextView.setText(mDatasBackup.getBottomText());
        centerButton.setText(mDatasBackup.getCenterText());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e(TAG, "onTouchEvent: event.getX = " + motionEvent.getX() + "event.getY = " + motionEvent.getY());
        return false;
    }
}
