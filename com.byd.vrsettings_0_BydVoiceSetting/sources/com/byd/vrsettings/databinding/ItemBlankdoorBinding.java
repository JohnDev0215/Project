package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;
import com.byd.vrsettings.view.AppTextView;

/* JADX INFO: loaded from: classes.dex */
public final class ItemBlankdoorBinding implements ViewBinding {
    public final View bottomLine;
    public final AppTextView isAgreeText;
    private final ConstraintLayout rootView;
    public final AppTextView timeText;

    private ItemBlankdoorBinding(ConstraintLayout constraintLayout, View view, AppTextView appTextView, AppTextView appTextView2) {
        this.rootView = constraintLayout;
        this.bottomLine = view;
        this.isAgreeText = appTextView;
        this.timeText = appTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemBlankdoorBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemBlankdoorBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.item_blankdoor, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemBlankdoorBinding bind(View view) {
        int i = R.id.bottomLine;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.bottomLine);
        if (viewFindChildViewById != null) {
            i = R.id.isAgreeText;
            AppTextView appTextView = (AppTextView) ViewBindings.findChildViewById(view, R.id.isAgreeText);
            if (appTextView != null) {
                i = R.id.timeText;
                AppTextView appTextView2 = (AppTextView) ViewBindings.findChildViewById(view, R.id.timeText);
                if (appTextView2 != null) {
                    return new ItemBlankdoorBinding((ConstraintLayout) view, viewFindChildViewById, appTextView, appTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
