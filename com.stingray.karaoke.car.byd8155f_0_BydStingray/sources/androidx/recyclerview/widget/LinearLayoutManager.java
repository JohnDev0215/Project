package androidx.recyclerview.widget;

import F1.H;
import T1.f;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.bydauto.ac.tsimpl.a;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import h3.x;
import h6.m;
import k3.C1648i0;
import s3.AbstractC2367F;
import s3.C2366E;
import s3.C2368G;
import s3.C2377P;
import s3.C2393m;
import s3.C2398r;
import s3.C2399s;
import s3.C2400t;
import s3.InterfaceC2376O;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends AbstractC2367F implements InterfaceC2376O {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final x f12076A;
    public final C2398r B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final int f12077C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int[] f12078D;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f12079p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C2399s f12080q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public f f12081r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12082s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f12083t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f12084u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f12085v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final boolean f12086w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f12087x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f12088y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C2400t f12089z;

    public LinearLayoutManager(int i7) {
        this.f12079p = 1;
        this.f12083t = false;
        this.f12084u = false;
        this.f12085v = false;
        this.f12086w = true;
        this.f12087x = -1;
        this.f12088y = Integer.MIN_VALUE;
        this.f12089z = null;
        this.f12076A = new x();
        this.B = new C2398r();
        this.f12077C = 2;
        this.f12078D = new int[2];
        U0(i7);
        c(null);
        if (this.f12083t) {
            this.f12083t = false;
            i0();
        }
    }

    public final int A0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        C0();
        f fVar = this.f12081r;
        boolean z6 = !this.f12086w;
        return m.l(c2377p, fVar, F0(z6), E0(z6), this, this.f12086w);
    }

    public final int B0(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 17 ? i7 != 33 ? i7 != 66 ? (i7 == 130 && this.f12079p == 1) ? 1 : Integer.MIN_VALUE : this.f12079p == 0 ? 1 : Integer.MIN_VALUE : this.f12079p == 1 ? -1 : Integer.MIN_VALUE : this.f12079p == 0 ? -1 : Integer.MIN_VALUE : (this.f12079p != 1 && N0()) ? -1 : 1 : (this.f12079p != 1 && N0()) ? 1 : -1;
    }

    public final void C0() {
        if (this.f12080q == null) {
            C2399s c2399s = new C2399s();
            c2399s.f22340a = true;
            c2399s.f22345h = 0;
            c2399s.f22346i = 0;
            c2399s.f22348k = null;
            this.f12080q = c2399s;
        }
    }

    public final int D0(C1648i0 c1648i0, C2399s c2399s, C2377P c2377p, boolean z6) {
        int i7;
        int i10 = c2399s.c;
        int i11 = c2399s.f22344g;
        if (i11 != Integer.MIN_VALUE) {
            if (i10 < 0) {
                c2399s.f22344g = i11 + i10;
            }
            Q0(c1648i0, c2399s);
        }
        int i12 = c2399s.c + c2399s.f22345h;
        while (true) {
            if ((!c2399s.l && i12 <= 0) || (i7 = c2399s.d) < 0 || i7 >= c2377p.b()) {
                break;
            }
            C2398r c2398r = this.B;
            c2398r.f22338a = 0;
            c2398r.f22339b = false;
            c2398r.c = false;
            c2398r.d = false;
            O0(c1648i0, c2377p, c2399s, c2398r);
            if (!c2398r.f22339b) {
                int i13 = c2399s.f22341b;
                int i14 = c2398r.f22338a;
                c2399s.f22341b = (c2399s.f22343f * i14) + i13;
                if (!c2398r.c || c2399s.f22348k != null || !c2377p.f22206g) {
                    c2399s.c -= i14;
                    i12 -= i14;
                }
                int i15 = c2399s.f22344g;
                if (i15 != Integer.MIN_VALUE) {
                    int i16 = i15 + i14;
                    c2399s.f22344g = i16;
                    int i17 = c2399s.c;
                    if (i17 < 0) {
                        c2399s.f22344g = i16 + i17;
                    }
                    Q0(c1648i0, c2399s);
                }
                if (z6 && c2398r.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i10 - c2399s.c;
    }

    public final View E0(boolean z6) {
        return this.f12084u ? H0(0, v(), z6) : H0(v() - 1, -1, z6);
    }

    public final View F0(boolean z6) {
        return this.f12084u ? H0(v() - 1, -1, z6) : H0(0, v(), z6);
    }

    public final View G0(int i7, int i10) {
        int i11;
        int i12;
        C0();
        if (i10 <= i7 && i10 >= i7) {
            return u(i7);
        }
        if (this.f12081r.e(u(i7)) < this.f12081r.k()) {
            i11 = 16644;
            i12 = 16388;
        } else {
            i11 = 4161;
            i12 = 4097;
        }
        return this.f12079p == 0 ? this.c.x(i7, i10, i11, i12) : this.d.x(i7, i10, i11, i12);
    }

    @Override // s3.AbstractC2367F
    public final boolean H() {
        return true;
    }

    public final View H0(int i7, int i10, boolean z6) {
        C0();
        int i11 = z6 ? 24579 : 320;
        return this.f12079p == 0 ? this.c.x(i7, i10, i11, 320) : this.d.x(i7, i10, i11, 320);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View I0(k3.C1648i0 r17, s3.C2377P r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r16.C0()
            int r1 = r16.v()
            r2 = 0
            r3 = 1
            if (r20 == 0) goto L15
            int r1 = r16.v()
            int r1 = r1 - r3
            r4 = -1
            r5 = r4
            goto L18
        L15:
            r4 = r1
            r1 = r2
            r5 = r3
        L18:
            int r6 = r18.b()
            T1.f r7 = r0.f12081r
            int r7 = r7.k()
            T1.f r8 = r0.f12081r
            int r8 = r8.g()
            r9 = 0
            r10 = r9
            r11 = r10
        L2b:
            if (r1 == r4) goto L7c
            android.view.View r12 = r0.u(r1)
            int r13 = s3.AbstractC2367F.D(r12)
            T1.f r14 = r0.f12081r
            int r14 = r14.e(r12)
            T1.f r15 = r0.f12081r
            int r15 = r15.b(r12)
            if (r13 < 0) goto L7a
            if (r13 >= r6) goto L7a
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            s3.G r13 = (s3.C2368G) r13
            s3.U r13 = r13.f22190a
            boolean r13 = r13.i()
            if (r13 == 0) goto L57
            if (r11 != 0) goto L7a
            r11 = r12
            goto L7a
        L57:
            if (r15 > r7) goto L5d
            if (r14 >= r7) goto L5d
            r13 = r3
            goto L5e
        L5d:
            r13 = r2
        L5e:
            if (r14 < r8) goto L64
            if (r15 <= r8) goto L64
            r14 = r3
            goto L65
        L64:
            r14 = r2
        L65:
            if (r13 != 0) goto L6b
            if (r14 == 0) goto L6a
            goto L6b
        L6a:
            return r12
        L6b:
            if (r19 == 0) goto L73
            if (r14 == 0) goto L70
            goto L75
        L70:
            if (r9 != 0) goto L7a
            goto L79
        L73:
            if (r13 == 0) goto L77
        L75:
            r10 = r12
            goto L7a
        L77:
            if (r9 != 0) goto L7a
        L79:
            r9 = r12
        L7a:
            int r1 = r1 + r5
            goto L2b
        L7c:
            if (r9 == 0) goto L7f
            goto L84
        L7f:
            if (r10 == 0) goto L83
            r9 = r10
            goto L84
        L83:
            r9 = r11
        L84:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.I0(k3.i0, s3.P, boolean, boolean):android.view.View");
    }

    public final int J0(int i7, C1648i0 c1648i0, C2377P c2377p, boolean z6) {
        int iG;
        int iG2 = this.f12081r.g() - i7;
        if (iG2 <= 0) {
            return 0;
        }
        int i10 = -T0(-iG2, c1648i0, c2377p);
        int i11 = i7 + i10;
        if (!z6 || (iG = this.f12081r.g() - i11) <= 0) {
            return i10;
        }
        this.f12081r.p(iG);
        return iG + i10;
    }

    public final int K0(int i7, C1648i0 c1648i0, C2377P c2377p, boolean z6) {
        int iK;
        int iK2 = i7 - this.f12081r.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i10 = -T0(iK2, c1648i0, c2377p);
        int i11 = i7 + i10;
        if (!z6 || (iK = i11 - this.f12081r.k()) <= 0) {
            return i10;
        }
        this.f12081r.p(-iK);
        return i10 - iK;
    }

    public final View L0() {
        return u(this.f12084u ? 0 : v() - 1);
    }

    public final View M0() {
        return u(this.f12084u ? v() - 1 : 0);
    }

    @Override // s3.AbstractC2367F
    public final void N(RecyclerView recyclerView) {
    }

    public final boolean N0() {
        RecyclerView recyclerView = this.f22180b;
        int[] iArr = H.f2303a;
        return recyclerView.getLayoutDirection() == 1;
    }

    @Override // s3.AbstractC2367F
    public View O(View view, int i7, C1648i0 c1648i0, C2377P c2377p) {
        int iB0;
        S0();
        if (v() == 0 || (iB0 = B0(i7)) == Integer.MIN_VALUE) {
            return null;
        }
        C0();
        W0(iB0, (int) (this.f12081r.l() * 0.33333334f), false, c2377p);
        C2399s c2399s = this.f12080q;
        c2399s.f22344g = Integer.MIN_VALUE;
        c2399s.f22340a = false;
        D0(c1648i0, c2399s, c2377p, true);
        View viewG0 = iB0 == -1 ? this.f12084u ? G0(v() - 1, -1) : G0(0, v()) : this.f12084u ? G0(0, v()) : G0(v() - 1, -1);
        View viewM0 = iB0 == -1 ? M0() : L0();
        if (!viewM0.hasFocusable()) {
            return viewG0;
        }
        if (viewG0 == null) {
            return null;
        }
        return viewM0;
    }

    public void O0(C1648i0 c1648i0, C2377P c2377p, C2399s c2399s, C2398r c2398r) {
        int iC;
        int i7;
        int iD;
        int i10;
        int iD2;
        View viewB = c2399s.b(c1648i0);
        if (viewB == null) {
            c2398r.f22339b = true;
            return;
        }
        C2368G c2368g = (C2368G) viewB.getLayoutParams();
        if (c2399s.f22348k == null) {
            if (this.f12084u == (c2399s.f22343f == -1)) {
                b(viewB, -1, false);
            } else {
                b(viewB, 0, false);
            }
        } else {
            if (this.f12084u == (c2399s.f22343f == -1)) {
                b(viewB, -1, true);
            } else {
                b(viewB, 0, true);
            }
        }
        C2368G c2368g2 = (C2368G) viewB.getLayoutParams();
        Rect rectH = this.f22180b.H(viewB);
        int i11 = rectH.left + rectH.right;
        int i12 = rectH.top + rectH.bottom;
        int iW = AbstractC2367F.w(d(), this.f22189n, this.l, B() + A() + ((ViewGroup.MarginLayoutParams) c2368g2).leftMargin + ((ViewGroup.MarginLayoutParams) c2368g2).rightMargin + i11, ((ViewGroup.MarginLayoutParams) c2368g2).width);
        int iW2 = AbstractC2367F.w(e(), this.o, this.f22188m, z() + C() + ((ViewGroup.MarginLayoutParams) c2368g2).topMargin + ((ViewGroup.MarginLayoutParams) c2368g2).bottomMargin + i12, ((ViewGroup.MarginLayoutParams) c2368g2).height);
        if (r0(viewB, iW, iW2, c2368g2)) {
            viewB.measure(iW, iW2);
        }
        c2398r.f22338a = this.f12081r.c(viewB);
        if (this.f12079p == 1) {
            if (N0()) {
                iD = this.f22189n - B();
                iD2 = iD - this.f12081r.d(viewB);
            } else {
                int iA = A();
                iD = this.f12081r.d(viewB) + iA;
                iD2 = iA;
            }
            if (c2399s.f22343f == -1) {
                i10 = c2399s.f22341b;
                iC = i10 - c2398r.f22338a;
            } else {
                iC = c2399s.f22341b;
                i10 = c2398r.f22338a + iC;
            }
        } else {
            iC = C();
            int iD3 = this.f12081r.d(viewB) + iC;
            if (c2399s.f22343f == -1) {
                iD = c2399s.f22341b;
                i7 = iD - c2398r.f22338a;
            } else {
                i7 = c2399s.f22341b;
                iD = c2398r.f22338a + i7;
            }
            int i13 = i7;
            i10 = iD3;
            iD2 = i13;
        }
        AbstractC2367F.J(viewB, iD2, iC, iD, i10);
        if (c2368g.f22190a.i() || c2368g.f22190a.l()) {
            c2398r.c = true;
        }
        c2398r.d = viewB.hasFocusable();
    }

    @Override // s3.AbstractC2367F
    public final void P(AccessibilityEvent accessibilityEvent) {
        super.P(accessibilityEvent);
        if (v() > 0) {
            View viewH0 = H0(0, v(), false);
            accessibilityEvent.setFromIndex(viewH0 == null ? -1 : AbstractC2367F.D(viewH0));
            View viewH02 = H0(v() - 1, -1, false);
            accessibilityEvent.setToIndex(viewH02 != null ? AbstractC2367F.D(viewH02) : -1);
        }
    }

    public void P0(C1648i0 c1648i0, C2377P c2377p, x xVar, int i7) {
    }

    public final void Q0(C1648i0 c1648i0, C2399s c2399s) {
        if (!c2399s.f22340a || c2399s.l) {
            return;
        }
        int i7 = c2399s.f22344g;
        int i10 = c2399s.f22346i;
        if (c2399s.f22343f == -1) {
            int iV = v();
            if (i7 < 0) {
                return;
            }
            int iF = (this.f12081r.f() - i7) + i10;
            if (this.f12084u) {
                for (int i11 = 0; i11 < iV; i11++) {
                    View viewU = u(i11);
                    if (this.f12081r.e(viewU) < iF || this.f12081r.o(viewU) < iF) {
                        R0(c1648i0, 0, i11);
                        return;
                    }
                }
                return;
            }
            int i12 = iV - 1;
            for (int i13 = i12; i13 >= 0; i13--) {
                View viewU2 = u(i13);
                if (this.f12081r.e(viewU2) < iF || this.f12081r.o(viewU2) < iF) {
                    R0(c1648i0, i12, i13);
                    return;
                }
            }
            return;
        }
        if (i7 < 0) {
            return;
        }
        int i14 = i7 - i10;
        int iV2 = v();
        if (!this.f12084u) {
            for (int i15 = 0; i15 < iV2; i15++) {
                View viewU3 = u(i15);
                if (this.f12081r.b(viewU3) > i14 || this.f12081r.n(viewU3) > i14) {
                    R0(c1648i0, 0, i15);
                    return;
                }
            }
            return;
        }
        int i16 = iV2 - 1;
        for (int i17 = i16; i17 >= 0; i17--) {
            View viewU4 = u(i17);
            if (this.f12081r.b(viewU4) > i14 || this.f12081r.n(viewU4) > i14) {
                R0(c1648i0, i16, i17);
                return;
            }
        }
    }

    public final void R0(C1648i0 c1648i0, int i7, int i10) {
        if (i7 == i10) {
            return;
        }
        if (i10 <= i7) {
            while (i7 > i10) {
                View viewU = u(i7);
                g0(i7);
                c1648i0.h(viewU);
                i7--;
            }
            return;
        }
        for (int i11 = i10 - 1; i11 >= i7; i11--) {
            View viewU2 = u(i11);
            g0(i11);
            c1648i0.h(viewU2);
        }
    }

    public final void S0() {
        if (this.f12079p == 1 || !N0()) {
            this.f12084u = this.f12083t;
        } else {
            this.f12084u = !this.f12083t;
        }
    }

    public final int T0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        if (v() == 0 || i7 == 0) {
            return 0;
        }
        C0();
        this.f12080q.f22340a = true;
        int i10 = i7 > 0 ? 1 : -1;
        int iAbs = Math.abs(i7);
        W0(i10, iAbs, true, c2377p);
        C2399s c2399s = this.f12080q;
        int iD0 = D0(c1648i0, c2399s, c2377p, false) + c2399s.f22344g;
        if (iD0 < 0) {
            return 0;
        }
        if (iAbs > iD0) {
            i7 = i10 * iD0;
        }
        this.f12081r.p(-i7);
        this.f12080q.f22347j = i7;
        return i7;
    }

    public final void U0(int i7) {
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException(a.e(i7, "invalid orientation:"));
        }
        c(null);
        if (i7 != this.f12079p || this.f12081r == null) {
            f fVarA = f.a(this, i7);
            this.f12081r = fVarA;
            this.f12076A.f15498f = fVarA;
            this.f12079p = i7;
            i0();
        }
    }

    public void V0(boolean z6) {
        c(null);
        if (this.f12085v == z6) {
            return;
        }
        this.f12085v = z6;
        i0();
    }

    public final void W0(int i7, int i10, boolean z6, C2377P c2377p) {
        int iK;
        this.f12080q.l = this.f12081r.i() == 0 && this.f12081r.f() == 0;
        this.f12080q.f22343f = i7;
        int[] iArr = this.f12078D;
        iArr[0] = 0;
        iArr[1] = 0;
        w0(c2377p, iArr);
        int iMax = Math.max(0, iArr[0]);
        int iMax2 = Math.max(0, iArr[1]);
        boolean z10 = i7 == 1;
        C2399s c2399s = this.f12080q;
        int i11 = z10 ? iMax2 : iMax;
        c2399s.f22345h = i11;
        if (!z10) {
            iMax = iMax2;
        }
        c2399s.f22346i = iMax;
        if (z10) {
            c2399s.f22345h = this.f12081r.h() + i11;
            View viewL0 = L0();
            C2399s c2399s2 = this.f12080q;
            c2399s2.f22342e = this.f12084u ? -1 : 1;
            int iD = AbstractC2367F.D(viewL0);
            C2399s c2399s3 = this.f12080q;
            c2399s2.d = iD + c2399s3.f22342e;
            c2399s3.f22341b = this.f12081r.b(viewL0);
            iK = this.f12081r.b(viewL0) - this.f12081r.g();
        } else {
            View viewM0 = M0();
            C2399s c2399s4 = this.f12080q;
            c2399s4.f22345h = this.f12081r.k() + c2399s4.f22345h;
            C2399s c2399s5 = this.f12080q;
            c2399s5.f22342e = this.f12084u ? 1 : -1;
            int iD2 = AbstractC2367F.D(viewM0);
            C2399s c2399s6 = this.f12080q;
            c2399s5.d = iD2 + c2399s6.f22342e;
            c2399s6.f22341b = this.f12081r.e(viewM0);
            iK = (-this.f12081r.e(viewM0)) + this.f12081r.k();
        }
        C2399s c2399s7 = this.f12080q;
        c2399s7.c = i10;
        if (z6) {
            c2399s7.c = i10 - iK;
        }
        c2399s7.f22344g = iK;
    }

    public final void X0(int i7, int i10) {
        this.f12080q.c = this.f12081r.g() - i10;
        C2399s c2399s = this.f12080q;
        c2399s.f22342e = this.f12084u ? -1 : 1;
        c2399s.d = i7;
        c2399s.f22343f = 1;
        c2399s.f22341b = i10;
        c2399s.f22344g = Integer.MIN_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x018c  */
    @Override // s3.AbstractC2367F
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Y(k3.C1648i0 r18, s3.C2377P r19) {
        /*
            Method dump skipped, instruction units count: 1078
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.Y(k3.i0, s3.P):void");
    }

    public final void Y0(int i7, int i10) {
        this.f12080q.c = i10 - this.f12081r.k();
        C2399s c2399s = this.f12080q;
        c2399s.d = i7;
        c2399s.f22342e = this.f12084u ? 1 : -1;
        c2399s.f22343f = -1;
        c2399s.f22341b = i10;
        c2399s.f22344g = Integer.MIN_VALUE;
    }

    @Override // s3.AbstractC2367F
    public void Z(C2377P c2377p) {
        this.f12089z = null;
        this.f12087x = -1;
        this.f12088y = Integer.MIN_VALUE;
        this.f12076A.g();
    }

    @Override // s3.InterfaceC2376O
    public final PointF a(int i7) {
        if (v() == 0) {
            return null;
        }
        int i10 = (i7 < AbstractC2367F.D(u(0))) != this.f12084u ? -1 : 1;
        return this.f12079p == 0 ? new PointF(i10, 0.0f) : new PointF(0.0f, i10);
    }

    @Override // s3.AbstractC2367F
    public final void a0(Parcelable parcelable) {
        if (parcelable instanceof C2400t) {
            C2400t c2400t = (C2400t) parcelable;
            this.f12089z = c2400t;
            if (this.f12087x != -1) {
                c2400t.l = -1;
            }
            i0();
        }
    }

    @Override // s3.AbstractC2367F
    public final Parcelable b0() {
        C2400t c2400t = this.f12089z;
        if (c2400t != null) {
            C2400t c2400t2 = new C2400t();
            c2400t2.l = c2400t.l;
            c2400t2.f22349m = c2400t.f22349m;
            c2400t2.f22350n = c2400t.f22350n;
            return c2400t2;
        }
        C2400t c2400t3 = new C2400t();
        if (v() > 0) {
            C0();
            boolean z6 = this.f12082s ^ this.f12084u;
            c2400t3.f22350n = z6;
            if (z6) {
                View viewL0 = L0();
                c2400t3.f22349m = this.f12081r.g() - this.f12081r.b(viewL0);
                c2400t3.l = AbstractC2367F.D(viewL0);
            } else {
                View viewM0 = M0();
                c2400t3.l = AbstractC2367F.D(viewM0);
                c2400t3.f22349m = this.f12081r.e(viewM0) - this.f12081r.k();
            }
        } else {
            c2400t3.l = -1;
        }
        return c2400t3;
    }

    @Override // s3.AbstractC2367F
    public final void c(String str) {
        RecyclerView recyclerView;
        if (this.f12089z != null || (recyclerView = this.f22180b) == null) {
            return;
        }
        recyclerView.f(str);
    }

    @Override // s3.AbstractC2367F
    public final boolean d() {
        return this.f12079p == 0;
    }

    @Override // s3.AbstractC2367F
    public final boolean e() {
        return this.f12079p == 1;
    }

    @Override // s3.AbstractC2367F
    public final void h(int i7, int i10, C2377P c2377p, C2393m c2393m) {
        if (this.f12079p != 0) {
            i7 = i10;
        }
        if (v() == 0 || i7 == 0) {
            return;
        }
        C0();
        W0(i7 > 0 ? 1 : -1, Math.abs(i7), true, c2377p);
        x0(c2377p, this.f12080q, c2393m);
    }

    @Override // s3.AbstractC2367F
    public final void i(int i7, C2393m c2393m) {
        boolean z6;
        int i10;
        C2400t c2400t = this.f12089z;
        if (c2400t == null || (i10 = c2400t.l) < 0) {
            S0();
            z6 = this.f12084u;
            i10 = this.f12087x;
            if (i10 == -1) {
                i10 = z6 ? i7 - 1 : 0;
            }
        } else {
            z6 = c2400t.f22350n;
        }
        int i11 = z6 ? -1 : 1;
        for (int i12 = 0; i12 < this.f12077C && i10 >= 0 && i10 < i7; i12++) {
            c2393m.b(i10, 0);
            i10 += i11;
        }
    }

    @Override // s3.AbstractC2367F
    public final int j(C2377P c2377p) {
        return y0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public int j0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        if (this.f12079p == 1) {
            return 0;
        }
        return T0(i7, c1648i0, c2377p);
    }

    @Override // s3.AbstractC2367F
    public int k(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final void k0(int i7) {
        this.f12087x = i7;
        this.f12088y = Integer.MIN_VALUE;
        C2400t c2400t = this.f12089z;
        if (c2400t != null) {
            c2400t.l = -1;
        }
        i0();
    }

    @Override // s3.AbstractC2367F
    public int l(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public int l0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        if (this.f12079p == 0) {
            return 0;
        }
        return T0(i7, c1648i0, c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int m(C2377P c2377p) {
        return y0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public int n(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public int o(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final View q(int i7) {
        int iV = v();
        if (iV == 0) {
            return null;
        }
        int iD = i7 - AbstractC2367F.D(u(0));
        if (iD >= 0 && iD < iV) {
            View viewU = u(iD);
            if (AbstractC2367F.D(viewU) == i7) {
                return viewU;
            }
        }
        return super.q(i7);
    }

    @Override // s3.AbstractC2367F
    public C2368G r() {
        return new C2368G(-2, -2);
    }

    @Override // s3.AbstractC2367F
    public final boolean s0() {
        if (this.f22188m == 1073741824 || this.l == 1073741824) {
            return false;
        }
        int iV = v();
        for (int i7 = 0; i7 < iV; i7++) {
            ViewGroup.LayoutParams layoutParams = u(i7).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // s3.AbstractC2367F
    public boolean v0() {
        return this.f12089z == null && this.f12082s == this.f12085v;
    }

    public void w0(C2377P c2377p, int[] iArr) {
        int i7;
        int iL = c2377p.f22202a != -1 ? this.f12081r.l() : 0;
        if (this.f12080q.f22343f == -1) {
            i7 = 0;
        } else {
            i7 = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i7;
    }

    public void x0(C2377P c2377p, C2399s c2399s, C2393m c2393m) {
        int i7 = c2399s.d;
        if (i7 < 0 || i7 >= c2377p.b()) {
            return;
        }
        c2393m.b(i7, Math.max(0, c2399s.f22344g));
    }

    public final int y0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        C0();
        f fVar = this.f12081r;
        boolean z6 = !this.f12086w;
        return m.j(c2377p, fVar, F0(z6), E0(z6), this, this.f12086w);
    }

    public final int z0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        C0();
        f fVar = this.f12081r;
        boolean z6 = !this.f12086w;
        return m.k(c2377p, fVar, F0(z6), E0(z6), this, this.f12086w, this.f12084u);
    }

    @SuppressLint({"UnknownNullness"})
    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i7, int i10) {
        this.f12079p = 1;
        this.f12083t = false;
        this.f12084u = false;
        this.f12085v = false;
        this.f12086w = true;
        this.f12087x = -1;
        this.f12088y = Integer.MIN_VALUE;
        this.f12089z = null;
        this.f12076A = new x();
        this.B = new C2398r();
        this.f12077C = 2;
        this.f12078D = new int[2];
        C2366E c2366eE = AbstractC2367F.E(context, attributeSet, i7, i10);
        U0(c2366eE.f22177a);
        boolean z6 = c2366eE.c;
        c(null);
        if (z6 != this.f12083t) {
            this.f12083t = z6;
            i0();
        }
        V0(c2366eE.d);
    }
}
