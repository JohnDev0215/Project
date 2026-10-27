package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinContentLoadingProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemUpdateAppBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final Group b;

    @NonNull
    public final SkinAppCompatImageView c;

    @NonNull
    public final ImageView d;

    @NonNull
    public final SkinContentLoadingProgressBar e;

    @NonNull
    public final DownloadProgressButtonView f;

    @NonNull
    public final SkinAppCompatTextView g;

    @NonNull
    public final SkinAppCompatTextView h;

    @NonNull
    public final SkinAppCompatTextView i;

    private ItemUpdateAppBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Group group, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull ImageView imageView, @NonNull SkinContentLoadingProgressBar skinContentLoadingProgressBar, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = group;
        this.c = skinAppCompatImageView;
        this.d = imageView;
        this.e = skinContentLoadingProgressBar;
        this.f = downloadProgressButtonView;
        this.g = skinAppCompatTextView;
        this.h = skinAppCompatTextView2;
        this.i = skinAppCompatTextView3;
    }

    @NonNull
    public static ItemUpdateAppBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_update_app, viewGroup, false);
        int i = R.id.g_update_state_show;
        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_update_state_show);
        if (group != null) {
            i = R.id.iv_update_cancel;
            SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_update_cancel);
            if (skinAppCompatImageView != null) {
                i = R.id.iv_update_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_update_icon);
                if (imageView != null) {
                    i = R.id.pb_update_progress;
                    SkinContentLoadingProgressBar skinContentLoadingProgressBar = (SkinContentLoadingProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_update_progress);
                    if (skinContentLoadingProgressBar != null) {
                        i = R.id.s_update_end_bottom;
                        Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_update_end_bottom);
                        if (space != null) {
                            i = R.id.s_update_panel;
                            Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_update_panel);
                            if (space2 != null) {
                                i = R.id.s_update_start_top;
                                Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_update_start_top);
                                if (space3 != null) {
                                    i = R.id.tv_update_button;
                                    DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_button);
                                    if (downloadProgressButtonView != null) {
                                        i = R.id.tv_update_info_area;
                                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_info_area);
                                        if (skinAppCompatTextView != null) {
                                            i = R.id.tv_update_name;
                                            SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_name);
                                            if (skinAppCompatTextView2 != null) {
                                                i = R.id.tv_update_size;
                                                SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_size);
                                                if (skinAppCompatTextView3 != null) {
                                                    i = R.id.v_update_divider;
                                                    SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_update_divider);
                                                    if (skinView != null) {
                                                        i = R.id.v_update_line;
                                                        SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_update_line);
                                                        if (skinView2 != null) {
                                                            return new ItemUpdateAppBinding((ConstraintLayout) viewInflate, group, skinAppCompatImageView, imageView, skinContentLoadingProgressBar, space, space2, space3, downloadProgressButtonView, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3, skinView, skinView2);
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
