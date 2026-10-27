package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentSoftwareDetailPreviewImageBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinAppCompatImageView b;

    @NonNull
    public final LinearLayout c;

    @NonNull
    public final ConstraintLayout d;

    @NonNull
    public final ViewPager2 e;

    private FragmentSoftwareDetailPreviewImageBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull LinearLayout linearLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull SkinView skinView, @NonNull ViewPager2 viewPager2) {
        this.a = constraintLayout;
        this.b = skinAppCompatImageView;
        this.c = linearLayout;
        this.d = constraintLayout2;
        this.e = viewPager2;
    }

    @NonNull
    public static FragmentSoftwareDetailPreviewImageBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_software_detail_preview_image, (ViewGroup) null, false);
        int i = R.id.iv_app_detail_preview_back;
        SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_app_detail_preview_back);
        if (skinAppCompatImageView != null) {
            i = R.id.ll_app_detail_bottom_page_number_view;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(viewInflate, R.id.ll_app_detail_bottom_page_number_view);
            if (linearLayout != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                i = R.id.s_app_detail_anchor_bottom_end;
                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_app_detail_anchor_bottom_end);
                if (space != null) {
                    i = R.id.s_app_detail_anchor_top_start;
                    Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_app_detail_anchor_top_start);
                    if (space2 != null) {
                        i = R.id.s_bottom_state_bar;
                        Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                        if (space3 != null) {
                            i = R.id.s_top_state_bar;
                            Space space4 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                            if (space4 != null) {
                                i = R.id.v_detail_preview_background;
                                SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_detail_preview_background);
                                if (skinView != null) {
                                    i = R.id.vp_app_detail_preview_images;
                                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(viewInflate, R.id.vp_app_detail_preview_images);
                                    if (viewPager2 != null) {
                                        return new FragmentSoftwareDetailPreviewImageBinding(constraintLayout, skinAppCompatImageView, linearLayout, constraintLayout, space, space2, space3, space4, skinView, viewPager2);
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
