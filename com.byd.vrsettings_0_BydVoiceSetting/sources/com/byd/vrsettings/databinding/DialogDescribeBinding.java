package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class DialogDescribeBinding implements ViewBinding {
    public final ConstraintLayout dialogBg;
    public final ConstraintLayout layoutRoot;
    private final RelativeLayout rootView;
    public final AppTextView textPositive;
    public final TextView textviewContent;
    public final TextView textviewTitle;
    public final View viewLine;

    private DialogDescribeBinding(RelativeLayout relativeLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppTextView appTextView, TextView textView, TextView textView2, View view) {
        this.rootView = relativeLayout;
        this.dialogBg = constraintLayout;
        this.layoutRoot = constraintLayout2;
        this.textPositive = appTextView;
        this.textviewContent = textView;
        this.textviewTitle = textView2;
        this.viewLine = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DialogDescribeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogDescribeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_describe, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogDescribeBinding bind(View view) {
        int i = R.id.dialog_bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.dialog_bg);
        if (constraintLayout != null) {
            i = R.id.layout_root;
            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layout_root);
            if (constraintLayout2 != null) {
                i = R.id.text_positive;
                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.text_positive);
                if (appTextView != null) {
                    i = R.id.textview_content;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textview_content);
                    if (textView != null) {
                        i = R.id.textview_title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.textview_title);
                        if (textView2 != null) {
                            i = R.id.view_line;
                            View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.view_line);
                            if (viewFindChildViewById != null) {
                                return new DialogDescribeBinding((RelativeLayout) view, constraintLayout, constraintLayout2, appTextView, textView, textView2, viewFindChildViewById);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
