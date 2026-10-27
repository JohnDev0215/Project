package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;
import com.android.provision.widget.ToneGeneratorButton;

/* JADX INFO: loaded from: classes.dex */
public final class DenzaActivityPrivacyPolicyRightRudderBinding implements ViewBinding {
    public final FrameLayout flContent;
    private final RelativeLayout rootView;
    public final Space space;
    public final ToneGeneratorButton tvPrivacyPolicySkip;
    public final ToneGeneratorButton tvPrivacyPolicySure;
    public final TextView tvSafetyWarningTitle;

    private DenzaActivityPrivacyPolicyRightRudderBinding(RelativeLayout rootView, FrameLayout flContent, Space space, ToneGeneratorButton tvPrivacyPolicySkip, ToneGeneratorButton tvPrivacyPolicySure, TextView tvSafetyWarningTitle) {
        this.rootView = rootView;
        this.flContent = flContent;
        this.space = space;
        this.tvPrivacyPolicySkip = tvPrivacyPolicySkip;
        this.tvPrivacyPolicySure = tvPrivacyPolicySure;
        this.tvSafetyWarningTitle = tvSafetyWarningTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static DenzaActivityPrivacyPolicyRightRudderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DenzaActivityPrivacyPolicyRightRudderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.denza_activity_privacy_policy_right_rudder, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DenzaActivityPrivacyPolicyRightRudderBinding bind(View rootView) {
        int i = R.id.fl_content;
        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.fl_content);
        if (frameLayout != null) {
            i = R.id.space;
            Space space = (Space) rootView.findViewById(R.id.space);
            if (space != null) {
                i = R.id.tv_privacy_policy_skip;
                ToneGeneratorButton toneGeneratorButton = (ToneGeneratorButton) rootView.findViewById(R.id.tv_privacy_policy_skip);
                if (toneGeneratorButton != null) {
                    i = R.id.tv_privacy_policy_sure;
                    ToneGeneratorButton toneGeneratorButton2 = (ToneGeneratorButton) rootView.findViewById(R.id.tv_privacy_policy_sure);
                    if (toneGeneratorButton2 != null) {
                        i = R.id.tv_safety_warning_title;
                        TextView textView = (TextView) rootView.findViewById(R.id.tv_safety_warning_title);
                        if (textView != null) {
                            return new DenzaActivityPrivacyPolicyRightRudderBinding((RelativeLayout) rootView, frameLayout, space, toneGeneratorButton, toneGeneratorButton2, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
