package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import c1.g;
import c1.j;
import c1.m;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements RecyclerView.y.b {
    public static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    public final a mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final b mLayoutChunkResult;
    private c mLayoutState;
    public int mOrientation;
    public j mOrientationHelper;
    public SavedState mPendingSavedState;
    public int mPendingScrollPosition;
    public int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    public boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1574d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1575e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1576f;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean f() {
            return this.f1574d >= 0;
        }

        public void g() {
            this.f1574d = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            parcel.writeInt(this.f1574d);
            parcel.writeInt(this.f1575e);
            parcel.writeInt(this.f1576f ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f1574d = parcel.readInt();
            this.f1575e = parcel.readInt();
            this.f1576f = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1574d = savedState.f1574d;
            this.f1575e = savedState.f1575e;
            this.f1576f = savedState.f1576f;
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public j f1577a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1578b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1579c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1580d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1581e;

        public a() {
            e();
        }

        public void a() {
            this.f1579c = this.f1580d ? this.f1577a.i() : this.f1577a.m();
        }

        public void b(View view, int i8) {
            if (this.f1580d) {
                this.f1579c = this.f1577a.d(view) + this.f1577a.o();
            } else {
                this.f1579c = this.f1577a.g(view);
            }
            this.f1578b = i8;
        }

        public void c(View view, int i8) {
            int iO = this.f1577a.o();
            if (iO >= 0) {
                b(view, i8);
                return;
            }
            this.f1578b = i8;
            if (this.f1580d) {
                int i9 = (this.f1577a.i() - iO) - this.f1577a.d(view);
                this.f1579c = this.f1577a.i() - i9;
                if (i9 > 0) {
                    int iE = this.f1579c - this.f1577a.e(view);
                    int iM = this.f1577a.m();
                    int iMin = iE - (iM + Math.min(this.f1577a.g(view) - iM, 0));
                    if (iMin < 0) {
                        this.f1579c += Math.min(i9, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iG = this.f1577a.g(view);
            int iM2 = iG - this.f1577a.m();
            this.f1579c = iG;
            if (iM2 > 0) {
                int i10 = (this.f1577a.i() - Math.min(0, (this.f1577a.i() - iO) - this.f1577a.d(view))) - (iG + this.f1577a.e(view));
                if (i10 < 0) {
                    this.f1579c -= Math.min(iM2, -i10);
                }
            }
        }

        public boolean d(View view, RecyclerView.z zVar) {
            RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
            return !pVar.c() && pVar.a() >= 0 && pVar.a() < zVar.b();
        }

        public void e() {
            this.f1578b = -1;
            this.f1579c = Integer.MIN_VALUE;
            this.f1580d = false;
            this.f1581e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1578b + ", mCoordinate=" + this.f1579c + ", mLayoutFromEnd=" + this.f1580d + ", mValid=" + this.f1581e + '}';
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1582a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1583b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1584c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1585d;

        public void a() {
            this.f1582a = 0;
            this.f1583b = false;
            this.f1584c = false;
            this.f1585d = false;
        }
    }

    public static class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1587b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1588c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1589d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1590e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1591f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1592g;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1596k;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f1598m;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1586a = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1593h = 0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1594i = 0;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1595j = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public List<RecyclerView.c0> f1597l = null;

        public void a() {
            b(null);
        }

        public void b(View view) {
            View viewF = f(view);
            if (viewF == null) {
                this.f1589d = -1;
            } else {
                this.f1589d = ((RecyclerView.p) viewF.getLayoutParams()).a();
            }
        }

        public boolean c(RecyclerView.z zVar) {
            int i8 = this.f1589d;
            return i8 >= 0 && i8 < zVar.b();
        }

        public View d(RecyclerView.v vVar) {
            if (this.f1597l != null) {
                return e();
            }
            View viewO = vVar.o(this.f1589d);
            this.f1589d += this.f1590e;
            return viewO;
        }

        public final View e() {
            int size = this.f1597l.size();
            for (int i8 = 0; i8 < size; i8++) {
                View view = this.f1597l.get(i8).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
                if (!pVar.c() && this.f1589d == pVar.a()) {
                    b(view);
                    return view;
                }
            }
            return null;
        }

        public View f(View view) {
            int iA;
            int size = this.f1597l.size();
            View view2 = null;
            int i8 = Integer.MAX_VALUE;
            for (int i9 = 0; i9 < size; i9++) {
                View view3 = this.f1597l.get(i9).itemView;
                RecyclerView.p pVar = (RecyclerView.p) view3.getLayoutParams();
                if (view3 != view && !pVar.c() && (iA = (pVar.a() - this.f1589d) * this.f1590e) >= 0 && iA < i8) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i8 = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private int computeScrollExtent(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return m.a(zVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return m.b(zVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.z zVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return m.c(zVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findFirstReferenceChild(RecyclerView.v vVar, RecyclerView.z zVar) {
        return findReferenceChild(vVar, zVar, 0, getChildCount(), zVar.b());
    }

    private View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private View findLastReferenceChild(RecyclerView.v vVar, RecyclerView.z zVar) {
        return findReferenceChild(vVar, zVar, getChildCount() - 1, -1, zVar.b());
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
        return this.mShouldReverseLayout ? findFirstPartiallyOrCompletelyInvisibleChild() : findLastPartiallyOrCompletelyInvisibleChild();
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
        return this.mShouldReverseLayout ? findLastPartiallyOrCompletelyInvisibleChild() : findFirstPartiallyOrCompletelyInvisibleChild();
    }

    private View findReferenceChildClosestToEnd(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.mShouldReverseLayout ? findFirstReferenceChild(vVar, zVar) : findLastReferenceChild(vVar, zVar);
    }

    private View findReferenceChildClosestToStart(RecyclerView.v vVar, RecyclerView.z zVar) {
        return this.mShouldReverseLayout ? findLastReferenceChild(vVar, zVar) : findFirstReferenceChild(vVar, zVar);
    }

    private int fixLayoutEndGap(int i8, RecyclerView.v vVar, RecyclerView.z zVar, boolean z7) {
        int i9;
        int i10 = this.mOrientationHelper.i() - i8;
        if (i10 <= 0) {
            return 0;
        }
        int i11 = -scrollBy(-i10, vVar, zVar);
        int i12 = i8 + i11;
        if (!z7 || (i9 = this.mOrientationHelper.i() - i12) <= 0) {
            return i11;
        }
        this.mOrientationHelper.r(i9);
        return i9 + i11;
    }

    private int fixLayoutStartGap(int i8, RecyclerView.v vVar, RecyclerView.z zVar, boolean z7) {
        int iM;
        int iM2 = i8 - this.mOrientationHelper.m();
        if (iM2 <= 0) {
            return 0;
        }
        int i9 = -scrollBy(iM2, vVar, zVar);
        int i10 = i8 + i9;
        if (!z7 || (iM = i10 - this.mOrientationHelper.m()) <= 0) {
            return i9;
        }
        this.mOrientationHelper.r(-iM);
        return i9 - iM;
    }

    private View getChildClosestToEnd() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    private void layoutForPredictiveAnimations(RecyclerView.v vVar, RecyclerView.z zVar, int i8, int i9) {
        if (!zVar.g() || getChildCount() == 0 || zVar.e() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.c0> listK = vVar.k();
        int size = listK.size();
        int position = getPosition(getChildAt(0));
        int iE = 0;
        int iE2 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView.c0 c0Var = listK.get(i10);
            if (!c0Var.isRemoved()) {
                if (((c0Var.getLayoutPosition() < position) != this.mShouldReverseLayout ? (byte) -1 : (byte) 1) == -1) {
                    iE += this.mOrientationHelper.e(c0Var.itemView);
                } else {
                    iE2 += this.mOrientationHelper.e(c0Var.itemView);
                }
            }
        }
        this.mLayoutState.f1597l = listK;
        if (iE > 0) {
            updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i8);
            c cVar = this.mLayoutState;
            cVar.f1593h = iE;
            cVar.f1588c = 0;
            cVar.a();
            fill(vVar, this.mLayoutState, zVar, false);
        }
        if (iE2 > 0) {
            updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), i9);
            c cVar2 = this.mLayoutState;
            cVar2.f1593h = iE2;
            cVar2.f1588c = 0;
            cVar2.a();
            fill(vVar, this.mLayoutState, zVar, false);
        }
        this.mLayoutState.f1597l = null;
    }

    private void logChildren() {
        Log.d(TAG, "internal representation of views on the screen");
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            Log.d(TAG, "item " + getPosition(childAt) + ", coord:" + this.mOrientationHelper.g(childAt));
        }
        Log.d(TAG, "==============");
    }

    private void recycleByLayoutState(RecyclerView.v vVar, c cVar) {
        if (!cVar.f1586a || cVar.f1598m) {
            return;
        }
        int i8 = cVar.f1592g;
        int i9 = cVar.f1594i;
        if (cVar.f1591f == -1) {
            recycleViewsFromEnd(vVar, i8, i9);
        } else {
            recycleViewsFromStart(vVar, i8, i9);
        }
    }

    private void recycleChildren(RecyclerView.v vVar, int i8, int i9) {
        if (i8 == i9) {
            return;
        }
        if (i9 <= i8) {
            while (i8 > i9) {
                removeAndRecycleViewAt(i8, vVar);
                i8--;
            }
        } else {
            for (int i10 = i9 - 1; i10 >= i8; i10--) {
                removeAndRecycleViewAt(i10, vVar);
            }
        }
    }

    private void recycleViewsFromEnd(RecyclerView.v vVar, int i8, int i9) {
        int childCount = getChildCount();
        if (i8 < 0) {
            return;
        }
        int iH = (this.mOrientationHelper.h() - i8) + i9;
        if (this.mShouldReverseLayout) {
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                if (this.mOrientationHelper.g(childAt) < iH || this.mOrientationHelper.q(childAt) < iH) {
                    recycleChildren(vVar, 0, i10);
                    return;
                }
            }
            return;
        }
        int i11 = childCount - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            View childAt2 = getChildAt(i12);
            if (this.mOrientationHelper.g(childAt2) < iH || this.mOrientationHelper.q(childAt2) < iH) {
                recycleChildren(vVar, i11, i12);
                return;
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.v vVar, int i8, int i9) {
        if (i8 < 0) {
            return;
        }
        int i10 = i8 - i9;
        int childCount = getChildCount();
        if (!this.mShouldReverseLayout) {
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                if (this.mOrientationHelper.d(childAt) > i10 || this.mOrientationHelper.p(childAt) > i10) {
                    recycleChildren(vVar, 0, i11);
                    return;
                }
            }
            return;
        }
        int i12 = childCount - 1;
        for (int i13 = i12; i13 >= 0; i13--) {
            View childAt2 = getChildAt(i13);
            if (this.mOrientationHelper.d(childAt2) > i10 || this.mOrientationHelper.p(childAt2) > i10) {
                recycleChildren(vVar, i12, i13);
                return;
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && aVar.d(focusedChild, zVar)) {
            aVar.c(focusedChild, getPosition(focusedChild));
            return true;
        }
        if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
        }
        View viewFindReferenceChildClosestToEnd = aVar.f1580d ? findReferenceChildClosestToEnd(vVar, zVar) : findReferenceChildClosestToStart(vVar, zVar);
        if (viewFindReferenceChildClosestToEnd == null) {
            return false;
        }
        aVar.b(viewFindReferenceChildClosestToEnd, getPosition(viewFindReferenceChildClosestToEnd));
        if (!zVar.e() && supportsPredictiveItemAnimations()) {
            if (this.mOrientationHelper.g(viewFindReferenceChildClosestToEnd) >= this.mOrientationHelper.i() || this.mOrientationHelper.d(viewFindReferenceChildClosestToEnd) < this.mOrientationHelper.m()) {
                aVar.f1579c = aVar.f1580d ? this.mOrientationHelper.i() : this.mOrientationHelper.m();
            }
        }
        return true;
    }

    private boolean updateAnchorFromPendingData(RecyclerView.z zVar, a aVar) {
        int i8;
        if (!zVar.e() && (i8 = this.mPendingScrollPosition) != -1) {
            if (i8 >= 0 && i8 < zVar.b()) {
                aVar.f1578b = this.mPendingScrollPosition;
                SavedState savedState = this.mPendingSavedState;
                if (savedState != null && savedState.f()) {
                    boolean z7 = this.mPendingSavedState.f1576f;
                    aVar.f1580d = z7;
                    if (z7) {
                        aVar.f1579c = this.mOrientationHelper.i() - this.mPendingSavedState.f1575e;
                    } else {
                        aVar.f1579c = this.mOrientationHelper.m() + this.mPendingSavedState.f1575e;
                    }
                    return true;
                }
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    boolean z8 = this.mShouldReverseLayout;
                    aVar.f1580d = z8;
                    if (z8) {
                        aVar.f1579c = this.mOrientationHelper.i() - this.mPendingScrollPositionOffset;
                    } else {
                        aVar.f1579c = this.mOrientationHelper.m() + this.mPendingScrollPositionOffset;
                    }
                    return true;
                }
                View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                if (viewFindViewByPosition == null) {
                    if (getChildCount() > 0) {
                        aVar.f1580d = (this.mPendingScrollPosition < getPosition(getChildAt(0))) == this.mShouldReverseLayout;
                    }
                    aVar.a();
                } else {
                    if (this.mOrientationHelper.e(viewFindViewByPosition) > this.mOrientationHelper.n()) {
                        aVar.a();
                        return true;
                    }
                    if (this.mOrientationHelper.g(viewFindViewByPosition) - this.mOrientationHelper.m() < 0) {
                        aVar.f1579c = this.mOrientationHelper.m();
                        aVar.f1580d = false;
                        return true;
                    }
                    if (this.mOrientationHelper.i() - this.mOrientationHelper.d(viewFindViewByPosition) < 0) {
                        aVar.f1579c = this.mOrientationHelper.i();
                        aVar.f1580d = true;
                        return true;
                    }
                    aVar.f1579c = aVar.f1580d ? this.mOrientationHelper.d(viewFindViewByPosition) + this.mOrientationHelper.o() : this.mOrientationHelper.g(viewFindViewByPosition);
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.v vVar, RecyclerView.z zVar, a aVar) {
        if (updateAnchorFromPendingData(zVar, aVar) || updateAnchorFromChildren(vVar, zVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.f1578b = this.mStackFromEnd ? zVar.b() - 1 : 0;
    }

    private void updateLayoutState(int i8, int i9, boolean z7, RecyclerView.z zVar) {
        int iM;
        this.mLayoutState.f1598m = resolveIsInfinite();
        this.mLayoutState.f1591f = i8;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(zVar, iArr);
        int iMax = Math.max(0, this.mReusableIntPair[0]);
        int iMax2 = Math.max(0, this.mReusableIntPair[1]);
        boolean z8 = i8 == 1;
        c cVar = this.mLayoutState;
        int i10 = z8 ? iMax2 : iMax;
        cVar.f1593h = i10;
        if (!z8) {
            iMax = iMax2;
        }
        cVar.f1594i = iMax;
        if (z8) {
            cVar.f1593h = i10 + this.mOrientationHelper.j();
            View childClosestToEnd = getChildClosestToEnd();
            c cVar2 = this.mLayoutState;
            cVar2.f1590e = this.mShouldReverseLayout ? -1 : 1;
            int position = getPosition(childClosestToEnd);
            c cVar3 = this.mLayoutState;
            cVar2.f1589d = position + cVar3.f1590e;
            cVar3.f1587b = this.mOrientationHelper.d(childClosestToEnd);
            iM = this.mOrientationHelper.d(childClosestToEnd) - this.mOrientationHelper.i();
        } else {
            View childClosestToStart = getChildClosestToStart();
            this.mLayoutState.f1593h += this.mOrientationHelper.m();
            c cVar4 = this.mLayoutState;
            cVar4.f1590e = this.mShouldReverseLayout ? 1 : -1;
            int position2 = getPosition(childClosestToStart);
            c cVar5 = this.mLayoutState;
            cVar4.f1589d = position2 + cVar5.f1590e;
            cVar5.f1587b = this.mOrientationHelper.g(childClosestToStart);
            iM = (-this.mOrientationHelper.g(childClosestToStart)) + this.mOrientationHelper.m();
        }
        c cVar6 = this.mLayoutState;
        cVar6.f1588c = i9;
        if (z7) {
            cVar6.f1588c = i9 - iM;
        }
        cVar6.f1592g = iM;
    }

    private void updateLayoutStateToFillEnd(a aVar) {
        updateLayoutStateToFillEnd(aVar.f1578b, aVar.f1579c);
    }

    private void updateLayoutStateToFillStart(a aVar) {
        updateLayoutStateToFillStart(aVar.f1578b, aVar.f1579c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public void calculateExtraLayoutSpace(RecyclerView.z zVar, int[] iArr) {
        int i8;
        int extraLayoutSpace = getExtraLayoutSpace(zVar);
        if (this.mLayoutState.f1591f == -1) {
            i8 = 0;
        } else {
            i8 = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i8, int i9, RecyclerView.z zVar, RecyclerView.o.c cVar) {
        if (this.mOrientation != 0) {
            i8 = i9;
        }
        if (getChildCount() == 0 || i8 == 0) {
            return;
        }
        ensureLayoutState();
        updateLayoutState(i8 > 0 ? 1 : -1, Math.abs(i8), true, zVar);
        collectPrefetchPositionsForLayoutState(zVar, this.mLayoutState, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectInitialPrefetchPositions(int i8, RecyclerView.o.c cVar) {
        boolean z7;
        int i9;
        SavedState savedState = this.mPendingSavedState;
        if (savedState == null || !savedState.f()) {
            resolveShouldLayoutReverse();
            z7 = this.mShouldReverseLayout;
            i9 = this.mPendingScrollPosition;
            if (i9 == -1) {
                i9 = z7 ? i8 - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.mPendingSavedState;
            z7 = savedState2.f1576f;
            i9 = savedState2.f1574d;
        }
        int i10 = z7 ? -1 : 1;
        for (int i11 = 0; i11 < this.mInitialPrefetchItemCount && i9 >= 0 && i9 < i8; i11++) {
            cVar.a(i9, 0);
            i9 += i10;
        }
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.z zVar, c cVar, RecyclerView.o.c cVar2) {
        int i8 = cVar.f1589d;
        if (i8 < 0 || i8 >= zVar.b()) {
            return;
        }
        cVar2.a(i8, Math.max(0, cVar.f1592g));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollExtent(RecyclerView.z zVar) {
        return computeScrollExtent(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.z zVar) {
        return computeScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.z zVar) {
        return computeScrollRange(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.y.b
    public PointF computeScrollVectorForPosition(int i8) {
        if (getChildCount() == 0) {
            return null;
        }
        int i9 = (i8 < getPosition(getChildAt(0))) != this.mShouldReverseLayout ? -1 : 1;
        return this.mOrientation == 0 ? new PointF(i9, 0.0f) : new PointF(0.0f, i9);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollExtent(RecyclerView.z zVar) {
        return computeScrollExtent(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.z zVar) {
        return computeScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.z zVar) {
        return computeScrollRange(zVar);
    }

    public int convertFocusDirectionToLayoutDirection(int i8) {
        return i8 != 1 ? i8 != 2 ? i8 != 17 ? i8 != 33 ? i8 != 66 ? (i8 == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public c createLayoutState() {
        return new c();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    public int fill(RecyclerView.v vVar, c cVar, RecyclerView.z zVar, boolean z7) {
        int i8 = cVar.f1588c;
        int i9 = cVar.f1592g;
        if (i9 != Integer.MIN_VALUE) {
            if (i8 < 0) {
                cVar.f1592g = i9 + i8;
            }
            recycleByLayoutState(vVar, cVar);
        }
        int i10 = cVar.f1588c + cVar.f1593h;
        b bVar = this.mLayoutChunkResult;
        while (true) {
            if ((!cVar.f1598m && i10 <= 0) || !cVar.c(zVar)) {
                break;
            }
            bVar.a();
            layoutChunk(vVar, zVar, cVar, bVar);
            if (!bVar.f1583b) {
                cVar.f1587b += bVar.f1582a * cVar.f1591f;
                if (!bVar.f1584c || cVar.f1597l != null || !zVar.e()) {
                    int i11 = cVar.f1588c;
                    int i12 = bVar.f1582a;
                    cVar.f1588c = i11 - i12;
                    i10 -= i12;
                }
                int i13 = cVar.f1592g;
                if (i13 != Integer.MIN_VALUE) {
                    int i14 = i13 + bVar.f1582a;
                    cVar.f1592g = i14;
                    int i15 = cVar.f1588c;
                    if (i15 < 0) {
                        cVar.f1592g = i14 + i15;
                    }
                    recycleByLayoutState(vVar, cVar);
                }
                if (z7 && bVar.f1585d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i8 - cVar.f1588c;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public View findFirstVisibleChildClosestToEnd(boolean z7, boolean z8) {
        return this.mShouldReverseLayout ? findOneVisibleChild(0, getChildCount(), z7, z8) : findOneVisibleChild(getChildCount() - 1, -1, z7, z8);
    }

    public View findFirstVisibleChildClosestToStart(boolean z7, boolean z8) {
        return this.mShouldReverseLayout ? findOneVisibleChild(getChildCount() - 1, -1, z7, z8) : findOneVisibleChild(0, getChildCount(), z7, z8);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i8, int i9) {
        int i10;
        int i11;
        ensureLayoutState();
        if ((i9 > i8 ? (byte) 1 : i9 < i8 ? (byte) -1 : (byte) 0) == 0) {
            return getChildAt(i8);
        }
        if (this.mOrientationHelper.g(getChildAt(i8)) < this.mOrientationHelper.m()) {
            i10 = 16644;
            i11 = 16388;
        } else {
            i10 = 4161;
            i11 = 4097;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i8, i9, i10, i11) : this.mVerticalBoundCheck.a(i8, i9, i10, i11);
    }

    public View findOneVisibleChild(int i8, int i9, boolean z7, boolean z8) {
        ensureLayoutState();
        int i10 = z7 ? 24579 : 320;
        int i11 = z8 ? 320 : 0;
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i8, i9, i10, i11) : this.mVerticalBoundCheck.a(i8, i9, i10, i11);
    }

    public View findReferenceChild(RecyclerView.v vVar, RecyclerView.z zVar, int i8, int i9, int i10) {
        ensureLayoutState();
        int iM = this.mOrientationHelper.m();
        int i11 = this.mOrientationHelper.i();
        int i12 = i9 > i8 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i8 != i9) {
            View childAt = getChildAt(i8);
            int position = getPosition(childAt);
            if (position >= 0 && position < i10) {
                if (((RecyclerView.p) childAt.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.g(childAt) < i11 && this.mOrientationHelper.d(childAt) >= iM) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i8 += i12;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View findViewByPosition(int i8) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i8 - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i8) {
                return childAt;
            }
        }
        return super.findViewByPosition(i8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateDefaultLayoutParams() {
        return new RecyclerView.p(-2, -2);
    }

    @Deprecated
    public int getExtraLayoutSpace(RecyclerView.z zVar) {
        if (zVar.d()) {
            return this.mOrientationHelper.n();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(RecyclerView.v vVar, RecyclerView.z zVar, c cVar, b bVar) {
        int i8;
        int i9;
        int i10;
        int paddingLeft;
        int iF;
        View viewD = cVar.d(vVar);
        if (viewD == null) {
            bVar.f1583b = true;
            return;
        }
        RecyclerView.p pVar = (RecyclerView.p) viewD.getLayoutParams();
        if (cVar.f1597l == null) {
            if (this.mShouldReverseLayout == (cVar.f1591f == -1)) {
                addView(viewD);
            } else {
                addView(viewD, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (cVar.f1591f == -1)) {
                addDisappearingView(viewD);
            } else {
                addDisappearingView(viewD, 0);
            }
        }
        measureChildWithMargins(viewD, 0, 0);
        bVar.f1582a = this.mOrientationHelper.e(viewD);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                iF = getWidth() - getPaddingRight();
                paddingLeft = iF - this.mOrientationHelper.f(viewD);
            } else {
                paddingLeft = getPaddingLeft();
                iF = this.mOrientationHelper.f(viewD) + paddingLeft;
            }
            if (cVar.f1591f == -1) {
                int i11 = cVar.f1587b;
                i10 = i11;
                i9 = iF;
                i8 = i11 - bVar.f1582a;
            } else {
                int i12 = cVar.f1587b;
                i8 = i12;
                i9 = iF;
                i10 = bVar.f1582a + i12;
            }
        } else {
            int paddingTop = getPaddingTop();
            int iF2 = this.mOrientationHelper.f(viewD) + paddingTop;
            if (cVar.f1591f == -1) {
                int i13 = cVar.f1587b;
                i9 = i13;
                i8 = paddingTop;
                i10 = iF2;
                paddingLeft = i13 - bVar.f1582a;
            } else {
                int i14 = cVar.f1587b;
                i8 = paddingTop;
                i9 = bVar.f1582a + i14;
                i10 = iF2;
                paddingLeft = i14;
            }
        }
        layoutDecoratedWithMargins(viewD, paddingLeft, i8, i9, i10);
        if (pVar.c() || pVar.b()) {
            bVar.f1584c = true;
        }
        bVar.f1585d = viewD.hasFocusable();
    }

    public void onAnchorReady(RecyclerView.v vVar, RecyclerView.z zVar, a aVar, int i8) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.v vVar) {
        super.onDetachedFromWindow(recyclerView, vVar);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(vVar);
            vVar.c();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        int iConvertFocusDirectionToLayoutDirection;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i8)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        updateLayoutState(iConvertFocusDirectionToLayoutDirection, (int) (this.mOrientationHelper.n() * MAX_SCROLL_FACTOR), false, zVar);
        c cVar = this.mLayoutState;
        cVar.f1592g = Integer.MIN_VALUE;
        cVar.f1586a = false;
        fill(vVar, cVar, zVar, true);
        View viewFindPartiallyOrCompletelyInvisibleChildClosestToStart = iConvertFocusDirectionToLayoutDirection == -1 ? findPartiallyOrCompletelyInvisibleChildClosestToStart() : findPartiallyOrCompletelyInvisibleChildClosestToEnd();
        View childClosestToStart = iConvertFocusDirectionToLayoutDirection == -1 ? getChildClosestToStart() : getChildClosestToEnd();
        if (!childClosestToStart.hasFocusable()) {
            return viewFindPartiallyOrCompletelyInvisibleChildClosestToStart;
        }
        if (viewFindPartiallyOrCompletelyInvisibleChildClosestToStart == null) {
            return null;
        }
        return childClosestToStart;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i8;
        int i9;
        int i10;
        int i11;
        int iFixLayoutEndGap;
        int i12;
        View viewFindViewByPosition;
        int iG;
        int i13;
        int i14 = -1;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && zVar.b() == 0) {
            removeAndRecycleAllViews(vVar);
            return;
        }
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.f()) {
            this.mPendingScrollPosition = this.mPendingSavedState.f1574d;
        }
        ensureLayoutState();
        this.mLayoutState.f1586a = false;
        resolveShouldLayoutReverse();
        View focusedChild = getFocusedChild();
        a aVar = this.mAnchorInfo;
        if (!aVar.f1581e || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            aVar.e();
            a aVar2 = this.mAnchorInfo;
            aVar2.f1580d = this.mShouldReverseLayout ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(vVar, zVar, aVar2);
            this.mAnchorInfo.f1581e = true;
        } else if (focusedChild != null && (this.mOrientationHelper.g(focusedChild) >= this.mOrientationHelper.i() || this.mOrientationHelper.d(focusedChild) <= this.mOrientationHelper.m())) {
            this.mAnchorInfo.c(focusedChild, getPosition(focusedChild));
        }
        c cVar = this.mLayoutState;
        cVar.f1591f = cVar.f1596k >= 0 ? 1 : -1;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(zVar, iArr);
        int iMax = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.m();
        int iMax2 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.j();
        if (zVar.e() && (i12 = this.mPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (viewFindViewByPosition = findViewByPosition(i12)) != null) {
            if (this.mShouldReverseLayout) {
                i13 = this.mOrientationHelper.i() - this.mOrientationHelper.d(viewFindViewByPosition);
                iG = this.mPendingScrollPositionOffset;
            } else {
                iG = this.mOrientationHelper.g(viewFindViewByPosition) - this.mOrientationHelper.m();
                i13 = this.mPendingScrollPositionOffset;
            }
            int i15 = i13 - iG;
            if (i15 > 0) {
                iMax += i15;
            } else {
                iMax2 -= i15;
            }
        }
        a aVar3 = this.mAnchorInfo;
        if (!aVar3.f1580d ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i14 = 1;
        }
        onAnchorReady(vVar, zVar, aVar3, i14);
        detachAndScrapAttachedViews(vVar);
        this.mLayoutState.f1598m = resolveIsInfinite();
        this.mLayoutState.f1595j = zVar.e();
        this.mLayoutState.f1594i = 0;
        a aVar4 = this.mAnchorInfo;
        if (aVar4.f1580d) {
            updateLayoutStateToFillStart(aVar4);
            c cVar2 = this.mLayoutState;
            cVar2.f1593h = iMax;
            fill(vVar, cVar2, zVar, false);
            c cVar3 = this.mLayoutState;
            i9 = cVar3.f1587b;
            int i16 = cVar3.f1589d;
            int i17 = cVar3.f1588c;
            if (i17 > 0) {
                iMax2 += i17;
            }
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            c cVar4 = this.mLayoutState;
            cVar4.f1593h = iMax2;
            cVar4.f1589d += cVar4.f1590e;
            fill(vVar, cVar4, zVar, false);
            c cVar5 = this.mLayoutState;
            i8 = cVar5.f1587b;
            int i18 = cVar5.f1588c;
            if (i18 > 0) {
                updateLayoutStateToFillStart(i16, i9);
                c cVar6 = this.mLayoutState;
                cVar6.f1593h = i18;
                fill(vVar, cVar6, zVar, false);
                i9 = this.mLayoutState.f1587b;
            }
        } else {
            updateLayoutStateToFillEnd(aVar4);
            c cVar7 = this.mLayoutState;
            cVar7.f1593h = iMax2;
            fill(vVar, cVar7, zVar, false);
            c cVar8 = this.mLayoutState;
            i8 = cVar8.f1587b;
            int i19 = cVar8.f1589d;
            int i20 = cVar8.f1588c;
            if (i20 > 0) {
                iMax += i20;
            }
            updateLayoutStateToFillStart(this.mAnchorInfo);
            c cVar9 = this.mLayoutState;
            cVar9.f1593h = iMax;
            cVar9.f1589d += cVar9.f1590e;
            fill(vVar, cVar9, zVar, false);
            c cVar10 = this.mLayoutState;
            i9 = cVar10.f1587b;
            int i21 = cVar10.f1588c;
            if (i21 > 0) {
                updateLayoutStateToFillEnd(i19, i8);
                c cVar11 = this.mLayoutState;
                cVar11.f1593h = i21;
                fill(vVar, cVar11, zVar, false);
                i8 = this.mLayoutState.f1587b;
            }
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int iFixLayoutEndGap2 = fixLayoutEndGap(i8, vVar, zVar, true);
                i10 = i9 + iFixLayoutEndGap2;
                i11 = i8 + iFixLayoutEndGap2;
                iFixLayoutEndGap = fixLayoutStartGap(i10, vVar, zVar, false);
            } else {
                int iFixLayoutStartGap = fixLayoutStartGap(i9, vVar, zVar, true);
                i10 = i9 + iFixLayoutStartGap;
                i11 = i8 + iFixLayoutStartGap;
                iFixLayoutEndGap = fixLayoutEndGap(i11, vVar, zVar, false);
            }
            i9 = i10 + iFixLayoutEndGap;
            i8 = i11 + iFixLayoutEndGap;
        }
        layoutForPredictiveAnimations(vVar, zVar, i9, i8);
        if (zVar.e()) {
            this.mAnchorInfo.e();
        } else {
            this.mOrientationHelper.s();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.z zVar) {
        super.onLayoutCompleted(zVar);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z7 = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState.f1576f = z7;
            if (z7) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState.f1575e = this.mOrientationHelper.i() - this.mOrientationHelper.d(childClosestToEnd);
                savedState.f1574d = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState.f1574d = getPosition(childClosestToStart);
                savedState.f1575e = this.mOrientationHelper.g(childClosestToStart) - this.mOrientationHelper.m();
            }
        } else {
            savedState.g();
        }
        return savedState;
    }

    public void prepareForDrop(View view, View view2, int i8, int i9) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        byte b8 = position < position2 ? (byte) 1 : (byte) -1;
        if (this.mShouldReverseLayout) {
            if (b8 == 1) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.i() - (this.mOrientationHelper.g(view2) + this.mOrientationHelper.e(view)));
                return;
            } else {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.i() - this.mOrientationHelper.d(view2));
                return;
            }
        }
        if (b8 == -1) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.g(view2));
        } else {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.d(view2) - this.mOrientationHelper.e(view));
        }
    }

    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.k() == 0 && this.mOrientationHelper.h() == 0;
    }

    public int scrollBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (getChildCount() == 0 || i8 == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.f1586a = true;
        int i9 = i8 > 0 ? 1 : -1;
        int iAbs = Math.abs(i8);
        updateLayoutState(i9, iAbs, true, zVar);
        c cVar = this.mLayoutState;
        int iFill = cVar.f1592g + fill(vVar, cVar, zVar, false);
        if (iFill < 0) {
            return 0;
        }
        if (iAbs > iFill) {
            i8 = i9 * iFill;
        }
        this.mOrientationHelper.r(-i8);
        this.mLayoutState.f1596k = i8;
        return i8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i8, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i8) {
        this.mPendingScrollPosition = i8;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.g();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i8, int i9) {
        this.mPendingScrollPosition = i8;
        this.mPendingScrollPositionOffset = i9;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.g();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i8, vVar, zVar);
    }

    public void setInitialPrefetchItemCount(int i8) {
        this.mInitialPrefetchItemCount = i8;
    }

    public void setOrientation(int i8) {
        if (i8 != 0 && i8 != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i8);
        }
        assertNotInLayoutOrScroll(null);
        if (i8 != this.mOrientation || this.mOrientationHelper == null) {
            j jVarB = j.b(this, i8);
            this.mOrientationHelper = jVarB;
            this.mAnchorInfo.f1577a = jVarB;
            this.mOrientation = i8;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z7) {
        this.mRecycleChildrenOnDetach = z7;
    }

    public void setReverseLayout(boolean z7) {
        assertNotInLayoutOrScroll(null);
        if (z7 == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z7;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z7) {
        this.mSmoothScrollbarEnabled = z7;
    }

    public void setStackFromEnd(boolean z7) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z7) {
            return;
        }
        this.mStackFromEnd = z7;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.z zVar, int i8) {
        g gVar = new g(recyclerView.getContext());
        gVar.setTargetPosition(i8);
        startSmoothScroll(gVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    public void validateChildOrder() {
        Log.d(TAG, "validating child count " + getChildCount());
        if (getChildCount() < 1) {
            return;
        }
        int position = getPosition(getChildAt(0));
        int iG = this.mOrientationHelper.g(getChildAt(0));
        if (this.mShouldReverseLayout) {
            for (int i8 = 1; i8 < getChildCount(); i8++) {
                View childAt = getChildAt(i8);
                int position2 = getPosition(childAt);
                int iG2 = this.mOrientationHelper.g(childAt);
                if (position2 < position) {
                    logChildren();
                    StringBuilder sb = new StringBuilder();
                    sb.append("detected invalid position. loc invalid? ");
                    sb.append(iG2 < iG);
                    throw new RuntimeException(sb.toString());
                }
                if (iG2 > iG) {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i9 = 1; i9 < getChildCount(); i9++) {
            View childAt2 = getChildAt(i9);
            int position3 = getPosition(childAt2);
            int iG3 = this.mOrientationHelper.g(childAt2);
            if (position3 < position) {
                logChildren();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("detected invalid position. loc invalid? ");
                sb2.append(iG3 < iG);
                throw new RuntimeException(sb2.toString());
            }
            if (iG3 < iG) {
                logChildren();
                throw new RuntimeException("detected invalid location");
            }
        }
    }

    public LinearLayoutManager(Context context, int i8, boolean z7) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i8);
        setReverseLayout(z7);
    }

    private void updateLayoutStateToFillEnd(int i8, int i9) {
        this.mLayoutState.f1588c = this.mOrientationHelper.i() - i9;
        c cVar = this.mLayoutState;
        cVar.f1590e = this.mShouldReverseLayout ? -1 : 1;
        cVar.f1589d = i8;
        cVar.f1591f = 1;
        cVar.f1587b = i9;
        cVar.f1592g = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(int i8, int i9) {
        this.mLayoutState.f1588c = i9 - this.mOrientationHelper.m();
        c cVar = this.mLayoutState;
        cVar.f1589d = i8;
        cVar.f1590e = this.mShouldReverseLayout ? 1 : -1;
        cVar.f1591f = -1;
        cVar.f1587b = i9;
        cVar.f1592g = Integer.MIN_VALUE;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i8, int i9) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i8, i9);
        setOrientation(properties.f1625a);
        setReverseLayout(properties.f1627c);
        setStackFromEnd(properties.f1628d);
    }
}
