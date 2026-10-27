package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.widget.CommonTabLayout;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final FragmentContainerView containerView;
    private final ConstraintLayout rootView;
    public final CommonTabLayout tabLayout;
    public final LinearLayout tabTopLayout;

    private ActivityMainBinding(ConstraintLayout constraintLayout, FragmentContainerView fragmentContainerView, CommonTabLayout commonTabLayout, LinearLayout linearLayout) {
        this.rootView = constraintLayout;
        this.containerView = fragmentContainerView;
        this.tabLayout = commonTabLayout;
        this.tabTopLayout = linearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMainBinding bind(View view) {
        int i = R.id.container_view;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.container_view);
        if (fragmentContainerView != null) {
            i = R.id.tab_layout;
            CommonTabLayout commonTabLayout = (CommonTabLayout) ViewBindings.findChildViewById(view, R.id.tab_layout);
            if (commonTabLayout != null) {
                i = R.id.tab_top_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.tab_top_layout);
                if (linearLayout != null) {
                    return new ActivityMainBinding((ConstraintLayout) view, fragmentContainerView, commonTabLayout, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
