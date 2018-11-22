package com.example.hotfix.hotfixapplication.recycler;

/**
 * Created by bici on 15/7/13.
 */
public interface LoadMore {

    public void checkLoadMore(int position);

    public interface OnLoadEndListener {
        public void onLoadEnd();
    }

    public void setOnLoadEndListener(OnLoadEndListener onLoadEndListener);
}
