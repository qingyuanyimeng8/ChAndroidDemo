package com.example.hotfix.hotfixapplication;

import android.app.Application;
import android.content.Context;

import com.example.hotfix.hotfixapplication.recycler.HBRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.taobao.sophix.SophixManager;

public class MyApplication extends Application {
    /**======================= 定义全局 refresh commit_succes_layout 的 header 和 footer ===============================*/
    //static 代码段可以防止内存泄露

    /**
     * ======================= 定义全局 refresh commit_succes_layout 的 header 和 footer ===============================
     */

    @Override
    public void onCreate() {
        super.onCreate();
        //热修复
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
