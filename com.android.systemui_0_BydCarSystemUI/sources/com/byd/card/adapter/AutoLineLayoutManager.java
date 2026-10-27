package com.byd.card.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.card.utils.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class AutoLineLayoutManager extends RecyclerView.LayoutManager {
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int width = getWidth();
        int i = 0;
        int iMax = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < getItemCount(); i3++) {
            View viewForPosition = recycler.getViewForPosition(i3);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int i4 = i + decoratedMeasuredWidth;
            if (i4 <= width) {
                if (AppUtils.isRtl(viewForPosition.getContext())) {
                    int i5 = width - i4;
                    layoutDecorated(viewForPosition, i5, i2, i5 + decoratedMeasuredWidth, i2 + decoratedMeasuredHeight);
                } else {
                    layoutDecorated(viewForPosition, i4 - decoratedMeasuredWidth, i2, i4, i2 + decoratedMeasuredHeight);
                }
                iMax = Math.max(iMax, decoratedMeasuredHeight);
                i = i4;
            } else {
                if (iMax == 0) {
                    iMax = decoratedMeasuredHeight;
                }
                i2 += iMax;
                if (AppUtils.isRtl(viewForPosition.getContext())) {
                    layoutDecorated(viewForPosition, width - decoratedMeasuredWidth, i2, width, i2 + decoratedMeasuredHeight);
                } else {
                    layoutDecorated(viewForPosition, 0, i2, decoratedMeasuredWidth, i2 + decoratedMeasuredHeight);
                }
                i = decoratedMeasuredWidth;
                iMax = decoratedMeasuredHeight;
            }
        }
    }
}
