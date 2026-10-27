package com.byd.overseaappstore.databinding;

import android.view.View;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutFeatureFragmentHeadBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinAppCompatImageView b;

    @NonNull
    public final Space c;

    @NonNull
    public final Space d;

    @NonNull
    public final SkinTextView e;

    @NonNull
    public final SkinTextView f;

    @NonNull
    public final SkinView g;

    private LayoutFeatureFragmentHeadBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = skinAppCompatImageView;
        this.c = space;
        this.d = space2;
        this.e = skinTextView;
        this.f = skinTextView2;
        this.g = skinView;
    }

    @NonNull
    public static LayoutFeatureFragmentHeadBinding a(@NonNull View view) {
        int i = R.id.iv_down_load;
        SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(view, R.id.iv_down_load);
        if (skinAppCompatImageView != null) {
            i = R.id.iv_enter_search;
            SkinAppCompatImageView skinAppCompatImageView2 = (SkinAppCompatImageView) ViewBindings.findChildViewById(view, R.id.iv_enter_search);
            if (skinAppCompatImageView2 != null) {
                i = R.id.s_between_search_and_download;
                Space space = (Space) ViewBindings.findChildViewById(view, R.id.s_between_search_and_download);
                if (space != null) {
                    i = R.id.s_top_left_space;
                    Space space2 = (Space) ViewBindings.findChildViewById(view, R.id.s_top_left_space);
                    if (space2 != null) {
                        i = R.id.s_top_state_bar;
                        Space space3 = (Space) ViewBindings.findChildViewById(view, R.id.s_top_state_bar);
                        if (space3 != null) {
                            i = R.id.tv_enter_search;
                            SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(view, R.id.tv_enter_search);
                            if (skinTextView != null) {
                                i = R.id.tv_feature_download_red_point;
                                SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(view, R.id.tv_feature_download_red_point);
                                if (skinTextView2 != null) {
                                    i = R.id.v_down_load_area;
                                    SkinView skinView = (SkinView) ViewBindings.findChildViewById(view, R.id.v_down_load_area);
                                    if (skinView != null) {
                                        i = R.id.v_feature_download_red_point;
                                        SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(view, R.id.v_feature_download_red_point);
                                        if (skinView2 != null) {
                                            return new LayoutFeatureFragmentHeadBinding((ConstraintLayout) view, skinAppCompatImageView, skinAppCompatImageView2, space, space2, space3, skinTextView, skinTextView2, skinView, skinView2);
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

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
