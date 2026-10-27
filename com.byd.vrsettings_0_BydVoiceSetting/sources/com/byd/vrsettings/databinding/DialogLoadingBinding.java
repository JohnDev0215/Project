package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogLoadingBinding implements ViewBinding {
    public final Guideline guideline;
    public final View loadingBg;
    public final ImageView loadingIcon;
    public final ProgressBar progressLoading;
    private final ConstraintLayout rootView;
    public final AppTextView tvLoading;

    private DialogLoadingBinding(ConstraintLayout constraintLayout, Guideline guideline, View view, ImageView imageView, ProgressBar progressBar, AppTextView appTextView) {
        this.rootView = constraintLayout;
        this.guideline = guideline;
        this.loadingBg = view;
        this.loadingIcon = imageView;
        this.progressLoading = progressBar;
        this.tvLoading = appTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogLoadingBinding bind(View view) {
        int i = R.id.guideline;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.guideline);
        if (guideline != null) {
            i = R.id.loading_bg;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.loading_bg);
            if (viewFindChildViewById != null) {
                i = R.id.loading_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.loading_icon);
                if (imageView != null) {
                    i = R.id.progress_loading;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progress_loading);
                    if (progressBar != null) {
                        i = R.id.tv_loading;
                        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_loading);
                        if (appTextView != null) {
                            return new DialogLoadingBinding((ConstraintLayout) view, guideline, viewFindChildViewById, imageView, progressBar, appTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
