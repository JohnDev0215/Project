package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemDabPtyBinding extends ViewDataBinding {

    @Bindable
    protected RadioDBBean mProgramInfo;

    public abstract void setProgramInfo(RadioDBBean radioDBBean);

    protected ItemDabPtyBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public RadioDBBean getProgramInfo() {
        return this.mProgramInfo;
    }

    public static ItemDabPtyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabPtyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemDabPtyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_dab_pty, viewGroup, z, obj);
    }

    public static ItemDabPtyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabPtyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemDabPtyBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_dab_pty, null, false, obj);
    }

    public static ItemDabPtyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabPtyBinding bind(View view, Object obj) {
        return (ItemDabPtyBinding) bind(obj, view, R.layout.item_dab_pty);
    }
}
