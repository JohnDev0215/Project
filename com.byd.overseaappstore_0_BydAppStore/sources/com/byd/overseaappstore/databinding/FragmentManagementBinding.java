package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentManagementBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinImageView b;

    @NonNull
    public final ConstraintLayout c;

    @NonNull
    public final TabLayout d;

    @NonNull
    public final ViewPager2 e;

    private FragmentManagementBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinImageView skinImageView, @NonNull ConstraintLayout constraintLayout2, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull TabLayout tabLayout, @NonNull SkinView skinView, @NonNull ViewPager2 viewPager2) {
        this.a = constraintLayout;
        this.b = skinImageView;
        this.c = constraintLayout2;
        this.d = tabLayout;
        this.e = viewPager2;
    }

    @NonNull
    public static FragmentManagementBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_management, (ViewGroup) null, false);
        int i = R.id.iv_setting;
        SkinImageView skinImageView = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_setting);
        if (skinImageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
            i = R.id.s_anchor_top_end;
            Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_end);
            if (space != null) {
                i = R.id.s_anchor_top_start;
                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_start);
                if (space2 != null) {
                    i = R.id.s_bottom_state_bar;
                    Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                    if (space3 != null) {
                        i = R.id.s_top_state_bar;
                        Space space4 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                        if (space4 != null) {
                            i = R.id.tl_manager;
                            TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(viewInflate, R.id.tl_manager);
                            if (tabLayout != null) {
                                i = R.id.v_management_background;
                                SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_management_background);
                                if (skinView != null) {
                                    i = R.id.vp_manager_container;
                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(viewInflate, R.id.vp_manager_container);
                                    if (viewPager2 != null) {
                                        return new FragmentManagementBinding(constraintLayout, skinImageView, constraintLayout, space, space2, space3, space4, tabLayout, skinView, viewPager2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
