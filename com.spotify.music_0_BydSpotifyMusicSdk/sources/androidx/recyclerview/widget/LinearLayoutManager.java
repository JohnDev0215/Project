package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.WeakHashMap;
import p.b01;
import p.ci1;
import p.cr1;
import p.dr1;
import p.f22;
import p.j22;
import p.jt2;
import p.k22;
import p.la1;
import p.ma1;
import p.na1;
import p.oa1;
import p.qa1;
import p.u4;
import p.x12;
import p.y12;
import p.z12;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends y12 implements j22 {
    public final la1 A;
    public final ma1 B;
    public final int C;
    public final int[] D;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f19p;
    public na1 q;
    public cr1 r;
    public boolean s;
    public final boolean t;
    public boolean u;
    public boolean v;
    public final boolean w;
    public int x;
    public int y;
    public oa1 z;

    public LinearLayoutManager(int i) {
        this.f19p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new la1();
        this.B = new ma1();
        this.C = 2;
        this.D = new int[2];
        U0(i);
        c(null);
        if (this.t) {
            this.t = false;
            g0();
        }
    }

    public final int A0(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.f19p == 1) ? 1 : Integer.MIN_VALUE : this.f19p == 0 ? 1 : Integer.MIN_VALUE : this.f19p == 1 ? -1 : Integer.MIN_VALUE : this.f19p == 0 ? -1 : Integer.MIN_VALUE : (this.f19p != 1 && N0()) ? -1 : 1 : (this.f19p != 1 && N0()) ? 1 : -1;
    }

    public final void B0() {
        if (this.q == null) {
            na1 na1Var = new na1();
            na1Var.a = true;
            na1Var.h = 0;
            na1Var.i = 0;
            na1Var.k = null;
            this.q = na1Var;
        }
    }

    public final int C0(f22 f22Var, na1 na1Var, k22 k22Var, boolean z) {
        int i;
        int i2 = na1Var.c;
        int i3 = na1Var.g;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                na1Var.g = i3 + i2;
            }
            Q0(f22Var, na1Var);
        }
        int i4 = na1Var.c + na1Var.h;
        while (true) {
            if ((!na1Var.l && i4 <= 0) || (i = na1Var.d) < 0 || i >= k22Var.b()) {
                break;
            }
            ma1 ma1Var = this.B;
            ma1Var.a = 0;
            ma1Var.b = false;
            ma1Var.c = false;
            ma1Var.d = false;
            O0(f22Var, k22Var, na1Var, ma1Var);
            if (!ma1Var.b) {
                int i5 = na1Var.b;
                int i6 = ma1Var.a;
                na1Var.b = (na1Var.f * i6) + i5;
                if (!ma1Var.c || na1Var.k != null || !k22Var.g) {
                    na1Var.c -= i6;
                    i4 -= i6;
                }
                int i7 = na1Var.g;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + i6;
                    na1Var.g = i8;
                    int i9 = na1Var.c;
                    if (i9 < 0) {
                        na1Var.g = i8 + i9;
                    }
                    Q0(f22Var, na1Var);
                }
                if (z && ma1Var.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - na1Var.c;
    }

    public final View D0(boolean z) {
        return this.u ? H0(0, v(), z) : H0(v() - 1, -1, z);
    }

    public final View E0(boolean z) {
        return this.u ? H0(v() - 1, -1, z) : H0(0, v(), z);
    }

    public final int F0() {
        View viewH0 = H0(v() - 1, -1, false);
        if (viewH0 == null) {
            return -1;
        }
        return y12.D(viewH0);
    }

    public final View G0(int i, int i2) {
        int i3;
        int i4;
        B0();
        if (i2 <= i && i2 >= i) {
            return u(i);
        }
        if (this.r.d(u(i)) < this.r.f()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.f19p == 0 ? this.c.f(i, i2, i3, i4) : this.d.f(i, i2, i3, i4);
    }

    @Override // p.y12
    public final boolean H() {
        return true;
    }

    public final View H0(int i, int i2, boolean z) {
        B0();
        int i3 = z ? 24579 : 320;
        return this.f19p == 0 ? this.c.f(i, i2, i3, 320) : this.d.f(i, i2, i3, 320);
    }

    public View I0(f22 f22Var, k22 k22Var, int i, int i2, int i3) {
        B0();
        int iF = this.r.f();
        int iE = this.r.e();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewU = u(i);
            int iD = y12.D(viewU);
            if (iD >= 0 && iD < i3) {
                if (((z12) viewU.getLayoutParams()).a.j()) {
                    if (view2 == null) {
                        view2 = viewU;
                    }
                } else {
                    if (this.r.d(viewU) < iE && this.r.b(viewU) >= iF) {
                        return viewU;
                    }
                    if (view == null) {
                        view = viewU;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    public final int J0(int i, f22 f22Var, k22 k22Var, boolean z) {
        int iE;
        int iE2 = this.r.e() - i;
        if (iE2 <= 0) {
            return 0;
        }
        int i2 = -T0(-iE2, f22Var, k22Var);
        int i3 = i + i2;
        if (!z || (iE = this.r.e() - i3) <= 0) {
            return i2;
        }
        this.r.k(iE);
        return iE + i2;
    }

    public final int K0(int i, f22 f22Var, k22 k22Var, boolean z) {
        int iF;
        int iF2 = i - this.r.f();
        if (iF2 <= 0) {
            return 0;
        }
        int i2 = -T0(iF2, f22Var, k22Var);
        int i3 = i + i2;
        if (!z || (iF = i3 - this.r.f()) <= 0) {
            return i2;
        }
        this.r.k(-iF);
        return i2 - iF;
    }

    public final View L0() {
        return u(this.u ? 0 : v() - 1);
    }

    @Override // p.y12
    public final void M(RecyclerView recyclerView) {
    }

    public final View M0() {
        return u(this.u ? v() - 1 : 0);
    }

    @Override // p.y12
    public View N(View view, int i, f22 f22Var, k22 k22Var) {
        int iA0;
        S0();
        if (v() == 0 || (iA0 = A0(i)) == Integer.MIN_VALUE) {
            return null;
        }
        B0();
        W0(iA0, (int) (this.r.g() * 0.33333334f), false, k22Var);
        na1 na1Var = this.q;
        na1Var.g = Integer.MIN_VALUE;
        na1Var.a = false;
        C0(f22Var, na1Var, k22Var, true);
        View viewG0 = iA0 == -1 ? this.u ? G0(v() - 1, -1) : G0(0, v()) : this.u ? G0(0, v()) : G0(v() - 1, -1);
        View viewM0 = iA0 == -1 ? M0() : L0();
        if (!viewM0.hasFocusable()) {
            return viewG0;
        }
        if (viewG0 == null) {
            return null;
        }
        return viewM0;
    }

    public final boolean N0() {
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = zt2.a;
        return jt2.d(recyclerView) == 1;
    }

    @Override // p.y12
    public final void O(AccessibilityEvent accessibilityEvent) {
        super.O(accessibilityEvent);
        if (v() > 0) {
            View viewH0 = H0(0, v(), false);
            accessibilityEvent.setFromIndex(viewH0 == null ? -1 : y12.D(viewH0));
            accessibilityEvent.setToIndex(F0());
        }
    }

    public void O0(f22 f22Var, k22 k22Var, na1 na1Var, ma1 ma1Var) {
        int iA;
        int i;
        int i2;
        int iL;
        View viewB = na1Var.b(f22Var);
        if (viewB == null) {
            ma1Var.b = true;
            return;
        }
        z12 z12Var = (z12) viewB.getLayoutParams();
        if (na1Var.k == null) {
            if (this.u == (na1Var.f == -1)) {
                b(viewB, -1, false);
            } else {
                b(viewB, 0, false);
            }
        } else {
            if (this.u == (na1Var.f == -1)) {
                b(viewB, -1, true);
            } else {
                b(viewB, 0, true);
            }
        }
        z12 z12Var2 = (z12) viewB.getLayoutParams();
        Rect rectJ = this.b.J(viewB);
        int i3 = rectJ.left + rectJ.right;
        int i4 = rectJ.top + rectJ.bottom;
        int iW = y12.w(this.n, this.l, B() + A() + ((ViewGroup.MarginLayoutParams) z12Var2).leftMargin + ((ViewGroup.MarginLayoutParams) z12Var2).rightMargin + i3, ((ViewGroup.MarginLayoutParams) z12Var2).width, d());
        int iW2 = y12.w(this.o, this.m, z() + C() + ((ViewGroup.MarginLayoutParams) z12Var2).topMargin + ((ViewGroup.MarginLayoutParams) z12Var2).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) z12Var2).height, e());
        if (p0(viewB, iW, iW2, z12Var2)) {
            viewB.measure(iW, iW2);
        }
        ma1Var.a = this.r.c(viewB);
        if (this.f19p == 1) {
            if (N0()) {
                iL = this.n - B();
                iA = iL - this.r.l(viewB);
            } else {
                iA = A();
                iL = this.r.l(viewB) + iA;
            }
            if (na1Var.f == -1) {
                i = na1Var.b;
                i2 = i - ma1Var.a;
            } else {
                i2 = na1Var.b;
                i = ma1Var.a + i2;
            }
        } else {
            int iC = C();
            int iL2 = this.r.l(viewB) + iC;
            if (na1Var.f == -1) {
                int i5 = na1Var.b;
                int i6 = i5 - ma1Var.a;
                iL = i5;
                i = iL2;
                iA = i6;
                i2 = iC;
            } else {
                int i7 = na1Var.b;
                int i8 = ma1Var.a + i7;
                iA = i7;
                i = iL2;
                i2 = iC;
                iL = i8;
            }
        }
        y12.J(viewB, iA, i2, iL, i);
        if (z12Var.a.j() || z12Var.a.m()) {
            ma1Var.c = true;
        }
        ma1Var.d = viewB.hasFocusable();
    }

    public void P0(f22 f22Var, k22 k22Var, la1 la1Var, int i) {
    }

    public final void Q0(f22 f22Var, na1 na1Var) {
        int i;
        if (!na1Var.a || na1Var.l) {
            return;
        }
        int i2 = na1Var.g;
        int i3 = na1Var.i;
        if (na1Var.f != -1) {
            if (i2 < 0) {
                return;
            }
            int i4 = i2 - i3;
            int iV = v();
            if (!this.u) {
                for (int i5 = 0; i5 < iV; i5++) {
                    View viewU = u(i5);
                    if (this.r.b(viewU) > i4 || this.r.i(viewU) > i4) {
                        R0(f22Var, 0, i5);
                        return;
                    }
                }
                return;
            }
            int i6 = iV - 1;
            for (int i7 = i6; i7 >= 0; i7--) {
                View viewU2 = u(i7);
                if (this.r.b(viewU2) > i4 || this.r.i(viewU2) > i4) {
                    R0(f22Var, i6, i7);
                    return;
                }
            }
            return;
        }
        int iV2 = v();
        if (i2 < 0) {
            return;
        }
        cr1 cr1Var = this.r;
        int i8 = cr1Var.d;
        y12 y12Var = cr1Var.a;
        switch (i8) {
            case 0:
                i = y12Var.n;
                break;
            default:
                i = y12Var.o;
                break;
        }
        int i9 = (i - i2) + i3;
        if (this.u) {
            for (int i10 = 0; i10 < iV2; i10++) {
                View viewU3 = u(i10);
                if (this.r.d(viewU3) < i9 || this.r.j(viewU3) < i9) {
                    R0(f22Var, 0, i10);
                    return;
                }
            }
            return;
        }
        int i11 = iV2 - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            View viewU4 = u(i12);
            if (this.r.d(viewU4) < i9 || this.r.j(viewU4) < i9) {
                R0(f22Var, i11, i12);
                return;
            }
        }
    }

    public final void R0(f22 f22Var, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                View viewU = u(i);
                e0(i);
                f22Var.f(viewU);
                i--;
            }
            return;
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            View viewU2 = u(i3);
            e0(i3);
            f22Var.f(viewU2);
        }
    }

    public final void S0() {
        if (this.f19p == 1 || !N0()) {
            this.u = this.t;
        } else {
            this.u = !this.t;
        }
    }

    public final int T0(int i, f22 f22Var, k22 k22Var) {
        if (v() == 0 || i == 0) {
            return 0;
        }
        B0();
        this.q.a = true;
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        W0(i2, iAbs, true, k22Var);
        na1 na1Var = this.q;
        int iC0 = C0(f22Var, na1Var, k22Var, false) + na1Var.g;
        if (iC0 < 0) {
            return 0;
        }
        if (iAbs > iC0) {
            i = i2 * iC0;
        }
        this.r.k(-i);
        this.q.j = i;
        return i;
    }

    public final void U0(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(u4.e("invalid orientation:", i));
        }
        c(null);
        if (i != this.f19p || this.r == null) {
            cr1 cr1VarA = dr1.a(this, i);
            this.r = cr1VarA;
            this.A.a = cr1VarA;
            this.f19p = i;
            g0();
        }
    }

    public void V0(boolean z) {
        c(null);
        if (this.v == z) {
            return;
        }
        this.v = z;
        g0();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0188  */
    @Override // p.y12
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void W(p.f22 r18, p.k22 r19) {
        /*
            Method dump skipped, instruction units count: 1198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.W(p.f22, p.k22):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void W0(int r7, int r8, boolean r9, p.k22 r10) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.W0(int, int, boolean, p.k22):void");
    }

    @Override // p.y12
    public void X(k22 k22Var) {
        this.z = null;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.A.d();
    }

    public final void X0(int i, int i2) {
        this.q.c = this.r.e() - i2;
        na1 na1Var = this.q;
        na1Var.e = this.u ? -1 : 1;
        na1Var.d = i;
        na1Var.f = 1;
        na1Var.b = i2;
        na1Var.g = Integer.MIN_VALUE;
    }

    @Override // p.y12
    public final void Y(Parcelable parcelable) {
        if (parcelable instanceof oa1) {
            this.z = (oa1) parcelable;
            g0();
        }
    }

    public final void Y0(int i, int i2) {
        this.q.c = i2 - this.r.f();
        na1 na1Var = this.q;
        na1Var.d = i;
        na1Var.e = this.u ? 1 : -1;
        na1Var.f = -1;
        na1Var.b = i2;
        na1Var.g = Integer.MIN_VALUE;
    }

    @Override // p.y12
    public final Parcelable Z() {
        oa1 oa1Var = this.z;
        if (oa1Var != null) {
            oa1 oa1Var2 = new oa1();
            oa1Var2.c = oa1Var.c;
            oa1Var2.e = oa1Var.e;
            oa1Var2.k = oa1Var.k;
            return oa1Var2;
        }
        oa1 oa1Var3 = new oa1();
        if (v() > 0) {
            B0();
            boolean z = this.s ^ this.u;
            oa1Var3.k = z;
            if (z) {
                View viewL0 = L0();
                oa1Var3.e = this.r.e() - this.r.b(viewL0);
                oa1Var3.c = y12.D(viewL0);
            } else {
                View viewM0 = M0();
                oa1Var3.c = y12.D(viewM0);
                oa1Var3.e = this.r.d(viewM0) - this.r.f();
            }
        } else {
            oa1Var3.c = -1;
        }
        return oa1Var3;
    }

    @Override // p.j22
    public final PointF a(int i) {
        if (v() == 0) {
            return null;
        }
        int i2 = (i < y12.D(u(0))) != this.u ? -1 : 1;
        return this.f19p == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    @Override // p.y12
    public final void c(String str) {
        RecyclerView recyclerView;
        if (this.z != null || (recyclerView = this.b) == null) {
            return;
        }
        recyclerView.i(str);
    }

    @Override // p.y12
    public final boolean d() {
        return this.f19p == 0;
    }

    @Override // p.y12
    public final boolean e() {
        return this.f19p == 1;
    }

    @Override // p.y12
    public final void h(int i, int i2, k22 k22Var, b01 b01Var) {
        if (this.f19p != 0) {
            i = i2;
        }
        if (v() == 0 || i == 0) {
            return;
        }
        B0();
        W0(i > 0 ? 1 : -1, Math.abs(i), true, k22Var);
        w0(k22Var, this.q, b01Var);
    }

    @Override // p.y12
    public int h0(int i, f22 f22Var, k22 k22Var) {
        if (this.f19p == 1) {
            return 0;
        }
        return T0(i, f22Var, k22Var);
    }

    @Override // p.y12
    public final void i(int i, b01 b01Var) {
        boolean z;
        int i2;
        oa1 oa1Var = this.z;
        if (oa1Var == null || (i2 = oa1Var.c) < 0) {
            S0();
            z = this.u;
            i2 = this.x;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = oa1Var.k;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.C && i2 >= 0 && i2 < i; i4++) {
            b01Var.a(i2, 0);
            i2 += i3;
        }
    }

    @Override // p.y12
    public final void i0(int i) {
        this.x = i;
        this.y = Integer.MIN_VALUE;
        oa1 oa1Var = this.z;
        if (oa1Var != null) {
            oa1Var.c = -1;
        }
        g0();
    }

    @Override // p.y12
    public final int j(k22 k22Var) {
        return x0(k22Var);
    }

    @Override // p.y12
    public int j0(int i, f22 f22Var, k22 k22Var) {
        if (this.f19p == 0) {
            return 0;
        }
        return T0(i, f22Var, k22Var);
    }

    @Override // p.y12
    public int k(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // p.y12
    public int l(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // p.y12
    public final int m(k22 k22Var) {
        return x0(k22Var);
    }

    @Override // p.y12
    public int n(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // p.y12
    public int o(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // p.y12
    public final View q(int i) {
        int iV = v();
        if (iV == 0) {
            return null;
        }
        int iD = i - y12.D(u(0));
        if (iD >= 0 && iD < iV) {
            View viewU = u(iD);
            if (y12.D(viewU) == i) {
                return viewU;
            }
        }
        return super.q(i);
    }

    @Override // p.y12
    public final boolean q0() {
        if (this.m == 1073741824 || this.l == 1073741824) {
            return false;
        }
        int iV = v();
        for (int i = 0; i < iV; i++) {
            ViewGroup.LayoutParams layoutParams = u(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // p.y12
    public z12 r() {
        return new z12(-2, -2);
    }

    @Override // p.y12
    public void s0(RecyclerView recyclerView, int i) {
        qa1 qa1Var = new qa1(recyclerView.getContext());
        qa1Var.a = i;
        t0(qa1Var);
    }

    @Override // p.y12
    public boolean u0() {
        return this.z == null && this.s == this.v;
    }

    public void v0(k22 k22Var, int[] iArr) {
        int i;
        int iG = k22Var.a != -1 ? this.r.g() : 0;
        if (this.q.f == -1) {
            i = 0;
        } else {
            i = iG;
            iG = 0;
        }
        iArr[0] = iG;
        iArr[1] = i;
    }

    public void w0(k22 k22Var, na1 na1Var, b01 b01Var) {
        int i = na1Var.d;
        if (i < 0 || i >= k22Var.b()) {
            return;
        }
        b01Var.a(i, Math.max(0, na1Var.g));
    }

    public final int x0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        B0();
        cr1 cr1Var = this.r;
        boolean z = !this.w;
        return ci1.o(k22Var, cr1Var, E0(z), D0(z), this, this.w);
    }

    public final int y0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        B0();
        cr1 cr1Var = this.r;
        boolean z = !this.w;
        return ci1.p(k22Var, cr1Var, E0(z), D0(z), this, this.w, this.u);
    }

    public final int z0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        B0();
        cr1 cr1Var = this.r;
        boolean z = !this.w;
        return ci1.q(k22Var, cr1Var, E0(z), D0(z), this, this.w);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f19p = 1;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = true;
        this.x = -1;
        this.y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new la1();
        this.B = new ma1();
        this.C = 2;
        this.D = new int[2];
        x12 x12VarE = y12.E(context, attributeSet, i, i2);
        U0(x12VarE.a);
        boolean z = x12VarE.c;
        c(null);
        if (z != this.t) {
            this.t = z;
            g0();
        }
        V0(x12VarE.d);
    }
}
