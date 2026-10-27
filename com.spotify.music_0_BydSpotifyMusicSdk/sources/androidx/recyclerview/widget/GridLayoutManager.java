package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.WeakHashMap;
import p.a11;
import p.b01;
import p.b3;
import p.c3;
import p.f22;
import p.it2;
import p.k22;
import p.la1;
import p.na1;
import p.u4;
import p.xl2;
import p.y12;
import p.z12;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean E;
    public int F;
    public int[] G;
    public View[] H;
    public final SparseIntArray I;
    public final SparseIntArray J;
    public final xl2 K;
    public final Rect L;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new xl2(1);
        this.L = new Rect();
        g1(y12.E(context, attributeSet, i, i2).b);
    }

    @Override // p.y12
    public final int F(f22 f22Var, k22 k22Var) {
        if (this.f19p == 0) {
            return this.F;
        }
        if (k22Var.b() < 1) {
            return 0;
        }
        return c1(k22Var.b() - 1, f22Var, k22Var) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View I0(f22 f22Var, k22 k22Var, int i, int i2, int i3) {
        B0();
        int iF = this.r.f();
        int iE = this.r.e();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View viewU = u(i);
            int iD = y12.D(viewU);
            if (iD >= 0 && iD < i3 && d1(iD, f22Var, k22Var) == 0) {
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

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00df, code lost:
    
        if (r13 == (r2 > r15)) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0126  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View N(android.view.View r23, int r24, p.f22 r25, p.k22 r26) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.N(android.view.View, int, p.f22, p.k22):android.view.View");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a4  */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v38 */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void O0(p.f22 r19, p.k22 r20, p.na1 r21, p.ma1 r22) {
        /*
            Method dump skipped, instruction units count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.O0(p.f22, p.k22, p.na1, p.ma1):void");
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void P0(f22 f22Var, k22 k22Var, la1 la1Var, int i) {
        h1();
        if (k22Var.b() > 0 && !k22Var.g) {
            boolean z = i == 1;
            int iD1 = d1(la1Var.b, f22Var, k22Var);
            if (z) {
                while (iD1 > 0) {
                    int i2 = la1Var.b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    la1Var.b = i3;
                    iD1 = d1(i3, f22Var, k22Var);
                }
            } else {
                int iB = k22Var.b() - 1;
                int i4 = la1Var.b;
                while (i4 < iB) {
                    int i5 = i4 + 1;
                    int iD12 = d1(i5, f22Var, k22Var);
                    if (iD12 <= iD1) {
                        break;
                    }
                    i4 = i5;
                    iD1 = iD12;
                }
                la1Var.b = i4;
            }
        }
        a1();
    }

    @Override // p.y12
    public final void Q(f22 f22Var, k22 k22Var, View view, c3 c3Var) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof a11)) {
            P(view, c3Var);
            return;
        }
        a11 a11Var = (a11) layoutParams;
        int iC1 = c1(a11Var.a.c(), f22Var, k22Var);
        if (this.f19p == 0) {
            c3Var.h(b3.a(a11Var.e, a11Var.f, iC1, 1, false));
        } else {
            c3Var.h(b3.a(iC1, 1, a11Var.e, a11Var.f, false));
        }
    }

    @Override // p.y12
    public final void R(int i, int i2) {
        xl2 xl2Var = this.K;
        xl2Var.d();
        ((SparseIntArray) xl2Var.d).clear();
    }

    @Override // p.y12
    public final void S() {
        xl2 xl2Var = this.K;
        xl2Var.d();
        ((SparseIntArray) xl2Var.d).clear();
    }

    @Override // p.y12
    public final void T(int i, int i2) {
        xl2 xl2Var = this.K;
        xl2Var.d();
        ((SparseIntArray) xl2Var.d).clear();
    }

    @Override // p.y12
    public final void U(int i, int i2) {
        xl2 xl2Var = this.K;
        xl2Var.d();
        ((SparseIntArray) xl2Var.d).clear();
    }

    @Override // p.y12
    public final void V(int i, int i2) {
        xl2 xl2Var = this.K;
        xl2Var.d();
        ((SparseIntArray) xl2Var.d).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void V0(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.V0(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final void W(f22 f22Var, k22 k22Var) {
        boolean z = k22Var.g;
        SparseIntArray sparseIntArray = this.J;
        SparseIntArray sparseIntArray2 = this.I;
        if (z) {
            int iV = v();
            for (int i = 0; i < iV; i++) {
                a11 a11Var = (a11) u(i).getLayoutParams();
                int iC = a11Var.a.c();
                sparseIntArray2.put(iC, a11Var.f);
                sparseIntArray.put(iC, a11Var.e);
            }
        }
        super.W(f22Var, k22Var);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final void X(k22 k22Var) {
        super.X(k22Var);
        this.E = false;
    }

    public final void Z0(int i) {
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

    public final void a1() {
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
    }

    public final int b1(int i, int i2) {
        if (this.f19p != 1 || !N0()) {
            int[] iArr = this.G;
            return iArr[i2 + i] - iArr[i];
        }
        int[] iArr2 = this.G;
        int i3 = this.F;
        return iArr2[i3 - i] - iArr2[(i3 - i) - i2];
    }

    public final int c1(int i, f22 f22Var, k22 k22Var) {
        boolean z = k22Var.g;
        xl2 xl2Var = this.K;
        if (!z) {
            return xl2Var.a(i, this.F);
        }
        int iB = f22Var.b(i);
        if (iB == -1) {
            return 0;
        }
        return xl2Var.a(iB, this.F);
    }

    public final int d1(int i, f22 f22Var, k22 k22Var) {
        boolean z = k22Var.g;
        xl2 xl2Var = this.K;
        if (!z) {
            return xl2Var.b(i, this.F);
        }
        int i2 = this.J.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int iB = f22Var.b(i);
        if (iB == -1) {
            return 0;
        }
        return xl2Var.b(iB, this.F);
    }

    public final int e1(int i, f22 f22Var, k22 k22Var) {
        boolean z = k22Var.g;
        xl2 xl2Var = this.K;
        if (!z) {
            xl2Var.getClass();
            return 1;
        }
        int i2 = this.I.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        if (f22Var.b(i) == -1) {
            return 1;
        }
        xl2Var.getClass();
        return 1;
    }

    @Override // p.y12
    public final boolean f(z12 z12Var) {
        return z12Var instanceof a11;
    }

    public final void f1(View view, int i, boolean z) {
        int iW;
        int iW2;
        a11 a11Var = (a11) view.getLayoutParams();
        Rect rect = a11Var.b;
        int i2 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) a11Var).topMargin + ((ViewGroup.MarginLayoutParams) a11Var).bottomMargin;
        int i3 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) a11Var).leftMargin + ((ViewGroup.MarginLayoutParams) a11Var).rightMargin;
        int iB1 = b1(a11Var.e, a11Var.f);
        if (this.f19p == 1) {
            iW2 = y12.w(iB1, i, i3, ((ViewGroup.MarginLayoutParams) a11Var).width, false);
            iW = y12.w(this.r.g(), this.m, i2, ((ViewGroup.MarginLayoutParams) a11Var).height, true);
        } else {
            int iW3 = y12.w(iB1, i, i2, ((ViewGroup.MarginLayoutParams) a11Var).height, false);
            int iW4 = y12.w(this.r.g(), this.l, i3, ((ViewGroup.MarginLayoutParams) a11Var).width, true);
            iW = iW3;
            iW2 = iW4;
        }
        z12 z12Var = (z12) view.getLayoutParams();
        if (z ? r0(view, iW2, iW, z12Var) : p0(view, iW2, iW, z12Var)) {
            view.measure(iW2, iW);
        }
    }

    public final void g1(int i) {
        if (i == this.F) {
            return;
        }
        this.E = true;
        if (i < 1) {
            throw new IllegalArgumentException(u4.e("Span count should be at least 1. Provided ", i));
        }
        this.F = i;
        this.K.d();
        g0();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int h0(int i, f22 f22Var, k22 k22Var) {
        h1();
        a1();
        return super.h0(i, f22Var, k22Var);
    }

    public final void h1() {
        int iZ;
        int iC;
        if (this.f19p == 1) {
            iZ = this.n - B();
            iC = A();
        } else {
            iZ = this.o - z();
            iC = C();
        }
        Z0(iZ - iC);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int j0(int i, f22 f22Var, k22 k22Var) {
        h1();
        a1();
        return super.j0(i, f22Var, k22Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int k(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int l(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // p.y12
    public final void m0(Rect rect, int i, int i2) {
        int iG;
        int iG2;
        if (this.G == null) {
            super.m0(rect, i, i2);
        }
        int iB = B() + A();
        int iZ = z() + C();
        if (this.f19p == 1) {
            int iHeight = rect.height() + iZ;
            RecyclerView recyclerView = this.b;
            WeakHashMap weakHashMap = zt2.a;
            iG2 = y12.g(i2, iHeight, it2.d(recyclerView));
            int[] iArr = this.G;
            iG = y12.g(i, iArr[iArr.length - 1] + iB, it2.e(this.b));
        } else {
            int iWidth = rect.width() + iB;
            RecyclerView recyclerView2 = this.b;
            WeakHashMap weakHashMap2 = zt2.a;
            iG = y12.g(i, iWidth, it2.e(recyclerView2));
            int[] iArr2 = this.G;
            iG2 = y12.g(i2, iArr2[iArr2.length - 1] + iZ, it2.d(this.b));
        }
        this.b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int n(k22 k22Var) {
        return y0(k22Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final int o(k22 k22Var) {
        return z0(k22Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final z12 r() {
        return this.f19p == 0 ? new a11(-2, -1) : new a11(-1, -2);
    }

    @Override // p.y12
    public final z12 s(Context context, AttributeSet attributeSet) {
        a11 a11Var = new a11(context, attributeSet);
        a11Var.e = -1;
        a11Var.f = 0;
        return a11Var;
    }

    @Override // p.y12
    public final z12 t(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            a11 a11Var = new a11((ViewGroup.MarginLayoutParams) layoutParams);
            a11Var.e = -1;
            a11Var.f = 0;
            return a11Var;
        }
        a11 a11Var2 = new a11(layoutParams);
        a11Var2.e = -1;
        a11Var2.f = 0;
        return a11Var2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final boolean u0() {
        return this.z == null && !this.E;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void w0(k22 k22Var, na1 na1Var, b01 b01Var) {
        int i;
        int i2 = this.F;
        for (int i3 = 0; i3 < this.F && (i = na1Var.d) >= 0 && i < k22Var.b() && i2 > 0; i3++) {
            b01Var.a(na1Var.d, Math.max(0, na1Var.g));
            this.K.getClass();
            i2--;
            na1Var.d += na1Var.e;
        }
    }

    @Override // p.y12
    public final int x(f22 f22Var, k22 k22Var) {
        if (this.f19p == 1) {
            return this.F;
        }
        if (k22Var.b() < 1) {
            return 0;
        }
        return c1(k22Var.b() - 1, f22Var, k22Var) + 1;
    }

    public GridLayoutManager(int i) {
        super(1);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new xl2(1);
        this.L = new Rect();
        g1(i);
    }
}
