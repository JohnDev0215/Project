package s3;

import A.s1;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import k3.C1648i0;
import l8.C1789g;
import r3.AbstractC2287a;

/* JADX INFO: renamed from: s3.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2367F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h3.y f22179a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f22180b;
    public final C1789g c;
    public final C1789g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C2402v f22181e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f22182f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f22183g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f22184h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f22185i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f22186j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f22187k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22188m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22189n;
    public int o;

    public AbstractC2367F() {
        C2365D c2365d = new C2365D(this, 0);
        C2365D c2365d2 = new C2365D(this, 1);
        this.c = new C1789g(c2365d);
        this.d = new C1789g(c2365d2);
        this.f22182f = false;
        this.f22183g = false;
        this.f22184h = true;
        this.f22185i = true;
    }

    public static int D(View view) {
        return ((C2368G) view.getLayoutParams()).f22190a.b();
    }

    public static C2366E E(Context context, AttributeSet attributeSet, int i7, int i10) {
        C2366E c2366e = new C2366E();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC2287a.f21808a, i7, i10);
        c2366e.f22177a = typedArrayObtainStyledAttributes.getInt(0, 1);
        c2366e.f22178b = typedArrayObtainStyledAttributes.getInt(10, 1);
        c2366e.c = typedArrayObtainStyledAttributes.getBoolean(9, false);
        c2366e.d = typedArrayObtainStyledAttributes.getBoolean(11, false);
        typedArrayObtainStyledAttributes.recycle();
        return c2366e;
    }

    public static boolean I(int i7, int i10, int i11) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        if (i11 > 0 && i7 != i11) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i7;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i7;
        }
        return true;
    }

    public static void J(View view, int i7, int i10, int i11, int i12) {
        C2368G c2368g = (C2368G) view.getLayoutParams();
        Rect rect = c2368g.f22191b;
        view.layout(i7 + rect.left + ((ViewGroup.MarginLayoutParams) c2368g).leftMargin, i10 + rect.top + ((ViewGroup.MarginLayoutParams) c2368g).topMargin, (i11 - rect.right) - ((ViewGroup.MarginLayoutParams) c2368g).rightMargin, (i12 - rect.bottom) - ((ViewGroup.MarginLayoutParams) c2368g).bottomMargin);
    }

    public static int g(int i7, int i10, int i11) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i10, i11) : size : Math.min(size, Math.max(i10, i11));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int w(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            int r5 = r5 - r7
            r7 = 0
            int r5 = java.lang.Math.max(r7, r5)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r4 == 0) goto L1d
            if (r8 < 0) goto L12
        L10:
            r6 = r3
            goto L30
        L12:
            if (r8 != r1) goto L1a
            if (r6 == r2) goto L22
            if (r6 == 0) goto L1a
            if (r6 == r3) goto L22
        L1a:
            r6 = r7
            r8 = r6
            goto L30
        L1d:
            if (r8 < 0) goto L20
            goto L10
        L20:
            if (r8 != r1) goto L24
        L22:
            r8 = r5
            goto L30
        L24:
            if (r8 != r0) goto L1a
            if (r6 == r2) goto L2e
            if (r6 != r3) goto L2b
            goto L2e
        L2b:
            r8 = r5
            r6 = r7
            goto L30
        L2e:
            r8 = r5
            r6 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.AbstractC2367F.w(boolean, int, int, int, int):int");
    }

    public static void y(View view, Rect rect) {
        int[] iArr = RecyclerView.f12090H0;
        C2368G c2368g = (C2368G) view.getLayoutParams();
        Rect rect2 = c2368g.f22191b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) c2368g).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) c2368g).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) c2368g).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) c2368g).bottomMargin);
    }

    public final int A() {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public final int B() {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public final int C() {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int F(C1648i0 c1648i0, C2377P c2377p) {
        return -1;
    }

    public final void G(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((C2368G) view.getLayoutParams()).f22191b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.f22180b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.f22180b.f12153v;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public abstract boolean H();

    public void K(int i7) {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            int iO = recyclerView.f12143q.o();
            for (int i10 = 0; i10 < iO; i10++) {
                recyclerView.f12143q.n(i10).offsetLeftAndRight(i7);
            }
        }
    }

    public void L(int i7) {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            int iO = recyclerView.f12143q.o();
            for (int i10 = 0; i10 < iO; i10++) {
                recyclerView.f12143q.n(i10).offsetTopAndBottom(i7);
            }
        }
    }

    public void M() {
    }

    public abstract void N(RecyclerView recyclerView);

    public abstract View O(View view, int i7, C1648i0 c1648i0, C2377P c2377p);

    public void P(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.f22180b;
        C1648i0 c1648i0 = recyclerView.f12138n;
        C2377P c2377p = recyclerView.f12140o0;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z6 = true;
        if (!recyclerView.canScrollVertically(1) && !this.f22180b.canScrollVertically(-1) && !this.f22180b.canScrollHorizontally(-1) && !this.f22180b.canScrollHorizontally(1)) {
            z6 = false;
        }
        accessibilityEvent.setScrollable(z6);
        AbstractC2405y abstractC2405y = this.f22180b.f12155w;
        if (abstractC2405y != null) {
            accessibilityEvent.setItemCount(abstractC2405y.a());
        }
    }

    public void Q(C1648i0 c1648i0, C2377P c2377p, G1.e eVar) {
        boolean zCanScrollVertically = this.f22180b.canScrollVertically(-1);
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        if (zCanScrollVertically || this.f22180b.canScrollHorizontally(-1)) {
            eVar.a(8192);
            accessibilityNodeInfo.setScrollable(true);
        }
        if (this.f22180b.canScrollVertically(1) || this.f22180b.canScrollHorizontally(1)) {
            eVar.a(4096);
            accessibilityNodeInfo.setScrollable(true);
        }
        eVar.i(B2.O.e0(F(c1648i0, c2377p), x(c1648i0, c2377p), 0));
    }

    public final void R(View view, G1.e eVar) {
        U uG = RecyclerView.G(view);
        if (uG == null || uG.i() || ((ArrayList) this.f22179a.o).contains(uG.f22219a)) {
            return;
        }
        RecyclerView recyclerView = this.f22180b;
        S(recyclerView.f12138n, recyclerView.f12140o0, view, eVar);
    }

    public void S(C1648i0 c1648i0, C2377P c2377p, View view, G1.e eVar) {
    }

    public void T(int i7, int i10) {
    }

    public void U() {
    }

    public void V(int i7, int i10) {
    }

    public void W(int i7, int i10) {
    }

    public void X(int i7, int i10) {
    }

    public abstract void Y(C1648i0 c1648i0, C2377P c2377p);

    public abstract void Z(C2377P c2377p);

    public abstract void a0(Parcelable parcelable);

    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.view.View r8, int r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.AbstractC2367F.b(android.view.View, int, boolean):void");
    }

    public abstract Parcelable b0();

    public abstract void c(String str);

    public void c0(int i7) {
    }

    public abstract boolean d();

    public final void d0(C1648i0 c1648i0) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            if (!RecyclerView.G(u(iV)).p()) {
                View viewU = u(iV);
                g0(iV);
                c1648i0.h(viewU);
            }
        }
    }

    public abstract boolean e();

    public final void e0(C1648i0 c1648i0) {
        ArrayList arrayList;
        int size = ((ArrayList) c1648i0.d).size();
        int i7 = size - 1;
        while (true) {
            arrayList = (ArrayList) c1648i0.d;
            if (i7 < 0) {
                break;
            }
            View view = ((U) arrayList.get(i7)).f22219a;
            U uG = RecyclerView.G(view);
            if (!uG.p()) {
                uG.o(false);
                if (uG.k()) {
                    this.f22180b.removeDetachedView(view, false);
                }
                AbstractC2364C abstractC2364C = this.f22180b.f12121U;
                if (abstractC2364C != null) {
                    abstractC2364C.d(uG);
                }
                uG.o(true);
                U uG2 = RecyclerView.G(view);
                uG2.f22229n = null;
                uG2.o = false;
                uG2.f22226j &= -33;
                c1648i0.i(uG2);
            }
            i7--;
        }
        arrayList.clear();
        ArrayList arrayList2 = (ArrayList) c1648i0.f17994e;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.f22180b.invalidate();
        }
    }

    public boolean f(C2368G c2368g) {
        return c2368g != null;
    }

    public final void f0(View view, C1648i0 c1648i0) {
        h3.y yVar = this.f22179a;
        C2404x c2404x = (C2404x) yVar.f15499m;
        int iIndexOfChild = c2404x.f22362a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            if (((s1) yVar.f15500n).v(iIndexOfChild)) {
                yVar.P(view);
            }
            c2404x.h(iIndexOfChild);
        }
        c1648i0.h(view);
    }

    public final void g0(int i7) {
        if (u(i7) != null) {
            h3.y yVar = this.f22179a;
            int iW = yVar.w(i7);
            C2404x c2404x = (C2404x) yVar.f15499m;
            View childAt = c2404x.f22362a.getChildAt(iW);
            if (childAt == null) {
                return;
            }
            if (((s1) yVar.f15500n).v(iW)) {
                yVar.P(childAt);
            }
            c2404x.h(iW);
        }
    }

    public abstract void h(int i7, int i10, C2377P c2377p, C2393m c2393m);

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.A()
            int r1 = r8.C()
            int r2 = r8.f22189n
            int r3 = r8.B()
            int r2 = r2 - r3
            int r3 = r8.o
            int r4 = r8.z()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            androidx.recyclerview.widget.RecyclerView r3 = r8.f22180b
            int[] r7 = F1.H.f2303a
            int r3 = r3.getLayoutDirection()
            r7 = 1
            if (r3 != r7) goto L60
            if (r2 == 0) goto L5b
            goto L68
        L5b:
            int r2 = java.lang.Math.max(r6, r10)
            goto L68
        L60:
            if (r6 == 0) goto L63
            goto L67
        L63:
            int r6 = java.lang.Math.min(r4, r2)
        L67:
            r2 = r6
        L68:
            if (r1 == 0) goto L6b
            goto L6f
        L6b:
            int r1 = java.lang.Math.min(r5, r11)
        L6f:
            int[] r10 = new int[]{r2, r1}
            r11 = r10[r0]
            r10 = r10[r7]
            if (r13 == 0) goto Lb2
            android.view.View r13 = r9.getFocusedChild()
            if (r13 != 0) goto L80
            goto Lb7
        L80:
            int r1 = r8.A()
            int r2 = r8.C()
            int r3 = r8.f22189n
            int r4 = r8.B()
            int r3 = r3 - r4
            int r4 = r8.o
            int r5 = r8.z()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r8 = r8.f22180b
            android.graphics.Rect r8 = r8.f12149t
            y(r13, r8)
            int r13 = r8.left
            int r13 = r13 - r11
            if (r13 >= r3) goto Lb7
            int r13 = r8.right
            int r13 = r13 - r11
            if (r13 <= r1) goto Lb7
            int r13 = r8.top
            int r13 = r13 - r10
            if (r13 >= r4) goto Lb7
            int r8 = r8.bottom
            int r8 = r8 - r10
            if (r8 > r2) goto Lb2
            goto Lb7
        Lb2:
            if (r11 != 0) goto Lb8
            if (r10 == 0) goto Lb7
            goto Lb8
        Lb7:
            return r0
        Lb8:
            if (r12 == 0) goto Lbe
            r9.scrollBy(r11, r10)
            goto Lc1
        Lbe:
            r9.Z(r11, r10, r0)
        Lc1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.AbstractC2367F.h0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public void i(int i7, C2393m c2393m) {
    }

    public final void i0() {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public abstract int j(C2377P c2377p);

    public abstract int j0(int i7, C1648i0 c1648i0, C2377P c2377p);

    public abstract int k(C2377P c2377p);

    public abstract void k0(int i7);

    public abstract int l(C2377P c2377p);

    public abstract int l0(int i7, C1648i0 c1648i0, C2377P c2377p);

    public abstract int m(C2377P c2377p);

    public final void m0(RecyclerView recyclerView) {
        n0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    public abstract int n(C2377P c2377p);

    public final void n0(int i7, int i10) {
        this.f22189n = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        this.l = mode;
        if (mode == 0 && !RecyclerView.f12092J0) {
            this.f22189n = 0;
        }
        this.o = View.MeasureSpec.getSize(i10);
        int mode2 = View.MeasureSpec.getMode(i10);
        this.f22188m = mode2;
        if (mode2 != 0 || RecyclerView.f12092J0) {
            return;
        }
        this.o = 0;
    }

    public abstract int o(C2377P c2377p);

    public void o0(Rect rect, int i7, int i10) {
        int iB = B() + A() + rect.width();
        int iZ = z() + C() + rect.height();
        RecyclerView recyclerView = this.f22180b;
        int[] iArr = F1.H.f2303a;
        this.f22180b.setMeasuredDimension(g(i7, iB, recyclerView.getMinimumWidth()), g(i10, iZ, this.f22180b.getMinimumHeight()));
    }

    public final void p(C1648i0 c1648i0) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            U uG = RecyclerView.G(viewU);
            if (!uG.p()) {
                if (!uG.g() || uG.i() || this.f22180b.f12155w.f22364b) {
                    u(iV);
                    this.f22179a.j(iV);
                    c1648i0.j(viewU);
                    this.f22180b.f12145r.w0(uG);
                } else {
                    g0(iV);
                    c1648i0.i(uG);
                }
            }
        }
    }

    public final void p0(int i7, int i10) {
        int iV = v();
        if (iV == 0) {
            this.f22180b.l(i7, i10);
            return;
        }
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MAX_VALUE;
        int i13 = Integer.MIN_VALUE;
        int i14 = Integer.MAX_VALUE;
        for (int i15 = 0; i15 < iV; i15++) {
            View viewU = u(i15);
            Rect rect = this.f22180b.f12149t;
            y(viewU, rect);
            int i16 = rect.left;
            if (i16 < i14) {
                i14 = i16;
            }
            int i17 = rect.right;
            if (i17 > i11) {
                i11 = i17;
            }
            int i18 = rect.top;
            if (i18 < i12) {
                i12 = i18;
            }
            int i19 = rect.bottom;
            if (i19 > i13) {
                i13 = i19;
            }
        }
        this.f22180b.f12149t.set(i14, i12, i11, i13);
        o0(this.f22180b.f12149t, i7, i10);
    }

    public View q(int i7) {
        int iV = v();
        for (int i10 = 0; i10 < iV; i10++) {
            View viewU = u(i10);
            U uG = RecyclerView.G(viewU);
            if (uG != null && uG.b() == i7 && !uG.p() && (this.f22180b.f12140o0.f22206g || !uG.i())) {
                return viewU;
            }
        }
        return null;
    }

    public final void q0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.f22180b = null;
            this.f22179a = null;
            this.f22189n = 0;
            this.o = 0;
        } else {
            this.f22180b = recyclerView;
            this.f22179a = recyclerView.f12143q;
            this.f22189n = recyclerView.getWidth();
            this.o = recyclerView.getHeight();
        }
        this.l = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        this.f22188m = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
    }

    public abstract C2368G r();

    public final boolean r0(View view, int i7, int i10, C2368G c2368g) {
        return (!view.isLayoutRequested() && this.f22184h && I(view.getWidth(), i7, ((ViewGroup.MarginLayoutParams) c2368g).width) && I(view.getHeight(), i10, ((ViewGroup.MarginLayoutParams) c2368g).height)) ? false : true;
    }

    public C2368G s(Context context, AttributeSet attributeSet) {
        return new C2368G(context, attributeSet);
    }

    public boolean s0() {
        return false;
    }

    public C2368G t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2368G ? new C2368G((C2368G) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C2368G((ViewGroup.MarginLayoutParams) layoutParams) : new C2368G(layoutParams);
    }

    public final boolean t0(View view, int i7, int i10, C2368G c2368g) {
        return (this.f22184h && I(view.getMeasuredWidth(), i7, ((ViewGroup.MarginLayoutParams) c2368g).width) && I(view.getMeasuredHeight(), i10, ((ViewGroup.MarginLayoutParams) c2368g).height)) ? false : true;
    }

    public final View u(int i7) {
        h3.y yVar = this.f22179a;
        if (yVar != null) {
            return yVar.n(i7);
        }
        return null;
    }

    public final void u0(C2402v c2402v) {
        C2402v c2402v2 = this.f22181e;
        if (c2402v2 != null && c2402v != c2402v2 && c2402v2.f22353e) {
            c2402v2.c();
        }
        this.f22181e = c2402v;
        RecyclerView recyclerView = this.f22180b;
        RunnableC2380T runnableC2380T = recyclerView.f12135l0;
        runnableC2380T.f22217r.removeCallbacks(runnableC2380T);
        runnableC2380T.f22214n.abortAnimation();
        if (c2402v.f22356h) {
            h6.n.t("RecyclerView", "An instance of " + c2402v.getClass().getSimpleName() + " was started more than once. Each instance of" + c2402v.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        }
        c2402v.f22352b = recyclerView;
        c2402v.c = this;
        int i7 = c2402v.f22351a;
        if (i7 == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.f12140o0.f22202a = i7;
        c2402v.f22353e = true;
        c2402v.d = true;
        c2402v.f22354f = recyclerView.f12157x.q(i7);
        c2402v.f22352b.f12135l0.b();
        c2402v.f22356h = true;
    }

    public final int v() {
        h3.y yVar = this.f22179a;
        if (yVar != null) {
            return yVar.o();
        }
        return 0;
    }

    public abstract boolean v0();

    public int x(C1648i0 c1648i0, C2377P c2377p) {
        return -1;
    }

    public final int z() {
        RecyclerView recyclerView = this.f22180b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }
}
