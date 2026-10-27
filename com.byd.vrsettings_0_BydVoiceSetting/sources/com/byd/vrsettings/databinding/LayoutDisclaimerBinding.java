package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutDisclaimerBinding implements ViewBinding {
    public final ConstraintLayout clDisclaimer;
    public final WebView disclaimerWebView;
    public final ImageView ivCloseDisclaimer;
    private final ConstraintLayout rootView;
    public final AppTextView tvTitleDisclaimer;
    public final View vDisclaimer;

    private LayoutDisclaimerBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, WebView webView, ImageView imageView, AppTextView appTextView, View view) {
        this.rootView = constraintLayout;
        this.clDisclaimer = constraintLayout2;
        this.disclaimerWebView = webView;
        this.ivCloseDisclaimer = imageView;
        this.tvTitleDisclaimer = appTextView;
        this.vDisclaimer = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutDisclaimerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutDisclaimerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_disclaimer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutDisclaimerBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.disclaimer_webView;
        WebView webView = (WebView) ViewBindings.findChildViewById(view, R.id.disclaimer_webView);
        if (webView != null) {
            i = R.id.iv_close_disclaimer;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_disclaimer);
            if (imageView != null) {
                i = R.id.tv_title_disclaimer;
                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_disclaimer);
                if (appTextView != null) {
                    i = R.id.v_disclaimer;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_disclaimer);
                    if (viewFindChildViewById != null) {
                        return new LayoutDisclaimerBinding(constraintLayout, constraintLayout, webView, imageView, appTextView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
