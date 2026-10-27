package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.LocalMusicListViewModel;
import com.byd.music.view.LoadingView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLocalMusiclistBinding extends ViewDataBinding {
    public final ConstraintLayout clOperate;
    public final ImageView ivCancelSelect;
    public final ImageView ivDelete;
    public final ImageView ivSelectAll;
    public final LoadingView loadingView;

    @Bindable
    protected LocalMusicListViewModel mViewModel;
    public final RecyclerView recyclerView;
    public final TextView tvCancelSelect;
    public final TextView tvDelete;
    public final TextView tvDeleteAllTips;
    public final TextView tvSelectAll;

    public abstract void setViewModel(LocalMusicListViewModel localMusicListViewModel);

    protected FragmentLocalMusiclistBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, LoadingView loadingView, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clOperate = constraintLayout;
        this.ivCancelSelect = imageView;
        this.ivDelete = imageView2;
        this.ivSelectAll = imageView3;
        this.loadingView = loadingView;
        this.recyclerView = recyclerView;
        this.tvCancelSelect = textView;
        this.tvDelete = textView2;
        this.tvDeleteAllTips = textView3;
        this.tvSelectAll = textView4;
    }

    public LocalMusicListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentLocalMusiclistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusiclistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLocalMusiclistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_musiclist, viewGroup, z, obj);
    }

    public static FragmentLocalMusiclistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusiclistBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLocalMusiclistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_musiclist, null, false, obj);
    }

    public static FragmentLocalMusiclistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusiclistBinding bind(View view, Object obj) {
        return (FragmentLocalMusiclistBinding) bind(obj, view, R.layout.fragment_local_musiclist);
    }
}
