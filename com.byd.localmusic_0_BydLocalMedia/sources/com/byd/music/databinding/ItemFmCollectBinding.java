package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.fragment.radio.FMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemFmCollectBinding extends ViewDataBinding {
    public final ImageButton btnRemove2;
    public final CollectLottieView collectLottie;
    public final View emptyView;

    @Bindable
    protected RadioDBBean mFmCardBean;

    @Bindable
    protected boolean mIsCollect;

    @Bindable
    protected FMViewModel mViewModel;
    public final PlayingSlideAnimView playingSlideAnimView;
    public final TextView tvAlignName;
    public final TextView tvRadioName;
    public final TextView tvRadioType;

    public abstract void setFmCardBean(RadioDBBean radioDBBean);

    public abstract void setIsCollect(boolean z);

    public abstract void setViewModel(FMViewModel fMViewModel);

    protected ItemFmCollectBinding(Object obj, View view, int i, ImageButton imageButton, CollectLottieView collectLottieView, View view2, PlayingSlideAnimView playingSlideAnimView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnRemove2 = imageButton;
        this.collectLottie = collectLottieView;
        this.emptyView = view2;
        this.playingSlideAnimView = playingSlideAnimView;
        this.tvAlignName = textView;
        this.tvRadioName = textView2;
        this.tvRadioType = textView3;
    }

    public RadioDBBean getFmCardBean() {
        return this.mFmCardBean;
    }

    public FMViewModel getViewModel() {
        return this.mViewModel;
    }

    public boolean getIsCollect() {
        return this.mIsCollect;
    }

    public static ItemFmCollectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFmCollectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemFmCollectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fm_collect, viewGroup, z, obj);
    }

    public static ItemFmCollectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFmCollectBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemFmCollectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_fm_collect, null, false, obj);
    }

    public static ItemFmCollectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemFmCollectBinding bind(View view, Object obj) {
        return (ItemFmCollectBinding) bind(obj, view, R.layout.item_fm_collect);
    }
}
