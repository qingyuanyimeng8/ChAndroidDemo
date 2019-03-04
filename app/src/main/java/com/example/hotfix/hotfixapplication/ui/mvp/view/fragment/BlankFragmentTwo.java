package com.example.hotfix.hotfixapplication.ui.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotfix.hotfixapplication.R;

public class BlankFragmentTwo extends BaseFragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("BlankFragmentTwo","-----setUserVisibleHint"+isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("BlankFragmentTwo","-----onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BlankFragmentTwo","-----onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test2, container, false);
        Log.e("BlankFragmentTwo","-----onCreateView");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("url","http://192.168.20.7/JSTest/html/baiduTest.html");
                addReplaceFragment(new MyWebView(),R.id.fl_container,bundle,true);            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("BlankFragmentTwo","-----onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("BlankFragmentTwo","-----onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("BlankFragmentTwo","-----onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("BlankFragmentTwo","-----onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("BlankFragmentTwo","-----onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("BlankFragmentTwo","-----onDetach");
    }

}
