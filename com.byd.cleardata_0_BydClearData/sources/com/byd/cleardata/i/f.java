package com.byd.cleardata.i;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class f extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2699c;

    public f(int i, int i2, boolean z) {
        this.f2697a = i;
        this.f2698b = i2;
        this.f2699c = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        int iD0 = recyclerView.d0(view);
        int i = this.f2697a;
        int i2 = iD0 % i;
        if (this.f2699c) {
            int i3 = this.f2698b;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * i3) / i;
            if (iD0 < i) {
                rect.top = i3;
            }
            rect.bottom = i3;
            return;
        }
        int i4 = this.f2698b;
        rect.left = (i2 * i4) / i;
        rect.right = i4 - (((i2 + 1) * i4) / i);
        if (iD0 >= i) {
            rect.top = i4;
        }
    }
}
