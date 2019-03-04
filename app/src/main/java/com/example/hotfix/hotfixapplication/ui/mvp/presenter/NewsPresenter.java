package com.example.hotfix.hotfixapplication.ui.mvp.presenter;

import com.example.hotfix.hotfixapplication.ui.mvp.bean.NewsInfo;
import com.example.hotfix.hotfixapplication.ui.mvp.model.NewsModel;
import com.example.hotfix.hotfixapplication.ui.mvp.view.DataCallback;

public class NewsPresenter extends BasePresenter{
    DataCallback callback;
    NewsModel newsModel;

    public NewsPresenter(DataCallback callback) {
        this.callback=callback;
//        super.attachView(activity);
        newsModel=new NewsModel();

    }

    public void getNewsData(int page){
        if(newsModel!=null){
            newsModel.getData(page);
            newsModel.newcCallback(new NewsModel.NewsCallback() {
                @Override
                public void onSuccess(NewsInfo newsInfo) {
                    if(callback!=null){
                        callback.success(newsInfo);
                    }
                }

            });
        }

    }
}
