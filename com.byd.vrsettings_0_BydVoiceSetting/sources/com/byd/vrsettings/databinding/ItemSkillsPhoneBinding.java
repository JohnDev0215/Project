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
public final class ItemSkillsPhoneBinding implements ViewBinding {
    public final CustomSkillsPopupView cspvContact;
    public final CustomSkillsPopupView cspvDial;
    public final ConstraintLayout customPhone;
    public final View phoneBottom;
    private final CustomScrollView rootView;
    public final CustomScrollView svPhone;

    private ItemSkillsPhoneBinding(CustomScrollView customScrollView, CustomSkillsPopupView customSkillsPopupView, CustomSkillsPopupView customSkillsPopupView2, ConstraintLayout constraintLayout, View view, CustomScrollView customScrollView2) {
        this.rootView = customScrollView;
        this.cspvContact = customSkillsPopupView;
        this.cspvDial = customSkillsPopupView2;
        this.customPhone = constraintLayout;
        this.phoneBottom = view;
        this.svPhone = customScrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CustomScrollView getRoot() {
        return this.rootView;
    }

    public static ItemSkillsPhoneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSkillsPhoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_skills_phone, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemSkillsPhoneBinding bind(View view) {
        int i = R.id.cspv_contact;
        CustomSkillsPopupView customSkillsPopupView = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_contact);
        if (customSkillsPopupView != null) {
            i = R.id.cspv_dial;
            CustomSkillsPopupView customSkillsPopupView2 = (CustomSkillsPopupView) ViewBindings.findChildViewById(view, R.id.cspv_dial);
            if (customSkillsPopupView2 != null) {
                i = R.id.custom_phone;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.custom_phone);
                if (constraintLayout != null) {
                    i = R.id.phone_bottom;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.phone_bottom);
                    if (viewFindChildViewById != null) {
                        CustomScrollView customScrollView = (CustomScrollView) view;
                        return new ItemSkillsPhoneBinding(customScrollView, customSkillsPopupView, customSkillsPopupView2, constraintLayout, viewFindChildViewById, customScrollView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
