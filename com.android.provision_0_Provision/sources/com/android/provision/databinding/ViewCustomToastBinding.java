package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;

/* JADX INFO: loaded from: classes.dex */
public final class ViewCustomToastBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView tvContent;

    private ViewCustomToastBinding(ConstraintLayout rootView, TextView tvContent) {
        this.rootView = rootView;
        this.tvContent = tvContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ViewCustomToastBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewCustomToastBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_custom_toast, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewCustomToastBinding bind(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_content);
        if (textView != null) {
            return new ViewCustomToastBinding((ConstraintLayout) rootView, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.tv_content)));
    }
}
