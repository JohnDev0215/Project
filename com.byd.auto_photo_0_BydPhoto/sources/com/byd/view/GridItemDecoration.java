package com.byd.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int horizontalSpacing;
    private int verticalSpacing;

    public GridItemDecoration() {
        this(10);
    }

    public GridItemDecoration(int i) {
        this.horizontalSpacing = i;
        this.verticalSpacing = i;
    }

    public GridItemDecoration(int i, int i2) {
        this.horizontalSpacing = i;
        this.verticalSpacing = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.horizontalSpacing;
        if (i == 0 && this.verticalSpacing == 0) {
            return;
        }
        int i2 = i >> 1;
        rect.left = i2;
        rect.right = i2;
        int i3 = this.verticalSpacing >> 1;
        rect.top = i3;
        rect.bottom = i3;
    }
}
