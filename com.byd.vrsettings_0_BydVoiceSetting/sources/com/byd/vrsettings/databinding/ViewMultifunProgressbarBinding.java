package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewMultifunProgressbarBinding implements ViewBinding {
    public final Group gpProgress;
    public final ImageView ivStatus;
    public final ProgressBar progressbar;
    public final ConstraintLayout rootView;
    private final ConstraintLayout rootView_;
    public final TextView textFuntion;
    public final TextView textProgress;

    private ViewMultifunProgressbarBinding(ConstraintLayout constraintLayout, Group group, ImageView imageView, ProgressBar progressBar, ConstraintLayout constraintLayout2, TextView textView, TextView textView2) {
        this.rootView_ = constraintLayout;
        this.gpProgress = group;
        this.ivStatus = imageView;
        this.progressbar = progressBar;
        this.rootView = constraintLayout2;
        this.textFuntion = textView;
        this.textProgress = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView_;
    }

    public static ViewMultifunProgressbarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewMultifunProgressbarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_multifun_progressbar, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewMultifunProgressbarBinding bind(View view) {
        int i = R.id.gp_progress;
        Group group = (Group) ViewBindings.findChildViewById(view, R.id.gp_progress);
        if (group != null) {
            i = R.id.iv_status;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_status);
            if (imageView != null) {
                i = R.id.progressbar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressbar);
                if (progressBar != null) {
                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                    i = R.id.text_funtion;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.text_funtion);
                    if (textView != null) {
                        i = R.id.text_progress;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.text_progress);
                        if (textView2 != null) {
                            return new ViewMultifunProgressbarBinding(constraintLayout, group, imageView, progressBar, constraintLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
