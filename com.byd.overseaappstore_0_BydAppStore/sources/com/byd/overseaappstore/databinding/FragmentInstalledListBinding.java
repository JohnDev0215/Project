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
public final class FragmentInstalledListBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final Group b;

    @NonNull
    public final SkinAppCompatImageView c;

    @NonNull
    public final RecyclerView d;

    @NonNull
    public final SkinAppCompatTextView e;

    private FragmentInstalledListBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Group group, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull RecyclerView recyclerView, @NonNull Space space, @NonNull Space space2, @NonNull SkinAppCompatTextView skinAppCompatTextView) {
        this.a = constraintLayout;
        this.b = group;
        this.c = skinAppCompatImageView;
        this.d = recyclerView;
        this.e = skinAppCompatTextView;
    }

    @NonNull
    public static FragmentInstalledListBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_installed_list, (ViewGroup) null, false);
        int i = R.id.g_error;
        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_error);
        if (group != null) {
            i = R.id.iv_un_install_app_null;
            SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_un_install_app_null);
            if (skinAppCompatImageView != null) {
                i = R.id.rv_installed_list;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_installed_list);
                if (recyclerView != null) {
                    i = R.id.s_anchor_top_end;
                    Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_end);
                    if (space != null) {
                        i = R.id.s_anchor_top_start;
                        Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_top_start);
                        if (space2 != null) {
                            i = R.id.tv_un_install_reminder;
                            SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_un_install_reminder);
                            if (skinAppCompatTextView != null) {
                                return new FragmentInstalledListBinding((ConstraintLayout) viewInflate, group, skinAppCompatImageView, recyclerView, space, space2, skinAppCompatTextView);
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
