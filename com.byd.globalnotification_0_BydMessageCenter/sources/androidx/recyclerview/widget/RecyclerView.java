package androidx.recyclerview.widget;

import a.h.k.x.b;
import a.p.e.a;
import a.p.e.b0;
import a.p.e.c;
import a.p.e.c0;
import a.p.e.e0;
import a.p.e.f0;
import a.p.e.l;
import a.p.e.n;
import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements a.h.k.f {
    public static final Class<?>[] A0;
    public static final Interpolator B0;
    public static final int[] v0 = {R.attr.nestedScrollingEnabled};
    public static final boolean w0 = false;
    public static final boolean x0 = true;
    public static final boolean y0 = true;
    public static final boolean z0 = true;
    public boolean A;
    public final AccessibilityManager B;
    public List<o> C;
    public boolean D;
    public boolean E;
    public int F;
    public int G;
    public i H;
    public EdgeEffect I;
    public EdgeEffect J;
    public EdgeEffect K;
    public EdgeEffect L;
    public j M;
    public int N;
    public int O;
    public VelocityTracker P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public p V;
    public final int W;
    public final int X;
    public float Y;
    public float Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f1615a;
    public boolean a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f1616b;
    public final a0 b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public w f1617c;
    public a.p.e.n c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.p.e.a f1618d;
    public n.b d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a.p.e.c f1619e;
    public final y e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f0 f1620f;
    public r f0;
    public boolean g;
    public List<r> g0;
    public final Runnable h;
    public boolean h0;
    public final Rect i;
    public boolean i0;
    public final Rect j;
    public j.b j0;
    public final RectF k;
    public boolean k0;
    public e l;
    public a.p.e.b0 l0;
    public m m;
    public h m0;
    public u n;
    public final int[] n0;
    public final ArrayList<l> o;
    public a.h.k.g o0;
    public final ArrayList<q> p;
    public final int[] p0;
    public q q;
    public final int[] q0;
    public boolean r;
    public final int[] r0;
    public boolean s;
    public final List<b0> s0;
    public boolean t;
    public Runnable t0;
    public boolean u;
    public final f0.b u0;
    public int v;
    public boolean w;
    public boolean x;
    public boolean y;
    public int z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.u || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.r) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.x) {
                recyclerView2.w = true;
            } else {
                recyclerView2.n();
            }
        }
    }

    public class a0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public OverScroller f1624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Interpolator f1625d = RecyclerView.B0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1626e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1627f = false;

        public a0() {
            this.f1624c = new OverScroller(RecyclerView.this.getContext(), RecyclerView.B0);
        }

        public void a() {
            if (this.f1626e) {
                this.f1627f = true;
            } else {
                RecyclerView.this.removeCallbacks(this);
                a.h.k.q.N(RecyclerView.this, this);
            }
        }

        public void b(int i, int i2, int i3, Interpolator interpolator) {
            int iRound;
            if (i3 == Integer.MIN_VALUE) {
                int iAbs = Math.abs(i);
                int iAbs2 = Math.abs(i2);
                boolean z = iAbs > iAbs2;
                int iSqrt = (int) Math.sqrt(0);
                int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i * i));
                RecyclerView recyclerView = RecyclerView.this;
                int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
                int i4 = width / 2;
                float f2 = width;
                float f3 = i4;
                float fSin = (((float) Math.sin((Math.min(1.0f, (iSqrt2 * 1.0f) / f2) - 0.5f) * 0.47123894f)) * f3) + f3;
                if (iSqrt > 0) {
                    iRound = Math.round(Math.abs(fSin / iSqrt) * 1000.0f) * 4;
                } else {
                    if (!z) {
                        iAbs = iAbs2;
                    }
                    iRound = (int) (((iAbs / f2) + 1.0f) * 300.0f);
                }
                i3 = Math.min(iRound, 2000);
            }
            int i5 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.B0;
            }
            if (this.f1625d != interpolator) {
                this.f1625d = interpolator;
                this.f1624c = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1623b = 0;
            this.f1622a = 0;
            RecyclerView.this.setScrollState(2);
            this.f1624c.startScroll(0, 0, i, i2, i5);
            a();
        }

        public void c() {
            RecyclerView.this.removeCallbacks(this);
            this.f1624c.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.m == null) {
                c();
                return;
            }
            this.f1627f = false;
            this.f1626e = true;
            recyclerView.n();
            OverScroller overScroller = this.f1624c;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.f1622a;
                int i4 = currY - this.f1623b;
                this.f1622a = currX;
                this.f1623b = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.r0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.t(i3, i4, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.r0;
                    i3 -= iArr2[0];
                    i4 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.m(i3, i4);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.l != null) {
                    int[] iArr3 = recyclerView3.r0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.j0(i3, i4, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.r0;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    i3 -= i2;
                    i4 -= i;
                    x xVar = recyclerView4.m.g;
                    if (xVar != null && !xVar.f1680d && xVar.f1681e) {
                        int iB = recyclerView4.e0.b();
                        if (iB == 0) {
                            xVar.e();
                        } else {
                            if (xVar.f1677a >= iB) {
                                xVar.f1677a = iB - 1;
                            }
                            xVar.c(i2, i);
                        }
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                if (!RecyclerView.this.o.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.r0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.u(i2, i, i3, i4, null, 1, iArr5);
                int[] iArr6 = RecyclerView.this.r0;
                int i5 = i3 - iArr6[0];
                int i6 = i4 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    RecyclerView.this.v(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                x xVar2 = RecyclerView.this.m.g;
                if ((xVar2 != null && xVar2.f1680d) || !z) {
                    a();
                    RecyclerView recyclerView6 = RecyclerView.this;
                    a.p.e.n nVar = recyclerView6.c0;
                    if (nVar != null) {
                        nVar.a(recyclerView6, i2, i);
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
                        RecyclerView recyclerView7 = RecyclerView.this;
                        if (recyclerView7 == null) {
                            throw null;
                        }
                        if (i7 < 0) {
                            recyclerView7.x();
                            if (recyclerView7.I.isFinished()) {
                                recyclerView7.I.onAbsorb(-i7);
                            }
                        } else if (i7 > 0) {
                            recyclerView7.y();
                            if (recyclerView7.K.isFinished()) {
                                recyclerView7.K.onAbsorb(i7);
                            }
                        }
                        if (currVelocity < 0) {
                            recyclerView7.z();
                            if (recyclerView7.J.isFinished()) {
                                recyclerView7.J.onAbsorb(-currVelocity);
                            }
                        } else if (currVelocity > 0) {
                            recyclerView7.w();
                            if (recyclerView7.L.isFinished()) {
                                recyclerView7.L.onAbsorb(currVelocity);
                            }
                        }
                        if (i7 != 0 || currVelocity != 0) {
                            a.h.k.q.M(recyclerView7);
                        }
                    }
                    boolean z2 = RecyclerView.z0;
                    n.b bVar = RecyclerView.this.d0;
                    int[] iArr7 = bVar.f1224c;
                    if (iArr7 != null) {
                        Arrays.fill(iArr7, -1);
                    }
                    bVar.f1225d = 0;
                }
            }
            x xVar3 = RecyclerView.this.m.g;
            if (xVar3 != null && xVar3.f1680d) {
                xVar3.c(0, 0);
            }
            this.f1626e = false;
            if (this.f1627f) {
                RecyclerView.this.removeCallbacks(this);
                a.h.k.q.N(RecyclerView.this, this);
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.r0(1);
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j jVar = RecyclerView.this.M;
            if (jVar != null) {
                a.p.e.l lVar = (a.p.e.l) jVar;
                boolean z = !lVar.h.isEmpty();
                boolean z2 = !lVar.j.isEmpty();
                boolean z3 = !lVar.k.isEmpty();
                boolean z4 = !lVar.i.isEmpty();
                if (z || z2 || z4 || z3) {
                    for (b0 b0Var : lVar.h) {
                        View view = b0Var.f1629a;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        lVar.q.add(b0Var);
                        viewPropertyAnimatorAnimate.setDuration(lVar.f1641d).alpha(0.0f).setListener(new a.p.e.g(lVar, b0Var, viewPropertyAnimatorAnimate, view)).start();
                    }
                    lVar.h.clear();
                    if (z2) {
                        ArrayList<l.b> arrayList = new ArrayList<>();
                        arrayList.addAll(lVar.j);
                        lVar.m.add(arrayList);
                        lVar.j.clear();
                        a.p.e.d dVar = new a.p.e.d(lVar, arrayList);
                        if (z) {
                            a.h.k.q.O(arrayList.get(0).f1200a.f1629a, dVar, lVar.f1641d);
                        } else {
                            dVar.run();
                        }
                    }
                    if (z3) {
                        ArrayList<l.a> arrayList2 = new ArrayList<>();
                        arrayList2.addAll(lVar.k);
                        lVar.n.add(arrayList2);
                        lVar.k.clear();
                        a.p.e.e eVar = new a.p.e.e(lVar, arrayList2);
                        if (z) {
                            a.h.k.q.O(arrayList2.get(0).f1194a.f1629a, eVar, lVar.f1641d);
                        } else {
                            eVar.run();
                        }
                    }
                    if (z4) {
                        ArrayList<b0> arrayList3 = new ArrayList<>();
                        arrayList3.addAll(lVar.i);
                        lVar.l.add(arrayList3);
                        lVar.i.clear();
                        a.p.e.f fVar = new a.p.e.f(lVar, arrayList3);
                        if (z || z2 || z3) {
                            a.h.k.q.O(arrayList3.get(0).f1629a, fVar, Math.max(z2 ? lVar.f1642e : 0L, z3 ? lVar.f1643f : 0L) + (z ? lVar.f1641d : 0L));
                        } else {
                            fVar.run();
                        }
                    }
                }
            }
            RecyclerView.this.k0 = false;
        }
    }

    public static abstract class b0 {
        public static final List<Object> s = Collections.emptyList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakReference<RecyclerView> f1630b;
        public int j;
        public RecyclerView r;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1631c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1632d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1633e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1634f = -1;
        public int g = -1;
        public b0 h = null;
        public b0 i = null;
        public List<Object> k = null;
        public List<Object> l = null;
        public int m = 0;
        public t n = null;
        public boolean o = false;
        public int p = 0;
        public int q = -1;

        public b0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1629a = view;
        }

        public void a(Object obj) {
            if (obj == null) {
                b(1024);
                return;
            }
            if ((1024 & this.j) == 0) {
                if (this.k == null) {
                    ArrayList arrayList = new ArrayList();
                    this.k = arrayList;
                    this.l = Collections.unmodifiableList(arrayList);
                }
                this.k.add(obj);
            }
        }

        public void b(int i) {
            this.j = i | this.j;
        }

        public void c() {
            this.f1632d = -1;
            this.g = -1;
        }

        public void d() {
            this.j &= -33;
        }

        public final int e() {
            RecyclerView recyclerView = this.r;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.H(this);
        }

        public final int f() {
            int i = this.g;
            return i == -1 ? this.f1631c : i;
        }

        public List<Object> g() {
            if ((this.j & 1024) != 0) {
                return s;
            }
            List<Object> list = this.k;
            return (list == null || list.size() == 0) ? s : this.l;
        }

        public boolean h(int i) {
            return (this.j & i) != 0;
        }

        public boolean i() {
            return (this.f1629a.getParent() == null || this.f1629a.getParent() == this.r) ? false : true;
        }

        public boolean j() {
            return (this.j & 1) != 0;
        }

        public boolean k() {
            return (this.j & 4) != 0;
        }

        public final boolean l() {
            return (this.j & 16) == 0 && !a.h.k.q.A(this.f1629a);
        }

        public boolean m() {
            return (this.j & 8) != 0;
        }

        public boolean n() {
            return this.n != null;
        }

        public boolean o() {
            return (this.j & 256) != 0;
        }

        public boolean p() {
            return (this.j & 2) != 0;
        }

        public void q(int i, boolean z) {
            if (this.f1632d == -1) {
                this.f1632d = this.f1631c;
            }
            if (this.g == -1) {
                this.g = this.f1631c;
            }
            if (z) {
                this.g += i;
            }
            this.f1631c += i;
            if (this.f1629a.getLayoutParams() != null) {
                ((n) this.f1629a.getLayoutParams()).f1661c = true;
            }
        }

        public void r() {
            this.j = 0;
            this.f1631c = -1;
            this.f1632d = -1;
            this.f1633e = -1L;
            this.g = -1;
            this.m = 0;
            this.h = null;
            this.i = null;
            List<Object> list = this.k;
            if (list != null) {
                list.clear();
            }
            this.j &= -1025;
            this.p = 0;
            this.q = -1;
            RecyclerView.k(this);
        }

        public void s(int i, int i2) {
            this.j = (i & i2) | (this.j & (~i2));
        }

        public final void t(boolean z) {
            int i;
            int i2 = this.m;
            int i3 = z ? i2 - 1 : i2 + 1;
            this.m = i3;
            if (i3 < 0) {
                this.m = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z && i3 == 1) {
                i = this.j | 16;
            } else if (!z || this.m != 0) {
                return;
            } else {
                i = this.j & (-17);
            }
            this.j = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1631c + " id=" + this.f1633e + ", oldPos=" + this.f1632d + ", pLpos:" + this.g);
            if (n()) {
                sb.append(" scrap ");
                sb.append(this.o ? "[changeScrap]" : "[attachedScrap]");
            }
            if (k()) {
                sb.append(" invalid");
            }
            if (!j()) {
                sb.append(" unbound");
            }
            boolean z = true;
            if ((this.j & 2) != 0) {
                sb.append(" update");
            }
            if (m()) {
                sb.append(" removed");
            }
            if (u()) {
                sb.append(" ignored");
            }
            if (o()) {
                sb.append(" tmpDetached");
            }
            if (!l()) {
                StringBuilder sbD = b.a.a.a.a.d(" not recyclable(");
                sbD.append(this.m);
                sbD.append(")");
                sb.append(sbD.toString());
            }
            if ((this.j & 512) == 0 && !k()) {
                z = false;
            }
            if (z) {
                sb.append(" undefined adapter position");
            }
            if (this.f1629a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public boolean u() {
            return (this.j & 128) != 0;
        }

        public boolean v() {
            return (this.j & 32) != 0;
        }
    }

    public static class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public class d implements f0.b {
        public d() {
        }

        public void a(b0 b0Var, j.c cVar, j.c cVar2) {
            boolean zJ;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView == null) {
                throw null;
            }
            b0Var.t(false);
            c0 c0Var = (c0) recyclerView.M;
            if (c0Var == null) {
                throw null;
            }
            if (cVar == null || (cVar.f1644a == cVar2.f1644a && cVar.f1645b == cVar2.f1645b)) {
                a.p.e.l lVar = (a.p.e.l) c0Var;
                lVar.o(b0Var);
                b0Var.f1629a.setAlpha(0.0f);
                lVar.i.add(b0Var);
                zJ = true;
            } else {
                zJ = c0Var.j(b0Var, cVar.f1644a, cVar.f1645b, cVar2.f1644a, cVar2.f1645b);
            }
            if (zJ) {
                recyclerView.a0();
            }
        }

        public void b(b0 b0Var, j.c cVar, j.c cVar2) {
            boolean zJ;
            RecyclerView.this.f1616b.k(b0Var);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f(b0Var);
            b0Var.t(false);
            c0 c0Var = (c0) recyclerView.M;
            if (c0Var == null) {
                throw null;
            }
            int i = cVar.f1644a;
            int i2 = cVar.f1645b;
            View view = b0Var.f1629a;
            int left = cVar2 == null ? view.getLeft() : cVar2.f1644a;
            int top = cVar2 == null ? view.getTop() : cVar2.f1645b;
            if (b0Var.m() || (i == left && i2 == top)) {
                a.p.e.l lVar = (a.p.e.l) c0Var;
                lVar.o(b0Var);
                lVar.h.add(b0Var);
                zJ = true;
            } else {
                view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                zJ = c0Var.j(b0Var, i, i2, left, top);
            }
            if (zJ) {
                recyclerView.a0();
            }
        }
    }

    public static abstract class e<VH extends b0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f f1636a = new f();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1637b = false;

        public abstract int a();

        public long b(int i) {
            return -1L;
        }

        public int c(int i) {
            return 0;
        }

        public final void d() {
            this.f1636a.b();
        }

        public void e(RecyclerView recyclerView) {
        }

        public abstract void f(VH vh, int i);

        public void g(VH vh, int i, List<Object> list) {
            f(vh, i);
        }

        public abstract VH h(ViewGroup viewGroup, int i);

        public void i(RecyclerView recyclerView) {
        }
    }

    public static class f extends Observable<g> {
        public boolean a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                v vVar = (v) ((g) ((Observable) this).mObservers.get(size));
                RecyclerView.this.i(null);
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.e0.f1694f = true;
                recyclerView.c0(true);
                if (!RecyclerView.this.f1618d.g()) {
                    RecyclerView.this.requestLayout();
                }
            }
        }

        public void c(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                v vVar = (v) ((g) ((Observable) this).mObservers.get(size));
                RecyclerView.this.i(null);
                a.p.e.a aVar = RecyclerView.this.f1618d;
                if (aVar == null) {
                    throw null;
                }
                boolean z = false;
                if (i != i2) {
                    aVar.f1130b.add(aVar.h(8, i, i2, null));
                    aVar.g |= 8;
                    if (aVar.f1130b.size() == 1) {
                        z = true;
                    }
                }
                if (z) {
                    vVar.a();
                }
            }
        }

        public void d(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                v vVar = (v) ((g) ((Observable) this).mObservers.get(size));
                RecyclerView.this.i(null);
                a.p.e.a aVar = RecyclerView.this.f1618d;
                if (aVar == null) {
                    throw null;
                }
                boolean z = false;
                if (i2 >= 1) {
                    aVar.f1130b.add(aVar.h(4, i, i2, obj));
                    aVar.g |= 4;
                    if (aVar.f1130b.size() == 1) {
                        z = true;
                    }
                }
                if (z) {
                    vVar.a();
                }
            }
        }

        public void e(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                v vVar = (v) ((g) ((Observable) this).mObservers.get(size));
                RecyclerView.this.i(null);
                a.p.e.a aVar = RecyclerView.this.f1618d;
                if (aVar == null) {
                    throw null;
                }
                boolean z = false;
                if (i2 >= 1) {
                    aVar.f1130b.add(aVar.h(1, i, i2, null));
                    aVar.g |= 1;
                    if (aVar.f1130b.size() == 1) {
                        z = true;
                    }
                }
                if (z) {
                    vVar.a();
                }
            }
        }

        public void f(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                v vVar = (v) ((g) ((Observable) this).mObservers.get(size));
                RecyclerView.this.i(null);
                a.p.e.a aVar = RecyclerView.this.f1618d;
                if (aVar == null) {
                    throw null;
                }
                boolean z = false;
                if (i2 >= 1) {
                    aVar.f1130b.add(aVar.h(2, i, i2, null));
                    aVar.g |= 2;
                    if (aVar.f1130b.size() == 1) {
                        z = true;
                    }
                }
                if (z) {
                    vVar.a();
                }
            }
        }
    }

    public static abstract class g {
    }

    public interface h {
    }

    public static class i {
        public EdgeEffect a(RecyclerView recyclerView) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b f1638a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1639b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1640c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1641d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1642e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1643f = 250;

        public interface a {
            void a();
        }

        public interface b {
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1644a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1645b;
        }

        public static int b(b0 b0Var) {
            int i = b0Var.j & 14;
            if (b0Var.k()) {
                return 4;
            }
            if ((i & 4) != 0) {
                return i;
            }
            int i2 = b0Var.f1632d;
            int iE = b0Var.e();
            return (i2 == -1 || iE == -1 || i2 == iE) ? i : i | 2048;
        }

        public abstract boolean a(b0 b0Var, b0 b0Var2, c cVar, c cVar2);

        public boolean c(b0 b0Var, List<Object> list) {
            return !((c0) this).g || b0Var.k();
        }

        public final void d(b0 b0Var) {
            b bVar = this.f1638a;
            if (bVar != null) {
                k kVar = (k) bVar;
                if (kVar == null) {
                    throw null;
                }
                boolean z = true;
                b0Var.t(true);
                if (b0Var.h != null && b0Var.i == null) {
                    b0Var.h = null;
                }
                b0Var.i = null;
                if ((b0Var.j & 16) != 0) {
                    return;
                }
                RecyclerView recyclerView = RecyclerView.this;
                View view = b0Var.f1629a;
                recyclerView.o0();
                a.p.e.c cVar = recyclerView.f1619e;
                int iIndexOfChild = ((a.p.e.z) cVar.f1144a).f1269a.indexOfChild(view);
                if (iIndexOfChild == -1) {
                    cVar.l(view);
                } else if (cVar.f1145b.d(iIndexOfChild)) {
                    cVar.f1145b.f(iIndexOfChild);
                    cVar.l(view);
                    ((a.p.e.z) cVar.f1144a).c(iIndexOfChild);
                } else {
                    z = false;
                }
                if (z) {
                    b0 b0VarK = RecyclerView.K(view);
                    recyclerView.f1616b.k(b0VarK);
                    recyclerView.f1616b.h(b0VarK);
                }
                recyclerView.q0(!z);
                if (z || !b0Var.o()) {
                    return;
                }
                RecyclerView.this.removeDetachedView(b0Var.f1629a, false);
            }
        }

        public final void e() {
            int size = this.f1639b.size();
            for (int i = 0; i < size; i++) {
                this.f1639b.get(i).a();
            }
            this.f1639b.clear();
        }

        public abstract void f(b0 b0Var);

        public abstract void g();

        public abstract boolean h();

        public c i(b0 b0Var) {
            c cVar = new c();
            View view = b0Var.f1629a;
            cVar.f1644a = view.getLeft();
            cVar.f1645b = view.getTop();
            view.getRight();
            view.getBottom();
            return cVar;
        }
    }

    public class k implements j.b {
        public k() {
        }
    }

    public static abstract class l {
        public void f(Rect rect, View view, RecyclerView recyclerView, y yVar) {
            ((n) view.getLayoutParams()).a();
            rect.set(0, 0, 0, 0);
        }

        public void g(Canvas canvas, RecyclerView recyclerView, y yVar) {
        }

        public void h(Canvas canvas, RecyclerView recyclerView, y yVar) {
        }
    }

    public static abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.p.e.c f1647a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1648b;
        public x g;
        public int m;
        public boolean n;
        public int o;
        public int p;
        public int q;
        public int r;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final e0.b f1649c = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final e0.b f1650d = new b();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e0 f1651e = new e0(this.f1649c);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public e0 f1652f = new e0(this.f1650d);
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public boolean k = true;
        public boolean l = true;

        public class a implements e0.b {
            public a() {
            }

            @Override // a.p.e.e0.b
            public int a() {
                m mVar = m.this;
                return mVar.q - mVar.P();
            }

            @Override // a.p.e.e0.b
            public int b() {
                return m.this.O();
            }

            @Override // a.p.e.e0.b
            public int c(View view) {
                return m.this.G(view) + ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).rightMargin;
            }

            @Override // a.p.e.e0.b
            public int d(View view) {
                return m.this.D(view) - ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).leftMargin;
            }

            @Override // a.p.e.e0.b
            public View e(int i) {
                return m.this.y(i);
            }
        }

        public class b implements e0.b {
            public b() {
            }

            @Override // a.p.e.e0.b
            public int a() {
                m mVar = m.this;
                return mVar.r - mVar.N();
            }

            @Override // a.p.e.e0.b
            public int b() {
                return m.this.Q();
            }

            @Override // a.p.e.e0.b
            public int c(View view) {
                return m.this.C(view) + ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).bottomMargin;
            }

            @Override // a.p.e.e0.b
            public int d(View view) {
                return m.this.H(view) - ((ViewGroup.MarginLayoutParams) ((n) view.getLayoutParams())).topMargin;
            }

            @Override // a.p.e.e0.b
            public View e(int i) {
                return m.this.y(i);
            }
        }

        public interface c {
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1655a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1656b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1657c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1658d;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int A(int r4, int r5, int r6, int r7, boolean r8) {
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.A(int, int, int, int, boolean):int");
        }

        public static d S(Context context, AttributeSet attributeSet, int i, int i2) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.p.d.RecyclerView, i, i2);
            dVar.f1655a = typedArrayObtainStyledAttributes.getInt(a.p.d.RecyclerView_android_orientation, 1);
            dVar.f1656b = typedArrayObtainStyledAttributes.getInt(a.p.d.RecyclerView_spanCount, 1);
            dVar.f1657c = typedArrayObtainStyledAttributes.getBoolean(a.p.d.RecyclerView_reverseLayout, false);
            dVar.f1658d = typedArrayObtainStyledAttributes.getBoolean(a.p.d.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static boolean Y(int i, int i2, int i3) {
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

        public static int i(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        public Parcelable A0() {
            return null;
        }

        public int B(t tVar, y yVar) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null || recyclerView.l == null || !f()) {
                return 1;
            }
            return this.f1648b.l.a();
        }

        public void B0(int i) {
        }

        public int C(View view) {
            return view.getBottom() + ((n) view.getLayoutParams()).f1660b.bottom;
        }

        public boolean C0(int i) {
            int iQ;
            int iO;
            int i2;
            int i3;
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null) {
                return false;
            }
            if (i == 4096) {
                iQ = recyclerView.canScrollVertically(1) ? (this.r - Q()) - N() : 0;
                if (this.f1648b.canScrollHorizontally(1)) {
                    iO = (this.q - O()) - P();
                    i3 = iO;
                    i2 = iQ;
                }
                i2 = iQ;
                i3 = 0;
            } else if (i != 8192) {
                i3 = 0;
                i2 = 0;
            } else {
                iQ = recyclerView.canScrollVertically(-1) ? -((this.r - Q()) - N()) : 0;
                if (this.f1648b.canScrollHorizontally(-1)) {
                    iO = -((this.q - O()) - P());
                    i3 = iO;
                    i2 = iQ;
                }
                i2 = iQ;
                i3 = 0;
            }
            if (i2 == 0 && i3 == 0) {
                return false;
            }
            this.f1648b.m0(i3, i2, null, Integer.MIN_VALUE, true);
            return true;
        }

        public int D(View view) {
            return view.getLeft() - ((n) view.getLayoutParams()).f1660b.left;
        }

        public boolean D0() {
            return false;
        }

        public int E(View view) {
            Rect rect = ((n) view.getLayoutParams()).f1660b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void E0(t tVar) {
            for (int iZ = z() - 1; iZ >= 0; iZ--) {
                if (!RecyclerView.K(y(iZ)).u()) {
                    H0(iZ, tVar);
                }
            }
        }

        public int F(View view) {
            Rect rect = ((n) view.getLayoutParams()).f1660b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void F0(t tVar) {
            int size = tVar.f1669a.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = tVar.f1669a.get(i).f1629a;
                b0 b0VarK = RecyclerView.K(view);
                if (!b0VarK.u()) {
                    b0VarK.t(false);
                    if (b0VarK.o()) {
                        this.f1648b.removeDetachedView(view, false);
                    }
                    j jVar = this.f1648b.M;
                    if (jVar != null) {
                        jVar.f(b0VarK);
                    }
                    b0VarK.t(true);
                    b0 b0VarK2 = RecyclerView.K(view);
                    b0VarK2.n = null;
                    b0VarK2.o = false;
                    b0VarK2.d();
                    tVar.h(b0VarK2);
                }
            }
            tVar.f1669a.clear();
            ArrayList<b0> arrayList = tVar.f1670b;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (size > 0) {
                this.f1648b.invalidate();
            }
        }

        public int G(View view) {
            return view.getRight() + ((n) view.getLayoutParams()).f1660b.right;
        }

        public void G0(View view, t tVar) {
            a.p.e.c cVar = this.f1647a;
            int iIndexOfChild = ((a.p.e.z) cVar.f1144a).f1269a.indexOfChild(view);
            if (iIndexOfChild >= 0) {
                if (cVar.f1145b.f(iIndexOfChild)) {
                    cVar.l(view);
                }
                ((a.p.e.z) cVar.f1144a).c(iIndexOfChild);
            }
            tVar.g(view);
        }

        public int H(View view) {
            return view.getTop() - ((n) view.getLayoutParams()).f1660b.top;
        }

        public void H0(int i, t tVar) {
            View viewY = y(i);
            I0(i);
            tVar.g(viewY);
        }

        public View I() {
            View focusedChild;
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1647a.f1146c.contains(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void I0(int i) {
            a.p.e.c cVar;
            int iF;
            View viewA;
            if (y(i) == null || (viewA = ((a.p.e.z) cVar.f1144a).a((iF = (cVar = this.f1647a).f(i)))) == null) {
                return;
            }
            if (cVar.f1145b.f(iF)) {
                cVar.l(viewA);
            }
            ((a.p.e.z) cVar.f1144a).c(iF);
        }

        public int J() {
            RecyclerView recyclerView = this.f1648b;
            e adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.a();
            }
            return 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00b6  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean J0(androidx.recyclerview.widget.RecyclerView r10, android.view.View r11, android.graphics.Rect r12, boolean r13, boolean r14) {
            /*
                Method dump skipped, instruction units count: 206
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m.J0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public int K() {
            return a.h.k.q.p(this.f1648b);
        }

        public void K0() {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int L() {
            return a.h.k.q.q(this.f1648b);
        }

        public int L0(int i, t tVar, y yVar) {
            return 0;
        }

        public int M() {
            return a.h.k.q.r(this.f1648b);
        }

        public void M0(int i) {
        }

        public int N() {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public int N0(int i, t tVar, y yVar) {
            return 0;
        }

        public int O() {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void O0(RecyclerView recyclerView) {
            P0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public int P() {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public void P0(int i, int i2) {
            this.q = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.o = mode;
            if (mode == 0) {
                boolean z = RecyclerView.x0;
            }
            this.r = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.p = mode2;
            if (mode2 == 0) {
                boolean z2 = RecyclerView.x0;
            }
        }

        public int Q() {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public void Q0(Rect rect, int i, int i2) {
            int iP = P() + O() + rect.width();
            int iN = N() + Q() + rect.height();
            this.f1648b.setMeasuredDimension(i(i, iP, M()), i(i2, iN, L()));
        }

        public int R(View view) {
            return ((n) view.getLayoutParams()).a();
        }

        public void R0(int i, int i2) {
            int iZ = z();
            if (iZ == 0) {
                this.f1648b.o(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MIN_VALUE;
            for (int i7 = 0; i7 < iZ; i7++) {
                View viewY = y(i7);
                Rect rect = this.f1648b.i;
                RecyclerView.L(viewY, rect);
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
            this.f1648b.i.set(i4, i5, i3, i6);
            Q0(this.f1648b.i, i, i2);
        }

        public void S0(RecyclerView recyclerView) {
            int height;
            if (recyclerView == null) {
                this.f1648b = null;
                this.f1647a = null;
                height = 0;
                this.q = 0;
            } else {
                this.f1648b = recyclerView;
                this.f1647a = recyclerView.f1619e;
                this.q = recyclerView.getWidth();
                height = recyclerView.getHeight();
            }
            this.r = height;
            this.o = 1073741824;
            this.p = 1073741824;
        }

        public int T(t tVar, y yVar) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null || recyclerView.l == null || !g()) {
                return 1;
            }
            return this.f1648b.l.a();
        }

        public boolean T0(View view, int i, int i2, n nVar) {
            return (!view.isLayoutRequested() && this.k && Y(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) nVar).width) && Y(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) nVar).height)) ? false : true;
        }

        public int U() {
            return 0;
        }

        public boolean U0() {
            return false;
        }

        public void V(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((n) view.getLayoutParams()).f1660b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1648b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1648b.k;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean V0(View view, int i, int i2, n nVar) {
            return (this.k && Y(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) nVar).width) && Y(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) nVar).height)) ? false : true;
        }

        public boolean W() {
            return this.j;
        }

        public void W0(RecyclerView recyclerView, y yVar, int i) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public boolean X() {
            return false;
        }

        public void X0(x xVar) {
            x xVar2 = this.g;
            if (xVar2 != null && xVar != xVar2 && xVar2.f1681e) {
                xVar2.e();
            }
            this.g = xVar;
            RecyclerView recyclerView = this.f1648b;
            if (xVar == null) {
                throw null;
            }
            recyclerView.b0.c();
            if (xVar.h) {
                StringBuilder sbD = b.a.a.a.a.d("An instance of ");
                sbD.append(xVar.getClass().getSimpleName());
                sbD.append(" was started more than once. Each instance of");
                sbD.append(xVar.getClass().getSimpleName());
                sbD.append(" is intended to only be used once. You should create a new instance for each use.");
                Log.w("RecyclerView", sbD.toString());
            }
            xVar.f1678b = recyclerView;
            xVar.f1679c = this;
            int i = xVar.f1677a;
            if (i == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.e0.f1689a = i;
            xVar.f1681e = true;
            xVar.f1680d = true;
            xVar.f1682f = recyclerView.m.u(i);
            xVar.f1678b.b0.a();
            xVar.h = true;
        }

        public boolean Y0() {
            return false;
        }

        public boolean Z(View view, boolean z) {
            boolean z2 = this.f1651e.b(view, 24579) && this.f1652f.b(view, 24579);
            return z ? z2 : !z2;
        }

        public void a0(View view, int i, int i2, int i3, int i4) {
            n nVar = (n) view.getLayoutParams();
            Rect rect = nVar.f1660b;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) nVar).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) nVar).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) nVar).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) nVar).bottomMargin);
        }

        public void b(View view) {
            c(view, -1, false);
        }

        public void b0(int i) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                int iE = recyclerView.f1619e.e();
                for (int i2 = 0; i2 < iE; i2++) {
                    recyclerView.f1619e.d(i2).offsetLeftAndRight(i);
                }
            }
        }

        public final void c(View view, int i, boolean z) {
            b0 b0VarK = RecyclerView.K(view);
            if (z || b0VarK.m()) {
                this.f1648b.f1620f.a(b0VarK);
            } else {
                this.f1648b.f1620f.f(b0VarK);
            }
            n nVar = (n) view.getLayoutParams();
            if (b0VarK.v() || b0VarK.n()) {
                if (b0VarK.n()) {
                    b0VarK.n.k(b0VarK);
                } else {
                    b0VarK.d();
                }
                this.f1647a.b(view, i, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1648b) {
                int iJ = this.f1647a.j(view);
                if (i == -1) {
                    i = this.f1647a.e();
                }
                if (iJ == -1) {
                    StringBuilder sbD = b.a.a.a.a.d("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    sbD.append(this.f1648b.indexOfChild(view));
                    throw new IllegalStateException(b.a.a.a.a.b(this.f1648b, sbD));
                }
                if (iJ != i) {
                    m mVar = this.f1648b.m;
                    View viewY = mVar.y(iJ);
                    if (viewY == null) {
                        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + iJ + mVar.f1648b.toString());
                    }
                    mVar.y(iJ);
                    mVar.s(iJ);
                    n nVar2 = (n) viewY.getLayoutParams();
                    b0 b0VarK2 = RecyclerView.K(viewY);
                    if (b0VarK2.m()) {
                        mVar.f1648b.f1620f.a(b0VarK2);
                    } else {
                        mVar.f1648b.f1620f.f(b0VarK2);
                    }
                    mVar.f1647a.b(viewY, i, nVar2, b0VarK2.m());
                }
            } else {
                this.f1647a.a(view, i, false);
                nVar.f1661c = true;
                x xVar = this.g;
                if (xVar != null && xVar.f1681e && xVar.b(view) == xVar.f1677a) {
                    xVar.f1682f = view;
                }
            }
            if (nVar.f1662d) {
                b0VarK.f1629a.invalidate();
                nVar.f1662d = false;
            }
        }

        public void c0(int i) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                int iE = recyclerView.f1619e.e();
                for (int i2 = 0; i2 < iE; i2++) {
                    recyclerView.f1619e.d(i2).offsetTopAndBottom(i);
                }
            }
        }

        public void d(String str) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView != null) {
                recyclerView.i(str);
            }
        }

        public void d0() {
        }

        public void e(View view, Rect rect) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.M(view));
            }
        }

        public boolean e0() {
            return false;
        }

        public boolean f() {
            return false;
        }

        public void f0() {
        }

        public boolean g() {
            return false;
        }

        @Deprecated
        public void g0() {
        }

        public boolean h(n nVar) {
            return nVar != null;
        }

        public void h0(RecyclerView recyclerView, t tVar) {
            g0();
        }

        public View i0(View view, int i, t tVar, y yVar) {
            return null;
        }

        public void j(int i, int i2, y yVar, c cVar) {
        }

        public void j0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1648b;
            t tVar = recyclerView.f1616b;
            y yVar = recyclerView.e0;
            k0(accessibilityEvent);
        }

        public void k(int i, c cVar) {
        }

        public void k0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1648b.canScrollVertically(-1) && !this.f1648b.canScrollHorizontally(-1) && !this.f1648b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            e eVar = this.f1648b.l;
            if (eVar != null) {
                accessibilityEvent.setItemCount(eVar.a());
            }
        }

        public int l(y yVar) {
            return 0;
        }

        public void l0(View view, a.h.k.x.b bVar) {
            b0 b0VarK = RecyclerView.K(view);
            if (b0VarK == null || b0VarK.m() || this.f1647a.k(b0VarK.f1629a)) {
                return;
            }
            RecyclerView recyclerView = this.f1648b;
            m0(recyclerView.f1616b, recyclerView.e0, view, bVar);
        }

        public int m(y yVar) {
            return 0;
        }

        public void m0(t tVar, y yVar, View view, a.h.k.x.b bVar) {
            bVar.i(b.c.a(g() ? R(view) : 0, 1, f() ? R(view) : 0, 1, false, false));
        }

        public int n(y yVar) {
            return 0;
        }

        public View n0() {
            return null;
        }

        public int o(y yVar) {
            return 0;
        }

        public void o0(RecyclerView recyclerView, int i, int i2) {
        }

        public int p(y yVar) {
            return 0;
        }

        public void p0(RecyclerView recyclerView) {
        }

        public int q(y yVar) {
            return 0;
        }

        public void q0(RecyclerView recyclerView, int i, int i2, int i3) {
        }

        public void r(t tVar) {
            int iZ = z();
            while (true) {
                iZ--;
                if (iZ < 0) {
                    return;
                }
                View viewY = y(iZ);
                b0 b0VarK = RecyclerView.K(viewY);
                if (!b0VarK.u()) {
                    if (!b0VarK.k() || b0VarK.m() || this.f1648b.l.f1637b) {
                        y(iZ);
                        s(iZ);
                        tVar.i(viewY);
                        this.f1648b.f1620f.f(b0VarK);
                    } else {
                        I0(iZ);
                        tVar.h(b0VarK);
                    }
                }
            }
        }

        public void r0(RecyclerView recyclerView, int i, int i2) {
        }

        public final void s(int i) {
            this.f1647a.c(i);
        }

        public void s0() {
        }

        public View t(View view) {
            View viewC;
            RecyclerView recyclerView = this.f1648b;
            if (recyclerView == null || (viewC = recyclerView.C(view)) == null || this.f1647a.f1146c.contains(viewC)) {
                return null;
            }
            return viewC;
        }

        public void t0(RecyclerView recyclerView, int i, int i2, Object obj) {
            s0();
        }

        public View u(int i) {
            int iZ = z();
            for (int i2 = 0; i2 < iZ; i2++) {
                View viewY = y(i2);
                b0 b0VarK = RecyclerView.K(viewY);
                if (b0VarK != null && b0VarK.f() == i && !b0VarK.u() && (this.f1648b.e0.g || !b0VarK.m())) {
                    return viewY;
                }
            }
            return null;
        }

        public void u0(t tVar, y yVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public abstract n v();

        public void v0(y yVar) {
        }

        public n w(Context context, AttributeSet attributeSet) {
            return new n(context, attributeSet);
        }

        public void w0(int i, int i2) {
            this.f1648b.o(i, i2);
        }

        public n x(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof n ? new n((n) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new n((ViewGroup.MarginLayoutParams) layoutParams) : new n(layoutParams);
        }

        @Deprecated
        public boolean x0(RecyclerView recyclerView) {
            x xVar = this.g;
            return (xVar != null && xVar.f1681e) || recyclerView.P();
        }

        public View y(int i) {
            a.p.e.c cVar = this.f1647a;
            if (cVar == null) {
                return null;
            }
            return ((a.p.e.z) cVar.f1144a).a(cVar.f(i));
        }

        public boolean y0(RecyclerView recyclerView, View view, View view2) {
            return x0(recyclerView);
        }

        public int z() {
            a.p.e.c cVar = this.f1647a;
            if (cVar != null) {
                return cVar.e();
            }
            return 0;
        }

        public void z0(Parcelable parcelable) {
        }
    }

    public static class n extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b0 f1659a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f1660b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1661c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1662d;

        public n(int i, int i2) {
            super(i, i2);
            this.f1660b = new Rect();
            this.f1661c = true;
            this.f1662d = false;
        }

        public n(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1660b = new Rect();
            this.f1661c = true;
            this.f1662d = false;
        }

        public n(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1660b = new Rect();
            this.f1661c = true;
            this.f1662d = false;
        }

        public n(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1660b = new Rect();
            this.f1661c = true;
            this.f1662d = false;
        }

        public n(n nVar) {
            super((ViewGroup.LayoutParams) nVar);
            this.f1660b = new Rect();
            this.f1661c = true;
            this.f1662d = false;
        }

        public int a() {
            return this.f1659a.f();
        }

        public boolean b() {
            return this.f1659a.p();
        }

        public boolean c() {
            return this.f1659a.m();
        }
    }

    public interface o {
        void a(View view);

        void e(View view);
    }

    public static abstract class p {
    }

    public interface q {
        void b(boolean z);

        boolean c(RecyclerView recyclerView, MotionEvent motionEvent);

        void d(RecyclerView recyclerView, MotionEvent motionEvent);
    }

    public static abstract class r {
        public void a(RecyclerView recyclerView, int i) {
        }

        public void b(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public static class s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SparseArray<a> f1663a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1664b = 0;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final ArrayList<b0> f1665a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1666b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public long f1667c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public long f1668d = 0;
        }

        public final a a(int i) {
            a aVar = this.f1663a.get(i);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1663a.put(i, aVar2);
            return aVar2;
        }

        public long b(long j, long j2) {
            if (j == 0) {
                return j2;
            }
            return (j2 / 4) + ((j / 4) * 3);
        }
    }

    public final class t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<b0> f1669a = new ArrayList<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<b0> f1670b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<b0> f1671c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<b0> f1672d = Collections.unmodifiableList(this.f1669a);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1673e = 2;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1674f = 2;
        public s g;

        public t() {
        }

        public void a(b0 b0Var, boolean z) {
            RecyclerView.k(b0Var);
            View view = b0Var.f1629a;
            a.p.e.b0 b0Var2 = RecyclerView.this.l0;
            if (b0Var2 != null) {
                b0.a aVar = b0Var2.f1141e;
                a.h.k.q.U(view, aVar instanceof b0.a ? aVar.f1143e.remove(view) : null);
            }
            if (z) {
                u uVar = RecyclerView.this.n;
                if (uVar != null) {
                    uVar.a(b0Var);
                }
                e eVar = RecyclerView.this.l;
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.e0 != null) {
                    recyclerView.f1620f.g(b0Var);
                }
            }
            b0Var.r = null;
            s sVarD = d();
            if (sVarD == null) {
                throw null;
            }
            int i = b0Var.f1634f;
            ArrayList<b0> arrayList = sVarD.a(i).f1665a;
            if (sVarD.f1663a.get(i).f1666b <= arrayList.size()) {
                return;
            }
            b0Var.r();
            arrayList.add(b0Var);
        }

        public void b() {
            this.f1669a.clear();
            e();
        }

        public int c(int i) {
            if (i >= 0 && i < RecyclerView.this.e0.b()) {
                RecyclerView recyclerView = RecyclerView.this;
                return !recyclerView.e0.g ? i : recyclerView.f1618d.f(i, 0);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("invalid position ");
            sb.append(i);
            sb.append(". State item count is ");
            sb.append(RecyclerView.this.e0.b());
            throw new IndexOutOfBoundsException(b.a.a.a.a.b(RecyclerView.this, sb));
        }

        public s d() {
            if (this.g == null) {
                this.g = new s();
            }
            return this.g;
        }

        public void e() {
            for (int size = this.f1671c.size() - 1; size >= 0; size--) {
                f(size);
            }
            this.f1671c.clear();
            boolean z = RecyclerView.z0;
            n.b bVar = RecyclerView.this.d0;
            int[] iArr = bVar.f1224c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            bVar.f1225d = 0;
        }

        public void f(int i) {
            a(this.f1671c.get(i), true);
            this.f1671c.remove(i);
        }

        public void g(View view) {
            b0 b0VarK = RecyclerView.K(view);
            if (b0VarK.o()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (b0VarK.n()) {
                b0VarK.n.k(b0VarK);
            } else if (b0VarK.v()) {
                b0VarK.d();
            }
            h(b0VarK);
            if (RecyclerView.this.M == null || b0VarK.l()) {
                return;
            }
            RecyclerView.this.M.f(b0VarK);
        }

        public void h(b0 b0Var) {
            boolean z;
            boolean z2;
            if (b0Var.n() || b0Var.f1629a.getParent() != null) {
                StringBuilder sbD = b.a.a.a.a.d("Scrapped or attached views may not be recycled. isScrap:");
                sbD.append(b0Var.n());
                sbD.append(" isAttached:");
                sbD.append(b0Var.f1629a.getParent() != null);
                throw new IllegalArgumentException(b.a.a.a.a.b(RecyclerView.this, sbD));
            }
            if (b0Var.o()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
                sb.append(b0Var);
                throw new IllegalArgumentException(b.a.a.a.a.b(RecyclerView.this, sb));
            }
            if (b0Var.u()) {
                throw new IllegalArgumentException(b.a.a.a.a.b(RecyclerView.this, b.a.a.a.a.d("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.")));
            }
            boolean z3 = (b0Var.j & 16) == 0 && a.h.k.q.A(b0Var.f1629a);
            e eVar = RecyclerView.this.l;
            if (eVar != null && z3 && eVar == null) {
                throw null;
            }
            if (b0Var.l()) {
                if (this.f1674f <= 0 || b0Var.h(526)) {
                    z2 = false;
                } else {
                    int size = this.f1671c.size();
                    if (size >= this.f1674f && size > 0) {
                        f(0);
                        size--;
                    }
                    boolean z4 = RecyclerView.z0;
                    if (size > 0 && !RecyclerView.this.d0.c(b0Var.f1631c)) {
                        do {
                            size--;
                            if (size < 0) {
                                break;
                            }
                        } while (RecyclerView.this.d0.c(this.f1671c.get(size).f1631c));
                        size++;
                    }
                    this.f1671c.add(size, b0Var);
                    z2 = true;
                }
                if (!z2) {
                    a(b0Var, true);
                    z = true;
                }
                z = z;
                z = z2;
            } else {
                z = false;
            }
            RecyclerView.this.f1620f.g(b0Var);
            if (z || z || !z3) {
                return;
            }
            b0Var.r = null;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void i(android.view.View r5) {
            /*
                r4 = this;
                androidx.recyclerview.widget.RecyclerView$b0 r5 = androidx.recyclerview.widget.RecyclerView.K(r5)
                r0 = 12
                boolean r0 = r5.h(r0)
                r1 = 0
                if (r0 != 0) goto L3d
                boolean r0 = r5.p()
                if (r0 == 0) goto L3d
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$j r0 = r0.M
                r2 = 1
                if (r0 == 0) goto L27
                java.util.List r3 = r5.g()
                boolean r0 = r0.c(r5, r3)
                if (r0 == 0) goto L25
                goto L27
            L25:
                r0 = r1
                goto L28
            L27:
                r0 = r2
            L28:
                if (r0 == 0) goto L2b
                goto L3d
            L2b:
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$b0> r0 = r4.f1670b
                if (r0 != 0) goto L36
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                r4.f1670b = r0
            L36:
                r5.n = r4
                r5.o = r2
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$b0> r4 = r4.f1670b
                goto L6a
            L3d:
                boolean r0 = r5.k()
                if (r0 == 0) goto L64
                boolean r0 = r5.m()
                if (r0 != 0) goto L64
                androidx.recyclerview.widget.RecyclerView r0 = androidx.recyclerview.widget.RecyclerView.this
                androidx.recyclerview.widget.RecyclerView$e r0 = r0.l
                boolean r0 = r0.f1637b
                if (r0 == 0) goto L52
                goto L64
            L52:
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool."
                java.lang.StringBuilder r0 = b.a.a.a.a.d(r0)
                androidx.recyclerview.widget.RecyclerView r4 = androidx.recyclerview.widget.RecyclerView.this
                java.lang.String r4 = b.a.a.a.a.b(r4, r0)
                r5.<init>(r4)
                throw r5
            L64:
                r5.n = r4
                r5.o = r1
                java.util.ArrayList<androidx.recyclerview.widget.RecyclerView$b0> r4 = r4.f1669a
            L6a:
                r4.add(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.t.i(android.view.View):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:71:0x0102, code lost:
        
            if (r10 == null) goto L85;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0104, code lost:
        
            r7 = androidx.recyclerview.widget.RecyclerView.K(r10);
            r8 = r16.h.f1619e;
            r9 = ((a.p.e.z) r8.f1144a).f1269a.indexOfChild(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0116, code lost:
        
            if (r9 < 0) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x011e, code lost:
        
            if (r8.f1145b.d(r9) == false) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0120, code lost:
        
            r8.f1145b.a(r9);
            r8.l(r10);
            r8 = r16.h.f1619e.j(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0130, code lost:
        
            if (r8 == (-1)) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0132, code lost:
        
            r16.h.f1619e.c(r8);
            i(r10);
            r7.b(8224);
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0143, code lost:
        
            r2 = new java.lang.StringBuilder();
            r2.append("layout index should not be -1 after unhiding a view:");
            r2.append(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x015b, code lost:
        
            throw new java.lang.IllegalStateException(b.a.a.a.a.b(r16.h, r2));
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0172, code lost:
        
            throw new java.lang.RuntimeException("trying to unhide a view that was not hidden" + r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0189, code lost:
        
            throw new java.lang.IllegalArgumentException("view is not a child, cannot hide " + r10);
         */
        /* JADX WARN: Removed duplicated region for block: B:116:0x0201  */
        /* JADX WARN: Removed duplicated region for block: B:133:0x024b  */
        /* JADX WARN: Removed duplicated region for block: B:185:0x033f  */
        /* JADX WARN: Removed duplicated region for block: B:223:0x0410  */
        /* JADX WARN: Removed duplicated region for block: B:229:0x0434  */
        /* JADX WARN: Removed duplicated region for block: B:231:0x043a  */
        /* JADX WARN: Removed duplicated region for block: B:238:0x044a  */
        /* JADX WARN: Removed duplicated region for block: B:290:0x0530  */
        /* JADX WARN: Removed duplicated region for block: B:291:0x0537  */
        /* JADX WARN: Removed duplicated region for block: B:298:0x0554 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01bc  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public androidx.recyclerview.widget.RecyclerView.b0 j(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 1420
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.t.j(int, boolean, long):androidx.recyclerview.widget.RecyclerView$b0");
        }

        public void k(b0 b0Var) {
            (b0Var.o ? this.f1670b : this.f1669a).remove(b0Var);
            b0Var.n = null;
            b0Var.o = false;
            b0Var.d();
        }

        public void l() {
            m mVar = RecyclerView.this.m;
            this.f1674f = this.f1673e + (mVar != null ? mVar.m : 0);
            for (int size = this.f1671c.size() - 1; size >= 0 && this.f1671c.size() > this.f1674f; size--) {
                f(size);
            }
        }
    }

    public interface u {
        void a(b0 b0Var);
    }

    public class v extends g {
        public v() {
        }

        public void a() {
            boolean z = RecyclerView.y0;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.s && recyclerView.r) {
                a.h.k.q.N(recyclerView, recyclerView.h);
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.A = true;
            recyclerView2.requestLayout();
        }
    }

    public static class w extends a.j.a.a {
        public static final Parcelable.Creator<w> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Parcelable f1676c;

        public static class a implements Parcelable.ClassLoaderCreator<w> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new w(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new w[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public w createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new w(parcel, classLoader);
            }
        }

        public w(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1676c = parcel.readParcelable(classLoader == null ? m.class.getClassLoader() : classLoader);
        }

        public w(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeParcelable(this.f1676c, 0);
        }
    }

    public static abstract class x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1678b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public m f1679c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1680d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1681e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f1682f;
        public boolean h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1677a = -1;
        public final a g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1683a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1684b;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1686d = -1;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public boolean f1688f = false;
            public int g = 0;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1685c = Integer.MIN_VALUE;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public Interpolator f1687e = null;

            public a(int i, int i2) {
                this.f1683a = i;
                this.f1684b = i2;
            }

            public void a(RecyclerView recyclerView) {
                int i = this.f1686d;
                if (i >= 0) {
                    this.f1686d = -1;
                    recyclerView.Q(i);
                    this.f1688f = false;
                    return;
                }
                if (!this.f1688f) {
                    this.g = 0;
                    return;
                }
                if (this.f1687e != null && this.f1685c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                int i2 = this.f1685c;
                if (i2 < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
                recyclerView.b0.b(this.f1683a, this.f1684b, i2, this.f1687e);
                int i3 = this.g + 1;
                this.g = i3;
                if (i3 > 10) {
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.f1688f = false;
            }

            public void b(int i, int i2, int i3, Interpolator interpolator) {
                this.f1683a = i;
                this.f1684b = i2;
                this.f1685c = i3;
                this.f1687e = interpolator;
                this.f1688f = true;
            }
        }

        public interface b {
            PointF a(int i);
        }

        public PointF a(int i) {
            Object obj = this.f1679c;
            if (obj instanceof b) {
                return ((b) obj).a(i);
            }
            StringBuilder sbD = b.a.a.a.a.d("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
            sbD.append(b.class.getCanonicalName());
            Log.w("RecyclerView", sbD.toString());
            return null;
        }

        public int b(View view) {
            if (this.f1678b == null) {
                throw null;
            }
            b0 b0VarK = RecyclerView.K(view);
            if (b0VarK != null) {
                return b0VarK.f();
            }
            return -1;
        }

        public void c(int i, int i2) {
            PointF pointFA;
            RecyclerView recyclerView = this.f1678b;
            if (this.f1677a == -1 || recyclerView == null) {
                e();
            }
            if (this.f1680d && this.f1682f == null && this.f1679c != null && (pointFA = a(this.f1677a)) != null && (pointFA.x != 0.0f || pointFA.y != 0.0f)) {
                recyclerView.j0((int) Math.signum(pointFA.x), (int) Math.signum(pointFA.y), null);
            }
            this.f1680d = false;
            View view = this.f1682f;
            if (view != null) {
                if (b(view) == this.f1677a) {
                    d(this.f1682f, recyclerView.e0, this.g);
                    this.g.a(recyclerView);
                    e();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f1682f = null;
                }
            }
            if (this.f1681e) {
                y yVar = recyclerView.e0;
                a aVar = this.g;
                a.p.e.s sVar = (a.p.e.s) this;
                if (sVar.f1678b.m.z() == 0) {
                    sVar.e();
                } else {
                    int i3 = sVar.o;
                    int i4 = i3 - i;
                    if (i3 * i4 <= 0) {
                        i4 = 0;
                    }
                    sVar.o = i4;
                    int i5 = sVar.p;
                    int i6 = i5 - i2;
                    if (i5 * i6 <= 0) {
                        i6 = 0;
                    }
                    sVar.p = i6;
                    if (sVar.o == 0 && i6 == 0) {
                        PointF pointFA2 = sVar.a(sVar.f1677a);
                        if (pointFA2 == null || (pointFA2.x == 0.0f && pointFA2.y == 0.0f)) {
                            aVar.f1686d = sVar.f1677a;
                            sVar.e();
                        } else {
                            float f2 = pointFA2.x;
                            float f3 = pointFA2.y;
                            float fSqrt = (float) Math.sqrt((f3 * f3) + (f2 * f2));
                            float f4 = pointFA2.x / fSqrt;
                            pointFA2.x = f4;
                            float f5 = pointFA2.y / fSqrt;
                            pointFA2.y = f5;
                            sVar.k = pointFA2;
                            sVar.o = (int) (f4 * 10000.0f);
                            sVar.p = (int) (f5 * 10000.0f);
                            aVar.b((int) (sVar.o * 1.2f), (int) (sVar.p * 1.2f), (int) (sVar.h(10000) * 1.2f), sVar.i);
                        }
                    }
                }
                boolean z = this.g.f1686d >= 0;
                this.g.a(recyclerView);
                if (z && this.f1681e) {
                    this.f1680d = true;
                    recyclerView.b0.a();
                }
            }
        }

        public abstract void d(View view, y yVar, a aVar);

        public final void e() {
            if (this.f1681e) {
                this.f1681e = false;
                a.p.e.s sVar = (a.p.e.s) this;
                sVar.p = 0;
                sVar.o = 0;
                sVar.k = null;
                this.f1678b.e0.f1689a = -1;
                this.f1682f = null;
                this.f1677a = -1;
                this.f1680d = false;
                m mVar = this.f1679c;
                if (mVar.g == this) {
                    mVar.g = null;
                }
                this.f1679c = null;
                this.f1678b = null;
            }
        }
    }

    public static class y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1689a = -1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1690b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1691c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1692d = 1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1693e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1694f = false;
        public boolean g = false;
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public int l;
        public long m;
        public int n;
        public int o;

        public void a(int i) {
            if ((this.f1692d & i) != 0) {
                return;
            }
            StringBuilder sbD = b.a.a.a.a.d("Layout state should be one of ");
            sbD.append(Integer.toBinaryString(i));
            sbD.append(" but it is ");
            sbD.append(Integer.toBinaryString(this.f1692d));
            throw new IllegalStateException(sbD.toString());
        }

        public int b() {
            return this.g ? this.f1690b - this.f1691c : this.f1693e;
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("State{mTargetPosition=");
            sbD.append(this.f1689a);
            sbD.append(", mData=");
            sbD.append((Object) null);
            sbD.append(", mItemCount=");
            sbD.append(this.f1693e);
            sbD.append(", mIsMeasuring=");
            sbD.append(this.i);
            sbD.append(", mPreviousLayoutItemCount=");
            sbD.append(this.f1690b);
            sbD.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            sbD.append(this.f1691c);
            sbD.append(", mStructureChanged=");
            sbD.append(this.f1694f);
            sbD.append(", mInPreLayout=");
            sbD.append(this.g);
            sbD.append(", mRunSimpleAnimations=");
            sbD.append(this.j);
            sbD.append(", mRunPredictiveAnimations=");
            sbD.append(this.k);
            sbD.append('}');
            return sbD.toString();
        }
    }

    public static abstract class z {
    }

    static {
        Class<?> cls = Integer.TYPE;
        A0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        B0 = new c();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.p.a.recyclerViewStyle);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:30|(1:32)(12:33|(1:36)|35|74|38|(1:40)(1:41)|42|43|70|44|45|49)|37|35|74|38|(0)(0)|42|43|70|44|45|49) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0258, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x025a, code lost:
    
        r0 = r3.getConstructor(new java.lang.Class[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x026e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x026f, code lost:
    
        r0.initCause(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x028f, code lost:
    
        throw new java.lang.IllegalStateException(r21.getPositionDescription() + ": Error creating LayoutManager " + r2, r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0224 A[Catch: ClassCastException -> 0x0290, IllegalAccessException -> 0x02af, InstantiationException -> 0x02ce, InvocationTargetException -> 0x02eb, ClassNotFoundException -> 0x0308, TryCatch #4 {ClassCastException -> 0x0290, ClassNotFoundException -> 0x0308, IllegalAccessException -> 0x02af, InstantiationException -> 0x02ce, InvocationTargetException -> 0x02eb, blocks: (B:38:0x021e, B:40:0x0224, B:42:0x0231, B:44:0x023c, B:49:0x0260, B:48:0x025a, B:51:0x026f, B:52:0x028f, B:41:0x022d), top: B:74:0x021e }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x022d A[Catch: ClassCastException -> 0x0290, IllegalAccessException -> 0x02af, InstantiationException -> 0x02ce, InvocationTargetException -> 0x02eb, ClassNotFoundException -> 0x0308, TryCatch #4 {ClassCastException -> 0x0290, ClassNotFoundException -> 0x0308, IllegalAccessException -> 0x02af, InstantiationException -> 0x02ce, InvocationTargetException -> 0x02eb, blocks: (B:38:0x021e, B:40:0x0224, B:42:0x0231, B:44:0x023c, B:49:0x0260, B:48:0x025a, B:51:0x026f, B:52:0x028f, B:41:0x022d), top: B:74:0x021e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RecyclerView(android.content.Context r20, android.util.AttributeSet r21, int r22) {
        /*
            Method dump skipped, instruction units count: 839
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static RecyclerView F(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewF = F(viewGroup.getChildAt(i2));
            if (recyclerViewF != null) {
                return recyclerViewF;
            }
        }
        return null;
    }

    public static b0 K(View view) {
        if (view == null) {
            return null;
        }
        return ((n) view.getLayoutParams()).f1659a;
    }

    public static void L(View view, Rect rect) {
        n nVar = (n) view.getLayoutParams();
        Rect rect2 = nVar.f1660b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) nVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) nVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) nVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) nVar).bottomMargin);
    }

    private a.h.k.g getScrollingChildHelper() {
        if (this.o0 == null) {
            this.o0 = new a.h.k.g(this);
        }
        return this.o0;
    }

    public static void k(b0 b0Var) {
        WeakReference<RecyclerView> weakReference = b0Var.f1630b;
        if (weakReference != null) {
            Object parent = weakReference.get();
            while (true) {
                for (View view = (View) parent; view != null; view = null) {
                    if (view == b0Var.f1629a) {
                        return;
                    }
                    parent = view.getParent();
                    if (parent instanceof View) {
                        break;
                    }
                }
                b0Var.f1630b = null;
                return;
            }
        }
    }

    public String A() {
        StringBuilder sbD = b.a.a.a.a.d(" ");
        sbD.append(super.toString());
        sbD.append(", adapter:");
        sbD.append(this.l);
        sbD.append(", layout:");
        sbD.append(this.m);
        sbD.append(", context:");
        sbD.append(getContext());
        return sbD.toString();
    }

    public final void B(y yVar) {
        if (getScrollState() != 2) {
            yVar.o = 0;
            return;
        }
        OverScroller overScroller = this.b0.f1624c;
        yVar.o = overScroller.getFinalX() - overScroller.getCurrX();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    public View C(View view) {
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

    public final boolean D(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            q qVar = this.p.get(i2);
            if (qVar.c(this, motionEvent) && action != 3) {
                this.q = qVar;
                return true;
            }
        }
        return false;
    }

    public final void E(int[] iArr) {
        int iE = this.f1619e.e();
        if (iE == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iE; i4++) {
            b0 b0VarK = K(this.f1619e.d(i4));
            if (!b0VarK.u()) {
                int iF = b0VarK.f();
                if (iF < i2) {
                    i2 = iF;
                }
                if (iF > i3) {
                    i3 = iF;
                }
            }
        }
        iArr[0] = i2;
        iArr[1] = i3;
    }

    public b0 G(int i2) {
        b0 b0Var = null;
        if (this.D) {
            return null;
        }
        int iH = this.f1619e.h();
        for (int i3 = 0; i3 < iH; i3++) {
            b0 b0VarK = K(this.f1619e.g(i3));
            if (b0VarK != null && !b0VarK.m() && H(b0VarK) == i2) {
                if (!this.f1619e.k(b0VarK.f1629a)) {
                    return b0VarK;
                }
                b0Var = b0VarK;
            }
        }
        return b0Var;
    }

    public int H(b0 b0Var) {
        if (!b0Var.h(524) && b0Var.j()) {
            a.p.e.a aVar = this.f1618d;
            int i2 = b0Var.f1631c;
            int size = aVar.f1130b.size();
            for (int i3 = 0; i3 < size; i3++) {
                a.b bVar = aVar.f1130b.get(i3);
                int i4 = bVar.f1135a;
                if (i4 != 1) {
                    if (i4 == 2) {
                        int i5 = bVar.f1136b;
                        if (i5 <= i2) {
                            int i6 = bVar.f1138d;
                            if (i5 + i6 <= i2) {
                                i2 -= i6;
                            }
                        } else {
                            continue;
                        }
                    } else if (i4 == 8) {
                        int i7 = bVar.f1136b;
                        if (i7 == i2) {
                            i2 = bVar.f1138d;
                        } else {
                            if (i7 < i2) {
                                i2--;
                            }
                            if (bVar.f1138d <= i2) {
                                i2++;
                            }
                        }
                    }
                } else if (bVar.f1136b <= i2) {
                    i2 += bVar.f1138d;
                }
            }
            return i2;
        }
        return -1;
    }

    public long I(b0 b0Var) {
        return this.l.f1637b ? b0Var.f1633e : b0Var.f1631c;
    }

    public b0 J(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return K(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public Rect M(View view) {
        n nVar = (n) view.getLayoutParams();
        if (!nVar.f1661c) {
            return nVar.f1660b;
        }
        if (this.e0.g && (nVar.b() || nVar.f1659a.k())) {
            return nVar.f1660b;
        }
        Rect rect = nVar.f1660b;
        rect.set(0, 0, 0, 0);
        int size = this.o.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.i.set(0, 0, 0, 0);
            this.o.get(i2).f(this.i, view, this, this.e0);
            int i3 = rect.left;
            Rect rect2 = this.i;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        nVar.f1661c = false;
        return rect;
    }

    public boolean N() {
        return !this.u || this.D || this.f1618d.g();
    }

    public void O() {
        this.L = null;
        this.J = null;
        this.K = null;
        this.I = null;
    }

    public boolean P() {
        return this.F > 0;
    }

    public void Q(int i2) {
        if (this.m == null) {
            return;
        }
        setScrollState(2);
        this.m.M0(i2);
        awakenScrollBars();
    }

    public void R() {
        int iH = this.f1619e.h();
        for (int i2 = 0; i2 < iH; i2++) {
            ((n) this.f1619e.g(i2).getLayoutParams()).f1661c = true;
        }
        t tVar = this.f1616b;
        int size = tVar.f1671c.size();
        for (int i3 = 0; i3 < size; i3++) {
            n nVar = (n) tVar.f1671c.get(i3).f1629a.getLayoutParams();
            if (nVar != null) {
                nVar.f1661c = true;
            }
        }
    }

    public void S(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iH = this.f1619e.h();
        for (int i5 = 0; i5 < iH; i5++) {
            b0 b0VarK = K(this.f1619e.g(i5));
            if (b0VarK != null && !b0VarK.u()) {
                int i6 = b0VarK.f1631c;
                if (i6 >= i4) {
                    b0VarK.q(-i3, z2);
                } else if (i6 >= i2) {
                    b0VarK.b(8);
                    b0VarK.q(-i3, z2);
                    b0VarK.f1631c = i2 - 1;
                }
                this.e0.f1694f = true;
            }
        }
        t tVar = this.f1616b;
        int size = tVar.f1671c.size();
        while (true) {
            size--;
            if (size < 0) {
                requestLayout();
                return;
            }
            b0 b0Var = tVar.f1671c.get(size);
            if (b0Var != null) {
                int i7 = b0Var.f1631c;
                if (i7 >= i4) {
                    b0Var.q(-i3, z2);
                } else if (i7 >= i2) {
                    b0Var.b(8);
                    tVar.f(size);
                }
            }
        }
    }

    public void T() {
    }

    public void U() {
    }

    public void V() {
        this.F++;
    }

    public void W(boolean z2) {
        int i2;
        int i3 = this.F - 1;
        this.F = i3;
        if (i3 < 1) {
            this.F = 0;
            if (z2) {
                int i4 = this.z;
                this.z = 0;
                if (i4 != 0) {
                    AccessibilityManager accessibilityManager = this.B;
                    if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                        accessibilityEventObtain.setEventType(2048);
                        accessibilityEventObtain.setContentChangeTypes(i4);
                        sendAccessibilityEventUnchecked(accessibilityEventObtain);
                    }
                }
                for (int size = this.s0.size() - 1; size >= 0; size--) {
                    b0 b0Var = this.s0.get(size);
                    if (b0Var.f1629a.getParent() == this && !b0Var.u() && (i2 = b0Var.q) != -1) {
                        a.h.k.q.b0(b0Var.f1629a, i2);
                        b0Var.q = -1;
                    }
                }
                this.s0.clear();
            }
        }
    }

    public final void X(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.O) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.O = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.S = x2;
            this.Q = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.T = y2;
            this.R = y2;
        }
    }

    public void Y() {
    }

    public void Z() {
    }

    public void a0() {
        if (this.k0 || !this.r) {
            return;
        }
        a.h.k.q.N(this, this.t0);
        this.k0 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        m mVar = this.m;
        if (mVar == null || !mVar.e0()) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public final void b0() {
        boolean z2 = false;
        if (this.D) {
            a.p.e.a aVar = this.f1618d;
            aVar.l(aVar.f1130b);
            aVar.l(aVar.f1131c);
            aVar.g = 0;
            if (this.E) {
                this.m.p0(this);
            }
        }
        if (this.M != null && this.m.Y0()) {
            this.f1618d.j();
        } else {
            this.f1618d.c();
        }
        boolean z3 = this.h0 || this.i0;
        this.e0.j = this.u && this.M != null && (this.D || z3 || this.m.h) && (!this.D || this.l.f1637b);
        y yVar = this.e0;
        if (yVar.j && z3 && !this.D) {
            if (this.M != null && this.m.Y0()) {
                z2 = true;
            }
        }
        yVar.k = z2;
    }

    public void c0(boolean z2) {
        this.E = z2 | this.E;
        this.D = true;
        int iH = this.f1619e.h();
        for (int i2 = 0; i2 < iH; i2++) {
            b0 b0VarK = K(this.f1619e.g(i2));
            if (b0VarK != null && !b0VarK.u()) {
                b0VarK.b(6);
            }
        }
        R();
        t tVar = this.f1616b;
        int size = tVar.f1671c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b0 b0Var = tVar.f1671c.get(i3);
            if (b0Var != null) {
                b0Var.b(6);
                b0Var.a(null);
            }
        }
        e eVar = RecyclerView.this.l;
        if (eVar == null || !eVar.f1637b) {
            tVar.e();
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof n) && this.m.h((n) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        m mVar = this.m;
        if (mVar != null && mVar.f()) {
            return this.m.l(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        m mVar = this.m;
        if (mVar != null && mVar.f()) {
            return this.m.m(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        m mVar = this.m;
        if (mVar != null && mVar.f()) {
            return this.m.n(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        m mVar = this.m;
        if (mVar != null && mVar.g()) {
            return this.m.o(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        m mVar = this.m;
        if (mVar != null && mVar.g()) {
            return this.m.p(this.e0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        m mVar = this.m;
        if (mVar != null && mVar.g()) {
            return this.m.q(this.e0);
        }
        return 0;
    }

    public void d0(b0 b0Var, j.c cVar) {
        b0Var.s(0, 8192);
        if (this.e0.h && b0Var.p() && !b0Var.m() && !b0Var.u()) {
            this.f1620f.f1167b.f(I(b0Var), b0Var);
        }
        this.f1620f.c(b0Var, cVar);
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
        return getScrollingChildHelper().c(i2, i3, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return getScrollingChildHelper().e(i2, i3, i4, i5, iArr);
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
        boolean z2;
        float paddingRight;
        float paddingBottom;
        super.draw(canvas);
        int size = this.o.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.o.get(i2).h(canvas, this, this.e0);
        }
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom2 = this.g ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom2, 0.0f);
            EdgeEffect edgeEffect2 = this.I;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.J;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.g) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.J;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.K;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.g ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.K;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.L;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.g) {
                paddingRight = getPaddingRight() + (-getWidth());
                paddingBottom = getPaddingBottom() + (-getHeight());
            } else {
                paddingRight = -getWidth();
                paddingBottom = -getHeight();
            }
            canvas.translate(paddingRight, paddingBottom);
            EdgeEffect edgeEffect8 = this.L;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.M == null || this.o.size() <= 0 || !this.M.h()) ? z2 : true) {
            a.h.k.q.M(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public void e0() {
        j jVar = this.M;
        if (jVar != null) {
            jVar.g();
        }
        m mVar = this.m;
        if (mVar != null) {
            mVar.E0(this.f1616b);
            this.m.F0(this.f1616b);
        }
        this.f1616b.b();
    }

    public final void f(b0 b0Var) {
        View view = b0Var.f1629a;
        boolean z2 = view.getParent() == this;
        this.f1616b.k(J(view));
        boolean zO = b0Var.o();
        a.p.e.c cVar = this.f1619e;
        if (zO) {
            cVar.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z2) {
            cVar.a(view, -1, true);
            return;
        }
        int iIndexOfChild = ((a.p.e.z) cVar.f1144a).f1269a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            cVar.f1145b.h(iIndexOfChild);
            cVar.i(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public void f0(l lVar) {
        m mVar = this.m;
        if (mVar != null) {
            mVar.d("Cannot remove item decoration during a scroll  or layout");
        }
        this.o.remove(lVar);
        if (this.o.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        R();
        requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0075  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View focusSearch(android.view.View r14, int r15) {
        /*
            Method dump skipped, instruction units count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    public void g(l lVar) {
        m mVar = this.m;
        if (mVar != null) {
            mVar.d("Cannot add item decoration during a scroll  or layout");
        }
        if (this.o.isEmpty()) {
            setWillNotDraw(false);
        }
        this.o.add(lVar);
        R();
        requestLayout();
    }

    public final void g0(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.i.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof n) {
            n nVar = (n) layoutParams;
            if (!nVar.f1661c) {
                Rect rect = nVar.f1660b;
                Rect rect2 = this.i;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.i);
            offsetRectIntoDescendantCoords(view, this.i);
        }
        this.m.J0(this, view, this.i, !this.u, view2 == null);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        m mVar = this.m;
        if (mVar != null) {
            return mVar.v();
        }
        throw new IllegalStateException(b.a.a.a.a.b(this, b.a.a.a.a.d("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        m mVar = this.m;
        if (mVar != null) {
            return mVar.w(getContext(), attributeSet);
        }
        throw new IllegalStateException(b.a.a.a.a.b(this, b.a.a.a.a.d("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        m mVar = this.m;
        if (mVar != null) {
            return mVar.x(layoutParams);
        }
        throw new IllegalStateException(b.a.a.a.a.b(this, b.a.a.a.a.d("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public e getAdapter() {
        return this.l;
    }

    @Override // android.view.View
    public int getBaseline() {
        m mVar = this.m;
        if (mVar == null) {
            return super.getBaseline();
        }
        if (mVar != null) {
            return -1;
        }
        throw null;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        h hVar = this.m0;
        if (hVar == null) {
            return super.getChildDrawingOrder(i2, i3);
        }
        a.p.e.q qVar = (a.p.e.q) hVar;
        a.p.e.o oVar = qVar.f1256a;
        View view = oVar.x;
        if (view == null) {
            return i3;
        }
        int iIndexOfChild = oVar.y;
        if (iIndexOfChild == -1) {
            iIndexOfChild = oVar.r.indexOfChild(view);
            qVar.f1256a.y = iIndexOfChild;
        }
        return i3 == i2 + (-1) ? iIndexOfChild : i3 < iIndexOfChild ? i3 : i3 + 1;
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.g;
    }

    public a.p.e.b0 getCompatAccessibilityDelegate() {
        return this.l0;
    }

    public i getEdgeEffectFactory() {
        return this.H;
    }

    public j getItemAnimator() {
        return this.M;
    }

    public int getItemDecorationCount() {
        return this.o.size();
    }

    public m getLayoutManager() {
        return this.m;
    }

    public int getMaxFlingVelocity() {
        return this.X;
    }

    public int getMinFlingVelocity() {
        return this.W;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public p getOnFlingListener() {
        return this.V;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.a0;
    }

    public s getRecycledViewPool() {
        return this.f1616b.d();
    }

    public int getScrollState() {
        return this.N;
    }

    public void h(r rVar) {
        if (this.g0 == null) {
            this.g0 = new ArrayList();
        }
        this.g0.add(rVar);
    }

    public final void h0() {
        VelocityTracker velocityTracker = this.P;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        r0(0);
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.I.isFinished();
        }
        EdgeEffect edgeEffect2 = this.J;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.J.isFinished();
        }
        EdgeEffect edgeEffect3 = this.K;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.K.isFinished();
        }
        EdgeEffect edgeEffect4 = this.L;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.L.isFinished();
        }
        if (zIsFinished) {
            a.h.k.q.M(this);
        }
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().h(0);
    }

    public void i(String str) {
        if (P()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(b.a.a.a.a.b(this, b.a.a.a.a.d("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.G > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(b.a.a.a.a.b(this, b.a.a.a.a.d(""))));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean i0(int r18, int r19, android.view.MotionEvent r20) {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.i0(int, int, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.r;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.x;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f817d;
    }

    public final void j() {
        h0();
        setScrollState(0);
    }

    public void j0(int i2, int i3, int[] iArr) {
        b0 b0Var;
        o0();
        V();
        Trace.beginSection("RV Scroll");
        B(this.e0);
        int iL0 = i2 != 0 ? this.m.L0(i2, this.f1616b, this.e0) : 0;
        int iN0 = i3 != 0 ? this.m.N0(i3, this.f1616b, this.e0) : 0;
        Trace.endSection();
        int iE = this.f1619e.e();
        for (int i4 = 0; i4 < iE; i4++) {
            View viewD = this.f1619e.d(i4);
            b0 b0VarJ = J(viewD);
            if (b0VarJ != null && (b0Var = b0VarJ.i) != null) {
                View view = b0Var.f1629a;
                int left = viewD.getLeft();
                int top = viewD.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        W(true);
        q0(false);
        if (iArr != null) {
            iArr[0] = iL0;
            iArr[1] = iN0;
        }
    }

    public void k0(int i2) {
        if (this.x) {
            return;
        }
        s0();
        m mVar = this.m;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            mVar.M0(i2);
            awakenScrollBars();
        }
    }

    public void l() {
        int iH = this.f1619e.h();
        for (int i2 = 0; i2 < iH; i2++) {
            b0 b0VarK = K(this.f1619e.g(i2));
            if (!b0VarK.u()) {
                b0VarK.c();
            }
        }
        t tVar = this.f1616b;
        int size = tVar.f1671c.size();
        for (int i3 = 0; i3 < size; i3++) {
            tVar.f1671c.get(i3).c();
        }
        int size2 = tVar.f1669a.size();
        for (int i4 = 0; i4 < size2; i4++) {
            tVar.f1669a.get(i4).c();
        }
        ArrayList<b0> arrayList = tVar.f1670b;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i5 = 0; i5 < size3; i5++) {
                tVar.f1670b.get(i5).c();
            }
        }
    }

    public boolean l0(b0 b0Var, int i2) {
        if (!P()) {
            a.h.k.q.b0(b0Var.f1629a, i2);
            return true;
        }
        b0Var.q = i2;
        this.s0.add(b0Var);
        return false;
    }

    public void m(int i2, int i3) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.I;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            zIsFinished = false;
        } else {
            this.I.onRelease();
            zIsFinished = this.I.isFinished();
        }
        EdgeEffect edgeEffect2 = this.K;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.K.onRelease();
            zIsFinished |= this.K.isFinished();
        }
        EdgeEffect edgeEffect3 = this.J;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.J.onRelease();
            zIsFinished |= this.J.isFinished();
        }
        EdgeEffect edgeEffect4 = this.L;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.L.onRelease();
            zIsFinished |= this.L.isFinished();
        }
        if (zIsFinished) {
            a.h.k.q.M(this);
        }
    }

    public void m0(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
        m mVar = this.m;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.x) {
            return;
        }
        if (!mVar.f()) {
            i2 = 0;
        }
        if (!this.m.g()) {
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
            p0(i5, 1);
        }
        this.b0.b(i2, i3, i4, interpolator);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n() {
        /*
            r6 = this;
            boolean r0 = r6.u
            java.lang.String r1 = "RV FullInvalidate"
            if (r0 == 0) goto L90
            boolean r0 = r6.D
            if (r0 == 0) goto Lc
            goto L90
        Lc:
            a.p.e.a r0 = r6.f1618d
            boolean r0 = r0.g()
            if (r0 != 0) goto L15
            return
        L15:
            a.p.e.a r0 = r6.f1618d
            int r0 = r0.g
            r0 = r0 & 4
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L21
            r0 = r3
            goto L22
        L21:
            r0 = r2
        L22:
            if (r0 == 0) goto L7e
            a.p.e.a r0 = r6.f1618d
            int r0 = r0.g
            r0 = r0 & 11
            if (r0 == 0) goto L2e
            r0 = r3
            goto L2f
        L2e:
            r0 = r2
        L2f:
            if (r0 != 0) goto L7e
            java.lang.String r0 = "RV PartialInvalidate"
            android.os.Trace.beginSection(r0)
            r6.o0()
            r6.V()
            a.p.e.a r0 = r6.f1618d
            r0.j()
            boolean r0 = r6.w
            if (r0 != 0) goto L77
            a.p.e.c r0 = r6.f1619e
            int r0 = r0.e()
            r1 = r2
        L4c:
            if (r1 >= r0) goto L6c
            a.p.e.c r4 = r6.f1619e
            android.view.View r4 = r4.d(r1)
            androidx.recyclerview.widget.RecyclerView$b0 r4 = K(r4)
            if (r4 == 0) goto L69
            boolean r5 = r4.u()
            if (r5 == 0) goto L61
            goto L69
        L61:
            boolean r4 = r4.p()
            if (r4 == 0) goto L69
            r2 = r3
            goto L6c
        L69:
            int r1 = r1 + 1
            goto L4c
        L6c:
            if (r2 == 0) goto L72
            r6.q()
            goto L77
        L72:
            a.p.e.a r0 = r6.f1618d
            r0.b()
        L77:
            r6.q0(r3)
            r6.W(r3)
            goto L8c
        L7e:
            a.p.e.a r0 = r6.f1618d
            boolean r0 = r0.g()
            if (r0 == 0) goto L8f
            android.os.Trace.beginSection(r1)
            r6.q()
        L8c:
            android.os.Trace.endSection()
        L8f:
            return
        L90:
            android.os.Trace.beginSection(r1)
            r6.q()
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.n():void");
    }

    public void n0(int i2) {
        if (this.x) {
            return;
        }
        m mVar = this.m;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            mVar.W0(this, this.e0, i2);
        }
    }

    public void o(int i2, int i3) {
        setMeasuredDimension(m.i(i2, getPaddingRight() + getPaddingLeft(), a.h.k.q.r(this)), m.i(i3, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public void o0() {
        int i2 = this.v + 1;
        this.v = i2;
        if (i2 != 1 || this.x) {
            return;
        }
        this.w = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.F = 0;
        this.r = true;
        this.u = this.u && !isLayoutRequested();
        m mVar = this.m;
        if (mVar != null) {
            mVar.i = true;
            mVar.f0();
        }
        this.k0 = false;
        a.p.e.n nVar = a.p.e.n.f1216e.get();
        this.c0 = nVar;
        if (nVar == null) {
            this.c0 = new a.p.e.n();
            Display displayK = a.h.k.q.k(this);
            float f2 = 60.0f;
            if (!isInEditMode() && displayK != null) {
                float refreshRate = displayK.getRefreshRate();
                if (refreshRate >= 30.0f) {
                    f2 = refreshRate;
                }
            }
            a.p.e.n nVar2 = this.c0;
            nVar2.f1220c = (long) (1.0E9f / f2);
            a.p.e.n.f1216e.set(nVar2);
        }
        this.c0.f1218a.add(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j jVar = this.M;
        if (jVar != null) {
            jVar.g();
        }
        s0();
        this.r = false;
        m mVar = this.m;
        if (mVar != null) {
            t tVar = this.f1616b;
            mVar.i = false;
            mVar.h0(this, tVar);
        }
        this.s0.clear();
        removeCallbacks(this.t0);
        if (this.f1620f == null) {
            throw null;
        }
        while (f0.a.f1168d.a() != null) {
        }
        a.p.e.n nVar = this.c0;
        if (nVar != null) {
            nVar.f1218a.remove(this);
            this.c0 = null;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.o.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.o.get(i2).g(canvas, this, this.e0);
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
            androidx.recyclerview.widget.RecyclerView$m r0 = r5.m
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.x
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
            androidx.recyclerview.widget.RecyclerView$m r0 = r5.m
            boolean r0 = r0.g()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.m
            boolean r3 = r3.f()
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
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.m
            boolean r3 = r3.g()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$m r3 = r5.m
            boolean r3 = r3.f()
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
            float r2 = r5.Y
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.Z
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.i0(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (this.x) {
            return false;
        }
        this.q = null;
        if (D(motionEvent)) {
            j();
            return true;
        }
        m mVar = this.m;
        if (mVar == null) {
            return false;
        }
        boolean zF = mVar.f();
        boolean zG = this.m.g();
        if (this.P == null) {
            this.P = VelocityTracker.obtain();
        }
        this.P.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.y) {
                this.y = false;
            }
            this.O = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.S = x2;
            this.Q = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.T = y2;
            this.R = y2;
            if (this.N == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                r0(1);
            }
            int[] iArr = this.q0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zF ? 1 : 0;
            if (zG) {
                i2 |= 2;
            }
            p0(i2, 0);
        } else if (actionMasked == 1) {
            this.P.clear();
            r0(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.O);
            if (iFindPointerIndex < 0) {
                StringBuilder sbD = b.a.a.a.a.d("Error processing scroll; pointer index for id ");
                sbD.append(this.O);
                sbD.append(" not found. Did any MotionEvents get skipped?");
                Log.e("RecyclerView", sbD.toString());
                return false;
            }
            int x3 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.N != 1) {
                int i3 = x3 - this.Q;
                int i4 = y3 - this.R;
                if (!zF || Math.abs(i3) <= this.U) {
                    z2 = false;
                } else {
                    this.S = x3;
                    z2 = true;
                }
                if (zG && Math.abs(i4) > this.U) {
                    this.T = y3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            j();
        } else if (actionMasked == 5) {
            this.O = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.S = x4;
            this.Q = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.T = y4;
            this.R = y4;
        } else if (actionMasked == 6) {
            X(motionEvent);
        }
        return this.N == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Trace.beginSection("RV OnLayout");
        q();
        Trace.endSection();
        this.u = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        m mVar = this.m;
        if (mVar == null) {
            o(i2, i3);
            return;
        }
        boolean z2 = false;
        if (mVar.W()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.m.w0(i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.l == null) {
                return;
            }
            if (this.e0.f1692d == 1) {
                r();
            }
            this.m.P0(i2, i3);
            this.e0.i = true;
            s();
            this.m.R0(i2, i3);
            if (this.m.U0()) {
                this.m.P0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.e0.i = true;
                s();
                this.m.R0(i2, i3);
                return;
            }
            return;
        }
        if (this.s) {
            this.m.w0(i2, i3);
            return;
        }
        if (this.A) {
            o0();
            V();
            b0();
            W(true);
            y yVar = this.e0;
            if (yVar.k) {
                yVar.g = true;
            } else {
                this.f1618d.c();
                this.e0.g = false;
            }
            this.A = false;
            q0(false);
        } else if (this.e0.k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        e eVar = this.l;
        if (eVar != null) {
            this.e0.f1693e = eVar.a();
        } else {
            this.e0.f1693e = 0;
        }
        o0();
        this.m.w0(i2, i3);
        q0(false);
        this.e0.g = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (P()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof w)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        w wVar = (w) parcelable;
        this.f1617c = wVar;
        super.onRestoreInstanceState(wVar.f892a);
        m mVar = this.m;
        if (mVar == null || (parcelable2 = this.f1617c.f1676c) == null) {
            return;
        }
        mVar.z0(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        w wVar = new w(super.onSaveInstanceState());
        w wVar2 = this.f1617c;
        if (wVar2 != null) {
            wVar.f1676c = wVar2.f1676c;
        } else {
            m mVar = this.m;
            wVar.f1676c = mVar != null ? mVar.A0() : null;
        }
        return wVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        O();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:154:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f5 A[PHI: r8
  0x00f5: PHI (r8v20 int) = (r8v16 int), (r8v24 int) binds: [B:50:0x00e0, B:55:0x00f1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v13 */
    /* JADX WARN: Type inference failed for: r21v14 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [int] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14, types: [androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r8v8, types: [a.p.e.d0, a.p.e.y] */
    /* JADX WARN: Type inference failed for: r9v4, types: [androidx.recyclerview.widget.RecyclerView$m] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 1009
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void p(View view) {
        b0 b0VarK = K(view);
        U();
        e eVar = this.l;
        if (eVar != null && b0VarK != null && eVar == null) {
            throw null;
        }
        List<o> list = this.C;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.C.get(size).a(view);
            }
        }
    }

    public boolean p0(int i2, int i3) {
        return getScrollingChildHelper().i(i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void q() {
        /*
            Method dump skipped, instruction units count: 985
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.q():void");
    }

    public void q0(boolean z2) {
        if (this.v < 1) {
            this.v = 1;
        }
        if (!z2 && !this.x) {
            this.w = false;
        }
        if (this.v == 1) {
            if (z2 && this.w && !this.x && this.m != null && this.l != null) {
                q();
            }
            if (!this.x) {
                this.w = false;
            }
        }
        this.v--;
    }

    public final void r() {
        int id;
        View viewC;
        this.e0.a(1);
        B(this.e0);
        this.e0.i = false;
        o0();
        f0 f0Var = this.f1620f;
        f0Var.f1166a.clear();
        f0Var.f1167b.a();
        V();
        b0();
        View focusedChild = (this.a0 && hasFocus() && this.l != null) ? getFocusedChild() : null;
        b0 b0VarJ = (focusedChild == null || (viewC = C(focusedChild)) == null) ? null : J(viewC);
        if (b0VarJ == null) {
            y yVar = this.e0;
            yVar.m = -1L;
            yVar.l = -1;
            yVar.n = -1;
        } else {
            this.e0.m = this.l.f1637b ? b0VarJ.f1633e : -1L;
            this.e0.l = this.D ? -1 : b0VarJ.m() ? b0VarJ.f1632d : b0VarJ.e();
            y yVar2 = this.e0;
            View focusedChild2 = b0VarJ.f1629a;
            loop3: while (true) {
                id = focusedChild2.getId();
                while (!focusedChild2.isFocused() && (focusedChild2 instanceof ViewGroup) && focusedChild2.hasFocus()) {
                    focusedChild2 = ((ViewGroup) focusedChild2).getFocusedChild();
                    if (focusedChild2.getId() != -1) {
                        break;
                    }
                }
            }
            yVar2.n = id;
        }
        y yVar3 = this.e0;
        yVar3.h = yVar3.j && this.i0;
        this.i0 = false;
        this.h0 = false;
        y yVar4 = this.e0;
        yVar4.g = yVar4.k;
        yVar4.f1693e = this.l.a();
        E(this.n0);
        if (this.e0.j) {
            int iE = this.f1619e.e();
            for (int i2 = 0; i2 < iE; i2++) {
                b0 b0VarK = K(this.f1619e.d(i2));
                if (!b0VarK.u() && (!b0VarK.k() || this.l.f1637b)) {
                    j jVar = this.M;
                    j.b(b0VarK);
                    b0VarK.g();
                    this.f1620f.c(b0VarK, jVar.i(b0VarK));
                    if (this.e0.h && b0VarK.p() && !b0VarK.m() && !b0VarK.u() && !b0VarK.k()) {
                        this.f1620f.f1167b.f(I(b0VarK), b0VarK);
                    }
                }
            }
        }
        if (this.e0.k) {
            int iH = this.f1619e.h();
            for (int i3 = 0; i3 < iH; i3++) {
                b0 b0VarK2 = K(this.f1619e.g(i3));
                if (!b0VarK2.u() && b0VarK2.f1632d == -1) {
                    b0VarK2.f1632d = b0VarK2.f1631c;
                }
            }
            y yVar5 = this.e0;
            boolean z2 = yVar5.f1694f;
            yVar5.f1694f = false;
            this.m.u0(this.f1616b, yVar5);
            this.e0.f1694f = z2;
            for (int i4 = 0; i4 < this.f1619e.e(); i4++) {
                b0 b0VarK3 = K(this.f1619e.d(i4));
                if (!b0VarK3.u()) {
                    f0.a orDefault = this.f1620f.f1166a.getOrDefault(b0VarK3, null);
                    if (!((orDefault == null || (orDefault.f1169a & 4) == 0) ? false : true)) {
                        j.b(b0VarK3);
                        boolean zH = b0VarK3.h(8192);
                        j jVar2 = this.M;
                        b0VarK3.g();
                        j.c cVarI = jVar2.i(b0VarK3);
                        if (zH) {
                            d0(b0VarK3, cVarI);
                        } else {
                            f0 f0Var2 = this.f1620f;
                            f0.a orDefault2 = f0Var2.f1166a.getOrDefault(b0VarK3, null);
                            if (orDefault2 == null) {
                                orDefault2 = f0.a.a();
                                f0Var2.f1166a.put(b0VarK3, orDefault2);
                            }
                            orDefault2.f1169a |= 2;
                            orDefault2.f1170b = cVarI;
                        }
                    }
                }
            }
        }
        l();
        W(true);
        q0(false);
        this.e0.f1692d = 2;
    }

    public void r0(int i2) {
        getScrollingChildHelper().j(i2);
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z2) {
        b0 b0VarK = K(view);
        if (b0VarK != null) {
            if (b0VarK.o()) {
                b0VarK.j &= -257;
            } else if (!b0VarK.u()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(b0VarK);
                throw new IllegalArgumentException(b.a.a.a.a.b(this, sb));
            }
        }
        view.clearAnimation();
        p(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.m.y0(this, view, view2) && view2 != null) {
            g0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.m.J0(this, view, rect, z2, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.p.get(i2).b(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.v != 0 || this.x) {
            this.w = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s() {
        o0();
        V();
        this.e0.a(6);
        this.f1618d.c();
        this.e0.f1693e = this.l.a();
        y yVar = this.e0;
        yVar.f1691c = 0;
        yVar.g = false;
        this.m.u0(this.f1616b, yVar);
        y yVar2 = this.e0;
        yVar2.f1694f = false;
        this.f1617c = null;
        yVar2.j = yVar2.j && this.M != null;
        this.e0.f1692d = 4;
        W(true);
        q0(false);
    }

    public void s0() {
        x xVar;
        setScrollState(0);
        this.b0.c();
        m mVar = this.m;
        if (mVar == null || (xVar = mVar.g) == null) {
            return;
        }
        xVar.e();
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        m mVar = this.m;
        if (mVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.x) {
            return;
        }
        boolean zF = mVar.f();
        boolean zG = this.m.g();
        if (zF || zG) {
            if (!zF) {
                i2 = 0;
            }
            if (!zG) {
                i3 = 0;
            }
            i0(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (P()) {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.z |= contentChangeTypes != 0 ? contentChangeTypes : 0;
            i = 1;
        }
        if (i != 0) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(a.p.e.b0 b0Var) {
        this.l0 = b0Var;
        a.h.k.q.U(this, b0Var);
    }

    public void setAdapter(e eVar) {
        setLayoutFrozen(false);
        e eVar2 = this.l;
        if (eVar2 != null) {
            eVar2.f1636a.unregisterObserver(this.f1615a);
            this.l.i(this);
        }
        e0();
        a.p.e.a aVar = this.f1618d;
        aVar.l(aVar.f1130b);
        aVar.l(aVar.f1131c);
        aVar.g = 0;
        e eVar3 = this.l;
        this.l = eVar;
        if (eVar != null) {
            eVar.f1636a.registerObserver(this.f1615a);
            eVar.e(this);
        }
        m mVar = this.m;
        if (mVar != null) {
            mVar.d0();
        }
        t tVar = this.f1616b;
        e eVar4 = this.l;
        tVar.b();
        s sVarD = tVar.d();
        if (sVarD == null) {
            throw null;
        }
        if (eVar3 != null) {
            sVarD.f1664b--;
        }
        if (sVarD.f1664b == 0) {
            for (int i2 = 0; i2 < sVarD.f1663a.size(); i2++) {
                sVarD.f1663a.valueAt(i2).f1665a.clear();
            }
        }
        if (eVar4 != null) {
            sVarD.f1664b++;
        }
        this.e0.f1694f = true;
        c0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(h hVar) {
        if (hVar == this.m0) {
            return;
        }
        this.m0 = hVar;
        setChildrenDrawingOrderEnabled(hVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.g) {
            O();
        }
        this.g = z2;
        super.setClipToPadding(z2);
        if (this.u) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(i iVar) {
        if (iVar == null) {
            throw null;
        }
        this.H = iVar;
        O();
    }

    public void setHasFixedSize(boolean z2) {
        this.s = z2;
    }

    public void setItemAnimator(j jVar) {
        j jVar2 = this.M;
        if (jVar2 != null) {
            jVar2.g();
            this.M.f1638a = null;
        }
        this.M = jVar;
        if (jVar != null) {
            jVar.f1638a = this.j0;
        }
    }

    public void setItemViewCacheSize(int i2) {
        t tVar = this.f1616b;
        tVar.f1673e = i2;
        tVar.l();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z2) {
        suppressLayout(z2);
    }

    public void setLayoutManager(m mVar) {
        if (mVar == this.m) {
            return;
        }
        s0();
        if (this.m != null) {
            j jVar = this.M;
            if (jVar != null) {
                jVar.g();
            }
            this.m.E0(this.f1616b);
            this.m.F0(this.f1616b);
            this.f1616b.b();
            if (this.r) {
                m mVar2 = this.m;
                t tVar = this.f1616b;
                mVar2.i = false;
                mVar2.h0(this, tVar);
            }
            this.m.S0(null);
            this.m = null;
        } else {
            this.f1616b.b();
        }
        a.p.e.c cVar = this.f1619e;
        c.a aVar = cVar.f1145b;
        aVar.f1147a = 0L;
        c.a aVar2 = aVar.f1148b;
        if (aVar2 != null) {
            aVar2.g();
        }
        int size = cVar.f1146c.size();
        while (true) {
            size--;
            if (size < 0) {
                a.p.e.z zVar = (a.p.e.z) cVar.f1144a;
                int iB = zVar.b();
                for (int i2 = 0; i2 < iB; i2++) {
                    View viewA = zVar.a(i2);
                    zVar.f1269a.p(viewA);
                    viewA.clearAnimation();
                }
                zVar.f1269a.removeAllViews();
                this.m = mVar;
                if (mVar != null) {
                    if (mVar.f1648b != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("LayoutManager ");
                        sb.append(mVar);
                        sb.append(" is already attached to a RecyclerView:");
                        throw new IllegalArgumentException(b.a.a.a.a.b(mVar.f1648b, sb));
                    }
                    mVar.S0(this);
                    if (this.r) {
                        m mVar3 = this.m;
                        mVar3.i = true;
                        mVar3.f0();
                    }
                }
                this.f1616b.l();
                requestLayout();
                return;
            }
            c.b bVar = cVar.f1144a;
            View view = cVar.f1146c.get(size);
            a.p.e.z zVar2 = (a.p.e.z) bVar;
            if (zVar2 == null) {
                throw null;
            }
            b0 b0VarK = K(view);
            if (b0VarK != null) {
                zVar2.f1269a.l0(b0VarK, b0VarK.p);
                b0VarK.p = 0;
            }
            cVar.f1146c.remove(size);
        }
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
    public void setNestedScrollingEnabled(boolean z2) {
        a.h.k.g scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.f817d) {
            a.h.k.q.g0(scrollingChildHelper.f816c);
        }
        scrollingChildHelper.f817d = z2;
    }

    public void setOnFlingListener(p pVar) {
        this.V = pVar;
    }

    @Deprecated
    public void setOnScrollListener(r rVar) {
        this.f0 = rVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.a0 = z2;
    }

    public void setRecycledViewPool(s sVar) {
        t tVar = this.f1616b;
        if (tVar.g != null) {
            r0.f1664b--;
        }
        tVar.g = sVar;
        if (sVar == null || RecyclerView.this.getAdapter() == null) {
            return;
        }
        tVar.g.f1664b++;
    }

    public void setRecyclerListener(u uVar) {
        this.n = uVar;
    }

    public void setScrollState(int i2) {
        x xVar;
        if (i2 == this.N) {
            return;
        }
        this.N = i2;
        if (i2 != 2) {
            this.b0.c();
            m mVar = this.m;
            if (mVar != null && (xVar = mVar.g) != null) {
                xVar.e();
            }
        }
        m mVar2 = this.m;
        if (mVar2 != null) {
            mVar2.B0(i2);
        }
        Y();
        r rVar = this.f0;
        if (rVar != null) {
            rVar.a(this, i2);
        }
        List<r> list = this.g0;
        if (list == null) {
            return;
        }
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            } else {
                this.g0.get(size).a(this, i2);
            }
        }
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
        this.U = scaledTouchSlop;
    }

    public void setViewCacheExtension(z zVar) {
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().i(i2, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().j(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.x) {
            i("Do not suppressLayout in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.x = true;
                this.y = true;
                s0();
                return;
            }
            this.x = false;
            if (this.w && this.m != null && this.l != null) {
                requestLayout();
            }
            this.w = false;
        }
    }

    public boolean t(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().c(i2, i3, iArr, null, i4);
    }

    public final void u(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().f(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void v(int i2, int i3) {
        this.G++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        Z();
        r rVar = this.f0;
        if (rVar != null) {
            rVar.b(this, i2, i3);
        }
        List<r> list = this.g0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.g0.get(size).b(this, i2, i3);
            }
        }
        this.G--;
    }

    public void w() {
        if (this.L != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this);
        this.L = edgeEffectA;
        if (this.g) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void x() {
        if (this.I != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this);
        this.I = edgeEffectA;
        if (this.g) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void y() {
        if (this.K != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this);
        this.K = edgeEffectA;
        if (this.g) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void z() {
        if (this.J != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.H.a(this);
        this.J = edgeEffectA;
        if (this.g) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }
}
