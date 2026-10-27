package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityPreviewImageBinding implements ViewBinding {
    public final ImageView closeBtn;
    public final ImageView floatView;
    public final Guideline gl50;
    public final TextView itemPosition;
    public final ViewPager2 previewList;
    private final ConstraintLayout rootView;
    public final TextView useNowBtn;

    private ActivityPreviewImageBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, Guideline guideline, TextView textView, ViewPager2 viewPager2, TextView textView2) {
        this.rootView = constraintLayout;
        this.closeBtn = imageView;
        this.floatView = imageView2;
        this.gl50 = guideline;
        this.itemPosition = textView;
        this.previewList = viewPager2;
        this.useNowBtn = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPreviewImageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityPreviewImageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_preview_image, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPreviewImageBinding bind(View view) {
        int i = R.id.close_btn;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.close_btn);
        if (imageView != null) {
            i = R.id.float_view;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.float_view);
            if (imageView2 != null) {
                i = R.id.gl50;
                Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.gl50);
                if (guideline != null) {
                    i = R.id.item_position;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.item_position);
                    if (textView != null) {
                        i = R.id.preview_list;
                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.preview_list);
                        if (viewPager2 != null) {
                            i = R.id.use_now_btn;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.use_now_btn);
                            if (textView2 != null) {
                                return new ActivityPreviewImageBinding((ConstraintLayout) view, imageView, imageView2, guideline, textView, viewPager2, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
