package com.azoft.carousellayoutmanager;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class CenterScrollListener extends RecyclerView.OnScrollListener {
    private boolean mAutoSet = true;

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof CarouselLayoutManager)) {
            this.mAutoSet = true;
            return;
        }
        CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) layoutManager;
        if (!this.mAutoSet && i == 0) {
            int offsetCenterView = carouselLayoutManager.getOffsetCenterView();
            if (carouselLayoutManager.getOrientation() == 0) {
                recyclerView.smoothScrollBy(offsetCenterView, 0);
            } else {
                recyclerView.smoothScrollBy(0, offsetCenterView);
            }
            this.mAutoSet = true;
        }
        if (1 == i || 2 == i) {
            this.mAutoSet = false;
        }
    }
}
