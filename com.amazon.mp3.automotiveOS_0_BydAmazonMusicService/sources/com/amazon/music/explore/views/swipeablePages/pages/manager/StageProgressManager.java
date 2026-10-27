package com.amazon.music.explore.views.swipeablePages.pages.manager;

import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView;

/* JADX INFO: loaded from: classes4.dex */
public interface StageProgressManager {
    int getCurrentPageIndex();

    void goToNextPage(float f, boolean z);

    void goToPage(int i, StageSwipeableSectionsView.Direction direction);

    void goToPreviousPage(float f);

    void setCurrentPageIndex(int i);
}
