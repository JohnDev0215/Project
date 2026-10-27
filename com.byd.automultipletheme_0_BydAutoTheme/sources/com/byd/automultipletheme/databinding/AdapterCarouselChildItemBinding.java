package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class AdapterCarouselChildItemBinding implements ViewBinding {
    public final TextView itemCount;
    public final ImageView itemImage;
    public final ImageView itemSelected;
    private final ConstraintLayout rootView;

    private AdapterCarouselChildItemBinding(ConstraintLayout constraintLayout, TextView textView, ImageView imageView, ImageView imageView2) {
        this.rootView = constraintLayout;
        this.itemCount = textView;
        this.itemImage = imageView;
        this.itemSelected = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AdapterCarouselChildItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AdapterCarouselChildItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.adapter_carousel_child_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AdapterCarouselChildItemBinding bind(View view) {
        int i = R.id.item_count;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.item_count);
        if (textView != null) {
            i = R.id.item_image;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.item_image);
            if (imageView != null) {
                i = R.id.item_selected;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.item_selected);
                if (imageView2 != null) {
                    return new AdapterCarouselChildItemBinding((ConstraintLayout) view, textView, imageView, imageView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
