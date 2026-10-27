package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutSettingBinding implements ViewBinding {
    public final ConstraintLayout clSetting;
    public final FrameLayout flSetting;
    public final FrameLayout flSettingBackground;
    public final ImageView ivAppIcon;
    public final ImageView ivTab;
    public final ImageView ivVoiceSetting;
    public final ImageView ivVoiceSkills;
    public final View mainBottom;
    private final FrameLayout rootView;
    public final AppTextView tvTabSubtitle;
    public final AppTextView tvVoiceSetting;
    public final AppTextView tvVoiceSkills;
    public final View vVoiceSetting;
    public final View vVoiceSkills;

    private LayoutSettingBinding(FrameLayout frameLayout, ConstraintLayout constraintLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, View view2, View view3) {
        this.rootView = frameLayout;
        this.clSetting = constraintLayout;
        this.flSetting = frameLayout2;
        this.flSettingBackground = frameLayout3;
        this.ivAppIcon = imageView;
        this.ivTab = imageView2;
        this.ivVoiceSetting = imageView3;
        this.ivVoiceSkills = imageView4;
        this.mainBottom = view;
        this.tvTabSubtitle = appTextView;
        this.tvVoiceSetting = appTextView2;
        this.tvVoiceSkills = appTextView3;
        this.vVoiceSetting = view2;
        this.vVoiceSkills = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static LayoutSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSettingBinding bind(View view) {
        int i = R.id.cl_setting;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_setting);
        if (constraintLayout != null) {
            i = R.id.fl_setting;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_setting);
            if (frameLayout != null) {
                FrameLayout frameLayout2 = (FrameLayout) view;
                i = R.id.iv_appIcon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_appIcon);
                if (imageView != null) {
                    i = R.id.iv_tab;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tab);
                    if (imageView2 != null) {
                        i = R.id.iv_voice_setting;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_setting);
                        if (imageView3 != null) {
                            i = R.id.iv_voice_skills;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills);
                            if (imageView4 != null) {
                                i = R.id.main_bottom;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.main_bottom);
                                if (viewFindChildViewById != null) {
                                    i = R.id.tv_tabSubtitle;
                                    AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_tabSubtitle);
                                    if (appTextView != null) {
                                        i = R.id.tv_voice_setting;
                                        AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_setting);
                                        if (appTextView2 != null) {
                                            i = R.id.tv_voice_skills;
                                            AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills);
                                            if (appTextView3 != null) {
                                                i = R.id.v_voice_setting;
                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_voice_setting);
                                                if (viewFindChildViewById2 != null) {
                                                    i = R.id.v_voice_skills;
                                                    View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_voice_skills);
                                                    if (viewFindChildViewById3 != null) {
                                                        return new LayoutSettingBinding(frameLayout2, constraintLayout, frameLayout, frameLayout2, imageView, imageView2, imageView3, imageView4, viewFindChildViewById, appTextView, appTextView2, appTextView3, viewFindChildViewById2, viewFindChildViewById3);
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
