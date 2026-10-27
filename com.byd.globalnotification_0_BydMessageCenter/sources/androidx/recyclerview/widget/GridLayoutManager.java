package androidx.recyclerview.widget;

import a.h.k.x.b;
import a.p.e.n;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean H;
    public int I;
    public int[] J;
    public View[] K;
    public final SparseIntArray L;
    public final SparseIntArray M;
    public c N;
    public final Rect O;

    public static final class a extends c {
    }

    public static class b extends RecyclerView.n {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1591e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1592f;

        public b(int i, int i2) {
            super(i, i2);
            this.f1591e = -1;
            this.f1592f = 0;
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1591e = -1;
            this.f1592f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1591e = -1;
            this.f1592f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1591e = -1;
            this.f1592f = 0;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseIntArray f1593a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final SparseIntArray f1594b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1595c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1596d = false;

        public int a(int i, int i2) {
            if (!this.f1596d) {
                return c(i, i2);
            }
            int i3 = this.f1594b.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iC = c(i, i2);
            this.f1594b.put(i, iC);
            return iC;
        }

        public int b(int i, int i2) {
            if (!this.f1595c) {
                return i % i2;
            }
            int i3 = this.f1593a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int i4 = i % i2;
            this.f1593a.put(i, i4);
            return i4;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int c(int r9, int r10) {
            /*
                r8 = this;
                boolean r0 = r8.f1596d
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L44
                android.util.SparseIntArray r0 = r8.f1594b
                int r3 = r0.size()
                r4 = -1
                int r3 = r3 + r4
                r5 = r1
            Lf:
                if (r5 > r3) goto L20
                int r6 = r5 + r3
                int r6 = r6 >>> r2
                int r7 = r0.keyAt(r6)
                if (r7 >= r9) goto L1d
                int r5 = r6 + 1
                goto Lf
            L1d:
                int r3 = r6 + (-1)
                goto Lf
            L20:
                int r5 = r5 + r4
                if (r5 < 0) goto L2e
                int r3 = r0.size()
                if (r5 >= r3) goto L2e
                int r0 = r0.keyAt(r5)
                goto L2f
            L2e:
                r0 = r4
            L2f:
                if (r0 == r4) goto L44
                android.util.SparseIntArray r3 = r8.f1594b
                int r3 = r3.get(r0)
                int r4 = r0 + 1
                int r8 = r8.b(r0, r10)
                int r8 = r8 + r2
                if (r8 != r10) goto L47
                int r3 = r3 + 1
                r8 = r1
                goto L47
            L44:
                r8 = r1
                r3 = r8
                r4 = r3
            L47:
                if (r4 >= r9) goto L59
                int r8 = r8 + 1
                if (r8 != r10) goto L51
                int r3 = r3 + 1
                r8 = r1
                goto L56
            L51:
                if (r8 <= r10) goto L56
                int r3 = r3 + 1
                r8 = r2
            L56:
                int r4 = r4 + 1
                goto L47
            L59:
                int r8 = r8 + r2
                if (r8 <= r10) goto L5e
                int r3 = r3 + 1
            L5e:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.c.c(int, int):int");
        }
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(i2, z);
        this.H = false;
        this.I = -1;
        this.L = new SparseIntArray();
        this.M = new SparseIntArray();
        this.N = new a();
        this.O = new Rect();
        P1(i);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.H = false;
        this.I = -1;
        this.L = new SparseIntArray();
        this.M = new SparseIntArray();
        this.N = new a();
        this.O = new Rect();
        P1(RecyclerView.m.S(context, attributeSet, i, i2).f1656b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int B(RecyclerView.t tVar, RecyclerView.y yVar) {
        if (this.s == 1) {
            return this.I;
        }
        if (yVar.b() < 1) {
            return 0;
        }
        return K1(tVar, yVar, yVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void D1(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        d(null);
        if (this.y) {
            this.y = false;
            K0();
        }
    }

    public final void H1(int i) {
        int i2;
        int[] iArr = this.J;
        int i3 = this.I;
        if (iArr == null || iArr.length != i3 + 1 || iArr[iArr.length - 1] != i) {
            iArr = new int[i3 + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.J = iArr;
    }

    public final void I1() {
        View[] viewArr = this.K;
        if (viewArr == null || viewArr.length != this.I) {
            this.K = new View[this.I];
        }
    }

    public int J1(int i, int i2) {
        if (this.s != 1 || !u1()) {
            int[] iArr = this.J;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.J;
        int i3 = this.I;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public final int K1(RecyclerView.t tVar, RecyclerView.y yVar, int i) {
        if (!yVar.g) {
            return this.N.a(i, this.I);
        }
        int iC = tVar.c(i);
        if (iC != -1) {
            return this.N.a(iC, this.I);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int L0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        Q1();
        I1();
        if (this.s == 1) {
            return 0;
        }
        return B1(i, tVar, yVar);
    }

    public final int L1(RecyclerView.t tVar, RecyclerView.y yVar, int i) {
        if (!yVar.g) {
            return this.N.b(i, this.I);
        }
        int i2 = this.M.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iC = tVar.c(i);
        if (iC != -1) {
            return this.N.b(iC, this.I);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    public final int M1(RecyclerView.t tVar, RecyclerView.y yVar, int i) {
        if (!yVar.g) {
            if (((a) this.N) != null) {
                return 1;
            }
            throw null;
        }
        int i2 = this.L.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        if (tVar.c(i) != -1) {
            if (((a) this.N) != null) {
                return 1;
            }
            throw null;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int N0(int i, RecyclerView.t tVar, RecyclerView.y yVar) {
        Q1();
        I1();
        if (this.s == 0) {
            return 0;
        }
        return B1(i, tVar, yVar);
    }

    public final void N1(View view, int i, boolean z) {
        int iA;
        int iA2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1660b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iJ1 = J1(bVar.f1591e, bVar.f1592f);
        if (this.s == 1) {
            iA2 = RecyclerView.m.A(iJ1, i, i3, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iA = RecyclerView.m.A(this.u.l(), this.p, i2, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iA3 = RecyclerView.m.A(iJ1, i, i2, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iA4 = RecyclerView.m.A(this.u.l(), this.o, i3, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iA = iA3;
            iA2 = iA4;
        }
        O1(view, iA2, iA, z);
    }

    public final void O1(View view, int i, int i2, boolean z) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        if (z ? V0(view, i, i2, nVar) : T0(view, i, i2, nVar)) {
            view.measure(i, i2);
        }
    }

    public void P1(int i) {
        if (i == this.I) {
            return;
        }
        this.H = true;
        if (i >= 1) {
            this.I = i;
            this.N.f1593a.clear();
            K0();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void Q0(Rect rect, int i, int i2) {
        int i3;
        int i4;
        if (this.J == null) {
            super.Q0(rect, i, i2);
        }
        int iP = P() + O();
        int iN = N() + Q();
        if (this.s == 1) {
            i4 = RecyclerView.m.i(i2, rect.height() + iN, L());
            int[] iArr = this.J;
            i3 = RecyclerView.m.i(i, iArr[iArr.length - 1] + iP, M());
        } else {
            i3 = RecyclerView.m.i(i, rect.width() + iP, M());
            int[] iArr2 = this.J;
            i4 = RecyclerView.m.i(i2, iArr2[iArr2.length - 1] + iN, L());
        }
        this.f1648b.setMeasuredDimension(i3, i4);
    }

    public final void Q1() {
        int iN;
        int iQ;
        if (this.s == 1) {
            iN = this.q - P();
            iQ = O();
        } else {
            iN = this.r - N();
            iQ = Q();
        }
        H1(iN - iQ);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int T(RecyclerView.t tVar, RecyclerView.y yVar) {
        if (this.s == 0) {
            return this.I;
        }
        if (yVar.b() < 1) {
            return 0;
        }
        return K1(tVar, yVar, yVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public boolean Y0() {
        return this.C == null && !this.H;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void a1(RecyclerView.y yVar, LinearLayoutManager.c cVar, RecyclerView.m.c cVar2) {
        int i = this.I;
        for (int i2 = 0; i2 < this.I && cVar.b(yVar) && i > 0; i2++) {
            ((n.b) cVar2).a(cVar.f1609d, Math.max(0, cVar.g));
            if (((a) this.N) == null) {
                throw null;
            }
            i--;
            cVar.f1609d += cVar.f1610e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean h(RecyclerView.n nVar) {
        return nVar instanceof b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ca, code lost:
    
        if (r13 == (r2 > r15)) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f0  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View i0(android.view.View r23, int r24, androidx.recyclerview.widget.RecyclerView.t r25, androidx.recyclerview.widget.RecyclerView.y r26) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.i0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$y):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int m(RecyclerView.y yVar) {
        return c1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void m0(RecyclerView.t tVar, RecyclerView.y yVar, View view, a.h.k.x.b bVar) {
        int i;
        int i2;
        int i3;
        boolean z;
        boolean z2;
        int i4;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.l0(view, bVar);
            return;
        }
        b bVar2 = (b) layoutParams;
        int iK1 = K1(tVar, yVar, bVar2.a());
        if (this.s == 0) {
            i4 = bVar2.f1591e;
            i = bVar2.f1592f;
            i3 = 1;
            z = false;
            z2 = false;
            i2 = iK1;
        } else {
            i = 1;
            i2 = bVar2.f1591e;
            i3 = bVar2.f1592f;
            z = false;
            z2 = false;
            i4 = iK1;
        }
        bVar.i(b.c.a(i4, i, i2, i3, z, z2));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int n(RecyclerView.y yVar) {
        return d1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void o0(RecyclerView recyclerView, int i, int i2) {
        this.N.f1593a.clear();
        this.N.f1594b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int p(RecyclerView.y yVar) {
        return c1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void p0(RecyclerView recyclerView) {
        this.N.f1593a.clear();
        this.N.f1594b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public View p1(RecyclerView.t tVar, RecyclerView.y yVar, int i, int i2, int i3) {
        f1();
        int iK = this.u.k();
        int iG = this.u.g();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewY = y(i);
            int iR = R(viewY);
            if (iR >= 0 && iR < i3 && L1(tVar, yVar, iR) == 0) {
                if (((RecyclerView.n) viewY.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewY;
                    }
                } else {
                    if (this.u.e(viewY) < iG && this.u.b(viewY) >= iK) {
                        return viewY;
                    }
                    if (view == null) {
                        view = viewY;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.y yVar) {
        return d1(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void q0(RecyclerView recyclerView, int i, int i2, int i3) {
        this.N.f1593a.clear();
        this.N.f1594b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void r0(RecyclerView recyclerView, int i, int i2) {
        this.N.f1593a.clear();
        this.N.f1594b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void t0(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.N.f1593a.clear();
        this.N.f1594b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void u0(RecyclerView.t tVar, RecyclerView.y yVar) {
        if (yVar.g) {
            int iZ = z();
            for (int i = 0; i < iZ; i++) {
                b bVar = (b) y(i).getLayoutParams();
                int iA = bVar.a();
                this.L.put(iA, bVar.f1592f);
                this.M.put(iA, bVar.f1591e);
            }
        }
        super.u0(tVar, yVar);
        this.L.clear();
        this.M.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n v() {
        return this.s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void v0(RecyclerView.y yVar) {
        this.C = null;
        this.A = -1;
        this.B = Integer.MIN_VALUE;
        this.D.d();
        this.H = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x024d  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v1(androidx.recyclerview.widget.RecyclerView.t r19, androidx.recyclerview.widget.RecyclerView.y r20, androidx.recyclerview.widget.LinearLayoutManager.c r21, androidx.recyclerview.widget.LinearLayoutManager.b r22) {
        /*
            Method dump skipped, instruction units count: 616
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.v1(androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$y, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n w(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void w1(RecyclerView.t tVar, RecyclerView.y yVar, LinearLayoutManager.a aVar, int i) {
        Q1();
        if (yVar.b() > 0 && !yVar.g) {
            boolean z = i == 1;
            int iL1 = L1(tVar, yVar, aVar.f1598b);
            if (z) {
                while (iL1 > 0) {
                    int i2 = aVar.f1598b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    aVar.f1598b = i3;
                    iL1 = L1(tVar, yVar, i3);
                }
            } else {
                int iB = yVar.b() - 1;
                int i4 = aVar.f1598b;
                while (i4 < iB) {
                    int i5 = i4 + 1;
                    int iL12 = L1(tVar, yVar, i5);
                    if (iL12 <= iL1) {
                        break;
                    }
                    i4 = i5;
                    iL1 = iL12;
                }
                aVar.f1598b = i4;
            }
        }
        I1();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n x(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }
}
