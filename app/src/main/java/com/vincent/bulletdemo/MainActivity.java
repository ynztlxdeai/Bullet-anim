package com.vincent.bulletdemo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.vincent.bulletdemo.widget.CustomSurfaceView;

public class MainActivity
        extends AppCompatActivity
{

    private CustomSurfaceView mCustomSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 设置window没有标题而且全屏
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置屏幕的方向为横向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mCustomSurfaceView = new CustomSurfaceView(this);
        //setContentView(R.layout.activity_main);
        setContentView(mCustomSurfaceView);
    }
}
