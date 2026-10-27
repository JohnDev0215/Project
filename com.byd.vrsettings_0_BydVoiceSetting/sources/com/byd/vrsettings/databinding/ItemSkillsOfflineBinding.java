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
public final class ItemSkillsOfflineBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvAc;
    public final CustomSkillsPopupView cspvDial;
    public final CustomSkillsPopupView cspvOtherOperations;
    public final CustomSkillsPopupView cspvVolume;
    public final CustomSkillsPopupView cspvWindows;
    public final ConstraintLayout customOffline;
    public final View offlineBottom;
    private final CustomScrollView rootView;
    public final CustomScrollView svOffline;

    private ItemSkillsOfflineBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, CustomSkillsPopupView customSkillsPopupView3, CustomSkillsPopupView customSkillsPopupView4, CustomSkillsPopupView customSkillsPopupView5, ConstraintLayout constraintLayout, View view, CustomScrollView customScrollView2) {
        this.rootView = customScrollView;
        this.cspvAc = customSkillsPopupView;
        this.cspvDial = customSkillsPopupView2;
        this.cspvOtherOperations = customSkillsPopupView3;
        this.cspvVolume = customSkillsPopupView4;
        this.cspvWindows = customSkillsPopupView5;
        this.customOffline = constraintLayout;
        this.offlineBottom = view;
        this.svOffline = customScrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsOfflineBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsOfflineBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_offline, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsOfflineBinding bind(View view) {
        int i = R.id.cspv_ac;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_ac);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_dial;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_dial);
            if (customSkillsPopupView2 != null) {
                i = R.id.cspv_other_operations;
                CustomSkillsPopupView customSkillsPopupView3 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_other_operations);
                if (customSkillsPopupView3 != null) {
                    i = R.id.cspv_volume;
                    CustomSkillsPopupView customSkillsPopupView4 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_volume);
                    if (customSkillsPopupView4 != null) {
                        i = R.id.cspv_windows;
                        CustomSkillsPopupView customSkillsPopupView5 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_windows);
                        if (customSkillsPopupView5 != null) {
                            i = R.id.custom_offline;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.custom_offline);
                            if (constraintLayout != null) {
                                i = R.id.offline_bottom;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.offline_bottom);
                                if (viewFindChildViewById != null) {
                                    CustomScrollView customScrollView = (CustomScrollView) view;
                                    return new ItemSkillsOfflineBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, customSkillsPopupView3, customSkillsPopupView4, customSkillsPopupView5, constraintLayout, viewFindChildViewById, customScrollView);
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
