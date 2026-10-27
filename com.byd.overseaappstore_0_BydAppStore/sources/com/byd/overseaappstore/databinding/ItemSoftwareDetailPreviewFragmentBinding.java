package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;

/* JADX INFO: loaded from: classes.dex */
public final class ItemSoftwareDetailPreviewFragmentBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    @NonNull
    public final ImageView b;

    private ItemSoftwareDetailPreviewFragmentBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView) {
        this.a = constraintLayout;
        this.b = imageView;
    }

    @NonNull
    public static ItemSoftwareDetailPreviewFragmentBinding a(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.item_software_detail_preview_fragment, viewGroup, false);
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_software_preview_fragment);
        if (imageView != null) {
            return new ItemSoftwareDetailPreviewFragmentBinding((ConstraintLayout) viewInflate, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(R.id.iv_software_preview_fragment)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
