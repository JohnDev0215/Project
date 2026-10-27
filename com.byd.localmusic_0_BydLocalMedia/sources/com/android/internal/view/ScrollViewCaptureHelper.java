package com.android.internal.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollViewCaptureHelper implements ScrollCaptureViewHelper<ViewGroup> {
    private int mOverScrollMode;
    private boolean mScrollBarEnabled;
    private int mStartScrollY;

    @Override // com.android.internal.view.ScrollCaptureViewHelper
    public void onPrepareForStart(ViewGroup viewGroup, Rect rect) {
        this.mStartScrollY = viewGroup.getScrollY();
        int overScrollMode = viewGroup.getOverScrollMode();
        this.mOverScrollMode = overScrollMode;
        if (overScrollMode != 2) {
            viewGroup.setOverScrollMode(2);
        }
        boolean zIsVerticalScrollBarEnabled = viewGroup.isVerticalScrollBarEnabled();
        this.mScrollBarEnabled = zIsVerticalScrollBarEnabled;
        if (zIsVerticalScrollBarEnabled) {
            viewGroup.setVerticalScrollBarEnabled(false);
        }
    }

    @Override // com.android.internal.view.ScrollCaptureViewHelper
    public Rect onScrollRequested(ViewGroup viewGroup, Rect rect, Rect rect2) {
        View childAt = viewGroup.getChildAt(0);
        if (childAt == null) {
            return null;
        }
        int scrollY = viewGroup.getScrollY() - this.mStartScrollY;
        Rect rect3 = new Rect(rect2);
        rect3.offset(0, -scrollY);
        rect3.offset(rect.left, rect.top);
        Rect rect4 = new Rect(rect3);
        rect4.offset(viewGroup.getScrollX() - childAt.getLeft(), viewGroup.getScrollY() - childAt.getTop());
        childAt.requestRectangleOnScreen(new Rect(rect4), true);
        int scrollY2 = viewGroup.getScrollY() - this.mStartScrollY;
        Point point = new Point();
        Rect rect5 = new Rect(rect4);
        if (!viewGroup.getChildVisibleRect(childAt, rect5, point)) {
            rect5.setEmpty();
            return rect5;
        }
        rect5.offset(-point.x, -point.y);
        rect5.offset(childAt.getLeft() - viewGroup.getScrollX(), childAt.getTop() - viewGroup.getScrollY());
        rect5.offset(-rect.left, -rect.top);
        rect5.offset(0, scrollY2);
        return rect5;
    }

    @Override // com.android.internal.view.ScrollCaptureViewHelper
    public void onPrepareForEnd(ViewGroup viewGroup) {
        viewGroup.scrollTo(0, this.mStartScrollY);
        int i = this.mOverScrollMode;
        if (i != 2) {
            viewGroup.setOverScrollMode(i);
        }
        if (this.mScrollBarEnabled) {
            viewGroup.setVerticalScrollBarEnabled(true);
        }
    }
}
