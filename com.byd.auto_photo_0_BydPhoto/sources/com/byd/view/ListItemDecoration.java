package com.byd.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class ListItemDecoration extends RecyclerView.ItemDecoration {
    private boolean isHorizontal;
    private int spacing;

    public ListItemDecoration() {
        this(10);
    }

    public ListItemDecoration(int i) {
        this(i, false);
    }

    public ListItemDecoration(int i, boolean z) {
        this.spacing = i;
        this.isHorizontal = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (!this.isHorizontal) {
            rect.bottom = this.spacing;
            return;
        }
        int i = this.spacing >> 1;
        rect.left = i;
        rect.right = i;
    }
}
