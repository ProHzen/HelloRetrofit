package com.bbk.open.androidkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private CustomKeyboard customKeyBoardItemView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customKeyBoardItemView = (CustomKeyboard) findViewById(R.id.custom_keyboard);
        customKeyBoardItemView.setOnDpadCenterListener(new OnDpadCenterListener() {

            @Override
            public void onDpadCenter(String selectString) {
                Log.e(TAG, "onDpadCenter: " + selectString );
            }

            @Override
            public void onClearPressed() {
                Log.e(TAG, "onClearPressed: "  );
            }

            @Override
            public void onDeletePressed() {
                Log.e(TAG, "onDeletePressed: "  );
            }
        });
    }
}
