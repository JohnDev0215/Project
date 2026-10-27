package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;
import com.android.provision.widget.ToneGeneratorButton;

/* JADX INFO: loaded from: classes.dex */
public final class DenzaDialogPolicyTipsBinding implements ViewBinding {
    public final TextView message;
    private final ConstraintLayout rootView;
    public final Space space;
    public final TextView title;
    public final View titleLine;
    public final ToneGeneratorButton tvCancelPolicy;
    public final ToneGeneratorButton tvSurePolicy;
    public final ConstraintLayout viewRoot;

    private DenzaDialogPolicyTipsBinding(ConstraintLayout rootView, TextView message, Space space, TextView title, View titleLine, ToneGeneratorButton tvCancelPolicy, ToneGeneratorButton tvSurePolicy, ConstraintLayout viewRoot) {
        this.rootView = rootView;
        this.message = message;
        this.space = space;
        this.title = title;
        this.titleLine = titleLine;
        this.tvCancelPolicy = tvCancelPolicy;
        this.tvSurePolicy = tvSurePolicy;
        this.viewRoot = viewRoot;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DenzaDialogPolicyTipsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DenzaDialogPolicyTipsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.denza_dialog_policy_tips, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DenzaDialogPolicyTipsBinding bind(View rootView) {
        int i = R.id.message;
        TextView textView = (TextView) rootView.findViewById(R.id.message);
        if (textView != null) {
            i = R.id.space;
            Space space = (Space) rootView.findViewById(R.id.space);
            if (space != null) {
                i = R.id.title;
                TextView textView2 = (TextView) rootView.findViewById(R.id.title);
                if (textView2 != null) {
                    i = R.id.title_line;
                    View viewFindViewById = rootView.findViewById(R.id.title_line);
                    if (viewFindViewById != null) {
                        i = R.id.tv_cancel_policy;
                        ToneGeneratorButton toneGeneratorButton = (ToneGeneratorButton) rootView.findViewById(R.id.tv_cancel_policy);
                        if (toneGeneratorButton != null) {
                            i = R.id.tv_sure_policy;
                            ToneGeneratorButton toneGeneratorButton2 = (ToneGeneratorButton) rootView.findViewById(R.id.tv_sure_policy);
                            if (toneGeneratorButton2 != null) {
                                ConstraintLayout constraintLayout = (ConstraintLayout) rootView;
                                return new DenzaDialogPolicyTipsBinding(constraintLayout, textView, space, textView2, viewFindViewById, toneGeneratorButton, toneGeneratorButton2, constraintLayout);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
