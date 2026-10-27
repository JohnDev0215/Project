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
import b.g.k.c0.c;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    boolean J;
    int K;
    int[] L;
    View[] M;
    final SparseIntArray N;
    final SparseIntArray O;
    c P;
    final Rect Q;
    private boolean R;

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

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final SparseIntArray f1267a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final SparseIntArray f1268b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1269c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1270d = false;

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
            if (!this.f1270d) {
                return d(i, i2);
            }
            int i3 = this.f1268b.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iD = d(i, i2);
            this.f1268b.put(i, iD);
            return iD;
        }

        int c(int i, int i2) {
            if (!this.f1269c) {
                return e(i, i2);
            }
            int i3 = this.f1267a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iE = e(i, i2);
            this.f1267a.put(i, iE);
            return iE;
        }

        public int d(int i, int i2) {
            int i3;
            int i4;
            int iC;
            int iA;
            if (!this.f1270d || (iA = a(this.f1268b, i)) == -1) {
                i3 = 0;
                i4 = 0;
                iC = 0;
            } else {
                i3 = this.f1268b.get(iA);
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
            this.f1268b.clear();
        }

        public void h() {
            this.f1267a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.J = false;
        this.K = -1;
        this.N = new SparseIntArray();
        this.O = new SparseIntArray();
        this.P = new a();
        this.Q = new Rect();
        q3(RecyclerView.o.o0(context, attributeSet, i, i2).f1326b);
    }

    private void Z2(RecyclerView.v vVar, RecyclerView.z zVar, int i, boolean z) {
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
            View view = this.M[i2];
            b bVar = (b) view.getLayoutParams();
            int iM3 = m3(vVar, zVar, n0(view));
            bVar.g = iM3;
            bVar.f = i4;
            i4 += iM3;
            i2 += i3;
        }
    }

    private void a3() {
        int iT = T();
        for (int i = 0; i < iT; i++) {
            b bVar = (b) S(i).getLayoutParams();
            int iU = bVar.u();
            this.N.put(iU, bVar.z());
            this.O.put(iU, bVar.y());
        }
    }

    private void b3(int i) {
        this.L = c3(this.L, this.K, i);
    }

    static int[] c3(int[] iArr, int i, int i2) {
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

    private void d3() {
        this.N.clear();
        this.O.clear();
    }

    private int e3(RecyclerView.z zVar) {
        if (T() != 0 && zVar.b() != 0) {
            d2();
            boolean zD2 = D2();
            View viewJ2 = j2(!zD2, true);
            View viewI2 = i2(!zD2, true);
            if (viewJ2 != null && viewI2 != null) {
                int iB = this.P.b(n0(viewJ2), this.K);
                int iB2 = this.P.b(n0(viewI2), this.K);
                int iMax = this.y ? Math.max(0, ((this.P.b(zVar.b() - 1, this.K) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zD2) {
                    return Math.round((iMax * (Math.abs(this.v.d(viewI2) - this.v.g(viewJ2)) / ((this.P.b(n0(viewI2), this.K) - this.P.b(n0(viewJ2), this.K)) + 1))) + (this.v.m() - this.v.g(viewJ2)));
                }
                return iMax;
            }
        }
        return 0;
    }

    private int f3(RecyclerView.z zVar) {
        if (T() != 0 && zVar.b() != 0) {
            d2();
            View viewJ2 = j2(!D2(), true);
            View viewI2 = i2(!D2(), true);
            if (viewJ2 != null && viewI2 != null) {
                if (!D2()) {
                    return this.P.b(zVar.b() - 1, this.K) + 1;
                }
                int iD = this.v.d(viewI2) - this.v.g(viewJ2);
                int iB = this.P.b(n0(viewJ2), this.K);
                return (int) ((iD / ((this.P.b(n0(viewI2), this.K) - iB) + 1)) * (this.P.b(zVar.b() - 1, this.K) + 1));
            }
        }
        return 0;
    }

    private void g3(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i) {
        boolean z = i == 1;
        int iL3 = l3(vVar, zVar, aVar.f1275b);
        if (z) {
            while (iL3 > 0) {
                int i2 = aVar.f1275b;
                if (i2 <= 0) {
                    return;
                }
                int i3 = i2 - 1;
                aVar.f1275b = i3;
                iL3 = l3(vVar, zVar, i3);
            }
            return;
        }
        int iB = zVar.b() - 1;
        int i4 = aVar.f1275b;
        while (i4 < iB) {
            int i5 = i4 + 1;
            int iL32 = l3(vVar, zVar, i5);
            if (iL32 <= iL3) {
                break;
            }
            i4 = i5;
            iL3 = iL32;
        }
        aVar.f1275b = i4;
    }

    private void h3() {
        View[] viewArr = this.M;
        if (viewArr == null || viewArr.length != this.K) {
            this.M = new View[this.K];
        }
    }

    private int k3(RecyclerView.v vVar, RecyclerView.z zVar, int i) {
        if (!zVar.e()) {
            return this.P.b(i, this.K);
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.P.b(iF, this.K);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int l3(RecyclerView.v vVar, RecyclerView.z zVar, int i) {
        if (!zVar.e()) {
            return this.P.c(i, this.K);
        }
        int i2 = this.O.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.P.c(iF, this.K);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int m3(RecyclerView.v vVar, RecyclerView.z zVar, int i) {
        if (!zVar.e()) {
            return this.P.f(i);
        }
        int i2 = this.N.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.P.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    private void n3(float f, int i) {
        b3(Math.max(Math.round(f * this.K), i));
    }

    private void o3(View view, int i, boolean z) {
        int iU;
        int iU2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1330c;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iI3 = i3(bVar.f, bVar.g);
        if (this.t == 1) {
            iU2 = RecyclerView.o.U(iI3, i, i3, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iU = RecyclerView.o.U(this.v.n(), h0(), i2, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iU3 = RecyclerView.o.U(iI3, i, i2, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iU4 = RecyclerView.o.U(this.v.n(), v0(), i3, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iU = iU3;
            iU2 = iU4;
        }
        p3(view, iU2, iU, z);
    }

    private void p3(View view, int i, int i2, boolean z) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z ? R1(view, i, i2, pVar) : P1(view, i, i2, pVar)) {
            view.measure(i, i2);
        }
    }

    private void r3() {
        int iG0;
        int paddingTop;
        if (B2() == 1) {
            iG0 = u0() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            iG0 = g0() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        b3(iG0 - paddingTop);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int B(RecyclerView.z zVar) {
        return this.R ? e3(zVar) : super.B(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int C(RecyclerView.z zVar) {
        return this.R ? f3(zVar) : super.C(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int E(RecyclerView.z zVar) {
        return this.R ? e3(zVar) : super.E(zVar);
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
    void E2(androidx.recyclerview.widget.RecyclerView.v r18, androidx.recyclerview.widget.RecyclerView.z r19, androidx.recyclerview.widget.LinearLayoutManager.c r20, androidx.recyclerview.widget.LinearLayoutManager.b r21) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.E2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int F(RecyclerView.z zVar) {
        return this.R ? f3(zVar) : super.F(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int F1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        r3();
        h3();
        return super.F1(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void G2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i) {
        super.G2(vVar, zVar, aVar, i);
        r3();
        if (zVar.b() > 0 && !zVar.e()) {
            g3(vVar, zVar, aVar, i);
        }
        h3();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int H1(int i, RecyclerView.v vVar, RecyclerView.z zVar) {
        r3();
        h3();
        return super.H1(i, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void M1(Rect rect, int i, int i2) {
        int iX;
        int iX2;
        if (this.L == null) {
            super.M1(rect, i, i2);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.t == 1) {
            iX2 = RecyclerView.o.x(i2, rect.height() + paddingTop, l0());
            int[] iArr = this.L;
            iX = RecyclerView.o.x(i, iArr[iArr.length - 1] + paddingLeft, m0());
        } else {
            iX = RecyclerView.o.x(i, rect.width() + paddingLeft, m0());
            int[] iArr2 = this.L;
            iX2 = RecyclerView.o.x(i2, iArr2[iArr2.length - 1] + paddingTop, l0());
        }
        L1(iX, iX2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p N() {
        return this.t == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p O(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p P(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void Q2(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.Q2(false);
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
    public android.view.View R0(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.z r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.R0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean V1() {
        return this.E == null && !this.J;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int X(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.t == 1) {
            return this.K;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return k3(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void X0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.g.k.c0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.W0(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iK3 = k3(vVar, zVar, bVar.u());
        if (this.t == 0) {
            cVar.e0(c.C0038c.a(bVar.y(), bVar.z(), iK3, 1, false, false));
        } else {
            cVar.e0(c.C0038c.a(iK3, 1, bVar.y(), bVar.z(), false, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void X1(RecyclerView.z zVar, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.K;
        for (int i = 0; i < this.K && cVar.c(zVar) && iF > 0; i++) {
            int i2 = cVar.f1286d;
            cVar2.a(i2, Math.max(0, cVar.g));
            iF -= this.P.f(i2);
            cVar.f1286d += cVar.f1287e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Z0(RecyclerView recyclerView, int i, int i2) {
        this.P.h();
        this.P.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a1(RecyclerView recyclerView) {
        this.P.h();
        this.P.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void b1(RecyclerView recyclerView, int i, int i2, int i3) {
        this.P.h();
        this.P.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void c1(RecyclerView recyclerView, int i, int i2) {
        this.P.h();
        this.P.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e1(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.P.h();
        this.P.g();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void f1(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (zVar.e()) {
            a3();
        }
        super.f1(vVar, zVar);
        d3();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void g1(RecyclerView.z zVar) {
        super.g1(zVar);
        this.J = false;
    }

    int i3(int i, int i2) {
        if (this.t != 1 || !C2()) {
            int[] iArr = this.L;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.L;
        int i3 = this.K;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public int j3() {
        return this.K;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int q0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.t == 0) {
            return this.K;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return k3(vVar, zVar, zVar.b() - 1) + 1;
    }

    public void q3(int i) {
        if (i == this.K) {
            return;
        }
        this.J = true;
        if (i >= 1) {
            this.K = i;
            this.P.h();
            C1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View t2(RecyclerView.v vVar, RecyclerView.z zVar, int i, int i2, int i3) {
        d2();
        int iM = this.v.m();
        int i4 = this.v.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewS = S(i);
            int iN0 = n0(viewS);
            if (iN0 >= 0 && iN0 < i3 && l3(vVar, zVar, iN0) == 0) {
                if (((RecyclerView.p) viewS.getLayoutParams()).w()) {
                    if (view2 == null) {
                        view2 = viewS;
                    }
                } else {
                    if (this.v.g(viewS) < i4 && this.v.d(viewS) >= iM) {
                        return viewS;
                    }
                    if (view == null) {
                        view = viewS;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean w(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    public static class b extends RecyclerView.p {
        int f;
        int g;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f = -1;
            this.g = 0;
        }

        public int y() {
            return this.f;
        }

        public int z() {
            return this.g;
        }

        public b(int i, int i2) {
            super(i, i2);
            this.f = -1;
            this.g = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f = -1;
            this.g = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f = -1;
            this.g = 0;
        }
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.J = false;
        this.K = -1;
        this.N = new SparseIntArray();
        this.O = new SparseIntArray();
        this.P = new a();
        this.Q = new Rect();
        q3(i);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        this.J = false;
        this.K = -1;
        this.N = new SparseIntArray();
        this.O = new SparseIntArray();
        this.P = new a();
        this.Q = new Rect();
        q3(i);
    }
}
