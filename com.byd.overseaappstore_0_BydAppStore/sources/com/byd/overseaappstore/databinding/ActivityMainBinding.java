package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    private ActivityMainBinding(@NonNull ConstraintLayout constraintLayout, @NonNull FragmentContainerView fragmentContainerView) {
        this.a = constraintLayout;
    }

    @NonNull
    public static ActivityMainBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_main, (ViewGroup) null, false);
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(viewInflate, R.id.fcv_main);
        if (fragmentContainerView != null) {
            return new ActivityMainBinding((ConstraintLayout) viewInflate, fragmentContainerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(R.id.fcv_main)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
