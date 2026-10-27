package com.byd.music.view;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;

/* JADX INFO: loaded from: classes5.dex */
public class NoScrollCarouselLayoutManager extends CarouselLayoutManager {
    @Override // com.azoft.carousellayoutmanager.CarouselLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override // com.azoft.carousellayoutmanager.CarouselLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return false;
    }

    public NoScrollCarouselLayoutManager(int i) {
        super(i);
    }

    public NoScrollCarouselLayoutManager(int i, boolean z) {
        super(i, z);
    }
}
