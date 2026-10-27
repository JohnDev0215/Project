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
public final class LayoutFirstPrivacyBinding implements ViewBinding {
    public final ImageView UserImprovementButton;
    public final AppTextView UserImprovementContext;
    public final ConstraintLayout clGuide;
    public final ConstraintLayout privacyContext;
    public final View privacyLine;
    private final ConstraintLayout rootView;
    public final ScrollView svGuide;
    public final AppTextView tvNo;
    public final AppTextView tvTextGuide;
    public final AppTextView tvTitleGuide;
    public final AppTextView tvYes;
    public final View vGuide;

    private LayoutFirstPrivacyBinding(ConstraintLayout constraintLayout, ImageView imageView, AppTextView appTextView, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, View view, ScrollView scrollView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, View view2) {
        this.rootView = constraintLayout;
        this.UserImprovementButton = imageView;
        this.UserImprovementContext = appTextView;
        this.clGuide = constraintLayout2;
        this.privacyContext = constraintLayout3;
        this.privacyLine = view;
        this.svGuide = scrollView;
        this.tvNo = appTextView2;
        this.tvTextGuide = appTextView3;
        this.tvTitleGuide = appTextView4;
        this.tvYes = appTextView5;
        this.vGuide = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutFirstPrivacyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutFirstPrivacyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_first_privacy, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutFirstPrivacyBinding bind(View view) {
        int i = R.id.UserImprovementButton;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.UserImprovementButton);
        if (imageView != null) {
            i = R.id.UserImprovementContext;
            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.UserImprovementContext);
            if (appTextView != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.privacy_context;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.privacy_context);
                if (constraintLayout2 != null) {
                    i = R.id.privacy_line;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.privacy_line);
                    if (viewFindChildViewById != null) {
                        i = R.id.sv_guide;
                        ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.sv_guide);
                        if (scrollView != null) {
                            i = R.id.tv_no;
                            AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_no);
                            if (appTextView2 != null) {
                                i = R.id.tv_text_guide;
                                AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_text_guide);
                                if (appTextView3 != null) {
                                    i = R.id.tv_title_guide;
                                    AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_guide);
                                    if (appTextView4 != null) {
                                        i = R.id.tv_yes;
                                        AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_yes);
                                        if (appTextView5 != null) {
                                            i = R.id.v_guide;
                                            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_guide);
                                            if (viewFindChildViewById2 != null) {
                                                return new LayoutFirstPrivacyBinding(constraintLayout, imageView, appTextView, constraintLayout, constraintLayout2, viewFindChildViewById, scrollView, appTextView2, appTextView3, appTextView4, appTextView5, viewFindChildViewById2);
                                            }
                                        }
                                    }
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
