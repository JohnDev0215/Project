package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewProgressDialogBinding implements ViewBinding {
    public final ProgressBar cropProgressBar;
    private final ConstraintLayout rootView;

    private ViewProgressDialogBinding(ConstraintLayout constraintLayout, ProgressBar progressBar) {
        this.rootView = constraintLayout;
        this.cropProgressBar = progressBar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewProgressDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewProgressDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_progress_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewProgressDialogBinding bind(View view) {
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.cropProgressBar);
        if (progressBar != null) {
            return new ViewProgressDialogBinding((ConstraintLayout) view, progressBar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.cropProgressBar)));
    }
}
