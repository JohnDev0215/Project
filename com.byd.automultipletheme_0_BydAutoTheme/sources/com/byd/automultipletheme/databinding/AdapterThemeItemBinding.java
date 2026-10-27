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
public final class AdapterThemeItemBinding implements ViewBinding {
    public final TextView itemAction;
    public final ImageView itemCurrent;
    public final ImageView itemImage;
    public final TextView itemName;
    private final ConstraintLayout rootView;

    private AdapterThemeItemBinding(ConstraintLayout constraintLayout, TextView textView, ImageView imageView, ImageView imageView2, TextView textView2) {
        this.rootView = constraintLayout;
        this.itemAction = textView;
        this.itemCurrent = imageView;
        this.itemImage = imageView2;
        this.itemName = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AdapterThemeItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AdapterThemeItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.adapter_theme_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static AdapterThemeItemBinding bind(View view) {
        int i = R.id.item_action;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.item_action);
        if (textView != null) {
            i = R.id.item_current;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.item_current);
            if (imageView != null) {
                i = R.id.item_image;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.item_image);
                if (imageView2 != null) {
                    i = R.id.item_name;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.item_name);
                    if (textView2 != null) {
                        return new AdapterThemeItemBinding((ConstraintLayout) view, textView, imageView, imageView2, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
