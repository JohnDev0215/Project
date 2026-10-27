package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.PullToRefreshView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentSearchActiveBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final AppCompatEditText b;

    @NonNull
    public final Group c;

    @NonNull
    public final Group d;

    @NonNull
    public final Group e;

    @NonNull
    public final SkinAppCompatImageView f;

    @NonNull
    public final SkinAppCompatImageView g;

    @NonNull
    public final SkinAppCompatImageView h;

    @NonNull
    public final PullToRefreshView i;

    @NonNull
    public final RecyclerView j;

    @NonNull
    public final RecyclerView k;

    @NonNull
    public final SkinTextView l;

    @NonNull
    public final SkinTextView m;

    @NonNull
    public final View n;

    private FragmentSearchActiveBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatEditText appCompatEditText, @NonNull Group group, @NonNull Group group2, @NonNull Group group3, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull SkinAppCompatImageView skinAppCompatImageView3, @NonNull SkinAppCompatImageView skinAppCompatImageView4, @NonNull SkinAppCompatImageView skinAppCompatImageView5, @NonNull PullToRefreshView pullToRefreshView, @NonNull ConstraintLayout constraintLayout2, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull Space space5, @NonNull Space space6, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull View view, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = appCompatEditText;
        this.c = group;
        this.d = group2;
        this.e = group3;
        this.f = skinAppCompatImageView;
        this.g = skinAppCompatImageView2;
        this.h = skinAppCompatImageView3;
        this.i = pullToRefreshView;
        this.j = recyclerView;
        this.k = recyclerView2;
        this.l = skinTextView;
        this.m = skinTextView3;
        this.n = view;
    }

    @NonNull
    public static FragmentSearchActiveBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_search_active, (ViewGroup) null, false);
        int i = R.id.edit_bar;
        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(viewInflate, R.id.edit_bar);
        if (appCompatEditText != null) {
            i = R.id.g_search_error;
            Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_search_error);
            if (group != null) {
                i = R.id.g_search_hot_search;
                Group group2 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_search_hot_search);
                if (group2 != null) {
                    i = R.id.g_search_search_result;
                    Group group3 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_search_search_result);
                    if (group3 != null) {
                        i = R.id.ic_no_search_results;
                        SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.ic_no_search_results);
                        if (skinAppCompatImageView != null) {
                            i = R.id.iv_back;
                            SkinAppCompatImageView skinAppCompatImageView2 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_back);
                            if (skinAppCompatImageView2 != null) {
                                i = R.id.iv_delete_icon;
                                SkinAppCompatImageView skinAppCompatImageView3 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_delete_icon);
                                if (skinAppCompatImageView3 != null) {
                                    i = R.id.iv_edit_bar;
                                    SkinAppCompatImageView skinAppCompatImageView4 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_edit_bar);
                                    if (skinAppCompatImageView4 != null) {
                                        i = R.id.iv_search_icon;
                                        SkinAppCompatImageView skinAppCompatImageView5 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_search_icon);
                                        if (skinAppCompatImageView5 != null) {
                                            i = R.id.pull_to_refresh_view;
                                            PullToRefreshView pullToRefreshView = (PullToRefreshView) ViewBindings.findChildViewById(viewInflate, R.id.pull_to_refresh_view);
                                            if (pullToRefreshView != null) {
                                                ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                                                i = R.id.rv_search_hot_search_tag;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_search_hot_search_tag);
                                                if (recyclerView != null) {
                                                    i = R.id.rvSearchResult;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rvSearchResult);
                                                    if (recyclerView2 != null) {
                                                        i = R.id.s_anchor_bottom_end;
                                                        Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_bottom_end);
                                                        if (space != null) {
                                                            i = R.id.s_anchor_bottom_left;
                                                            Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_bottom_left);
                                                            if (space2 != null) {
                                                                i = R.id.s_anchor_top_right;
                                                                Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_right);
                                                                if (space3 != null) {
                                                                    i = R.id.s_anchor_top_start;
                                                                    Space space4 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_start);
                                                                    if (space4 != null) {
                                                                        i = R.id.s_bottom_state_bar;
                                                                        Space space5 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_bottom_state_bar);
                                                                        if (space5 != null) {
                                                                            i = R.id.s_top_state_bar;
                                                                            Space space6 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_top_state_bar);
                                                                            if (space6 != null) {
                                                                                i = R.id.tv_no_search_results;
                                                                                SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_no_search_results);
                                                                                if (skinTextView != null) {
                                                                                    i = R.id.tv_search_hot_search;
                                                                                    SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_search_hot_search);
                                                                                    if (skinTextView2 != null) {
                                                                                        i = R.id.tv_search_result_tip;
                                                                                        SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_search_result_tip);
                                                                                        if (skinTextView3 != null) {
                                                                                            i = R.id.v_blank;
                                                                                            View viewFindChildViewById = ViewBindings.findChildViewById(viewInflate, R.id.v_blank);
                                                                                            if (viewFindChildViewById != null) {
                                                                                                i = R.id.v_search_background;
                                                                                                SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_search_background);
                                                                                                if (skinView != null) {
                                                                                                    return new FragmentSearchActiveBinding(constraintLayout, appCompatEditText, group, group2, group3, skinAppCompatImageView, skinAppCompatImageView2, skinAppCompatImageView3, skinAppCompatImageView4, skinAppCompatImageView5, pullToRefreshView, constraintLayout, recyclerView, recyclerView2, space, space2, space3, space4, space5, space6, skinTextView, skinTextView2, skinTextView3, viewFindChildViewById, skinView);
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
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(i)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
