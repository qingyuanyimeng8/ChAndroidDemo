package com.example.hotfix.hotfixapplication.ui.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hotfix.hotfixapplication.ListBean;
import com.example.hotfix.hotfixapplication.R;
import com.example.hotfix.hotfixapplication.recycler.BaseAdapterHelper;
import com.example.hotfix.hotfixapplication.recycler.RecycleAdapter;
import com.example.hotfix.hotfixapplication.ui.mvp.bean.NewsInfo;
import com.example.hotfix.hotfixapplication.ui.mvp.presenter.NewsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MVPActivity extends Activity implements DataCallback, View.OnClickListener {
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.ll_pop)
    LinearLayout ll_pop;
    @BindView(R.id.lv_pop)
    ListView lv_pop;

    NewsPresenter newsPresenter;
    ListAdapter listAdapter;
    List<String> list = new ArrayList<>();
    List<ListBean> listBean = new ArrayList<>();
    List<List<ListBean>> listBeans = new ArrayList<>();
    String[] tabStrs1 = {"列表11", "列表12", "列表13", "列表14"};
    String[] tabStrs2 = {"列表21", "列表22", "列表23", "列表24"};
    String[] tabStrs3 = {"列表31", "列表32", "列表33", "列表34"};
    int itemIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        newsPresenter = new NewsPresenter(this);
        newsPresenter.getNewsData(1);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        srl.setEnableRefresh(true);//启用刷新
        srl.setEnableLoadmore(true);//启用加载
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);

        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }

        initialData(tabStrs1, 0);
        initialData(tabStrs2, 1);
        initialData(tabStrs3, 2);


        rv.setAdapter(new RecycleAdapter<String>(getApplicationContext(), R.layout.mvp_rv_item, list) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item, final int position) {
                switch (position) {
                    case 0:
                        helper.getTextView(R.id.tv_name).setText("fragment思考");
                        break;

                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                //高德定位
                                intentActivity(FragmentActivity.class);
                                break;
                            case 1:
                                ll_pop.setVisibility(View.VISIBLE);
                                break;

                        }
                    }
                });
            }
        });
        listAdapter = new ListAdapter(listBeans.get(0));
        lv_pop.setAdapter(listAdapter);
        lv_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<ListBean> listBean=listBeans.get(itemIndex);
                for(int i=0;i<listBean.size();i++){
                    if(i==position){
                        listBean.get(i).setSelected(true);
                    }else{
                        listBean.get(i).setSelected(false);
                    }
                }
                listAdapter.changeList(listBeans.get(listBean.get(position).getIndex()));
            }
        });
    }

    public void initialData(String[] strs, int index) {
        for (String strings : strs) {
            listBean.add(new ListBean(strings, false, index));
        }
        listBeans.add(listBean);
        listBean = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                listAdapter.changeList(listBeans.get(0));
                itemIndex=0;
                break;
            case R.id.tv_2:
                listAdapter.changeList(listBeans.get(1));
                itemIndex=1;
                break;
            case R.id.tv_3:
                listAdapter.changeList(listBeans.get(2));
                itemIndex=2;
                break;

        }
    }

    public class ListAdapter extends BaseAdapter {
        List<ListBean> listBeans;

        public ListAdapter(List<ListBean> listBeans) {
            this.listBeans = listBeans;
        }

        public void changeList(List<ListBean> listBeans) {
            this.listBeans = listBeans;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return listBeans.size();
        }

        @Override
        public ListBean getItem(int position) {
            return listBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MVPActivity.this, R.layout.item_layout, null);
            ListBean listBean = listBeans.get(position);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_name);
            RelativeLayout rl_select = (RelativeLayout) convertView.findViewById(R.id.rl_select);
            textView.setText(listBean.getName());
            if (listBean.isSelected()){
                rl_select.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }else{
                rl_select.setBackgroundColor(getResources().getColor(R.color.white));
            }
                return convertView;
        }
    }

    public void intentActivity(Class clazz) {
        startActivity(new Intent(MVPActivity.this, clazz));
    }

    @Override
    public void success(Object object) {

        NewsInfo newsInfo = (NewsInfo) object;

    }
}
