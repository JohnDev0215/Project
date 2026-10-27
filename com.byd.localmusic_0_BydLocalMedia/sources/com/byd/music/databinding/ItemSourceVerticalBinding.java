package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.activity.main.MediaMainViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ItemSourceVerticalBinding extends ViewDataBinding {

    @Bindable
    protected Integer mTitleId;

    @Bindable
    protected MediaMainViewModel mViewModel;
    public final ImageView sourceImage;
    public final TextView sourceTitle;

    public abstract void setTitleId(Integer num);

    public abstract void setViewModel(MediaMainViewModel mediaMainViewModel);

    protected ItemSourceVerticalBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.sourceImage = imageView;
        this.sourceTitle = textView;
    }

    public Integer getTitleId() {
        return this.mTitleId;
    }

    public MediaMainViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ItemSourceVerticalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSourceVerticalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemSourceVerticalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_source_vertical, viewGroup, z, obj);
    }

    public static ItemSourceVerticalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSourceVerticalBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemSourceVerticalBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_source_vertical, null, false, obj);
    }

    public static ItemSourceVerticalBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSourceVerticalBinding bind(View view, Object obj) {
        return (ItemSourceVerticalBinding) bind(obj, view, R.layout.item_source_vertical);
    }
}
