package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import defpackage.C0175b;
import defpackage.gq2;
import defpackage.in3;
import defpackage.om3;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean E;
    public int F;
    public int[] G;
    public View[] H;
    public final SparseIntArray I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final SparseIntArray f18J;
    public b K;
    public final Rect L;

    public static final class a extends b {
        @Override // androidx.recyclerview.widget.GridLayoutManager.b
        public final int b(int i, int i2) {
            return i % i2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.b
        public final int c(int i) {
            return 1;
        }
    }

    public static abstract class b {
        public final SparseIntArray a = new SparseIntArray();
        public final SparseIntArray b = new SparseIntArray();

        public final int a(int i, int i2) {
            int iC = c(i);
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int iC2 = c(i5);
                i3 += iC2;
                if (i3 == i2) {
                    i4++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i4++;
                    i3 = iC2;
                }
            }
            return i3 + iC > i2 ? i4 + 1 : i4;
        }

        public int b(int i, int i2) {
            int iC = c(i);
            if (iC == i2) {
                return 0;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                int iC2 = c(i4);
                i3 += iC2;
                if (i3 == i2) {
                    i3 = 0;
                } else if (i3 > i2) {
                    i3 = iC2;
                }
            }
            if (iC + i3 <= i2) {
                return i3;
            }
            return 0;
        }

        public abstract int c(int i);

        public final void d() {
            this.a.clear();
        }
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.f18J = new SparseIntArray();
        this.K = new a();
        this.L = new Rect();
        s1(RecyclerView.m.L(context, attributeSet, i, i2).b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int C(RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.p == 1) {
            return Math.min(this.F, D());
        }
        if (wVar.b() < 1) {
            return 0;
        }
        return o1(wVar.b() - 1, sVar, wVar) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public boolean H0() {
        return this.z == null && !this.E;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void J0(RecyclerView.w wVar, LinearLayoutManager.c cVar, RecyclerView.m.c cVar2) {
        int iC = this.F;
        for (int i = 0; i < this.F; i++) {
            int i2 = cVar.d;
            if (!(i2 >= 0 && i2 < wVar.b()) || iC <= 0) {
                return;
            }
            int i3 = cVar.d;
            ((k.b) cVar2).a(i3, Math.max(0, cVar.g));
            iC -= this.K.c(i3);
            cVar.d += cVar.e;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int N(RecyclerView.s sVar, RecyclerView.w wVar) {
        if (this.p == 0) {
            return Math.min(this.F, D());
        }
        if (wVar.b() < 1) {
            return 0;
        }
        return o1(wVar.b() - 1, sVar, wVar) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View U0(RecyclerView.s sVar, RecyclerView.w wVar, boolean z, boolean z2) {
        int i;
        int iA;
        int iA2 = A();
        int i2 = 1;
        if (z2) {
            iA = A() - 1;
            i = -1;
            i2 = -1;
        } else {
            i = iA2;
            iA = 0;
        }
        int iB = wVar.b();
        O0();
        int iK = this.r.k();
        int iG = this.r.g();
        View view = null;
        View view2 = null;
        while (iA != i) {
            View viewZ = z(iA);
            int iK2 = RecyclerView.m.K(viewZ);
            if (iK2 >= 0 && iK2 < iB && p1(iK2, sVar, wVar) == 0) {
                if (((RecyclerView.LayoutParams) viewZ.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = viewZ;
                    }
                } else {
                    if (this.r.e(viewZ) < iG && this.r.b(viewZ) >= iK) {
                        return viewZ;
                    }
                    if (view == null) {
                        view = viewZ;
                    }
                }
            }
            iA += i2;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00e1, code lost:
    
        if (r13 == (r2 > r9)) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011c  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View X(android.view.View r23, int r24, androidx.recyclerview.widget.RecyclerView.s r25, androidx.recyclerview.widget.RecyclerView.w r26) {
        /*
            Method dump skipped, instruction units count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.X(android.view.View, int, androidx.recyclerview.widget.RecyclerView$s, androidx.recyclerview.widget.RecyclerView$w):android.view.View");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final void Z(RecyclerView.s sVar, RecyclerView.w wVar, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.Z(sVar, wVar, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.n(GridView.class.getName());
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void a1(RecyclerView.s sVar, RecyclerView.w wVar, LinearLayoutManager.c cVar, LinearLayoutManager.b bVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int iD;
        int iJ;
        int iH;
        int iD2;
        int i5;
        int i6;
        int iB;
        int iB2;
        boolean z;
        View viewB;
        int iJ2 = this.r.j();
        boolean z2 = iJ2 != 1073741824;
        int i7 = A() > 0 ? this.G[this.F] : 0;
        if (z2) {
            t1();
        }
        boolean z3 = cVar.e == 1;
        int iP1 = this.F;
        if (!z3) {
            iP1 = p1(cVar.d, sVar, wVar) + q1(cVar.d, sVar, wVar);
        }
        int i8 = 0;
        while (i8 < this.F) {
            int i9 = cVar.d;
            if (!(i9 >= 0 && i9 < wVar.b()) || iP1 <= 0) {
                break;
            }
            int i10 = cVar.d;
            int iQ1 = q1(i10, sVar, wVar);
            if (iQ1 > this.F) {
                throw new IllegalArgumentException("Item at position " + i10 + " requires " + iQ1 + " spans but GridLayoutManager has only " + this.F + " spans.");
            }
            iP1 -= iQ1;
            if (iP1 < 0 || (viewB = cVar.b(sVar)) == null) {
                break;
            }
            this.H[i8] = viewB;
            i8++;
        }
        if (i8 == 0) {
            bVar.b = true;
            return;
        }
        if (z3) {
            i3 = 1;
            i2 = i8;
            i = 0;
        } else {
            i = i8 - 1;
            i2 = -1;
            i3 = -1;
        }
        int i11 = 0;
        while (i != i2) {
            View view = this.H[i];
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int iQ12 = q1(RecyclerView.m.K(view), sVar, wVar);
            layoutParams.f = iQ12;
            layoutParams.e = i11;
            i11 += iQ12;
            i += i3;
        }
        float f = 0.0f;
        int i12 = 0;
        for (int i13 = 0; i13 < i8; i13++) {
            View view2 = this.H[i13];
            if (cVar.k != null) {
                z = false;
                if (z3) {
                    e(view2, -1, true);
                } else {
                    e(view2, 0, true);
                }
            } else if (z3) {
                z = false;
                e(view2, -1, false);
            } else {
                z = false;
                e(view2, 0, false);
            }
            g(view2, this.L);
            r1(view2, iJ2, z);
            int iC = this.r.c(view2);
            if (iC > i12) {
                i12 = iC;
            }
            float fD = (this.r.d(view2) * 1.0f) / ((LayoutParams) view2.getLayoutParams()).f;
            if (fD > f) {
                f = fD;
            }
        }
        if (z2) {
            m1(Math.max(Math.round(f * this.F), i7));
            i12 = 0;
            for (int i14 = 0; i14 < i8; i14++) {
                View view3 = this.H[i14];
                r1(view3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, true);
                int iC2 = this.r.c(view3);
                if (iC2 > i12) {
                    i12 = iC2;
                }
            }
        }
        for (int i15 = 0; i15 < i8; i15++) {
            View view4 = this.H[i15];
            if (this.r.c(view4) != i12) {
                LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
                Rect rect = layoutParams2.b;
                int i16 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                int i17 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                int iN1 = n1(layoutParams2.e, layoutParams2.f);
                if (this.p == 1) {
                    iB2 = RecyclerView.m.B(false, iN1, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i17, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
                    iB = View.MeasureSpec.makeMeasureSpec(i12 - i16, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12 - i17, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                    iB = RecyclerView.m.B(false, iN1, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED, i16, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
                    iB2 = iMakeMeasureSpec;
                }
                if (E0(view4, iB2, iB, (RecyclerView.LayoutParams) view4.getLayoutParams())) {
                    view4.measure(iB2, iB);
                }
            }
        }
        bVar.a = i12;
        if (this.p == 1) {
            if (cVar.f == -1) {
                i6 = cVar.b;
                i5 = i6 - i12;
            } else {
                int i18 = cVar.b;
                i5 = i18;
                i6 = i12 + i18;
            }
            iH = 0;
            iJ = i5;
            iD2 = i6;
            iD = 0;
        } else {
            if (cVar.f == -1) {
                iD = cVar.b;
                i4 = iD - i12;
            } else {
                int i19 = cVar.b;
                i4 = i19;
                iD = i12 + i19;
            }
            iJ = 0;
            iH = i4;
            iD2 = 0;
        }
        for (int i20 = 0; i20 < i8; i20++) {
            View view5 = this.H[i20];
            LayoutParams layoutParams3 = (LayoutParams) view5.getLayoutParams();
            if (this.p != 1) {
                iJ = J() + this.G[layoutParams3.e];
                iD2 = this.r.d(view5) + iJ;
            } else if (Z0()) {
                iD = H() + this.G[this.F - layoutParams3.e];
                iH = iD - this.r.d(view5);
            } else {
                iH = this.G[layoutParams3.e] + H();
                iD = this.r.d(view5) + iH;
            }
            RecyclerView.m.S(view5, iH, iJ, iD, iD2);
            if (layoutParams3.c() || layoutParams3.b()) {
                bVar.c = true;
            }
            bVar.d = view5.hasFocusable() | bVar.d;
        }
        Arrays.fill(this.H, (Object) null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void b0(RecyclerView.s sVar, RecyclerView.w wVar, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            a0(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int iO1 = o1(layoutParams2.a(), sVar, wVar);
        if (this.p == 0) {
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(layoutParams2.e, layoutParams2.f, iO1, 1, false, false));
        } else {
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(iO1, 1, layoutParams2.e, layoutParams2.f, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void b1(RecyclerView.s sVar, RecyclerView.w wVar, LinearLayoutManager.a aVar, int i) {
        t1();
        if (wVar.b() > 0 && !wVar.g) {
            boolean z = i == 1;
            int iP1 = p1(aVar.b, sVar, wVar);
            if (z) {
                while (iP1 > 0) {
                    int i2 = aVar.b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    aVar.b = i3;
                    iP1 = p1(i3, sVar, wVar);
                }
            } else {
                int iB = wVar.b() - 1;
                int i4 = aVar.b;
                while (i4 < iB) {
                    int i5 = i4 + 1;
                    int iP12 = p1(i5, sVar, wVar);
                    if (iP12 <= iP1) {
                        break;
                    }
                    i4 = i5;
                    iP1 = iP12;
                }
                aVar.b = i4;
            }
        }
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void c0(int i, int i2) {
        this.K.d();
        this.K.b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void d0() {
        this.K.d();
        this.K.b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void e0(int i, int i2) {
        this.K.d();
        this.K.b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void f0(int i, int i2) {
        this.K.d();
        this.K.b.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void g0(int i, int i2) {
        this.K.d();
        this.K.b.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void h0(RecyclerView.s sVar, RecyclerView.w wVar) {
        boolean z = wVar.g;
        SparseIntArray sparseIntArray = this.f18J;
        SparseIntArray sparseIntArray2 = this.I;
        if (z) {
            int iA = A();
            for (int i = 0; i < iA; i++) {
                LayoutParams layoutParams = (LayoutParams) z(i).getLayoutParams();
                int iA2 = layoutParams.a();
                sparseIntArray2.put(iA2, layoutParams.f);
                sparseIntArray.put(iA2, layoutParams.e);
            }
        }
        super.h0(sVar, wVar);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final void i0(RecyclerView.w wVar) {
        super.i0(wVar);
        this.E = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void i1(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.i1(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final boolean j(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final boolean m0(int i, Bundle bundle) {
        if (i != 16908343 || bundle == null) {
            return super.m0(i, bundle);
        }
        int i2 = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
        int i3 = bundle.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
        if (i2 != -1 && i3 != -1) {
            int iK = this.b.q.k();
            int i4 = 0;
            while (true) {
                if (i4 >= iK) {
                    i4 = -1;
                    break;
                }
                RecyclerView recyclerView = this.b;
                int iP1 = p1(i4, recyclerView.g, recyclerView.m0);
                RecyclerView recyclerView2 = this.b;
                int iO1 = o1(i4, recyclerView2.g, recyclerView2.m0);
                if (this.p != 1) {
                    if (iP1 == i2 && iO1 == i3) {
                        break;
                    }
                    i4++;
                } else {
                    if (iP1 == i3 && iO1 == i2) {
                        break;
                    }
                    i4++;
                }
            }
            if (i4 > -1) {
                g1(i4, 0);
                return true;
            }
        }
        return false;
    }

    public final void m1(int i) {
        int i2;
        int[] iArr = this.G;
        int i3 = this.F;
        if (iArr == null || iArr.length != i3 + 1 || iArr[iArr.length - 1] != i) {
            iArr = new int[i3 + 1];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 <= 0 || i3 - i4 >= i6) {
                i2 = i5;
            } else {
                i2 = i5 + 1;
                i4 -= i3;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.G = iArr;
    }

    public final int n1(int i, int i2) {
        if (this.p != 1 || !Z0()) {
            int[] iArr = this.G;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.G;
        int i3 = this.F;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int o(RecyclerView.w wVar) {
        return L0(wVar);
    }

    public final int o1(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (!wVar.g) {
            return this.K.a(i, this.F);
        }
        int iB = sVar.b(i);
        if (iB != -1) {
            return this.K.a(iB, this.F);
        }
        C0175b.s("Cannot find span size for pre layout position. ", i, "GridLayoutManager");
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int p(RecyclerView.w wVar) {
        return M0(wVar);
    }

    public final int p1(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (!wVar.g) {
            return this.K.b(i, this.F);
        }
        int i2 = this.f18J.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iB = sVar.b(i);
        if (iB != -1) {
            return this.K.b(iB, this.F);
        }
        C0175b.s("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i, "GridLayoutManager");
        return 0;
    }

    public final int q1(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        if (!wVar.g) {
            return this.K.c(i);
        }
        int i2 = this.I.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iB = sVar.b(i);
        if (iB != -1) {
            return this.K.c(iB);
        }
        C0175b.s("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:", i, "GridLayoutManager");
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int r(RecyclerView.w wVar) {
        return L0(wVar);
    }

    public final void r1(View view, int i, boolean z) {
        int iB;
        int iB2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int iN1 = n1(layoutParams.e, layoutParams.f);
        if (this.p == 1) {
            iB2 = RecyclerView.m.B(false, iN1, i, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width);
            iB = RecyclerView.m.B(true, this.r.l(), this.m, i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
        } else {
            int iB3 = RecyclerView.m.B(false, iN1, i, i2, ((ViewGroup.MarginLayoutParams) layoutParams).height);
            int iB4 = RecyclerView.m.B(true, this.r.l(), this.l, i3, ((ViewGroup.MarginLayoutParams) layoutParams).width);
            iB = iB3;
            iB2 = iB4;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z ? E0(view, iB2, iB, layoutParams2) : C0(view, iB2, iB, layoutParams2)) {
            view.measure(iB2, iB);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int s(RecyclerView.w wVar) {
        return M0(wVar);
    }

    public final void s1(int i) {
        if (i == this.F) {
            return;
        }
        this.E = true;
        if (i < 1) {
            throw new IllegalArgumentException(gq2.e("Span count should be at least 1. Provided ", i));
        }
        this.F = i;
        this.K.d();
        t0();
    }

    public final void t1() {
        int iG;
        int iJ;
        if (this.p == 1) {
            iG = this.n - I();
            iJ = H();
        } else {
            iG = this.o - G();
            iJ = J();
        }
        m1(iG - iJ);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int u0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        t1();
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
        return super.u0(i, sVar, wVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams v() {
        return this.p == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams w(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final int w0(int i, RecyclerView.s sVar, RecyclerView.w wVar) {
        t1();
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
        return super.w0(i, sVar, wVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final RecyclerView.LayoutParams x(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final void z0(Rect rect, int i, int i2) {
        int iK;
        int iK2;
        if (this.G == null) {
            super.z0(rect, i, i2);
        }
        int I = I() + H();
        int iG = G() + J();
        if (this.p == 1) {
            int iHeight = rect.height() + iG;
            RecyclerView recyclerView = this.b;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            iK2 = RecyclerView.m.k(i2, iHeight, om3.d.c(recyclerView));
            int[] iArr = this.G;
            iK = RecyclerView.m.k(i, iArr[iArr.length - 1] + I, om3.d.d(this.b));
        } else {
            int iWidth = rect.width() + I;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            iK = RecyclerView.m.k(i, iWidth, om3.d.d(recyclerView2));
            int[] iArr2 = this.G;
            iK2 = RecyclerView.m.k(i2, iArr2[iArr2.length - 1] + iG, om3.d.c(this.b));
        }
        this.b.setMeasuredDimension(iK, iK2);
    }

    public static class LayoutParams extends RecyclerView.LayoutParams {
        public int e;
        public int f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.e = -1;
            this.f = 0;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.e = -1;
            this.f = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.e = -1;
            this.f = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.e = -1;
            this.f = 0;
        }
    }

    public GridLayoutManager(Context context, int i) {
        super(context);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.f18J = new SparseIntArray();
        this.K = new a();
        this.L = new Rect();
        s1(i);
    }

    public GridLayoutManager(int i) {
        super(1);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.f18J = new SparseIntArray();
        this.K = new a();
        this.L = new Rect();
        s1(i);
    }
}
