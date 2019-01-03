package com.example.hotfix.hotfixapplication.ServiceTest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class ServiceDemo extends Service {
    public static final String TAG = "ServiceDemo";
//    @Nullable
////    @Override
////    public IBinder onBind(Intent intent) {
////        Log.i(TAG,"call onBind...");
////        return null;
////    }
////
////    @Override
////    public void onCreate() {
////        Log.i(TAG,"call onCreate...");
////    }
////
////    @Override
////    public void onStart(Intent intent, int startId) {
////        Log.i(TAG,"call onStart...");
////    }
////
////    @Override
////    public int onStartCommand(Intent intent, int flags, int startId) {
////        Log.i(TAG,"call onStartCommand...");
////        return super.onStartCommand(intent, flags, startId);
////    }
////
////    @Override
////    public void onDestroy() {
////        Log.i(TAG,"call onDestroy...");
////    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"call onBind...");
        //返回IBinder 接口对象
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"call onUnbind...");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"call onCreate...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG,"call onStart...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"call onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"call onDestroy...");
    }

    // 添加一个类继承Binder
    public  class MyBinder extends Binder{
        // 添加要与外界交互的方法
        public String  getStringInfo(){
            return "调用了服务中的方法";
        }
    }

}