package com.example.hotfix.hotfixapplication.ui.mvp.http;

import android.os.Debug;
import android.os.Environment;
import android.util.Config;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiHelper {
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    public static final String CONTENT_TYPE = "application/json";
    public static final String CONTENT_SEND_PICTURE_TYPE = "multipart/form-data";
    public static final String CONNECTION = "keep-alive";
    public static final String ACCEPT = "*/*";
    public static String JSESSIONID = "";
    public static final String USER_AGENT = "android";
    public static final int CONNECT_TIME_OUT_NORMAL = 30 * 1000;
    public static final int READ_TIME_OUT_NORMAL = 30 * 1000;

    // 3.0新增
    // 设备唯一标识
//    public static final String deviceID = V3Utils2OS.getAndroidId() + V3Utils2OS.getWifiMacAddress();
//    // 设备名称，如：iPhone8 or xiaomi
//    public static final String model = V3Utils2OS.getModel();
//    // 手机系统版本，如：iOS11.3 or Android7.0
//    public static final String osVersion = V3Utils2OS.getOsVersion();
//    // 渠道，如：App Store or 应用宝
//    public static final String channel = ConfigUntil.getMetaData(WalletApplication.app);
//    public static final String packageName = WalletApplication.app.getPackageName();
    public static OkHttpClient creatOkHttpClient(){
        OkHttpClient.Builder okHttpBuilder=new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT_NORMAL, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME_OUT_NORMAL,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false);
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original=chain.request();
                String oldUrl=original.url().url().toString();
                String url=oldUrl.replaceFirst("%3F","?");
                Request request=original.newBuilder()
                        .header("Content-Type",CONTENT_TYPE)
                        .header("Accept", ACCEPT)
                        .header("User-Agent", USER_AGENT)
                        .header("Cookie", JSESSIONID)
                        .header("f_device_imei", "2834032840")
                        .addHeader("Cache-Control", "max-stale=10")
                        .url(url).build();
                Response response=chain.proceed(request);
                if(response.code() == HttpURLConnection.HTTP_OK){
                    String cookieValue=response.header("Set-Cookie");
                    if(cookieValue !=null && cookieValue.contains("JSESSIONID")){
                        JSESSIONID = cookieValue.substring(0, cookieValue.indexOf(";"));
                    }
                }
                return response;
            }
        });

        if(Config.DEBUG){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("xxx", message.toString());
                }
            });
            //Okhttp3的拦截器日志分类 4种
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(httpLoggingInterceptor);
        }

        File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
        okHttpBuilder.cache(new Cache(sdcache.getAbsoluteFile(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE));

        return okHttpBuilder.build();
    }
}
