package com.example.hotfix.hotfixapplication.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotfix.hotfixapplication.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class HBRefreshHeader extends RelativeLayout implements RefreshHeader {

    private TextView tv_hint;
    private ImageView iv_indicator;
    private HBProgressDrawable mProgressDrawable;

    public HBRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public HBRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HBRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.v3_refresh_header, this, true);
        tv_hint = (TextView) findViewById(R.id.tv_hint);
        iv_indicator = (ImageView) findViewById(R.id.iv_indicator);
        mProgressDrawable = new HBProgressDrawable(getContext());
        iv_indicator.setImageDrawable(mProgressDrawable);
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        mProgressDrawable.setPercent(percent);
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
        mProgressDrawable.setPercent(percent);
    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {
        mProgressDrawable.start();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        mProgressDrawable.stop();
        if (success) {
            tv_hint.setText("刷新完成");
        } else {
            tv_hint.setText("刷新失败");
        }
        return 200;//延迟200毫秒之后再弹回
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                tv_hint.setText("下拉可以刷新");
                break;
            case Refreshing:
                tv_hint.setText("正在刷新...");
                break;
            case ReleaseToRefresh:
                tv_hint.setText("松开立即刷新");
                break;
        }
    }


}
