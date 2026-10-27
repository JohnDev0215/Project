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
public final class ItemSkillsSystemBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvApplicationOperations;
    public final CustomSkillsPopupView cspvOtherOperations;
    public final CustomSkillsPopupView cspvRotate;
    public final CustomSkillsPopupView cspvSettingsOperations;
    public final CustomSkillsPopupView cspvVolume;
    public final ConstraintLayout customSystem;
    private final CustomScrollView rootView;
    public final CustomScrollView svSystem;
    public final View systemBottom;

    private ItemSkillsSystemBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, CustomSkillsPopupView customSkillsPopupView3, CustomSkillsPopupView customSkillsPopupView4, CustomSkillsPopupView customSkillsPopupView5, ConstraintLayout constraintLayout, CustomScrollView customScrollView2, View view) {
        this.rootView = customScrollView;
        this.cspvApplicationOperations = customSkillsPopupView;
        this.cspvOtherOperations = customSkillsPopupView2;
        this.cspvRotate = customSkillsPopupView3;
        this.cspvSettingsOperations = customSkillsPopupView4;
        this.cspvVolume = customSkillsPopupView5;
        this.customSystem = constraintLayout;
        this.svSystem = customScrollView2;
        this.systemBottom = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsSystemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsSystemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_system, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsSystemBinding bind(View view) {
        int i = R.id.cspv_application_operations;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_application_operations);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_other_operations;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_other_operations);
            if (customSkillsPopupView2 != null) {
                i = R.id.cspv_rotate;
                CustomSkillsPopupView customSkillsPopupView3 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_rotate);
                if (customSkillsPopupView3 != null) {
                    i = R.id.cspv_settings_operations;
                    CustomSkillsPopupView customSkillsPopupView4 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_settings_operations);
                    if (customSkillsPopupView4 != null) {
                        i = R.id.cspv_volume;
                        CustomSkillsPopupView customSkillsPopupView5 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_volume);
                        if (customSkillsPopupView5 != null) {
                            i = R.id.custom_system;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.custom_system);
                            if (constraintLayout != null) {
                                CustomScrollView customScrollView = (CustomScrollView) view;
                                i = R.id.system_bottom;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.system_bottom);
                                if (viewFindChildViewById != null) {
                                    return new ItemSkillsSystemBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, customSkillsPopupView3, customSkillsPopupView4, customSkillsPopupView5, constraintLayout, customScrollView, viewFindChildViewById);
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
