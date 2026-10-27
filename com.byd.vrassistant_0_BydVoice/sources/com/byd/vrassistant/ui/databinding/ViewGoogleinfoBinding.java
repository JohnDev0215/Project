package com.byd.vrassistant.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewGoogleinfoBinding implements ViewBinding {
    public final View bgDialog;
    public final ConstraintLayout bgGi;
    private final ConstraintLayout rootView;
    public final TextView tvContent;
    public final TextView tvMore;
    public final TextView tvOk;
    public final TextView tvTitle;
    public final View vBgMore;
    public final View vBgOk;
    public final View vDivider;

    private ViewGoogleinfoBinding(ConstraintLayout constraintLayout, View view, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2, View view3, View view4) {
        this.rootView = constraintLayout;
        this.bgDialog = view;
        this.bgGi = constraintLayout2;
        this.tvContent = textView;
        this.tvMore = textView2;
        this.tvOk = textView3;
        this.tvTitle = textView4;
        this.vBgMore = view2;
        this.vBgOk = view3;
        this.vDivider = view4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewGoogleinfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewGoogleinfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_googleinfo, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewGoogleinfoBinding bind(View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i = R.id.bg_dialog;
        View viewFindChildViewById4 = ViewBindings.findChildViewById(view, i);
        if (viewFindChildViewById4 != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i = R.id.tv_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.tv_more;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.tv_ok;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView3 != null) {
                        i = R.id.tv_title;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView4 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.v_bg_more))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.v_bg_ok))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(view, (i = R.id.v_divider))) != null) {
                            return new ViewGoogleinfoBinding(constraintLayout, viewFindChildViewById4, constraintLayout, textView, textView2, textView3, textView4, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
