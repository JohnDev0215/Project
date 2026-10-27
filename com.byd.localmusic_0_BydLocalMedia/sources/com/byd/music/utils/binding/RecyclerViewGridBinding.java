package com.byd.music.utils.binding;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;

/* JADX INFO: loaded from: classes5.dex */
public class RecyclerViewGridBinding {
    public static void setRecyclerView(RecyclerView recyclerView, int i, int i2, int i3) {
        LinearLayoutManager linearLayoutManager;
        if (i == 0) {
            linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), OrientationUtil.isPortrait() ? 4 : i);
            if (OrientationUtil.isPortrait()) {
                i = 4;
            }
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(i, i2, i3, false));
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
        private final boolean isRTL;
        private int spacingH;
        private int spacingV;
        private int spanCount;

        public GridSpacingItemDecoration(int i, int i2, int i3, boolean z) {
            this.spanCount = i;
            this.spacingH = i2;
            this.spacingV = i3;
            this.includeEdge = z;
            this.isRTL = false;
            LogUtil.d("MyItemDecoration", "isRTL: false");
        }

        public GridSpacingItemDecoration(int i, int i2, int i3, boolean z, boolean z2) {
            this.spanCount = i;
            this.spacingH = i2;
            this.spacingV = i3;
            this.includeEdge = z;
            this.isRTL = z2;
            LogUtil.d("MyItemDecoration", "isRTL: " + z2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = childAdapterPosition / this.spanCount;
            StringBuilder sb = new StringBuilder("getItemOffsets");
            sb.append(" includeEdge= ");
            sb.append(this.includeEdge);
            sb.append(" isRTL= ");
            sb.append(this.isRTL);
            sb.append(" spanCount= ");
            sb.append(this.spanCount);
            sb.append(" position= ");
            sb.append(childAdapterPosition);
            sb.append(" row= ");
            sb.append(i);
            if (this.includeEdge) {
                if (this.isRTL) {
                    int i2 = this.spacingH;
                    rect.right = i2 - ((i * i2) / this.spanCount);
                    rect.left = ((i + 1) * this.spacingH) / this.spanCount;
                } else {
                    int i3 = this.spacingH;
                    rect.left = i3 - ((i * i3) / this.spanCount);
                    rect.right = ((i + 1) * this.spacingH) / this.spanCount;
                }
                if (childAdapterPosition < this.spanCount) {
                    rect.top = this.spacingV;
                }
                rect.bottom = this.spacingV;
            } else {
                rect.right = this.spacingH / 2;
                rect.left = this.spacingH / 2;
                if (childAdapterPosition >= this.spanCount) {
                    rect.top = this.spacingV / 2;
                }
                rect.bottom = this.spacingV / 2;
            }
            sb.append(" spacingH= ");
            sb.append(this.spacingH);
            sb.append(" spacingV= ");
            sb.append(this.spacingV);
            sb.append(" left= ");
            sb.append(rect.left);
            sb.append(" right= ");
            sb.append(rect.right);
            LogUtil.e("getItemOffsets", sb.toString());
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
