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
public final class ItemSearchResultBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final DownloadProgressButtonView b;

    @NonNull
    public final ImageView c;

    @NonNull
    public final SkinAppCompatTextView d;

    @NonNull
    public final SkinAppCompatTextView e;

    @NonNull
    public final SkinAppCompatTextView f;

    private ItemSearchResultBinding(@NonNull ConstraintLayout constraintLayout, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull ImageView imageView, @NonNull Space space, @NonNull Space space2, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinAppCompatTextView skinAppCompatTextView3, @NonNull SkinView skinView, @NonNull SkinView skinView2) {
        this.a = constraintLayout;
        this.b = downloadProgressButtonView;
        this.c = imageView;
        this.d = skinAppCompatTextView;
        this.e = skinAppCompatTextView2;
        this.f = skinAppCompatTextView3;
    }

    @NonNull
    public static ItemSearchResultBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_search_result, viewGroup, false);
        int i = R.id.dpb_operate;
        DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.dpb_operate);
        if (downloadProgressButtonView != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_icon);
            if (imageView != null) {
                i = R.id.s_search_result_app_bottom_end;
                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_search_result_app_bottom_end);
                if (space != null) {
                    i = R.id.s_search_result_app_top_start;
                    Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_search_result_app_top_start);
                    if (space2 != null) {
                        i = R.id.tv_name;
                        SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_name);
                        if (skinAppCompatTextView != null) {
                            i = R.id.tv_size;
                            SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_size);
                            if (skinAppCompatTextView2 != null) {
                                i = R.id.tv_theme;
                                SkinAppCompatTextView skinAppCompatTextView3 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_theme);
                                if (skinAppCompatTextView3 != null) {
                                    i = R.id.v_search_result_app_click_hot_spot;
                                    SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_search_result_app_click_hot_spot);
                                    if (skinView != null) {
                                        i = R.id.v_topic_detail_app_theme_boundary;
                                        SkinView skinView2 = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_topic_detail_app_theme_boundary);
                                        if (skinView2 != null) {
                                            return new ItemSearchResultBinding((ConstraintLayout) viewInflate, downloadProgressButtonView, imageView, space, space2, skinAppCompatTextView, skinAppCompatTextView2, skinAppCompatTextView3, skinView, skinView2);
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
