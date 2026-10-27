package c1;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends o {
    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    private j mHorizontalHelper;
    private j mVerticalHelper;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    public class a extends g {
        public a(Context context) {
            super(context);
        }

        @Override // c1.g
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // c1.g
        public int calculateTimeForScrolling(int i8) {
            return Math.min(100, super.calculateTimeForScrolling(i8));
        }

        @Override // c1.g, androidx.recyclerview.widget.RecyclerView.y
        public void onTargetFound(View view, RecyclerView.z zVar, RecyclerView.y.a aVar) {
            k kVar = k.this;
            int[] iArrCalculateDistanceToFinalSnap = kVar.calculateDistanceToFinalSnap(kVar.mRecyclerView.getLayoutManager(), view);
            int i8 = iArrCalculateDistanceToFinalSnap[0];
            int i9 = iArrCalculateDistanceToFinalSnap[1];
            int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i8), Math.abs(i9)));
            if (iCalculateTimeForDeceleration > 0) {
                aVar.d(i8, i9, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }
    }

    private int distanceToCenter(RecyclerView.o oVar, View view, j jVar) {
        return (jVar.g(view) + (jVar.e(view) / 2)) - (jVar.m() + (jVar.n() / 2));
    }

    private View findCenterView(RecyclerView.o oVar, j jVar) {
        int childCount = oVar.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int iM = jVar.m() + (jVar.n() / 2);
        int i8 = Integer.MAX_VALUE;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = oVar.getChildAt(i9);
            int iAbs = Math.abs((jVar.g(childAt) + (jVar.e(childAt) / 2)) - iM);
            if (iAbs < i8) {
                view = childAt;
                i8 = iAbs;
            }
        }
        return view;
    }

    private j getHorizontalHelper(RecyclerView.o oVar) {
        j jVar = this.mHorizontalHelper;
        if (jVar == null || jVar.f2271a != oVar) {
            this.mHorizontalHelper = j.a(oVar);
        }
        return this.mHorizontalHelper;
    }

    private j getOrientationHelper(RecyclerView.o oVar) {
        if (oVar.canScrollVertically()) {
            return getVerticalHelper(oVar);
        }
        if (oVar.canScrollHorizontally()) {
            return getHorizontalHelper(oVar);
        }
        return null;
    }

    private j getVerticalHelper(RecyclerView.o oVar) {
        j jVar = this.mVerticalHelper;
        if (jVar == null || jVar.f2271a != oVar) {
            this.mVerticalHelper = j.c(oVar);
        }
        return this.mVerticalHelper;
    }

    private boolean isForwardFling(RecyclerView.o oVar, int i8, int i9) {
        return oVar.canScrollHorizontally() ? i8 > 0 : i9 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isReverseLayout(RecyclerView.o oVar) {
        PointF pointFComputeScrollVectorForPosition;
        int itemCount = oVar.getItemCount();
        if (!(oVar instanceof RecyclerView.y.b) || (pointFComputeScrollVectorForPosition = ((RecyclerView.y.b) oVar).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return false;
        }
        return pointFComputeScrollVectorForPosition.x < 0.0f || pointFComputeScrollVectorForPosition.y < 0.0f;
    }

    @Override // c1.o
    public int[] calculateDistanceToFinalSnap(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(oVar, view, getHorizontalHelper(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.canScrollVertically()) {
            iArr[1] = distanceToCenter(oVar, view, getVerticalHelper(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // c1.o
    public g createSnapScroller(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.y.b) {
            return new a(this.mRecyclerView.getContext());
        }
        return null;
    }

    @Override // c1.o
    public View findSnapView(RecyclerView.o oVar) {
        if (oVar.canScrollVertically()) {
            return findCenterView(oVar, getVerticalHelper(oVar));
        }
        if (oVar.canScrollHorizontally()) {
            return findCenterView(oVar, getHorizontalHelper(oVar));
        }
        return null;
    }

    @Override // c1.o
    public int findTargetSnapPosition(RecyclerView.o oVar, int i8, int i9) {
        j orientationHelper;
        int itemCount = oVar.getItemCount();
        if (itemCount == 0 || (orientationHelper = getOrientationHelper(oVar)) == null) {
            return -1;
        }
        int i10 = Integer.MIN_VALUE;
        int i11 = Integer.MAX_VALUE;
        int childCount = oVar.getChildCount();
        View view = null;
        View view2 = null;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = oVar.getChildAt(i12);
            if (childAt != null) {
                int iDistanceToCenter = distanceToCenter(oVar, childAt, orientationHelper);
                if (iDistanceToCenter <= 0 && iDistanceToCenter > i10) {
                    view2 = childAt;
                    i10 = iDistanceToCenter;
                }
                if (iDistanceToCenter >= 0 && iDistanceToCenter < i11) {
                    view = childAt;
                    i11 = iDistanceToCenter;
                }
            }
        }
        boolean zIsForwardFling = isForwardFling(oVar, i8, i9);
        if (zIsForwardFling && view != null) {
            return oVar.getPosition(view);
        }
        if (!zIsForwardFling && view2 != null) {
            return oVar.getPosition(view2);
        }
        if (zIsForwardFling) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int position = oVar.getPosition(view) + (isReverseLayout(oVar) == zIsForwardFling ? -1 : 1);
        if (position < 0 || position >= itemCount) {
            return -1;
        }
        return position;
    }
}
