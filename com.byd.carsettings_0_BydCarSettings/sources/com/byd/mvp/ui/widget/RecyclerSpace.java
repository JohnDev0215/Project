package com.byd.mvp.ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class RecyclerSpace extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3804c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3805d;

    public RecyclerSpace(int i, int i2, int i3, int i4) {
        this.f3802a = i;
        this.f3803b = i2;
        this.f3804c = i3;
        this.f3805d = i4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.f3802a;
        rect.top = this.f3803b;
        rect.right = this.f3804c;
        rect.bottom = this.f3805d;
    }
}
