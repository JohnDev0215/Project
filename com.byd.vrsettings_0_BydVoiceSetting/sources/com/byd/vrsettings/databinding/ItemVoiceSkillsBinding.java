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
public final class ItemVoiceSkillsBinding implements ViewBinding {
    public final View bugView;
    public final ConstraintLayout clItemVoiceSkills;
    public final FrameLayout flSkills;
    public final ImageView ivVoiceSkillsMap;
    public final ImageView ivVoiceSkillsOffline;
    public final ImageView ivVoiceSkillsPhone;
    public final ImageView ivVoiceSkillsPlayer;
    public final ImageView ivVoiceSkillsSystem;
    public final ImageView ivVoiceSkillsVehicle;
    public final ConstraintLayout layoutTab;
    private final ConstraintLayout rootView;
    public final AppTextView tvVoiceSkillsMap;
    public final AppTextView tvVoiceSkillsOffline;
    public final AppTextView tvVoiceSkillsPhone;
    public final AppTextView tvVoiceSkillsPlayer;
    public final AppTextView tvVoiceSkillsSystem;
    public final AppTextView tvVoiceSkillsVehicle;
    public final View vMap;
    public final View vMapClick;
    public final View vOffline;
    public final View vOfflineClick;
    public final View vPhone;
    public final View vPhoneClick;
    public final View vPlayer;
    public final View vPlayerClick;
    public final View vSystem;
    public final View vSystemClick;
    public final View vVehicle;
    public final View vVehicleClick;

    private ItemVoiceSkillsBinding(ConstraintLayout constraintLayout, View view, ConstraintLayout constraintLayout2, FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ConstraintLayout constraintLayout3, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, AppTextView appTextView6, View view2, View view3, View view4, View view5, View view6, View view7, View view8, View view9, View view10, View view11, View view12, View view13) {
        this.rootView = constraintLayout;
        this.bugView = view;
        this.clItemVoiceSkills = constraintLayout2;
        this.flSkills = frameLayout;
        this.ivVoiceSkillsMap = imageView;
        this.ivVoiceSkillsOffline = imageView2;
        this.ivVoiceSkillsPhone = imageView3;
        this.ivVoiceSkillsPlayer = imageView4;
        this.ivVoiceSkillsSystem = imageView5;
        this.ivVoiceSkillsVehicle = imageView6;
        this.layoutTab = constraintLayout3;
        this.tvVoiceSkillsMap = appTextView;
        this.tvVoiceSkillsOffline = appTextView2;
        this.tvVoiceSkillsPhone = appTextView3;
        this.tvVoiceSkillsPlayer = appTextView4;
        this.tvVoiceSkillsSystem = appTextView5;
        this.tvVoiceSkillsVehicle = appTextView6;
        this.vMap = view2;
        this.vMapClick = view3;
        this.vOffline = view4;
        this.vOfflineClick = view5;
        this.vPhone = view6;
        this.vPhoneClick = view7;
        this.vPlayer = view8;
        this.vPlayerClick = view9;
        this.vSystem = view10;
        this.vSystemClick = view11;
        this.vVehicle = view12;
        this.vVehicleClick = view13;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemVoiceSkillsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoiceSkillsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_voice_skills, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemVoiceSkillsBinding bind(View view) {
        int i = R.id.bugView;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bugView);
        if (viewFindChildViewById != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.fl_skills;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.fl_skills);
            if (frameLayout != null) {
                i = R.id.iv_voice_skills_map;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_map);
                if (imageView != null) {
                    i = R.id.iv_voice_skills_offline;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_offline);
                    if (imageView2 != null) {
                        i = R.id.iv_voice_skills_phone;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_phone);
                        if (imageView3 != null) {
                            i = R.id.iv_voice_skills_player;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_player);
                            if (imageView4 != null) {
                                i = R.id.iv_voice_skills_system;
                                ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_system);
                                if (imageView5 != null) {
                                    i = R.id.iv_voice_skills_vehicle;
                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_voice_skills_vehicle);
                                    if (imageView6 != null) {
                                        i = R.id.layout_tab;
                                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_tab);
                                        if (constraintLayout2 != null) {
                                            i = R.id.tv_voice_skills_map;
                                            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_map);
                                            if (appTextView != null) {
                                                i = R.id.tv_voice_skills_offline;
                                                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_offline);
                                                if (appTextView2 != null) {
                                                    i = R.id.tv_voice_skills_phone;
                                                    AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_phone);
                                                    if (appTextView3 != null) {
                                                        i = R.id.tv_voice_skills_player;
                                                        AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_player);
                                                        if (appTextView4 != null) {
                                                            i = R.id.tv_voice_skills_system;
                                                            AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_system);
                                                            if (appTextView5 != null) {
                                                                i = R.id.tv_voice_skills_vehicle;
                                                                AppTextView appTextView6 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_voice_skills_vehicle);
                                                                if (appTextView6 != null) {
                                                                    i = R.id.v_map;
                                                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_map);
                                                                    if (viewFindChildViewById2 != null) {
                                                                        i = R.id.v_map_click;
                                                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_map_click);
                                                                        if (viewFindChildViewById3 != null) {
                                                                            i = R.id.v_offline;
                                                                            View viewFindChildViewById4 = ViewBindings.findChildViewById(view, R.id.v_offline);
                                                                            if (viewFindChildViewById4 != null) {
                                                                                i = R.id.v_offline_click;
                                                                                View viewFindChildViewById5 = ViewBindings.findChildViewById(view, R.id.v_offline_click);
                                                                                if (viewFindChildViewById5 != null) {
                                                                                    i = R.id.v_phone;
                                                                                    View viewFindChildViewById6 = ViewBindings.findChildViewById(view, R.id.v_phone);
                                                                                    if (viewFindChildViewById6 != null) {
                                                                                        i = R.id.v_phone_click;
                                                                                        View viewFindChildViewById7 = ViewBindings.findChildViewById(view, R.id.v_phone_click);
                                                                                        if (viewFindChildViewById7 != null) {
                                                                                            i = R.id.v_player;
                                                                                            View viewFindChildViewById8 = ViewBindings.findChildViewById(view, R.id.v_player);
                                                                                            if (viewFindChildViewById8 != null) {
                                                                                                i = R.id.v_player_click;
                                                                                                View viewFindChildViewById9 = ViewBindings.findChildViewById(view, R.id.v_player_click);
                                                                                                if (viewFindChildViewById9 != null) {
                                                                                                    i = R.id.v_system;
                                                                                                    View viewFindChildViewById10 = ViewBindings.findChildViewById(view, R.id.v_system);
                                                                                                    if (viewFindChildViewById10 != null) {
                                                                                                        i = R.id.v_system_click;
                                                                                                        View viewFindChildViewById11 = ViewBindings.findChildViewById(view, R.id.v_system_click);
                                                                                                        if (viewFindChildViewById11 != null) {
                                                                                                            i = R.id.v_vehicle;
                                                                                                            View viewFindChildViewById12 = ViewBindings.findChildViewById(view, R.id.v_vehicle);
                                                                                                            if (viewFindChildViewById12 != null) {
                                                                                                                i = R.id.v_vehicle_click;
                                                                                                                View viewFindChildViewById13 = ViewBindings.findChildViewById(view, R.id.v_vehicle_click);
                                                                                                                if (viewFindChildViewById13 != null) {
                                                                                                                    return new ItemVoiceSkillsBinding(constraintLayout, viewFindChildViewById, constraintLayout, frameLayout, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, constraintLayout2, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, appTextView6, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5, viewFindChildViewById6, viewFindChildViewById7, viewFindChildViewById8, viewFindChildViewById9, viewFindChildViewById10, viewFindChildViewById11, viewFindChildViewById12, viewFindChildViewById13);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
