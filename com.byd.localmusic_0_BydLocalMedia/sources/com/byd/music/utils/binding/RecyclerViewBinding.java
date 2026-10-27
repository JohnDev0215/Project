package com.byd.music.utils.binding;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class RecyclerViewBinding {
    public static void setRecyclerView(RecyclerView recyclerView, int i, int i2, int i3) {
        LinearLayoutManager linearLayoutManager;
        int dimension = i2 == 0 ? 0 : (int) recyclerView.getResources().getDimension(i2);
        int dimension2 = i3 == 0 ? 0 : (int) recyclerView.getResources().getDimension(i3);
        if (i == 0) {
            linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.addItemDecoration(new MyItemDecoration(dimension, dimension2));
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), i);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(i, dimension, dimension2, false));
            linearLayoutManager = gridLayoutManager;
        }
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private static class MyItemDecoration extends RecyclerView.ItemDecoration {
        private final int gapH;
        private final int gapV;

        MyItemDecoration(int i, int i2) {
            this.gapH = i;
            this.gapV = i2;
            LogUtil.d("MyItemDecoration", "gapH:" + i);
            LogUtil.d("MyItemDecoration", "gapV:" + i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.bottom = this.gapV / 2;
            rect.top = this.gapV / 2;
            rect.left = this.gapH / 2;
            rect.right = this.gapH / 2;
        }
    }

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private boolean includeEdge;
        private int spacingH;
        private int spacingV;
        private int spanCount;

        public GridSpacingItemDecoration(int i, int i2, int i3, boolean z) {
            this.spanCount = i;
            this.spacingH = i2;
            this.spacingV = i3;
            this.includeEdge = z;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = this.spanCount;
            int i2 = childAdapterPosition % i;
            if (this.includeEdge) {
                int i3 = this.spacingH;
                rect.left = i3 - ((i2 * i3) / i);
                rect.right = ((i2 + 1) * this.spacingH) / this.spanCount;
                if (childAdapterPosition < this.spanCount) {
                    rect.top = this.spacingV;
                }
                rect.bottom = this.spacingV;
                return;
            }
            rect.left = (this.spacingH * i2) / i;
            int i4 = this.spacingH;
            rect.right = i4 - (((i2 + 1) * i4) / this.spanCount);
            if (childAdapterPosition >= this.spanCount) {
                rect.top = this.spacingV;
            }
        }
    }

    public static void setOrientation(RecyclerView recyclerView, int i, boolean z) {
        LinearLayoutManager linearLayoutManager;
        if (z) {
            linearLayoutManager = new CenterLinearManagerLayout(recyclerView.getContext());
        } else {
            linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        }
        linearLayoutManager.setOrientation(i);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
