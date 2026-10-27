package com.google.android.material.tabs;

import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: compiled from: TabLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements View.OnLayoutChangeListener {
    public final /* synthetic */ View e;
    public final /* synthetic */ TabLayout.TabView f;

    public d(TabLayout.TabView tabView, View view) {
        this.f = tabView;
        this.e = view;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        View view2 = this.e;
        if (view2.getVisibility() == 0) {
            int i9 = TabLayout.TabView.p;
            this.f.c(view2);
        }
    }
}
