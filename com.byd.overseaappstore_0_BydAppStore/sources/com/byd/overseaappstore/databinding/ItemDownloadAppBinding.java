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
public final class ItemDownloadAppBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final Group b;

    @NonNull
    public final Group c;

    @NonNull
    public final Group d;

    @NonNull
    public final SkinAppCompatImageView e;

    @NonNull
    public final ImageView f;

    @NonNull
    public final SkinContentLoadingProgressBar g;

    @NonNull
    public final DownloadProgressButtonView h;

    @NonNull
    public final SkinAppCompatTextView i;

    @NonNull
    public final SkinAppCompatTextView j;

    @NonNull
    public final SkinAppCompatTextView k;

    private ItemDownloadAppBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Group group, @NonNull Group group2, @NonNull Group group3, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull ImageView imageView, @NonNull SkinContentLoadingProgressBar skinContentLoadingProgressBar, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = group;
        this.c = group2;
        this.d = group3;
        this.e = skinAppCompatImageView;
        this.f = imageView;
        this.g = skinContentLoadingProgressBar;
        this.h = downloadProgressButtonView;
        this.i = skinAppCompatTextView;
        this.j = skinAppCompatTextView2;
        this.k = skinAppCompatTextView3;
    }

    @NonNull
    public static ItemDownloadAppBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_download_app, viewGroup, false);
        int i = R.id.g_download_progressbar;
        Group group = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_download_progressbar);
        if (group != null) {
            i = R.id.g_download_state_show;
            Group group2 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_download_state_show);
            if (group2 != null) {
                i = R.id.g_download_state_working;
                Group group3 = (Group) ViewBindings.findChildViewById(viewInflate, R.id.g_download_state_working);
                if (group3 != null) {
                    i = R.id.iv_download_cancel;
                    SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_download_cancel);
                    if (skinAppCompatImageView != null) {
                        i = R.id.iv_download_icon;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_download_icon);
                        if (imageView != null) {
                            i = R.id.pb_download_progress;
                            SkinContentLoadingProgressBar skinContentLoadingProgressBar = (SkinContentLoadingProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_download_progress);
                            if (skinContentLoadingProgressBar != null) {
                                i = R.id.pb_manager_download_button;
                                DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.pb_manager_download_button);
                                if (downloadProgressButtonView != null) {
                                    i = R.id.s_download_end_bottom;
                                    Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_download_end_bottom);
                                    if (space != null) {
                                        i = R.id.s_download_panel;
                                        Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_download_panel);
                                        if (space2 != null) {
                                            i = R.id.s_download_start_top;
                                            Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_download_start_top);
                                            if (space3 != null) {
                                                i = R.id.tv_download_info_area;
                                                SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_download_info_area);
                                                if (skinAppCompatTextView != null) {
                                                    i = R.id.tv_download_name;
                                                    SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_download_name);
                                                    if (skinAppCompatTextView2 != null) {
                                                        i = R.id.tv_download_size;
                                                        SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_download_size);
                                                        if (skinAppCompatTextView3 != null) {
                                                            i = R.id.v_download_divider;
                                                            SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_download_divider);
                                                            if (skinView != null) {
                                                                i = R.id.v_download_line;
                                                                SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_download_line);
                                                                if (skinView2 != null) {
                                                                    return new ItemDownloadAppBinding((ConstraintLayout) viewInflate, group, group2, group3, skinAppCompatImageView, imageView, skinContentLoadingProgressBar, downloadProgressButtonView, space, space2, space3, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3, skinView, skinView2);
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
