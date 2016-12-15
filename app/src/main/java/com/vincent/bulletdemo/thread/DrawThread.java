package com.vincent.bulletdemo.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.vincent.bulletdemo.widget.CustomSurfaceView;

/**
 * projectName: 	    BulletDemo
 * packageName:	        com.vincent.bulletdemo.thread
 * className:	        DrawThread
 * author:	            Luoxiang
 * time:	            2016/12/15	9:57
 * desc:	            绘制的线程类
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2016/12/15
 * upDateDesc:	        TODO
 */
public class DrawThread extends Thread{

    private final SurfaceHolder mSurfaceHolder;
    //工作标记位
    private boolean mFlag = true;
    CustomSurfaceView mCustomSurfaceView;
    //线程休眠的时间
    private int mSleepTime = 100;

    public DrawThread(CustomSurfaceView customSurfaceView) {
        mCustomSurfaceView = customSurfaceView;
        mSurfaceHolder = customSurfaceView.getHolder();

    }

    @Override
    public void run() {
        super.run();
        Canvas canvas;
        while (mFlag){
            canvas = null;
            //锁定画布
            try {
                canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder){
                    //绘制每一帧
                    mCustomSurfaceView.onDraw(canvas);
                }
            } finally {
                if (canvas != null){
                    //释放锁
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            try {
                //睡眠一会儿
                Thread.sleep(mSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {
        mFlag = flag;
    }
}
