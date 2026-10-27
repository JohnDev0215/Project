package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.fragment.radio.EpgListViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemEpgDabListBinding extends ViewDataBinding {
    public final ImageView clock;

    @Bindable
    protected RadioDBBean mSetBean;

    @Bindable
    protected EpgListViewModel mViewModel;
    public final TextView text;

    public abstract void setSetBean(RadioDBBean radioDBBean);

    public abstract void setViewModel(EpgListViewModel epgListViewModel);

    protected ItemEpgDabListBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.clock = imageView;
        this.text = textView;
    }

    public RadioDBBean getSetBean() {
        return this.mSetBean;
    }

    public EpgListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ItemEpgDabListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgDabListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemEpgDabListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_epg_dab_list, viewGroup, z, obj);
    }

    public static ItemEpgDabListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgDabListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemEpgDabListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_epg_dab_list, null, false, obj);
    }

    public static ItemEpgDabListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgDabListBinding bind(View view, Object obj) {
        return (ItemEpgDabListBinding) bind(obj, view, R.layout.item_epg_dab_list);
    }
}
