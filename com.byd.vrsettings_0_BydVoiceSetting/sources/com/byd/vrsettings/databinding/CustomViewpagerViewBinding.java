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
public final class CustomViewpagerViewBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final AppTextView tip1;
    public final AppTextView tip2;
    public final AppTextView tip3;
    public final AppTextView tip4;

    private CustomViewpagerViewBinding(ConstraintLayout constraintLayout, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4) {
        this.rootView = constraintLayout;
        this.tip1 = appTextView;
        this.tip2 = appTextView2;
        this.tip3 = appTextView3;
        this.tip4 = appTextView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static CustomViewpagerViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomViewpagerViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_viewpager_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomViewpagerViewBinding bind(View view) {
        int i = R.id.tip1;
        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tip1);
        if (appTextView != null) {
            i = R.id.tip2;
            AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tip2);
            if (appTextView2 != null) {
                i = R.id.tip3;
                AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tip3);
                if (appTextView3 != null) {
                    i = R.id.tip4;
                    AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tip4);
                    if (appTextView4 != null) {
                        return new CustomViewpagerViewBinding((ConstraintLayout) view, appTextView, appTextView2, appTextView3, appTextView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
