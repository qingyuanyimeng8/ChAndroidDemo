package com.example.hotfix.hotfixapplication.ServiceTest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

public class IntentServiceDemo extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public static final String TAG ="IntentServiceDemo";
    public IntentServiceDemo() {
        super("IntentServiceDemo");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        boolean isMainThread =  Thread.currentThread() == Looper.getMainLooper().getThread();
        Log.i(TAG,"is main thread:"+isMainThread);

        // 执行耗时下载操作
        mockDownload();
    }
    private void mockDownload(){
        try {
            Thread.sleep(5000);
            Log.i(TAG,"下载完成...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
