package com.byd.vrsettings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewLogtoolBinding implements ViewBinding {
    public final ConstraintLayout clLogTool;
    public final ImageView ivClose;
    public final Button logBeginBtn;
    public final Button logEndBtn;
    public final Button logPackBtn;
    public final TextView logTip;
    public final TextView logToolTitle;
    private final ConstraintLayout rootView;
    public final View vLogToolBackground;

    private ViewLogtoolBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, Button button, Button button2, Button button3, TextView textView, TextView textView2, View view) {
        this.rootView = constraintLayout;
        this.clLogTool = constraintLayout2;
        this.ivClose = imageView;
        this.logBeginBtn = button;
        this.logEndBtn = button2;
        this.logPackBtn = button3;
        this.logTip = textView;
        this.logToolTitle = textView2;
        this.vLogToolBackground = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewLogtoolBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewLogtoolBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_logtool, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewLogtoolBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.log_beginBtn;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.log_beginBtn);
            if (button != null) {
                i = R.id.log_endBtn;
                Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.log_endBtn);
                if (button2 != null) {
                    i = R.id.log_packBtn;
                    Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.log_packBtn);
                    if (button3 != null) {
                        i = R.id.log_tip;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.log_tip);
                        if (textView != null) {
                            i = R.id.logToolTitle;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.logToolTitle);
                            if (textView2 != null) {
                                i = R.id.v_logToolBackground;
                                View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.v_logToolBackground);
                                if (viewFindChildViewById != null) {
                                    return new ViewLogtoolBinding(constraintLayout, constraintLayout, imageView, button, button2, button3, textView, textView2, viewFindChildViewById);
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
