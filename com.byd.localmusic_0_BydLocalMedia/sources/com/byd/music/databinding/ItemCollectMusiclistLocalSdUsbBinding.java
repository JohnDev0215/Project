package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemCollectMusiclistLocalSdUsbBinding extends ViewDataBinding {
    protected ItemCollectMusiclistLocalSdUsbBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static ItemCollectMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemCollectMusiclistLocalSdUsbBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_collect_musiclist_local_sd_usb, viewGroup, z, obj);
    }

    public static ItemCollectMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemCollectMusiclistLocalSdUsbBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_collect_musiclist_local_sd_usb, null, false, obj);
    }

    public static ItemCollectMusiclistLocalSdUsbBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemCollectMusiclistLocalSdUsbBinding bind(View view, Object obj) {
        return (ItemCollectMusiclistLocalSdUsbBinding) bind(obj, view, R.layout.item_collect_musiclist_local_sd_usb);
    }
}
