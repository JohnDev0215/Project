package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.WeakHashMap;
import p.b01;
import p.b3;
import p.c3;
import p.ci1;
import p.cm0;
import p.cr1;
import p.dr1;
import p.f22;
import p.hu2;
import p.it2;
import p.j22;
import p.jt2;
import p.k22;
import p.of2;
import p.pf2;
import p.qa1;
import p.rf2;
import p.sf2;
import p.t81;
import p.x12;
import p.y12;
import p.z12;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends y12 implements j22 {
    public final hu2 B;
    public final int C;
    public boolean D;
    public boolean E;
    public rf2 F;
    public final Rect G;
    public final of2 H;
    public final boolean I;
    public int[] J;
    public final cm0 K;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f21p;
    public final sf2[] q;
    public final dr1 r;
    public final dr1 s;
    public final int t;
    public int u;
    public final t81 v;
    public boolean w;
    public final BitSet y;
    public boolean x = false;
    public int z = -1;
    public int A = Integer.MIN_VALUE;

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f21p = -1;
        this.w = false;
        hu2 hu2Var = new hu2(1);
        this.B = hu2Var;
        this.C = 2;
        this.G = new Rect();
        this.H = new of2(this);
        this.I = true;
        this.K = new cm0(1, this);
        x12 x12VarE = y12.E(context, attributeSet, i, i2);
        int i3 = x12VarE.a;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        c(null);
        if (i3 != this.t) {
            this.t = i3;
            dr1 dr1Var = this.r;
            this.r = this.s;
            this.s = dr1Var;
            g0();
        }
        int i4 = x12VarE.b;
        c(null);
        if (i4 != this.f21p) {
            hu2Var.d();
            g0();
            this.f21p = i4;
            this.y = new BitSet(this.f21p);
            this.q = new sf2[this.f21p];
            for (int i5 = 0; i5 < this.f21p; i5++) {
                this.q[i5] = new sf2(this, i5);
            }
            g0();
        }
        boolean z = x12VarE.c;
        c(null);
        rf2 rf2Var = this.F;
        if (rf2Var != null && rf2Var.f180p != z) {
            rf2Var.f180p = z;
        }
        this.w = z;
        g0();
        t81 t81Var = new t81();
        t81Var.a = true;
        t81Var.f = 0;
        t81Var.g = 0;
        this.v = t81Var;
        this.r = dr1.a(this, this.t);
        this.s = dr1.a(this, 1 - this.t);
    }

    public static int Y0(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean, int] */
    public final int A0(f22 f22Var, t81 t81Var, k22 k22Var) {
        sf2 sf2Var;
        ?? r6;
        int i;
        int iH;
        int iC;
        int iF;
        int iC2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        int i7 = 1;
        this.y.set(0, this.f21p, true);
        t81 t81Var2 = this.v;
        int i8 = t81Var2.i ? t81Var.e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : t81Var.e == 1 ? t81Var.g + t81Var.b : t81Var.f - t81Var.b;
        int i9 = t81Var.e;
        for (int i10 = 0; i10 < this.f21p; i10++) {
            if (!this.q[i10].a.isEmpty()) {
                X0(this.q[i10], i9, i8);
            }
        }
        int iE = this.x ? this.r.e() : this.r.f();
        boolean z = false;
        while (true) {
            int i11 = t81Var.c;
            if (((i11 < 0 || i11 >= k22Var.b()) ? i6 : i7) == 0 || (!t81Var2.i && this.y.isEmpty())) {
                break;
            }
            View view = f22Var.i(t81Var.c, Long.MAX_VALUE).a;
            t81Var.c += t81Var.d;
            pf2 pf2Var = (pf2) view.getLayoutParams();
            int iC3 = pf2Var.a.c();
            hu2 hu2Var = this.B;
            int[] iArr = (int[]) hu2Var.b;
            int i12 = (iArr == null || iC3 >= iArr.length) ? -1 : iArr[iC3];
            if (i12 == -1) {
                if (O0(t81Var.e)) {
                    i5 = this.f21p - i7;
                    i4 = -1;
                    i3 = -1;
                } else {
                    i3 = i7;
                    i4 = this.f21p;
                    i5 = i6;
                }
                sf2 sf2Var2 = null;
                if (t81Var.e == i7) {
                    int iF2 = this.r.f();
                    int i13 = Integer.MAX_VALUE;
                    while (i5 != i4) {
                        sf2 sf2Var3 = this.q[i5];
                        int iF3 = sf2Var3.f(iF2);
                        if (iF3 < i13) {
                            i13 = iF3;
                            sf2Var2 = sf2Var3;
                        }
                        i5 += i3;
                    }
                } else {
                    int iE2 = this.r.e();
                    int i14 = Integer.MIN_VALUE;
                    while (i5 != i4) {
                        sf2 sf2Var4 = this.q[i5];
                        int iH2 = sf2Var4.h(iE2);
                        if (iH2 > i14) {
                            sf2Var2 = sf2Var4;
                            i14 = iH2;
                        }
                        i5 += i3;
                    }
                }
                sf2Var = sf2Var2;
                hu2Var.e(iC3);
                ((int[]) hu2Var.b)[iC3] = sf2Var.e;
            } else {
                sf2Var = this.q[i12];
            }
            pf2Var.e = sf2Var;
            if (t81Var.e == 1) {
                r6 = 0;
                b(view, -1, false);
            } else {
                r6 = 0;
                b(view, 0, false);
            }
            if (this.t == 1) {
                i = 1;
                M0(view, y12.w(this.u, this.l, r6, ((ViewGroup.MarginLayoutParams) pf2Var).width, r6), y12.w(this.o, this.m, z() + C(), ((ViewGroup.MarginLayoutParams) pf2Var).height, true));
            } else {
                i = 1;
                M0(view, y12.w(this.n, this.l, B() + A(), ((ViewGroup.MarginLayoutParams) pf2Var).width, true), y12.w(this.u, this.m, 0, ((ViewGroup.MarginLayoutParams) pf2Var).height, false));
            }
            if (t81Var.e == i) {
                iC = sf2Var.f(iE);
                iH = this.r.c(view) + iC;
            } else {
                iH = sf2Var.h(iE);
                iC = iH - this.r.c(view);
            }
            if (t81Var.e == 1) {
                sf2 sf2Var5 = pf2Var.e;
                sf2Var5.getClass();
                pf2 pf2Var2 = (pf2) view.getLayoutParams();
                pf2Var2.e = sf2Var5;
                ArrayList arrayList = sf2Var5.a;
                arrayList.add(view);
                sf2Var5.c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    sf2Var5.b = Integer.MIN_VALUE;
                }
                if (pf2Var2.a.j() || pf2Var2.a.m()) {
                    sf2Var5.d = sf2Var5.f.r.c(view) + sf2Var5.d;
                }
            } else {
                sf2 sf2Var6 = pf2Var.e;
                sf2Var6.getClass();
                pf2 pf2Var3 = (pf2) view.getLayoutParams();
                pf2Var3.e = sf2Var6;
                ArrayList arrayList2 = sf2Var6.a;
                arrayList2.add(0, view);
                sf2Var6.b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    sf2Var6.c = Integer.MIN_VALUE;
                }
                if (pf2Var3.a.j() || pf2Var3.a.m()) {
                    sf2Var6.d = sf2Var6.f.r.c(view) + sf2Var6.d;
                }
            }
            if (L0() && this.t == 1) {
                iC2 = this.s.e() - (((this.f21p - 1) - sf2Var.e) * this.u);
                iF = iC2 - this.s.c(view);
            } else {
                iF = this.s.f() + (sf2Var.e * this.u);
                iC2 = this.s.c(view) + iF;
            }
            if (this.t == 1) {
                y12.J(view, iF, iC, iC2, iH);
            } else {
                y12.J(view, iC, iF, iH, iC2);
            }
            X0(sf2Var, t81Var2.e, i8);
            Q0(f22Var, t81Var2);
            if (t81Var2.h && view.hasFocusable()) {
                i2 = 0;
                this.y.set(sf2Var.e, false);
            } else {
                i2 = 0;
            }
            i6 = i2;
            i7 = 1;
            z = true;
        }
        int i15 = i6;
        if (!z) {
            Q0(f22Var, t81Var2);
        }
        int iF4 = t81Var2.e == -1 ? this.r.f() - I0(this.r.f()) : H0(this.r.e()) - this.r.e();
        return iF4 > 0 ? Math.min(t81Var.b, iF4) : i15;
    }

    public final View B0(boolean z) {
        int iF = this.r.f();
        int iE = this.r.e();
        View view = null;
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            int iD = this.r.d(viewU);
            int iB = this.r.b(viewU);
            if (iB > iF && iD < iE) {
                if (iB <= iE || !z) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final View C0(boolean z) {
        int iF = this.r.f();
        int iE = this.r.e();
        int iV = v();
        View view = null;
        for (int i = 0; i < iV; i++) {
            View viewU = u(i);
            int iD = this.r.d(viewU);
            if (this.r.b(viewU) > iF && iD < iE) {
                if (iD >= iF || !z) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final void D0(f22 f22Var, k22 k22Var, boolean z) {
        int iE;
        int iH0 = H0(Integer.MIN_VALUE);
        if (iH0 != Integer.MIN_VALUE && (iE = this.r.e() - iH0) > 0) {
            int i = iE - (-U0(-iE, f22Var, k22Var));
            if (!z || i <= 0) {
                return;
            }
            this.r.k(i);
        }
    }

    public final void E0(f22 f22Var, k22 k22Var, boolean z) {
        int iF;
        int iI0 = I0(Integer.MAX_VALUE);
        if (iI0 != Integer.MAX_VALUE && (iF = iI0 - this.r.f()) > 0) {
            int iU0 = iF - U0(iF, f22Var, k22Var);
            if (!z || iU0 <= 0) {
                return;
            }
            this.r.k(-iU0);
        }
    }

    @Override // p.y12
    public final int F(f22 f22Var, k22 k22Var) {
        return this.t == 0 ? this.f21p : super.F(f22Var, k22Var);
    }

    public final int F0() {
        if (v() == 0) {
            return 0;
        }
        return y12.D(u(0));
    }

    public final int G0() {
        int iV = v();
        if (iV == 0) {
            return 0;
        }
        return y12.D(u(iV - 1));
    }

    @Override // p.y12
    public final boolean H() {
        return this.C != 0;
    }

    public final int H0(int i) {
        int iF = this.q[0].f(i);
        for (int i2 = 1; i2 < this.f21p; i2++) {
            int iF2 = this.q[i2].f(i);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    public final int I0(int i) {
        int iH = this.q[0].h(i);
        for (int i2 = 1; i2 < this.f21p; i2++) {
            int iH2 = this.q[i2].h(i);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void J0(int r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r0 = r7.x
            if (r0 == 0) goto L9
            int r0 = r7.G0()
            goto Ld
        L9:
            int r0 = r7.F0()
        Ld:
            r1 = 8
            if (r10 != r1) goto L1b
            if (r8 >= r9) goto L17
            int r2 = r9 + 1
        L15:
            r3 = r8
            goto L1e
        L17:
            int r2 = r8 + 1
            r3 = r9
            goto L1e
        L1b:
            int r2 = r8 + r9
            goto L15
        L1e:
            p.hu2 r4 = r7.B
            r4.g(r3)
            r5 = 1
            if (r10 == r5) goto L37
            r6 = 2
            if (r10 == r6) goto L33
            if (r10 == r1) goto L2c
            goto L3a
        L2c:
            r4.j(r8, r5)
            r4.i(r9, r5)
            goto L3a
        L33:
            r4.j(r8, r9)
            goto L3a
        L37:
            r4.i(r8, r9)
        L3a:
            if (r2 > r0) goto L3d
            return
        L3d:
            boolean r8 = r7.x
            if (r8 == 0) goto L46
            int r8 = r7.F0()
            goto L4a
        L46:
            int r8 = r7.G0()
        L4a:
            if (r3 > r8) goto L4f
            r7.g0()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.J0(int, int, int):void");
    }

    @Override // p.y12
    public final void K(int i) {
        super.K(i);
        for (int i2 = 0; i2 < this.f21p; i2++) {
            sf2 sf2Var = this.q[i2];
            int i3 = sf2Var.b;
            if (i3 != Integer.MIN_VALUE) {
                sf2Var.b = i3 + i;
            }
            int i4 = sf2Var.c;
            if (i4 != Integer.MIN_VALUE) {
                sf2Var.c = i4 + i;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View K0() {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.K0():android.view.View");
    }

    @Override // p.y12
    public final void L(int i) {
        super.L(i);
        for (int i2 = 0; i2 < this.f21p; i2++) {
            sf2 sf2Var = this.q[i2];
            int i3 = sf2Var.b;
            if (i3 != Integer.MIN_VALUE) {
                sf2Var.b = i3 + i;
            }
            int i4 = sf2Var.c;
            if (i4 != Integer.MIN_VALUE) {
                sf2Var.c = i4 + i;
            }
        }
    }

    public final boolean L0() {
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = zt2.a;
        return jt2.d(recyclerView) == 1;
    }

    @Override // p.y12
    public final void M(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.K);
        }
        for (int i = 0; i < this.f21p; i++) {
            this.q[i].b();
        }
        recyclerView.requestLayout();
    }

    public final void M0(View view, int i, int i2) {
        RecyclerView recyclerView = this.b;
        Rect rect = this.G;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.J(view));
        }
        pf2 pf2Var = (pf2) view.getLayoutParams();
        int iY0 = Y0(i, ((ViewGroup.MarginLayoutParams) pf2Var).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) pf2Var).rightMargin + rect.right);
        int iY02 = Y0(i2, ((ViewGroup.MarginLayoutParams) pf2Var).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) pf2Var).bottomMargin + rect.bottom);
        if (p0(view, iY0, iY02, pf2Var)) {
            view.measure(iY0, iY02);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x005f  */
    @Override // p.y12
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View N(android.view.View r9, int r10, p.f22 r11, p.k22 r12) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.N(android.view.View, int, p.f22, p.k22):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x042c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void N0(p.f22 r17, p.k22 r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1120
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.N0(p.f22, p.k22, boolean):void");
    }

    @Override // p.y12
    public final void O(AccessibilityEvent accessibilityEvent) {
        super.O(accessibilityEvent);
        if (v() > 0) {
            View viewC0 = C0(false);
            View viewB0 = B0(false);
            if (viewC0 == null || viewB0 == null) {
                return;
            }
            int iD = y12.D(viewC0);
            int iD2 = y12.D(viewB0);
            if (iD < iD2) {
                accessibilityEvent.setFromIndex(iD);
                accessibilityEvent.setToIndex(iD2);
            } else {
                accessibilityEvent.setFromIndex(iD2);
                accessibilityEvent.setToIndex(iD);
            }
        }
    }

    public final boolean O0(int i) {
        if (this.t == 0) {
            return (i == -1) != this.x;
        }
        return ((i == -1) == this.x) == L0();
    }

    public final void P0(int i, k22 k22Var) {
        int iF0;
        int i2;
        if (i > 0) {
            iF0 = G0();
            i2 = 1;
        } else {
            iF0 = F0();
            i2 = -1;
        }
        t81 t81Var = this.v;
        t81Var.a = true;
        W0(iF0, k22Var);
        V0(i2);
        t81Var.c = iF0 + t81Var.d;
        t81Var.b = Math.abs(i);
    }

    @Override // p.y12
    public final void Q(f22 f22Var, k22 k22Var, View view, c3 c3Var) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof pf2)) {
            P(view, c3Var);
            return;
        }
        pf2 pf2Var = (pf2) layoutParams;
        if (this.t == 0) {
            sf2 sf2Var = pf2Var.e;
            c3Var.h(b3.a(sf2Var == null ? -1 : sf2Var.e, 1, -1, -1, false));
        } else {
            sf2 sf2Var2 = pf2Var.e;
            c3Var.h(b3.a(-1, -1, sf2Var2 == null ? -1 : sf2Var2.e, 1, false));
        }
    }

    public final void Q0(f22 f22Var, t81 t81Var) {
        if (!t81Var.a || t81Var.i) {
            return;
        }
        if (t81Var.b == 0) {
            if (t81Var.e == -1) {
                R0(t81Var.g, f22Var);
                return;
            } else {
                S0(t81Var.f, f22Var);
                return;
            }
        }
        int i = 1;
        if (t81Var.e == -1) {
            int i2 = t81Var.f;
            int iH = this.q[0].h(i2);
            while (i < this.f21p) {
                int iH2 = this.q[i].h(i2);
                if (iH2 > iH) {
                    iH = iH2;
                }
                i++;
            }
            int i3 = i2 - iH;
            R0(i3 < 0 ? t81Var.g : t81Var.g - Math.min(i3, t81Var.b), f22Var);
            return;
        }
        int i4 = t81Var.g;
        int iF = this.q[0].f(i4);
        while (i < this.f21p) {
            int iF2 = this.q[i].f(i4);
            if (iF2 < iF) {
                iF = iF2;
            }
            i++;
        }
        int i5 = iF - t81Var.g;
        S0(i5 < 0 ? t81Var.f : Math.min(i5, t81Var.b) + t81Var.f, f22Var);
    }

    @Override // p.y12
    public final void R(int i, int i2) {
        J0(i, i2, 1);
    }

    public final void R0(int i, f22 f22Var) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            if (this.r.d(viewU) < i || this.r.j(viewU) < i) {
                return;
            }
            pf2 pf2Var = (pf2) viewU.getLayoutParams();
            pf2Var.getClass();
            if (pf2Var.e.a.size() == 1) {
                return;
            }
            sf2 sf2Var = pf2Var.e;
            ArrayList arrayList = sf2Var.a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            pf2 pf2Var2 = (pf2) view.getLayoutParams();
            pf2Var2.e = null;
            if (pf2Var2.a.j() || pf2Var2.a.m()) {
                sf2Var.d -= sf2Var.f.r.c(view);
            }
            if (size == 1) {
                sf2Var.b = Integer.MIN_VALUE;
            }
            sf2Var.c = Integer.MIN_VALUE;
            d0(viewU, f22Var);
        }
    }

    @Override // p.y12
    public final void S() {
        this.B.d();
        g0();
    }

    public final void S0(int i, f22 f22Var) {
        while (v() > 0) {
            View viewU = u(0);
            if (this.r.b(viewU) > i || this.r.i(viewU) > i) {
                return;
            }
            pf2 pf2Var = (pf2) viewU.getLayoutParams();
            pf2Var.getClass();
            if (pf2Var.e.a.size() == 1) {
                return;
            }
            sf2 sf2Var = pf2Var.e;
            ArrayList arrayList = sf2Var.a;
            View view = (View) arrayList.remove(0);
            pf2 pf2Var2 = (pf2) view.getLayoutParams();
            pf2Var2.e = null;
            if (arrayList.size() == 0) {
                sf2Var.c = Integer.MIN_VALUE;
            }
            if (pf2Var2.a.j() || pf2Var2.a.m()) {
                sf2Var.d -= sf2Var.f.r.c(view);
            }
            sf2Var.b = Integer.MIN_VALUE;
            d0(viewU, f22Var);
        }
    }

    @Override // p.y12
    public final void T(int i, int i2) {
        J0(i, i2, 8);
    }

    public final void T0() {
        if (this.t == 1 || !L0()) {
            this.x = this.w;
        } else {
            this.x = !this.w;
        }
    }

    @Override // p.y12
    public final void U(int i, int i2) {
        J0(i, i2, 2);
    }

    public final int U0(int i, f22 f22Var, k22 k22Var) {
        if (v() == 0 || i == 0) {
            return 0;
        }
        P0(i, k22Var);
        t81 t81Var = this.v;
        int iA0 = A0(f22Var, t81Var, k22Var);
        if (t81Var.b >= iA0) {
            i = i < 0 ? -iA0 : iA0;
        }
        this.r.k(-i);
        this.D = this.x;
        t81Var.b = 0;
        Q0(f22Var, t81Var);
        return i;
    }

    @Override // p.y12
    public final void V(int i, int i2) {
        J0(i, i2, 4);
    }

    public final void V0(int i) {
        t81 t81Var = this.v;
        t81Var.e = i;
        t81Var.d = this.x != (i == -1) ? -1 : 1;
    }

    @Override // p.y12
    public final void W(f22 f22Var, k22 k22Var) {
        N0(f22Var, k22Var, true);
    }

    public final void W0(int i, k22 k22Var) {
        int iG;
        int iG2;
        int i2;
        int i3;
        int i4;
        int i5;
        t81 t81Var = this.v;
        boolean z = false;
        t81Var.b = 0;
        t81Var.c = i;
        qa1 qa1Var = this.e;
        if (!(qa1Var != null && qa1Var.e) || (i5 = k22Var.a) == -1) {
            iG = 0;
            iG2 = 0;
        } else {
            if (this.x == (i5 < i)) {
                iG = this.r.g();
                iG2 = 0;
            } else {
                iG2 = this.r.g();
                iG = 0;
            }
        }
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || !recyclerView.o) {
            cr1 cr1Var = (cr1) this.r;
            int i6 = cr1Var.d;
            y12 y12Var = cr1Var.a;
            switch (i6) {
                case 0:
                    i2 = y12Var.n;
                    break;
                default:
                    i2 = y12Var.o;
                    break;
            }
            t81Var.g = i2 + iG;
            t81Var.f = -iG2;
        } else {
            t81Var.f = this.r.f() - iG2;
            t81Var.g = this.r.e() + iG;
        }
        t81Var.h = false;
        t81Var.a = true;
        dr1 dr1Var = this.r;
        cr1 cr1Var2 = (cr1) dr1Var;
        int i7 = cr1Var2.d;
        y12 y12Var2 = cr1Var2.a;
        switch (i7) {
            case 0:
                i3 = y12Var2.l;
                break;
            default:
                i3 = y12Var2.m;
                break;
        }
        if (i3 == 0) {
            cr1 cr1Var3 = (cr1) dr1Var;
            int i8 = cr1Var3.d;
            y12 y12Var3 = cr1Var3.a;
            switch (i8) {
                case 0:
                    i4 = y12Var3.n;
                    break;
                default:
                    i4 = y12Var3.o;
                    break;
            }
            if (i4 == 0) {
                z = true;
            }
        }
        t81Var.i = z;
    }

    @Override // p.y12
    public final void X(k22 k22Var) {
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.F = null;
        this.H.a();
    }

    public final void X0(sf2 sf2Var, int i, int i2) {
        int i3 = sf2Var.d;
        int i4 = sf2Var.e;
        if (i != -1) {
            int i5 = sf2Var.c;
            if (i5 == Integer.MIN_VALUE) {
                sf2Var.a();
                i5 = sf2Var.c;
            }
            if (i5 - i3 >= i2) {
                this.y.set(i4, false);
                return;
            }
            return;
        }
        int i6 = sf2Var.b;
        if (i6 == Integer.MIN_VALUE) {
            View view = (View) sf2Var.a.get(0);
            pf2 pf2Var = (pf2) view.getLayoutParams();
            sf2Var.b = sf2Var.f.r.d(view);
            pf2Var.getClass();
            i6 = sf2Var.b;
        }
        if (i6 + i3 <= i2) {
            this.y.set(i4, false);
        }
    }

    @Override // p.y12
    public final void Y(Parcelable parcelable) {
        if (parcelable instanceof rf2) {
            this.F = (rf2) parcelable;
            g0();
        }
    }

    @Override // p.y12
    public final Parcelable Z() {
        int iH;
        int iF;
        int[] iArr;
        rf2 rf2Var = this.F;
        if (rf2Var != null) {
            rf2 rf2Var2 = new rf2();
            rf2Var2.k = rf2Var.k;
            rf2Var2.c = rf2Var.c;
            rf2Var2.e = rf2Var.e;
            rf2Var2.l = rf2Var.l;
            rf2Var2.m = rf2Var.m;
            rf2Var2.n = rf2Var.n;
            rf2Var2.f180p = rf2Var.f180p;
            rf2Var2.q = rf2Var.q;
            rf2Var2.r = rf2Var.r;
            rf2Var2.o = rf2Var.o;
            return rf2Var2;
        }
        rf2 rf2Var3 = new rf2();
        rf2Var3.f180p = this.w;
        rf2Var3.q = this.D;
        rf2Var3.r = this.E;
        hu2 hu2Var = this.B;
        if (hu2Var == null || (iArr = (int[]) hu2Var.b) == null) {
            rf2Var3.m = 0;
        } else {
            rf2Var3.n = iArr;
            rf2Var3.m = iArr.length;
            rf2Var3.o = (List) hu2Var.c;
        }
        if (v() > 0) {
            rf2Var3.c = this.D ? G0() : F0();
            View viewB0 = this.x ? B0(true) : C0(true);
            rf2Var3.e = viewB0 != null ? y12.D(viewB0) : -1;
            int i = this.f21p;
            rf2Var3.k = i;
            rf2Var3.l = new int[i];
            for (int i2 = 0; i2 < this.f21p; i2++) {
                if (this.D) {
                    iH = this.q[i2].f(Integer.MIN_VALUE);
                    if (iH != Integer.MIN_VALUE) {
                        iF = this.r.e();
                        iH -= iF;
                    }
                } else {
                    iH = this.q[i2].h(Integer.MIN_VALUE);
                    if (iH != Integer.MIN_VALUE) {
                        iF = this.r.f();
                        iH -= iF;
                    }
                }
                rf2Var3.l[i2] = iH;
            }
        } else {
            rf2Var3.c = -1;
            rf2Var3.e = -1;
            rf2Var3.k = 0;
        }
        return rf2Var3;
    }

    @Override // p.j22
    public final PointF a(int i) {
        int iV0 = v0(i);
        PointF pointF = new PointF();
        if (iV0 == 0) {
            return null;
        }
        if (this.t == 0) {
            pointF.x = iV0;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iV0;
        }
        return pointF;
    }

    @Override // p.y12
    public final void a0(int i) {
        if (i == 0) {
            w0();
        }
    }

    @Override // p.y12
    public final void c(String str) {
        RecyclerView recyclerView;
        if (this.F != null || (recyclerView = this.b) == null) {
            return;
        }
        recyclerView.i(str);
    }

    @Override // p.y12
    public final boolean d() {
        return this.t == 0;
    }

    @Override // p.y12
    public final boolean e() {
        return this.t == 1;
    }

    @Override // p.y12
    public final boolean f(z12 z12Var) {
        return z12Var instanceof pf2;
    }

    @Override // p.y12
    public final void h(int i, int i2, k22 k22Var, b01 b01Var) {
        t81 t81Var;
        int iF;
        int iH;
        if (this.t != 0) {
            i = i2;
        }
        if (v() == 0 || i == 0) {
            return;
        }
        P0(i, k22Var);
        int[] iArr = this.J;
        if (iArr == null || iArr.length < this.f21p) {
            this.J = new int[this.f21p];
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = this.f21p;
            t81Var = this.v;
            if (i3 >= i5) {
                break;
            }
            if (t81Var.d == -1) {
                iF = t81Var.f;
                iH = this.q[i3].h(iF);
            } else {
                iF = this.q[i3].f(t81Var.g);
                iH = t81Var.g;
            }
            int i6 = iF - iH;
            if (i6 >= 0) {
                this.J[i4] = i6;
                i4++;
            }
            i3++;
        }
        Arrays.sort(this.J, 0, i4);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = t81Var.c;
            if (i8 < 0 || i8 >= k22Var.b()) {
                return;
            }
            b01Var.a(t81Var.c, this.J[i7]);
            t81Var.c += t81Var.d;
        }
    }

    @Override // p.y12
    public final int h0(int i, f22 f22Var, k22 k22Var) {
        return U0(i, f22Var, k22Var);
    }

    @Override // p.y12
    public final void i0(int i) {
        rf2 rf2Var = this.F;
        if (rf2Var != null && rf2Var.c != i) {
            rf2Var.l = null;
            rf2Var.k = 0;
            rf2Var.c = -1;
            rf2Var.e = -1;
        }
        this.z = i;
        this.A = Integer.MIN_VALUE;
        g0();
    }

    @Override // p.y12
    public final int j(k22 k22Var) {
        return x0(k22Var);
    }

    @Override // p.y12
    public final int j0(int i, f22 f22Var, k22 k22Var) {
        return U0(i, f22Var, k22Var);
    }

    @Override // p.y12
    public final int k(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // p.y12
    public final int l(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // p.y12
    public final int m(k22 k22Var) {
        return x0(k22Var);
    }

    @Override // p.y12
    public final void m0(Rect rect, int i, int i2) {
        int iG;
        int iG2;
        int iB = B() + A();
        int iZ = z() + C();
        if (this.t == 1) {
            int iHeight = rect.height() + iZ;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = zt2.a;
            iG2 = y12.g(i2, iHeight, it2.d(recyclerView));
            iG = y12.g(i, (this.u * this.f21p) + iB, it2.e(this.b));
        } else {
            int iWidth = rect.width() + iB;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = zt2.a;
            iG = y12.g(i, iWidth, it2.e(recyclerView2));
            iG2 = y12.g(i2, (this.u * this.f21p) + iZ, it2.d(this.b));
        }
        this.b.setMeasuredDimension(iG, iG2);
    }

    @Override // p.y12
    public final int n(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // p.y12
    public final int o(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // p.y12
    public final z12 r() {
        return this.t == 0 ? new pf2(-2, -1) : new pf2(-1, -2);
    }

    @Override // p.y12
    public final z12 s(Context context, AttributeSet attributeSet) {
        return new pf2(context, attributeSet);
    }

    @Override // p.y12
    public final void s0(RecyclerView recyclerView, int i) {
        qa1 qa1Var = new qa1(recyclerView.getContext());
        qa1Var.a = i;
        t0(qa1Var);
    }

    @Override // p.y12
    public final z12 t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new pf2((ViewGroup.MarginLayoutParams) layoutParams) : new pf2(layoutParams);
    }

    @Override // p.y12
    public final boolean u0() {
        return this.F == null;
    }

    public final int v0(int i) {
        if (v() == 0) {
            return this.x ? 1 : -1;
        }
        return (i < F0()) != this.x ? -1 : 1;
    }

    public final boolean w0() {
        int iF0;
        if (v() != 0 && this.C != 0 && this.g) {
            if (this.x) {
                iF0 = G0();
                F0();
            } else {
                iF0 = F0();
                G0();
            }
            hu2 hu2Var = this.B;
            if (iF0 == 0 && K0() != null) {
                hu2Var.d();
                this.f = true;
                g0();
                return true;
            }
        }
        return false;
    }

    @Override // p.y12
    public final int x(f22 f22Var, k22 k22Var) {
        return this.t == 1 ? this.f21p : super.x(f22Var, k22Var);
    }

    public final int x0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        dr1 dr1Var = this.r;
        boolean z = this.I;
        return ci1.o(k22Var, dr1Var, C0(!z), B0(!z), this, this.I);
    }

    public final int y0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        dr1 dr1Var = this.r;
        boolean z = this.I;
        return ci1.p(k22Var, dr1Var, C0(!z), B0(!z), this, this.I, this.x);
    }

    public final int z0(k22 k22Var) {
        if (v() == 0) {
            return 0;
        }
        dr1 dr1Var = this.r;
        boolean z = this.I;
        return ci1.q(k22Var, dr1Var, C0(!z), B0(!z), this, this.I);
    }
}
