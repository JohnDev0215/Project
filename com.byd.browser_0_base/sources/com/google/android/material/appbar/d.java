package com.google.android.material.appbar;

import android.view.View;

/* JADX INFO: compiled from: AppBarLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements androidx.core.view.accessibility.a {
    public final /* synthetic */ AppBarLayout e;
    public final /* synthetic */ boolean f;

    public d(AppBarLayout appBarLayout, boolean z) {
        this.e = appBarLayout;
        this.f = z;
    }

    @Override // androidx.core.view.accessibility.a
    public final boolean f(View view) {
        this.e.setExpanded(this.f);
        return true;
    }
}
