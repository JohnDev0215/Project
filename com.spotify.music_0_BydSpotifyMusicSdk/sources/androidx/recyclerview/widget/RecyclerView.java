package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import p.a22;
import p.a5;
import p.b01;
import p.b22;
import p.b5;
import p.c22;
import p.cu2;
import p.d01;
import p.d22;
import p.e22;
import p.f22;
import p.g22;
import p.gm0;
import p.gu2;
import p.h22;
import p.hu2;
import p.is;
import p.it2;
import p.k22;
import p.km2;
import p.l22;
import p.lm2;
import p.m22;
import p.n22;
import p.o12;
import p.ot2;
import p.p12;
import p.p2;
import p.p22;
import p.pm1;
import p.q12;
import p.qa1;
import p.s12;
import p.t12;
import p.u12;
import p.u4;
import p.v12;
import p.w12;
import p.wd1;
import p.y12;
import p.z12;
import p.zb0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup {
    public static final int[] A0 = {R.attr.nestedScrollingEnabled};
    public static final Class[] B0;
    public static final cu2 C0;
    public int A;
    public boolean B;
    public boolean C;
    public boolean D;
    public int E;
    public final AccessibilityManager F;
    public boolean G;
    public boolean H;
    public int I;
    public int J;
    public t12 K;
    public EdgeEffect L;
    public EdgeEffect M;
    public EdgeEffect N;
    public EdgeEffect O;
    public v12 P;
    public int Q;
    public int R;
    public VelocityTracker S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int a0;
    public a22 b0;
    public final zb0 c;
    public final int c0;
    public final int d0;
    public final f22 e;
    public final float e0;
    public final float f0;
    public boolean g0;
    public final m22 h0;
    public d01 i0;
    public final b01 j0;
    public h22 k;
    public final k22 k0;
    public final b5 l;
    public c22 l0;
    public final is m;
    public ArrayList m0;
    public final hu2 n;
    public boolean n0;
    public boolean o;
    public boolean o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f20p;
    public final p12 p0;
    public final Rect q;
    public boolean q0;
    public final RectF r;
    public p22 r0;
    public q12 s;
    public final int[] s0;
    public y12 t;
    public pm1 t0;
    public final ArrayList u;
    public final int[] u0;
    public final ArrayList v;
    public final int[] v0;
    public b22 w;
    public final int[] w0;
    public boolean x;
    public final ArrayList x0;
    public boolean y;
    public final o12 y0;
    public boolean z;
    public final p12 z0;

    static {
        Class cls = Integer.TYPE;
        B0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        C0 = new cu2(1);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.spotify.music.R.attr.recyclerViewStyle);
    }

    public static RecyclerView D(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView recyclerViewD = D(viewGroup.getChildAt(i));
            if (recyclerViewD != null) {
                return recyclerViewD;
            }
        }
        return null;
    }

    public static n22 I(View view) {
        if (view == null) {
            return null;
        }
        return ((z12) view.getLayoutParams()).a;
    }

    private pm1 getScrollingChildHelper() {
        if (this.t0 == null) {
            this.t0 = new pm1(this);
        }
        return this.t0;
    }

    public static void j(n22 n22Var) {
        WeakReference weakReference = n22Var.b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == n22Var.a) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            n22Var.b = null;
        }
    }

    public final View A(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public final boolean B(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        ArrayList arrayList = this.v;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            b22 b22Var = (b22) arrayList.get(i);
            gm0 gm0Var = (gm0) b22Var;
            int i2 = gm0Var.v;
            if (i2 == 1) {
                boolean zD = gm0Var.d(motionEvent.getX(), motionEvent.getY());
                boolean zC = gm0Var.c(motionEvent.getX(), motionEvent.getY());
                if (motionEvent.getAction() == 0 && (zD || zC)) {
                    if (zC) {
                        gm0Var.w = 1;
                        gm0Var.f125p = (int) motionEvent.getX();
                    } else if (zD) {
                        gm0Var.w = 2;
                        gm0Var.m = (int) motionEvent.getY();
                    }
                    gm0Var.f(2);
                }
            } else {
                z = i2 == 2;
            }
            if (z && action != 3) {
                this.w = b22Var;
                return true;
            }
        }
        return false;
    }

    public final void C(int[] iArr) {
        int iE = this.m.e();
        if (iE == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < iE; i3++) {
            n22 n22VarI = I(this.m.d(i3));
            if (!n22VarI.q()) {
                int iC = n22VarI.c();
                if (iC < i) {
                    i = iC;
                }
                if (iC > i2) {
                    i2 = iC;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public final n22 E(int i) {
        n22 n22Var = null;
        if (this.G) {
            return null;
        }
        int iH = this.m.h();
        for (int i2 = 0; i2 < iH; i2++) {
            n22 n22VarI = I(this.m.g(i2));
            if (n22VarI != null && !n22VarI.j() && F(n22VarI) == i) {
                if (!this.m.j(n22VarI.a)) {
                    return n22VarI;
                }
                n22Var = n22VarI;
            }
        }
        return n22Var;
    }

    public final int F(n22 n22Var) {
        if (n22Var.e(524) || !n22Var.g()) {
            return -1;
        }
        b5 b5Var = this.l;
        int i = n22Var.c;
        ArrayList arrayList = b5Var.b;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            a5 a5Var = (a5) arrayList.get(i2);
            int i3 = a5Var.a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = a5Var.b;
                    if (i4 <= i) {
                        int i5 = a5Var.d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = a5Var.b;
                    if (i6 == i) {
                        i = a5Var.d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (a5Var.d <= i) {
                            i++;
                        }
                    }
                }
            } else if (a5Var.b <= i) {
                i += a5Var.d;
            }
        }
        return i;
    }

    public final long G(n22 n22Var) {
        return this.s.b ? n22Var.e : n22Var.c;
    }

    public final n22 H(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return I(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect J(View view) {
        z12 z12Var = (z12) view.getLayoutParams();
        boolean z = z12Var.c;
        Rect rect = z12Var.b;
        if (!z) {
            return rect;
        }
        if (this.k0.g && (z12Var.a.m() || z12Var.a.h())) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Rect rect2 = this.f20p;
            rect2.set(0, 0, 0, 0);
            ((w12) arrayList.get(i)).getClass();
            ((z12) view.getLayoutParams()).a.c();
            rect2.set(0, 0, 0, 0);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        z12Var.c = false;
        return rect;
    }

    public final boolean K() {
        return !this.z || this.G || this.l.g();
    }

    public final boolean L() {
        return this.I > 0;
    }

    public final void M(int i) {
        if (this.t == null) {
            return;
        }
        setScrollState(2);
        this.t.i0(i);
        awakenScrollBars();
    }

    public final void N() {
        int iH = this.m.h();
        for (int i = 0; i < iH; i++) {
            ((z12) this.m.g(i).getLayoutParams()).c = true;
        }
        ArrayList arrayList = (ArrayList) this.e.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            z12 z12Var = (z12) ((n22) arrayList.get(i2)).a.getLayoutParams();
            if (z12Var != null) {
                z12Var.c = true;
            }
        }
    }

    public final void O(int i, int i2, boolean z) {
        int i3 = i + i2;
        int iH = this.m.h();
        for (int i4 = 0; i4 < iH; i4++) {
            n22 n22VarI = I(this.m.g(i4));
            if (n22VarI != null && !n22VarI.q()) {
                int i5 = n22VarI.c;
                k22 k22Var = this.k0;
                if (i5 >= i3) {
                    n22VarI.n(-i2, z);
                    k22Var.f = true;
                } else if (i5 >= i) {
                    n22VarI.b(8);
                    n22VarI.n(-i2, z);
                    n22VarI.c = i - 1;
                    k22Var.f = true;
                }
            }
        }
        f22 f22Var = this.e;
        ArrayList arrayList = (ArrayList) f22Var.f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n22 n22Var = (n22) arrayList.get(size);
            if (n22Var != null) {
                int i6 = n22Var.c;
                if (i6 >= i3) {
                    n22Var.n(-i2, z);
                } else if (i6 >= i) {
                    n22Var.b(8);
                    f22Var.e(size);
                }
            }
        }
        requestLayout();
    }

    public final void P() {
        this.I++;
    }

    public final void Q(boolean z) {
        int i;
        AccessibilityManager accessibilityManager;
        int i2 = this.I - 1;
        this.I = i2;
        if (i2 < 1) {
            this.I = 0;
            if (z) {
                int i3 = this.E;
                this.E = 0;
                if (i3 != 0 && (accessibilityManager = this.F) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    p2.b(accessibilityEventObtain, i3);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.x0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    n22 n22Var = (n22) arrayList.get(size);
                    if (n22Var.a.getParent() == this && !n22Var.q() && (i = n22Var.q) != -1) {
                        WeakHashMap weakHashMap = zt2.a;
                        it2.s(n22Var.a, i);
                        n22Var.q = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void R(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.R) {
            int i = actionIndex == 0 ? 1 : 0;
            this.R = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.V = x;
            this.T = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.W = y;
            this.U = y;
        }
    }

    public final void S() {
        if (this.q0 || !this.x) {
            return;
        }
        WeakHashMap weakHashMap = zt2.a;
        it2.m(this, this.y0);
        this.q0 = true;
    }

    public final void T() {
        boolean z;
        if (this.G) {
            b5 b5Var = this.l;
            b5Var.l(b5Var.b);
            b5Var.l(b5Var.c);
            if (this.H) {
                this.t.S();
            }
        }
        if (this.P == null || !this.t.u0()) {
            this.l.c();
        } else {
            this.l.j();
        }
        boolean z2 = this.n0 || this.o0;
        boolean z3 = this.z && this.P != null && ((z = this.G) || z2 || this.t.f) && (!z || this.s.b);
        k22 k22Var = this.k0;
        k22Var.j = z3;
        k22Var.k = z3 && z2 && !this.G && this.P != null && this.t.u0();
    }

    public final void U(boolean z) {
        this.H = z | this.H;
        this.G = true;
        int iH = this.m.h();
        for (int i = 0; i < iH; i++) {
            n22 n22VarI = I(this.m.g(i));
            if (n22VarI != null && !n22VarI.q()) {
                n22VarI.b(6);
            }
        }
        N();
        f22 f22Var = this.e;
        ArrayList arrayList = (ArrayList) f22Var.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n22 n22Var = (n22) arrayList.get(i2);
            if (n22Var != null) {
                n22Var.b(6);
                n22Var.a(null);
            }
        }
        q12 q12Var = ((RecyclerView) f22Var.i).s;
        if (q12Var == null || !q12Var.b) {
            f22Var.d();
        }
    }

    public final void V(n22 n22Var, u12 u12Var) {
        n22Var.j &= -8193;
        boolean z = this.k0.h;
        hu2 hu2Var = this.n;
        if (z && n22Var.m() && !n22Var.j() && !n22Var.q()) {
            ((wd1) hu2Var.c).g(G(n22Var), n22Var);
        }
        hu2Var.c(n22Var, u12Var);
    }

    public final void W(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.f20p;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof z12) {
            z12 z12Var = (z12) layoutParams;
            if (!z12Var.c) {
                int i = rect.left;
                Rect rect2 = z12Var.b;
                rect.left = i - rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.t.f0(this, view, this.f20p, !this.z, view2 == null);
    }

    public final void X() {
        VelocityTracker velocityTracker = this.S;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        e0(0);
        EdgeEffect edgeEffect = this.L;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.L.isFinished();
        }
        EdgeEffect edgeEffect2 = this.M;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.M.isFinished();
        }
        EdgeEffect edgeEffect3 = this.N;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.N.isFinished();
        }
        EdgeEffect edgeEffect4 = this.O;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = zt2.a;
            it2.k(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean Y(int r19, int r20, android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Y(int, int, android.view.MotionEvent):boolean");
    }

    public final void Z(int i, int i2, int[] iArr) {
        n22 n22Var;
        c0();
        P();
        int i3 = lm2.a;
        km2.a("RV Scroll");
        k22 k22Var = this.k0;
        z(k22Var);
        f22 f22Var = this.e;
        int iH0 = i != 0 ? this.t.h0(i, f22Var, k22Var) : 0;
        int iJ0 = i2 != 0 ? this.t.j0(i2, f22Var, k22Var) : 0;
        km2.b();
        int iE = this.m.e();
        for (int i4 = 0; i4 < iE; i4++) {
            View viewD = this.m.d(i4);
            n22 n22VarH = H(viewD);
            if (n22VarH != null && (n22Var = n22VarH.i) != null) {
                int left = viewD.getLeft();
                int top = viewD.getTop();
                View view = n22Var.a;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        Q(true);
        d0(false);
        if (iArr != null) {
            iArr[0] = iH0;
            iArr[1] = iJ0;
        }
    }

    public final void a0(int i) {
        qa1 qa1Var;
        if (this.C) {
            return;
        }
        setScrollState(0);
        m22 m22Var = this.h0;
        m22Var.o.removeCallbacks(m22Var);
        m22Var.k.abortAnimation();
        y12 y12Var = this.t;
        if (y12Var != null && (qa1Var = y12Var.e) != null) {
            qa1Var.f();
        }
        y12 y12Var2 = this.t;
        if (y12Var2 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            y12Var2.i0(i);
            awakenScrollBars();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        y12 y12Var = this.t;
        if (y12Var != null) {
            y12Var.getClass();
        }
        super.addFocusables(arrayList, i, i2);
    }

    public final void b0(int i, int i2, boolean z) {
        y12 y12Var = this.t;
        if (y12Var == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        if (!y12Var.d()) {
            i = 0;
        }
        if (!this.t.e()) {
            i2 = 0;
        }
        if (i == 0 && i2 == 0) {
            return;
        }
        if (z) {
            int i3 = i != 0 ? 1 : 0;
            if (i2 != 0) {
                i3 |= 2;
            }
            getScrollingChildHelper().h(i3, 1);
        }
        this.h0.b(i, i2, Integer.MIN_VALUE, null);
    }

    public final void c0() {
        int i = this.A + 1;
        this.A = i;
        if (i != 1 || this.C) {
            return;
        }
        this.B = false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof z12) && this.t.f((z12) layoutParams);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.d()) {
            return this.t.j(this.k0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.d()) {
            return this.t.k(this.k0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.d()) {
            return this.t.l(this.k0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.e()) {
            return this.t.m(this.k0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.e()) {
            return this.t.n(this.k0);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        y12 y12Var = this.t;
        if (y12Var != null && y12Var.e()) {
            return this.t.o(this.k0);
        }
        return 0;
    }

    public final void d0(boolean z) {
        if (this.A < 1) {
            this.A = 1;
        }
        if (!z && !this.C) {
            this.B = false;
        }
        if (this.A == 1) {
            if (z && this.B && !this.C && this.t != null && this.s != null) {
                o();
            }
            if (!this.C) {
                this.B = false;
            }
        }
        this.A--;
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().a(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().b(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().e(i, i2, i3, i4, iArr, 0, null);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ((w12) arrayList.get(i)).b(canvas);
        }
        EdgeEffect edgeEffect = this.L;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.o ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.L;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.M;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.o) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.M;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.N;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.o ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.N;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.O;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.o) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.O;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(iSave4);
        }
        if ((z || this.P == null || arrayList.size() <= 0 || !this.P.f()) && !z) {
            return;
        }
        WeakHashMap weakHashMap = zt2.a;
        it2.k(this);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public final void e0(int i) {
        getScrollingChildHelper().i(i);
    }

    public final void f(n22 n22Var) {
        View view = n22Var.a;
        boolean z = view.getParent() == this;
        this.e.j(H(view));
        if (n22Var.l()) {
            this.m.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z) {
            this.m.a(view, -1, true);
            return;
        }
        is isVar = this.m;
        int iIndexOfChild = isVar.a.a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            isVar.b.h(iIndexOfChild);
            isVar.i(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007b  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View focusSearch(android.view.View r18, int r19) {
        /*
            Method dump skipped, instruction units count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public final void g(w12 w12Var) {
        y12 y12Var = this.t;
        if (y12Var != null) {
            y12Var.c("Cannot add item decoration during a scroll  or layout");
        }
        ArrayList arrayList = this.u;
        if (arrayList.isEmpty()) {
            setWillNotDraw(false);
        }
        arrayList.add(w12Var);
        N();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        y12 y12Var = this.t;
        if (y12Var != null) {
            return y12Var.r();
        }
        throw new IllegalStateException(u4.c(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        y12 y12Var = this.t;
        if (y12Var != null) {
            return y12Var.s(getContext(), attributeSet);
        }
        throw new IllegalStateException(u4.c(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public q12 getAdapter() {
        return this.s;
    }

    @Override // android.view.View
    public int getBaseline() {
        y12 y12Var = this.t;
        if (y12Var == null) {
            return super.getBaseline();
        }
        y12Var.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.o;
    }

    public p22 getCompatAccessibilityDelegate() {
        return this.r0;
    }

    public t12 getEdgeEffectFactory() {
        return this.K;
    }

    public v12 getItemAnimator() {
        return this.P;
    }

    public int getItemDecorationCount() {
        return this.u.size();
    }

    public y12 getLayoutManager() {
        return this.t;
    }

    public int getMaxFlingVelocity() {
        return this.d0;
    }

    public int getMinFlingVelocity() {
        return this.c0;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public a22 getOnFlingListener() {
        return this.b0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.g0;
    }

    public e22 getRecycledViewPool() {
        return this.e.c();
    }

    public int getScrollState() {
        return this.Q;
    }

    public final void h(c22 c22Var) {
        if (this.m0 == null) {
            this.m0 = new ArrayList();
        }
        this.m0.add(c22Var);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().g(0);
    }

    public final void i(String str) {
        if (L()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(u4.c(this, new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.J > 0) {
            new IllegalStateException(u4.c(this, new StringBuilder("")));
        }
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.x;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.C;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void k() {
        int iH = this.m.h();
        for (int i = 0; i < iH; i++) {
            n22 n22VarI = I(this.m.g(i));
            if (!n22VarI.q()) {
                n22VarI.d = -1;
                n22VarI.g = -1;
            }
        }
        f22 f22Var = this.e;
        ArrayList arrayList = (ArrayList) f22Var.f;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            n22 n22Var = (n22) arrayList.get(i2);
            n22Var.d = -1;
            n22Var.g = -1;
        }
        ArrayList arrayList2 = (ArrayList) f22Var.d;
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            n22 n22Var2 = (n22) arrayList2.get(i3);
            n22Var2.d = -1;
            n22Var2.g = -1;
        }
        ArrayList arrayList3 = (ArrayList) f22Var.e;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i4 = 0; i4 < size3; i4++) {
                n22 n22Var3 = (n22) ((ArrayList) f22Var.e).get(i4);
                n22Var3.d = -1;
                n22Var3.g = -1;
            }
        }
    }

    public final void l(int i, int i2) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.L;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            zIsFinished = false;
        } else {
            this.L.onRelease();
            zIsFinished = this.L.isFinished();
        }
        EdgeEffect edgeEffect2 = this.N;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.N.onRelease();
            zIsFinished |= this.N.isFinished();
        }
        EdgeEffect edgeEffect3 = this.M;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.M.onRelease();
            zIsFinished |= this.M.isFinished();
        }
        EdgeEffect edgeEffect4 = this.O;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.O.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = zt2.a;
            it2.k(this);
        }
    }

    public final void m() {
        if (!this.z || this.G) {
            int i = lm2.a;
            km2.a("RV FullInvalidate");
            o();
            km2.b();
            return;
        }
        if (this.l.g()) {
            this.l.getClass();
            if (this.l.g()) {
                int i2 = lm2.a;
                km2.a("RV FullInvalidate");
                o();
                km2.b();
            }
        }
    }

    public final void n(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = zt2.a;
        setMeasuredDimension(y12.g(i, paddingRight, it2.e(this)), y12.g(i2, getPaddingBottom() + getPaddingTop(), it2.d(this)));
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x035c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o() {
        /*
            Method dump skipped, instruction units count: 960
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.I = r0
            r1 = 1
            r5.x = r1
            boolean r2 = r5.z
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.z = r2
            p.y12 r2 = r5.t
            if (r2 == 0) goto L1e
            r2.g = r1
        L1e:
            r5.q0 = r0
            java.lang.ThreadLocal r0 = p.d01.m
            java.lang.Object r1 = r0.get()
            p.d01 r1 = (p.d01) r1
            r5.i0 = r1
            if (r1 != 0) goto L68
            p.d01 r1 = new p.d01
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.c = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.l = r2
            r5.i0 = r1
            java.util.WeakHashMap r1 = p.zt2.a
            android.view.Display r1 = p.jt2.b(r5)
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L5a
            if (r1 == 0) goto L5a
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L5a
            goto L5c
        L5a:
            r1 = 1114636288(0x42700000, float:60.0)
        L5c:
            p.d01 r2 = r5.i0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.k = r3
            r0.set(r2)
        L68:
            p.d01 r0 = r5.i0
            java.util.ArrayList r0 = r0.c
            r0.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        qa1 qa1Var;
        super.onDetachedFromWindow();
        v12 v12Var = this.P;
        if (v12Var != null) {
            v12Var.e();
        }
        setScrollState(0);
        m22 m22Var = this.h0;
        m22Var.o.removeCallbacks(m22Var);
        m22Var.k.abortAnimation();
        y12 y12Var = this.t;
        if (y12Var != null && (qa1Var = y12Var.e) != null) {
            qa1Var.f();
        }
        this.x = false;
        y12 y12Var2 = this.t;
        if (y12Var2 != null) {
            y12Var2.g = false;
            y12Var2.M(this);
        }
        this.x0.clear();
        removeCallbacks(this.y0);
        this.n.getClass();
        while (gu2.d.a() != null) {
        }
        d01 d01Var = this.i0;
        if (d01Var != null) {
            d01Var.c.remove(this);
            this.i0 = null;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((w12) arrayList.get(i)).a(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            p.y12 r0 = r5.t
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.C
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r6.getAction()
            r2 = 8
            if (r0 != r2) goto L77
            int r0 = r6.getSource()
            r0 = r0 & 2
            r2 = 0
            if (r0 == 0) goto L3e
            p.y12 r0 = r5.t
            boolean r0 = r0.e()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            p.y12 r3 = r5.t
            boolean r3 = r3.d()
            if (r3 == 0) goto L3c
            r3 = 10
            float r3 = r6.getAxisValue(r3)
            goto L64
        L3c:
            r3 = r2
            goto L64
        L3e:
            int r0 = r6.getSource()
            r3 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r3
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r6.getAxisValue(r0)
            p.y12 r3 = r5.t
            boolean r3 = r3.e()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            p.y12 r3 = r5.t
            boolean r3 = r3.d()
            if (r3 == 0) goto L62
            r3 = r0
            r0 = r2
            goto L64
        L62:
            r0 = r2
            r3 = r0
        L64:
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L6c
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L77
        L6c:
            float r2 = r5.e0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.f0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.Y(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.C) {
            return false;
        }
        this.w = null;
        if (B(motionEvent)) {
            X();
            setScrollState(0);
            return true;
        }
        y12 y12Var = this.t;
        if (y12Var == null) {
            return false;
        }
        boolean zD = y12Var.d();
        boolean zE = this.t.e();
        if (this.S == null) {
            this.S = VelocityTracker.obtain();
        }
        this.S.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.D) {
                this.D = false;
            }
            this.R = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.V = x;
            this.T = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.W = y;
            this.U = y;
            if (this.Q == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                e0(1);
            }
            int[] iArr = this.v0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i = zD;
            if (zE) {
                i = (zD ? 1 : 0) | 2;
            }
            getScrollingChildHelper().h(i, 0);
        } else if (actionMasked == 1) {
            this.S.clear();
            e0(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.R);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.R + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x2 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.Q != 1) {
                int i2 = x2 - this.T;
                int i3 = y2 - this.U;
                if (!zD || Math.abs(i2) <= this.a0) {
                    z = false;
                } else {
                    this.V = x2;
                    z = true;
                }
                if (zE && Math.abs(i3) > this.a0) {
                    this.W = y2;
                } else if (z) {
                }
                setScrollState(1);
            }
        } else if (actionMasked == 3) {
            X();
            setScrollState(0);
        } else if (actionMasked == 5) {
            this.R = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.V = x3;
            this.T = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.W = y3;
            this.U = y3;
        } else if (actionMasked == 6) {
            R(motionEvent);
        }
        return this.Q == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = lm2.a;
        km2.a("RV OnLayout");
        o();
        km2.b();
        this.z = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        y12 y12Var = this.t;
        if (y12Var == null) {
            n(i, i2);
            return;
        }
        boolean zH = y12Var.H();
        k22 k22Var = this.k0;
        if (!zH) {
            if (this.y) {
                this.t.b.n(i, i2);
                return;
            }
            if (k22Var.k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            q12 q12Var = this.s;
            if (q12Var != null) {
                k22Var.e = q12Var.a();
            } else {
                k22Var.e = 0;
            }
            c0();
            this.t.b.n(i, i2);
            d0(false);
            k22Var.g = false;
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.t.b.n(i, i2);
        if ((mode == 1073741824 && mode2 == 1073741824) || this.s == null) {
            return;
        }
        if (k22Var.d == 1) {
            p();
        }
        this.t.l0(i, i2);
        k22Var.i = true;
        q();
        this.t.n0(i, i2);
        if (this.t.q0()) {
            this.t.l0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            k22Var.i = true;
            q();
            this.t.n0(i, i2);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (L()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof h22)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h22 h22Var = (h22) parcelable;
        this.k = h22Var;
        super.onRestoreInstanceState(h22Var.c);
        y12 y12Var = this.t;
        if (y12Var == null || (parcelable2 = this.k.k) == null) {
            return;
        }
        y12Var.Y(parcelable2);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        h22 h22Var = new h22(super.onSaveInstanceState());
        h22 h22Var2 = this.k;
        if (h22Var2 != null) {
            h22Var.k = h22Var2.k;
        } else {
            y12 y12Var = this.t;
            if (y12Var != null) {
                h22Var.k = y12Var.Z();
            } else {
                h22Var.k = null;
            }
        }
        return h22Var;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.O = null;
        this.M = null;
        this.N = null;
        this.L = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:152:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00eb A[PHI: r0
  0x00eb: PHI (r0v58 int) = (r0v45 int), (r0v62 int) binds: [B:44:0x00d4, B:48:0x00e7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0104  */
    /* JADX WARN: Type inference failed for: r7v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r26) {
        /*
            Method dump skipped, instruction units count: 1256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.p():void");
    }

    public final void q() {
        c0();
        P();
        k22 k22Var = this.k0;
        k22Var.a(6);
        this.l.c();
        k22Var.e = this.s.a();
        k22Var.c = 0;
        k22Var.g = false;
        this.t.W(this.e, k22Var);
        k22Var.f = false;
        this.k = null;
        k22Var.j = k22Var.j && this.P != null;
        k22Var.d = 4;
        Q(true);
        d0(false);
    }

    public final boolean r(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i, i2, i3, iArr, iArr2);
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z) {
        n22 n22VarI = I(view);
        if (n22VarI != null) {
            if (n22VarI.l()) {
                n22VarI.j &= -257;
            } else if (!n22VarI.q()) {
                StringBuilder sb = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(n22VarI);
                throw new IllegalArgumentException(u4.c(this, sb));
            }
        }
        view.clearAnimation();
        I(view);
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        qa1 qa1Var = this.t.e;
        if ((qa1Var == null || !qa1Var.e) && !L() && view2 != null) {
            W(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.t.f0(this, view, rect, z, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        ArrayList arrayList = this.v;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((b22) arrayList.get(i)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.A != 0 || this.C) {
            this.B = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        getScrollingChildHelper().e(i, i2, i3, i4, iArr, i5, iArr2);
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        y12 y12Var = this.t;
        if (y12Var == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        boolean zD = y12Var.d();
        boolean zE = this.t.e();
        if (zD || zE) {
            if (!zD) {
                i = 0;
            }
            if (!zE) {
                i2 = 0;
            }
            Y(i, i2, null);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!L()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int iA = accessibilityEvent != null ? p2.a(accessibilityEvent) : 0;
            this.E |= iA != 0 ? iA : 0;
        }
    }

    public void setAccessibilityDelegateCompat(p22 p22Var) {
        this.r0 = p22Var;
        zt2.k(this, p22Var);
    }

    public void setAdapter(q12 q12Var) {
        setLayoutFrozen(false);
        q12 q12Var2 = this.s;
        zb0 zb0Var = this.c;
        if (q12Var2 != null) {
            q12Var2.a.unregisterObserver(zb0Var);
            this.s.getClass();
        }
        v12 v12Var = this.P;
        if (v12Var != null) {
            v12Var.e();
        }
        y12 y12Var = this.t;
        f22 f22Var = this.e;
        if (y12Var != null) {
            y12Var.b0(f22Var);
            this.t.c0(f22Var);
        }
        ((ArrayList) f22Var.d).clear();
        f22Var.d();
        b5 b5Var = this.l;
        b5Var.l(b5Var.b);
        b5Var.l(b5Var.c);
        q12 q12Var3 = this.s;
        this.s = q12Var;
        if (q12Var != null) {
            q12Var.a.registerObserver(zb0Var);
        }
        q12 q12Var4 = this.s;
        ((ArrayList) f22Var.d).clear();
        f22Var.d();
        e22 e22VarC = f22Var.c();
        if (q12Var3 != null) {
            e22VarC.b--;
        }
        if (e22VarC.b == 0) {
            int i = 0;
            while (true) {
                SparseArray sparseArray = e22VarC.a;
                if (i >= sparseArray.size()) {
                    break;
                }
                ((d22) sparseArray.valueAt(i)).a.clear();
                i++;
            }
        }
        if (q12Var4 != null) {
            e22VarC.b++;
        }
        this.k0.f = true;
        U(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(s12 s12Var) {
        if (s12Var == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z) {
        if (z != this.o) {
            this.O = null;
            this.M = null;
            this.N = null;
            this.L = null;
        }
        this.o = z;
        super.setClipToPadding(z);
        if (this.z) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(t12 t12Var) {
        t12Var.getClass();
        this.K = t12Var;
        this.O = null;
        this.M = null;
        this.N = null;
        this.L = null;
    }

    public void setHasFixedSize(boolean z) {
        this.y = z;
    }

    public void setItemAnimator(v12 v12Var) {
        v12 v12Var2 = this.P;
        if (v12Var2 != null) {
            v12Var2.e();
            this.P.a = null;
        }
        this.P = v12Var;
        if (v12Var != null) {
            v12Var.a = this.p0;
        }
    }

    public void setItemViewCacheSize(int i) {
        f22 f22Var = this.e;
        f22Var.b = i;
        f22Var.k();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z) {
        suppressLayout(z);
    }

    public void setLayoutManager(y12 y12Var) {
        p12 p12Var;
        qa1 qa1Var;
        if (y12Var == this.t) {
            return;
        }
        setScrollState(0);
        m22 m22Var = this.h0;
        m22Var.o.removeCallbacks(m22Var);
        m22Var.k.abortAnimation();
        y12 y12Var2 = this.t;
        if (y12Var2 != null && (qa1Var = y12Var2.e) != null) {
            qa1Var.f();
        }
        y12 y12Var3 = this.t;
        f22 f22Var = this.e;
        if (y12Var3 != null) {
            v12 v12Var = this.P;
            if (v12Var != null) {
                v12Var.e();
            }
            this.t.b0(f22Var);
            this.t.c0(f22Var);
            ((ArrayList) f22Var.d).clear();
            f22Var.d();
            if (this.x) {
                y12 y12Var4 = this.t;
                y12Var4.g = false;
                y12Var4.M(this);
            }
            this.t.o0(null);
            this.t = null;
        } else {
            ((ArrayList) f22Var.d).clear();
            f22Var.d();
        }
        is isVar = this.m;
        isVar.b.g();
        ArrayList arrayList = isVar.c;
        int size = arrayList.size() - 1;
        while (true) {
            p12Var = isVar.a;
            if (size < 0) {
                break;
            }
            View view = (View) arrayList.get(size);
            p12Var.getClass();
            n22 n22VarI = I(view);
            if (n22VarI != null) {
                int i = n22VarI.f157p;
                RecyclerView recyclerView = p12Var.a;
                if (recyclerView.L()) {
                    n22VarI.q = i;
                    recyclerView.x0.add(n22VarI);
                } else {
                    WeakHashMap weakHashMap = zt2.a;
                    it2.s(n22VarI.a, i);
                }
                n22VarI.f157p = 0;
            }
            arrayList.remove(size);
            size--;
        }
        RecyclerView recyclerView2 = p12Var.a;
        int childCount = recyclerView2.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView2.getChildAt(i2);
            I(childAt);
            childAt.clearAnimation();
        }
        recyclerView2.removeAllViews();
        this.t = y12Var;
        if (y12Var != null) {
            if (y12Var.b != null) {
                StringBuilder sb = new StringBuilder("LayoutManager ");
                sb.append(y12Var);
                sb.append(" is already attached to a RecyclerView:");
                throw new IllegalArgumentException(u4.c(y12Var.b, sb));
            }
            y12Var.o0(this);
            if (this.x) {
                this.t.g = true;
            }
        }
        f22Var.k();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        pm1 scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            WeakHashMap weakHashMap = zt2.a;
            ot2.z(scrollingChildHelper.c);
        }
        scrollingChildHelper.d = z;
    }

    public void setOnFlingListener(a22 a22Var) {
        this.b0 = a22Var;
    }

    @Deprecated
    public void setOnScrollListener(c22 c22Var) {
        this.l0 = c22Var;
    }

    public void setPreserveFocusAfterLayout(boolean z) {
        this.g0 = z;
    }

    public void setRecycledViewPool(e22 e22Var) {
        f22 f22Var = this.e;
        if (((e22) f22Var.g) != null) {
            r1.b--;
        }
        f22Var.g = e22Var;
        if (e22Var == null || ((RecyclerView) f22Var.i).getAdapter() == null) {
            return;
        }
        ((e22) f22Var.g).b++;
    }

    public void setRecyclerListener(g22 g22Var) {
    }

    public void setScrollState(int i) {
        qa1 qa1Var;
        if (i == this.Q) {
            return;
        }
        this.Q = i;
        if (i != 2) {
            m22 m22Var = this.h0;
            m22Var.o.removeCallbacks(m22Var);
            m22Var.k.abortAnimation();
            y12 y12Var = this.t;
            if (y12Var != null && (qa1Var = y12Var.e) != null) {
                qa1Var.f();
            }
        }
        y12 y12Var2 = this.t;
        if (y12Var2 != null) {
            y12Var2.a0(i);
        }
        c22 c22Var = this.l0;
        if (c22Var != null) {
            c22Var.a(this, i);
        }
        ArrayList arrayList = this.m0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((c22) this.m0.get(size)).a(this, i);
            }
        }
    }

    public void setScrollingTouchSlop(int i) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i != 1) {
            this.a0 = viewConfiguration.getScaledTouchSlop();
        } else {
            this.a0 = viewConfiguration.getScaledPagingTouchSlop();
        }
    }

    public void setViewCacheExtension(l22 l22Var) {
        this.e.h = l22Var;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return getScrollingChildHelper().h(i, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().i(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        qa1 qa1Var;
        if (z != this.C) {
            i("Do not suppressLayout in layout or scroll");
            if (!z) {
                this.C = false;
                if (this.B && this.t != null && this.s != null) {
                    requestLayout();
                }
                this.B = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.C = true;
            this.D = true;
            setScrollState(0);
            m22 m22Var = this.h0;
            m22Var.o.removeCallbacks(m22Var);
            m22Var.k.abortAnimation();
            y12 y12Var = this.t;
            if (y12Var == null || (qa1Var = y12Var.e) == null) {
                return;
            }
            qa1Var.f();
        }
    }

    public final void t(int i, int i2) {
        this.J++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        c22 c22Var = this.l0;
        if (c22Var != null) {
            c22Var.b(this, i, i2);
        }
        ArrayList arrayList = this.m0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((c22) this.m0.get(size)).b(this, i, i2);
            }
        }
        this.J--;
    }

    public final void u() {
        if (this.O != null) {
            return;
        }
        this.K.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.O = edgeEffect;
        if (this.o) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void v() {
        if (this.L != null) {
            return;
        }
        this.K.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.L = edgeEffect;
        if (this.o) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void w() {
        if (this.N != null) {
            return;
        }
        this.K.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.N = edgeEffect;
        if (this.o) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void x() {
        if (this.M != null) {
            return;
        }
        this.K.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.M = edgeEffect;
        if (this.o) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String y() {
        return " " + super.toString() + ", adapter:" + this.s + ", layout:" + this.t + ", context:" + getContext();
    }

    public final void z(k22 k22Var) {
        if (getScrollState() != 2) {
            k22Var.getClass();
            return;
        }
        OverScroller overScroller = this.h0.k;
        overScroller.getFinalX();
        overScroller.getCurrX();
        k22Var.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x03a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RecyclerView(android.content.Context r19, android.util.AttributeSet r20, int r21) {
        /*
            Method dump skipped, instruction units count: 972
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        y12 y12Var = this.t;
        if (y12Var != null) {
            return y12Var.t(layoutParams);
        }
        throw new IllegalStateException(u4.c(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }
}
