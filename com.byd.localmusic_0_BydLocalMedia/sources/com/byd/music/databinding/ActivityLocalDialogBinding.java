package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.activity.DialogActivityViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityLocalDialogBinding extends ViewDataBinding {

    @Bindable
    protected DialogActivityViewModel mViewModel;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvDesc;
    public final TextView tvDiv;

    public abstract void setViewModel(DialogActivityViewModel dialogActivityViewModel);

    protected ActivityLocalDialogBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.tvCancel = textView;
        this.tvConfirm = textView2;
        this.tvDesc = textView3;
        this.tvDiv = textView4;
    }

    public DialogActivityViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityLocalDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLocalDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_local_dialog, viewGroup, z, obj);
    }

    public static ActivityLocalDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLocalDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_local_dialog, null, false, obj);
    }

    public static ActivityLocalDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogBinding bind(View view, Object obj) {
        return (ActivityLocalDialogBinding) bind(obj, view, R.layout.activity_local_dialog);
    }
}
