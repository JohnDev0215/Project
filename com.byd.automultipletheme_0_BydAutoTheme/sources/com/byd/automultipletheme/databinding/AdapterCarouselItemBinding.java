package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class AdapterCarouselItemBinding implements ViewBinding {
    public final ImageView itemDelete;
    public final ImageView itemImage;
    private final ConstraintLayout rootView;

    private AdapterCarouselItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2) {
        this.rootView = constraintLayout;
        this.itemDelete = imageView;
        this.itemImage = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AdapterCarouselItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AdapterCarouselItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.adapter_carousel_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AdapterCarouselItemBinding bind(View view) {
        int i = R.id.item_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.item_delete);
        if (imageView != null) {
            i = R.id.item_image;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.item_image);
            if (imageView2 != null) {
                return new AdapterCarouselItemBinding((ConstraintLayout) view, imageView, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
