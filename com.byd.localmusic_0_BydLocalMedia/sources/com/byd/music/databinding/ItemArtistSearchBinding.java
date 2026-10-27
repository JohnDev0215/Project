package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.AlbumBean;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemArtistSearchBinding extends ViewDataBinding {

    @Bindable
    protected AlbumBean mLocalSearchAlbumBean;

    @Bindable
    protected LocalMusicSearchViewModel mViewModel;
    public final TextView tvAlbumName;

    public abstract void setLocalSearchAlbumBean(AlbumBean albumBean);

    public abstract void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel);

    protected ItemArtistSearchBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tvAlbumName = textView;
    }

    public LocalMusicSearchViewModel getViewModel() {
        return this.mViewModel;
    }

    public AlbumBean getLocalSearchAlbumBean() {
        return this.mLocalSearchAlbumBean;
    }

    public static ItemArtistSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemArtistSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemArtistSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_artist_search, viewGroup, z, obj);
    }

    public static ItemArtistSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemArtistSearchBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemArtistSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_artist_search, null, false, obj);
    }

    public static ItemArtistSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemArtistSearchBinding bind(View view, Object obj) {
        return (ItemArtistSearchBinding) bind(obj, view, R.layout.item_artist_search);
    }
}
