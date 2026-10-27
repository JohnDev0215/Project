package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutLanguageBinding implements ViewBinding {
    public final ConstraintLayout clLanguage;
    public final View gLanguage;
    public final RadioGroup rgLanguage;
    private final ConstraintLayout rootView;
    public final ScrollView svLanguage;
    public final TextView tvBtnNoLanguage;
    public final TextView tvBtnYesLanguage;
    public final TextView tvSwitchLanguage;
    public final TextView tvTitleLanguage;
    public final View vLanguage;

    private LayoutLanguageBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, View view, RadioGroup radioGroup, ScrollView scrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        this.rootView = constraintLayout;
        this.clLanguage = constraintLayout2;
        this.gLanguage = view;
        this.rgLanguage = radioGroup;
        this.svLanguage = scrollView;
        this.tvBtnNoLanguage = textView;
        this.tvBtnYesLanguage = textView2;
        this.tvSwitchLanguage = textView3;
        this.tvTitleLanguage = textView4;
        this.vLanguage = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutLanguageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutLanguageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_language, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutLanguageBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.g_language;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.g_language);
        if (viewFindChildViewById != null) {
            i = R.id.rg_language;
            RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.rg_language);
            if (radioGroup != null) {
                i = R.id.sv_language;
                ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.sv_language);
                if (scrollView != null) {
                    i = R.id.tv_btn_no_language;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_btn_no_language);
                    if (textView != null) {
                        i = R.id.tv_btn_yes_language;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_btn_yes_language);
                        if (textView2 != null) {
                            i = R.id.tv_switch_language;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_switch_language);
                            if (textView3 != null) {
                                i = R.id.tv_title_language;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title_language);
                                if (textView4 != null) {
                                    i = R.id.v_language;
                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_language);
                                    if (viewFindChildViewById2 != null) {
                                        return new LayoutLanguageBinding(constraintLayout, constraintLayout, viewFindChildViewById, radioGroup, scrollView, textView, textView2, textView3, textView4, viewFindChildViewById2);
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
