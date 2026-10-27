package com.google.android.material.appbar;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* JADX INFO: compiled from: AppBarLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements androidx.core.view.accessibility.a {
    public final /* synthetic */ CoordinatorLayout e;
    public final /* synthetic */ AppBarLayout f;
    public final /* synthetic */ View g;
    public final /* synthetic */ int h;
    public final /* synthetic */ AppBarLayout.BaseBehavior i;

    public c(AppBarLayout.BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        this.i = baseBehavior;
        this.e = coordinatorLayout;
        this.f = appBarLayout;
        this.g = view;
        this.h = i;
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        this.i.D(this.e, this.f, this.g, this.h, new int[]{0, 0});
        return true;
    }
}
