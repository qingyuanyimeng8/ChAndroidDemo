package com.example.hotfix.hotfixapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotfix.hotfixapplication.ServiceTest.IntentServiceDemo;
import com.example.hotfix.hotfixapplication.ServiceTest.ServiceDemo;
import com.example.hotfix.hotfixapplication.permission.PermissionHelper;
import com.example.hotfix.hotfixapplication.recycler.BaseAdapterHelper;
import com.example.hotfix.hotfixapplication.recycler.FootRecyclerAdapter;
import com.example.hotfix.hotfixapplication.recycler.HBRefreshHeader;
import com.example.hotfix.hotfixapplication.recycler.MultiItemTypeSupport;
import com.example.hotfix.hotfixapplication.recycler.RecycleAdapter;
import com.example.hotfix.hotfixapplication.ui.GaodeActivity;
import com.example.hotfix.hotfixapplication.ui.MyView;
import com.example.hotfix.hotfixapplication.ui.TouchActivity;
import com.gelitenight.waveview.library.WaveView;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    WaveView waveView;
    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private SmartRefreshLayout srl;
    private RecyclerView rv;
    ServiceDemo.MyBinder myBinder;
    String imgUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545716426939&di=d0311f79bdb0ccf9bcf02a93b346a853&imgtype=0&src=http%3A%2F%2Fimg1.ph.126.net%2FnKj_uu4TxPBIafCwZTrILw%3D%3D%2F6598172576880820486.jpg";
    RecycleAdapter<ListBean> recyclerAdapter = new RecycleAdapter<ListBean>(MainActivity.this, new MultiItemTypeSupport<ListBean>() {
        @Override
        public int getLayoutId(int viewType) {
            if (viewType == 1) {
                return R.layout.view_custom_component;
            }
            return R.layout.item_layout;
        }

        @Override
        public int getItemViewType(int position, ListBean listBean) {
            if (position == 2) {
                return 1;
            }
            return 0;
        }
    }) {

        @Override
        protected void convert(BaseAdapterHelper helper, final ListBean item, final int position) {
            if(position==2){
                MyView myView=(MyView)helper.itemView.findViewById(R.id.my_view);
                myView.setText("自定义控件");
            }else{
                helper.getTextView(R.id.tv_name).setText(item.getName());
                switch (position) {
                    case 0:
                        helper.getTextView(R.id.tv_name).setText("高德地图");
                        break;
                    case 1:
                        helper.getTextView(R.id.tv_name).setText("安卓View的setEnabled和setClickable的思考");
                        break;
                    case 3:
                        helper.getTextView(R.id.tv_name).setText("startService");
                        break;
                    case 4:
                        helper.getTextView(R.id.tv_name).setText("IntentService");
                        break;

                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                //高德定位
                                intentActivity(GaodeActivity.class);
                                break;
                            case 1:
                                //安卓View的setEnabled和setClickable的思考
                                intentActivity(TouchActivity.class);
                                break;
                            case 3://startService
                                Intent intent = new Intent(MainActivity.this,ServiceDemo.class);
//                                // 启动服务
//                                startService(intent);
                                bindService(intent, new ServiceConnection() {
                                    @Override
                                    public void onServiceConnected(ComponentName name, IBinder service) {
                                        myBinder=(ServiceDemo.MyBinder) service;
                                        Toast makeText = Toast.makeText(context, null, Toast.LENGTH_LONG);
                                        makeText.setText(myBinder.getStringInfo());
                                        makeText.show();
                                    }

                                    @Override
                                    public void onServiceDisconnected(ComponentName name) {
                                        myBinder=null;
                                    }
                                },Context.BIND_AUTO_CREATE);
                                break;
                            case 4:
                                Intent intent1 = new Intent(MainActivity.this,IntentServiceDemo.class);
                                startService(intent1);
                                break;
                        }
                    }
                });
            }
        }
    };
    List<ListBean> list = new ArrayList<ListBean>();
    ListBean listBean = new ListBean();
    FootRecyclerAdapter footRecyclerAdapter;
    private PermissionHelper permissionHelper;
    private static final int REQUEST_CODE_SETTINGS = 1;
    int x = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity activity = new MainActivity();
        permissionHelper = PermissionHelper.createForLocation(this);
        permissionHelper.requestPermission(REQUEST_CODE_SETTINGS);
        /**
         * recycleView + SmartRefreshLayout +BaseQuickAdapter 的使用
         */
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new HBRefreshHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20).setProgressResource(R.mipmap.ic_launcher);
            }
        });
        srl = (SmartRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        srl.setEnableRefresh(true);//启用刷新
        srl.setEnableLoadmore(true);//启用加载
        float a = 5 >> 1;
        Log.e("-----", a + "");

        for (int i = 0; i < 10; i++) {
            listBean = new ListBean();
            listBean.setName("水波纹-----11" + (i + 1));
            list.add(listBean);
        }

        srl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                List<ListBean> listItems = new ArrayList();
                listBean = new ListBean();
                listBean.setName("水波纹----");
                listItems.add(listBean);
                recyclerAdapter.setDataList(listItems, false);
                srl.finishLoadmore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list = new ArrayList<ListBean>();
                for (int i = 0; i < 10; i++) {
                    listBean = new ListBean();
                    listBean.setName("水波纹" + (i + 1));
                    list.add(listBean);
                }
                recyclerAdapter.setDataList(list, true);
                srl.finishRefresh();
            }
        });


        rv.setAdapter(recyclerAdapter);
        recyclerAdapter.addAll(list);

        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.add();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.cut();
            }
        }).start();


        /**
         * 可以改变状态的水波进度view，true:水波静止到最大波浪，false显示波浪
         */
        ImageView iv = (ImageView) findViewById(R.id.iv);
        ImageView iv_picasso = (ImageView) findViewById(R.id.iv_picasso);
        waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);
        mWaveHelper = new WaveHelper(waveView, 0.3f, true);
        iv.setImageResource(R.drawable.ring_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
        /**
         * 可以改变状态的水波进度view，true:水波静止到最大波浪，false显示波浪
         */

//        Picasso.with(MainActivity.this).load(imgUrl).into(iv_picasso);

    }

    public synchronized void add() {
        x = x + 1;
        Log.e("Main", "add----x=" + x);
    }

    public synchronized void cut() {
        x = x - 1;
        Log.e("Main", "cut----x=" + x);
    }

    public void intentActivity(Class clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mWaveHelper.cancel();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SETTINGS) {
            permissionHelper.onRequestPermissionsResult(permissions, grantResults);
        }
    }
}
