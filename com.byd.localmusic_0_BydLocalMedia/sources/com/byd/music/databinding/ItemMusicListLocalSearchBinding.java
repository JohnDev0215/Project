package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemMusicListLocalSearchBinding extends ViewDataBinding {

    @Bindable
    protected LocalMusicBean mLocalSearchMusicBean;

    @Bindable
    protected LocalMusicSearchViewModel mViewModel;
    public final TextView tvAnnouncer;
    public final TextView tvIndex;
    public final TextView tvSongName;
    public final TextView tvStorageType;
    public final View view2;

    public abstract void setLocalSearchMusicBean(LocalMusicBean localMusicBean);

    public abstract void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel);

    protected ItemMusicListLocalSearchBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        super(obj, view, i);
        this.tvAnnouncer = textView;
        this.tvIndex = textView2;
        this.tvSongName = textView3;
        this.tvStorageType = textView4;
        this.view2 = view2;
    }

    public LocalMusicSearchViewModel getViewModel() {
        return this.mViewModel;
    }

    public LocalMusicBean getLocalSearchMusicBean() {
        return this.mLocalSearchMusicBean;
    }

    public static ItemMusicListLocalSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusicListLocalSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemMusicListLocalSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_music_list_local_search, viewGroup, z, obj);
    }

    public static ItemMusicListLocalSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusicListLocalSearchBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemMusicListLocalSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_music_list_local_search, null, false, obj);
    }

    public static ItemMusicListLocalSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusicListLocalSearchBinding bind(View view, Object obj) {
        return (ItemMusicListLocalSearchBinding) bind(obj, view, R.layout.item_music_list_local_search);
    }
}
