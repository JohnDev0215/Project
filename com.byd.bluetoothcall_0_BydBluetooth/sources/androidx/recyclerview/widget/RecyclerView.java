package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.b;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.p;
import androidx.recyclerview.widget.q;
import b.g.k.c0.c;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements b.g.k.j {

    /* JADX INFO: renamed from: b */
    private static final int[] f1625b = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: c */
    static final boolean f1626c;

    /* JADX INFO: renamed from: d */
    static final boolean f1627d;

    /* JADX INFO: renamed from: e */
    static final boolean f1628e;
    static final boolean f;
    private static final boolean g;
    private static final boolean h;
    private static final Class<?>[] i;
    static final Interpolator j;
    private s A;
    private final int[] A0;
    boolean B;
    final int[] B0;
    boolean C;
    final List<c0> C0;
    boolean D;
    private Runnable D0;
    boolean E;
    private final q.b E0;
    private int F;
    boolean G;
    boolean H;
    private boolean I;
    private int J;
    boolean K;
    private final AccessibilityManager L;
    private List<q> M;
    boolean N;
    boolean O;
    private int P;
    private int Q;
    private k R;
    private EdgeEffect S;
    private EdgeEffect T;
    private EdgeEffect U;
    private EdgeEffect V;
    l W;
    private int X;
    private int Y;
    private VelocityTracker Z;
    private int a0;
    private int b0;
    private int c0;
    private int d0;
    private int e0;
    private r f0;
    private final int g0;
    private final int h0;
    private float i0;
    private float j0;
    private final x k;
    private boolean k0;
    final v l;
    final b0 l0;
    private SavedState m;
    androidx.recyclerview.widget.e m0;
    androidx.recyclerview.widget.a n;
    e.b n0;
    androidx.recyclerview.widget.b o;
    final z o0;
    final androidx.recyclerview.widget.q p;
    private t p0;
    boolean q;
    private List<t> q0;
    final Runnable r;
    boolean r0;
    final Rect s;
    boolean s0;
    private final Rect t;
    private l.b t0;
    final RectF u;
    boolean u0;
    g v;
    androidx.recyclerview.widget.l v0;
    o w;
    private j w0;
    w x;
    private final int[] x0;
    final ArrayList<n> y;
    private b.g.k.k y0;
    private final ArrayList<s> z;
    private final int[] z0;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.E || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.B) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.H) {
                recyclerView2.G = true;
            } else {
                recyclerView2.v();
            }
        }
    }

    public static abstract class a0 {
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = RecyclerView.this.W;
            if (lVar != null) {
                lVar.u();
            }
            RecyclerView.this.u0 = false;
        }
    }

    class b0 implements Runnable {

        /* JADX INFO: renamed from: b */
        private int f1632b;

        /* JADX INFO: renamed from: c */
        private int f1633c;

        /* JADX INFO: renamed from: d */
        OverScroller f1634d;

        /* JADX INFO: renamed from: e */
        Interpolator f1635e;
        private boolean f;
        private boolean g;

        b0() {
            Interpolator interpolator = RecyclerView.j;
            this.f1635e = interpolator;
            this.f = false;
            this.g = false;
            this.f1634d = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int a(int i, int i2, int i3, int i4) {
            int iRound;
            int iAbs = Math.abs(i);
            int iAbs2 = Math.abs(i2);
            boolean z = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i3 * i3) + (i4 * i4));
            int iSqrt2 = (int) Math.sqrt((i * i) + (i2 * i2));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i5 = width / 2;
            float f = width;
            float f2 = i5;
            float fB = f2 + (b(Math.min(1.0f, (iSqrt2 * 1.0f) / f)) * f2);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fB / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        private float b(float f) {
            return (float) Math.sin((f - 0.5f) * 0.47123894f);
        }

        private void d() {
            RecyclerView.this.removeCallbacks(this);
            b.g.k.t.c0(RecyclerView.this, this);
        }

        public void c(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.f1633c = 0;
            this.f1632b = 0;
            Interpolator interpolator = this.f1635e;
            Interpolator interpolator2 = RecyclerView.j;
            if (interpolator != interpolator2) {
                this.f1635e = interpolator2;
                this.f1634d = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f1634d.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        void e() {
            if (this.f) {
                this.g = true;
            } else {
                d();
            }
        }

        public void f(int i, int i2, int i3, Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                i3 = a(i, i2, 0, 0);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.j;
            }
            if (this.f1635e != interpolator) {
                this.f1635e = interpolator;
                this.f1634d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1633c = 0;
            this.f1632b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1634d.startScroll(0, 0, i, i2, i4);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1634d.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f1634d.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.w == null) {
                g();
                return;
            }
            this.g = false;
            this.f = true;
            recyclerView.v();
            OverScroller overScroller = this.f1634d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.f1632b;
                int i4 = currY - this.f1633c;
                this.f1632b = currX;
                this.f1633c = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.B0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.G(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.B0;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.u(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.v != null) {
                    int[] iArr3 = recyclerView3.B0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.j1(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.B0;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    i3 -= i2;
                    i4 -= i;
                    y yVar = recyclerView4.w.g;
                    if (yVar != null && !yVar.g() && yVar.h()) {
                        int iB = RecyclerView.this.o0.b();
                        if (iB == 0) {
                            yVar.r();
                        } else if (yVar.f() >= iB) {
                            yVar.p(iB - 1);
                            yVar.j(i2, i);
                        } else {
                            yVar.j(i2, i);
                        }
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                if (!RecyclerView.this.y.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.B0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.H(i2, i, i3, i4, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.B0;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    recyclerView6.J(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                y yVar2 = RecyclerView.this.w.g;
                if ((yVar2 != null && yVar2.g()) || !z) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    androidx.recyclerview.widget.e eVar = recyclerView7.m0;
                    if (eVar != null) {
                        eVar.f(recyclerView7, i2, i);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                        if (i6 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i6 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i7, currVelocity);
                    }
                    if (RecyclerView.f) {
                        RecyclerView.this.n0.b();
                    }
                }
            }
            y yVar3 = RecyclerView.this.w.g;
            if (yVar3 != null && yVar3.g()) {
                yVar3.j(0, 0);
            }
            this.f = false;
            if (this.g) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.w1(1);
            }
        }
    }

    static class c implements Interpolator {
        c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    public static abstract class c0 {

        /* JADX INFO: renamed from: a */
        private static final List<Object> f1636a = Collections.emptyList();

        /* JADX INFO: renamed from: b */
        public final View f1637b;

        /* JADX INFO: renamed from: c */
        WeakReference<RecyclerView> f1638c;
        int k;
        RecyclerView s;

        /* JADX INFO: renamed from: d */
        int f1639d = -1;

        /* JADX INFO: renamed from: e */
        int f1640e = -1;
        long f = -1;
        int g = -1;
        int h = -1;
        c0 i = null;
        c0 j = null;
        List<Object> l = null;
        List<Object> m = null;
        private int n = 0;
        v o = null;
        boolean p = false;
        private int q = 0;
        int r = -1;

        public c0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1637b = view;
        }

        private void g() {
            if (this.l == null) {
                ArrayList arrayList = new ArrayList();
                this.l = arrayList;
                this.m = Collections.unmodifiableList(arrayList);
            }
        }

        void A(int i, boolean z) {
            if (this.f1640e == -1) {
                this.f1640e = this.f1639d;
            }
            if (this.h == -1) {
                this.h = this.f1639d;
            }
            if (z) {
                this.h += i;
            }
            this.f1639d += i;
            if (this.f1637b.getLayoutParams() != null) {
                ((p) this.f1637b.getLayoutParams()).f1669c = true;
            }
        }

        void B(RecyclerView recyclerView) {
            int i = this.r;
            if (i != -1) {
                this.q = i;
            } else {
                this.q = b.g.k.t.x(this.f1637b);
            }
            recyclerView.m1(this, 4);
        }

        void C(RecyclerView recyclerView) {
            recyclerView.m1(this, this.q);
            this.q = 0;
        }

        void D() {
            this.k = 0;
            this.f1639d = -1;
            this.f1640e = -1;
            this.f = -1L;
            this.h = -1;
            this.n = 0;
            this.i = null;
            this.j = null;
            d();
            this.q = 0;
            this.r = -1;
            RecyclerView.s(this);
        }

        void E() {
            if (this.f1640e == -1) {
                this.f1640e = this.f1639d;
            }
        }

        void F(int i, int i2) {
            this.k = (i & i2) | (this.k & (~i2));
        }

        public final void G(boolean z) {
            int i = this.n;
            int i2 = z ? i - 1 : i + 1;
            this.n = i2;
            if (i2 < 0) {
                this.n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i2 == 1) {
                this.k |= 16;
            } else if (z && i2 == 0) {
                this.k &= -17;
            }
        }

        void H(v vVar, boolean z) {
            this.o = vVar;
            this.p = z;
        }

        boolean I() {
            return (this.k & 16) != 0;
        }

        boolean J() {
            return (this.k & 128) != 0;
        }

        void K() {
            this.o.J(this);
        }

        boolean L() {
            return (this.k & 32) != 0;
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.k) == 0) {
                g();
                this.l.add(obj);
            }
        }

        void b(int i) {
            this.k = i | this.k;
        }

        void c() {
            this.f1640e = -1;
            this.h = -1;
        }

        void d() {
            List<Object> list = this.l;
            if (list != null) {
                list.clear();
            }
            this.k &= -1025;
        }

        void e() {
            this.k &= -33;
        }

        void f() {
            this.k &= -257;
        }

        boolean h() {
            return (this.k & 16) == 0 && b.g.k.t.M(this.f1637b);
        }

        void i(int i, int i2, boolean z) {
            b(8);
            A(i2, z);
            this.f1639d = i;
        }

        public final int j() {
            RecyclerView recyclerView = this.s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.d0(this);
        }

        public final long k() {
            return this.f;
        }

        public final int l() {
            return this.g;
        }

        public final int m() {
            int i = this.h;
            return i == -1 ? this.f1639d : i;
        }

        public final int n() {
            return this.f1640e;
        }

        List<Object> o() {
            if ((this.k & 1024) != 0) {
                return f1636a;
            }
            List<Object> list = this.l;
            return (list == null || list.size() == 0) ? f1636a : this.m;
        }

        boolean p(int i) {
            return (i & this.k) != 0;
        }

        boolean q() {
            return (this.k & 512) != 0 || t();
        }

        boolean r() {
            return (this.f1637b.getParent() == null || this.f1637b.getParent() == this.s) ? false : true;
        }

        boolean s() {
            return (this.k & 1) != 0;
        }

        boolean t() {
            return (this.k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1639d + " id=" + this.f + ", oldPos=" + this.f1640e + ", pLpos:" + this.h);
            if (w()) {
                sb.append(" scrap ");
                sb.append(this.p ? "[changeScrap]" : "[attachedScrap]");
            }
            if (t()) {
                sb.append(" invalid");
            }
            if (!s()) {
                sb.append(" unbound");
            }
            if (z()) {
                sb.append(" update");
            }
            if (v()) {
                sb.append(" removed");
            }
            if (J()) {
                sb.append(" ignored");
            }
            if (x()) {
                sb.append(" tmpDetached");
            }
            if (!u()) {
                sb.append(" not recyclable(" + this.n + ")");
            }
            if (q()) {
                sb.append(" undefined adapter position");
            }
            if (this.f1637b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.k & 16) == 0 && !b.g.k.t.M(this.f1637b);
        }

        boolean v() {
            return (this.k & 8) != 0;
        }

        boolean w() {
            return this.o != null;
        }

        boolean x() {
            return (this.k & 256) != 0;
        }

        boolean y() {
            return (this.k & 2) != 0;
        }

        boolean z() {
            return (this.k & 2) != 0;
        }
    }

    class d implements q.b {
        d() {
        }

        @Override // androidx.recyclerview.widget.q.b
        public void a(c0 c0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.w.m1(c0Var.f1637b, recyclerView.l);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void b(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.m(c0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void c(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.l.J(c0Var);
            RecyclerView.this.o(c0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void d(c0 c0Var, l.c cVar, l.c cVar2) {
            c0Var.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.N) {
                if (recyclerView.W.b(c0Var, c0Var, cVar, cVar2)) {
                    RecyclerView.this.P0();
                }
            } else if (recyclerView.W.d(c0Var, cVar, cVar2)) {
                RecyclerView.this.P0();
            }
        }
    }

    class e implements b.InterfaceC0031b {
        e() {
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public View a(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void b(View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0 != null) {
                c0VarI0.B(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void c(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.A(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void d() {
            int iK = k();
            for (int i = 0; i < iK; i++) {
                View viewA = a(i);
                RecyclerView.this.A(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public c0 e(View view) {
            return RecyclerView.i0(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void f(int i) {
            c0 c0VarI0;
            View viewA = a(i);
            if (viewA != null && (c0VarI0 = RecyclerView.i0(viewA)) != null) {
                if (c0VarI0.x() && !c0VarI0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarI0 + RecyclerView.this.Q());
                }
                c0VarI0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void g(View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0 != null) {
                c0VarI0.C(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void h(View view, int i, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0 != null) {
                if (!c0VarI0.x() && !c0VarI0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarI0 + RecyclerView.this.Q());
                }
                c0VarI0.f();
            }
            RecyclerView.this.attachViewToParent(view, i, layoutParams);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public void i(View view, int i) {
            RecyclerView.this.addView(view, i);
            RecyclerView.this.z(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0031b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    class f implements a.InterfaceC0030a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void a(int i, int i2) {
            RecyclerView.this.F0(i, i2);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public c0 c(int i) {
            c0 c0VarB0 = RecyclerView.this.b0(i, true);
            if (c0VarB0 == null || RecyclerView.this.o.n(c0VarB0.f1637b)) {
                return null;
            }
            return c0VarB0;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void d(int i, int i2) {
            RecyclerView.this.G0(i, i2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.r0 = true;
            recyclerView.o0.f1696d += i2;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void e(int i, int i2) {
            RecyclerView.this.G0(i, i2, false);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void g(int i, int i2) {
            RecyclerView.this.E0(i, i2);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0030a
        public void h(int i, int i2, Object obj) {
            RecyclerView.this.z1(i, i2, obj);
            RecyclerView.this.s0 = true;
        }

        void i(a.b bVar) {
            int i = bVar.f1725a;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.w.R0(recyclerView, bVar.f1726b, bVar.f1728d);
                return;
            }
            if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.w.U0(recyclerView2, bVar.f1726b, bVar.f1728d);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.w.W0(recyclerView3, bVar.f1726b, bVar.f1728d, bVar.f1727c);
            } else {
                if (i != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.w.T0(recyclerView4, bVar.f1726b, bVar.f1728d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a */
        private final h f1644a = new h();

        /* JADX INFO: renamed from: b */
        private boolean f1645b = false;

        public final void a(VH vh, int i) {
            vh.f1639d = i;
            if (g()) {
                vh.f = d(i);
            }
            vh.F(1, 519);
            b.g.g.a.a("RV OnBindView");
            k(vh, i, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f1637b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1669c = true;
            }
            b.g.g.a.b();
        }

        public final VH b(ViewGroup viewGroup, int i) {
            try {
                b.g.g.a.a("RV CreateView");
                VH vh = (VH) l(viewGroup, i);
                if (vh.f1637b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.g = i;
                return vh;
            } finally {
                b.g.g.a.b();
            }
        }

        public abstract int c();

        public long d(int i) {
            return -1L;
        }

        public int e(int i) {
            return 0;
        }

        public final boolean f() {
            return this.f1644a.a();
        }

        public final boolean g() {
            return this.f1645b;
        }

        public final void h() {
            this.f1644a.b();
        }

        public void i(RecyclerView recyclerView) {
        }

        public abstract void j(VH vh, int i);

        public void k(VH vh, int i, List<Object> list) {
            j(vh, i);
        }

        public abstract VH l(ViewGroup viewGroup, int i);

        public void m(RecyclerView recyclerView) {
        }

        public boolean n(VH vh) {
            return false;
        }

        public void o(VH vh) {
        }

        public void p(VH vh) {
        }

        public void q(VH vh) {
        }

        public void r(i iVar) {
            this.f1644a.registerObserver(iVar);
        }

        public void s(boolean z) {
            if (f()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1645b = z;
        }

        public void t(i iVar) {
            this.f1644a.unregisterObserver(iVar);
        }
    }

    static class h extends Observable<i> {
        h() {
        }

        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).a();
            }
        }
    }

    public static abstract class i {
        public void a() {
        }
    }

    public interface j {
        int a(int i, int i2);
    }

    public static class k {
        protected EdgeEffect a(RecyclerView recyclerView, int i) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a */
        private b f1646a = null;

        /* JADX INFO: renamed from: b */
        private ArrayList<a> f1647b = new ArrayList<>();

        /* JADX INFO: renamed from: c */
        private long f1648c = 120;

        /* JADX INFO: renamed from: d */
        private long f1649d = 120;

        /* JADX INFO: renamed from: e */
        private long f1650e = 250;
        private long f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a */
            public int f1651a;

            /* JADX INFO: renamed from: b */
            public int f1652b;

            /* JADX INFO: renamed from: c */
            public int f1653c;

            /* JADX INFO: renamed from: d */
            public int f1654d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i) {
                View view = c0Var.f1637b;
                this.f1651a = view.getLeft();
                this.f1652b = view.getTop();
                this.f1653c = view.getRight();
                this.f1654d = view.getBottom();
                return this;
            }
        }

        static int e(c0 c0Var) {
            int i = c0Var.k & 14;
            if (c0Var.t()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int iN = c0Var.n();
            int iJ = c0Var.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i : i | 2048;
        }

        public abstract boolean a(c0 c0Var, c cVar, c cVar2);

        public abstract boolean b(c0 c0Var, c0 c0Var2, c cVar, c cVar2);

        public abstract boolean c(c0 c0Var, c cVar, c cVar2);

        public abstract boolean d(c0 c0Var, c cVar, c cVar2);

        public abstract boolean f(c0 c0Var);

        public boolean g(c0 c0Var, List<Object> list) {
            return f(c0Var);
        }

        public final void h(c0 c0Var) {
            r(c0Var);
            b bVar = this.f1646a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f1647b.size();
            for (int i = 0; i < size; i++) {
                this.f1647b.get(i).a();
            }
            this.f1647b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f1648c;
        }

        public long m() {
            return this.f;
        }

        public long n() {
            return this.f1650e;
        }

        public long o() {
            return this.f1649d;
        }

        public abstract boolean p();

        public c q() {
            return new c();
        }

        public void r(c0 c0Var) {
        }

        public c s(z zVar, c0 c0Var) {
            return q().a(c0Var);
        }

        public c t(z zVar, c0 c0Var, int i, List<Object> list) {
            return q().a(c0Var);
        }

        public abstract void u();

        void v(b bVar) {
            this.f1646a = bVar;
        }
    }

    private class m implements l.b {
        m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.i != null && c0Var.j == null) {
                c0Var.i = null;
            }
            c0Var.j = null;
            if (c0Var.I() || RecyclerView.this.Y0(c0Var.f1637b) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f1637b, false);
        }
    }

    public static abstract class n {
        @Deprecated
        public void d(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void e(Rect rect, View view, RecyclerView recyclerView, z zVar) {
            d(rect, ((p) view.getLayoutParams()).a(), recyclerView);
        }

        @Deprecated
        public void f(Canvas canvas, RecyclerView recyclerView) {
        }

        public void g(Canvas canvas, RecyclerView recyclerView, z zVar) {
            f(canvas, recyclerView);
        }

        @Deprecated
        public void h(Canvas canvas, RecyclerView recyclerView) {
        }

        public void i(Canvas canvas, RecyclerView recyclerView, z zVar) {
            h(canvas, recyclerView);
        }
    }

    public static abstract class o {

        /* JADX INFO: renamed from: a */
        androidx.recyclerview.widget.b f1656a;

        /* JADX INFO: renamed from: b */
        RecyclerView f1657b;

        /* JADX INFO: renamed from: c */
        private final p.b f1658c;

        /* JADX INFO: renamed from: d */
        private final p.b f1659d;

        /* JADX INFO: renamed from: e */
        androidx.recyclerview.widget.p f1660e;
        androidx.recyclerview.widget.p f;
        y g;
        boolean h;
        boolean i;
        boolean j;
        private boolean k;
        private boolean l;
        int m;
        boolean n;
        private int o;
        private int p;
        private int q;
        private int r;

        class a implements p.b {
            a() {
            }

            @Override // androidx.recyclerview.widget.p.b
            public View a(int i) {
                return o.this.I(i);
            }

            @Override // androidx.recyclerview.widget.p.b
            public int b() {
                return o.this.o0() - o.this.f0();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int c() {
                return o.this.e0();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int d(View view) {
                return o.this.T(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.p.b
            public int e(View view) {
                return o.this.Q(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        class b implements p.b {
            b() {
            }

            @Override // androidx.recyclerview.widget.p.b
            public View a(int i) {
                return o.this.I(i);
            }

            @Override // androidx.recyclerview.widget.p.b
            public int b() {
                return o.this.W() - o.this.d0();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int c() {
                return o.this.g0();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int d(View view) {
                return o.this.O(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.p.b
            public int e(View view) {
                return o.this.U(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i, int i2);
        }

        public static class d {

            /* JADX INFO: renamed from: a */
            public int f1663a;

            /* JADX INFO: renamed from: b */
            public int f1664b;

            /* JADX INFO: renamed from: c */
            public boolean f1665c;

            /* JADX INFO: renamed from: d */
            public boolean f1666d;
        }

        public o() {
            a aVar = new a();
            this.f1658c = aVar;
            b bVar = new b();
            this.f1659d = bVar;
            this.f1660e = new androidx.recyclerview.widget.p(aVar);
            this.f = new androidx.recyclerview.widget.p(bVar);
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = true;
            this.l = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int K(int r4, int r5, int r6, int r7, boolean r8) {
            /*
                int r4 = r4 - r6
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r8 == 0) goto L1a
                if (r7 < 0) goto L11
                goto L1c
            L11:
                if (r7 != r1) goto L2f
                if (r5 == r2) goto L20
                if (r5 == 0) goto L2f
                if (r5 == r3) goto L20
                goto L2f
            L1a:
                if (r7 < 0) goto L1e
            L1c:
                r5 = r3
                goto L31
            L1e:
                if (r7 != r1) goto L22
            L20:
                r7 = r4
                goto L31
            L22:
                if (r7 != r0) goto L2f
                if (r5 == r2) goto L2c
                if (r5 != r3) goto L29
                goto L2c
            L29:
                r7 = r4
                r5 = r6
                goto L31
            L2c:
                r7 = r4
                r5 = r2
                goto L31
            L2f:
                r5 = r6
                r7 = r5
            L31:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o.K(int, int, int, int, boolean):int");
        }

        private int[] L(View view, Rect rect) {
            int[] iArr = new int[2];
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i = left - iE0;
            int iMin = Math.min(0, i);
            int i2 = top - iG0;
            int iMin2 = Math.min(0, i2);
            int i3 = iWidth - iO0;
            int iMax = Math.max(0, i3);
            int iMax2 = Math.max(0, iHeight - iW);
            if (Z() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i3);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i2, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        private void f(View view, int i, boolean z) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (z || c0VarI0.v()) {
                this.f1657b.p.b(c0VarI0);
            } else {
                this.f1657b.p.p(c0VarI0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarI0.L() || c0VarI0.w()) {
                if (c0VarI0.w()) {
                    c0VarI0.K();
                } else {
                    c0VarI0.e();
                }
                this.f1656a.c(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1657b) {
                int iM = this.f1656a.m(view);
                if (i == -1) {
                    i = this.f1656a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1657b.indexOfChild(view) + this.f1657b.Q());
                }
                if (iM != i) {
                    this.f1657b.w.B0(iM, i);
                }
            } else {
                this.f1656a.a(view, i, false);
                pVar.f1669c = true;
                y yVar = this.g;
                if (yVar != null && yVar.h()) {
                    this.g.k(view);
                }
            }
            if (pVar.f1670d) {
                c0VarI0.f1637b.invalidate();
                pVar.f1670d = false;
            }
        }

        public static d i0(Context context, AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.l.c.f, i, i2);
            dVar.f1663a = typedArrayObtainStyledAttributes.getInt(b.l.c.g, 1);
            dVar.f1664b = typedArrayObtainStyledAttributes.getInt(b.l.c.q, 1);
            dVar.f1665c = typedArrayObtainStyledAttributes.getBoolean(b.l.c.p, false);
            dVar.f1666d = typedArrayObtainStyledAttributes.getBoolean(b.l.c.r, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int n(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        private boolean t0(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            Rect rect = this.f1657b.s;
            P(focusedChild, rect);
            return rect.left - i < iO0 && rect.right - i > iE0 && rect.top - i2 < iW && rect.bottom - i2 > iG0;
        }

        private void v1(v vVar, int i, View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0.J()) {
                return;
            }
            if (c0VarI0.t() && !c0VarI0.v() && !this.f1657b.v.g()) {
                q1(i);
                vVar.C(c0VarI0);
            } else {
                x(i);
                vVar.D(view);
                this.f1657b.p.k(c0VarI0);
            }
        }

        private static boolean w0(int i, int i2, int i3) {
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

        private void y(int i, View view) {
            this.f1656a.d(i);
        }

        void A(RecyclerView recyclerView, v vVar) {
            this.i = false;
            I0(recyclerView, vVar);
        }

        public void A0(View view, int i, int i2) {
            p pVar = (p) view.getLayoutParams();
            Rect rectN0 = this.f1657b.n0(view);
            int i3 = i + rectN0.left + rectN0.right;
            int i4 = i2 + rectN0.top + rectN0.bottom;
            int iK = K(o0(), p0(), e0() + f0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i3, ((ViewGroup.MarginLayoutParams) pVar).width, k());
            int iK2 = K(W(), X(), g0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).height, l());
            if (F1(view, iK, iK2, pVar)) {
                view.measure(iK, iK2);
            }
        }

        void A1(int i, int i2) {
            this.q = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.o = mode;
            if (mode == 0 && !RecyclerView.f1627d) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.f1627d) {
                return;
            }
            this.r = 0;
        }

        public View B(View view) {
            View viewS;
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null || (viewS = recyclerView.S(view)) == null || this.f1656a.n(viewS)) {
                return null;
            }
            return viewS;
        }

        public void B0(int i, int i2) {
            View viewI = I(i);
            if (viewI != null) {
                x(i);
                h(viewI, i2);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.f1657b.toString());
            }
        }

        public void B1(int i, int i2) {
            this.f1657b.setMeasuredDimension(i, i2);
        }

        public View C(int i) {
            int iJ = J();
            for (int i2 = 0; i2 < iJ; i2++) {
                View viewI = I(i2);
                c0 c0VarI0 = RecyclerView.i0(viewI);
                if (c0VarI0 != null && c0VarI0.m() == i && !c0VarI0.J() && (this.f1657b.o0.e() || !c0VarI0.v())) {
                    return viewI;
                }
            }
            return null;
        }

        public void C0(int i) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                recyclerView.C0(i);
            }
        }

        public void C1(Rect rect, int i, int i2) {
            B1(n(i, rect.width() + e0() + f0(), c0()), n(i2, rect.height() + g0() + d0(), b0()));
        }

        public abstract p D();

        public void D0(int i) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                recyclerView.D0(i);
            }
        }

        void D1(int i, int i2) {
            int iJ = J();
            if (iJ == 0) {
                this.f1657b.x(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < iJ; i7++) {
                View viewI = I(i7);
                Rect rect = this.f1657b.s;
                P(viewI, rect);
                int i8 = rect.left;
                if (i8 < i4) {
                    i4 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i5) {
                    i5 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i6) {
                    i6 = i11;
                }
            }
            this.f1657b.s.set(i4, i5, i3, i6);
            C1(this.f1657b.s, i, i2);
        }

        public p E(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void E0(g gVar, g gVar2) {
        }

        void E1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1657b = null;
                this.f1656a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f1657b = recyclerView;
                this.f1656a = recyclerView.o;
                this.q = recyclerView.getWidth();
                this.r = recyclerView.getHeight();
            }
            this.o = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.p = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public p F(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        public boolean F0(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        boolean F1(View view, int i, int i2, p pVar) {
            return (!view.isLayoutRequested() && this.k && w0(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public int G() {
            return -1;
        }

        public void G0(RecyclerView recyclerView) {
        }

        boolean G1() {
            return false;
        }

        public int H(View view) {
            return ((p) view.getLayoutParams()).f1668b.bottom;
        }

        @Deprecated
        public void H0(RecyclerView recyclerView) {
        }

        boolean H1(View view, int i, int i2, p pVar) {
            return (this.k && w0(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public View I(int i) {
            androidx.recyclerview.widget.b bVar = this.f1656a;
            if (bVar != null) {
                return bVar.f(i);
            }
            return null;
        }

        public void I0(RecyclerView recyclerView, v vVar) {
            H0(recyclerView);
        }

        public void I1(RecyclerView recyclerView, z zVar, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public int J() {
            androidx.recyclerview.widget.b bVar = this.f1656a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public View J0(View view, int i, v vVar, z zVar) {
            return null;
        }

        public void J1(y yVar) {
            y yVar2 = this.g;
            if (yVar2 != null && yVar != yVar2 && yVar2.h()) {
                this.g.r();
            }
            this.g = yVar;
            yVar.q(this.f1657b, this);
        }

        public void K0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1657b;
            L0(recyclerView.l, recyclerView.o0, accessibilityEvent);
        }

        void K1() {
            y yVar = this.g;
            if (yVar != null) {
                yVar.r();
            }
        }

        public void L0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1657b.canScrollVertically(-1) && !this.f1657b.canScrollHorizontally(-1) && !this.f1657b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f1657b.v;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public boolean L1() {
            return false;
        }

        public boolean M() {
            RecyclerView recyclerView = this.f1657b;
            return recyclerView != null && recyclerView.q;
        }

        void M0(b.g.k.c0.c cVar) {
            RecyclerView recyclerView = this.f1657b;
            N0(recyclerView.l, recyclerView.o0, cVar);
        }

        public int N(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null || recyclerView.v == null || !k()) {
                return 1;
            }
            return this.f1657b.v.c();
        }

        public void N0(v vVar, z zVar, b.g.k.c0.c cVar) {
            if (this.f1657b.canScrollVertically(-1) || this.f1657b.canScrollHorizontally(-1)) {
                cVar.a(CarPlayConfig.IapFeature.IAP_FEATURE_OOB_BT_PAIRING);
                cVar.x0(true);
            }
            if (this.f1657b.canScrollVertically(1) || this.f1657b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.x0(true);
            }
            cVar.e0(c.b.b(k0(vVar, zVar), N(vVar, zVar), v0(vVar, zVar), l0(vVar, zVar)));
        }

        public int O(View view) {
            return view.getBottom() + H(view);
        }

        void O0(View view, b.g.k.c0.c cVar) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0 == null || c0VarI0.v() || this.f1656a.n(c0VarI0.f1637b)) {
                return;
            }
            RecyclerView recyclerView = this.f1657b;
            P0(recyclerView.l, recyclerView.o0, view, cVar);
        }

        public void P(View view, Rect rect) {
            RecyclerView.k0(view, rect);
        }

        public void P0(v vVar, z zVar, View view, b.g.k.c0.c cVar) {
            cVar.f0(c.C0054c.a(l() ? h0(view) : 0, 1, k() ? h0(view) : 0, 1, false, false));
        }

        public int Q(View view) {
            return view.getLeft() - a0(view);
        }

        public View Q0(View view, int i) {
            return null;
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1668b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void R0(RecyclerView recyclerView, int i, int i2) {
        }

        public int S(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1668b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void S0(RecyclerView recyclerView) {
        }

        public int T(View view) {
            return view.getRight() + j0(view);
        }

        public void T0(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int U(View view) {
            return view.getTop() - m0(view);
        }

        public void U0(RecyclerView recyclerView, int i, int i2) {
        }

        public View V() {
            View focusedChild;
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1656a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void V0(RecyclerView recyclerView, int i, int i2) {
        }

        public int W() {
            return this.r;
        }

        public void W0(RecyclerView recyclerView, int i, int i2, Object obj) {
            V0(recyclerView, i, i2);
        }

        public int X() {
            return this.p;
        }

        public void X0(v vVar, z zVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int Y() {
            RecyclerView recyclerView = this.f1657b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        public void Y0(z zVar) {
        }

        public int Z() {
            return b.g.k.t.z(this.f1657b);
        }

        public void Z0(v vVar, z zVar, int i, int i2) {
            this.f1657b.x(i, i2);
        }

        public int a0(View view) {
            return ((p) view.getLayoutParams()).f1668b.left;
        }

        @Deprecated
        public boolean a1(RecyclerView recyclerView, View view, View view2) {
            return x0() || recyclerView.x0();
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            return b.g.k.t.A(this.f1657b);
        }

        public boolean b1(RecyclerView recyclerView, z zVar, View view, View view2) {
            return a1(recyclerView, view, view2);
        }

        public void c(View view, int i) {
            f(view, i, true);
        }

        public int c0() {
            return b.g.k.t.B(this.f1657b);
        }

        public void c1(Parcelable parcelable) {
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public Parcelable d1() {
            return null;
        }

        public void e(View view, int i) {
            f(view, i, false);
        }

        public int e0() {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e1(int i) {
        }

        public int f0() {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        void f1(y yVar) {
            if (this.g == yVar) {
                this.g = null;
            }
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0() {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        boolean g1(int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1657b;
            return h1(recyclerView.l, recyclerView.o0, i, bundle);
        }

        public void h(View view, int i) {
            i(view, i, (p) view.getLayoutParams());
        }

        public int h0(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public boolean h1(v vVar, z zVar, int i, Bundle bundle) {
            int iW;
            int iO0;
            int i2;
            int i3;
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                iW = recyclerView.canScrollVertically(1) ? (W() - g0()) - d0() : 0;
                if (this.f1657b.canScrollHorizontally(1)) {
                    iO0 = (o0() - e0()) - f0();
                    i2 = iW;
                    i3 = iO0;
                }
                i2 = iW;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                iW = recyclerView.canScrollVertically(-1) ? -((W() - g0()) - d0()) : 0;
                if (this.f1657b.canScrollHorizontally(-1)) {
                    iO0 = -((o0() - e0()) - f0());
                    i2 = iW;
                    i3 = iO0;
                }
                i2 = iW;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.f1657b.r1(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void i(View view, int i, p pVar) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0.v()) {
                this.f1657b.p.b(c0VarI0);
            } else {
                this.f1657b.p.p(c0VarI0);
            }
            this.f1656a.c(view, i, pVar, c0VarI0.v());
        }

        boolean i1(View view, int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1657b;
            return j1(recyclerView.l, recyclerView.o0, view, i, bundle);
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.n0(view));
            }
        }

        public int j0(View view) {
            return ((p) view.getLayoutParams()).f1668b.right;
        }

        public boolean j1(v vVar, z zVar, View view, int i, Bundle bundle) {
            return false;
        }

        public boolean k() {
            return false;
        }

        public int k0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView == null || recyclerView.v == null || !l()) {
                return 1;
            }
            return this.f1657b.v.c();
        }

        public void k1(v vVar) {
            for (int iJ = J() - 1; iJ >= 0; iJ--) {
                if (!RecyclerView.i0(I(iJ)).J()) {
                    n1(iJ, vVar);
                }
            }
        }

        public boolean l() {
            return false;
        }

        public int l0(v vVar, z zVar) {
            return 0;
        }

        void l1(v vVar) {
            int iJ = vVar.j();
            for (int i = iJ - 1; i >= 0; i--) {
                View viewN = vVar.n(i);
                c0 c0VarI0 = RecyclerView.i0(viewN);
                if (!c0VarI0.J()) {
                    c0VarI0.G(false);
                    if (c0VarI0.x()) {
                        this.f1657b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f1657b.W;
                    if (lVar != null) {
                        lVar.j(c0VarI0);
                    }
                    c0VarI0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f1657b.invalidate();
            }
        }

        public boolean m(p pVar) {
            return pVar != null;
        }

        public int m0(View view) {
            return ((p) view.getLayoutParams()).f1668b.top;
        }

        public void m1(View view, v vVar) {
            p1(view);
            vVar.B(view);
        }

        public void n0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f1668b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1657b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1657b.u;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void n1(int i, v vVar) {
            View viewI = I(i);
            q1(i);
            vVar.B(viewI);
        }

        public void o(int i, int i2, z zVar, c cVar) {
        }

        public int o0() {
            return this.q;
        }

        public boolean o1(Runnable runnable) {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void p(int i, c cVar) {
        }

        public int p0() {
            return this.o;
        }

        public void p1(View view) {
            this.f1656a.p(view);
        }

        public int q(z zVar) {
            return 0;
        }

        boolean q0() {
            int iJ = J();
            for (int i = 0; i < iJ; i++) {
                ViewGroup.LayoutParams layoutParams = I(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void q1(int i) {
            if (I(i) != null) {
                this.f1656a.q(i);
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public boolean r0() {
            return this.i;
        }

        public boolean r1(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return s1(recyclerView, view, rect, z, false);
        }

        public int s(z zVar) {
            return 0;
        }

        public boolean s0() {
            return this.j;
        }

        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] iArrL = L(view, rect);
            int i = iArrL[0];
            int i2 = iArrL[1];
            if ((z2 && !t0(recyclerView, i, i2)) || (i == 0 && i2 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.o1(i, i2);
            }
            return true;
        }

        public int t(z zVar) {
            return 0;
        }

        public void t1() {
            RecyclerView recyclerView = this.f1657b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int u(z zVar) {
            return 0;
        }

        public final boolean u0() {
            return this.l;
        }

        public void u1() {
            this.h = true;
        }

        public int v(z zVar) {
            return 0;
        }

        public boolean v0(v vVar, z zVar) {
            return false;
        }

        public void w(v vVar) {
            for (int iJ = J() - 1; iJ >= 0; iJ--) {
                v1(vVar, iJ, I(iJ));
            }
        }

        public int w1(int i, v vVar, z zVar) {
            return 0;
        }

        public void x(int i) {
            y(i, I(i));
        }

        public boolean x0() {
            y yVar = this.g;
            return yVar != null && yVar.h();
        }

        public void x1(int i) {
        }

        public boolean y0(View view, boolean z, boolean z2) {
            boolean z3 = this.f1660e.b(view, 24579) && this.f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public int y1(int i, v vVar, z zVar) {
            return 0;
        }

        void z(RecyclerView recyclerView) {
            this.i = true;
            G0(recyclerView);
        }

        public void z0(View view, int i, int i2, int i3, int i4) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1668b;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
        }

        void z1(RecyclerView recyclerView) {
            A1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
    }

    public interface q {
        void a(View view);

        void b(View view);
    }

    public static abstract class r {
        public abstract boolean a(int i, int i2);
    }

    public interface s {
        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(RecyclerView recyclerView, MotionEvent motionEvent);

        void c(boolean z);
    }

    public static abstract class t {
        public void a(RecyclerView recyclerView, int i) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public static class u {

        /* JADX INFO: renamed from: a */
        SparseArray<a> f1671a = new SparseArray<>();

        /* JADX INFO: renamed from: b */
        private int f1672b = 0;

        static class a {

            /* JADX INFO: renamed from: a */
            final ArrayList<c0> f1673a = new ArrayList<>();

            /* JADX INFO: renamed from: b */
            int f1674b = 5;

            /* JADX INFO: renamed from: c */
            long f1675c = 0;

            /* JADX INFO: renamed from: d */
            long f1676d = 0;

            a() {
            }
        }

        private a g(int i) {
            a aVar = this.f1671a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1671a.put(i, aVar2);
            return aVar2;
        }

        void a() {
            this.f1672b++;
        }

        public void b() {
            for (int i = 0; i < this.f1671a.size(); i++) {
                this.f1671a.valueAt(i).f1673a.clear();
            }
        }

        void c() {
            this.f1672b--;
        }

        void d(int i, long j) {
            a aVarG = g(i);
            aVarG.f1676d = j(aVarG.f1676d, j);
        }

        void e(int i, long j) {
            a aVarG = g(i);
            aVarG.f1675c = j(aVarG.f1675c, j);
        }

        public c0 f(int i) {
            a aVar = this.f1671a.get(i);
            if (aVar == null || aVar.f1673a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f1673a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        void h(g gVar, g gVar2, boolean z) {
            if (gVar != null) {
                c();
            }
            if (!z && this.f1672b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f1673a;
            if (this.f1671a.get(iL).f1674b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        boolean k(int i, long j, long j2) {
            long j3 = g(i).f1676d;
            return j3 == 0 || j + j3 < j2;
        }

        boolean l(int i, long j, long j2) {
            long j3 = g(i).f1675c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a */
        final ArrayList<c0> f1677a;

        /* JADX INFO: renamed from: b */
        ArrayList<c0> f1678b;

        /* JADX INFO: renamed from: c */
        final ArrayList<c0> f1679c;

        /* JADX INFO: renamed from: d */
        private final List<c0> f1680d;

        /* JADX INFO: renamed from: e */
        private int f1681e;
        int f;
        u g;
        private a0 h;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f1677a = arrayList;
            this.f1678b = null;
            this.f1679c = new ArrayList<>();
            this.f1680d = Collections.unmodifiableList(arrayList);
            this.f1681e = 2;
            this.f = 2;
        }

        private boolean H(c0 c0Var, int i, int i2, long j) {
            c0Var.s = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.g.k(iL, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.v.a(c0Var, i);
            this.g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.o0.e()) {
                return true;
            }
            c0Var.h = i2;
            return true;
        }

        private void b(c0 c0Var) {
            if (RecyclerView.this.w0()) {
                View view = c0Var.f1637b;
                if (b.g.k.t.x(view) == 0) {
                    b.g.k.t.u0(view, 1);
                }
                androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
                if (lVar == null) {
                    return;
                }
                b.g.k.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                b.g.k.t.k0(view, aVarN);
            }
        }

        private void q(ViewGroup viewGroup, boolean z) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    q((ViewGroup) childAt, true);
                }
            }
            if (z) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        private void r(c0 c0Var) {
            View view = c0Var.f1637b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i) {
            a(this.f1679c.get(i), true);
            this.f1679c.remove(i);
        }

        public void B(View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (c0VarI0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (c0VarI0.w()) {
                c0VarI0.K();
            } else if (c0VarI0.L()) {
                c0VarI0.e();
            }
            C(c0VarI0);
            if (RecyclerView.this.W == null || c0VarI0.u()) {
                return;
            }
            RecyclerView.this.W.j(c0VarI0);
        }

        void C(c0 c0Var) {
            boolean z;
            boolean z2 = true;
            if (c0Var.w() || c0Var.f1637b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f1637b.getParent() != null);
                sb.append(RecyclerView.this.Q());
                throw new IllegalArgumentException(sb.toString());
            }
            if (c0Var.x()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + c0Var + RecyclerView.this.Q());
            }
            if (c0Var.J()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.Q());
            }
            boolean zH = c0Var.h();
            g gVar = RecyclerView.this.v;
            if ((gVar != null && zH && gVar.n(c0Var)) || c0Var.u()) {
                if (this.f <= 0 || c0Var.p(526)) {
                    z = false;
                } else {
                    int size = this.f1679c.size();
                    if (size >= this.f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.f && size > 0 && !RecyclerView.this.n0.d(c0Var.f1639d)) {
                        int i = size - 1;
                        while (i >= 0) {
                            if (!RecyclerView.this.n0.d(this.f1679c.get(i).f1639d)) {
                                break;
                            } else {
                                i--;
                            }
                        }
                        size = i + 1;
                    }
                    this.f1679c.add(size, c0Var);
                    z = true;
                }
                if (z) {
                    z2 = false;
                } else {
                    a(c0Var, true);
                }
                z = z;
            } else {
                z2 = false;
            }
            RecyclerView.this.p.q(c0Var);
            if (z || z2 || !zH) {
                return;
            }
            c0Var.s = null;
        }

        void D(View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            if (!c0VarI0.p(12) && c0VarI0.y() && !RecyclerView.this.q(c0VarI0)) {
                if (this.f1678b == null) {
                    this.f1678b = new ArrayList<>();
                }
                c0VarI0.H(this, true);
                this.f1678b.add(c0VarI0);
                return;
            }
            if (!c0VarI0.t() || c0VarI0.v() || RecyclerView.this.v.g()) {
                c0VarI0.H(this, false);
                this.f1677a.add(c0VarI0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        void E(u uVar) {
            u uVar2 = this.g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.g.a();
        }

        void F(a0 a0Var) {
        }

        public void G(int i) {
            this.f1681e = i;
            K();
        }

        /* JADX WARN: Removed duplicated region for block: B:122:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:131:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:133:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:168:0x0134 A[PHI: r1 r4
  0x0134: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$c0) = (r1v11 androidx.recyclerview.widget.RecyclerView$c0), (r1v30 androidx.recyclerview.widget.RecyclerView$c0) binds: [B:132:0x005d, B:154:0x00b5] A[DONT_GENERATE, DONT_INLINE]
  0x0134: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:132:0x005d, B:154:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:177:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:183:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:195:0x01bf  */
        /* JADX WARN: Removed duplicated region for block: B:201:0x01db A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:203:0x01de  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        androidx.recyclerview.widget.RecyclerView.c0 I(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 538
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.I(int, boolean, long):androidx.recyclerview.widget.RecyclerView$c0");
        }

        void J(c0 c0Var) {
            if (c0Var.p) {
                this.f1678b.remove(c0Var);
            } else {
                this.f1677a.remove(c0Var);
            }
            c0Var.o = null;
            c0Var.p = false;
            c0Var.e();
        }

        void K() {
            o oVar = RecyclerView.this.w;
            this.f = this.f1681e + (oVar != null ? oVar.m : 0);
            for (int size = this.f1679c.size() - 1; size >= 0 && this.f1679c.size() > this.f; size--) {
                A(size);
            }
        }

        boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.o0.e();
            }
            int i = c0Var.f1639d;
            if (i >= 0 && i < RecyclerView.this.v.c()) {
                if (RecyclerView.this.o0.e() || RecyclerView.this.v.e(c0Var.f1639d) == c0Var.l()) {
                    return !RecyclerView.this.v.g() || c0Var.k() == RecyclerView.this.v.d(c0Var.f1639d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.Q());
        }

        void M(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.f1679c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1679c.get(size);
                if (c0Var != null && (i3 = c0Var.f1639d) >= i && i3 < i4) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        void a(c0 c0Var, boolean z) {
            RecyclerView.s(c0Var);
            View view = c0Var.f1637b;
            androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
            if (lVar != null) {
                b.g.k.a aVarN = lVar.n();
                b.g.k.t.k0(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z) {
                g(c0Var);
            }
            c0Var.s = null;
            i().i(c0Var);
        }

        public void c() {
            this.f1677a.clear();
            z();
        }

        void d() {
            int size = this.f1679c.size();
            for (int i = 0; i < size; i++) {
                this.f1679c.get(i).c();
            }
            int size2 = this.f1677a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.f1677a.get(i2).c();
            }
            ArrayList<c0> arrayList = this.f1678b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.f1678b.get(i3).c();
                }
            }
        }

        void e() {
            this.f1677a.clear();
            ArrayList<c0> arrayList = this.f1678b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i) {
            if (i >= 0 && i < RecyclerView.this.o0.b()) {
                return !RecyclerView.this.o0.e() ? i : RecyclerView.this.n.m(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.o0.b() + RecyclerView.this.Q());
        }

        void g(c0 c0Var) {
            w wVar = RecyclerView.this.x;
            if (wVar != null) {
                wVar.a(c0Var);
            }
            g gVar = RecyclerView.this.v;
            if (gVar != null) {
                gVar.q(c0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.o0 != null) {
                recyclerView.p.q(c0Var);
            }
        }

        c0 h(int i) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f1678b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    c0 c0Var = this.f1678b.get(i2);
                    if (!c0Var.L() && c0Var.m() == i) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.v.g() && (iM = RecyclerView.this.n.m(i)) > 0 && iM < RecyclerView.this.v.c()) {
                    long jD = RecyclerView.this.v.d(iM);
                    for (int i3 = 0; i3 < size; i3++) {
                        c0 c0Var2 = this.f1678b.get(i3);
                        if (!c0Var2.L() && c0Var2.k() == jD) {
                            c0Var2.b(32);
                            return c0Var2;
                        }
                    }
                }
            }
            return null;
        }

        u i() {
            if (this.g == null) {
                this.g = new u();
            }
            return this.g;
        }

        int j() {
            return this.f1677a.size();
        }

        public List<c0> k() {
            return this.f1680d;
        }

        c0 l(long j, int i, boolean z) {
            for (int size = this.f1677a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1677a.get(size);
                if (c0Var.k() == j && !c0Var.L()) {
                    if (i == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.o0.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z) {
                        this.f1677a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f1637b, false);
                        y(c0Var.f1637b);
                    }
                }
            }
            int size2 = this.f1679c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f1679c.get(size2);
                if (c0Var2.k() == j && !c0Var2.r()) {
                    if (i == c0Var2.l()) {
                        if (!z) {
                            this.f1679c.remove(size2);
                        }
                        return c0Var2;
                    }
                    if (!z) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        c0 m(int i, boolean z) {
            View viewE;
            int size = this.f1677a.size();
            for (int i2 = 0; i2 < size; i2++) {
                c0 c0Var = this.f1677a.get(i2);
                if (!c0Var.L() && c0Var.m() == i && !c0Var.t() && (RecyclerView.this.o0.h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z || (viewE = RecyclerView.this.o.e(i)) == null) {
                int size2 = this.f1679c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    c0 c0Var2 = this.f1679c.get(i3);
                    if (!c0Var2.t() && c0Var2.m() == i && !c0Var2.r()) {
                        if (!z) {
                            this.f1679c.remove(i3);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarI0 = RecyclerView.i0(viewE);
            RecyclerView.this.o.s(viewE);
            int iM = RecyclerView.this.o.m(viewE);
            if (iM != -1) {
                RecyclerView.this.o.d(iM);
                D(viewE);
                c0VarI0.b(8224);
                return c0VarI0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarI0 + RecyclerView.this.Q());
        }

        View n(int i) {
            return this.f1677a.get(i).f1637b;
        }

        public View o(int i) {
            return p(i, false);
        }

        View p(int i, boolean z) {
            return I(i, z, Long.MAX_VALUE).f1637b;
        }

        void s() {
            int size = this.f1679c.size();
            for (int i = 0; i < size; i++) {
                p pVar = (p) this.f1679c.get(i).f1637b.getLayoutParams();
                if (pVar != null) {
                    pVar.f1669c = true;
                }
            }
        }

        void t() {
            int size = this.f1679c.size();
            for (int i = 0; i < size; i++) {
                c0 c0Var = this.f1679c.get(i);
                if (c0Var != null) {
                    c0Var.b(6);
                    c0Var.a(null);
                }
            }
            g gVar = RecyclerView.this.v;
            if (gVar == null || !gVar.g()) {
                z();
            }
        }

        void u(int i, int i2) {
            int size = this.f1679c.size();
            for (int i3 = 0; i3 < size; i3++) {
                c0 c0Var = this.f1679c.get(i3);
                if (c0Var != null && c0Var.f1639d >= i) {
                    c0Var.A(i2, true);
                }
            }
        }

        void v(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            if (i < i2) {
                i3 = -1;
                i5 = i;
                i4 = i2;
            } else {
                i3 = 1;
                i4 = i;
                i5 = i2;
            }
            int size = this.f1679c.size();
            for (int i7 = 0; i7 < size; i7++) {
                c0 c0Var = this.f1679c.get(i7);
                if (c0Var != null && (i6 = c0Var.f1639d) >= i5 && i6 <= i4) {
                    if (i6 == i) {
                        c0Var.A(i2 - i, false);
                    } else {
                        c0Var.A(i3, false);
                    }
                }
            }
        }

        void w(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.f1679c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1679c.get(size);
                if (c0Var != null) {
                    int i4 = c0Var.f1639d;
                    if (i4 >= i3) {
                        c0Var.A(-i2, z);
                    } else if (i4 >= i) {
                        c0Var.b(8);
                        A(size);
                    }
                }
            }
        }

        void x(g gVar, g gVar2, boolean z) {
            c();
            i().h(gVar, gVar2, z);
        }

        void y(View view) {
            c0 c0VarI0 = RecyclerView.i0(view);
            c0VarI0.o = null;
            c0VarI0.p = false;
            c0VarI0.e();
            C(c0VarI0);
        }

        void z() {
            for (int size = this.f1679c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1679c.clear();
            if (RecyclerView.f) {
                RecyclerView.this.n0.b();
            }
        }
    }

    public interface w {
        void a(c0 c0Var);
    }

    private class x extends i {
        x() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.p(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.o0.g = true;
            recyclerView.S0(true);
            if (RecyclerView.this.n.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    public static abstract class y {

        /* JADX INFO: renamed from: b */
        private RecyclerView f1684b;

        /* JADX INFO: renamed from: c */
        private o f1685c;

        /* JADX INFO: renamed from: d */
        private boolean f1686d;

        /* JADX INFO: renamed from: e */
        private boolean f1687e;
        private View f;
        private boolean h;

        /* JADX INFO: renamed from: a */
        private int f1683a = -1;
        private final a g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a */
            private int f1688a;

            /* JADX INFO: renamed from: b */
            private int f1689b;

            /* JADX INFO: renamed from: c */
            private int f1690c;

            /* JADX INFO: renamed from: d */
            private int f1691d;

            /* JADX INFO: renamed from: e */
            private Interpolator f1692e;
            private boolean f;
            private int g;

            public a(int i, int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            private void e() {
                if (this.f1692e != null && this.f1690c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1690c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.f1691d >= 0;
            }

            public void b(int i) {
                this.f1691d = i;
            }

            void c(RecyclerView recyclerView) {
                int i = this.f1691d;
                if (i >= 0) {
                    this.f1691d = -1;
                    recyclerView.z0(i);
                    this.f = false;
                } else {
                    if (!this.f) {
                        this.g = 0;
                        return;
                    }
                    e();
                    recyclerView.l0.f(this.f1688a, this.f1689b, this.f1690c, this.f1692e);
                    int i2 = this.g + 1;
                    this.g = i2;
                    if (i2 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                }
            }

            public void d(int i, int i2, int i3, Interpolator interpolator) {
                this.f1688a = i;
                this.f1689b = i2;
                this.f1690c = i3;
                this.f1692e = interpolator;
                this.f = true;
            }

            public a(int i, int i2, int i3, Interpolator interpolator) {
                this.f1691d = -1;
                this.f = false;
                this.g = 0;
                this.f1688a = i;
                this.f1689b = i2;
                this.f1690c = i3;
                this.f1692e = interpolator;
            }
        }

        public interface b {
            PointF a(int i);
        }

        public PointF a(int i) {
            Object objE = e();
            if (objE instanceof b) {
                return ((b) objE).a(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public View b(int i) {
            return this.f1684b.w.C(i);
        }

        public int c() {
            return this.f1684b.w.J();
        }

        public int d(View view) {
            return this.f1684b.g0(view);
        }

        public o e() {
            return this.f1685c;
        }

        public int f() {
            return this.f1683a;
        }

        public boolean g() {
            return this.f1686d;
        }

        public boolean h() {
            return this.f1687e;
        }

        protected void i(PointF pointF) {
            float f = pointF.x;
            float f2 = pointF.y;
            float fSqrt = (float) Math.sqrt((f * f) + (f2 * f2));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        void j(int i, int i2) {
            PointF pointFA;
            RecyclerView recyclerView = this.f1684b;
            if (this.f1683a == -1 || recyclerView == null) {
                r();
            }
            if (this.f1686d && this.f == null && this.f1685c != null && (pointFA = a(this.f1683a)) != null) {
                float f = pointFA.x;
                if (f != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.j1((int) Math.signum(f), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f1686d = false;
            View view = this.f;
            if (view != null) {
                if (d(view) == this.f1683a) {
                    o(this.f, recyclerView.o0, this.g);
                    this.g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.f1687e) {
                l(i, i2, recyclerView.o0, this.g);
                boolean zA = this.g.a();
                this.g.c(recyclerView);
                if (zA && this.f1687e) {
                    this.f1686d = true;
                    recyclerView.l0.e();
                }
            }
        }

        protected void k(View view) {
            if (d(view) == f()) {
                this.f = view;
            }
        }

        protected abstract void l(int i, int i2, z zVar, a aVar);

        protected abstract void m();

        protected abstract void n();

        protected abstract void o(View view, z zVar, a aVar);

        public void p(int i) {
            this.f1683a = i;
        }

        void q(RecyclerView recyclerView, o oVar) {
            recyclerView.l0.g();
            if (this.h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1684b = recyclerView;
            this.f1685c = oVar;
            int i = this.f1683a;
            if (i == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.o0.f1693a = i;
            this.f1687e = true;
            this.f1686d = true;
            this.f = b(f());
            m();
            this.f1684b.l0.e();
            this.h = true;
        }

        protected final void r() {
            if (this.f1687e) {
                this.f1687e = false;
                n();
                this.f1684b.o0.f1693a = -1;
                this.f = null;
                this.f1683a = -1;
                this.f1686d = false;
                this.f1685c.f1(this);
                this.f1685c = null;
                this.f1684b = null;
            }
        }
    }

    public static class z {

        /* JADX INFO: renamed from: b */
        private SparseArray<Object> f1694b;
        int m;
        long n;
        int o;
        int p;
        int q;

        /* JADX INFO: renamed from: a */
        int f1693a = -1;

        /* JADX INFO: renamed from: c */
        int f1695c = 0;

        /* JADX INFO: renamed from: d */
        int f1696d = 0;

        /* JADX INFO: renamed from: e */
        int f1697e = 1;
        int f = 0;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;

        void a(int i) {
            if ((this.f1697e & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.f1697e));
        }

        public int b() {
            return this.h ? this.f1695c - this.f1696d : this.f;
        }

        public int c() {
            return this.f1693a;
        }

        public boolean d() {
            return this.f1693a != -1;
        }

        public boolean e() {
            return this.h;
        }

        void f(g gVar) {
            this.f1697e = 1;
            this.f = gVar.c();
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1693a + ", mData=" + this.f1694b + ", mItemCount=" + this.f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1695c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1696d + ", mStructureChanged=" + this.g + ", mInPreLayout=" + this.h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f1626c = i2 == 18 || i2 == 19 || i2 == 20;
        f1627d = i2 >= 23;
        f1628e = i2 >= 16;
        f = i2 >= 21;
        g = i2 <= 15;
        h = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        i = new Class[]{Context.class, AttributeSet.class, cls, cls};
        j = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void B() {
        int i2 = this.J;
        this.J = 0;
        if (i2 == 0 || !w0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        b.g.k.c0.b.b(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private void D() {
        this.o0.a(1);
        R(this.o0);
        this.o0.j = false;
        t1();
        this.p.f();
        J0();
        R0();
        g1();
        z zVar = this.o0;
        zVar.i = zVar.k && this.s0;
        this.s0 = false;
        this.r0 = false;
        zVar.h = zVar.l;
        zVar.f = this.v.c();
        V(this.x0);
        if (this.o0.k) {
            int iG = this.o.g();
            for (int i2 = 0; i2 < iG; i2++) {
                c0 c0VarI0 = i0(this.o.f(i2));
                if (!c0VarI0.J() && (!c0VarI0.t() || this.v.g())) {
                    this.p.e(c0VarI0, this.W.t(this.o0, c0VarI0, l.e(c0VarI0), c0VarI0.o()));
                    if (this.o0.i && c0VarI0.y() && !c0VarI0.v() && !c0VarI0.J() && !c0VarI0.t()) {
                        this.p.c(e0(c0VarI0), c0VarI0);
                    }
                }
            }
        }
        if (this.o0.l) {
            h1();
            z zVar2 = this.o0;
            boolean z2 = zVar2.g;
            zVar2.g = false;
            this.w.X0(this.l, zVar2);
            this.o0.g = z2;
            for (int i3 = 0; i3 < this.o.g(); i3++) {
                c0 c0VarI02 = i0(this.o.f(i3));
                if (!c0VarI02.J() && !this.p.i(c0VarI02)) {
                    int iE = l.e(c0VarI02);
                    boolean zP = c0VarI02.p(CarPlayConfig.IapFeature.IAP_FEATURE_OOB_BT_PAIRING);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.W.t(this.o0, c0VarI02, iE, c0VarI02.o());
                    if (zP) {
                        U0(c0VarI02, cVarT);
                    } else {
                        this.p.a(c0VarI02, cVarT);
                    }
                }
            }
            t();
        } else {
            t();
        }
        K0();
        v1(false);
        this.o0.f1697e = 2;
    }

    private void E() {
        t1();
        J0();
        this.o0.a(6);
        this.n.j();
        this.o0.f = this.v.c();
        z zVar = this.o0;
        zVar.f1696d = 0;
        zVar.h = false;
        this.w.X0(this.l, zVar);
        z zVar2 = this.o0;
        zVar2.g = false;
        this.m = null;
        zVar2.k = zVar2.k && this.W != null;
        zVar2.f1697e = 4;
        K0();
        v1(false);
    }

    private void F() {
        this.o0.a(4);
        t1();
        J0();
        z zVar = this.o0;
        zVar.f1697e = 1;
        if (zVar.k) {
            for (int iG = this.o.g() - 1; iG >= 0; iG--) {
                c0 c0VarI0 = i0(this.o.f(iG));
                if (!c0VarI0.J()) {
                    long jE0 = e0(c0VarI0);
                    l.c cVarS = this.W.s(this.o0, c0VarI0);
                    c0 c0VarG = this.p.g(jE0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.p.d(c0VarI0, cVarS);
                    } else {
                        boolean zH = this.p.h(c0VarG);
                        boolean zH2 = this.p.h(c0VarI0);
                        if (zH && c0VarG == c0VarI0) {
                            this.p.d(c0VarI0, cVarS);
                        } else {
                            l.c cVarN = this.p.n(c0VarG);
                            this.p.d(c0VarI0, cVarS);
                            l.c cVarM = this.p.m(c0VarI0);
                            if (cVarN == null) {
                                o0(jE0, c0VarI0, c0VarG);
                            } else {
                                n(c0VarG, c0VarI0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.p.o(this.E0);
        }
        this.w.l1(this.l);
        z zVar2 = this.o0;
        zVar2.f1695c = zVar2.f;
        this.N = false;
        this.O = false;
        zVar2.k = false;
        zVar2.l = false;
        this.w.h = false;
        ArrayList<c0> arrayList = this.l.f1678b;
        if (arrayList != null) {
            arrayList.clear();
        }
        o oVar = this.w;
        if (oVar.n) {
            oVar.m = 0;
            oVar.n = false;
            this.l.K();
        }
        this.w.Y0(this.o0);
        K0();
        v1(false);
        this.p.f();
        int[] iArr = this.x0;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        V0();
        e1();
    }

    private boolean L(MotionEvent motionEvent) {
        s sVar = this.A;
        if (sVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return U(motionEvent);
        }
        sVar.b(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.A = null;
        }
        return true;
    }

    private void M0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.Y) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.Y = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.c0 = x2;
            this.a0 = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.d0 = y2;
            this.b0 = y2;
        }
    }

    private boolean Q0() {
        return this.W != null && this.w.L1();
    }

    private void R0() {
        boolean z2;
        if (this.N) {
            this.n.u();
            if (this.O) {
                this.w.S0(this);
            }
        }
        if (Q0()) {
            this.n.s();
        } else {
            this.n.j();
        }
        boolean z3 = false;
        boolean z4 = this.r0 || this.s0;
        this.o0.k = this.E && this.W != null && ((z2 = this.N) || z4 || this.w.h) && (!z2 || this.v.g());
        z zVar = this.o0;
        if (zVar.k && z4 && !this.N && Q0()) {
            z3 = true;
        }
        zVar.l = z3;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void T0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L21
            r6.N()
            android.widget.EdgeEffect r1 = r6.S
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
            androidx.core.widget.d.a(r1, r4, r9)
        L1f:
            r9 = r3
            goto L3c
        L21:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L3b
            r6.O()
            android.widget.EdgeEffect r1 = r6.U
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            androidx.core.widget.d.a(r1, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L56
            r6.P()
            android.widget.EdgeEffect r9 = r6.T
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            androidx.core.widget.d.a(r9, r1, r7)
            goto L72
        L56:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L71
            r6.M()
            android.widget.EdgeEffect r9 = r6.V
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            androidx.core.widget.d.a(r9, r1, r2)
            goto L72
        L71:
            r3 = r9
        L72:
            if (r3 != 0) goto L7c
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 != 0) goto L7c
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 == 0) goto L7f
        L7c:
            b.g.k.t.a0(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.T0(float, float, float, float):void");
    }

    private boolean U(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.z.size();
        for (int i2 = 0; i2 < size; i2++) {
            s sVar = this.z.get(i2);
            if (sVar.a(this, motionEvent) && action != 3) {
                this.A = sVar;
                return true;
            }
        }
        return false;
    }

    private void V(int[] iArr) {
        int iG = this.o.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iG; i4++) {
            c0 c0VarI0 = i0(this.o.f(i4));
            if (!c0VarI0.J()) {
                int iM = c0VarI0.m();
                if (iM < i2) {
                    i2 = iM;
                }
                if (iM > i3) {
                    i3 = iM;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    private void V0() {
        View viewFindViewById;
        if (!this.k0 || this.v == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!h || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.o.n(focusedChild)) {
                    return;
                }
            } else if (this.o.g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewX = null;
        c0 c0VarZ = (this.o0.n == -1 || !this.v.g()) ? null : Z(this.o0.n);
        if (c0VarZ != null && !this.o.n(c0VarZ.f1637b) && c0VarZ.f1637b.hasFocusable()) {
            viewX = c0VarZ.f1637b;
        } else if (this.o.g() > 0) {
            viewX = X();
        }
        if (viewX != null) {
            int i2 = this.o0.o;
            if (i2 != -1 && (viewFindViewById = viewX.findViewById(i2)) != null && viewFindViewById.isFocusable()) {
                viewX = viewFindViewById;
            }
            viewX.requestFocus();
        }
    }

    static RecyclerView W(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewW = W(viewGroup.getChildAt(i2));
            if (recyclerViewW != null) {
                return recyclerViewW;
            }
        }
        return null;
    }

    private void W0() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.S.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.T;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.T.isFinished();
        }
        EdgeEffect edgeEffect3 = this.U;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect4 = this.V;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        if (zIsFinished) {
            b.g.k.t.a0(this);
        }
    }

    private View X() {
        c0 c0VarY;
        z zVar = this.o0;
        int i2 = zVar.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int iB = zVar.b();
        for (int i3 = i2; i3 < iB; i3++) {
            c0 c0VarY2 = Y(i3);
            if (c0VarY2 == null) {
                break;
            }
            if (c0VarY2.f1637b.hasFocusable()) {
                return c0VarY2.f1637b;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (c0VarY = Y(iMin)) == null) {
                return null;
            }
        } while (!c0VarY.f1637b.hasFocusable());
        return c0VarY.f1637b;
    }

    private void d1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.s.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1669c) {
                Rect rect = pVar.f1668b;
                Rect rect2 = this.s;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.s);
            offsetRectIntoDescendantCoords(view, this.s);
        }
        this.w.s1(this, view, this.s, !this.E, view2 == null);
    }

    private void e1() {
        z zVar = this.o0;
        zVar.n = -1L;
        zVar.m = -1;
        zVar.o = -1;
    }

    private void f1() {
        VelocityTracker velocityTracker = this.Z;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        w1(0);
        W0();
    }

    private void g(c0 c0Var) {
        View view = c0Var.f1637b;
        boolean z2 = view.getParent() == this;
        this.l.J(h0(view));
        if (c0Var.x()) {
            this.o.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.o.k(view);
        } else {
            this.o.b(view, true);
        }
    }

    private void g1() {
        View focusedChild = (this.k0 && hasFocus() && this.v != null) ? getFocusedChild() : null;
        c0 c0VarT = focusedChild != null ? T(focusedChild) : null;
        if (c0VarT == null) {
            e1();
            return;
        }
        this.o0.n = this.v.g() ? c0VarT.k() : -1L;
        this.o0.m = this.N ? -1 : c0VarT.v() ? c0VarT.f1640e : c0VarT.j();
        this.o0.o = l0(c0VarT.f1637b);
    }

    private b.g.k.k getScrollingChildHelper() {
        if (this.y0 == null) {
            this.y0 = new b.g.k.k(this);
        }
        return this.y0;
    }

    static c0 i0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1667a;
    }

    static void k0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1668b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    private int l0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private void l1(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.v;
        if (gVar2 != null) {
            gVar2.t(this.k);
            this.v.m(this);
        }
        if (!z2 || z3) {
            X0();
        }
        this.n.u();
        g gVar3 = this.v;
        this.v = gVar;
        if (gVar != null) {
            gVar.r(this.k);
            gVar.i(this);
        }
        o oVar = this.w;
        if (oVar != null) {
            oVar.E0(gVar3, this.v);
        }
        this.l.x(gVar3, this.v, z2);
        this.o0.g = true;
    }

    private String m0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void n(c0 c0Var, c0 c0Var2, l.c cVar, l.c cVar2, boolean z2, boolean z3) {
        c0Var.G(false);
        if (z2) {
            g(c0Var);
        }
        if (c0Var != c0Var2) {
            if (z3) {
                g(c0Var2);
            }
            c0Var.i = c0Var2;
            g(c0Var);
            this.l.J(c0Var);
            c0Var2.G(false);
            c0Var2.j = c0Var;
        }
        if (this.W.b(c0Var, c0Var2, cVar, cVar2)) {
            P0();
        }
    }

    private void o0(long j2, c0 c0Var, c0 c0Var2) {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarI0 = i0(this.o.f(i2));
            if (c0VarI0 != c0Var && e0(c0VarI0) == j2) {
                g gVar = this.v;
                if (gVar == null || !gVar.g()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarI0 + " \n View Holder 2:" + c0Var + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarI0 + " \n View Holder 2:" + c0Var + Q());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + Q());
    }

    private boolean q0() {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarI0 = i0(this.o.f(i2));
            if (c0VarI0 != null && !c0VarI0.J() && c0VarI0.y()) {
                return true;
            }
        }
        return false;
    }

    private void r() {
        f1();
        setScrollState(0);
    }

    static void s(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f1638c;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f1637b) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f1638c = null;
        }
    }

    @SuppressLint({"InlinedApi"})
    private void s0() {
        if (b.g.k.t.y(this) == 0) {
            b.g.k.t.v0(this, 8);
        }
    }

    private void t0() {
        this.o = new androidx.recyclerview.widget.b(new e());
    }

    private void w(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String strM0 = m0(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = Class.forName(strM0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(o.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(i);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strM0, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strM0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strM0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strM0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strM0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strM0, e8);
            }
        }
    }

    private boolean y(int i2, int i3) {
        V(this.x0);
        int[] iArr = this.x0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    private boolean y0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || S(view2) == null) {
            return false;
        }
        if (view == null || S(view) == null) {
            return true;
        }
        this.s.set(0, 0, view.getWidth(), view.getHeight());
        this.t.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.s);
        offsetDescendantRectToMyCoords(view2, this.t);
        byte b2 = -1;
        int i4 = this.w.Z() == 1 ? -1 : 1;
        Rect rect = this.s;
        int i5 = rect.left;
        Rect rect2 = this.t;
        int i6 = rect2.left;
        if ((i5 < i6 || rect.right <= i6) && rect.right < rect2.right) {
            i3 = 1;
        } else {
            int i7 = rect.right;
            int i8 = rect2.right;
            i3 = ((i7 > i8 || i5 >= i8) && i5 > i6) ? -1 : 0;
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        if ((i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom) {
            b2 = 1;
        } else {
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if ((i11 <= i12 && i9 < i12) || i9 <= i10) {
                b2 = 0;
            }
        }
        if (i2 == 1) {
            return b2 < 0 || (b2 == 0 && i3 * i4 <= 0);
        }
        if (i2 == 2) {
            return b2 > 0 || (b2 == 0 && i3 * i4 >= 0);
        }
        if (i2 == 17) {
            return i3 < 0;
        }
        if (i2 == 33) {
            return b2 < 0;
        }
        if (i2 == 66) {
            return i3 > 0;
        }
        if (i2 == 130) {
            return b2 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i2 + Q());
    }

    private void y1() {
        this.l0.g();
        o oVar = this.w;
        if (oVar != null) {
            oVar.K1();
        }
    }

    void A(View view) {
        c0 c0VarI0 = i0(view);
        I0(view);
        g gVar = this.v;
        if (gVar != null && c0VarI0 != null) {
            gVar.p(c0VarI0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).b(view);
            }
        }
    }

    void A0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((p) this.o.i(i2).getLayoutParams()).f1669c = true;
        }
        this.l.s();
    }

    void B0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarI0 = i0(this.o.i(i2));
            if (c0VarI0 != null && !c0VarI0.J()) {
                c0VarI0.b(6);
            }
        }
        A0();
        this.l.t();
    }

    void C() {
        if (this.v == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.w == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        z zVar = this.o0;
        zVar.j = false;
        if (zVar.f1697e == 1) {
            D();
            this.w.z1(this);
            E();
        } else if (!this.n.q() && this.w.o0() == getWidth() && this.w.W() == getHeight()) {
            this.w.z1(this);
        } else {
            this.w.z1(this);
            E();
        }
        F();
    }

    public void C0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetLeftAndRight(i2);
        }
    }

    public void D0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetTopAndBottom(i2);
        }
    }

    void E0(int i2, int i3) {
        int iJ = this.o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            c0 c0VarI0 = i0(this.o.i(i4));
            if (c0VarI0 != null && !c0VarI0.J() && c0VarI0.f1639d >= i2) {
                c0VarI0.A(i3, false);
                this.o0.g = true;
            }
        }
        this.l.u(i2, i3);
        requestLayout();
    }

    void F0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int iJ = this.o.j();
        if (i2 < i3) {
            i6 = -1;
            i5 = i2;
            i4 = i3;
        } else {
            i4 = i2;
            i5 = i3;
            i6 = 1;
        }
        for (int i8 = 0; i8 < iJ; i8++) {
            c0 c0VarI0 = i0(this.o.i(i8));
            if (c0VarI0 != null && (i7 = c0VarI0.f1639d) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    c0VarI0.A(i3 - i2, false);
                } else {
                    c0VarI0.A(i6, false);
                }
                this.o0.g = true;
            }
        }
        this.l.v(i2, i3);
        requestLayout();
    }

    public boolean G(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    void G0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.o.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            c0 c0VarI0 = i0(this.o.i(i5));
            if (c0VarI0 != null && !c0VarI0.J()) {
                int i6 = c0VarI0.f1639d;
                if (i6 >= i4) {
                    c0VarI0.A(-i3, z2);
                    this.o0.g = true;
                } else if (i6 >= i2) {
                    c0VarI0.i(i2 - 1, -i3, z2);
                    this.o0.g = true;
                }
            }
        }
        this.l.w(i2, i3, z2);
        requestLayout();
    }

    public final void H(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void H0(View view) {
    }

    void I(int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.e1(i2);
        }
        N0(i2);
        t tVar = this.p0;
        if (tVar != null) {
            tVar.a(this, i2);
        }
        List<t> list = this.q0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.q0.get(size).a(this, i2);
            }
        }
    }

    public void I0(View view) {
    }

    void J(int i2, int i3) {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        O0(i2, i3);
        t tVar = this.p0;
        if (tVar != null) {
            tVar.b(this, i2, i3);
        }
        List<t> list = this.q0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.q0.get(size).b(this, i2, i3);
            }
        }
        this.Q--;
    }

    void J0() {
        this.P++;
    }

    void K() {
        int i2;
        for (int size = this.C0.size() - 1; size >= 0; size--) {
            c0 c0Var = this.C0.get(size);
            if (c0Var.f1637b.getParent() == this && !c0Var.J() && (i2 = c0Var.r) != -1) {
                b.g.k.t.u0(c0Var.f1637b, i2);
                c0Var.r = -1;
            }
        }
        this.C0.clear();
    }

    void K0() {
        L0(true);
    }

    void L0(boolean z2) {
        int i2 = this.P - 1;
        this.P = i2;
        if (i2 < 1) {
            this.P = 0;
            if (z2) {
                B();
                K();
            }
        }
    }

    void M() {
        if (this.V != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 3);
        this.V = edgeEffectA;
        if (this.q) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void N() {
        if (this.S != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 0);
        this.S = edgeEffectA;
        if (this.q) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void N0(int i2) {
    }

    void O() {
        if (this.U != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 2);
        this.U = edgeEffectA;
        if (this.q) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void O0(int i2, int i3) {
    }

    void P() {
        if (this.T != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 1);
        this.T = edgeEffectA;
        if (this.q) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    void P0() {
        if (this.u0 || !this.B) {
            return;
        }
        b.g.k.t.c0(this, this.D0);
        this.u0 = true;
    }

    String Q() {
        return " " + super.toString() + ", adapter:" + this.v + ", layout:" + this.w + ", context:" + getContext();
    }

    final void R(z zVar) {
        if (getScrollState() != 2) {
            zVar.p = 0;
            zVar.q = 0;
        } else {
            OverScroller overScroller = this.l0.f1634d;
            zVar.p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public View S(View view) {
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

    void S0(boolean z2) {
        this.O = z2 | this.O;
        this.N = true;
        B0();
    }

    public c0 T(View view) {
        View viewS = S(view);
        if (viewS == null) {
            return null;
        }
        return h0(viewS);
    }

    void U0(c0 c0Var, l.c cVar) {
        c0Var.F(0, CarPlayConfig.IapFeature.IAP_FEATURE_OOB_BT_PAIRING);
        if (this.o0.i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.p.c(e0(c0Var), c0Var);
        }
        this.p.e(c0Var, cVar);
    }

    void X0() {
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        o oVar = this.w;
        if (oVar != null) {
            oVar.k1(this.l);
            this.w.l1(this.l);
        }
        this.l.c();
    }

    public c0 Y(int i2) {
        c0 c0Var = null;
        if (this.N) {
            return null;
        }
        int iJ = this.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarI0 = i0(this.o.i(i3));
            if (c0VarI0 != null && !c0VarI0.v() && d0(c0VarI0) == i2) {
                if (!this.o.n(c0VarI0.f1637b)) {
                    return c0VarI0;
                }
                c0Var = c0VarI0;
            }
        }
        return c0Var;
    }

    boolean Y0(View view) {
        t1();
        boolean zR = this.o.r(view);
        if (zR) {
            c0 c0VarI0 = i0(view);
            this.l.J(c0VarI0);
            this.l.C(c0VarI0);
        }
        v1(!zR);
        return zR;
    }

    public c0 Z(long j2) {
        g gVar = this.v;
        c0 c0Var = null;
        if (gVar != null && gVar.g()) {
            int iJ = this.o.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                c0 c0VarI0 = i0(this.o.i(i2));
                if (c0VarI0 != null && !c0VarI0.v() && c0VarI0.k() == j2) {
                    if (!this.o.n(c0VarI0.f1637b)) {
                        return c0VarI0;
                    }
                    c0Var = c0VarI0;
                }
            }
        }
        return c0Var;
    }

    public void Z0(n nVar) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.y.remove(nVar);
        if (this.y.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        A0();
        requestLayout();
    }

    void a(int i2, int i3) {
        if (i2 < 0) {
            N();
            if (this.S.isFinished()) {
                this.S.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            O();
            if (this.U.isFinished()) {
                this.U.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            P();
            if (this.T.isFinished()) {
                this.T.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            M();
            if (this.V.isFinished()) {
                this.V.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        b.g.k.t.a0(this);
    }

    public c0 a0(int i2) {
        return b0(i2, false);
    }

    public void a1(s sVar) {
        this.z.remove(sVar);
        if (this.A == sVar) {
            this.A = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        o oVar = this.w;
        if (oVar == null || !oVar.F0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.recyclerview.widget.RecyclerView.c0 b0(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.b r0 = r5.o
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.b r3 = r5.o
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$c0 r3 = i0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1639d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.b r1 = r5.o
            android.view.View r4 = r3.f1637b
            boolean r1 = r1.n(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.b0(int, boolean):androidx.recyclerview.widget.RecyclerView$c0");
    }

    public void b1(t tVar) {
        List<t> list = this.q0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    public boolean c0(int i2, int i3) {
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.H) {
            return false;
        }
        boolean zK = oVar.k();
        boolean zL = this.w.l();
        if (!zK || Math.abs(i2) < this.g0) {
            i2 = 0;
        }
        if (!zL || Math.abs(i3) < this.g0) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = zK || zL;
            dispatchNestedFling(f2, f3, z2);
            r rVar = this.f0;
            if (rVar != null && rVar.a(i2, i3)) {
                return true;
            }
            if (z2) {
                int i4 = zK ? 1 : 0;
                if (zL) {
                    i4 |= 2;
                }
                u1(i4, 1);
                int i5 = this.h0;
                int iMax = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.h0;
                this.l0.c(iMax, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    void c1() {
        c0 c0Var;
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.o.f(i2);
            c0 c0VarH0 = h0(viewF);
            if (c0VarH0 != null && (c0Var = c0VarH0.j) != null) {
                View view = c0Var.f1637b;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.w.m((p) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.w;
        if (oVar != null && oVar.k()) {
            return this.w.q(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.w;
        if (oVar != null && oVar.k()) {
            return this.w.r(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.w;
        if (oVar != null && oVar.k()) {
            return this.w.s(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.w;
        if (oVar != null && oVar.l()) {
            return this.w.t(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.w;
        if (oVar != null && oVar.l()) {
            return this.w.u(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.w;
        if (oVar != null && oVar.l()) {
            return this.w.v(this.o0);
        }
        return 0;
    }

    int d0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.n.e(c0Var.f1639d);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return getScrollingChildHelper().a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return getScrollingChildHelper().b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().f(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z2;
        super.draw(canvas);
        int size = this.y.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.y.get(i2).i(canvas, this, this.o0);
        }
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.q ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.S;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.q) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.T;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.U;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.q ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.U;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.V;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.q) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.V;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.W == null || this.y.size() <= 0 || !this.W.p()) ? z2 : true) {
            b.g.k.t.a0(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    long e0(c0 c0Var) {
        return this.v.g() ? c0Var.k() : c0Var.f1639d;
    }

    public int f0(View view) {
        c0 c0VarI0 = i0(view);
        if (c0VarI0 != null) {
            return c0VarI0.j();
        }
        return -1;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewJ0;
        boolean z2;
        View viewQ0 = this.w.Q0(view, i2);
        if (viewQ0 != null) {
            return viewQ0;
        }
        boolean z3 = (this.v == null || this.w == null || x0() || this.H) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.w.l()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (g) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.w.k()) {
                int i4 = (this.w.Z() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (g) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                v();
                if (S(view) == null) {
                    return null;
                }
                t1();
                this.w.J0(view, i2, this.l, this.o0);
                v1(false);
            }
            viewJ0 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                v();
                if (S(view) == null) {
                    return null;
                }
                t1();
                viewJ0 = this.w.J0(view, i2, this.l, this.o0);
                v1(false);
            } else {
                viewJ0 = viewFindNextFocus;
            }
        }
        if (viewJ0 == null || viewJ0.hasFocusable()) {
            return y0(view, viewJ0, i2) ? viewJ0 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        d1(viewJ0, null);
        return view;
    }

    public int g0(View view) {
        c0 c0VarI0 = i0(view);
        if (c0VarI0 != null) {
            return c0VarI0.m();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.D();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.E(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public g getAdapter() {
        return this.v;
    }

    @Override // android.view.View
    public int getBaseline() {
        o oVar = this.w;
        return oVar != null ? oVar.G() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.w0;
        return jVar == null ? super.getChildDrawingOrder(i2, i3) : jVar.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.q;
    }

    public androidx.recyclerview.widget.l getCompatAccessibilityDelegate() {
        return this.v0;
    }

    public k getEdgeEffectFactory() {
        return this.R;
    }

    public l getItemAnimator() {
        return this.W;
    }

    public int getItemDecorationCount() {
        return this.y.size();
    }

    public o getLayoutManager() {
        return this.w;
    }

    public int getMaxFlingVelocity() {
        return this.h0;
    }

    public int getMinFlingVelocity() {
        return this.g0;
    }

    long getNanoTime() {
        if (f) {
            return System.nanoTime();
        }
        return 0L;
    }

    public r getOnFlingListener() {
        return this.f0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.k0;
    }

    public u getRecycledViewPool() {
        return this.l.i();
    }

    public int getScrollState() {
        return this.X;
    }

    public void h(n nVar) {
        i(nVar, -1);
    }

    public c0 h0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return i0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    void h1() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarI0 = i0(this.o.i(i2));
            if (!c0VarI0.J()) {
                c0VarI0.E();
            }
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(n nVar, int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.g("Cannot add item decoration during a scroll  or layout");
        }
        if (this.y.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.y.add(nVar);
        } else {
            this.y.add(i2, nVar);
        }
        A0();
        requestLayout();
    }

    boolean i1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        v();
        if (this.v != null) {
            int[] iArr = this.B0;
            iArr[0] = 0;
            iArr[1] = 0;
            j1(i2, i3, iArr);
            int[] iArr2 = this.B0;
            int i8 = iArr2[0];
            int i9 = iArr2[1];
            i4 = i9;
            i5 = i8;
            i6 = i2 - i8;
            i7 = i3 - i9;
        } else {
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
        }
        if (!this.y.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.B0;
        iArr3[0] = 0;
        iArr3[1] = 0;
        H(i5, i4, i6, i7, this.z0, 0, iArr3);
        int[] iArr4 = this.B0;
        int i10 = i6 - iArr4[0];
        int i11 = i7 - iArr4[1];
        boolean z2 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i12 = this.c0;
        int[] iArr5 = this.z0;
        this.c0 = i12 - iArr5[0];
        this.d0 -= iArr5[1];
        int[] iArr6 = this.A0;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !b.g.k.i.a(motionEvent, 8194)) {
                T0(motionEvent.getX(), i10, motionEvent.getY(), i11);
            }
            u(i2, i3);
        }
        if (i5 != 0 || i4 != 0) {
            J(i5, i4);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z2 && i5 == 0 && i4 == 0) ? false : true;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.B;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.H;
    }

    @Override // android.view.View, b.g.k.j
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(q qVar) {
        if (this.M == null) {
            this.M = new ArrayList();
        }
        this.M.add(qVar);
    }

    public void j0(View view, Rect rect) {
        k0(view, rect);
    }

    void j1(int i2, int i3, int[] iArr) {
        t1();
        J0();
        b.g.g.a.a("RV Scroll");
        R(this.o0);
        int iW1 = i2 != 0 ? this.w.w1(i2, this.l, this.o0) : 0;
        int iY1 = i3 != 0 ? this.w.y1(i3, this.l, this.o0) : 0;
        b.g.g.a.b();
        c1();
        K0();
        v1(false);
        if (iArr != null) {
            iArr[0] = iW1;
            iArr[1] = iY1;
        }
    }

    public void k(s sVar) {
        this.z.add(sVar);
    }

    public void k1(int i2) {
        if (this.H) {
            return;
        }
        x1();
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.x1(i2);
            awakenScrollBars();
        }
    }

    public void l(t tVar) {
        if (this.q0 == null) {
            this.q0 = new ArrayList();
        }
        this.q0.add(tVar);
    }

    void m(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.W.a(c0Var, cVar, cVar2)) {
            P0();
        }
    }

    boolean m1(c0 c0Var, int i2) {
        if (!x0()) {
            b.g.k.t.u0(c0Var.f1637b, i2);
            return true;
        }
        c0Var.r = i2;
        this.C0.add(c0Var);
        return false;
    }

    Rect n0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1669c) {
            return pVar.f1668b;
        }
        if (this.o0.e() && (pVar.b() || pVar.d())) {
            return pVar.f1668b;
        }
        Rect rect = pVar.f1668b;
        rect.set(0, 0, 0, 0);
        int size = this.y.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.s.set(0, 0, 0, 0);
            this.y.get(i2).e(this.s, view, this, this.o0);
            int i3 = rect.left;
            Rect rect2 = this.s;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        pVar.f1669c = false;
        return rect;
    }

    boolean n1(AccessibilityEvent accessibilityEvent) {
        if (!x0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? b.g.k.c0.b.a(accessibilityEvent) : 0;
        this.J |= iA != 0 ? iA : 0;
        return true;
    }

    void o(c0 c0Var, l.c cVar, l.c cVar2) {
        g(c0Var);
        c0Var.G(false);
        if (this.W.c(c0Var, cVar, cVar2)) {
            P0();
        }
    }

    public void o1(int i2, int i3) {
        p1(i2, i3, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = 0;
        this.B = true;
        this.E = this.E && !isLayoutRequested();
        o oVar = this.w;
        if (oVar != null) {
            oVar.z(this);
        }
        this.u0 = false;
        if (f) {
            ThreadLocal<androidx.recyclerview.widget.e> threadLocal = androidx.recyclerview.widget.e.f1781b;
            androidx.recyclerview.widget.e eVar = threadLocal.get();
            this.m0 = eVar;
            if (eVar == null) {
                this.m0 = new androidx.recyclerview.widget.e();
                Display displayT = b.g.k.t.t(this);
                float f2 = 60.0f;
                if (!isInEditMode() && displayT != null) {
                    float refreshRate = displayT.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                androidx.recyclerview.widget.e eVar2 = this.m0;
                eVar2.f = (long) (1.0E9f / f2);
                threadLocal.set(eVar2);
            }
            this.m0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        androidx.recyclerview.widget.e eVar;
        super.onDetachedFromWindow();
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        x1();
        this.B = false;
        o oVar = this.w;
        if (oVar != null) {
            oVar.A(this, this.l);
        }
        this.C0.clear();
        removeCallbacks(this.D0);
        this.p.j();
        if (!f || (eVar = this.m0) == null) {
            return;
        }
        eVar.j(this);
        this.m0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.y.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.y.get(i2).g(canvas, this, this.o0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x006c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.w
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.H
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.w
            boolean r0 = r0.l()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.w
            boolean r3 = r3.k()
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
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.w
            boolean r3 = r3.l()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.w
            boolean r3 = r3.k()
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
            float r2 = r5.i0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.j0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.i1(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (this.H) {
            return false;
        }
        this.A = null;
        if (U(motionEvent)) {
            r();
            return true;
        }
        o oVar = this.w;
        if (oVar == null) {
            return false;
        }
        boolean zK = oVar.k();
        boolean zL = this.w.l();
        if (this.Z == null) {
            this.Z = VelocityTracker.obtain();
        }
        this.Z.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.I) {
                this.I = false;
            }
            this.Y = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.c0 = x2;
            this.a0 = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.d0 = y2;
            this.b0 = y2;
            if (this.X == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                w1(1);
            }
            int[] iArr = this.A0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zK;
            if (zL) {
                i2 = (zK ? 1 : 0) | 2;
            }
            u1(i2, 0);
        } else if (actionMasked == 1) {
            this.Z.clear();
            w1(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.Y);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.Y + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x3 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.X != 1) {
                int i3 = x3 - this.a0;
                int i4 = y3 - this.b0;
                if (!zK || Math.abs(i3) <= this.e0) {
                    z2 = false;
                } else {
                    this.c0 = x3;
                    z2 = true;
                }
                if (zL && Math.abs(i4) > this.e0) {
                    this.d0 = y3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            r();
        } else if (actionMasked == 5) {
            this.Y = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.c0 = x4;
            this.a0 = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.d0 = y4;
            this.b0 = y4;
        } else if (actionMasked == 6) {
            M0(motionEvent);
        }
        return this.X == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        b.g.g.a.a("RV OnLayout");
        C();
        b.g.g.a.b();
        this.E = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        o oVar = this.w;
        if (oVar == null) {
            x(i2, i3);
            return;
        }
        boolean z2 = false;
        if (oVar.s0()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.w.Z0(this.l, this.o0, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.v == null) {
                return;
            }
            if (this.o0.f1697e == 1) {
                D();
            }
            this.w.A1(i2, i3);
            this.o0.j = true;
            E();
            this.w.D1(i2, i3);
            if (this.w.G1()) {
                this.w.A1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.o0.j = true;
                E();
                this.w.D1(i2, i3);
                return;
            }
            return;
        }
        if (this.C) {
            this.w.Z0(this.l, this.o0, i2, i3);
            return;
        }
        if (this.K) {
            t1();
            J0();
            R0();
            K0();
            z zVar = this.o0;
            if (zVar.l) {
                zVar.h = true;
            } else {
                this.n.j();
                this.o0.h = false;
            }
            this.K = false;
            v1(false);
        } else if (this.o0.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.v;
        if (gVar != null) {
            this.o0.f = gVar.c();
        } else {
            this.o0.f = 0;
        }
        t1();
        this.w.Z0(this.l, this.o0, i2, i3);
        v1(false);
        this.o0.h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (x0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.m = savedState;
        super.onRestoreInstanceState(savedState.a());
        o oVar = this.w;
        if (oVar == null || (parcelable2 = this.m.f1629d) == null) {
            return;
        }
        oVar.c1(parcelable2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.m;
        if (savedState2 != null) {
            savedState.c(savedState2);
        } else {
            o oVar = this.w;
            if (oVar != null) {
                savedState.f1629d = oVar.d1();
            } else {
                savedState.f1629d = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        v0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:154:0x00df A[PHI: r0
  0x00df: PHI (r0v36 int) = (r0v26 int), (r0v40 int) binds: [B:148:0x00c8, B:152:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x00f8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    void p(String str) {
        if (x0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + Q());
        }
        if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + Q()));
        }
    }

    public boolean p0() {
        return !this.E || this.N || this.n.p();
    }

    public void p1(int i2, int i3, Interpolator interpolator) {
        q1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    boolean q(c0 c0Var) {
        l lVar = this.W;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    public void q1(int i2, int i3, Interpolator interpolator, int i4) {
        r1(i2, i3, interpolator, i4, false);
    }

    void r0() {
        this.n = new androidx.recyclerview.widget.a(new f());
    }

    void r1(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.H) {
            return;
        }
        if (!oVar.k()) {
            i2 = 0;
        }
        if (!this.w.l()) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        if (!(i4 == Integer.MIN_VALUE || i4 > 0)) {
            scrollBy(i2, i3);
            return;
        }
        if (z2) {
            int i5 = i2 != 0 ? 1 : 0;
            if (i3 != 0) {
                i5 |= 2;
            }
            u1(i5, 1);
        }
        this.l0.f(i2, i3, i4, interpolator);
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z2) {
        c0 c0VarI0 = i0(view);
        if (c0VarI0 != null) {
            if (c0VarI0.x()) {
                c0VarI0.f();
            } else if (!c0VarI0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + c0VarI0 + Q());
            }
        }
        view.clearAnimation();
        A(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.w.b1(this, this.o0, view, view2) && view2 != null) {
            d1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.w.r1(this, view, rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.z.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.z.get(i2).c(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.F != 0 || this.H) {
            this.G = true;
        } else {
            super.requestLayout();
        }
    }

    public void s1(int i2) {
        if (this.H) {
            return;
        }
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.I1(this, this.o0, i2);
        }
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.H) {
            return;
        }
        boolean zK = oVar.k();
        boolean zL = this.w.l();
        if (zK || zL) {
            if (!zK) {
                i2 = 0;
            }
            if (!zL) {
                i3 = 0;
            }
            i1(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (n1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.l lVar) {
        this.v0 = lVar;
        b.g.k.t.k0(this, lVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        l1(gVar, false, true);
        S0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.w0) {
            return;
        }
        this.w0 = jVar;
        setChildrenDrawingOrderEnabled(jVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.q) {
            v0();
        }
        this.q = z2;
        super.setClipToPadding(z2);
        if (this.E) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        b.g.j.h.c(kVar);
        this.R = kVar;
        v0();
    }

    public void setHasFixedSize(boolean z2) {
        this.C = z2;
    }

    public void setItemAnimator(l lVar) {
        l lVar2 = this.W;
        if (lVar2 != null) {
            lVar2.k();
            this.W.v(null);
        }
        this.W = lVar;
        if (lVar != null) {
            lVar.v(this.t0);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.l.G(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z2) {
        suppressLayout(z2);
    }

    public void setLayoutManager(o oVar) {
        if (oVar == this.w) {
            return;
        }
        x1();
        if (this.w != null) {
            l lVar = this.W;
            if (lVar != null) {
                lVar.k();
            }
            this.w.k1(this.l);
            this.w.l1(this.l);
            this.l.c();
            if (this.B) {
                this.w.A(this, this.l);
            }
            this.w.E1(null);
            this.w = null;
        } else {
            this.l.c();
        }
        this.o.o();
        this.w = oVar;
        if (oVar != null) {
            if (oVar.f1657b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1657b.Q());
            }
            oVar.E1(this);
            if (this.B) {
                this.w.z(this);
            }
        }
        this.l.K();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (Build.VERSION.SDK_INT < 18) {
            if (layoutTransition == null) {
                suppressLayout(false);
                return;
            } else if (layoutTransition.getAnimator(0) == null && layoutTransition.getAnimator(1) == null && layoutTransition.getAnimator(2) == null && layoutTransition.getAnimator(3) == null && layoutTransition.getAnimator(4) == null) {
                suppressLayout(true);
                return;
            }
        }
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        getScrollingChildHelper().m(z2);
    }

    public void setOnFlingListener(r rVar) {
        this.f0 = rVar;
    }

    @Deprecated
    public void setOnScrollListener(t tVar) {
        this.p0 = tVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.k0 = z2;
    }

    public void setRecycledViewPool(u uVar) {
        this.l.E(uVar);
    }

    public void setRecyclerListener(w wVar) {
        this.x = wVar;
    }

    void setScrollState(int i2) {
        if (i2 == this.X) {
            return;
        }
        this.X = i2;
        if (i2 != 2) {
            y1();
        }
        I(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                this.e0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        this.e0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(a0 a0Var) {
        this.l.F(a0Var);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().o(i2);
    }

    @Override // android.view.View, b.g.k.j
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.H) {
            p("Do not suppressLayout in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.H = true;
                this.I = true;
                x1();
                return;
            }
            this.H = false;
            if (this.G && this.w != null && this.v != null) {
                requestLayout();
            }
            this.G = false;
        }
    }

    void t() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarI0 = i0(this.o.i(i2));
            if (!c0VarI0.J()) {
                c0VarI0.c();
            }
        }
        this.l.d();
    }

    void t1() {
        int i2 = this.F + 1;
        this.F = i2;
        if (i2 != 1 || this.H) {
            return;
        }
        this.G = false;
    }

    void u(int i2, int i3) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            zIsFinished = false;
        } else {
            this.S.onRelease();
            zIsFinished = this.S.isFinished();
        }
        EdgeEffect edgeEffect2 = this.U;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.U.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.T.onRelease();
            zIsFinished |= this.T.isFinished();
        }
        EdgeEffect edgeEffect4 = this.V;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.V.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        if (zIsFinished) {
            b.g.k.t.a0(this);
        }
    }

    void u0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(b.l.b.f2415a), resources.getDimensionPixelSize(b.l.b.f2417c), resources.getDimensionPixelOffset(b.l.b.f2416b));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
        }
    }

    public boolean u1(int i2, int i3) {
        return getScrollingChildHelper().p(i2, i3);
    }

    void v() {
        if (!this.E || this.N) {
            b.g.g.a.a("RV FullInvalidate");
            C();
            b.g.g.a.b();
            return;
        }
        if (this.n.p()) {
            if (!this.n.o(4) || this.n.o(11)) {
                if (this.n.p()) {
                    b.g.g.a.a("RV FullInvalidate");
                    C();
                    b.g.g.a.b();
                    return;
                }
                return;
            }
            b.g.g.a.a("RV PartialInvalidate");
            t1();
            J0();
            this.n.s();
            if (!this.G) {
                if (q0()) {
                    C();
                } else {
                    this.n.i();
                }
            }
            v1(true);
            K0();
            b.g.g.a.b();
        }
    }

    void v0() {
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    void v1(boolean z2) {
        if (this.F < 1) {
            this.F = 1;
        }
        if (!z2 && !this.H) {
            this.G = false;
        }
        if (this.F == 1) {
            if (z2 && this.G && !this.H && this.w != null && this.v != null) {
                C();
            }
            if (!this.H) {
                this.G = false;
            }
        }
        this.F--;
    }

    boolean w0() {
        AccessibilityManager accessibilityManager = this.L;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void w1(int i2) {
        getScrollingChildHelper().r(i2);
    }

    void x(int i2, int i3) {
        setMeasuredDimension(o.n(i2, getPaddingLeft() + getPaddingRight(), b.g.k.t.B(this)), o.n(i3, getPaddingTop() + getPaddingBottom(), b.g.k.t.A(this)));
    }

    public boolean x0() {
        return this.P > 0;
    }

    public void x1() {
        setScrollState(0);
        y1();
    }

    void z(View view) {
        c0 c0VarI0 = i0(view);
        H0(view);
        g gVar = this.v;
        if (gVar != null && c0VarI0 != null) {
            gVar.o(c0VarI0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).a(view);
            }
        }
    }

    void z0(int i2) {
        if (this.w == null) {
            return;
        }
        setScrollState(2);
        this.w.x1(i2);
        awakenScrollBars();
    }

    void z1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.o.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.o.i(i6);
            c0 c0VarI0 = i0(viewI);
            if (c0VarI0 != null && !c0VarI0.J() && (i4 = c0VarI0.f1639d) >= i2 && i4 < i5) {
                c0VarI0.b(2);
                c0VarI0.a(obj);
                ((p) viewI.getLayoutParams()).f1669c = true;
            }
        }
        this.l.M(i2, i3);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.l.a.f2414a);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = new x();
        this.l = new v();
        this.p = new androidx.recyclerview.widget.q();
        this.r = new a();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new RectF();
        this.y = new ArrayList<>();
        this.z = new ArrayList<>();
        this.F = 0;
        this.N = false;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.R = new k();
        this.W = new androidx.recyclerview.widget.c();
        this.X = 0;
        this.Y = -1;
        this.i0 = Float.MIN_VALUE;
        this.j0 = Float.MIN_VALUE;
        boolean z2 = true;
        this.k0 = true;
        this.l0 = new b0();
        this.n0 = f ? new e.b() : null;
        this.o0 = new z();
        this.r0 = false;
        this.s0 = false;
        this.t0 = new m();
        this.u0 = false;
        this.x0 = new int[2];
        this.z0 = new int[2];
        this.A0 = new int[2];
        this.B0 = new int[2];
        this.C0 = new ArrayList();
        this.D0 = new b();
        this.E0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.e0 = viewConfiguration.getScaledTouchSlop();
        this.i0 = b.g.k.u.b(viewConfiguration, context);
        this.j0 = b.g.k.u.d(viewConfiguration, context);
        this.g0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.h0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.W.v(this.t0);
        r0();
        t0();
        s0();
        if (b.g.k.t.x(this) == 0) {
            b.g.k.t.u0(this, 1);
        }
        this.L = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.l(this));
        int[] iArr = b.l.c.f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(b.l.c.o);
        if (typedArrayObtainStyledAttributes.getInt(b.l.c.i, -1) == -1) {
            setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
        }
        this.q = typedArrayObtainStyledAttributes.getBoolean(b.l.c.h, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(b.l.c.j, false);
        this.D = z3;
        if (z3) {
            u0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.l.c.m), typedArrayObtainStyledAttributes.getDrawable(b.l.c.n), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.l.c.k), typedArrayObtainStyledAttributes.getDrawable(b.l.c.l));
        }
        typedArrayObtainStyledAttributes.recycle();
        w(context, string, attributeSet, i2, 0);
        if (i3 >= 21) {
            int[] iArr2 = f1625b;
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
            if (i3 >= 29) {
                saveAttributeDataForStyleable(context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i2, 0);
            }
            z2 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
            typedArrayObtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z2);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d */
        Parcelable f1629d;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1629d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        void c(SavedState savedState) {
            this.f1629d = savedState.f1629d;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f1629d, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a */
        c0 f1667a;

        /* JADX INFO: renamed from: b */
        final Rect f1668b;

        /* JADX INFO: renamed from: c */
        boolean f1669c;

        /* JADX INFO: renamed from: d */
        boolean f1670d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1668b = new Rect();
            this.f1669c = true;
            this.f1670d = false;
        }

        public int a() {
            return this.f1667a.m();
        }

        public boolean b() {
            return this.f1667a.y();
        }

        public boolean c() {
            return this.f1667a.v();
        }

        public boolean d() {
            return this.f1667a.t();
        }

        public p(int i, int i2) {
            super(i, i2);
            this.f1668b = new Rect();
            this.f1669c = true;
            this.f1670d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1668b = new Rect();
            this.f1669c = true;
            this.f1670d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1668b = new Rect();
            this.f1669c = true;
            this.f1670d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1668b = new Rect();
            this.f1669c = true;
            this.f1670d = false;
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.F(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }
}
