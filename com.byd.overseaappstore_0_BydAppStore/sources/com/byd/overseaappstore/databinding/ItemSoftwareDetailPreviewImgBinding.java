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

/* JADX INFO: loaded from: classes.dex */
public final class ItemSoftwareDetailPreviewImgBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    private ItemSoftwareDetailPreviewImgBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull Space space, @NonNull Space space2) {
        this.a = constraintLayout;
        this.b = imageView;
    }

    @NonNull
    public static ItemSoftwareDetailPreviewImgBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_software_detail_preview_img, viewGroup, false);
        int i = R.id.iv_software_preview_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_software_preview_img);
        if (imageView != null) {
            i = R.id.s_detail_preview_img_bottom_end;
            Space space = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_detail_preview_img_bottom_end);
            if (space != null) {
                i = R.id.s_detail_preview_img_top_start;
                Space space2 = (Space) ViewBindings.findChildViewById(viewInflate, R.id.s_detail_preview_img_top_start);
                if (space2 != null) {
                    return new ItemSoftwareDetailPreviewImgBinding((ConstraintLayout) viewInflate, imageView, space, space2);
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
