package com.bbk.open.androidkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/8/16.
 */
public class CustomKeyboardItem extends FrameLayout implements OnKeyWorkListener {

    private static final String TAG = "CustomKeyboardItem";

    private CustomCircleKeyboardItem circleKeyboardItem;
    private CustomNumKeyboardItem numKeyboardItem;
    private CustomKeyboardItemEntity mData;

    public CustomKeyboardItem(Context context) {
        super(context);
        load();
    }

    public CustomKeyboardItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        load();
    }

    public CustomKeyboardItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        load();
    }

    private void load() {
        if (isInEditMode()) {
            return;
        }
        inflateLayout();
        initUI();
        initData();
    }

    private void inflateLayout() {
        try {
            LayoutInflater.from(getContext()).inflate(R.layout.custom_keyboard_item, this, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        circleKeyboardItem = (CustomCircleKeyboardItem) findViewById(R.id.custom_circle_keyboard_item);
        if (circleKeyboardItem == null) {
            circleKeyboardItem = new CustomCircleKeyboardItem(getContext());
            this.addView(circleKeyboardItem);
        }
        numKeyboardItem = (CustomNumKeyboardItem) findViewById(R.id.custom_number_keyboard_item);
        if (numKeyboardItem == null) {
            numKeyboardItem = new CustomNumKeyboardItem(getContext());
            this.addView(numKeyboardItem);
        }
    }

    private void initData() {
        circleKeyboardItem.setOnKeyWorkListener(this);
        numKeyboardItem.setOnKeyWorkListener(this);
        numKeyboardItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDpadCenterListener.onDpadCenter(getSelectedString());
            }
        });
        numKeyboardItem.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                circleKeyboardItem.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    /**
     * @Description 得到选中的字符
     * @return 选中的字符
     */
    public String getSelectedString() {
        return mData.getNumText();
    }


    /**
     * @Description 设置数据
     * @param data 自定义键盘item实体类
     */
    public void setData(final CustomKeyboardItemEntity data) {
        mData = data;
        circleKeyboardItem.setData(data);
        numKeyboardItem.setData(data);
    }

    /**
     * @Field @showAnim : 是否需要动画
     */
    private boolean showAnim = false;

    @Override
    public void onDpadCenter(View view) {
        if (view == numKeyboardItem && numKeyboardItem.isShown()
                && !circleKeyboardItem.isShown()) {
            circleKeyboardItem.appearWithAnimation(showAnim);
            numKeyboardItem.disappearWithAnimation(showAnim);
        } else if (view == circleKeyboardItem) {
            if (null != mOnDpadCenterListener) {
                mOnDpadCenterListener.onDpadCenter(circleKeyboardItem.getSelectedString());
            }
        }

    }
    private OnDpadCenterListener mOnDpadCenterListener;

    /**
     * @param onDpadCenterListener the onDpadCenterListener to set
     */
    public void setOnDpadCenterListener(final OnDpadCenterListener onDpadCenterListener) {
        this.mOnDpadCenterListener = onDpadCenterListener;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.azz.customkeyboard.listener.OnKeyWorkListener#onBack()
     */
    @Override
    public boolean onBack(final View view) {
        if (view == circleKeyboardItem
                && circleKeyboardItem.isShown()
                && !numKeyboardItem.isShown()) {
            numKeyboardItem.appearWithAnimation(showAnim);
            circleKeyboardItem.disappearWithAnimation(showAnim);
            return true;
        }
        return false;
    }
}
