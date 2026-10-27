package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLocalRootMusicBinding extends ViewDataBinding {
    protected FragmentLocalRootMusicBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static FragmentLocalRootMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalRootMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLocalRootMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_root_music, viewGroup, z, obj);
    }

    public static FragmentLocalRootMusicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalRootMusicBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLocalRootMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_root_music, null, false, obj);
    }

    public static FragmentLocalRootMusicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalRootMusicBinding bind(View view, Object obj) {
        return (FragmentLocalRootMusicBinding) bind(obj, view, R.layout.fragment_local_root_music);
    }
}
