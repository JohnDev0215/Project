package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogCommon2Binding implements ViewBinding {
    public final ConstraintLayout dialogBg;
    public final ConstraintLayout layoutDialogRoot;
    public final AppTextView negative;
    public final AppTextView positive;
    private final RelativeLayout rootView;
    public final AppTextView textiewTitle;
    public final AppTextView textviewContent;

    private DialogCommon2Binding(RelativeLayout relativeLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4) {
        this.rootView = relativeLayout;
        this.dialogBg = constraintLayout;
        this.layoutDialogRoot = constraintLayout2;
        this.negative = appTextView;
        this.positive = appTextView2;
        this.textiewTitle = appTextView3;
        this.textviewContent = appTextView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogCommon2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCommon2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_common2, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogCommon2Binding bind(View view) {
        int i = R.id.dialog_bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.dialog_bg);
        if (constraintLayout != null) {
            i = R.id.layout_dialog_root;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_dialog_root);
            if (constraintLayout2 != null) {
                i = R.id.negative;
                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.negative);
                if (appTextView != null) {
                    i = R.id.positive;
                    AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.positive);
                    if (appTextView2 != null) {
                        i = R.id.textiew_title;
                        AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textiew_title);
                        if (appTextView3 != null) {
                            i = R.id.textview_content;
                            AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_content);
                            if (appTextView4 != null) {
                                return new DialogCommon2Binding((RelativeLayout) view, constraintLayout, constraintLayout2, appTextView, appTextView2, appTextView3, appTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
