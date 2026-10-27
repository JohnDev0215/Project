package com.android.launcher3.widget;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.BaseRecyclerView;
import com.android.launcher3.R;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetsRecyclerView extends BaseRecyclerView implements RecyclerView.OnItemTouchListener {
    private WidgetsListAdapter mAdapter;
    private final Point mFastScrollerOffset;
    private final int mScrollbarTop;
    private boolean mTouchDownOnScroller;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public WidgetsRecyclerView(Context context) {
        this(context, null);
    }

    public WidgetsRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetsRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFastScrollerOffset = new Point();
        this.mScrollbarTop = getResources().getDimensionPixelSize(R.dimen.dynamic_grid_edge_margin);
        addOnItemTouchListener(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        this.mAdapter = (WidgetsListAdapter) adapter;
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public String scrollToPositionAtProgress(float touchFraction) {
        if (isModelNotReady()) {
            return "";
        }
        stopScroll();
        float itemCount = this.mAdapter.getItemCount() * touchFraction;
        ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(0, (int) (-(getAvailableScrollHeight() * touchFraction)));
        if (touchFraction == 1.0f) {
            itemCount -= 1.0f;
        }
        return this.mAdapter.getSectionName((int) itemCount);
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public void onUpdateScrollbar(int dy) {
        if (isModelNotReady()) {
            return;
        }
        int currentScrollY = getCurrentScrollY();
        if (currentScrollY < 0) {
            this.mScrollbar.setThumbOffsetY(-1);
        } else {
            synchronizeScrollBarThumbOffsetToViewScroll(currentScrollY, getAvailableScrollHeight());
        }
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public int getCurrentScrollY() {
        if (isModelNotReady() || getChildCount() == 0) {
            return -1;
        }
        View childAt = getChildAt(0);
        int measuredHeight = childAt.getMeasuredHeight() * getChildPosition(childAt);
        return (getPaddingTop() + measuredHeight) - getLayoutManager().getDecoratedTop(childAt);
    }

    @Override // com.android.launcher3.BaseRecyclerView
    protected int getAvailableScrollHeight() {
        return ((getChildAt(0).getMeasuredHeight() * this.mAdapter.getItemCount()) - getScrollbarTrackHeight()) - this.mScrollbarTop;
    }

    private boolean isModelNotReady() {
        return this.mAdapter.getItemCount() == 0;
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public int getScrollBarTop() {
        return this.mScrollbarTop;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (e.getAction() == 0) {
            this.mTouchDownOnScroller = this.mScrollbar.isHitInParent(e.getX(), e.getY(), this.mFastScrollerOffset);
        }
        if (this.mTouchDownOnScroller) {
            return this.mScrollbar.handleTouchEvent(e, this.mFastScrollerOffset);
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        if (this.mTouchDownOnScroller) {
            this.mScrollbar.handleTouchEvent(e, this.mFastScrollerOffset);
        }
    }
}
