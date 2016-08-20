package com.bbk.open.androidkeyboard.enity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bbk.open.androidkeyboard.listener.OnDpadCenterListener;
import com.bbk.open.androidkeyboard.listener.OnKeyWorkListener;
import com.bbk.open.androidkeyboard.R;

/**
 * Created by Administrator on 2016/8/16.
 */
public class CustomNumKeyboardItem extends LinearLayout implements View.OnClickListener {

    private Button mNumButton;
    private TextView mLetterTextView;
    private CustomKeyboardItemEntity mData;
    private OnKeyWorkListener mOnKeyWorkListener;
    private OnDpadCenterListener mOnDpadCenterListener;
    private static final String TAG = "CustomNumKeyboardItem";

    public CustomNumKeyboardItem(Context context) {
        super(context);
        load();
    }

    public CustomNumKeyboardItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        load();
    }

    public CustomNumKeyboardItem(Context context, AttributeSet attrs, int defStyleAttr) {
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
            LayoutInflater.from(getContext()).inflate(R.layout.custom_keyboard_item_num, this, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        mNumButton = (Button) findViewById(R.id.numButton);
        mLetterTextView = (TextView) findViewById(R.id.letterTextView);
    }

    private void initData() {
        mNumButton.setOnClickListener(this);
        mNumButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (mOnKeyWorkListener != null) {
                    mOnKeyWorkListener.onDpadCenter(CustomNumKeyboardItem.this, true);
                }
                return true;
            }
        });
        //初始化数据
        mData = new CustomKeyboardItemEntity("A", "B", "C", "D", "E");
        setData(mData);
    }

    public void setData(CustomKeyboardItemEntity data) {
        mData = data;
        mNumButton.setText(data.getNumText());
        mLetterTextView.setText(data.getLettersText());
    }

    @Override
    public void onClick(View view) {
        if (mOnDpadCenterListener != null) {
            mOnDpadCenterListener.onDpadCenter(getSelectedString());
            Log.e(TAG, "onClick: " + getSelectedString());
        }
        if (mOnKeyWorkListener != null) {
            mOnKeyWorkListener.onDpadCenter(CustomNumKeyboardItem.this, false);
        }
    }

    /**
     * @Description 得到选中的字符
     * @return 选中的字符
     */
    public String getSelectedString() {
        return mData.getNumText();
    }

    /**
     * @param onKeyWorkListener the mOnKeyWorkListener to set
     */
    public void setOnKeyWorkListener(final OnKeyWorkListener onKeyWorkListener) {
        this.mOnKeyWorkListener = onKeyWorkListener;
    }
    /**
     * @param onDpadCenterListener the onDpadCenterListener to set
     */
    public void setOnDpadCenterListener(final OnDpadCenterListener onDpadCenterListener) {
        this.mOnDpadCenterListener = onDpadCenterListener;
    }

    /**
     * @Description 显示
     * @param anim 是否加特效
     */
    public void appearWithAnimation(final boolean anim) {
        this.setVisibility(View.VISIBLE);
        this.requestFocus();
        if (anim) {
            this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.appear_alpha_anim));
        }
    }
    /**
     * @Description 显示
     * @param anim 是否加特效
     */
    public void disappearWithAnimation(final boolean anim) {
        if (anim) {
            this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.disappear_alpha_anim));
        }
        this.setVisibility(View.GONE);
    }
}
