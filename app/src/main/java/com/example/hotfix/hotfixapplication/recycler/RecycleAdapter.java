package com.example.hotfix.hotfixapplication.recycler;

import android.content.Context;
import android.support.annotation.LayoutRes;

import java.util.List;


/**
 */
public abstract class RecycleAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The mvp_rv_item resource id of each item.
     */
    public RecycleAdapter(Context context, @LayoutRes int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The mvp_rv_item resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public RecycleAdapter(Context context, @LayoutRes int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    /**
     * Create a multi-view-type QuickAdapter
     *
     * @param context              The context
     * @param multiItemTypeSupport multiitemtypesupport
     */
    protected RecycleAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,MultiItemTypeSupport) but with
     * some initialization data
     *
     * @param context
     * @param multiItemTypeSupport
     * @param data
     */
    protected RecycleAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

}
