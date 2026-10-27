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
public final class LayoutJoinUserPolicyBinding implements ViewBinding {
    public final View privacy;
    public final View privacyLine;
    public final ConstraintLayout privacyPolicyLayout;
    private final ConstraintLayout rootView;
    public final AppTextView tvBtnCancelPrivacy;
    public final AppTextView tvBtnConfirmPrivacy;
    public final AppTextView tvPrivacyPolicy;
    public final AppTextView tvTitlePrivacy;
    public final View vPrivacyBackground;

    private LayoutJoinUserPolicyBinding(ConstraintLayout constraintLayout, View view, View view2, ConstraintLayout constraintLayout2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, View view3) {
        this.rootView = constraintLayout;
        this.privacy = view;
        this.privacyLine = view2;
        this.privacyPolicyLayout = constraintLayout2;
        this.tvBtnCancelPrivacy = appTextView;
        this.tvBtnConfirmPrivacy = appTextView2;
        this.tvPrivacyPolicy = appTextView3;
        this.tvTitlePrivacy = appTextView4;
        this.vPrivacyBackground = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutJoinUserPolicyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutJoinUserPolicyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_join_user_policy, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutJoinUserPolicyBinding bind(View view) {
        int i = R.id.privacy;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.privacy);
        if (viewFindChildViewById != null) {
            i = R.id.privacy_line;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.privacy_line);
            if (viewFindChildViewById2 != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i = R.id.tv_btn_cancel_privacy;
                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_cancel_privacy);
                if (appTextView != null) {
                    i = R.id.tv_btn_confirm_privacy;
                    AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_confirm_privacy);
                    if (appTextView2 != null) {
                        i = R.id.tv_privacy_policy;
                        AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_privacy_policy);
                        if (appTextView3 != null) {
                            i = R.id.tv_title_privacy;
                            AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_privacy);
                            if (appTextView4 != null) {
                                i = R.id.v_privacyBackground;
                                View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_privacyBackground);
                                if (viewFindChildViewById3 != null) {
                                    return new LayoutJoinUserPolicyBinding(constraintLayout, viewFindChildViewById, viewFindChildViewById2, constraintLayout, appTextView, appTextView2, appTextView3, appTextView4, viewFindChildViewById3);
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
