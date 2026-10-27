package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.utils.MyWebView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutUserImproveDisclaimerBinding implements ViewBinding {
    public final ConstraintLayout clDisclaimer;
    public final MyWebView disclaimerWebView;
    public final ImageView ivCloseDisclaimer;
    private final ConstraintLayout rootView;
    public final TextView tvTitleDisclaimer;
    public final View vDisclaimer;

    private LayoutUserImproveDisclaimerBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, MyWebView myWebView, ImageView imageView, TextView textView, View view) {
        this.rootView = constraintLayout;
        this.clDisclaimer = constraintLayout2;
        this.disclaimerWebView = myWebView;
        this.ivCloseDisclaimer = imageView;
        this.tvTitleDisclaimer = textView;
        this.vDisclaimer = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutUserImproveDisclaimerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutUserImproveDisclaimerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_user_improve_disclaimer, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutUserImproveDisclaimerBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.disclaimer_webView;
        MyWebView myWebView = (MyWebView) ViewBindings.findChildViewById(view, R.id.disclaimer_webView);
        if (myWebView != null) {
            i = R.id.iv_close_disclaimer;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_disclaimer);
            if (imageView != null) {
                i = R.id.tv_title_disclaimer;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_disclaimer);
                if (textView != null) {
                    i = R.id.v_disclaimer;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_disclaimer);
                    if (viewFindChildViewById != null) {
                        return new LayoutUserImproveDisclaimerBinding(constraintLayout, constraintLayout, myWebView, imageView, textView, viewFindChildViewById);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
