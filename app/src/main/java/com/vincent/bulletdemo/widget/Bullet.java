package com.vincent.bulletdemo.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.vincent.bulletdemo.Constant;

/**
 * projectName: 	    BulletDemo
 * packageName:	        com.vincent.bulletdemo.widget
 * className:	        Bullet
 * author:	            Luoxiang
 * time:	            2016/12/15	9:58
 * desc:	            炮弹类
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/15
 * upDateDesc:	        TODO
 */

public class Bullet {
    CustomSurfaceView mCustomSurfaceView;
    //位图
    private Bitmap   mBulletBitmap;
    //爆炸动画图组
    private Bitmap[] mExplodeBmps;
    //X轴位置
    float mX;
    float mY;
    //X轴速度
    float mVx;
    float mVy;
    //生存时间
    private float mLiveTime;
    //时间间隔
    private float mSpanTime = 0.5f;
    //炮弹尺寸
    private int mBulletSize;
    //是否绘制炮弹标记位
    private boolean isExplosion;
    //爆炸对象的引用
    private Explosion mExplosion;


    public Bullet(CustomSurfaceView customSurfaceView,
                  Bitmap bulletBitmap,
                  Bitmap[] explodeBmps,
                  float x,
                  float y,
                  float vx,
                  float vy)
    {
        mCustomSurfaceView = customSurfaceView;
        mBulletBitmap = bulletBitmap;
        mExplodeBmps = explodeBmps;
        mX = x;
        mY = y;
        mVx = vx;
        mVy = vy;
        mBulletSize = bulletBitmap.getHeight();
    }

    /**
     * 绘制自己
     * @param canvas 画板
     * @param paint 画笔
     */
    public void drawSelf(Canvas canvas, Paint paint) {
        if (isExplosion && mExplosion != null){
            mExplosion.drawSelf(canvas , paint);
        }else {
            go();
            canvas.drawBitmap(mBulletBitmap , mX , mY , paint);
        }

    }

    /**
     * 绘制炮弹前进的方法
     */
    private void go() {
        //在水平方向上做匀速运动
        mX += mVx * mLiveTime;
        //在竖直方向上做上抛运动
        mY += mVy * mLiveTime + 0.5f + Constant.G * mLiveTime * mLiveTime;
        //爆炸点
        if (mX >= Constant.EXPLOSION_X || mY >= Constant.SCREEN_HEIGHT){
            mExplosion = new Explosion(mCustomSurfaceView , mExplodeBmps , mX , mY);
            //不在绘制炮弹
            isExplosion = true;
            return;
        }
        //更新生存时间
        mLiveTime += mSpanTime;
    }
}
