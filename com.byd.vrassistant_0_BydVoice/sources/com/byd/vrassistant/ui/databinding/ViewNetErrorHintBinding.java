package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.base.DirectionAwareImageView;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewNetErrorHintBinding implements ViewBinding {
    public final DirectionAwareImageView ivArrow;
    private final ConstraintLayout rootView;
    public final TextView tvNetErrorHint;

    private ViewNetErrorHintBinding(ConstraintLayout constraintLayout, DirectionAwareImageView directionAwareImageView, TextView textView) {
        this.rootView = constraintLayout;
        this.ivArrow = directionAwareImageView;
        this.tvNetErrorHint = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewNetErrorHintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewNetErrorHintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_net_error_hint, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewNetErrorHintBinding bind(View view) {
        int i = R.id.iv_arrow;
        DirectionAwareImageView directionAwareImageView = (DirectionAwareImageView) ViewBindings.findChildViewById(view, i);
        if (directionAwareImageView != null) {
            i = R.id.tv_net_error_hint;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                return new ViewNetErrorHintBinding((ConstraintLayout) view, directionAwareImageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
