package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewCommonTabBinding implements ViewBinding {
    public final ImageView commonTabIconView;
    public final TextView commonTabTextView;
    private final LinearLayout rootView;

    private ViewCommonTabBinding(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        this.rootView = linearLayout;
        this.commonTabIconView = imageView;
        this.commonTabTextView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewCommonTabBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewCommonTabBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_common_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewCommonTabBinding bind(View view) {
        int i = R.id.commonTabIconView;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.commonTabIconView);
        if (imageView != null) {
            i = R.id.commonTabTextView;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.commonTabTextView);
            if (textView != null) {
                return new ViewCommonTabBinding((LinearLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
