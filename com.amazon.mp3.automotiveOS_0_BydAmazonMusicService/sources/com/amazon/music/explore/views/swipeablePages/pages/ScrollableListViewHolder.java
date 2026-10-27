package com.amazon.music.explore.views.swipeablePages.pages;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollableListViewHolder extends SwipeablePageViewHolder {
    private final ScrollableListView view;

    public ScrollableListViewHolder(View view) {
        super(view);
        this.view = (ScrollableListView) view;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder
    public ScrollableListView getView() {
        return this.view;
    }
}
