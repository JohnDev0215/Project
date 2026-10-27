package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemMusiclistLocalSdUsbBinding extends ViewDataBinding {
    protected ItemMusiclistLocalSdUsbBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static ItemMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemMusiclistLocalSdUsbBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_musiclist_local_sd_usb, viewGroup, z, obj);
    }

    public static ItemMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusiclistLocalSdUsbBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemMusiclistLocalSdUsbBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_musiclist_local_sd_usb, null, false, obj);
    }

    public static ItemMusiclistLocalSdUsbBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMusiclistLocalSdUsbBinding bind(View view, Object obj) {
        return (ItemMusiclistLocalSdUsbBinding) bind(obj, view, R.layout.item_musiclist_local_sd_usb);
    }
}
