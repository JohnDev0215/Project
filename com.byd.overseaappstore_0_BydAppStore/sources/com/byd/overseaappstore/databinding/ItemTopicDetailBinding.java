package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemTopicDetailBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    @NonNull
    public final DownloadProgressButtonView c;

    @NonNull
    public final SkinAppCompatTextView d;

    @NonNull
    public final SkinAppCompatTextView e;

    @NonNull
    public final SkinAppCompatTextView f;

    @NonNull
    public final SkinView g;

    private ItemTopicDetailBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull Space space, @NonNull Space space2, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2, @NonNull SkinView skinView3) {
        this.a = constraintLayout;
        this.b = imageView;
        this.c = downloadProgressButtonView;
        this.d = skinAppCompatTextView;
        this.e = skinAppCompatTextView2;
        this.f = skinAppCompatTextView3;
        this.g = skinView2;
    }

    @NonNull
    public static ItemTopicDetailBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_topic_detail, viewGroup, false);
        int i = R.id.iv_topic_detail_app_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_topic_detail_app_icon);
        if (imageView != null) {
            i = R.id.pb_topic_detail_app_button;
            DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.pb_topic_detail_app_button);
            if (downloadProgressButtonView != null) {
                i = R.id.s_topic_detail_app_bottom_end;
                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_topic_detail_app_bottom_end);
                if (space != null) {
                    i = R.id.s_topic_detail_app_top_start;
                    Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_topic_detail_app_top_start);
                    if (space2 != null) {
                        i = R.id.tv_topic_detail_app_name;
                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_topic_detail_app_name);
                        if (skinAppCompatTextView != null) {
                            i = R.id.tv_topic_detail_app_size;
                            SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_topic_detail_app_size);
                            if (skinAppCompatTextView2 != null) {
                                i = R.id.tv_topic_detail_app_theme;
                                SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_topic_detail_app_theme);
                                if (skinAppCompatTextView3 != null) {
                                    i = R.id.v_topic_detail_app_boundary;
                                    SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_topic_detail_app_boundary);
                                    if (skinView != null) {
                                        i = R.id.v_topic_detail_app_click_hot_spot;
                                        SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_topic_detail_app_click_hot_spot);
                                        if (skinView2 != null) {
                                            i = R.id.v_topic_detail_app_theme_boundary;
                                            SkinView skinView3 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_topic_detail_app_theme_boundary);
                                            if (skinView3 != null) {
                                                return new ItemTopicDetailBinding((ConstraintLayout) viewInflate, imageView, downloadProgressButtonView, space, space2, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3, skinView, skinView2, skinView3);
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
