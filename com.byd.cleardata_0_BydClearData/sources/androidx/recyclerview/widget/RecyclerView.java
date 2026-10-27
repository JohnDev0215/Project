package androidx.recyclerview.widget;

import a.g.k.c0.c;
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
import androidx.recyclerview.widget.a;
import androidx.recyclerview.widget.b;
import androidx.recyclerview.widget.f;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.p;
import androidx.recyclerview.widget.q;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements a.g.k.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f1440b = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final boolean f1441c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final boolean f1442d;
    static final boolean e;
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
    final List<d0> C0;
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
    final c0 l0;
    private y m;
    androidx.recyclerview.widget.f m0;
    androidx.recyclerview.widget.a n;
    f.b n0;
    androidx.recyclerview.widget.b o;
    final a0 o0;
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
    private a.g.k.k y0;
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
                recyclerView2.u();
            }
        }
    }

    public static class a0 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SparseArray<Object> f1445b;
        int m;
        long n;
        int o;
        int p;
        int q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1444a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1446c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1447d = 0;
        int e = 1;
        int f = 0;
        boolean g = false;
        boolean h = false;
        boolean i = false;
        boolean j = false;
        boolean k = false;
        boolean l = false;

        void a(int i) {
            if ((this.e & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.e));
        }

        public int b() {
            return this.h ? this.f1446c - this.f1447d : this.f;
        }

        public int c() {
            return this.f1444a;
        }

        public boolean d() {
            return this.f1444a != -1;
        }

        public boolean e() {
            return this.h;
        }

        void f(g gVar) {
            this.e = 1;
            this.f = gVar.c();
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1444a + ", mData=" + this.f1445b + ", mItemCount=" + this.f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1446c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1447d + ", mStructureChanged=" + this.g + ", mInPreLayout=" + this.h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
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

    public static abstract class b0 {
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

    class c0 implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1449b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1450c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        OverScroller f1451d;
        Interpolator e;
        private boolean f;
        private boolean g;

        c0() {
            Interpolator interpolator = RecyclerView.j;
            this.e = interpolator;
            this.f = false;
            this.g = false;
            this.f1451d = new OverScroller(RecyclerView.this.getContext(), interpolator);
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
            a.g.k.t.Z(RecyclerView.this, this);
        }

        public void c(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.f1450c = 0;
            this.f1449b = 0;
            Interpolator interpolator = this.e;
            Interpolator interpolator2 = RecyclerView.j;
            if (interpolator != interpolator2) {
                this.e = interpolator2;
                this.f1451d = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f1451d.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
            if (this.e != interpolator) {
                this.e = interpolator;
                this.f1451d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1450c = 0;
            this.f1449b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1451d.startScroll(0, 0, i, i2, i4);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1451d.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f1451d.abortAnimation();
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
            recyclerView.u();
            OverScroller overScroller = this.f1451d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.f1449b;
                int i4 = currY - this.f1450c;
                this.f1449b = currX;
                this.f1450c = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.B0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.F(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.B0;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.t(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.v != null) {
                    int[] iArr3 = recyclerView3.B0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.h1(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.B0;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    i3 -= i2;
                    i4 -= i;
                    z zVar = recyclerView4.w.g;
                    if (zVar != null && !zVar.g() && zVar.h()) {
                        int iB = RecyclerView.this.o0.b();
                        if (iB == 0) {
                            zVar.r();
                        } else {
                            if (zVar.f() >= iB) {
                                zVar.p(iB - 1);
                            }
                            zVar.j(i2, i);
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
                recyclerView5.G(i2, i, i3, i4, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.B0;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    recyclerView6.I(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                z zVar2 = RecyclerView.this.w.g;
                if ((zVar2 != null && zVar2.g()) || !z) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    androidx.recyclerview.widget.f fVar = recyclerView7.m0;
                    if (fVar != null) {
                        fVar.f(recyclerView7, i2, i);
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
            z zVar3 = RecyclerView.this.w.g;
            if (zVar3 != null && zVar3.g()) {
                zVar3.j(0, 0);
            }
            this.f = false;
            if (this.g) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.u1(1);
            }
        }
    }

    class d implements q.b {
        d() {
        }

        @Override // androidx.recyclerview.widget.q.b
        public void a(d0 d0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.w.H1(d0Var.f1454b, recyclerView.l);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void b(d0 d0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.l(d0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void c(d0 d0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.l.J(d0Var);
            RecyclerView.this.n(d0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.q.b
        public void d(d0 d0Var, l.c cVar, l.c cVar2) {
            d0Var.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            boolean z = recyclerView.N;
            l lVar = recyclerView.W;
            if (z) {
                if (!lVar.b(d0Var, d0Var, cVar, cVar2)) {
                    return;
                }
            } else if (!lVar.d(d0Var, cVar, cVar2)) {
                return;
            }
            RecyclerView.this.N0();
        }
    }

    public static abstract class d0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final List<Object> f1453a = Collections.emptyList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f1454b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        WeakReference<RecyclerView> f1455c;
        int k;
        RecyclerView s;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1456d = -1;
        int e = -1;
        long f = -1;
        int g = -1;
        int h = -1;
        d0 i = null;
        d0 j = null;
        List<Object> l = null;
        List<Object> m = null;
        private int n = 0;
        v o = null;
        boolean p = false;
        private int q = 0;
        int r = -1;

        public d0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1454b = view;
        }

        private void g() {
            if (this.l == null) {
                ArrayList arrayList = new ArrayList();
                this.l = arrayList;
                this.m = Collections.unmodifiableList(arrayList);
            }
        }

        void A(int i, boolean z) {
            if (this.e == -1) {
                this.e = this.f1456d;
            }
            if (this.h == -1) {
                this.h = this.f1456d;
            }
            if (z) {
                this.h += i;
            }
            this.f1456d += i;
            if (this.f1454b.getLayoutParams() != null) {
                ((p) this.f1454b.getLayoutParams()).f1482d = true;
            }
        }

        void B(RecyclerView recyclerView) {
            int iW = this.r;
            if (iW == -1) {
                iW = a.g.k.t.w(this.f1454b);
            }
            this.q = iW;
            recyclerView.k1(this, 4);
        }

        void C(RecyclerView recyclerView) {
            recyclerView.k1(this, this.q);
            this.q = 0;
        }

        void D() {
            this.k = 0;
            this.f1456d = -1;
            this.e = -1;
            this.f = -1L;
            this.h = -1;
            this.n = 0;
            this.i = null;
            this.j = null;
            d();
            this.q = 0;
            this.r = -1;
            RecyclerView.r(this);
        }

        void E() {
            if (this.e == -1) {
                this.e = this.f1456d;
            }
        }

        void F(int i, int i2) {
            this.k = (i & i2) | (this.k & (~i2));
        }

        public final void G(boolean z) {
            int i;
            int i2 = this.n;
            int i3 = z ? i2 - 1 : i2 + 1;
            this.n = i3;
            if (i3 < 0) {
                this.n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i3 == 1) {
                i = this.k | 16;
            } else if (!z || i3 != 0) {
                return;
            } else {
                i = this.k & (-17);
            }
            this.k = i;
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
            this.e = -1;
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
            return (this.k & 16) == 0 && a.g.k.t.K(this.f1454b);
        }

        void i(int i, int i2, boolean z) {
            b(8);
            A(i2, z);
            this.f1456d = i;
        }

        public final int j() {
            RecyclerView recyclerView = this.s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.b0(this);
        }

        public final long k() {
            return this.f;
        }

        public final int l() {
            return this.g;
        }

        public final int m() {
            int i = this.h;
            return i == -1 ? this.f1456d : i;
        }

        public final int n() {
            return this.e;
        }

        List<Object> o() {
            if ((this.k & 1024) != 0) {
                return f1453a;
            }
            List<Object> list = this.l;
            return (list == null || list.size() == 0) ? f1453a : this.m;
        }

        boolean p(int i) {
            return (i & this.k) != 0;
        }

        boolean q() {
            return (this.k & 512) != 0 || t();
        }

        boolean r() {
            return (this.f1454b.getParent() == null || this.f1454b.getParent() == this.s) ? false : true;
        }

        boolean s() {
            return (this.k & 1) != 0;
        }

        boolean t() {
            return (this.k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1456d + " id=" + this.f + ", oldPos=" + this.e + ", pLpos:" + this.h);
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
            if (this.f1454b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.k & 16) == 0 && !a.g.k.t.K(this.f1454b);
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

    class e implements b.InterfaceC0046b {
        e() {
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public View a(int i) {
            return RecyclerView.this.getChildAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void b(View view) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0 != null) {
                d0VarG0.B(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void c(int i) {
            View childAt = RecyclerView.this.getChildAt(i);
            if (childAt != null) {
                RecyclerView.this.z(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void d() {
            int iK = k();
            for (int i = 0; i < iK; i++) {
                View viewA = a(i);
                RecyclerView.this.z(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public d0 e(View view) {
            return RecyclerView.g0(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void f(int i) {
            d0 d0VarG0;
            View viewA = a(i);
            if (viewA != null && (d0VarG0 = RecyclerView.g0(viewA)) != null) {
                if (d0VarG0.x() && !d0VarG0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + d0VarG0 + RecyclerView.this.P());
                }
                d0VarG0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void g(View view) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0 != null) {
                d0VarG0.C(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void h(View view, int i, ViewGroup.LayoutParams layoutParams) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0 != null) {
                if (!d0VarG0.x() && !d0VarG0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + d0VarG0 + RecyclerView.this.P());
                }
                d0VarG0.f();
            }
            RecyclerView.this.attachViewToParent(view, i, layoutParams);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public void i(View view, int i) {
            RecyclerView.this.addView(view, i);
            RecyclerView.this.y(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.b.InterfaceC0046b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    class f implements a.InterfaceC0045a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void a(int i, int i2) {
            RecyclerView.this.D0(i, i2);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public d0 c(int i) {
            d0 d0VarZ = RecyclerView.this.Z(i, true);
            if (d0VarZ == null || RecyclerView.this.o.n(d0VarZ.f1454b)) {
                return null;
            }
            return d0VarZ;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void d(int i, int i2) {
            RecyclerView.this.E0(i, i2, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.r0 = true;
            recyclerView.o0.f1447d += i2;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void e(int i, int i2) {
            RecyclerView.this.E0(i, i2, false);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void g(int i, int i2) {
            RecyclerView.this.C0(i, i2);
            RecyclerView.this.r0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0045a
        public void h(int i, int i2, Object obj) {
            RecyclerView.this.x1(i, i2, obj);
            RecyclerView.this.s0 = true;
        }

        void i(a.b bVar) {
            int i = bVar.f1524a;
            if (i == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.w.l1(recyclerView, bVar.f1525b, bVar.f1527d);
                return;
            }
            if (i == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.w.o1(recyclerView2, bVar.f1525b, bVar.f1527d);
            } else if (i == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.w.q1(recyclerView3, bVar.f1525b, bVar.f1527d, bVar.f1526c);
            } else {
                if (i != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.w.n1(recyclerView4, bVar.f1525b, bVar.f1527d, 1);
            }
        }
    }

    public static abstract class g<VH extends d0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final h f1459a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1460b = false;

        public final void a(VH vh, int i) {
            vh.f1456d = i;
            if (g()) {
                vh.f = d(i);
            }
            vh.F(1, 519);
            a.g.g.a.a("RV OnBindView");
            n(vh, i, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f1454b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1482d = true;
            }
            a.g.g.a.b();
        }

        public final VH b(ViewGroup viewGroup, int i) {
            try {
                a.g.g.a.a("RV CreateView");
                VH vh = (VH) o(viewGroup, i);
                if (vh.f1454b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.g = i;
                return vh;
            } finally {
                a.g.g.a.b();
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
            return this.f1459a.a();
        }

        public final boolean g() {
            return this.f1460b;
        }

        public final void h() {
            this.f1459a.b();
        }

        public final void i(int i) {
            this.f1459a.c(i, 1);
        }

        public final void j(int i) {
            this.f1459a.e(i, 1);
        }

        public final void k(int i) {
            this.f1459a.f(i, 1);
        }

        public void l(RecyclerView recyclerView) {
        }

        public abstract void m(VH vh, int i);

        public void n(VH vh, int i, List<Object> list) {
            m(vh, i);
        }

        public abstract VH o(ViewGroup viewGroup, int i);

        public void p(RecyclerView recyclerView) {
        }

        public boolean q(VH vh) {
            return false;
        }

        public void r(VH vh) {
        }

        public void s(VH vh) {
        }

        public void t(VH vh) {
        }

        public void u(i iVar) {
            this.f1459a.registerObserver(iVar);
        }

        public void v(boolean z) {
            if (f()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1460b = z;
        }

        public void w(i iVar) {
            this.f1459a.unregisterObserver(iVar);
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

        public void c(int i, int i2) {
            d(i, i2, null);
        }

        public void d(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i, i2, obj);
            }
        }

        public void e(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).d(i, i2);
            }
        }

        public void f(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).e(i, i2);
            }
        }
    }

    public static abstract class i {
        public void a() {
        }

        public void b(int i, int i2) {
        }

        public void c(int i, int i2, Object obj) {
            b(i, i2);
        }

        public void d(int i, int i2) {
        }

        public void e(int i, int i2) {
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b f1461a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<a> f1462b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f1463c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f1464d = 120;
        private long e = 250;
        private long f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(d0 d0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1465a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1466b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1467c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1468d;

            public c a(d0 d0Var) {
                return b(d0Var, 0);
            }

            public c b(d0 d0Var, int i) {
                View view = d0Var.f1454b;
                this.f1465a = view.getLeft();
                this.f1466b = view.getTop();
                this.f1467c = view.getRight();
                this.f1468d = view.getBottom();
                return this;
            }
        }

        static int e(d0 d0Var) {
            int i = d0Var.k & 14;
            if (d0Var.t()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int iN = d0Var.n();
            int iJ = d0Var.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i : i | 2048;
        }

        public abstract boolean a(d0 d0Var, c cVar, c cVar2);

        public abstract boolean b(d0 d0Var, d0 d0Var2, c cVar, c cVar2);

        public abstract boolean c(d0 d0Var, c cVar, c cVar2);

        public abstract boolean d(d0 d0Var, c cVar, c cVar2);

        public abstract boolean f(d0 d0Var);

        public boolean g(d0 d0Var, List<Object> list) {
            return f(d0Var);
        }

        public final void h(d0 d0Var) {
            r(d0Var);
            b bVar = this.f1461a;
            if (bVar != null) {
                bVar.a(d0Var);
            }
        }

        public final void i() {
            int size = this.f1462b.size();
            for (int i = 0; i < size; i++) {
                this.f1462b.get(i).a();
            }
            this.f1462b.clear();
        }

        public abstract void j(d0 d0Var);

        public abstract void k();

        public long l() {
            return this.f1463c;
        }

        public long m() {
            return this.f;
        }

        public long n() {
            return this.e;
        }

        public long o() {
            return this.f1464d;
        }

        public abstract boolean p();

        public c q() {
            return new c();
        }

        public void r(d0 d0Var) {
        }

        public c s(a0 a0Var, d0 d0Var) {
            return q().a(d0Var);
        }

        public c t(a0 a0Var, d0 d0Var, int i, List<Object> list) {
            return q().a(d0Var);
        }

        public abstract void u();

        void v(b bVar) {
            this.f1461a = bVar;
        }
    }

    private class m implements l.b {
        m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(d0 d0Var) {
            d0Var.G(true);
            if (d0Var.i != null && d0Var.j == null) {
                d0Var.i = null;
            }
            d0Var.j = null;
            if (d0Var.I() || RecyclerView.this.W0(d0Var.f1454b) || !d0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(d0Var.f1454b, false);
        }
    }

    public static abstract class n {
        @Deprecated
        public void d(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        public void e(Rect rect, View view, RecyclerView recyclerView, a0 a0Var) {
            d(rect, ((p) view.getLayoutParams()).u(), recyclerView);
        }

        @Deprecated
        public void f(Canvas canvas, RecyclerView recyclerView) {
        }

        public void g(Canvas canvas, RecyclerView recyclerView, a0 a0Var) {
            f(canvas, recyclerView);
        }

        @Deprecated
        public void h(Canvas canvas, RecyclerView recyclerView) {
        }

        public void i(Canvas canvas, RecyclerView recyclerView, a0 a0Var) {
            h(canvas, recyclerView);
        }
    }

    public static abstract class o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        androidx.recyclerview.widget.b f1470a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        RecyclerView f1471b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final p.b f1472c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final p.b f1473d;
        androidx.recyclerview.widget.p e;
        androidx.recyclerview.widget.p f;
        z g;
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
                return o.this.e0(i);
            }

            @Override // androidx.recyclerview.widget.p.b
            public int b() {
                return o.this.G0() - o.this.b();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int c() {
                return o.this.l();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int d(View view) {
                return o.this.p0(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.p.b
            public int e(View view) {
                return o.this.m0(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        class b implements p.b {
            b() {
            }

            @Override // androidx.recyclerview.widget.p.b
            public View a(int i) {
                return o.this.e0(i);
            }

            @Override // androidx.recyclerview.widget.p.b
            public int b() {
                return o.this.s0() - o.this.e();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int c() {
                return o.this.j();
            }

            @Override // androidx.recyclerview.widget.p.b
            public int d(View view) {
                return o.this.k0(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.p.b
            public int e(View view) {
                return o.this.q0(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i, int i2);
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1476a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1477b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1478c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1479d;
        }

        public o() {
            a aVar = new a();
            this.f1472c = aVar;
            b bVar = new b();
            this.f1473d = bVar;
            this.e = new androidx.recyclerview.widget.p(aVar);
            this.f = new androidx.recyclerview.widget.p(bVar);
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = true;
            this.l = true;
        }

        public static d A0(Context context, AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.m.c.f, i, i2);
            dVar.f1476a = typedArrayObtainStyledAttributes.getInt(a.m.c.g, 1);
            dVar.f1477b = typedArrayObtainStyledAttributes.getInt(a.m.c.q, 1);
            dVar.f1478c = typedArrayObtainStyledAttributes.getBoolean(a.m.c.p, false);
            dVar.f1479d = typedArrayObtainStyledAttributes.getBoolean(a.m.c.r, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        private void B(View view, int i, boolean z) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (z || d0VarG0.v()) {
                this.f1471b.p.b(d0VarG0);
            } else {
                this.f1471b.p.p(d0VarG0);
            }
            p pVar = (p) view.getLayoutParams();
            if (d0VarG0.L() || d0VarG0.w()) {
                if (d0VarG0.w()) {
                    d0VarG0.K();
                } else {
                    d0VarG0.e();
                }
                this.f1470a.c(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1471b) {
                int iM = this.f1470a.m(view);
                if (i == -1) {
                    i = this.f1470a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1471b.indexOfChild(view) + this.f1471b.P());
                }
                if (iM != i) {
                    this.f1471b.w.V0(iM, i);
                }
            } else {
                this.f1470a.a(view, i, false);
                pVar.f1482d = true;
                z zVar = this.g;
                if (zVar != null && zVar.h()) {
                    this.g.k(view);
                }
            }
            if (pVar.e) {
                d0VarG0.f1454b.invalidate();
                pVar.e = false;
            }
        }

        public static int J(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        private boolean L0(RecyclerView recyclerView, int i, int i2) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iL = l();
            int iJ = j();
            int iG0 = G0() - b();
            int iS0 = s0() - e();
            Rect rect = this.f1471b.s;
            l0(focusedChild, rect);
            return rect.left - i < iG0 && rect.right - i > iL && rect.top - i2 < iS0 && rect.bottom - i2 > iJ;
        }

        private static boolean P0(int i, int i2, int i3) {
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

        private void Q1(v vVar, int i, View view) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0.J()) {
                return;
            }
            if (d0VarG0.t() && !d0VarG0.v() && !this.f1471b.v.g()) {
                L1(i);
                vVar.C(d0VarG0);
            } else {
                T(i);
                vVar.D(view);
                this.f1471b.p.k(d0VarG0);
            }
        }

        private void U(int i, View view) {
            this.f1470a.d(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int g0(int r4, int r5, int r6, int r7, boolean r8) {
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o.g0(int, int, int, int, boolean):int");
        }

        private int[] h0(View view, Rect rect) {
            int[] iArr = new int[2];
            int iL = l();
            int iJ = j();
            int iG0 = G0() - b();
            int iS0 = s0() - e();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i = left - iL;
            int iMin = Math.min(0, i);
            int i2 = top - iJ;
            int iMin2 = Math.min(0, i2);
            int i3 = iWidth - iG0;
            int iMax = Math.max(0, i3);
            int iMax2 = Math.max(0, iHeight - iS0);
            if (v0() != 1) {
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

        public void A(View view, int i) {
            B(view, i, false);
        }

        boolean A1(int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1471b;
            return B1(recyclerView.l, recyclerView.o0, i, bundle);
        }

        public int B0(View view) {
            return ((p) view.getLayoutParams()).f1481c.right;
        }

        public boolean B1(v vVar, a0 a0Var, int i, Bundle bundle) {
            int iS0;
            int iG0;
            int i2;
            int i3;
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                iS0 = recyclerView.canScrollVertically(1) ? (s0() - j()) - e() : 0;
                if (this.f1471b.canScrollHorizontally(1)) {
                    iG0 = (G0() - l()) - b();
                    i2 = iS0;
                    i3 = iG0;
                }
                i2 = iS0;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                iS0 = recyclerView.canScrollVertically(-1) ? -((s0() - j()) - e()) : 0;
                if (this.f1471b.canScrollHorizontally(-1)) {
                    iG0 = -((G0() - l()) - b());
                    i2 = iS0;
                    i3 = iG0;
                }
                i2 = iS0;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.f1471b.p1(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void C(String str) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                recyclerView.o(str);
            }
        }

        public int C0(v vVar, a0 a0Var) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null || recyclerView.v == null || !H()) {
                return 1;
            }
            return this.f1471b.v.c();
        }

        boolean C1(View view, int i, Bundle bundle) {
            RecyclerView recyclerView = this.f1471b;
            return D1(recyclerView.l, recyclerView.o0, view, i, bundle);
        }

        public void D(View view, int i) {
            E(view, i, (p) view.getLayoutParams());
        }

        public int D0(v vVar, a0 a0Var) {
            return 0;
        }

        public boolean D1(v vVar, a0 a0Var, View view, int i, Bundle bundle) {
            return false;
        }

        public void E(View view, int i, p pVar) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0.v()) {
                this.f1471b.p.b(d0VarG0);
            } else {
                this.f1471b.p.p(d0VarG0);
            }
            this.f1470a.c(view, i, pVar, d0VarG0.v());
        }

        public int E0(View view) {
            return ((p) view.getLayoutParams()).f1481c.top;
        }

        public void E1() {
            for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
                this.f1470a.q(iF0);
            }
        }

        public void F(View view, Rect rect) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.l0(view));
            }
        }

        public void F0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f1481c;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1471b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1471b.u;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void F1(v vVar) {
            for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
                if (!RecyclerView.g0(e0(iF0)).J()) {
                    I1(iF0, vVar);
                }
            }
        }

        public boolean G() {
            return false;
        }

        public int G0() {
            return this.q;
        }

        void G1(v vVar) {
            int iJ = vVar.j();
            for (int i = iJ - 1; i >= 0; i--) {
                View viewN = vVar.n(i);
                d0 d0VarG0 = RecyclerView.g0(viewN);
                if (!d0VarG0.J()) {
                    d0VarG0.G(false);
                    if (d0VarG0.x()) {
                        this.f1471b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f1471b.W;
                    if (lVar != null) {
                        lVar.j(d0VarG0);
                    }
                    d0VarG0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f1471b.invalidate();
            }
        }

        public boolean H() {
            return false;
        }

        public int H0() {
            return this.o;
        }

        public void H1(View view, v vVar) {
            K1(view);
            vVar.B(view);
        }

        public boolean I(p pVar) {
            return pVar != null;
        }

        boolean I0() {
            int iF0 = f0();
            for (int i = 0; i < iF0; i++) {
                ViewGroup.LayoutParams layoutParams = e0(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void I1(int i, v vVar) {
            View viewE0 = e0(i);
            L1(i);
            vVar.B(viewE0);
        }

        public boolean J0() {
            return this.i;
        }

        public boolean J1(Runnable runnable) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void K(int i, int i2, a0 a0Var, c cVar) {
        }

        public boolean K0() {
            return this.j;
        }

        public void K1(View view) {
            this.f1470a.p(view);
        }

        public void L(int i, c cVar) {
        }

        public void L1(int i) {
            if (e0(i) != null) {
                this.f1470a.q(i);
            }
        }

        public int M(a0 a0Var) {
            return 0;
        }

        public final boolean M0() {
            return this.l;
        }

        public boolean M1(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return N1(recyclerView, view, rect, z, false);
        }

        public int N(a0 a0Var) {
            return 0;
        }

        public boolean N0(v vVar, a0 a0Var) {
            return false;
        }

        public boolean N1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] iArrH0 = h0(view, rect);
            int i = iArrH0[0];
            int i2 = iArrH0[1];
            if ((z2 && !L0(recyclerView, i, i2)) || (i == 0 && i2 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i, i2);
            } else {
                recyclerView.m1(i, i2);
            }
            return true;
        }

        public int O(a0 a0Var) {
            return 0;
        }

        public boolean O0() {
            return this.k;
        }

        public void O1() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int P(a0 a0Var) {
            return 0;
        }

        public void P1() {
            this.h = true;
        }

        public int Q(a0 a0Var) {
            return 0;
        }

        public boolean Q0() {
            z zVar = this.g;
            return zVar != null && zVar.h();
        }

        public int R(a0 a0Var) {
            return 0;
        }

        public boolean R0(View view, boolean z, boolean z2) {
            boolean z3 = this.e.b(view, 24579) && this.f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public int R1(int i, v vVar, a0 a0Var) {
            return 0;
        }

        public void S(v vVar) {
            for (int iF0 = f0() - 1; iF0 >= 0; iF0--) {
                Q1(vVar, iF0, e0(iF0));
            }
        }

        public void S0(View view, int i, int i2, int i3, int i4) {
            Rect rect = ((p) view.getLayoutParams()).f1481c;
            view.layout(i + rect.left, i2 + rect.top, i3 - rect.right, i4 - rect.bottom);
        }

        public void S1(int i) {
        }

        public void T(int i) {
            U(i, e0(i));
        }

        public void T0(View view, int i, int i2, int i3, int i4) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1481c;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
        }

        public int T1(int i, v vVar, a0 a0Var) {
            return 0;
        }

        public void U0(View view, int i, int i2) {
            p pVar = (p) view.getLayoutParams();
            Rect rectL0 = this.f1471b.l0(view);
            int i3 = i + rectL0.left + rectL0.right;
            int i4 = i2 + rectL0.top + rectL0.bottom;
            int iG0 = g0(G0(), H0(), l() + b() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i3, ((ViewGroup.MarginLayoutParams) pVar).width, G());
            int iG02 = g0(s0(), t0(), j() + e() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).height, H());
            if (b2(view, iG0, iG02, pVar)) {
                view.measure(iG0, iG02);
            }
        }

        @Deprecated
        public void U1(boolean z) {
            this.j = z;
        }

        void V(RecyclerView recyclerView) {
            this.i = true;
            a1(recyclerView);
        }

        public void V0(int i, int i2) {
            View viewE0 = e0(i);
            if (viewE0 != null) {
                T(i);
                D(viewE0, i2);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i + this.f1471b.toString());
            }
        }

        void V1(RecyclerView recyclerView) {
            W1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        void W(RecyclerView recyclerView, v vVar) {
            this.i = false;
            c1(recyclerView, vVar);
        }

        public void W0(int i) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                recyclerView.A0(i);
            }
        }

        void W1(int i, int i2) {
            this.q = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.o = mode;
            if (mode == 0 && !RecyclerView.f1442d) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.f1442d) {
                return;
            }
            this.r = 0;
        }

        public View X(View view) {
            View viewR;
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null || (viewR = recyclerView.R(view)) == null || this.f1470a.n(viewR)) {
                return null;
            }
            return viewR;
        }

        public void X0(int i) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                recyclerView.B0(i);
            }
        }

        public void X1(int i, int i2) {
            this.f1471b.setMeasuredDimension(i, i2);
        }

        public View Y(int i) {
            int iF0 = f0();
            for (int i2 = 0; i2 < iF0; i2++) {
                View viewE0 = e0(i2);
                d0 d0VarG0 = RecyclerView.g0(viewE0);
                if (d0VarG0 != null && d0VarG0.m() == i && !d0VarG0.J() && (this.f1471b.o0.e() || !d0VarG0.v())) {
                    return viewE0;
                }
            }
            return null;
        }

        public void Y0(g gVar, g gVar2) {
        }

        public void Y1(Rect rect, int i, int i2) {
            X1(J(i, rect.width() + l() + b(), y0()), J(i2, rect.height() + j() + e(), x0()));
        }

        public abstract p Z();

        public boolean Z0(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
            return false;
        }

        void Z1(int i, int i2) {
            int iF0 = f0();
            if (iF0 == 0) {
                this.f1471b.w(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < iF0; i7++) {
                View viewE0 = e0(i7);
                Rect rect = this.f1471b.s;
                l0(viewE0, rect);
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
            this.f1471b.s.set(i4, i5, i3, i6);
            Y1(this.f1471b.s, i, i2);
        }

        public p a0(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void a1(RecyclerView recyclerView) {
        }

        void a2(RecyclerView recyclerView) {
            int height;
            if (recyclerView == null) {
                this.f1471b = null;
                this.f1470a = null;
                height = 0;
                this.q = 0;
            } else {
                this.f1471b = recyclerView;
                this.f1470a = recyclerView.o;
                this.q = recyclerView.getWidth();
                height = recyclerView.getHeight();
            }
            this.r = height;
            this.o = 1073741824;
            this.p = 1073741824;
        }

        public int b() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public p b0(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        @Deprecated
        public void b1(RecyclerView recyclerView) {
        }

        boolean b2(View view, int i, int i2, p pVar) {
            return (!view.isLayoutRequested() && this.k && P0(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && P0(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public int c0() {
            return -1;
        }

        public void c1(RecyclerView recyclerView, v vVar) {
            b1(recyclerView);
        }

        boolean c2() {
            return false;
        }

        public int d0(View view) {
            return ((p) view.getLayoutParams()).f1481c.bottom;
        }

        public View d1(View view, int i, v vVar, a0 a0Var) {
            return null;
        }

        boolean d2(View view, int i, int i2, p pVar) {
            return (this.k && P0(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) pVar).width) && P0(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public int e() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public View e0(int i) {
            androidx.recyclerview.widget.b bVar = this.f1470a;
            if (bVar != null) {
                return bVar.f(i);
            }
            return null;
        }

        public void e1(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1471b;
            f1(recyclerView.l, recyclerView.o0, accessibilityEvent);
        }

        public void e2(RecyclerView recyclerView, a0 a0Var, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public int f() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return a.g.k.t.C(recyclerView);
            }
            return 0;
        }

        public int f0() {
            androidx.recyclerview.widget.b bVar = this.f1470a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public void f1(v vVar, a0 a0Var, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1471b.canScrollVertically(-1) && !this.f1471b.canScrollHorizontally(-1) && !this.f1471b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f1471b.v;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public void f2(z zVar) {
            z zVar2 = this.g;
            if (zVar2 != null && zVar != zVar2 && zVar2.h()) {
                this.g.r();
            }
            this.g = zVar;
            zVar.q(this.f1471b, this);
        }

        void g1(a.g.k.c0.c cVar) {
            RecyclerView recyclerView = this.f1471b;
            h1(recyclerView.l, recyclerView.o0, cVar);
        }

        void g2() {
            z zVar = this.g;
            if (zVar != null) {
                zVar.r();
            }
        }

        public void h1(v vVar, a0 a0Var, a.g.k.c0.c cVar) {
            if (this.f1471b.canScrollVertically(-1) || this.f1471b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.p0(true);
            }
            if (this.f1471b.canScrollVertically(1) || this.f1471b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.p0(true);
            }
            cVar.Z(c.b.a(C0(vVar, a0Var), j0(vVar, a0Var), N0(vVar, a0Var), D0(vVar, a0Var)));
        }

        public boolean h2() {
            return false;
        }

        public boolean i0() {
            RecyclerView recyclerView = this.f1471b;
            return recyclerView != null && recyclerView.q;
        }

        void i1(View view, a.g.k.c0.c cVar) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0 == null || d0VarG0.v() || this.f1470a.n(d0VarG0.f1454b)) {
                return;
            }
            RecyclerView recyclerView = this.f1471b;
            j1(recyclerView.l, recyclerView.o0, view, cVar);
        }

        public int j() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int j0(v vVar, a0 a0Var) {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null || recyclerView.v == null || !G()) {
                return 1;
            }
            return this.f1471b.v.c();
        }

        public void j1(v vVar, a0 a0Var, View view, a.g.k.c0.c cVar) {
            cVar.a0(c.C0014c.a(H() ? z0(view) : 0, 1, G() ? z0(view) : 0, 1, false, false));
        }

        public int k0(View view) {
            return view.getBottom() + d0(view);
        }

        public View k1(View view, int i) {
            return null;
        }

        public int l() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void l0(View view, Rect rect) {
            RecyclerView.i0(view, rect);
        }

        public void l1(RecyclerView recyclerView, int i, int i2) {
        }

        public int m() {
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView != null) {
                return a.g.k.t.D(recyclerView);
            }
            return 0;
        }

        public int m0(View view) {
            return view.getLeft() - w0(view);
        }

        public void m1(RecyclerView recyclerView) {
        }

        public int n0(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1481c;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void n1(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public int o0(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1481c;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void o1(RecyclerView recyclerView, int i, int i2) {
        }

        public int p0(View view) {
            return view.getRight() + B0(view);
        }

        public void p1(RecyclerView recyclerView, int i, int i2) {
        }

        public int q0(View view) {
            return view.getTop() - E0(view);
        }

        public void q1(RecyclerView recyclerView, int i, int i2, Object obj) {
            p1(recyclerView, i, i2);
        }

        public View r0() {
            View focusedChild;
            RecyclerView recyclerView = this.f1471b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1470a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void r1(v vVar, a0 a0Var) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int s0() {
            return this.r;
        }

        public void s1(a0 a0Var) {
        }

        public int t0() {
            return this.p;
        }

        public void t1(v vVar, a0 a0Var, int i, int i2) {
            this.f1471b.w(i, i2);
        }

        public int u0() {
            RecyclerView recyclerView = this.f1471b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        @Deprecated
        public boolean u1(RecyclerView recyclerView, View view, View view2) {
            return Q0() || recyclerView.v0();
        }

        public int v0() {
            return a.g.k.t.y(this.f1471b);
        }

        public boolean v1(RecyclerView recyclerView, a0 a0Var, View view, View view2) {
            return u1(recyclerView, view, view2);
        }

        public int w0(View view) {
            return ((p) view.getLayoutParams()).f1481c.left;
        }

        public void w1(Parcelable parcelable) {
        }

        public void x(View view) {
            y(view, -1);
        }

        public int x0() {
            return a.g.k.t.z(this.f1471b);
        }

        public Parcelable x1() {
            return null;
        }

        public void y(View view, int i) {
            B(view, i, true);
        }

        public int y0() {
            return a.g.k.t.A(this.f1471b);
        }

        public void y1(int i) {
        }

        public void z(View view) {
            A(view, -1);
        }

        public int z0(View view) {
            return ((p) view.getLayoutParams()).u();
        }

        void z1(z zVar) {
            if (this.g == zVar) {
                this.g = null;
            }
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d0 f1480b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Rect f1481c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1482d;
        boolean e;

        public p(int i, int i2) {
            super(i, i2);
            this.f1481c = new Rect();
            this.f1482d = true;
            this.e = false;
        }

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1481c = new Rect();
            this.f1482d = true;
            this.e = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1481c = new Rect();
            this.f1482d = true;
            this.e = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1481c = new Rect();
            this.f1482d = true;
            this.e = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1481c = new Rect();
            this.f1482d = true;
            this.e = false;
        }

        public int u() {
            return this.f1480b.m();
        }

        public boolean v() {
            return this.f1480b.y();
        }

        public boolean w() {
            return this.f1480b.v();
        }

        public boolean x() {
            return this.f1480b.t();
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        SparseArray<a> f1483a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1484b = 0;

        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final ArrayList<d0> f1485a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f1486b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            long f1487c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            long f1488d = 0;

            a() {
            }
        }

        private a g(int i) {
            a aVar = this.f1483a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1483a.put(i, aVar2);
            return aVar2;
        }

        void a() {
            this.f1484b++;
        }

        public void b() {
            for (int i = 0; i < this.f1483a.size(); i++) {
                this.f1483a.valueAt(i).f1485a.clear();
            }
        }

        void c() {
            this.f1484b--;
        }

        void d(int i, long j) {
            a aVarG = g(i);
            aVarG.f1488d = j(aVarG.f1488d, j);
        }

        void e(int i, long j) {
            a aVarG = g(i);
            aVarG.f1487c = j(aVarG.f1487c, j);
        }

        public d0 f(int i) {
            a aVar = this.f1483a.get(i);
            if (aVar == null || aVar.f1485a.isEmpty()) {
                return null;
            }
            ArrayList<d0> arrayList = aVar.f1485a;
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
            if (!z && this.f1484b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(d0 d0Var) {
            int iL = d0Var.l();
            ArrayList<d0> arrayList = g(iL).f1485a;
            if (this.f1483a.get(iL).f1486b <= arrayList.size()) {
                return;
            }
            d0Var.D();
            arrayList.add(d0Var);
        }

        long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        public void k(int i, int i2) {
            a aVarG = g(i);
            aVarG.f1486b = i2;
            ArrayList<d0> arrayList = aVarG.f1485a;
            while (arrayList.size() > i2) {
                arrayList.remove(arrayList.size() - 1);
            }
        }

        boolean l(int i, long j, long j2) {
            long j3 = g(i).f1488d;
            return j3 == 0 || j + j3 < j2;
        }

        boolean m(int i, long j, long j2) {
            long j3 = g(i).f1487c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ArrayList<d0> f1489a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<d0> f1490b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<d0> f1491c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<d0> f1492d;
        private int e;
        int f;
        u g;
        private b0 h;

        public v() {
            ArrayList<d0> arrayList = new ArrayList<>();
            this.f1489a = arrayList;
            this.f1490b = null;
            this.f1491c = new ArrayList<>();
            this.f1492d = Collections.unmodifiableList(arrayList);
            this.e = 2;
            this.f = 2;
        }

        private boolean H(d0 d0Var, int i, int i2, long j) {
            d0Var.s = RecyclerView.this;
            int iL = d0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.g.l(iL, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.v.a(d0Var, i);
            this.g.d(d0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(d0Var);
            if (!RecyclerView.this.o0.e()) {
                return true;
            }
            d0Var.h = i2;
            return true;
        }

        private void b(d0 d0Var) {
            if (RecyclerView.this.u0()) {
                View view = d0Var.f1454b;
                if (a.g.k.t.w(view) == 0) {
                    a.g.k.t.q0(view, 1);
                }
                androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
                if (lVar == null) {
                    return;
                }
                a.g.k.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                a.g.k.t.h0(view, aVarN);
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

        private void r(d0 d0Var) {
            View view = d0Var.f1454b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i) {
            a(this.f1491c.get(i), true);
            this.f1491c.remove(i);
        }

        public void B(View view) {
            d0 d0VarG0 = RecyclerView.g0(view);
            if (d0VarG0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (d0VarG0.w()) {
                d0VarG0.K();
            } else if (d0VarG0.L()) {
                d0VarG0.e();
            }
            C(d0VarG0);
            if (RecyclerView.this.W == null || d0VarG0.u()) {
                return;
            }
            RecyclerView.this.W.j(d0VarG0);
        }

        void C(d0 d0Var) {
            boolean z;
            boolean z2 = true;
            if (d0Var.w() || d0Var.f1454b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(d0Var.w());
                sb.append(" isAttached:");
                sb.append(d0Var.f1454b.getParent() != null);
                sb.append(RecyclerView.this.P());
                throw new IllegalArgumentException(sb.toString());
            }
            if (d0Var.x()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + d0Var + RecyclerView.this.P());
            }
            if (d0Var.J()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.P());
            }
            boolean zH = d0Var.h();
            g gVar = RecyclerView.this.v;
            if ((gVar != null && zH && gVar.q(d0Var)) || d0Var.u()) {
                if (this.f <= 0 || d0Var.p(526)) {
                    z = false;
                } else {
                    int size = this.f1491c.size();
                    if (size >= this.f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.f && size > 0 && !RecyclerView.this.n0.d(d0Var.f1456d)) {
                        int i = size - 1;
                        while (i >= 0) {
                            if (!RecyclerView.this.n0.d(this.f1491c.get(i).f1456d)) {
                                break;
                            } else {
                                i--;
                            }
                        }
                        size = i + 1;
                    }
                    this.f1491c.add(size, d0Var);
                    z = true;
                }
                if (z) {
                    z2 = false;
                } else {
                    a(d0Var, true);
                }
                z = z;
            } else {
                z2 = false;
            }
            RecyclerView.this.p.q(d0Var);
            if (z || z2 || !zH) {
                return;
            }
            d0Var.s = null;
        }

        void D(View view) {
            ArrayList<d0> arrayList;
            d0 d0VarG0 = RecyclerView.g0(view);
            if (!d0VarG0.p(12) && d0VarG0.y() && !RecyclerView.this.p(d0VarG0)) {
                if (this.f1490b == null) {
                    this.f1490b = new ArrayList<>();
                }
                d0VarG0.H(this, true);
                arrayList = this.f1490b;
            } else {
                if (d0VarG0.t() && !d0VarG0.v() && !RecyclerView.this.v.g()) {
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.P());
                }
                d0VarG0.H(this, false);
                arrayList = this.f1489a;
            }
            arrayList.add(d0VarG0);
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

        void F(b0 b0Var) {
        }

        public void G(int i) {
            this.e = i;
            K();
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x01d7  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0134 A[PHI: r1 r4
  0x0134: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$d0) = (r1v11 androidx.recyclerview.widget.RecyclerView$d0), (r1v30 androidx.recyclerview.widget.RecyclerView$d0) binds: [B:28:0x005d, B:50:0x00b5] A[DONT_GENERATE, DONT_INLINE]
  0x0134: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:28:0x005d, B:50:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x01bf  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x01d4 A[ADDED_TO_REGION] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        androidx.recyclerview.widget.RecyclerView.d0 I(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 531
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.I(int, boolean, long):androidx.recyclerview.widget.RecyclerView$d0");
        }

        void J(d0 d0Var) {
            (d0Var.p ? this.f1490b : this.f1489a).remove(d0Var);
            d0Var.o = null;
            d0Var.p = false;
            d0Var.e();
        }

        void K() {
            o oVar = RecyclerView.this.w;
            this.f = this.e + (oVar != null ? oVar.m : 0);
            for (int size = this.f1491c.size() - 1; size >= 0 && this.f1491c.size() > this.f; size--) {
                A(size);
            }
        }

        boolean L(d0 d0Var) {
            if (d0Var.v()) {
                return RecyclerView.this.o0.e();
            }
            int i = d0Var.f1456d;
            if (i >= 0 && i < RecyclerView.this.v.c()) {
                if (RecyclerView.this.o0.e() || RecyclerView.this.v.e(d0Var.f1456d) == d0Var.l()) {
                    return !RecyclerView.this.v.g() || d0Var.k() == RecyclerView.this.v.d(d0Var.f1456d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + d0Var + RecyclerView.this.P());
        }

        void M(int i, int i2) {
            int i3;
            int i4 = i2 + i;
            for (int size = this.f1491c.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1491c.get(size);
                if (d0Var != null && (i3 = d0Var.f1456d) >= i && i3 < i4) {
                    d0Var.b(2);
                    A(size);
                }
            }
        }

        void a(d0 d0Var, boolean z) {
            RecyclerView.r(d0Var);
            View view = d0Var.f1454b;
            androidx.recyclerview.widget.l lVar = RecyclerView.this.v0;
            if (lVar != null) {
                a.g.k.a aVarN = lVar.n();
                a.g.k.t.h0(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z) {
                g(d0Var);
            }
            d0Var.s = null;
            i().i(d0Var);
        }

        public void c() {
            this.f1489a.clear();
            z();
        }

        void d() {
            int size = this.f1491c.size();
            for (int i = 0; i < size; i++) {
                this.f1491c.get(i).c();
            }
            int size2 = this.f1489a.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.f1489a.get(i2).c();
            }
            ArrayList<d0> arrayList = this.f1490b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    this.f1490b.get(i3).c();
                }
            }
        }

        void e() {
            this.f1489a.clear();
            ArrayList<d0> arrayList = this.f1490b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i) {
            if (i >= 0 && i < RecyclerView.this.o0.b()) {
                return !RecyclerView.this.o0.e() ? i : RecyclerView.this.n.m(i);
            }
            throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + RecyclerView.this.o0.b() + RecyclerView.this.P());
        }

        void g(d0 d0Var) {
            w wVar = RecyclerView.this.x;
            if (wVar != null) {
                wVar.a(d0Var);
            }
            g gVar = RecyclerView.this.v;
            if (gVar != null) {
                gVar.t(d0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.o0 != null) {
                recyclerView.p.q(d0Var);
            }
        }

        d0 h(int i) {
            int size;
            int iM;
            ArrayList<d0> arrayList = this.f1490b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    d0 d0Var = this.f1490b.get(i2);
                    if (!d0Var.L() && d0Var.m() == i) {
                        d0Var.b(32);
                        return d0Var;
                    }
                }
                if (RecyclerView.this.v.g() && (iM = RecyclerView.this.n.m(i)) > 0 && iM < RecyclerView.this.v.c()) {
                    long jD = RecyclerView.this.v.d(iM);
                    for (int i3 = 0; i3 < size; i3++) {
                        d0 d0Var2 = this.f1490b.get(i3);
                        if (!d0Var2.L() && d0Var2.k() == jD) {
                            d0Var2.b(32);
                            return d0Var2;
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
            return this.f1489a.size();
        }

        public List<d0> k() {
            return this.f1492d;
        }

        d0 l(long j, int i, boolean z) {
            for (int size = this.f1489a.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1489a.get(size);
                if (d0Var.k() == j && !d0Var.L()) {
                    if (i == d0Var.l()) {
                        d0Var.b(32);
                        if (d0Var.v() && !RecyclerView.this.o0.e()) {
                            d0Var.F(2, 14);
                        }
                        return d0Var;
                    }
                    if (!z) {
                        this.f1489a.remove(size);
                        RecyclerView.this.removeDetachedView(d0Var.f1454b, false);
                        y(d0Var.f1454b);
                    }
                }
            }
            int size2 = this.f1491c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                d0 d0Var2 = this.f1491c.get(size2);
                if (d0Var2.k() == j && !d0Var2.r()) {
                    if (i == d0Var2.l()) {
                        if (!z) {
                            this.f1491c.remove(size2);
                        }
                        return d0Var2;
                    }
                    if (!z) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        d0 m(int i, boolean z) {
            View viewE;
            int size = this.f1489a.size();
            for (int i2 = 0; i2 < size; i2++) {
                d0 d0Var = this.f1489a.get(i2);
                if (!d0Var.L() && d0Var.m() == i && !d0Var.t() && (RecyclerView.this.o0.h || !d0Var.v())) {
                    d0Var.b(32);
                    return d0Var;
                }
            }
            if (z || (viewE = RecyclerView.this.o.e(i)) == null) {
                int size2 = this.f1491c.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    d0 d0Var2 = this.f1491c.get(i3);
                    if (!d0Var2.t() && d0Var2.m() == i && !d0Var2.r()) {
                        if (!z) {
                            this.f1491c.remove(i3);
                        }
                        return d0Var2;
                    }
                }
                return null;
            }
            d0 d0VarG0 = RecyclerView.g0(viewE);
            RecyclerView.this.o.s(viewE);
            int iM = RecyclerView.this.o.m(viewE);
            if (iM != -1) {
                RecyclerView.this.o.d(iM);
                D(viewE);
                d0VarG0.b(8224);
                return d0VarG0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + d0VarG0 + RecyclerView.this.P());
        }

        View n(int i) {
            return this.f1489a.get(i).f1454b;
        }

        public View o(int i) {
            return p(i, false);
        }

        View p(int i, boolean z) {
            return I(i, z, Long.MAX_VALUE).f1454b;
        }

        void s() {
            int size = this.f1491c.size();
            for (int i = 0; i < size; i++) {
                p pVar = (p) this.f1491c.get(i).f1454b.getLayoutParams();
                if (pVar != null) {
                    pVar.f1482d = true;
                }
            }
        }

        void t() {
            int size = this.f1491c.size();
            for (int i = 0; i < size; i++) {
                d0 d0Var = this.f1491c.get(i);
                if (d0Var != null) {
                    d0Var.b(6);
                    d0Var.a(null);
                }
            }
            g gVar = RecyclerView.this.v;
            if (gVar == null || !gVar.g()) {
                z();
            }
        }

        void u(int i, int i2) {
            int size = this.f1491c.size();
            for (int i3 = 0; i3 < size; i3++) {
                d0 d0Var = this.f1491c.get(i3);
                if (d0Var != null && d0Var.f1456d >= i) {
                    d0Var.A(i2, true);
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
            int size = this.f1491c.size();
            for (int i7 = 0; i7 < size; i7++) {
                d0 d0Var = this.f1491c.get(i7);
                if (d0Var != null && (i6 = d0Var.f1456d) >= i5 && i6 <= i4) {
                    if (i6 == i) {
                        d0Var.A(i2 - i, false);
                    } else {
                        d0Var.A(i3, false);
                    }
                }
            }
        }

        void w(int i, int i2, boolean z) {
            int i3 = i + i2;
            for (int size = this.f1491c.size() - 1; size >= 0; size--) {
                d0 d0Var = this.f1491c.get(size);
                if (d0Var != null) {
                    int i4 = d0Var.f1456d;
                    if (i4 >= i3) {
                        d0Var.A(-i2, z);
                    } else if (i4 >= i) {
                        d0Var.b(8);
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
            d0 d0VarG0 = RecyclerView.g0(view);
            d0VarG0.o = null;
            d0VarG0.p = false;
            d0VarG0.e();
            C(d0VarG0);
        }

        void z() {
            for (int size = this.f1491c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1491c.clear();
            if (RecyclerView.f) {
                RecyclerView.this.n0.b();
            }
        }
    }

    public interface w {
        void a(d0 d0Var);
    }

    private class x extends i {
        x() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.o(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.o0.g = true;
            recyclerView.Q0(true);
            if (RecyclerView.this.n.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i, int i2, Object obj) {
            RecyclerView.this.o(null);
            if (RecyclerView.this.n.r(i, i2, obj)) {
                f();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i, int i2) {
            RecyclerView.this.o(null);
            if (RecyclerView.this.n.s(i, i2)) {
                f();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void e(int i, int i2) {
            RecyclerView.this.o(null);
            if (RecyclerView.this.n.t(i, i2)) {
                f();
            }
        }

        void f() {
            if (RecyclerView.e) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.C && recyclerView.B) {
                    a.g.k.t.Z(recyclerView, recyclerView.r);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.K = true;
            recyclerView2.requestLayout();
        }
    }

    public static class y extends a.i.a.a {
        public static final Parcelable.Creator<y> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Parcelable f1494d;

        static class a implements Parcelable.ClassLoaderCreator<y> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public y createFromParcel(Parcel parcel) {
                return new y(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public y createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new y(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public y[] newArray(int i) {
                return new y[i];
            }
        }

        y(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1494d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        y(Parcelable parcelable) {
            super(parcelable);
        }

        void v(y yVar) {
            this.f1494d = yVar.f1494d;
        }

        @Override // a.i.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f1494d, 0);
        }
    }

    public static abstract class z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private RecyclerView f1496b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private o f1497c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1498d;
        private boolean e;
        private View f;
        private boolean h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1495a = -1;
        private final a g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private int f1499a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private int f1500b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f1501c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f1502d;
            private Interpolator e;
            private boolean f;
            private int g;

            public a(int i, int i2) {
                this(i, i2, Integer.MIN_VALUE, null);
            }

            public a(int i, int i2, int i3, Interpolator interpolator) {
                this.f1502d = -1;
                this.f = false;
                this.g = 0;
                this.f1499a = i;
                this.f1500b = i2;
                this.f1501c = i3;
                this.e = interpolator;
            }

            private void e() {
                if (this.e != null && this.f1501c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1501c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.f1502d >= 0;
            }

            public void b(int i) {
                this.f1502d = i;
            }

            void c(RecyclerView recyclerView) {
                int i = this.f1502d;
                if (i >= 0) {
                    this.f1502d = -1;
                    recyclerView.x0(i);
                    this.f = false;
                } else {
                    if (!this.f) {
                        this.g = 0;
                        return;
                    }
                    e();
                    recyclerView.l0.f(this.f1499a, this.f1500b, this.f1501c, this.e);
                    int i2 = this.g + 1;
                    this.g = i2;
                    if (i2 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                }
            }

            public void d(int i, int i2, int i3, Interpolator interpolator) {
                this.f1499a = i;
                this.f1500b = i2;
                this.f1501c = i3;
                this.e = interpolator;
                this.f = true;
            }
        }

        public interface b {
            PointF n(int i);
        }

        public PointF a(int i) {
            Object objE = e();
            if (objE instanceof b) {
                return ((b) objE).n(i);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public View b(int i) {
            return this.f1496b.w.Y(i);
        }

        public int c() {
            return this.f1496b.w.f0();
        }

        public int d(View view) {
            return this.f1496b.e0(view);
        }

        public o e() {
            return this.f1497c;
        }

        public int f() {
            return this.f1495a;
        }

        public boolean g() {
            return this.f1498d;
        }

        public boolean h() {
            return this.e;
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
            RecyclerView recyclerView = this.f1496b;
            if (this.f1495a == -1 || recyclerView == null) {
                r();
            }
            if (this.f1498d && this.f == null && this.f1497c != null && (pointFA = a(this.f1495a)) != null) {
                float f = pointFA.x;
                if (f != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.h1((int) Math.signum(f), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f1498d = false;
            View view = this.f;
            if (view != null) {
                if (d(view) == this.f1495a) {
                    o(this.f, recyclerView.o0, this.g);
                    this.g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f = null;
                }
            }
            if (this.e) {
                l(i, i2, recyclerView.o0, this.g);
                boolean zA = this.g.a();
                this.g.c(recyclerView);
                if (zA && this.e) {
                    this.f1498d = true;
                    recyclerView.l0.e();
                }
            }
        }

        protected void k(View view) {
            if (d(view) == f()) {
                this.f = view;
            }
        }

        protected abstract void l(int i, int i2, a0 a0Var, a aVar);

        protected abstract void m();

        protected abstract void n();

        protected abstract void o(View view, a0 a0Var, a aVar);

        public void p(int i) {
            this.f1495a = i;
        }

        void q(RecyclerView recyclerView, o oVar) {
            recyclerView.l0.g();
            if (this.h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1496b = recyclerView;
            this.f1497c = oVar;
            int i = this.f1495a;
            if (i == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.o0.f1444a = i;
            this.e = true;
            this.f1498d = true;
            this.f = b(f());
            m();
            this.f1496b.l0.e();
            this.h = true;
        }

        protected final void r() {
            if (this.e) {
                this.e = false;
                n();
                this.f1496b.o0.f1444a = -1;
                this.f = null;
                this.f1495a = -1;
                this.f1498d = false;
                this.f1497c.z1(this);
                this.f1497c = null;
                this.f1496b = null;
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f1441c = i2 == 18 || i2 == 19 || i2 == 20;
        f1442d = i2 >= 23;
        e = i2 >= 16;
        f = i2 >= 21;
        g = i2 <= 15;
        h = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        i = new Class[]{Context.class, AttributeSet.class, cls, cls};
        j = new c();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.m.a.f493a);
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
        this.l0 = new c0();
        this.n0 = f ? new f.b() : null;
        this.o0 = new a0();
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
        this.i0 = a.g.k.u.b(viewConfiguration, context);
        this.j0 = a.g.k.u.d(viewConfiguration, context);
        this.g0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.h0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.W.v(this.t0);
        p0();
        r0();
        q0();
        if (a.g.k.t.w(this) == 0) {
            a.g.k.t.q0(this, 1);
        }
        this.L = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.l(this));
        int[] iArr = a.m.c.f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(a.m.c.o);
        if (typedArrayObtainStyledAttributes.getInt(a.m.c.i, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.q = typedArrayObtainStyledAttributes.getBoolean(a.m.c.h, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(a.m.c.j, false);
        this.D = z3;
        if (z3) {
            s0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(a.m.c.m), typedArrayObtainStyledAttributes.getDrawable(a.m.c.n), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(a.m.c.k), typedArrayObtainStyledAttributes.getDrawable(a.m.c.l));
        }
        typedArrayObtainStyledAttributes.recycle();
        v(context, string, attributeSet, i2, 0);
        if (i3 >= 21) {
            int[] iArr2 = f1440b;
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i2, 0);
            if (i3 >= 29) {
                saveAttributeDataForStyleable(context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i2, 0);
            }
            z2 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
            typedArrayObtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z2);
    }

    private void A() {
        int i2 = this.J;
        this.J = 0;
        if (i2 == 0 || !u0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        a.g.k.c0.b.b(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private void C() {
        this.o0.a(1);
        Q(this.o0);
        this.o0.j = false;
        r1();
        this.p.f();
        H0();
        P0();
        e1();
        a0 a0Var = this.o0;
        a0Var.i = a0Var.k && this.s0;
        this.s0 = false;
        this.r0 = false;
        a0Var.h = a0Var.l;
        a0Var.f = this.v.c();
        U(this.x0);
        if (this.o0.k) {
            int iG = this.o.g();
            for (int i2 = 0; i2 < iG; i2++) {
                d0 d0VarG0 = g0(this.o.f(i2));
                if (!d0VarG0.J() && (!d0VarG0.t() || this.v.g())) {
                    this.p.e(d0VarG0, this.W.t(this.o0, d0VarG0, l.e(d0VarG0), d0VarG0.o()));
                    if (this.o0.i && d0VarG0.y() && !d0VarG0.v() && !d0VarG0.J() && !d0VarG0.t()) {
                        this.p.c(c0(d0VarG0), d0VarG0);
                    }
                }
            }
        }
        if (this.o0.l) {
            f1();
            a0 a0Var2 = this.o0;
            boolean z2 = a0Var2.g;
            a0Var2.g = false;
            this.w.r1(this.l, a0Var2);
            this.o0.g = z2;
            for (int i3 = 0; i3 < this.o.g(); i3++) {
                d0 d0VarG02 = g0(this.o.f(i3));
                if (!d0VarG02.J() && !this.p.i(d0VarG02)) {
                    int iE = l.e(d0VarG02);
                    boolean zP = d0VarG02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.W.t(this.o0, d0VarG02, iE, d0VarG02.o());
                    if (zP) {
                        S0(d0VarG02, cVarT);
                    } else {
                        this.p.a(d0VarG02, cVarT);
                    }
                }
            }
        }
        s();
        I0();
        t1(false);
        this.o0.e = 2;
    }

    private void D() {
        r1();
        H0();
        this.o0.a(6);
        this.n.j();
        this.o0.f = this.v.c();
        a0 a0Var = this.o0;
        a0Var.f1447d = 0;
        a0Var.h = false;
        this.w.r1(this.l, a0Var);
        a0 a0Var2 = this.o0;
        a0Var2.g = false;
        this.m = null;
        a0Var2.k = a0Var2.k && this.W != null;
        a0Var2.e = 4;
        I0();
        t1(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void E() {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.E():void");
    }

    private boolean K(MotionEvent motionEvent) {
        s sVar = this.A;
        if (sVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return T(motionEvent);
        }
        sVar.b(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.A = null;
        }
        return true;
    }

    private void K0(MotionEvent motionEvent) {
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

    private boolean O0() {
        return this.W != null && this.w.h2();
    }

    private void P0() {
        boolean z2;
        if (this.N) {
            this.n.x();
            if (this.O) {
                this.w.m1(this);
            }
        }
        if (O0()) {
            this.n.v();
        } else {
            this.n.j();
        }
        boolean z3 = false;
        boolean z4 = this.r0 || this.s0;
        this.o0.k = this.E && this.W != null && ((z2 = this.N) || z4 || this.w.h) && (!z2 || this.v.g());
        a0 a0Var = this.o0;
        if (a0Var.k && z4 && !this.N && O0()) {
            z3 = true;
        }
        a0Var.l = z3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void R0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L21
            r6.M()
            android.widget.EdgeEffect r1 = r6.S
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
        L1c:
            androidx.core.widget.d.a(r1, r4, r9)
            r9 = r3
            goto L39
        L21:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L38
            r6.N()
            android.widget.EdgeEffect r1 = r6.U
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            goto L1c
        L38:
            r9 = 0
        L39:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L53
            r6.O()
            android.widget.EdgeEffect r9 = r6.T
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            androidx.core.widget.d.a(r9, r1, r7)
            goto L6f
        L53:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L6e
            r6.L()
            android.widget.EdgeEffect r9 = r6.V
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            androidx.core.widget.d.a(r9, r1, r2)
            goto L6f
        L6e:
            r3 = r9
        L6f:
            if (r3 != 0) goto L79
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 != 0) goto L79
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 == 0) goto L7c
        L79:
            a.g.k.t.Y(r6)
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.R0(float, float, float, float):void");
    }

    private boolean T(MotionEvent motionEvent) {
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

    private void T0() {
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
        View viewW = null;
        d0 d0VarY = (this.o0.n == -1 || !this.v.g()) ? null : Y(this.o0.n);
        if (d0VarY != null && !this.o.n(d0VarY.f1454b) && d0VarY.f1454b.hasFocusable()) {
            viewW = d0VarY.f1454b;
        } else if (this.o.g() > 0) {
            viewW = W();
        }
        if (viewW != null) {
            int i2 = this.o0.o;
            if (i2 != -1 && (viewFindViewById = viewW.findViewById(i2)) != null && viewFindViewById.isFocusable()) {
                viewW = viewFindViewById;
            }
            viewW.requestFocus();
        }
    }

    private void U(int[] iArr) {
        int iG = this.o.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iG; i4++) {
            d0 d0VarG0 = g0(this.o.f(i4));
            if (!d0VarG0.J()) {
                int iM = d0VarG0.m();
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

    private void U0() {
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
            a.g.k.t.Y(this);
        }
    }

    static RecyclerView V(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewV = V(viewGroup.getChildAt(i2));
            if (recyclerViewV != null) {
                return recyclerViewV;
            }
        }
        return null;
    }

    private View W() {
        d0 d0VarX;
        a0 a0Var = this.o0;
        int i2 = a0Var.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int iB = a0Var.b();
        for (int i3 = i2; i3 < iB; i3++) {
            d0 d0VarX2 = X(i3);
            if (d0VarX2 == null) {
                break;
            }
            if (d0VarX2.f1454b.hasFocusable()) {
                return d0VarX2.f1454b;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (d0VarX = X(iMin)) == null) {
                return null;
            }
        } while (!d0VarX.f1454b.hasFocusable());
        return d0VarX.f1454b;
    }

    private void b1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.s.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1482d) {
                Rect rect = pVar.f1481c;
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
        this.w.N1(this, view, this.s, !this.E, view2 == null);
    }

    private void c1() {
        a0 a0Var = this.o0;
        a0Var.n = -1L;
        a0Var.m = -1;
        a0Var.o = -1;
    }

    private void d1() {
        VelocityTracker velocityTracker = this.Z;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        u1(0);
        U0();
    }

    private void e1() {
        View focusedChild = (this.k0 && hasFocus() && this.v != null) ? getFocusedChild() : null;
        d0 d0VarS = focusedChild != null ? S(focusedChild) : null;
        if (d0VarS == null) {
            c1();
            return;
        }
        this.o0.n = this.v.g() ? d0VarS.k() : -1L;
        this.o0.m = this.N ? -1 : d0VarS.v() ? d0VarS.e : d0VarS.j();
        this.o0.o = j0(d0VarS.f1454b);
    }

    private void g(d0 d0Var) {
        View view = d0Var.f1454b;
        boolean z2 = view.getParent() == this;
        this.l.J(f0(view));
        if (d0Var.x()) {
            this.o.c(view, -1, view.getLayoutParams(), true);
            return;
        }
        androidx.recyclerview.widget.b bVar = this.o;
        if (z2) {
            bVar.k(view);
        } else {
            bVar.b(view, true);
        }
    }

    static d0 g0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1480b;
    }

    private a.g.k.k getScrollingChildHelper() {
        if (this.y0 == null) {
            this.y0 = new a.g.k.k(this);
        }
        return this.y0;
    }

    static void i0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1481c;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    private int j0(View view) {
        int id;
        loop0: while (true) {
            id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    break;
                }
            }
        }
        return id;
    }

    private void j1(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.v;
        if (gVar2 != null) {
            gVar2.w(this.k);
            this.v.p(this);
        }
        if (!z2 || z3) {
            V0();
        }
        this.n.x();
        g gVar3 = this.v;
        this.v = gVar;
        if (gVar != null) {
            gVar.u(this.k);
            gVar.l(this);
        }
        o oVar = this.w;
        if (oVar != null) {
            oVar.Y0(gVar3, this.v);
        }
        this.l.x(gVar3, this.v, z2);
        this.o0.g = true;
    }

    private String k0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void m(d0 d0Var, d0 d0Var2, l.c cVar, l.c cVar2, boolean z2, boolean z3) {
        d0Var.G(false);
        if (z2) {
            g(d0Var);
        }
        if (d0Var != d0Var2) {
            if (z3) {
                g(d0Var2);
            }
            d0Var.i = d0Var2;
            g(d0Var);
            this.l.J(d0Var);
            d0Var2.G(false);
            d0Var2.j = d0Var;
        }
        if (this.W.b(d0Var, d0Var2, cVar, cVar2)) {
            N0();
        }
    }

    private void m0(long j2, d0 d0Var, d0 d0Var2) {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            d0 d0VarG0 = g0(this.o.f(i2));
            if (d0VarG0 != d0Var && c0(d0VarG0) == j2) {
                g gVar = this.v;
                if (gVar == null || !gVar.g()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + d0VarG0 + " \n View Holder 2:" + d0Var + P());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + d0VarG0 + " \n View Holder 2:" + d0Var + P());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + d0Var2 + " cannot be found but it is necessary for " + d0Var + P());
    }

    private boolean o0() {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            d0 d0VarG0 = g0(this.o.f(i2));
            if (d0VarG0 != null && !d0VarG0.J() && d0VarG0.y()) {
                return true;
            }
        }
        return false;
    }

    private void q() {
        d1();
        setScrollState(0);
    }

    @SuppressLint({"InlinedApi"})
    private void q0() {
        if (a.g.k.t.x(this) == 0) {
            a.g.k.t.r0(this, 8);
        }
    }

    static void r(d0 d0Var) {
        WeakReference<RecyclerView> weakReference = d0Var.f1455c;
        if (weakReference != null) {
            Object parent = weakReference.get();
            while (true) {
                for (View view = (View) parent; view != null; view = null) {
                    if (view == d0Var.f1454b) {
                        return;
                    }
                    parent = view.getParent();
                    if (parent instanceof View) {
                        break;
                    }
                }
                d0Var.f1455c = null;
                return;
            }
        }
    }

    private void r0() {
        this.o = new androidx.recyclerview.widget.b(new e());
    }

    private void v(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String strK0 = k0(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = Class.forName(strK0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(o.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(i);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strK0, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strK0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strK0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strK0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strK0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strK0, e8);
            }
        }
    }

    private boolean w0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || R(view2) == null) {
            return false;
        }
        if (view == null || R(view) == null) {
            return true;
        }
        this.s.set(0, 0, view.getWidth(), view.getHeight());
        this.t.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.s);
        offsetDescendantRectToMyCoords(view2, this.t);
        byte b2 = -1;
        int i4 = this.w.v0() == 1 ? -1 : 1;
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
        throw new IllegalArgumentException("Invalid direction: " + i2 + P());
    }

    private void w1() {
        this.l0.g();
        o oVar = this.w;
        if (oVar != null) {
            oVar.g2();
        }
    }

    private boolean x(int i2, int i3) {
        U(this.x0);
        int[] iArr = this.x0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    public void A0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetLeftAndRight(i2);
        }
    }

    void B() {
        String str;
        if (this.v == null) {
            str = "No adapter attached; skipping layout";
        } else {
            if (this.w != null) {
                a0 a0Var = this.o0;
                a0Var.j = false;
                if (a0Var.e != 1) {
                    if (!this.n.q() && this.w.G0() == getWidth() && this.w.s0() == getHeight()) {
                        this.w.V1(this);
                    }
                    E();
                    return;
                }
                C();
                this.w.V1(this);
                D();
                E();
                return;
            }
            str = "No layout manager attached; skipping layout";
        }
        Log.e("RecyclerView", str);
    }

    public void B0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetTopAndBottom(i2);
        }
    }

    void C0(int i2, int i3) {
        int iJ = this.o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            d0 d0VarG0 = g0(this.o.i(i4));
            if (d0VarG0 != null && !d0VarG0.J() && d0VarG0.f1456d >= i2) {
                d0VarG0.A(i3, false);
                this.o0.g = true;
            }
        }
        this.l.u(i2, i3);
        requestLayout();
    }

    void D0(int i2, int i3) {
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
            d0 d0VarG0 = g0(this.o.i(i8));
            if (d0VarG0 != null && (i7 = d0VarG0.f1456d) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    d0VarG0.A(i3 - i2, false);
                } else {
                    d0VarG0.A(i6, false);
                }
                this.o0.g = true;
            }
        }
        this.l.v(i2, i3);
        requestLayout();
    }

    void E0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.o.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            d0 d0VarG0 = g0(this.o.i(i5));
            if (d0VarG0 != null && !d0VarG0.J()) {
                int i6 = d0VarG0.f1456d;
                if (i6 >= i4) {
                    d0VarG0.A(-i3, z2);
                } else if (i6 >= i2) {
                    d0VarG0.i(i2 - 1, -i3, z2);
                }
                this.o0.g = true;
            }
        }
        this.l.w(i2, i3, z2);
        requestLayout();
    }

    public boolean F(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    public void F0(View view) {
    }

    public final void G(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void G0(View view) {
    }

    void H(int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.y1(i2);
        }
        L0(i2);
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

    void H0() {
        this.P++;
    }

    void I(int i2, int i3) {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        M0(i2, i3);
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

    void I0() {
        J0(true);
    }

    void J() {
        int i2;
        for (int size = this.C0.size() - 1; size >= 0; size--) {
            d0 d0Var = this.C0.get(size);
            if (d0Var.f1454b.getParent() == this && !d0Var.J() && (i2 = d0Var.r) != -1) {
                a.g.k.t.q0(d0Var.f1454b, i2);
                d0Var.r = -1;
            }
        }
        this.C0.clear();
    }

    void J0(boolean z2) {
        int i2 = this.P - 1;
        this.P = i2;
        if (i2 < 1) {
            this.P = 0;
            if (z2) {
                A();
                J();
            }
        }
    }

    void L() {
        int measuredWidth;
        int measuredHeight;
        if (this.V != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 3);
        this.V = edgeEffectA;
        if (this.q) {
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        edgeEffectA.setSize(measuredWidth, measuredHeight);
    }

    public void L0(int i2) {
    }

    void M() {
        int measuredHeight;
        int measuredWidth;
        if (this.S != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 0);
        this.S = edgeEffectA;
        if (this.q) {
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        edgeEffectA.setSize(measuredHeight, measuredWidth);
    }

    public void M0(int i2, int i3) {
    }

    void N() {
        int measuredHeight;
        int measuredWidth;
        if (this.U != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 2);
        this.U = edgeEffectA;
        if (this.q) {
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        } else {
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
        edgeEffectA.setSize(measuredHeight, measuredWidth);
    }

    void N0() {
        if (this.u0 || !this.B) {
            return;
        }
        a.g.k.t.Z(this, this.D0);
        this.u0 = true;
    }

    void O() {
        int measuredWidth;
        int measuredHeight;
        if (this.T != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 1);
        this.T = edgeEffectA;
        if (this.q) {
            measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        } else {
            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();
        }
        edgeEffectA.setSize(measuredWidth, measuredHeight);
    }

    String P() {
        return " " + super.toString() + ", adapter:" + this.v + ", layout:" + this.w + ", context:" + getContext();
    }

    final void Q(a0 a0Var) {
        if (getScrollState() != 2) {
            a0Var.p = 0;
            a0Var.q = 0;
        } else {
            OverScroller overScroller = this.l0.f1451d;
            a0Var.p = overScroller.getFinalX() - overScroller.getCurrX();
            a0Var.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    void Q0(boolean z2) {
        this.O = z2 | this.O;
        this.N = true;
        z0();
    }

    public View R(View view) {
        Object parent;
        while (true) {
            parent = view.getParent();
            if (parent == null || parent == this || !(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public d0 S(View view) {
        View viewR = R(view);
        if (viewR == null) {
            return null;
        }
        return f0(viewR);
    }

    void S0(d0 d0Var, l.c cVar) {
        d0Var.F(0, 8192);
        if (this.o0.i && d0Var.y() && !d0Var.v() && !d0Var.J()) {
            this.p.c(c0(d0Var), d0Var);
        }
        this.p.e(d0Var, cVar);
    }

    void V0() {
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        o oVar = this.w;
        if (oVar != null) {
            oVar.F1(this.l);
            this.w.G1(this.l);
        }
        this.l.c();
    }

    boolean W0(View view) {
        r1();
        boolean zR = this.o.r(view);
        if (zR) {
            d0 d0VarG0 = g0(view);
            this.l.J(d0VarG0);
            this.l.C(d0VarG0);
        }
        t1(!zR);
        return zR;
    }

    public d0 X(int i2) {
        d0 d0Var = null;
        if (this.N) {
            return null;
        }
        int iJ = this.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            d0 d0VarG0 = g0(this.o.i(i3));
            if (d0VarG0 != null && !d0VarG0.v() && b0(d0VarG0) == i2) {
                if (!this.o.n(d0VarG0.f1454b)) {
                    return d0VarG0;
                }
                d0Var = d0VarG0;
            }
        }
        return d0Var;
    }

    public void X0(n nVar) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.C("Cannot remove item decoration during a scroll  or layout");
        }
        this.y.remove(nVar);
        if (this.y.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        y0();
        requestLayout();
    }

    public d0 Y(long j2) {
        g gVar = this.v;
        d0 d0Var = null;
        if (gVar != null && gVar.g()) {
            int iJ = this.o.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                d0 d0VarG0 = g0(this.o.i(i2));
                if (d0VarG0 != null && !d0VarG0.v() && d0VarG0.k() == j2) {
                    if (!this.o.n(d0VarG0.f1454b)) {
                        return d0VarG0;
                    }
                    d0Var = d0VarG0;
                }
            }
        }
        return d0Var;
    }

    public void Y0(s sVar) {
        this.z.remove(sVar);
        if (this.A == sVar) {
            this.A = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.recyclerview.widget.RecyclerView.d0 Z(int r6, boolean r7) {
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
            androidx.recyclerview.widget.RecyclerView$d0 r3 = g0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1456d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.b r1 = r5.o
            android.view.View r4 = r3.f1454b
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Z(int, boolean):androidx.recyclerview.widget.RecyclerView$d0");
    }

    public void Z0(t tVar) {
        List<t> list = this.q0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    void a(int i2, int i3) {
        if (i2 < 0) {
            M();
            if (this.S.isFinished()) {
                this.S.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            N();
            if (this.U.isFinished()) {
                this.U.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            O();
            if (this.T.isFinished()) {
                this.T.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            L();
            if (this.V.isFinished()) {
                this.V.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        a.g.k.t.Y(this);
    }

    public boolean a0(int i2, int i3) {
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.H) {
            return false;
        }
        boolean zG = oVar.G();
        boolean zH = this.w.H();
        if (!zG || Math.abs(i2) < this.g0) {
            i2 = 0;
        }
        if (!zH || Math.abs(i3) < this.g0) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = zG || zH;
            dispatchNestedFling(f2, f3, z2);
            r rVar = this.f0;
            if (rVar != null && rVar.a(i2, i3)) {
                return true;
            }
            if (z2) {
                int i4 = zG ? 1 : 0;
                if (zH) {
                    i4 |= 2;
                }
                s1(i4, 1);
                int i5 = this.h0;
                int iMax = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.h0;
                this.l0.c(iMax, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    void a1() {
        d0 d0Var;
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.o.f(i2);
            d0 d0VarF0 = f0(viewF);
            if (d0VarF0 != null && (d0Var = d0VarF0.j) != null) {
                View view = d0Var.f1454b;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        o oVar = this.w;
        if (oVar == null || !oVar.Z0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    int b0(d0 d0Var) {
        if (d0Var.p(524) || !d0Var.s()) {
            return -1;
        }
        return this.n.e(d0Var.f1456d);
    }

    long c0(d0 d0Var) {
        return this.v.g() ? d0Var.k() : d0Var.f1456d;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.w.I((p) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.w;
        if (oVar != null && oVar.G()) {
            return this.w.M(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.w;
        if (oVar != null && oVar.G()) {
            return this.w.N(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.w;
        if (oVar != null && oVar.G()) {
            return this.w.O(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.w;
        if (oVar != null && oVar.H()) {
            return this.w.P(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.w;
        if (oVar != null && oVar.H()) {
            return this.w.Q(this.o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.w;
        if (oVar != null && oVar.H()) {
            return this.w.R(this.o0);
        }
        return 0;
    }

    public int d0(View view) {
        d0 d0VarG0 = g0(view);
        if (d0VarG0 != null) {
            return d0VarG0.j();
        }
        return -1;
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
        float paddingRight;
        int paddingBottom;
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
            int paddingBottom2 = this.q ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom2, 0.0f);
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
                paddingRight = (-getWidth()) + getPaddingRight();
                paddingBottom = (-getHeight()) + getPaddingBottom();
            } else {
                paddingRight = -getWidth();
                paddingBottom = -getHeight();
            }
            canvas.translate(paddingRight, paddingBottom);
            EdgeEffect edgeEffect8 = this.V;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.W == null || this.y.size() <= 0 || !this.W.p()) ? z2 : true) {
            a.g.k.t.Y(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public int e0(View view) {
        d0 d0VarG0 = g0(view);
        if (d0VarG0 != null) {
            return d0VarG0.m();
        }
        return -1;
    }

    public d0 f0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return g0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    void f1() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            d0 d0VarG0 = g0(this.o.i(i2));
            if (!d0VarG0.J()) {
                d0VarG0.E();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewD1;
        boolean z2;
        View viewK1 = this.w.k1(view, i2);
        if (viewK1 != null) {
            return viewK1;
        }
        boolean z3 = (this.v == null || this.w == null || v0() || this.H) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.w.H()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (g) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.w.G()) {
                int i4 = (this.w.v0() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (g) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                u();
                if (R(view) == null) {
                    return null;
                }
                r1();
                this.w.d1(view, i2, this.l, this.o0);
                t1(false);
            }
            viewD1 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                u();
                if (R(view) == null) {
                    return null;
                }
                r1();
                viewD1 = this.w.d1(view, i2, this.l, this.o0);
                t1(false);
            } else {
                viewD1 = viewFindNextFocus;
            }
        }
        if (viewD1 == null || viewD1.hasFocusable()) {
            return w0(view, viewD1, i2) ? viewD1 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        b1(viewD1, null);
        return view;
    }

    boolean g1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        u();
        if (this.v != null) {
            int[] iArr = this.B0;
            iArr[0] = 0;
            iArr[1] = 0;
            h1(i2, i3, iArr);
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
        G(i5, i4, i6, i7, this.z0, 0, iArr3);
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
            if (motionEvent != null && !a.g.k.i.a(motionEvent, 8194)) {
                R0(motionEvent.getX(), i10, motionEvent.getY(), i11);
            }
            t(i2, i3);
        }
        if (i5 != 0 || i4 != 0) {
            I(i5, i4);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z2 && i5 == 0 && i4 == 0) ? false : true;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.Z();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + P());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.a0(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + P());
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.b0(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + P());
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
        return oVar != null ? oVar.c0() : super.getBaseline();
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

    public void h0(View view, Rect rect) {
        i0(view, rect);
    }

    void h1(int i2, int i3, int[] iArr) {
        r1();
        H0();
        a.g.g.a.a("RV Scroll");
        Q(this.o0);
        int iR1 = i2 != 0 ? this.w.R1(i2, this.l, this.o0) : 0;
        int iT1 = i3 != 0 ? this.w.T1(i3, this.l, this.o0) : 0;
        a.g.g.a.b();
        a1();
        I0();
        t1(false);
        if (iArr != null) {
            iArr[0] = iR1;
            iArr[1] = iT1;
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(n nVar, int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.C("Cannot add item decoration during a scroll  or layout");
        }
        if (this.y.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.y.add(nVar);
        } else {
            this.y.add(i2, nVar);
        }
        y0();
        requestLayout();
    }

    public void i1(int i2) {
        if (this.H) {
            return;
        }
        v1();
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.S1(i2);
            awakenScrollBars();
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.B;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.H;
    }

    @Override // android.view.View, a.g.k.j
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(s sVar) {
        this.z.add(sVar);
    }

    public void k(t tVar) {
        if (this.q0 == null) {
            this.q0 = new ArrayList();
        }
        this.q0.add(tVar);
    }

    boolean k1(d0 d0Var, int i2) {
        if (!v0()) {
            a.g.k.t.q0(d0Var.f1454b, i2);
            return true;
        }
        d0Var.r = i2;
        this.C0.add(d0Var);
        return false;
    }

    void l(d0 d0Var, l.c cVar, l.c cVar2) {
        d0Var.G(false);
        if (this.W.a(d0Var, cVar, cVar2)) {
            N0();
        }
    }

    Rect l0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1482d) {
            return pVar.f1481c;
        }
        if (this.o0.e() && (pVar.v() || pVar.x())) {
            return pVar.f1481c;
        }
        Rect rect = pVar.f1481c;
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
        pVar.f1482d = false;
        return rect;
    }

    boolean l1(AccessibilityEvent accessibilityEvent) {
        if (!v0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? a.g.k.c0.b.a(accessibilityEvent) : 0;
        this.J |= iA != 0 ? iA : 0;
        return true;
    }

    public void m1(int i2, int i3) {
        n1(i2, i3, null);
    }

    void n(d0 d0Var, l.c cVar, l.c cVar2) {
        g(d0Var);
        d0Var.G(false);
        if (this.W.c(d0Var, cVar, cVar2)) {
            N0();
        }
    }

    public boolean n0() {
        return !this.E || this.N || this.n.p();
    }

    public void n1(int i2, int i3, Interpolator interpolator) {
        o1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    void o(String str) {
        if (v0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + P());
        }
        if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + P()));
        }
    }

    public void o1(int i2, int i3, Interpolator interpolator, int i4) {
        p1(i2, i3, interpolator, i4, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = 0;
        this.B = true;
        this.E = this.E && !isLayoutRequested();
        o oVar = this.w;
        if (oVar != null) {
            oVar.V(this);
        }
        this.u0 = false;
        if (f) {
            ThreadLocal<androidx.recyclerview.widget.f> threadLocal = androidx.recyclerview.widget.f.f1580b;
            androidx.recyclerview.widget.f fVar = threadLocal.get();
            this.m0 = fVar;
            if (fVar == null) {
                this.m0 = new androidx.recyclerview.widget.f();
                Display displayS = a.g.k.t.s(this);
                float f2 = 60.0f;
                if (!isInEditMode() && displayS != null) {
                    float refreshRate = displayS.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                androidx.recyclerview.widget.f fVar2 = this.m0;
                fVar2.f = (long) (1.0E9f / f2);
                threadLocal.set(fVar2);
            }
            this.m0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        androidx.recyclerview.widget.f fVar;
        super.onDetachedFromWindow();
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        v1();
        this.B = false;
        o oVar = this.w;
        if (oVar != null) {
            oVar.W(this, this.l);
        }
        this.C0.clear();
        removeCallbacks(this.D0);
        this.p.j();
        if (!f || (fVar = this.m0) == null) {
            return;
        }
        fVar.j(this);
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

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
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
            boolean r0 = r0.H()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.w
            boolean r3 = r3.G()
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
            boolean r3 = r3.H()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.w
            boolean r3 = r3.G()
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
            r5.g1(r2, r0, r6)
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
        if (T(motionEvent)) {
            q();
            return true;
        }
        o oVar = this.w;
        if (oVar == null) {
            return false;
        }
        boolean zG = oVar.G();
        boolean zH = this.w.H();
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
                u1(1);
            }
            int[] iArr = this.A0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zG;
            if (zH) {
                i2 = (zG ? 1 : 0) | 2;
            }
            s1(i2, 0);
        } else if (actionMasked == 1) {
            this.Z.clear();
            u1(0);
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
                if (!zG || Math.abs(i3) <= this.e0) {
                    z2 = false;
                } else {
                    this.c0 = x3;
                    z2 = true;
                }
                if (zH && Math.abs(i4) > this.e0) {
                    this.d0 = y3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            q();
        } else if (actionMasked == 5) {
            this.Y = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.c0 = x4;
            this.a0 = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.d0 = y4;
            this.b0 = y4;
        } else if (actionMasked == 6) {
            K0(motionEvent);
        }
        return this.X == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        a.g.g.a.a("RV OnLayout");
        B();
        a.g.g.a.b();
        this.E = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        o oVar = this.w;
        if (oVar == null) {
            w(i2, i3);
            return;
        }
        boolean z2 = false;
        if (oVar.K0()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.w.t1(this.l, this.o0, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.v == null) {
                return;
            }
            if (this.o0.e == 1) {
                C();
            }
            this.w.W1(i2, i3);
            this.o0.j = true;
            D();
            this.w.Z1(i2, i3);
            if (this.w.c2()) {
                this.w.W1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.o0.j = true;
                D();
                this.w.Z1(i2, i3);
                return;
            }
            return;
        }
        if (this.C) {
            this.w.t1(this.l, this.o0, i2, i3);
            return;
        }
        if (this.K) {
            r1();
            H0();
            P0();
            I0();
            a0 a0Var = this.o0;
            if (a0Var.l) {
                a0Var.h = true;
            } else {
                this.n.j();
                this.o0.h = false;
            }
            this.K = false;
            t1(false);
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
        r1();
        this.w.t1(this.l, this.o0, i2, i3);
        t1(false);
        this.o0.h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (v0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof y)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        y yVar = (y) parcelable;
        this.m = yVar;
        super.onRestoreInstanceState(yVar.u());
        o oVar = this.w;
        if (oVar == null || (parcelable2 = this.m.f1494d) == null) {
            return;
        }
        oVar.w1(parcelable2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        y yVar = new y(super.onSaveInstanceState());
        y yVar2 = this.m;
        if (yVar2 != null) {
            yVar.v(yVar2);
        } else {
            o oVar = this.w;
            yVar.f1494d = oVar != null ? oVar.x1() : null;
        }
        return yVar;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        t0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00dd A[PHI: r0
  0x00dd: PHI (r0v36 int) = (r0v26 int), (r0v40 int) binds: [B:41:0x00c8, B:46:0x00d9] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    boolean p(d0 d0Var) {
        l lVar = this.W;
        return lVar == null || lVar.g(d0Var, d0Var.o());
    }

    void p0() {
        this.n = new androidx.recyclerview.widget.a(new f());
    }

    void p1(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.H) {
            return;
        }
        if (!oVar.G()) {
            i2 = 0;
        }
        if (!this.w.H()) {
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
            s1(i5, 1);
        }
        this.l0.f(i2, i3, i4, interpolator);
    }

    public void q1(int i2) {
        if (this.H) {
            return;
        }
        o oVar = this.w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.e2(this, this.o0, i2);
        }
    }

    void r1() {
        int i2 = this.F + 1;
        this.F = i2;
        if (i2 != 1 || this.H) {
            return;
        }
        this.G = false;
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z2) {
        d0 d0VarG0 = g0(view);
        if (d0VarG0 != null) {
            if (d0VarG0.x()) {
                d0VarG0.f();
            } else if (!d0VarG0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + d0VarG0 + P());
            }
        }
        view.clearAnimation();
        z(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.w.v1(this, this.o0, view, view2) && view2 != null) {
            b1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.w.M1(this, view, rect, z2);
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

    void s() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            d0 d0VarG0 = g0(this.o.i(i2));
            if (!d0VarG0.J()) {
                d0VarG0.c();
            }
        }
        this.l.d();
    }

    void s0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.e(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(a.m.b.f494a), resources.getDimensionPixelSize(a.m.b.f496c), resources.getDimensionPixelOffset(a.m.b.f495b));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + P());
        }
    }

    public boolean s1(int i2, int i3) {
        return getScrollingChildHelper().p(i2, i3);
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
        boolean zG = oVar.G();
        boolean zH = this.w.H();
        if (zG || zH) {
            if (!zG) {
                i2 = 0;
            }
            if (!zH) {
                i3 = 0;
            }
            g1(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (l1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.l lVar) {
        this.v0 = lVar;
        a.g.k.t.h0(this, lVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        j1(gVar, false, true);
        Q0(false);
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
            t0();
        }
        this.q = z2;
        super.setClipToPadding(z2);
        if (this.E) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        a.g.j.h.c(kVar);
        this.R = kVar;
        t0();
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
        v1();
        if (this.w != null) {
            l lVar = this.W;
            if (lVar != null) {
                lVar.k();
            }
            this.w.F1(this.l);
            this.w.G1(this.l);
            this.l.c();
            if (this.B) {
                this.w.W(this, this.l);
            }
            this.w.a2(null);
            this.w = null;
        } else {
            this.l.c();
        }
        this.o.o();
        this.w = oVar;
        if (oVar != null) {
            if (oVar.f1471b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1471b.P());
            }
            oVar.a2(this);
            if (this.B) {
                this.w.V(this);
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
            w1();
        }
        H(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        int scaledTouchSlop;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 == 0) {
            scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        } else if (i2 != 1) {
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
            scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        } else {
            scaledTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        }
        this.e0 = scaledTouchSlop;
    }

    public void setViewCacheExtension(b0 b0Var) {
        this.l.F(b0Var);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().o(i2);
    }

    @Override // android.view.View, a.g.k.j
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.H) {
            o("Do not suppressLayout in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.H = true;
                this.I = true;
                v1();
                return;
            }
            this.H = false;
            if (this.G && this.w != null && this.v != null) {
                requestLayout();
            }
            this.G = false;
        }
    }

    void t(int i2, int i3) {
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
            a.g.k.t.Y(this);
        }
    }

    void t0() {
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    void t1(boolean z2) {
        if (this.F < 1) {
            this.F = 1;
        }
        if (!z2 && !this.H) {
            this.G = false;
        }
        if (this.F == 1) {
            if (z2 && this.G && !this.H && this.w != null && this.v != null) {
                B();
            }
            if (!this.H) {
                this.G = false;
            }
        }
        this.F--;
    }

    void u() {
        if (!this.E || this.N) {
            a.g.g.a.a("RV FullInvalidate");
            B();
            a.g.g.a.b();
            return;
        }
        if (this.n.p()) {
            if (this.n.o(4) && !this.n.o(11)) {
                a.g.g.a.a("RV PartialInvalidate");
                r1();
                H0();
                this.n.v();
                if (!this.G) {
                    if (o0()) {
                        B();
                    } else {
                        this.n.i();
                    }
                }
                t1(true);
                I0();
            } else {
                if (!this.n.p()) {
                    return;
                }
                a.g.g.a.a("RV FullInvalidate");
                B();
            }
            a.g.g.a.b();
        }
    }

    boolean u0() {
        AccessibilityManager accessibilityManager = this.L;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void u1(int i2) {
        getScrollingChildHelper().r(i2);
    }

    public boolean v0() {
        return this.P > 0;
    }

    public void v1() {
        setScrollState(0);
        w1();
    }

    void w(int i2, int i3) {
        setMeasuredDimension(o.J(i2, getPaddingLeft() + getPaddingRight(), a.g.k.t.A(this)), o.J(i3, getPaddingTop() + getPaddingBottom(), a.g.k.t.z(this)));
    }

    void x0(int i2) {
        if (this.w == null) {
            return;
        }
        setScrollState(2);
        this.w.S1(i2);
        awakenScrollBars();
    }

    void x1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.o.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.o.i(i6);
            d0 d0VarG0 = g0(viewI);
            if (d0VarG0 != null && !d0VarG0.J() && (i4 = d0VarG0.f1456d) >= i2 && i4 < i5) {
                d0VarG0.b(2);
                d0VarG0.a(obj);
                ((p) viewI.getLayoutParams()).f1482d = true;
            }
        }
        this.l.M(i2, i3);
    }

    void y(View view) {
        d0 d0VarG0 = g0(view);
        F0(view);
        g gVar = this.v;
        if (gVar != null && d0VarG0 != null) {
            gVar.r(d0VarG0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).a(view);
            }
        }
    }

    void y0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((p) this.o.i(i2).getLayoutParams()).f1482d = true;
        }
        this.l.s();
    }

    void z(View view) {
        d0 d0VarG0 = g0(view);
        G0(view);
        g gVar = this.v;
        if (gVar != null && d0VarG0 != null) {
            gVar.s(d0VarG0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).b(view);
            }
        }
    }

    void z0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            d0 d0VarG0 = g0(this.o.i(i2));
            if (d0VarG0 != null && !d0VarG0.J()) {
                d0VarG0.b(6);
            }
        }
        y0();
        this.l.t();
    }
}
