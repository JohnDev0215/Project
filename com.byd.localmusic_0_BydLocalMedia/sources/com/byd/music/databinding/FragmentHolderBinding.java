package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentHolderBinding extends ViewDataBinding {
    public final FrameLayout flHolder;

    protected FragmentHolderBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.flHolder = frameLayout;
    }

    public static FragmentHolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHolderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentHolderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_holder, viewGroup, z, obj);
    }

    public static FragmentHolderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHolderBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentHolderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_holder, null, false, obj);
    }

    public static FragmentHolderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHolderBinding bind(View view, Object obj) {
        return (FragmentHolderBinding) bind(obj, view, R.layout.fragment_holder);
    }
}
