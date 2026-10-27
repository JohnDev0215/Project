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

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements b.g.k.j {

    /* JADX INFO: renamed from: b */
    private static final int[] f1771b = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: c */
    static final boolean f1772c;

    /* JADX INFO: renamed from: d */
    static final boolean f1773d;

    /* JADX INFO: renamed from: e */
    static final boolean f1774e;

    /* JADX INFO: renamed from: f */
    static final boolean f1775f;

    /* JADX INFO: renamed from: g */
    private static final boolean f1776g;

    /* JADX INFO: renamed from: h */
    private static final boolean f1777h;

    /* JADX INFO: renamed from: i */
    private static final Class<?>[] f1778i;
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
        private int f1782b;

        /* JADX INFO: renamed from: c */
        private int f1783c;

        /* JADX INFO: renamed from: d */
        OverScroller f1784d;

        /* JADX INFO: renamed from: e */
        Interpolator f1785e;

        /* JADX INFO: renamed from: f */
        private boolean f1786f;

        /* JADX INFO: renamed from: g */
        private boolean f1787g;

        b0() {
            Interpolator interpolator = RecyclerView.j;
            this.f1785e = interpolator;
            this.f1786f = false;
            this.f1787g = false;
            this.f1784d = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int a(int i2, int i3, int i4, int i5) {
            int iRound;
            int iAbs = Math.abs(i2);
            int iAbs2 = Math.abs(i3);
            boolean z = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i4 * i4) + (i5 * i5));
            int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i3 * i3));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i6 = width / 2;
            float f2 = width;
            float f3 = i6;
            float fB = f3 + (b(Math.min(1.0f, (iSqrt2 * 1.0f) / f2)) * f3);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fB / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f2) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        private float b(float f2) {
            return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
        }

        private void d() {
            RecyclerView.this.removeCallbacks(this);
            b.g.k.t.b0(RecyclerView.this, this);
        }

        public void c(int i2, int i3) {
            RecyclerView.this.setScrollState(2);
            this.f1783c = 0;
            this.f1782b = 0;
            Interpolator interpolator = this.f1785e;
            Interpolator interpolator2 = RecyclerView.j;
            if (interpolator != interpolator2) {
                this.f1785e = interpolator2;
                this.f1784d = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f1784d.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        void e() {
            if (this.f1786f) {
                this.f1787g = true;
            } else {
                d();
            }
        }

        public void f(int i2, int i3, int i4, Interpolator interpolator) {
            if (i4 == Integer.MIN_VALUE) {
                i4 = a(i2, i3, 0, 0);
            }
            int i5 = i4;
            if (interpolator == null) {
                interpolator = RecyclerView.j;
            }
            if (this.f1785e != interpolator) {
                this.f1785e = interpolator;
                this.f1784d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1783c = 0;
            this.f1782b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1784d.startScroll(0, 0, i2, i3, i5);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1784d.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f1784d.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2;
            int i3;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.w == null) {
                g();
                return;
            }
            this.f1787g = false;
            this.f1786f = true;
            recyclerView.v();
            OverScroller overScroller = this.f1784d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.f1782b;
                int i5 = currY - this.f1783c;
                this.f1782b = currX;
                this.f1783c = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.B0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.G(i4, i5, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.B0;
                    i4 -= iArr2[0];
                    i5 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.u(i4, i5);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.v != null) {
                    int[] iArr3 = recyclerView3.B0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.g1(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.B0;
                    i3 = iArr4[0];
                    i2 = iArr4[1];
                    i4 -= i3;
                    i5 -= i2;
                    y yVar = recyclerView4.w.f1820g;
                    if (yVar != null && !yVar.g() && yVar.h()) {
                        int iB = RecyclerView.this.o0.b();
                        if (iB == 0) {
                            yVar.r();
                        } else if (yVar.f() >= iB) {
                            yVar.p(iB - 1);
                            yVar.j(i3, i2);
                        } else {
                            yVar.j(i3, i2);
                        }
                    }
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (!RecyclerView.this.y.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.B0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.H(i3, i2, i4, i5, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.B0;
                int i6 = i4 - iArr6[0];
                int i7 = i5 - iArr6[1];
                if (i3 != 0 || i2 != 0) {
                    recyclerView6.J(i3, i2);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i6 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i7 != 0));
                y yVar2 = RecyclerView.this.w.f1820g;
                if ((yVar2 != null && yVar2.g()) || !z) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    androidx.recyclerview.widget.e eVar = recyclerView7.m0;
                    if (eVar != null) {
                        eVar.f(recyclerView7, i3, i2);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i8 = i6 < 0 ? -currVelocity : i6 > 0 ? currVelocity : 0;
                        if (i7 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i7 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i8, currVelocity);
                    }
                    if (RecyclerView.f1775f) {
                        RecyclerView.this.n0.b();
                    }
                }
            }
            y yVar3 = RecyclerView.this.w.f1820g;
            if (yVar3 != null && yVar3.g()) {
                yVar3.j(0, 0);
            }
            this.f1786f = false;
            if (this.f1787g) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.t1(1);
            }
        }
    }

    static class c implements Interpolator {
        c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public static abstract class c0 {

        /* JADX INFO: renamed from: a */
        private static final List<Object> f1789a = Collections.emptyList();

        /* JADX INFO: renamed from: b */
        public final View f1790b;

        /* JADX INFO: renamed from: c */
        WeakReference<RecyclerView> f1791c;
        int k;
        RecyclerView s;

        /* JADX INFO: renamed from: d */
        int f1792d = -1;

        /* JADX INFO: renamed from: e */
        int f1793e = -1;

        /* JADX INFO: renamed from: f */
        long f1794f = -1;

        /* JADX INFO: renamed from: g */
        int f1795g = -1;

        /* JADX INFO: renamed from: h */
        int f1796h = -1;

        /* JADX INFO: renamed from: i */
        c0 f1797i = null;
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
            this.f1790b = view;
        }

        private void g() {
            if (this.l == null) {
                ArrayList arrayList = new ArrayList();
                this.l = arrayList;
                this.m = Collections.unmodifiableList(arrayList);
            }
        }

        void A(int i2, boolean z) {
            if (this.f1793e == -1) {
                this.f1793e = this.f1792d;
            }
            if (this.f1796h == -1) {
                this.f1796h = this.f1792d;
            }
            if (z) {
                this.f1796h += i2;
            }
            this.f1792d += i2;
            if (this.f1790b.getLayoutParams() != null) {
                ((p) this.f1790b.getLayoutParams()).f1831c = true;
            }
        }

        void B(RecyclerView recyclerView) {
            int i2 = this.r;
            if (i2 != -1) {
                this.q = i2;
            } else {
                this.q = b.g.k.t.x(this.f1790b);
            }
            recyclerView.j1(this, 4);
        }

        void C(RecyclerView recyclerView) {
            recyclerView.j1(this, this.q);
            this.q = 0;
        }

        void D() {
            this.k = 0;
            this.f1792d = -1;
            this.f1793e = -1;
            this.f1794f = -1L;
            this.f1796h = -1;
            this.n = 0;
            this.f1797i = null;
            this.j = null;
            d();
            this.q = 0;
            this.r = -1;
            RecyclerView.s(this);
        }

        void E() {
            if (this.f1793e == -1) {
                this.f1793e = this.f1792d;
            }
        }

        void F(int i2, int i3) {
            this.k = (i2 & i3) | (this.k & (~i3));
        }

        public final void G(boolean z) {
            int i2 = this.n;
            int i3 = z ? i2 - 1 : i2 + 1;
            this.n = i3;
            if (i3 < 0) {
                this.n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i3 == 1) {
                this.k |= 16;
            } else if (z && i3 == 0) {
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

        void b(int i2) {
            this.k = i2 | this.k;
        }

        void c() {
            this.f1793e = -1;
            this.f1796h = -1;
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
            return (this.k & 16) == 0 && b.g.k.t.M(this.f1790b);
        }

        void i(int i2, int i3, boolean z) {
            b(8);
            A(i3, z);
            this.f1792d = i2;
        }

        public final int j() {
            RecyclerView recyclerView = this.s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.c0(this);
        }

        public final long k() {
            return this.f1794f;
        }

        public final int l() {
            return this.f1795g;
        }

        public final int m() {
            int i2 = this.f1796h;
            return i2 == -1 ? this.f1792d : i2;
        }

        public final int n() {
            return this.f1793e;
        }

        List<Object> o() {
            if ((this.k & 1024) != 0) {
                return f1789a;
            }
            List<Object> list = this.l;
            return (list == null || list.size() == 0) ? f1789a : this.m;
        }

        boolean p(int i2) {
            return (i2 & this.k) != 0;
        }

        boolean q() {
            return (this.k & 512) != 0 || t();
        }

        boolean r() {
            return (this.f1790b.getParent() == null || this.f1790b.getParent() == this.s) ? false : true;
        }

        boolean s() {
            return (this.k & 1) != 0;
        }

        boolean t() {
            return (this.k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1792d + " id=" + this.f1794f + ", oldPos=" + this.f1793e + ", pLpos:" + this.f1796h);
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
            if (this.f1790b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.k & 16) == 0 && !b.g.k.t.M(this.f1790b);
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
            recyclerView.w.m1(c0Var.f1790b, recyclerView.l);
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
                    RecyclerView.this.M0();
                }
            } else if (recyclerView.W.d(c0Var, cVar, cVar2)) {
                RecyclerView.this.M0();
            }
        }
    }

    class e implements b.InterfaceC0027b {
        e() {
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public View a(int i2) {
            return RecyclerView.this.getChildAt(i2);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void b(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.B(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void c(int i2) {
            View childAt = RecyclerView.this.getChildAt(i2);
            if (childAt != null) {
                RecyclerView.this.A(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i2);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void d() {
            int iK = k();
            for (int i2 = 0; i2 < iK; i2++) {
                View viewA = a(i2);
                RecyclerView.this.A(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public c0 e(View view) {
            return RecyclerView.g0(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void f(int i2) {
            c0 c0VarG0;
            View viewA = a(i2);
            if (viewA != null && (c0VarG0 = RecyclerView.g0(viewA)) != null) {
                if (c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i2);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void g(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.C(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void h(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                if (!c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.f();
            }
            RecyclerView.this.attachViewToParent(view, i2, layoutParams);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public void i(View view, int i2) {
            RecyclerView.this.addView(view, i2);
            RecyclerView.this.z(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0027b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    class f implements a.InterfaceC0026a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void a(int i2, int i3) {
            RecyclerView.this.C0(i2, i3);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public c0 c(int i2) {
            c0 c0VarA0 = RecyclerView.this.a0(i2, true);
            if (c0VarA0 == null || RecyclerView.this.o.n(c0VarA0.f1790b)) {
                return null;
            }
            return c0VarA0;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void d(int i2, int i3) {
            RecyclerView.this.D0(i2, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.r0 = true;
            recyclerView.o0.f1867d += i3;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void e(int i2, int i3) {
            RecyclerView.this.D0(i2, i3, false);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void g(int i2, int i3) {
            RecyclerView.this.B0(i2, i3);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0026a
        public void h(int i2, int i3, Object obj) {
            RecyclerView.this.w1(i2, i3, obj);
            RecyclerView.this.s0 = true;
        }

        void i(a.b bVar) {
            int i2 = bVar.f1911a;
            if (i2 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.w.R0(recyclerView, bVar.f1912b, bVar.f1914d);
                return;
            }
            if (i2 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.w.U0(recyclerView2, bVar.f1912b, bVar.f1914d);
            } else if (i2 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.w.W0(recyclerView3, bVar.f1912b, bVar.f1914d, bVar.f1913c);
            } else {
                if (i2 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.w.T0(recyclerView4, bVar.f1912b, bVar.f1914d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a */
        private final h f1801a = new h();

        /* JADX INFO: renamed from: b */
        private boolean f1802b = false;

        public final void a(VH vh, int i2) {
            vh.f1792d = i2;
            if (g()) {
                vh.f1794f = d(i2);
            }
            vh.F(1, 519);
            b.g.g.b.a("RV OnBindView");
            k(vh, i2, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f1790b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1831c = true;
            }
            b.g.g.b.b();
        }

        public final VH b(ViewGroup viewGroup, int i2) {
            try {
                b.g.g.b.a("RV CreateView");
                VH vh = (VH) l(viewGroup, i2);
                if (vh.f1790b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f1795g = i2;
                return vh;
            } finally {
                b.g.g.b.b();
            }
        }

        public abstract int c();

        public long d(int i2) {
            return -1L;
        }

        public int e(int i2) {
            return 0;
        }

        public final boolean f() {
            return this.f1801a.a();
        }

        public final boolean g() {
            return this.f1802b;
        }

        public final void h() {
            this.f1801a.b();
        }

        public void i(RecyclerView recyclerView) {
        }

        public abstract void j(VH vh, int i2);

        public void k(VH vh, int i2, List<Object> list) {
            j(vh, i2);
        }

        public abstract VH l(ViewGroup viewGroup, int i2);

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
            this.f1801a.registerObserver(iVar);
        }

        public void s(boolean z) {
            if (f()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1802b = z;
        }

        public void t(i iVar) {
            this.f1801a.unregisterObserver(iVar);
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
        int a(int i2, int i3);
    }

    public static class k {
        protected EdgeEffect a(RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a */
        private b f1803a = null;

        /* JADX INFO: renamed from: b */
        private ArrayList<a> f1804b = new ArrayList<>();

        /* JADX INFO: renamed from: c */
        private long f1805c = 120;

        /* JADX INFO: renamed from: d */
        private long f1806d = 120;

        /* JADX INFO: renamed from: e */
        private long f1807e = 250;

        /* JADX INFO: renamed from: f */
        private long f1808f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a */
            public int f1809a;

            /* JADX INFO: renamed from: b */
            public int f1810b;

            /* JADX INFO: renamed from: c */
            public int f1811c;

            /* JADX INFO: renamed from: d */
            public int f1812d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i2) {
                View view = c0Var.f1790b;
                this.f1809a = view.getLeft();
                this.f1810b = view.getTop();
                this.f1811c = view.getRight();
                this.f1812d = view.getBottom();
                return this;
            }
        }

        static int e(c0 c0Var) {
            int i2 = c0Var.k & 14;
            if (c0Var.t()) {
                return 4;
            }
            if ((i2 & 4) != 0) {
                return i2;
            }
            int iN = c0Var.n();
            int iJ = c0Var.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i2 : i2 | 2048;
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
            b bVar = this.f1803a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f1804b.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1804b.get(i2).a();
            }
            this.f1804b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f1805c;
        }

        public long m() {
            return this.f1808f;
        }

        public long n() {
            return this.f1807e;
        }

        public long o() {
            return this.f1806d;
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

        public c t(z zVar, c0 c0Var, int i2, List<Object> list) {
            return q().a(c0Var);
        }

        public abstract void u();

        void v(b bVar) {
            this.f1803a = bVar;
        }
    }

    private class m implements l.b {
        m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.f1797i != null && c0Var.j == null) {
                c0Var.f1797i = null;
            }
            c0Var.j = null;
            if (c0Var.I() || RecyclerView.this.V0(c0Var.f1790b) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f1790b, false);
        }
    }

    public static abstract class n {
        @Deprecated
        public void d(Rect rect, int i2, RecyclerView recyclerView) {
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
        androidx.recyclerview.widget.b f1814a;

        /* JADX INFO: renamed from: b */
        RecyclerView f1815b;

        /* JADX INFO: renamed from: c */
        private final p.b f1816c;

        /* JADX INFO: renamed from: d */
        private final p.b f1817d;

        /* JADX INFO: renamed from: e */
        androidx.recyclerview.widget.p f1818e;

        /* JADX INFO: renamed from: f */
        androidx.recyclerview.widget.p f1819f;

        /* JADX INFO: renamed from: g */
        y f1820g;

        /* JADX INFO: renamed from: h */
        boolean f1821h;

        /* JADX INFO: renamed from: i */
        boolean f1822i;
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
            public View a(int i2) {
                return o.this.I(i2);
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
            public View a(int i2) {
                return o.this.I(i2);
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
            void a(int i2, int i3);
        }

        public static class d {

            /* JADX INFO: renamed from: a */
            public int f1825a;

            /* JADX INFO: renamed from: b */
            public int f1826b;

            /* JADX INFO: renamed from: c */
            public boolean f1827c;

            /* JADX INFO: renamed from: d */
            public boolean f1828d;
        }

        public o() {
            a aVar = new a();
            this.f1816c = aVar;
            b bVar = new b();
            this.f1817d = bVar;
            this.f1818e = new androidx.recyclerview.widget.p(aVar);
            this.f1819f = new androidx.recyclerview.widget.p(bVar);
            this.f1821h = false;
            this.f1822i = false;
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
            int i2 = left - iE0;
            int iMin = Math.min(0, i2);
            int i3 = top - iG0;
            int iMin2 = Math.min(0, i3);
            int i4 = iWidth - iO0;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, iHeight - iW);
            if (Z() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i2, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i4);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i3, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        private void f(View view, int i2, boolean z) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (z || c0VarG0.v()) {
                this.f1815b.p.b(c0VarG0);
            } else {
                this.f1815b.p.p(c0VarG0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarG0.L() || c0VarG0.w()) {
                if (c0VarG0.w()) {
                    c0VarG0.K();
                } else {
                    c0VarG0.e();
                }
                this.f1814a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1815b) {
                int iM = this.f1814a.m(view);
                if (i2 == -1) {
                    i2 = this.f1814a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1815b.indexOfChild(view) + this.f1815b.Q());
                }
                if (iM != i2) {
                    this.f1815b.w.B0(iM, i2);
                }
            } else {
                this.f1814a.a(view, i2, false);
                pVar.f1831c = true;
                y yVar = this.f1820g;
                if (yVar != null && yVar.h()) {
                    this.f1820g.k(view);
                }
            }
            if (pVar.f1832d) {
                c0VarG0.f1790b.invalidate();
                pVar.f1832d = false;
            }
        }

        public static d i0(Context context, AttributeSet attributeSet, int i2, int i3) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.n.c.RecyclerView, i2, i3);
            dVar.f1825a = typedArrayObtainStyledAttributes.getInt(b.n.c.RecyclerView_android_orientation, 1);
            dVar.f1826b = typedArrayObtainStyledAttributes.getInt(b.n.c.RecyclerView_spanCount, 1);
            dVar.f1827c = typedArrayObtainStyledAttributes.getBoolean(b.n.c.RecyclerView_reverseLayout, false);
            dVar.f1828d = typedArrayObtainStyledAttributes.getBoolean(b.n.c.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int n(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        private boolean t0(RecyclerView recyclerView, int i2, int i3) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            Rect rect = this.f1815b.s;
            P(focusedChild, rect);
            return rect.left - i2 < iO0 && rect.right - i2 > iE0 && rect.top - i3 < iW && rect.bottom - i3 > iG0;
        }

        private void v1(v vVar, int i2, View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.J()) {
                return;
            }
            if (c0VarG0.t() && !c0VarG0.v() && !this.f1815b.v.g()) {
                q1(i2);
                vVar.C(c0VarG0);
            } else {
                x(i2);
                vVar.D(view);
                this.f1815b.p.k(c0VarG0);
            }
        }

        private static boolean w0(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            if (i4 > 0 && i2 != i4) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i2;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i2;
            }
            return true;
        }

        private void y(int i2, View view) {
            this.f1814a.d(i2);
        }

        void A(RecyclerView recyclerView, v vVar) {
            this.f1822i = false;
            I0(recyclerView, vVar);
        }

        public void A0(View view, int i2, int i3) {
            p pVar = (p) view.getLayoutParams();
            Rect rectK0 = this.f1815b.k0(view);
            int i4 = i2 + rectK0.left + rectK0.right;
            int i5 = i3 + rectK0.top + rectK0.bottom;
            int iK = K(o0(), p0(), e0() + f0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).width, k());
            int iK2 = K(W(), X(), g0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) pVar).height, l());
            if (F1(view, iK, iK2, pVar)) {
                view.measure(iK, iK2);
            }
        }

        void A1(int i2, int i3) {
            this.q = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.o = mode;
            if (mode == 0 && !RecyclerView.f1773d) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.f1773d) {
                return;
            }
            this.r = 0;
        }

        public View B(View view) {
            View viewS;
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null || (viewS = recyclerView.S(view)) == null || this.f1814a.n(viewS)) {
                return null;
            }
            return viewS;
        }

        public void B0(int i2, int i3) {
            View viewI = I(i2);
            if (viewI != null) {
                x(i2);
                h(viewI, i3);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.f1815b.toString());
            }
        }

        public void B1(int i2, int i3) {
            this.f1815b.setMeasuredDimension(i2, i3);
        }

        public View C(int i2) {
            int iJ = J();
            for (int i3 = 0; i3 < iJ; i3++) {
                View viewI = I(i3);
                c0 c0VarG0 = RecyclerView.g0(viewI);
                if (c0VarG0 != null && c0VarG0.m() == i2 && !c0VarG0.J() && (this.f1815b.o0.e() || !c0VarG0.v())) {
                    return viewI;
                }
            }
            return null;
        }

        public void C0(int i2) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                recyclerView.z0(i2);
            }
        }

        public void C1(Rect rect, int i2, int i3) {
            B1(n(i2, rect.width() + e0() + f0(), c0()), n(i3, rect.height() + g0() + d0(), b0()));
        }

        public abstract p D();

        public void D0(int i2) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                recyclerView.A0(i2);
            }
        }

        void D1(int i2, int i3) {
            int iJ = J();
            if (iJ == 0) {
                this.f1815b.x(i2, i3);
                return;
            }
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = 0; i8 < iJ; i8++) {
                View viewI = I(i8);
                Rect rect = this.f1815b.s;
                P(viewI, rect);
                int i9 = rect.left;
                if (i9 < i5) {
                    i5 = i9;
                }
                int i10 = rect.right;
                if (i10 > i4) {
                    i4 = i10;
                }
                int i11 = rect.top;
                if (i11 < i6) {
                    i6 = i11;
                }
                int i12 = rect.bottom;
                if (i12 > i7) {
                    i7 = i12;
                }
            }
            this.f1815b.s.set(i5, i6, i4, i7);
            C1(this.f1815b.s, i2, i3);
        }

        public p E(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void E0(g gVar, g gVar2) {
        }

        void E1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1815b = null;
                this.f1814a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f1815b = recyclerView;
                this.f1814a = recyclerView.o;
                this.q = recyclerView.getWidth();
                this.r = recyclerView.getHeight();
            }
            this.o = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.p = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public p F(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        public boolean F0(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i3) {
            return false;
        }

        boolean F1(View view, int i2, int i3, p pVar) {
            return (!view.isLayoutRequested() && this.k && w0(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
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
            return ((p) view.getLayoutParams()).f1830b.bottom;
        }

        @Deprecated
        public void H0(RecyclerView recyclerView) {
        }

        boolean H1(View view, int i2, int i3, p pVar) {
            return (this.k && w0(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public View I(int i2) {
            androidx.recyclerview.widget.b bVar = this.f1814a;
            if (bVar != null) {
                return bVar.f(i2);
            }
            return null;
        }

        public void I0(RecyclerView recyclerView, v vVar) {
            H0(recyclerView);
        }

        public void I1(RecyclerView recyclerView, z zVar, int i2) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public int J() {
            androidx.recyclerview.widget.b bVar = this.f1814a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public View J0(View view, int i2, v vVar, z zVar) {
            return null;
        }

        public void J1(y yVar) {
            y yVar2 = this.f1820g;
            if (yVar2 != null && yVar != yVar2 && yVar2.h()) {
                this.f1820g.r();
            }
            this.f1820g = yVar;
            yVar.q(this.f1815b, this);
        }

        public void K0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1815b;
            L0(recyclerView.l, recyclerView.o0, accessibilityEvent);
        }

        void K1() {
            y yVar = this.f1820g;
            if (yVar != null) {
                yVar.r();
            }
        }

        public void L0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1815b.canScrollVertically(-1) && !this.f1815b.canScrollHorizontally(-1) && !this.f1815b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f1815b.v;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public boolean L1() {
            return false;
        }

        public boolean M() {
            RecyclerView recyclerView = this.f1815b;
            return recyclerView != null && recyclerView.q;
        }

        void M0(b.g.k.c0.c cVar) {
            RecyclerView recyclerView = this.f1815b;
            N0(recyclerView.l, recyclerView.o0, cVar);
        }

        public int N(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null || recyclerView.v == null || !k()) {
                return 1;
            }
            return this.f1815b.v.c();
        }

        public void N0(v vVar, z zVar, b.g.k.c0.c cVar) {
            if (this.f1815b.canScrollVertically(-1) || this.f1815b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.u0(true);
            }
            if (this.f1815b.canScrollVertically(1) || this.f1815b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.u0(true);
            }
            cVar.d0(c.b.b(k0(vVar, zVar), N(vVar, zVar), v0(vVar, zVar), l0(vVar, zVar)));
        }

        public int O(View view) {
            return view.getBottom() + H(view);
        }

        void O0(View view, b.g.k.c0.c cVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 == null || c0VarG0.v() || this.f1814a.n(c0VarG0.f1790b)) {
                return;
            }
            RecyclerView recyclerView = this.f1815b;
            P0(recyclerView.l, recyclerView.o0, view, cVar);
        }

        public void P(View view, Rect rect) {
            RecyclerView.h0(view, rect);
        }

        public void P0(v vVar, z zVar, View view, b.g.k.c0.c cVar) {
            cVar.e0(c.C0047c.a(l() ? h0(view) : 0, 1, k() ? h0(view) : 0, 1, false, false));
        }

        public int Q(View view) {
            return view.getLeft() - a0(view);
        }

        public View Q0(View view, int i2) {
            return null;
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1830b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void R0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int S(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1830b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void S0(RecyclerView recyclerView) {
        }

        public int T(View view) {
            return view.getRight() + j0(view);
        }

        public void T0(RecyclerView recyclerView, int i2, int i3, int i4) {
        }

        public int U(View view) {
            return view.getTop() - m0(view);
        }

        public void U0(RecyclerView recyclerView, int i2, int i3) {
        }

        public View V() {
            View focusedChild;
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1814a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void V0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int W() {
            return this.r;
        }

        public void W0(RecyclerView recyclerView, int i2, int i3, Object obj) {
            V0(recyclerView, i2, i3);
        }

        public int X() {
            return this.p;
        }

        public void X0(v vVar, z zVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int Y() {
            RecyclerView recyclerView = this.f1815b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        public void Y0(z zVar) {
        }

        public int Z() {
            return b.g.k.t.z(this.f1815b);
        }

        public void Z0(v vVar, z zVar, int i2, int i3) {
            this.f1815b.x(i2, i3);
        }

        public int a0(View view) {
            return ((p) view.getLayoutParams()).f1830b.left;
        }

        @Deprecated
        public boolean a1(RecyclerView recyclerView, View view, View view2) {
            return x0() || recyclerView.u0();
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            return b.g.k.t.A(this.f1815b);
        }

        public boolean b1(RecyclerView recyclerView, z zVar, View view, View view2) {
            return a1(recyclerView, view, view2);
        }

        public void c(View view, int i2) {
            f(view, i2, true);
        }

        public int c0() {
            return b.g.k.t.B(this.f1815b);
        }

        public void c1(Parcelable parcelable) {
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public Parcelable d1() {
            return null;
        }

        public void e(View view, int i2) {
            f(view, i2, false);
        }

        public int e0() {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e1(int i2) {
        }

        public int f0() {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        void f1(y yVar) {
            if (this.f1820g == yVar) {
                this.f1820g = null;
            }
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0() {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        boolean g1(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1815b;
            return h1(recyclerView.l, recyclerView.o0, i2, bundle);
        }

        public void h(View view, int i2) {
            i(view, i2, (p) view.getLayoutParams());
        }

        public int h0(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public boolean h1(v vVar, z zVar, int i2, Bundle bundle) {
            int iW;
            int iO0;
            int i3;
            int i4;
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 == 4096) {
                iW = recyclerView.canScrollVertically(1) ? (W() - g0()) - d0() : 0;
                if (this.f1815b.canScrollHorizontally(1)) {
                    iO0 = (o0() - e0()) - f0();
                    i3 = iW;
                    i4 = iO0;
                }
                i3 = iW;
                i4 = 0;
            } else if (i2 != 8192) {
                i4 = 0;
                i3 = 0;
            } else {
                iW = recyclerView.canScrollVertically(-1) ? -((W() - g0()) - d0()) : 0;
                if (this.f1815b.canScrollHorizontally(-1)) {
                    iO0 = -((o0() - e0()) - f0());
                    i3 = iW;
                    i4 = iO0;
                }
                i3 = iW;
                i4 = 0;
            }
            if (i3 == 0 && i4 == 0) {
                return false;
            }
            this.f1815b.o1(i4, i3, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void i(View view, int i2, p pVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.v()) {
                this.f1815b.p.b(c0VarG0);
            } else {
                this.f1815b.p.p(c0VarG0);
            }
            this.f1814a.c(view, i2, pVar, c0VarG0.v());
        }

        boolean i1(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1815b;
            return j1(recyclerView.l, recyclerView.o0, view, i2, bundle);
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.k0(view));
            }
        }

        public int j0(View view) {
            return ((p) view.getLayoutParams()).f1830b.right;
        }

        public boolean j1(v vVar, z zVar, View view, int i2, Bundle bundle) {
            return false;
        }

        public boolean k() {
            return false;
        }

        public int k0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView == null || recyclerView.v == null || !l()) {
                return 1;
            }
            return this.f1815b.v.c();
        }

        public void k1(v vVar) {
            for (int iJ = J() - 1; iJ >= 0; iJ--) {
                if (!RecyclerView.g0(I(iJ)).J()) {
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
            for (int i2 = iJ - 1; i2 >= 0; i2--) {
                View viewN = vVar.n(i2);
                c0 c0VarG0 = RecyclerView.g0(viewN);
                if (!c0VarG0.J()) {
                    c0VarG0.G(false);
                    if (c0VarG0.x()) {
                        this.f1815b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f1815b.W;
                    if (lVar != null) {
                        lVar.j(c0VarG0);
                    }
                    c0VarG0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f1815b.invalidate();
            }
        }

        public boolean m(p pVar) {
            return pVar != null;
        }

        public int m0(View view) {
            return ((p) view.getLayoutParams()).f1830b.top;
        }

        public void m1(View view, v vVar) {
            p1(view);
            vVar.B(view);
        }

        public void n0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f1830b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1815b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1815b.u;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void n1(int i2, v vVar) {
            View viewI = I(i2);
            q1(i2);
            vVar.B(viewI);
        }

        public void o(int i2, int i3, z zVar, c cVar) {
        }

        public int o0() {
            return this.q;
        }

        public boolean o1(Runnable runnable) {
            RecyclerView recyclerView = this.f1815b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void p(int i2, c cVar) {
        }

        public int p0() {
            return this.o;
        }

        public void p1(View view) {
            this.f1814a.p(view);
        }

        public int q(z zVar) {
            return 0;
        }

        boolean q0() {
            int iJ = J();
            for (int i2 = 0; i2 < iJ; i2++) {
                ViewGroup.LayoutParams layoutParams = I(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void q1(int i2) {
            if (I(i2) != null) {
                this.f1814a.q(i2);
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public boolean r0() {
            return this.f1822i;
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
            int i2 = iArrL[0];
            int i3 = iArrL[1];
            if ((z2 && !t0(recyclerView, i2, i3)) || (i2 == 0 && i3 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i2, i3);
            } else {
                recyclerView.l1(i2, i3);
            }
            return true;
        }

        public int t(z zVar) {
            return 0;
        }

        public void t1() {
            RecyclerView recyclerView = this.f1815b;
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
            this.f1821h = true;
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

        public int w1(int i2, v vVar, z zVar) {
            return 0;
        }

        public void x(int i2) {
            y(i2, I(i2));
        }

        public boolean x0() {
            y yVar = this.f1820g;
            return yVar != null && yVar.h();
        }

        public void x1(int i2) {
        }

        public boolean y0(View view, boolean z, boolean z2) {
            boolean z3 = this.f1818e.b(view, 24579) && this.f1819f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public int y1(int i2, v vVar, z zVar) {
            return 0;
        }

        void z(RecyclerView recyclerView) {
            this.f1822i = true;
            G0(recyclerView);
        }

        public void z0(View view, int i2, int i3, int i4, int i5) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1830b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
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
        public abstract boolean a(int i2, int i3);
    }

    public interface s {
        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(RecyclerView recyclerView, MotionEvent motionEvent);

        void c(boolean z);
    }

    public static abstract class t {
        public void a(RecyclerView recyclerView, int i2) {
        }

        public void b(RecyclerView recyclerView, int i2, int i3) {
        }
    }

    public static class u {

        /* JADX INFO: renamed from: a */
        SparseArray<a> f1833a = new SparseArray<>();

        /* JADX INFO: renamed from: b */
        private int f1834b = 0;

        static class a {

            /* JADX INFO: renamed from: a */
            final ArrayList<c0> f1835a = new ArrayList<>();

            /* JADX INFO: renamed from: b */
            int f1836b = 5;

            /* JADX INFO: renamed from: c */
            long f1837c = 0;

            /* JADX INFO: renamed from: d */
            long f1838d = 0;

            a() {
            }
        }

        private a g(int i2) {
            a aVar = this.f1833a.get(i2);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1833a.put(i2, aVar2);
            return aVar2;
        }

        void a() {
            this.f1834b++;
        }

        public void b() {
            for (int i2 = 0; i2 < this.f1833a.size(); i2++) {
                this.f1833a.valueAt(i2).f1835a.clear();
            }
        }

        void c() {
            this.f1834b--;
        }

        void d(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f1838d = j(aVarG.f1838d, j);
        }

        void e(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f1837c = j(aVarG.f1837c, j);
        }

        public c0 f(int i2) {
            a aVar = this.f1833a.get(i2);
            if (aVar == null || aVar.f1835a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f1835a;
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
            if (!z && this.f1834b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f1835a;
            if (this.f1833a.get(iL).f1836b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        boolean k(int i2, long j, long j2) {
            long j3 = g(i2).f1838d;
            return j3 == 0 || j + j3 < j2;
        }

        boolean l(int i2, long j, long j2) {
            long j3 = g(i2).f1837c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a */
        final ArrayList<c0> f1839a;

        /* JADX INFO: renamed from: b */
        ArrayList<c0> f1840b;

        /* JADX INFO: renamed from: c */
        final ArrayList<c0> f1841c;

        /* JADX INFO: renamed from: d */
        private final List<c0> f1842d;

        /* JADX INFO: renamed from: e */
        private int f1843e;

        /* JADX INFO: renamed from: f */
        int f1844f;

        /* JADX INFO: renamed from: g */
        u f1845g;

        /* JADX INFO: renamed from: h */
        private a0 f1846h;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f1839a = arrayList;
            this.f1840b = null;
            this.f1841c = new ArrayList<>();
            this.f1842d = Collections.unmodifiableList(arrayList);
            this.f1843e = 2;
            this.f1844f = 2;
        }

        private boolean H(c0 c0Var, int i2, int i3, long j) {
            c0Var.s = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.f1845g.k(iL, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.v.a(c0Var, i2);
            this.f1845g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.o0.e()) {
                return true;
            }
            c0Var.f1796h = i3;
            return true;
        }

        private void b(c0 c0Var) {
            if (RecyclerView.this.t0()) {
                View view = c0Var.f1790b;
                if (b.g.k.t.x(view) == 0) {
                    b.g.k.t.t0(view, 1);
                }
                androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
                if (lVar == null) {
                    return;
                }
                b.g.k.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                b.g.k.t.j0(view, aVarN);
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
            View view = c0Var.f1790b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i2) {
            a(this.f1841c.get(i2), true);
            this.f1841c.remove(i2);
        }

        public void B(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (c0VarG0.w()) {
                c0VarG0.K();
            } else if (c0VarG0.L()) {
                c0VarG0.e();
            }
            C(c0VarG0);
            if (RecyclerView.this.W == null || c0VarG0.u()) {
                return;
            }
            RecyclerView.this.W.j(c0VarG0);
        }

        void C(c0 c0Var) {
            boolean z;
            boolean z2 = true;
            if (c0Var.w() || c0Var.f1790b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f1790b.getParent() != null);
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
                if (this.f1844f <= 0 || c0Var.p(526)) {
                    z = false;
                } else {
                    int size = this.f1841c.size();
                    if (size >= this.f1844f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.f1775f && size > 0 && !RecyclerView.this.n0.d(c0Var.f1792d)) {
                        int i2 = size - 1;
                        while (i2 >= 0) {
                            if (!RecyclerView.this.n0.d(this.f1841c.get(i2).f1792d)) {
                                break;
                            } else {
                                i2--;
                            }
                        }
                        size = i2 + 1;
                    }
                    this.f1841c.add(size, c0Var);
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
            c0 c0VarG0 = RecyclerView.g0(view);
            if (!c0VarG0.p(12) && c0VarG0.y() && !RecyclerView.this.q(c0VarG0)) {
                if (this.f1840b == null) {
                    this.f1840b = new ArrayList<>();
                }
                c0VarG0.H(this, true);
                this.f1840b.add(c0VarG0);
                return;
            }
            if (!c0VarG0.t() || c0VarG0.v() || RecyclerView.this.v.g()) {
                c0VarG0.H(this, false);
                this.f1839a.add(c0VarG0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        void E(u uVar) {
            u uVar2 = this.f1845g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.f1845g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f1845g.a();
        }

        void F(a0 a0Var) {
        }

        public void G(int i2) {
            this.f1843e = i2;
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
                this.f1840b.remove(c0Var);
            } else {
                this.f1839a.remove(c0Var);
            }
            c0Var.o = null;
            c0Var.p = false;
            c0Var.e();
        }

        void K() {
            o oVar = RecyclerView.this.w;
            this.f1844f = this.f1843e + (oVar != null ? oVar.m : 0);
            for (int size = this.f1841c.size() - 1; size >= 0 && this.f1841c.size() > this.f1844f; size--) {
                A(size);
            }
        }

        boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.o0.e();
            }
            int i2 = c0Var.f1792d;
            if (i2 >= 0 && i2 < RecyclerView.this.v.c()) {
                if (RecyclerView.this.o0.e() || RecyclerView.this.v.e(c0Var.f1792d) == c0Var.l()) {
                    return !RecyclerView.this.v.g() || c0Var.k() == RecyclerView.this.v.d(c0Var.f1792d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.Q());
        }

        void M(int i2, int i3) {
            int i4;
            int i5 = i3 + i2;
            for (int size = this.f1841c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1841c.get(size);
                if (c0Var != null && (i4 = c0Var.f1792d) >= i2 && i4 < i5) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        void a(c0 c0Var, boolean z) {
            RecyclerView.s(c0Var);
            View view = c0Var.f1790b;
            androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
            if (lVar != null) {
                b.g.k.a aVarN = lVar.n();
                b.g.k.t.j0(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z) {
                g(c0Var);
            }
            c0Var.s = null;
            i().i(c0Var);
        }

        public void c() {
            this.f1839a.clear();
            z();
        }

        void d() {
            int size = this.f1841c.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1841c.get(i2).c();
            }
            int size2 = this.f1839a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                this.f1839a.get(i3).c();
            }
            ArrayList<c0> arrayList = this.f1840b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    this.f1840b.get(i4).c();
                }
            }
        }

        void e() {
            this.f1839a.clear();
            ArrayList<c0> arrayList = this.f1840b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.o0.b()) {
                return !RecyclerView.this.o0.e() ? i2 : RecyclerView.this.n.m(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State item count is " + RecyclerView.this.o0.b() + RecyclerView.this.Q());
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

        c0 h(int i2) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f1840b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    c0 c0Var = this.f1840b.get(i3);
                    if (!c0Var.L() && c0Var.m() == i2) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.v.g() && (iM = RecyclerView.this.n.m(i2)) > 0 && iM < RecyclerView.this.v.c()) {
                    long jD = RecyclerView.this.v.d(iM);
                    for (int i4 = 0; i4 < size; i4++) {
                        c0 c0Var2 = this.f1840b.get(i4);
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
            if (this.f1845g == null) {
                this.f1845g = new u();
            }
            return this.f1845g;
        }

        int j() {
            return this.f1839a.size();
        }

        public List<c0> k() {
            return this.f1842d;
        }

        c0 l(long j, int i2, boolean z) {
            for (int size = this.f1839a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1839a.get(size);
                if (c0Var.k() == j && !c0Var.L()) {
                    if (i2 == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.o0.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z) {
                        this.f1839a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f1790b, false);
                        y(c0Var.f1790b);
                    }
                }
            }
            int size2 = this.f1841c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f1841c.get(size2);
                if (c0Var2.k() == j && !c0Var2.r()) {
                    if (i2 == c0Var2.l()) {
                        if (!z) {
                            this.f1841c.remove(size2);
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

        c0 m(int i2, boolean z) {
            View viewE;
            int size = this.f1839a.size();
            for (int i3 = 0; i3 < size; i3++) {
                c0 c0Var = this.f1839a.get(i3);
                if (!c0Var.L() && c0Var.m() == i2 && !c0Var.t() && (RecyclerView.this.o0.f1871h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z || (viewE = RecyclerView.this.o.e(i2)) == null) {
                int size2 = this.f1841c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    c0 c0Var2 = this.f1841c.get(i4);
                    if (!c0Var2.t() && c0Var2.m() == i2 && !c0Var2.r()) {
                        if (!z) {
                            this.f1841c.remove(i4);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarG0 = RecyclerView.g0(viewE);
            RecyclerView.this.o.s(viewE);
            int iM = RecyclerView.this.o.m(viewE);
            if (iM != -1) {
                RecyclerView.this.o.d(iM);
                D(viewE);
                c0VarG0.b(8224);
                return c0VarG0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarG0 + RecyclerView.this.Q());
        }

        View n(int i2) {
            return this.f1839a.get(i2).f1790b;
        }

        public View o(int i2) {
            return p(i2, false);
        }

        View p(int i2, boolean z) {
            return I(i2, z, Long.MAX_VALUE).f1790b;
        }

        void s() {
            int size = this.f1841c.size();
            for (int i2 = 0; i2 < size; i2++) {
                p pVar = (p) this.f1841c.get(i2).f1790b.getLayoutParams();
                if (pVar != null) {
                    pVar.f1831c = true;
                }
            }
        }

        void t() {
            int size = this.f1841c.size();
            for (int i2 = 0; i2 < size; i2++) {
                c0 c0Var = this.f1841c.get(i2);
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

        void u(int i2, int i3) {
            int size = this.f1841c.size();
            for (int i4 = 0; i4 < size; i4++) {
                c0 c0Var = this.f1841c.get(i4);
                if (c0Var != null && c0Var.f1792d >= i2) {
                    c0Var.A(i3, true);
                }
            }
        }

        void v(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            if (i2 < i3) {
                i4 = -1;
                i6 = i2;
                i5 = i3;
            } else {
                i4 = 1;
                i5 = i2;
                i6 = i3;
            }
            int size = this.f1841c.size();
            for (int i8 = 0; i8 < size; i8++) {
                c0 c0Var = this.f1841c.get(i8);
                if (c0Var != null && (i7 = c0Var.f1792d) >= i6 && i7 <= i5) {
                    if (i7 == i2) {
                        c0Var.A(i3 - i2, false);
                    } else {
                        c0Var.A(i4, false);
                    }
                }
            }
        }

        void w(int i2, int i3, boolean z) {
            int i4 = i2 + i3;
            for (int size = this.f1841c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1841c.get(size);
                if (c0Var != null) {
                    int i5 = c0Var.f1792d;
                    if (i5 >= i4) {
                        c0Var.A(-i3, z);
                    } else if (i5 >= i2) {
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
            c0 c0VarG0 = RecyclerView.g0(view);
            c0VarG0.o = null;
            c0VarG0.p = false;
            c0VarG0.e();
            C(c0VarG0);
        }

        void z() {
            for (int size = this.f1841c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1841c.clear();
            if (RecyclerView.f1775f) {
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
            recyclerView.o0.f1870g = true;
            recyclerView.P0(true);
            if (RecyclerView.this.n.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    public static abstract class y {

        /* JADX INFO: renamed from: b */
        private RecyclerView f1850b;

        /* JADX INFO: renamed from: c */
        private o f1851c;

        /* JADX INFO: renamed from: d */
        private boolean f1852d;

        /* JADX INFO: renamed from: e */
        private boolean f1853e;

        /* JADX INFO: renamed from: f */
        private View f1854f;

        /* JADX INFO: renamed from: h */
        private boolean f1856h;

        /* JADX INFO: renamed from: a */
        private int f1849a = -1;

        /* JADX INFO: renamed from: g */
        private final a f1855g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a */
            private int f1857a;

            /* JADX INFO: renamed from: b */
            private int f1858b;

            /* JADX INFO: renamed from: c */
            private int f1859c;

            /* JADX INFO: renamed from: d */
            private int f1860d;

            /* JADX INFO: renamed from: e */
            private Interpolator f1861e;

            /* JADX INFO: renamed from: f */
            private boolean f1862f;

            /* JADX INFO: renamed from: g */
            private int f1863g;

            public a(int i2, int i3) {
                this(i2, i3, Integer.MIN_VALUE, null);
            }

            private void e() {
                if (this.f1861e != null && this.f1859c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1859c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.f1860d >= 0;
            }

            public void b(int i2) {
                this.f1860d = i2;
            }

            void c(RecyclerView recyclerView) {
                int i2 = this.f1860d;
                if (i2 >= 0) {
                    this.f1860d = -1;
                    recyclerView.w0(i2);
                    this.f1862f = false;
                } else {
                    if (!this.f1862f) {
                        this.f1863g = 0;
                        return;
                    }
                    e();
                    recyclerView.l0.f(this.f1857a, this.f1858b, this.f1859c, this.f1861e);
                    int i3 = this.f1863g + 1;
                    this.f1863g = i3;
                    if (i3 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f1862f = false;
                }
            }

            public void d(int i2, int i3, int i4, Interpolator interpolator) {
                this.f1857a = i2;
                this.f1858b = i3;
                this.f1859c = i4;
                this.f1861e = interpolator;
                this.f1862f = true;
            }

            public a(int i2, int i3, int i4, Interpolator interpolator) {
                this.f1860d = -1;
                this.f1862f = false;
                this.f1863g = 0;
                this.f1857a = i2;
                this.f1858b = i3;
                this.f1859c = i4;
                this.f1861e = interpolator;
            }
        }

        public interface b {
            PointF a(int i2);
        }

        public PointF a(int i2) {
            Object objE = e();
            if (objE instanceof b) {
                return ((b) objE).a(i2);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public View b(int i2) {
            return this.f1850b.w.C(i2);
        }

        public int c() {
            return this.f1850b.w.J();
        }

        public int d(View view) {
            return this.f1850b.e0(view);
        }

        public o e() {
            return this.f1851c;
        }

        public int f() {
            return this.f1849a;
        }

        public boolean g() {
            return this.f1852d;
        }

        public boolean h() {
            return this.f1853e;
        }

        protected void i(PointF pointF) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            float fSqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        void j(int i2, int i3) {
            PointF pointFA;
            RecyclerView recyclerView = this.f1850b;
            if (this.f1849a == -1 || recyclerView == null) {
                r();
            }
            if (this.f1852d && this.f1854f == null && this.f1851c != null && (pointFA = a(this.f1849a)) != null) {
                float f2 = pointFA.x;
                if (f2 != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.g1((int) Math.signum(f2), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f1852d = false;
            View view = this.f1854f;
            if (view != null) {
                if (d(view) == this.f1849a) {
                    o(this.f1854f, recyclerView.o0, this.f1855g);
                    this.f1855g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f1854f = null;
                }
            }
            if (this.f1853e) {
                l(i2, i3, recyclerView.o0, this.f1855g);
                boolean zA = this.f1855g.a();
                this.f1855g.c(recyclerView);
                if (zA && this.f1853e) {
                    this.f1852d = true;
                    recyclerView.l0.e();
                }
            }
        }

        protected void k(View view) {
            if (d(view) == f()) {
                this.f1854f = view;
            }
        }

        protected abstract void l(int i2, int i3, z zVar, a aVar);

        protected abstract void m();

        protected abstract void n();

        protected abstract void o(View view, z zVar, a aVar);

        public void p(int i2) {
            this.f1849a = i2;
        }

        void q(RecyclerView recyclerView, o oVar) {
            recyclerView.l0.g();
            if (this.f1856h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1850b = recyclerView;
            this.f1851c = oVar;
            int i2 = this.f1849a;
            if (i2 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.o0.f1864a = i2;
            this.f1853e = true;
            this.f1852d = true;
            this.f1854f = b(f());
            m();
            this.f1850b.l0.e();
            this.f1856h = true;
        }

        protected final void r() {
            if (this.f1853e) {
                this.f1853e = false;
                n();
                this.f1850b.o0.f1864a = -1;
                this.f1854f = null;
                this.f1849a = -1;
                this.f1852d = false;
                this.f1851c.f1(this);
                this.f1851c = null;
                this.f1850b = null;
            }
        }
    }

    public static class z {

        /* JADX INFO: renamed from: b */
        private SparseArray<Object> f1865b;
        int m;
        long n;
        int o;
        int p;
        int q;

        /* JADX INFO: renamed from: a */
        int f1864a = -1;

        /* JADX INFO: renamed from: c */
        int f1866c = 0;

        /* JADX INFO: renamed from: d */
        int f1867d = 0;

        /* JADX INFO: renamed from: e */
        int f1868e = 1;

        /* JADX INFO: renamed from: f */
        int f1869f = 0;

        /* JADX INFO: renamed from: g */
        boolean f1870g = false;

        /* JADX INFO: renamed from: h */
        boolean f1871h = false;

        /* JADX INFO: renamed from: i */
        boolean f1872i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;

        void a(int i2) {
            if ((this.f1868e & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f1868e));
        }

        public int b() {
            return this.f1871h ? this.f1866c - this.f1867d : this.f1869f;
        }

        public int c() {
            return this.f1864a;
        }

        public boolean d() {
            return this.f1864a != -1;
        }

        public boolean e() {
            return this.f1871h;
        }

        void f(g gVar) {
            this.f1868e = 1;
            this.f1869f = gVar.c();
            this.f1871h = false;
            this.f1872i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1864a + ", mData=" + this.f1865b + ", mItemCount=" + this.f1869f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1866c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1867d + ", mStructureChanged=" + this.f1870g + ", mInPreLayout=" + this.f1871h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f1772c = i2 == 18 || i2 == 19 || i2 == 20;
        f1773d = i2 >= 23;
        f1774e = i2 >= 16;
        f1775f = i2 >= 21;
        f1776g = i2 <= 15;
        f1777h = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        f1778i = new Class[]{Context.class, AttributeSet.class, cls, cls};
        j = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void B() {
        int i2 = this.J;
        this.J = 0;
        if (i2 == 0 || !t0()) {
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
        q1();
        this.p.f();
        G0();
        O0();
        d1();
        z zVar = this.o0;
        zVar.f1872i = zVar.k && this.s0;
        this.s0 = false;
        this.r0 = false;
        zVar.f1871h = zVar.l;
        zVar.f1869f = this.v.c();
        V(this.x0);
        if (this.o0.k) {
            int iG = this.o.g();
            for (int i2 = 0; i2 < iG; i2++) {
                c0 c0VarG0 = g0(this.o.f(i2));
                if (!c0VarG0.J() && (!c0VarG0.t() || this.v.g())) {
                    this.p.e(c0VarG0, this.W.t(this.o0, c0VarG0, l.e(c0VarG0), c0VarG0.o()));
                    if (this.o0.f1872i && c0VarG0.y() && !c0VarG0.v() && !c0VarG0.J() && !c0VarG0.t()) {
                        this.p.c(d0(c0VarG0), c0VarG0);
                    }
                }
            }
        }
        if (this.o0.l) {
            e1();
            z zVar2 = this.o0;
            boolean z2 = zVar2.f1870g;
            zVar2.f1870g = false;
            this.w.X0(this.l, zVar2);
            this.o0.f1870g = z2;
            for (int i3 = 0; i3 < this.o.g(); i3++) {
                c0 c0VarG02 = g0(this.o.f(i3));
                if (!c0VarG02.J() && !this.p.i(c0VarG02)) {
                    int iE = l.e(c0VarG02);
                    boolean zP = c0VarG02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.W.t(this.o0, c0VarG02, iE, c0VarG02.o());
                    if (zP) {
                        R0(c0VarG02, cVarT);
                    } else {
                        this.p.a(c0VarG02, cVarT);
                    }
                }
            }
            t();
        } else {
            t();
        }
        H0();
        s1(false);
        this.o0.f1868e = 2;
    }

    private void E() {
        q1();
        G0();
        this.o0.a(6);
        this.n.j();
        this.o0.f1869f = this.v.c();
        z zVar = this.o0;
        zVar.f1867d = 0;
        zVar.f1871h = false;
        this.w.X0(this.l, zVar);
        z zVar2 = this.o0;
        zVar2.f1870g = false;
        this.m = null;
        zVar2.k = zVar2.k && this.W != null;
        zVar2.f1868e = 4;
        H0();
        s1(false);
    }

    private void F() {
        this.o0.a(4);
        q1();
        G0();
        z zVar = this.o0;
        zVar.f1868e = 1;
        if (zVar.k) {
            for (int iG = this.o.g() - 1; iG >= 0; iG--) {
                c0 c0VarG0 = g0(this.o.f(iG));
                if (!c0VarG0.J()) {
                    long jD0 = d0(c0VarG0);
                    l.c cVarS = this.W.s(this.o0, c0VarG0);
                    c0 c0VarG = this.p.g(jD0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.p.d(c0VarG0, cVarS);
                    } else {
                        boolean zH = this.p.h(c0VarG);
                        boolean zH2 = this.p.h(c0VarG0);
                        if (zH && c0VarG == c0VarG0) {
                            this.p.d(c0VarG0, cVarS);
                        } else {
                            l.c cVarN = this.p.n(c0VarG);
                            this.p.d(c0VarG0, cVarS);
                            l.c cVarM = this.p.m(c0VarG0);
                            if (cVarN == null) {
                                l0(jD0, c0VarG0, c0VarG);
                            } else {
                                n(c0VarG, c0VarG0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.p.o(this.E0);
        }
        this.w.l1(this.l);
        z zVar2 = this.o0;
        zVar2.f1866c = zVar2.f1869f;
        this.N = false;
        this.O = false;
        zVar2.k = false;
        zVar2.l = false;
        this.w.f1821h = false;
        ArrayList<c0> arrayList = this.l.f1840b;
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
        H0();
        s1(false);
        this.p.f();
        int[] iArr = this.x0;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        S0();
        b1();
    }

    private void J0(MotionEvent motionEvent) {
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

    private boolean N0() {
        return this.W != null && this.w.L1();
    }

    private void O0() {
        boolean z2;
        if (this.N) {
            this.n.u();
            if (this.O) {
                this.w.S0(this);
            }
        }
        if (N0()) {
            this.n.s();
        } else {
            this.n.j();
        }
        boolean z3 = false;
        boolean z4 = this.r0 || this.s0;
        this.o0.k = this.E && this.W != null && ((z2 = this.N) || z4 || this.w.f1821h) && (!z2 || this.v.g());
        z zVar = this.o0;
        if (zVar.k && z4 && !this.N && N0()) {
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
    private void Q0(float r7, float r8, float r9, float r10) {
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Q0(float, float, float, float):void");
    }

    private void S0() {
        View viewFindViewById;
        if (!this.k0 || this.v == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!f1777h || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
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
        if (c0VarZ != null && !this.o.n(c0VarZ.f1790b) && c0VarZ.f1790b.hasFocusable()) {
            viewX = c0VarZ.f1790b;
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

    private void T0() {
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
            c0 c0VarG0 = g0(this.o.f(i4));
            if (!c0VarG0.J()) {
                int iM = c0VarG0.m();
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
            if (c0VarY2.f1790b.hasFocusable()) {
                return c0VarY2.f1790b;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (c0VarY = Y(iMin)) == null) {
                return null;
            }
        } while (!c0VarY.f1790b.hasFocusable());
        return c0VarY.f1790b;
    }

    private void a1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.s.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1831c) {
                Rect rect = pVar.f1830b;
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

    private void b1() {
        z zVar = this.o0;
        zVar.n = -1L;
        zVar.m = -1;
        zVar.o = -1;
    }

    private void c1() {
        VelocityTracker velocityTracker = this.Z;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        t1(0);
        T0();
    }

    private void d1() {
        View focusedChild = (this.k0 && hasFocus() && this.v != null) ? getFocusedChild() : null;
        c0 c0VarT = focusedChild != null ? T(focusedChild) : null;
        if (c0VarT == null) {
            b1();
            return;
        }
        this.o0.n = this.v.g() ? c0VarT.k() : -1L;
        this.o0.m = this.N ? -1 : c0VarT.v() ? c0VarT.f1793e : c0VarT.j();
        this.o0.o = i0(c0VarT.f1790b);
    }

    private void g(c0 c0Var) {
        View view = c0Var.f1790b;
        boolean z2 = view.getParent() == this;
        this.l.J(f0(view));
        if (c0Var.x()) {
            this.o.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.o.k(view);
        } else {
            this.o.b(view, true);
        }
    }

    static c0 g0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1829a;
    }

    private b.g.k.k getScrollingChildHelper() {
        if (this.y0 == null) {
            this.y0 = new b.g.k.k(this);
        }
        return this.y0;
    }

    static void h0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1830b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    private int i0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private void i1(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.v;
        if (gVar2 != null) {
            gVar2.t(this.k);
            this.v.m(this);
        }
        if (!z2 || z3) {
            U0();
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
        this.o0.f1870g = true;
    }

    private String j0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void l0(long j2, c0 c0Var, c0 c0Var2) {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarG0 = g0(this.o.f(i2));
            if (c0VarG0 != c0Var && d0(c0VarG0) == j2) {
                g gVar = this.v;
                if (gVar == null || !gVar.g()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + Q());
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
            c0Var.f1797i = c0Var2;
            g(c0Var);
            this.l.J(c0Var);
            c0Var2.G(false);
            c0Var2.j = c0Var;
        }
        if (this.W.b(c0Var, c0Var2, cVar, cVar2)) {
            M0();
        }
    }

    private boolean n0() {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarG0 = g0(this.o.f(i2));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.y()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void p0() {
        if (b.g.k.t.y(this) == 0) {
            b.g.k.t.u0(this, 8);
        }
    }

    private void q0() {
        this.o = new androidx.recyclerview.widget.b(new e());
    }

    private void r() {
        c1();
        setScrollState(0);
    }

    static void s(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f1791c;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f1790b) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f1791c = null;
        }
    }

    private boolean v0(View view, View view2, int i2) {
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

    private void v1() {
        this.l0.g();
        o oVar = this.w;
        if (oVar != null) {
            oVar.K1();
        }
    }

    private void w(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String strJ0 = j0(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = Class.forName(strJ0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(o.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(f1778i);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strJ0, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strJ0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strJ0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strJ0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e8);
            }
        }
    }

    private boolean y(int i2, int i3) {
        V(this.x0);
        int[] iArr = this.x0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    void A(View view) {
        c0 c0VarG0 = g0(view);
        F0(view);
        g gVar = this.v;
        if (gVar != null && c0VarG0 != null) {
            gVar.p(c0VarG0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).b(view);
            }
        }
    }

    public void A0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetTopAndBottom(i2);
        }
    }

    void B0(int i2, int i3) {
        int iJ = this.o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            c0 c0VarG0 = g0(this.o.i(i4));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.f1792d >= i2) {
                c0VarG0.A(i3, false);
                this.o0.f1870g = true;
            }
        }
        this.l.u(i2, i3);
        requestLayout();
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
        if (zVar.f1868e == 1) {
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

    void C0(int i2, int i3) {
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
            c0 c0VarG0 = g0(this.o.i(i8));
            if (c0VarG0 != null && (i7 = c0VarG0.f1792d) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    c0VarG0.A(i3 - i2, false);
                } else {
                    c0VarG0.A(i6, false);
                }
                this.o0.f1870g = true;
            }
        }
        this.l.v(i2, i3);
        requestLayout();
    }

    void D0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.o.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            c0 c0VarG0 = g0(this.o.i(i5));
            if (c0VarG0 != null && !c0VarG0.J()) {
                int i6 = c0VarG0.f1792d;
                if (i6 >= i4) {
                    c0VarG0.A(-i3, z2);
                    this.o0.f1870g = true;
                } else if (i6 >= i2) {
                    c0VarG0.i(i2 - 1, -i3, z2);
                    this.o0.f1870g = true;
                }
            }
        }
        this.l.w(i2, i3, z2);
        requestLayout();
    }

    public void E0(View view) {
    }

    public void F0(View view) {
    }

    public boolean G(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    void G0() {
        this.P++;
    }

    public final void H(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    void H0() {
        I0(true);
    }

    void I(int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.e1(i2);
        }
        K0(i2);
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

    void I0(boolean z2) {
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

    void J(int i2, int i3) {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        L0(i2, i3);
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

    void K() {
        int i2;
        for (int size = this.C0.size() - 1; size >= 0; size--) {
            c0 c0Var = this.C0.get(size);
            if (c0Var.f1790b.getParent() == this && !c0Var.J() && (i2 = c0Var.r) != -1) {
                b.g.k.t.t0(c0Var.f1790b, i2);
                c0Var.r = -1;
            }
        }
        this.C0.clear();
    }

    public void K0(int i2) {
    }

    public void L0(int i2, int i3) {
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

    void M0() {
        if (this.u0 || !this.B) {
            return;
        }
        b.g.k.t.b0(this, this.D0);
        this.u0 = true;
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

    void P0(boolean z2) {
        this.O = z2 | this.O;
        this.N = true;
        y0();
    }

    String Q() {
        return " " + super.toString() + ", adapter:" + this.v + ", layout:" + this.w + ", context:" + getContext();
    }

    final void R(z zVar) {
        if (getScrollState() != 2) {
            zVar.p = 0;
            zVar.q = 0;
        } else {
            OverScroller overScroller = this.l0.f1784d;
            zVar.p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    void R0(c0 c0Var, l.c cVar) {
        c0Var.F(0, 8192);
        if (this.o0.f1872i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.p.c(d0(c0Var), c0Var);
        }
        this.p.e(c0Var, cVar);
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

    public c0 T(View view) {
        View viewS = S(view);
        if (viewS == null) {
            return null;
        }
        return f0(viewS);
    }

    void U0() {
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

    boolean V0(View view) {
        q1();
        boolean zR = this.o.r(view);
        if (zR) {
            c0 c0VarG0 = g0(view);
            this.l.J(c0VarG0);
            this.l.C(c0VarG0);
        }
        s1(!zR);
        return zR;
    }

    public void W0(n nVar) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.y.remove(nVar);
        if (this.y.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        x0();
        requestLayout();
    }

    public void X0(s sVar) {
        this.z.remove(sVar);
        if (this.A == sVar) {
            this.A = null;
        }
    }

    public c0 Y(int i2) {
        c0 c0Var = null;
        if (this.N) {
            return null;
        }
        int iJ = this.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarG0 = g0(this.o.i(i3));
            if (c0VarG0 != null && !c0VarG0.v() && c0(c0VarG0) == i2) {
                if (!this.o.n(c0VarG0.f1790b)) {
                    return c0VarG0;
                }
                c0Var = c0VarG0;
            }
        }
        return c0Var;
    }

    public void Y0(t tVar) {
        List<t> list = this.q0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    public c0 Z(long j2) {
        g gVar = this.v;
        c0 c0Var = null;
        if (gVar != null && gVar.g()) {
            int iJ = this.o.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                c0 c0VarG0 = g0(this.o.i(i2));
                if (c0VarG0 != null && !c0VarG0.v() && c0VarG0.k() == j2) {
                    if (!this.o.n(c0VarG0.f1790b)) {
                        return c0VarG0;
                    }
                    c0Var = c0VarG0;
                }
            }
        }
        return c0Var;
    }

    void Z0() {
        c0 c0Var;
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.o.f(i2);
            c0 c0VarF0 = f0(viewF);
            if (c0VarF0 != null && (c0Var = c0VarF0.j) != null) {
                View view = c0Var.f1790b;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
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

    /* JADX WARN: Removed duplicated region for block: B:43:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.recyclerview.widget.RecyclerView.c0 a0(int r6, boolean r7) {
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
            androidx.recyclerview.widget.RecyclerView$c0 r3 = g0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1792d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.b r1 = r5.o
            android.view.View r4 = r3.f1790b
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.a0(int, boolean):androidx.recyclerview.widget.RecyclerView$c0");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        o oVar = this.w;
        if (oVar == null || !oVar.F0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public boolean b0(int i2, int i3) {
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
                r1(i4, 1);
                int i5 = this.h0;
                int iMax = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.h0;
                this.l0.c(iMax, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    int c0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.n.e(c0Var.f1792d);
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

    long d0(c0 c0Var) {
        return this.v.g() ? c0Var.k() : c0Var.f1792d;
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

    public int e0(View view) {
        c0 c0VarG0 = g0(view);
        if (c0VarG0 != null) {
            return c0VarG0.m();
        }
        return -1;
    }

    void e1() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarG0 = g0(this.o.i(i2));
            if (!c0VarG0.J()) {
                c0VarG0.E();
            }
        }
    }

    public c0 f0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return g0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    boolean f1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        v();
        if (this.v != null) {
            int[] iArr = this.B0;
            iArr[0] = 0;
            iArr[1] = 0;
            g1(i2, i3, iArr);
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
                Q0(motionEvent.getX(), i10, motionEvent.getY(), i11);
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

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewJ0;
        boolean z2;
        View viewQ0 = this.w.Q0(view, i2);
        if (viewQ0 != null) {
            return viewQ0;
        }
        boolean z3 = (this.v == null || this.w == null || u0() || this.H) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.w.l()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (f1776g) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.w.k()) {
                int i4 = (this.w.Z() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (f1776g) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                v();
                if (S(view) == null) {
                    return null;
                }
                q1();
                this.w.J0(view, i2, this.l, this.o0);
                s1(false);
            }
            viewJ0 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                v();
                if (S(view) == null) {
                    return null;
                }
                q1();
                viewJ0 = this.w.J0(view, i2, this.l, this.o0);
                s1(false);
            } else {
                viewJ0 = viewFindNextFocus;
            }
        }
        if (viewJ0 == null || viewJ0.hasFocusable()) {
            return v0(view, viewJ0, i2) ? viewJ0 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        a1(viewJ0, null);
        return view;
    }

    void g1(int i2, int i3, int[] iArr) {
        q1();
        G0();
        b.g.g.b.a("RV Scroll");
        R(this.o0);
        int iW1 = i2 != 0 ? this.w.w1(i2, this.l, this.o0) : 0;
        int iY1 = i3 != 0 ? this.w.y1(i3, this.l, this.o0) : 0;
        b.g.g.b.b();
        Z0();
        H0();
        s1(false);
        if (iArr != null) {
            iArr[0] = iW1;
            iArr[1] = iY1;
        }
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
        if (f1775f) {
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

    public void h1(int i2) {
        if (this.H) {
            return;
        }
        u1();
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.x1(i2);
            awakenScrollBars();
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
        x0();
        requestLayout();
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

    boolean j1(c0 c0Var, int i2) {
        if (!u0()) {
            b.g.k.t.t0(c0Var.f1790b, i2);
            return true;
        }
        c0Var.r = i2;
        this.C0.add(c0Var);
        return false;
    }

    public void k(s sVar) {
        this.z.add(sVar);
    }

    Rect k0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1831c) {
            return pVar.f1830b;
        }
        if (this.o0.e() && (pVar.b() || pVar.d())) {
            return pVar.f1830b;
        }
        Rect rect = pVar.f1830b;
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
        pVar.f1831c = false;
        return rect;
    }

    boolean k1(AccessibilityEvent accessibilityEvent) {
        if (!u0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? b.g.k.c0.b.a(accessibilityEvent) : 0;
        this.J |= iA != 0 ? iA : 0;
        return true;
    }

    public void l(t tVar) {
        if (this.q0 == null) {
            this.q0 = new ArrayList();
        }
        this.q0.add(tVar);
    }

    public void l1(int i2, int i3) {
        m1(i2, i3, null);
    }

    void m(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.W.a(c0Var, cVar, cVar2)) {
            M0();
        }
    }

    public boolean m0() {
        return !this.E || this.N || this.n.p();
    }

    public void m1(int i2, int i3, Interpolator interpolator) {
        n1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    public void n1(int i2, int i3, Interpolator interpolator, int i4) {
        o1(i2, i3, interpolator, i4, false);
    }

    void o(c0 c0Var, l.c cVar, l.c cVar2) {
        g(c0Var);
        c0Var.G(false);
        if (this.W.c(c0Var, cVar, cVar2)) {
            M0();
        }
    }

    void o0() {
        this.n = new androidx.recyclerview.widget.a(new f());
    }

    void o1(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
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
            r1(i5, 1);
        }
        this.l0.f(i2, i3, i4, interpolator);
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
        if (f1775f) {
            ThreadLocal<androidx.recyclerview.widget.e> threadLocal = androidx.recyclerview.widget.e.f1975b;
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
                eVar2.f1979f = (long) (1.0E9f / f2);
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
        u1();
        this.B = false;
        o oVar = this.w;
        if (oVar != null) {
            oVar.A(this, this.l);
        }
        this.C0.clear();
        removeCallbacks(this.D0);
        this.p.j();
        if (!f1775f || (eVar = this.m0) == null) {
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
            r5.f1(r2, r0, r6)
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
                t1(1);
            }
            int[] iArr = this.A0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zK;
            if (zL) {
                i2 = (zK ? 1 : 0) | 2;
            }
            r1(i2, 0);
        } else if (actionMasked == 1) {
            this.Z.clear();
            t1(0);
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
            J0(motionEvent);
        }
        return this.X == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        b.g.g.b.a("RV OnLayout");
        C();
        b.g.g.b.b();
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
            if (this.o0.f1868e == 1) {
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
            q1();
            G0();
            O0();
            H0();
            z zVar = this.o0;
            if (zVar.l) {
                zVar.f1871h = true;
            } else {
                this.n.j();
                this.o0.f1871h = false;
            }
            this.K = false;
            s1(false);
        } else if (this.o0.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.v;
        if (gVar != null) {
            this.o0.f1869f = gVar.c();
        } else {
            this.o0.f1869f = 0;
        }
        q1();
        this.w.Z0(this.l, this.o0, i2, i3);
        s1(false);
        this.o0.f1871h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (u0()) {
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
        if (oVar == null || (parcelable2 = this.m.f1779d) == null) {
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
                savedState.f1779d = oVar.d1();
            } else {
                savedState.f1779d = null;
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
        s0();
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
        if (u0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + Q());
        }
        if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + Q()));
        }
    }

    public void p1(int i2) {
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

    boolean q(c0 c0Var) {
        l lVar = this.W;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    void q1() {
        int i2 = this.F + 1;
        this.F = i2;
        if (i2 != 1 || this.H) {
            return;
        }
        this.G = false;
    }

    void r0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(b.n.b.fastscroll_default_thickness), resources.getDimensionPixelSize(b.n.b.fastscroll_minimum_range), resources.getDimensionPixelOffset(b.n.b.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
        }
    }

    public boolean r1(int i2, int i3) {
        return getScrollingChildHelper().p(i2, i3);
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z2) {
        c0 c0VarG0 = g0(view);
        if (c0VarG0 != null) {
            if (c0VarG0.x()) {
                c0VarG0.f();
            } else if (!c0VarG0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + c0VarG0 + Q());
            }
        }
        view.clearAnimation();
        A(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.w.b1(this, this.o0, view, view2) && view2 != null) {
            a1(view, view2);
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

    void s0() {
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    void s1(boolean z2) {
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
            f1(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (k1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.l lVar) {
        this.v0 = lVar;
        b.g.k.t.j0(this, lVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        i1(gVar, false, true);
        P0(false);
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
            s0();
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
        s0();
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
        u1();
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
            if (oVar.f1815b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1815b.Q());
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
            v1();
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
                u1();
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
            c0 c0VarG0 = g0(this.o.i(i2));
            if (!c0VarG0.J()) {
                c0VarG0.c();
            }
        }
        this.l.d();
    }

    boolean t0() {
        AccessibilityManager accessibilityManager = this.L;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void t1(int i2) {
        getScrollingChildHelper().r(i2);
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

    public boolean u0() {
        return this.P > 0;
    }

    public void u1() {
        setScrollState(0);
        v1();
    }

    void v() {
        if (!this.E || this.N) {
            b.g.g.b.a("RV FullInvalidate");
            C();
            b.g.g.b.b();
            return;
        }
        if (this.n.p()) {
            if (!this.n.o(4) || this.n.o(11)) {
                if (this.n.p()) {
                    b.g.g.b.a("RV FullInvalidate");
                    C();
                    b.g.g.b.b();
                    return;
                }
                return;
            }
            b.g.g.b.a("RV PartialInvalidate");
            q1();
            G0();
            this.n.s();
            if (!this.G) {
                if (n0()) {
                    C();
                } else {
                    this.n.i();
                }
            }
            s1(true);
            H0();
            b.g.g.b.b();
        }
    }

    void w0(int i2) {
        if (this.w == null) {
            return;
        }
        setScrollState(2);
        this.w.x1(i2);
        awakenScrollBars();
    }

    void w1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.o.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.o.i(i6);
            c0 c0VarG0 = g0(viewI);
            if (c0VarG0 != null && !c0VarG0.J() && (i4 = c0VarG0.f1792d) >= i2 && i4 < i5) {
                c0VarG0.b(2);
                c0VarG0.a(obj);
                ((p) viewI.getLayoutParams()).f1831c = true;
            }
        }
        this.l.M(i2, i3);
    }

    void x(int i2, int i3) {
        setMeasuredDimension(o.n(i2, getPaddingLeft() + getPaddingRight(), b.g.k.t.B(this)), o.n(i3, getPaddingTop() + getPaddingBottom(), b.g.k.t.A(this)));
    }

    void x0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((p) this.o.i(i2).getLayoutParams()).f1831c = true;
        }
        this.l.s();
    }

    void y0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarG0 = g0(this.o.i(i2));
            if (c0VarG0 != null && !c0VarG0.J()) {
                c0VarG0.b(6);
            }
        }
        x0();
        this.l.t();
    }

    void z(View view) {
        c0 c0VarG0 = g0(view);
        E0(view);
        g gVar = this.v;
        if (gVar != null && c0VarG0 != null) {
            gVar.o(c0VarG0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).a(view);
            }
        }
    }

    public void z0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetLeftAndRight(i2);
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.n.a.recyclerViewStyle);
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
        this.n0 = f1775f ? new e.b() : null;
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
        o0();
        q0();
        p0();
        if (b.g.k.t.x(this) == 0) {
            b.g.k.t.t0(this, 1);
        }
        this.L = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.l(this));
        int[] iArr = b.n.c.RecyclerView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(b.n.c.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(b.n.c.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.q = typedArrayObtainStyledAttributes.getBoolean(b.n.c.RecyclerView_android_clipToPadding, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(b.n.c.RecyclerView_fastScrollEnabled, false);
        this.D = z3;
        if (z3) {
            r0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.n.c.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(b.n.c.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.n.c.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(b.n.c.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArrayObtainStyledAttributes.recycle();
        w(context, string, attributeSet, i2, 0);
        if (i3 >= 21) {
            int[] iArr2 = f1771b;
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
        Parcelable f1779d;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1779d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        void c(SavedState savedState) {
            this.f1779d = savedState.f1779d;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.f1779d, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a */
        c0 f1829a;

        /* JADX INFO: renamed from: b */
        final Rect f1830b;

        /* JADX INFO: renamed from: c */
        boolean f1831c;

        /* JADX INFO: renamed from: d */
        boolean f1832d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1830b = new Rect();
            this.f1831c = true;
            this.f1832d = false;
        }

        public int a() {
            return this.f1829a.m();
        }

        public boolean b() {
            return this.f1829a.y();
        }

        public boolean c() {
            return this.f1829a.v();
        }

        public boolean d() {
            return this.f1829a.t();
        }

        public p(int i2, int i3) {
            super(i2, i3);
            this.f1830b = new Rect();
            this.f1831c = true;
            this.f1832d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1830b = new Rect();
            this.f1831c = true;
            this.f1832d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1830b = new Rect();
            this.f1831c = true;
            this.f1832d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1830b = new Rect();
            this.f1831c = true;
            this.f1832d = false;
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
