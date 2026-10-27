package com.byd.base.view.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class RVGridItemDecoration extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2515c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2516d;

    public RVGridItemDecoration(int i, int i2, int i3, int i4) {
        this.f2513a = i;
        this.f2514b = i2;
        this.f2515c = i3;
        this.f2516d = i4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(this.f2513a, this.f2514b, this.f2515c, this.f2516d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }
}
