package com.example.hotfix.hotfixapplication.ui.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotfix.hotfixapplication.R;
import com.example.hotfix.hotfixapplication.ui.mvp.view.MVPActivity;

import java.util.ArrayList;
import java.util.List;

public class BlankFragmentOne extends BaseFragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("BlankFragmentOne","-----setUserVisibleHint"+isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("BlankFragmentOne","-----onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BlankFragmentOne","-----onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test3, container, false);
        ViewPager viewPager=(ViewPager) view.findViewById(R.id.viewpager);
        final List<BaseFragment> list=new ArrayList<>();
        list.add(new BlankFragmentTwo());
        list.add(new BlankFragmentTwo());
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("BlankFragmentOne","-----onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("BlankFragmentOne","-----onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("BlankFragmentOne","-----onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("BlankFragmentOne","-----onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("BlankFragmentOne","-----onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("BlankFragmentOne","-----onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BlankFragmentOne","-----onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("BlankFragmentOne","-----onDetach");
    }


}
