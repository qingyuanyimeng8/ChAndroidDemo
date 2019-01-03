package com.example.hotfix.hotfixapplication.recycler;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hotfix.hotfixapplication.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by mcy on 2016/10/18.
 */

public abstract class FootRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, LoadMore {

    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();
    public static final int START = 1;
    public int SIZE = 10;
    protected final Context context;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //加载完成已经没有更多数据了
    public static final int NO_MORE_DATA = 2;
    //首次加载就已经没有更多数据了
    public static final int NO_DATA = 3;

    private int layoutResId;
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    protected final List<T> data;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = null;
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;
    private OnLoadEndListener onLoadEndListener;
    private boolean loadMoreEnabled;
    private boolean loadingMore;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    private boolean isCommonBottomVisible = false;

    /**
     * 公共底部是否隐藏的方法
     *
     * @param isCommobBottomvisible
     */
    public void setBottomVisible(boolean isCommobBottomvisible) {
        isCommonBottomVisible = isCommobBottomvisible;
        notifyDataSetChanged();

    }


    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The activity_touch resource id of each item.
     */
    protected FootRecyclerAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The activity_touch resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    protected FootRecyclerAdapter(Context context, int layoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
        setHasStableIds(true);
    }

    protected FootRecyclerAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        this(context, multiItemTypeSupport, null);
    }

    protected FootRecyclerAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        this.context = context;
        this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
        setHasStableIds(true);
    }


    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        if (data.size() == 0) {
            return data.size();
        } else {
            return data.size() + 1;
        }
    }

    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getData() {
        return data;
    }

    public void setSize(int size) {
        SIZE = size;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = null;
            if (mMultiItemTypeSupport != null) {
                int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
                view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            } else {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
            }
            view.setOnClickListener(this);
            BaseAdapterHelper itemViewHolder = new BaseAdapterHelper(view);
            return itemViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_load_more_layout, viewGroup, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BaseAdapterHelper) {
            checkLoadMore(position);
            holder.itemView.setTag(position);
            T item = getItem(position);
            if (item != null ) {
//                    convert((BaseAdapterHelper)holder, item);
                    convert((BaseAdapterHelper) holder, item);
            }
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if (isCommonBottomVisible) {
                footViewHolder.disc_bottom.setVisibility(View.VISIBLE);
            } else {
                footViewHolder.disc_bottom.setVisibility(View.GONE);
            }
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                    footViewHolder.progressBar.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    footViewHolder.progressBar.setVisibility(View.VISIBLE);
                    break;
                case NO_MORE_DATA:
                    footViewHolder.foot_view_item_tv.setText("没有更多记录了");
                    footViewHolder.progressBar.setVisibility(View.GONE);
                    break;
                case NO_DATA:
                    footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
                    footViewHolder.progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(BaseAdapterHelper helper, T item);
//    protected abstract void convert(H helper, T item , int position);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    /**
     * Clear data list
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void setDataList(List<T> datas, boolean refresh) {
        loadingMore = false;
        loadMoreEnabled = datas.size() >= SIZE ? true : false;
        if (refresh) {
            data.clear();
            data.addAll(datas);
            if (loadMoreEnabled) {
                changeMoreStatus(PULLUP_LOAD_MORE);
            } else {
                changeMoreStatus(NO_MORE_DATA);
            }
            notifyDataSetChanged();
        } else {
            data.addAll(datas);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loadMoreEnabled) {
                        changeMoreStatus(PULLUP_LOAD_MORE);
                    } else {
                        changeMoreStatus(NO_MORE_DATA);
                    }
                    notifyDataSetChanged();
                }
            }, 100);
        }
    }

    public void setDataListTwo(List<T> datas, List<T> newDatas, boolean refresh) {
        loadingMore = false;
        loadMoreEnabled = newDatas.size() >= SIZE ? true : false;
        if (refresh) {
            data.clear();
            data.addAll(datas);
            if (loadMoreEnabled) {
                changeMoreStatus(PULLUP_LOAD_MORE);
            } else {
                changeMoreStatus(NO_DATA);
            }
            notifyDataSetChanged();
        } else {
            data.clear();
            data.addAll(datas);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loadMoreEnabled) {
                        changeMoreStatus(PULLUP_LOAD_MORE);
                    } else {
                        changeMoreStatus(NO_MORE_DATA);
                    }
                    notifyDataSetChanged();
                }
            }, 100);
        }
    }

    public void setLoadMoreEnabled(boolean loadMoreEnabled) {
        this.loadMoreEnabled = loadMoreEnabled;
    }

    public void setLoadingMore(boolean loadingMore) {
        this.loadingMore = loadingMore;
    }

    public void checkLoadMore(int position) {
        if (loadMoreEnabled && !loadingMore
                && onLoadEndListener != null
                && position >= (data.size() - 2)) {
            loadingMore = true;
            load_more_status = LOADING_MORE;
            onLoadEndListener.onLoadEnd();
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private View disc_bottom;
        private TextView foot_view_item_tv, tv_down;
        private ProgressBar progressBar;

        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.foot_view_item_tv);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            disc_bottom = view.findViewById(R.id.ll_view_bottom);
            tv_down=(TextView) view.findViewById(R.id.tv_down);
        }
    }

    public void setOnLoadEndListener(LoadMore.OnLoadEndListener onLoadEndListener) {
        this.onLoadEndListener = onLoadEndListener;
    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }
}
