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
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.h;
import androidx.recyclerview.widget.o;
import androidx.recyclerview.widget.s;
import androidx.recyclerview.widget.t;
import j0.c;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements i0.k {
    private static final int[] D0 = {R.attr.nestedScrollingEnabled};
    static final boolean E0 = false;
    static final boolean F0 = true;
    static final boolean G0 = true;
    static final boolean H0 = true;
    private static final boolean I0 = false;
    private static final boolean J0 = false;
    private static final Class<?>[] K0;
    static final Interpolator L0;
    private int A;
    final List<c0> A0;
    boolean B;
    private Runnable B0;
    boolean C;
    private final t.b C0;
    private boolean D;
    private int E;
    boolean F;
    private final AccessibilityManager G;
    private List<q> H;
    boolean I;
    boolean J;
    private int K;
    private int L;
    private k M;
    private EdgeEffect N;
    private EdgeEffect O;
    private EdgeEffect P;
    private EdgeEffect Q;
    l R;
    private int S;
    private int T;
    private VelocityTracker U;
    private int V;
    private int W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private int f2799a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private int f2800b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private int f2801c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private r f2802d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final x f2803e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private final int f2804e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private final int f2805f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final v f2806g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private float f2807g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private SavedState f2808h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private float f2809h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    androidx.recyclerview.widget.a f2810i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private boolean f2811i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    androidx.recyclerview.widget.d f2812j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    final b0 f2813j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final androidx.recyclerview.widget.t f2814k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    androidx.recyclerview.widget.h f2815k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f2816l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    h.b f2817l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final Runnable f2818m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    final z f2819m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final Rect f2820n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private t f2821n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final Rect f2822o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private List<t> f2823o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    final RectF f2824p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    boolean f2825p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    g f2826q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    boolean f2827q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    o f2828r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private l.b f2829r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    w f2830s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    boolean f2831s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    final ArrayList<n> f2832t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    androidx.recyclerview.widget.o f2833t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final ArrayList<s> f2834u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private j f2835u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private s f2836v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private final int[] f2837v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    boolean f2838w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private i0.l f2839w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    boolean f2840x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private final int[] f2841x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    boolean f2842y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private final int[] f2843y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    boolean f2844z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    final int[] f2845z0;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.f2844z || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.f2838w) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.C) {
                recyclerView2.B = true;
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
            l lVar = RecyclerView.this.R;
            if (lVar != null) {
                lVar.u();
            }
            RecyclerView.this.f2831s0 = false;
        }
    }

    class b0 implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2849e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f2850g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        OverScroller f2851h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Interpolator f2852i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private boolean f2853j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private boolean f2854k;

        b0() {
            Interpolator interpolator = RecyclerView.L0;
            this.f2852i = interpolator;
            this.f2853j = false;
            this.f2854k = false;
            this.f2851h = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        private int a(int i7, int i8, int i9, int i10) {
            int iRound;
            int iAbs = Math.abs(i7);
            int iAbs2 = Math.abs(i8);
            boolean z6 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i9 * i9) + (i10 * i10));
            int iSqrt2 = (int) Math.sqrt((i7 * i7) + (i8 * i8));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z6 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i11 = width / 2;
            float f7 = width;
            float f8 = i11;
            float fB = f8 + (b(Math.min(1.0f, (iSqrt2 * 1.0f) / f7)) * f8);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fB / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z6) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f7) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        private float b(float f7) {
            return (float) Math.sin((f7 - 0.5f) * 0.47123894f);
        }

        private void d() {
            RecyclerView.this.removeCallbacks(this);
            i0.u.c0(RecyclerView.this, this);
        }

        public void c(int i7, int i8) {
            RecyclerView.this.setScrollState(2);
            this.f2850g = 0;
            this.f2849e = 0;
            Interpolator interpolator = this.f2852i;
            Interpolator interpolator2 = RecyclerView.L0;
            if (interpolator != interpolator2) {
                this.f2852i = interpolator2;
                this.f2851h = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f2851h.fling(0, 0, i7, i8, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        void e() {
            if (this.f2853j) {
                this.f2854k = true;
            } else {
                d();
            }
        }

        public void f(int i7, int i8, int i9, Interpolator interpolator) {
            if (i9 == Integer.MIN_VALUE) {
                i9 = a(i7, i8, 0, 0);
            }
            int i10 = i9;
            if (interpolator == null) {
                interpolator = RecyclerView.L0;
            }
            if (this.f2852i != interpolator) {
                this.f2852i = interpolator;
                this.f2851h = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f2850g = 0;
            this.f2849e = 0;
            RecyclerView.this.setScrollState(2);
            this.f2851h.startScroll(0, 0, i7, i8, i10);
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f2851h.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i7;
            int i8;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f2828r == null) {
                g();
                return;
            }
            this.f2854k = false;
            this.f2853j = true;
            recyclerView.v();
            OverScroller overScroller = this.f2851h;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i9 = currX - this.f2849e;
                int i10 = currY - this.f2850g;
                this.f2849e = currX;
                this.f2850g = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.f2845z0;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.G(i9, i10, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.f2845z0;
                    i9 -= iArr2[0];
                    i10 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.u(i9, i10);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.f2826q != null) {
                    int[] iArr3 = recyclerView3.f2845z0;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.i1(i9, i10, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.f2845z0;
                    i8 = iArr4[0];
                    i7 = iArr4[1];
                    i9 -= i8;
                    i10 -= i7;
                    y yVar = recyclerView4.f2828r.f2897g;
                    if (yVar != null && !yVar.g() && yVar.h()) {
                        int iB = RecyclerView.this.f2819m0.b();
                        if (iB == 0) {
                            yVar.r();
                        } else if (yVar.f() >= iB) {
                            yVar.p(iB - 1);
                            yVar.j(i8, i7);
                        } else {
                            yVar.j(i8, i7);
                        }
                    }
                } else {
                    i7 = 0;
                    i8 = 0;
                }
                if (!RecyclerView.this.f2832t.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.f2845z0;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.H(i8, i7, i9, i10, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.f2845z0;
                int i11 = i9 - iArr6[0];
                int i12 = i10 - iArr6[1];
                if (i8 != 0 || i7 != 0) {
                    recyclerView6.J(i8, i7);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z6 = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i11 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i12 != 0));
                y yVar2 = RecyclerView.this.f2828r.f2897g;
                if ((yVar2 != null && yVar2.g()) || !z6) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    androidx.recyclerview.widget.h hVar = recyclerView7.f2815k0;
                    if (hVar != null) {
                        hVar.f(recyclerView7, i8, i7);
                    }
                } else {
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i13 = i11 < 0 ? -currVelocity : i11 > 0 ? currVelocity : 0;
                        if (i12 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i12 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i13, currVelocity);
                    }
                    if (RecyclerView.H0) {
                        RecyclerView.this.f2817l0.b();
                    }
                }
            }
            y yVar3 = RecyclerView.this.f2828r.f2897g;
            if (yVar3 != null && yVar3.g()) {
                yVar3.j(0, 0);
            }
            this.f2853j = false;
            if (this.f2854k) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.v1(1);
            }
        }
    }

    static class c implements Interpolator {
        c() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8 = f7 - 1.0f;
            return (f8 * f8 * f8 * f8 * f8) + 1.0f;
        }
    }

    public static abstract class c0 {

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        private static final List<Object> f2856s = Collections.emptyList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f2857a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        WeakReference<RecyclerView> f2858b;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        int f2866j;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        RecyclerView f2874r;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2859c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2860d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f2861e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2862f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2863g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        c0 f2864h = null;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        c0 f2865i = null;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        List<Object> f2867k = null;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        List<Object> f2868l = null;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private int f2869m = 0;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        v f2870n = null;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f2871o = false;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private int f2872p = 0;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        int f2873q = -1;

        public c0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f2857a = view;
        }

        private void g() {
            if (this.f2867k == null) {
                ArrayList arrayList = new ArrayList();
                this.f2867k = arrayList;
                this.f2868l = Collections.unmodifiableList(arrayList);
            }
        }

        void A(int i7, boolean z6) {
            if (this.f2860d == -1) {
                this.f2860d = this.f2859c;
            }
            if (this.f2863g == -1) {
                this.f2863g = this.f2859c;
            }
            if (z6) {
                this.f2863g += i7;
            }
            this.f2859c += i7;
            if (this.f2857a.getLayoutParams() != null) {
                ((p) this.f2857a.getLayoutParams()).f2917c = true;
            }
        }

        void B(RecyclerView recyclerView) {
            int i7 = this.f2873q;
            if (i7 != -1) {
                this.f2872p = i7;
            } else {
                this.f2872p = i0.u.w(this.f2857a);
            }
            recyclerView.l1(this, 4);
        }

        void C(RecyclerView recyclerView) {
            recyclerView.l1(this, this.f2872p);
            this.f2872p = 0;
        }

        void D() {
            this.f2866j = 0;
            this.f2859c = -1;
            this.f2860d = -1;
            this.f2861e = -1L;
            this.f2863g = -1;
            this.f2869m = 0;
            this.f2864h = null;
            this.f2865i = null;
            d();
            this.f2872p = 0;
            this.f2873q = -1;
            RecyclerView.s(this);
        }

        void E() {
            if (this.f2860d == -1) {
                this.f2860d = this.f2859c;
            }
        }

        void F(int i7, int i8) {
            this.f2866j = (i7 & i8) | (this.f2866j & (~i8));
        }

        public final void G(boolean z6) {
            int i7 = this.f2869m;
            int i8 = z6 ? i7 - 1 : i7 + 1;
            this.f2869m = i8;
            if (i8 < 0) {
                this.f2869m = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z6 && i8 == 1) {
                this.f2866j |= 16;
            } else if (z6 && i8 == 0) {
                this.f2866j &= -17;
            }
        }

        void H(v vVar, boolean z6) {
            this.f2870n = vVar;
            this.f2871o = z6;
        }

        boolean I() {
            return (this.f2866j & 16) != 0;
        }

        boolean J() {
            return (this.f2866j & 128) != 0;
        }

        void K() {
            this.f2870n.J(this);
        }

        boolean L() {
            return (this.f2866j & 32) != 0;
        }

        void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.f2866j) == 0) {
                g();
                this.f2867k.add(obj);
            }
        }

        void b(int i7) {
            this.f2866j = i7 | this.f2866j;
        }

        void c() {
            this.f2860d = -1;
            this.f2863g = -1;
        }

        void d() {
            List<Object> list = this.f2867k;
            if (list != null) {
                list.clear();
            }
            this.f2866j &= -1025;
        }

        void e() {
            this.f2866j &= -33;
        }

        void f() {
            this.f2866j &= -257;
        }

        boolean h() {
            return (this.f2866j & 16) == 0 && i0.u.M(this.f2857a);
        }

        void i(int i7, int i8, boolean z6) {
            b(8);
            A(i8, z6);
            this.f2859c = i7;
        }

        public final int j() {
            RecyclerView recyclerView = this.f2874r;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.c0(this);
        }

        public final long k() {
            return this.f2861e;
        }

        public final int l() {
            return this.f2862f;
        }

        public final int m() {
            int i7 = this.f2863g;
            return i7 == -1 ? this.f2859c : i7;
        }

        public final int n() {
            return this.f2860d;
        }

        List<Object> o() {
            if ((this.f2866j & 1024) != 0) {
                return f2856s;
            }
            List<Object> list = this.f2867k;
            return (list == null || list.size() == 0) ? f2856s : this.f2868l;
        }

        boolean p(int i7) {
            return (i7 & this.f2866j) != 0;
        }

        boolean q() {
            return (this.f2866j & 512) != 0 || t();
        }

        boolean r() {
            return (this.f2857a.getParent() == null || this.f2857a.getParent() == this.f2874r) ? false : true;
        }

        boolean s() {
            return (this.f2866j & 1) != 0;
        }

        boolean t() {
            return (this.f2866j & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f2859c + " id=" + this.f2861e + ", oldPos=" + this.f2860d + ", pLpos:" + this.f2863g);
            if (w()) {
                sb.append(" scrap ");
                sb.append(this.f2871o ? "[changeScrap]" : "[attachedScrap]");
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
                sb.append(" not recyclable(" + this.f2869m + ")");
            }
            if (q()) {
                sb.append(" undefined adapter position");
            }
            if (this.f2857a.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.f2866j & 16) == 0 && !i0.u.M(this.f2857a);
        }

        boolean v() {
            return (this.f2866j & 8) != 0;
        }

        boolean w() {
            return this.f2870n != null;
        }

        boolean x() {
            return (this.f2866j & 256) != 0;
        }

        boolean y() {
            return (this.f2866j & 2) != 0;
        }

        boolean z() {
            return (this.f2866j & 2) != 0;
        }
    }

    class d implements t.b {
        d() {
        }

        @Override // androidx.recyclerview.widget.t.b
        public void a(c0 c0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f2828r.m1(c0Var.f2857a, recyclerView.f2806g);
        }

        @Override // androidx.recyclerview.widget.t.b
        public void b(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.m(c0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.t.b
        public void c(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.f2806g.J(c0Var);
            RecyclerView.this.o(c0Var, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.t.b
        public void d(c0 c0Var, l.c cVar, l.c cVar2) {
            c0Var.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.I) {
                if (recyclerView.R.b(c0Var, c0Var, cVar, cVar2)) {
                    RecyclerView.this.N0();
                }
            } else if (recyclerView.R.d(c0Var, cVar, cVar2)) {
                RecyclerView.this.N0();
            }
        }
    }

    class e implements d.b {
        e() {
        }

        @Override // androidx.recyclerview.widget.d.b
        public View a(int i7) {
            return RecyclerView.this.getChildAt(i7);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void b(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.B(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.d.b
        public void c(int i7) {
            View childAt = RecyclerView.this.getChildAt(i7);
            if (childAt != null) {
                RecyclerView.this.A(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i7);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void d() {
            int iK = k();
            for (int i7 = 0; i7 < iK; i7++) {
                View viewA = a(i7);
                RecyclerView.this.A(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.d.b
        public c0 e(View view) {
            return RecyclerView.g0(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void f(int i7) {
            c0 c0VarG0;
            View viewA = a(i7);
            if (viewA != null && (c0VarG0 = RecyclerView.g0(viewA)) != null) {
                if (c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i7);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void g(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                c0VarG0.C(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.d.b
        public void h(View view, int i7, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 != null) {
                if (!c0VarG0.x() && !c0VarG0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarG0 + RecyclerView.this.Q());
                }
                c0VarG0.f();
            }
            RecyclerView.this.attachViewToParent(view, i7, layoutParams);
        }

        @Override // androidx.recyclerview.widget.d.b
        public void i(View view, int i7) {
            RecyclerView.this.addView(view, i7);
            RecyclerView.this.z(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.d.b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    class f implements a.InterfaceC0027a {
        f() {
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void a(int i7, int i8) {
            RecyclerView.this.D0(i7, i8);
            RecyclerView.this.f2825p0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public c0 c(int i7) {
            c0 c0VarA0 = RecyclerView.this.a0(i7, true);
            if (c0VarA0 == null || RecyclerView.this.f2812j.n(c0VarA0.f2857a)) {
                return null;
            }
            return c0VarA0;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void d(int i7, int i8) {
            RecyclerView.this.E0(i7, i8, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.f2825p0 = true;
            recyclerView.f2819m0.f2952d += i8;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void e(int i7, int i8) {
            RecyclerView.this.E0(i7, i8, false);
            RecyclerView.this.f2825p0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void g(int i7, int i8) {
            RecyclerView.this.C0(i7, i8);
            RecyclerView.this.f2825p0 = true;
        }

        @Override // androidx.recyclerview.widget.a.InterfaceC0027a
        public void h(int i7, int i8, Object obj) {
            RecyclerView.this.y1(i7, i8, obj);
            RecyclerView.this.f2827q0 = true;
        }

        void i(a.b bVar) {
            int i7 = bVar.f3014a;
            if (i7 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.f2828r.R0(recyclerView, bVar.f3015b, bVar.f3017d);
                return;
            }
            if (i7 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.f2828r.U0(recyclerView2, bVar.f3015b, bVar.f3017d);
            } else if (i7 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.f2828r.W0(recyclerView3, bVar.f3015b, bVar.f3017d, bVar.f3016c);
            } else {
                if (i7 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.f2828r.T0(recyclerView4, bVar.f3015b, bVar.f3017d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final h f2878a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2879b = false;

        public void A(i iVar) {
            this.f2878a.unregisterObserver(iVar);
        }

        public final void c(VH vh, int i7) {
            vh.f2859c = i7;
            if (i()) {
                vh.f2861e = f(i7);
            }
            vh.F(1, 519);
            d0.c.a("RV OnBindView");
            r(vh, i7, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f2857a.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f2917c = true;
            }
            d0.c.b();
        }

        public final VH d(ViewGroup viewGroup, int i7) {
            try {
                d0.c.a("RV CreateView");
                VH vh = (VH) s(viewGroup, i7);
                if (vh.f2857a.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f2862f = i7;
                return vh;
            } finally {
                d0.c.b();
            }
        }

        public abstract int e();

        public long f(int i7) {
            return -1L;
        }

        public int g(int i7) {
            return 0;
        }

        public final boolean h() {
            return this.f2878a.a();
        }

        public final boolean i() {
            return this.f2879b;
        }

        public final void j() {
            this.f2878a.b();
        }

        public final void k(int i7) {
            this.f2878a.d(i7, 1);
        }

        public final void l(int i7, int i8) {
            this.f2878a.c(i7, i8);
        }

        public final void m(int i7, int i8, Object obj) {
            this.f2878a.e(i7, i8, obj);
        }

        public final void n(int i7, int i8) {
            this.f2878a.f(i7, i8);
        }

        public final void o(int i7, int i8) {
            this.f2878a.g(i7, i8);
        }

        public void p(RecyclerView recyclerView) {
        }

        public abstract void q(VH vh, int i7);

        public void r(VH vh, int i7, List<Object> list) {
            q(vh, i7);
        }

        public abstract VH s(ViewGroup viewGroup, int i7);

        public void t(RecyclerView recyclerView) {
        }

        public boolean u(VH vh) {
            return false;
        }

        public void v(VH vh) {
        }

        public void w(VH vh) {
        }

        public void x(VH vh) {
        }

        public void y(i iVar) {
            this.f2878a.registerObserver(iVar);
        }

        public void z(boolean z6) {
            if (h()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f2879b = z6;
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

        public void c(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).e(i7, i8, 1);
            }
        }

        public void d(int i7, int i8) {
            e(i7, i8, null);
        }

        public void e(int i7, int i8, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i7, i8, obj);
            }
        }

        public void f(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).d(i7, i8);
            }
        }

        public void g(int i7, int i8) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).f(i7, i8);
            }
        }
    }

    public static abstract class i {
        public void a() {
        }

        public void b(int i7, int i8) {
        }

        public void c(int i7, int i8, Object obj) {
            b(i7, i8);
        }

        public void d(int i7, int i8) {
        }

        public void e(int i7, int i8, int i9) {
        }

        public void f(int i7, int i8) {
        }
    }

    public interface j {
        int a(int i7, int i8);
    }

    public static class k {
        protected EdgeEffect a(RecyclerView recyclerView, int i7) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private b f2880a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<a> f2881b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2882c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f2883d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f2884e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f2885f = 250;

        public interface a {
            void a();
        }

        interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f2886a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f2887b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f2888c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f2889d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i7) {
                View view = c0Var.f2857a;
                this.f2886a = view.getLeft();
                this.f2887b = view.getTop();
                this.f2888c = view.getRight();
                this.f2889d = view.getBottom();
                return this;
            }
        }

        static int e(c0 c0Var) {
            int i7 = c0Var.f2866j & 14;
            if (c0Var.t()) {
                return 4;
            }
            if ((i7 & 4) != 0) {
                return i7;
            }
            int iN = c0Var.n();
            int iJ = c0Var.j();
            return (iN == -1 || iJ == -1 || iN == iJ) ? i7 : i7 | 2048;
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
            b bVar = this.f2880a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f2881b.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.f2881b.get(i7).a();
            }
            this.f2881b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f2882c;
        }

        public long m() {
            return this.f2885f;
        }

        public long n() {
            return this.f2884e;
        }

        public long o() {
            return this.f2883d;
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

        public c t(z zVar, c0 c0Var, int i7, List<Object> list) {
            return q().a(c0Var);
        }

        public abstract void u();

        void v(b bVar) {
            this.f2880a = bVar;
        }
    }

    private class m implements l.b {
        m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.f2864h != null && c0Var.f2865i == null) {
                c0Var.f2864h = null;
            }
            c0Var.f2865i = null;
            if (c0Var.I() || RecyclerView.this.W0(c0Var.f2857a) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f2857a, false);
        }
    }

    public static abstract class n {
        @Deprecated
        public void d(Rect rect, int i7, RecyclerView recyclerView) {
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
        androidx.recyclerview.widget.d f2891a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        RecyclerView f2892b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final s.b f2893c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final s.b f2894d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        androidx.recyclerview.widget.s f2895e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        androidx.recyclerview.widget.s f2896f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        y f2897g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f2898h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f2899i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f2900j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private boolean f2901k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private boolean f2902l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        int f2903m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        boolean f2904n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private int f2905o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private int f2906p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private int f2907q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private int f2908r;

        class a implements s.b {
            a() {
            }

            @Override // androidx.recyclerview.widget.s.b
            public View a(int i7) {
                return o.this.I(i7);
            }

            @Override // androidx.recyclerview.widget.s.b
            public int b() {
                return o.this.o0() - o.this.f0();
            }

            @Override // androidx.recyclerview.widget.s.b
            public int c() {
                return o.this.e0();
            }

            @Override // androidx.recyclerview.widget.s.b
            public int d(View view) {
                return o.this.T(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // androidx.recyclerview.widget.s.b
            public int e(View view) {
                return o.this.Q(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        class b implements s.b {
            b() {
            }

            @Override // androidx.recyclerview.widget.s.b
            public View a(int i7) {
                return o.this.I(i7);
            }

            @Override // androidx.recyclerview.widget.s.b
            public int b() {
                return o.this.W() - o.this.d0();
            }

            @Override // androidx.recyclerview.widget.s.b
            public int c() {
                return o.this.g0();
            }

            @Override // androidx.recyclerview.widget.s.b
            public int d(View view) {
                return o.this.O(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.s.b
            public int e(View view) {
                return o.this.U(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i7, int i8);
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f2911a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f2912b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f2913c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f2914d;
        }

        public o() {
            a aVar = new a();
            this.f2893c = aVar;
            b bVar = new b();
            this.f2894d = bVar;
            this.f2895e = new androidx.recyclerview.widget.s(aVar);
            this.f2896f = new androidx.recyclerview.widget.s(bVar);
            this.f2898h = false;
            this.f2899i = false;
            this.f2900j = false;
            this.f2901k = true;
            this.f2902l = true;
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
            int i7 = left - iE0;
            int iMin = Math.min(0, i7);
            int i8 = top - iG0;
            int iMin2 = Math.min(0, i8);
            int i9 = iWidth - iO0;
            int iMax = Math.max(0, i9);
            int iMax2 = Math.max(0, iHeight - iW);
            if (Z() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i7, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i9);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i8, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        private void f(View view, int i7, boolean z6) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (z6 || c0VarG0.v()) {
                this.f2892b.f2814k.b(c0VarG0);
            } else {
                this.f2892b.f2814k.p(c0VarG0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarG0.L() || c0VarG0.w()) {
                if (c0VarG0.w()) {
                    c0VarG0.K();
                } else {
                    c0VarG0.e();
                }
                this.f2891a.c(view, i7, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f2892b) {
                int iM = this.f2891a.m(view);
                if (i7 == -1) {
                    i7 = this.f2891a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f2892b.indexOfChild(view) + this.f2892b.Q());
                }
                if (iM != i7) {
                    this.f2892b.f2828r.B0(iM, i7);
                }
            } else {
                this.f2891a.a(view, i7, false);
                pVar.f2917c = true;
                y yVar = this.f2897g;
                if (yVar != null && yVar.h()) {
                    this.f2897g.k(view);
                }
            }
            if (pVar.f2918d) {
                c0VarG0.f2857a.invalidate();
                pVar.f2918d = false;
            }
        }

        public static d i0(Context context, AttributeSet attributeSet, int i7, int i8) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, x0.c.RecyclerView, i7, i8);
            dVar.f2911a = typedArrayObtainStyledAttributes.getInt(x0.c.RecyclerView_android_orientation, 1);
            dVar.f2912b = typedArrayObtainStyledAttributes.getInt(x0.c.RecyclerView_spanCount, 1);
            dVar.f2913c = typedArrayObtainStyledAttributes.getBoolean(x0.c.RecyclerView_reverseLayout, false);
            dVar.f2914d = typedArrayObtainStyledAttributes.getBoolean(x0.c.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int n(int i7, int i8, int i9) {
            int mode = View.MeasureSpec.getMode(i7);
            int size = View.MeasureSpec.getSize(i7);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i8, i9) : size : Math.min(size, Math.max(i8, i9));
        }

        private boolean t0(RecyclerView recyclerView, int i7, int i8) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            Rect rect = this.f2892b.f2820n;
            P(focusedChild, rect);
            return rect.left - i7 < iO0 && rect.right - i7 > iE0 && rect.top - i8 < iW && rect.bottom - i8 > iG0;
        }

        private void v1(v vVar, int i7, View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.J()) {
                return;
            }
            if (c0VarG0.t() && !c0VarG0.v() && !this.f2892b.f2826q.i()) {
                q1(i7);
                vVar.C(c0VarG0);
            } else {
                x(i7);
                vVar.D(view);
                this.f2892b.f2814k.k(c0VarG0);
            }
        }

        private static boolean w0(int i7, int i8, int i9) {
            int mode = View.MeasureSpec.getMode(i8);
            int size = View.MeasureSpec.getSize(i8);
            if (i9 > 0 && i7 != i9) {
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

        private void y(int i7, View view) {
            this.f2891a.d(i7);
        }

        void A(RecyclerView recyclerView, v vVar) {
            this.f2899i = false;
            I0(recyclerView, vVar);
        }

        public void A0(View view, int i7, int i8) {
            p pVar = (p) view.getLayoutParams();
            Rect rectK0 = this.f2892b.k0(view);
            int i9 = i7 + rectK0.left + rectK0.right;
            int i10 = i8 + rectK0.top + rectK0.bottom;
            int iK = K(o0(), p0(), e0() + f0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i9, ((ViewGroup.MarginLayoutParams) pVar).width, k());
            int iK2 = K(W(), X(), g0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i10, ((ViewGroup.MarginLayoutParams) pVar).height, l());
            if (F1(view, iK, iK2, pVar)) {
                view.measure(iK, iK2);
            }
        }

        void A1(int i7, int i8) {
            this.f2907q = View.MeasureSpec.getSize(i7);
            int mode = View.MeasureSpec.getMode(i7);
            this.f2905o = mode;
            if (mode == 0 && !RecyclerView.F0) {
                this.f2907q = 0;
            }
            this.f2908r = View.MeasureSpec.getSize(i8);
            int mode2 = View.MeasureSpec.getMode(i8);
            this.f2906p = mode2;
            if (mode2 != 0 || RecyclerView.F0) {
                return;
            }
            this.f2908r = 0;
        }

        public View B(View view) {
            View viewS;
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null || (viewS = recyclerView.S(view)) == null || this.f2891a.n(viewS)) {
                return null;
            }
            return viewS;
        }

        public void B0(int i7, int i8) {
            View viewI = I(i7);
            if (viewI != null) {
                x(i7);
                h(viewI, i8);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i7 + this.f2892b.toString());
            }
        }

        public void B1(int i7, int i8) {
            this.f2892b.setMeasuredDimension(i7, i8);
        }

        public View C(int i7) {
            int iJ = J();
            for (int i8 = 0; i8 < iJ; i8++) {
                View viewI = I(i8);
                c0 c0VarG0 = RecyclerView.g0(viewI);
                if (c0VarG0 != null && c0VarG0.m() == i7 && !c0VarG0.J() && (this.f2892b.f2819m0.e() || !c0VarG0.v())) {
                    return viewI;
                }
            }
            return null;
        }

        public void C0(int i7) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                recyclerView.A0(i7);
            }
        }

        public void C1(Rect rect, int i7, int i8) {
            B1(n(i7, rect.width() + e0() + f0(), c0()), n(i8, rect.height() + g0() + d0(), b0()));
        }

        public abstract p D();

        public void D0(int i7) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                recyclerView.B0(i7);
            }
        }

        void D1(int i7, int i8) {
            int iJ = J();
            if (iJ == 0) {
                this.f2892b.x(i7, i8);
                return;
            }
            int i9 = Integer.MIN_VALUE;
            int i10 = Integer.MAX_VALUE;
            int i11 = Integer.MAX_VALUE;
            int i12 = Integer.MIN_VALUE;
            for (int i13 = 0; i13 < iJ; i13++) {
                View viewI = I(i13);
                Rect rect = this.f2892b.f2820n;
                P(viewI, rect);
                int i14 = rect.left;
                if (i14 < i10) {
                    i10 = i14;
                }
                int i15 = rect.right;
                if (i15 > i9) {
                    i9 = i15;
                }
                int i16 = rect.top;
                if (i16 < i11) {
                    i11 = i16;
                }
                int i17 = rect.bottom;
                if (i17 > i12) {
                    i12 = i17;
                }
            }
            this.f2892b.f2820n.set(i10, i11, i9, i12);
            C1(this.f2892b.f2820n, i7, i8);
        }

        public p E(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void E0(g gVar, g gVar2) {
        }

        void E1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f2892b = null;
                this.f2891a = null;
                this.f2907q = 0;
                this.f2908r = 0;
            } else {
                this.f2892b = recyclerView;
                this.f2891a = recyclerView.f2812j;
                this.f2907q = recyclerView.getWidth();
                this.f2908r = recyclerView.getHeight();
            }
            this.f2905o = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.f2906p = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public p F(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        public boolean F0(RecyclerView recyclerView, ArrayList<View> arrayList, int i7, int i8) {
            return false;
        }

        boolean F1(View view, int i7, int i8, p pVar) {
            return (!view.isLayoutRequested() && this.f2901k && w0(view.getWidth(), i7, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getHeight(), i8, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
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
            return ((p) view.getLayoutParams()).f2916b.bottom;
        }

        @Deprecated
        public void H0(RecyclerView recyclerView) {
        }

        boolean H1(View view, int i7, int i8, p pVar) {
            return (this.f2901k && w0(view.getMeasuredWidth(), i7, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getMeasuredHeight(), i8, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public View I(int i7) {
            androidx.recyclerview.widget.d dVar = this.f2891a;
            if (dVar != null) {
                return dVar.f(i7);
            }
            return null;
        }

        public void I0(RecyclerView recyclerView, v vVar) {
            H0(recyclerView);
        }

        public void I1(RecyclerView recyclerView, z zVar, int i7) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public int J() {
            androidx.recyclerview.widget.d dVar = this.f2891a;
            if (dVar != null) {
                return dVar.g();
            }
            return 0;
        }

        public View J0(View view, int i7, v vVar, z zVar) {
            return null;
        }

        public void J1(y yVar) {
            y yVar2 = this.f2897g;
            if (yVar2 != null && yVar != yVar2 && yVar2.h()) {
                this.f2897g.r();
            }
            this.f2897g = yVar;
            yVar.q(this.f2892b, this);
        }

        public void K0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f2892b;
            L0(recyclerView.f2806g, recyclerView.f2819m0, accessibilityEvent);
        }

        void K1() {
            y yVar = this.f2897g;
            if (yVar != null) {
                yVar.r();
            }
        }

        public void L0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z6 = true;
            if (!recyclerView.canScrollVertically(1) && !this.f2892b.canScrollVertically(-1) && !this.f2892b.canScrollHorizontally(-1) && !this.f2892b.canScrollHorizontally(1)) {
                z6 = false;
            }
            accessibilityEvent.setScrollable(z6);
            g gVar = this.f2892b.f2826q;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.e());
            }
        }

        public boolean L1() {
            return false;
        }

        public boolean M() {
            RecyclerView recyclerView = this.f2892b;
            return recyclerView != null && recyclerView.f2816l;
        }

        public void M0(v vVar, z zVar, j0.c cVar) {
            if (this.f2892b.canScrollVertically(-1) || this.f2892b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.h0(true);
            }
            if (this.f2892b.canScrollVertically(1) || this.f2892b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.h0(true);
            }
            cVar.R(c.b.a(k0(vVar, zVar), N(vVar, zVar), v0(vVar, zVar), l0(vVar, zVar)));
        }

        public int N(v vVar, z zVar) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null || recyclerView.f2826q == null || !k()) {
                return 1;
            }
            return this.f2892b.f2826q.e();
        }

        void N0(j0.c cVar) {
            RecyclerView recyclerView = this.f2892b;
            M0(recyclerView.f2806g, recyclerView.f2819m0, cVar);
        }

        public int O(View view) {
            return view.getBottom() + H(view);
        }

        void O0(View view, j0.c cVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0 == null || c0VarG0.v() || this.f2891a.n(c0VarG0.f2857a)) {
                return;
            }
            RecyclerView recyclerView = this.f2892b;
            P0(recyclerView.f2806g, recyclerView.f2819m0, view, cVar);
        }

        public void P(View view, Rect rect) {
            RecyclerView.h0(view, rect);
        }

        public void P0(v vVar, z zVar, View view, j0.c cVar) {
            cVar.S(c.C0090c.a(l() ? h0(view) : 0, 1, k() ? h0(view) : 0, 1, false, false));
        }

        public int Q(View view) {
            return view.getLeft() - a0(view);
        }

        public View Q0(View view, int i7) {
            return null;
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f2916b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void R0(RecyclerView recyclerView, int i7, int i8) {
        }

        public int S(View view) {
            Rect rect = ((p) view.getLayoutParams()).f2916b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void S0(RecyclerView recyclerView) {
        }

        public int T(View view) {
            return view.getRight() + j0(view);
        }

        public void T0(RecyclerView recyclerView, int i7, int i8, int i9) {
        }

        public int U(View view) {
            return view.getTop() - m0(view);
        }

        public void U0(RecyclerView recyclerView, int i7, int i8) {
        }

        public View V() {
            View focusedChild;
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f2891a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void V0(RecyclerView recyclerView, int i7, int i8) {
        }

        public int W() {
            return this.f2908r;
        }

        public void W0(RecyclerView recyclerView, int i7, int i8, Object obj) {
            V0(recyclerView, i7, i8);
        }

        public int X() {
            return this.f2906p;
        }

        public void X0(v vVar, z zVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int Y() {
            RecyclerView recyclerView = this.f2892b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.e();
            }
            return 0;
        }

        public void Y0(z zVar) {
        }

        public int Z() {
            return i0.u.y(this.f2892b);
        }

        public void Z0(v vVar, z zVar, int i7, int i8) {
            this.f2892b.x(i7, i8);
        }

        public int a0(View view) {
            return ((p) view.getLayoutParams()).f2916b.left;
        }

        @Deprecated
        public boolean a1(RecyclerView recyclerView, View view, View view2) {
            return x0() || recyclerView.v0();
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            return i0.u.z(this.f2892b);
        }

        public boolean b1(RecyclerView recyclerView, z zVar, View view, View view2) {
            return a1(recyclerView, view, view2);
        }

        public void c(View view, int i7) {
            f(view, i7, true);
        }

        public int c0() {
            return i0.u.A(this.f2892b);
        }

        public void c1(Parcelable parcelable) {
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public Parcelable d1() {
            return null;
        }

        public void e(View view, int i7) {
            f(view, i7, false);
        }

        public int e0() {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e1(int i7) {
        }

        public int f0() {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        void f1(y yVar) {
            if (this.f2897g == yVar) {
                this.f2897g = null;
            }
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0() {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        boolean g1(int i7, Bundle bundle) {
            RecyclerView recyclerView = this.f2892b;
            return h1(recyclerView.f2806g, recyclerView.f2819m0, i7, bundle);
        }

        public void h(View view, int i7) {
            i(view, i7, (p) view.getLayoutParams());
        }

        public int h0(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public boolean h1(v vVar, z zVar, int i7, Bundle bundle) {
            int iW;
            int iO0;
            int i8;
            int i9;
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null) {
                return false;
            }
            if (i7 == 4096) {
                iW = recyclerView.canScrollVertically(1) ? (W() - g0()) - d0() : 0;
                if (this.f2892b.canScrollHorizontally(1)) {
                    iO0 = (o0() - e0()) - f0();
                    i8 = iW;
                    i9 = iO0;
                }
                i8 = iW;
                i9 = 0;
            } else if (i7 != 8192) {
                i9 = 0;
                i8 = 0;
            } else {
                iW = recyclerView.canScrollVertically(-1) ? -((W() - g0()) - d0()) : 0;
                if (this.f2892b.canScrollHorizontally(-1)) {
                    iO0 = -((o0() - e0()) - f0());
                    i8 = iW;
                    i9 = iO0;
                }
                i8 = iW;
                i9 = 0;
            }
            if (i8 == 0 && i9 == 0) {
                return false;
            }
            this.f2892b.q1(i9, i8, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void i(View view, int i7, p pVar) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (c0VarG0.v()) {
                this.f2892b.f2814k.b(c0VarG0);
            } else {
                this.f2892b.f2814k.p(c0VarG0);
            }
            this.f2891a.c(view, i7, pVar, c0VarG0.v());
        }

        boolean i1(View view, int i7, Bundle bundle) {
            RecyclerView recyclerView = this.f2892b;
            return j1(recyclerView.f2806g, recyclerView.f2819m0, view, i7, bundle);
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.k0(view));
            }
        }

        public int j0(View view) {
            return ((p) view.getLayoutParams()).f2916b.right;
        }

        public boolean j1(v vVar, z zVar, View view, int i7, Bundle bundle) {
            return false;
        }

        public boolean k() {
            return false;
        }

        public int k0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView == null || recyclerView.f2826q == null || !l()) {
                return 1;
            }
            return this.f2892b.f2826q.e();
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
            for (int i7 = iJ - 1; i7 >= 0; i7--) {
                View viewN = vVar.n(i7);
                c0 c0VarG0 = RecyclerView.g0(viewN);
                if (!c0VarG0.J()) {
                    c0VarG0.G(false);
                    if (c0VarG0.x()) {
                        this.f2892b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f2892b.R;
                    if (lVar != null) {
                        lVar.j(c0VarG0);
                    }
                    c0VarG0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f2892b.invalidate();
            }
        }

        public boolean m(p pVar) {
            return pVar != null;
        }

        public int m0(View view) {
            return ((p) view.getLayoutParams()).f2916b.top;
        }

        public void m1(View view, v vVar) {
            p1(view);
            vVar.B(view);
        }

        public void n0(View view, boolean z6, Rect rect) {
            Matrix matrix;
            if (z6) {
                Rect rect2 = ((p) view.getLayoutParams()).f2916b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f2892b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f2892b.f2824p;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public void n1(int i7, v vVar) {
            View viewI = I(i7);
            q1(i7);
            vVar.B(viewI);
        }

        public void o(int i7, int i8, z zVar, c cVar) {
        }

        public int o0() {
            return this.f2907q;
        }

        public boolean o1(Runnable runnable) {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public void p(int i7, c cVar) {
        }

        public int p0() {
            return this.f2905o;
        }

        public void p1(View view) {
            this.f2891a.p(view);
        }

        public int q(z zVar) {
            return 0;
        }

        boolean q0() {
            int iJ = J();
            for (int i7 = 0; i7 < iJ; i7++) {
                ViewGroup.LayoutParams layoutParams = I(i7).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public void q1(int i7) {
            if (I(i7) != null) {
                this.f2891a.q(i7);
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public boolean r0() {
            return this.f2899i;
        }

        public boolean r1(RecyclerView recyclerView, View view, Rect rect, boolean z6) {
            return s1(recyclerView, view, rect, z6, false);
        }

        public int s(z zVar) {
            return 0;
        }

        public boolean s0() {
            return this.f2900j;
        }

        public boolean s1(RecyclerView recyclerView, View view, Rect rect, boolean z6, boolean z7) {
            int[] iArrL = L(view, rect);
            int i7 = iArrL[0];
            int i8 = iArrL[1];
            if ((z7 && !t0(recyclerView, i7, i8)) || (i7 == 0 && i8 == 0)) {
                return false;
            }
            if (z6) {
                recyclerView.scrollBy(i7, i8);
            } else {
                recyclerView.n1(i7, i8);
            }
            return true;
        }

        public int t(z zVar) {
            return 0;
        }

        public void t1() {
            RecyclerView recyclerView = this.f2892b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int u(z zVar) {
            return 0;
        }

        public final boolean u0() {
            return this.f2902l;
        }

        public void u1() {
            this.f2898h = true;
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

        public int w1(int i7, v vVar, z zVar) {
            return 0;
        }

        public void x(int i7) {
            y(i7, I(i7));
        }

        public boolean x0() {
            y yVar = this.f2897g;
            return yVar != null && yVar.h();
        }

        public void x1(int i7) {
        }

        public boolean y0(View view, boolean z6, boolean z7) {
            boolean z8 = this.f2895e.b(view, 24579) && this.f2896f.b(view, 24579);
            return z6 ? z8 : !z8;
        }

        public int y1(int i7, v vVar, z zVar) {
            return 0;
        }

        void z(RecyclerView recyclerView) {
            this.f2899i = true;
            G0(recyclerView);
        }

        public void z0(View view, int i7, int i8, int i9, int i10) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f2916b;
            view.layout(i7 + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i8 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i9 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i10 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
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
        public abstract boolean a(int i7, int i8);
    }

    public interface s {
        boolean a(RecyclerView recyclerView, MotionEvent motionEvent);

        void b(RecyclerView recyclerView, MotionEvent motionEvent);

        void c(boolean z6);
    }

    public static abstract class t {
        public void a(RecyclerView recyclerView, int i7) {
        }

        public void b(RecyclerView recyclerView, int i7, int i8) {
        }
    }

    public static class u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        SparseArray<a> f2919a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2920b = 0;

        static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final ArrayList<c0> f2921a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            int f2922b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            long f2923c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            long f2924d = 0;

            a() {
            }
        }

        private a g(int i7) {
            a aVar = this.f2919a.get(i7);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f2919a.put(i7, aVar2);
            return aVar2;
        }

        void a() {
            this.f2920b++;
        }

        public void b() {
            for (int i7 = 0; i7 < this.f2919a.size(); i7++) {
                this.f2919a.valueAt(i7).f2921a.clear();
            }
        }

        void c() {
            this.f2920b--;
        }

        void d(int i7, long j7) {
            a aVarG = g(i7);
            aVarG.f2924d = j(aVarG.f2924d, j7);
        }

        void e(int i7, long j7) {
            a aVarG = g(i7);
            aVarG.f2923c = j(aVarG.f2923c, j7);
        }

        public c0 f(int i7) {
            a aVar = this.f2919a.get(i7);
            if (aVar == null || aVar.f2921a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f2921a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        void h(g gVar, g gVar2, boolean z6) {
            if (gVar != null) {
                c();
            }
            if (!z6 && this.f2920b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f2921a;
            if (this.f2919a.get(iL).f2922b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        long j(long j7, long j8) {
            return j7 == 0 ? j8 : ((j7 / 4) * 3) + (j8 / 4);
        }

        boolean k(int i7, long j7, long j8) {
            long j9 = g(i7).f2924d;
            return j9 == 0 || j7 + j9 < j8;
        }

        boolean l(int i7, long j7, long j8) {
            long j9 = g(i7).f2923c;
            return j9 == 0 || j7 + j9 < j8;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ArrayList<c0> f2925a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<c0> f2926b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<c0> f2927c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<c0> f2928d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f2929e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2930f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        u f2931g;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f2925a = arrayList;
            this.f2926b = null;
            this.f2927c = new ArrayList<>();
            this.f2928d = Collections.unmodifiableList(arrayList);
            this.f2929e = 2;
            this.f2930f = 2;
        }

        private boolean H(c0 c0Var, int i7, int i8, long j7) {
            c0Var.f2874r = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j7 != Long.MAX_VALUE && !this.f2931g.k(iL, nanoTime, j7)) {
                return false;
            }
            RecyclerView.this.f2826q.c(c0Var, i7);
            this.f2931g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.f2819m0.e()) {
                return true;
            }
            c0Var.f2863g = i8;
            return true;
        }

        private void b(c0 c0Var) {
            if (RecyclerView.this.u0()) {
                View view = c0Var.f2857a;
                if (i0.u.w(view) == 0) {
                    i0.u.t0(view, 1);
                }
                androidx.recyclerview.widget.o oVar = RecyclerView.this.f2833t0;
                if (oVar == null) {
                    return;
                }
                i0.a aVarN = oVar.n();
                if (aVarN instanceof o.a) {
                    ((o.a) aVarN).o(view);
                }
                i0.u.k0(view, aVarN);
            }
        }

        private void q(ViewGroup viewGroup, boolean z6) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    q((ViewGroup) childAt, true);
                }
            }
            if (z6) {
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
            View view = c0Var.f2857a;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        void A(int i7) {
            a(this.f2927c.get(i7), true);
            this.f2927c.remove(i7);
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
            if (RecyclerView.this.R == null || c0VarG0.u()) {
                return;
            }
            RecyclerView.this.R.j(c0VarG0);
        }

        void C(c0 c0Var) {
            boolean z6;
            boolean z7 = true;
            if (c0Var.w() || c0Var.f2857a.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f2857a.getParent() != null);
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
            g gVar = RecyclerView.this.f2826q;
            if ((gVar != null && zH && gVar.u(c0Var)) || c0Var.u()) {
                if (this.f2930f <= 0 || c0Var.p(526)) {
                    z6 = false;
                } else {
                    int size = this.f2927c.size();
                    if (size >= this.f2930f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.H0 && size > 0 && !RecyclerView.this.f2817l0.d(c0Var.f2859c)) {
                        int i7 = size - 1;
                        while (i7 >= 0) {
                            if (!RecyclerView.this.f2817l0.d(this.f2927c.get(i7).f2859c)) {
                                break;
                            } else {
                                i7--;
                            }
                        }
                        size = i7 + 1;
                    }
                    this.f2927c.add(size, c0Var);
                    z6 = true;
                }
                if (z6) {
                    z7 = false;
                } else {
                    a(c0Var, true);
                }
                z = z6;
            } else {
                z7 = false;
            }
            RecyclerView.this.f2814k.q(c0Var);
            if (z || z7 || !zH) {
                return;
            }
            c0Var.f2874r = null;
        }

        void D(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            if (!c0VarG0.p(12) && c0VarG0.y() && !RecyclerView.this.q(c0VarG0)) {
                if (this.f2926b == null) {
                    this.f2926b = new ArrayList<>();
                }
                c0VarG0.H(this, true);
                this.f2926b.add(c0VarG0);
                return;
            }
            if (!c0VarG0.t() || c0VarG0.v() || RecyclerView.this.f2826q.i()) {
                c0VarG0.H(this, false);
                this.f2925a.add(c0VarG0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        void E(u uVar) {
            u uVar2 = this.f2931g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.f2931g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f2931g.a();
        }

        void F(a0 a0Var) {
        }

        public void G(int i7) {
            this.f2929e = i7;
            K();
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x012c A[PHI: r1 r4
  0x012c: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$c0) = (r1v11 androidx.recyclerview.widget.RecyclerView$c0), (r1v30 androidx.recyclerview.widget.RecyclerView$c0) binds: [B:28:0x005d, B:45:0x00ad] A[DONT_GENERATE, DONT_INLINE]
  0x012c: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:28:0x005d, B:45:0x00ad] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x014d  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0179  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01a9  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01b7  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x01d3 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01d6  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        androidx.recyclerview.widget.RecyclerView.c0 I(int r17, boolean r18, long r19) {
            /*
                Method dump skipped, instruction units count: 530
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.v.I(int, boolean, long):androidx.recyclerview.widget.RecyclerView$c0");
        }

        void J(c0 c0Var) {
            if (c0Var.f2871o) {
                this.f2926b.remove(c0Var);
            } else {
                this.f2925a.remove(c0Var);
            }
            c0Var.f2870n = null;
            c0Var.f2871o = false;
            c0Var.e();
        }

        void K() {
            o oVar = RecyclerView.this.f2828r;
            this.f2930f = this.f2929e + (oVar != null ? oVar.f2903m : 0);
            for (int size = this.f2927c.size() - 1; size >= 0 && this.f2927c.size() > this.f2930f; size--) {
                A(size);
            }
        }

        boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.f2819m0.e();
            }
            int i7 = c0Var.f2859c;
            if (i7 >= 0 && i7 < RecyclerView.this.f2826q.e()) {
                if (RecyclerView.this.f2819m0.e() || RecyclerView.this.f2826q.g(c0Var.f2859c) == c0Var.l()) {
                    return !RecyclerView.this.f2826q.i() || c0Var.k() == RecyclerView.this.f2826q.f(c0Var.f2859c);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.Q());
        }

        void M(int i7, int i8) {
            int i9;
            int i10 = i8 + i7;
            for (int size = this.f2927c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f2927c.get(size);
                if (c0Var != null && (i9 = c0Var.f2859c) >= i7 && i9 < i10) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        void a(c0 c0Var, boolean z6) {
            RecyclerView.s(c0Var);
            View view = c0Var.f2857a;
            androidx.recyclerview.widget.o oVar = RecyclerView.this.f2833t0;
            if (oVar != null) {
                i0.a aVarN = oVar.n();
                i0.u.k0(view, aVarN instanceof o.a ? ((o.a) aVarN).n(view) : null);
            }
            if (z6) {
                g(c0Var);
            }
            c0Var.f2874r = null;
            i().i(c0Var);
        }

        public void c() {
            this.f2925a.clear();
            z();
        }

        void d() {
            int size = this.f2927c.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.f2927c.get(i7).c();
            }
            int size2 = this.f2925a.size();
            for (int i8 = 0; i8 < size2; i8++) {
                this.f2925a.get(i8).c();
            }
            ArrayList<c0> arrayList = this.f2926b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i9 = 0; i9 < size3; i9++) {
                    this.f2926b.get(i9).c();
                }
            }
        }

        void e() {
            this.f2925a.clear();
            ArrayList<c0> arrayList = this.f2926b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i7) {
            if (i7 >= 0 && i7 < RecyclerView.this.f2819m0.b()) {
                return !RecyclerView.this.f2819m0.e() ? i7 : RecyclerView.this.f2810i.m(i7);
            }
            throw new IndexOutOfBoundsException("invalid position " + i7 + ". State item count is " + RecyclerView.this.f2819m0.b() + RecyclerView.this.Q());
        }

        void g(c0 c0Var) {
            w wVar = RecyclerView.this.f2830s;
            if (wVar != null) {
                wVar.a(c0Var);
            }
            g gVar = RecyclerView.this.f2826q;
            if (gVar != null) {
                gVar.x(c0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.f2819m0 != null) {
                recyclerView.f2814k.q(c0Var);
            }
        }

        c0 h(int i7) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f2926b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i8 = 0; i8 < size; i8++) {
                    c0 c0Var = this.f2926b.get(i8);
                    if (!c0Var.L() && c0Var.m() == i7) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.f2826q.i() && (iM = RecyclerView.this.f2810i.m(i7)) > 0 && iM < RecyclerView.this.f2826q.e()) {
                    long jF = RecyclerView.this.f2826q.f(iM);
                    for (int i9 = 0; i9 < size; i9++) {
                        c0 c0Var2 = this.f2926b.get(i9);
                        if (!c0Var2.L() && c0Var2.k() == jF) {
                            c0Var2.b(32);
                            return c0Var2;
                        }
                    }
                }
            }
            return null;
        }

        u i() {
            if (this.f2931g == null) {
                this.f2931g = new u();
            }
            return this.f2931g;
        }

        int j() {
            return this.f2925a.size();
        }

        public List<c0> k() {
            return this.f2928d;
        }

        c0 l(long j7, int i7, boolean z6) {
            for (int size = this.f2925a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f2925a.get(size);
                if (c0Var.k() == j7 && !c0Var.L()) {
                    if (i7 == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.f2819m0.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z6) {
                        this.f2925a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f2857a, false);
                        y(c0Var.f2857a);
                    }
                }
            }
            int size2 = this.f2927c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f2927c.get(size2);
                if (c0Var2.k() == j7 && !c0Var2.r()) {
                    if (i7 == c0Var2.l()) {
                        if (!z6) {
                            this.f2927c.remove(size2);
                        }
                        return c0Var2;
                    }
                    if (!z6) {
                        A(size2);
                        return null;
                    }
                }
            }
        }

        c0 m(int i7, boolean z6) {
            View viewE;
            int size = this.f2925a.size();
            for (int i8 = 0; i8 < size; i8++) {
                c0 c0Var = this.f2925a.get(i8);
                if (!c0Var.L() && c0Var.m() == i7 && !c0Var.t() && (RecyclerView.this.f2819m0.f2956h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z6 || (viewE = RecyclerView.this.f2812j.e(i7)) == null) {
                int size2 = this.f2927c.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    c0 c0Var2 = this.f2927c.get(i9);
                    if (!c0Var2.t() && c0Var2.m() == i7 && !c0Var2.r()) {
                        if (!z6) {
                            this.f2927c.remove(i9);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarG0 = RecyclerView.g0(viewE);
            RecyclerView.this.f2812j.s(viewE);
            int iM = RecyclerView.this.f2812j.m(viewE);
            if (iM != -1) {
                RecyclerView.this.f2812j.d(iM);
                D(viewE);
                c0VarG0.b(8224);
                return c0VarG0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarG0 + RecyclerView.this.Q());
        }

        View n(int i7) {
            return this.f2925a.get(i7).f2857a;
        }

        public View o(int i7) {
            return p(i7, false);
        }

        View p(int i7, boolean z6) {
            return I(i7, z6, Long.MAX_VALUE).f2857a;
        }

        void s() {
            int size = this.f2927c.size();
            for (int i7 = 0; i7 < size; i7++) {
                p pVar = (p) this.f2927c.get(i7).f2857a.getLayoutParams();
                if (pVar != null) {
                    pVar.f2917c = true;
                }
            }
        }

        void t() {
            int size = this.f2927c.size();
            for (int i7 = 0; i7 < size; i7++) {
                c0 c0Var = this.f2927c.get(i7);
                if (c0Var != null) {
                    c0Var.b(6);
                    c0Var.a(null);
                }
            }
            g gVar = RecyclerView.this.f2826q;
            if (gVar == null || !gVar.i()) {
                z();
            }
        }

        void u(int i7, int i8) {
            int size = this.f2927c.size();
            for (int i9 = 0; i9 < size; i9++) {
                c0 c0Var = this.f2927c.get(i9);
                if (c0Var != null && c0Var.f2859c >= i7) {
                    c0Var.A(i8, true);
                }
            }
        }

        void v(int i7, int i8) {
            int i9;
            int i10;
            int i11;
            int i12;
            if (i7 < i8) {
                i9 = -1;
                i11 = i7;
                i10 = i8;
            } else {
                i9 = 1;
                i10 = i7;
                i11 = i8;
            }
            int size = this.f2927c.size();
            for (int i13 = 0; i13 < size; i13++) {
                c0 c0Var = this.f2927c.get(i13);
                if (c0Var != null && (i12 = c0Var.f2859c) >= i11 && i12 <= i10) {
                    if (i12 == i7) {
                        c0Var.A(i8 - i7, false);
                    } else {
                        c0Var.A(i9, false);
                    }
                }
            }
        }

        void w(int i7, int i8, boolean z6) {
            int i9 = i7 + i8;
            for (int size = this.f2927c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f2927c.get(size);
                if (c0Var != null) {
                    int i10 = c0Var.f2859c;
                    if (i10 >= i9) {
                        c0Var.A(-i8, z6);
                    } else if (i10 >= i7) {
                        c0Var.b(8);
                        A(size);
                    }
                }
            }
        }

        void x(g gVar, g gVar2, boolean z6) {
            c();
            i().h(gVar, gVar2, z6);
        }

        void y(View view) {
            c0 c0VarG0 = RecyclerView.g0(view);
            c0VarG0.f2870n = null;
            c0VarG0.f2871o = false;
            c0VarG0.e();
            C(c0VarG0);
        }

        void z() {
            for (int size = this.f2927c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f2927c.clear();
            if (RecyclerView.H0) {
                RecyclerView.this.f2817l0.b();
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
            recyclerView.f2819m0.f2955g = true;
            recyclerView.Q0(true);
            if (RecyclerView.this.f2810i.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i7, int i8, Object obj) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f2810i.r(i7, i8, obj)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i7, int i8) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f2810i.s(i7, i8)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void e(int i7, int i8, int i9) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f2810i.t(i7, i8, i9)) {
                g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void f(int i7, int i8) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.f2810i.u(i7, i8)) {
                g();
            }
        }

        void g() {
            if (RecyclerView.G0) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.f2840x && recyclerView.f2838w) {
                    i0.u.c0(recyclerView, recyclerView.f2818m);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.F = true;
            recyclerView2.requestLayout();
        }
    }

    public static abstract class y {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private RecyclerView f2935b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private o f2936c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2937d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2938e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private View f2939f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f2941h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2934a = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final a f2940g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private int f2942a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private int f2943b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f2944c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f2945d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            private Interpolator f2946e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            private boolean f2947f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            private int f2948g;

            public a(int i7, int i8) {
                this(i7, i8, Integer.MIN_VALUE, null);
            }

            private void e() {
                if (this.f2946e != null && this.f2944c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f2944c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            boolean a() {
                return this.f2945d >= 0;
            }

            public void b(int i7) {
                this.f2945d = i7;
            }

            void c(RecyclerView recyclerView) {
                int i7 = this.f2945d;
                if (i7 >= 0) {
                    this.f2945d = -1;
                    recyclerView.x0(i7);
                    this.f2947f = false;
                } else {
                    if (!this.f2947f) {
                        this.f2948g = 0;
                        return;
                    }
                    e();
                    recyclerView.f2813j0.f(this.f2942a, this.f2943b, this.f2944c, this.f2946e);
                    int i8 = this.f2948g + 1;
                    this.f2948g = i8;
                    if (i8 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f2947f = false;
                }
            }

            public void d(int i7, int i8, int i9, Interpolator interpolator) {
                this.f2942a = i7;
                this.f2943b = i8;
                this.f2944c = i9;
                this.f2946e = interpolator;
                this.f2947f = true;
            }

            public a(int i7, int i8, int i9, Interpolator interpolator) {
                this.f2945d = -1;
                this.f2947f = false;
                this.f2948g = 0;
                this.f2942a = i7;
                this.f2943b = i8;
                this.f2944c = i9;
                this.f2946e = interpolator;
            }
        }

        public interface b {
            PointF a(int i7);
        }

        public PointF a(int i7) {
            Object objE = e();
            if (objE instanceof b) {
                return ((b) objE).a(i7);
            }
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        public View b(int i7) {
            return this.f2935b.f2828r.C(i7);
        }

        public int c() {
            return this.f2935b.f2828r.J();
        }

        public int d(View view) {
            return this.f2935b.e0(view);
        }

        public o e() {
            return this.f2936c;
        }

        public int f() {
            return this.f2934a;
        }

        public boolean g() {
            return this.f2937d;
        }

        public boolean h() {
            return this.f2938e;
        }

        protected void i(PointF pointF) {
            float f7 = pointF.x;
            float f8 = pointF.y;
            float fSqrt = (float) Math.sqrt((f7 * f7) + (f8 * f8));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        void j(int i7, int i8) {
            PointF pointFA;
            RecyclerView recyclerView = this.f2935b;
            if (this.f2934a == -1 || recyclerView == null) {
                r();
            }
            if (this.f2937d && this.f2939f == null && this.f2936c != null && (pointFA = a(this.f2934a)) != null) {
                float f7 = pointFA.x;
                if (f7 != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.i1((int) Math.signum(f7), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f2937d = false;
            View view = this.f2939f;
            if (view != null) {
                if (d(view) == this.f2934a) {
                    o(this.f2939f, recyclerView.f2819m0, this.f2940g);
                    this.f2940g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f2939f = null;
                }
            }
            if (this.f2938e) {
                l(i7, i8, recyclerView.f2819m0, this.f2940g);
                boolean zA = this.f2940g.a();
                this.f2940g.c(recyclerView);
                if (zA && this.f2938e) {
                    this.f2937d = true;
                    recyclerView.f2813j0.e();
                }
            }
        }

        protected void k(View view) {
            if (d(view) == f()) {
                this.f2939f = view;
            }
        }

        protected abstract void l(int i7, int i8, z zVar, a aVar);

        protected abstract void m();

        protected abstract void n();

        protected abstract void o(View view, z zVar, a aVar);

        public void p(int i7) {
            this.f2934a = i7;
        }

        void q(RecyclerView recyclerView, o oVar) {
            recyclerView.f2813j0.g();
            if (this.f2941h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f2935b = recyclerView;
            this.f2936c = oVar;
            int i7 = this.f2934a;
            if (i7 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.f2819m0.f2949a = i7;
            this.f2938e = true;
            this.f2937d = true;
            this.f2939f = b(f());
            m();
            this.f2935b.f2813j0.e();
            this.f2941h = true;
        }

        protected final void r() {
            if (this.f2938e) {
                this.f2938e = false;
                n();
                this.f2935b.f2819m0.f2949a = -1;
                this.f2939f = null;
                this.f2934a = -1;
                this.f2937d = false;
                this.f2936c.f1(this);
                this.f2936c = null;
                this.f2935b = null;
            }
        }
    }

    public static class z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SparseArray<Object> f2950b;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        int f2961m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        long f2962n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        int f2963o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        int f2964p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        int f2965q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2949a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2951c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2952d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2953e = 1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f2954f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f2955g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f2956h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f2957i = false;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f2958j = false;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        boolean f2959k = false;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        boolean f2960l = false;

        void a(int i7) {
            if ((this.f2953e & i7) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i7) + " but it is " + Integer.toBinaryString(this.f2953e));
        }

        public int b() {
            return this.f2956h ? this.f2951c - this.f2952d : this.f2954f;
        }

        public int c() {
            return this.f2949a;
        }

        public boolean d() {
            return this.f2949a != -1;
        }

        public boolean e() {
            return this.f2956h;
        }

        void f(g gVar) {
            this.f2953e = 1;
            this.f2954f = gVar.e();
            this.f2956h = false;
            this.f2957i = false;
            this.f2958j = false;
        }

        public boolean g() {
            return this.f2960l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f2949a + ", mData=" + this.f2950b + ", mItemCount=" + this.f2954f + ", mIsMeasuring=" + this.f2958j + ", mPreviousLayoutItemCount=" + this.f2951c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f2952d + ", mStructureChanged=" + this.f2955g + ", mInPreLayout=" + this.f2956h + ", mRunSimpleAnimations=" + this.f2959k + ", mRunPredictiveAnimations=" + this.f2960l + '}';
        }
    }

    static {
        Class<?> cls = Integer.TYPE;
        K0 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        L0 = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void B() {
        int i7 = this.E;
        this.E = 0;
        if (i7 == 0 || !u0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        j0.b.b(accessibilityEventObtain, i7);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private void D() {
        this.f2819m0.a(1);
        R(this.f2819m0);
        this.f2819m0.f2958j = false;
        s1();
        this.f2814k.f();
        H0();
        P0();
        f1();
        z zVar = this.f2819m0;
        zVar.f2957i = zVar.f2959k && this.f2827q0;
        this.f2827q0 = false;
        this.f2825p0 = false;
        zVar.f2956h = zVar.f2960l;
        zVar.f2954f = this.f2826q.e();
        V(this.f2837v0);
        if (this.f2819m0.f2959k) {
            int iG = this.f2812j.g();
            for (int i7 = 0; i7 < iG; i7++) {
                c0 c0VarG0 = g0(this.f2812j.f(i7));
                if (!c0VarG0.J() && (!c0VarG0.t() || this.f2826q.i())) {
                    this.f2814k.e(c0VarG0, this.R.t(this.f2819m0, c0VarG0, l.e(c0VarG0), c0VarG0.o()));
                    if (this.f2819m0.f2957i && c0VarG0.y() && !c0VarG0.v() && !c0VarG0.J() && !c0VarG0.t()) {
                        this.f2814k.c(d0(c0VarG0), c0VarG0);
                    }
                }
            }
        }
        if (this.f2819m0.f2960l) {
            g1();
            z zVar2 = this.f2819m0;
            boolean z6 = zVar2.f2955g;
            zVar2.f2955g = false;
            this.f2828r.X0(this.f2806g, zVar2);
            this.f2819m0.f2955g = z6;
            for (int i8 = 0; i8 < this.f2812j.g(); i8++) {
                c0 c0VarG02 = g0(this.f2812j.f(i8));
                if (!c0VarG02.J() && !this.f2814k.i(c0VarG02)) {
                    int iE = l.e(c0VarG02);
                    boolean zP = c0VarG02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.R.t(this.f2819m0, c0VarG02, iE, c0VarG02.o());
                    if (zP) {
                        S0(c0VarG02, cVarT);
                    } else {
                        this.f2814k.a(c0VarG02, cVarT);
                    }
                }
            }
            t();
        } else {
            t();
        }
        I0();
        u1(false);
        this.f2819m0.f2953e = 2;
    }

    private void E() {
        s1();
        H0();
        this.f2819m0.a(6);
        this.f2810i.j();
        this.f2819m0.f2954f = this.f2826q.e();
        z zVar = this.f2819m0;
        zVar.f2952d = 0;
        zVar.f2956h = false;
        this.f2828r.X0(this.f2806g, zVar);
        z zVar2 = this.f2819m0;
        zVar2.f2955g = false;
        this.f2808h = null;
        zVar2.f2959k = zVar2.f2959k && this.R != null;
        zVar2.f2953e = 4;
        I0();
        u1(false);
    }

    private void F() {
        this.f2819m0.a(4);
        s1();
        H0();
        z zVar = this.f2819m0;
        zVar.f2953e = 1;
        if (zVar.f2959k) {
            for (int iG = this.f2812j.g() - 1; iG >= 0; iG--) {
                c0 c0VarG0 = g0(this.f2812j.f(iG));
                if (!c0VarG0.J()) {
                    long jD0 = d0(c0VarG0);
                    l.c cVarS = this.R.s(this.f2819m0, c0VarG0);
                    c0 c0VarG = this.f2814k.g(jD0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.f2814k.d(c0VarG0, cVarS);
                    } else {
                        boolean zH = this.f2814k.h(c0VarG);
                        boolean zH2 = this.f2814k.h(c0VarG0);
                        if (zH && c0VarG == c0VarG0) {
                            this.f2814k.d(c0VarG0, cVarS);
                        } else {
                            l.c cVarN = this.f2814k.n(c0VarG);
                            this.f2814k.d(c0VarG0, cVarS);
                            l.c cVarM = this.f2814k.m(c0VarG0);
                            if (cVarN == null) {
                                m0(jD0, c0VarG0, c0VarG);
                            } else {
                                n(c0VarG, c0VarG0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.f2814k.o(this.C0);
        }
        this.f2828r.l1(this.f2806g);
        z zVar2 = this.f2819m0;
        zVar2.f2951c = zVar2.f2954f;
        this.I = false;
        this.J = false;
        zVar2.f2959k = false;
        zVar2.f2960l = false;
        this.f2828r.f2898h = false;
        ArrayList<c0> arrayList = this.f2806g.f2926b;
        if (arrayList != null) {
            arrayList.clear();
        }
        o oVar = this.f2828r;
        if (oVar.f2904n) {
            oVar.f2903m = 0;
            oVar.f2904n = false;
            this.f2806g.K();
        }
        this.f2828r.Y0(this.f2819m0);
        I0();
        u1(false);
        this.f2814k.f();
        int[] iArr = this.f2837v0;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        T0();
        d1();
    }

    private void K0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.T) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.T = motionEvent.getPointerId(i7);
            int x6 = (int) (motionEvent.getX(i7) + 0.5f);
            this.f2799a0 = x6;
            this.V = x6;
            int y6 = (int) (motionEvent.getY(i7) + 0.5f);
            this.f2800b0 = y6;
            this.W = y6;
        }
    }

    private boolean L(MotionEvent motionEvent) {
        s sVar = this.f2836v;
        if (sVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return U(motionEvent);
        }
        sVar.b(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.f2836v = null;
        }
        return true;
    }

    private boolean O0() {
        return this.R != null && this.f2828r.L1();
    }

    private void P0() {
        boolean z6;
        if (this.I) {
            this.f2810i.y();
            if (this.J) {
                this.f2828r.S0(this);
            }
        }
        if (O0()) {
            this.f2810i.w();
        } else {
            this.f2810i.j();
        }
        boolean z7 = false;
        boolean z8 = this.f2825p0 || this.f2827q0;
        this.f2819m0.f2959k = this.f2844z && this.R != null && ((z6 = this.I) || z8 || this.f2828r.f2898h) && (!z6 || this.f2826q.i());
        z zVar = this.f2819m0;
        if (zVar.f2959k && z8 && !this.I && O0()) {
            z7 = true;
        }
        zVar.f2960l = z7;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
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
            r6.N()
            android.widget.EdgeEffect r1 = r6.N
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
            android.widget.EdgeEffect r1 = r6.P
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
            android.widget.EdgeEffect r9 = r6.O
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
            android.widget.EdgeEffect r9 = r6.Q
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
            i0.u.b0(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.R0(float, float, float, float):void");
    }

    private void T0() {
        View viewFindViewById;
        if (!this.f2811i0 || this.f2826q == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!J0 || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.f2812j.n(focusedChild)) {
                    return;
                }
            } else if (this.f2812j.g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewX = null;
        c0 c0VarZ = (this.f2819m0.f2962n == -1 || !this.f2826q.i()) ? null : Z(this.f2819m0.f2962n);
        if (c0VarZ != null && !this.f2812j.n(c0VarZ.f2857a) && c0VarZ.f2857a.hasFocusable()) {
            viewX = c0VarZ.f2857a;
        } else if (this.f2812j.g() > 0) {
            viewX = X();
        }
        if (viewX != null) {
            int i7 = this.f2819m0.f2963o;
            if (i7 != -1 && (viewFindViewById = viewX.findViewById(i7)) != null && viewFindViewById.isFocusable()) {
                viewX = viewFindViewById;
            }
            viewX.requestFocus();
        }
    }

    private boolean U(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.f2834u.size();
        for (int i7 = 0; i7 < size; i7++) {
            s sVar = this.f2834u.get(i7);
            if (sVar.a(this, motionEvent) && action != 3) {
                this.f2836v = sVar;
                return true;
            }
        }
        return false;
    }

    private void U0() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.N.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.O;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        EdgeEffect edgeEffect3 = this.P;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.P.isFinished();
        }
        EdgeEffect edgeEffect4 = this.Q;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.Q.isFinished();
        }
        if (zIsFinished) {
            i0.u.b0(this);
        }
    }

    private void V(int[] iArr) {
        int iG = this.f2812j.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i7 = Integer.MAX_VALUE;
        int i8 = Integer.MIN_VALUE;
        for (int i9 = 0; i9 < iG; i9++) {
            c0 c0VarG0 = g0(this.f2812j.f(i9));
            if (!c0VarG0.J()) {
                int iM = c0VarG0.m();
                if (iM < i7) {
                    i7 = iM;
                }
                if (iM > i8) {
                    i8 = iM;
                }
            }
        }
        iArr[0] = i7;
        iArr[1] = i8;
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
        for (int i7 = 0; i7 < childCount; i7++) {
            RecyclerView recyclerViewW = W(viewGroup.getChildAt(i7));
            if (recyclerViewW != null) {
                return recyclerViewW;
            }
        }
        return null;
    }

    private View X() {
        c0 c0VarY;
        z zVar = this.f2819m0;
        int i7 = zVar.f2961m;
        if (i7 == -1) {
            i7 = 0;
        }
        int iB = zVar.b();
        for (int i8 = i7; i8 < iB; i8++) {
            c0 c0VarY2 = Y(i8);
            if (c0VarY2 == null) {
                break;
            }
            if (c0VarY2.f2857a.hasFocusable()) {
                return c0VarY2.f2857a;
            }
        }
        int iMin = Math.min(iB, i7);
        do {
            iMin--;
            if (iMin < 0 || (c0VarY = Y(iMin)) == null) {
                return null;
            }
        } while (!c0VarY.f2857a.hasFocusable());
        return c0VarY.f2857a;
    }

    private void c1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.f2820n.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f2917c) {
                Rect rect = pVar.f2916b;
                Rect rect2 = this.f2820n;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.f2820n);
            offsetRectIntoDescendantCoords(view, this.f2820n);
        }
        this.f2828r.s1(this, view, this.f2820n, !this.f2844z, view2 == null);
    }

    private void d1() {
        z zVar = this.f2819m0;
        zVar.f2962n = -1L;
        zVar.f2961m = -1;
        zVar.f2963o = -1;
    }

    private void e1() {
        VelocityTracker velocityTracker = this.U;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        v1(0);
        U0();
    }

    private void f1() {
        View focusedChild = (this.f2811i0 && hasFocus() && this.f2826q != null) ? getFocusedChild() : null;
        c0 c0VarT = focusedChild != null ? T(focusedChild) : null;
        if (c0VarT == null) {
            d1();
            return;
        }
        this.f2819m0.f2962n = this.f2826q.i() ? c0VarT.k() : -1L;
        this.f2819m0.f2961m = this.I ? -1 : c0VarT.v() ? c0VarT.f2860d : c0VarT.j();
        this.f2819m0.f2963o = i0(c0VarT.f2857a);
    }

    private void g(c0 c0Var) {
        View view = c0Var.f2857a;
        boolean z6 = view.getParent() == this;
        this.f2806g.J(f0(view));
        if (c0Var.x()) {
            this.f2812j.c(view, -1, view.getLayoutParams(), true);
        } else if (z6) {
            this.f2812j.k(view);
        } else {
            this.f2812j.b(view, true);
        }
    }

    static c0 g0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f2915a;
    }

    private i0.l getScrollingChildHelper() {
        if (this.f2839w0 == null) {
            this.f2839w0 = new i0.l(this);
        }
        return this.f2839w0;
    }

    static void h0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f2916b;
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

    private String j0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    private void k1(g gVar, boolean z6, boolean z7) {
        g gVar2 = this.f2826q;
        if (gVar2 != null) {
            gVar2.A(this.f2803e);
            this.f2826q.t(this);
        }
        if (!z6 || z7) {
            V0();
        }
        this.f2810i.y();
        g gVar3 = this.f2826q;
        this.f2826q = gVar;
        if (gVar != null) {
            gVar.y(this.f2803e);
            gVar.p(this);
        }
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.E0(gVar3, this.f2826q);
        }
        this.f2806g.x(gVar3, this.f2826q, z6);
        this.f2819m0.f2955g = true;
    }

    private void m0(long j7, c0 c0Var, c0 c0Var2) {
        int iG = this.f2812j.g();
        for (int i7 = 0; i7 < iG; i7++) {
            c0 c0VarG0 = g0(this.f2812j.f(i7));
            if (c0VarG0 != c0Var && d0(c0VarG0) == j7) {
                g gVar = this.f2826q;
                if (gVar == null || !gVar.i()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarG0 + " \n View Holder 2:" + c0Var + Q());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + Q());
    }

    private void n(c0 c0Var, c0 c0Var2, l.c cVar, l.c cVar2, boolean z6, boolean z7) {
        c0Var.G(false);
        if (z6) {
            g(c0Var);
        }
        if (c0Var != c0Var2) {
            if (z7) {
                g(c0Var2);
            }
            c0Var.f2864h = c0Var2;
            g(c0Var);
            this.f2806g.J(c0Var);
            c0Var2.G(false);
            c0Var2.f2865i = c0Var;
        }
        if (this.R.b(c0Var, c0Var2, cVar, cVar2)) {
            N0();
        }
    }

    private boolean o0() {
        int iG = this.f2812j.g();
        for (int i7 = 0; i7 < iG; i7++) {
            c0 c0VarG0 = g0(this.f2812j.f(i7));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.y()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void q0() {
        if (i0.u.x(this) == 0) {
            i0.u.u0(this, 8);
        }
    }

    private void r() {
        e1();
        setScrollState(0);
    }

    private void r0() {
        this.f2812j = new androidx.recyclerview.widget.d(new e());
    }

    static void s(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f2858b;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f2857a) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f2858b = null;
        }
    }

    private void w(Context context, String str, AttributeSet attributeSet, int i7, int i8) {
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
                    constructor = clsAsSubclass.getConstructor(K0);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i7), Integer.valueOf(i8)};
                } catch (NoSuchMethodException e7) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e8) {
                        e8.initCause(e7);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strJ0, e8);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e9) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strJ0, e9);
            } catch (ClassNotFoundException e10) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strJ0, e10);
            } catch (IllegalAccessException e11) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strJ0, e11);
            } catch (InstantiationException e12) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e12);
            } catch (InvocationTargetException e13) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strJ0, e13);
            }
        }
    }

    private boolean w0(View view, View view2, int i7) {
        int i8;
        if (view2 == null || view2 == this || S(view2) == null) {
            return false;
        }
        if (view == null || S(view) == null) {
            return true;
        }
        this.f2820n.set(0, 0, view.getWidth(), view.getHeight());
        this.f2822o.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.f2820n);
        offsetDescendantRectToMyCoords(view2, this.f2822o);
        byte b7 = -1;
        int i9 = this.f2828r.Z() == 1 ? -1 : 1;
        Rect rect = this.f2820n;
        int i10 = rect.left;
        Rect rect2 = this.f2822o;
        int i11 = rect2.left;
        if ((i10 < i11 || rect.right <= i11) && rect.right < rect2.right) {
            i8 = 1;
        } else {
            int i12 = rect.right;
            int i13 = rect2.right;
            i8 = ((i12 > i13 || i10 >= i13) && i10 > i11) ? -1 : 0;
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        if ((i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom) {
            b7 = 1;
        } else {
            int i16 = rect.bottom;
            int i17 = rect2.bottom;
            if ((i16 <= i17 && i14 < i17) || i14 <= i15) {
                b7 = 0;
            }
        }
        if (i7 == 1) {
            return b7 < 0 || (b7 == 0 && i8 * i9 <= 0);
        }
        if (i7 == 2) {
            return b7 > 0 || (b7 == 0 && i8 * i9 >= 0);
        }
        if (i7 == 17) {
            return i8 < 0;
        }
        if (i7 == 33) {
            return b7 < 0;
        }
        if (i7 == 66) {
            return i8 > 0;
        }
        if (i7 == 130) {
            return b7 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i7 + Q());
    }

    private void x1() {
        this.f2813j0.g();
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.K1();
        }
    }

    private boolean y(int i7, int i8) {
        V(this.f2837v0);
        int[] iArr = this.f2837v0;
        return (iArr[0] == i7 && iArr[1] == i8) ? false : true;
    }

    void A(View view) {
        c0 c0VarG0 = g0(view);
        G0(view);
        g gVar = this.f2826q;
        if (gVar != null && c0VarG0 != null) {
            gVar.w(c0VarG0);
        }
        List<q> list = this.H;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.H.get(size).b(view);
            }
        }
    }

    public void A0(int i7) {
        int iG = this.f2812j.g();
        for (int i8 = 0; i8 < iG; i8++) {
            this.f2812j.f(i8).offsetLeftAndRight(i7);
        }
    }

    public void B0(int i7) {
        int iG = this.f2812j.g();
        for (int i8 = 0; i8 < iG; i8++) {
            this.f2812j.f(i8).offsetTopAndBottom(i7);
        }
    }

    void C() {
        if (this.f2826q == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.f2828r == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        z zVar = this.f2819m0;
        zVar.f2958j = false;
        if (zVar.f2953e == 1) {
            D();
            this.f2828r.z1(this);
            E();
        } else if (!this.f2810i.q() && this.f2828r.o0() == getWidth() && this.f2828r.W() == getHeight()) {
            this.f2828r.z1(this);
        } else {
            this.f2828r.z1(this);
            E();
        }
        F();
    }

    void C0(int i7, int i8) {
        int iJ = this.f2812j.j();
        for (int i9 = 0; i9 < iJ; i9++) {
            c0 c0VarG0 = g0(this.f2812j.i(i9));
            if (c0VarG0 != null && !c0VarG0.J() && c0VarG0.f2859c >= i7) {
                c0VarG0.A(i8, false);
                this.f2819m0.f2955g = true;
            }
        }
        this.f2806g.u(i7, i8);
        requestLayout();
    }

    void D0(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int iJ = this.f2812j.j();
        if (i7 < i8) {
            i11 = -1;
            i10 = i7;
            i9 = i8;
        } else {
            i9 = i7;
            i10 = i8;
            i11 = 1;
        }
        for (int i13 = 0; i13 < iJ; i13++) {
            c0 c0VarG0 = g0(this.f2812j.i(i13));
            if (c0VarG0 != null && (i12 = c0VarG0.f2859c) >= i10 && i12 <= i9) {
                if (i12 == i7) {
                    c0VarG0.A(i8 - i7, false);
                } else {
                    c0VarG0.A(i11, false);
                }
                this.f2819m0.f2955g = true;
            }
        }
        this.f2806g.v(i7, i8);
        requestLayout();
    }

    void E0(int i7, int i8, boolean z6) {
        int i9 = i7 + i8;
        int iJ = this.f2812j.j();
        for (int i10 = 0; i10 < iJ; i10++) {
            c0 c0VarG0 = g0(this.f2812j.i(i10));
            if (c0VarG0 != null && !c0VarG0.J()) {
                int i11 = c0VarG0.f2859c;
                if (i11 >= i9) {
                    c0VarG0.A(-i8, z6);
                    this.f2819m0.f2955g = true;
                } else if (i11 >= i7) {
                    c0VarG0.i(i7 - 1, -i8, z6);
                    this.f2819m0.f2955g = true;
                }
            }
        }
        this.f2806g.w(i7, i8, z6);
        requestLayout();
    }

    public void F0(View view) {
    }

    public boolean G(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        return getScrollingChildHelper().d(i7, i8, iArr, iArr2, i9);
    }

    public void G0(View view) {
    }

    public final void H(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        getScrollingChildHelper().e(i7, i8, i9, i10, iArr, i11, iArr2);
    }

    void H0() {
        this.K++;
    }

    void I(int i7) {
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.e1(i7);
        }
        L0(i7);
        t tVar = this.f2821n0;
        if (tVar != null) {
            tVar.a(this, i7);
        }
        List<t> list = this.f2823o0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f2823o0.get(size).a(this, i7);
            }
        }
    }

    void I0() {
        J0(true);
    }

    void J(int i7, int i8) {
        this.L++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i7, scrollY - i8);
        M0(i7, i8);
        t tVar = this.f2821n0;
        if (tVar != null) {
            tVar.b(this, i7, i8);
        }
        List<t> list = this.f2823o0;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.f2823o0.get(size).b(this, i7, i8);
            }
        }
        this.L--;
    }

    void J0(boolean z6) {
        int i7 = this.K - 1;
        this.K = i7;
        if (i7 < 1) {
            this.K = 0;
            if (z6) {
                B();
                K();
            }
        }
    }

    void K() {
        int i7;
        for (int size = this.A0.size() - 1; size >= 0; size--) {
            c0 c0Var = this.A0.get(size);
            if (c0Var.f2857a.getParent() == this && !c0Var.J() && (i7 = c0Var.f2873q) != -1) {
                i0.u.t0(c0Var.f2857a, i7);
                c0Var.f2873q = -1;
            }
        }
        this.A0.clear();
    }

    public void L0(int i7) {
    }

    void M() {
        if (this.Q != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.M.a(this, 3);
        this.Q = edgeEffectA;
        if (this.f2816l) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void M0(int i7, int i8) {
    }

    void N() {
        if (this.N != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.M.a(this, 0);
        this.N = edgeEffectA;
        if (this.f2816l) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void N0() {
        if (this.f2831s0 || !this.f2838w) {
            return;
        }
        i0.u.c0(this, this.B0);
        this.f2831s0 = true;
    }

    void O() {
        if (this.P != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.M.a(this, 2);
        this.P = edgeEffectA;
        if (this.f2816l) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    void P() {
        if (this.O != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.M.a(this, 1);
        this.O = edgeEffectA;
        if (this.f2816l) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    String Q() {
        return " " + super.toString() + ", adapter:" + this.f2826q + ", layout:" + this.f2828r + ", context:" + getContext();
    }

    void Q0(boolean z6) {
        this.J = z6 | this.J;
        this.I = true;
        z0();
    }

    final void R(z zVar) {
        if (getScrollState() != 2) {
            zVar.f2964p = 0;
            zVar.f2965q = 0;
        } else {
            OverScroller overScroller = this.f2813j0.f2851h;
            zVar.f2964p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.f2965q = overScroller.getFinalY() - overScroller.getCurrY();
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

    void S0(c0 c0Var, l.c cVar) {
        c0Var.F(0, 8192);
        if (this.f2819m0.f2957i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.f2814k.c(d0(c0Var), c0Var);
        }
        this.f2814k.e(c0Var, cVar);
    }

    public c0 T(View view) {
        View viewS = S(view);
        if (viewS == null) {
            return null;
        }
        return f0(viewS);
    }

    void V0() {
        l lVar = this.R;
        if (lVar != null) {
            lVar.k();
        }
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.k1(this.f2806g);
            this.f2828r.l1(this.f2806g);
        }
        this.f2806g.c();
    }

    boolean W0(View view) {
        s1();
        boolean zR = this.f2812j.r(view);
        if (zR) {
            c0 c0VarG0 = g0(view);
            this.f2806g.J(c0VarG0);
            this.f2806g.C(c0VarG0);
        }
        u1(!zR);
        return zR;
    }

    public void X0(n nVar) {
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.f2832t.remove(nVar);
        if (this.f2832t.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        y0();
        requestLayout();
    }

    public c0 Y(int i7) {
        c0 c0Var = null;
        if (this.I) {
            return null;
        }
        int iJ = this.f2812j.j();
        for (int i8 = 0; i8 < iJ; i8++) {
            c0 c0VarG0 = g0(this.f2812j.i(i8));
            if (c0VarG0 != null && !c0VarG0.v() && c0(c0VarG0) == i7) {
                if (!this.f2812j.n(c0VarG0.f2857a)) {
                    return c0VarG0;
                }
                c0Var = c0VarG0;
            }
        }
        return c0Var;
    }

    public void Y0(int i7) {
        int itemDecorationCount = getItemDecorationCount();
        if (i7 >= 0 && i7 < itemDecorationCount) {
            X0(l0(i7));
            return;
        }
        throw new IndexOutOfBoundsException(i7 + " is an invalid index for size " + itemDecorationCount);
    }

    public c0 Z(long j7) {
        g gVar = this.f2826q;
        c0 c0Var = null;
        if (gVar != null && gVar.i()) {
            int iJ = this.f2812j.j();
            for (int i7 = 0; i7 < iJ; i7++) {
                c0 c0VarG0 = g0(this.f2812j.i(i7));
                if (c0VarG0 != null && !c0VarG0.v() && c0VarG0.k() == j7) {
                    if (!this.f2812j.n(c0VarG0.f2857a)) {
                        return c0VarG0;
                    }
                    c0Var = c0VarG0;
                }
            }
        }
        return c0Var;
    }

    public void Z0(s sVar) {
        this.f2834u.remove(sVar);
        if (this.f2836v == sVar) {
            this.f2836v = null;
        }
    }

    void a(int i7, int i8) {
        if (i7 < 0) {
            N();
            if (this.N.isFinished()) {
                this.N.onAbsorb(-i7);
            }
        } else if (i7 > 0) {
            O();
            if (this.P.isFinished()) {
                this.P.onAbsorb(i7);
            }
        }
        if (i8 < 0) {
            P();
            if (this.O.isFinished()) {
                this.O.onAbsorb(-i8);
            }
        } else if (i8 > 0) {
            M();
            if (this.Q.isFinished()) {
                this.Q.onAbsorb(i8);
            }
        }
        if (i7 == 0 && i8 == 0) {
            return;
        }
        i0.u.b0(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.recyclerview.widget.RecyclerView.c0 a0(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.d r0 = r5.f2812j
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.d r3 = r5.f2812j
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$c0 r3 = g0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f2859c
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.d r1 = r5.f2812j
            android.view.View r4 = r3.f2857a
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

    public void a1(t tVar) {
        List<t> list = this.f2823o0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i7, int i8) {
        o oVar = this.f2828r;
        if (oVar == null || !oVar.F0(this, arrayList, i7, i8)) {
            super.addFocusables(arrayList, i7, i8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public boolean b0(int i7, int i8) {
        o oVar = this.f2828r;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.C) {
            return false;
        }
        int iK = oVar.k();
        boolean zL = this.f2828r.l();
        if (iK == 0 || Math.abs(i7) < this.f2804e0) {
            i7 = 0;
        }
        if (!zL || Math.abs(i8) < this.f2804e0) {
            i8 = 0;
        }
        if (i7 == 0 && i8 == 0) {
            return false;
        }
        float f7 = i7;
        float f8 = i8;
        if (!dispatchNestedPreFling(f7, f8)) {
            boolean z6 = iK != 0 || zL;
            dispatchNestedFling(f7, f8, z6);
            r rVar = this.f2802d0;
            if (rVar != null && rVar.a(i7, i8)) {
                return true;
            }
            if (z6) {
                if (zL) {
                    iK = (iK == true ? 1 : 0) | 2;
                }
                t1(iK, 1);
                int i9 = this.f2805f0;
                int iMax = Math.max(-i9, Math.min(i7, i9));
                int i10 = this.f2805f0;
                this.f2813j0.c(iMax, Math.max(-i10, Math.min(i8, i10)));
                return true;
            }
        }
        return false;
    }

    void b1() {
        c0 c0Var;
        int iG = this.f2812j.g();
        for (int i7 = 0; i7 < iG; i7++) {
            View viewF = this.f2812j.f(i7);
            c0 c0VarF0 = f0(viewF);
            if (c0VarF0 != null && (c0Var = c0VarF0.f2865i) != null) {
                View view = c0Var.f2857a;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    int c0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.f2810i.e(c0Var.f2859c);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.f2828r.m((p) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.k()) {
            return this.f2828r.q(this.f2819m0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.k()) {
            return this.f2828r.r(this.f2819m0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.k()) {
            return this.f2828r.s(this.f2819m0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.l()) {
            return this.f2828r.t(this.f2819m0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.l()) {
            return this.f2828r.u(this.f2819m0);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.f2828r;
        if (oVar != null && oVar.l()) {
            return this.f2828r.v(this.f2819m0);
        }
        return 0;
    }

    long d0(c0 c0Var) {
        return this.f2826q.i() ? c0Var.k() : c0Var.f2859c;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return getScrollingChildHelper().a(f7, f8, z6);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f7, float f8) {
        return getScrollingChildHelper().b(f7, f8);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i7, i8, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return getScrollingChildHelper().f(i7, i8, i9, i10, iArr);
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
        boolean z6;
        super.draw(canvas);
        int size = this.f2832t.size();
        boolean z7 = false;
        for (int i7 = 0; i7 < size; i7++) {
            this.f2832t.get(i7).i(canvas, this, this.f2819m0);
        }
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z6 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.f2816l ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.N;
            z6 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.O;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.f2816l) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.O;
            z6 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.P;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.f2816l ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.P;
            z6 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.Q;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.f2816l) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.Q;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z7 = true;
            }
            z6 |= z7;
            canvas.restoreToCount(iSave4);
        }
        if ((z6 || this.R == null || this.f2832t.size() <= 0 || !this.R.p()) ? z6 : true) {
            i0.u.b0(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j7) {
        return super.drawChild(canvas, view, j7);
    }

    public int e0(View view) {
        c0 c0VarG0 = g0(view);
        if (c0VarG0 != null) {
            return c0VarG0.m();
        }
        return -1;
    }

    public c0 f0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return g0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i7) {
        View viewJ0;
        boolean z6;
        View viewQ0 = this.f2828r.Q0(view, i7);
        if (viewQ0 != null) {
            return viewQ0;
        }
        boolean z7 = (this.f2826q == null || this.f2828r == null || v0() || this.C) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z7 && (i7 == 2 || i7 == 1)) {
            if (this.f2828r.l()) {
                int i8 = i7 == 2 ? 130 : 33;
                z6 = focusFinder.findNextFocus(this, view, i8) == null;
                if (I0) {
                    i7 = i8;
                }
            } else {
                z6 = false;
            }
            if (!z6 && this.f2828r.k()) {
                int i9 = (this.f2828r.Z() == 1) ^ (i7 == 2) ? 66 : 17;
                boolean z8 = focusFinder.findNextFocus(this, view, i9) == null;
                if (I0) {
                    i7 = i9;
                }
                z6 = z8;
            }
            if (z6) {
                v();
                if (S(view) == null) {
                    return null;
                }
                s1();
                this.f2828r.J0(view, i7, this.f2806g, this.f2819m0);
                u1(false);
            }
            viewJ0 = focusFinder.findNextFocus(this, view, i7);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i7);
            if (viewFindNextFocus == null && z7) {
                v();
                if (S(view) == null) {
                    return null;
                }
                s1();
                viewJ0 = this.f2828r.J0(view, i7, this.f2806g, this.f2819m0);
                u1(false);
            } else {
                viewJ0 = viewFindNextFocus;
            }
        }
        if (viewJ0 == null || viewJ0.hasFocusable()) {
            return w0(view, viewJ0, i7) ? viewJ0 : super.focusSearch(view, i7);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i7);
        }
        c1(viewJ0, null);
        return view;
    }

    void g1() {
        int iJ = this.f2812j.j();
        for (int i7 = 0; i7 < iJ; i7++) {
            c0 c0VarG0 = g0(this.f2812j.i(i7));
            if (!c0VarG0.J()) {
                c0VarG0.E();
            }
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.f2828r;
        if (oVar != null) {
            return oVar.D();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.f2828r;
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
        return this.f2826q;
    }

    @Override // android.view.View
    public int getBaseline() {
        o oVar = this.f2828r;
        return oVar != null ? oVar.G() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i7, int i8) {
        j jVar = this.f2835u0;
        return jVar == null ? super.getChildDrawingOrder(i7, i8) : jVar.a(i7, i8);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.f2816l;
    }

    public androidx.recyclerview.widget.o getCompatAccessibilityDelegate() {
        return this.f2833t0;
    }

    public k getEdgeEffectFactory() {
        return this.M;
    }

    public l getItemAnimator() {
        return this.R;
    }

    public int getItemDecorationCount() {
        return this.f2832t.size();
    }

    public o getLayoutManager() {
        return this.f2828r;
    }

    public int getMaxFlingVelocity() {
        return this.f2805f0;
    }

    public int getMinFlingVelocity() {
        return this.f2804e0;
    }

    long getNanoTime() {
        if (H0) {
            return System.nanoTime();
        }
        return 0L;
    }

    public r getOnFlingListener() {
        return this.f2802d0;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.f2811i0;
    }

    public u getRecycledViewPool() {
        return this.f2806g.i();
    }

    public int getScrollState() {
        return this.S;
    }

    public void h(n nVar) {
        i(nVar, -1);
    }

    boolean h1(int i7, int i8, MotionEvent motionEvent) {
        int i9;
        int i10;
        int i11;
        int i12;
        v();
        if (this.f2826q != null) {
            int[] iArr = this.f2845z0;
            iArr[0] = 0;
            iArr[1] = 0;
            i1(i7, i8, iArr);
            int[] iArr2 = this.f2845z0;
            int i13 = iArr2[0];
            int i14 = iArr2[1];
            i9 = i14;
            i10 = i13;
            i11 = i7 - i13;
            i12 = i8 - i14;
        } else {
            i9 = 0;
            i10 = 0;
            i11 = 0;
            i12 = 0;
        }
        if (!this.f2832t.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.f2845z0;
        iArr3[0] = 0;
        iArr3[1] = 0;
        H(i10, i9, i11, i12, this.f2841x0, 0, iArr3);
        int[] iArr4 = this.f2845z0;
        int i15 = i11 - iArr4[0];
        int i16 = i12 - iArr4[1];
        boolean z6 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i17 = this.f2799a0;
        int[] iArr5 = this.f2841x0;
        this.f2799a0 = i17 - iArr5[0];
        this.f2800b0 -= iArr5[1];
        int[] iArr6 = this.f2843y0;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !i0.j.a(motionEvent, 8194)) {
                R0(motionEvent.getX(), i15, motionEvent.getY(), i16);
            }
            u(i7, i8);
        }
        if (i10 != 0 || i9 != 0) {
            J(i10, i9);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z6 && i10 == 0 && i9 == 0) ? false : true;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(n nVar, int i7) {
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.g("Cannot add item decoration during a scroll  or layout");
        }
        if (this.f2832t.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i7 < 0) {
            this.f2832t.add(nVar);
        } else {
            this.f2832t.add(i7, nVar);
        }
        y0();
        requestLayout();
    }

    void i1(int i7, int i8, int[] iArr) {
        s1();
        H0();
        d0.c.a("RV Scroll");
        R(this.f2819m0);
        int iW1 = i7 != 0 ? this.f2828r.w1(i7, this.f2806g, this.f2819m0) : 0;
        int iY1 = i8 != 0 ? this.f2828r.y1(i8, this.f2806g, this.f2819m0) : 0;
        d0.c.b();
        b1();
        I0();
        u1(false);
        if (iArr != null) {
            iArr[0] = iW1;
            iArr[1] = iY1;
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.f2838w;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.C;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(q qVar) {
        if (this.H == null) {
            this.H = new ArrayList();
        }
        this.H.add(qVar);
    }

    public void j1(int i7) {
        if (this.C) {
            return;
        }
        w1();
        o oVar = this.f2828r;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.x1(i7);
            awakenScrollBars();
        }
    }

    public void k(s sVar) {
        this.f2834u.add(sVar);
    }

    Rect k0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f2917c) {
            return pVar.f2916b;
        }
        if (this.f2819m0.e() && (pVar.b() || pVar.d())) {
            return pVar.f2916b;
        }
        Rect rect = pVar.f2916b;
        rect.set(0, 0, 0, 0);
        int size = this.f2832t.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f2820n.set(0, 0, 0, 0);
            this.f2832t.get(i7).e(this.f2820n, view, this, this.f2819m0);
            int i8 = rect.left;
            Rect rect2 = this.f2820n;
            rect.left = i8 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        pVar.f2917c = false;
        return rect;
    }

    public void l(t tVar) {
        if (this.f2823o0 == null) {
            this.f2823o0 = new ArrayList();
        }
        this.f2823o0.add(tVar);
    }

    public n l0(int i7) {
        int itemDecorationCount = getItemDecorationCount();
        if (i7 >= 0 && i7 < itemDecorationCount) {
            return this.f2832t.get(i7);
        }
        throw new IndexOutOfBoundsException(i7 + " is an invalid index for size " + itemDecorationCount);
    }

    boolean l1(c0 c0Var, int i7) {
        if (!v0()) {
            i0.u.t0(c0Var.f2857a, i7);
            return true;
        }
        c0Var.f2873q = i7;
        this.A0.add(c0Var);
        return false;
    }

    void m(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.R.a(c0Var, cVar, cVar2)) {
            N0();
        }
    }

    boolean m1(AccessibilityEvent accessibilityEvent) {
        if (!v0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? j0.b.a(accessibilityEvent) : 0;
        this.E |= iA != 0 ? iA : 0;
        return true;
    }

    public boolean n0() {
        return !this.f2844z || this.I || this.f2810i.p();
    }

    public void n1(int i7, int i8) {
        o1(i7, i8, null);
    }

    void o(c0 c0Var, l.c cVar, l.c cVar2) {
        g(c0Var);
        c0Var.G(false);
        if (this.R.c(c0Var, cVar, cVar2)) {
            N0();
        }
    }

    public void o1(int i7, int i8, Interpolator interpolator) {
        p1(i7, i8, interpolator, Integer.MIN_VALUE);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.K = 0;
        this.f2838w = true;
        this.f2844z = this.f2844z && !isLayoutRequested();
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.z(this);
        }
        this.f2831s0 = false;
        if (H0) {
            ThreadLocal<androidx.recyclerview.widget.h> threadLocal = androidx.recyclerview.widget.h.f3131j;
            androidx.recyclerview.widget.h hVar = threadLocal.get();
            this.f2815k0 = hVar;
            if (hVar == null) {
                this.f2815k0 = new androidx.recyclerview.widget.h();
                Display displayS = i0.u.s(this);
                float f7 = 60.0f;
                if (!isInEditMode() && displayS != null) {
                    float refreshRate = displayS.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f7 = refreshRate;
                    }
                }
                androidx.recyclerview.widget.h hVar2 = this.f2815k0;
                hVar2.f3135h = (long) (1.0E9f / f7);
                threadLocal.set(hVar2);
            }
            this.f2815k0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        androidx.recyclerview.widget.h hVar;
        super.onDetachedFromWindow();
        l lVar = this.R;
        if (lVar != null) {
            lVar.k();
        }
        w1();
        this.f2838w = false;
        o oVar = this.f2828r;
        if (oVar != null) {
            oVar.A(this, this.f2806g);
        }
        this.A0.clear();
        removeCallbacks(this.B0);
        this.f2814k.j();
        if (!H0 || (hVar = this.f2815k0) == null) {
            return;
        }
        hVar.j(this);
        this.f2815k0 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.f2832t.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f2832t.get(i7).g(canvas, this, this.f2819m0);
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.f2828r
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.f2828r
            boolean r0 = r0.l()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f2828r
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
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f2828r
            boolean r3 = r3.l()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.f2828r
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
            float r2 = r5.f2807g0
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.f2809h0
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.h1(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        if (this.C) {
            return false;
        }
        this.f2836v = null;
        if (U(motionEvent)) {
            r();
            return true;
        }
        o oVar = this.f2828r;
        if (oVar == null) {
            return false;
        }
        boolean zK = oVar.k();
        boolean zL = this.f2828r.l();
        if (this.U == null) {
            this.U = VelocityTracker.obtain();
        }
        this.U.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.D) {
                this.D = false;
            }
            this.T = motionEvent.getPointerId(0);
            int x6 = (int) (motionEvent.getX() + 0.5f);
            this.f2799a0 = x6;
            this.V = x6;
            int y6 = (int) (motionEvent.getY() + 0.5f);
            this.f2800b0 = y6;
            this.W = y6;
            if (this.S == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                v1(1);
            }
            int[] iArr = this.f2843y0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i7 = zK;
            if (zL) {
                i7 = (zK ? 1 : 0) | 2;
            }
            t1(i7, 0);
        } else if (actionMasked == 1) {
            this.U.clear();
            v1(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.T);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.T + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x7 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y7 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.S != 1) {
                int i8 = x7 - this.V;
                int i9 = y7 - this.W;
                if (!zK || Math.abs(i8) <= this.f2801c0) {
                    z6 = false;
                } else {
                    this.f2799a0 = x7;
                    z6 = true;
                }
                if (zL && Math.abs(i9) > this.f2801c0) {
                    this.f2800b0 = y7;
                    z6 = true;
                }
                if (z6) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            r();
        } else if (actionMasked == 5) {
            this.T = motionEvent.getPointerId(actionIndex);
            int x8 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.f2799a0 = x8;
            this.V = x8;
            int y8 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.f2800b0 = y8;
            this.W = y8;
        } else if (actionMasked == 6) {
            K0(motionEvent);
        }
        return this.S == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        d0.c.a("RV OnLayout");
        C();
        d0.c.b();
        this.f2844z = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        o oVar = this.f2828r;
        if (oVar == null) {
            x(i7, i8);
            return;
        }
        boolean z6 = false;
        if (oVar.s0()) {
            int mode = View.MeasureSpec.getMode(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            this.f2828r.Z0(this.f2806g, this.f2819m0, i7, i8);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z6 = true;
            }
            if (z6 || this.f2826q == null) {
                return;
            }
            if (this.f2819m0.f2953e == 1) {
                D();
            }
            this.f2828r.A1(i7, i8);
            this.f2819m0.f2958j = true;
            E();
            this.f2828r.D1(i7, i8);
            if (this.f2828r.G1()) {
                this.f2828r.A1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.f2819m0.f2958j = true;
                E();
                this.f2828r.D1(i7, i8);
                return;
            }
            return;
        }
        if (this.f2840x) {
            this.f2828r.Z0(this.f2806g, this.f2819m0, i7, i8);
            return;
        }
        if (this.F) {
            s1();
            H0();
            P0();
            I0();
            z zVar = this.f2819m0;
            if (zVar.f2960l) {
                zVar.f2956h = true;
            } else {
                this.f2810i.j();
                this.f2819m0.f2956h = false;
            }
            this.F = false;
            u1(false);
        } else if (this.f2819m0.f2960l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.f2826q;
        if (gVar != null) {
            this.f2819m0.f2954f = gVar.e();
        } else {
            this.f2819m0.f2954f = 0;
        }
        s1();
        this.f2828r.Z0(this.f2806g, this.f2819m0, i7, i8);
        u1(false);
        this.f2819m0.f2956h = false;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (v0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i7, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.f2808h = savedState;
        super.onRestoreInstanceState(savedState.b());
        o oVar = this.f2828r;
        if (oVar == null || (parcelable2 = this.f2808h.f2846h) == null) {
            return;
        }
        oVar.c1(parcelable2);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.f2808h;
        if (savedState2 != null) {
            savedState.c(savedState2);
        } else {
            o oVar = this.f2828r;
            if (oVar != null) {
                savedState.f2846h = oVar.d1();
            } else {
                savedState.f2846h = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 == i9 && i8 == i10) {
            return;
        }
        t0();
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

    void p(String str) {
        if (v0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + Q());
        }
        if (this.L > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + Q()));
        }
    }

    void p0() {
        this.f2810i = new androidx.recyclerview.widget.a(new f());
    }

    public void p1(int i7, int i8, Interpolator interpolator, int i9) {
        q1(i7, i8, interpolator, i9, false);
    }

    boolean q(c0 c0Var) {
        l lVar = this.R;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    void q1(int i7, int i8, Interpolator interpolator, int i9, boolean z6) {
        o oVar = this.f2828r;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        if (!oVar.k()) {
            i7 = 0;
        }
        if (!this.f2828r.l()) {
            i8 = 0;
        }
        if (i7 == 0 && i8 == 0) {
            return;
        }
        if (!(i9 == Integer.MIN_VALUE || i9 > 0)) {
            scrollBy(i7, i8);
            return;
        }
        if (z6) {
            int i10 = i7 != 0 ? 1 : 0;
            if (i8 != 0) {
                i10 |= 2;
            }
            t1(i10, 1);
        }
        this.f2813j0.f(i7, i8, i9, interpolator);
    }

    public void r1(int i7) {
        if (this.C) {
            return;
        }
        o oVar = this.f2828r;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            oVar.I1(this, this.f2819m0, i7);
        }
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z6) {
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
        super.removeDetachedView(view, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.f2828r.b1(this, this.f2819m0, view, view2) && view2 != null) {
            c1(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        return this.f2828r.r1(this, view, rect, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z6) {
        int size = this.f2834u.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f2834u.get(i7).c(z6);
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.A != 0 || this.C) {
            this.B = true;
        } else {
            super.requestLayout();
        }
    }

    void s0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new androidx.recyclerview.widget.g(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(x0.b.fastscroll_default_thickness), resources.getDimensionPixelSize(x0.b.fastscroll_minimum_range), resources.getDimensionPixelOffset(x0.b.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
        }
    }

    void s1() {
        int i7 = this.A + 1;
        this.A = i7;
        if (i7 != 1 || this.C) {
            return;
        }
        this.B = false;
    }

    @Override // android.view.View
    public void scrollBy(int i7, int i8) {
        o oVar = this.f2828r;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.C) {
            return;
        }
        boolean zK = oVar.k();
        boolean zL = this.f2828r.l();
        if (zK || zL) {
            if (!zK) {
                i7 = 0;
            }
            if (!zL) {
                i8 = 0;
            }
            h1(i7, i8, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i7, int i8) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (m1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(androidx.recyclerview.widget.o oVar) {
        this.f2833t0 = oVar;
        i0.u.k0(this, oVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        k1(gVar, false, true);
        Q0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.f2835u0) {
            return;
        }
        this.f2835u0 = jVar;
        setChildrenDrawingOrderEnabled(jVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z6) {
        if (z6 != this.f2816l) {
            t0();
        }
        this.f2816l = z6;
        super.setClipToPadding(z6);
        if (this.f2844z) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        h0.h.f(kVar);
        this.M = kVar;
        t0();
    }

    public void setHasFixedSize(boolean z6) {
        this.f2840x = z6;
    }

    public void setItemAnimator(l lVar) {
        l lVar2 = this.R;
        if (lVar2 != null) {
            lVar2.k();
            this.R.v(null);
        }
        this.R = lVar;
        if (lVar != null) {
            lVar.v(this.f2829r0);
        }
    }

    public void setItemViewCacheSize(int i7) {
        this.f2806g.G(i7);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z6) {
        suppressLayout(z6);
    }

    public void setLayoutManager(o oVar) {
        if (oVar == this.f2828r) {
            return;
        }
        w1();
        if (this.f2828r != null) {
            l lVar = this.R;
            if (lVar != null) {
                lVar.k();
            }
            this.f2828r.k1(this.f2806g);
            this.f2828r.l1(this.f2806g);
            this.f2806g.c();
            if (this.f2838w) {
                this.f2828r.A(this, this.f2806g);
            }
            this.f2828r.E1(null);
            this.f2828r = null;
        } else {
            this.f2806g.c();
        }
        this.f2812j.o();
        this.f2828r = oVar;
        if (oVar != null) {
            if (oVar.f2892b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f2892b.Q());
            }
            oVar.E1(this);
            if (this.f2838w) {
                this.f2828r.z(this);
            }
        }
        this.f2806g.K();
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
        getScrollingChildHelper().m(z6);
    }

    public void setOnFlingListener(r rVar) {
        this.f2802d0 = rVar;
    }

    @Deprecated
    public void setOnScrollListener(t tVar) {
        this.f2821n0 = tVar;
    }

    public void setPreserveFocusAfterLayout(boolean z6) {
        this.f2811i0 = z6;
    }

    public void setRecycledViewPool(u uVar) {
        this.f2806g.E(uVar);
    }

    public void setRecyclerListener(w wVar) {
        this.f2830s = wVar;
    }

    void setScrollState(int i7) {
        if (i7 == this.S) {
            return;
        }
        this.S = i7;
        if (i7 != 2) {
            x1();
        }
        I(i7);
    }

    public void setScrollingTouchSlop(int i7) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i7 != 0) {
            if (i7 == 1) {
                this.f2801c0 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i7 + "; using default value");
        }
        this.f2801c0 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(a0 a0Var) {
        this.f2806g.F(a0Var);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i7) {
        return getScrollingChildHelper().o(i7);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z6) {
        if (z6 != this.C) {
            p("Do not suppressLayout in layout or scroll");
            if (z6) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.C = true;
                this.D = true;
                w1();
                return;
            }
            this.C = false;
            if (this.B && this.f2828r != null && this.f2826q != null) {
                requestLayout();
            }
            this.B = false;
        }
    }

    void t() {
        int iJ = this.f2812j.j();
        for (int i7 = 0; i7 < iJ; i7++) {
            c0 c0VarG0 = g0(this.f2812j.i(i7));
            if (!c0VarG0.J()) {
                c0VarG0.c();
            }
        }
        this.f2806g.d();
    }

    void t0() {
        this.Q = null;
        this.O = null;
        this.P = null;
        this.N = null;
    }

    public boolean t1(int i7, int i8) {
        return getScrollingChildHelper().p(i7, i8);
    }

    void u(int i7, int i8) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.N;
        if (edgeEffect == null || edgeEffect.isFinished() || i7 <= 0) {
            zIsFinished = false;
        } else {
            this.N.onRelease();
            zIsFinished = this.N.isFinished();
        }
        EdgeEffect edgeEffect2 = this.P;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i7 < 0) {
            this.P.onRelease();
            zIsFinished |= this.P.isFinished();
        }
        EdgeEffect edgeEffect3 = this.O;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i8 > 0) {
            this.O.onRelease();
            zIsFinished |= this.O.isFinished();
        }
        EdgeEffect edgeEffect4 = this.Q;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i8 < 0) {
            this.Q.onRelease();
            zIsFinished |= this.Q.isFinished();
        }
        if (zIsFinished) {
            i0.u.b0(this);
        }
    }

    boolean u0() {
        AccessibilityManager accessibilityManager = this.G;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    void u1(boolean z6) {
        if (this.A < 1) {
            this.A = 1;
        }
        if (!z6 && !this.C) {
            this.B = false;
        }
        if (this.A == 1) {
            if (z6 && this.B && !this.C && this.f2828r != null && this.f2826q != null) {
                C();
            }
            if (!this.C) {
                this.B = false;
            }
        }
        this.A--;
    }

    void v() {
        if (!this.f2844z || this.I) {
            d0.c.a("RV FullInvalidate");
            C();
            d0.c.b();
            return;
        }
        if (this.f2810i.p()) {
            if (!this.f2810i.o(4) || this.f2810i.o(11)) {
                if (this.f2810i.p()) {
                    d0.c.a("RV FullInvalidate");
                    C();
                    d0.c.b();
                    return;
                }
                return;
            }
            d0.c.a("RV PartialInvalidate");
            s1();
            H0();
            this.f2810i.w();
            if (!this.B) {
                if (o0()) {
                    C();
                } else {
                    this.f2810i.i();
                }
            }
            u1(true);
            I0();
            d0.c.b();
        }
    }

    public boolean v0() {
        return this.K > 0;
    }

    public void v1(int i7) {
        getScrollingChildHelper().r(i7);
    }

    public void w1() {
        setScrollState(0);
        x1();
    }

    void x(int i7, int i8) {
        setMeasuredDimension(o.n(i7, getPaddingLeft() + getPaddingRight(), i0.u.A(this)), o.n(i8, getPaddingTop() + getPaddingBottom(), i0.u.z(this)));
    }

    void x0(int i7) {
        if (this.f2828r == null) {
            return;
        }
        setScrollState(2);
        this.f2828r.x1(i7);
        awakenScrollBars();
    }

    void y0() {
        int iJ = this.f2812j.j();
        for (int i7 = 0; i7 < iJ; i7++) {
            ((p) this.f2812j.i(i7).getLayoutParams()).f2917c = true;
        }
        this.f2806g.s();
    }

    void y1(int i7, int i8, Object obj) {
        int i9;
        int iJ = this.f2812j.j();
        int i10 = i7 + i8;
        for (int i11 = 0; i11 < iJ; i11++) {
            View viewI = this.f2812j.i(i11);
            c0 c0VarG0 = g0(viewI);
            if (c0VarG0 != null && !c0VarG0.J() && (i9 = c0VarG0.f2859c) >= i7 && i9 < i10) {
                c0VarG0.b(2);
                c0VarG0.a(obj);
                ((p) viewI.getLayoutParams()).f2917c = true;
            }
        }
        this.f2806g.M(i7, i8);
    }

    void z(View view) {
        c0 c0VarG0 = g0(view);
        F0(view);
        g gVar = this.f2826q;
        if (gVar != null && c0VarG0 != null) {
            gVar.v(c0VarG0);
        }
        List<q> list = this.H;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.H.get(size).a(view);
            }
        }
    }

    void z0() {
        int iJ = this.f2812j.j();
        for (int i7 = 0; i7 < iJ; i7++) {
            c0 c0VarG0 = g0(this.f2812j.i(i7));
            if (c0VarG0 != null && !c0VarG0.J()) {
                c0VarG0.b(6);
            }
        }
        y0();
        this.f2806g.t();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, x0.a.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f2803e = new x();
        this.f2806g = new v();
        this.f2814k = new androidx.recyclerview.widget.t();
        this.f2818m = new a();
        this.f2820n = new Rect();
        this.f2822o = new Rect();
        this.f2824p = new RectF();
        this.f2832t = new ArrayList<>();
        this.f2834u = new ArrayList<>();
        this.A = 0;
        this.I = false;
        this.J = false;
        this.K = 0;
        this.L = 0;
        this.M = new k();
        this.R = new androidx.recyclerview.widget.e();
        this.S = 0;
        this.T = -1;
        this.f2807g0 = Float.MIN_VALUE;
        this.f2809h0 = Float.MIN_VALUE;
        this.f2811i0 = true;
        this.f2813j0 = new b0();
        this.f2817l0 = H0 ? new h.b() : null;
        this.f2819m0 = new z();
        this.f2825p0 = false;
        this.f2827q0 = false;
        this.f2829r0 = new m();
        this.f2831s0 = false;
        this.f2837v0 = new int[2];
        this.f2841x0 = new int[2];
        this.f2843y0 = new int[2];
        this.f2845z0 = new int[2];
        this.A0 = new ArrayList();
        this.B0 = new b();
        this.C0 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f2801c0 = viewConfiguration.getScaledTouchSlop();
        this.f2807g0 = i0.v.a(viewConfiguration, context);
        this.f2809h0 = i0.v.b(viewConfiguration, context);
        this.f2804e0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f2805f0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.R.v(this.f2829r0);
        p0();
        r0();
        q0();
        if (i0.u.w(this) == 0) {
            i0.u.t0(this, 1);
        }
        this.G = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new androidx.recyclerview.widget.o(this));
        int[] iArr = x0.c.RecyclerView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, 0);
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i7, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(x0.c.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(x0.c.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.f2816l = typedArrayObtainStyledAttributes.getBoolean(x0.c.RecyclerView_android_clipToPadding, true);
        boolean z6 = typedArrayObtainStyledAttributes.getBoolean(x0.c.RecyclerView_fastScrollEnabled, false);
        this.f2842y = z6;
        if (z6) {
            s0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(x0.c.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(x0.c.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(x0.c.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(x0.c.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArrayObtainStyledAttributes.recycle();
        w(context, string, attributeSet, i7, 0);
        int[] iArr2 = D0;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i7, 0);
        if (i8 >= 29) {
            saveAttributeDataForStyleable(context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i7, 0);
        }
        boolean z7 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z7);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Parcelable f2846h;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2846h = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        void c(SavedState savedState) {
            this.f2846h = savedState.f2846h;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeParcelable(this.f2846h, 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        c0 f2915a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Rect f2916b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2917c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2918d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2916b = new Rect();
            this.f2917c = true;
            this.f2918d = false;
        }

        public int a() {
            return this.f2915a.m();
        }

        public boolean b() {
            return this.f2915a.y();
        }

        public boolean c() {
            return this.f2915a.v();
        }

        public boolean d() {
            return this.f2915a.t();
        }

        public p(int i7, int i8) {
            super(i7, i8);
            this.f2916b = new Rect();
            this.f2917c = true;
            this.f2918d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2916b = new Rect();
            this.f2917c = true;
            this.f2918d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2916b = new Rect();
            this.f2917c = true;
            this.f2918d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f2916b = new Rect();
            this.f2917c = true;
            this.f2918d = false;
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.f2828r;
        if (oVar != null) {
            return oVar.F(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }
}
