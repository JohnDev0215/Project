package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityEpgReplaceDialogBinding extends ViewDataBinding {
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvDesc;

    protected ActivityEpgReplaceDialogBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.tvCancel = textView;
        this.tvConfirm = textView2;
        this.tvDesc = textView3;
    }

    public static ActivityEpgReplaceDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEpgReplaceDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityEpgReplaceDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_epg_replace_dialog, viewGroup, z, obj);
    }

    public static ActivityEpgReplaceDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEpgReplaceDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityEpgReplaceDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_epg_replace_dialog, null, false, obj);
    }

    public static ActivityEpgReplaceDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityEpgReplaceDialogBinding bind(View view, Object obj) {
        return (ActivityEpgReplaceDialogBinding) bind(obj, view, R.layout.activity_epg_replace_dialog);
    }
}
