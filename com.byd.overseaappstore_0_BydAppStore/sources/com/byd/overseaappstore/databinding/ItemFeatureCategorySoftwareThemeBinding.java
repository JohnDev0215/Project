package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemFeatureCategorySoftwareThemeBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    @NonNull
    public final RecyclerView c;

    @NonNull
    public final SkinAppCompatTextView d;

    @NonNull
    public final SkinView e;

    private ItemFeatureCategorySoftwareThemeBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull SkinAppCompatImageView skinAppCompatImageView, @NonNull RecyclerView recyclerView, @NonNull Space space, @NonNull Space space2, @NonNull Space space3, @NonNull SkinAppCompatTextView skinAppCompatTextView, @NonNull SkinAppCompatTextView skinAppCompatTextView2, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = imageView;
        this.c = recyclerView;
        this.d = skinAppCompatTextView2;
        this.e = skinView;
    }

    @NonNull
    public static ItemFeatureCategorySoftwareThemeBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_feature_category_software_theme, viewGroup, false);
        int i = R.id.iv_feature_detail_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_feature_detail_img);
        if (imageView != null) {
            i = R.id.iv_feature_more_icon;
            SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_feature_more_icon);
            if (skinAppCompatImageView != null) {
                i = R.id.rv_software_theme_apps;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(viewInflate, R.id.rv_software_theme_apps);
                if (recyclerView != null) {
                    i = R.id.s_software_theme_anchor_bottom_end;
                    Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_software_theme_anchor_bottom_end);
                    if (space != null) {
                        i = R.id.s_software_theme_anchor_top_start;
                        Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_software_theme_anchor_top_start);
                        if (space2 != null) {
                            i = R.id.s_software_theme_item_panel;
                            Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_software_theme_item_panel);
                            if (space3 != null) {
                                i = R.id.tv_feature_more_text;
                                SkinAppCompatTextView skinAppCompatTextView = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_feature_more_text);
                                if (skinAppCompatTextView != null) {
                                    i = R.id.tv_feature_title;
                                    SkinAppCompatTextView skinAppCompatTextView2 = (SkinAppCompatTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_feature_title);
                                    if (skinAppCompatTextView2 != null) {
                                        i = R.id.v_feature_more_hot_click;
                                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_feature_more_hot_click);
                                        if (skinView != null) {
                                            return new ItemFeatureCategorySoftwareThemeBinding((ConstraintLayout) viewInflate, imageView, skinAppCompatImageView, recyclerView, space, space2, space3, skinAppCompatTextView, skinAppCompatTextView2, skinView);
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
