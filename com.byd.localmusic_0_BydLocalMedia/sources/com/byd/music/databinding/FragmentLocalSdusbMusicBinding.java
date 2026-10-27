package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLocalSdusbMusicBinding extends ViewDataBinding {
    public final ConstraintLayout clMain;

    protected FragmentLocalSdusbMusicBinding(Object obj, View view, int i, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.clMain = constraintLayout;
    }

    public static FragmentLocalSdusbMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSdusbMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLocalSdusbMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_sdusb_music, viewGroup, z, obj);
    }

    public static FragmentLocalSdusbMusicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSdusbMusicBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLocalSdusbMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_sdusb_music, null, false, obj);
    }

    public static FragmentLocalSdusbMusicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSdusbMusicBinding bind(View view, Object obj) {
        return (FragmentLocalSdusbMusicBinding) bind(obj, view, R.layout.fragment_local_sdusb_music);
    }
}
