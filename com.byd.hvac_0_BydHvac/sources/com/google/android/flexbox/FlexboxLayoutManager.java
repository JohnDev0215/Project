package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.a.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements b.b.a.a.a, RecyclerView.SmoothScroller.ScrollVectorProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Rect f1134a = new Rect();
    public c.b A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1135b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1136c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public boolean h;
    public List<b.b.a.a.b> i;
    public final b.b.a.a.c j;
    public RecyclerView.Recycler k;
    public RecyclerView.State l;
    public c m;
    public b n;
    public OrientationHelper o;
    public OrientationHelper p;
    public SavedState q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public SparseArray<View> w;
    public final Context x;
    public View y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1142a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1143b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1144c;
        public int d;
        public boolean e;
        public boolean f;
        public boolean g;

        public b() {
            this.d = 0;
        }

        public final void q() {
            if (FlexboxLayoutManager.this.i() || !FlexboxLayoutManager.this.g) {
                this.f1144c = this.e ? FlexboxLayoutManager.this.o.getEndAfterPadding() : FlexboxLayoutManager.this.o.getStartAfterPadding();
            } else {
                this.f1144c = this.e ? FlexboxLayoutManager.this.o.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.o.getStartAfterPadding();
            }
        }

        public final void r(View view) {
            if (FlexboxLayoutManager.this.i() || !FlexboxLayoutManager.this.g) {
                if (this.e) {
                    this.f1144c = FlexboxLayoutManager.this.o.getDecoratedEnd(view) + FlexboxLayoutManager.this.o.getTotalSpaceChange();
                } else {
                    this.f1144c = FlexboxLayoutManager.this.o.getDecoratedStart(view);
                }
            } else if (this.e) {
                this.f1144c = FlexboxLayoutManager.this.o.getDecoratedStart(view) + FlexboxLayoutManager.this.o.getTotalSpaceChange();
            } else {
                this.f1144c = FlexboxLayoutManager.this.o.getDecoratedEnd(view);
            }
            this.f1142a = FlexboxLayoutManager.this.getPosition(view);
            this.g = false;
            int[] iArr = FlexboxLayoutManager.this.j.f957c;
            int i = this.f1142a;
            if (i == -1) {
                i = 0;
            }
            int i2 = iArr[i];
            this.f1143b = i2 != -1 ? i2 : 0;
            if (FlexboxLayoutManager.this.i.size() > this.f1143b) {
                this.f1142a = ((b.b.a.a.b) FlexboxLayoutManager.this.i.get(this.f1143b)).o;
            }
        }

        public final void s() {
            this.f1142a = -1;
            this.f1143b = -1;
            this.f1144c = Integer.MIN_VALUE;
            this.f = false;
            this.g = false;
            if (FlexboxLayoutManager.this.i()) {
                if (FlexboxLayoutManager.this.f1136c == 0) {
                    this.e = FlexboxLayoutManager.this.f1135b == 1;
                    return;
                } else {
                    this.e = FlexboxLayoutManager.this.f1136c == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.f1136c == 0) {
                this.e = FlexboxLayoutManager.this.f1135b == 3;
            } else {
                this.e = FlexboxLayoutManager.this.f1136c == 2;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.f1142a + ", mFlexLinePosition=" + this.f1143b + ", mCoordinate=" + this.f1144c + ", mPerpendicularCoordinate=" + this.d + ", mLayoutFromEnd=" + this.e + ", mValid=" + this.f + ", mAssignedFromSavedState=" + this.g + '}';
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1145a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1146b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1147c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public boolean j;

        public c() {
            this.h = 1;
            this.i = 1;
        }

        public static /* synthetic */ int i(c cVar) {
            int i = cVar.f1147c;
            cVar.f1147c = i + 1;
            return i;
        }

        public static /* synthetic */ int j(c cVar) {
            int i = cVar.f1147c;
            cVar.f1147c = i - 1;
            return i;
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.f1145a + ", mFlexLinePosition=" + this.f1147c + ", mPosition=" + this.d + ", mOffset=" + this.e + ", mScrollingOffset=" + this.f + ", mLastScrollDelta=" + this.g + ", mItemDirection=" + this.h + ", mLayoutDirection=" + this.i + '}';
        }

        public final boolean w(RecyclerView.State state, List<b.b.a.a.b> list) {
            int i;
            int i2 = this.d;
            return i2 >= 0 && i2 < state.getItemCount() && (i = this.f1147c) >= 0 && i < list.size();
        }
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    private boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    public final View A(int i, int i2, int i3) {
        t();
        ensureLayoutState();
        int startAfterPadding = this.o.getStartAfterPadding();
        int endAfterPadding = this.o.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.o.getDecoratedStart(childAt) >= startAfterPadding && this.o.getDecoratedEnd(childAt) <= endAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    public final int B(View view) {
        return getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    public final int C(View view) {
        return getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
    }

    public final int D(View view) {
        return getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
    }

    public final int E(View view) {
        return getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
    }

    public List<b.b.a.a.b> F() {
        ArrayList arrayList = new ArrayList(this.i.size());
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            b.b.a.a.b bVar = this.i.get(i);
            if (bVar.b() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public int G(int i) {
        return this.j.f957c[i];
    }

    public final int H(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        t();
        int i2 = 1;
        this.m.j = true;
        boolean z = !i() && this.g;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int iAbs = Math.abs(i);
        b0(i2, iAbs);
        int iU = this.m.f + u(recycler, state, this.m);
        if (iU < 0) {
            return 0;
        }
        if (z) {
            if (iAbs > iU) {
                i = (-i2) * iU;
            }
        } else if (iAbs > iU) {
            i = i2 * iU;
        }
        this.o.offsetChildren(-i);
        this.m.g = i;
        return i;
    }

    public final int I(int i) {
        int iMin;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        t();
        boolean zI = i();
        View view = this.y;
        int width = zI ? view.getWidth() : view.getHeight();
        int width2 = zI ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int iAbs = Math.abs(i);
            if (i < 0) {
                iMin = Math.min((width2 + this.n.d) - width, iAbs);
            } else {
                if (this.n.d + i <= 0) {
                    return i;
                }
                iMin = this.n.d;
            }
        } else {
            if (i > 0) {
                return Math.min((width2 - this.n.d) - width, i);
            }
            if (this.n.d + i >= 0) {
                return i;
            }
            iMin = this.n.d;
        }
        return -iMin;
    }

    public boolean J() {
        return this.g;
    }

    public final boolean K(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int iC = C(view);
        int iE = E(view);
        int iD = D(view);
        int iB = B(view);
        return z ? (paddingLeft <= iC && width >= iD) && (paddingTop <= iE && height >= iB) : (iC >= width || iD >= paddingLeft) && (iE >= height || iB >= paddingTop);
    }

    public final int L(b.b.a.a.b bVar, c cVar) {
        return i() ? M(bVar, cVar) : N(bVar, cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int M(b.b.a.a.b r22, com.google.android.flexbox.FlexboxLayoutManager.c r23) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.M(b.b.a.a.b, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int N(b.b.a.a.b r26, com.google.android.flexbox.FlexboxLayoutManager.c r27) {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.N(b.b.a.a.b, com.google.android.flexbox.FlexboxLayoutManager$c):int");
    }

    public final void O(RecyclerView.Recycler recycler, c cVar) {
        if (cVar.j) {
            if (cVar.i == -1) {
                P(recycler, cVar);
            } else {
                Q(recycler, cVar);
            }
        }
    }

    public final void P(RecyclerView.Recycler recycler, c cVar) {
        if (cVar.f < 0) {
            return;
        }
        this.o.getEnd();
        int unused = cVar.f;
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int i = childCount - 1;
        int i2 = this.j.f957c[getPosition(getChildAt(i))];
        if (i2 == -1) {
            return;
        }
        b.b.a.a.b bVar = this.i.get(i2);
        int i3 = i;
        while (true) {
            if (i3 < 0) {
                break;
            }
            View childAt = getChildAt(i3);
            if (!q(childAt, cVar.f)) {
                break;
            }
            if (bVar.o == getPosition(childAt)) {
                if (i2 <= 0) {
                    childCount = i3;
                    break;
                } else {
                    i2 += cVar.i;
                    bVar = this.i.get(i2);
                    childCount = i3;
                }
            }
            i3--;
        }
        recycleChildren(recycler, childCount, i);
    }

    public final void Q(RecyclerView.Recycler recycler, c cVar) {
        int childCount;
        if (cVar.f >= 0 && (childCount = getChildCount()) != 0) {
            int i = this.j.f957c[getPosition(getChildAt(0))];
            int i2 = -1;
            if (i == -1) {
                return;
            }
            b.b.a.a.b bVar = this.i.get(i);
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    break;
                }
                View childAt = getChildAt(i3);
                if (!r(childAt, cVar.f)) {
                    break;
                }
                if (bVar.p == getPosition(childAt)) {
                    if (i >= this.i.size() - 1) {
                        i2 = i3;
                        break;
                    } else {
                        i += cVar.i;
                        bVar = this.i.get(i);
                        i2 = i3;
                    }
                }
                i3++;
            }
            recycleChildren(recycler, 0, i2);
        }
    }

    public final void R() {
        int heightMode = i() ? getHeightMode() : getWidthMode();
        this.m.f1146b = heightMode == 0 || heightMode == Integer.MIN_VALUE;
    }

    public final void S() {
        int layoutDirection = getLayoutDirection();
        int i = this.f1135b;
        if (i == 0) {
            this.g = layoutDirection == 1;
            this.h = this.f1136c == 2;
            return;
        }
        if (i == 1) {
            this.g = layoutDirection != 1;
            this.h = this.f1136c == 2;
            return;
        }
        if (i == 2) {
            boolean z = layoutDirection == 1;
            this.g = z;
            if (this.f1136c == 2) {
                this.g = !z;
            }
            this.h = false;
            return;
        }
        if (i != 3) {
            this.g = false;
            this.h = false;
            return;
        }
        boolean z2 = layoutDirection == 1;
        this.g = z2;
        if (this.f1136c == 2) {
            this.g = !z2;
        }
        this.h = true;
    }

    public void T(int i) {
        int i2 = this.e;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                removeAllViews();
                s();
            }
            this.e = i;
            requestLayout();
        }
    }

    public void U(int i) {
        if (this.f1135b != i) {
            removeAllViews();
            this.f1135b = i;
            this.o = null;
            this.p = null;
            s();
            requestLayout();
        }
    }

    public void V(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i2 = this.f1136c;
        if (i2 != i) {
            if (i2 == 0 || i == 0) {
                removeAllViews();
                s();
            }
            this.f1136c = i;
            this.o = null;
            this.p = null;
            requestLayout();
        }
    }

    public final boolean W(RecyclerView.State state, b bVar) {
        if (getChildCount() == 0) {
            return false;
        }
        View viewX = bVar.e ? x(state.getItemCount()) : v(state.getItemCount());
        if (viewX == null) {
            return false;
        }
        bVar.r(viewX);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.o.getDecoratedStart(viewX) >= this.o.getEndAfterPadding() || this.o.getDecoratedEnd(viewX) < this.o.getStartAfterPadding()) {
                bVar.f1144c = bVar.e ? this.o.getEndAfterPadding() : this.o.getStartAfterPadding();
            }
        }
        return true;
    }

    public final boolean X(RecyclerView.State state, b bVar, SavedState savedState) {
        int i;
        if (!state.isPreLayout() && (i = this.r) != -1) {
            if (i >= 0 && i < state.getItemCount()) {
                bVar.f1142a = this.r;
                bVar.f1143b = this.j.f957c[bVar.f1142a];
                SavedState savedState2 = this.q;
                if (savedState2 != null && savedState2.t(state.getItemCount())) {
                    bVar.f1144c = this.o.getStartAfterPadding() + savedState.f1141b;
                    bVar.g = true;
                    bVar.f1143b = -1;
                    return true;
                }
                if (this.s != Integer.MIN_VALUE) {
                    if (i() || !this.g) {
                        bVar.f1144c = this.o.getStartAfterPadding() + this.s;
                    } else {
                        bVar.f1144c = this.s - this.o.getEndPadding();
                    }
                    return true;
                }
                View viewFindViewByPosition = findViewByPosition(this.r);
                if (viewFindViewByPosition == null) {
                    if (getChildCount() > 0) {
                        bVar.e = this.r < getPosition(getChildAt(0));
                    }
                    bVar.q();
                } else {
                    if (this.o.getDecoratedMeasurement(viewFindViewByPosition) > this.o.getTotalSpace()) {
                        bVar.q();
                        return true;
                    }
                    if (this.o.getDecoratedStart(viewFindViewByPosition) - this.o.getStartAfterPadding() < 0) {
                        bVar.f1144c = this.o.getStartAfterPadding();
                        bVar.e = false;
                        return true;
                    }
                    if (this.o.getEndAfterPadding() - this.o.getDecoratedEnd(viewFindViewByPosition) < 0) {
                        bVar.f1144c = this.o.getEndAfterPadding();
                        bVar.e = true;
                        return true;
                    }
                    bVar.f1144c = bVar.e ? this.o.getDecoratedEnd(viewFindViewByPosition) + this.o.getTotalSpaceChange() : this.o.getDecoratedStart(viewFindViewByPosition);
                }
                return true;
            }
            this.r = -1;
            this.s = Integer.MIN_VALUE;
        }
        return false;
    }

    public final void Y(RecyclerView.State state, b bVar) {
        if (X(state, bVar, this.q) || W(state, bVar)) {
            return;
        }
        bVar.q();
        bVar.f1142a = 0;
        bVar.f1143b = 0;
    }

    public final void Z(int i) {
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int iFindLastVisibleItemPosition = findLastVisibleItemPosition();
        if (i >= iFindLastVisibleItemPosition) {
            return;
        }
        int childCount = getChildCount();
        this.j.t(childCount);
        this.j.u(childCount);
        this.j.s(childCount);
        if (i >= this.j.f957c.length) {
            return;
        }
        this.z = i;
        View childClosestToStart = getChildClosestToStart();
        if (childClosestToStart == null) {
            return;
        }
        if (iFindFirstVisibleItemPosition > i || i > iFindLastVisibleItemPosition) {
            this.r = getPosition(childClosestToStart);
            if (i() || !this.g) {
                this.s = this.o.getDecoratedStart(childClosestToStart) - this.o.getStartAfterPadding();
            } else {
                this.s = this.o.getDecoratedEnd(childClosestToStart) + this.o.getEndPadding();
            }
        }
    }

    @Override // b.b.a.a.a
    public View a(int i) {
        View view = this.w.get(i);
        return view != null ? view : this.k.getViewForPosition(i);
    }

    public final void a0(int i) {
        boolean z;
        int i2;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        if (i()) {
            int i3 = this.t;
            z = (i3 == Integer.MIN_VALUE || i3 == width) ? false : true;
            i2 = this.m.f1146b ? this.x.getResources().getDisplayMetrics().heightPixels : this.m.f1145a;
        } else {
            int i4 = this.u;
            z = (i4 == Integer.MIN_VALUE || i4 == height) ? false : true;
            i2 = this.m.f1146b ? this.x.getResources().getDisplayMetrics().widthPixels : this.m.f1145a;
        }
        int i5 = i2;
        this.t = width;
        this.u = height;
        int i6 = this.z;
        if (i6 == -1 && (this.r != -1 || z)) {
            if (this.n.e) {
                return;
            }
            this.i.clear();
            this.A.a();
            if (i()) {
                this.j.e(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.n.f1142a, this.i);
            } else {
                this.j.h(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.n.f1142a, this.i);
            }
            this.i = this.A.f958a;
            this.j.p(iMakeMeasureSpec, iMakeMeasureSpec2);
            this.j.W();
            b bVar = this.n;
            bVar.f1143b = this.j.f957c[bVar.f1142a];
            this.m.f1147c = this.n.f1143b;
            return;
        }
        int iMin = i6 != -1 ? Math.min(i6, this.n.f1142a) : this.n.f1142a;
        this.A.a();
        if (i()) {
            if (this.i.size() > 0) {
                this.j.j(this.i, iMin);
                this.j.b(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i5, iMin, this.n.f1142a, this.i);
            } else {
                this.j.s(i);
                this.j.d(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.i);
            }
        } else if (this.i.size() > 0) {
            this.j.j(this.i, iMin);
            this.j.b(this.A, iMakeMeasureSpec2, iMakeMeasureSpec, i5, iMin, this.n.f1142a, this.i);
        } else {
            this.j.s(i);
            this.j.g(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.i);
        }
        this.i = this.A.f958a;
        this.j.q(iMakeMeasureSpec, iMakeMeasureSpec2, iMin);
        this.j.X(iMin);
    }

    @Override // b.b.a.a.a
    public int b(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (i()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    public final void b0(int i, int i2) {
        this.m.i = i;
        boolean zI = i();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean z = !zI && this.g;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.m.e = this.o.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View viewY = y(childAt, this.i.get(this.j.f957c[position]));
            this.m.h = 1;
            c cVar = this.m;
            cVar.d = position + cVar.h;
            if (this.j.f957c.length <= this.m.d) {
                this.m.f1147c = -1;
            } else {
                c cVar2 = this.m;
                cVar2.f1147c = this.j.f957c[cVar2.d];
            }
            if (z) {
                this.m.e = this.o.getDecoratedStart(viewY);
                this.m.f = (-this.o.getDecoratedStart(viewY)) + this.o.getStartAfterPadding();
                c cVar3 = this.m;
                cVar3.f = cVar3.f >= 0 ? this.m.f : 0;
            } else {
                this.m.e = this.o.getDecoratedEnd(viewY);
                this.m.f = this.o.getDecoratedEnd(viewY) - this.o.getEndAfterPadding();
            }
            if ((this.m.f1147c == -1 || this.m.f1147c > this.i.size() - 1) && this.m.d <= getFlexItemCount()) {
                int i3 = i2 - this.m.f;
                this.A.a();
                if (i3 > 0) {
                    if (zI) {
                        this.j.d(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.m.d, this.i);
                    } else {
                        this.j.g(this.A, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.m.d, this.i);
                    }
                    this.j.q(iMakeMeasureSpec, iMakeMeasureSpec2, this.m.d);
                    this.j.X(this.m.d);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.m.e = this.o.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View viewW = w(childAt2, this.i.get(this.j.f957c[position2]));
            this.m.h = 1;
            int i4 = this.j.f957c[position2];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.m.d = position2 - this.i.get(i4 - 1).b();
            } else {
                this.m.d = -1;
            }
            this.m.f1147c = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.m.e = this.o.getDecoratedEnd(viewW);
                this.m.f = this.o.getDecoratedEnd(viewW) - this.o.getEndAfterPadding();
                c cVar4 = this.m;
                cVar4.f = cVar4.f >= 0 ? this.m.f : 0;
            } else {
                this.m.e = this.o.getDecoratedStart(viewW);
                this.m.f = (-this.o.getDecoratedStart(viewW)) + this.o.getStartAfterPadding();
            }
        }
        c cVar5 = this.m;
        cVar5.f1145a = i2 - cVar5.f;
    }

    @Override // b.b.a.a.a
    public void c(b.b.a.a.b bVar) {
    }

    public final void c0(b bVar, boolean z, boolean z2) {
        if (z2) {
            R();
        } else {
            this.m.f1146b = false;
        }
        if (i() || !this.g) {
            this.m.f1145a = this.o.getEndAfterPadding() - bVar.f1144c;
        } else {
            this.m.f1145a = bVar.f1144c - getPaddingRight();
        }
        this.m.d = bVar.f1142a;
        this.m.h = 1;
        this.m.i = 1;
        this.m.e = bVar.f1144c;
        this.m.f = Integer.MIN_VALUE;
        this.m.f1147c = bVar.f1143b;
        if (!z || this.i.size() <= 1 || bVar.f1143b < 0 || bVar.f1143b >= this.i.size() - 1) {
            return;
        }
        b.b.a.a.b bVar2 = this.i.get(bVar.f1143b);
        c.i(this.m);
        this.m.d += bVar2.b();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return !i() || getWidth() > this.y.getWidth();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return i() || getHeight() > this.y.getHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        computeScrollOffset(state);
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        t();
        View viewV = v(itemCount);
        View viewX = x(itemCount);
        if (state.getItemCount() == 0 || viewV == null || viewX == null) {
            return 0;
        }
        return Math.min(this.o.getTotalSpace(), this.o.getDecoratedEnd(viewX) - this.o.getDecoratedStart(viewV));
    }

    public final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewV = v(itemCount);
        View viewX = x(itemCount);
        if (state.getItemCount() != 0 && viewV != null && viewX != null) {
            int position = getPosition(viewV);
            int position2 = getPosition(viewX);
            int iAbs = Math.abs(this.o.getDecoratedEnd(viewX) - this.o.getDecoratedStart(viewV));
            int i = this.j.f957c[position];
            if (i != 0 && i != -1) {
                return Math.round((i * (iAbs / ((r4[position2] - i) + 1))) + (this.o.getStartAfterPadding() - this.o.getDecoratedStart(viewV)));
            }
        }
        return 0;
    }

    public final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewV = v(itemCount);
        View viewX = x(itemCount);
        if (state.getItemCount() == 0 || viewV == null || viewX == null) {
            return 0;
        }
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.o.getDecoratedEnd(viewX) - this.o.getDecoratedStart(viewV)) / ((findLastVisibleItemPosition() - iFindFirstVisibleItemPosition) + 1)) * state.getItemCount());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        return i() ? new PointF(0.0f, i2) : new PointF(i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // b.b.a.a.a
    public int d(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    public final void d0(b bVar, boolean z, boolean z2) {
        if (z2) {
            R();
        } else {
            this.m.f1146b = false;
        }
        if (i() || !this.g) {
            this.m.f1145a = bVar.f1144c - this.o.getStartAfterPadding();
        } else {
            this.m.f1145a = (this.y.getWidth() - bVar.f1144c) - this.o.getStartAfterPadding();
        }
        this.m.d = bVar.f1142a;
        this.m.h = 1;
        this.m.i = -1;
        this.m.e = bVar.f1144c;
        this.m.f = Integer.MIN_VALUE;
        this.m.f1147c = bVar.f1143b;
        if (!z || bVar.f1143b <= 0 || this.i.size() <= bVar.f1143b) {
            return;
        }
        b.b.a.a.b bVar2 = this.i.get(bVar.f1143b);
        c.j(this.m);
        this.m.d -= bVar2.b();
    }

    @Override // b.b.a.a.a
    public View e(int i) {
        return a(i);
    }

    public final void ensureLayoutState() {
        if (this.m == null) {
            this.m = new c();
        }
    }

    @Override // b.b.a.a.a
    public void f(View view, int i, int i2, b.b.a.a.b bVar) {
        calculateItemDecorationsForChild(view, f1134a);
        if (i()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            bVar.e += leftDecorationWidth;
            bVar.f += leftDecorationWidth;
        } else {
            int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
            bVar.e += topDecorationHeight;
            bVar.f += topDecorationHeight;
        }
    }

    public int findFirstVisibleItemPosition() {
        View viewZ = z(0, getChildCount(), false);
        if (viewZ == null) {
            return -1;
        }
        return getPosition(viewZ);
    }

    public int findLastVisibleItemPosition() {
        View viewZ = z(getChildCount() - 1, -1, false);
        if (viewZ == null) {
            return -1;
        }
        return getPosition(viewZ);
    }

    public final int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int iH;
        int endAfterPadding;
        if (!i() && this.g) {
            int startAfterPadding = i - this.o.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            iH = H(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.o.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            iH = -H(-endAfterPadding2, recycler, state);
        }
        int i2 = i + iH;
        if (!z || (endAfterPadding = this.o.getEndAfterPadding() - i2) <= 0) {
            return iH;
        }
        this.o.offsetChildren(endAfterPadding);
        return endAfterPadding + iH;
    }

    public final int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int iH;
        int startAfterPadding;
        if (i() || !this.g) {
            int startAfterPadding2 = i - this.o.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            iH = -H(startAfterPadding2, recycler, state);
        } else {
            int endAfterPadding = this.o.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            iH = H(-endAfterPadding, recycler, state);
        }
        int i2 = i + iH;
        if (!z || (startAfterPadding = i2 - this.o.getStartAfterPadding()) <= 0) {
            return iH;
        }
        this.o.offsetChildren(-startAfterPadding);
        return iH - startAfterPadding;
    }

    @Override // b.b.a.a.a
    public int g(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // b.b.a.a.a
    public int getAlignContent() {
        return 5;
    }

    @Override // b.b.a.a.a
    public int getAlignItems() {
        return this.e;
    }

    public final View getChildClosestToStart() {
        return getChildAt(0);
    }

    @Override // b.b.a.a.a
    public int getFlexDirection() {
        return this.f1135b;
    }

    @Override // b.b.a.a.a
    public int getFlexItemCount() {
        return this.l.getItemCount();
    }

    @Override // b.b.a.a.a
    public List<b.b.a.a.b> getFlexLinesInternal() {
        return this.i;
    }

    @Override // b.b.a.a.a
    public int getFlexWrap() {
        return this.f1136c;
    }

    @Override // b.b.a.a.a
    public int getLargestMainSize() {
        if (this.i.size() == 0) {
            return 0;
        }
        int iMax = Integer.MIN_VALUE;
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            iMax = Math.max(iMax, this.i.get(i).e);
        }
        return iMax;
    }

    @Override // b.b.a.a.a
    public int getMaxLine() {
        return this.f;
    }

    @Override // b.b.a.a.a
    public int getSumOfCrossSize() {
        int size = this.i.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.i.get(i2).g;
        }
        return i;
    }

    @Override // b.b.a.a.a
    public void h(int i, View view) {
        this.w.put(i, view);
    }

    @Override // b.b.a.a.a
    public boolean i() {
        int i = this.f1135b;
        return i == 0 || i == 1;
    }

    @Override // b.b.a.a.a
    public int j(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (i()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.y = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.v) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        Z(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        Z(Math.min(i, i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        Z(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        Z(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        this.k = recycler;
        this.l = state;
        int itemCount = state.getItemCount();
        if (itemCount == 0 && state.isPreLayout()) {
            return;
        }
        S();
        t();
        ensureLayoutState();
        this.j.t(itemCount);
        this.j.u(itemCount);
        this.j.s(itemCount);
        this.m.j = false;
        SavedState savedState = this.q;
        if (savedState != null && savedState.t(itemCount)) {
            this.r = this.q.f1140a;
        }
        if (!this.n.f || this.r != -1 || this.q != null) {
            this.n.s();
            Y(state, this.n);
            this.n.f = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.n.e) {
            d0(this.n, false, true);
        } else {
            c0(this.n, false, true);
        }
        a0(itemCount);
        if (this.n.e) {
            u(recycler, state, this.m);
            i2 = this.m.e;
            c0(this.n, true, false);
            u(recycler, state, this.m);
            i = this.m.e;
        } else {
            u(recycler, state, this.m);
            i = this.m.e;
            d0(this.n, true, false);
            u(recycler, state, this.m);
            i2 = this.m.e;
        }
        if (getChildCount() > 0) {
            if (this.n.e) {
                fixLayoutStartGap(i2 + fixLayoutEndGap(i, recycler, state, true), recycler, state, false);
            } else {
                fixLayoutEndGap(i + fixLayoutStartGap(i2, recycler, state, true), recycler, state, false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.q = null;
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.z = -1;
        this.n.s();
        this.w.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.q = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.q != null) {
            return new SavedState(this.q);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState.f1140a = getPosition(childClosestToStart);
            savedState.f1141b = this.o.getDecoratedStart(childClosestToStart) - this.o.getStartAfterPadding();
        } else {
            savedState.u();
        }
        return savedState;
    }

    public final boolean q(View view, int i) {
        return (i() || !this.g) ? this.o.getDecoratedStart(view) >= this.o.getEnd() - i : this.o.getDecoratedEnd(view) <= i;
    }

    public final boolean r(View view, int i) {
        return (i() || !this.g) ? this.o.getDecoratedEnd(view) <= i : this.o.getEnd() - this.o.getDecoratedStart(view) <= i;
    }

    public final void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    public final void s() {
        this.i.clear();
        this.n.s();
        this.n.d = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!i()) {
            int iH = H(i, recycler, state);
            this.w.clear();
            return iH;
        }
        int I = I(i);
        this.n.d += I;
        this.p.offsetChildren(-I);
        return I;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.r = i;
        this.s = Integer.MIN_VALUE;
        SavedState savedState = this.q;
        if (savedState != null) {
            savedState.u();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (i()) {
            int iH = H(i, recycler, state);
            this.w.clear();
            return iH;
        }
        int I = I(i);
        this.n.d += I;
        this.p.offsetChildren(-I);
        return I;
    }

    @Override // b.b.a.a.a
    public void setFlexLines(List<b.b.a.a.b> list) {
        this.i = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public final void t() {
        if (this.o != null) {
            return;
        }
        if (i()) {
            if (this.f1136c == 0) {
                this.o = OrientationHelper.createHorizontalHelper(this);
                this.p = OrientationHelper.createVerticalHelper(this);
                return;
            } else {
                this.o = OrientationHelper.createVerticalHelper(this);
                this.p = OrientationHelper.createHorizontalHelper(this);
                return;
            }
        }
        if (this.f1136c == 0) {
            this.o = OrientationHelper.createVerticalHelper(this);
            this.p = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.o = OrientationHelper.createHorizontalHelper(this);
            this.p = OrientationHelper.createVerticalHelper(this);
        }
    }

    public final int u(RecyclerView.Recycler recycler, RecyclerView.State state, c cVar) {
        if (cVar.f != Integer.MIN_VALUE) {
            if (cVar.f1145a < 0) {
                cVar.f += cVar.f1145a;
            }
            O(recycler, cVar);
        }
        int i = cVar.f1145a;
        int iA = cVar.f1145a;
        int iL = 0;
        boolean zI = i();
        while (true) {
            if ((iA <= 0 && !this.m.f1146b) || !cVar.w(state, this.i)) {
                break;
            }
            b.b.a.a.b bVar = this.i.get(cVar.f1147c);
            cVar.d = bVar.o;
            iL += L(bVar, cVar);
            if (zI || !this.g) {
                cVar.e += bVar.a() * cVar.i;
            } else {
                cVar.e -= bVar.a() * cVar.i;
            }
            iA -= bVar.a();
        }
        cVar.f1145a -= iL;
        if (cVar.f != Integer.MIN_VALUE) {
            cVar.f += iL;
            if (cVar.f1145a < 0) {
                cVar.f += cVar.f1145a;
            }
            O(recycler, cVar);
        }
        return i - cVar.f1145a;
    }

    public final View v(int i) {
        View viewA = A(0, getChildCount(), i);
        if (viewA == null) {
            return null;
        }
        int i2 = this.j.f957c[getPosition(viewA)];
        if (i2 == -1) {
            return null;
        }
        return w(viewA, this.i.get(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View w(android.view.View r6, b.b.a.a.b r7) {
        /*
            r5 = this;
            boolean r0 = r5.i()
            int r7 = r7.h
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.getChildAt(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.g
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.OrientationHelper r3 = r5.o
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.o
            int r4 = r4.getDecoratedEnd(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.o
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.o
            int r4 = r4.getDecoratedStart(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.w(android.view.View, b.b.a.a.b):android.view.View");
    }

    public final View x(int i) {
        View viewA = A(getChildCount() - 1, -1, i);
        if (viewA == null) {
            return null;
        }
        return y(viewA, this.i.get(this.j.f957c[getPosition(viewA)]));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View y(android.view.View r6, b.b.a.a.b r7) {
        /*
            r5 = this;
            boolean r0 = r5.i()
            int r1 = r5.getChildCount()
            int r1 = r1 + (-2)
            int r2 = r5.getChildCount()
            int r7 = r7.h
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.getChildAt(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.g
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.OrientationHelper r3 = r5.o
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.o
            int r4 = r4.getDecoratedStart(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.o
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.o
            int r4 = r4.getDecoratedEnd(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.y(android.view.View, b.b.a.a.b):android.view.View");
    }

    public final View z(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (K(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1140a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1141b;

        /* JADX INFO: compiled from: caradapter-bug */
        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public final boolean t(int i) {
            int i2 = this.f1140a;
            return i2 >= 0 && i2 < i;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.f1140a + ", mAnchorOffset=" + this.f1141b + '}';
        }

        public final void u() {
            this.f1140a = -1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1140a);
            parcel.writeInt(this.f1141b);
        }

        public SavedState() {
        }

        public SavedState(Parcel parcel) {
            this.f1140a = parcel.readInt();
            this.f1141b = parcel.readInt();
        }

        public SavedState(SavedState savedState) {
            this.f1140a = savedState.f1140a;
            this.f1141b = savedState.f1141b;
        }
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.f = -1;
        this.i = new ArrayList();
        this.j = new b.b.a.a.c(this);
        this.n = new b();
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.w = new SparseArray<>();
        this.z = -1;
        this.A = new c.b();
        U(i);
        V(i2);
        T(4);
        setAutoMeasureEnabled(true);
        this.x = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        Z(i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1137a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1138b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1139c;
        public float d;
        public int e;
        public int f;
        public int g;
        public int h;
        public boolean i;

        /* JADX INFO: compiled from: caradapter-bug */
        public static class a implements Parcelable.Creator<LayoutParams> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1137a = 0.0f;
            this.f1138b = 1.0f;
            this.f1139c = -1;
            this.d = -1.0f;
            this.g = ViewCompat.MEASURED_SIZE_MASK;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return this.e;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean d() {
            return this.i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float e() {
            return this.f1137a;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int g() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float h() {
            return this.d;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int i() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int j() {
            return this.f1139c;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float k() {
            return this.f1138b;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int l() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int m() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.f1137a);
            parcel.writeFloat(this.f1138b);
            parcel.writeInt(this.f1139c);
            parcel.writeFloat(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f1137a = 0.0f;
            this.f1138b = 1.0f;
            this.f1139c = -1;
            this.d = -1.0f;
            this.g = ViewCompat.MEASURED_SIZE_MASK;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
        }

        public LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.f1137a = 0.0f;
            this.f1138b = 1.0f;
            this.f1139c = -1;
            this.d = -1.0f;
            this.g = ViewCompat.MEASURED_SIZE_MASK;
            this.h = ViewCompat.MEASURED_SIZE_MASK;
            this.f1137a = parcel.readFloat();
            this.f1138b = parcel.readFloat();
            this.f1139c = parcel.readInt();
            this.d = parcel.readFloat();
            this.e = parcel.readInt();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f = -1;
        this.i = new ArrayList();
        this.j = new b.b.a.a.c(this);
        this.n = new b();
        this.r = -1;
        this.s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.u = Integer.MIN_VALUE;
        this.w = new SparseArray<>();
        this.z = -1;
        this.A = new c.b();
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    U(3);
                } else {
                    U(2);
                }
            }
        } else if (properties.reverseLayout) {
            U(1);
        } else {
            U(0);
        }
        V(1);
        T(4);
        setAutoMeasureEnabled(true);
        this.x = context;
    }
}
