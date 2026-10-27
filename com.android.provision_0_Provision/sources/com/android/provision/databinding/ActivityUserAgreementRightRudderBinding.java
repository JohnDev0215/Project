package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;
import com.android.provision.widget.ToneGeneratorButton;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityUserAgreementRightRudderBinding implements ViewBinding {
    public final CheckBox checkbox;
    public final FrameLayout flContent;
    public final LinearLayout llCheck;
    private final RelativeLayout rootView;
    public final TextView tvAgreeTips;
    public final ToneGeneratorButton tvPrivacyPolicySure;
    public final TextView tvSafetyWarningTitle;

    private ActivityUserAgreementRightRudderBinding(RelativeLayout rootView, CheckBox checkbox, FrameLayout flContent, LinearLayout llCheck, TextView tvAgreeTips, ToneGeneratorButton tvPrivacyPolicySure, TextView tvSafetyWarningTitle) {
        this.rootView = rootView;
        this.checkbox = checkbox;
        this.flContent = flContent;
        this.llCheck = llCheck;
        this.tvAgreeTips = tvAgreeTips;
        this.tvPrivacyPolicySure = tvPrivacyPolicySure;
        this.tvSafetyWarningTitle = tvSafetyWarningTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUserAgreementRightRudderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUserAgreementRightRudderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_user_agreement_right_rudder, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUserAgreementRightRudderBinding bind(View rootView) {
        int i = R.id.checkbox;
        CheckBox checkBox = (CheckBox) rootView.findViewById(R.id.checkbox);
        if (checkBox != null) {
            i = R.id.fl_content;
            FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.fl_content);
            if (frameLayout != null) {
                i = R.id.ll_check;
                LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_check);
                if (linearLayout != null) {
                    i = R.id.tv_agree_tips;
                    TextView textView = (TextView) rootView.findViewById(R.id.tv_agree_tips);
                    if (textView != null) {
                        i = R.id.tv_privacy_policy_sure;
                        ToneGeneratorButton toneGeneratorButton = (ToneGeneratorButton) rootView.findViewById(R.id.tv_privacy_policy_sure);
                        if (toneGeneratorButton != null) {
                            i = R.id.tv_safety_warning_title;
                            TextView textView2 = (TextView) rootView.findViewById(R.id.tv_safety_warning_title);
                            if (textView2 != null) {
                                return new ActivityUserAgreementRightRudderBinding((RelativeLayout) rootView, checkBox, frameLayout, linearLayout, textView, toneGeneratorButton, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
