package com.byd.filemanager.k;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SpacesItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3638b;

    public m(int i, int i2) {
        this.f3637a = i;
        this.f3638b = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        boolean z = ((RecyclerView.p) view.getLayoutParams()).u() == recyclerView.getAdapter().c() - 1;
        if (this.f3638b == 0) {
            rect.right = z ? 0 : this.f3637a;
        } else {
            rect.bottom = z ? 0 : this.f3637a;
        }
    }
}
