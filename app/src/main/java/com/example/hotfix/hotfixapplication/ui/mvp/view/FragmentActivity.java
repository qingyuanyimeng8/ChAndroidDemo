package com.example.hotfix.hotfixapplication.ui.mvp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hotfix.hotfixapplication.R;
import com.example.hotfix.hotfixapplication.ui.mvp.view.fragment.BaseFragment;
import com.example.hotfix.hotfixapplication.ui.mvp.view.fragment.BlankFragmentOne;
import com.example.hotfix.hotfixapplication.ui.mvp.view.fragment.BlankFragmentTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentActivity extends AppCompatActivity {

    @BindView(R.id.bt_change)
    Button bt_change;
    @BindView(R.id.bt_jump)
    Button bt_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        replaceFragment(new BlankFragmentTwo());
        Log.e("FragmentActivity","--------------onCreate");

    }

    @OnClick({R.id.bt_change,R.id.bt_jump})
    public void onClick(final View v){
        switch (v.getId()){
            case R.id.bt_change:
                replaceFragment(new BlankFragmentOne());
                break;
            case R.id.bt_jump:
                replaceFragment(new BlankFragmentTwo());
                break;
        }
    }

    public void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container, fragment);
        transaction.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("FragmentActivity","--------------onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FragmentActivity","--------------onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("FragmentActivity","--------------onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("FragmentActivity","--------------onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("FragmentActivity","--------------onDestroy");
    }


}
