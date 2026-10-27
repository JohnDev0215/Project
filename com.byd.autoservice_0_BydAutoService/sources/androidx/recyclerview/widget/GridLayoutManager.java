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
import m0.c;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean I;
    public int J;
    public int[] K;
    public View[] L;
    public final SparseIntArray M;
    public final SparseIntArray N;
    public c O;
    public final Rect P;
    public boolean Q;

    public static final class a extends c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int e(int i3, int i4) {
            return i3 % i4;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i3) {
            return 1;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseIntArray f1375a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final SparseIntArray f1376b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1377c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1378d = false;

        public static int a(SparseIntArray sparseIntArray, int i3) {
            int size = sparseIntArray.size() - 1;
            int i4 = 0;
            while (i4 <= size) {
                int i5 = (i4 + size) >>> 1;
                if (sparseIntArray.keyAt(i5) < i3) {
                    i4 = i5 + 1;
                } else {
                    size = i5 - 1;
                }
            }
            int i6 = i4 - 1;
            if (i6 < 0 || i6 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i6);
        }

        public int b(int i3, int i4) {
            if (!this.f1378d) {
                return d(i3, i4);
            }
            int i5 = this.f1376b.get(i3, -1);
            if (i5 != -1) {
                return i5;
            }
            int iD = d(i3, i4);
            this.f1376b.put(i3, iD);
            return iD;
        }

        public int c(int i3, int i4) {
            if (!this.f1377c) {
                return e(i3, i4);
            }
            int i5 = this.f1375a.get(i3, -1);
            if (i5 != -1) {
                return i5;
            }
            int iE = e(i3, i4);
            this.f1375a.put(i3, iE);
            return iE;
        }

        public int d(int i3, int i4) {
            int i5;
            int i6;
            int iC;
            int iA;
            if (!this.f1378d || (iA = a(this.f1376b, i3)) == -1) {
                i5 = 0;
                i6 = 0;
                iC = 0;
            } else {
                i5 = this.f1376b.get(iA);
                i6 = iA + 1;
                iC = c(iA, i4) + f(iA);
                if (iC == i4) {
                    i5++;
                    iC = 0;
                }
            }
            int iF = f(i3);
            while (i6 < i3) {
                int iF2 = f(i6);
                iC += iF2;
                if (iC == i4) {
                    i5++;
                    iC = 0;
                } else if (iC > i4) {
                    i5++;
                    iC = iF2;
                }
                i6++;
            }
            return iC + iF > i4 ? i5 + 1 : i5;
        }

        public abstract int e(int i3, int i4);

        public abstract int f(int i3);

        public void g() {
            this.f1376b.clear();
        }

        public void h() {
            this.f1375a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i3, int i4) {
        super(context, attributeSet, i3, i4);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        e3(RecyclerView.o.i0(context, attributeSet, i3, i4).f1529b);
    }

    public static int[] Q2(int[] iArr, int i3, int i4) {
        int i5;
        if (iArr == null || iArr.length != i3 + 1 || iArr[iArr.length - 1] != i4) {
            iArr = new int[i3 + 1];
        }
        int i6 = 0;
        iArr[0] = 0;
        int i7 = i4 / i3;
        int i8 = i4 % i3;
        int i9 = 0;
        for (int i10 = 1; i10 <= i3; i10++) {
            i6 += i8;
            if (i6 <= 0 || i3 - i6 >= i8) {
                i5 = i7;
            } else {
                i5 = i7 + 1;
                i6 -= i3;
            }
            i9 += i5;
            iArr[i10] = i9;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i3, int i4) {
        int iN;
        int iN2;
        if (this.K == null) {
            super.C1(rect, i3, i4);
        }
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.f1379s == 1) {
            iN2 = RecyclerView.o.n(i4, rect.height() + iG0, b0());
            int[] iArr = this.K;
            iN = RecyclerView.o.n(i3, iArr[iArr.length - 1] + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i3, rect.width() + iE0, c0());
            int[] iArr2 = this.K;
            iN2 = RecyclerView.o.n(i4, iArr2[iArr2.length - 1] + iG0, b0());
        }
        B1(iN, iN2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.f1379s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void E2(boolean z3) {
        if (z3) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.E2(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p F(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
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
    public android.view.View J0(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.z r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.J0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean L1() {
        return this.D == null && !this.I;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f1379s == 1) {
            return this.J;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return Y2(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void N1(RecyclerView.z zVar, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.J;
        for (int i3 = 0; i3 < this.J && cVar.c(zVar) && iF > 0; i3++) {
            int i4 = cVar.f1402d;
            cVar2.a(i4, Math.max(0, cVar.f1405g));
            iF -= this.O.f(i4);
            cVar.f1402d += cVar.f1403e;
        }
    }

    public final void N2(RecyclerView.v vVar, RecyclerView.z zVar, int i3, boolean z3) {
        int i4;
        int i5;
        int i6 = 0;
        int i7 = -1;
        if (z3) {
            i5 = 1;
            i7 = i3;
            i4 = 0;
        } else {
            i4 = i3 - 1;
            i5 = -1;
        }
        while (i4 != i7) {
            View view = this.L[i4];
            b bVar = (b) view.getLayoutParams();
            int iA3 = a3(vVar, zVar, h0(view));
            bVar.f1374f = iA3;
            bVar.f1373e = i6;
            i6 += iA3;
            i4 += i5;
        }
    }

    public final void O2() {
        int iJ = J();
        for (int i3 = 0; i3 < iJ; i3++) {
            b bVar = (b) I(i3).getLayoutParams();
            int iA = bVar.a();
            this.M.put(iA, bVar.f());
            this.N.put(iA, bVar.e());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, m0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.O0(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iY2 = Y2(vVar, zVar, bVar.a());
        if (this.f1379s == 0) {
            cVar.e0(c.C0036c.a(bVar.e(), bVar.f(), iY2, 1, false, false));
        } else {
            cVar.e0(c.C0036c.a(iY2, 1, bVar.e(), bVar.f(), false, false));
        }
    }

    public final void P2(int i3) {
        this.K = Q2(this.K, this.J, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i3, int i4) {
        this.O.h();
        this.O.g();
    }

    public final void R2() {
        this.M.clear();
        this.N.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    public final int S2(RecyclerView.z zVar) {
        if (J() != 0 && zVar.b() != 0) {
            T1();
            boolean zR2 = r2();
            View viewY1 = Y1(!zR2, true);
            View viewX1 = X1(!zR2, true);
            if (viewY1 != null && viewX1 != null) {
                int iB = this.O.b(h0(viewY1), this.J);
                int iB2 = this.O.b(h0(viewX1), this.J);
                int iMax = this.f1384x ? Math.max(0, ((this.O.b(zVar.b() - 1, this.J) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zR2) {
                    return Math.round((iMax * (Math.abs(this.f1381u.d(viewX1) - this.f1381u.g(viewY1)) / ((this.O.b(h0(viewX1), this.J) - this.O.b(h0(viewY1), this.J)) + 1))) + (this.f1381u.m() - this.f1381u.g(viewY1)));
                }
                return iMax;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i3, int i4, int i5) {
        this.O.h();
        this.O.g();
    }

    public final int T2(RecyclerView.z zVar) {
        if (J() != 0 && zVar.b() != 0) {
            T1();
            View viewY1 = Y1(!r2(), true);
            View viewX1 = X1(!r2(), true);
            if (viewY1 != null && viewX1 != null) {
                if (!r2()) {
                    return this.O.b(zVar.b() - 1, this.J) + 1;
                }
                int iD = this.f1381u.d(viewX1) - this.f1381u.g(viewY1);
                int iB = this.O.b(h0(viewY1), this.J);
                return (int) ((iD / ((this.O.b(h0(viewX1), this.J) - iB) + 1)) * (this.O.b(zVar.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i3, int i4) {
        this.O.h();
        this.O.g();
    }

    public final void U2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i3) {
        boolean z3 = i3 == 1;
        int iZ2 = Z2(vVar, zVar, aVar.f1391b);
        if (z3) {
            while (iZ2 > 0) {
                int i4 = aVar.f1391b;
                if (i4 <= 0) {
                    return;
                }
                int i5 = i4 - 1;
                aVar.f1391b = i5;
                iZ2 = Z2(vVar, zVar, i5);
            }
            return;
        }
        int iB = zVar.b() - 1;
        int i6 = aVar.f1391b;
        while (i6 < iB) {
            int i7 = i6 + 1;
            int iZ22 = Z2(vVar, zVar, i7);
            if (iZ22 <= iZ2) {
                break;
            }
            i6 = i7;
            iZ2 = iZ22;
        }
        aVar.f1391b = i6;
    }

    public final void V2() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i3, int i4, Object obj) {
        this.O.h();
        this.O.g();
    }

    public int W2(int i3, int i4) {
        if (this.f1379s != 1 || !q2()) {
            int[] iArr = this.K;
            return iArr[i4 + i3] - iArr[i3];
        }
        int[] iArr2 = this.K;
        int i5 = this.J;
        return iArr2[i5 - i3] - iArr2[(i5 - i3) - i4];
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (zVar.e()) {
            O2();
        }
        super.X0(vVar, zVar);
        R2();
    }

    public int X2() {
        return this.J;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void Y0(RecyclerView.z zVar) {
        super.Y0(zVar);
        this.I = false;
    }

    public final int Y2(RecyclerView.v vVar, RecyclerView.z zVar, int i3) {
        if (!zVar.e()) {
            return this.O.b(i3, this.J);
        }
        int iF = vVar.f(i3);
        if (iF != -1) {
            return this.O.b(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i3);
        return 0;
    }

    public final int Z2(RecyclerView.v vVar, RecyclerView.z zVar, int i3) {
        if (!zVar.e()) {
            return this.O.c(i3, this.J);
        }
        int i4 = this.N.get(i3, -1);
        if (i4 != -1) {
            return i4;
        }
        int iF = vVar.f(i3);
        if (iF != -1) {
            return this.O.c(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i3);
        return 0;
    }

    public final int a3(RecyclerView.v vVar, RecyclerView.z zVar, int i3) {
        if (!zVar.e()) {
            return this.O.f(i3);
        }
        int i4 = this.M.get(i3, -1);
        if (i4 != -1) {
            return i4;
        }
        int iF = vVar.f(i3);
        if (iF != -1) {
            return this.O.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i3);
        return 1;
    }

    public final void b3(float f3, int i3) {
        P2(Math.max(Math.round(f3 * this.J), i3));
    }

    public final void c3(View view, int i3, boolean z3) {
        int iK;
        int iK2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1533b;
        int i4 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i5 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iW2 = W2(bVar.f1373e, bVar.f1374f);
        if (this.f1379s == 1) {
            iK2 = RecyclerView.o.K(iW2, i3, i5, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iK = RecyclerView.o.K(this.f1381u.n(), X(), i4, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iK3 = RecyclerView.o.K(iW2, i3, i4, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iK4 = RecyclerView.o.K(this.f1381u.n(), p0(), i5, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iK = iK3;
            iK2 = iK4;
        }
        d3(view, iK2, iK, z3);
    }

    public final void d3(View view, int i3, int i4, boolean z3) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z3 ? H1(view, i3, i4, pVar) : F1(view, i3, i4, pVar)) {
            view.measure(i3, i4);
        }
    }

    public void e3(int i3) {
        if (i3 == this.J) {
            return;
        }
        this.I = true;
        if (i3 >= 1) {
            this.J = i3;
            this.O.h();
            t1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i3);
        }
    }

    public final void f3() {
        int iW;
        int iG0;
        if (p2() == 1) {
            iW = o0() - f0();
            iG0 = e0();
        } else {
            iW = W() - d0();
            iG0 = g0();
        }
        P2(iW - iG0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i3, int i4, int i5) {
        T1();
        int iM = this.f1381u.m();
        int i6 = this.f1381u.i();
        int i7 = i4 > i3 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i3 != i4) {
            View viewI = I(i3);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i5 && Z2(vVar, zVar, iH0) == 0) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.f1381u.g(viewI) < i6 && this.f1381u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i3 += i7;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f1379s == 0) {
            return this.J;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return Y2(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean m(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return this.Q ? S2(zVar) : super.r(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int s(RecyclerView.z zVar) {
        return this.Q ? T2(zVar) : super.s(zVar);
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
    public void s2(androidx.recyclerview.widget.RecyclerView.v r18, androidx.recyclerview.widget.RecyclerView.z r19, androidx.recyclerview.widget.LinearLayoutManager.c r20, androidx.recyclerview.widget.LinearLayoutManager.b r21) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.s2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return this.Q ? S2(zVar) : super.u(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void u2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i3) {
        super.u2(vVar, zVar, aVar, i3);
        f3();
        if (zVar.b() > 0 && !zVar.e()) {
            U2(vVar, zVar, aVar, i3);
        }
        V2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return this.Q ? T2(zVar) : super.v(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        f3();
        V2();
        return super.w1(i3, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i3, RecyclerView.v vVar, RecyclerView.z zVar) {
        f3();
        V2();
        return super.y1(i3, vVar, zVar);
    }

    public static class b extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1373e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1374f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1373e = -1;
            this.f1374f = 0;
        }

        public int e() {
            return this.f1373e;
        }

        public int f() {
            return this.f1374f;
        }

        public b(int i3, int i4) {
            super(i3, i4);
            this.f1373e = -1;
            this.f1374f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1373e = -1;
            this.f1374f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1373e = -1;
            this.f1374f = 0;
        }
    }

    public GridLayoutManager(Context context, int i3, int i4, boolean z3) {
        super(context, i4, z3);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        e3(i3);
    }
}
