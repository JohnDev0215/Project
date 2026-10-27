package com.byd.mvp.ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class RecyclerSpace extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f729a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f730b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f731c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f732d;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.f729a;
        rect.top = this.f730b;
        rect.right = this.f731c;
        rect.bottom = this.f732d;
    }
}
