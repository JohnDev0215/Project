package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class ViewLayoutToastBinding implements ViewBinding {
    public final AppTextView message;
    private final LinearLayout rootView;

    private ViewLayoutToastBinding(LinearLayout linearLayout, AppTextView appTextView) {
        this.rootView = linearLayout;
        this.message = appTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewLayoutToastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewLayoutToastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_layout_toast, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewLayoutToastBinding bind(View view) {
        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.message);
        if (appTextView != null) {
            return new ViewLayoutToastBinding((LinearLayout) view, appTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.message)));
    }
}
