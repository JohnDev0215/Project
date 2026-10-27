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
import com.byd.music.fragment.localMusic.ui.LocalSDViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemCollectMusiclistLocalSdBinding extends ViewDataBinding {
    public final CollectLottieView collectLottiesView;
    public final ImageView ivDelete;
    public final ImageView ivLike;
    public final ImageView ivMultiSelect;

    @Bindable
    protected LocalMusicBean mCollectLocalSdUsbMusicBean;

    @Bindable
    protected LocalSDViewModel mViewModel;
    public final PlayingSlideAnimView playSlideView;
    public final TextView tvAnnouncer;
    public final TextView tvIndex;
    public final TextView tvSongName;
    public final View view2;

    public abstract void setCollectLocalSdUsbMusicBean(LocalMusicBean localMusicBean);

    public abstract void setViewModel(LocalSDViewModel localSDViewModel);

    protected ItemCollectMusiclistLocalSdBinding(Object obj, View view, int i, CollectLottieView collectLottieView, ImageView imageView, ImageView imageView2, ImageView imageView3, PlayingSlideAnimView playingSlideAnimView, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.collectLottiesView = collectLottieView;
        this.ivDelete = imageView;
        this.ivLike = imageView2;
        this.ivMultiSelect = imageView3;
        this.playSlideView = playingSlideAnimView;
        this.tvAnnouncer = textView;
        this.tvIndex = textView2;
        this.tvSongName = textView3;
        this.view2 = view2;
    }

    public LocalSDViewModel getViewModel() {
        return this.mViewModel;
    }

    public LocalMusicBean getCollectLocalSdUsbMusicBean() {
        return this.mCollectLocalSdUsbMusicBean;
    }

    public static ItemCollectMusiclistLocalSdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCollectMusiclistLocalSdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_collect_musiclist_local_sd, viewGroup, z, obj);
    }

    public static ItemCollectMusiclistLocalSdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCollectMusiclistLocalSdBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_collect_musiclist_local_sd, null, false, obj);
    }

    public static ItemCollectMusiclistLocalSdBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdBinding bind(View view, Object obj) {
        return (ItemCollectMusiclistLocalSdBinding) bind(obj, view, R.layout.item_collect_musiclist_local_sd);
    }
}
