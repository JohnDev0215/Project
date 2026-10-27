package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.activity.OpenBTViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityOpenBtBinding extends ViewDataBinding {

    @Bindable
    protected OpenBTViewModel mViewModel;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvDesc;

    public abstract void setViewModel(OpenBTViewModel openBTViewModel);

    protected ActivityOpenBtBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.tvCancel = textView;
        this.tvConfirm = textView2;
        this.tvDesc = textView3;
    }

    public OpenBTViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityOpenBtBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOpenBtBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityOpenBtBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_open_bt, viewGroup, z, obj);
    }

    public static ActivityOpenBtBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOpenBtBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityOpenBtBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_open_bt, null, false, obj);
    }

    public static ActivityOpenBtBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityOpenBtBinding bind(View view, Object obj) {
        return (ActivityOpenBtBinding) bind(obj, view, R.layout.activity_open_bt);
    }
}
