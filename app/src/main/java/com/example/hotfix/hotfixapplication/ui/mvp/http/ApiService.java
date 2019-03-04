package com.example.hotfix.hotfixapplication.ui.mvp.http;

import com.example.hotfix.hotfixapplication.ui.mvp.bean.NewsInfo;

import java.util.List;
import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("wap/data/news/txs/page_{page}.json")
    Observable<List<NewsInfo>> getNews(@Path("page") int page);
}
