package com.byd.music.view.commonAdapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int hSpace;
    private int vSpace;

    public GridItemDecoration(int i) {
        this.hSpace = i;
        this.vSpace = i;
    }

    public GridItemDecoration(int i, int i2) {
        this.hSpace = i;
        this.vSpace = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.hSpace;
        rect.right = this.hSpace;
        rect.bottom = this.vSpace;
        rect.top = this.vSpace;
    }
}
