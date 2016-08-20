package com.bbk.open.interpolator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout animLayout;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animLayout = (LinearLayout) findViewById(R.id.anim_layout);

       findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_linear_interpolator));
            }
        });

        findViewById(R.id.btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_accelerate_interpolator));
            }
        });

        findViewById(R.id.btn_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_decelerate_interpolator));
            }
        });

        findViewById(R.id.btn_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_accelerate_decelerate_interpolator));
            }
        });

        findViewById(R.id.btn_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_anticipate_interpolator));
            }
        });

        findViewById(R.id.btn_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_overshoot_interpolator));
            }
        });

        findViewById(R.id.btn_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_anticipate_overshoot_interpolator));
            }
        });

        findViewById(R.id.btn_eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_bounce_interpolator));
            }
        });

        findViewById(R.id.btn_nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animLayout.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_cycle_interpolator));
            }
        });

        findViewById(R.id.btn_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 1000.0f);
                translateAnimation.setInterpolator(new HesitateInterPolator());
                translateAnimation.setDuration(3000);
                animLayout.startAnimation(translateAnimation);
            }
        });
    }
}
