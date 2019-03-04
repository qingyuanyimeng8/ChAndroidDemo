package com.example.hotfix.hotfixapplication.ui.mvp.model;

import android.util.Log;

import com.example.hotfix.hotfixapplication.ui.mvp.bean.NewsInfo;
import com.example.hotfix.hotfixapplication.ui.mvp.http.Api;
import com.example.hotfix.hotfixapplication.ui.mvp.http.ApiHelper;
import com.example.hotfix.hotfixapplication.ui.mvp.http.ApiService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NewsModel {
    NewsCallback newCallback;
    public interface NewsCallback{

        void onSuccess(NewsInfo newsInfo);
    }

    public void newcCallback(NewsCallback newCallback){
        this.newCallback =newCallback;
    }

    public void getData(int page){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(ApiHelper.creatOkHttpClient())
                .build();
        ApiService apiService=retrofit.create(ApiService.class);
        Observable<List<NewsInfo>> observable = apiService.getNews(page);
        observable.subscribeOn(Schedulers.io()).flatMap(new Func1<List<NewsInfo>, Observable<NewsInfo>>() {
            @Override
            public Observable<NewsInfo> call(List<NewsInfo> newsInfos) {
                return Observable.from(newsInfos);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NewsInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewsInfo newsInfo) {
                List<NewsInfo.DataBean> data = newsInfo.getData();
                String title = data.get(0).getTitle();
                Log.i("xxx", title);
                if (newCallback != null) {
                    newCallback.onSuccess(newsInfo);
                }
            }
        });


    }
}
