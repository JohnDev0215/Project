package com.amazon.music.explore.views.swipeablePages.pages;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeableSectionsViewHolder extends SwipeablePageViewHolder {
    private final StageSwipeableSectionsView view;

    public StageSwipeableSectionsViewHolder(View view) {
        super(view);
        this.view = (StageSwipeableSectionsView) view;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder
    public StageSwipeableSectionsView getView() {
        return this.view;
    }
}
