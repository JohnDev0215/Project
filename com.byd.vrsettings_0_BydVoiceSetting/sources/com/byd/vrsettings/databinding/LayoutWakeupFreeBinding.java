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
import com.byd.vrsettings.view.CustomSettingPopupView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutWakeupFreeBinding implements ViewBinding {
    public final ConstraintLayout clWakeupFree;
    public final CustomSettingPopupView cspvAcControl;
    public final CustomSettingPopupView cspvHomeCommand;
    public final CustomSettingPopupView cspvMapNav;
    public final CustomSettingPopupView cspvMusicPlayer;
    public final CustomSettingPopupView cspvTelControl;
    public final CustomSettingPopupView cspvVolumeControl;
    public final ImageView ivWakeupFreeBack;
    private final ScrollView rootView;
    public final ScrollView svWakeupFree;
    public final AppTextView tvWakeupFreeTitle;
    public final View vWakeupFree;
    public final View view;

    private LayoutWakeupFreeBinding(ScrollView scrollView, ConstraintLayout constraintLayout, CustomSettingPopupView customSettingPopupView, CustomSettingPopupView customSettingPopupView2, CustomSettingPopupView customSettingPopupView3, CustomSettingPopupView customSettingPopupView4, CustomSettingPopupView customSettingPopupView5, CustomSettingPopupView customSettingPopupView6, ImageView imageView, ScrollView scrollView2, AppTextView appTextView, View view, View view2) {
        this.rootView = scrollView;
        this.clWakeupFree = constraintLayout;
        this.cspvAcControl = customSettingPopupView;
        this.cspvHomeCommand = customSettingPopupView2;
        this.cspvMapNav = customSettingPopupView3;
        this.cspvMusicPlayer = customSettingPopupView4;
        this.cspvTelControl = customSettingPopupView5;
        this.cspvVolumeControl = customSettingPopupView6;
        this.ivWakeupFreeBack = imageView;
        this.svWakeupFree = scrollView2;
        this.tvWakeupFreeTitle = appTextView;
        this.vWakeupFree = view;
        this.view = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static LayoutWakeupFreeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutWakeupFreeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_wakeup_free, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutWakeupFreeBinding bind(View view) {
        int i = R.id.cl_wakeup_free;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_wakeup_free);
        if (constraintLayout != null) {
            i = R.id.cspv_ac_control;
            CustomSettingPopupView customSettingPopupView = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_ac_control);
            if (customSettingPopupView != null) {
                i = R.id.cspv_home_command;
                CustomSettingPopupView customSettingPopupView2 = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_home_command);
                if (customSettingPopupView2 != null) {
                    i = R.id.cspv_map_nav;
                    CustomSettingPopupView customSettingPopupView3 = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_map_nav);
                    if (customSettingPopupView3 != null) {
                        i = R.id.cspv_music_player;
                        CustomSettingPopupView customSettingPopupView4 = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_music_player);
                        if (customSettingPopupView4 != null) {
                            i = R.id.cspv_tel_control;
                            CustomSettingPopupView customSettingPopupView5 = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_tel_control);
                            if (customSettingPopupView5 != null) {
                                i = R.id.cspv_volume_control;
                                CustomSettingPopupView customSettingPopupView6 = (CustomSettingPopupView) ViewBindings.findChildViewById(view, R.id.cspv_volume_control);
                                if (customSettingPopupView6 != null) {
                                    i = R.id.iv_wakeup_free_back;
                                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_wakeup_free_back);
                                    if (imageView != null) {
                                        ScrollView scrollView = (ScrollView) view;
                                        i = R.id.tv_wakeup_free_title;
                                        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_wakeup_free_title);
                                        if (appTextView != null) {
                                            i = R.id.v_wakeup_free;
                                            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_wakeup_free);
                                            if (viewFindChildViewById != null) {
                                                i = R.id.view;
                                                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.view);
                                                if (viewFindChildViewById2 != null) {
                                                    return new LayoutWakeupFreeBinding(scrollView, constraintLayout, customSettingPopupView, customSettingPopupView2, customSettingPopupView3, customSettingPopupView4, customSettingPopupView5, customSettingPopupView6, imageView, scrollView, appTextView, viewFindChildViewById, viewFindChildViewById2);
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
