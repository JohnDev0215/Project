package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class y12 {
    public is a;
    public RecyclerView b;
    public final hu2 c;
    public final hu2 d;
    public qa1 e;
    public boolean f;
    public boolean g;
    public final boolean h;
    public final boolean i;
    public int j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;

    public y12() {
        du duVar = new du(0, this);
        du duVar2 = new du(1, this);
        this.c = new hu2(duVar);
        this.d = new hu2(duVar2);
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = true;
    }

    public static int D(View view) {
        return ((z12) view.getLayoutParams()).a.c();
    }

    public static x12 E(Context context, AttributeSet attributeSet, int i, int i2) {
        x12 x12Var = new x12();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, wz1.a, i, i2);
        x12Var.a = typedArrayObtainStyledAttributes.getInt(0, 1);
        x12Var.b = typedArrayObtainStyledAttributes.getInt(10, 1);
        x12Var.c = typedArrayObtainStyledAttributes.getBoolean(9, false);
        x12Var.d = typedArrayObtainStyledAttributes.getBoolean(11, false);
        typedArrayObtainStyledAttributes.recycle();
        return x12Var;
    }

    public static boolean I(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    public static void J(View view, int i, int i2, int i3, int i4) {
        z12 z12Var = (z12) view.getLayoutParams();
        Rect rect = z12Var.b;
        view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) z12Var).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) z12Var).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) z12Var).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) z12Var).bottomMargin);
    }

    public static int g(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int w(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L1d
            if (r7 < 0) goto L12
        L10:
            r5 = r3
            goto L30
        L12:
            if (r7 != r1) goto L1a
            if (r5 == r2) goto L22
            if (r5 == 0) goto L1a
            if (r5 == r3) goto L22
        L1a:
            r5 = r6
            r7 = r5
            goto L30
        L1d:
            if (r7 < 0) goto L20
            goto L10
        L20:
            if (r7 != r1) goto L24
        L22:
            r7 = r4
            goto L30
        L24:
            if (r7 != r0) goto L1a
            if (r5 == r2) goto L2e
            if (r5 != r3) goto L2b
            goto L2e
        L2b:
            r7 = r4
            r5 = r6
            goto L30
        L2e:
            r7 = r4
            r5 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p.y12.w(int, int, int, int, boolean):int");
    }

    public static void y(View view, Rect rect) {
        int[] iArr = RecyclerView.A0;
        z12 z12Var = (z12) view.getLayoutParams();
        Rect rect2 = z12Var.b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) z12Var).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) z12Var).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) z12Var).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) z12Var).bottomMargin);
    }

    public final int A() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public final int B() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public final int C() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int F(f22 f22Var, k22 k22Var) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || recyclerView.s == null || !e()) {
            return 1;
        }
        return this.b.s.a();
    }

    public final void G(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((z12) view.getLayoutParams()).b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.b.r;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public abstract boolean H();

    public void K(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iE = recyclerView.m.e();
            for (int i2 = 0; i2 < iE; i2++) {
                recyclerView.m.d(i2).offsetLeftAndRight(i);
            }
        }
    }

    public void L(int i) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            int iE = recyclerView.m.e();
            for (int i2 = 0; i2 < iE; i2++) {
                recyclerView.m.d(i2).offsetTopAndBottom(i);
            }
        }
    }

    public abstract void M(RecyclerView recyclerView);

    public abstract View N(View view, int i, f22 f22Var, k22 k22Var);

    public void O(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.b;
        f22 f22Var = recyclerView.e;
        k22 k22Var = recyclerView.k0;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z = true;
        if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        q12 q12Var = this.b.s;
        if (q12Var != null) {
            accessibilityEvent.setItemCount(q12Var.a());
        }
    }

    public final void P(View view, c3 c3Var) {
        n22 n22VarI = RecyclerView.I(view);
        if (n22VarI == null || n22VarI.j() || this.a.c.contains(n22VarI.a)) {
            return;
        }
        RecyclerView recyclerView = this.b;
        Q(recyclerView.e, recyclerView.k0, view, c3Var);
    }

    public void Q(f22 f22Var, k22 k22Var, View view, c3 c3Var) {
        c3Var.h(b3.a(e() ? D(view) : 0, 1, d() ? D(view) : 0, 1, false));
    }

    public void R(int i, int i2) {
    }

    public void S() {
    }

    public void T(int i, int i2) {
    }

    public void U(int i, int i2) {
    }

    public void V(int i, int i2) {
    }

    public abstract void W(f22 f22Var, k22 k22Var);

    public abstract void X(k22 k22Var);

    public abstract void Y(Parcelable parcelable);

    public abstract Parcelable Z();

    public void a0(int i) {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.view.View r7, int r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.y12.b(android.view.View, int, boolean):void");
    }

    public final void b0(f22 f22Var) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            if (!RecyclerView.I(u(iV)).q()) {
                View viewU = u(iV);
                e0(iV);
                f22Var.f(viewU);
            }
        }
    }

    public abstract void c(String str);

    public final void c0(f22 f22Var) {
        Object obj;
        int size = ((ArrayList) f22Var.d).size();
        int i = size - 1;
        while (true) {
            obj = f22Var.d;
            if (i < 0) {
                break;
            }
            View view = ((n22) ((ArrayList) obj).get(i)).a;
            n22 n22VarI = RecyclerView.I(view);
            if (!n22VarI.q()) {
                n22VarI.p(false);
                if (n22VarI.l()) {
                    this.b.removeDetachedView(view, false);
                }
                v12 v12Var = this.b.P;
                if (v12Var != null) {
                    v12Var.d(n22VarI);
                }
                n22VarI.p(true);
                n22 n22VarI2 = RecyclerView.I(view);
                n22VarI2.n = null;
                n22VarI2.o = false;
                n22VarI2.j &= -33;
                f22Var.g(n22VarI2);
            }
            i--;
        }
        ((ArrayList) obj).clear();
        ArrayList arrayList = (ArrayList) f22Var.e;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    public abstract boolean d();

    public final void d0(View view, f22 f22Var) {
        is isVar = this.a;
        p12 p12Var = isVar.a;
        int iIndexOfChild = p12Var.a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            if (isVar.b.f(iIndexOfChild)) {
                isVar.k(view);
            }
            p12Var.h(iIndexOfChild);
        }
        f22Var.f(view);
    }

    public abstract boolean e();

    public final void e0(int i) {
        if (u(i) != null) {
            is isVar = this.a;
            int iF = isVar.f(i);
            p12 p12Var = isVar.a;
            View childAt = p12Var.a.getChildAt(iF);
            if (childAt == null) {
                return;
            }
            if (isVar.b.f(iF)) {
                isVar.k(childAt);
            }
            p12Var.h(iF);
        }
    }

    public boolean f(z12 z12Var) {
        return z12Var != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.A()
            int r1 = r8.C()
            int r2 = r8.n
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
            androidx.recyclerview.widget.RecyclerView r3 = r8.b
            java.util.WeakHashMap r7 = p.zt2.a
            int r3 = p.jt2.d(r3)
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
            if (r13 == 0) goto Laa
            android.view.View r10 = r9.getFocusedChild()
            if (r10 != 0) goto L78
            goto Laf
        L78:
            int r11 = r8.A()
            int r13 = r8.C()
            int r3 = r8.n
            int r4 = r8.B()
            int r3 = r3 - r4
            int r4 = r8.o
            int r5 = r8.z()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r8.b
            android.graphics.Rect r5 = r5.f20p
            y(r10, r5)
            int r10 = r5.left
            int r10 = r10 - r2
            if (r10 >= r3) goto Laf
            int r10 = r5.right
            int r10 = r10 - r2
            if (r10 <= r11) goto Laf
            int r10 = r5.top
            int r10 = r10 - r1
            if (r10 >= r4) goto Laf
            int r10 = r5.bottom
            int r10 = r10 - r1
            if (r10 > r13) goto Laa
            goto Laf
        Laa:
            if (r2 != 0) goto Lb0
            if (r1 == 0) goto Laf
            goto Lb0
        Laf:
            return r0
        Lb0:
            if (r12 == 0) goto Lb6
            r9.scrollBy(r2, r1)
            goto Lb9
        Lb6:
            r9.b0(r2, r1, r0)
        Lb9:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p.y12.f0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public final void g0() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public abstract void h(int i, int i2, k22 k22Var, b01 b01Var);

    public abstract int h0(int i, f22 f22Var, k22 k22Var);

    public void i(int i, b01 b01Var) {
    }

    public abstract void i0(int i);

    public abstract int j(k22 k22Var);

    public abstract int j0(int i, f22 f22Var, k22 k22Var);

    public abstract int k(k22 k22Var);

    public final void k0(RecyclerView recyclerView) {
        l0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public abstract int l(k22 k22Var);

    public final void l0(int i, int i2) {
        this.n = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        this.l = mode;
        if (mode == 0) {
            int[] iArr = RecyclerView.A0;
        }
        this.o = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.m = mode2;
        if (mode2 == 0) {
            int[] iArr2 = RecyclerView.A0;
        }
    }

    public abstract int m(k22 k22Var);

    public void m0(Rect rect, int i, int i2) {
        int iB = B() + A() + rect.width();
        int iZ = z() + C() + rect.height();
        RecyclerView recyclerView = this.b;
        WeakHashMap weakHashMap = zt2.a;
        this.b.setMeasuredDimension(g(i, iB, it2.e(recyclerView)), g(i2, iZ, it2.d(this.b)));
    }

    public abstract int n(k22 k22Var);

    public final void n0(int i, int i2) {
        int iV = v();
        if (iV == 0) {
            this.b.n(i, i2);
            return;
        }
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        int i6 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < iV; i7++) {
            View viewU = u(i7);
            Rect rect = this.b.f20p;
            y(viewU, rect);
            int i8 = rect.left;
            if (i8 < i6) {
                i6 = i8;
            }
            int i9 = rect.right;
            if (i9 > i3) {
                i3 = i9;
            }
            int i10 = rect.top;
            if (i10 < i4) {
                i4 = i10;
            }
            int i11 = rect.bottom;
            if (i11 > i5) {
                i5 = i11;
            }
        }
        this.b.f20p.set(i6, i4, i3, i5);
        m0(this.b.f20p, i, i2);
    }

    public abstract int o(k22 k22Var);

    public final void o0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.b = null;
            this.a = null;
            this.n = 0;
            this.o = 0;
        } else {
            this.b = recyclerView;
            this.a = recyclerView.m;
            this.n = recyclerView.getWidth();
            this.o = recyclerView.getHeight();
        }
        this.l = 1073741824;
        this.m = 1073741824;
    }

    public final void p(f22 f22Var) {
        for (int iV = v() - 1; iV >= 0; iV--) {
            View viewU = u(iV);
            n22 n22VarI = RecyclerView.I(viewU);
            if (!n22VarI.q()) {
                if (!n22VarI.h() || n22VarI.j() || this.b.s.b) {
                    u(iV);
                    this.a.c(iV);
                    f22Var.h(viewU);
                    this.b.n.l(n22VarI);
                } else {
                    e0(iV);
                    f22Var.g(n22VarI);
                }
            }
        }
    }

    public final boolean p0(View view, int i, int i2, z12 z12Var) {
        return (!view.isLayoutRequested() && this.h && I(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) z12Var).width) && I(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) z12Var).height)) ? false : true;
    }

    public View q(int i) {
        int iV = v();
        for (int i2 = 0; i2 < iV; i2++) {
            View viewU = u(i2);
            n22 n22VarI = RecyclerView.I(viewU);
            if (n22VarI != null && n22VarI.c() == i && !n22VarI.q() && (this.b.k0.g || !n22VarI.j())) {
                return viewU;
            }
        }
        return null;
    }

    public boolean q0() {
        return false;
    }

    public abstract z12 r();

    public final boolean r0(View view, int i, int i2, z12 z12Var) {
        return (this.h && I(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) z12Var).width) && I(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) z12Var).height)) ? false : true;
    }

    public z12 s(Context context, AttributeSet attributeSet) {
        return new z12(context, attributeSet);
    }

    public abstract void s0(RecyclerView recyclerView, int i);

    public z12 t(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof z12 ? new z12((z12) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new z12((ViewGroup.MarginLayoutParams) layoutParams) : new z12(layoutParams);
    }

    public final void t0(qa1 qa1Var) {
        qa1 qa1Var2 = this.e;
        if (qa1Var2 != null && qa1Var != qa1Var2 && qa1Var2.e) {
            qa1Var2.f();
        }
        this.e = qa1Var;
        RecyclerView recyclerView = this.b;
        m22 m22Var = recyclerView.h0;
        m22Var.o.removeCallbacks(m22Var);
        m22Var.k.abortAnimation();
        qa1Var.b = recyclerView;
        qa1Var.c = this;
        int i = qa1Var.a;
        if (i == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.k0.a = i;
        qa1Var.e = true;
        qa1Var.d = true;
        qa1Var.f = recyclerView.t.q(i);
        qa1Var.b.h0.a();
    }

    public final View u(int i) {
        is isVar = this.a;
        if (isVar != null) {
            return isVar.d(i);
        }
        return null;
    }

    public abstract boolean u0();

    public final int v() {
        is isVar = this.a;
        if (isVar != null) {
            return isVar.e();
        }
        return 0;
    }

    public int x(f22 f22Var, k22 k22Var) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || recyclerView.s == null || !d()) {
            return 1;
        }
        return this.b.s.a();
    }

    public final int z() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }
}
