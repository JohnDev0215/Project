package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveShovelerSnapHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0018\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001e\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0016\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010!\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper;", "Landroidx/recyclerview/widget/LinearSnapHelper;", "()V", "context", "Landroid/content/Context;", "maxScrollDistance", "", "orientationHelper", "Landroidx/recyclerview/widget/OrientationHelper;", "scroller", "Landroid/widget/Scroller;", "attachToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "calculateDistanceToFinalSnap", "", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "targetView", "Landroid/view/View;", "calculateScrollDistance", "velocityX", "velocityY", "createScroller", "Landroidx/recyclerview/widget/RecyclerView$SmoothScroller;", "distanceToStart", "helper", "findFirstView", "findSnapView", "getOrientationHelper", "scrollToPositionWithSnap", "index", "snapOffset", "Companion", "OnSnapPositionChangeListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveShovelerSnapHelper extends LinearSnapHelper {
    private Context context;
    private int maxScrollDistance;
    private OrientationHelper orientationHelper;
    private Scroller scroller;

    /* JADX INFO: compiled from: DescriptiveShovelerSnapHelper.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;", "", "onSnapPositionChange", "", "oldPosition", "", "newPosition", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnSnapPositionChangeListener {
        void onSnapPositionChange(int oldPosition, int newPosition);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.context = recyclerView.getContext();
            this.scroller = new Scroller(this.context, new DecelerateInterpolator());
        } else {
            this.scroller = null;
            this.context = null;
        }
        super.attachToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findFirstView(layoutManager, getOrientationHelper(layoutManager));
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        Intrinsics.checkNotNullParameter(targetView, "targetView");
        return new int[]{distanceToStart(targetView, getOrientationHelper(layoutManager)), 0};
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        int endAfterPadding;
        int[] iArr = new int[2];
        OrientationHelper orientationHelper = this.orientationHelper;
        if (orientationHelper == null) {
            return iArr;
        }
        if (this.maxScrollDistance >= 0) {
            endAfterPadding = (orientationHelper.getEndAfterPadding() - orientationHelper.getStartAfterPadding()) / 3;
        } else {
            endAfterPadding = (orientationHelper.getEndAfterPadding() - orientationHelper.getStartAfterPadding()) / 8;
        }
        int i = endAfterPadding;
        this.maxScrollDistance = i;
        Scroller scroller = this.scroller;
        if (scroller != null) {
            scroller.fling(0, 0, velocityX, velocityY, -i, i, 0, 0);
        }
        Scroller scroller2 = this.scroller;
        iArr[0] = scroller2 == null ? 0 : scroller2.getFinalX();
        Scroller scroller3 = this.scroller;
        iArr[1] = scroller3 != null ? scroller3.getFinalY() : 0;
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return super.createScroller(layoutManager);
        }
        Context context = this.context;
        if (context == null) {
            return null;
        }
        return new LinearSmoothScroller(context, this, layoutManager) { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerSnapHelper.createScroller.1
            final /* synthetic */ Context $context;
            final /* synthetic */ RecyclerView.LayoutManager $layoutManager;
            final /* synthetic */ DescriptiveShovelerSnapHelper this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                this.$context = context;
                this.this$0 = this;
                this.$layoutManager = layoutManager;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
            protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                Intrinsics.checkNotNullParameter(targetView, "targetView");
                Intrinsics.checkNotNullParameter(state, "state");
                Intrinsics.checkNotNullParameter(action, "action");
                int[] iArrCalculateDistanceToFinalSnap = this.this$0.calculateDistanceToFinalSnap(this.$layoutManager, targetView);
                int i = iArrCalculateDistanceToFinalSnap[0];
                action.update(i, iArrCalculateDistanceToFinalSnap[1], Math.max(1, Math.min(1000, calculateTimeForDeceleration(Math.abs(i)))), this.mDecelerateInterpolator);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                Intrinsics.checkNotNullParameter(displayMetrics, "displayMetrics");
                return 500.0f / displayMetrics.densityDpi;
            }
        };
    }

    private final int distanceToStart(View targetView, OrientationHelper helper) {
        return (helper.getDecoratedStart(targetView) - helper.getStartAfterPadding()) - snapOffset();
    }

    private final int snapOffset() {
        Context context = this.context;
        Resources resources = context == null ? null : context.getResources();
        return (resources == null ? 0 : (int) resources.getDimension(R.dimen.spacer_20)) - (resources != null ? (int) resources.getDimension(R.dimen.spacer_8) : 0);
    }

    private final View findFirstView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int childCount;
        View view = null;
        if (layoutManager == null || (childCount = layoutManager.getChildCount()) == 0) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        int startAfterPadding = helper.getStartAfterPadding();
        int i2 = 0;
        while (i2 < childCount) {
            int i3 = i2 + 1;
            View childAt = layoutManager.getChildAt(i2);
            int iAbs = Math.abs(helper.getDecoratedStart(childAt) - startAfterPadding);
            if (iAbs < i) {
                view = childAt;
                i2 = i3;
                i = iAbs;
            } else {
                i2 = i3;
            }
        }
        return view;
    }

    private final OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.orientationHelper == null) {
            this.orientationHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        OrientationHelper orientationHelper = this.orientationHelper;
        if (orientationHelper != null) {
            return orientationHelper;
        }
        OrientationHelper orientationHelperCreateHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        Intrinsics.checkNotNullExpressionValue(orientationHelperCreateHorizontalHelper, "createHorizontalHelper(layoutManager)");
        return orientationHelperCreateHorizontalHelper;
    }

    public final void scrollToPositionWithSnap(int index, RecyclerView recyclerView) {
        View viewFindViewByPosition;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || (viewFindViewByPosition = layoutManager.findViewByPosition(index)) == null) {
            return;
        }
        View viewFindSnapView = findSnapView(layoutManager);
        int[] iArrCalculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, viewFindViewByPosition);
        if (iArrCalculateDistanceToFinalSnap[0] == 0 && iArrCalculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        int width = (viewFindSnapView == null ? 0 : viewFindSnapView.getWidth()) / 2;
        int i = iArrCalculateDistanceToFinalSnap[0];
        if (i > 0) {
            iArrCalculateDistanceToFinalSnap[0] = (i + snapOffset()) - width;
        }
        int i2 = iArrCalculateDistanceToFinalSnap[1];
        if (i2 > 0) {
            iArrCalculateDistanceToFinalSnap[1] = (i2 + snapOffset()) - width;
        }
        recyclerView.smoothScrollBy(iArrCalculateDistanceToFinalSnap[0], iArrCalculateDistanceToFinalSnap[1]);
    }
}
