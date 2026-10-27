package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.cropper.CropImageView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentCropLandBinding implements ViewBinding {
    public final TextView cropAction;
    public final TextView cropCancel;
    public final CropImageView cropImageView;
    public final TextView cropTitle;
    private final ConstraintLayout rootView;
    public final ConstraintLayout tabTopLayout;

    private FragmentCropLandBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, CropImageView cropImageView, TextView textView3, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.cropAction = textView;
        this.cropCancel = textView2;
        this.cropImageView = cropImageView;
        this.cropTitle = textView3;
        this.tabTopLayout = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCropLandBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentCropLandBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_crop_land, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCropLandBinding bind(View view) {
        int i = R.id.crop_action;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.crop_action);
        if (textView != null) {
            i = R.id.crop_cancel;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.crop_cancel);
            if (textView2 != null) {
                i = R.id.crop_image_view;
                CropImageView cropImageView = (CropImageView) ViewBindings.findChildViewById(view, R.id.crop_image_view);
                if (cropImageView != null) {
                    i = R.id.crop_title;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.crop_title);
                    if (textView3 != null) {
                        i = R.id.tab_top_layout;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.tab_top_layout);
                        if (constraintLayout != null) {
                            return new FragmentCropLandBinding((ConstraintLayout) view, textView, textView2, cropImageView, textView3, constraintLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
