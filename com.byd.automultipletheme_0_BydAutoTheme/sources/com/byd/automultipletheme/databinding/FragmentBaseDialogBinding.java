package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentBaseDialogBinding implements ViewBinding {
    public final TextView dialogCancel;
    public final TextView dialogContent;
    public final TextView dialogOk;
    public final TextView dialogTitle;
    public final Guideline glv50;
    public final TextView itemLine;
    private final ConstraintLayout rootView;

    private FragmentBaseDialogBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, Guideline guideline, TextView textView5) {
        this.rootView = constraintLayout;
        this.dialogCancel = textView;
        this.dialogContent = textView2;
        this.dialogOk = textView3;
        this.dialogTitle = textView4;
        this.glv50 = guideline;
        this.itemLine = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBaseDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentBaseDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_base_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentBaseDialogBinding bind(View view) {
        int i = R.id.dialog_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_cancel);
        if (textView != null) {
            i = R.id.dialog_content;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_content);
            if (textView2 != null) {
                i = R.id.dialog_ok;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_ok);
                if (textView3 != null) {
                    i = R.id.dialog_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.dialog_title);
                    if (textView4 != null) {
                        i = R.id.glv50;
                        Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.glv50);
                        if (guideline != null) {
                            i = R.id.item_line;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.item_line);
                            if (textView5 != null) {
                                return new FragmentBaseDialogBinding((ConstraintLayout) view, textView, textView2, textView3, textView4, guideline, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
