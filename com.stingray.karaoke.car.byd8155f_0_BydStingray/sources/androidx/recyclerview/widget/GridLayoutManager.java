package androidx.recyclerview.widget;

import B2.O;
import F1.H;
import G1.e;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import h3.x;
import java.util.Arrays;
import k3.C1648i0;
import m2.b;
import s3.AbstractC2367F;
import s3.C2368G;
import s3.C2377P;
import s3.C2393m;
import s3.C2396p;
import s3.C2398r;
import s3.C2399s;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f12068E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f12069F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int[] f12070G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View[] f12071H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final SparseIntArray f12072I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final SparseIntArray f12073J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final b f12074K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final Rect f12075L;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i10) {
        super(context, attributeSet, i7, i10);
        this.f12068E = false;
        this.f12069F = -1;
        this.f12072I = new SparseIntArray();
        this.f12073J = new SparseIntArray();
        this.f12074K = new b(6);
        this.f12075L = new Rect();
        int i11 = AbstractC2367F.E(context, attributeSet, i7, i10).f22178b;
        if (i11 == this.f12069F) {
            return;
        }
        this.f12068E = true;
        if (i11 < 1) {
            throw new IllegalArgumentException(a.e(i11, "Span count should be at least 1. Provided "));
        }
        this.f12069F = i11;
        this.f12074K.s0();
        i0();
    }

    @Override // s3.AbstractC2367F
    public final int F(C1648i0 c1648i0, C2377P c2377p) {
        if (this.f12079p == 0) {
            return this.f12069F;
        }
        if (c2377p.b() < 1) {
            return 0;
        }
        return c1(c2377p.b() - 1, c1648i0, c2377p) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View I0(C1648i0 c1648i0, C2377P c2377p, boolean z6, boolean z10) {
        int i7;
        int iV;
        int iV2 = v();
        int i10 = 1;
        if (z10) {
            iV = v() - 1;
            i7 = -1;
            i10 = -1;
        } else {
            i7 = iV2;
            iV = 0;
        }
        int iB = c2377p.b();
        C0();
        int iK = this.f12081r.k();
        int iG = this.f12081r.g();
        View view = null;
        View view2 = null;
        while (iV != i7) {
            View viewU = u(iV);
            int iD = AbstractC2367F.D(viewU);
            if (iD >= 0 && iD < iB && d1(iD, c1648i0, c2377p) == 0) {
                if (((C2368G) viewU.getLayoutParams()).f22190a.i()) {
                    if (view2 == null) {
                        view2 = viewU;
                    }
                } else {
                    if (this.f12081r.e(viewU) < iG && this.f12081r.b(viewU) >= iK) {
                        return viewU;
                    }
                    if (view == null) {
                        view = viewU;
                    }
                }
            }
            iV += i10;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e1, code lost:
    
        if (r13 == (r2 > r15)) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:81:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x011c  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View O(android.view.View r23, int r24, k3.C1648i0 r25, s3.C2377P r26) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.O(android.view.View, int, k3.i0, s3.P):android.view.View");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v37 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void O0(C1648i0 c1648i0, C2377P c2377p, C2399s c2399s, C2398r c2398r) {
        int i7;
        int i10;
        int i11;
        int iD;
        int iA;
        int iC;
        int iD2;
        int iW;
        int iW2;
        ?? r82;
        int i12;
        View viewB;
        int iJ = this.f12081r.j();
        boolean z6 = iJ != 1073741824;
        int i13 = v() > 0 ? this.f12070G[this.f12069F] : 0;
        if (z6) {
            g1();
        }
        boolean z10 = c2399s.f22342e == 1;
        int iD1 = this.f12069F;
        if (!z10) {
            iD1 = d1(c2399s.d, c1648i0, c2377p) + e1(c2399s.d, c1648i0, c2377p);
        }
        int i14 = 0;
        while (i14 < this.f12069F && (i12 = c2399s.d) >= 0 && i12 < c2377p.b() && iD1 > 0) {
            int i15 = c2399s.d;
            int iE1 = e1(i15, c1648i0, c2377p);
            if (iE1 > this.f12069F) {
                throw new IllegalArgumentException(M5.b.h(M5.b.l("Item at position ", " requires ", i15, iE1, " spans but GridLayoutManager has only "), this.f12069F, " spans."));
            }
            iD1 -= iE1;
            if (iD1 < 0 || (viewB = c2399s.b(c1648i0)) == null) {
                break;
            }
            this.f12071H[i14] = viewB;
            i14++;
        }
        if (i14 == 0) {
            c2398r.f22339b = true;
            return;
        }
        if (z10) {
            i11 = 1;
            i10 = i14;
            i7 = 0;
        } else {
            i7 = i14 - 1;
            i10 = -1;
            i11 = -1;
        }
        int i16 = 0;
        while (i7 != i10) {
            View view = this.f12071H[i7];
            C2396p c2396p = (C2396p) view.getLayoutParams();
            int iE12 = e1(AbstractC2367F.D(view), c1648i0, c2377p);
            c2396p.f22330f = iE12;
            c2396p.f22329e = i16;
            i16 += iE12;
            i7 += i11;
        }
        float f10 = 0.0f;
        int i17 = 0;
        for (int i18 = 0; i18 < i14; i18++) {
            View view2 = this.f12071H[i18];
            if (c2399s.f22348k != null) {
                r82 = 0;
                r82 = 0;
                if (z10) {
                    b(view2, -1, true);
                } else {
                    b(view2, 0, true);
                }
            } else if (z10) {
                r82 = 0;
                b(view2, -1, false);
            } else {
                r82 = 0;
                b(view2, 0, false);
            }
            RecyclerView recyclerView = this.f22180b;
            Rect rect = this.f12075L;
            if (recyclerView == null) {
                rect.set(r82, r82, r82, r82);
            } else {
                rect.set(recyclerView.H(view2));
            }
            f1(view2, iJ, r82);
            int iC2 = this.f12081r.c(view2);
            if (iC2 > i17) {
                i17 = iC2;
            }
            float fD = (this.f12081r.d(view2) * 1.0f) / ((C2396p) view2.getLayoutParams()).f22330f;
            if (fD > f10) {
                f10 = fD;
            }
        }
        if (z6) {
            Z0(Math.max(Math.round(f10 * this.f12069F), i13));
            i17 = 0;
            for (int i19 = 0; i19 < i14; i19++) {
                View view3 = this.f12071H[i19];
                f1(view3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, true);
                int iC3 = this.f12081r.c(view3);
                if (iC3 > i17) {
                    i17 = iC3;
                }
            }
        }
        for (int i20 = 0; i20 < i14; i20++) {
            View view4 = this.f12071H[i20];
            if (this.f12081r.c(view4) != i17) {
                C2396p c2396p2 = (C2396p) view4.getLayoutParams();
                Rect rect2 = c2396p2.f22191b;
                int i21 = rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) c2396p2).topMargin + ((ViewGroup.MarginLayoutParams) c2396p2).bottomMargin;
                int i22 = rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) c2396p2).leftMargin + ((ViewGroup.MarginLayoutParams) c2396p2).rightMargin;
                int iB1 = b1(c2396p2.f22329e, c2396p2.f22330f);
                if (this.f12079p == 1) {
                    iW2 = AbstractC2367F.w(false, iB1, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i22, ((ViewGroup.MarginLayoutParams) c2396p2).width);
                    iW = View.MeasureSpec.makeMeasureSpec(i17 - i21, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i17 - i22, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    iW = AbstractC2367F.w(false, iB1, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i21, ((ViewGroup.MarginLayoutParams) c2396p2).height);
                    iW2 = iMakeMeasureSpec;
                }
                if (t0(view4, iW2, iW, (C2368G) view4.getLayoutParams())) {
                    view4.measure(iW2, iW);
                }
            }
        }
        c2398r.f22338a = i17;
        if (this.f12079p != 1) {
            if (c2399s.f22343f == -1) {
                int i23 = c2399s.f22341b;
                iA = i23 - i17;
                iD = i23;
            } else {
                int i24 = c2399s.f22341b;
                iD = i24 + i17;
                iA = i24;
            }
            iC = 0;
            iD2 = 0;
        } else if (c2399s.f22343f == -1) {
            iD2 = c2399s.f22341b;
            iC = iD2 - i17;
            iA = 0;
            iD = 0;
        } else {
            int i25 = c2399s.f22341b;
            iC = i25;
            iD = 0;
            iD2 = i25 + i17;
            iA = 0;
        }
        for (int i26 = 0; i26 < i14; i26++) {
            View view5 = this.f12071H[i26];
            C2396p c2396p3 = (C2396p) view5.getLayoutParams();
            if (this.f12079p != 1) {
                iC = C() + this.f12070G[c2396p3.f22329e];
                iD2 = this.f12081r.d(view5) + iC;
            } else if (N0()) {
                int iA2 = A() + this.f12070G[this.f12069F - c2396p3.f22329e];
                iD = iA2;
                iA = iA2 - this.f12081r.d(view5);
            } else {
                iA = A() + this.f12070G[c2396p3.f22329e];
                iD = this.f12081r.d(view5) + iA;
            }
            AbstractC2367F.J(view5, iA, iC, iD, iD2);
            if (c2396p3.f22190a.i() || c2396p3.f22190a.l()) {
                c2398r.c = true;
            }
            c2398r.d = view5.hasFocusable() | c2398r.d;
        }
        Arrays.fill(this.f12071H, (Object) null);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void P0(C1648i0 c1648i0, C2377P c2377p, x xVar, int i7) {
        g1();
        if (c2377p.b() > 0 && !c2377p.f22206g) {
            boolean z6 = i7 == 1;
            int iD1 = d1(xVar.c, c1648i0, c2377p);
            if (z6) {
                while (iD1 > 0) {
                    int i10 = xVar.c;
                    if (i10 <= 0) {
                        break;
                    }
                    int i11 = i10 - 1;
                    xVar.c = i11;
                    iD1 = d1(i11, c1648i0, c2377p);
                }
            } else {
                int iB = c2377p.b() - 1;
                int i12 = xVar.c;
                while (i12 < iB) {
                    int i13 = i12 + 1;
                    int iD12 = d1(i13, c1648i0, c2377p);
                    if (iD12 <= iD1) {
                        break;
                    }
                    i12 = i13;
                    iD1 = iD12;
                }
                xVar.c = i12;
            }
        }
        a1();
    }

    @Override // s3.AbstractC2367F
    public final void Q(C1648i0 c1648i0, C2377P c2377p, e eVar) {
        super.Q(c1648i0, c2377p, eVar);
        eVar.h("android.widget.GridView");
    }

    @Override // s3.AbstractC2367F
    public final void S(C1648i0 c1648i0, C2377P c2377p, View view, e eVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C2396p)) {
            R(view, eVar);
            return;
        }
        C2396p c2396p = (C2396p) layoutParams;
        int iC1 = c1(c2396p.f22190a.b(), c1648i0, c2377p);
        if (this.f12079p == 0) {
            eVar.j(O.f0(false, c2396p.f22329e, c2396p.f22330f, iC1, 1));
        } else {
            eVar.j(O.f0(false, iC1, 1, c2396p.f22329e, c2396p.f22330f));
        }
    }

    @Override // s3.AbstractC2367F
    public final void T(int i7, int i10) {
        b bVar = this.f12074K;
        bVar.s0();
        ((SparseIntArray) bVar.f18945m).clear();
    }

    @Override // s3.AbstractC2367F
    public final void U() {
        b bVar = this.f12074K;
        bVar.s0();
        ((SparseIntArray) bVar.f18945m).clear();
    }

    @Override // s3.AbstractC2367F
    public final void V(int i7, int i10) {
        b bVar = this.f12074K;
        bVar.s0();
        ((SparseIntArray) bVar.f18945m).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void V0(boolean z6) {
        if (z6) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.V0(false);
    }

    @Override // s3.AbstractC2367F
    public final void W(int i7, int i10) {
        b bVar = this.f12074K;
        bVar.s0();
        ((SparseIntArray) bVar.f18945m).clear();
    }

    @Override // s3.AbstractC2367F
    public final void X(int i7, int i10) {
        b bVar = this.f12074K;
        bVar.s0();
        ((SparseIntArray) bVar.f18945m).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final void Y(C1648i0 c1648i0, C2377P c2377p) {
        boolean z6 = c2377p.f22206g;
        SparseIntArray sparseIntArray = this.f12073J;
        SparseIntArray sparseIntArray2 = this.f12072I;
        if (z6) {
            int iV = v();
            for (int i7 = 0; i7 < iV; i7++) {
                C2396p c2396p = (C2396p) u(i7).getLayoutParams();
                int iB = c2396p.f22190a.b();
                sparseIntArray2.put(iB, c2396p.f22330f);
                sparseIntArray.put(iB, c2396p.f22329e);
            }
        }
        super.Y(c1648i0, c2377p);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final void Z(C2377P c2377p) {
        super.Z(c2377p);
        this.f12068E = false;
    }

    public final void Z0(int i7) {
        int i10;
        int[] iArr = this.f12070G;
        int i11 = this.f12069F;
        if (iArr == null || iArr.length != i11 + 1 || iArr[iArr.length - 1] != i7) {
            iArr = new int[i11 + 1];
        }
        int i12 = 0;
        iArr[0] = 0;
        int i13 = i7 / i11;
        int i14 = i7 % i11;
        int i15 = 0;
        for (int i16 = 1; i16 <= i11; i16++) {
            i12 += i14;
            if (i12 <= 0 || i11 - i12 >= i14) {
                i10 = i13;
            } else {
                i10 = i13 + 1;
                i12 -= i11;
            }
            i15 += i10;
            iArr[i16] = i15;
        }
        this.f12070G = iArr;
    }

    public final void a1() {
        View[] viewArr = this.f12071H;
        if (viewArr == null || viewArr.length != this.f12069F) {
            this.f12071H = new View[this.f12069F];
        }
    }

    public final int b1(int i7, int i10) {
        if (this.f12079p != 1 || !N0()) {
            int[] iArr = this.f12070G;
            return iArr[i10 + i7] - iArr[i7];
        }
        int[] iArr2 = this.f12070G;
        int i11 = this.f12069F;
        return iArr2[i11 - i7] - iArr2[(i11 - i7) - i10];
    }

    public final int c1(int i7, C1648i0 c1648i0, C2377P c2377p) {
        boolean z6 = c2377p.f22206g;
        b bVar = this.f12074K;
        if (!z6) {
            int i10 = this.f12069F;
            bVar.getClass();
            return b.r0(i7, i10);
        }
        int iB = c1648i0.b(i7);
        if (iB == -1) {
            a.r("Cannot find span size for pre layout position. ", i7, "GridLayoutManager");
            return 0;
        }
        int i11 = this.f12069F;
        bVar.getClass();
        return b.r0(iB, i11);
    }

    public final int d1(int i7, C1648i0 c1648i0, C2377P c2377p) {
        boolean z6 = c2377p.f22206g;
        b bVar = this.f12074K;
        if (!z6) {
            int i10 = this.f12069F;
            bVar.getClass();
            return i7 % i10;
        }
        int i11 = this.f12073J.get(i7, -1);
        if (i11 != -1) {
            return i11;
        }
        int iB = c1648i0.b(i7);
        if (iB == -1) {
            a.r("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i7, "GridLayoutManager");
            return 0;
        }
        int i12 = this.f12069F;
        bVar.getClass();
        return iB % i12;
    }

    public final int e1(int i7, C1648i0 c1648i0, C2377P c2377p) {
        boolean z6 = c2377p.f22206g;
        b bVar = this.f12074K;
        if (!z6) {
            bVar.getClass();
            return 1;
        }
        int i10 = this.f12072I.get(i7, -1);
        if (i10 != -1) {
            return i10;
        }
        if (c1648i0.b(i7) == -1) {
            a.r("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i7, "GridLayoutManager");
            return 1;
        }
        bVar.getClass();
        return 1;
    }

    @Override // s3.AbstractC2367F
    public final boolean f(C2368G c2368g) {
        return c2368g instanceof C2396p;
    }

    public final void f1(View view, int i7, boolean z6) {
        int iW;
        int iW2;
        C2396p c2396p = (C2396p) view.getLayoutParams();
        Rect rect = c2396p.f22191b;
        int i10 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) c2396p).topMargin + ((ViewGroup.MarginLayoutParams) c2396p).bottomMargin;
        int i11 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) c2396p).leftMargin + ((ViewGroup.MarginLayoutParams) c2396p).rightMargin;
        int iB1 = b1(c2396p.f22329e, c2396p.f22330f);
        if (this.f12079p == 1) {
            iW2 = AbstractC2367F.w(false, iB1, i7, i11, ((ViewGroup.MarginLayoutParams) c2396p).width);
            iW = AbstractC2367F.w(true, this.f12081r.l(), this.f22188m, i10, ((ViewGroup.MarginLayoutParams) c2396p).height);
        } else {
            int iW3 = AbstractC2367F.w(false, iB1, i7, i10, ((ViewGroup.MarginLayoutParams) c2396p).height);
            int iW4 = AbstractC2367F.w(true, this.f12081r.l(), this.l, i11, ((ViewGroup.MarginLayoutParams) c2396p).width);
            iW = iW3;
            iW2 = iW4;
        }
        C2368G c2368g = (C2368G) view.getLayoutParams();
        if (z6 ? t0(view, iW2, iW, c2368g) : r0(view, iW2, iW, c2368g)) {
            view.measure(iW2, iW);
        }
    }

    public final void g1() {
        int iZ;
        int iC;
        if (this.f12079p == 1) {
            iZ = this.f22189n - B();
            iC = A();
        } else {
            iZ = this.o - z();
            iC = C();
        }
        Z0(iZ - iC);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int j0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        g1();
        a1();
        return super.j0(i7, c1648i0, c2377p);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int k(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int l(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int l0(int i7, C1648i0 c1648i0, C2377P c2377p) {
        g1();
        a1();
        return super.l0(i7, c1648i0, c2377p);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int n(C2377P c2377p) {
        return z0(c2377p);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final int o(C2377P c2377p) {
        return A0(c2377p);
    }

    @Override // s3.AbstractC2367F
    public final void o0(Rect rect, int i7, int i10) {
        int iG;
        int iG2;
        if (this.f12070G == null) {
            super.o0(rect, i7, i10);
        }
        int iB = B() + A();
        int iZ = z() + C();
        if (this.f12079p == 1) {
            int iHeight = rect.height() + iZ;
            RecyclerView recyclerView = this.f22180b;
            int[] iArr = H.f2303a;
            iG2 = AbstractC2367F.g(i10, iHeight, recyclerView.getMinimumHeight());
            int[] iArr2 = this.f12070G;
            iG = AbstractC2367F.g(i7, iArr2[iArr2.length - 1] + iB, this.f22180b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iB;
            RecyclerView recyclerView2 = this.f22180b;
            int[] iArr3 = H.f2303a;
            iG = AbstractC2367F.g(i7, iWidth, recyclerView2.getMinimumWidth());
            int[] iArr4 = this.f12070G;
            iG2 = AbstractC2367F.g(i10, iArr4[iArr4.length - 1] + iZ, this.f22180b.getMinimumHeight());
        }
        this.f22180b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final C2368G r() {
        return this.f12079p == 0 ? new C2396p(-2, -1) : new C2396p(-1, -2);
    }

    @Override // s3.AbstractC2367F
    public final C2368G s(Context context, AttributeSet attributeSet) {
        C2396p c2396p = new C2396p(context, attributeSet);
        c2396p.f22329e = -1;
        c2396p.f22330f = 0;
        return c2396p;
    }

    @Override // s3.AbstractC2367F
    public final C2368G t(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            C2396p c2396p = new C2396p((ViewGroup.MarginLayoutParams) layoutParams);
            c2396p.f22329e = -1;
            c2396p.f22330f = 0;
            return c2396p;
        }
        C2396p c2396p2 = new C2396p(layoutParams);
        c2396p2.f22329e = -1;
        c2396p2.f22330f = 0;
        return c2396p2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, s3.AbstractC2367F
    public final boolean v0() {
        return this.f12089z == null && !this.f12068E;
    }

    @Override // s3.AbstractC2367F
    public final int x(C1648i0 c1648i0, C2377P c2377p) {
        if (this.f12079p == 1) {
            return this.f12069F;
        }
        if (c2377p.b() < 1) {
            return 0;
        }
        return c1(c2377p.b() - 1, c1648i0, c2377p) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void x0(C2377P c2377p, C2399s c2399s, C2393m c2393m) {
        int i7;
        int i10 = this.f12069F;
        for (int i11 = 0; i11 < this.f12069F && (i7 = c2399s.d) >= 0 && i7 < c2377p.b() && i10 > 0; i11++) {
            c2393m.b(c2399s.d, Math.max(0, c2399s.f22344g));
            this.f12074K.getClass();
            i10--;
            c2399s.d += c2399s.f22342e;
        }
    }
}
