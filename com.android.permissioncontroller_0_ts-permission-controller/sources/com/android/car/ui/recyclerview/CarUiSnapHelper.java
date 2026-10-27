package com.android.car.ui.recyclerview;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiSnapHelper extends LinearSnapHelper {
    private static final float LONG_ITEM_END_VISIBLE_THRESHOLD = 0.3f;
    private static final float VIEW_VISIBLE_THRESHOLD = 0.5f;
    private final Context mContext;
    private OrientationHelper mHorizontalHelper;
    private RecyclerView mRecyclerView;
    private OrientationHelper mVerticalHelper;

    public CarUiSnapHelper(Context context) {
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] out = new int[2];
        if (!this.mRecyclerView.isInTouchMode()) {
            return out;
        }
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToTopMargin(targetView, getHorizontalHelper(layoutManager));
        }
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToTopMargin(targetView, getVerticalHelper(layoutManager));
        }
        return out;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        if (childCount == 1) {
            View firstChild = layoutManager.getChildAt(0);
            if (isValidSnapView(firstChild, orientationHelper)) {
                return firstChild;
            }
            return null;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return null;
        }
        View firstChild2 = recyclerView.getChildAt(0);
        if (firstChild2.getHeight() > this.mRecyclerView.getHeight() && orientationHelper.getDecoratedStart(firstChild2) < 0 && orientationHelper.getDecoratedEnd(firstChild2) > this.mRecyclerView.getHeight() * LONG_ITEM_END_VISIBLE_THRESHOLD) {
            return null;
        }
        View childAt = layoutManager.getChildAt(childCount - 1);
        Objects.requireNonNull(childAt);
        View lastVisibleChild = childAt;
        boolean lastItemVisible = layoutManager.getPosition(lastVisibleChild) == layoutManager.getItemCount() - 1;
        float lastItemPercentageVisible = lastItemVisible ? getPercentageVisible(lastVisibleChild, orientationHelper) : 0.0f;
        View closestChild = null;
        int closestDistanceToStart = Integer.MAX_VALUE;
        float closestPercentageVisible = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            int startOffset = orientationHelper.getDecoratedStart(child);
            if (Math.abs(startOffset) < closestDistanceToStart) {
                float percentageVisible = getPercentageVisible(child, orientationHelper);
                if (percentageVisible > VIEW_VISIBLE_THRESHOLD && percentageVisible > closestPercentageVisible) {
                    closestDistanceToStart = startOffset;
                    closestChild = child;
                    closestPercentageVisible = percentageVisible;
                }
            }
        }
        View childToReturn = closestChild;
        if (childToReturn == null || (lastItemVisible && lastItemPercentageVisible > closestPercentageVisible)) {
            childToReturn = lastVisibleChild;
        }
        if (isValidSnapView(childToReturn, orientationHelper)) {
            return childToReturn;
        }
        return null;
    }

    private static int distanceToTopMargin(View targetView, OrientationHelper helper) {
        int childTop = helper.getDecoratedStart(targetView);
        int containerTop = helper.getStartAfterPadding();
        return childTop - containerTop;
    }

    private static View findTopView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int absDistance;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }
        View closestChild = null;
        int absClosest = Integer.MAX_VALUE;
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            if (child != null && (absDistance = Math.abs(distanceToTopMargin(child, helper))) < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    private static boolean isValidSnapView(View view, OrientationHelper helper) {
        return helper.getDecoratedMeasurement(view) <= helper.getTotalSpace();
    }

    static float getPercentageVisible(View view, OrientationHelper helper) {
        int start = helper.getStartAfterPadding();
        int end = helper.getEndAfterPadding();
        int viewStart = helper.getDecoratedStart(view);
        int viewEnd = helper.getDecoratedEnd(view);
        if (viewStart >= start && viewEnd <= end) {
            return 1.0f;
        }
        if (viewEnd <= start || viewStart >= end) {
            return 0.0f;
        }
        if (viewStart <= start && viewEnd >= end) {
            return (end - start) / helper.getDecoratedMeasurement(view);
        }
        if (viewStart < start) {
            return (viewEnd - start) / helper.getDecoratedMeasurement(view);
        }
        return (end - viewStart) / helper.getDecoratedMeasurement(view);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return new CarUiSmoothScroller(this.mContext);
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
        View childAt = layoutManager.getChildAt(lastChildPosition);
        Objects.requireNonNull(childAt);
        View lastChild = childAt;
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

    public int estimateNextPositionDiffForScrollDistance(OrientationHelper helper, int scrollDistance) {
        float distancePerChild = computeDistancePerChild(helper.getLayoutManager(), helper);
        if (distancePerChild <= 0.0f) {
            return 0;
        }
        return Math.round(scrollDistance / distancePerChild);
    }

    float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        View minPosView = null;
        View maxPosView = null;
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            int pos = layoutManager.getPosition(child);
            if (pos != -1) {
                if (pos < minPos) {
                    minPos = pos;
                    minPosView = child;
                }
                if (pos > maxPos) {
                    maxPos = pos;
                    maxPosView = child;
                }
            }
        }
        if (minPosView == null || maxPosView == null) {
            return 1.0f;
        }
        int start = Math.min(helper.getDecoratedStart(minPosView), helper.getDecoratedStart(maxPosView));
        int end = Math.max(helper.getDecoratedEnd(minPosView), helper.getDecoratedEnd(maxPosView));
        int distance = end - start;
        if (distance != 0) {
            return (distance * 1.0f) / ((maxPos - minPos) + 1);
        }
        return 0.0f;
    }

    public boolean isAtStart(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        View childAt = layoutManager.getChildAt(0);
        Objects.requireNonNull(childAt);
        View firstChild = childAt;
        OrientationHelper orientationHelper = layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager);
        if (orientationHelper.getDecoratedStart(firstChild) >= orientationHelper.getStartAfterPadding() && layoutManager.getPosition(firstChild) == 0) {
            return true;
        }
        return false;
    }

    public boolean isAtEnd(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        int childCount = layoutManager.getChildCount();
        OrientationHelper orientationHelper = layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager);
        View childAt = layoutManager.getChildAt(childCount - 1);
        Objects.requireNonNull(childAt);
        View lastVisibleChild = childAt;
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
