package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.activity.DialogActivityConfirmViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ActivityLocalDialogConfirmBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;

    @Bindable
    protected DialogActivityConfirmViewModel mViewModel;
    public final SeekBar seekBar;
    public final TextView tvCancelDelete;
    public final TextView tvDelete;
    public final TextView tvProgress;
    public final View view;

    public abstract void setViewModel(DialogActivityConfirmViewModel dialogActivityConfirmViewModel);

    protected ActivityLocalDialogConfirmBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, SeekBar seekBar, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.seekBar = seekBar;
        this.tvCancelDelete = textView;
        this.tvDelete = textView2;
        this.tvProgress = textView3;
        this.view = view2;
    }

    public DialogActivityConfirmViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityLocalDialogConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLocalDialogConfirmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_local_dialog_confirm, viewGroup, z, obj);
    }

    public static ActivityLocalDialogConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogConfirmBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLocalDialogConfirmBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_local_dialog_confirm, null, false, obj);
    }

    public static ActivityLocalDialogConfirmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocalDialogConfirmBinding bind(View view, Object obj) {
        return (ActivityLocalDialogConfirmBinding) bind(obj, view, R.layout.activity_local_dialog_confirm);
    }
}
