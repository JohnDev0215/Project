package spi;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.recyclerview.CarUiSmoothScroller;
import java.util.Objects;

/* JADX INFO: compiled from: CarUiSnapHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t1 extends LinearSnapHelper {
    public final Context a;

    @Nullable
    public RecyclerView b;

    @Nullable
    public OrientationHelper c;

    @Nullable
    public OrientationHelper d;

    public t1(Context context) {
        this.a = context;
    }

    public static float a(View view, OrientationHelper orientationHelper) {
        float f;
        int decoratedMeasurement;
        int startAfterPadding = orientationHelper.getStartAfterPadding();
        int endAfterPadding = orientationHelper.getEndAfterPadding();
        int decoratedStart = orientationHelper.getDecoratedStart(view);
        int decoratedEnd = orientationHelper.getDecoratedEnd(view);
        if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
            return 1.0f;
        }
        if (decoratedEnd <= startAfterPadding || decoratedStart >= endAfterPadding) {
            return 0.0f;
        }
        if (decoratedStart <= startAfterPadding && decoratedEnd >= endAfterPadding) {
            f = endAfterPadding - startAfterPadding;
            decoratedMeasurement = orientationHelper.getDecoratedMeasurement(view);
        } else {
            if (decoratedStart >= startAfterPadding) {
                return (endAfterPadding - decoratedStart) / orientationHelper.getDecoratedMeasurement(view);
            }
            f = decoratedEnd - startAfterPadding;
            decoratedMeasurement = orientationHelper.getDecoratedMeasurement(view);
        }
        return f / decoratedMeasurement;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public final void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        this.b = recyclerView;
    }

    public final boolean b(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        int childCount = layoutManager.getChildCount();
        OrientationHelper verticalHelper = layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager);
        View childAt = layoutManager.getChildAt(childCount - 1);
        Objects.requireNonNull(childAt);
        return layoutManager.getPosition(childAt) == layoutManager.getItemCount() - 1 && layoutManager.getDecoratedBottom(childAt) <= verticalHelper.getEndAfterPadding();
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public final int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && !recyclerView.isInTouchMode()) {
            return iArr;
        }
        if (layoutManager.canScrollHorizontally()) {
            OrientationHelper horizontalHelper = getHorizontalHelper(layoutManager);
            iArr[0] = horizontalHelper.getDecoratedStart(view) - horizontalHelper.getStartAfterPadding();
        }
        if (layoutManager.canScrollVertically()) {
            OrientationHelper verticalHelper = getVerticalHelper(layoutManager);
            iArr[1] = verticalHelper.getDecoratedStart(view) - verticalHelper.getStartAfterPadding();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public final int[] calculateScrollDistance(int i, int i2) {
        RecyclerView.LayoutManager layoutManager;
        int[] iArrCalculateScrollDistance = super.calculateScrollDistance(i, i2);
        RecyclerView recyclerView = this.b;
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null && layoutManager.getChildCount() != 0) {
            int childCount = b(layoutManager) ? 0 : layoutManager.getChildCount() - 1;
            OrientationHelper verticalHelper = layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager);
            View childAt = layoutManager.getChildAt(childCount);
            Objects.requireNonNull(childAt);
            float fA = a(childAt, verticalHelper);
            int height = layoutManager.getHeight();
            if (fA > 0.0f) {
                height -= layoutManager.getDecoratedMeasuredHeight(childAt);
            }
            int i3 = -height;
            iArrCalculateScrollDistance[0] = Math.max(i3, Math.min(height, iArrCalculateScrollDistance[0]));
            iArrCalculateScrollDistance[1] = Math.max(i3, Math.min(height, iArrCalculateScrollDistance[1]));
        }
        return iArrCalculateScrollDistance;
    }

    public final float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            if (childAt != null) {
                int position = layoutManager.getPosition(childAt);
                if (position != -1) {
                    if (position < i2) {
                        view = childAt;
                        i2 = position;
                    }
                    if (position > i) {
                        view2 = childAt;
                        i = position;
                    }
                }
            } else {
                Log.e("CarUiSnapHelper", "computeDistancePerChild child == null !!!");
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int iMax = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2));
        if (iMax == 0) {
            return 0.0f;
        }
        return (iMax * 1.0f) / ((i - i2) + 1);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public final RecyclerView.SmoothScroller createScroller(@NonNull RecyclerView.LayoutManager layoutManager) {
        return new CarUiSmoothScroller(this.a);
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    @Nullable
    public final View findSnapView(RecyclerView.LayoutManager layoutManager) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }
        OrientationHelper verticalHelper = layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager);
        if (childCount == 1) {
            View childAt = layoutManager.getChildAt(0);
            if (verticalHelper.getDecoratedMeasurement(childAt) <= verticalHelper.getTotalSpace()) {
                return childAt;
            }
            return null;
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            return null;
        }
        View childAt2 = recyclerView.getChildAt(0);
        if (childAt2.getHeight() > this.b.getHeight() && verticalHelper.getDecoratedStart(childAt2) < 0 && verticalHelper.getDecoratedEnd(childAt2) > this.b.getHeight() * 0.3f) {
            return null;
        }
        View childAt3 = layoutManager.getChildAt(childCount - 1);
        Objects.requireNonNull(childAt3);
        boolean z = layoutManager.getPosition(childAt3) == layoutManager.getItemCount() - 1;
        float f = 0.0f;
        float fA = z ? a(childAt3, verticalHelper) : 0.0f;
        int i = Integer.MAX_VALUE;
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt4 = layoutManager.getChildAt(i2);
            int decoratedStart = verticalHelper.getDecoratedStart(childAt4);
            if (Math.abs(decoratedStart) < i) {
                float fA2 = a(childAt4, verticalHelper);
                if (fA2 > 0.5f && fA2 > f) {
                    view = childAt4;
                    i = decoratedStart;
                    f = fA2;
                }
            }
        }
        if (view != null && (!z || fA <= f)) {
            childAt3 = view;
        }
        if (verticalHelper.getDecoratedMeasurement(childAt3) <= verticalHelper.getTotalSpace()) {
            return childAt3;
        }
        return null;
    }

    @NonNull
    public final OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.d;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.d = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.d;
    }

    @NonNull
    public final OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.c;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.c = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.c;
    }
}
