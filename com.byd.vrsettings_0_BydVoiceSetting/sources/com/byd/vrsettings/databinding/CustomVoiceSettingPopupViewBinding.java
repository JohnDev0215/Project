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
import com.google.android.flexbox.FlexboxLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CustomVoiceSettingPopupViewBinding implements ViewBinding {
    public final ConstraintLayout clPopup;
    public final FlexboxLayout fblPopup;
    public final ImageView ivSwitch;
    public final ImageView ivUpDown;
    private final ConstraintLayout rootView;
    public final AppTextView tvBtnCommand1;
    public final AppTextView tvBtnCommand2;
    public final AppTextView tvBtnCommand3;
    public final AppTextView tvBtnCommand4;
    public final AppTextView tvTitle;
    public final AppTextView tvTitleTip;
    public final View vBottomLine;

    private CustomVoiceSettingPopupViewBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, FlexboxLayout flexboxLayout, ImageView imageView, ImageView imageView2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, View view) {
        this.rootView = constraintLayout;
        this.clPopup = constraintLayout2;
        this.fblPopup = flexboxLayout;
        this.ivSwitch = imageView;
        this.ivUpDown = imageView2;
        this.tvBtnCommand1 = appTextView;
        this.tvBtnCommand2 = appTextView2;
        this.tvBtnCommand3 = appTextView3;
        this.tvBtnCommand4 = appTextView4;
        this.tvTitle = appTextView5;
        this.tvTitleTip = appTextView6;
        this.vBottomLine = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static CustomVoiceSettingPopupViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomVoiceSettingPopupViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.custom_voice_setting_popup_view, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CustomVoiceSettingPopupViewBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.fbl_popup;
        FlexboxLayout flexboxLayout = (FlexboxLayout) ViewBindings.findChildViewById(view, R.id.fbl_popup);
        if (flexboxLayout != null) {
            i = R.id.iv_switch;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_switch);
            if (imageView != null) {
                i = R.id.iv_up_down;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_up_down);
                if (imageView2 != null) {
                    i = R.id.tv_btn_command1;
                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_command1);
                    if (appTextView != null) {
                        i = R.id.tv_btn_command2;
                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_command2);
                        if (appTextView2 != null) {
                            i = R.id.tv_btn_command3;
                            AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_command3);
                            if (appTextView3 != null) {
                                i = R.id.tv_btn_command4;
                                AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_command4);
                                if (appTextView4 != null) {
                                    i = R.id.tv_title;
                                    AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                    if (appTextView5 != null) {
                                        i = R.id.tv_title_tip;
                                        AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_tip);
                                        if (appTextView6 != null) {
                                            i = R.id.v_bottomLine;
                                            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_bottomLine);
                                            if (viewFindChildViewById != null) {
                                                return new CustomVoiceSettingPopupViewBinding(constraintLayout, constraintLayout, flexboxLayout, imageView, imageView2, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, appTextView6, viewFindChildViewById);
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
