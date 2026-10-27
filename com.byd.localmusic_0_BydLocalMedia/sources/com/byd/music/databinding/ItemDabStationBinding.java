package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.fragment.radio.DABViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemDabStationBinding extends ViewDataBinding {
    public final ImageButton btnCollect;
    public final ImageView clock;
    public final CollectLottieView collectLottie;
    public final ShapeableImageView ivStationImg;

    @Bindable
    protected boolean mIsCollect;

    @Bindable
    protected RadioDBBean mProgramInfo;

    @Bindable
    protected DABViewModel mViewModel;
    public final PlayingSlideAnimView playingSlide;
    public final TextView tvDabTitle;

    public abstract void setIsCollect(boolean z);

    public abstract void setProgramInfo(RadioDBBean radioDBBean);

    public abstract void setViewModel(DABViewModel dABViewModel);

    protected ItemDabStationBinding(Object obj, View view, int i, ImageButton imageButton, ImageView imageView, CollectLottieView collectLottieView, ShapeableImageView shapeableImageView, PlayingSlideAnimView playingSlideAnimView, TextView textView) {
        super(obj, view, i);
        this.btnCollect = imageButton;
        this.clock = imageView;
        this.collectLottie = collectLottieView;
        this.ivStationImg = shapeableImageView;
        this.playingSlide = playingSlideAnimView;
        this.tvDabTitle = textView;
    }

    public RadioDBBean getProgramInfo() {
        return this.mProgramInfo;
    }

    public DABViewModel getViewModel() {
        return this.mViewModel;
    }

    public boolean getIsCollect() {
        return this.mIsCollect;
    }

    public static ItemDabStationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabStationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemDabStationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_dab_station, viewGroup, z, obj);
    }

    public static ItemDabStationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabStationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemDabStationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_dab_station, null, false, obj);
    }

    public static ItemDabStationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDabStationBinding bind(View view, Object obj) {
        return (ItemDabStationBinding) bind(obj, view, R.layout.item_dab_station);
    }
}
