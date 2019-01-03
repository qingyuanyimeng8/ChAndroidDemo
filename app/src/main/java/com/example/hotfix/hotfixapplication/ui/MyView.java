package com.example.hotfix.hotfixapplication.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.hotfix.hotfixapplication.R;

@SuppressLint("AppCompatCustomView")
public class MyView extends TextView {
    private static final String TAG = "MyView";
    private int height;
    private int width;
    private String name;
    private int sex;
    private boolean student;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.burce);
        height=array.getInt(R.styleable.burce_mHeight,0);
        width=array.getInt(R.styleable.burce_mWidth,0);
        name=array.getString(R.styleable.burce_mName);
        sex=array.getInt(R.styleable.burce_sex,0);
        student=array.getBoolean(R.styleable.burce_student,true);
        array.recycle();

        Log.e(TAG, "height: "+height);
        Log.e(TAG, "width: "+width);
        Log.e(TAG, "name: "+name);
        Log.e(TAG, "sex: "+sex);
        Log.e(TAG, "student: "+student);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
