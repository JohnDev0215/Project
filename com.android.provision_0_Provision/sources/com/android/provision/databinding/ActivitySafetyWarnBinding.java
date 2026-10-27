package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;
import com.android.provision.widget.ToneGeneratorButton;

/* JADX INFO: loaded from: classes.dex */
public final class ActivitySafetyWarnBinding implements ViewBinding {
    public final LinearLayout llTitle;
    private final RelativeLayout rootView;
    public final ToneGeneratorButton tvOk;
    public final TextView tvSafetyWarningContent;
    public final TextView tvSafetyWarningTitle;

    private ActivitySafetyWarnBinding(RelativeLayout rootView, LinearLayout llTitle, ToneGeneratorButton tvOk, TextView tvSafetyWarningContent, TextView tvSafetyWarningTitle) {
        this.rootView = rootView;
        this.llTitle = llTitle;
        this.tvOk = tvOk;
        this.tvSafetyWarningContent = tvSafetyWarningContent;
        this.tvSafetyWarningTitle = tvSafetyWarningTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySafetyWarnBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySafetyWarnBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_safety_warn, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivitySafetyWarnBinding bind(View rootView) {
        int i = R.id.ll_title;
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_title);
        if (linearLayout != null) {
            i = R.id.tv_ok;
            ToneGeneratorButton toneGeneratorButton = (ToneGeneratorButton) rootView.findViewById(R.id.tv_ok);
            if (toneGeneratorButton != null) {
                i = R.id.tv_safety_warning_content;
                TextView textView = (TextView) rootView.findViewById(R.id.tv_safety_warning_content);
                if (textView != null) {
                    i = R.id.tv_safety_warning_title;
                    TextView textView2 = (TextView) rootView.findViewById(R.id.tv_safety_warning_title);
                    if (textView2 != null) {
                        return new ActivitySafetyWarnBinding((RelativeLayout) rootView, linearLayout, toneGeneratorButton, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
