package com.android.provision.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.android.provision.R;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityDefaultBinding implements ViewBinding {
    public final FrameLayout flContent;
    private final ConstraintLayout rootView;

    private ActivityDefaultBinding(ConstraintLayout rootView, FrameLayout flContent) {
        this.rootView = rootView;
        this.flContent = flContent;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDefaultBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDefaultBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_default, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDefaultBinding bind(View rootView) {
        FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.fl_content);
        if (frameLayout != null) {
            return new ActivityDefaultBinding((ConstraintLayout) rootView, frameLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(R.id.fl_content)));
    }
}
