package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import p0.c;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1560b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f1561c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View[] f1562d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SparseIntArray f1563e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final SparseIntArray f1564f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f1565g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f1566h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1567i;

    public static final class a extends c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int e(int i8, int i9) {
            return i8 % i9;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i8) {
            return 1;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseIntArray f1570a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final SparseIntArray f1571b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1572c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1573d = false;

        public static int a(SparseIntArray sparseIntArray, int i8) {
            int size = sparseIntArray.size() - 1;
            int i9 = 0;
            while (i9 <= size) {
                int i10 = (i9 + size) >>> 1;
                if (sparseIntArray.keyAt(i10) < i8) {
                    i9 = i10 + 1;
                } else {
                    size = i10 - 1;
                }
            }
            int i11 = i9 - 1;
            if (i11 < 0 || i11 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i11);
        }

        public int b(int i8, int i9) {
            if (!this.f1573d) {
                return d(i8, i9);
            }
            int i10 = this.f1571b.get(i8, -1);
            if (i10 != -1) {
                return i10;
            }
            int iD = d(i8, i9);
            this.f1571b.put(i8, iD);
            return iD;
        }

        public int c(int i8, int i9) {
            if (!this.f1572c) {
                return e(i8, i9);
            }
            int i10 = this.f1570a.get(i8, -1);
            if (i10 != -1) {
                return i10;
            }
            int iE = e(i8, i9);
            this.f1570a.put(i8, iE);
            return iE;
        }

        public int d(int i8, int i9) {
            int i10;
            int i11;
            int iC;
            int iA;
            if (!this.f1573d || (iA = a(this.f1571b, i8)) == -1) {
                i10 = 0;
                i11 = 0;
                iC = 0;
            } else {
                i10 = this.f1571b.get(iA);
                i11 = iA + 1;
                iC = c(iA, i9) + f(iA);
                if (iC == i9) {
                    i10++;
                    iC = 0;
                }
            }
            int iF = f(i8);
            while (i11 < i8) {
                int iF2 = f(i11);
                iC += iF2;
                if (iC == i9) {
                    i10++;
                    iC = 0;
                } else if (iC > i9) {
                    i10++;
                    iC = iF2;
                }
                i11++;
            }
            return iC + iF > i9 ? i10 + 1 : i10;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002b -> B:17:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002d -> B:17:0x0030). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002f -> B:17:0x0030). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int e(int r6, int r7) {
            /*
                r5 = this;
                int r0 = r5.f(r6)
                r1 = 0
                if (r0 != r7) goto L8
                return r1
            L8:
                boolean r2 = r5.f1572c
                if (r2 == 0) goto L20
                android.util.SparseIntArray r2 = r5.f1570a
                int r2 = a(r2, r6)
                if (r2 < 0) goto L20
                android.util.SparseIntArray r3 = r5.f1570a
                int r3 = r3.get(r2)
                int r4 = r5.f(r2)
                int r3 = r3 + r4
                goto L30
            L20:
                r2 = r1
                r3 = r2
            L22:
                if (r2 >= r6) goto L33
                int r4 = r5.f(r2)
                int r3 = r3 + r4
                if (r3 != r7) goto L2d
                r3 = r1
                goto L30
            L2d:
                if (r3 <= r7) goto L30
                r3 = r4
            L30:
                int r2 = r2 + 1
                goto L22
            L33:
                int r0 = r0 + r3
                if (r0 > r7) goto L37
                return r3
            L37:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.c.e(int, int):int");
        }

        public abstract int f(int i8);

        public void g() {
            this.f1571b.clear();
        }

        public void h() {
            this.f1570a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.f1559a = false;
        this.f1560b = -1;
        this.f1563e = new SparseIntArray();
        this.f1564f = new SparseIntArray();
        this.f1565g = new a();
        this.f1566h = new Rect();
        r(RecyclerView.o.getProperties(context, attributeSet, i8, i9).f1626b);
    }

    public static int[] d(int[] iArr, int i8, int i9) {
        int i10;
        if (iArr == null || iArr.length != i8 + 1 || iArr[iArr.length - 1] != i9) {
            iArr = new int[i8 + 1];
        }
        int i11 = 0;
        iArr[0] = 0;
        int i12 = i9 / i8;
        int i13 = i9 % i8;
        int i14 = 0;
        for (int i15 = 1; i15 <= i8; i15++) {
            i11 += i13;
            if (i11 <= 0 || i8 - i11 >= i13) {
                i10 = i12;
            } else {
                i10 = i12 + 1;
                i11 -= i8;
            }
            i14 += i10;
            iArr[i15] = i14;
        }
        return iArr;
    }

    public final void a(RecyclerView.v vVar, RecyclerView.z zVar, int i8, boolean z7) {
        int i9;
        int i10;
        int i11 = 0;
        int i12 = -1;
        if (z7) {
            i10 = 1;
            i12 = i8;
            i9 = 0;
        } else {
            i9 = i8 - 1;
            i10 = -1;
        }
        while (i9 != i12) {
            View view = this.f1562d[i9];
            b bVar = (b) view.getLayoutParams();
            int iN = n(vVar, zVar, getPosition(view));
            bVar.f1569f = iN;
            bVar.f1568e = i11;
            i11 += iN;
            i9 += i10;
        }
    }

    public final void b() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            b bVar = (b) getChildAt(i8).getLayoutParams();
            int iA = bVar.a();
            this.f1563e.put(iA, bVar.f());
            this.f1564f.put(iA, bVar.e());
        }
    }

    public final void c(int i8) {
        this.f1561c = d(this.f1561c, this.f1560b, i8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean checkLayoutParams(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void collectPrefetchPositionsForLayoutState(RecyclerView.z zVar, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.f1560b;
        for (int i8 = 0; i8 < this.f1560b && cVar.c(zVar) && iF > 0; i8++) {
            int i9 = cVar.f1589d;
            cVar2.a(i9, Math.max(0, cVar.f1592g));
            iF -= this.f1565g.f(i9);
            cVar.f1589d += cVar.f1590e;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.z zVar) {
        return this.f1567i ? f(zVar) : super.computeHorizontalScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.z zVar) {
        return this.f1567i ? g(zVar) : super.computeHorizontalScrollRange(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.z zVar) {
        return this.f1567i ? f(zVar) : super.computeVerticalScrollOffset(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.z zVar) {
        return this.f1567i ? g(zVar) : super.computeVerticalScrollRange(zVar);
    }

    public final void e() {
        this.f1563e.clear();
        this.f1564f.clear();
    }

    public final int f(RecyclerView.z zVar) {
        if (getChildCount() != 0 && zVar.b() != 0) {
            ensureLayoutState();
            boolean zIsSmoothScrollbarEnabled = isSmoothScrollbarEnabled();
            View viewFindFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!zIsSmoothScrollbarEnabled, true);
            View viewFindFirstVisibleChildClosestToEnd = findFirstVisibleChildClosestToEnd(!zIsSmoothScrollbarEnabled, true);
            if (viewFindFirstVisibleChildClosestToStart != null && viewFindFirstVisibleChildClosestToEnd != null) {
                int iB = this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToStart), this.f1560b);
                int iB2 = this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToEnd), this.f1560b);
                int iMax = this.mShouldReverseLayout ? Math.max(0, ((this.f1565g.b(zVar.b() - 1, this.f1560b) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zIsSmoothScrollbarEnabled) {
                    return Math.round((iMax * (Math.abs(this.mOrientationHelper.d(viewFindFirstVisibleChildClosestToEnd) - this.mOrientationHelper.g(viewFindFirstVisibleChildClosestToStart)) / ((this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToEnd), this.f1560b) - this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToStart), this.f1560b)) + 1))) + (this.mOrientationHelper.m() - this.mOrientationHelper.g(viewFindFirstVisibleChildClosestToStart)));
                }
                return iMax;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
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
            if (position >= 0 && position < i10 && m(vVar, zVar, position) == 0) {
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

    public final int g(RecyclerView.z zVar) {
        if (getChildCount() != 0 && zVar.b() != 0) {
            ensureLayoutState();
            View viewFindFirstVisibleChildClosestToStart = findFirstVisibleChildClosestToStart(!isSmoothScrollbarEnabled(), true);
            View viewFindFirstVisibleChildClosestToEnd = findFirstVisibleChildClosestToEnd(!isSmoothScrollbarEnabled(), true);
            if (viewFindFirstVisibleChildClosestToStart != null && viewFindFirstVisibleChildClosestToEnd != null) {
                if (!isSmoothScrollbarEnabled()) {
                    return this.f1565g.b(zVar.b() - 1, this.f1560b) + 1;
                }
                int iD = this.mOrientationHelper.d(viewFindFirstVisibleChildClosestToEnd) - this.mOrientationHelper.g(viewFindFirstVisibleChildClosestToStart);
                int iB = this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToStart), this.f1560b);
                return (int) ((iD / ((this.f1565g.b(getPosition(viewFindFirstVisibleChildClosestToEnd), this.f1560b) - iB) + 1)) * (this.f1565g.b(zVar.b() - 1, this.f1560b) + 1));
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getColumnCountForAccessibility(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.mOrientation == 1) {
            return this.f1560b;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return l(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getRowCountForAccessibility(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.mOrientation == 0) {
            return this.f1560b;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return l(vVar, zVar, zVar.b() - 1) + 1;
    }

    public final void h(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i8) {
        boolean z7 = i8 == 1;
        int iM = m(vVar, zVar, aVar.f1578b);
        if (z7) {
            while (iM > 0) {
                int i9 = aVar.f1578b;
                if (i9 <= 0) {
                    return;
                }
                int i10 = i9 - 1;
                aVar.f1578b = i10;
                iM = m(vVar, zVar, i10);
            }
            return;
        }
        int iB = zVar.b() - 1;
        int i11 = aVar.f1578b;
        while (i11 < iB) {
            int i12 = i11 + 1;
            int iM2 = m(vVar, zVar, i12);
            if (iM2 <= iM) {
                break;
            }
            i11 = i12;
            iM = iM2;
        }
        aVar.f1578b = i11;
    }

    public final void i() {
        View[] viewArr = this.f1562d;
        if (viewArr == null || viewArr.length != this.f1560b) {
            this.f1562d = new View[this.f1560b];
        }
    }

    public int j(int i8, int i9) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.f1561c;
            return iArr[i9 + i8] - iArr[i8];
        }
        int[] iArr2 = this.f1561c;
        int i10 = this.f1560b;
        return iArr2[i10 - i8] - iArr2[(i10 - i8) - i9];
    }

    public int k() {
        return this.f1560b;
    }

    public final int l(RecyclerView.v vVar, RecyclerView.z zVar, int i8) {
        if (!zVar.e()) {
            return this.f1565g.b(i8, this.f1560b);
        }
        int iF = vVar.f(i8);
        if (iF != -1) {
            return this.f1565g.b(iF, this.f1560b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i8);
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0218  */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v19 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layoutChunk(androidx.recyclerview.widget.RecyclerView.v r18, androidx.recyclerview.widget.RecyclerView.z r19, androidx.recyclerview.widget.LinearLayoutManager.c r20, androidx.recyclerview.widget.LinearLayoutManager.b r21) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.layoutChunk(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    public final int m(RecyclerView.v vVar, RecyclerView.z zVar, int i8) {
        if (!zVar.e()) {
            return this.f1565g.c(i8, this.f1560b);
        }
        int i9 = this.f1564f.get(i8, -1);
        if (i9 != -1) {
            return i9;
        }
        int iF = vVar.f(i8);
        if (iF != -1) {
            return this.f1565g.c(iF, this.f1560b);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i8);
        return 0;
    }

    public final int n(RecyclerView.v vVar, RecyclerView.z zVar, int i8) {
        if (!zVar.e()) {
            return this.f1565g.f(i8);
        }
        int i9 = this.f1563e.get(i8, -1);
        if (i9 != -1) {
            return i9;
        }
        int iF = vVar.f(i8);
        if (iF != -1) {
            return this.f1565g.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i8);
        return 1;
    }

    public final void o(float f8, int i8) {
        c(Math.max(Math.round(f8 * this.f1560b), i8));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void onAnchorReady(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i8) {
        super.onAnchorReady(vVar, zVar, aVar, i8);
        t();
        if (zVar.b() > 0 && !zVar.e()) {
            h(vVar, zVar, aVar, i8);
        }
        i();
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        if (r13 == (r2 > r15)) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0107  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View onFocusSearchFailed(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.z r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v vVar, RecyclerView.z zVar, View view, p0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iL = l(vVar, zVar, bVar.a());
        if (this.mOrientation == 0) {
            cVar.f0(c.C0101c.a(bVar.e(), bVar.f(), iL, 1, false, false));
        } else {
            cVar.f0(c.C0101c.a(iL, 1, bVar.e(), bVar.f(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsAdded(RecyclerView recyclerView, int i8, int i9) {
        this.f1565g.h();
        this.f1565g.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsChanged(RecyclerView recyclerView) {
        this.f1565g.h();
        this.f1565g.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsMoved(RecyclerView recyclerView, int i8, int i9, int i10) {
        this.f1565g.h();
        this.f1565g.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsRemoved(RecyclerView recyclerView, int i8, int i9) {
        this.f1565g.h();
        this.f1565g.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsUpdated(RecyclerView recyclerView, int i8, int i9, Object obj) {
        this.f1565g.h();
        this.f1565g.g();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (zVar.e()) {
            b();
        }
        super.onLayoutChildren(vVar, zVar);
        e();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.z zVar) {
        super.onLayoutCompleted(zVar);
        this.f1559a = false;
    }

    public final void p(View view, int i8, boolean z7) {
        int childMeasureSpec;
        int childMeasureSpec2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1630b;
        int i9 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i10 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iJ = j(bVar.f1568e, bVar.f1569f);
        if (this.mOrientation == 1) {
            childMeasureSpec2 = RecyclerView.o.getChildMeasureSpec(iJ, i8, i10, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            childMeasureSpec = RecyclerView.o.getChildMeasureSpec(this.mOrientationHelper.n(), getHeightMode(), i9, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int childMeasureSpec3 = RecyclerView.o.getChildMeasureSpec(iJ, i8, i9, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int childMeasureSpec4 = RecyclerView.o.getChildMeasureSpec(this.mOrientationHelper.n(), getWidthMode(), i10, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            childMeasureSpec = childMeasureSpec3;
            childMeasureSpec2 = childMeasureSpec4;
        }
        q(view, childMeasureSpec2, childMeasureSpec, z7);
    }

    public final void q(View view, int i8, int i9, boolean z7) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z7 ? shouldReMeasureChild(view, i8, i9, pVar) : shouldMeasureChild(view, i8, i9, pVar)) {
            view.measure(i8, i9);
        }
    }

    public void r(int i8) {
        if (i8 == this.f1560b) {
            return;
        }
        this.f1559a = true;
        if (i8 >= 1) {
            this.f1560b = i8;
            this.f1565g.h();
            requestLayout();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i8);
        }
    }

    public void s(c cVar) {
        this.f1565g = cVar;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        t();
        i();
        return super.scrollHorizontallyBy(i8, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i8, RecyclerView.v vVar, RecyclerView.z zVar) {
        t();
        i();
        return super.scrollVerticallyBy(i8, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void setMeasuredDimension(Rect rect, int i8, int i9) {
        int iChooseSize;
        int iChooseSize2;
        if (this.f1561c == null) {
            super.setMeasuredDimension(rect, i8, i9);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            iChooseSize2 = RecyclerView.o.chooseSize(i9, rect.height() + paddingTop, getMinimumHeight());
            int[] iArr = this.f1561c;
            iChooseSize = RecyclerView.o.chooseSize(i8, iArr[iArr.length - 1] + paddingLeft, getMinimumWidth());
        } else {
            iChooseSize = RecyclerView.o.chooseSize(i8, rect.width() + paddingLeft, getMinimumWidth());
            int[] iArr2 = this.f1561c;
            iChooseSize2 = RecyclerView.o.chooseSize(i9, iArr2[iArr2.length - 1] + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void setStackFromEnd(boolean z7) {
        if (z7) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.f1559a;
    }

    public final void t() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        c(height - paddingTop);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    public static class b extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1568e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1569f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1568e = -1;
            this.f1569f = 0;
        }

        public int e() {
            return this.f1568e;
        }

        public int f() {
            return this.f1569f;
        }

        public b(int i8, int i9) {
            super(i8, i9);
            this.f1568e = -1;
            this.f1569f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1568e = -1;
            this.f1569f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1568e = -1;
            this.f1569f = 0;
        }
    }

    public GridLayoutManager(Context context, int i8) {
        super(context);
        this.f1559a = false;
        this.f1560b = -1;
        this.f1563e = new SparseIntArray();
        this.f1564f = new SparseIntArray();
        this.f1565g = new a();
        this.f1566h = new Rect();
        r(i8);
    }

    public GridLayoutManager(Context context, int i8, int i9, boolean z7) {
        super(context, i9, z7);
        this.f1559a = false;
        this.f1560b = -1;
        this.f1563e = new SparseIntArray();
        this.f1564f = new SparseIntArray();
        this.f1565g = new a();
        this.f1566h = new Rect();
        r(i8);
    }
}
