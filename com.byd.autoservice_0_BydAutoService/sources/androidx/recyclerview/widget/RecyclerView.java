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
import androidx.recyclerview.R$attr;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$styleable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import m0.c;
import w0.a;
import w0.b;
import w0.e;
import w0.l;
import w0.p;
import w0.q;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements l0.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1412b = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final boolean f1413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1414d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f1415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f1416f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f1417g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f1418h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Class<?>[] f1419i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Interpolator f1420j;
    public s A;
    public final int[] A0;
    public boolean B;
    public final int[] B0;
    public boolean C;
    public final List<c0> C0;
    public boolean D;
    public Runnable D0;
    public boolean E;
    public final q.b E0;
    public int F;
    public boolean G;
    public boolean H;
    public boolean I;
    public int J;
    public boolean K;
    public final AccessibilityManager L;
    public List<q> M;
    public boolean N;
    public boolean O;
    public int P;
    public int Q;
    public k R;
    public EdgeEffect S;
    public EdgeEffect T;
    public EdgeEffect U;
    public EdgeEffect V;
    public l W;
    public int X;
    public int Y;
    public VelocityTracker Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f1421a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f1422b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f1423c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f1424d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f1425e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public r f1426f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final int f1427g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final int f1428h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f1429i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f1430j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final x f1431k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f1432k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final v f1433l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final b0 f1434l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SavedState f1435m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public w0.e f1436m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public w0.a f1437n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public e.b f1438n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public w0.b f1439o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final z f1440o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w0.q f1441p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public t f1442p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1443q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public List<t> f1444q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Runnable f1445r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public boolean f1446r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f1447s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public boolean f1448s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f1449t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public l.b f1450t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final RectF f1451u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f1452u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g f1453v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public w0.l f1454v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public o f1455w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public j f1456w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public w f1457x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final int[] f1458x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ArrayList<n> f1459y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public l0.k f1460y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ArrayList<s> f1461z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final int[] f1462z0;

    public class a implements Runnable {
        public a() {
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

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = RecyclerView.this.W;
            if (lVar != null) {
                lVar.u();
            }
            RecyclerView.this.f1452u0 = false;
        }
    }

    public class b0 implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1466b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1467c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public OverScroller f1468d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Interpolator f1469e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1470f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1471g;

        public b0() {
            Interpolator interpolator = RecyclerView.f1420j;
            this.f1469e = interpolator;
            this.f1470f = false;
            this.f1471g = false;
            this.f1468d = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        public final int a(int i3, int i4, int i5, int i6) {
            int iRound;
            int iAbs = Math.abs(i3);
            int iAbs2 = Math.abs(i4);
            boolean z3 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i5 * i5) + (i6 * i6));
            int iSqrt2 = (int) Math.sqrt((i3 * i3) + (i4 * i4));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z3 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i7 = width / 2;
            float f3 = width;
            float f4 = i7;
            float fB = f4 + (b(Math.min(1.0f, (iSqrt2 * 1.0f) / f3)) * f4);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fB / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z3) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f3) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        public final float b(float f3) {
            return (float) Math.sin((f3 - 0.5f) * 0.47123894f);
        }

        public void c(int i3, int i4) {
            RecyclerView.this.setScrollState(2);
            this.f1467c = 0;
            this.f1466b = 0;
            Interpolator interpolator = this.f1469e;
            Interpolator interpolator2 = RecyclerView.f1420j;
            if (interpolator != interpolator2) {
                this.f1469e = interpolator2;
                this.f1468d = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f1468d.fling(0, 0, i3, i4, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        public final void d() {
            RecyclerView.this.removeCallbacks(this);
            l0.t.b0(RecyclerView.this, this);
        }

        public void e() {
            if (this.f1470f) {
                this.f1471g = true;
            } else {
                d();
            }
        }

        public void f(int i3, int i4, int i5, Interpolator interpolator) {
            if (i5 == Integer.MIN_VALUE) {
                i5 = a(i3, i4, 0, 0);
            }
            int i6 = i5;
            if (interpolator == null) {
                interpolator = RecyclerView.f1420j;
            }
            if (this.f1469e != interpolator) {
                this.f1469e = interpolator;
                this.f1468d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1467c = 0;
            this.f1466b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1468d.startScroll(0, 0, i3, i4, i6);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1468d.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f1468d.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i3;
            int i4;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f1455w == null) {
                g();
                return;
            }
            this.f1471g = false;
            this.f1470f = true;
            recyclerView.v();
            OverScroller overScroller = this.f1468d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i5 = currX - this.f1466b;
                int i6 = currY - this.f1467c;
                this.f1466b = currX;
                this.f1467c = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.B0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.G(i5, i6, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.B0;
                    i5 -= iArr2[0];
                    i6 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.u(i5, i6);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.f1453v != null) {
                    int[] iArr3 = recyclerView3.B0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.g1(i5, i6, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.B0;
                    i4 = iArr4[0];
                    i3 = iArr4[1];
                    i5 -= i4;
                    i6 -= i3;
                    y yVar = recyclerView4.f1455w.f1514g;
                    if (yVar != null && !yVar.g() && yVar.h()) {
                        int iB = RecyclerView.this.f1440o0.b();
                        if (iB == 0) {
                            yVar.r();
                        } else if (yVar.f() >= iB) {
                            yVar.p(iB - 1);
                            yVar.j(i4, i3);
                        } else {
                            yVar.j(i4, i3);
                        }
                    }
                } else {
                    i3 = 0;
                    i4 = 0;
                }
                if (!RecyclerView.this.f1459y.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.B0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.H(i4, i3, i5, i6, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.B0;
                int i7 = i5 - iArr6[0];
                int i8 = i6 - iArr6[1];
                if (i4 != 0 || i3 != 0) {
                    recyclerView6.J(i4, i3);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z3 = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i7 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i8 != 0));
                y yVar2 = RecyclerView.this.f1455w.f1514g;
                if ((yVar2 != null && yVar2.g()) || !z3) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    w0.e eVar = recyclerView7.f1436m0;
                    if (eVar != null) {
                        eVar.f(recyclerView7, i4, i3);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i9 = i7 < 0 ? -currVelocity : i7 > 0 ? currVelocity : 0;
                        if (i8 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i8 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i9, currVelocity);
                    }
                    if (RecyclerView.f1416f) {
                        RecyclerView.this.f1438n0.b();
                    }
                }
            }
            y yVar3 = RecyclerView.this.f1455w.f1514g;
            if (yVar3 != null && yVar3.g()) {
                yVar3.j(0, 0);
            }
            this.f1470f = false;
            if (this.f1471g) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.t1(1);
            }
        }
    }

    public static class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            float f4 = f3 - 1.0f;
            return (f4 * f4 * f4 * f4 * f4) + 1.0f;
        }
    }

    public static abstract class c0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final List<Object> f1473a = Collections.emptyList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f1474b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<RecyclerView> f1475c;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1483k;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public RecyclerView f1491s;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1476d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1477e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1478f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1479g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1480h = -1;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public c0 f1481i = null;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public c0 f1482j = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public List<Object> f1484l = null;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public List<Object> f1485m = null;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1486n = 0;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public v f1487o = null;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f1488p = false;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1489q = 0;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f1490r = -1;

        public c0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1474b = view;
        }

        public void A(int i3, boolean z3) {
            if (this.f1477e == -1) {
                this.f1477e = this.f1476d;
            }
            if (this.f1480h == -1) {
                this.f1480h = this.f1476d;
            }
            if (z3) {
                this.f1480h += i3;
            }
            this.f1476d += i3;
            if (this.f1474b.getLayoutParams() != null) {
                ((p) this.f1474b.getLayoutParams()).f1534c = true;
            }
        }

        public void B(RecyclerView recyclerView) {
            int i3 = this.f1490r;
            if (i3 != -1) {
                this.f1489q = i3;
            } else {
                this.f1489q = l0.t.x(this.f1474b);
            }
            recyclerView.j1(this, 4);
        }

        public void C(RecyclerView recyclerView) {
            recyclerView.j1(this, this.f1489q);
            this.f1489q = 0;
        }

        public void D() {
            this.f1483k = 0;
            this.f1476d = -1;
            this.f1477e = -1;
            this.f1478f = -1L;
            this.f1480h = -1;
            this.f1486n = 0;
            this.f1481i = null;
            this.f1482j = null;
            d();
            this.f1489q = 0;
            this.f1490r = -1;
            RecyclerView.s(this);
        }

        public void E() {
            if (this.f1477e == -1) {
                this.f1477e = this.f1476d;
            }
        }

        public void F(int i3, int i4) {
            this.f1483k = (i3 & i4) | (this.f1483k & (~i4));
        }

        public final void G(boolean z3) {
            int i3 = this.f1486n;
            int i4 = z3 ? i3 - 1 : i3 + 1;
            this.f1486n = i4;
            if (i4 < 0) {
                this.f1486n = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z3 && i4 == 1) {
                this.f1483k |= 16;
            } else if (z3 && i4 == 0) {
                this.f1483k &= -17;
            }
        }

        public void H(v vVar, boolean z3) {
            this.f1487o = vVar;
            this.f1488p = z3;
        }

        public boolean I() {
            return (this.f1483k & 16) != 0;
        }

        public boolean J() {
            return (this.f1483k & 128) != 0;
        }

        public void K() {
            this.f1487o.J(this);
        }

        public boolean L() {
            return (this.f1483k & 32) != 0;
        }

        public void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.f1483k) == 0) {
                g();
                this.f1484l.add(obj);
            }
        }

        public void b(int i3) {
            this.f1483k = i3 | this.f1483k;
        }

        public void c() {
            this.f1477e = -1;
            this.f1480h = -1;
        }

        public void d() {
            List<Object> list = this.f1484l;
            if (list != null) {
                list.clear();
            }
            this.f1483k &= -1025;
        }

        public void e() {
            this.f1483k &= -33;
        }

        public void f() {
            this.f1483k &= -257;
        }

        public final void g() {
            if (this.f1484l == null) {
                ArrayList arrayList = new ArrayList();
                this.f1484l = arrayList;
                this.f1485m = Collections.unmodifiableList(arrayList);
            }
        }

        public boolean h() {
            return (this.f1483k & 16) == 0 && l0.t.M(this.f1474b);
        }

        public void i(int i3, int i4, boolean z3) {
            b(8);
            A(i4, z3);
            this.f1476d = i3;
        }

        public final int j() {
            RecyclerView recyclerView = this.f1491s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.c0(this);
        }

        public final long k() {
            return this.f1478f;
        }

        public final int l() {
            return this.f1479g;
        }

        public final int m() {
            int i3 = this.f1480h;
            return i3 == -1 ? this.f1476d : i3;
        }

        public final int n() {
            return this.f1477e;
        }

        public List<Object> o() {
            if ((this.f1483k & 1024) != 0) {
                return f1473a;
            }
            List<Object> list = this.f1484l;
            return (list == null || list.size() == 0) ? f1473a : this.f1485m;
        }

        public boolean p(int i3) {
            return (i3 & this.f1483k) != 0;
        }

        public boolean q() {
            return (this.f1483k & 512) != 0 || t();
        }

        public boolean r() {
            return (this.f1474b.getParent() == null || this.f1474b.getParent() == this.f1491s) ? false : true;
        }

        public boolean s() {
            return (this.f1483k & 1) != 0;
        }

        public boolean t() {
            return (this.f1483k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1476d + " id=" + this.f1478f + ", oldPos=" + this.f1477e + ", pLpos:" + this.f1480h);
            if (w()) {
                sb.append(" scrap ");
                sb.append(this.f1488p ? "[changeScrap]" : "[attachedScrap]");
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
                sb.append(" not recyclable(" + this.f1486n + ")");
            }
            if (q()) {
                sb.append(" undefined adapter position");
            }
            if (this.f1474b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.f1483k & 16) == 0 && !l0.t.M(this.f1474b);
        }

        public boolean v() {
            return (this.f1483k & 8) != 0;
        }

        public boolean w() {
            return this.f1487o != null;
        }

        public boolean x() {
            return (this.f1483k & 256) != 0;
        }

        public boolean y() {
            return (this.f1483k & 2) != 0;
        }

        public boolean z() {
            return (this.f1483k & 2) != 0;
        }
    }

    public class d implements q.b {
        public d() {
        }

        @Override // w0.q.b
        public void a(c0 c0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f1455w.m1(c0Var.f1474b, recyclerView.f1433l);
        }

        @Override // w0.q.b
        public void b(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.m(c0Var, cVar, cVar2);
        }

        @Override // w0.q.b
        public void c(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.f1433l.J(c0Var);
            RecyclerView.this.o(c0Var, cVar, cVar2);
        }

        @Override // w0.q.b
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

    public class e implements b.InterfaceC0069b {
        public e() {
        }

        @Override // w0.b.InterfaceC0069b
        public View a(int i3) {
            return RecyclerView.this.getChildAt(i3);
        }

        @Override // w0.b.InterfaceC0069b
        public void b(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.B(RecyclerView.this);
            }
        }

        @Override // w0.b.InterfaceC0069b
        public void c(int i3) {
            View childAt = RecyclerView.this.getChildAt(i3);
            if (childAt != null) {
                RecyclerView.this.A(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i3);
        }

        @Override // w0.b.InterfaceC0069b
        public void d() {
            int iK = k();
            for (int i3 = 0; i3 < iK; i3++) {
                View viewA = a(i3);
                RecyclerView.this.A(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // w0.b.InterfaceC0069b
        public c0 e(View view) {
            return RecyclerView.g0(view);
        }

        @Override // w0.b.InterfaceC0069b
        public void f(int i3) {
            c0 c0VarG0;
            View viewA = a(i3);
            if (viewA != null && (c0VarG0 = RecyclerView.g0(viewA)) != null) {
                if (c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i3);
        }

        @Override // w0.b.InterfaceC0069b
        public void g(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.C(RecyclerView.this);
            }
        }

        @Override // w0.b.InterfaceC0069b
        public void h(View view, int i3, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                if (!c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.f();
            }
            RecyclerView.this.attachViewToParent(view, i3, layoutParams);
        }

        @Override // w0.b.InterfaceC0069b
        public void i(View view, int i3) {
            RecyclerView.this.addView(view, i3);
            RecyclerView.this.z(view);
        }

        @Override // w0.b.InterfaceC0069b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // w0.b.InterfaceC0069b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    public class f implements a.InterfaceC0068a {
        public f() {
        }

        @Override // w0.a.InterfaceC0068a
        public void a(int i3, int i4) {
            RecyclerView.this.C0(i3, i4);
            RecyclerView.this.f1446r0 = true;
        }

        @Override // w0.a.InterfaceC0068a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // w0.a.InterfaceC0068a
        public c0 c(int i3) {
            c0 c0VarA0 = RecyclerView.this.a0(i3, true);
            if (c0VarA0 == null || RecyclerView.this.f1439o.n(c0VarA0.f1474b)) {
                return null;
            }
            return c0VarA0;
        }

        @Override // w0.a.InterfaceC0068a
        public void d(int i3, int i4) {
            RecyclerView.this.D0(i3, i4, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f1446r0 = true;
            recyclerView.f1440o0.f1570d += i4;
        }

        @Override // w0.a.InterfaceC0068a
        public void e(int i3, int i4) {
            RecyclerView.this.D0(i3, i4, false);
            RecyclerView.this.f1446r0 = true;
        }

        @Override // w0.a.InterfaceC0068a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // w0.a.InterfaceC0068a
        public void g(int i3, int i4) {
            RecyclerView.this.B0(i3, i4);
            RecyclerView.this.f1446r0 = true;
        }

        @Override // w0.a.InterfaceC0068a
        public void h(int i3, int i4, Object obj) {
            RecyclerView.this.w1(i3, i4, obj);
            RecyclerView.this.f1448s0 = true;
        }

        public void i(a.b bVar) {
            int i3 = bVar.f5525a;
            if (i3 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.f1455w.R0(recyclerView, bVar.f5526b, bVar.f5528d);
                return;
            }
            if (i3 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.f1455w.U0(recyclerView2, bVar.f5526b, bVar.f5528d);
            } else if (i3 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.f1455w.W0(recyclerView3, bVar.f5526b, bVar.f5528d, bVar.f5527c);
            } else {
                if (i3 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.f1455w.T0(recyclerView4, bVar.f5526b, bVar.f5528d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h f1495a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1496b = false;

        public final void a(VH vh, int i3) {
            vh.f1476d = i3;
            if (g()) {
                vh.f1478f = d(i3);
            }
            vh.F(1, 519);
            h0.a.a("RV OnBindView");
            k(vh, i3, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f1474b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1534c = true;
            }
            h0.a.b();
        }

        public final VH b(ViewGroup viewGroup, int i3) {
            try {
                h0.a.a("RV CreateView");
                VH vh = (VH) l(viewGroup, i3);
                if (vh.f1474b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f1479g = i3;
                return vh;
            } finally {
                h0.a.b();
            }
        }

        public abstract int c();

        public long d(int i3) {
            return -1L;
        }

        public int e(int i3) {
            return 0;
        }

        public final boolean f() {
            return this.f1495a.a();
        }

        public final boolean g() {
            return this.f1496b;
        }

        public final void h() {
            this.f1495a.b();
        }

        public void i(RecyclerView recyclerView) {
        }

        public abstract void j(VH vh, int i3);

        public void k(VH vh, int i3, List<Object> list) {
            j(vh, i3);
        }

        public abstract VH l(ViewGroup viewGroup, int i3);

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
            this.f1495a.registerObserver(iVar);
        }

        public void s(boolean z3) {
            if (f()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1496b = z3;
        }

        public void t(i iVar) {
            this.f1495a.unregisterObserver(iVar);
        }
    }

    public static class h extends Observable<i> {
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
        int a(int i3, int i4);
    }

    public static class k {
        public EdgeEffect a(RecyclerView recyclerView, int i3) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b f1497a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1498b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1499c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1500d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1501e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1502f = 250;

        public interface a {
            void a();
        }

        public interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1503a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1504b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1505c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1506d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i3) {
                View view = c0Var.f1474b;
                this.f1503a = view.getLeft();
                this.f1504b = view.getTop();
                this.f1505c = view.getRight();
                this.f1506d = view.getBottom();
                return this;
            }
        }

        public static int e(c0 c0Var) {
            int i3 = c0Var.f1483k & 14;
            if (c0Var.t()) {
                return 4;
            }
            if ((i3 & 4) != 0) {
                return i3;
            }
            int iN = c0Var.n();
            int iJ = c0Var.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i3 : i3 | 2048;
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
            b bVar = this.f1497a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f1498b.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f1498b.get(i3).a();
            }
            this.f1498b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f1499c;
        }

        public long m() {
            return this.f1502f;
        }

        public long n() {
            return this.f1501e;
        }

        public long o() {
            return this.f1500d;
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

        public c t(z zVar, c0 c0Var, int i3, List<Object> list) {
            return q().a(c0Var);
        }

        public abstract void u();

        public void v(b bVar) {
            this.f1497a = bVar;
        }
    }

    public class m implements l.b {
        public m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.f1481i != null && c0Var.f1482j == null) {
                c0Var.f1481i = null;
            }
            c0Var.f1482j = null;
            if (c0Var.I() || RecyclerView.this.V0(c0Var.f1474b) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f1474b, false);
        }
    }

    public static abstract class n {
        @Deprecated
        public void d(Rect rect, int i3, RecyclerView recyclerView) {
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w0.b f1508a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1509b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final p.b f1510c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final p.b f1511d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public w0.p f1512e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public w0.p f1513f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public y f1514g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1515h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1516i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1517j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1518k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1519l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1520m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f1521n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1522o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1523p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1524q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f1525r;

        public class a implements p.b {
            public a() {
            }

            @Override // w0.p.b
            public View a(int i3) {
                return o.this.I(i3);
            }

            @Override // w0.p.b
            public int b() {
                return o.this.o0() - o.this.f0();
            }

            @Override // w0.p.b
            public int c() {
                return o.this.e0();
            }

            @Override // w0.p.b
            public int d(View view) {
                return o.this.T(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // w0.p.b
            public int e(View view) {
                return o.this.Q(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        public class b implements p.b {
            public b() {
            }

            @Override // w0.p.b
            public View a(int i3) {
                return o.this.I(i3);
            }

            @Override // w0.p.b
            public int b() {
                return o.this.W() - o.this.d0();
            }

            @Override // w0.p.b
            public int c() {
                return o.this.g0();
            }

            @Override // w0.p.b
            public int d(View view) {
                return o.this.O(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // w0.p.b
            public int e(View view) {
                return o.this.U(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i3, int i4);
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1528a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1529b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1530c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1531d;
        }

        public o() {
            a aVar = new a();
            this.f1510c = aVar;
            b bVar = new b();
            this.f1511d = bVar;
            this.f1512e = new w0.p(aVar);
            this.f1513f = new w0.p(bVar);
            this.f1515h = false;
            this.f1516i = false;
            this.f1517j = false;
            this.f1518k = true;
            this.f1519l = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
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

        public static d i0(Context context, AttributeSet attributeSet, int i3, int i4) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i3, i4);
            dVar.f1528a = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_orientation, 1);
            dVar.f1529b = typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_spanCount, 1);
            dVar.f1530c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_reverseLayout, false);
            dVar.f1531d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int n(int i3, int i4, int i5) {
            int mode = View.MeasureSpec.getMode(i3);
            int size = View.MeasureSpec.getSize(i3);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i4, i5) : size : Math.min(size, Math.max(i4, i5));
        }

        public static boolean w0(int i3, int i4, int i5) {
            int mode = View.MeasureSpec.getMode(i4);
            int size = View.MeasureSpec.getSize(i4);
            if (i5 > 0 && i3 != i5) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i3;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i3;
            }
            return true;
        }

        public void A(RecyclerView recyclerView, v vVar) {
            this.f1516i = false;
            I0(recyclerView, vVar);
        }

        public void A0(View view, int i3, int i4) {
            p pVar = (p) view.getLayoutParams();
            Rect rectK0 = this.f1509b.k0(view);
            int i5 = i3 + rectK0.left + rectK0.right;
            int i6 = i4 + rectK0.top + rectK0.bottom;
            int iK = K(o0(), p0(), e0() + f0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i5, ((ViewGroup.MarginLayoutParams) pVar).width, k());
            int iK2 = K(W(), X(), g0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i6, ((ViewGroup.MarginLayoutParams) pVar).height, l());
            if (F1(view, iK, iK2, pVar)) {
                view.measure(iK, iK2);
            }
        }

        public void A1(int i3, int i4) {
            this.f1524q = View.MeasureSpec.getSize(i3);
            int mode = View.MeasureSpec.getMode(i3);
            this.f1522o = mode;
            if (mode == 0 && !RecyclerView.f1414d) {
                this.f1524q = 0;
            }
            this.f1525r = View.MeasureSpec.getSize(i4);
            int mode2 = View.MeasureSpec.getMode(i4);
            this.f1523p = mode2;
            if (mode2 != 0 || RecyclerView.f1414d) {
                return;
            }
            this.f1525r = 0;
        }

        public View B(View view) {
            View viewS;
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null || (viewS = recyclerView.S(view)) == null || this.f1508a.n(viewS)) {
                return null;
            }
            return viewS;
        }

        public void B0(int i3, int i4) {
            View viewI = I(i3);
            if (viewI != null) {
                x(i3);
                h(viewI, i4);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i3 + this.f1509b.toString());
            }
        }

        public void B1(int i3, int i4) {
            this.f1509b.setMeasuredDimension(i3, i4);
        }

        public View C(int i3) {
            int iJ = J();
            for (int i4 = 0; i4 < iJ; i4++) {
                View viewI = I(i4);
                c0 c0VarG0 = RecyclerView.g0(viewI);
                if (c0VarG0 != null && c0VarG0.m() == i3 && !c0VarG0.J() && (this.f1509b.f1440o0.e() || !c0VarG0.v())) {
                    return viewI;
                }
            }
            return null;
        }

        public void C0(int i3) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                recyclerView.z0(i3);
            }
        }

        public void C1(Rect rect, int i3, int i4) {
            B1(n(i3, rect.width() + e0() + f0(), c0()), n(i4, rect.height() + g0() + d0(), b0()));
        }

        public abstract p D();

        public void D0(int i3) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                recyclerView.A0(i3);
            }
        }

        public void D1(int i3, int i4) {
            int iJ = J();
            if (iJ == 0) {
                this.f1509b.x(i3, i4);
                return;
            }
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MAX_VALUE;
            int i8 = Integer.MIN_VALUE;
            for (int i9 = 0; i9 < iJ; i9++) {
                View viewI = I(i9);
                Rect rect = this.f1509b.f1447s;
                P(viewI, rect);
                int i10 = rect.left;
                if (i10 < i6) {
                    i6 = i10;
                }
                int i11 = rect.right;
                if (i11 > i5) {
                    i5 = i11;
                }
                int i12 = rect.top;
                if (i12 < i7) {
                    i7 = i12;
                }
                int i13 = rect.bottom;
                if (i13 > i8) {
                    i8 = i13;
                }
            }
            this.f1509b.f1447s.set(i6, i7, i5, i8);
            C1(this.f1509b.f1447s, i3, i4);
        }

        public p E(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void E0(g gVar, g gVar2) {
        }

        public void E1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1509b = null;
                this.f1508a = null;
                this.f1524q = 0;
                this.f1525r = 0;
            } else {
                this.f1509b = recyclerView;
                this.f1508a = recyclerView.f1439o;
                this.f1524q = recyclerView.getWidth();
                this.f1525r = recyclerView.getHeight();
            }
            this.f1522o = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.f1523p = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public p F(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        public boolean F0(RecyclerView recyclerView, ArrayList<View> arrayList, int i3, int i4) {
            return false;
        }

        public boolean F1(View view, int i3, int i4, p pVar) {
            return (!view.isLayoutRequested() && this.f1518k && w0(view.getWidth(), i3, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getHeight(), i4, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public int G() {
            return -1;
        }

        public void G0(RecyclerView recyclerView) {
        }

        public boolean G1() {
            return false;
        }

        public int H(View view) {
            return ((p) view.getLayoutParams()).f1533b.bottom;
        }

        @Deprecated
        public void H0(RecyclerView recyclerView) {
        }

        public boolean H1(View view, int i3, int i4, p pVar) {
            return (this.f1518k && w0(view.getMeasuredWidth(), i3, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getMeasuredHeight(), i4, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public View I(int i3) {
            w0.b bVar = this.f1508a;
            if (bVar != null) {
                return bVar.f(i3);
            }
            return null;
        }

        public void I0(RecyclerView recyclerView, v vVar) {
            H0(recyclerView);
        }

        public void I1(RecyclerView recyclerView, z zVar, int i3) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public int J() {
            w0.b bVar = this.f1508a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public View J0(View view, int i3, v vVar, z zVar) {
            return null;
        }

        public void J1(y yVar) {
            y yVar2 = this.f1514g;
            if (yVar2 != null && yVar != yVar2 && yVar2.h()) {
                this.f1514g.r();
            }
            this.f1514g = yVar;
            yVar.q(this.f1509b, this);
        }

        public void K0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1509b;
            L0(recyclerView.f1433l, recyclerView.f1440o0, accessibilityEvent);
        }

        public void K1() {
            y yVar = this.f1514g;
            if (yVar != null) {
                yVar.r();
            }
        }

        public final int[] L(View view, Rect rect) {
            int[] iArr = new int[2];
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i3 = left - iE0;
            int iMin = Math.min(0, i3);
            int i4 = top - iG0;
            int iMin2 = Math.min(0, i4);
            int i5 = iWidth - iO0;
            int iMax = Math.max(0, i5);
            int iMax2 = Math.max(0, iHeight - iW);
            if (Z() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i3, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i5);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i4, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        public void L0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z3 = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1509b.canScrollVertically(-1) && !this.f1509b.canScrollHorizontally(-1) && !this.f1509b.canScrollHorizontally(1)) {
                z3 = false;
            }
            accessibilityEvent.setScrollable(z3);
            g gVar = this.f1509b.f1453v;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public boolean L1() {
            return false;
        }

        public boolean M() {
            RecyclerView recyclerView = this.f1509b;
            return recyclerView != null && recyclerView.f1443q;
        }

        public void M0(m0.c cVar) {
            RecyclerView recyclerView = this.f1509b;
            N0(recyclerView.f1433l, recyclerView.f1440o0, cVar);
        }

        public int N(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null || recyclerView.f1453v == null || !k()) {
                return 1;
            }
            return this.f1509b.f1453v.c();
        }

        public void N0(v vVar, z zVar, m0.c cVar) {
            if (this.f1509b.canScrollVertically(-1) || this.f1509b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.v0(true);
            }
            if (this.f1509b.canScrollVertically(1) || this.f1509b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.v0(true);
            }
            cVar.d0(c.b.b(k0(vVar, zVar), N(vVar, zVar), v0(vVar, zVar), l0(vVar, zVar)));
        }

        public int O(View view) {
            return view.getBottom() + H(view);
        }

        public void O0(View view, m0.c cVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 == null || c0VarG0.v() || this.f1508a.n(c0VarG0.f1474b)) {
                return;
            }
            RecyclerView recyclerView = this.f1509b;
            P0(recyclerView.f1433l, recyclerView.f1440o0, view, cVar);
        }

        public void P(View view, Rect rect) {
            RecyclerView.h0(view, rect);
        }

        public void P0(v vVar, z zVar, View view, m0.c cVar) {
            cVar.e0(c.C0036c.a(l() ? h0(view) : 0, 1, k() ? h0(view) : 0, 1, false, false));
        }

        public int Q(View view) {
            return view.getLeft() - a0(view);
        }

        public View Q0(View view, int i3) {
            return null;
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1533b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void R0(RecyclerView recyclerView, int i3, int i4) {
        }

        public int S(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1533b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void S0(RecyclerView recyclerView) {
        }

        public int T(View view) {
            return view.getRight() + j0(view);
        }

        public void T0(RecyclerView recyclerView, int i3, int i4, int i5) {
        }

        public int U(View view) {
            return view.getTop() - m0(view);
        }

        public void U0(RecyclerView recyclerView, int i3, int i4) {
        }

        public View V() {
            View focusedChild;
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1508a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void V0(RecyclerView recyclerView, int i3, int i4) {
        }

        public int W() {
            return this.f1525r;
        }

        public void W0(RecyclerView recyclerView, int i3, int i4, Object obj) {
            V0(recyclerView, i3, i4);
        }

        public int X() {
            return this.f1523p;
        }

        public void X0(v vVar, z zVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int Y() {
            RecyclerView recyclerView = this.f1509b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        public void Y0(z zVar) {
        }

        public int Z() {
            return l0.t.z(this.f1509b);
        }

        public void Z0(v vVar, z zVar, int i3, int i4) {
            this.f1509b.x(i3, i4);
        }

        public int a0(View view) {
            return ((p) view.getLayoutParams()).f1533b.left;
        }

        @Deprecated
        public boolean a1(RecyclerView recyclerView, View view, View view2) {
            return x0() || recyclerView.u0();
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            return l0.t.A(this.f1509b);
        }

        public boolean b1(RecyclerView recyclerView, z zVar, View view, View view2) {
            return a1(recyclerView, view, view2);
        }

        public void c(View view, int i3) {
            f(view, i3, true);
        }

        public int c0() {
            return l0.t.B(this.f1509b);
        }

        public void c1(Parcelable parcelable) {
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public Parcelable d1() {
            return null;
        }

        public void e(View view, int i3) {
            f(view, i3, false);
        }

        public int e0() {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e1(int i3) {
        }

        public final void f(View view, int i3, boolean z3) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (z3 || c0VarG0.v()) {
                this.f1509b.f1441p.b(c0VarG0);
            } else {
                this.f1509b.f1441p.p(c0VarG0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarG0.L() || c0VarG0.w()) {
                if (c0VarG0.w()) {
                    c0VarG0.K();
                } else {
                    c0VarG0.e();
                }
                this.f1508a.c(view, i3, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1509b) {
                int iM = this.f1508a.m(view);
                if (i3 == -1) {
                    i3 = this.f1508a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1509b.indexOfChild(view) + this.f1509b.Q());
                }
                if (iM != i3) {
                    this.f1509b.f1455w.B0(iM, i3);
                }
            } else {
                this.f1508a.a(view, i3, false);
                pVar.f1534c = true;
                y yVar = this.f1514g;
                if (yVar != null && yVar.h()) {
                    this.f1514g.k(view);
                }
            }
            if (pVar.f1535d) {
                c0VarG0.f1474b.invalidate();
                pVar.f1535d = false;
            }
        }

        public int f0() {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public void f1(y yVar) {
            if (this.f1514g == yVar) {
                this.f1514g = null;
            }
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0() {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public boolean g1(int i3, Bundle bundle) {
            RecyclerView recyclerView = this.f1509b;
            return h1(recyclerView.f1433l, recyclerView.f1440o0, i3, bundle);
        }

        public void h(View view, int i3) {
            i(view, i3, (p) view.getLayoutParams());
        }

        public int h0(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public boolean h1(v vVar, z zVar, int i3, Bundle bundle) {
            int iW;
            int iO0;
            int i4;
            int i5;
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null) {
                return false;
            }
            if (i3 == 4096) {
                iW = recyclerView.canScrollVertically(1) ? (W() - g0()) - d0() : 0;
                if (this.f1509b.canScrollHorizontally(1)) {
                    iO0 = (o0() - e0()) - f0();
                    i4 = iW;
                    i5 = iO0;
                }
                i4 = iW;
                i5 = 0;
            } else if (i3 != 8192) {
                i5 = 0;
                i4 = 0;
            } else {
                iW = recyclerView.canScrollVertically(-1) ? -((W() - g0()) - d0()) : 0;
                if (this.f1509b.canScrollHorizontally(-1)) {
                    iO0 = -((o0() - e0()) - f0());
                    i4 = iW;
                    i5 = iO0;
                }
                i4 = iW;
                i5 = 0;
            }
            if (i4 == 0 && i5 == 0) {
                return false;
            }
            this.f1509b.o1(i5, i4, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void i(View view, int i3, p pVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.v()) {
                this.f1509b.f1441p.b(c0VarG0);
            } else {
                this.f1509b.f1441p.p(c0VarG0);
            }
            this.f1508a.c(view, i3, pVar, c0VarG0.v());
        }

        public boolean i1(View view, int i3, Bundle bundle) {
            RecyclerView recyclerView = this.f1509b;
            return j1(recyclerView.f1433l, recyclerView.f1440o0, view, i3, bundle);
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.k0(view));
            }
        }

        public int j0(View view) {
            return ((p) view.getLayoutParams()).f1533b.right;
        }

        public boolean j1(v vVar, z zVar, View view, int i3, Bundle bundle) {
            return false;
        }

        public boolean k() {
            return false;
        }

        public int k0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView == null || recyclerView.f1453v == null || !l()) {
                return 1;
            }
            return this.f1509b.f1453v.c();
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

        public void l1(v vVar) {
            int iJ = vVar.j();
            for (int i3 = iJ - 1; i3 >= 0; i3--) {
                View viewN = vVar.n(i3);
                c0 c0VarG0 = RecyclerView.g0(viewN);
                if (!c0VarG0.J()) {
                    c0VarG0.G(false);
                    if (c0VarG0.x()) {
                        this.f1509b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f1509b.W;
                    if (lVar != null) {
                        lVar.j(c0VarG0);
                    }
                    c0VarG0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f1509b.invalidate();
            }
        }

        public boolean m(p pVar) {
            return pVar != null;
        }

        public int m0(View view) {
            return ((p) view.getLayoutParams()).f1533b.top;
        }

        public void m1(View view, v vVar) {
            p1(view);
            vVar.B(view);
        }

        public void n0(View view, boolean z3, Rect rect) {
            Matrix matrix;
            if (z3) {
                Rect rect2 = ((p) view.getLayoutParams()).f1533b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1509b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1509b.f1451u;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void n1(int i3, v vVar) {
            View viewI = I(i3);
            q1(i3);
            vVar.B(viewI);
        }

        public void o(int i3, int i4, z zVar, c cVar) {
        }

        public int o0() {
            return this.f1524q;
        }

        public boolean o1(Runnable runnable) {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void p(int i3, c cVar) {
        }

        public int p0() {
            return this.f1522o;
        }

        public void p1(View view) {
            this.f1508a.p(view);
        }

        public int q(z zVar) {
            return 0;
        }

        public boolean q0() {
            int iJ = J();
            for (int i3 = 0; i3 < iJ; i3++) {
                ViewGroup.LayoutParams layoutParams = I(i3).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void q1(int i3) {
            if (I(i3) != null) {
                this.f1508a.q(i3);
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public boolean r0() {
            return this.f1516i;
        }

        public boolean r1(RecyclerView recyclerView, View view, Rect rect, boolean z3) {
            return s1(recyclerView, view, rect, z3, false);
        }

        public int s(z zVar) {
            return 0;
        }

        public boolean s0() {
            return this.f1517j;
        }

        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z3, boolean z4) {
            int[] iArrL = L(view, rect);
            int i3 = iArrL[0];
            int i4 = iArrL[1];
            if ((z4 && !t0(recyclerView, i3, i4)) || (i3 == 0 && i4 == 0)) {
                return false;
            }
            if (z3) {
                recyclerView.scrollBy(i3, i4);
            } else {
                recyclerView.l1(i3, i4);
            }
            return true;
        }

        public int t(z zVar) {
            return 0;
        }

        public final boolean t0(RecyclerView recyclerView, int i3, int i4) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            Rect rect = this.f1509b.f1447s;
            P(focusedChild, rect);
            return rect.left - i3 < iO0 && rect.right - i3 > iE0 && rect.top - i4 < iW && rect.bottom - i4 > iG0;
        }

        public void t1() {
            RecyclerView recyclerView = this.f1509b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int u(z zVar) {
            return 0;
        }

        public final boolean u0() {
            return this.f1519l;
        }

        public void u1() {
            this.f1515h = true;
        }

        public int v(z zVar) {
            return 0;
        }

        public boolean v0(v vVar, z zVar) {
            return false;
        }

        public final void v1(v vVar, int i3, View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.J()) {
                return;
            }
            if (c0VarG0.t() && !c0VarG0.v() && !this.f1509b.f1453v.g()) {
                q1(i3);
                vVar.C(c0VarG0);
            } else {
                x(i3);
                vVar.D(view);
                this.f1509b.f1441p.k(c0VarG0);
            }
        }

        public void w(v vVar) {
            for (int iJ = J() - 1; iJ >= 0; iJ--) {
                v1(vVar, iJ, I(iJ));
            }
        }

        public int w1(int i3, v vVar, z zVar) {
            return 0;
        }

        public void x(int i3) {
            y(i3, I(i3));
        }

        public boolean x0() {
            y yVar = this.f1514g;
            return yVar != null && yVar.h();
        }

        public void x1(int i3) {
        }

        public final void y(int i3, View view) {
            this.f1508a.d(i3);
        }

        public boolean y0(View view, boolean z3, boolean z4) {
            boolean z5 = this.f1512e.b(view, 24579) && this.f1513f.b(view, 24579);
            return z3 ? z5 : !z5;
        }

        public int y1(int i3, v vVar, z zVar) {
            return 0;
        }

        public void z(RecyclerView recyclerView) {
            this.f1516i = true;
            G0(recyclerView);
        }

        public void z0(View view, int i3, int i4, int i5, int i6) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1533b;
            view.layout(i3 + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i4 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i5 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i6 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
        }

        public void z1(RecyclerView recyclerView) {
            A1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }
    }

    public interface q {
        void a(View view);

        void b(View view);
    }

    public static abstract class r {
        public abstract boolean a(int i3, int i4);
    }

    public interface s {
        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(RecyclerView recyclerView, MotionEvent motionEvent);

        void c(boolean z3);
    }

    public static abstract class t {
        public void a(RecyclerView recyclerView, int i3) {
        }

        public void b(RecyclerView recyclerView, int i3, int i4) {
        }
    }

    public static class u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SparseArray<a> f1536a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1537b = 0;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final ArrayList<c0> f1538a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1539b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public long f1540c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public long f1541d = 0;
        }

        public void a() {
            this.f1537b++;
        }

        public void b() {
            for (int i3 = 0; i3 < this.f1536a.size(); i3++) {
                this.f1536a.valueAt(i3).f1538a.clear();
            }
        }

        public void c() {
            this.f1537b--;
        }

        public void d(int i3, long j3) {
            a aVarG = g(i3);
            aVarG.f1541d = j(aVarG.f1541d, j3);
        }

        public void e(int i3, long j3) {
            a aVarG = g(i3);
            aVarG.f1540c = j(aVarG.f1540c, j3);
        }

        public c0 f(int i3) {
            a aVar = this.f1536a.get(i3);
            if (aVar == null || aVar.f1538a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f1538a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public final a g(int i3) {
            a aVar = this.f1536a.get(i3);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1536a.put(i3, aVar2);
            return aVar2;
        }

        public void h(g gVar, g gVar2, boolean z3) {
            if (gVar != null) {
                c();
            }
            if (!z3 && this.f1537b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f1538a;
            if (this.f1536a.get(iL).f1539b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        public long j(long j3, long j4) {
            return j3 == 0 ? j4 : ((j3 / 4) * 3) + (j4 / 4);
        }

        public boolean k(int i3, long j3, long j4) {
            long j5 = g(i3).f1541d;
            return j5 == 0 || j3 + j5 < j4;
        }

        public boolean l(int i3, long j3, long j4) {
            long j5 = g(i3).f1540c;
            return j5 == 0 || j3 + j5 < j4;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<c0> f1542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<c0> f1543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<c0> f1544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<c0> f1545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1546e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1547f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public u f1548g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public a0 f1549h;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f1542a = arrayList;
            this.f1543b = null;
            this.f1544c = new ArrayList<>();
            this.f1545d = Collections.unmodifiableList(arrayList);
            this.f1546e = 2;
            this.f1547f = 2;
        }

        public void A(int i3) {
            a(this.f1544c.get(i3), true);
            this.f1544c.remove(i3);
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

        public void C(c0 c0Var) {
            boolean z3;
            boolean z4 = true;
            if (c0Var.w() || c0Var.f1474b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f1474b.getParent() != null);
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
            g gVar = RecyclerView.this.f1453v;
            if ((gVar != null && zH && gVar.n(c0Var)) || c0Var.u()) {
                if (this.f1547f <= 0 || c0Var.p(526)) {
                    z3 = false;
                } else {
                    int size = this.f1544c.size();
                    if (size >= this.f1547f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.f1416f && size > 0 && !RecyclerView.this.f1438n0.d(c0Var.f1476d)) {
                        int i3 = size - 1;
                        while (i3 >= 0) {
                            if (!RecyclerView.this.f1438n0.d(this.f1544c.get(i3).f1476d)) {
                                break;
                            } else {
                                i3--;
                            }
                        }
                        size = i3 + 1;
                    }
                    this.f1544c.add(size, c0Var);
                    z3 = true;
                }
                if (z3) {
                    z4 = false;
                } else {
                    a(c0Var, true);
                }
                z = z3;
            } else {
                z4 = false;
            }
            RecyclerView.this.f1441p.q(c0Var);
            if (z || z4 || !zH) {
                return;
            }
            c0Var.f1491s = null;
        }

        public void D(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (!c0VarG0.p(12) && c0VarG0.y() && !RecyclerView.this.q(c0VarG0)) {
                if (this.f1543b == null) {
                    this.f1543b = new ArrayList<>();
                }
                c0VarG0.H(this, true);
                this.f1543b.add(c0VarG0);
                return;
            }
            if (!c0VarG0.t() || c0VarG0.v() || RecyclerView.this.f1453v.g()) {
                c0VarG0.H(this, false);
                this.f1542a.add(c0VarG0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        public void E(u uVar) {
            u uVar2 = this.f1548g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.f1548g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f1548g.a();
        }

        public void F(a0 a0Var) {
        }

        public void G(int i3) {
            this.f1546e = i3;
            K();
        }

        public final boolean H(c0 c0Var, int i3, int i4, long j3) {
            c0Var.f1491s = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j3 != Long.MAX_VALUE && !this.f1548g.k(iL, nanoTime, j3)) {
                return false;
            }
            RecyclerView.this.f1453v.a(c0Var, i3);
            this.f1548g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.f1440o0.e()) {
                return true;
            }
            c0Var.f1480h = i4;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0134 A[PHI: r1 r4
  0x0134: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$c0) = (r1v11 androidx.recyclerview.widget.RecyclerView$c0), (r1v30 androidx.recyclerview.widget.RecyclerView$c0) binds: [B:28:0x005d, B:50:0x00b5] A[DONT_GENERATE, DONT_INLINE]
  0x0134: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:28:0x005d, B:50:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:91:0x01bf  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01db A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01de  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public androidx.recyclerview.widget.RecyclerView.c0 I(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 538
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.I(int, boolean, long):androidx.recyclerview.widget.RecyclerView$c0");
        }

        public void J(c0 c0Var) {
            if (c0Var.f1488p) {
                this.f1543b.remove(c0Var);
            } else {
                this.f1542a.remove(c0Var);
            }
            c0Var.f1487o = null;
            c0Var.f1488p = false;
            c0Var.e();
        }

        public void K() {
            o oVar = RecyclerView.this.f1455w;
            this.f1547f = this.f1546e + (oVar != null ? oVar.f1520m : 0);
            for (int size = this.f1544c.size() - 1; size >= 0 && this.f1544c.size() > this.f1547f; size--) {
                A(size);
            }
        }

        public boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.f1440o0.e();
            }
            int i3 = c0Var.f1476d;
            if (i3 >= 0 && i3 < RecyclerView.this.f1453v.c()) {
                if (RecyclerView.this.f1440o0.e() || RecyclerView.this.f1453v.e(c0Var.f1476d) == c0Var.l()) {
                    return !RecyclerView.this.f1453v.g() || c0Var.k() == RecyclerView.this.f1453v.d(c0Var.f1476d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.Q());
        }

        public void M(int i3, int i4) {
            int i5;
            int i6 = i4 + i3;
            for (int size = this.f1544c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1544c.get(size);
                if (c0Var != null && (i5 = c0Var.f1476d) >= i3 && i5 < i6) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        public void a(c0 c0Var, boolean z3) {
            RecyclerView.s(c0Var);
            View view = c0Var.f1474b;
            w0.l lVar = RecyclerView.this.f1454v0;
            if (lVar != null) {
                l0.a aVarN = lVar.n();
                l0.t.j0(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z3) {
                g(c0Var);
            }
            c0Var.f1491s = null;
            i().i(c0Var);
        }

        public final void b(c0 c0Var) {
            if (RecyclerView.this.t0()) {
                View view = c0Var.f1474b;
                if (l0.t.x(view) == 0) {
                    l0.t.t0(view, 1);
                }
                w0.l lVar = RecyclerView.this.f1454v0;
                if (lVar == null) {
                    return;
                }
                l0.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                l0.t.j0(view, aVarN);
            }
        }

        public void c() {
            this.f1542a.clear();
            z();
        }

        public void d() {
            int size = this.f1544c.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f1544c.get(i3).c();
            }
            int size2 = this.f1542a.size();
            for (int i4 = 0; i4 < size2; i4++) {
                this.f1542a.get(i4).c();
            }
            ArrayList<c0> arrayList = this.f1543b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    this.f1543b.get(i5).c();
                }
            }
        }

        public void e() {
            this.f1542a.clear();
            ArrayList<c0> arrayList = this.f1543b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i3) {
            if (i3 >= 0 && i3 < RecyclerView.this.f1440o0.b()) {
                return !RecyclerView.this.f1440o0.e() ? i3 : RecyclerView.this.f1437n.m(i3);
            }
            throw new IndexOutOfBoundsException("invalid position " + i3 + ". State item count is " + RecyclerView.this.f1440o0.b() + RecyclerView.this.Q());
        }

        public void g(c0 c0Var) {
            w wVar = RecyclerView.this.f1457x;
            if (wVar != null) {
                wVar.a(c0Var);
            }
            g gVar = RecyclerView.this.f1453v;
            if (gVar != null) {
                gVar.q(c0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f1440o0 != null) {
                recyclerView.f1441p.q(c0Var);
            }
        }

        public c0 h(int i3) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f1543b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i4 = 0; i4 < size; i4++) {
                    c0 c0Var = this.f1543b.get(i4);
                    if (!c0Var.L() && c0Var.m() == i3) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.f1453v.g() && (iM = RecyclerView.this.f1437n.m(i3)) > 0 && iM < RecyclerView.this.f1453v.c()) {
                    long jD = RecyclerView.this.f1453v.d(iM);
                    for (int i5 = 0; i5 < size; i5++) {
                        c0 c0Var2 = this.f1543b.get(i5);
                        if (!c0Var2.L() && c0Var2.k() == jD) {
                            c0Var2.b(32);
                            return c0Var2;
                        }
                    }
                }
            }
            return null;
        }

        public u i() {
            if (this.f1548g == null) {
                this.f1548g = new u();
            }
            return this.f1548g;
        }

        public int j() {
            return this.f1542a.size();
        }

        public List<c0> k() {
            return this.f1545d;
        }

        public c0 l(long j3, int i3, boolean z3) {
            for (int size = this.f1542a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1542a.get(size);
                if (c0Var.k() == j3 && !c0Var.L()) {
                    if (i3 == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.f1440o0.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z3) {
                        this.f1542a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f1474b, false);
                        y(c0Var.f1474b);
                    }
                }
            }
            int size2 = this.f1544c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f1544c.get(size2);
                if (c0Var2.k() == j3 && !c0Var2.r()) {
                    if (i3 == c0Var2.l()) {
                        if (!z3) {
                            this.f1544c.remove(size2);
                        }
                        return c0Var2;
                    }
                    if (!z3) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        public c0 m(int i3, boolean z3) {
            View viewE;
            int size = this.f1542a.size();
            for (int i4 = 0; i4 < size; i4++) {
                c0 c0Var = this.f1542a.get(i4);
                if (!c0Var.L() && c0Var.m() == i3 && !c0Var.t() && (RecyclerView.this.f1440o0.f1574h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z3 || (viewE = RecyclerView.this.f1439o.e(i3)) == null) {
                int size2 = this.f1544c.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    c0 c0Var2 = this.f1544c.get(i5);
                    if (!c0Var2.t() && c0Var2.m() == i3 && !c0Var2.r()) {
                        if (!z3) {
                            this.f1544c.remove(i5);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarG0 = RecyclerView.g0(viewE);
            RecyclerView.this.f1439o.s(viewE);
            int iM = RecyclerView.this.f1439o.m(viewE);
            if (iM != -1) {
                RecyclerView.this.f1439o.d(iM);
                D(viewE);
                c0VarG0.b(8224);
                return c0VarG0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarG0 + RecyclerView.this.Q());
        }

        public View n(int i3) {
            return this.f1542a.get(i3).f1474b;
        }

        public View o(int i3) {
            return p(i3, false);
        }

        public View p(int i3, boolean z3) {
            return I(i3, z3, Long.MAX_VALUE).f1474b;
        }

        public final void q(ViewGroup viewGroup, boolean z3) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    q((ViewGroup) childAt, true);
                }
            }
            if (z3) {
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

        public final void r(c0 c0Var) {
            View view = c0Var.f1474b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        public void s() {
            int size = this.f1544c.size();
            for (int i3 = 0; i3 < size; i3++) {
                p pVar = (p) this.f1544c.get(i3).f1474b.getLayoutParams();
                if (pVar != null) {
                    pVar.f1534c = true;
                }
            }
        }

        public void t() {
            int size = this.f1544c.size();
            for (int i3 = 0; i3 < size; i3++) {
                c0 c0Var = this.f1544c.get(i3);
                if (c0Var != null) {
                    c0Var.b(6);
                    c0Var.a(null);
                }
            }
            g gVar = RecyclerView.this.f1453v;
            if (gVar == null || !gVar.g()) {
                z();
            }
        }

        public void u(int i3, int i4) {
            int size = this.f1544c.size();
            for (int i5 = 0; i5 < size; i5++) {
                c0 c0Var = this.f1544c.get(i5);
                if (c0Var != null && c0Var.f1476d >= i3) {
                    c0Var.A(i4, true);
                }
            }
        }

        public void v(int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            if (i3 < i4) {
                i5 = -1;
                i7 = i3;
                i6 = i4;
            } else {
                i5 = 1;
                i6 = i3;
                i7 = i4;
            }
            int size = this.f1544c.size();
            for (int i9 = 0; i9 < size; i9++) {
                c0 c0Var = this.f1544c.get(i9);
                if (c0Var != null && (i8 = c0Var.f1476d) >= i7 && i8 <= i6) {
                    if (i8 == i3) {
                        c0Var.A(i4 - i3, false);
                    } else {
                        c0Var.A(i5, false);
                    }
                }
            }
        }

        public void w(int i3, int i4, boolean z3) {
            int i5 = i3 + i4;
            for (int size = this.f1544c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1544c.get(size);
                if (c0Var != null) {
                    int i6 = c0Var.f1476d;
                    if (i6 >= i5) {
                        c0Var.A(-i4, z3);
                    } else if (i6 >= i3) {
                        c0Var.b(8);
                        A(size);
                    }
                }
            }
        }

        public void x(g gVar, g gVar2, boolean z3) {
            c();
            i().h(gVar, gVar2, z3);
        }

        public void y(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            c0VarG0.f1487o = null;
            c0VarG0.f1488p = false;
            c0VarG0.e();
            C(c0VarG0);
        }

        public void z() {
            for (int size = this.f1544c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1544c.clear();
            if (RecyclerView.f1416f) {
                RecyclerView.this.f1438n0.b();
            }
        }
    }

    public interface w {
        void a(c0 c0Var);
    }

    public class x extends i {
        public x() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            RecyclerView.this.p(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f1440o0.f1573g = true;
            recyclerView.P0(true);
            if (RecyclerView.this.f1437n.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    public static abstract class y {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1553b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public o f1554c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1555d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1556e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f1557f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1559h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1552a = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final a f1558g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1560a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1561b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1562c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1563d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public Interpolator f1564e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public boolean f1565f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public int f1566g;

            public a(int i3, int i4) {
                this(i3, i4, Integer.MIN_VALUE, null);
            }

            public boolean a() {
                return this.f1563d >= 0;
            }

            public void b(int i3) {
                this.f1563d = i3;
            }

            public void c(RecyclerView recyclerView) {
                int i3 = this.f1563d;
                if (i3 >= 0) {
                    this.f1563d = -1;
                    recyclerView.w0(i3);
                    this.f1565f = false;
                } else {
                    if (!this.f1565f) {
                        this.f1566g = 0;
                        return;
                    }
                    e();
                    recyclerView.f1434l0.f(this.f1560a, this.f1561b, this.f1562c, this.f1564e);
                    int i4 = this.f1566g + 1;
                    this.f1566g = i4;
                    if (i4 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f1565f = false;
                }
            }

            public void d(int i3, int i4, int i5, Interpolator interpolator) {
                this.f1560a = i3;
                this.f1561b = i4;
                this.f1562c = i5;
                this.f1564e = interpolator;
                this.f1565f = true;
            }

            public final void e() {
                if (this.f1564e != null && this.f1562c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1562c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public a(int i3, int i4, int i5, Interpolator interpolator) {
                this.f1563d = -1;
                this.f1565f = false;
                this.f1566g = 0;
                this.f1560a = i3;
                this.f1561b = i4;
                this.f1562c = i5;
                this.f1564e = interpolator;
            }
        }

        public interface b {
            PointF a(int i3);
        }

        public PointF a(int i3) {
            Object objE = e();
            if (objE instanceof b) {
                return ((b) objE).a(i3);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public View b(int i3) {
            return this.f1553b.f1455w.C(i3);
        }

        public int c() {
            return this.f1553b.f1455w.J();
        }

        public int d(View view) {
            return this.f1553b.e0(view);
        }

        public o e() {
            return this.f1554c;
        }

        public int f() {
            return this.f1552a;
        }

        public boolean g() {
            return this.f1555d;
        }

        public boolean h() {
            return this.f1556e;
        }

        public void i(PointF pointF) {
            float f3 = pointF.x;
            float f4 = pointF.y;
            float fSqrt = (float) Math.sqrt((f3 * f3) + (f4 * f4));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        public void j(int i3, int i4) {
            PointF pointFA;
            RecyclerView recyclerView = this.f1553b;
            if (this.f1552a == -1 || recyclerView == null) {
                r();
            }
            if (this.f1555d && this.f1557f == null && this.f1554c != null && (pointFA = a(this.f1552a)) != null) {
                float f3 = pointFA.x;
                if (f3 != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.g1((int) Math.signum(f3), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f1555d = false;
            View view = this.f1557f;
            if (view != null) {
                if (d(view) == this.f1552a) {
                    o(this.f1557f, recyclerView.f1440o0, this.f1558g);
                    this.f1558g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f1557f = null;
                }
            }
            if (this.f1556e) {
                l(i3, i4, recyclerView.f1440o0, this.f1558g);
                boolean zA = this.f1558g.a();
                this.f1558g.c(recyclerView);
                if (zA && this.f1556e) {
                    this.f1555d = true;
                    recyclerView.f1434l0.e();
                }
            }
        }

        public void k(View view) {
            if (d(view) == f()) {
                this.f1557f = view;
            }
        }

        public abstract void l(int i3, int i4, z zVar, a aVar);

        public abstract void m();

        public abstract void n();

        public abstract void o(View view, z zVar, a aVar);

        public void p(int i3) {
            this.f1552a = i3;
        }

        public void q(RecyclerView recyclerView, o oVar) {
            recyclerView.f1434l0.g();
            if (this.f1559h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1553b = recyclerView;
            this.f1554c = oVar;
            int i3 = this.f1552a;
            if (i3 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.f1440o0.f1567a = i3;
            this.f1556e = true;
            this.f1555d = true;
            this.f1557f = b(f());
            m();
            this.f1553b.f1434l0.e();
            this.f1559h = true;
        }

        public final void r() {
            if (this.f1556e) {
                this.f1556e = false;
                n();
                this.f1553b.f1440o0.f1567a = -1;
                this.f1557f = null;
                this.f1552a = -1;
                this.f1555d = false;
                this.f1554c.f1(this);
                this.f1554c = null;
                this.f1553b = null;
            }
        }
    }

    public static class z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SparseArray<Object> f1568b;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1579m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public long f1580n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1581o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1582p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1583q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1567a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1569c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1570d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1571e = 1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1572f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1573g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1574h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1575i = false;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1576j = false;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1577k = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1578l = false;

        public void a(int i3) {
            if ((this.f1571e & i3) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i3) + " but it is " + Integer.toBinaryString(this.f1571e));
        }

        public int b() {
            return this.f1574h ? this.f1569c - this.f1570d : this.f1572f;
        }

        public int c() {
            return this.f1567a;
        }

        public boolean d() {
            return this.f1567a != -1;
        }

        public boolean e() {
            return this.f1574h;
        }

        public void f(g gVar) {
            this.f1571e = 1;
            this.f1572f = gVar.c();
            this.f1574h = false;
            this.f1575i = false;
            this.f1576j = false;
        }

        public boolean g() {
            return this.f1578l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1567a + ", mData=" + this.f1568b + ", mItemCount=" + this.f1572f + ", mIsMeasuring=" + this.f1576j + ", mPreviousLayoutItemCount=" + this.f1569c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1570d + ", mStructureChanged=" + this.f1573g + ", mInPreLayout=" + this.f1574h + ", mRunSimpleAnimations=" + this.f1577k + ", mRunPredictiveAnimations=" + this.f1578l + '}';
        }
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        f1413c = i3 == 18 || i3 == 19 || i3 == 20;
        f1414d = i3 >= 23;
        f1415e = i3 >= 16;
        f1416f = i3 >= 21;
        f1417g = i3 <= 15;
        f1418h = i3 <= 15;
        Class<?> cls = Integer.TYPE;
        f1419i = new Class[]{Context.class, AttributeSet.class, cls, cls};
        f1420j = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public static RecyclerView W(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            RecyclerView recyclerViewW = W(viewGroup.getChildAt(i3));
            if (recyclerViewW != null) {
                return recyclerViewW;
            }
        }
        return null;
    }

    public static c0 g0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1532a;
    }

    private l0.k getScrollingChildHelper() {
        if (this.f1460y0 == null) {
            this.f1460y0 = new l0.k(this);
        }
        return this.f1460y0;
    }

    public static void h0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1533b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    public static void s(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f1475c;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f1474b) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f1475c = null;
        }
    }

    public void A(View view) {
        c0 c0VarG0 = g0(view);
        F0(view);
        g gVar = this.f1453v;
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

    public void A0(int i3) {
        int iG = this.f1439o.g();
        for (int i4 = 0; i4 < iG; i4++) {
            this.f1439o.f(i4).offsetTopAndBottom(i3);
        }
    }

    public final void B() {
        int i3 = this.J;
        this.J = 0;
        if (i3 == 0 || !t0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        m0.b.b(accessibilityEventObtain, i3);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    public void B0(int i3, int i4) {
        int iJ = this.f1439o.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            c0 c0VarG0 = g0(this.f1439o.i(i5));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.f1476d >= i3) {
                c0VarG0.A(i4, false);
                this.f1440o0.f1573g = true;
            }
        }
        this.f1433l.u(i3, i4);
        requestLayout();
    }

    public void C() {
        if (this.f1453v == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.f1455w == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        z zVar = this.f1440o0;
        zVar.f1576j = false;
        if (zVar.f1571e == 1) {
            D();
            this.f1455w.z1(this);
            E();
        } else if (!this.f1437n.q() && this.f1455w.o0() == getWidth() && this.f1455w.W() == getHeight()) {
            this.f1455w.z1(this);
        } else {
            this.f1455w.z1(this);
            E();
        }
        F();
    }

    public void C0(int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int iJ = this.f1439o.j();
        if (i3 < i4) {
            i7 = -1;
            i6 = i3;
            i5 = i4;
        } else {
            i5 = i3;
            i6 = i4;
            i7 = 1;
        }
        for (int i9 = 0; i9 < iJ; i9++) {
            c0 c0VarG0 = g0(this.f1439o.i(i9));
            if (c0VarG0 != null && (i8 = c0VarG0.f1476d) >= i6 && i8 <= i5) {
                if (i8 == i3) {
                    c0VarG0.A(i4 - i3, false);
                } else {
                    c0VarG0.A(i7, false);
                }
                this.f1440o0.f1573g = true;
            }
        }
        this.f1433l.v(i3, i4);
        requestLayout();
    }

    public final void D() {
        this.f1440o0.a(1);
        R(this.f1440o0);
        this.f1440o0.f1576j = false;
        q1();
        this.f1441p.f();
        G0();
        O0();
        d1();
        z zVar = this.f1440o0;
        zVar.f1575i = zVar.f1577k && this.f1448s0;
        this.f1448s0 = false;
        this.f1446r0 = false;
        zVar.f1574h = zVar.f1578l;
        zVar.f1572f = this.f1453v.c();
        V(this.f1458x0);
        if (this.f1440o0.f1577k) {
            int iG = this.f1439o.g();
            for (int i3 = 0; i3 < iG; i3++) {
                c0 c0VarG0 = g0(this.f1439o.f(i3));
                if (!c0VarG0.J() && (!c0VarG0.t() || this.f1453v.g())) {
                    this.f1441p.e(c0VarG0, this.W.t(this.f1440o0, c0VarG0, l.e(c0VarG0), c0VarG0.o()));
                    if (this.f1440o0.f1575i && c0VarG0.y() && !c0VarG0.v() && !c0VarG0.J() && !c0VarG0.t()) {
                        this.f1441p.c(d0(c0VarG0), c0VarG0);
                    }
                }
            }
        }
        if (this.f1440o0.f1578l) {
            e1();
            z zVar2 = this.f1440o0;
            boolean z3 = zVar2.f1573g;
            zVar2.f1573g = false;
            this.f1455w.X0(this.f1433l, zVar2);
            this.f1440o0.f1573g = z3;
            for (int i4 = 0; i4 < this.f1439o.g(); i4++) {
                c0 c0VarG02 = g0(this.f1439o.f(i4));
                if (!c0VarG02.J() && !this.f1441p.i(c0VarG02)) {
                    int iE = l.e(c0VarG02);
                    boolean zP = c0VarG02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.W.t(this.f1440o0, c0VarG02, iE, c0VarG02.o());
                    if (zP) {
                        R0(c0VarG02, cVarT);
                    } else {
                        this.f1441p.a(c0VarG02, cVarT);
                    }
                }
            }
            t();
        } else {
            t();
        }
        H0();
        s1(false);
        this.f1440o0.f1571e = 2;
    }

    public void D0(int i3, int i4, boolean z3) {
        int i5 = i3 + i4;
        int iJ = this.f1439o.j();
        for (int i6 = 0; i6 < iJ; i6++) {
            c0 c0VarG0 = g0(this.f1439o.i(i6));
            if (c0VarG0 != null && !c0VarG0.J()) {
                int i7 = c0VarG0.f1476d;
                if (i7 >= i5) {
                    c0VarG0.A(-i4, z3);
                    this.f1440o0.f1573g = true;
                } else if (i7 >= i3) {
                    c0VarG0.i(i3 - 1, -i4, z3);
                    this.f1440o0.f1573g = true;
                }
            }
        }
        this.f1433l.w(i3, i4, z3);
        requestLayout();
    }

    public final void E() {
        q1();
        G0();
        this.f1440o0.a(6);
        this.f1437n.j();
        this.f1440o0.f1572f = this.f1453v.c();
        z zVar = this.f1440o0;
        zVar.f1570d = 0;
        zVar.f1574h = false;
        this.f1455w.X0(this.f1433l, zVar);
        z zVar2 = this.f1440o0;
        zVar2.f1573g = false;
        this.f1435m = null;
        zVar2.f1577k = zVar2.f1577k && this.W != null;
        zVar2.f1571e = 4;
        H0();
        s1(false);
    }

    public void E0(View view) {
    }

    public final void F() {
        this.f1440o0.a(4);
        q1();
        G0();
        z zVar = this.f1440o0;
        zVar.f1571e = 1;
        if (zVar.f1577k) {
            for (int iG = this.f1439o.g() - 1; iG >= 0; iG--) {
                c0 c0VarG0 = g0(this.f1439o.f(iG));
                if (!c0VarG0.J()) {
                    long jD0 = d0(c0VarG0);
                    l.c cVarS = this.W.s(this.f1440o0, c0VarG0);
                    c0 c0VarG = this.f1441p.g(jD0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.f1441p.d(c0VarG0, cVarS);
                    } else {
                        boolean zH = this.f1441p.h(c0VarG);
                        boolean zH2 = this.f1441p.h(c0VarG0);
                        if (zH && c0VarG == c0VarG0) {
                            this.f1441p.d(c0VarG0, cVarS);
                        } else {
                            l.c cVarN = this.f1441p.n(c0VarG);
                            this.f1441p.d(c0VarG0, cVarS);
                            l.c cVarM = this.f1441p.m(c0VarG0);
                            if (cVarN == null) {
                                l0(jD0, c0VarG0, c0VarG);
                            } else {
                                n(c0VarG, c0VarG0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.f1441p.o(this.E0);
        }
        this.f1455w.l1(this.f1433l);
        z zVar2 = this.f1440o0;
        zVar2.f1569c = zVar2.f1572f;
        this.N = false;
        this.O = false;
        zVar2.f1577k = false;
        zVar2.f1578l = false;
        this.f1455w.f1515h = false;
        ArrayList<c0> arrayList = this.f1433l.f1543b;
        if (arrayList != null) {
            arrayList.clear();
        }
        o oVar = this.f1455w;
        if (oVar.f1521n) {
            oVar.f1520m = 0;
            oVar.f1521n = false;
            this.f1433l.K();
        }
        this.f1455w.Y0(this.f1440o0);
        H0();
        s1(false);
        this.f1441p.f();
        int[] iArr = this.f1458x0;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        S0();
        b1();
    }

    public void F0(View view) {
    }

    public boolean G(int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        return getScrollingChildHelper().d(i3, i4, iArr, iArr2, i5);
    }

    public void G0() {
        this.P++;
    }

    public final void H(int i3, int i4, int i5, int i6, int[] iArr, int i7, int[] iArr2) {
        getScrollingChildHelper().e(i3, i4, i5, i6, iArr, i7, iArr2);
    }

    public void H0() {
        I0(true);
    }

    public void I(int i3) {
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.e1(i3);
        }
        K0(i3);
        t tVar = this.f1442p0;
        if (tVar != null) {
            tVar.a(this, i3);
        }
        List<t> list = this.f1444q0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f1444q0.get(size).a(this, i3);
            }
        }
    }

    public void I0(boolean z3) {
        int i3 = this.P - 1;
        this.P = i3;
        if (i3 < 1) {
            this.P = 0;
            if (z3) {
                B();
                K();
            }
        }
    }

    public void J(int i3, int i4) {
        this.Q++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i3, scrollY - i4);
        L0(i3, i4);
        t tVar = this.f1442p0;
        if (tVar != null) {
            tVar.b(this, i3, i4);
        }
        List<t> list = this.f1444q0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f1444q0.get(size).b(this, i3, i4);
            }
        }
        this.Q--;
    }

    public final void J0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.Y) {
            int i3 = actionIndex == 0 ? 1 : 0;
            this.Y = motionEvent.getPointerId(i3);
            int x3 = (int) (motionEvent.getX(i3) + 0.5f);
            this.f1423c0 = x3;
            this.f1421a0 = x3;
            int y3 = (int) (motionEvent.getY(i3) + 0.5f);
            this.f1424d0 = y3;
            this.f1422b0 = y3;
        }
    }

    public void K() {
        int i3;
        for (int size = this.C0.size() - 1; size >= 0; size--) {
            c0 c0Var = this.C0.get(size);
            if (c0Var.f1474b.getParent() == this && !c0Var.J() && (i3 = c0Var.f1490r) != -1) {
                l0.t.t0(c0Var.f1474b, i3);
                c0Var.f1490r = -1;
            }
        }
        this.C0.clear();
    }

    public void K0(int i3) {
    }

    public final boolean L(MotionEvent motionEvent) {
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

    public void L0(int i3, int i4) {
    }

    public void M() {
        if (this.V != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 3);
        this.V = edgeEffectA;
        if (this.f1443q) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void M0() {
        if (this.f1452u0 || !this.B) {
            return;
        }
        l0.t.b0(this, this.D0);
        this.f1452u0 = true;
    }

    public void N() {
        if (this.S != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 0);
        this.S = edgeEffectA;
        if (this.f1443q) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final boolean N0() {
        return this.W != null && this.f1455w.L1();
    }

    public void O() {
        if (this.U != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 2);
        this.U = edgeEffectA;
        if (this.f1443q) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void O0() {
        boolean z3;
        if (this.N) {
            this.f1437n.u();
            if (this.O) {
                this.f1455w.S0(this);
            }
        }
        if (N0()) {
            this.f1437n.s();
        } else {
            this.f1437n.j();
        }
        boolean z4 = false;
        boolean z5 = this.f1446r0 || this.f1448s0;
        this.f1440o0.f1577k = this.E && this.W != null && ((z3 = this.N) || z5 || this.f1455w.f1515h) && (!z3 || this.f1453v.g());
        z zVar = this.f1440o0;
        if (zVar.f1577k && z5 && !this.N && N0()) {
            z4 = true;
        }
        zVar.f1578l = z4;
    }

    public void P() {
        if (this.T != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.R.a(this, 1);
        this.T = edgeEffectA;
        if (this.f1443q) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void P0(boolean z3) {
        this.O = z3 | this.O;
        this.N = true;
        y0();
    }

    public String Q() {
        return " " + super.toString() + ", adapter:" + this.f1453v + ", layout:" + this.f1455w + ", context:" + getContext();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Q0(float r7, float r8, float r9, float r10) {
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
            n0.d.a(r1, r4, r9)
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
            n0.d.a(r1, r4, r9)
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
            n0.d.a(r9, r1, r7)
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
            n0.d.a(r9, r1, r2)
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
            l0.t.a0(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Q0(float, float, float, float):void");
    }

    public final void R(z zVar) {
        if (getScrollState() != 2) {
            zVar.f1582p = 0;
            zVar.f1583q = 0;
        } else {
            OverScroller overScroller = this.f1434l0.f1468d;
            zVar.f1582p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.f1583q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public void R0(c0 c0Var, l.c cVar) {
        c0Var.F(0, 8192);
        if (this.f1440o0.f1575i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.f1441p.c(d0(c0Var), c0Var);
        }
        this.f1441p.e(c0Var, cVar);
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

    public final void S0() {
        View viewFindViewById;
        if (!this.f1432k0 || this.f1453v == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!f1418h || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.f1439o.n(focusedChild)) {
                    return;
                }
            } else if (this.f1439o.g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewX = null;
        c0 c0VarZ = (this.f1440o0.f1580n == -1 || !this.f1453v.g()) ? null : Z(this.f1440o0.f1580n);
        if (c0VarZ != null && !this.f1439o.n(c0VarZ.f1474b) && c0VarZ.f1474b.hasFocusable()) {
            viewX = c0VarZ.f1474b;
        } else if (this.f1439o.g() > 0) {
            viewX = X();
        }
        if (viewX != null) {
            int i3 = this.f1440o0.f1581o;
            if (i3 != -1 && (viewFindViewById = viewX.findViewById(i3)) != null && viewFindViewById.isFocusable()) {
                viewX = viewFindViewById;
            }
            viewX.requestFocus();
        }
    }

    public c0 T(View view) {
        View viewS = S(view);
        if (viewS == null) {
            return null;
        }
        return f0(viewS);
    }

    public final void T0() {
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
            l0.t.a0(this);
        }
    }

    public final boolean U(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.f1461z.size();
        for (int i3 = 0; i3 < size; i3++) {
            s sVar = this.f1461z.get(i3);
            if (sVar.a(this, motionEvent) && action != 3) {
                this.A = sVar;
                return true;
            }
        }
        return false;
    }

    public void U0() {
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.k1(this.f1433l);
            this.f1455w.l1(this.f1433l);
        }
        this.f1433l.c();
    }

    public final void V(int[] iArr) {
        int iG = this.f1439o.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (int i5 = 0; i5 < iG; i5++) {
            c0 c0VarG0 = g0(this.f1439o.f(i5));
            if (!c0VarG0.J()) {
                int iM = c0VarG0.m();
                if (iM < i3) {
                    i3 = iM;
                }
                if (iM > i4) {
                    i4 = iM;
                }
            }
        }
        iArr[0] = i3;
        iArr[1] = i4;
    }

    public boolean V0(View view) {
        q1();
        boolean zR = this.f1439o.r(view);
        if (zR) {
            c0 c0VarG0 = g0(view);
            this.f1433l.J(c0VarG0);
            this.f1433l.C(c0VarG0);
        }
        s1(!zR);
        return zR;
    }

    public void W0(n nVar) {
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.f1459y.remove(nVar);
        if (this.f1459y.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        x0();
        requestLayout();
    }

    public final View X() {
        c0 c0VarY;
        z zVar = this.f1440o0;
        int i3 = zVar.f1579m;
        if (i3 == -1) {
            i3 = 0;
        }
        int iB = zVar.b();
        for (int i4 = i3; i4 < iB; i4++) {
            c0 c0VarY2 = Y(i4);
            if (c0VarY2 == null) {
                break;
            }
            if (c0VarY2.f1474b.hasFocusable()) {
                return c0VarY2.f1474b;
            }
        }
        int iMin = Math.min(iB, i3);
        do {
            iMin--;
            if (iMin < 0 || (c0VarY = Y(iMin)) == null) {
                return null;
            }
        } while (!c0VarY.f1474b.hasFocusable());
        return c0VarY.f1474b;
    }

    public void X0(s sVar) {
        this.f1461z.remove(sVar);
        if (this.A == sVar) {
            this.A = null;
        }
    }

    public c0 Y(int i3) {
        c0 c0Var = null;
        if (this.N) {
            return null;
        }
        int iJ = this.f1439o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            c0 c0VarG0 = g0(this.f1439o.i(i4));
            if (c0VarG0 != null && !c0VarG0.v() && c0(c0VarG0) == i3) {
                if (!this.f1439o.n(c0VarG0.f1474b)) {
                    return c0VarG0;
                }
                c0Var = c0VarG0;
            }
        }
        return c0Var;
    }

    public void Y0(t tVar) {
        List<t> list = this.f1444q0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    public c0 Z(long j3) {
        g gVar = this.f1453v;
        c0 c0Var = null;
        if (gVar != null && gVar.g()) {
            int iJ = this.f1439o.j();
            for (int i3 = 0; i3 < iJ; i3++) {
                c0 c0VarG0 = g0(this.f1439o.i(i3));
                if (c0VarG0 != null && !c0VarG0.v() && c0VarG0.k() == j3) {
                    if (!this.f1439o.n(c0VarG0.f1474b)) {
                        return c0VarG0;
                    }
                    c0Var = c0VarG0;
                }
            }
        }
        return c0Var;
    }

    public void Z0() {
        c0 c0Var;
        int iG = this.f1439o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            View viewF = this.f1439o.f(i3);
            c0 c0VarF0 = f0(viewF);
            if (c0VarF0 != null && (c0Var = c0VarF0.f1482j) != null) {
                View view = c0Var.f1474b;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public void a(int i3, int i4) {
        if (i3 < 0) {
            N();
            if (this.S.isFinished()) {
                this.S.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            O();
            if (this.U.isFinished()) {
                this.U.onAbsorb(i3);
            }
        }
        if (i4 < 0) {
            P();
            if (this.T.isFinished()) {
                this.T.onAbsorb(-i4);
            }
        } else if (i4 > 0) {
            M();
            if (this.V.isFinished()) {
                this.V.onAbsorb(i4);
            }
        }
        if (i3 == 0 && i4 == 0) {
            return;
        }
        l0.t.a0(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.recyclerview.widget.RecyclerView.c0 a0(int r6, boolean r7) {
        /*
            r5 = this;
            w0.b r0 = r5.f1439o
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            w0.b r3 = r5.f1439o
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$c0 r3 = g0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1476d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            w0.b r1 = r5.f1439o
            android.view.View r4 = r3.f1474b
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

    public final void a1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.f1447s.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1534c) {
                Rect rect = pVar.f1533b;
                Rect rect2 = this.f1447s;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.f1447s);
            offsetRectIntoDescendantCoords(view, this.f1447s);
        }
        this.f1455w.s1(this, view, this.f1447s, !this.E, view2 == null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i3, int i4) {
        o oVar = this.f1455w;
        if (oVar == null || !oVar.F0(this, arrayList, i3, i4)) {
            super.addFocusables(arrayList, i3, i4);
        }
    }

    public boolean b0(int i3, int i4) {
        o oVar = this.f1455w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.H) {
            return false;
        }
        boolean zK = oVar.k();
        boolean zL = this.f1455w.l();
        if (!zK || Math.abs(i3) < this.f1427g0) {
            i3 = 0;
        }
        if (!zL || Math.abs(i4) < this.f1427g0) {
            i4 = 0;
        }
        if (i3 == 0 && i4 == 0) {
            return false;
        }
        float f3 = i3;
        float f4 = i4;
        if (!dispatchNestedPreFling(f3, f4)) {
            boolean z3 = zK || zL;
            dispatchNestedFling(f3, f4, z3);
            r rVar = this.f1426f0;
            if (rVar != null && rVar.a(i3, i4)) {
                return true;
            }
            if (z3) {
                int i5 = zK ? 1 : 0;
                if (zL) {
                    i5 |= 2;
                }
                r1(i5, 1);
                int i6 = this.f1428h0;
                int iMax = Math.max(-i6, Math.min(i3, i6));
                int i7 = this.f1428h0;
                this.f1434l0.c(iMax, Math.max(-i7, Math.min(i4, i7)));
                return true;
            }
        }
        return false;
    }

    public final void b1() {
        z zVar = this.f1440o0;
        zVar.f1580n = -1L;
        zVar.f1579m = -1;
        zVar.f1581o = -1;
    }

    public int c0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.f1437n.e(c0Var.f1476d);
    }

    public final void c1() {
        VelocityTracker velocityTracker = this.Z;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        t1(0);
        T0();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.f1455w.m((p) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.k()) {
            return this.f1455w.q(this.f1440o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.k()) {
            return this.f1455w.r(this.f1440o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.k()) {
            return this.f1455w.s(this.f1440o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.l()) {
            return this.f1455w.t(this.f1440o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.l()) {
            return this.f1455w.u(this.f1440o0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.f1455w;
        if (oVar != null && oVar.l()) {
            return this.f1455w.v(this.f1440o0);
        }
        return 0;
    }

    public long d0(c0 c0Var) {
        return this.f1453v.g() ? c0Var.k() : c0Var.f1476d;
    }

    public final void d1() {
        View focusedChild = (this.f1432k0 && hasFocus() && this.f1453v != null) ? getFocusedChild() : null;
        c0 c0VarT = focusedChild != null ? T(focusedChild) : null;
        if (c0VarT == null) {
            b1();
            return;
        }
        this.f1440o0.f1580n = this.f1453v.g() ? c0VarT.k() : -1L;
        this.f1440o0.f1579m = this.N ? -1 : c0VarT.v() ? c0VarT.f1477e : c0VarT.j();
        this.f1440o0.f1581o = i0(c0VarT.f1474b);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f3, float f4, boolean z3) {
        return getScrollingChildHelper().a(f3, f4, z3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f3, float f4) {
        return getScrollingChildHelper().b(f3, f4);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i3, int i4, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i3, i4, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i3, int i4, int i5, int i6, int[] iArr) {
        return getScrollingChildHelper().f(i3, i4, i5, i6, iArr);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z3;
        super.draw(canvas);
        int size = this.f1459y.size();
        boolean z4 = false;
        for (int i3 = 0; i3 < size; i3++) {
            this.f1459y.get(i3).i(canvas, this, this.f1440o0);
        }
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z3 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.f1443q ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.S;
            z3 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.f1443q) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.T;
            z3 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.U;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f1443q ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.U;
            z3 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.V;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f1443q) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.V;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z4 = true;
            }
            z3 |= z4;
            canvas.restoreToCount(iSave4);
        }
        if ((z3 || this.W == null || this.f1459y.size() <= 0 || !this.W.p()) ? z3 : true) {
            l0.t.a0(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j3) {
        return super.drawChild(canvas, view, j3);
    }

    public int e0(View view) {
        c0 c0VarG0 = g0(view);
        if (c0VarG0 != null) {
            return c0VarG0.m();
        }
        return -1;
    }

    public void e1() {
        int iJ = this.f1439o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarG0 = g0(this.f1439o.i(i3));
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

    public boolean f1(int i3, int i4, MotionEvent motionEvent) {
        int i5;
        int i6;
        int i7;
        int i8;
        v();
        if (this.f1453v != null) {
            int[] iArr = this.B0;
            iArr[0] = 0;
            iArr[1] = 0;
            g1(i3, i4, iArr);
            int[] iArr2 = this.B0;
            int i9 = iArr2[0];
            int i10 = iArr2[1];
            i5 = i10;
            i6 = i9;
            i7 = i3 - i9;
            i8 = i4 - i10;
        } else {
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
        }
        if (!this.f1459y.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.B0;
        iArr3[0] = 0;
        iArr3[1] = 0;
        H(i6, i5, i7, i8, this.f1462z0, 0, iArr3);
        int[] iArr4 = this.B0;
        int i11 = i7 - iArr4[0];
        int i12 = i8 - iArr4[1];
        boolean z3 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i13 = this.f1423c0;
        int[] iArr5 = this.f1462z0;
        this.f1423c0 = i13 - iArr5[0];
        this.f1424d0 -= iArr5[1];
        int[] iArr6 = this.A0;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !l0.i.a(motionEvent, 8194)) {
                Q0(motionEvent.getX(), i11, motionEvent.getY(), i12);
            }
            u(i3, i4);
        }
        if (i6 != 0 || i5 != 0) {
            J(i6, i5);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z3 && i6 == 0 && i5 == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i3) {
        View viewJ0;
        boolean z3;
        View viewQ0 = this.f1455w.Q0(view, i3);
        if (viewQ0 != null) {
            return viewQ0;
        }
        boolean z4 = (this.f1453v == null || this.f1455w == null || u0() || this.H) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z4 && (i3 == 2 || i3 == 1)) {
            if (this.f1455w.l()) {
                int i4 = i3 == 2 ? 130 : 33;
                z3 = focusFinder.findNextFocus(this, view, i4) == null;
                if (f1417g) {
                    i3 = i4;
                }
            } else {
                z3 = false;
            }
            if (!z3 && this.f1455w.k()) {
                int i5 = (this.f1455w.Z() == 1) ^ (i3 == 2) ? 66 : 17;
                boolean z5 = focusFinder.findNextFocus(this, view, i5) == null;
                if (f1417g) {
                    i3 = i5;
                }
                z3 = z5;
            }
            if (z3) {
                v();
                if (S(view) == null) {
                    return null;
                }
                q1();
                this.f1455w.J0(view, i3, this.f1433l, this.f1440o0);
                s1(false);
            }
            viewJ0 = focusFinder.findNextFocus(this, view, i3);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i3);
            if (viewFindNextFocus == null && z4) {
                v();
                if (S(view) == null) {
                    return null;
                }
                q1();
                viewJ0 = this.f1455w.J0(view, i3, this.f1433l, this.f1440o0);
                s1(false);
            } else {
                viewJ0 = viewFindNextFocus;
            }
        }
        if (viewJ0 == null || viewJ0.hasFocusable()) {
            return v0(view, viewJ0, i3) ? viewJ0 : super.focusSearch(view, i3);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i3);
        }
        a1(viewJ0, null);
        return view;
    }

    public final void g(c0 c0Var) {
        View view = c0Var.f1474b;
        boolean z3 = view.getParent() == this;
        this.f1433l.J(f0(view));
        if (c0Var.x()) {
            this.f1439o.c(view, -1, view.getLayoutParams(), true);
        } else if (z3) {
            this.f1439o.k(view);
        } else {
            this.f1439o.b(view, true);
        }
    }

    public void g1(int i3, int i4, int[] iArr) {
        q1();
        G0();
        h0.a.a("RV Scroll");
        R(this.f1440o0);
        int iW1 = i3 != 0 ? this.f1455w.w1(i3, this.f1433l, this.f1440o0) : 0;
        int iY1 = i4 != 0 ? this.f1455w.y1(i4, this.f1433l, this.f1440o0) : 0;
        h0.a.b();
        Z0();
        H0();
        s1(false);
        if (iArr != null) {
            iArr[0] = iW1;
            iArr[1] = iY1;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.f1455w;
        if (oVar != null) {
            return oVar.D();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.f1455w;
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
        return this.f1453v;
    }

    @Override // android.view.View
    public int getBaseline() {
        o oVar = this.f1455w;
        return oVar != null ? oVar.G() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i3, int i4) {
        j jVar = this.f1456w0;
        return jVar == null ? super.getChildDrawingOrder(i3, i4) : jVar.a(i3, i4);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f1443q;
    }

    public w0.l getCompatAccessibilityDelegate() {
        return this.f1454v0;
    }

    public k getEdgeEffectFactory() {
        return this.R;
    }

    public l getItemAnimator() {
        return this.W;
    }

    public int getItemDecorationCount() {
        return this.f1459y.size();
    }

    public o getLayoutManager() {
        return this.f1455w;
    }

    public int getMaxFlingVelocity() {
        return this.f1428h0;
    }

    public int getMinFlingVelocity() {
        return this.f1427g0;
    }

    public long getNanoTime() {
        if (f1416f) {
            return System.nanoTime();
        }
        return 0L;
    }

    public r getOnFlingListener() {
        return this.f1426f0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f1432k0;
    }

    public u getRecycledViewPool() {
        return this.f1433l.i();
    }

    public int getScrollState() {
        return this.X;
    }

    public void h(n nVar) {
        i(nVar, -1);
    }

    public void h1(int i3) {
        if (this.H) {
            return;
        }
        u1();
        o oVar = this.f1455w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.x1(i3);
            awakenScrollBars();
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(n nVar, int i3) {
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.g("Cannot add item decoration during a scroll  or layout");
        }
        if (this.f1459y.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i3 < 0) {
            this.f1459y.add(nVar);
        } else {
            this.f1459y.add(i3, nVar);
        }
        x0();
        requestLayout();
    }

    public final int i0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public final void i1(g gVar, boolean z3, boolean z4) {
        g gVar2 = this.f1453v;
        if (gVar2 != null) {
            gVar2.t(this.f1431k);
            this.f1453v.m(this);
        }
        if (!z3 || z4) {
            U0();
        }
        this.f1437n.u();
        g gVar3 = this.f1453v;
        this.f1453v = gVar;
        if (gVar != null) {
            gVar.r(this.f1431k);
            gVar.i(this);
        }
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.E0(gVar3, this.f1453v);
        }
        this.f1433l.x(gVar3, this.f1453v, z3);
        this.f1440o0.f1573g = true;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.B;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.H;
    }

    @Override // android.view.View, l0.j
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(q qVar) {
        if (this.M == null) {
            this.M = new ArrayList();
        }
        this.M.add(qVar);
    }

    public final String j0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    public boolean j1(c0 c0Var, int i3) {
        if (!u0()) {
            l0.t.t0(c0Var.f1474b, i3);
            return true;
        }
        c0Var.f1490r = i3;
        this.C0.add(c0Var);
        return false;
    }

    public void k(s sVar) {
        this.f1461z.add(sVar);
    }

    public Rect k0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1534c) {
            return pVar.f1533b;
        }
        if (this.f1440o0.e() && (pVar.b() || pVar.d())) {
            return pVar.f1533b;
        }
        Rect rect = pVar.f1533b;
        rect.set(0, 0, 0, 0);
        int size = this.f1459y.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f1447s.set(0, 0, 0, 0);
            this.f1459y.get(i3).e(this.f1447s, view, this, this.f1440o0);
            int i4 = rect.left;
            Rect rect2 = this.f1447s;
            rect.left = i4 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        pVar.f1534c = false;
        return rect;
    }

    public boolean k1(AccessibilityEvent accessibilityEvent) {
        if (!u0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? m0.b.a(accessibilityEvent) : 0;
        this.J |= iA != 0 ? iA : 0;
        return true;
    }

    public void l(t tVar) {
        if (this.f1444q0 == null) {
            this.f1444q0 = new ArrayList();
        }
        this.f1444q0.add(tVar);
    }

    public final void l0(long j3, c0 c0Var, c0 c0Var2) {
        int iG = this.f1439o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            c0 c0VarG0 = g0(this.f1439o.f(i3));
            if (c0VarG0 != c0Var && d0(c0VarG0) == j3) {
                g gVar = this.f1453v;
                if (gVar == null || !gVar.g()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + Q());
    }

    public void l1(int i3, int i4) {
        m1(i3, i4, null);
    }

    public void m(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.W.a(c0Var, cVar, cVar2)) {
            M0();
        }
    }

    public boolean m0() {
        return !this.E || this.N || this.f1437n.p();
    }

    public void m1(int i3, int i4, Interpolator interpolator) {
        n1(i3, i4, interpolator, Integer.MIN_VALUE);
    }

    public final void n(c0 c0Var, c0 c0Var2, l.c cVar, l.c cVar2, boolean z3, boolean z4) {
        c0Var.G(false);
        if (z3) {
            g(c0Var);
        }
        if (c0Var != c0Var2) {
            if (z4) {
                g(c0Var2);
            }
            c0Var.f1481i = c0Var2;
            g(c0Var);
            this.f1433l.J(c0Var);
            c0Var2.G(false);
            c0Var2.f1482j = c0Var;
        }
        if (this.W.b(c0Var, c0Var2, cVar, cVar2)) {
            M0();
        }
    }

    public final boolean n0() {
        int iG = this.f1439o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            c0 c0VarG0 = g0(this.f1439o.f(i3));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.y()) {
                return true;
            }
        }
        return false;
    }

    public void n1(int i3, int i4, Interpolator interpolator, int i5) {
        o1(i3, i4, interpolator, i5, false);
    }

    public void o(c0 c0Var, l.c cVar, l.c cVar2) {
        g(c0Var);
        c0Var.G(false);
        if (this.W.c(c0Var, cVar, cVar2)) {
            M0();
        }
    }

    public void o0() {
        this.f1437n = new w0.a(new f());
    }

    public void o1(int i3, int i4, Interpolator interpolator, int i5, boolean z3) {
        o oVar = this.f1455w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.H) {
            return;
        }
        if (!oVar.k()) {
            i3 = 0;
        }
        if (!this.f1455w.l()) {
            i4 = 0;
        }
        if (i3 == 0 && i4 == 0) {
            return;
        }
        if (!(i5 == Integer.MIN_VALUE || i5 > 0)) {
            scrollBy(i3, i4);
            return;
        }
        if (z3) {
            int i6 = i3 != 0 ? 1 : 0;
            if (i4 != 0) {
                i6 |= 2;
            }
            r1(i6, 1);
        }
        this.f1434l0.f(i3, i4, i5, interpolator);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = 0;
        this.B = true;
        this.E = this.E && !isLayoutRequested();
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.z(this);
        }
        this.f1452u0 = false;
        if (f1416f) {
            ThreadLocal<w0.e> threadLocal = w0.e.f5616b;
            w0.e eVar = threadLocal.get();
            this.f1436m0 = eVar;
            if (eVar == null) {
                this.f1436m0 = new w0.e();
                Display displayT = l0.t.t(this);
                float f3 = 60.0f;
                if (!isInEditMode() && displayT != null) {
                    float refreshRate = displayT.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f3 = refreshRate;
                    }
                }
                w0.e eVar2 = this.f1436m0;
                eVar2.f5620f = (long) (1.0E9f / f3);
                threadLocal.set(eVar2);
            }
            this.f1436m0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        w0.e eVar;
        super.onDetachedFromWindow();
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        u1();
        this.B = false;
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.A(this, this.f1433l);
        }
        this.C0.clear();
        removeCallbacks(this.D0);
        this.f1441p.j();
        if (!f1416f || (eVar = this.f1436m0) == null) {
            return;
        }
        eVar.j(this);
        this.f1436m0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.f1459y.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f1459y.get(i3).g(canvas, this, this.f1440o0);
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.f1455w
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.f1455w
            boolean r0 = r0.l()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f1455w
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
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f1455w
            boolean r3 = r3.l()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f1455w
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
            float r2 = r5.f1429i0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.f1430j0
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
        boolean z3;
        if (this.H) {
            return false;
        }
        this.A = null;
        if (U(motionEvent)) {
            r();
            return true;
        }
        o oVar = this.f1455w;
        if (oVar == null) {
            return false;
        }
        boolean zK = oVar.k();
        boolean zL = this.f1455w.l();
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
            int x3 = (int) (motionEvent.getX() + 0.5f);
            this.f1423c0 = x3;
            this.f1421a0 = x3;
            int y3 = (int) (motionEvent.getY() + 0.5f);
            this.f1424d0 = y3;
            this.f1422b0 = y3;
            if (this.X == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                t1(1);
            }
            int[] iArr = this.A0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i3 = zK;
            if (zL) {
                i3 = (zK ? 1 : 0) | 2;
            }
            r1(i3, 0);
        } else if (actionMasked == 1) {
            this.Z.clear();
            t1(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.Y);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.Y + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x4 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y4 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.X != 1) {
                int i4 = x4 - this.f1421a0;
                int i5 = y4 - this.f1422b0;
                if (!zK || Math.abs(i4) <= this.f1425e0) {
                    z3 = false;
                } else {
                    this.f1423c0 = x4;
                    z3 = true;
                }
                if (zL && Math.abs(i5) > this.f1425e0) {
                    this.f1424d0 = y4;
                    z3 = true;
                }
                if (z3) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            r();
        } else if (actionMasked == 5) {
            this.Y = motionEvent.getPointerId(actionIndex);
            int x5 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.f1423c0 = x5;
            this.f1421a0 = x5;
            int y5 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.f1424d0 = y5;
            this.f1422b0 = y5;
        } else if (actionMasked == 6) {
            J0(motionEvent);
        }
        return this.X == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        h0.a.a("RV OnLayout");
        C();
        h0.a.b();
        this.E = true;
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        o oVar = this.f1455w;
        if (oVar == null) {
            x(i3, i4);
            return;
        }
        boolean z3 = false;
        if (oVar.s0()) {
            int mode = View.MeasureSpec.getMode(i3);
            int mode2 = View.MeasureSpec.getMode(i4);
            this.f1455w.Z0(this.f1433l, this.f1440o0, i3, i4);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z3 = true;
            }
            if (z3 || this.f1453v == null) {
                return;
            }
            if (this.f1440o0.f1571e == 1) {
                D();
            }
            this.f1455w.A1(i3, i4);
            this.f1440o0.f1576j = true;
            E();
            this.f1455w.D1(i3, i4);
            if (this.f1455w.G1()) {
                this.f1455w.A1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.f1440o0.f1576j = true;
                E();
                this.f1455w.D1(i3, i4);
                return;
            }
            return;
        }
        if (this.C) {
            this.f1455w.Z0(this.f1433l, this.f1440o0, i3, i4);
            return;
        }
        if (this.K) {
            q1();
            G0();
            O0();
            H0();
            z zVar = this.f1440o0;
            if (zVar.f1578l) {
                zVar.f1574h = true;
            } else {
                this.f1437n.j();
                this.f1440o0.f1574h = false;
            }
            this.K = false;
            s1(false);
        } else if (this.f1440o0.f1578l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.f1453v;
        if (gVar != null) {
            this.f1440o0.f1572f = gVar.c();
        } else {
            this.f1440o0.f1572f = 0;
        }
        q1();
        this.f1455w.Z0(this.f1433l, this.f1440o0, i3, i4);
        s1(false);
        this.f1440o0.f1574h = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i3, Rect rect) {
        if (u0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i3, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.f1435m = savedState;
        super.onRestoreInstanceState(savedState.f());
        o oVar = this.f1455w;
        if (oVar == null || (parcelable2 = this.f1435m.f1463d) == null) {
            return;
        }
        oVar.c1(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.f1435m;
        if (savedState2 != null) {
            savedState.g(savedState2);
        } else {
            o oVar = this.f1455w;
            if (oVar != null) {
                savedState.f1463d = oVar.d1();
            } else {
                savedState.f1463d = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        if (i3 == i5 && i4 == i6) {
            return;
        }
        s0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00df A[PHI: r0
  0x00df: PHI (r0v36 int) = (r0v26 int), (r0v40 int) binds: [B:41:0x00c8, B:45:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f8  */
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

    public void p(String str) {
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

    @SuppressLint({"InlinedApi"})
    public final void p0() {
        if (l0.t.y(this) == 0) {
            l0.t.u0(this, 8);
        }
    }

    public void p1(int i3) {
        if (this.H) {
            return;
        }
        o oVar = this.f1455w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.I1(this, this.f1440o0, i3);
        }
    }

    public boolean q(c0 c0Var) {
        l lVar = this.W;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    public final void q0() {
        this.f1439o = new w0.b(new e());
    }

    public void q1() {
        int i3 = this.F + 1;
        this.F = i3;
        if (i3 != 1 || this.H) {
            return;
        }
        this.G = false;
    }

    public final void r() {
        c1();
        setScrollState(0);
    }

    public void r0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new w0.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(R$dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(R$dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(R$dimen.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
        }
    }

    public boolean r1(int i3, int i4) {
        return getScrollingChildHelper().p(i3, i4);
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z3) {
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
        super.removeDetachedView(view, z3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.f1455w.b1(this, this.f1440o0, view, view2) && view2 != null) {
            a1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z3) {
        return this.f1455w.r1(this, view, rect, z3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z3) {
        int size = this.f1461z.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f1461z.get(i3).c(z3);
        }
        super.requestDisallowInterceptTouchEvent(z3);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.F != 0 || this.H) {
            this.G = true;
        } else {
            super.requestLayout();
        }
    }

    public void s0() {
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    public void s1(boolean z3) {
        if (this.F < 1) {
            this.F = 1;
        }
        if (!z3 && !this.H) {
            this.G = false;
        }
        if (this.F == 1) {
            if (z3 && this.G && !this.H && this.f1455w != null && this.f1453v != null) {
                C();
            }
            if (!this.H) {
                this.G = false;
            }
        }
        this.F--;
    }

    @Override // android.view.View
    public void scrollBy(int i3, int i4) {
        o oVar = this.f1455w;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.H) {
            return;
        }
        boolean zK = oVar.k();
        boolean zL = this.f1455w.l();
        if (zK || zL) {
            if (!zK) {
                i3 = 0;
            }
            if (!zL) {
                i4 = 0;
            }
            f1(i3, i4, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i3, int i4) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (k1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(w0.l lVar) {
        this.f1454v0 = lVar;
        l0.t.j0(this, lVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        i1(gVar, false, true);
        P0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.f1456w0) {
            return;
        }
        this.f1456w0 = jVar;
        setChildrenDrawingOrderEnabled(jVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z3) {
        if (z3 != this.f1443q) {
            s0();
        }
        this.f1443q = z3;
        super.setClipToPadding(z3);
        if (this.E) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        k0.h.c(kVar);
        this.R = kVar;
        s0();
    }

    public void setHasFixedSize(boolean z3) {
        this.C = z3;
    }

    public void setItemAnimator(l lVar) {
        l lVar2 = this.W;
        if (lVar2 != null) {
            lVar2.k();
            this.W.v(null);
        }
        this.W = lVar;
        if (lVar != null) {
            lVar.v(this.f1450t0);
        }
    }

    public void setItemViewCacheSize(int i3) {
        this.f1433l.G(i3);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z3) {
        suppressLayout(z3);
    }

    public void setLayoutManager(o oVar) {
        if (oVar == this.f1455w) {
            return;
        }
        u1();
        if (this.f1455w != null) {
            l lVar = this.W;
            if (lVar != null) {
                lVar.k();
            }
            this.f1455w.k1(this.f1433l);
            this.f1455w.l1(this.f1433l);
            this.f1433l.c();
            if (this.B) {
                this.f1455w.A(this, this.f1433l);
            }
            this.f1455w.E1(null);
            this.f1455w = null;
        } else {
            this.f1433l.c();
        }
        this.f1439o.o();
        this.f1455w = oVar;
        if (oVar != null) {
            if (oVar.f1509b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1509b.Q());
            }
            oVar.E1(this);
            if (this.B) {
                this.f1455w.z(this);
            }
        }
        this.f1433l.K();
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
    public void setNestedScrollingEnabled(boolean z3) {
        getScrollingChildHelper().m(z3);
    }

    public void setOnFlingListener(r rVar) {
        this.f1426f0 = rVar;
    }

    @Deprecated
    public void setOnScrollListener(t tVar) {
        this.f1442p0 = tVar;
    }

    public void setPreserveFocusAfterLayout(boolean z3) {
        this.f1432k0 = z3;
    }

    public void setRecycledViewPool(u uVar) {
        this.f1433l.E(uVar);
    }

    public void setRecyclerListener(w wVar) {
        this.f1457x = wVar;
    }

    public void setScrollState(int i3) {
        if (i3 == this.X) {
            return;
        }
        this.X = i3;
        if (i3 != 2) {
            v1();
        }
        I(i3);
    }

    public void setScrollingTouchSlop(int i3) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i3 != 0) {
            if (i3 == 1) {
                this.f1425e0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i3 + "; using default value");
        }
        this.f1425e0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(a0 a0Var) {
        this.f1433l.F(a0Var);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i3) {
        return getScrollingChildHelper().o(i3);
    }

    @Override // android.view.View, l0.j
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z3) {
        if (z3 != this.H) {
            p("Do not suppressLayout in layout or scroll");
            if (z3) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.H = true;
                this.I = true;
                u1();
                return;
            }
            this.H = false;
            if (this.G && this.f1455w != null && this.f1453v != null) {
                requestLayout();
            }
            this.G = false;
        }
    }

    public void t() {
        int iJ = this.f1439o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarG0 = g0(this.f1439o.i(i3));
            if (!c0VarG0.J()) {
                c0VarG0.c();
            }
        }
        this.f1433l.d();
    }

    public boolean t0() {
        AccessibilityManager accessibilityManager = this.L;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void t1(int i3) {
        getScrollingChildHelper().r(i3);
    }

    public void u(int i3, int i4) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.S;
        if (edgeEffect == null || edgeEffect.isFinished() || i3 <= 0) {
            zIsFinished = false;
        } else {
            this.S.onRelease();
            zIsFinished = this.S.isFinished();
        }
        EdgeEffect edgeEffect2 = this.U;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i3 < 0) {
            this.U.onRelease();
            zIsFinished |= this.U.isFinished();
        }
        EdgeEffect edgeEffect3 = this.T;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i4 > 0) {
            this.T.onRelease();
            zIsFinished |= this.T.isFinished();
        }
        EdgeEffect edgeEffect4 = this.V;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i4 < 0) {
            this.V.onRelease();
            zIsFinished |= this.V.isFinished();
        }
        if (zIsFinished) {
            l0.t.a0(this);
        }
    }

    public boolean u0() {
        return this.P > 0;
    }

    public void u1() {
        setScrollState(0);
        v1();
    }

    public void v() {
        if (!this.E || this.N) {
            h0.a.a("RV FullInvalidate");
            C();
            h0.a.b();
            return;
        }
        if (this.f1437n.p()) {
            if (!this.f1437n.o(4) || this.f1437n.o(11)) {
                if (this.f1437n.p()) {
                    h0.a.a("RV FullInvalidate");
                    C();
                    h0.a.b();
                    return;
                }
                return;
            }
            h0.a.a("RV PartialInvalidate");
            q1();
            G0();
            this.f1437n.s();
            if (!this.G) {
                if (n0()) {
                    C();
                } else {
                    this.f1437n.i();
                }
            }
            s1(true);
            H0();
            h0.a.b();
        }
    }

    public final boolean v0(View view, View view2, int i3) {
        int i4;
        if (view2 == null || view2 == this || S(view2) == null) {
            return false;
        }
        if (view == null || S(view) == null) {
            return true;
        }
        this.f1447s.set(0, 0, view.getWidth(), view.getHeight());
        this.f1449t.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.f1447s);
        offsetDescendantRectToMyCoords(view2, this.f1449t);
        byte b3 = -1;
        int i5 = this.f1455w.Z() == 1 ? -1 : 1;
        Rect rect = this.f1447s;
        int i6 = rect.left;
        Rect rect2 = this.f1449t;
        int i7 = rect2.left;
        if ((i6 < i7 || rect.right <= i7) && rect.right < rect2.right) {
            i4 = 1;
        } else {
            int i8 = rect.right;
            int i9 = rect2.right;
            i4 = ((i8 > i9 || i6 >= i9) && i6 > i7) ? -1 : 0;
        }
        int i10 = rect.top;
        int i11 = rect2.top;
        if ((i10 < i11 || rect.bottom <= i11) && rect.bottom < rect2.bottom) {
            b3 = 1;
        } else {
            int i12 = rect.bottom;
            int i13 = rect2.bottom;
            if ((i12 <= i13 && i10 < i13) || i10 <= i11) {
                b3 = 0;
            }
        }
        if (i3 == 1) {
            return b3 < 0 || (b3 == 0 && i4 * i5 <= 0);
        }
        if (i3 == 2) {
            return b3 > 0 || (b3 == 0 && i4 * i5 >= 0);
        }
        if (i3 == 17) {
            return i4 < 0;
        }
        if (i3 == 33) {
            return b3 < 0;
        }
        if (i3 == 66) {
            return i4 > 0;
        }
        if (i3 == 130) {
            return b3 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i3 + Q());
    }

    public final void v1() {
        this.f1434l0.g();
        o oVar = this.f1455w;
        if (oVar != null) {
            oVar.K1();
        }
    }

    public final void w(Context context, String str, AttributeSet attributeSet, int i3, int i4) {
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
                    constructor = clsAsSubclass.getConstructor(f1419i);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i3), Integer.valueOf(i4)};
                } catch (NoSuchMethodException e3) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e4) {
                        e4.initCause(e3);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strJ0, e4);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strJ0, e5);
            } catch (ClassNotFoundException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strJ0, e6);
            } catch (IllegalAccessException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strJ0, e7);
            } catch (InstantiationException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e9);
            }
        }
    }

    public void w0(int i3) {
        if (this.f1455w == null) {
            return;
        }
        setScrollState(2);
        this.f1455w.x1(i3);
        awakenScrollBars();
    }

    public void w1(int i3, int i4, Object obj) {
        int i5;
        int iJ = this.f1439o.j();
        int i6 = i3 + i4;
        for (int i7 = 0; i7 < iJ; i7++) {
            View viewI = this.f1439o.i(i7);
            c0 c0VarG0 = g0(viewI);
            if (c0VarG0 != null && !c0VarG0.J() && (i5 = c0VarG0.f1476d) >= i3 && i5 < i6) {
                c0VarG0.b(2);
                c0VarG0.a(obj);
                ((p) viewI.getLayoutParams()).f1534c = true;
            }
        }
        this.f1433l.M(i3, i4);
    }

    public void x(int i3, int i4) {
        setMeasuredDimension(o.n(i3, getPaddingLeft() + getPaddingRight(), l0.t.B(this)), o.n(i4, getPaddingTop() + getPaddingBottom(), l0.t.A(this)));
    }

    public void x0() {
        int iJ = this.f1439o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            ((p) this.f1439o.i(i3).getLayoutParams()).f1534c = true;
        }
        this.f1433l.s();
    }

    public final boolean y(int i3, int i4) {
        V(this.f1458x0);
        int[] iArr = this.f1458x0;
        return (iArr[0] == i3 && iArr[1] == i4) ? false : true;
    }

    public void y0() {
        int iJ = this.f1439o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarG0 = g0(this.f1439o.i(i3));
            if (c0VarG0 != null && !c0VarG0.J()) {
                c0VarG0.b(6);
            }
        }
        x0();
        this.f1433l.t();
    }

    public void z(View view) {
        c0 c0VarG0 = g0(view);
        E0(view);
        g gVar = this.f1453v;
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

    public void z0(int i3) {
        int iG = this.f1439o.g();
        for (int i4 = 0; i4 < iG; i4++) {
            this.f1439o.f(i4).offsetLeftAndRight(i3);
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1431k = new x();
        this.f1433l = new v();
        this.f1441p = new w0.q();
        this.f1445r = new a();
        this.f1447s = new Rect();
        this.f1449t = new Rect();
        this.f1451u = new RectF();
        this.f1459y = new ArrayList<>();
        this.f1461z = new ArrayList<>();
        this.F = 0;
        this.N = false;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.R = new k();
        this.W = new w0.c();
        this.X = 0;
        this.Y = -1;
        this.f1429i0 = Float.MIN_VALUE;
        this.f1430j0 = Float.MIN_VALUE;
        boolean z3 = true;
        this.f1432k0 = true;
        this.f1434l0 = new b0();
        this.f1438n0 = f1416f ? new e.b() : null;
        this.f1440o0 = new z();
        this.f1446r0 = false;
        this.f1448s0 = false;
        this.f1450t0 = new m();
        this.f1452u0 = false;
        this.f1458x0 = new int[2];
        this.f1462z0 = new int[2];
        this.A0 = new int[2];
        this.B0 = new int[2];
        this.C0 = new ArrayList();
        this.D0 = new b();
        this.E0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1425e0 = viewConfiguration.getScaledTouchSlop();
        this.f1429i0 = l0.u.b(viewConfiguration, context);
        this.f1430j0 = l0.u.d(viewConfiguration, context);
        this.f1427g0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1428h0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.W.v(this.f1450t0);
        o0();
        q0();
        p0();
        if (l0.t.x(this) == 0) {
            l0.t.t0(this, 1);
        }
        this.L = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new w0.l(this));
        int[] iArr = R$styleable.RecyclerView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i3, 0);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i3, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(R$styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.f1443q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_android_clipToPadding, true);
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.RecyclerView_fastScrollEnabled, false);
        this.D = z4;
        if (z4) {
            r0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(R$styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArrayObtainStyledAttributes.recycle();
        w(context, string, attributeSet, i3, 0);
        if (i4 >= 21) {
            int[] iArr2 = f1412b;
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i3, 0);
            if (i4 >= 29) {
                saveAttributeDataForStyleable(context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i3, 0);
            }
            z3 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
            typedArrayObtainStyledAttributes2.recycle();
        }
        setNestedScrollingEnabled(z3);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Parcelable f1463d;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1463d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        public void g(SavedState savedState) {
            this.f1463d = savedState.f1463d;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeParcelable(this.f1463d, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c0 f1532a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f1533b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1534c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1535d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1533b = new Rect();
            this.f1534c = true;
            this.f1535d = false;
        }

        public int a() {
            return this.f1532a.m();
        }

        public boolean b() {
            return this.f1532a.y();
        }

        public boolean c() {
            return this.f1532a.v();
        }

        public boolean d() {
            return this.f1532a.t();
        }

        public p(int i3, int i4) {
            super(i3, i4);
            this.f1533b = new Rect();
            this.f1534c = true;
            this.f1535d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1533b = new Rect();
            this.f1534c = true;
            this.f1535d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1533b = new Rect();
            this.f1534c = true;
            this.f1535d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1533b = new Rect();
            this.f1534c = true;
            this.f1535d = false;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.f1455w;
        if (oVar != null) {
            return oVar.F(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }
}
