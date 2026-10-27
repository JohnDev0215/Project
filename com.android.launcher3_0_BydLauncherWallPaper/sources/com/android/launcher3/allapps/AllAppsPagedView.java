package com.android.launcher3.allapps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.android.launcher3.PagedView;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsPagedView extends PagedView<PersonalWorkSlidingTabStrip> {
    static final float MAX_SWIPE_ANGLE = 1.0471976f;
    static final float START_DAMPING_TOUCH_SLOP_ANGLE = 0.5235988f;
    static final float TOUCH_SLOP_DAMPING_FACTOR = 4.0f;

    @Override // com.android.launcher3.PagedView
    protected String getCurrentPageDescription() {
        return "";
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public AllAppsPagedView(Context context) {
        this(context, null);
    }

    public AllAppsPagedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AllAppsPagedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ((PersonalWorkSlidingTabStrip) this.mPageIndicator).setScroll(l, this.mMaxScrollX);
    }

    @Override // com.android.launcher3.PagedView
    protected void determineScrollingStart(MotionEvent ev) {
        float fAbs = Math.abs(ev.getX() - getDownMotionX());
        float fAbs2 = Math.abs(ev.getY() - getDownMotionY());
        if (Float.compare(fAbs, 0.0f) == 0) {
            return;
        }
        float fAtan = (float) Math.atan(fAbs2 / fAbs);
        if (fAbs > this.mTouchSlop || fAbs2 > this.mTouchSlop) {
            cancelCurrentPageLongPress();
        }
        if (fAtan > MAX_SWIPE_ANGLE) {
            return;
        }
        if (fAtan > START_DAMPING_TOUCH_SLOP_ANGLE) {
            super.determineScrollingStart(ev, (((float) Math.sqrt((fAtan - START_DAMPING_TOUCH_SLOP_ANGLE) / START_DAMPING_TOUCH_SLOP_ANGLE)) * TOUCH_SLOP_DAMPING_FACTOR) + 1.0f);
        } else {
            super.determineScrollingStart(ev);
        }
    }
}
