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
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentDownloadListBinding implements ViewBinding {

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
    public final SkinTextView j;

    @NonNull
    public final SkinTextView k;

    @NonNull
    public final SkinAppCompatTextView l;

    private FragmentDownloadListBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Group group, @NonNull Group group2, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull SkinAppCompatImageView skinAppCompatImageView2, @NonNull SkinAppCompatImageView skinAppCompatImageView3, @NonNull ConstraintLayout constraintLayout2, @NonNull RecyclerView recyclerView, @NonNull Space space, @NonNull Space space2, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView) {
        this.a = constraintLayout;
        this.b = group;
        this.c = group2;
        this.d = skinAppCompatImageView;
        this.e = skinAppCompatImageView2;
        this.f = skinAppCompatImageView3;
        this.g = constraintLayout2;
        this.h = recyclerView;
        this.i = space;
        this.j = skinTextView;
        this.k = skinTextView2;
        this.l = skinAppCompatTextView;
    }

    @NonNull
    public static FragmentDownloadListBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_download_list, (ViewGroup) null, false);
        int i = R.id.g_error;
        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_error);
        if (group != null) {
            i = R.id.g_start_pause_all;
            Group group2 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_start_pause_all);
            if (group2 != null) {
                i = R.id.iv_all_start;
                SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_all_start);
                if (skinAppCompatImageView != null) {
                    i = R.id.iv_all_stop;
                    SkinAppCompatImageView skinAppCompatImageView2 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_all_stop);
                    if (skinAppCompatImageView2 != null) {
                        i = R.id.iv_download_null;
                        SkinAppCompatImageView skinAppCompatImageView3 = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_download_null);
                        if (skinAppCompatImageView3 != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
                            i = R.id.rv_download_list;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_download_list);
                            if (recyclerView != null) {
                                i = R.id.s_anchor_top_end;
                                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_end);
                                if (space != null) {
                                    i = R.id.s_anchor_top_start;
                                    Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_start);
                                    if (space2 != null) {
                                        i = R.id.tv_all_start;
                                        SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_all_start);
                                        if (skinTextView != null) {
                                            i = R.id.tv_all_stop;
                                            SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_all_stop);
                                            if (skinTextView2 != null) {
                                                i = R.id.tv_download_reminder;
                                                SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_download_reminder);
                                                if (skinAppCompatTextView != null) {
                                                    return new FragmentDownloadListBinding(constraintLayout, group, group2, skinAppCompatImageView, skinAppCompatImageView2, skinAppCompatImageView3, constraintLayout, recyclerView, space, space2, skinTextView, skinTextView2, skinAppCompatTextView);
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
