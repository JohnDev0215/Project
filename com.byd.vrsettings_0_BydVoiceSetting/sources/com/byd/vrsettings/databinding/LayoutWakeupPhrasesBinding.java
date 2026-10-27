package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppEditText;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class LayoutWakeupPhrasesBinding implements ViewBinding {
    public final ConstraintLayout clWakeupPhrases;
    public final AppEditText etWakeUp;
    public final ImageView ivEmpty;
    private final ConstraintLayout rootView;
    public final AppTextView tvBtnNoWakeup;
    public final AppTextView tvBtnYesWakeup;
    public final AppTextView tvTitleWakeup;
    public final AppTextView tvWakeupPhrases;
    public final View vWakeupBackground;
    public final View vWakeupPhrases;
    public final View wakeupLine;

    private LayoutWakeupPhrasesBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, AppEditText appEditText, ImageView imageView, AppTextView appTextView, AppTextView appTextView2, AppTextView appTextView3, AppTextView appTextView4, View view, View view2, View view3) {
        this.rootView = constraintLayout;
        this.clWakeupPhrases = constraintLayout2;
        this.etWakeUp = appEditText;
        this.ivEmpty = imageView;
        this.tvBtnNoWakeup = appTextView;
        this.tvBtnYesWakeup = appTextView2;
        this.tvTitleWakeup = appTextView3;
        this.tvWakeupPhrases = appTextView4;
        this.vWakeupBackground = view;
        this.vWakeupPhrases = view2;
        this.wakeupLine = view3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static LayoutWakeupPhrasesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutWakeupPhrasesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_wakeup_phrases, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutWakeupPhrasesBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.et_wake_up;
        AppEditText appEditText = (AppEditText) ViewBindings.findChildViewById(view, R.id.et_wake_up);
        if (appEditText != null) {
            i = R.id.iv_empty;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_empty);
            if (imageView != null) {
                i = R.id.tv_btn_no_wakeup;
                AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_no_wakeup);
                if (appTextView != null) {
                    i = R.id.tv_btn_yes_wakeup;
                    AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_btn_yes_wakeup);
                    if (appTextView2 != null) {
                        i = R.id.tv_title_wakeup;
                        AppTextView appTextView3 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_title_wakeup);
                        if (appTextView3 != null) {
                            i = R.id.tv_wakeup_phrases;
                            AppTextView appTextView4 = (AppTextView) ViewBindings.findChildViewById(view, R.id.tv_wakeup_phrases);
                            if (appTextView4 != null) {
                                i = R.id.v_wakeupBackground;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_wakeupBackground);
                                if (viewFindChildViewById != null) {
                                    i = R.id.v_wakeup_phrases;
                                    View viewFindChildViewById2 = ViewBindings.findChildViewById(view, R.id.v_wakeup_phrases);
                                    if (viewFindChildViewById2 != null) {
                                        i = R.id.wakeup_line;
                                        View viewFindChildViewById3 = ViewBindings.findChildViewById(view, R.id.wakeup_line);
                                        if (viewFindChildViewById3 != null) {
                                            return new LayoutWakeupPhrasesBinding(constraintLayout, constraintLayout, appEditText, imageView, appTextView, appTextView2, appTextView3, appTextView4, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3);
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
