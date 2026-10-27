package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.accessibility.b;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class GridLayoutManager extends LinearLayoutManager {
    boolean I;
    int J;
    int[] K;
    View[] L;
    final SparseIntArray M;
    final SparseIntArray N;
    c O;
    final Rect P;
    private boolean Q;

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a extends c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int e(int i, int i2) {
            return i % i2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i) {
            return 1;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final SparseIntArray f1076a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final SparseIntArray f1077b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1078c = false;
        private boolean d = false;

        static int a(SparseIntArray sparseIntArray, int i) {
            int size = sparseIntArray.size() - 1;
            int i2 = 0;
            while (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                if (sparseIntArray.keyAt(i3) < i) {
                    i2 = i3 + 1;
                } else {
                    size = i3 - 1;
                }
            }
            int i4 = i2 - 1;
            if (i4 < 0 || i4 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i4);
        }

        int b(int i, int i2) {
            if (!this.d) {
                return d(i, i2);
            }
            int i3 = this.f1077b.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iD = d(i, i2);
            this.f1077b.put(i, iD);
            return iD;
        }

        int c(int i, int i2) {
            if (!this.f1078c) {
                return e(i, i2);
            }
            int i3 = this.f1076a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iE = e(i, i2);
            this.f1076a.put(i, iE);
            return iE;
        }

        public int d(int i, int i2) {
            int i3;
            int i4;
            int iC;
            int iA;
            if (!this.d || (iA = a(this.f1077b, i)) == -1) {
                i3 = 0;
                i4 = 0;
                iC = 0;
            } else {
                i3 = this.f1077b.get(iA);
                i4 = iA + 1;
                iC = c(iA, i2) + f(iA);
                if (iC == i2) {
                    i3++;
                    iC = 0;
                }
            }
            int iF = f(i);
            while (i4 < i) {
                int iF2 = f(i4);
                iC += iF2;
                if (iC == i2) {
                    i3++;
                    iC = 0;
                } else if (iC > i2) {
                    i3++;
                    iC = iF2;
                }
                i4++;
            }
            return iC + iF > i2 ? i3 + 1 : i3;
        }

        public abstract int e(int i, int i2);

        public abstract int f(int i);

        public void g() {
            this.f1077b.clear();
        }

        public void h() {
            this.f1076a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        T2(RecyclerView.m.g0(context, attributeSet, i, i2).f1116b);
    }

    private void D2(RecyclerView.t tVar, RecyclerView.x xVar, int i, boolean z) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = -1;
        if (z) {
            i3 = 1;
            i5 = i;
            i2 = 0;
        } else {
            i2 = i - 1;
            i3 = -1;
        }
        while (i2 != i5) {
            View view = this.L[i2];
            b bVar = (b) view.getLayoutParams();
            int iP2 = P2(tVar, xVar, f0(view));
            bVar.f = iP2;
            bVar.e = i4;
            i4 += iP2;
            i2 += i3;
        }
    }

    private void E2() {
        int I = I();
        for (int i = 0; i < I; i++) {
            b bVar = (b) H(i).getLayoutParams();
            int iA = bVar.a();
            this.M.put(iA, bVar.f());
            this.N.put(iA, bVar.e());
        }
    }

    private void F2(int i) {
        this.K = G2(this.K, this.J, i);
    }

    static int[] G2(int[] iArr, int i, int i2) {
        int i3;
        if (iArr == null || iArr.length != i + 1 || iArr[iArr.length - 1] != i2) {
            iArr = new int[i + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i2 / i;
        int i6 = i2 % i;
        int i7 = 0;
        for (int i8 = 1; i8 <= i; i8++) {
            i4 += i6;
            if (i4 <= 0 || i - i4 >= i6) {
                i3 = i5;
            } else {
                i3 = i5 + 1;
                i4 -= i;
            }
            i7 += i3;
            iArr[i8] = i7;
        }
        return iArr;
    }

    private void H2() {
        this.M.clear();
        this.N.clear();
    }

    private int I2(RecyclerView.x xVar) {
        if (I() != 0 && xVar.b() != 0) {
            N1();
            boolean zH2 = h2();
            View viewR1 = R1(!zH2, true);
            View viewQ1 = Q1(!zH2, true);
            if (viewR1 != null && viewQ1 != null) {
                int iB = this.O.b(f0(viewR1), this.J);
                int iB2 = this.O.b(f0(viewQ1), this.J);
                int iMax = this.x ? Math.max(0, ((this.O.b(xVar.b() - 1, this.J) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zH2) {
                    return Math.round((iMax * (Math.abs(this.u.d(viewQ1) - this.u.g(viewR1)) / ((this.O.b(f0(viewQ1), this.J) - this.O.b(f0(viewR1), this.J)) + 1))) + (this.u.m() - this.u.g(viewR1)));
                }
                return iMax;
            }
        }
        return 0;
    }

    private int J2(RecyclerView.x xVar) {
        if (I() != 0 && xVar.b() != 0) {
            N1();
            View viewR1 = R1(!h2(), true);
            View viewQ1 = Q1(!h2(), true);
            if (viewR1 != null && viewQ1 != null) {
                if (!h2()) {
                    return this.O.b(xVar.b() - 1, this.J) + 1;
                }
                int iD = this.u.d(viewQ1) - this.u.g(viewR1);
                int iB = this.O.b(f0(viewR1), this.J);
                return (int) ((iD / ((this.O.b(f0(viewQ1), this.J) - iB) + 1)) * (this.O.b(xVar.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    private void K2(RecyclerView.t tVar, RecyclerView.x xVar, LinearLayoutManager.a aVar, int i) {
        boolean z = i == 1;
        int iO2 = O2(tVar, xVar, aVar.f1083b);
        if (z) {
            while (iO2 > 0) {
                int i2 = aVar.f1083b;
                if (i2 <= 0) {
                    return;
                }
                int i3 = i2 - 1;
                aVar.f1083b = i3;
                iO2 = O2(tVar, xVar, i3);
            }
            return;
        }
        int iB = xVar.b() - 1;
        int i4 = aVar.f1083b;
        while (i4 < iB) {
            int i5 = i4 + 1;
            int iO22 = O2(tVar, xVar, i5);
            if (iO22 <= iO2) {
                break;
            }
            i4 = i5;
            iO2 = iO22;
        }
        aVar.f1083b = i4;
    }

    private void L2() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    private int N2(RecyclerView.t tVar, RecyclerView.x xVar, int i) {
        if (!xVar.e()) {
            return this.O.b(i, this.J);
        }
        int iF = tVar.f(i);
        if (iF != -1) {
            return this.O.b(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int O2(RecyclerView.t tVar, RecyclerView.x xVar, int i) {
        if (!xVar.e()) {
            return this.O.c(i, this.J);
        }
        int i2 = this.N.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = tVar.f(i);
        if (iF != -1) {
            return this.O.c(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int P2(RecyclerView.t tVar, RecyclerView.x xVar, int i) {
        if (!xVar.e()) {
            return this.O.f(i);
        }
        int i2 = this.M.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = tVar.f(i);
        if (iF != -1) {
            return this.O.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    private void Q2(float f, int i) {
        F2(Math.max(Math.round(f * this.J), i));
    }

    private void R2(View view, int i, boolean z) {
        int iJ;
        int iJ2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1119b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iM2 = M2(bVar.e, bVar.f);
        if (this.s == 1) {
            iJ2 = RecyclerView.m.J(iM2, i, i3, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iJ = RecyclerView.m.J(this.u.n(), W(), i2, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iJ3 = RecyclerView.m.J(iM2, i, i2, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iJ4 = RecyclerView.m.J(this.u.n(), n0(), i3, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iJ = iJ3;
            iJ2 = iJ4;
        }
        S2(view, iJ2, iJ, z);
    }

    private void S2(View view, int i, int i2, boolean z) {
        RecyclerView.n nVar = (RecyclerView.n) view.getLayoutParams();
        if (z ? D1(view, i, i2, nVar) : B1(view, i, i2, nVar)) {
            view.measure(i, i2);
        }
    }

    private void U2() {
        int iV;
        int iE0;
        if (f2() == 1) {
            iV = m0() - d0();
            iE0 = c0();
        } else {
            iV = V() - b0();
            iE0 = e0();
        }
        F2(iV - iE0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n C() {
        return this.s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n D(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public RecyclerView.n E(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public boolean F1() {
        return this.D == null && !this.I;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d6, code lost:
    
        if (r13 == (r2 > r15)) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0107  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View H0(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.t r26, androidx.recyclerview.widget.RecyclerView.x r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.H0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$t, androidx.recyclerview.widget.RecyclerView$x):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void H1(RecyclerView.x xVar, LinearLayoutManager.c cVar, RecyclerView.m.c cVar2) {
        int iF = this.J;
        for (int i = 0; i < this.J && cVar.c(xVar) && iF > 0; i++) {
            int i2 = cVar.d;
            cVar2.a(i2, Math.max(0, cVar.g));
            iF -= this.O.f(i2);
            cVar.d += cVar.e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int M(RecyclerView.t tVar, RecyclerView.x xVar) {
        if (this.s == 1) {
            return this.J;
        }
        if (xVar.b() < 1) {
            return 0;
        }
        return N2(tVar, xVar, xVar.b() - 1) + 1;
    }

    int M2(int i, int i2) {
        if (this.s != 1 || !g2()) {
            int[] iArr = this.K;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.K;
        int i3 = this.J;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void N0(RecyclerView.t tVar, RecyclerView.x xVar, View view, androidx.core.view.accessibility.b bVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.M0(view, bVar);
            return;
        }
        b bVar2 = (b) layoutParams;
        int iN2 = N2(tVar, xVar, bVar2.a());
        if (this.s == 0) {
            bVar.L(b.c.a(bVar2.e(), bVar2.f(), iN2, 1, false, false));
        } else {
            bVar.L(b.c.a(iN2, 1, bVar2.e(), bVar2.f(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void P0(RecyclerView recyclerView, int i, int i2) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void Q0(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void R0(RecyclerView recyclerView, int i, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void S0(RecyclerView recyclerView, int i, int i2) {
        this.O.h();
        this.O.g();
    }

    public void T2(int i) {
        if (i == this.J) {
            return;
        }
        this.I = true;
        if (i >= 1) {
            this.J = i;
            this.O.h();
            q1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void U0(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void V0(RecyclerView.t tVar, RecyclerView.x xVar) {
        if (xVar.e()) {
            E2();
        }
        super.V0(tVar, xVar);
        H2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView.x xVar) {
        super.W0(xVar);
        this.I = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View Z1(RecyclerView.t tVar, RecyclerView.x xVar, boolean z, boolean z2) {
        int I;
        int I2 = I();
        int i = -1;
        int i2 = 1;
        if (z2) {
            I = I() - 1;
            i2 = -1;
        } else {
            i = I2;
            I = 0;
        }
        int iB = xVar.b();
        N1();
        int iM = this.u.m();
        int i3 = this.u.i();
        View view = null;
        View view2 = null;
        while (I != i) {
            View viewH = H(I);
            int iF0 = f0(viewH);
            if (iF0 >= 0 && iF0 < iB && O2(tVar, xVar, iF0) == 0) {
                if (((RecyclerView.n) viewH.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewH;
                    }
                } else {
                    if (this.u.g(viewH) < i3 && this.u.d(viewH) >= iM) {
                        return viewH;
                    }
                    if (view == null) {
                        view = viewH;
                    }
                }
            }
            I += i2;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public int i0(RecyclerView.t tVar, RecyclerView.x xVar) {
        if (this.s == 0) {
            return this.J;
        }
        if (xVar.b() < 1) {
            return 0;
        }
        return N2(tVar, xVar, xVar.b() - 1) + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v19 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void i2(RecyclerView.t tVar, RecyclerView.x xVar, LinearLayoutManager.c cVar, LinearLayoutManager.b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int iF;
        int iF2;
        int iF3;
        int iJ;
        int iJ2;
        View viewD;
        int iL = this.u.l();
        ?? r5 = 0;
        boolean z = iL != 1073741824;
        int i6 = I() > 0 ? this.K[this.J] : 0;
        if (z) {
            U2();
        }
        boolean z2 = cVar.e == 1;
        int iO2 = this.J;
        if (!z2) {
            iO2 = O2(tVar, xVar, cVar.d) + P2(tVar, xVar, cVar.d);
        }
        int i7 = 0;
        while (i7 < this.J && cVar.c(xVar) && iO2 > 0) {
            int i8 = cVar.d;
            int iP2 = P2(tVar, xVar, i8);
            if (iP2 > this.J) {
                throw new IllegalArgumentException("Item at position " + i8 + " requires " + iP2 + " spans but GridLayoutManager has only " + this.J + " spans.");
            }
            iO2 -= iP2;
            if (iO2 < 0 || (viewD = cVar.d(tVar)) == null) {
                break;
            }
            this.L[i7] = viewD;
            i7++;
        }
        if (i7 == 0) {
            bVar.f1086b = true;
            return;
        }
        float f = 0.0f;
        D2(tVar, xVar, i7, z2);
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            View view = this.L[i9];
            if (cVar.l == null) {
                if (z2) {
                    c(view);
                } else {
                    d(view, r5);
                }
            } else if (z2) {
                a(view);
            } else {
                b(view, r5);
            }
            i(view, this.P);
            R2(view, iL, r5);
            int iE = this.u.e(view);
            if (iE > i10) {
                i10 = iE;
            }
            float f2 = (this.u.f(view) * 1.0f) / ((b) view.getLayoutParams()).f;
            if (f2 > f) {
                f = f2;
            }
            i9++;
            r5 = 0;
        }
        if (z) {
            Q2(f, i6);
            i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                View view2 = this.L[i11];
                R2(view2, 1073741824, true);
                int iE2 = this.u.e(view2);
                if (iE2 > i10) {
                    i10 = iE2;
                }
            }
        }
        for (int i12 = 0; i12 < i7; i12++) {
            View view3 = this.L[i12];
            if (this.u.e(view3) != i10) {
                b bVar2 = (b) view3.getLayoutParams();
                Rect rect = bVar2.f1119b;
                int i13 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar2).topMargin + ((ViewGroup.MarginLayoutParams) bVar2).bottomMargin;
                int i14 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar2).leftMargin + ((ViewGroup.MarginLayoutParams) bVar2).rightMargin;
                int iM2 = M2(bVar2.e, bVar2.f);
                if (this.s == 1) {
                    iJ2 = RecyclerView.m.J(iM2, 1073741824, i14, ((ViewGroup.MarginLayoutParams) bVar2).width, false);
                    iJ = View.MeasureSpec.makeMeasureSpec(i10 - i13, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - i14, 1073741824);
                    iJ = RecyclerView.m.J(iM2, 1073741824, i13, ((ViewGroup.MarginLayoutParams) bVar2).height, false);
                    iJ2 = iMakeMeasureSpec;
                }
                S2(view3, iJ2, iJ, true);
            }
        }
        int i15 = 0;
        bVar.f1085a = i10;
        if (this.s == 1) {
            if (cVar.f == -1) {
                i3 = cVar.f1089b;
                i4 = i3 - i10;
            } else {
                int i16 = cVar.f1089b;
                i4 = i16;
                i3 = i10 + i16;
            }
            i = 0;
            i2 = 0;
        } else if (cVar.f == -1) {
            int i17 = cVar.f1089b;
            i2 = i17 - i10;
            i4 = 0;
            i = i17;
            i3 = 0;
        } else {
            int i18 = cVar.f1089b;
            i = i10 + i18;
            i2 = i18;
            i3 = 0;
            i4 = 0;
        }
        while (i15 < i7) {
            View view4 = this.L[i15];
            b bVar3 = (b) view4.getLayoutParams();
            if (this.s == 1) {
                if (g2()) {
                    int iC0 = c0() + this.K[this.J - bVar3.e];
                    iF3 = i3;
                    iF = iC0;
                    iF2 = iC0 - this.u.f(view4);
                } else {
                    int iC02 = c0() + this.K[bVar3.e];
                    iF3 = i3;
                    iF2 = iC02;
                    iF = this.u.f(view4) + iC02;
                }
                i5 = i4;
            } else {
                int iE0 = e0() + this.K[bVar3.e];
                i5 = iE0;
                iF = i;
                iF2 = i2;
                iF3 = this.u.f(view4) + iE0;
            }
            x0(view4, iF2, i5, iF, iF3);
            if (bVar3.c() || bVar3.b()) {
                bVar.f1087c = true;
            }
            bVar.d |= view4.hasFocusable();
            i15++;
            i3 = iF3;
            i = iF;
            i2 = iF2;
            i4 = i5;
        }
        Arrays.fill(this.L, (Object) null);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void k2(RecyclerView.t tVar, RecyclerView.x xVar, LinearLayoutManager.a aVar, int i) {
        super.k2(tVar, xVar, aVar, i);
        U2();
        if (xVar.b() > 0 && !xVar.e()) {
            K2(tVar, xVar, aVar, i);
        }
        L2();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public boolean l(RecyclerView.n nVar) {
        return nVar instanceof b;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int q(RecyclerView.x xVar) {
        return this.Q ? I2(xVar) : super.q(xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int r(RecyclerView.x xVar) {
        return this.Q ? J2(xVar) : super.r(xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int t(RecyclerView.x xVar) {
        return this.Q ? I2(xVar) : super.t(xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int t1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        U2();
        L2();
        return super.t1(i, tVar, xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int u(RecyclerView.x xVar) {
        return this.Q ? J2(xVar) : super.u(xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public int u1(int i, RecyclerView.t tVar, RecyclerView.x xVar) {
        U2();
        L2();
        return super.u1(i, tVar, xVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void u2(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.u2(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public void y1(Rect rect, int i, int i2) {
        int iM;
        int iM2;
        if (this.K == null) {
            super.y1(rect, i, i2);
        }
        int iC0 = c0() + d0();
        int iE0 = e0() + b0();
        if (this.s == 1) {
            iM2 = RecyclerView.m.m(i2, rect.height() + iE0, Z());
            int[] iArr = this.K;
            iM = RecyclerView.m.m(i, iArr[iArr.length - 1] + iC0, a0());
        } else {
            iM = RecyclerView.m.m(i, rect.width() + iC0, a0());
            int[] iArr2 = this.K;
            iM2 = RecyclerView.m.m(i2, iArr2[iArr2.length - 1] + iE0, Z());
        }
        x1(iM, iM2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b extends RecyclerView.n {
        int e;
        int f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = -1;
            this.f = 0;
        }

        public int e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }

        public b(int i, int i2) {
            super(i, i2);
            this.e = -1;
            this.f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.e = -1;
            this.f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = -1;
            this.f = 0;
        }
    }
}
