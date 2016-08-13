package com.bbk.open.androidkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CustomCircleKeyboardItem customcircleKeyBoardItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customcircleKeyBoardItemView = (CustomCircleKeyboardItem) findViewById(R.id.custom_circle_keyboard_item);
        CustomKeyboardItemEntity data = new CustomKeyboardItemEntity("W", "X", "Y", "Z", "9");
        customcircleKeyBoardItemView.setData(data);
    }
}
