package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.music.R;
import com.byd.music.activity.main.MediaMainViewModel;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.viewsup.BydScrollerSupportedFrameLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityMediaMainBinding extends ViewDataBinding {
    public final ImageView btBluetooth;
    public final ConstraintLayout btMusicBattery;
    public final RecyclerView horizontalSourceList;
    public final BydScrollerSupportedFrameLayout horizontalSourceListContainer;

    @Bindable
    protected MediaMainViewModel mViewModel;

    @Bindable
    protected BluetoothViewModel mVm;
    public final ConstraintLayout mainConstraint;
    public final TextView textView;
    public final RecyclerView verticalSourceList;
    public final BydScrollerSupportedFrameLayout verticalSourceListContainer;
    public final ViewPager2 viewPager;

    public abstract void setViewModel(MediaMainViewModel mediaMainViewModel);

    public abstract void setVm(BluetoothViewModel bluetoothViewModel);

    protected ActivityMediaMainBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, RecyclerView recyclerView, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout, ConstraintLayout constraintLayout2, TextView textView, RecyclerView recyclerView2, BydScrollerSupportedFrameLayout bydScrollerSupportedFrameLayout2, ViewPager2 viewPager2) {
        super(obj, view, i);
        this.btBluetooth = imageView;
        this.btMusicBattery = constraintLayout;
        this.horizontalSourceList = recyclerView;
        this.horizontalSourceListContainer = bydScrollerSupportedFrameLayout;
        this.mainConstraint = constraintLayout2;
        this.textView = textView;
        this.verticalSourceList = recyclerView2;
        this.verticalSourceListContainer = bydScrollerSupportedFrameLayout2;
        this.viewPager = viewPager2;
    }

    public MediaMainViewModel getViewModel() {
        return this.mViewModel;
    }

    public BluetoothViewModel getVm() {
        return this.mVm;
    }

    public static ActivityMediaMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityMediaMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_main, viewGroup, z, obj);
    }

    public static ActivityMediaMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityMediaMainBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_media_main, null, false, obj);
    }

    public static ActivityMediaMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMediaMainBinding bind(View view, Object obj) {
        return (ActivityMediaMainBinding) bind(obj, view, R.layout.activity_media_main);
    }
}
