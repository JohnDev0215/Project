package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class PointLineGridLayoutManager extends GridLayoutManager {
    private int childCount;
    private int itemHeight;

    public PointLineGridLayoutManager(Context context, int i) {
        super(context, i);
        this.itemHeight = 0;
        this.childCount = 0;
    }

    public PointLineGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.itemHeight = 0;
        this.childCount = 0;
    }

    public PointLineGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
        this.itemHeight = 0;
        this.childCount = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
        int i5;
        super.layoutDecoratedWithMargins(view, i, i2, i3, i4);
        if (this.itemHeight != 0 || (i5 = i4 - i2) == 0) {
            return;
        }
        this.itemHeight = i5;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }

    public void setCustomChildCount(int i) {
        this.childCount = i;
    }

    public int getCustomChildCount() {
        return this.childCount;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException unused) {
        }
    }
}
