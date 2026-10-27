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
import j0.c;

/* JADX INFO: loaded from: classes.dex */
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

    public static final class a extends c {
        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int e(int i7, int i8) {
            return i7 % i8;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i7) {
            return 1;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final SparseIntArray f2762a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final SparseIntArray f2763b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2764c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2765d = false;

        static int a(SparseIntArray sparseIntArray, int i7) {
            int size = sparseIntArray.size() - 1;
            int i8 = 0;
            while (i8 <= size) {
                int i9 = (i8 + size) >>> 1;
                if (sparseIntArray.keyAt(i9) < i7) {
                    i8 = i9 + 1;
                } else {
                    size = i9 - 1;
                }
            }
            int i10 = i8 - 1;
            if (i10 < 0 || i10 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i10);
        }

        int b(int i7, int i8) {
            if (!this.f2765d) {
                return d(i7, i8);
            }
            int i9 = this.f2763b.get(i7, -1);
            if (i9 != -1) {
                return i9;
            }
            int iD = d(i7, i8);
            this.f2763b.put(i7, iD);
            return iD;
        }

        int c(int i7, int i8) {
            if (!this.f2764c) {
                return e(i7, i8);
            }
            int i9 = this.f2762a.get(i7, -1);
            if (i9 != -1) {
                return i9;
            }
            int iE = e(i7, i8);
            this.f2762a.put(i7, iE);
            return iE;
        }

        public int d(int i7, int i8) {
            int i9;
            int i10;
            int iC;
            int iA;
            if (!this.f2765d || (iA = a(this.f2763b, i7)) == -1) {
                i9 = 0;
                i10 = 0;
                iC = 0;
            } else {
                i9 = this.f2763b.get(iA);
                i10 = iA + 1;
                iC = c(iA, i8) + f(iA);
                if (iC == i8) {
                    i9++;
                    iC = 0;
                }
            }
            int iF = f(i7);
            while (i10 < i7) {
                int iF2 = f(i10);
                iC += iF2;
                if (iC == i8) {
                    i9++;
                    iC = 0;
                } else if (iC > i8) {
                    i9++;
                    iC = iF2;
                }
                i10++;
            }
            return iC + iF > i8 ? i9 + 1 : i9;
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
                boolean r2 = r5.f2764c
                if (r2 == 0) goto L20
                android.util.SparseIntArray r2 = r5.f2762a
                int r2 = a(r2, r6)
                if (r2 < 0) goto L20
                android.util.SparseIntArray r3 = r5.f2762a
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

        public abstract int f(int i7);

        public void g() {
            this.f2763b.clear();
        }

        public void h() {
            this.f2762a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        f3(RecyclerView.o.i0(context, attributeSet, i7, i8).f2912b);
    }

    private void N2(RecyclerView.v vVar, RecyclerView.z zVar, int i7, boolean z6) {
        int i8;
        int i9;
        int i10 = 0;
        int i11 = -1;
        if (z6) {
            i9 = 1;
            i11 = i7;
            i8 = 0;
        } else {
            i8 = i7 - 1;
            i9 = -1;
        }
        while (i8 != i11) {
            View view = this.L[i8];
            b bVar = (b) view.getLayoutParams();
            int iA3 = a3(vVar, zVar, h0(view));
            bVar.f2761f = iA3;
            bVar.f2760e = i10;
            i10 += iA3;
            i8 += i9;
        }
    }

    private void O2() {
        int iJ = J();
        for (int i7 = 0; i7 < iJ; i7++) {
            b bVar = (b) I(i7).getLayoutParams();
            int iA = bVar.a();
            this.M.put(iA, bVar.f());
            this.N.put(iA, bVar.e());
        }
    }

    private void P2(int i7) {
        this.K = Q2(this.K, this.J, i7);
    }

    static int[] Q2(int[] iArr, int i7, int i8) {
        int i9;
        if (iArr == null || iArr.length != i7 + 1 || iArr[iArr.length - 1] != i8) {
            iArr = new int[i7 + 1];
        }
        int i10 = 0;
        iArr[0] = 0;
        int i11 = i8 / i7;
        int i12 = i8 % i7;
        int i13 = 0;
        for (int i14 = 1; i14 <= i7; i14++) {
            i10 += i12;
            if (i10 <= 0 || i7 - i10 >= i12) {
                i9 = i11;
            } else {
                i9 = i11 + 1;
                i10 -= i7;
            }
            i13 += i9;
            iArr[i14] = i13;
        }
        return iArr;
    }

    private void R2() {
        this.M.clear();
        this.N.clear();
    }

    private int S2(RecyclerView.z zVar) {
        if (J() != 0 && zVar.b() != 0) {
            T1();
            boolean zR2 = r2();
            View viewY1 = Y1(!zR2, true);
            View viewX1 = X1(!zR2, true);
            if (viewY1 != null && viewX1 != null) {
                int iB = this.O.b(h0(viewY1), this.J);
                int iB2 = this.O.b(h0(viewX1), this.J);
                int iMax = this.f2771x ? Math.max(0, ((this.O.b(zVar.b() - 1, this.J) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zR2) {
                    return Math.round((iMax * (Math.abs(this.f2768u.d(viewX1) - this.f2768u.g(viewY1)) / ((this.O.b(h0(viewX1), this.J) - this.O.b(h0(viewY1), this.J)) + 1))) + (this.f2768u.m() - this.f2768u.g(viewY1)));
                }
                return iMax;
            }
        }
        return 0;
    }

    private int T2(RecyclerView.z zVar) {
        if (J() != 0 && zVar.b() != 0) {
            T1();
            View viewY1 = Y1(!r2(), true);
            View viewX1 = X1(!r2(), true);
            if (viewY1 != null && viewX1 != null) {
                if (!r2()) {
                    return this.O.b(zVar.b() - 1, this.J) + 1;
                }
                int iD = this.f2768u.d(viewX1) - this.f2768u.g(viewY1);
                int iB = this.O.b(h0(viewY1), this.J);
                return (int) ((iD / ((this.O.b(h0(viewX1), this.J) - iB) + 1)) * (this.O.b(zVar.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    private void U2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i7) {
        boolean z6 = i7 == 1;
        int iZ2 = Z2(vVar, zVar, aVar.f2778b);
        if (z6) {
            while (iZ2 > 0) {
                int i8 = aVar.f2778b;
                if (i8 <= 0) {
                    return;
                }
                int i9 = i8 - 1;
                aVar.f2778b = i9;
                iZ2 = Z2(vVar, zVar, i9);
            }
            return;
        }
        int iB = zVar.b() - 1;
        int i10 = aVar.f2778b;
        while (i10 < iB) {
            int i11 = i10 + 1;
            int iZ22 = Z2(vVar, zVar, i11);
            if (iZ22 <= iZ2) {
                break;
            }
            i10 = i11;
            iZ2 = iZ22;
        }
        aVar.f2778b = i10;
    }

    private void V2() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    private int Y2(RecyclerView.v vVar, RecyclerView.z zVar, int i7) {
        if (!zVar.e()) {
            return this.O.b(i7, this.J);
        }
        int iF = vVar.f(i7);
        if (iF != -1) {
            return this.O.b(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i7);
        return 0;
    }

    private int Z2(RecyclerView.v vVar, RecyclerView.z zVar, int i7) {
        if (!zVar.e()) {
            return this.O.c(i7, this.J);
        }
        int i8 = this.N.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        int iF = vVar.f(i7);
        if (iF != -1) {
            return this.O.c(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i7);
        return 0;
    }

    private int a3(RecyclerView.v vVar, RecyclerView.z zVar, int i7) {
        if (!zVar.e()) {
            return this.O.f(i7);
        }
        int i8 = this.M.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        int iF = vVar.f(i7);
        if (iF != -1) {
            return this.O.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i7);
        return 1;
    }

    private void c3(float f7, int i7) {
        P2(Math.max(Math.round(f7 * this.J), i7));
    }

    private void d3(View view, int i7, boolean z6) {
        int iK;
        int iK2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f2916b;
        int i8 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i9 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iW2 = W2(bVar.f2760e, bVar.f2761f);
        if (this.f2766s == 1) {
            iK2 = RecyclerView.o.K(iW2, i7, i9, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iK = RecyclerView.o.K(this.f2768u.n(), X(), i8, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iK3 = RecyclerView.o.K(iW2, i7, i8, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iK4 = RecyclerView.o.K(this.f2768u.n(), p0(), i9, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iK = iK3;
            iK2 = iK4;
        }
        e3(view, iK2, iK, z6);
    }

    private void e3(View view, int i7, int i8, boolean z6) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z6 ? H1(view, i7, i8, pVar) : F1(view, i7, i8, pVar)) {
            view.measure(i7, i8);
        }
    }

    private void h3() {
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void C1(Rect rect, int i7, int i8) {
        int iN;
        int iN2;
        if (this.K == null) {
            super.C1(rect, i7, i8);
        }
        int iE0 = e0() + f0();
        int iG0 = g0() + d0();
        if (this.f2766s == 1) {
            iN2 = RecyclerView.o.n(i8, rect.height() + iG0, b0());
            int[] iArr = this.K;
            iN = RecyclerView.o.n(i7, iArr[iArr.length - 1] + iE0, c0());
        } else {
            iN = RecyclerView.o.n(i7, rect.width() + iE0, c0());
            int[] iArr2 = this.K;
            iN2 = RecyclerView.o.n(i8, iArr2[iArr2.length - 1] + iG0, b0());
        }
        B1(iN, iN2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D() {
        return this.f2766s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void E2(boolean z6) {
        if (z6) {
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
        if (this.f2766s == 1) {
            return this.J;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return Y2(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void N1(RecyclerView.z zVar, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.J;
        for (int i7 = 0; i7 < this.J && cVar.c(zVar) && iF > 0; i7++) {
            int i8 = cVar.f2789d;
            cVar2.a(i8, Math.max(0, cVar.f2792g));
            iF -= this.O.f(i8);
            cVar.f2789d += cVar.f2790e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView.v vVar, RecyclerView.z zVar, View view, j0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.O0(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iY2 = Y2(vVar, zVar, bVar.a());
        if (this.f2766s == 0) {
            cVar.S(c.C0090c.a(bVar.e(), bVar.f(), iY2, 1, false, false));
        } else {
            cVar.S(c.C0090c.a(iY2, 1, bVar.e(), bVar.f(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i7, int i8) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void T0(RecyclerView recyclerView, int i7, int i8, int i9) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i7, int i8) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView recyclerView, int i7, int i8, Object obj) {
        this.O.h();
        this.O.g();
    }

    int W2(int i7, int i8) {
        if (this.f2766s != 1 || !q2()) {
            int[] iArr = this.K;
            return iArr[i8 + i7] - iArr[i7];
        }
        int[] iArr2 = this.K;
        int i9 = this.J;
        return iArr2[i9 - i7] - iArr2[(i9 - i7) - i8];
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

    public c b3() {
        return this.O;
    }

    public void f3(int i7) {
        if (i7 == this.J) {
            return;
        }
        this.I = true;
        if (i7 >= 1) {
            this.J = i7;
            this.O.h();
            t1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i7);
        }
    }

    public void g3(c cVar) {
        this.O = cVar;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View h2(RecyclerView.v vVar, RecyclerView.z zVar, int i7, int i8, int i9) {
        T1();
        int iM = this.f2768u.m();
        int i10 = this.f2768u.i();
        int i11 = i8 > i7 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i7 != i8) {
            View viewI = I(i7);
            int iH0 = h0(viewI);
            if (iH0 >= 0 && iH0 < i9 && Z2(vVar, zVar, iH0) == 0) {
                if (((RecyclerView.p) viewI.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewI;
                    }
                } else {
                    if (this.f2768u.g(viewI) < i10 && this.f2768u.d(viewI) >= iM) {
                        return viewI;
                    }
                    if (view == null) {
                        view = viewI;
                    }
                }
            }
            i7 += i11;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int k0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.f2766s == 0) {
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
    void s2(androidx.recyclerview.widget.RecyclerView.v r18, androidx.recyclerview.widget.RecyclerView.z r19, androidx.recyclerview.widget.LinearLayoutManager.c r20, androidx.recyclerview.widget.LinearLayoutManager.b r21) {
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
    void u2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i7) {
        super.u2(vVar, zVar, aVar, i7);
        h3();
        if (zVar.b() > 0 && !zVar.e()) {
            U2(vVar, zVar, aVar, i7);
        }
        V2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int v(RecyclerView.z zVar) {
        return this.Q ? T2(zVar) : super.v(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int w1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        h3();
        V2();
        return super.w1(i7, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int y1(int i7, RecyclerView.v vVar, RecyclerView.z zVar) {
        h3();
        V2();
        return super.y1(i7, vVar, zVar);
    }

    public static class b extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2760e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2761f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2760e = -1;
            this.f2761f = 0;
        }

        public int e() {
            return this.f2760e;
        }

        public int f() {
            return this.f2761f;
        }

        public b(int i7, int i8) {
            super(i7, i8);
            this.f2760e = -1;
            this.f2761f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2760e = -1;
            this.f2761f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2760e = -1;
            this.f2761f = 0;
        }
    }

    public GridLayoutManager(Context context, int i7) {
        super(context);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        f3(i7);
    }

    public GridLayoutManager(Context context, int i7, int i8, boolean z6) {
        super(context, i8, z6);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        f3(i7);
    }
}
