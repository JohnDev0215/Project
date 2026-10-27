package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import com.byd.overseaappstore.ui.view.custom.HorizontalRecycleView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentSoftwareDetailBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final SkinAppCompatImageView b;

    @NonNull
    public final ImageView c;

    @NonNull
    public final SkinImageView d;

    @NonNull
    public final ImageView e;

    @NonNull
    public final DownloadProgressButtonView f;

    @NonNull
    public final DownloadProgressButtonView g;

    @NonNull
    public final SkinProgressBar h;

    @NonNull
    public final NestedScrollView i;

    @NonNull
    public final HorizontalRecycleView j;

    @NonNull
    public final SkinTextView k;

    @NonNull
    public final SkinTextView l;

    @NonNull
    public final SkinTextView m;

    @NonNull
    public final SkinTextView n;

    @NonNull
    public final SkinTextView o;

    @NonNull
    public final SkinTextView p;

    @NonNull
    public final SkinTextView q;

    @NonNull
    public final SkinTextView r;

    @NonNull
    public final TextView s;

    @NonNull
    public final TextView t;

    @NonNull
    public final SkinTextView u;

    private FragmentSoftwareDetailBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull SkinImageView skinImageView, @NonNull SkinImageView skinImageView2, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull ImageView imageView, @NonNull SkinImageView skinImageView3, @NonNull ImageView imageView2, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull DownloadProgressButtonView downloadProgressButtonView2, @NonNull SkinProgressBar skinProgressBar, @NonNull NestedScrollView nestedScrollView, @NonNull HorizontalRecycleView horizontalRecycleView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull Space space4, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinTextView skinTextView3, @NonNull SkinTextView skinTextView4, @NonNull SkinTextView skinTextView5, @NonNull SkinTextView skinTextView6, @NonNull SkinTextView skinTextView7, @NonNull SkinTextView skinTextView8, @NonNull SkinTextView skinTextView9, @NonNull TextView textView, @NonNull TextView textView2, @NonNull SkinTextView skinTextView10, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = skinAppCompatImageView;
        this.c = imageView;
        this.d = skinImageView3;
        this.e = imageView2;
        this.f = downloadProgressButtonView;
        this.g = downloadProgressButtonView2;
        this.h = skinProgressBar;
        this.i = nestedScrollView;
        this.j = horizontalRecycleView;
        this.k = skinTextView;
        this.l = skinTextView2;
        this.m = skinTextView3;
        this.n = skinTextView5;
        this.o = skinTextView6;
        this.p = skinTextView7;
        this.q = skinTextView8;
        this.r = skinTextView9;
        this.s = textView;
        this.t = textView2;
        this.u = skinTextView10;
    }

    @NonNull
    public static FragmentSoftwareDetailBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_software_detail, (ViewGroup) null, false);
        int i = R.id.cl_wrap;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(viewInflate, R.id.cl_wrap);
        if (constraintLayout != null) {
            i = R.id.detail_short_line_1;
            SkinImageView skinImageView = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.detail_short_line_1);
            if (skinImageView != null) {
                i = R.id.detail_short_line_2;
                SkinImageView skinImageView2 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.detail_short_line_2);
                if (skinImageView2 != null) {
                    i = R.id.iv_detail_error;
                    SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_detail_error);
                    if (skinAppCompatImageView != null) {
                        i = R.id.iv_icon;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_icon);
                        if (imageView != null) {
                            i = R.id.iv_introduction_fold_button;
                            SkinImageView skinImageView3 = (SkinImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_introduction_fold_button);
                            if (skinImageView3 != null) {
                                i = R.id.iv_version_introduction_fold_button;
                                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_version_introduction_fold_button);
                                if (imageView2 != null) {
                                    i = R.id.pb_app_detail_download_progress_button;
                                    DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.pb_app_detail_download_progress_button);
                                    if (downloadProgressButtonView != null) {
                                        i = R.id.pb_app_detail_download_progress_button_top;
                                        DownloadProgressButtonView downloadProgressButtonView2 = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.pb_app_detail_download_progress_button_top);
                                        if (downloadProgressButtonView2 != null) {
                                            i = R.id.pb_loading_bar;
                                            SkinProgressBar skinProgressBar = (SkinProgressBar) ViewBindings.findChildViewById(viewInflate, R.id.pb_loading_bar);
                                            if (skinProgressBar != null) {
                                                i = R.id.rsv_detail;
                                                NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(viewInflate, R.id.rsv_detail);
                                                if (nestedScrollView != null) {
                                                    i = R.id.rv_software_detail_preview_images;
                                                    HorizontalRecycleView horizontalRecycleView = (HorizontalRecycleView) ViewBindings.findChildViewById(viewInflate, R.id.rv_software_detail_preview_images);
                                                    if (horizontalRecycleView != null) {
                                                        i = R.id.s_anchor_bottom_end;
                                                        Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_anchor_bottom_end);
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
                                                                        i = R.id.tv_app_orientation;
                                                                        SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_app_orientation);
                                                                        if (skinTextView != null) {
                                                                            i = R.id.tv_detail_error;
                                                                            SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_detail_error);
                                                                            if (skinTextView2 != null) {
                                                                                i = R.id.tv_introduction;
                                                                                SkinTextView skinTextView3 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_introduction);
                                                                                if (skinTextView3 != null) {
                                                                                    i = R.id.tv_introduction_title;
                                                                                    SkinTextView skinTextView4 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_introduction_title);
                                                                                    if (skinTextView4 != null) {
                                                                                        i = R.id.tv_loading;
                                                                                        SkinTextView skinTextView5 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_loading);
                                                                                        if (skinTextView5 != null) {
                                                                                            i = R.id.tv_name;
                                                                                            SkinTextView skinTextView6 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_name);
                                                                                            if (skinTextView6 != null) {
                                                                                                i = R.id.tv_size_tip;
                                                                                                SkinTextView skinTextView7 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_size_tip);
                                                                                                if (skinTextView7 != null) {
                                                                                                    i = R.id.tv_software_detail_theme_name;
                                                                                                    SkinTextView skinTextView8 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_software_detail_theme_name);
                                                                                                    if (skinTextView8 != null) {
                                                                                                        i = R.id.tv_update_time_tip;
                                                                                                        SkinTextView skinTextView9 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_update_time_tip);
                                                                                                        if (skinTextView9 != null) {
                                                                                                            i = R.id.tv_version_introduction;
                                                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_version_introduction);
                                                                                                            if (textView != null) {
                                                                                                                i = R.id.tv_version_introduction_title;
                                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_version_introduction_title);
                                                                                                                if (textView2 != null) {
                                                                                                                    i = R.id.tv_version_tip;
                                                                                                                    SkinTextView skinTextView10 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_version_tip);
                                                                                                                    if (skinTextView10 != null) {
                                                                                                                        i = R.id.v_detail_background;
                                                                                                                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_detail_background);
                                                                                                                        if (skinView != null) {
                                                                                                                            return new FragmentSoftwareDetailBinding((ConstraintLayout) viewInflate, constraintLayout, skinImageView, skinImageView2, skinAppCompatImageView, imageView, skinImageView3, imageView2, downloadProgressButtonView, downloadProgressButtonView2, skinProgressBar, nestedScrollView, horizontalRecycleView, space, space2, space3, space4, skinTextView, skinTextView2, skinTextView3, skinTextView4, skinTextView5, skinTextView6, skinTextView7, skinTextView8, skinTextView9, textView, textView2, skinTextView10, skinView);
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
