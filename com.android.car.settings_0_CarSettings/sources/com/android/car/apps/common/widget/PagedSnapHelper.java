package com.android.car.apps.common.widget;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.apps.common.util.PagedSmoothScroller;

/* JADX INFO: loaded from: classes5.dex */
public class PagedSnapHelper extends LinearSnapHelper {
    private final Context mContext;
    private OrientationHelper mHorizontalHelper;
    private RecyclerView mRecyclerView;
    private OrientationHelper mVerticalHelper;

    public PagedSnapHelper(Context context) {
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToTopMargin(layoutManager, targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToTopMargin(layoutManager, targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        if (this.mRecyclerView.computeVerticalScrollRange() - this.mRecyclerView.computeVerticalScrollOffset() <= orientationHelper.getTotalSpace() + this.mRecyclerView.getPaddingTop() + this.mRecyclerView.getPaddingBottom()) {
            return null;
        }
        if (layoutManager.canScrollVertically()) {
            return findTopView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findTopView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private int distanceToTopMargin(RecyclerView.LayoutManager layoutManager, View targetView, OrientationHelper helper) {
        int childTop = helper.getDecoratedStart(targetView);
        int containerTop = helper.getStartAfterPadding();
        return childTop - containerTop;
    }

    private View findTopView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int absDistance;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }
        View closestChild = null;
        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            if (child != null && (absDistance = Math.abs(distanceToTopMargin(layoutManager, child, helper))) < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    private float getPercentageVisible(View view, OrientationHelper helper) {
        int start = helper.getStartAfterPadding();
        int end = helper.getEndAfterPadding();
        int viewHeight = helper.getDecoratedMeasurement(view);
        int viewStart = helper.getDecoratedStart(view);
        int viewEnd = helper.getDecoratedEnd(view);
        if (viewEnd < start) {
            return 0.0f;
        }
        if (viewStart >= start && viewEnd <= end) {
            return 1.0f;
        }
        if (viewStart > start || viewEnd < end) {
            return viewStart < start ? 1.0f - (Math.abs(viewStart) / helper.getDecoratedMeasurement(view)) : 1.0f - (Math.abs(viewEnd) / helper.getDecoratedMeasurement(view));
        }
        return 1.0f - ((Math.abs(viewStart) + Math.abs(viewEnd)) / viewHeight);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        super.attachToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return new PagedSmoothScroller(this.mContext);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        RecyclerView.LayoutManager layoutManager;
        int[] outDist = super.calculateScrollDistance(velocityX, velocityY);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || layoutManager.getChildCount() == 0) {
            return outDist;
        }
        int lastChildPosition = isAtEnd(layoutManager) ? 0 : layoutManager.getChildCount() - 1;
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        View lastChild = layoutManager.getChildAt(lastChildPosition);
        float percentageVisible = getPercentageVisible(lastChild, orientationHelper);
        int maxDistance = layoutManager.getHeight();
        if (percentageVisible > 0.0f) {
            maxDistance -= layoutManager.getDecoratedMeasuredHeight(lastChild);
        }
        int minDistance = -maxDistance;
        outDist[0] = clamp(outDist[0], minDistance, maxDistance);
        outDist[1] = clamp(outDist[1], minDistance, maxDistance);
        return outDist;
    }

    boolean isAtStart(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper;
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        View firstChild = layoutManager.getChildAt(0);
        if (layoutManager.canScrollVertically()) {
            orientationHelper = getVerticalHelper(layoutManager);
        } else {
            orientationHelper = getHorizontalHelper(layoutManager);
        }
        if (orientationHelper.getDecoratedStart(firstChild) >= orientationHelper.getStartAfterPadding() && layoutManager.getPosition(firstChild) == 0) {
            return true;
        }
        return false;
    }

    public boolean isAtEnd(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper;
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        int childCount = layoutManager.getChildCount();
        if (layoutManager.canScrollVertically()) {
            orientationHelper = getVerticalHelper(layoutManager);
        } else {
            orientationHelper = getHorizontalHelper(layoutManager);
        }
        View lastVisibleChild = layoutManager.getChildAt(childCount - 1);
        return layoutManager.getPosition(lastVisibleChild) == layoutManager.getItemCount() - 1 && layoutManager.getDecoratedBottom(lastVisibleChild) <= orientationHelper.getEndAfterPadding();
    }

    private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return getVerticalHelper(layoutManager);
        }
        return getHorizontalHelper(layoutManager);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
