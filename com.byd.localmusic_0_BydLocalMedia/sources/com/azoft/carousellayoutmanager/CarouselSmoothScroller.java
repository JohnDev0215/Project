package com.azoft.carousellayoutmanager;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class CarouselSmoothScroller {
    public CarouselSmoothScroller(RecyclerView.State state, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("position can't be less then 0. position is : " + i);
        }
        if (i < state.getItemCount()) {
            return;
        }
        throw new IllegalArgumentException("position can't be great then adapter items count. position is : " + i);
    }

    public PointF computeScrollVectorForPosition(int i, CarouselLayoutManager carouselLayoutManager) {
        return carouselLayoutManager.computeScrollVectorForPosition(i);
    }

    public int calculateDyToMakeVisible(View view, CarouselLayoutManager carouselLayoutManager) {
        if (carouselLayoutManager.canScrollVertically()) {
            return carouselLayoutManager.getOffsetForCurrentView(view);
        }
        return 0;
    }

    public int calculateDxToMakeVisible(View view, CarouselLayoutManager carouselLayoutManager) {
        if (carouselLayoutManager.canScrollHorizontally()) {
            return carouselLayoutManager.getOffsetForCurrentView(view);
        }
        return 0;
    }
}
