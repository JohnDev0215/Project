package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.fragment.radio.EmptyViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentDabSettingBinding extends ViewDataBinding {
    public final ImageButton btnBack;
    public final LinearLayout checkBoxContainer;
    public final ScrollView checkBoxScroll;
    public final View dummyBottom;
    public final LinearLayout layoutTitle;

    @Bindable
    protected EmptyViewModel mViewModel;

    public abstract void setViewModel(EmptyViewModel emptyViewModel);

    protected FragmentDabSettingBinding(Object obj, View view, int i, ImageButton imageButton, LinearLayout linearLayout, ScrollView scrollView, View view2, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.btnBack = imageButton;
        this.checkBoxContainer = linearLayout;
        this.checkBoxScroll = scrollView;
        this.dummyBottom = view2;
        this.layoutTitle = linearLayout2;
    }

    public EmptyViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentDabSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentDabSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_dab_setting, viewGroup, z, obj);
    }

    public static FragmentDabSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabSettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentDabSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_dab_setting, null, false, obj);
    }

    public static FragmentDabSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDabSettingBinding bind(View view, Object obj) {
        return (FragmentDabSettingBinding) bind(obj, view, R.layout.fragment_dab_setting);
    }
}
