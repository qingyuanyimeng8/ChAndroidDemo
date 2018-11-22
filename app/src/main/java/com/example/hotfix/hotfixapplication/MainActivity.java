package com.example.hotfix.hotfixapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotfix.hotfixapplication.recycler.BaseAdapterHelper;
import com.example.hotfix.hotfixapplication.recycler.FootRecyclerAdapter;
import com.example.hotfix.hotfixapplication.recycler.HBRefreshHeader;
import com.example.hotfix.hotfixapplication.recycler.LoadMore;
import com.example.hotfix.hotfixapplication.recycler.RecycleAdapter;
import com.gelitenight.waveview.library.WaveView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WaveView waveView;
    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private SmartRefreshLayout srl;
    private RecyclerView rv;
//    RecycleAdapter<ListBean> recyclerAdapter;
    List<ListBean> list = new ArrayList<ListBean>();
    ListBean listBean = new ListBean();
    FootRecyclerAdapter footRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
        srl = (SmartRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        srl.setEnableRefresh(true);//启用刷新
        srl.setEnableLoadmore(true);//启用加载

        ImageView iv = (ImageView) findViewById(R.id.iv);
        waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);
        mWaveHelper = new WaveHelper(waveView, 0.3f, true);
        iv.setImageResource(R.drawable.ring_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
        float a = 5 >> 1;
        Log.e("-----", a + "");

        for (int i = 0; i < 10; i++) {
            listBean = new ListBean();
            listBean.setName("水波纹-----" + (i+1));
            list.add(listBean);
        }

//        recyclerAdapter = new RecycleAdapter<ListBean>(MainActivity.this, R.layout.item_layout) {
//
//            @Override
//            protected void convert(BaseAdapterHelper helper, final ListBean item, final int position) {
//                helper.getTextView(R.id.tv_name).setText(item.getName());
//                helper.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "点击了第" + (position + 1) +item.getName(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        };

        footRecyclerAdapter=new FootRecyclerAdapter<ListBean>(MainActivity.this, R.layout.item_layout) {
            @Override
            protected void convert(BaseAdapterHelper helper, final ListBean item) {
                helper.getTextView(R.id.tv_name).setText(item.getName());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了第------1234", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        footRecyclerAdapter.setOnLoadEndListener(new LoadMore.OnLoadEndListener() {
            @Override
            public void onLoadEnd() {
                List<ListBean> listItems=new ArrayList();
                listBean = new ListBean();
                listBean.setName("水波纹----");
                listItems.add(listBean);
                footRecyclerAdapter.setDataList(listItems,false);
//                srl.finishLoadmore();
            }
        });

        rv.setAdapter(footRecyclerAdapter);
        footRecyclerAdapter.addAll(list);
        srl.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list=new ArrayList<ListBean>();
                for (int i = 0; i < 10; i++) {
                    listBean = new ListBean();
                    listBean.setName("水波纹" + (i+1));
                    list.add(listBean);
                }
                footRecyclerAdapter.setDataList(list,true);
                srl.finishRefresh();
            }
        });

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
}
