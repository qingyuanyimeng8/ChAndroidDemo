package com.example.hotfix.hotfixapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;


import com.gelitenight.waveview.library.WaveView;

import java.util.ArrayList;
import java.util.List;

public class WaveHelper {
    private WaveView mWaveView;

    private AnimatorSet mAnimatorSet;
    List<Animator> animators;

    public WaveHelper(WaveView waveView, float percent, boolean isShowWaveGrowRepeatedly) {
        mWaveView = waveView;
        initAnimation(percent,isShowWaveGrowRepeatedly);
    }

    public void start() {
        mWaveView.setShowWave(true);
        if (mAnimatorSet != null) {
            mAnimatorSet.start();
        }
    }

    private void initAnimation(float percent,boolean isShowWaveGrowRepeatedly) {
        animators = new ArrayList<>();

        // horizontal animation.
        // wave waves infinitely.
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                mWaveView, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);

        // vertical animation.
        // water level increases from 0 to center of WaveView
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                mWaveView, "waterLevelRatio", percent, percent);
        waterLevelAnim.setDuration(10000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);

        setWaveGrowRepeatedly(isShowWaveGrowRepeatedly,2000);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animators);
    }

    //从平静到水波重复
    public void setWaveGrowRepeatedly(boolean flag,long time){
        if(flag){
            ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                    mWaveView, "amplitudeRatio", 0.0001f, 0.05f);
            amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
            amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
            amplitudeAnim.setDuration(time);
            amplitudeAnim.setInterpolator(new LinearInterpolator());
            animators.add(amplitudeAnim);
        }
    }

    public void cancel() {
        if (mAnimatorSet != null) {
//            mAnimatorSet.cancel();
            mAnimatorSet.end();
        }
    }
}
