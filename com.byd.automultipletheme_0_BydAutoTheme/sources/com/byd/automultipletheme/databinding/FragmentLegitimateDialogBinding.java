package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentLegitimateDialogBinding implements ViewBinding {
    public final TextView allowEvery;
    public final TextView allowOne;
    public final TextView btnReject;
    public final TextView dialogContent;
    public final TextView dialogTitle;
    public final TextView itemLine;
    private final ConstraintLayout rootView;

    private FragmentLegitimateDialogBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.rootView = constraintLayout;
        this.allowEvery = textView;
        this.allowOne = textView2;
        this.btnReject = textView3;
        this.dialogContent = textView4;
        this.dialogTitle = textView5;
        this.itemLine = textView6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLegitimateDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentLegitimateDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_legitimate_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentLegitimateDialogBinding bind(View view) {
        int i = R.id.allow_every;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.allow_every);
        if (textView != null) {
            i = R.id.allow_one;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.allow_one);
            if (textView2 != null) {
                i = R.id.btn_reject;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_reject);
                if (textView3 != null) {
                    i = R.id.dialog_content;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_content);
                    if (textView4 != null) {
                        i = R.id.dialog_title;
                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_title);
                        if (textView5 != null) {
                            i = R.id.item_line;
                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.item_line);
                            if (textView6 != null) {
                                return new FragmentLegitimateDialogBinding((ConstraintLayout) view, textView, textView2, textView3, textView4, textView5, textView6);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
