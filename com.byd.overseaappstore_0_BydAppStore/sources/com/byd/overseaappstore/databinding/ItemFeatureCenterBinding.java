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
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemFeatureCenterBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final DownloadProgressButtonView b;

    @NonNull
    public final ImageView c;

    @NonNull
    public final SkinTextView d;

    @NonNull
    public final SkinTextView e;

    @NonNull
    public final SkinView f;

    private ItemFeatureCenterBinding(@NonNull ConstraintLayout constraintLayout, @NonNull DownloadProgressButtonView downloadProgressButtonView, @NonNull ImageView imageView, @Nullable Space space, @Nullable Space space2, @Nullable Space space3, @NonNull SkinTextView skinTextView, @NonNull SkinTextView skinTextView2, @NonNull SkinView skinView) {
        this.a = constraintLayout;
        this.b = downloadProgressButtonView;
        this.c = imageView;
        this.d = skinTextView;
        this.e = skinTextView2;
        this.f = skinView;
    }

    @NonNull
    public static ItemFeatureCenterBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_feature_center, viewGroup, false);
        int i = R.id.iv_down_load;
        DownloadProgressButtonView downloadProgressButtonView = (DownloadProgressButtonView) ViewBindings.findChildViewById(viewInflate, R.id.iv_down_load);
        if (downloadProgressButtonView != null) {
            i = R.id.iv_icon;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_icon);
            if (imageView != null) {
                Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_item_feature_center_end_bottom);
                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_item_feature_center_panel);
                Space space3 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_item_feature_center_top_start);
                i = R.id.tv_name;
                SkinTextView skinTextView = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_name);
                if (skinTextView != null) {
                    i = R.id.tv_size;
                    SkinTextView skinTextView2 = (SkinTextView) ViewBindings.findChildViewById(viewInflate, R.id.tv_size);
                    if (skinTextView2 != null) {
                        i = R.id.v_click_hot_spot;
                        SkinView skinView = (SkinView) ViewBindings.findChildViewById(viewInflate, R.id.v_click_hot_spot);
                        if (skinView != null) {
                            return new ItemFeatureCenterBinding((ConstraintLayout) viewInflate, downloadProgressButtonView, imageView, space, space2, space3, skinTextView, skinTextView2, skinView);
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
