package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.cropper.CropImageView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentCropPortBinding implements ViewBinding {
    public final TextView cropAction;
    public final CropImageView cropImageView;
    public final TextView cropOver;
    public final TextView cropTitle;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final ConstraintLayout tabTopLayout;

    private FragmentCropPortBinding(ConstraintLayout constraintLayout, TextView textView, CropImageView cropImageView, TextView textView2, TextView textView3, ProgressBar progressBar, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.cropAction = textView;
        this.cropImageView = cropImageView;
        this.cropOver = textView2;
        this.cropTitle = textView3;
        this.progressBar = progressBar;
        this.tabTopLayout = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCropPortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCropPortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_crop_port, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCropPortBinding bind(View view) {
        int i = R.id.crop_action;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.crop_action);
        if (textView != null) {
            i = R.id.crop_image_view;
            CropImageView cropImageView = (CropImageView) ViewBindings.findChildViewById(view, R.id.crop_image_view);
            if (cropImageView != null) {
                i = R.id.crop_over;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.crop_over);
                if (textView2 != null) {
                    i = R.id.crop_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.crop_title);
                    if (textView3 != null) {
                        i = R.id.progressBar;
                        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressBar);
                        if (progressBar != null) {
                            i = R.id.tab_top_layout;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.tab_top_layout);
                            if (constraintLayout != null) {
                                return new FragmentCropPortBinding((ConstraintLayout) view, textView, cropImageView, textView2, textView3, progressBar, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
