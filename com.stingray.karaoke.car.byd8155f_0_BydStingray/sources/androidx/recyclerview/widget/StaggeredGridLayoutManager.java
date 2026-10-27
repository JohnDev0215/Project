package androidx.recyclerview.widget;

import F1.H;
import T1.f;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import h6.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import k3.C1648i0;
import l5.C1778f;
import s3.AbstractC2367F;
import s3.C2366E;
import s3.C2368G;
import s3.C2377P;
import s3.C2393m;
import s3.C2397q;
import s3.C2402v;
import s3.InterfaceC2376O;
import s3.RunnableC2389i;
import s3.X;
import s3.Y;
import s3.a0;
import s3.b0;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends AbstractC2367F implements InterfaceC2376O {
    public final C1778f B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final int f12164C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f12165D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f12166E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public a0 f12167F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Rect f12168G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final X f12169H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final boolean f12170I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int[] f12171J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final RunnableC2389i f12172K;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f12173p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final b0[] f12174q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final f f12175r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f f12176s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f12177t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f12178u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C2397q f12179v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12180w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final BitSet f12182y;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f12181x = false;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f12183z = -1;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f12163A = Integer.MIN_VALUE;

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i10) {
        this.f12173p = -1;
        this.f12180w = false;
        C1778f c1778f = new C1778f(7, (byte) 0);
        this.B = c1778f;
        this.f12164C = 2;
        this.f12168G = new Rect();
        this.f12169H = new X(this);
        this.f12170I = true;
        this.f12172K = new RunnableC2389i(this, 2);
        C2366E c2366eE = AbstractC2367F.E(context, attributeSet, i7, i10);
        int i11 = c2366eE.f22177a;
        if (i11 != 0 && i11 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        c(null);
        if (i11 != this.f12177t) {
            this.f12177t = i11;
            f fVar = this.f12175r;
            this.f12175r = this.f12176s;
            this.f12176s = fVar;
            i0();
        }
        int i12 = c2366eE.f22178b;
        c(null);
        if (i12 != this.f12173p) {
            c1778f.c();
            i0();
            this.f12173p = i12;
            this.f12182y = new BitSet(this.f12173p);
            this.f12174q = new b0[this.f12173p];
            for (int i13 = 0; i13 < this.f12173p; i13++) {
                this.f12174q[i13] = new b0(this, i13);
            }
            i0();
        }
        boolean z6 = c2366eE.c;
        c(null);
        a0 a0Var = this.f12167F;
        if (a0Var != null && a0Var.f22251s != z6) {
            a0Var.f22251s = z6;
        }
        this.f12180w = z6;
        i0();
        C2397q c2397q = new C2397q();
        c2397q.f22331a = true;
        c2397q.f22334f = 0;
        c2397q.f22335g = 0;
        this.f12179v = c2397q;
        this.f12175r = f.a(this, this.f12177t);
        this.f12176s = f.a(this, 1 - this.f12177t);
    }

    public static int Z0(int i7, int i10, int i11) {
        if (i10 == 0 && i11 == 0) {
            return i7;
        }
        int mode = View.MeasureSpec.getMode(i7);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i7) - i10) - i11), mode) : i7;
    }

    public final int A0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        f fVar = this.f12175r;
        boolean z6 = !this.f12170I;
        return m.l(c2377p, fVar, D0(z6), C0(z6), this, this.f12170I);
    }

    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean, int] */
    public final int B0(C1648i0 c1648i0, C2397q c2397q, C2377P c2377p) {
        b0 b0Var;
        ?? r62;
        int i7;
        int iJ;
        int iC;
        int iK;
        int iC2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14 = 0;
        int i15 = 1;
        this.f12182y.set(0, this.f12173p, true);
        C2397q c2397q2 = this.f12179v;
        int i16 = c2397q2.f22337i ? c2397q.f22333e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : c2397q.f22333e == 1 ? c2397q.f22335g + c2397q.f22332b : c2397q.f22334f - c2397q.f22332b;
        int i17 = c2397q.f22333e;
        for (int i18 = 0; i18 < this.f12173p; i18++) {
            if (!((ArrayList) this.f12174q[i18].f22259f).isEmpty()) {
                Y0(this.f12174q[i18], i17, i16);
            }
        }
        int iG = this.f12181x ? this.f12175r.g() : this.f12175r.k();
        boolean z6 = false;
        while (true) {
            int i19 = c2397q.c;
            if (((i19 < 0 || i19 >= c2377p.b()) ? i14 : i15) == 0 || (!c2397q2.f22337i && this.f12182y.isEmpty())) {
                break;
            }
            View view = c1648i0.k(Long.MAX_VALUE, c2397q.c).f22219a;
            c2397q.c += c2397q.d;
            Y y10 = (Y) view.getLayoutParams();
            int iB = y10.f22190a.b();
            C1778f c1778f = this.B;
            int[] iArr = (int[]) c1778f.f18638m;
            int i20 = (iArr == null || iB >= iArr.length) ? -1 : iArr[iB];
            if (i20 == -1) {
                if (P0(c2397q.f22333e)) {
                    i13 = this.f12173p - i15;
                    i12 = -1;
                    i11 = -1;
                } else {
                    i11 = i15;
                    i12 = this.f12173p;
                    i13 = i14;
                }
                b0 b0Var2 = null;
                if (c2397q.f22333e == i15) {
                    int iK2 = this.f12175r.k();
                    int i21 = Integer.MAX_VALUE;
                    while (i13 != i12) {
                        b0 b0Var3 = this.f12174q[i13];
                        int iH = b0Var3.h(iK2);
                        if (iH < i21) {
                            i21 = iH;
                            b0Var2 = b0Var3;
                        }
                        i13 += i11;
                    }
                } else {
                    int iG2 = this.f12175r.g();
                    int i22 = Integer.MIN_VALUE;
                    while (i13 != i12) {
                        b0 b0Var4 = this.f12174q[i13];
                        int iJ2 = b0Var4.j(iG2);
                        if (iJ2 > i22) {
                            b0Var2 = b0Var4;
                            i22 = iJ2;
                        }
                        i13 += i11;
                    }
                }
                b0Var = b0Var2;
                c1778f.f(iB);
                ((int[]) c1778f.f18638m)[iB] = b0Var.f22258e;
            } else {
                b0Var = this.f12174q[i20];
            }
            y10.f22241e = b0Var;
            if (c2397q.f22333e == 1) {
                r62 = 0;
                b(view, -1, false);
            } else {
                r62 = 0;
                b(view, 0, false);
            }
            if (this.f12177t == 1) {
                i7 = 1;
                N0(view, AbstractC2367F.w(r62, this.f12178u, this.l, r62, ((ViewGroup.MarginLayoutParams) y10).width), AbstractC2367F.w(true, this.o, this.f22188m, z() + C(), ((ViewGroup.MarginLayoutParams) y10).height));
            } else {
                i7 = 1;
                N0(view, AbstractC2367F.w(true, this.f22189n, this.l, B() + A(), ((ViewGroup.MarginLayoutParams) y10).width), AbstractC2367F.w(false, this.f12178u, this.f22188m, 0, ((ViewGroup.MarginLayoutParams) y10).height));
            }
            if (c2397q.f22333e == i7) {
                iC = b0Var.h(iG);
                iJ = this.f12175r.c(view) + iC;
            } else {
                iJ = b0Var.j(iG);
                iC = iJ - this.f12175r.c(view);
            }
            if (c2397q.f22333e == 1) {
                b0 b0Var5 = y10.f22241e;
                b0Var5.getClass();
                Y y11 = (Y) view.getLayoutParams();
                y11.f22241e = b0Var5;
                ArrayList arrayList = (ArrayList) b0Var5.f22259f;
                arrayList.add(view);
                b0Var5.c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    b0Var5.f22257b = Integer.MIN_VALUE;
                }
                if (y11.f22190a.i() || y11.f22190a.l()) {
                    b0Var5.d = ((StaggeredGridLayoutManager) b0Var5.f22260g).f12175r.c(view) + b0Var5.d;
                }
            } else {
                b0 b0Var6 = y10.f22241e;
                b0Var6.getClass();
                Y y12 = (Y) view.getLayoutParams();
                y12.f22241e = b0Var6;
                ArrayList arrayList2 = (ArrayList) b0Var6.f22259f;
                arrayList2.add(0, view);
                b0Var6.f22257b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    b0Var6.c = Integer.MIN_VALUE;
                }
                if (y12.f22190a.i() || y12.f22190a.l()) {
                    b0Var6.d = ((StaggeredGridLayoutManager) b0Var6.f22260g).f12175r.c(view) + b0Var6.d;
                }
            }
            if (M0() && this.f12177t == 1) {
                iC2 = this.f12176s.g() - (((this.f12173p - 1) - b0Var.f22258e) * this.f12178u);
                iK = iC2 - this.f12176s.c(view);
            } else {
                iK = this.f12176s.k() + (b0Var.f22258e * this.f12178u);
                iC2 = this.f12176s.c(view) + iK;
            }
            if (this.f12177t == 1) {
                AbstractC2367F.J(view, iK, iC, iC2, iJ);
            } else {
                AbstractC2367F.J(view, iC, iK, iJ, iC2);
            }
            Y0(b0Var, c2397q2.f22333e, i16);
            R0(c1648i0, c2397q2);
            if (c2397q2.f22336h && view.hasFocusable()) {
                i10 = 0;
                this.f12182y.set(b0Var.f22258e, false);
            } else {
                i10 = 0;
            }
            i14 = i10;
            i15 = 1;
            z6 = true;
        }
        int i23 = i14;
        if (!z6) {
            R0(c1648i0, c2397q2);
        }
        int iK3 = c2397q2.f22333e == -1 ? this.f12175r.k() - J0(this.f12175r.k()) : I0(this.f12175r.g()) - this.f12175r.g();
        return iK3 > 0 ? Math.min(c2397q.f22332b, iK3) : i23;
    }

    public final View C0(boolean z6) {
        int iK = this.f12175r.k();
        int iG = this.f12175r.g();
        View view = null;
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            int iE = this.f12175r.e(viewU);
            int iB = this.f12175r.b(viewU);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z6) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final View D0(boolean z6) {
        int iK = this.f12175r.k();
        int iG = this.f12175r.g();
        int iV = v();
        View view = null;
        for (int i7 = 0; i7 < iV; i7++) {
            View viewU = u(i7);
            int iE = this.f12175r.e(viewU);
            if (this.f12175r.b(viewU) > iK && iE < iG) {
                if (iE >= iK || !z6) {
                    return viewU;
                }
                if (view == null) {
                    view = viewU;
                }
            }
        }
        return view;
    }

    public final void E0(C1648i0 c1648i0, C2377P c2377p, boolean z6) {
        int iG;
        int iI0 = I0(Integer.MIN_VALUE);
        if (iI0 != Integer.MIN_VALUE && (iG = this.f12175r.g() - iI0) > 0) {
            int i7 = iG - (-V0(-iG, c1648i0, c2377p));
            if (!z6 || i7 <= 0) {
                return;
            }
            this.f12175r.p(i7);
        }
    }

    public final void F0(C1648i0 c1648i0, C2377P c2377p, boolean z6) {
        int iK;
        int iJ0 = J0(Integer.MAX_VALUE);
        if (iJ0 != Integer.MAX_VALUE && (iK = iJ0 - this.f12175r.k()) > 0) {
            int iV0 = iK - V0(iK, c1648i0, c2377p);
            if (!z6 || iV0 <= 0) {
                return;
            }
            this.f12175r.p(-iV0);
        }
    }

    public final int G0() {
        if (v() == 0) {
            return 0;
        }
        return AbstractC2367F.D(u(0));
    }

    @Override // s3.AbstractC2367F
    public final boolean H() {
        return this.f12164C != 0;
    }

    public final int H0() {
        int iV = v();
        if (iV == 0) {
            return 0;
        }
        return AbstractC2367F.D(u(iV - 1));
    }

    public final int I0(int i7) {
        int iH = this.f12174q[0].h(i7);
        for (int i10 = 1; i10 < this.f12173p; i10++) {
            int iH2 = this.f12174q[i10].h(i7);
            if (iH2 > iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    public final int J0(int i7) {
        int iJ = this.f12174q[0].j(i7);
        for (int i10 = 1; i10 < this.f12173p; i10++) {
            int iJ2 = this.f12174q[i10].j(i7);
            if (iJ2 < iJ) {
                iJ = iJ2;
            }
        }
        return iJ;
    }

    @Override // s3.AbstractC2367F
    public final void K(int i7) {
        super.K(i7);
        for (int i10 = 0; i10 < this.f12173p; i10++) {
            b0 b0Var = this.f12174q[i10];
            int i11 = b0Var.f22257b;
            if (i11 != Integer.MIN_VALUE) {
                b0Var.f22257b = i11 + i7;
            }
            int i12 = b0Var.c;
            if (i12 != Integer.MIN_VALUE) {
                b0Var.c = i12 + i7;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00cc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void K0(int r11, int r12, int r13) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.K0(int, int, int):void");
    }

    @Override // s3.AbstractC2367F
    public final void L(int i7) {
        super.L(i7);
        for (int i10 = 0; i10 < this.f12173p; i10++) {
            b0 b0Var = this.f12174q[i10];
            int i11 = b0Var.f22257b;
            if (i11 != Integer.MIN_VALUE) {
                b0Var.f22257b = i11 + i7;
            }
            int i12 = b0Var.c;
            if (i12 != Integer.MIN_VALUE) {
                b0Var.c = i12 + i7;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View L0() {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.L0():android.view.View");
    }

    @Override // s3.AbstractC2367F
    public final void M() {
        this.B.c();
        for (int i7 = 0; i7 < this.f12173p; i7++) {
            this.f12174q[i7].b();
        }
    }

    public final boolean M0() {
        RecyclerView recyclerView = this.f22180b;
        int[] iArr = H.f2303a;
        return recyclerView.getLayoutDirection() == 1;
    }

    @Override // s3.AbstractC2367F
    public final void N(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f22180b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.f12172K);
        }
        for (int i7 = 0; i7 < this.f12173p; i7++) {
            this.f12174q[i7].b();
        }
        recyclerView.requestLayout();
    }

    public final void N0(View view, int i7, int i10) {
        RecyclerView recyclerView = this.f22180b;
        Rect rect = this.f12168G;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.H(view));
        }
        Y y10 = (Y) view.getLayoutParams();
        int iZ0 = Z0(i7, ((ViewGroup.MarginLayoutParams) y10).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) y10).rightMargin + rect.right);
        int iZ02 = Z0(i10, ((ViewGroup.MarginLayoutParams) y10).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) y10).bottomMargin + rect.bottom);
        if (r0(view, iZ0, iZ02, y10)) {
            view.measure(iZ0, iZ02);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0063  */
    @Override // s3.AbstractC2367F
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View O(android.view.View r9, int r10, k3.C1648i0 r11, s3.C2377P r12) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.O(android.view.View, int, k3.i0, s3.P):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0407  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void O0(k3.C1648i0 r17, s3.C2377P r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1058
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.O0(k3.i0, s3.P, boolean):void");
    }

    @Override // s3.AbstractC2367F
    public final void P(AccessibilityEvent accessibilityEvent) {
        super.P(accessibilityEvent);
        if (v() > 0) {
            View viewD0 = D0(false);
            View viewC0 = C0(false);
            if (viewD0 == null || viewC0 == null) {
                return;
            }
            int iD = AbstractC2367F.D(viewD0);
            int iD2 = AbstractC2367F.D(viewC0);
            if (iD < iD2) {
                accessibilityEvent.setFromIndex(iD);
                accessibilityEvent.setToIndex(iD2);
            } else {
                accessibilityEvent.setFromIndex(iD2);
                accessibilityEvent.setToIndex(iD);
            }
        }
    }

    public final boolean P0(int i7) {
        if (this.f12177t == 0) {
            return (i7 == -1) != this.f12181x;
        }
        return ((i7 == -1) == this.f12181x) == M0();
    }

    public final void Q0(int i7, C2377P c2377p) {
        int iG0;
        int i10;
        if (i7 > 0) {
            iG0 = H0();
            i10 = 1;
        } else {
            iG0 = G0();
            i10 = -1;
        }
        C2397q c2397q = this.f12179v;
        c2397q.f22331a = true;
        X0(iG0, c2377p);
        W0(i10);
        c2397q.c = iG0 + c2397q.d;
        c2397q.f22332b = Math.abs(i7);
    }

    public final void R0(C1648i0 c1648i0, C2397q c2397q) {
        if (!c2397q.f22331a || c2397q.f22337i) {
            return;
        }
        if (c2397q.f22332b == 0) {
            if (c2397q.f22333e == -1) {
                S0(c1648i0, c2397q.f22335g);
                return;
            } else {
                T0(c1648i0, c2397q.f22334f);
                return;
            }
        }
        int i7 = 1;
        if (c2397q.f22333e == -1) {
            int i10 = c2397q.f22334f;
            int iJ = this.f12174q[0].j(i10);
            while (i7 < this.f12173p) {
                int iJ2 = this.f12174q[i7].j(i10);
                if (iJ2 > iJ) {
                    iJ = iJ2;
                }
                i7++;
            }
            int i11 = i10 - iJ;
            S0(c1648i0, i11 < 0 ? c2397q.f22335g : c2397q.f22335g - Math.min(i11, c2397q.f22332b));
            return;
        }
        int i12 = c2397q.f22335g;
        int iH = this.f12174q[0].h(i12);
        while (i7 < this.f12173p) {
            int iH2 = this.f12174q[i7].h(i12);
            if (iH2 < iH) {
                iH = iH2;
            }
            i7++;
        }
        int i13 = iH - c2397q.f22335g;
        T0(c1648i0, i13 < 0 ? c2397q.f22334f : Math.min(i13, c2397q.f22332b) + c2397q.f22334f);
    }

    public final void S0(C1648i0 c1648i0, int i7) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            if (this.f12175r.e(viewU) < i7 || this.f12175r.o(viewU) < i7) {
                return;
            }
            Y y10 = (Y) viewU.getLayoutParams();
            y10.getClass();
            if (((ArrayList) y10.f22241e.f22259f).size() == 1) {
                return;
            }
            b0 b0Var = y10.f22241e;
            ArrayList arrayList = (ArrayList) b0Var.f22259f;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            Y y11 = (Y) view.getLayoutParams();
            y11.f22241e = null;
            if (y11.f22190a.i() || y11.f22190a.l()) {
                b0Var.d -= ((StaggeredGridLayoutManager) b0Var.f22260g).f12175r.c(view);
            }
            if (size == 1) {
                b0Var.f22257b = Integer.MIN_VALUE;
            }
            b0Var.c = Integer.MIN_VALUE;
            f0(viewU, c1648i0);
        }
    }

    @Override // s3.AbstractC2367F
    public final void T(int i7, int i10) {
        K0(i7, i10, 1);
    }

    public final void T0(C1648i0 c1648i0, int i7) {
        while (v() > 0) {
            View viewU = u(0);
            if (this.f12175r.b(viewU) > i7 || this.f12175r.n(viewU) > i7) {
                return;
            }
            Y y10 = (Y) viewU.getLayoutParams();
            y10.getClass();
            if (((ArrayList) y10.f22241e.f22259f).size() == 1) {
                return;
            }
            b0 b0Var = y10.f22241e;
            ArrayList arrayList = (ArrayList) b0Var.f22259f;
            View view = (View) arrayList.remove(0);
            Y y11 = (Y) view.getLayoutParams();
            y11.f22241e = null;
            if (arrayList.size() == 0) {
                b0Var.c = Integer.MIN_VALUE;
            }
            if (y11.f22190a.i() || y11.f22190a.l()) {
                b0Var.d -= ((StaggeredGridLayoutManager) b0Var.f22260g).f12175r.c(view);
            }
            b0Var.f22257b = Integer.MIN_VALUE;
            f0(viewU, c1648i0);
        }
    }

    @Override // s3.AbstractC2367F
    public final void U() {
        this.B.c();
        i0();
    }

    public final void U0() {
        if (this.f12177t == 1 || !M0()) {
            this.f12181x = this.f12180w;
        } else {
            this.f12181x = !this.f12180w;
        }
    }

    @Override // s3.AbstractC2367F
    public final void V(int i7, int i10) {
        K0(i7, i10, 8);
    }

    public final int V0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        if (v() == 0 || i7 == 0) {
            return 0;
        }
        Q0(i7, c2377p);
        C2397q c2397q = this.f12179v;
        int iB0 = B0(c1648i0, c2397q, c2377p);
        if (c2397q.f22332b >= iB0) {
            i7 = i7 < 0 ? -iB0 : iB0;
        }
        this.f12175r.p(-i7);
        this.f12165D = this.f12181x;
        c2397q.f22332b = 0;
        R0(c1648i0, c2397q);
        return i7;
    }

    @Override // s3.AbstractC2367F
    public final void W(int i7, int i10) {
        K0(i7, i10, 2);
    }

    public final void W0(int i7) {
        C2397q c2397q = this.f12179v;
        c2397q.f22333e = i7;
        c2397q.d = this.f12181x != (i7 == -1) ? -1 : 1;
    }

    @Override // s3.AbstractC2367F
    public final void X(int i7, int i10) {
        K0(i7, i10, 4);
    }

    public final void X0(int i7, C2377P c2377p) {
        int iL;
        int iL2;
        int i10;
        C2397q c2397q = this.f12179v;
        boolean z6 = false;
        c2397q.f22332b = 0;
        c2397q.c = i7;
        C2402v c2402v = this.f22181e;
        if (!(c2402v != null && c2402v.f22353e) || (i10 = c2377p.f22202a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.f12181x == (i10 < i7)) {
                iL = this.f12175r.l();
                iL2 = 0;
            } else {
                iL2 = this.f12175r.l();
                iL = 0;
            }
        }
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView == null || !recyclerView.f12147s) {
            c2397q.f22335g = this.f12175r.f() + iL;
            c2397q.f22334f = -iL2;
        } else {
            c2397q.f22334f = this.f12175r.k() - iL2;
            c2397q.f22335g = this.f12175r.g() + iL;
        }
        c2397q.f22336h = false;
        c2397q.f22331a = true;
        if (this.f12175r.i() == 0 && this.f12175r.f() == 0) {
            z6 = true;
        }
        c2397q.f22337i = z6;
    }

    @Override // s3.AbstractC2367F
    public final void Y(C1648i0 c1648i0, C2377P c2377p) {
        O0(c1648i0, c2377p, true);
    }

    public final void Y0(b0 b0Var, int i7, int i10) {
        int i11 = b0Var.d;
        int i12 = b0Var.f22258e;
        if (i7 != -1) {
            int i13 = b0Var.c;
            if (i13 == Integer.MIN_VALUE) {
                b0Var.a();
                i13 = b0Var.c;
            }
            if (i13 - i11 >= i10) {
                this.f12182y.set(i12, false);
                return;
            }
            return;
        }
        int i14 = b0Var.f22257b;
        if (i14 == Integer.MIN_VALUE) {
            View view = (View) ((ArrayList) b0Var.f22259f).get(0);
            Y y10 = (Y) view.getLayoutParams();
            b0Var.f22257b = ((StaggeredGridLayoutManager) b0Var.f22260g).f12175r.e(view);
            y10.getClass();
            i14 = b0Var.f22257b;
        }
        if (i14 + i11 <= i10) {
            this.f12182y.set(i12, false);
        }
    }

    @Override // s3.AbstractC2367F
    public final void Z(C2377P c2377p) {
        this.f12183z = -1;
        this.f12163A = Integer.MIN_VALUE;
        this.f12167F = null;
        this.f12169H.a();
    }

    @Override // s3.InterfaceC2376O
    public final PointF a(int i7) {
        int iW0 = w0(i7);
        PointF pointF = new PointF();
        if (iW0 == 0) {
            return null;
        }
        if (this.f12177t == 0) {
            pointF.x = iW0;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iW0;
        }
        return pointF;
    }

    @Override // s3.AbstractC2367F
    public final void a0(Parcelable parcelable) {
        if (parcelable instanceof a0) {
            a0 a0Var = (a0) parcelable;
            this.f12167F = a0Var;
            if (this.f12183z != -1) {
                a0Var.o = null;
                a0Var.f22247n = 0;
                a0Var.l = -1;
                a0Var.f22246m = -1;
                a0Var.o = null;
                a0Var.f22247n = 0;
                a0Var.f22248p = 0;
                a0Var.f22249q = null;
                a0Var.f22250r = null;
            }
            i0();
        }
    }

    @Override // s3.AbstractC2367F
    public final Parcelable b0() {
        int iJ;
        int iK;
        int[] iArr;
        a0 a0Var = this.f12167F;
        if (a0Var != null) {
            a0 a0Var2 = new a0();
            a0Var2.f22247n = a0Var.f22247n;
            a0Var2.l = a0Var.l;
            a0Var2.f22246m = a0Var.f22246m;
            a0Var2.o = a0Var.o;
            a0Var2.f22248p = a0Var.f22248p;
            a0Var2.f22249q = a0Var.f22249q;
            a0Var2.f22251s = a0Var.f22251s;
            a0Var2.f22252t = a0Var.f22252t;
            a0Var2.f22253u = a0Var.f22253u;
            a0Var2.f22250r = a0Var.f22250r;
            return a0Var2;
        }
        a0 a0Var3 = new a0();
        a0Var3.f22251s = this.f12180w;
        a0Var3.f22252t = this.f12165D;
        a0Var3.f22253u = this.f12166E;
        C1778f c1778f = this.B;
        if (c1778f == null || (iArr = (int[]) c1778f.f18638m) == null) {
            a0Var3.f22248p = 0;
        } else {
            a0Var3.f22249q = iArr;
            a0Var3.f22248p = iArr.length;
            a0Var3.f22250r = (ArrayList) c1778f.f18639n;
        }
        if (v() > 0) {
            a0Var3.l = this.f12165D ? H0() : G0();
            View viewC0 = this.f12181x ? C0(true) : D0(true);
            a0Var3.f22246m = viewC0 != null ? AbstractC2367F.D(viewC0) : -1;
            int i7 = this.f12173p;
            a0Var3.f22247n = i7;
            a0Var3.o = new int[i7];
            for (int i10 = 0; i10 < this.f12173p; i10++) {
                if (this.f12165D) {
                    iJ = this.f12174q[i10].h(Integer.MIN_VALUE);
                    if (iJ != Integer.MIN_VALUE) {
                        iK = this.f12175r.g();
                        iJ -= iK;
                    }
                } else {
                    iJ = this.f12174q[i10].j(Integer.MIN_VALUE);
                    if (iJ != Integer.MIN_VALUE) {
                        iK = this.f12175r.k();
                        iJ -= iK;
                    }
                }
                a0Var3.o[i10] = iJ;
            }
        } else {
            a0Var3.l = -1;
            a0Var3.f22246m = -1;
            a0Var3.f22247n = 0;
        }
        return a0Var3;
    }

    @Override // s3.AbstractC2367F
    public final void c(String str) {
        RecyclerView recyclerView;
        if (this.f12167F != null || (recyclerView = this.f22180b) == null) {
            return;
        }
        recyclerView.f(str);
    }

    @Override // s3.AbstractC2367F
    public final void c0(int i7) {
        if (i7 == 0) {
            x0();
        }
    }

    @Override // s3.AbstractC2367F
    public final boolean d() {
        return this.f12177t == 0;
    }

    @Override // s3.AbstractC2367F
    public final boolean e() {
        return this.f12177t == 1;
    }

    @Override // s3.AbstractC2367F
    public final boolean f(C2368G c2368g) {
        return c2368g instanceof Y;
    }

    @Override // s3.AbstractC2367F
    public final void h(int i7, int i10, C2377P c2377p, C2393m c2393m) {
        C2397q c2397q;
        int iH;
        int iJ;
        if (this.f12177t != 0) {
            i7 = i10;
        }
        if (v() == 0 || i7 == 0) {
            return;
        }
        Q0(i7, c2377p);
        int[] iArr = this.f12171J;
        if (iArr == null || iArr.length < this.f12173p) {
            this.f12171J = new int[this.f12173p];
        }
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = this.f12173p;
            c2397q = this.f12179v;
            if (i11 >= i13) {
                break;
            }
            if (c2397q.d == -1) {
                iH = c2397q.f22334f;
                iJ = this.f12174q[i11].j(iH);
            } else {
                iH = this.f12174q[i11].h(c2397q.f22335g);
                iJ = c2397q.f22335g;
            }
            int i14 = iH - iJ;
            if (i14 >= 0) {
                this.f12171J[i12] = i14;
                i12++;
            }
            i11++;
        }
        Arrays.sort(this.f12171J, 0, i12);
        for (int i15 = 0; i15 < i12; i15++) {
            int i16 = c2397q.c;
            if (i16 < 0 || i16 >= c2377p.b()) {
                return;
            }
            c2393m.b(c2397q.c, this.f12171J[i15]);
            c2397q.c += c2397q.d;
        }
    }

    @Override // s3.AbstractC2367F
    public final int j(C2377P c2377p) {
        return y0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int j0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        return V0(i7, c1648i0, c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int k(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final void k0(int i7) {
        a0 a0Var = this.f12167F;
        if (a0Var != null && a0Var.l != i7) {
            a0Var.o = null;
            a0Var.f22247n = 0;
            a0Var.l = -1;
            a0Var.f22246m = -1;
        }
        this.f12183z = i7;
        this.f12163A = Integer.MIN_VALUE;
        i0();
    }

    @Override // s3.AbstractC2367F
    public final int l(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int l0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        return V0(i7, c1648i0, c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int m(C2377P c2377p) {
        return y0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int n(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final int o(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final void o0(Rect rect, int i7, int i10) {
        int iG;
        int iG2;
        int i11 = this.f12173p;
        int iB = B() + A();
        int iZ = z() + C();
        if (this.f12177t == 1) {
            int iHeight = rect.height() + iZ;
            RecyclerView recyclerView = this.f22180b;
            int[] iArr = H.f2303a;
            iG2 = AbstractC2367F.g(i10, iHeight, recyclerView.getMinimumHeight());
            iG = AbstractC2367F.g(i7, (this.f12178u * i11) + iB, this.f22180b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iB;
            RecyclerView recyclerView2 = this.f22180b;
            int[] iArr2 = H.f2303a;
            iG = AbstractC2367F.g(i7, iWidth, recyclerView2.getMinimumWidth());
            iG2 = AbstractC2367F.g(i10, (this.f12178u * i11) + iZ, this.f22180b.getMinimumHeight());
        }
        this.f22180b.setMeasuredDimension(iG, iG2);
    }

    @Override // s3.AbstractC2367F
    public final C2368G r() {
        return this.f12177t == 0 ? new Y(-2, -1) : new Y(-1, -2);
    }

    @Override // s3.AbstractC2367F
    public final C2368G s(Context context, AttributeSet attributeSet) {
        return new Y(context, attributeSet);
    }

    @Override // s3.AbstractC2367F
    public final C2368G t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new Y((ViewGroup.MarginLayoutParams) layoutParams) : new Y(layoutParams);
    }

    @Override // s3.AbstractC2367F
    public final boolean v0() {
        return this.f12167F == null;
    }

    public final int w0(int i7) {
        if (v() == 0) {
            return this.f12181x ? 1 : -1;
        }
        return (i7 < G0()) != this.f12181x ? -1 : 1;
    }

    public final boolean x0() {
        int iG0;
        if (v() != 0 && this.f12164C != 0 && this.f22183g) {
            if (this.f12181x) {
                iG0 = H0();
                G0();
            } else {
                iG0 = G0();
                H0();
            }
            C1778f c1778f = this.B;
            if (iG0 == 0 && L0() != null) {
                c1778f.c();
                this.f22182f = true;
                i0();
                return true;
            }
        }
        return false;
    }

    public final int y0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        f fVar = this.f12175r;
        boolean z6 = !this.f12170I;
        return m.j(c2377p, fVar, D0(z6), C0(z6), this, this.f12170I);
    }

    public final int z0(C2377P c2377p) {
        if (v() == 0) {
            return 0;
        }
        f fVar = this.f12175r;
        boolean z6 = !this.f12170I;
        return m.k(c2377p, fVar, D0(z6), C0(z6), this, this.f12170I, this.f12181x);
    }
}
