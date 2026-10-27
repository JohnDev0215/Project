package com.android.car.ui.recyclerview;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes2.dex */
class FastScroller implements View.OnTouchListener {
    private int mClickActionThreshold;
    private RecyclerView mRecyclerView;
    private View mScrollThumb;
    private View mScrollTrackView;
    private float mTouchDownY = -1.0f;

    FastScroller(RecyclerView recyclerView, View scrollTrackView, View scrollView) {
        this.mRecyclerView = recyclerView;
        this.mScrollTrackView = scrollTrackView;
        this.mScrollThumb = CarUiUtils.requireViewByRefId(scrollView, R.id.car_ui_scrollbar_thumb);
        this.mClickActionThreshold = ViewConfiguration.get(recyclerView.getContext()).getScaledTouchSlop();
    }

    void enable() {
        if (this.mRecyclerView != null) {
            this.mScrollTrackView.setOnTouchListener(this);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent me) {
        int action = me.getAction();
        if (action == 0) {
            float thumbBottom = me.getY();
            this.mTouchDownY = thumbBottom;
        } else if (action == 2) {
            float thumbBottom2 = this.mScrollThumb.getY() + this.mScrollThumb.getHeight();
            if (me.getY() + this.mScrollTrackView.getY() >= thumbBottom2 || me.getY() + this.mScrollTrackView.getY() <= this.mScrollThumb.getY()) {
                return true;
            }
            float thumbCenter = this.mScrollThumb.getY() + (this.mScrollThumb.getHeight() / 2.0f);
            verticalScrollTo((me.getY() - this.mTouchDownY) + thumbCenter);
            this.mTouchDownY = me.getY();
        } else {
            if (isClick(this.mTouchDownY, me.getY())) {
                verticalScrollTo(me.getY() + this.mScrollTrackView.getY());
            }
            this.mTouchDownY = -1.0f;
        }
        return true;
    }

    private boolean isClick(float startY, float endY) {
        return Math.abs(startY - endY) < ((float) this.mClickActionThreshold);
    }

    private void verticalScrollTo(float y) {
        int scrollingBy = calculateScrollDistance(y);
        if (scrollingBy != 0) {
            this.mRecyclerView.scrollBy(0, scrollingBy);
        }
    }

    private int calculateScrollDistance(float newDragPos) {
        int[] scrollbarRange = getVerticalRange();
        int scrollbarLength = scrollbarRange[1] - scrollbarRange[0];
        float thumbCenter = this.mScrollThumb.getY() + (this.mScrollThumb.getHeight() / 2.0f);
        if (scrollbarLength == 0) {
            return 0;
        }
        float percentage = (newDragPos - thumbCenter) / scrollbarLength;
        int totalPossibleOffset = this.mRecyclerView.computeVerticalScrollRange() - this.mRecyclerView.getHeight();
        return (int) (totalPossibleOffset * percentage);
    }

    private int[] getVerticalRange() {
        int[] verticalRange = {((int) this.mScrollTrackView.getY()) + (this.mScrollThumb.getHeight() / 2), (((int) this.mScrollTrackView.getY()) + this.mScrollTrackView.getHeight()) - (this.mScrollThumb.getHeight() / 2)};
        return verticalRange;
    }
}
