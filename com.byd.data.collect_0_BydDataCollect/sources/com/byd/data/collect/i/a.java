package com.byd.data.collect.i;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.byd.data.collect.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: compiled from: ActivityMainBinding.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CoordinatorLayout f3139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final FloatingActionButton f3140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Toolbar f3141c;

    private a(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Toolbar toolbar) {
        this.f3139a = coordinatorLayout;
        this.f3140b = floatingActionButton;
        this.f3141c = toolbar;
    }

    public static a a(View view) {
        int i2 = R.id.fab;
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        if (floatingActionButton != null) {
            i2 = R.id.toolbar;
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            if (toolbar != null) {
                return new a((CoordinatorLayout) view, floatingActionButton, toolbar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    public static a c(LayoutInflater layoutInflater) {
        return d(layoutInflater, null, false);
    }

    public static a d(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return a(viewInflate);
    }

    public CoordinatorLayout b() {
        return this.f3139a;
    }
}
