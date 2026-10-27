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
import com.byd.music.fragment.radio.AMViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemAmCollectBinding extends ViewDataBinding {
    public final ImageButton btnRemove2;
    public final CollectLottieView collectLottie;

    @Bindable
    protected RadioDBBean mFmCardBean;

    @Bindable
    protected AMViewModel mViewModel;
    public final PlayingSlideAnimView playingSlideAnimView2;
    public final TextView tvAlignName;

    public abstract void setFmCardBean(RadioDBBean radioDBBean);

    public abstract void setViewModel(AMViewModel aMViewModel);

    protected ItemAmCollectBinding(Object obj, View view, int i, ImageButton imageButton, CollectLottieView collectLottieView, PlayingSlideAnimView playingSlideAnimView, TextView textView) {
        super(obj, view, i);
        this.btnRemove2 = imageButton;
        this.collectLottie = collectLottieView;
        this.playingSlideAnimView2 = playingSlideAnimView;
        this.tvAlignName = textView;
    }

    public RadioDBBean getFmCardBean() {
        return this.mFmCardBean;
    }

    public AMViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ItemAmCollectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAmCollectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemAmCollectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_am_collect, viewGroup, z, obj);
    }

    public static ItemAmCollectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAmCollectBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemAmCollectBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_am_collect, null, false, obj);
    }

    public static ItemAmCollectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemAmCollectBinding bind(View view, Object obj) {
        return (ItemAmCollectBinding) bind(obj, view, R.layout.item_am_collect);
    }
}
