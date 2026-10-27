package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSkillsPopupView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemSkillsMapBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvDestination;
    public final CustomSkillsPopupView cspvMapControl;
    public final CustomSkillsPopupView cspvNavigating;
    public final CustomSkillsPopupView cspvNearby;
    public final ConstraintLayout customSkills;
    public final View mapBottom;
    public final CustomSkillsPopupView navigationVolume;
    private final CustomScrollView rootView;
    public final CustomScrollView svMap;

    private ItemSkillsMapBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, CustomSkillsPopupView customSkillsPopupView3, CustomSkillsPopupView customSkillsPopupView4, ConstraintLayout constraintLayout, View view, CustomSkillsPopupView customSkillsPopupView5, CustomScrollView customScrollView2) {
        this.rootView = customScrollView;
        this.cspvDestination = customSkillsPopupView;
        this.cspvMapControl = customSkillsPopupView2;
        this.cspvNavigating = customSkillsPopupView3;
        this.cspvNearby = customSkillsPopupView4;
        this.customSkills = constraintLayout;
        this.mapBottom = view;
        this.navigationVolume = customSkillsPopupView5;
        this.svMap = customScrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsMapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsMapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_map, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsMapBinding bind(View view) {
        int i = R.id.cspv_destination;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_destination);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_map_control;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_map_control);
            if (customSkillsPopupView2 != null) {
                i = R.id.cspv_navigating;
                CustomSkillsPopupView customSkillsPopupView3 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_navigating);
                if (customSkillsPopupView3 != null) {
                    i = R.id.cspv_nearby;
                    CustomSkillsPopupView customSkillsPopupView4 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_nearby);
                    if (customSkillsPopupView4 != null) {
                        i = R.id.customSkills;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.customSkills);
                        if (constraintLayout != null) {
                            i = R.id.map_bottom;
                            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.map_bottom);
                            if (viewFindChildViewById != null) {
                                i = R.id.navigation_volume;
                                CustomSkillsPopupView customSkillsPopupView5 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.navigation_volume);
                                if (customSkillsPopupView5 != null) {
                                    CustomScrollView customScrollView = (CustomScrollView) view;
                                    return new ItemSkillsMapBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, customSkillsPopupView3, customSkillsPopupView4, constraintLayout, viewFindChildViewById, customSkillsPopupView5, customScrollView);
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
