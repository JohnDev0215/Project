package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentApplyingWindowBinding implements ViewBinding {
    public final ProgressBar loadingBar;
    public final ImageView loadingState;
    public final TextView loadingTxt;
    private final LinearLayout rootView;

    private FragmentApplyingWindowBinding(LinearLayout linearLayout, ProgressBar progressBar, ImageView imageView, TextView textView) {
        this.rootView = linearLayout;
        this.loadingBar = progressBar;
        this.loadingState = imageView;
        this.loadingTxt = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentApplyingWindowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentApplyingWindowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_applying_window, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentApplyingWindowBinding bind(View view) {
        int i = R.id.loading_bar;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.loading_bar);
        if (progressBar != null) {
            i = R.id.loading_state;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.loading_state);
            if (imageView != null) {
                i = R.id.loading_txt;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.loading_txt);
                if (textView != null) {
                    return new FragmentApplyingWindowBinding((LinearLayout) view, progressBar, imageView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
