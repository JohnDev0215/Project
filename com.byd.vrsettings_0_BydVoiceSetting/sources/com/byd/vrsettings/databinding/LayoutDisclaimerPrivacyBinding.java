package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutDisclaimerPrivacyBinding implements ViewBinding {
    public final ConstraintLayout clGuide;
    public final ImageView disclaimerBtnX;
    public final View disclaimerLine;
    private final ConstraintLayout rootView;
    public final ScrollView svGuide;
    public final AppTextView tvTextGuide;
    public final AppTextView tvTitleGuide;
    public final View vGuide;
    public final View vGuideBackground;

    private LayoutDisclaimerPrivacyBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, View view, ScrollView scrollView, AppTextView appTextView, AppTextView appTextView2, View view2, View view3) {
        this.rootView = constraintLayout;
        this.clGuide = constraintLayout2;
        this.disclaimerBtnX = imageView;
        this.disclaimerLine = view;
        this.svGuide = scrollView;
        this.tvTextGuide = appTextView;
        this.tvTitleGuide = appTextView2;
        this.vGuide = view2;
        this.vGuideBackground = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDisclaimerPrivacyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutDisclaimerPrivacyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_disclaimer_privacy, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDisclaimerPrivacyBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.disclaimer_BtnX;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.disclaimer_BtnX);
        if (imageView != null) {
            i = R.id.disclaimer_line;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.disclaimer_line);
            if (viewFindChildViewById != null) {
                i = R.id.sv_guide;
                ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.sv_guide);
                if (scrollView != null) {
                    i = R.id.tv_text_guide;
                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_text_guide);
                    if (appTextView != null) {
                        i = R.id.tv_title_guide;
                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_guide);
                        if (appTextView2 != null) {
                            i = R.id.v_guide;
                            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_guide);
                            if (viewFindChildViewById2 != null) {
                                i = R.id.v_guideBackground;
                                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_guideBackground);
                                if (viewFindChildViewById3 != null) {
                                    return new LayoutDisclaimerPrivacyBinding(constraintLayout, constraintLayout, imageView, viewFindChildViewById, scrollView, appTextView, appTextView2, viewFindChildViewById2, viewFindChildViewById3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
