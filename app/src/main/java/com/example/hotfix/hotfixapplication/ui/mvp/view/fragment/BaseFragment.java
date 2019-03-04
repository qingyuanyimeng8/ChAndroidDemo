package com.example.hotfix.hotfixapplication.ui.mvp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

public class BaseFragment extends Fragment {

    public Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void intent(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //添加fragment
    protected void addReplaceFragment(BaseFragment fragment, int rId, Bundle bundle, boolean addToBackStack) {
        if (fragment != null) {
            if(bundle!=null){
                fragment.setArguments(bundle);
            }
            if (addToBackStack) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(rId, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
            } else {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(rId, fragment, fragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
            }
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }


    protected void removeAllFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public void runDelay(Runnable runnable, long delay) {
        new Handler().postDelayed(runnable, delay);
    }

    public void hideSoftInput(IBinder windowToken) {
        if (windowToken != null) {
            InputMethodManager inputManger = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(windowToken, 0);
        }
    }


}
