package com.android.launcher3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.views.RecyclerViewFastScroller;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseRecyclerView extends RecyclerView {
    protected RecyclerViewFastScroller mScrollbar;

    protected abstract int getAvailableScrollHeight();

    public abstract int getCurrentScrollY();

    public void onFastScrollCompleted() {
    }

    public abstract void onUpdateScrollbar(int dy);

    public abstract String scrollToPositionAtProgress(float touchFraction);

    public boolean supportsFastScrolling() {
        return true;
    }

    public BaseRecyclerView(Context context) {
        this(context, null);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        bindFastScrollbar();
    }

    public void bindFastScrollbar() {
        ViewGroup viewGroup = (ViewGroup) getParent().getParent();
        RecyclerViewFastScroller recyclerViewFastScroller = (RecyclerViewFastScroller) viewGroup.findViewById(R.id.fast_scroller);
        this.mScrollbar = recyclerViewFastScroller;
        recyclerViewFastScroller.setRecyclerView(this, (TextView) viewGroup.findViewById(R.id.fast_scroller_popup));
        onUpdateScrollbar(0);
    }

    public RecyclerViewFastScroller getScrollbar() {
        return this.mScrollbar;
    }

    public int getScrollBarTop() {
        return getPaddingTop();
    }

    public int getScrollbarTrackHeight() {
        return (this.mScrollbar.getHeight() - getScrollBarTop()) - getPaddingBottom();
    }

    protected int getAvailableScrollBarHeight() {
        return getScrollbarTrackHeight() - this.mScrollbar.getThumbHeight();
    }

    protected void synchronizeScrollBarThumbOffsetToViewScroll(int scrollY, int availableScrollHeight) {
        if (availableScrollHeight <= 0) {
            this.mScrollbar.setThumbOffsetY(-1);
        } else {
            this.mScrollbar.setThumbOffsetY((int) ((scrollY / availableScrollHeight) * getAvailableScrollBarHeight()));
        }
    }

    public boolean shouldContainerScroll(MotionEvent ev, View eventSource) {
        float[] fArr = {ev.getX(), ev.getY()};
        Utilities.mapCoordInSelfToDescendant(this.mScrollbar, eventSource, fArr);
        return !this.mScrollbar.shouldBlockIntercept((int) fArr[0], (int) fArr[1]) && getCurrentScrollY() == 0;
    }
}
