package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentMultipleThemeBinding implements ViewBinding {
    public final TextView appCopyright;
    public final TextView appVersion;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;

    private FragmentMultipleThemeBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.appCopyright = textView;
        this.appVersion = textView2;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentMultipleThemeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentMultipleThemeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_multiple_theme, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentMultipleThemeBinding bind(View view) {
        int i = R.id.app_copyright;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.app_copyright);
        if (textView != null) {
            i = R.id.app_version;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.app_version);
            if (textView2 != null) {
                i = R.id.recycler_view;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                if (recyclerView != null) {
                    return new FragmentMultipleThemeBinding((ConstraintLayout) view, textView, textView2, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
