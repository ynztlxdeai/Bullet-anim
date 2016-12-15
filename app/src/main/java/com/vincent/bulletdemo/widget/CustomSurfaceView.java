package com.vincent.bulletdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.vincent.bulletdemo.R;
import com.vincent.bulletdemo.thread.DrawThread;

/**
 * projectName: 	    BulletDemo
 * packageName:	        com.vincent.bulletdemo.widget
 * className:	        CustomSurfaceView
 * author:	            Luoxiang
 * time:	            2016/12/15	9:41
 * desc:	            SurfaceView类 用于显示炮弹的动画
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/15
 * upDateDesc:	        TODO
 */
public class CustomSurfaceView
        extends SurfaceView
        implements SurfaceHolder.Callback
{

    Context    mContext;
    Paint      mPaint;
    DrawThread mDrawThread;
    Bitmap     mBgBitmap;
    Bitmap     mBulletBitmap;
    Bitmap[]   mExplodeBmps;
    Bullet     mBullet;

    public CustomSurfaceView(Context context) {
        this(context, null);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        //获取焦点
        requestFocus();
        //设置可以接收触摸事件
        setFocusableInTouchMode(true);
        //注册回调接口
        getHolder().addCallback(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 首先绘制自己的背景
         * 然后绘制炮弹自己
         */
        canvas.drawBitmap(mBgBitmap , 0 , 0 , mPaint);
        mBullet.drawSelf(canvas , mPaint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //加载炮弹图片
        mBulletBitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.bullet);
        mBgBitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.bg);
        mExplodeBmps = new Bitmap[]{
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode0) ,
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode1) ,
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode2) ,
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode3) ,
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode4) ,
                BitmapFactory.decodeResource(getResources() , R.mipmap.explode5)
        };
        //创建炮弹对象
        mBullet = new Bullet(this , mBulletBitmap , mExplodeBmps , 200 , 290 , 1.3f , -5.9f );
        mDrawThread  = new DrawThread(this);
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawThread.setFlag(false);
    }
}
