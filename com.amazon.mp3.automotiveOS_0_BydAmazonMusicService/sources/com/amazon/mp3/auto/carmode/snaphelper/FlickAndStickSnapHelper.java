package com.amazon.mp3.auto.carmode.snaphelper;

import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FlickAndStickSnapHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\bH\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J \u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0016J\u0018\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/auto/carmode/snaphelper/FlickAndStickSnapHelper;", "Landroidx/recyclerview/widget/SnapHelper;", "speedMsPerInch", "", "itemsToFlick", "", "(FI)V", "coordinateHelper", "Lcom/amazon/mp3/auto/carmode/snaphelper/CoordinateHelper;", "orientationHelper", "Landroidx/recyclerview/widget/OrientationHelper;", "previousClosestPosition", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "attachToRecyclerView", "", "calculateDistanceToFinalSnap", "", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "targetView", "Landroid/view/View;", "createLayoutPositionHelper", "createScroller", "Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;", "findSnapView", "findTargetSnapPosition", "velocityX", "velocityY", "getCoordinateDelta", "getOrientationHelper", "isReachedToTheEndEdge", "", "view", "isReachedToTheStartEdge", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FlickAndStickSnapHelper extends SnapHelper {
    private final CoordinateHelper coordinateHelper;
    private final int itemsToFlick;
    private OrientationHelper orientationHelper;
    private int previousClosestPosition;
    private RecyclerView recyclerView;
    private final float speedMsPerInch;

    public FlickAndStickSnapHelper() {
        this(0.0f, 0, 3, null);
    }

    public /* synthetic */ FlickAndStickSnapHelper(float f, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 50.0f : f, (i2 & 2) != 0 ? 1 : i);
    }

    public FlickAndStickSnapHelper(float f, int i) {
        this.speedMsPerInch = f;
        this.itemsToFlick = i;
        this.coordinateHelper = createLayoutPositionHelper();
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        Intrinsics.checkNotNullParameter(targetView, "targetView");
        int coordinateDelta = getCoordinateDelta(targetView, getOrientationHelper(layoutManager));
        int[] iArr = new int[2];
        iArr[0] = layoutManager.canScrollHorizontally() ? coordinateDelta : 0;
        if (!layoutManager.canScrollVertically()) {
            coordinateDelta = 0;
        }
        iArr[1] = coordinateDelta;
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        int itemCount = layoutManager.getItemCount() - 1;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        int i3 = -1;
        while (i2 < childCount) {
            int i4 = i2 + 1;
            View childAt = layoutManager.getChildAt(i2);
            if (childAt == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            int position = layoutManager.getPosition(childAt);
            int iAbs = Math.abs(getCoordinateDelta(childAt, orientationHelper));
            if ((this.previousClosestPosition != 0 && position == 0 && isReachedToTheStartEdge(childAt, layoutManager)) || (this.previousClosestPosition != itemCount && position == itemCount && isReachedToTheEndEdge(childAt, layoutManager))) {
                view = childAt;
                i3 = position;
                break;
            }
            if (iAbs < i) {
                view = childAt;
                i2 = i4;
                i3 = position;
                i = iAbs;
            } else {
                i2 = i4;
            }
        }
        if (i3 != -1) {
            this.previousClosestPosition = i3;
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        boolean z = velocityY > 0;
        if (z && this.previousClosestPosition + this.itemsToFlick >= layoutManager.getItemCount()) {
            return this.previousClosestPosition;
        }
        int i = this.previousClosestPosition;
        return z ? i + this.itemsToFlick : i - this.itemsToFlick;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected RecyclerView.SmoothScroller createScroller(final RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView;
        if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) && (recyclerView = this.recyclerView) != null) {
            return new LinearSmoothScroller(recyclerView.getContext()) { // from class: com.amazon.mp3.auto.carmode.snaphelper.FlickAndStickSnapHelper.createScroller.1
                @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
                protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                    Intrinsics.checkNotNullParameter(targetView, "targetView");
                    Intrinsics.checkNotNullParameter(state, "state");
                    Intrinsics.checkNotNullParameter(action, "action");
                    int[] iArrCalculateDistanceToFinalSnap = FlickAndStickSnapHelper.this.calculateDistanceToFinalSnap(layoutManager, targetView);
                    int i = iArrCalculateDistanceToFinalSnap[0];
                    int i2 = iArrCalculateDistanceToFinalSnap[1];
                    int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
                    if (iCalculateTimeForDeceleration > 0) {
                        action.update(i, i2, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    Intrinsics.checkNotNullParameter(displayMetrics, "displayMetrics");
                    return FlickAndStickSnapHelper.this.speedMsPerInch / displayMetrics.densityDpi;
                }
            };
        }
        return null;
    }

    private final int getCoordinateDelta(View targetView, OrientationHelper orientationHelper) {
        return this.coordinateHelper.getTargetCoordinate(targetView, orientationHelper) - this.coordinateHelper.getBaseCoordinate(orientationHelper);
    }

    private final OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelperCreateVerticalHelper;
        OrientationHelper orientationHelper = this.orientationHelper;
        if (orientationHelper == null) {
            if (layoutManager.canScrollHorizontally()) {
                orientationHelperCreateVerticalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
            } else {
                if (!layoutManager.canScrollVertically()) {
                    throw new IllegalStateException("unknown orientation");
                }
                orientationHelperCreateVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
            }
            orientationHelper = orientationHelperCreateVerticalHelper;
            this.orientationHelper = orientationHelper;
            Intrinsics.checkNotNullExpressionValue(orientationHelper, "when {\n      layoutManag…ewOrientationHelper\n    }");
        }
        return orientationHelper;
    }

    private final CoordinateHelper createLayoutPositionHelper() {
        return new StartCoordinateHelper();
    }

    private final boolean isReachedToTheStartEdge(View view, RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        StartCoordinateHelper startCoordinateHelper = new StartCoordinateHelper();
        return Math.abs(startCoordinateHelper.getTargetCoordinate(view, orientationHelper) - startCoordinateHelper.getBaseCoordinate(orientationHelper)) == 0;
    }

    private final boolean isReachedToTheEndEdge(View view, RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        EndCoordinateHelper endCoordinateHelper = new EndCoordinateHelper();
        return Math.abs(endCoordinateHelper.getTargetCoordinate(view, orientationHelper) - endCoordinateHelper.getBaseCoordinate(orientationHelper)) == 0;
    }
}
