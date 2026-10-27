package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentAddCarouselChildBinding implements ViewBinding {
    public final ProgressBar progressBar;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;

    private FragmentAddCarouselChildBinding(ConstraintLayout constraintLayout, ProgressBar progressBar, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAddCarouselChildBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAddCarouselChildBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_add_carousel_child, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentAddCarouselChildBinding bind(View view) {
        int i = R.id.progressBar;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressBar);
        if (progressBar != null) {
            i = R.id.recycler_view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
            if (recyclerView != null) {
                return new FragmentAddCarouselChildBinding((ConstraintLayout) view, progressBar, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
