package com.bbk.open.androidkeyboard.enity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.bbk.open.androidkeyboard.listener.OnDpadCenterListener;
import com.bbk.open.androidkeyboard.R;

/**
 * Created by Administrator on 2016/8/16.
 */
public class CustomKeyboard extends RelativeLayout {
    private static final String TAG = "CustomKeyboard";
    private CustomNumKeyboardItem mItem1;
    private CustomKeyboardItem mItem2;
    private CustomKeyboardItem mItem3;
    private CustomKeyboardItem mItem4;
    private CustomKeyboardItem mItem5;
    private CustomKeyboardItem mItem6;
    private CustomKeyboardItem mItem7;
    private CustomKeyboardItem mItem8;
    private CustomKeyboardItem mItem9;

    private OnDpadCenterListener mOnDpadCenterListener;
    private Button mItem0;
    private ImageButton mItemClear;
    private ImageButton mItemDelete;

    public CustomKeyboard(Context context) {
        super(context);
        load();
    }

    public CustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        load();
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
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
            LayoutInflater.from(getContext()).inflate(R.layout.custom_keyboard_percent, this, true);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "load: isInEditMode" + e.toString());
        }
    }

    private void initUI() {
        mItem1 = (CustomNumKeyboardItem) findViewById(R.id.item1);
        mItem2 = (CustomKeyboardItem) findViewById(R.id.item2);
        mItem3 = (CustomKeyboardItem) findViewById(R.id.item3);
        mItem4 = (CustomKeyboardItem) findViewById(R.id.item4);
        mItem5 = (CustomKeyboardItem) findViewById(R.id.item5);
        mItem6 = (CustomKeyboardItem) findViewById(R.id.item6);
        mItem7 = (CustomKeyboardItem) findViewById(R.id.item7);
        mItem8 = (CustomKeyboardItem) findViewById(R.id.item8);
        mItem9 = (CustomKeyboardItem) findViewById(R.id.item9);
        mItem0 = (Button) findViewById(R.id.item0);
        mItemClear = (ImageButton) findViewById(R.id.itemClear);
        mItemDelete = (ImageButton) findViewById(R.id.itemDelete);
    }

    private void initData() {
        String[] dataItem1 = getResources().getStringArray(R.array.item1);
        String[] dataItem2 = getResources().getStringArray(R.array.item2);
        String[] dataItem3 = getResources().getStringArray(R.array.item3);
        String[] dataItem4 = getResources().getStringArray(R.array.item4);
        String[] dataItem5 = getResources().getStringArray(R.array.item5);
        String[] dataItem6 = getResources().getStringArray(R.array.item6);
        String[] dataItem7 = getResources().getStringArray(R.array.item7);
        String[] dataItem8 = getResources().getStringArray(R.array.item8);
        String[] dataItem9 = getResources().getStringArray(R.array.item9);
        mItem1 = (CustomNumKeyboardItem) findViewById(R.id.item1);
        CustomKeyboardItemEntity cEntity = new CustomKeyboardItemEntity(
                dataItem1[0], "", "", "");
        mItem1.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem2[0], dataItem2[1], dataItem2[2], dataItem2[3]);
        mItem2.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem3[0], dataItem3[1], dataItem3[2], dataItem3[3]);
        mItem3.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem4[0], dataItem4[1], dataItem4[2], dataItem4[3]);
        mItem4.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem5[0], dataItem5[1], dataItem5[2], dataItem5[3]);
        mItem5.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem6[0], dataItem6[1], dataItem6[2], dataItem6[3]);
        mItem6.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem7[0], dataItem7[1], dataItem7[2], dataItem7[3], dataItem7[4]);
        mItem7.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem8[0], dataItem8[1], dataItem8[2], dataItem8[3]);
        mItem8.setData(cEntity);
        cEntity = new CustomKeyboardItemEntity(
                dataItem9[0], dataItem9[1], dataItem9[2], dataItem9[3], dataItem9[4]);
        mItem9.setData(cEntity);
    }

    /**
     * @return the onDpadCenterListener
     */
    public OnDpadCenterListener getOnDpadCenterListener() {
        return mOnDpadCenterListener;
    }
    /**
     * @param onDpadCenterListener the onDpadCenterListener to set
     */
    public void setOnDpadCenterListener(final OnDpadCenterListener onDpadCenterListener) {
        this.mOnDpadCenterListener = onDpadCenterListener;
        mItem1.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem2.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem3.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem4.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem5.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem6.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem7.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem8.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem9.setOnDpadCenterListener(mOnDpadCenterListener);
        mItem0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != mOnDpadCenterListener) {
                    mOnDpadCenterListener.onDpadCenter(mItem0.getText().toString());
                }
                Log.e(TAG, "onClick: 0");
            }
        });
        mItemClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != mOnDpadCenterListener) {
                    mOnDpadCenterListener.onClearPressed();
                }
            }
        });
        mItemDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnDpadCenterListener) {
                    mOnDpadCenterListener.onDeletePressed();
                }
            }
        });
    }

}
