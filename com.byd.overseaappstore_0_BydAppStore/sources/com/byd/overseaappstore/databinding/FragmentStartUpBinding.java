package com.byd.overseaappstore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentStartUpBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout a;

    private FragmentStartUpBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SkinAppCompatImageView skinAppCompatImageView) {
        this.a = constraintLayout;
    }

    @NonNull
    public static FragmentStartUpBinding a(@NonNull LayoutInflater layoutInflater) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_start_up, (ViewGroup) null, false);
        SkinAppCompatImageView skinAppCompatImageView = (SkinAppCompatImageView) ViewBindings.findChildViewById(viewInflate, R.id.iv_start_up_background);
        if (skinAppCompatImageView != null) {
            return new FragmentStartUpBinding((ConstraintLayout) viewInflate, skinAppCompatImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(viewInflate.getResources().getResourceName(R.id.iv_start_up_background)));
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public final View getRoot() {
        return this.a;
    }
}
