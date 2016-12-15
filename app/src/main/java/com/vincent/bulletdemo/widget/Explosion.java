package com.vincent.bulletdemo.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * projectName: 	    BulletDemo
 * packageName:	        com.vincent.bulletdemo.widget
 * className:	        Explosion
 * author:	            Luoxiang
 * time:	            2016/12/15	11:05
 * desc:	            爆炸对象的引用
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/15
 * upDateDesc:	        TODO
 */
public class Explosion {
    CustomSurfaceView mCustomSurfaceView;
    Bitmap[] mBitmaps;
    float mX;
    float mY;
    //爆炸的动画指针
    int mAnmiIndex;
    public Explosion(CustomSurfaceView customSurfaceView, Bitmap[] explodeBmps, float x, float y) {
        mCustomSurfaceView = customSurfaceView;
        mBitmaps = explodeBmps;
        mX = x;
        mY = y;
    }

    public void drawSelf(Canvas canvas, Paint paint) {
        if (mAnmiIndex >= mBitmaps.length - 1){
            return;
        }
        canvas.drawBitmap(mBitmaps[mAnmiIndex++] , mX , mY , paint);
    }
}
