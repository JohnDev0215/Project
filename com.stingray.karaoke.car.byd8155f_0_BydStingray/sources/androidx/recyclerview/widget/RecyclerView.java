package androidx.recyclerview.widget;

import A.s1;
import C7.a;
import F0.U0;
import F1.A;
import F1.C;
import F1.C0251j;
import F1.C0254m;
import F1.E;
import F1.H;
import L1.c;
import N0.q;
import android.R;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
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
import androidx.core.view.ScrollingView;
import com.bumptech.glide.d;
import h3.y;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import k3.C1648i0;
import l3.C1764u;
import l9.n;
import m2.b;
import n7.AbstractC1999n;
import r3.AbstractC2287a;
import s3.AbstractC2363B;
import s3.AbstractC2364C;
import s3.AbstractC2367F;
import s3.AbstractC2369H;
import s3.AbstractC2370I;
import s3.AbstractC2379S;
import s3.AbstractC2405y;
import s3.C2368G;
import s3.C2371J;
import s3.C2372K;
import s3.C2374M;
import s3.C2377P;
import s3.C2378Q;
import s3.C2381a;
import s3.C2388h;
import s3.C2392l;
import s3.C2393m;
import s3.C2402v;
import s3.C2404x;
import s3.InterfaceC2362A;
import s3.InterfaceC2373L;
import s3.RunnableC2380T;
import s3.RunnableC2389i;
import s3.RunnableC2395o;
import s3.U;
import s3.W;
import s3.c0;
import u.C2458F;
import u.C2472l;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements ScrollingView {

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public static final int[] f12090H0 = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public static final float f12091I0 = (float) (Math.log(0.78d) / Math.log(0.9d));

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public static final boolean f12092J0 = true;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public static final boolean f12093K0 = true;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public static final Class[] f12094L0;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public static final c f12095M0;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public static final C2378Q f12096N0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final ArrayList f12097A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final int[] f12098A0;
    public C2392l B;
    public final ArrayList B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f12099C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public final RunnableC2389i f12100C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f12101D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public boolean f12102D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f12103E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public int f12104E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f12105F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public int f12106F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f12107G;
    public final C2404x G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f12108H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f12109I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f12110J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final AccessibilityManager f12111K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f12112L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f12113M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f12114N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f12115O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public AbstractC2363B f12116P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public EdgeEffect f12117Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public EdgeEffect f12118R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public EdgeEffect f12119S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public EdgeEffect f12120T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public AbstractC2364C f12121U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f12122V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f12123W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public VelocityTracker f12124a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f12125b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f12126c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f12127d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f12128e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f12129f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final int f12130g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final int f12131h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final float f12132i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final float f12133j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f12134k0;
    public final float l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final RunnableC2380T f12135l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final n f12136m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public RunnableC2395o f12137m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1648i0 f12138n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final C2393m f12139n0;
    public C2374M o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final C2377P f12140o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final q f12141p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public AbstractC2370I f12142p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final y f12143q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public ArrayList f12144q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final b f12145r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public boolean f12146r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12147s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public boolean f12148s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f12149t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final C2404x f12150t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Rect f12151u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f12152u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final RectF f12153v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public W f12154v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AbstractC2405y f12155w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public final int[] f12156w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public AbstractC2367F f12157x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public C0251j f12158x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ArrayList f12159y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public final int[] f12160y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ArrayList f12161z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final int[] f12162z0;

    static {
        Class cls = Integer.TYPE;
        f12094L0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        f12095M0 = new c(1);
        f12096N0 = new C2378Q();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.stingray.drivemodedetector.R.attr.recyclerViewStyle);
    }

    public static RecyclerView B(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            RecyclerView recyclerViewB = B(viewGroup.getChildAt(i7));
            if (recyclerViewB != null) {
                return recyclerViewB;
            }
        }
        return null;
    }

    public static U G(View view) {
        if (view == null) {
            return null;
        }
        return ((C2368G) view.getLayoutParams()).f22190a;
    }

    public static void g(U u4) {
        WeakReference weakReference = u4.f22220b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == u4.f22219a) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            u4.f22220b = null;
        }
    }

    private C0251j getScrollingChildHelper() {
        if (this.f12158x0 == null) {
            this.f12158x0 = new C0251j(this);
        }
        return this.f12158x0;
    }

    public static int j(int i7, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i10) {
        if (i7 > 0 && edgeEffect != null && d.C(edgeEffect) != 0.0f) {
            int iRound = Math.round(d.K(edgeEffect, ((-i7) * 4.0f) / i10, 0.5f) * ((-i10) / 4.0f));
            if (iRound != i7) {
                edgeEffect.finish();
            }
            return i7 - iRound;
        }
        if (i7 >= 0 || edgeEffect2 == null || d.C(edgeEffect2) == 0.0f) {
            return i7;
        }
        float f10 = i10;
        int iRound2 = Math.round(d.K(edgeEffect2, (i7 * 4.0f) / f10, 0.5f) * (f10 / 4.0f));
        if (iRound2 != i7) {
            edgeEffect2.finish();
        }
        return i7 - iRound2;
    }

    public final void A(int[] iArr) {
        int iO = this.f12143q.o();
        if (iO == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i7 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        for (int i11 = 0; i11 < iO; i11++) {
            U uG = G(this.f12143q.n(i11));
            if (!uG.p()) {
                int iB = uG.b();
                if (iB < i7) {
                    i7 = iB;
                }
                if (iB > i10) {
                    i10 = iB;
                }
            }
        }
        iArr[0] = i7;
        iArr[1] = i10;
    }

    public final U C(int i7) {
        U u4 = null;
        if (this.f12112L) {
            return null;
        }
        int iA = this.f12143q.A();
        for (int i10 = 0; i10 < iA; i10++) {
            U uG = G(this.f12143q.z(i10));
            if (uG != null && !uG.i() && D(uG) == i7) {
                if (!((ArrayList) this.f12143q.o).contains(uG.f22219a)) {
                    return uG;
                }
                u4 = uG;
            }
        }
        return u4;
    }

    public final int D(U u4) {
        if (u4.d(524) || !u4.f()) {
            return -1;
        }
        q qVar = this.f12141p;
        int i7 = u4.c;
        ArrayList arrayList = (ArrayList) qVar.l;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            C2381a c2381a = (C2381a) arrayList.get(i10);
            int i11 = c2381a.f22244a;
            if (i11 != 1) {
                if (i11 == 2) {
                    int i12 = c2381a.f22245b;
                    if (i12 <= i7) {
                        int i13 = c2381a.c;
                        if (i12 + i13 > i7) {
                            return -1;
                        }
                        i7 -= i13;
                    } else {
                        continue;
                    }
                } else if (i11 == 8) {
                    int i14 = c2381a.f22245b;
                    if (i14 == i7) {
                        i7 = c2381a.c;
                    } else {
                        if (i14 < i7) {
                            i7--;
                        }
                        if (c2381a.c <= i7) {
                            i7++;
                        }
                    }
                }
            } else if (c2381a.f22245b <= i7) {
                i7 += c2381a.c;
            }
        }
        return i7;
    }

    public final long E(U u4) {
        return this.f12155w.f22364b ? u4.f22221e : u4.c;
    }

    public final U F(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return G(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public final Rect H(View view) {
        C2368G c2368g = (C2368G) view.getLayoutParams();
        boolean z6 = c2368g.c;
        Rect rect = c2368g.f22191b;
        if (!z6) {
            return rect;
        }
        if (this.f12140o0.f22206g && (c2368g.f22190a.l() || c2368g.f22190a.g())) {
            return rect;
        }
        rect.set(0, 0, 0, 0);
        ArrayList arrayList = this.f12161z;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Rect rect2 = this.f12149t;
            rect2.set(0, 0, 0, 0);
            ((C2392l) arrayList.get(i7)).getClass();
            ((C2368G) view.getLayoutParams()).f22190a.getClass();
            rect2.set(0, 0, 0, 0);
            rect.left += rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        c2368g.c = false;
        return rect;
    }

    public final boolean I() {
        return !this.f12103E || this.f12112L || this.f12141p.E();
    }

    public final boolean J() {
        return this.f12114N > 0;
    }

    public final void K(int i7) {
        if (this.f12157x == null) {
            return;
        }
        setScrollState(2);
        this.f12157x.k0(i7);
        awakenScrollBars();
    }

    public final void L() {
        int iA = this.f12143q.A();
        for (int i7 = 0; i7 < iA; i7++) {
            ((C2368G) this.f12143q.z(i7).getLayoutParams()).c = true;
        }
        ArrayList arrayList = (ArrayList) this.f12138n.f17995f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            C2368G c2368g = (C2368G) ((U) arrayList.get(i10)).f22219a.getLayoutParams();
            if (c2368g != null) {
                c2368g.c = true;
            }
        }
    }

    public final void M(int i7, int i10, boolean z6) {
        int i11 = i7 + i10;
        int iA = this.f12143q.A();
        for (int i12 = 0; i12 < iA; i12++) {
            U uG = G(this.f12143q.z(i12));
            if (uG != null && !uG.p()) {
                int i13 = uG.c;
                C2377P c2377p = this.f12140o0;
                if (i13 >= i11) {
                    uG.m(-i10, z6);
                    c2377p.f22205f = true;
                } else if (i13 >= i7) {
                    uG.a(8);
                    uG.m(-i10, z6);
                    uG.c = i7 - 1;
                    c2377p.f22205f = true;
                }
            }
        }
        C1648i0 c1648i0 = this.f12138n;
        ArrayList arrayList = (ArrayList) c1648i0.f17995f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            U u4 = (U) arrayList.get(size);
            if (u4 != null) {
                int i14 = u4.c;
                if (i14 >= i11) {
                    u4.m(-i10, z6);
                } else if (i14 >= i7) {
                    u4.a(8);
                    c1648i0.g(size);
                }
            }
        }
        requestLayout();
    }

    public final void N() {
        this.f12114N++;
    }

    public final void O(boolean z6) {
        int i7;
        AccessibilityManager accessibilityManager;
        int i10 = this.f12114N - 1;
        this.f12114N = i10;
        if (i10 < 1) {
            this.f12114N = 0;
            if (z6) {
                int i11 = this.f12110J;
                this.f12110J = 0;
                if (i11 != 0 && (accessibilityManager = this.f12111K) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i11);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                ArrayList arrayList = this.B0;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    U u4 = (U) arrayList.get(size);
                    if (u4.f22219a.getParent() == this && !u4.p() && (i7 = u4.f22231q) != -1) {
                        int[] iArr = H.f2303a;
                        u4.f22219a.setImportantForAccessibility(i7);
                        u4.f22231q = -1;
                    }
                }
                arrayList.clear();
            }
        }
    }

    public final void P(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f12123W) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.f12123W = motionEvent.getPointerId(i7);
            int x8 = (int) (motionEvent.getX(i7) + 0.5f);
            this.f12127d0 = x8;
            this.f12125b0 = x8;
            int y10 = (int) (motionEvent.getY(i7) + 0.5f);
            this.f12128e0 = y10;
            this.f12126c0 = y10;
        }
    }

    public final void Q() {
        if (this.f12152u0 || !this.f12099C) {
            return;
        }
        int[] iArr = H.f2303a;
        postOnAnimation(this.f12100C0);
        this.f12152u0 = true;
    }

    public final void R(U u4, C0254m c0254m) {
        u4.f22226j &= -8193;
        boolean z6 = this.f12140o0.f22207h;
        b bVar = this.f12145r;
        if (z6 && u4.l() && !u4.i() && !u4.p()) {
            ((C2472l) bVar.f18945m).e(E(u4), u4);
        }
        C2458F c2458f = (C2458F) bVar.l;
        c0 c0VarA = (c0) c2458f.get(u4);
        if (c0VarA == null) {
            c0VarA = c0.a();
            c2458f.put(u4, c0VarA);
        }
        c0VarA.f22265b = c0254m;
        c0VarA.f22264a |= 4;
    }

    public final int S(float f10, int i7) {
        float height = f10 / getHeight();
        float width = i7 / getWidth();
        EdgeEffect edgeEffect = this.f12117Q;
        float f11 = 0.0f;
        if (edgeEffect == null || d.C(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.f12119S;
            if (edgeEffect2 != null && d.C(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.f12119S.onRelease();
                } else {
                    float fK = d.K(this.f12119S, width, height);
                    if (d.C(this.f12119S) == 0.0f) {
                        this.f12119S.onRelease();
                    }
                    f11 = fK;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.f12117Q.onRelease();
            } else {
                float f12 = -d.K(this.f12117Q, -width, 1.0f - height);
                if (d.C(this.f12117Q) == 0.0f) {
                    this.f12117Q.onRelease();
                }
                f11 = f12;
            }
            invalidate();
        }
        return Math.round(f11 * getWidth());
    }

    public final int T(float f10, int i7) {
        float width = f10 / getWidth();
        float height = i7 / getHeight();
        EdgeEffect edgeEffect = this.f12118R;
        float f11 = 0.0f;
        if (edgeEffect == null || d.C(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.f12120T;
            if (edgeEffect2 != null && d.C(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.f12120T.onRelease();
                } else {
                    float fK = d.K(this.f12120T, height, 1.0f - width);
                    if (d.C(this.f12120T) == 0.0f) {
                        this.f12120T.onRelease();
                    }
                    f11 = fK;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.f12118R.onRelease();
            } else {
                float f12 = -d.K(this.f12118R, -height, width);
                if (d.C(this.f12118R) == 0.0f) {
                    this.f12118R.onRelease();
                }
                f11 = f12;
            }
            invalidate();
        }
        return Math.round(f11 * getHeight());
    }

    public final void U(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        int width = view3.getWidth();
        int height = view3.getHeight();
        Rect rect = this.f12149t;
        rect.set(0, 0, width, height);
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C2368G) {
            C2368G c2368g = (C2368G) layoutParams;
            if (!c2368g.c) {
                int i7 = rect.left;
                Rect rect2 = c2368g.f22191b;
                rect.left = i7 - rect2.left;
                rect.right += rect2.right;
                rect.top -= rect2.top;
                rect.bottom += rect2.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, rect);
            offsetRectIntoDescendantCoords(view, rect);
        }
        this.f12157x.h0(this, view, this.f12149t, !this.f12103E, view2 == null);
    }

    public final void V() {
        VelocityTracker velocityTracker = this.f12124a0;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        c0(0);
        EdgeEffect edgeEffect = this.f12117Q;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.f12117Q.isFinished();
        }
        EdgeEffect edgeEffect2 = this.f12118R;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.f12118R.isFinished();
        }
        EdgeEffect edgeEffect3 = this.f12119S;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.f12119S.isFinished();
        }
        EdgeEffect edgeEffect4 = this.f12120T;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.f12120T.isFinished();
        }
        if (zIsFinished) {
            int[] iArr = H.f2303a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean W(int r19, int r20, android.view.MotionEvent r21, int r22) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.W(int, int, android.view.MotionEvent, int):boolean");
    }

    public final void X(int i7, int i10, int[] iArr) {
        U u4;
        a0();
        N();
        Trace.beginSection("RV Scroll");
        C2377P c2377p = this.f12140o0;
        x(c2377p);
        C1648i0 c1648i0 = this.f12138n;
        int iJ0 = i7 != 0 ? this.f12157x.j0(i7, c1648i0, c2377p) : 0;
        int iL0 = i10 != 0 ? this.f12157x.l0(i10, c1648i0, c2377p) : 0;
        Trace.endSection();
        int iO = this.f12143q.o();
        for (int i11 = 0; i11 < iO; i11++) {
            View viewN = this.f12143q.n(i11);
            U uF = F(viewN);
            if (uF != null && (u4 = uF.f22225i) != null) {
                int left = viewN.getLeft();
                int top = viewN.getTop();
                View view = u4.f22219a;
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        O(true);
        b0(false);
        if (iArr != null) {
            iArr[0] = iJ0;
            iArr[1] = iL0;
        }
    }

    public final boolean Y(EdgeEffect edgeEffect, int i7, int i10) {
        if (i7 > 0) {
            return true;
        }
        float fC = d.C(edgeEffect) * i10;
        float fAbs = Math.abs(-i7) * 0.35f;
        float f10 = this.l * 0.015f;
        double dLog = Math.log(fAbs / f10);
        double d = f12091I0;
        return ((float) (Math.exp((d / (d - 1.0d)) * dLog) * ((double) f10))) < fC;
    }

    public final void Z(int i7, int i10, boolean z6) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F == null) {
            h6.n.j("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f12108H) {
            return;
        }
        if (!abstractC2367F.d()) {
            i7 = 0;
        }
        if (!this.f12157x.e()) {
            i10 = 0;
        }
        if (i7 == 0 && i10 == 0) {
            return;
        }
        if (z6) {
            int i11 = i7 != 0 ? 1 : 0;
            if (i10 != 0) {
                i11 |= 2;
            }
            getScrollingChildHelper().g(i11, 1);
        }
        this.f12135l0.c(i7, i10, Integer.MIN_VALUE, null);
    }

    public final void a0() {
        int i7 = this.f12105F + 1;
        this.f12105F = i7;
        if (i7 != 1 || this.f12108H) {
            return;
        }
        this.f12107G = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i7, int i10) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null) {
            abstractC2367F.getClass();
        }
        super.addFocusables(arrayList, i7, i10);
    }

    public final void b0(boolean z6) {
        if (this.f12105F < 1) {
            this.f12105F = 1;
        }
        if (!z6 && !this.f12108H) {
            this.f12107G = false;
        }
        if (this.f12105F == 1) {
            if (z6 && this.f12107G && !this.f12108H && this.f12157x != null && this.f12155w != null) {
                m();
            }
            if (!this.f12108H) {
                this.f12107G = false;
            }
        }
        this.f12105F--;
    }

    public final void c0(int i7) {
        getScrollingChildHelper().h(i7);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C2368G) && this.f12157x.f((C2368G) layoutParams);
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollExtent() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.d()) {
            return this.f12157x.j(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollOffset() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.d()) {
            return this.f12157x.k(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollRange() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.d()) {
            return this.f12157x.l(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollExtent() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.e()) {
            return this.f12157x.m(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollOffset() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.e()) {
            return this.f12157x.n(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollRange() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && abstractC2367F.e()) {
            return this.f12157x.o(this.f12140o0);
        }
        return 0;
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f10, float f11, boolean z6) {
        return getScrollingChildHelper().a(f10, f11, z6);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f10, float f11) {
        return getScrollingChildHelper().b(f10, f11);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i7, int i10, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i7, i10, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i7, int i10, int i11, int i12, int[] iArr) {
        return getScrollingChildHelper().d(i7, i10, i11, i12, iArr, 0, null);
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
        boolean z6;
        super.draw(canvas);
        ArrayList arrayList = this.f12161z;
        int size = arrayList.size();
        boolean z10 = false;
        for (int i7 = 0; i7 < size; i7++) {
            C2392l c2392l = (C2392l) arrayList.get(i7);
            if (c2392l.f22310q != c2392l.f22312s.getWidth() || c2392l.f22311r != c2392l.f22312s.getHeight()) {
                c2392l.f22310q = c2392l.f22312s.getWidth();
                c2392l.f22311r = c2392l.f22312s.getHeight();
                c2392l.e(0);
            } else if (c2392l.f22297A != 0) {
                if (c2392l.f22313t) {
                    int i10 = c2392l.f22310q;
                    int i11 = c2392l.f22300e;
                    int i12 = i10 - i11;
                    int i13 = c2392l.l;
                    int i14 = c2392l.f22306k;
                    int i15 = i13 - (i14 / 2);
                    StateListDrawable stateListDrawable = c2392l.c;
                    stateListDrawable.setBounds(0, 0, i11, i14);
                    int i16 = c2392l.f22311r;
                    int i17 = c2392l.f22301f;
                    Drawable drawable = c2392l.d;
                    drawable.setBounds(0, 0, i17, i16);
                    RecyclerView recyclerView = c2392l.f22312s;
                    int[] iArr = H.f2303a;
                    if (recyclerView.getLayoutDirection() == 1) {
                        drawable.draw(canvas);
                        canvas.translate(i11, i15);
                        canvas.scale(-1.0f, 1.0f);
                        stateListDrawable.draw(canvas);
                        canvas.scale(-1.0f, 1.0f);
                        canvas.translate(-i11, -i15);
                    } else {
                        canvas.translate(i12, 0.0f);
                        drawable.draw(canvas);
                        canvas.translate(0.0f, i15);
                        stateListDrawable.draw(canvas);
                        canvas.translate(-i12, -i15);
                    }
                }
                if (c2392l.f22314u) {
                    int i18 = c2392l.f22311r;
                    int i19 = c2392l.f22304i;
                    int i20 = i18 - i19;
                    int i21 = c2392l.o;
                    int i22 = c2392l.f22308n;
                    int i23 = i21 - (i22 / 2);
                    StateListDrawable stateListDrawable2 = c2392l.f22302g;
                    stateListDrawable2.setBounds(0, 0, i22, i19);
                    int i24 = c2392l.f22310q;
                    int i25 = c2392l.f22305j;
                    Drawable drawable2 = c2392l.f22303h;
                    drawable2.setBounds(0, 0, i24, i25);
                    canvas.translate(0.0f, i20);
                    drawable2.draw(canvas);
                    canvas.translate(i23, 0.0f);
                    stateListDrawable2.draw(canvas);
                    canvas.translate(-i23, -i20);
                }
            }
        }
        EdgeEffect edgeEffect = this.f12117Q;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z6 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.f12147s ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.f12117Q;
            z6 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.f12118R;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.f12147s) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.f12118R;
            z6 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.f12119S;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f12147s ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.f12119S;
            z6 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.f12120T;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f12147s) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.f12120T;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z10 = true;
            }
            z6 |= z10;
            canvas.restoreToCount(iSave4);
        }
        if ((z6 || this.f12121U == null || arrayList.size() <= 0 || !this.f12121U.f()) ? z6 : true) {
            int[] iArr2 = H.f2303a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j3) {
        return super.drawChild(canvas, view, j3);
    }

    public final void e(U u4) {
        View view = u4.f22219a;
        boolean z6 = view.getParent() == this;
        this.f12138n.l(F(view));
        if (u4.k()) {
            this.f12143q.e(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z6) {
            this.f12143q.d(view, -1, true);
            return;
        }
        y yVar = this.f12143q;
        int iIndexOfChild = ((C2404x) yVar.f15499m).f22362a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            ((s1) yVar.f15500n).y(iIndexOfChild);
            yVar.C(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void f(String str) {
        if (J()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + w());
        }
        if (this.f12115O > 0) {
            h6.n.u("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + w()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007d  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null) {
            return abstractC2367F.r();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + w());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null) {
            return abstractC2367F.s(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + w());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public AbstractC2405y getAdapter() {
        return this.f12155w;
    }

    @Override // android.view.View
    public int getBaseline() {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F == null) {
            return super.getBaseline();
        }
        abstractC2367F.getClass();
        return -1;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i10) {
        return super.getChildDrawingOrder(i7, i10);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f12147s;
    }

    public W getCompatAccessibilityDelegate() {
        return this.f12154v0;
    }

    public AbstractC2363B getEdgeEffectFactory() {
        return this.f12116P;
    }

    public AbstractC2364C getItemAnimator() {
        return this.f12121U;
    }

    public int getItemDecorationCount() {
        return this.f12161z.size();
    }

    public AbstractC2367F getLayoutManager() {
        return this.f12157x;
    }

    public int getMaxFlingVelocity() {
        return this.f12131h0;
    }

    public int getMinFlingVelocity() {
        return this.f12130g0;
    }

    public long getNanoTime() {
        if (f12093K0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public AbstractC2369H getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f12134k0;
    }

    public C2372K getRecycledViewPool() {
        return this.f12138n.c();
    }

    public int getScrollState() {
        return this.f12122V;
    }

    public final void h() {
        int iA = this.f12143q.A();
        for (int i7 = 0; i7 < iA; i7++) {
            U uG = G(this.f12143q.z(i7));
            if (!uG.p()) {
                uG.d = -1;
                uG.f22223g = -1;
            }
        }
        C1648i0 c1648i0 = this.f12138n;
        ArrayList arrayList = (ArrayList) c1648i0.f17995f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            U u4 = (U) arrayList.get(i10);
            u4.d = -1;
            u4.f22223g = -1;
        }
        ArrayList arrayList2 = (ArrayList) c1648i0.d;
        int size2 = arrayList2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            U u10 = (U) arrayList2.get(i11);
            u10.d = -1;
            u10.f22223g = -1;
        }
        ArrayList arrayList3 = (ArrayList) c1648i0.f17994e;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i12 = 0; i12 < size3; i12++) {
                U u11 = (U) ((ArrayList) c1648i0.f17994e).get(i12);
                u11.d = -1;
                u11.f22223g = -1;
            }
        }
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().f(0);
    }

    public final void i(int i7, int i10) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.f12117Q;
        if (edgeEffect == null || edgeEffect.isFinished() || i7 <= 0) {
            zIsFinished = false;
        } else {
            this.f12117Q.onRelease();
            zIsFinished = this.f12117Q.isFinished();
        }
        EdgeEffect edgeEffect2 = this.f12119S;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i7 < 0) {
            this.f12119S.onRelease();
            zIsFinished |= this.f12119S.isFinished();
        }
        EdgeEffect edgeEffect3 = this.f12118R;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i10 > 0) {
            this.f12118R.onRelease();
            zIsFinished |= this.f12118R.isFinished();
        }
        EdgeEffect edgeEffect4 = this.f12120T;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i10 < 0) {
            this.f12120T.onRelease();
            zIsFinished |= this.f12120T.isFinished();
        }
        if (zIsFinished) {
            int[] iArr = H.f2303a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.f12099C;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.f12108H;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().d;
    }

    public final void k() {
        if (!this.f12103E || this.f12112L) {
            Trace.beginSection("RV FullInvalidate");
            m();
            Trace.endSection();
        } else if (this.f12141p.E()) {
            this.f12141p.getClass();
            if (this.f12141p.E()) {
                Trace.beginSection("RV FullInvalidate");
                m();
                Trace.endSection();
            }
        }
    }

    public final void l(int i7, int i10) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int[] iArr = H.f2303a;
        setMeasuredDimension(AbstractC2367F.g(i7, paddingRight, getMinimumWidth()), AbstractC2367F.g(i10, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x03a5  */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19, types: [int] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m() {
        /*
            Method dump skipped, instruction units count: 1033
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.m():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:251:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0226 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n() {
        /*
            Method dump skipped, instruction units count: 1336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.n():void");
    }

    public final void o() {
        a0();
        N();
        C2377P c2377p = this.f12140o0;
        c2377p.a(6);
        this.f12141p.v();
        c2377p.f22204e = this.f12155w.a();
        c2377p.c = 0;
        if (this.o != null) {
            AbstractC2405y abstractC2405y = this.f12155w;
            int iC = AbstractC2641j.c(abstractC2405y.c);
            if (iC == 1 ? abstractC2405y.a() > 0 : iC != 2) {
                Parcelable parcelable = this.o.f22196n;
                if (parcelable != null) {
                    this.f12157x.a0(parcelable);
                }
                this.o = null;
            }
        }
        c2377p.f22206g = false;
        this.f12157x.Y(this.f12138n, c2377p);
        c2377p.f22205f = false;
        c2377p.f22209j = c2377p.f22209j && this.f12121U != null;
        c2377p.d = 4;
        O(true);
        b0(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
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
            r5.f12114N = r0
            r1 = 1
            r5.f12099C = r1
            boolean r2 = r5.f12103E
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.f12103E = r2
            k3.i0 r2 = r5.f12138n
            r2.d()
            s3.F r2 = r5.f12157x
            if (r2 == 0) goto L23
            r2.f22183g = r1
        L23:
            r5.f12152u0 = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.f12093K0
            if (r0 == 0) goto L78
            java.lang.ThreadLocal r0 = s3.RunnableC2395o.f22325p
            java.lang.Object r1 = r0.get()
            s3.o r1 = (s3.RunnableC2395o) r1
            r5.f12137m0 = r1
            if (r1 != 0) goto L71
            s3.o r1 = new s3.o
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.l = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.o = r2
            r5.f12137m0 = r1
            int[] r1 = F1.H.f2303a
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L63
            if (r1 == 0) goto L63
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L63
            goto L65
        L63:
            r1 = 1114636288(0x42700000, float:60.0)
        L65:
            s3.o r2 = r5.f12137m0
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.f22328n = r3
            r0.set(r2)
        L71:
            s3.o r0 = r5.f12137m0
            java.util.ArrayList r0 = r0.l
            r0.add(r5)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        C1648i0 c1648i0;
        RunnableC2395o runnableC2395o;
        C2402v c2402v;
        super.onDetachedFromWindow();
        AbstractC2364C abstractC2364C = this.f12121U;
        if (abstractC2364C != null) {
            abstractC2364C.e();
        }
        int i7 = 0;
        setScrollState(0);
        RunnableC2380T runnableC2380T = this.f12135l0;
        runnableC2380T.f22217r.removeCallbacks(runnableC2380T);
        runnableC2380T.f22214n.abortAnimation();
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null && (c2402v = abstractC2367F.f22181e) != null) {
            c2402v.c();
        }
        this.f12099C = false;
        AbstractC2367F abstractC2367F2 = this.f12157x;
        if (abstractC2367F2 != null) {
            abstractC2367F2.f22183g = false;
            abstractC2367F2.N(this);
        }
        this.B0.clear();
        removeCallbacks(this.f12100C0);
        this.f12145r.getClass();
        while (c0.d.a() != null) {
        }
        int i10 = 0;
        while (true) {
            c1648i0 = this.f12138n;
            ArrayList arrayList = (ArrayList) c1648i0.f17995f;
            if (i10 >= arrayList.size()) {
                break;
            }
            a.z(((U) arrayList.get(i10)).f22219a);
            i10++;
        }
        c1648i0.e(((RecyclerView) c1648i0.f17997h).f12155w, false);
        while (i7 < getChildCount()) {
            int i11 = i7 + 1;
            View childAt = getChildAt(i7);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            ArrayList arrayList2 = a.L(childAt).f3048a;
            for (int iC = AbstractC1999n.C(arrayList2); -1 < iC; iC--) {
                ((U0) arrayList2.get(iC)).f2141a.c();
            }
            i7 = i11;
        }
        if (!f12093K0 || (runnableC2395o = this.f12137m0) == null) {
            return;
        }
        runnableC2395o.l.remove(this);
        this.f12137m0 = null;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.f12161z;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((C2392l) arrayList.get(i7)).getClass();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        boolean z10;
        if (this.f12108H) {
            return false;
        }
        this.B = null;
        if (z(motionEvent)) {
            V();
            setScrollState(0);
            return true;
        }
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F == null) {
            return false;
        }
        boolean zD = abstractC2367F.d();
        boolean zE = this.f12157x.e();
        if (this.f12124a0 == null) {
            this.f12124a0 = VelocityTracker.obtain();
        }
        this.f12124a0.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.f12109I) {
                this.f12109I = false;
            }
            this.f12123W = motionEvent.getPointerId(0);
            int x8 = (int) (motionEvent.getX() + 0.5f);
            this.f12127d0 = x8;
            this.f12125b0 = x8;
            int y10 = (int) (motionEvent.getY() + 0.5f);
            this.f12128e0 = y10;
            this.f12126c0 = y10;
            EdgeEffect edgeEffect = this.f12117Q;
            if (edgeEffect == null || d.C(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
                z6 = false;
            } else {
                d.K(this.f12117Q, 0.0f, 1.0f - (motionEvent.getY() / getHeight()));
                z6 = true;
            }
            EdgeEffect edgeEffect2 = this.f12119S;
            boolean z11 = z6;
            if (edgeEffect2 != null) {
                z11 = z6;
                if (d.C(edgeEffect2) != 0.0f) {
                    z11 = z6;
                    if (!canScrollHorizontally(1)) {
                        d.K(this.f12119S, 0.0f, motionEvent.getY() / getHeight());
                        z11 = true;
                    }
                }
            }
            EdgeEffect edgeEffect3 = this.f12118R;
            boolean z12 = z11;
            if (edgeEffect3 != null) {
                z12 = z11;
                if (d.C(edgeEffect3) != 0.0f) {
                    z12 = z11;
                    if (!canScrollVertically(-1)) {
                        d.K(this.f12118R, 0.0f, motionEvent.getX() / getWidth());
                        z12 = true;
                    }
                }
            }
            EdgeEffect edgeEffect4 = this.f12120T;
            boolean z13 = z12;
            if (edgeEffect4 != null) {
                z13 = z12;
                if (d.C(edgeEffect4) != 0.0f) {
                    z13 = z12;
                    if (!canScrollVertically(1)) {
                        d.K(this.f12120T, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
                        z13 = true;
                    }
                }
            }
            if (z13 || this.f12122V == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                c0(1);
            }
            int[] iArr = this.f12162z0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i7 = zD;
            if (zE) {
                i7 = (zD ? 1 : 0) | 2;
            }
            getScrollingChildHelper().g(i7, 0);
        } else if (actionMasked == 1) {
            this.f12124a0.clear();
            c0(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.f12123W);
            if (iFindPointerIndex < 0) {
                android.hardware.bydauto.ac.tsimpl.a.v(new StringBuilder("Error processing scroll; pointer index for id "), this.f12123W, " not found. Did any MotionEvents get skipped?", "RecyclerView");
                return false;
            }
            int x9 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y11 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.f12122V != 1) {
                int i10 = x9 - this.f12125b0;
                int i11 = y11 - this.f12126c0;
                if (!zD || Math.abs(i10) <= this.f12129f0) {
                    z10 = false;
                } else {
                    this.f12127d0 = x9;
                    z10 = true;
                }
                if (zE && Math.abs(i11) > this.f12129f0) {
                    this.f12128e0 = y11;
                    z10 = true;
                }
                if (z10) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            V();
            setScrollState(0);
        } else if (actionMasked == 5) {
            this.f12123W = motionEvent.getPointerId(actionIndex);
            int x10 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.f12127d0 = x10;
            this.f12125b0 = x10;
            int y12 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.f12128e0 = y12;
            this.f12126c0 = y12;
        } else if (actionMasked == 6) {
            P(motionEvent);
        }
        return this.f12122V == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        Trace.beginSection("RV OnLayout");
        m();
        Trace.endSection();
        this.f12103E = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F == null) {
            l(i7, i10);
            return;
        }
        boolean zH = abstractC2367F.H();
        boolean z6 = false;
        C2377P c2377p = this.f12140o0;
        if (!zH) {
            if (this.f12101D) {
                this.f12157x.f22180b.l(i7, i10);
                return;
            }
            if (c2377p.f22210k) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
            AbstractC2405y abstractC2405y = this.f12155w;
            if (abstractC2405y != null) {
                c2377p.f22204e = abstractC2405y.a();
            } else {
                c2377p.f22204e = 0;
            }
            a0();
            this.f12157x.f22180b.l(i7, i10);
            b0(false);
            c2377p.f22206g = false;
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        int mode2 = View.MeasureSpec.getMode(i10);
        this.f12157x.f22180b.l(i7, i10);
        if (mode == 1073741824 && mode2 == 1073741824) {
            z6 = true;
        }
        this.f12102D0 = z6;
        if (z6 || this.f12155w == null) {
            return;
        }
        if (c2377p.d == 1) {
            n();
        }
        this.f12157x.n0(i7, i10);
        c2377p.f22208i = true;
        o();
        this.f12157x.p0(i7, i10);
        if (this.f12157x.s0()) {
            this.f12157x.n0(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            c2377p.f22208i = true;
            o();
            this.f12157x.p0(i7, i10);
        }
        this.f12104E0 = getMeasuredWidth();
        this.f12106F0 = getMeasuredHeight();
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (J()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i7, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C2374M)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C2374M c2374m = (C2374M) parcelable;
        this.o = c2374m;
        super.onRestoreInstanceState(c2374m.l);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        C2374M c2374m = new C2374M(super.onSaveInstanceState());
        C2374M c2374m2 = this.o;
        if (c2374m2 != null) {
            c2374m.f22196n = c2374m2.f22196n;
        } else {
            AbstractC2367F abstractC2367F = this.f12157x;
            if (abstractC2367F != null) {
                c2374m.f22196n = abstractC2367F.b0();
            } else {
                c2374m.f22196n = null;
            }
        }
        return c2374m;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        if (i7 == i11 && i10 == i12) {
            return;
        }
        this.f12120T = null;
        this.f12118R = null;
        this.f12119S = null;
        this.f12117Q = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x038a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01ee A[PHI: r0
  0x01ee: PHI (r0v57 int) = (r0v42 int), (r0v61 int) binds: [B:90:0x01d7, B:94:0x01ea] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f1  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean p(int i7, int i10, int i11, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i7, i10, i11, iArr, iArr2);
    }

    public final void q(int i7, int i10, int i11, int i12, int[] iArr, int i13, int[] iArr2) {
        getScrollingChildHelper().d(i7, i10, i11, i12, iArr, i13, iArr2);
    }

    public final void r(int i7, int i10) {
        this.f12115O++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i7, scrollY - i10);
        AbstractC2370I abstractC2370I = this.f12142p0;
        if (abstractC2370I != null) {
            abstractC2370I.b(this, i7, i10);
        }
        ArrayList arrayList = this.f12144q0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((AbstractC2370I) this.f12144q0.get(size)).b(this, i7, i10);
            }
        }
        this.f12115O--;
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z6) {
        U uG = G(view);
        if (uG != null) {
            if (uG.k()) {
                uG.f22226j &= -257;
            } else if (!uG.p()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + uG + w());
            }
        }
        view.clearAnimation();
        G(view);
        super.removeDetachedView(view, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        C2402v c2402v = this.f12157x.f22181e;
        if ((c2402v == null || !c2402v.f22353e) && !J() && view2 != null) {
            U(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        return this.f12157x.h0(this, view, rect, z6, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        ArrayList arrayList = this.f12097A;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((C2392l) arrayList.get(i7)).getClass();
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.f12105F != 0 || this.f12108H) {
            this.f12107G = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s() {
        if (this.f12120T != null) {
            return;
        }
        ((C2378Q) this.f12116P).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.f12120T = edgeEffect;
        if (this.f12147s) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override // android.view.View
    public final void scrollBy(int i7, int i10) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F == null) {
            h6.n.j("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f12108H) {
            return;
        }
        boolean zD = abstractC2367F.d();
        boolean zE = this.f12157x.e();
        if (zD || zE) {
            if (!zD) {
                i7 = 0;
            }
            if (!zE) {
                i10 = 0;
            }
            W(i7, i10, null, 0);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i7, int i10) {
        h6.n.t("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!J()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.f12110J |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public void setAccessibilityDelegateCompat(W w10) {
        this.f12154v0 = w10;
        H.g(this, w10);
    }

    public void setAdapter(AbstractC2405y abstractC2405y) {
        setLayoutFrozen(false);
        AbstractC2405y abstractC2405y2 = this.f12155w;
        n nVar = this.f12136m;
        if (abstractC2405y2 != null) {
            abstractC2405y2.f22363a.unregisterObserver(nVar);
            this.f12155w.getClass();
        }
        AbstractC2364C abstractC2364C = this.f12121U;
        if (abstractC2364C != null) {
            abstractC2364C.e();
        }
        AbstractC2367F abstractC2367F = this.f12157x;
        C1648i0 c1648i0 = this.f12138n;
        if (abstractC2367F != null) {
            abstractC2367F.d0(c1648i0);
            this.f12157x.e0(c1648i0);
        }
        ((ArrayList) c1648i0.d).clear();
        c1648i0.f();
        q qVar = this.f12141p;
        qVar.P((ArrayList) qVar.l);
        qVar.P((ArrayList) qVar.f4400n);
        AbstractC2405y abstractC2405y3 = this.f12155w;
        this.f12155w = abstractC2405y;
        if (abstractC2405y != null) {
            abstractC2405y.f22363a.registerObserver(nVar);
        }
        AbstractC2367F abstractC2367F2 = this.f12157x;
        if (abstractC2367F2 != null) {
            abstractC2367F2.M();
        }
        AbstractC2405y abstractC2405y4 = this.f12155w;
        ((ArrayList) c1648i0.d).clear();
        c1648i0.f();
        c1648i0.e(abstractC2405y3, true);
        C2372K c2372kC = c1648i0.c();
        if (abstractC2405y3 != null) {
            c2372kC.f22195b--;
        }
        if (c2372kC.f22195b == 0) {
            int i7 = 0;
            while (true) {
                SparseArray sparseArray = c2372kC.f22194a;
                if (i7 >= sparseArray.size()) {
                    break;
                }
                C2371J c2371j = (C2371J) sparseArray.valueAt(i7);
                Iterator it = c2371j.f22192a.iterator();
                while (it.hasNext()) {
                    a.z(((U) it.next()).f22219a);
                }
                c2371j.f22192a.clear();
                i7++;
            }
        }
        if (abstractC2405y4 != null) {
            c2372kC.f22195b++;
        }
        c1648i0.d();
        this.f12140o0.f22205f = true;
        this.f12113M |= false;
        this.f12112L = true;
        int iA = this.f12143q.A();
        for (int i10 = 0; i10 < iA; i10++) {
            U uG = G(this.f12143q.z(i10));
            if (uG != null && !uG.p()) {
                uG.a(6);
            }
        }
        L();
        C1648i0 c1648i02 = this.f12138n;
        ArrayList arrayList = (ArrayList) c1648i02.f17995f;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            U u4 = (U) arrayList.get(i11);
            if (u4 != null) {
                u4.a(6);
                u4.a(1024);
            }
        }
        AbstractC2405y abstractC2405y5 = ((RecyclerView) c1648i02.f17997h).f12155w;
        if (abstractC2405y5 == null || !abstractC2405y5.f22364b) {
            c1648i02.f();
        }
        requestLayout();
    }

    public void setChildDrawingOrderCallback(InterfaceC2362A interfaceC2362A) {
        if (interfaceC2362A == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z6) {
        if (z6 != this.f12147s) {
            this.f12120T = null;
            this.f12118R = null;
            this.f12119S = null;
            this.f12117Q = null;
        }
        this.f12147s = z6;
        super.setClipToPadding(z6);
        if (this.f12103E) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(AbstractC2363B abstractC2363B) {
        abstractC2363B.getClass();
        this.f12116P = abstractC2363B;
        this.f12120T = null;
        this.f12118R = null;
        this.f12119S = null;
        this.f12117Q = null;
    }

    public void setHasFixedSize(boolean z6) {
        this.f12101D = z6;
    }

    public void setItemAnimator(AbstractC2364C abstractC2364C) {
        AbstractC2364C abstractC2364C2 = this.f12121U;
        if (abstractC2364C2 != null) {
            abstractC2364C2.e();
            this.f12121U.f22171a = null;
        }
        this.f12121U = abstractC2364C;
        if (abstractC2364C != null) {
            abstractC2364C.f22171a = this.f12150t0;
        }
    }

    public void setItemViewCacheSize(int i7) {
        C1648i0 c1648i0 = this.f12138n;
        c1648i0.f17993b = i7;
        c1648i0.m();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z6) {
        suppressLayout(z6);
    }

    public void setLayoutManager(AbstractC2367F abstractC2367F) {
        RecyclerView recyclerView;
        C2402v c2402v;
        if (abstractC2367F == this.f12157x) {
            return;
        }
        setScrollState(0);
        RunnableC2380T runnableC2380T = this.f12135l0;
        runnableC2380T.f22217r.removeCallbacks(runnableC2380T);
        runnableC2380T.f22214n.abortAnimation();
        AbstractC2367F abstractC2367F2 = this.f12157x;
        if (abstractC2367F2 != null && (c2402v = abstractC2367F2.f22181e) != null) {
            c2402v.c();
        }
        AbstractC2367F abstractC2367F3 = this.f12157x;
        C1648i0 c1648i0 = this.f12138n;
        if (abstractC2367F3 != null) {
            AbstractC2364C abstractC2364C = this.f12121U;
            if (abstractC2364C != null) {
                abstractC2364C.e();
            }
            this.f12157x.d0(c1648i0);
            this.f12157x.e0(c1648i0);
            ((ArrayList) c1648i0.d).clear();
            c1648i0.f();
            if (this.f12099C) {
                AbstractC2367F abstractC2367F4 = this.f12157x;
                abstractC2367F4.f22183g = false;
                abstractC2367F4.N(this);
            }
            this.f12157x.q0(null);
            this.f12157x = null;
        } else {
            ((ArrayList) c1648i0.d).clear();
            c1648i0.f();
        }
        y yVar = this.f12143q;
        ((s1) yVar.f15500n).x();
        ArrayList arrayList = (ArrayList) yVar.o;
        int size = arrayList.size() - 1;
        while (true) {
            recyclerView = ((C2404x) yVar.f15499m).f22362a;
            if (size < 0) {
                break;
            }
            U uG = G((View) arrayList.get(size));
            if (uG != null) {
                int i7 = uG.f22230p;
                if (recyclerView.J()) {
                    uG.f22231q = i7;
                    recyclerView.B0.add(uG);
                } else {
                    int[] iArr = H.f2303a;
                    uG.f22219a.setImportantForAccessibility(i7);
                }
                uG.f22230p = 0;
            }
            arrayList.remove(size);
            size--;
        }
        int childCount = recyclerView.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = recyclerView.getChildAt(i10);
            G(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.f12157x = abstractC2367F;
        if (abstractC2367F != null) {
            if (abstractC2367F.f22180b != null) {
                throw new IllegalArgumentException("LayoutManager " + abstractC2367F + " is already attached to a RecyclerView:" + abstractC2367F.f22180b.w());
            }
            abstractC2367F.q0(this);
            if (this.f12099C) {
                this.f12157x.f22183g = true;
            }
        }
        c1648i0.m();
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
    public void setNestedScrollingEnabled(boolean z6) {
        C0251j scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.d) {
            int[] iArr = H.f2303a;
            A.l(scrollingChildHelper.c);
        }
        scrollingChildHelper.d = z6;
    }

    public void setOnFlingListener(AbstractC2369H abstractC2369H) {
    }

    @Deprecated
    public void setOnScrollListener(AbstractC2370I abstractC2370I) {
        this.f12142p0 = abstractC2370I;
    }

    public void setPreserveFocusAfterLayout(boolean z6) {
        this.f12134k0 = z6;
    }

    public void setRecycledViewPool(C2372K c2372k) {
        C1648i0 c1648i0 = this.f12138n;
        RecyclerView recyclerView = (RecyclerView) c1648i0.f17997h;
        c1648i0.e(recyclerView.f12155w, false);
        if (((C2372K) c1648i0.f17996g) != null) {
            r1.f22195b--;
        }
        c1648i0.f17996g = c2372k;
        if (c2372k != null && recyclerView.getAdapter() != null) {
            ((C2372K) c1648i0.f17996g).f22195b++;
        }
        c1648i0.d();
    }

    @Deprecated
    public void setRecyclerListener(InterfaceC2373L interfaceC2373L) {
    }

    public void setScrollState(int i7) {
        C2402v c2402v;
        if (i7 == this.f12122V) {
            return;
        }
        this.f12122V = i7;
        if (i7 != 2) {
            RunnableC2380T runnableC2380T = this.f12135l0;
            runnableC2380T.f22217r.removeCallbacks(runnableC2380T);
            runnableC2380T.f22214n.abortAnimation();
            AbstractC2367F abstractC2367F = this.f12157x;
            if (abstractC2367F != null && (c2402v = abstractC2367F.f22181e) != null) {
                c2402v.c();
            }
        }
        AbstractC2367F abstractC2367F2 = this.f12157x;
        if (abstractC2367F2 != null) {
            abstractC2367F2.c0(i7);
        }
        AbstractC2370I abstractC2370I = this.f12142p0;
        if (abstractC2370I != null) {
            abstractC2370I.a(this, i7);
        }
        ArrayList arrayList = this.f12144q0;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((AbstractC2370I) this.f12144q0.get(size)).a(this, i7);
            }
        }
    }

    public void setScrollingTouchSlop(int i7) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i7 != 0) {
            if (i7 == 1) {
                this.f12129f0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            h6.n.t("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i7 + "; using default value");
        }
        this.f12129f0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(AbstractC2379S abstractC2379S) {
        this.f12138n.getClass();
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i7) {
        return getScrollingChildHelper().g(i7, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().h(0);
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z6) {
        C2402v c2402v;
        if (z6 != this.f12108H) {
            f("Do not suppressLayout in layout or scroll");
            if (!z6) {
                this.f12108H = false;
                if (this.f12107G && this.f12157x != null && this.f12155w != null) {
                    requestLayout();
                }
                this.f12107G = false;
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
            this.f12108H = true;
            this.f12109I = true;
            setScrollState(0);
            RunnableC2380T runnableC2380T = this.f12135l0;
            runnableC2380T.f22217r.removeCallbacks(runnableC2380T);
            runnableC2380T.f22214n.abortAnimation();
            AbstractC2367F abstractC2367F = this.f12157x;
            if (abstractC2367F == null || (c2402v = abstractC2367F.f22181e) == null) {
                return;
            }
            c2402v.c();
        }
    }

    public final void t() {
        if (this.f12117Q != null) {
            return;
        }
        ((C2378Q) this.f12116P).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.f12117Q = edgeEffect;
        if (this.f12147s) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void u() {
        if (this.f12119S != null) {
            return;
        }
        ((C2378Q) this.f12116P).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.f12119S = edgeEffect;
        if (this.f12147s) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void v() {
        if (this.f12118R != null) {
            return;
        }
        ((C2378Q) this.f12116P).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.f12118R = edgeEffect;
        if (this.f12147s) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String w() {
        return " " + super.toString() + ", adapter:" + this.f12155w + ", layout:" + this.f12157x + ", context:" + getContext();
    }

    public final void x(C2377P c2377p) {
        if (getScrollState() != 2) {
            c2377p.getClass();
            return;
        }
        OverScroller overScroller = this.f12135l0.f22214n;
        overScroller.getFinalX();
        overScroller.getCurrX();
        c2377p.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    public final View y(View view) {
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

    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean z(android.view.MotionEvent r12) {
        /*
            r11 = this;
            int r0 = r12.getAction()
            java.util.ArrayList r1 = r11.f12097A
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        Lc:
            if (r4 >= r2) goto L64
            java.lang.Object r5 = r1.get(r4)
            s3.l r5 = (s3.C2392l) r5
            int r6 = r5.f22315v
            r7 = 1
            r8 = 2
            if (r6 != r7) goto L59
            float r6 = r12.getX()
            float r9 = r12.getY()
            boolean r6 = r5.b(r6, r9)
            float r9 = r12.getX()
            float r10 = r12.getY()
            boolean r9 = r5.a(r9, r10)
            int r10 = r12.getAction()
            if (r10 != 0) goto L61
            if (r6 != 0) goto L3c
            if (r9 == 0) goto L61
        L3c:
            if (r9 == 0) goto L49
            r5.f22316w = r7
            float r6 = r12.getX()
            int r6 = (int) r6
            float r6 = (float) r6
            r5.f22309p = r6
            goto L55
        L49:
            if (r6 == 0) goto L55
            r5.f22316w = r8
            float r6 = r12.getY()
            int r6 = (int) r6
            float r6 = (float) r6
            r5.f22307m = r6
        L55:
            r5.e(r8)
            goto L5b
        L59:
            if (r6 != r8) goto L61
        L5b:
            r6 = 3
            if (r0 == r6) goto L61
            r11.B = r5
            return r7
        L61:
            int r4 = r4 + 1
            goto Lc
        L64:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.z(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i7) {
        TypedArray typedArray;
        char c;
        int i10;
        Constructor constructor;
        Object[] objArr;
        super(context, attributeSet, i7);
        int i11 = 1;
        this.f12136m = new n(this);
        this.f12138n = new C1648i0(this);
        this.f12145r = new b(7);
        this.f12149t = new Rect();
        this.f12151u = new Rect();
        this.f12153v = new RectF();
        this.f12159y = new ArrayList();
        this.f12161z = new ArrayList();
        this.f12097A = new ArrayList();
        this.f12105F = 0;
        this.f12112L = false;
        this.f12113M = false;
        this.f12114N = 0;
        this.f12115O = 0;
        this.f12116P = f12096N0;
        C2388h c2388h = new C2388h();
        c2388h.f22171a = null;
        c2388h.f22172b = new ArrayList();
        c2388h.c = 120L;
        c2388h.d = 120L;
        c2388h.f22173e = 250L;
        c2388h.f22174f = 250L;
        c2388h.f22281g = true;
        c2388h.f22282h = new ArrayList();
        c2388h.f22283i = new ArrayList();
        c2388h.f22284j = new ArrayList();
        c2388h.f22285k = new ArrayList();
        c2388h.l = new ArrayList();
        c2388h.f22286m = new ArrayList();
        c2388h.f22287n = new ArrayList();
        c2388h.o = new ArrayList();
        c2388h.f22288p = new ArrayList();
        c2388h.f22289q = new ArrayList();
        c2388h.f22290r = new ArrayList();
        this.f12121U = c2388h;
        this.f12122V = 0;
        this.f12123W = -1;
        this.f12132i0 = Float.MIN_VALUE;
        this.f12133j0 = Float.MIN_VALUE;
        this.f12134k0 = true;
        this.f12135l0 = new RunnableC2380T(this);
        this.f12139n0 = f12093K0 ? new C2393m() : null;
        C2377P c2377p = new C2377P();
        c2377p.f22202a = -1;
        c2377p.f22203b = 0;
        c2377p.c = 0;
        c2377p.d = 1;
        c2377p.f22204e = 0;
        c2377p.f22205f = false;
        c2377p.f22206g = false;
        c2377p.f22207h = false;
        c2377p.f22208i = false;
        c2377p.f22209j = false;
        c2377p.f22210k = false;
        this.f12140o0 = c2377p;
        this.f12146r0 = false;
        this.f12148s0 = false;
        C2404x c2404x = new C2404x(this);
        this.f12150t0 = c2404x;
        this.f12152u0 = false;
        this.f12156w0 = new int[2];
        this.f12160y0 = new int[2];
        this.f12162z0 = new int[2];
        this.f12098A0 = new int[2];
        this.B0 = new ArrayList();
        this.f12100C0 = new RunnableC2389i(this, i11);
        this.f12104E0 = 0;
        this.f12106F0 = 0;
        this.G0 = new C2404x(this);
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f12129f0 = viewConfiguration.getScaledTouchSlop();
        this.f12132i0 = viewConfiguration.getScaledHorizontalScrollFactor();
        this.f12133j0 = viewConfiguration.getScaledVerticalScrollFactor();
        this.f12130g0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f12131h0 = viewConfiguration.getScaledMaximumFlingVelocity();
        this.l = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.f12121U.f22171a = c2404x;
        C2404x c2404x2 = new C2404x(this);
        q qVar = new q();
        qVar.f4399m = new E1.c(30);
        qVar.l = new ArrayList();
        qVar.f4400n = new ArrayList();
        qVar.o = c2404x2;
        qVar.f4401p = new C1764u(qVar, 24);
        this.f12141p = qVar;
        this.f12143q = new y(new C2404x(this));
        int[] iArr = H.f2303a;
        if (C.a(this) == 0) {
            C.b(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.f12111K = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new W(this));
        int[] iArr2 = AbstractC2287a.f21808a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr2, i7, 0);
        E.b(this, context, iArr2, attributeSet, typedArrayObtainStyledAttributes, i7, 0);
        String string = typedArrayObtainStyledAttributes.getString(8);
        if (typedArrayObtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.f12147s = typedArrayObtainStyledAttributes.getBoolean(1, true);
        if (typedArrayObtainStyledAttributes.getBoolean(3, false)) {
            StateListDrawable stateListDrawable = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(6);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(7);
            StateListDrawable stateListDrawable2 = (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(5);
            if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
                throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + w());
            }
            Resources resources = getContext().getResources();
            typedArray = typedArrayObtainStyledAttributes;
            c = 2;
            i10 = 4;
            new C2392l(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.stingray.drivemodedetector.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.stingray.drivemodedetector.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.stingray.drivemodedetector.R.dimen.fastscroll_margin));
        } else {
            typedArray = typedArrayObtainStyledAttributes;
            c = 2;
            i10 = 4;
        }
        typedArray.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(".")) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                String str = strTrim;
                try {
                    Class<? extends U> clsAsSubclass = Class.forName(str, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(AbstractC2367F.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(f12094L0);
                        objArr = new Object[i10];
                        objArr[0] = context;
                        objArr[1] = attributeSet;
                        objArr[c] = Integer.valueOf(i7);
                        objArr[3] = 0;
                    } catch (NoSuchMethodException e10) {
                        try {
                            constructor = clsAsSubclass.getConstructor(null);
                            objArr = null;
                        } catch (NoSuchMethodException e11) {
                            e11.initCause(e10);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e11);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((AbstractC2367F) constructor.newInstance(objArr));
                } catch (ClassCastException e12) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e12);
                } catch (ClassNotFoundException e13) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e13);
                } catch (IllegalAccessException e14) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e14);
                } catch (InstantiationException e15) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e15);
                } catch (InvocationTargetException e16) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e16);
                }
            }
        }
        int[] iArr3 = f12090H0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr3, i7, 0);
        E.b(this, context, iArr3, attributeSet, typedArrayObtainStyledAttributes2, i7, 0);
        boolean z6 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z6);
        setTag(com.stingray.drivemodedetector.R.id.is_pooling_container_tag, Boolean.TRUE);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        AbstractC2367F abstractC2367F = this.f12157x;
        if (abstractC2367F != null) {
            return abstractC2367F.t(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + w());
    }
}
