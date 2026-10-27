package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.utils.MyWebView;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutCerencePrivacyPllicyBinding implements ViewBinding {
    public final View cerencePrivacy;
    public final ImageView cerencePrivacyBtnX;
    public final View cerencePrivacyLine;
    public final MyWebView cerencePrivacyWebView;
    public final ConstraintLayout clCerencePrivacy;
    private final ConstraintLayout rootView;
    public final AppTextView tvTitleCerencePrivacy;
    public final View vCerencePrivacyBackground;

    private LayoutCerencePrivacyPllicyBinding(ConstraintLayout constraintLayout, View view, ImageView imageView, View view2, MyWebView myWebView, ConstraintLayout constraintLayout2, AppTextView appTextView, View view3) {
        this.rootView = constraintLayout;
        this.cerencePrivacy = view;
        this.cerencePrivacyBtnX = imageView;
        this.cerencePrivacyLine = view2;
        this.cerencePrivacyWebView = myWebView;
        this.clCerencePrivacy = constraintLayout2;
        this.tvTitleCerencePrivacy = appTextView;
        this.vCerencePrivacyBackground = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutCerencePrivacyPllicyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCerencePrivacyPllicyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_cerence_privacy_pllicy, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutCerencePrivacyPllicyBinding bind(View view) {
        int i = R.id.cerence_privacy;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.cerence_privacy);
        if (viewFindChildViewById != null) {
            i = R.id.cerence_privacy_BtnX;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.cerence_privacy_BtnX);
            if (imageView != null) {
                i = R.id.cerence_privacy_line;
                View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.cerence_privacy_line);
                if (viewFindChildViewById2 != null) {
                    i = R.id.cerence_privacy_webView;
                    MyWebView myWebView = (MyWebView) ViewBindings.findChildViewById(view, R.id.cerence_privacy_webView);
                    if (myWebView != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                        i = R.id.tv_title_cerence_privacy;
                        AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_cerence_privacy);
                        if (appTextView != null) {
                            i = R.id.v_cerence_privacyBackground;
                            View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.v_cerence_privacyBackground);
                            if (viewFindChildViewById3 != null) {
                                return new LayoutCerencePrivacyPllicyBinding(constraintLayout, viewFindChildViewById, imageView, viewFindChildViewById2, myWebView, constraintLayout, appTextView, viewFindChildViewById3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
