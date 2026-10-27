package com.byd.cleardata.i;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class p extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2723a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2724b;

    public p(int i, int i2) {
        this.f2723a = i;
        this.f2724b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        boolean z = ((RecyclerView.p) view.getLayoutParams()).u() == recyclerView.getAdapter().c() - 1;
        if (this.f2724b == 0) {
            rect.right = z ? 0 : this.f2723a;
        } else {
            rect.bottom = z ? 0 : this.f2723a;
        }
    }
}
