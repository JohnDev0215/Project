package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentUpdateListBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final Group b;

    @NonNull
    public final Group c;

    @NonNull
    public final SkinAppCompatImageView d;

    @NonNull
    public final SkinAppCompatImageView e;

    @NonNull
    public final SkinAppCompatImageView f;

    @NonNull
    public final ConstraintLayout g;

    @NonNull
    public final RecyclerView h;

    @NonNull
    public final Space i;

    @NonNull
    public final SkinAppCompatTextView j;

    @NonNull
    public final SkinAppCompatTextView k;

    @NonNull
    public final SkinAppCompatTextView l;

    private FragmentUpdateListBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Group group, @NonNull Group group2, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull SkinAppCompatImageView skinAppCompatImageView3, @NonNull ConstraintLayout constraintLayout2, @NonNull RecyclerView recyclerView, @NonNull Space space, @NonNull Space space2, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3) {
        this.a = constraintLayout;
        this.b = group;
        this.c = group2;
        this.d = skinAppCompatImageView;
        this.e = skinAppCompatImageView2;
        this.f = skinAppCompatImageView3;
        this.g = constraintLayout2;
        this.h = recyclerView;
        this.i = space;
        this.j = skinAppCompatTextView;
        this.k = skinAppCompatTextView2;
        this.l = skinAppCompatTextView3;
    }

    @NonNull
    public static FragmentUpdateListBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_update_list, (ViewGroup) null, false);
        int i = R.id.g_error;
        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_error);
        if (group != null) {
            i = R.id.g_start_pause_all;
            Group group2 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_start_pause_all);
            if (group2 != null) {
                i = R.id.iv_update_all_start;
                SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_update_all_start);
                if (skinAppCompatImageView != null) {
                    i = R.id.iv_update_all_stop;
                    SkinAppCompatImageView skinAppCompatImageView2 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_update_all_stop);
                    if (skinAppCompatImageView2 != null) {
                        i = R.id.iv_update_null;
                        SkinAppCompatImageView skinAppCompatImageView3 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_update_null);
                        if (skinAppCompatImageView3 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                            i = R.id.rv_update_list;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_update_list);
                            if (recyclerView != null) {
                                i = R.id.s_update_end_top;
                                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_update_end_top);
                                if (space != null) {
                                    i = R.id.s_update_start_top;
                                    Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_update_start_top);
                                    if (space2 != null) {
                                        i = R.id.tv_update_all_start;
                                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_all_start);
                                        if (skinAppCompatTextView != null) {
                                            i = R.id.tv_update_all_stop;
                                            SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_all_stop);
                                            if (skinAppCompatTextView2 != null) {
                                                i = R.id.tv_update_reminder;
                                                SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_reminder);
                                                if (skinAppCompatTextView3 != null) {
                                                    return new FragmentUpdateListBinding(constraintLayout, group, group2, skinAppCompatImageView, skinAppCompatImageView2, skinAppCompatImageView3, constraintLayout, recyclerView, space, space2, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3);
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
