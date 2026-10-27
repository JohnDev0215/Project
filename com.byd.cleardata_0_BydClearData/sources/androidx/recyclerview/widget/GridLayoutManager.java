package androidx.recyclerview.widget;

import a.g.k.c0.c;
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
        public int e(int i, int i2) {
            return i % i2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i) {
            return 1;
        }
    }

    public static class b extends RecyclerView.p {
        int f;
        int g;

        public b(int i, int i2) {
            super(i, i2);
            this.f = -1;
            this.g = 0;
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f = -1;
            this.g = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f = -1;
            this.g = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f = -1;
            this.g = 0;
        }

        public int y() {
            return this.f;
        }

        public int z() {
            return this.g;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final SparseIntArray f1421a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final SparseIntArray f1422b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1423c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1424d = false;

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
            if (!this.f1424d) {
                return d(i, i2);
            }
            int i3 = this.f1422b.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iD = d(i, i2);
            this.f1422b.put(i, iD);
            return iD;
        }

        int c(int i, int i2) {
            if (!this.f1423c) {
                return e(i, i2);
            }
            int i3 = this.f1421a.get(i, -1);
            if (i3 != -1) {
                return i3;
            }
            int iE = e(i, i2);
            this.f1421a.put(i, iE);
            return iE;
        }

        public int d(int i, int i2) {
            int i3;
            int i4;
            int iC;
            int iA;
            if (!this.f1424d || (iA = a(this.f1422b, i)) == -1) {
                i3 = 0;
                i4 = 0;
                iC = 0;
            } else {
                i3 = this.f1422b.get(iA);
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
            this.f1422b.clear();
        }

        public void h() {
            this.f1421a.clear();
        }
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        A3(i);
    }

    public GridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i2, z);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        A3(i);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        A3(RecyclerView.o.A0(context, attributeSet, i, i2).f1477b);
    }

    private void B3() {
        int iS0;
        int iJ;
        if (L2() == 1) {
            iS0 = G0() - b();
            iJ = l();
        } else {
            iS0 = s0() - e();
            iJ = j();
        }
        l3(iS0 - iJ);
    }

    private void j3(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, boolean z) {
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
            int iW3 = w3(vVar, a0Var, z0(view));
            bVar.g = iW3;
            bVar.f = i4;
            i4 += iW3;
            i2 += i3;
        }
    }

    private void k3() {
        int iF0 = f0();
        for (int i = 0; i < iF0; i++) {
            b bVar = (b) e0(i).getLayoutParams();
            int iU = bVar.u();
            this.M.put(iU, bVar.z());
            this.N.put(iU, bVar.y());
        }
    }

    private void l3(int i) {
        this.K = m3(this.K, this.J, i);
    }

    static int[] m3(int[] iArr, int i, int i2) {
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

    private void n3() {
        this.M.clear();
        this.N.clear();
    }

    private int o3(RecyclerView.a0 a0Var) {
        if (f0() != 0 && a0Var.b() != 0) {
            p2();
            boolean zN2 = N2();
            View viewU2 = u2(!zN2, true);
            View viewT2 = t2(!zN2, true);
            if (viewU2 != null && viewT2 != null) {
                int iB = this.O.b(z0(viewU2), this.J);
                int iB2 = this.O.b(z0(viewT2), this.J);
                int iMax = this.x ? Math.max(0, ((this.O.b(a0Var.b() - 1, this.J) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zN2) {
                    return Math.round((iMax * (Math.abs(this.u.d(viewT2) - this.u.g(viewU2)) / ((this.O.b(z0(viewT2), this.J) - this.O.b(z0(viewU2), this.J)) + 1))) + (this.u.m() - this.u.g(viewU2)));
                }
                return iMax;
            }
        }
        return 0;
    }

    private int p3(RecyclerView.a0 a0Var) {
        if (f0() != 0 && a0Var.b() != 0) {
            p2();
            View viewU2 = u2(!N2(), true);
            View viewT2 = t2(!N2(), true);
            if (viewU2 != null && viewT2 != null) {
                if (!N2()) {
                    return this.O.b(a0Var.b() - 1, this.J) + 1;
                }
                int iD = this.u.d(viewT2) - this.u.g(viewU2);
                int iB = this.O.b(z0(viewU2), this.J);
                return (int) ((iD / ((this.O.b(z0(viewT2), this.J) - iB) + 1)) * (this.O.b(a0Var.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    private void q3(RecyclerView.v vVar, RecyclerView.a0 a0Var, LinearLayoutManager.a aVar, int i) {
        boolean z = i == 1;
        int iV3 = v3(vVar, a0Var, aVar.f1426b);
        if (z) {
            while (iV3 > 0) {
                int i2 = aVar.f1426b;
                if (i2 <= 0) {
                    return;
                }
                int i3 = i2 - 1;
                aVar.f1426b = i3;
                iV3 = v3(vVar, a0Var, i3);
            }
            return;
        }
        int iB = a0Var.b() - 1;
        int i4 = aVar.f1426b;
        while (i4 < iB) {
            int i5 = i4 + 1;
            int iV32 = v3(vVar, a0Var, i5);
            if (iV32 <= iV3) {
                break;
            }
            i4 = i5;
            iV3 = iV32;
        }
        aVar.f1426b = i4;
    }

    private void r3() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    private int u3(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.e()) {
            return this.O.b(i, this.J);
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.O.b(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    private int v3(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.e()) {
            return this.O.c(i, this.J);
        }
        int i2 = this.N.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.O.c(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    private int w3(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i) {
        if (!a0Var.e()) {
            return this.O.f(i);
        }
        int i2 = this.M.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iF = vVar.f(i);
        if (iF != -1) {
            return this.O.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    private void x3(float f, int i) {
        l3(Math.max(Math.round(f * this.J), i));
    }

    private void y3(View view, int i, boolean z) {
        int iG0;
        int iG02;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f1481c;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iS3 = s3(bVar.f, bVar.g);
        if (this.s == 1) {
            iG02 = RecyclerView.o.g0(iS3, i, i3, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iG0 = RecyclerView.o.g0(this.u.n(), t0(), i2, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iG03 = RecyclerView.o.g0(iS3, i, i2, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iG04 = RecyclerView.o.g0(this.u.n(), H0(), i3, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iG0 = iG03;
            iG02 = iG04;
        }
        z3(view, iG02, iG0, z);
    }

    private void z3(View view, int i, int i2, boolean z) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z ? d2(view, i, i2, pVar) : b2(view, i, i2, pVar)) {
            view.measure(i, i2);
        }
    }

    public void A3(int i) {
        if (i == this.J) {
            return;
        }
        this.I = true;
        if (i >= 1) {
            this.J = i;
            this.O.h();
            O1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int C0(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 0) {
            return this.J;
        }
        if (a0Var.b() < 1) {
            return 0;
        }
        return u3(vVar, a0Var, a0Var.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    View D2(RecyclerView.v vVar, RecyclerView.a0 a0Var, int i, int i2, int i3) {
        p2();
        int iM = this.u.m();
        int i4 = this.u.i();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewE0 = e0(i);
            int iZ0 = z0(viewE0);
            if (iZ0 >= 0 && iZ0 < i3 && v3(vVar, a0Var, iZ0) == 0) {
                if (((RecyclerView.p) viewE0.getLayoutParams()).w()) {
                    if (view2 == null) {
                        view2 = viewE0;
                    }
                } else {
                    if (this.u.g(viewE0) < i4 && this.u.d(viewE0) >= iM) {
                        return viewE0;
                    }
                    if (view == null) {
                        view = viewE0;
                    }
                }
            }
            i += i5;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean I(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int N(RecyclerView.a0 a0Var) {
        return this.Q ? o3(a0Var) : super.N(a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int O(RecyclerView.a0 a0Var) {
        return this.Q ? p3(a0Var) : super.O(a0Var);
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
    void O2(androidx.recyclerview.widget.RecyclerView.v r18, androidx.recyclerview.widget.RecyclerView.a0 r19, androidx.recyclerview.widget.LinearLayoutManager.c r20, androidx.recyclerview.widget.LinearLayoutManager.b r21) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.O2(androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0, androidx.recyclerview.widget.LinearLayoutManager$c, androidx.recyclerview.widget.LinearLayoutManager$b):void");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int Q(RecyclerView.a0 a0Var) {
        return this.Q ? o3(a0Var) : super.Q(a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void Q2(RecyclerView.v vVar, RecyclerView.a0 a0Var, LinearLayoutManager.a aVar, int i) {
        super.Q2(vVar, a0Var, aVar, i);
        B3();
        if (a0Var.b() > 0 && !a0Var.e()) {
            q3(vVar, a0Var, aVar, i);
        }
        r3();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int R(RecyclerView.a0 a0Var) {
        return this.Q ? p3(a0Var) : super.R(a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int R1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        B3();
        r3();
        return super.R1(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int T1(int i, RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        B3();
        r3();
        return super.T1(i, vVar, a0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Y1(Rect rect, int i, int i2) {
        int iJ;
        int iJ2;
        if (this.K == null) {
            super.Y1(rect, i, i2);
        }
        int iL = l() + b();
        int iJ3 = j() + e();
        if (this.s == 1) {
            iJ2 = RecyclerView.o.J(i2, rect.height() + iJ3, x0());
            int[] iArr = this.K;
            iJ = RecyclerView.o.J(i, iArr[iArr.length - 1] + iL, y0());
        } else {
            iJ = RecyclerView.o.J(i, rect.width() + iL, y0());
            int[] iArr2 = this.K;
            iJ2 = RecyclerView.o.J(i2, iArr2[iArr2.length - 1] + iJ3, x0());
        }
        X1(iJ, iJ2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p Z() {
        return this.s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p a0(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void a3(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.a3(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p b0(ViewGroup.LayoutParams layoutParams) {
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
    public android.view.View d1(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.a0 r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.d1(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$a0):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean h2() {
        return this.D == null && !this.I;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int j0(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (this.s == 1) {
            return this.J;
        }
        if (a0Var.b() < 1) {
            return 0;
        }
        return u3(vVar, a0Var, a0Var.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void j1(RecyclerView.v vVar, RecyclerView.a0 a0Var, View view, a.g.k.c0.c cVar) {
        int iZ;
        int iY;
        int iZ2;
        boolean z;
        boolean z2;
        int iY2;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.i1(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iU3 = u3(vVar, a0Var, bVar.u());
        if (this.s == 0) {
            iY2 = bVar.y();
            iZ = bVar.z();
            iZ2 = 1;
            z = false;
            z2 = false;
            iY = iU3;
        } else {
            iZ = 1;
            iY = bVar.y();
            iZ2 = bVar.z();
            z = false;
            z2 = false;
            iY2 = iU3;
        }
        cVar.a0(c.C0014c.a(iY2, iZ, iY, iZ2, z, z2));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    void j2(RecyclerView.a0 a0Var, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.J;
        for (int i = 0; i < this.J && cVar.c(a0Var) && iF > 0; i++) {
            int i2 = cVar.f1436d;
            cVar2.a(i2, Math.max(0, cVar.g));
            iF -= this.O.f(i2);
            cVar.f1436d += cVar.e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void l1(RecyclerView recyclerView, int i, int i2) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void m1(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void n1(RecyclerView recyclerView, int i, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void o1(RecyclerView recyclerView, int i, int i2) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void q1(RecyclerView recyclerView, int i, int i2, Object obj) {
        this.O.h();
        this.O.g();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void r1(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        if (a0Var.e()) {
            k3();
        }
        super.r1(vVar, a0Var);
        n3();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void s1(RecyclerView.a0 a0Var) {
        super.s1(a0Var);
        this.I = false;
    }

    int s3(int i, int i2) {
        if (this.s != 1 || !M2()) {
            int[] iArr = this.K;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.K;
        int i3 = this.J;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public int t3() {
        return this.J;
    }
}
