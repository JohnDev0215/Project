package com.vivid.spotify.common.ui;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.base.i;

/* JADX INFO: compiled from: ListSpaceItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f5228a = "ListSpaceItemDecoration";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5229b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f5230c;

    public c(int i7, int i8) {
        this.f5229b = i8;
        this.f5230c = i7;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.f5230c != 0) {
            rect.bottom = this.f5229b;
        } else if (i.a()) {
            rect.left = this.f5229b;
        } else {
            rect.right = this.f5229b;
        }
    }

    public int j() {
        return this.f5229b;
    }

    public int k() {
        return this.f5230c;
    }
}
