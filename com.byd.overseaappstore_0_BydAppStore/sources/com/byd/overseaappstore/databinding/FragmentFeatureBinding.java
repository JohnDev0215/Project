package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.CustomViewPager;
import com.byd.overseaappstore.ui.view.custom.PullToRefreshView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinConstraintLayout;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentFeatureBinding implements ViewBinding {

    @NonNull
    public final SkinConstraintLayout a;

    @NonNull
    public final LayoutFeatureFragmentHeadBinding b;

    @NonNull
    public final SkinAppCompatImageView c;

    @NonNull
    public final SkinAppCompatImageView d;

    @NonNull
    public final SkinProgressBar e;

    @NonNull
    public final PullToRefreshView f;

    @NonNull
    public final RecyclerView g;

    @NonNull
    public final RecyclerView h;

    @NonNull
    public final SkinTextView i;

    @NonNull
    public final SkinTextView j;

    @NonNull
    public final CustomViewPager k;

    private FragmentFeatureBinding(@NonNull SkinConstraintLayout skinConstraintLayout, @NonNull LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull NestedScrollView nestedScrollView, @NonNull LinearLayout linearLayout, @NonNull SkinProgressBar skinProgressBar, @NonNull PullToRefreshView pullToRefreshView, @NonNull SkinConstraintLayout skinConstraintLayout2, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull Space space, @NonNull Space space2, @Nullable Space space3, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull CustomViewPager customViewPager) {
        this.a = skinConstraintLayout;
        this.b = layoutFeatureFragmentHeadBinding;
        this.c = skinAppCompatImageView;
        this.d = skinAppCompatImageView2;
        this.e = skinProgressBar;
        this.f = pullToRefreshView;
        this.g = recyclerView;
        this.h = recyclerView2;
        this.i = skinTextView;
        this.j = skinTextView2;
        this.k = customViewPager;
    }

    @NonNull
    public static FragmentFeatureBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_feature, (ViewGroup) null, false);
        int i = R.id.i_feature_head;
        View viewFindChildViewById = ViewBindings.findChildViewById(viewInflate, R.id.i_feature_head);
        if (viewFindChildViewById != null) {
            LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBindingA = LayoutFeatureFragmentHeadBinding.a(viewFindChildViewById);
            i = R.id.iv_feature_error;
            SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_feature_error);
            if (skinAppCompatImageView != null) {
                i = R.id.iv_mid_cut_line;
                SkinAppCompatImageView skinAppCompatImageView2 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_mid_cut_line);
                if (skinAppCompatImageView2 != null) {
                    i = R.id.ll_feature_item;
                    NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(viewInflate, R.id.ll_feature_item);
                    if (nestedScrollView != null) {
                        i = R.id.ll_feature_wrap;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(viewInflate, R.id.ll_feature_wrap);
                        if (linearLayout != null) {
                            i = R.id.pb_loading_bar;
                            SkinProgressBar skinProgressBar = (SkinProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_loading_bar);
                            if (skinProgressBar != null) {
                                i = R.id.prsv_feature;
                                PullToRefreshView pullToRefreshView = (PullToRefreshView) ViewBindings.findChildViewById(viewInflate, R.id.prsv_feature);
                                if (pullToRefreshView != null) {
                                    SkinConstraintLayout skinConstraintLayout = (SkinConstraintLayout) viewInflate;
                                    i = R.id.rv_feature_category;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_feature_category);
                                    if (recyclerView != null) {
                                        i = R.id.rv_feature_center;
                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_feature_center);
                                        if (recyclerView2 != null) {
                                            i = R.id.s_bottom_state_bar;
                                            Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                                            if (space != null) {
                                                i = R.id.s_feature_anchor_bottom_end;
                                                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_feature_anchor_bottom_end);
                                                if (space2 != null) {
                                                    Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                                                    i = R.id.tv_feature_error;
                                                    SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_feature_error);
                                                    if (skinTextView != null) {
                                                        i = R.id.tv_loading;
                                                        SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_loading);
                                                        if (skinTextView2 != null) {
                                                            i = R.id.vp_banner;
                                                            CustomViewPager customViewPager = (CustomViewPager) ViewBindings.findChildViewById(viewInflate, R.id.vp_banner);
                                                            if (customViewPager != null) {
                                                                return new FragmentFeatureBinding(skinConstraintLayout, layoutFeatureFragmentHeadBindingA, skinAppCompatImageView, skinAppCompatImageView2, nestedScrollView, linearLayout, skinProgressBar, pullToRefreshView, skinConstraintLayout, recyclerView, recyclerView2, space, space2, space3, skinTextView, skinTextView2, customViewPager);
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
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
