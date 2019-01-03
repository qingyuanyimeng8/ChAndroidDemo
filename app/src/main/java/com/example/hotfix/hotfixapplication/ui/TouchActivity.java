package com.example.hotfix.hotfixapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.hotfix.hotfixapplication.R;

public class TouchActivity extends Activity implements View.OnLongClickListener,View.OnClickListener,View.OnTouchListener{
    ImageView iv_1,iv_2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        iv_1=(ImageView) findViewById(R.id.iv_1);
        iv_2=(ImageView) findViewById(R.id.iv_2);
        iv_1.setOnClickListener(this);
        iv_2.setOnClickListener(this);

        /**
         *
         */
//        iv_1.setOnLongClickListener(this);
//        iv_2.setOnLongClickListener(this);
        iv_1.setOnTouchListener(this);
        iv_2.setOnTouchListener(this);

        iv_2.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_1:
                Log.e("onClick---------","iv_1");
                break;
            case R.id.iv_2:
                Log.e("onClick---------","iv_2");
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.iv_1:
                Log.e("onLongClick---------","iv_1");
                break;
            case R.id.iv_2:
                Log.e("onLongClick---------","iv_2");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.iv_1:
                Log.e("MotionEvent---------","iv_1-------"+event.getAction()+"");
                break;
            case R.id.iv_2:
                Log.e("MotionEvent---------","iv_2-------"+event.getAction()+"");
                break;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
