package com.byd.base.view.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class RVGridItemDecoration extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f587a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f590d;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(this.f587a, this.f588b, this.f589c, this.f590d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }
}
