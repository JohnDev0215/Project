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
import com.byd.automultipletheme.widget.MyScrollView;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentWallpaperBinding implements ViewBinding {
    public final MyScrollView ScrollViewBg;
    public final TextView appCopyright;
    public final TextView appVersion;
    public final RecyclerView recyclerView;
    private final ConstraintLayout rootView;

    private FragmentWallpaperBinding(ConstraintLayout constraintLayout, MyScrollView myScrollView, TextView textView, TextView textView2, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.ScrollViewBg = myScrollView;
        this.appCopyright = textView;
        this.appVersion = textView2;
        this.recyclerView = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentWallpaperBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentWallpaperBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_wallpaper, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentWallpaperBinding bind(View view) {
        int i = R.id.ScrollViewBg;
        MyScrollView myScrollView = (MyScrollView) ViewBindings.findChildViewById(view, R.id.ScrollViewBg);
        if (myScrollView != null) {
            i = R.id.app_copyright;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.app_copyright);
            if (textView != null) {
                i = R.id.app_version;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.app_version);
                if (textView2 != null) {
                    i = R.id.recycler_view;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                    if (recyclerView != null) {
                        return new FragmentWallpaperBinding((ConstraintLayout) view, myScrollView, textView, textView2, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
