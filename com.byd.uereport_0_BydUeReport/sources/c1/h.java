package c1;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j f2268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f2269b;

    public final float a(RecyclerView.o oVar, j jVar) {
        int childCount = oVar.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        int i8 = Integer.MIN_VALUE;
        int i9 = Integer.MAX_VALUE;
        View view2 = null;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = oVar.getChildAt(i10);
            int position = oVar.getPosition(childAt);
            if (position != -1) {
                if (position < i9) {
                    view = childAt;
                    i9 = position;
                }
                if (position > i8) {
                    view2 = childAt;
                    i8 = position;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int iMax = Math.max(jVar.d(view), jVar.d(view2)) - Math.min(jVar.g(view), jVar.g(view2));
        if (iMax == 0) {
            return 1.0f;
        }
        return (iMax * 1.0f) / ((i8 - i9) + 1);
    }

    public final int b(RecyclerView.o oVar, j jVar, int i8, int i9) {
        int[] iArrCalculateScrollDistance = calculateScrollDistance(i8, i9);
        float fA = a(oVar, jVar);
        if (fA <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(iArrCalculateScrollDistance[0]) > Math.abs(iArrCalculateScrollDistance[1]) ? iArrCalculateScrollDistance[0] : iArrCalculateScrollDistance[1]) / fA);
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

    public final int distanceToCenter(RecyclerView.o oVar, View view, j jVar) {
        return (jVar.g(view) + (jVar.e(view) / 2)) - (jVar.m() + (jVar.n() / 2));
    }

    public final View findCenterView(RecyclerView.o oVar, j jVar) {
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // c1.o
    public int findTargetSnapPosition(RecyclerView.o oVar, int i8, int i9) {
        int itemCount;
        View viewFindSnapView;
        int position;
        int i10;
        PointF pointFComputeScrollVectorForPosition;
        int iB;
        int iB2;
        if (!(oVar instanceof RecyclerView.y.b) || (itemCount = oVar.getItemCount()) == 0 || (viewFindSnapView = findSnapView(oVar)) == null || (position = oVar.getPosition(viewFindSnapView)) == -1 || (pointFComputeScrollVectorForPosition = ((RecyclerView.y.b) oVar).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        if (oVar.canScrollHorizontally()) {
            iB = b(oVar, getHorizontalHelper(oVar), i8, 0);
            if (pointFComputeScrollVectorForPosition.x < 0.0f) {
                iB = -iB;
            }
        } else {
            iB = 0;
        }
        if (oVar.canScrollVertically()) {
            iB2 = b(oVar, getVerticalHelper(oVar), 0, i9);
            if (pointFComputeScrollVectorForPosition.y < 0.0f) {
                iB2 = -iB2;
            }
        } else {
            iB2 = 0;
        }
        if (oVar.canScrollVertically()) {
            iB = iB2;
        }
        if (iB == 0) {
            return -1;
        }
        int i11 = position + iB;
        int i12 = i11 >= 0 ? i11 : 0;
        return i12 >= itemCount ? i10 : i12;
    }

    public final j getHorizontalHelper(RecyclerView.o oVar) {
        j jVar = this.f2269b;
        if (jVar == null || jVar.f2271a != oVar) {
            this.f2269b = j.a(oVar);
        }
        return this.f2269b;
    }

    public final j getVerticalHelper(RecyclerView.o oVar) {
        j jVar = this.f2268a;
        if (jVar == null || jVar.f2271a != oVar) {
            this.f2268a = j.c(oVar);
        }
        return this.f2268a;
    }
}
