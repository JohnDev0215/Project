package com.amazon.music.explore.views.swipeablePages.pages;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeableSectionsViewHolder extends SwipeablePageViewHolder {
    private final SwipeableSectionsView view;

    public SwipeableSectionsViewHolder(View view) {
        super(view);
        this.view = (SwipeableSectionsView) view;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder
    public SwipeableSectionsView getView() {
        return this.view;
    }
}
