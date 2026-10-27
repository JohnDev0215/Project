package com.android.car.apps.common.widget;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.apps.common.util.PagedSmoothScroller;

/* JADX INFO: loaded from: classes.dex */
public class PagedSnapHelper extends LinearSnapHelper {
    public final Context a;
    public RecyclerView b;

    @Nullable
    public OrientationHelper c;

    @Nullable
    public OrientationHelper d;

    public PagedSnapHelper(Context context) {
        this.a = context;
    }

    public final int a(@NonNull View view, OrientationHelper orientationHelper) {
        return orientationHelper.getDecoratedStart(view) - orientationHelper.getStartAfterPadding();
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public final void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        this.b = recyclerView;
        super.attachToRecyclerView(recyclerView);
    }

    public final View b(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int iAbs;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            if (childAt != null && (iAbs = Math.abs(orientationHelper.getDecoratedStart(childAt) - orientationHelper.getStartAfterPadding())) < i) {
                view = childAt;
                i = iAbs;
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public final int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = a(view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = a(view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c9  */
    @Override // androidx.recyclerview.widget.SnapHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] calculateScrollDistance(int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.apps.common.widget.PagedSnapHelper.calculateScrollDistance(int, int):int[]");
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public final RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        return new PagedSmoothScroller(this.a);
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public final View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (this.b.computeVerticalScrollRange() - this.b.computeVerticalScrollOffset() <= this.b.getPaddingBottom() + this.b.getPaddingTop() + (layoutManager.canScrollVertically() ? getVerticalHelper(layoutManager) : getHorizontalHelper(layoutManager)).getTotalSpace()) {
            return null;
        }
        if (layoutManager.canScrollVertically()) {
            return b(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return b(layoutManager, getHorizontalHelper(layoutManager));
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
