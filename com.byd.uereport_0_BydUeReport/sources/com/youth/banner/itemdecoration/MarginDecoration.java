package com.youth.banner.itemdecoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class MarginDecoration extends RecyclerView.n {
    private int mMarginPx;

    public MarginDecoration(int i8) {
        this.mMarginPx = i8;
    }

    private LinearLayoutManager requireLinearLayoutManager(RecyclerView recyclerView) {
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return (LinearLayoutManager) layoutManager;
        }
        throw new IllegalStateException("The layoutManager must be LinearLayoutManager");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (requireLinearLayoutManager(recyclerView).getOrientation() == 1) {
            int i8 = this.mMarginPx;
            rect.top = i8;
            rect.bottom = i8;
        } else {
            int i9 = this.mMarginPx;
            rect.left = i9;
            rect.right = i9;
        }
    }
}
