package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutSettingUserImproveBinding implements ViewBinding {
    public final View bottomView;
    public final ConstraintLayout clUserimprove;
    public final AppTextView deldataBackgroud;
    public final AppTextView deldataText;
    public final ImageView ivSwitchUserImprove;
    public final ImageView ivUserimproveBack;
    public final ImageView ivUsrImproveIcon;
    public final AppTextView joinUserBackgroud;
    private final ConstraintLayout rootView;
    public final AppTextView tvCopyright;
    public final AppTextView tvJoinUserImproveTitle;
    public final AppTextView tvUserImprovePrivacyPolicy;
    public final AppTextView tvUserImprovePrivacyPolicyBottom;
    public final AppTextView tvUserImproveTitle;
    public final View vUserImporvement;

    private LayoutSettingUserImproveBinding(ConstraintLayout constraintLayout, View view, ConstraintLayout constraintLayout2, AppTextView appTextView, AppTextView appTextView2, ImageView imageView, ImageView imageView2, ImageView imageView3, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, AppTextView appTextView7, AppTextView appTextView8, View view2) {
        this.rootView = constraintLayout;
        this.bottomView = view;
        this.clUserimprove = constraintLayout2;
        this.deldataBackgroud = appTextView;
        this.deldataText = appTextView2;
        this.ivSwitchUserImprove = imageView;
        this.ivUserimproveBack = imageView2;
        this.ivUsrImproveIcon = imageView3;
        this.joinUserBackgroud = appTextView3;
        this.tvCopyright = appTextView4;
        this.tvJoinUserImproveTitle = appTextView5;
        this.tvUserImprovePrivacyPolicy = appTextView6;
        this.tvUserImprovePrivacyPolicyBottom = appTextView7;
        this.tvUserImproveTitle = appTextView8;
        this.vUserImporvement = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSettingUserImproveBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSettingUserImproveBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_setting_user_improve, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSettingUserImproveBinding bind(View view) {
        int i = R.id.bottomView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bottomView);
        if (viewFindChildViewById != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.deldata_backgroud;
            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.deldata_backgroud);
            if (appTextView != null) {
                i = R.id.deldataText;
                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.deldataText);
                if (appTextView2 != null) {
                    i = R.id.iv_switch_userImprove;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch_userImprove);
                    if (imageView != null) {
                        i = R.id.iv_userimprove_back;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_userimprove_back);
                        if (imageView2 != null) {
                            i = R.id.iv_usr_improve_icon;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_usr_improve_icon);
                            if (imageView3 != null) {
                                i = R.id.joinUser_backgroud;
                                AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.joinUser_backgroud);
                                if (appTextView3 != null) {
                                    i = R.id.tv_copyright;
                                    AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_copyright);
                                    if (appTextView4 != null) {
                                        i = R.id.tv_join_user_improve_title;
                                        AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_join_user_improve_title);
                                        if (appTextView5 != null) {
                                            i = R.id.tv_user_improve_privacy_policy;
                                            AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_improve_privacy_policy);
                                            if (appTextView6 != null) {
                                                i = R.id.tv_user_improve_privacy_policy_bottom;
                                                AppTextView appTextView7 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_improve_privacy_policy_bottom);
                                                if (appTextView7 != null) {
                                                    i = R.id.tv_user_improve_title;
                                                    AppTextView appTextView8 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_user_improve_title);
                                                    if (appTextView8 != null) {
                                                        i = R.id.v_user_Imporvement;
                                                        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_user_Imporvement);
                                                        if (viewFindChildViewById2 != null) {
                                                            return new LayoutSettingUserImproveBinding(constraintLayout, viewFindChildViewById, constraintLayout, appTextView, appTextView2, imageView, imageView2, imageView3, appTextView3, appTextView4, appTextView5, appTextView6, appTextView7, appTextView8, viewFindChildViewById2);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
