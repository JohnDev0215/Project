package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogNetTipBinding implements ViewBinding {
    public final CheckBox checkbox;
    public final ConstraintLayout dialogBg;
    public final ConstraintLayout layoutDialogRoot;
    public final AppTextView negative;
    public final AppTextView positive;
    private final RelativeLayout rootView;
    public final AppTextView textiewTitle;
    public final AppTextView textviewContent;
    public final AppTextView textviewRadio;
    public final View viewReboot;

    private DialogNetTipBinding(RelativeLayout relativeLayout, CheckBox checkBox, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, AppTextView appTextView5, View view) {
        this.rootView = relativeLayout;
        this.checkbox = checkBox;
        this.dialogBg = constraintLayout;
        this.layoutDialogRoot = constraintLayout2;
        this.negative = appTextView;
        this.positive = appTextView2;
        this.textiewTitle = appTextView3;
        this.textviewContent = appTextView4;
        this.textviewRadio = appTextView5;
        this.viewReboot = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogNetTipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogNetTipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_net_tip, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogNetTipBinding bind(View view) {
        int i = R.id.checkbox;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkbox);
        if (checkBox != null) {
            i = R.id.dialog_bg;
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
                                    i = R.id.textview_radio;
                                    AppTextView appTextView5 = (AppTextView) ViewBindings.findChildViewById(view, R.id.textview_radio);
                                    if (appTextView5 != null) {
                                        i = R.id.view_reboot;
                                        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.view_reboot);
                                        if (viewFindChildViewById != null) {
                                            return new DialogNetTipBinding((RelativeLayout) view, checkBox, constraintLayout, constraintLayout2, appTextView, appTextView2, appTextView3, appTextView4, appTextView5, viewFindChildViewById);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
