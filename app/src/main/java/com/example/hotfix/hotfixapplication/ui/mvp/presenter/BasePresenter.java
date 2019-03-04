package com.example.hotfix.hotfixapplication.ui.mvp.presenter;

public class BasePresenter<V> {
    public V view;
    public void attachView(V view){
        if(view!=null){
            this.view=view;
        }
    }
}
