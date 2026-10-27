package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.f.m.b0.c;
import java.util.Arrays;

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
        public int e(int i2, int i3) {
            return i2 % i3;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i2) {
            return 1;
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final SparseIntArray f549a = new SparseIntArray();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final SparseIntArray f550b = new SparseIntArray();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f551c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f552d = false;

        public static int a(SparseIntArray sparseIntArray, int i2) {
            int size = sparseIntArray.size() - 1;
            int i3 = 0;
            while (i3 <= size) {
                int i4 = (i3 + size) >>> 1;
                if (sparseIntArray.keyAt(i4) < i2) {
                    i3 = i4 + 1;
                } else {
                    size = i4 - 1;
                }
            }
            int i5 = i3 - 1;
            if (i5 < 0 || i5 >= sparseIntArray.size()) {
                return -1;
            }
            return sparseIntArray.keyAt(i5);
        }

        public int b(int i2, int i3) {
            if (!this.f552d) {
                return d(i2, i3);
            }
            int i4 = this.f550b.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int iD = d(i2, i3);
            this.f550b.put(i2, iD);
            return iD;
        }

        public int c(int i2, int i3) {
            if (!this.f551c) {
                return e(i2, i3);
            }
            int i4 = this.f549a.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int iE = e(i2, i3);
            this.f549a.put(i2, iE);
            return iE;
        }

        public int d(int i2, int i3) {
            int i4;
            int i5;
            int iC;
            int iA;
            if (!this.f552d || (iA = a(this.f550b, i2)) == -1) {
                i4 = 0;
                i5 = 0;
                iC = 0;
            } else {
                i4 = this.f550b.get(iA);
                i5 = iA + 1;
                iC = c(iA, i3) + f(iA);
                if (iC == i3) {
                    i4++;
                    iC = 0;
                }
            }
            int iF = f(i2);
            while (i5 < i2) {
                int iF2 = f(i5);
                iC += iF2;
                if (iC == i3) {
                    i4++;
                    iC = 0;
                } else if (iC > i3) {
                    i4++;
                    iC = iF2;
                }
                i5++;
            }
            return iC + iF > i3 ? i4 + 1 : i4;
        }

        public abstract int e(int i2, int i3);

        public abstract int f(int i2);

        public void g() {
            this.f550b.clear();
        }

        public void h() {
            this.f549a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.I = false;
        this.J = -1;
        this.M = new SparseIntArray();
        this.N = new SparseIntArray();
        this.O = new a();
        this.P = new Rect();
        X2(RecyclerView.o.g0(context, attributeSet, i2, i3).f616b);
    }

    public static int[] K2(int[] iArr, int i2, int i3) {
        int i4;
        if (iArr == null || iArr.length != i2 + 1 || iArr[iArr.length - 1] != i3) {
            iArr = new int[i2 + 1];
        }
        int i5 = 0;
        iArr[0] = 0;
        int i6 = i3 / i2;
        int i7 = i3 % i2;
        int i8 = 0;
        for (int i9 = 1; i9 <= i2; i9++) {
            i5 += i7;
            if (i5 <= 0 || i2 - i5 >= i7) {
                i4 = i6;
            } else {
                i4 = i6 + 1;
                i5 -= i2;
            }
            i8 += i4;
            iArr[i9] = i8;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p C() {
        return this.s == 0 ? new b(-2, -1) : new b(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p D(Context context, AttributeSet attributeSet) {
        return new b(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p E(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new b((ViewGroup.MarginLayoutParams) layoutParams) : new b(layoutParams);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public boolean F1() {
        return this.D == null && !this.I;
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
    public android.view.View H0(android.view.View r24, int r25, androidx.recyclerview.widget.RecyclerView.v r26, androidx.recyclerview.widget.RecyclerView.z r27) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.H0(android.view.View, int, androidx.recyclerview.widget.RecyclerView$v, androidx.recyclerview.widget.RecyclerView$z):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void H1(RecyclerView.z zVar, LinearLayoutManager.c cVar, RecyclerView.o.c cVar2) {
        int iF = this.J;
        for (int i2 = 0; i2 < this.J && cVar.c(zVar) && iF > 0; i2++) {
            int i3 = cVar.f567d;
            cVar2.a(i3, Math.max(0, cVar.f570g));
            iF -= this.O.f(i3);
            cVar.f567d += cVar.f568e;
        }
    }

    public final void H2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = -1;
        if (z) {
            i4 = 1;
            i6 = i2;
            i3 = 0;
        } else {
            i3 = i2 - 1;
            i4 = -1;
        }
        while (i3 != i6) {
            View view = this.L[i3];
            b bVar = (b) view.getLayoutParams();
            int iT2 = T2(vVar, zVar, f0(view));
            bVar.f548f = iT2;
            bVar.f547e = i5;
            i5 += iT2;
            i3 += i4;
        }
    }

    public final void I2() {
        int I = I();
        for (int i2 = 0; i2 < I; i2++) {
            b bVar = (b) H(i2).getLayoutParams();
            int iA = bVar.a();
            this.M.put(iA, bVar.f());
            this.N.put(iA, bVar.e());
        }
    }

    public final void J2(int i2) {
        this.K = K2(this.K, this.J, i2);
    }

    public final void L2() {
        this.M.clear();
        this.N.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int M(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 1) {
            return this.J;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return R2(vVar, zVar, zVar.b() - 1) + 1;
    }

    public final int M2(RecyclerView.z zVar) {
        if (I() != 0 && zVar.b() != 0) {
            N1();
            boolean zL2 = l2();
            View viewS1 = S1(!zL2, true);
            View viewR1 = R1(!zL2, true);
            if (viewS1 != null && viewR1 != null) {
                int iB = this.O.b(f0(viewS1), this.J);
                int iB2 = this.O.b(f0(viewR1), this.J);
                int iMax = this.x ? Math.max(0, ((this.O.b(zVar.b() - 1, this.J) + 1) - Math.max(iB, iB2)) - 1) : Math.max(0, Math.min(iB, iB2));
                if (zL2) {
                    return Math.round((iMax * (Math.abs(this.u.d(viewR1) - this.u.g(viewS1)) / ((this.O.b(f0(viewR1), this.J) - this.O.b(f0(viewS1), this.J)) + 1))) + (this.u.m() - this.u.g(viewS1)));
                }
                return iMax;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void N0(RecyclerView.v vVar, RecyclerView.z zVar, View view, b.f.m.b0.c cVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof b)) {
            super.M0(view, cVar);
            return;
        }
        b bVar = (b) layoutParams;
        int iR2 = R2(vVar, zVar, bVar.a());
        if (this.s == 0) {
            cVar.W(c.C0030c.a(bVar.e(), bVar.f(), iR2, 1, false, false));
        } else {
            cVar.W(c.C0030c.a(iR2, 1, bVar.e(), bVar.f(), false, false));
        }
    }

    public final int N2(RecyclerView.z zVar) {
        if (I() != 0 && zVar.b() != 0) {
            N1();
            View viewS1 = S1(!l2(), true);
            View viewR1 = R1(!l2(), true);
            if (viewS1 != null && viewR1 != null) {
                if (!l2()) {
                    return this.O.b(zVar.b() - 1, this.J) + 1;
                }
                int iD = this.u.d(viewR1) - this.u.g(viewS1);
                int iB = this.O.b(f0(viewS1), this.J);
                return (int) ((iD / ((this.O.b(f0(viewR1), this.J) - iB) + 1)) * (this.O.b(zVar.b() - 1, this.J) + 1));
            }
        }
        return 0;
    }

    public final void O2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i2) {
        boolean z = i2 == 1;
        int iS2 = S2(vVar, zVar, aVar.f556b);
        if (z) {
            while (iS2 > 0) {
                int i3 = aVar.f556b;
                if (i3 <= 0) {
                    return;
                }
                int i4 = i3 - 1;
                aVar.f556b = i4;
                iS2 = S2(vVar, zVar, i4);
            }
            return;
        }
        int iB = zVar.b() - 1;
        int i5 = aVar.f556b;
        while (i5 < iB) {
            int i6 = i5 + 1;
            int iS22 = S2(vVar, zVar, i6);
            if (iS22 <= iS2) {
                break;
            }
            i5 = i6;
            iS2 = iS22;
        }
        aVar.f556b = i5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void P0(RecyclerView recyclerView, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    public final void P2() {
        View[] viewArr = this.L;
        if (viewArr == null || viewArr.length != this.J) {
            this.L = new View[this.J];
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void Q0(RecyclerView recyclerView) {
        this.O.h();
        this.O.g();
    }

    public int Q2(int i2, int i3) {
        if (this.s != 1 || !k2()) {
            int[] iArr = this.K;
            return iArr[i3 + i2] - iArr[i2];
        }
        int[] iArr2 = this.K;
        int i4 = this.J;
        return iArr2[i4 - i2] - iArr2[(i4 - i2) - i3];
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void R0(RecyclerView recyclerView, int i2, int i3, int i4) {
        this.O.h();
        this.O.g();
    }

    public final int R2(RecyclerView.v vVar, RecyclerView.z zVar, int i2) {
        if (!zVar.e()) {
            return this.O.b(i2, this.J);
        }
        int iF = vVar.f(i2);
        if (iF != -1) {
            return this.O.b(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i2);
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void S0(RecyclerView recyclerView, int i2, int i3) {
        this.O.h();
        this.O.g();
    }

    public final int S2(RecyclerView.v vVar, RecyclerView.z zVar, int i2) {
        if (!zVar.e()) {
            return this.O.c(i2, this.J);
        }
        int i3 = this.N.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int iF = vVar.f(i2);
        if (iF != -1) {
            return this.O.c(iF, this.J);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 0;
    }

    public final int T2(RecyclerView.v vVar, RecyclerView.z zVar, int i2) {
        if (!zVar.e()) {
            return this.O.f(i2);
        }
        int i3 = this.M.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int iF = vVar.f(i2);
        if (iF != -1) {
            return this.O.f(iF);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void U0(RecyclerView recyclerView, int i2, int i3, Object obj) {
        this.O.h();
        this.O.g();
    }

    public final void U2(float f2, int i2) {
        J2(Math.max(Math.round(f2 * this.J), i2));
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void V0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (zVar.e()) {
            I2();
        }
        super.V0(vVar, zVar);
        L2();
    }

    public final void V2(View view, int i2, boolean z) {
        int iJ;
        int iJ2;
        b bVar = (b) view.getLayoutParams();
        Rect rect = bVar.f620b;
        int i3 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar).topMargin + ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
        int i4 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar).leftMargin + ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
        int iQ2 = Q2(bVar.f547e, bVar.f548f);
        if (this.s == 1) {
            iJ2 = RecyclerView.o.J(iQ2, i2, i4, ((ViewGroup.MarginLayoutParams) bVar).width, false);
            iJ = RecyclerView.o.J(this.u.n(), W(), i3, ((ViewGroup.MarginLayoutParams) bVar).height, true);
        } else {
            int iJ3 = RecyclerView.o.J(iQ2, i2, i3, ((ViewGroup.MarginLayoutParams) bVar).height, false);
            int iJ4 = RecyclerView.o.J(this.u.n(), n0(), i4, ((ViewGroup.MarginLayoutParams) bVar).width, true);
            iJ = iJ3;
            iJ2 = iJ4;
        }
        W2(view, iJ2, iJ, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void W0(RecyclerView.z zVar) {
        super.W0(zVar);
        this.I = false;
    }

    public final void W2(View view, int i2, int i3, boolean z) {
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        if (z ? D1(view, i2, i3, pVar) : B1(view, i2, i3, pVar)) {
            view.measure(i2, i3);
        }
    }

    public void X2(int i2) {
        if (i2 == this.J) {
            return;
        }
        this.I = true;
        if (i2 >= 1) {
            this.J = i2;
            this.O.h();
            q1();
        } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i2);
        }
    }

    public final void Y2() {
        int iV;
        int iE0;
        if (j2() == 1) {
            iV = m0() - d0();
            iE0 = c0();
        } else {
            iV = V() - b0();
            iE0 = e0();
        }
        J2(iV - iE0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public View b2(RecyclerView.v vVar, RecyclerView.z zVar, int i2, int i3, int i4) {
        N1();
        int iM = this.u.m();
        int i5 = this.u.i();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i2 != i3) {
            View viewH = H(i2);
            int iF0 = f0(viewH);
            if (iF0 >= 0 && iF0 < i4 && S2(vVar, zVar, iF0) == 0) {
                if (((RecyclerView.p) viewH.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewH;
                    }
                } else {
                    if (this.u.g(viewH) < i5 && this.u.d(viewH) >= iM) {
                        return viewH;
                    }
                    if (view == null) {
                        view = viewH;
                    }
                }
            }
            i2 += i6;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int i0(RecyclerView.v vVar, RecyclerView.z zVar) {
        if (this.s == 0) {
            return this.J;
        }
        if (zVar.b() < 1) {
            return 0;
        }
        return R2(vVar, zVar, zVar.b() - 1) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean l(RecyclerView.p pVar) {
        return pVar instanceof b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v19 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void m2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.c cVar, LinearLayoutManager.b bVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iF;
        int iF2;
        int iF3;
        int iJ;
        int iJ2;
        View viewD;
        int iL = this.u.l();
        ?? r5 = 0;
        boolean z = iL != 1073741824;
        int i7 = I() > 0 ? this.K[this.J] : 0;
        if (z) {
            Y2();
        }
        boolean z2 = cVar.f568e == 1;
        int iS2 = this.J;
        if (!z2) {
            iS2 = S2(vVar, zVar, cVar.f567d) + T2(vVar, zVar, cVar.f567d);
        }
        int i8 = 0;
        while (i8 < this.J && cVar.c(zVar) && iS2 > 0) {
            int i9 = cVar.f567d;
            int iT2 = T2(vVar, zVar, i9);
            if (iT2 > this.J) {
                throw new IllegalArgumentException("Item at position " + i9 + " requires " + iT2 + " spans but GridLayoutManager has only " + this.J + " spans.");
            }
            iS2 -= iT2;
            if (iS2 < 0 || (viewD = cVar.d(vVar)) == null) {
                break;
            }
            this.L[i8] = viewD;
            i8++;
        }
        if (i8 == 0) {
            bVar.f561b = true;
            return;
        }
        float f2 = 0.0f;
        H2(vVar, zVar, i8, z2);
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            View view = this.L[i10];
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
            V2(view, iL, r5);
            int iE = this.u.e(view);
            if (iE > i11) {
                i11 = iE;
            }
            float f3 = (this.u.f(view) * 1.0f) / ((b) view.getLayoutParams()).f548f;
            if (f3 > f2) {
                f2 = f3;
            }
            i10++;
            r5 = 0;
        }
        if (z) {
            U2(f2, i7);
            i11 = 0;
            for (int i12 = 0; i12 < i8; i12++) {
                View view2 = this.L[i12];
                V2(view2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, true);
                int iE2 = this.u.e(view2);
                if (iE2 > i11) {
                    i11 = iE2;
                }
            }
        }
        for (int i13 = 0; i13 < i8; i13++) {
            View view3 = this.L[i13];
            if (this.u.e(view3) != i11) {
                b bVar2 = (b) view3.getLayoutParams();
                Rect rect = bVar2.f620b;
                int i14 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) bVar2).topMargin + ((ViewGroup.MarginLayoutParams) bVar2).bottomMargin;
                int i15 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) bVar2).leftMargin + ((ViewGroup.MarginLayoutParams) bVar2).rightMargin;
                int iQ2 = Q2(bVar2.f547e, bVar2.f548f);
                if (this.s == 1) {
                    iJ2 = RecyclerView.o.J(iQ2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i15, ((ViewGroup.MarginLayoutParams) bVar2).width, false);
                    iJ = View.MeasureSpec.makeMeasureSpec(i11 - i14, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i11 - i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    iJ = RecyclerView.o.J(iQ2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i14, ((ViewGroup.MarginLayoutParams) bVar2).height, false);
                    iJ2 = iMakeMeasureSpec;
                }
                W2(view3, iJ2, iJ, true);
            }
        }
        int i16 = 0;
        bVar.f560a = i11;
        if (this.s == 1) {
            if (cVar.f569f == -1) {
                i4 = cVar.f565b;
                i5 = i4 - i11;
            } else {
                int i17 = cVar.f565b;
                i5 = i17;
                i4 = i11 + i17;
            }
            i2 = 0;
            i3 = 0;
        } else if (cVar.f569f == -1) {
            int i18 = cVar.f565b;
            i3 = i18 - i11;
            i5 = 0;
            i2 = i18;
            i4 = 0;
        } else {
            int i19 = cVar.f565b;
            i2 = i11 + i19;
            i3 = i19;
            i4 = 0;
            i5 = 0;
        }
        while (i16 < i8) {
            View view4 = this.L[i16];
            b bVar3 = (b) view4.getLayoutParams();
            if (this.s == 1) {
                if (k2()) {
                    int iC0 = c0() + this.K[this.J - bVar3.f547e];
                    iF3 = i4;
                    iF = iC0;
                    iF2 = iC0 - this.u.f(view4);
                } else {
                    int iC02 = c0() + this.K[bVar3.f547e];
                    iF3 = i4;
                    iF2 = iC02;
                    iF = this.u.f(view4) + iC02;
                }
                i6 = i5;
            } else {
                int iE0 = e0() + this.K[bVar3.f547e];
                i6 = iE0;
                iF = i2;
                iF2 = i3;
                iF3 = this.u.f(view4) + iE0;
            }
            x0(view4, iF2, i6, iF, iF3);
            if (bVar3.c() || bVar3.b()) {
                bVar.f562c = true;
            }
            bVar.f563d |= view4.hasFocusable();
            i16++;
            i4 = iF3;
            i2 = iF;
            i3 = iF2;
            i5 = i6;
        }
        Arrays.fill(this.L, (Object) null);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void o2(RecyclerView.v vVar, RecyclerView.z zVar, LinearLayoutManager.a aVar, int i2) {
        super.o2(vVar, zVar, aVar, i2);
        Y2();
        if (zVar.b() > 0 && !zVar.e()) {
            O2(vVar, zVar, aVar, i2);
        }
        P2();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int q(RecyclerView.z zVar) {
        return this.Q ? M2(zVar) : super.q(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int r(RecyclerView.z zVar) {
        return this.Q ? N2(zVar) : super.r(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int t(RecyclerView.z zVar) {
        return this.Q ? M2(zVar) : super.t(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int t1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        Y2();
        P2();
        return super.t1(i2, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int u(RecyclerView.z zVar) {
        return this.Q ? N2(zVar) : super.u(zVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public int u1(int i2, RecyclerView.v vVar, RecyclerView.z zVar) {
        Y2();
        P2();
        return super.u1(i2, vVar, zVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void y1(Rect rect, int i2, int i3) {
        int iM;
        int iM2;
        if (this.K == null) {
            super.y1(rect, i2, i3);
        }
        int iC0 = c0() + d0();
        int iE0 = e0() + b0();
        if (this.s == 1) {
            iM2 = RecyclerView.o.m(i3, rect.height() + iE0, Z());
            int[] iArr = this.K;
            iM = RecyclerView.o.m(i2, iArr[iArr.length - 1] + iC0, a0());
        } else {
            iM = RecyclerView.o.m(i2, rect.width() + iC0, a0());
            int[] iArr2 = this.K;
            iM2 = RecyclerView.o.m(i3, iArr2[iArr2.length - 1] + iE0, Z());
        }
        x1(iM, iM2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void y2(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.y2(false);
    }

    public static class b extends RecyclerView.p {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f547e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f548f;

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f547e = -1;
            this.f548f = 0;
        }

        public int e() {
            return this.f547e;
        }

        public int f() {
            return this.f548f;
        }

        public b(int i2, int i3) {
            super(i2, i3);
            this.f547e = -1;
            this.f548f = 0;
        }

        public b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f547e = -1;
            this.f548f = 0;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f547e = -1;
            this.f548f = 0;
        }
    }
}
