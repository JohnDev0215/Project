package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.radio.EpgListViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentEpgListBinding extends ViewDataBinding {
    public final ImageButton btnBack;

    @Bindable
    protected EpgListViewModel mViewModel;
    public final RecyclerView rvCurrentDabList;
    public final RecyclerView rvCurrentEPGList;

    public abstract void setViewModel(EpgListViewModel epgListViewModel);

    protected FragmentEpgListBinding(Object obj, View view, int i, ImageButton imageButton, RecyclerView recyclerView, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.btnBack = imageButton;
        this.rvCurrentDabList = recyclerView;
        this.rvCurrentEPGList = recyclerView2;
    }

    public EpgListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentEpgListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEpgListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentEpgListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_epg_list, viewGroup, z, obj);
    }

    public static FragmentEpgListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEpgListBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentEpgListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_epg_list, null, false, obj);
    }

    public static FragmentEpgListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentEpgListBinding bind(View view, Object obj) {
        return (FragmentEpgListBinding) bind(obj, view, R.layout.fragment_epg_list);
    }
}
