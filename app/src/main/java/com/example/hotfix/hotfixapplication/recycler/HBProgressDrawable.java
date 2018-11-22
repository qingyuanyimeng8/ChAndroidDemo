package com.example.hotfix.hotfixapplication.recycler;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

import com.example.hotfix.hotfixapplication.R;

public class HBProgressDrawable extends Drawable implements Animatable {


    private static final int STATE_DRAG = 0;
    private static final int STATE_PROGRESS = 1;

    private Context mContext;

    private Bitmap indicatorBitmap;
    private Bitmap progressBitmap;
    private Paint mPaint;


    private int mState;
    private float mPercent;
    private int mAngle;

    private ValueAnimator mValueAnimator;
    private int duration = 800;


    public HBProgressDrawable(Context context) {
        this.mContext = context;
        BitmapFactory.Options options = new BitmapFactory.Options();

        indicatorBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.indicator);
        progressBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.xsearch_loading);

        mState = STATE_DRAG;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mValueAnimator = ValueAnimator.ofInt(0, 359);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mAngle = value;
                invalidateSelf();
            }
        });
        mValueAnimator.setDuration(duration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
    }


    @Override
    public void start() {
        mState = STATE_PROGRESS;
        mValueAnimator.start();
    }

    @Override
    public void stop() {
        mState = STATE_DRAG;
        mValueAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        if (mState == STATE_DRAG) {
            canvas.rotate(mPercent * 4 * 360, canvas.getWidth() / 2, canvas.getHeight() / 2);
            canvas.drawBitmap(indicatorBitmap, (canvas.getWidth() - indicatorBitmap.getWidth()) / 2,
                    (canvas.getHeight() - indicatorBitmap.getHeight()) / 2, mPaint);
        } else {
            canvas.rotate(mAngle, canvas.getWidth() / 2, canvas.getHeight() / 2);
            canvas.drawBitmap(progressBitmap, (canvas.getWidth() - progressBitmap.getWidth()) / 2,
                    (canvas.getHeight() - progressBitmap.getHeight()) / 2, mPaint);
        }

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setPercent(float percent) {
        mPercent = percent;
        invalidateSelf();
    }
}
