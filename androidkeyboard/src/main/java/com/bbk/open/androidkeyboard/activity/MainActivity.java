package com.bbk.open.androidkeyboard.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.bbk.open.androidkeyboard.listener.OnDpadCenterListener;
import com.bbk.open.androidkeyboard.R;
import com.bbk.open.androidkeyboard.enity.CustomKeyboard;
import com.bbk.open.androidkeyboard.listener.OnFinshListener;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private CustomKeyboard customKeyBoardItemView;
    private static final String TAG = "MainActivity";

    private EditText editText;
    private Button button;

    private String tempContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        customKeyBoardItemView = (CustomKeyboard) findViewById(R.id.custom_keyboard);
        customKeyBoardItemView.setOnDpadCenterListener(new OnDpadCenterListener() {

            @Override
            public void onDpadCenter(String selectString) {
                Log.e(TAG, "onDpadCenter: " + selectString );
                StringBuilder sb = new StringBuilder(editText.getText().toString().trim());
                if (!TextUtils.isEmpty(selectString)) {
                    for (int i = 0; i < selectString.length(); i++) {
                          sb.append(selectString.charAt(i));
                    }
                }
                editText.setText(sb.toString());
                mOnFinishListener.onFinish();
            }

            @Override
            public void onClearPressed() {
                Log.e(TAG, "onClearPressed: "  );
            }

            @Override
            public void onDeletePressed() {
                tempContent = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(tempContent)) {
                    editText.setText(tempContent.substring(0, tempContent.length() - 1).toString());
                }
            }
        });
        customKeyBoardItemView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_bounce_interpolator));
        editText = (EditText) findViewById(R.id.edt);
        showKeyBoardView();
    }

    private void showKeyBoardView() {
        int inputType = editText.getInputType();
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        editText.setInputType(inputType);
    }

    private OnFinshListener mOnFinishListener;

    public void setOnFinishListener(OnFinshListener onFinishListener) {
        mOnFinishListener = onFinishListener;
    }
}
