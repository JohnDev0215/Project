package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentTaSettingBinding extends ViewDataBinding {
    public final ImageButton btnBack;
    public final LinearLayout checkBoxContainer;
    public final LinearLayout layoutTitle;

    protected FragmentTaSettingBinding(Object obj, View view, int i, ImageButton imageButton, LinearLayout linearLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.btnBack = imageButton;
        this.checkBoxContainer = linearLayout;
        this.layoutTitle = linearLayout2;
    }

    public static FragmentTaSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTaSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentTaSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ta_setting, viewGroup, z, obj);
    }

    public static FragmentTaSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTaSettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentTaSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_ta_setting, null, false, obj);
    }

    public static FragmentTaSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTaSettingBinding bind(View view, Object obj) {
        return (FragmentTaSettingBinding) bind(obj, view, R.layout.fragment_ta_setting);
    }
}
