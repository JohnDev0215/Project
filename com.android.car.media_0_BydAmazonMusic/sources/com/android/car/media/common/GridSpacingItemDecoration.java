package com.android.car.media.common;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    public final int a;

    public GridSpacingItemDecoration(int i) {
        this.a = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        int spanIndex = layoutParams.getSpanIndex();
        if (gridLayoutManager == null) {
            Log.e("GridSpacingItemDecoration", "getItemOffsets layoutManager == null !!!");
            return;
        }
        int spanCount = gridLayoutManager.getSpanCount();
        int i = this.a;
        rect.left = (spanIndex * i) / spanCount;
        rect.right = i - (((spanIndex + 1) * i) / spanCount);
    }
}
