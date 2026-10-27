package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeableSectionsVerticalContainer extends SwipeablePageRelativeLayout {
    private long sectionDuration;
    private float speed;
    private SwipeablePagesStyleSheet stylesheet;

    public StageSwipeableSectionsVerticalContainer(Context context) {
        super(context);
    }

    public StageSwipeableSectionsVerticalContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StageSwipeableSectionsVerticalContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setSectionDuration(int i) {
        this.sectionDuration = i;
    }

    public void setSpeed(int i) {
        this.speed = TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void setStyleSheet(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.stylesheet = swipeablePagesStyleSheet;
    }
}
