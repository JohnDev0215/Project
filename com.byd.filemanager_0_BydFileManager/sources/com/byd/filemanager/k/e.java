package com.byd.filemanager.k;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: GridSpacingItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3611a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3612b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3613c;

    public e(int i, int i2, boolean z) {
        this.f3611a = i;
        this.f3612b = i2;
        this.f3613c = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        int iE0 = recyclerView.e0(view);
        int i = this.f3611a;
        int i2 = iE0 % i;
        if (this.f3613c) {
            int i3 = this.f3612b;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * i3) / i;
            if (iE0 < i) {
                rect.top = i3;
            }
            rect.bottom = i3;
            return;
        }
        int i4 = this.f3612b;
        rect.left = (i2 * i4) / i;
        rect.right = i4 - (((i2 + 1) * i4) / i);
        if (iE0 >= i) {
            rect.top = i4;
        }
    }
}
