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
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.LocalMusicListViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemLocalmusiclistBinding extends ViewDataBinding {
    public final ImageView ivAdd;
    public final ImageView ivDownload;
    public final ImageView ivLike;

    @Bindable
    protected LocalMusicBean mLocalMusicBean;

    @Bindable
    protected LocalMusicListViewModel mViewModel;
    public final TextView tvAnnouncer;
    public final TextView tvIndex;
    public final TextView tvSongName;

    public abstract void setLocalMusicBean(LocalMusicBean localMusicBean);

    public abstract void setViewModel(LocalMusicListViewModel localMusicListViewModel);

    protected ItemLocalmusiclistBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ivAdd = imageView;
        this.ivDownload = imageView2;
        this.ivLike = imageView3;
        this.tvAnnouncer = textView;
        this.tvIndex = textView2;
        this.tvSongName = textView3;
    }

    public LocalMusicListViewModel getViewModel() {
        return this.mViewModel;
    }

    public LocalMusicBean getLocalMusicBean() {
        return this.mLocalMusicBean;
    }

    public static ItemLocalmusiclistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLocalmusiclistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemLocalmusiclistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_localmusiclist, viewGroup, z, obj);
    }

    public static ItemLocalmusiclistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLocalmusiclistBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemLocalmusiclistBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_localmusiclist, null, false, obj);
    }

    public static ItemLocalmusiclistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLocalmusiclistBinding bind(View view, Object obj) {
        return (ItemLocalmusiclistBinding) bind(obj, view, R.layout.item_localmusiclist);
    }
}
