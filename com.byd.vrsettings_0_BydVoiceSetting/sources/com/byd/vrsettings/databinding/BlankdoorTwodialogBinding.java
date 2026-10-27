package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class BlankdoorTwodialogBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final AppTextView tvCarModel;
    public final AppTextView tvX;
    public final View vBackground;

    private BlankdoorTwodialogBinding(ConstraintLayout constraintLayout, AppTextView appTextView, AppTextView appTextView2, View view) {
        this.rootView = constraintLayout;
        this.tvCarModel = appTextView;
        this.tvX = appTextView2;
        this.vBackground = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static BlankdoorTwodialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BlankdoorTwodialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.blankdoor_twodialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static BlankdoorTwodialogBinding bind(View view) {
        int i = R.id.tv_car_model;
        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_car_model);
        if (appTextView != null) {
            i = R.id.tv_x;
            AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_x);
            if (appTextView2 != null) {
                i = R.id.v_background;
                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_background);
                if (viewFindChildViewById != null) {
                    return new BlankdoorTwodialogBinding((ConstraintLayout) view, appTextView, appTextView2, viewFindChildViewById);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
