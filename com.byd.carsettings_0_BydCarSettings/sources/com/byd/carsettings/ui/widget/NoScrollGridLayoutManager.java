package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;

/* JADX INFO: loaded from: classes2.dex */
public class NoScrollGridLayoutManager extends GridLayoutManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3440a;

    public NoScrollGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f3440a = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.f3440a && super.canScrollHorizontally();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.f3440a && super.canScrollVertically();
    }

    public NoScrollGridLayoutManager(Context context, int i) {
        super(context, i);
        this.f3440a = false;
    }
}
