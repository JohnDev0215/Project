package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.fragment.radio.EpgListViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemEpgCurrentBinding extends ViewDataBinding {
    public final ImageButton imgBtn;

    @Bindable
    protected RadioEpgDBBean mEpgBean;

    @Bindable
    protected EpgListViewModel mViewModel;
    public final TextView textView2;
    public final TextView textView3;

    public abstract void setEpgBean(RadioEpgDBBean radioEpgDBBean);

    public abstract void setViewModel(EpgListViewModel epgListViewModel);

    protected ItemEpgCurrentBinding(Object obj, View view, int i, ImageButton imageButton, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imgBtn = imageButton;
        this.textView2 = textView;
        this.textView3 = textView2;
    }

    public RadioEpgDBBean getEpgBean() {
        return this.mEpgBean;
    }

    public EpgListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ItemEpgCurrentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgCurrentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemEpgCurrentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_epg_current, viewGroup, z, obj);
    }

    public static ItemEpgCurrentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgCurrentBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemEpgCurrentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_epg_current, null, false, obj);
    }

    public static ItemEpgCurrentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemEpgCurrentBinding bind(View view, Object obj) {
        return (ItemEpgCurrentBinding) bind(obj, view, R.layout.item_epg_current);
    }
}
