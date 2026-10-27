package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemGoneBinding extends ViewDataBinding {

    @Bindable
    protected Object mBean;

    public abstract void setBean(Object obj);

    protected ItemGoneBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public Object getBean() {
        return this.mBean;
    }

    public static ItemGoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemGoneBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_gone, viewGroup, z, obj);
    }

    public static ItemGoneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGoneBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemGoneBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_gone, null, false, obj);
    }

    public static ItemGoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGoneBinding bind(View view, Object obj) {
        return (ItemGoneBinding) bind(obj, view, R.layout.item_gone);
    }
}
