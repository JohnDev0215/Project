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
import b.h.m.c0.c;
import b.r.d.a;
import b.r.d.b;
import b.r.d.e;
import b.r.d.l;
import b.r.d.p;
import b.r.d.q;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements b.h.m.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1166b = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final boolean f1167c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1168d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f1169e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f1170f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f1171g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f1172h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Class<?>[] f1173i;
    public static final Interpolator j;
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
    public int a0;
    public int b0;
    public int c0;
    public int d0;
    public int e0;
    public r f0;
    public final int g0;
    public final int h0;
    public float i0;
    public float j0;
    public final x k;
    public boolean k0;
    public final v l;
    public final b0 l0;
    public SavedState m;
    public b.r.d.e m0;
    public b.r.d.a n;
    public e.b n0;
    public b.r.d.b o;
    public final z o0;
    public final b.r.d.q p;
    public t p0;
    public boolean q;
    public List<t> q0;
    public final Runnable r;
    public boolean r0;
    public final Rect s;
    public boolean s0;
    public final Rect t;
    public l.b t0;
    public final RectF u;
    public boolean u0;
    public g v;
    public b.r.d.l v0;
    public o w;
    public j w0;
    public w x;
    public final int[] x0;
    public final ArrayList<n> y;
    public b.h.m.k y0;
    public final ArrayList<s> z;
    public final int[] z0;

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
            RecyclerView.this.u0 = false;
        }
    }

    public class b0 implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1177b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1178c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public OverScroller f1179d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Interpolator f1180e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1181f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1182g;

        public b0() {
            Interpolator interpolator = RecyclerView.j;
            this.f1180e = interpolator;
            this.f1181f = false;
            this.f1182g = false;
            this.f1179d = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        public final int a(int i2, int i3, int i4, int i5) {
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

        public final float b(float f2) {
            return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
        }

        public void c(int i2, int i3) {
            RecyclerView.this.setScrollState(2);
            this.f1178c = 0;
            this.f1177b = 0;
            Interpolator interpolator = this.f1180e;
            Interpolator interpolator2 = RecyclerView.j;
            if (interpolator != interpolator2) {
                this.f1180e = interpolator2;
                this.f1179d = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.f1179d.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        public final void d() {
            RecyclerView.this.removeCallbacks(this);
            b.h.m.t.c0(RecyclerView.this, this);
        }

        public void e() {
            if (this.f1181f) {
                this.f1182g = true;
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
            if (this.f1180e != interpolator) {
                this.f1180e = interpolator;
                this.f1179d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f1178c = 0;
            this.f1177b = 0;
            RecyclerView.this.setScrollState(2);
            this.f1179d.startScroll(0, 0, i2, i3, i5);
            if (Build.VERSION.SDK_INT < 23) {
                this.f1179d.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.f1179d.abortAnimation();
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
            this.f1182g = false;
            this.f1181f = true;
            recyclerView.v();
            OverScroller overScroller = this.f1179d;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.f1177b;
                int i5 = currY - this.f1178c;
                this.f1177b = currX;
                this.f1178c = currY;
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
                    recyclerView3.h1(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.B0;
                    i3 = iArr4[0];
                    i2 = iArr4[1];
                    i4 -= i3;
                    i5 -= i2;
                    y yVar = recyclerView4.w.f1215g;
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
                y yVar2 = RecyclerView.this.w.f1215g;
                if ((yVar2 != null && yVar2.g()) || !z) {
                    e();
                    RecyclerView recyclerView7 = RecyclerView.this;
                    b.r.d.e eVar = recyclerView7.m0;
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
                    if (RecyclerView.f1170f) {
                        RecyclerView.this.n0.b();
                    }
                }
            }
            y yVar3 = RecyclerView.this.w.f1215g;
            if (yVar3 != null && yVar3.g()) {
                yVar3.j(0, 0);
            }
            this.f1181f = false;
            if (this.f1182g) {
                d();
            } else {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.u1(1);
            }
        }
    }

    public static class c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public static abstract class c0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final List<Object> f1184a = Collections.emptyList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f1185b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<RecyclerView> f1186c;
        public int k;
        public RecyclerView s;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1187d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1188e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1189f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1190g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1191h = -1;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public c0 f1192i = null;
        public c0 j = null;
        public List<Object> l = null;
        public List<Object> m = null;
        public int n = 0;
        public v o = null;
        public boolean p = false;
        public int q = 0;
        public int r = -1;

        public c0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.f1185b = view;
        }

        public void A(int i2, boolean z) {
            if (this.f1188e == -1) {
                this.f1188e = this.f1187d;
            }
            if (this.f1191h == -1) {
                this.f1191h = this.f1187d;
            }
            if (z) {
                this.f1191h += i2;
            }
            this.f1187d += i2;
            if (this.f1185b.getLayoutParams() != null) {
                ((p) this.f1185b.getLayoutParams()).f1226c = true;
            }
        }

        public void B(RecyclerView recyclerView) {
            int i2 = this.r;
            if (i2 != -1) {
                this.q = i2;
            } else {
                this.q = b.h.m.t.x(this.f1185b);
            }
            recyclerView.k1(this, 4);
        }

        public void C(RecyclerView recyclerView) {
            recyclerView.k1(this, this.q);
            this.q = 0;
        }

        public void D() {
            this.k = 0;
            this.f1187d = -1;
            this.f1188e = -1;
            this.f1189f = -1L;
            this.f1191h = -1;
            this.n = 0;
            this.f1192i = null;
            this.j = null;
            d();
            this.q = 0;
            this.r = -1;
            RecyclerView.s(this);
        }

        public void E() {
            if (this.f1188e == -1) {
                this.f1188e = this.f1187d;
            }
        }

        public void F(int i2, int i3) {
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

        public void H(v vVar, boolean z) {
            this.o = vVar;
            this.p = z;
        }

        public boolean I() {
            return (this.k & 16) != 0;
        }

        public boolean J() {
            return (this.k & 128) != 0;
        }

        public void K() {
            this.o.J(this);
        }

        public boolean L() {
            return (this.k & 32) != 0;
        }

        public void a(Object obj) {
            if (obj == null) {
                b(1024);
            } else if ((1024 & this.k) == 0) {
                g();
                this.l.add(obj);
            }
        }

        public void b(int i2) {
            this.k = i2 | this.k;
        }

        public void c() {
            this.f1188e = -1;
            this.f1191h = -1;
        }

        public void d() {
            List<Object> list = this.l;
            if (list != null) {
                list.clear();
            }
            this.k &= -1025;
        }

        public void e() {
            this.k &= -33;
        }

        public void f() {
            this.k &= -257;
        }

        public final void g() {
            if (this.l == null) {
                ArrayList arrayList = new ArrayList();
                this.l = arrayList;
                this.m = Collections.unmodifiableList(arrayList);
            }
        }

        public boolean h() {
            return (this.k & 16) == 0 && b.h.m.t.M(this.f1185b);
        }

        public void i(int i2, int i3, boolean z) {
            b(8);
            A(i3, z);
            this.f1187d = i2;
        }

        public final int j() {
            RecyclerView recyclerView = this.s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.c0(this);
        }

        public final long k() {
            return this.f1189f;
        }

        public final int l() {
            return this.f1190g;
        }

        public final int m() {
            int i2 = this.f1191h;
            return i2 == -1 ? this.f1187d : i2;
        }

        public final int n() {
            return this.f1188e;
        }

        public List<Object> o() {
            if ((this.k & 1024) != 0) {
                return f1184a;
            }
            List<Object> list = this.l;
            return (list == null || list.size() == 0) ? f1184a : this.m;
        }

        public boolean p(int i2) {
            return (i2 & this.k) != 0;
        }

        public boolean q() {
            return (this.k & 512) != 0 || t();
        }

        public boolean r() {
            return (this.f1185b.getParent() == null || this.f1185b.getParent() == this.s) ? false : true;
        }

        public boolean s() {
            return (this.k & 1) != 0;
        }

        public boolean t() {
            return (this.k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f1187d + " id=" + this.f1189f + ", oldPos=" + this.f1188e + ", pLpos:" + this.f1191h);
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
            if (this.f1185b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.k & 16) == 0 && !b.h.m.t.M(this.f1185b);
        }

        public boolean v() {
            return (this.k & 8) != 0;
        }

        public boolean w() {
            return this.o != null;
        }

        public boolean x() {
            return (this.k & 256) != 0;
        }

        public boolean y() {
            return (this.k & 2) != 0;
        }

        public boolean z() {
            return (this.k & 2) != 0;
        }
    }

    public class d implements q.b {
        public d() {
        }

        @Override // b.r.d.q.b
        public void a(c0 c0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.w.m1(c0Var.f1185b, recyclerView.l);
        }

        @Override // b.r.d.q.b
        public void b(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.m(c0Var, cVar, cVar2);
        }

        @Override // b.r.d.q.b
        public void c(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.l.J(c0Var);
            RecyclerView.this.o(c0Var, cVar, cVar2);
        }

        @Override // b.r.d.q.b
        public void d(c0 c0Var, l.c cVar, l.c cVar2) {
            c0Var.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.N) {
                if (recyclerView.W.b(c0Var, c0Var, cVar, cVar2)) {
                    RecyclerView.this.N0();
                }
            } else if (recyclerView.W.d(c0Var, cVar, cVar2)) {
                RecyclerView.this.N0();
            }
        }
    }

    public class e implements b.InterfaceC0057b {
        public e() {
        }

        @Override // b.r.d.b.InterfaceC0057b
        public View a(int i2) {
            return RecyclerView.this.getChildAt(i2);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void b(View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0 != null) {
                c0VarH0.B(RecyclerView.this);
            }
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void c(int i2) {
            View childAt = RecyclerView.this.getChildAt(i2);
            if (childAt != null) {
                RecyclerView.this.A(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i2);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void d() {
            int iK = k();
            for (int i2 = 0; i2 < iK; i2++) {
                View viewA = a(i2);
                RecyclerView.this.A(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // b.r.d.b.InterfaceC0057b
        public c0 e(View view) {
            return RecyclerView.h0(view);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void f(int i2) {
            c0 c0VarH0;
            View viewA = a(i2);
            if (viewA != null && (c0VarH0 = RecyclerView.h0(viewA)) != null) {
                if (c0VarH0.x() && !c0VarH0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarH0 + RecyclerView.this.Q());
                }
                c0VarH0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i2);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void g(View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0 != null) {
                c0VarH0.C(RecyclerView.this);
            }
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void h(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0 != null) {
                if (!c0VarH0.x() && !c0VarH0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarH0 + RecyclerView.this.Q());
                }
                c0VarH0.f();
            }
            RecyclerView.this.attachViewToParent(view, i2, layoutParams);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public void i(View view, int i2) {
            RecyclerView.this.addView(view, i2);
            RecyclerView.this.z(view);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // b.r.d.b.InterfaceC0057b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    public class f implements a.InterfaceC0056a {
        public f() {
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void a(int i2, int i3) {
            RecyclerView.this.D0(i2, i3);
            RecyclerView.this.r0 = true;
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // b.r.d.a.InterfaceC0056a
        public c0 c(int i2) {
            c0 c0VarA0 = RecyclerView.this.a0(i2, true);
            if (c0VarA0 == null || RecyclerView.this.o.n(c0VarA0.f1185b)) {
                return null;
            }
            return c0VarA0;
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void d(int i2, int i3) {
            RecyclerView.this.E0(i2, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.r0 = true;
            recyclerView.o0.f1262d += i3;
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void e(int i2, int i3) {
            RecyclerView.this.E0(i2, i3, false);
            RecyclerView.this.r0 = true;
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void g(int i2, int i3) {
            RecyclerView.this.C0(i2, i3);
            RecyclerView.this.r0 = true;
        }

        @Override // b.r.d.a.InterfaceC0056a
        public void h(int i2, int i3, Object obj) {
            RecyclerView.this.x1(i2, i3, obj);
            RecyclerView.this.s0 = true;
        }

        public void i(a.b bVar) {
            int i2 = bVar.f3023a;
            if (i2 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.w.R0(recyclerView, bVar.f3024b, bVar.f3026d);
                return;
            }
            if (i2 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.w.U0(recyclerView2, bVar.f3024b, bVar.f3026d);
            } else if (i2 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.w.W0(recyclerView3, bVar.f3024b, bVar.f3026d, bVar.f3025c);
            } else {
                if (i2 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.w.T0(recyclerView4, bVar.f3024b, bVar.f3026d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h f1196a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1197b = false;

        public final void a(VH vh, int i2) {
            vh.f1187d = i2;
            if (g()) {
                vh.f1189f = d(i2);
            }
            vh.F(1, 519);
            b.h.i.b.a("RV OnBindView");
            m(vh, i2, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f1185b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f1226c = true;
            }
            b.h.i.b.b();
        }

        public final VH b(ViewGroup viewGroup, int i2) {
            try {
                b.h.i.b.a("RV CreateView");
                VH vh = (VH) n(viewGroup, i2);
                if (vh.f1185b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f1190g = i2;
                return vh;
            } finally {
                b.h.i.b.b();
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
            return this.f1196a.a();
        }

        public final boolean g() {
            return this.f1197b;
        }

        public final void h() {
            this.f1196a.b();
        }

        public final void i(int i2) {
            this.f1196a.c(i2, 1);
        }

        public final void j(int i2) {
            this.f1196a.e(i2, 1);
        }

        public void k(RecyclerView recyclerView) {
        }

        public abstract void l(VH vh, int i2);

        public void m(VH vh, int i2, List<Object> list) {
            l(vh, i2);
        }

        public abstract VH n(ViewGroup viewGroup, int i2);

        public void o(RecyclerView recyclerView) {
        }

        public boolean p(VH vh) {
            return false;
        }

        public void q(VH vh) {
        }

        public void r(VH vh) {
        }

        public void s(VH vh) {
        }

        public void t(i iVar) {
            this.f1196a.registerObserver(iVar);
        }

        public void u(boolean z) {
            if (f()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.f1197b = z;
        }

        public void v(i iVar) {
            this.f1196a.unregisterObserver(iVar);
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

        public void c(int i2, int i3) {
            d(i2, i3, null);
        }

        public void d(int i2, int i3, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).c(i2, i3, obj);
            }
        }

        public void e(int i2, int i3) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((i) ((Observable) this).mObservers.get(size)).d(i2, i3);
            }
        }
    }

    public static abstract class i {
        public void a() {
        }

        public void b(int i2, int i3) {
        }

        public void c(int i2, int i3, Object obj) {
            b(i2, i3);
        }

        public void d(int i2, int i3) {
        }
    }

    public interface j {
        int a(int i2, int i3);
    }

    public static class k {
        public EdgeEffect a(RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b f1198a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f1199b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1200c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1201d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1202e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1203f = 250;

        public interface a {
            void a();
        }

        public interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1204a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1205b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1206c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1207d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i2) {
                View view = c0Var.f1185b;
                this.f1204a = view.getLeft();
                this.f1205b = view.getTop();
                this.f1206c = view.getRight();
                this.f1207d = view.getBottom();
                return this;
            }
        }

        public static int e(c0 c0Var) {
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
            b bVar = this.f1198a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f1199b.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1199b.get(i2).a();
            }
            this.f1199b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f1200c;
        }

        public long m() {
            return this.f1203f;
        }

        public long n() {
            return this.f1202e;
        }

        public long o() {
            return this.f1201d;
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

        public void v(b bVar) {
            this.f1198a = bVar;
        }
    }

    public class m implements l.b {
        public m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.f1192i != null && c0Var.j == null) {
                c0Var.f1192i = null;
            }
            c0Var.j = null;
            if (c0Var.I() || RecyclerView.this.W0(c0Var.f1185b) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f1185b, false);
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.r.d.b f1209a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1210b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final p.b f1211c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final p.b f1212d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.r.d.p f1213e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.r.d.p f1214f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public y f1215g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1216h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1217i;
        public boolean j;
        public boolean k;
        public boolean l;
        public int m;
        public boolean n;
        public int o;
        public int p;
        public int q;
        public int r;

        public class a implements p.b {
            public a() {
            }

            @Override // b.r.d.p.b
            public View a(int i2) {
                return o.this.I(i2);
            }

            @Override // b.r.d.p.b
            public int b() {
                return o.this.o0() - o.this.f0();
            }

            @Override // b.r.d.p.b
            public int c() {
                return o.this.e0();
            }

            @Override // b.r.d.p.b
            public int d(View view) {
                return o.this.T(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // b.r.d.p.b
            public int e(View view) {
                return o.this.Q(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        public class b implements p.b {
            public b() {
            }

            @Override // b.r.d.p.b
            public View a(int i2) {
                return o.this.I(i2);
            }

            @Override // b.r.d.p.b
            public int b() {
                return o.this.W() - o.this.d0();
            }

            @Override // b.r.d.p.b
            public int c() {
                return o.this.g0();
            }

            @Override // b.r.d.p.b
            public int d(View view) {
                return o.this.O(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // b.r.d.p.b
            public int e(View view) {
                return o.this.U(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i2, int i3);
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1220a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1221b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f1222c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1223d;
        }

        public o() {
            a aVar = new a();
            this.f1211c = aVar;
            b bVar = new b();
            this.f1212d = bVar;
            this.f1213e = new b.r.d.p(aVar);
            this.f1214f = new b.r.d.p(bVar);
            this.f1216h = false;
            this.f1217i = false;
            this.j = false;
            this.k = true;
            this.l = true;
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

        public static d i0(Context context, AttributeSet attributeSet, int i2, int i3) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.r.c.RecyclerView, i2, i3);
            dVar.f1220a = typedArrayObtainStyledAttributes.getInt(b.r.c.RecyclerView_android_orientation, 1);
            dVar.f1221b = typedArrayObtainStyledAttributes.getInt(b.r.c.RecyclerView_spanCount, 1);
            dVar.f1222c = typedArrayObtainStyledAttributes.getBoolean(b.r.c.RecyclerView_reverseLayout, false);
            dVar.f1223d = typedArrayObtainStyledAttributes.getBoolean(b.r.c.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int n(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        public static boolean w0(int i2, int i3, int i4) {
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

        public void A(RecyclerView recyclerView, v vVar) {
            this.f1217i = false;
            I0(recyclerView, vVar);
        }

        public void A0(View view, int i2, int i3) {
            p pVar = (p) view.getLayoutParams();
            Rect rectL0 = this.f1210b.l0(view);
            int i4 = i2 + rectL0.left + rectL0.right;
            int i5 = i3 + rectL0.top + rectL0.bottom;
            int iK = K(o0(), p0(), e0() + f0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).width, k());
            int iK2 = K(W(), X(), g0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) pVar).height, l());
            if (F1(view, iK, iK2, pVar)) {
                view.measure(iK, iK2);
            }
        }

        public void A1(int i2, int i3) {
            this.q = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.o = mode;
            if (mode == 0 && !RecyclerView.f1168d) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.f1168d) {
                return;
            }
            this.r = 0;
        }

        public View B(View view) {
            View viewS;
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null || (viewS = recyclerView.S(view)) == null || this.f1209a.n(viewS)) {
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
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.f1210b.toString());
            }
        }

        public void B1(int i2, int i3) {
            this.f1210b.setMeasuredDimension(i2, i3);
        }

        public View C(int i2) {
            int iJ = J();
            for (int i3 = 0; i3 < iJ; i3++) {
                View viewI = I(i3);
                c0 c0VarH0 = RecyclerView.h0(viewI);
                if (c0VarH0 != null && c0VarH0.m() == i2 && !c0VarH0.J() && (this.f1210b.o0.e() || !c0VarH0.v())) {
                    return viewI;
                }
            }
            return null;
        }

        public void C0(int i2) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                recyclerView.A0(i2);
            }
        }

        public void C1(Rect rect, int i2, int i3) {
            B1(n(i2, rect.width() + e0() + f0(), c0()), n(i3, rect.height() + g0() + d0(), b0()));
        }

        public abstract p D();

        public void D0(int i2) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                recyclerView.B0(i2);
            }
        }

        public void D1(int i2, int i3) {
            int iJ = J();
            if (iJ == 0) {
                this.f1210b.x(i2, i3);
                return;
            }
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = 0; i8 < iJ; i8++) {
                View viewI = I(i8);
                Rect rect = this.f1210b.s;
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
            this.f1210b.s.set(i5, i6, i4, i7);
            C1(this.f1210b.s, i2, i3);
        }

        public p E(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public void E0(g gVar, g gVar2) {
        }

        public void E1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f1210b = null;
                this.f1209a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f1210b = recyclerView;
                this.f1209a = recyclerView.o;
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

        public boolean F1(View view, int i2, int i3, p pVar) {
            return (!view.isLayoutRequested() && this.k && w0(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
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
            return ((p) view.getLayoutParams()).f1225b.bottom;
        }

        @Deprecated
        public void H0(RecyclerView recyclerView) {
        }

        public boolean H1(View view, int i2, int i3, p pVar) {
            return (this.k && w0(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && w0(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public View I(int i2) {
            b.r.d.b bVar = this.f1209a;
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
            b.r.d.b bVar = this.f1209a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public View J0(View view, int i2, v vVar, z zVar) {
            return null;
        }

        public void J1(y yVar) {
            y yVar2 = this.f1215g;
            if (yVar2 != null && yVar != yVar2 && yVar2.h()) {
                this.f1215g.r();
            }
            this.f1215g = yVar;
            yVar.q(this.f1210b, this);
        }

        public void K0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1210b;
            L0(recyclerView.l, recyclerView.o0, accessibilityEvent);
        }

        public void K1() {
            y yVar = this.f1215g;
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

        public void L0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f1210b.canScrollVertically(-1) && !this.f1210b.canScrollHorizontally(-1) && !this.f1210b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f1210b.v;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public boolean L1() {
            return false;
        }

        public boolean M() {
            RecyclerView recyclerView = this.f1210b;
            return recyclerView != null && recyclerView.q;
        }

        public void M0(b.h.m.c0.c cVar) {
            RecyclerView recyclerView = this.f1210b;
            N0(recyclerView.l, recyclerView.o0, cVar);
        }

        public int N(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null || recyclerView.v == null || !k()) {
                return 1;
            }
            return this.f1210b.v.c();
        }

        public void N0(v vVar, z zVar, b.h.m.c0.c cVar) {
            if (this.f1210b.canScrollVertically(-1) || this.f1210b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.x0(true);
            }
            if (this.f1210b.canScrollVertically(1) || this.f1210b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.x0(true);
            }
            cVar.e0(c.b.b(k0(vVar, zVar), N(vVar, zVar), v0(vVar, zVar), l0(vVar, zVar)));
        }

        public int O(View view) {
            return view.getBottom() + H(view);
        }

        public void O0(View view, b.h.m.c0.c cVar) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0 == null || c0VarH0.v() || this.f1209a.n(c0VarH0.f1185b)) {
                return;
            }
            RecyclerView recyclerView = this.f1210b;
            P0(recyclerView.l, recyclerView.o0, view, cVar);
        }

        public void P(View view, Rect rect) {
            RecyclerView.i0(view, rect);
        }

        public void P0(v vVar, z zVar, View view, b.h.m.c0.c cVar) {
            cVar.f0(c.C0044c.a(l() ? h0(view) : 0, 1, k() ? h0(view) : 0, 1, false, false));
        }

        public int Q(View view) {
            return view.getLeft() - a0(view);
        }

        public View Q0(View view, int i2) {
            return null;
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1225b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void R0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int S(View view) {
            Rect rect = ((p) view.getLayoutParams()).f1225b;
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
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f1209a.n(focusedChild)) {
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
            RecyclerView recyclerView = this.f1210b;
            g adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if (adapter != null) {
                return adapter.c();
            }
            return 0;
        }

        public void Y0(z zVar) {
        }

        public int Z() {
            return b.h.m.t.z(this.f1210b);
        }

        public void Z0(v vVar, z zVar, int i2, int i3) {
            this.f1210b.x(i2, i3);
        }

        public int a0(View view) {
            return ((p) view.getLayoutParams()).f1225b.left;
        }

        @Deprecated
        public boolean a1(RecyclerView recyclerView, View view, View view2) {
            return x0() || recyclerView.v0();
        }

        public void b(View view) {
            c(view, -1);
        }

        public int b0() {
            return b.h.m.t.A(this.f1210b);
        }

        public boolean b1(RecyclerView recyclerView, z zVar, View view, View view2) {
            return a1(recyclerView, view, view2);
        }

        public void c(View view, int i2) {
            f(view, i2, true);
        }

        public int c0() {
            return b.h.m.t.B(this.f1210b);
        }

        public void c1(Parcelable parcelable) {
        }

        public void d(View view) {
            e(view, -1);
        }

        public int d0() {
            RecyclerView recyclerView = this.f1210b;
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
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void e1(int i2) {
        }

        public final void f(View view, int i2, boolean z) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (z || c0VarH0.v()) {
                this.f1210b.p.b(c0VarH0);
            } else {
                this.f1210b.p.p(c0VarH0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarH0.L() || c0VarH0.w()) {
                if (c0VarH0.w()) {
                    c0VarH0.K();
                } else {
                    c0VarH0.e();
                }
                this.f1209a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f1210b) {
                int iM = this.f1209a.m(view);
                if (i2 == -1) {
                    i2 = this.f1209a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f1210b.indexOfChild(view) + this.f1210b.Q());
                }
                if (iM != i2) {
                    this.f1210b.w.B0(iM, i2);
                }
            } else {
                this.f1209a.a(view, i2, false);
                pVar.f1226c = true;
                y yVar = this.f1215g;
                if (yVar != null && yVar.h()) {
                    this.f1215g.k(view);
                }
            }
            if (pVar.f1227d) {
                c0VarH0.f1185b.invalidate();
                pVar.f1227d = false;
            }
        }

        public int f0() {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public void f1(y yVar) {
            if (this.f1215g == yVar) {
                this.f1215g = null;
            }
        }

        public void g(String str) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                recyclerView.p(str);
            }
        }

        public int g0() {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public boolean g1(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1210b;
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
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 == 4096) {
                iW = recyclerView.canScrollVertically(1) ? (W() - g0()) - d0() : 0;
                if (this.f1210b.canScrollHorizontally(1)) {
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
                if (this.f1210b.canScrollHorizontally(-1)) {
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
            this.f1210b.p1(i4, i3, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void i(View view, int i2, p pVar) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0.v()) {
                this.f1210b.p.b(c0VarH0);
            } else {
                this.f1210b.p.p(c0VarH0);
            }
            this.f1209a.c(view, i2, pVar, c0VarH0.v());
        }

        public boolean i1(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f1210b;
            return j1(recyclerView.l, recyclerView.o0, view, i2, bundle);
        }

        public void j(View view, Rect rect) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.l0(view));
            }
        }

        public int j0(View view) {
            return ((p) view.getLayoutParams()).f1225b.right;
        }

        public boolean j1(v vVar, z zVar, View view, int i2, Bundle bundle) {
            return false;
        }

        public boolean k() {
            return false;
        }

        public int k0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f1210b;
            if (recyclerView == null || recyclerView.v == null || !l()) {
                return 1;
            }
            return this.f1210b.v.c();
        }

        public void k1(v vVar) {
            for (int iJ = J() - 1; iJ >= 0; iJ--) {
                if (!RecyclerView.h0(I(iJ)).J()) {
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
            for (int i2 = iJ - 1; i2 >= 0; i2--) {
                View viewN = vVar.n(i2);
                c0 c0VarH0 = RecyclerView.h0(viewN);
                if (!c0VarH0.J()) {
                    c0VarH0.G(false);
                    if (c0VarH0.x()) {
                        this.f1210b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f1210b.W;
                    if (lVar != null) {
                        lVar.j(c0VarH0);
                    }
                    c0VarH0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f1210b.invalidate();
            }
        }

        public boolean m(p pVar) {
            return pVar != null;
        }

        public int m0(View view) {
            return ((p) view.getLayoutParams()).f1225b.top;
        }

        public void m1(View view, v vVar) {
            p1(view);
            vVar.B(view);
        }

        public void n0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f1225b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f1210b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f1210b.u;
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
            RecyclerView recyclerView = this.f1210b;
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
            this.f1209a.p(view);
        }

        public int q(z zVar) {
            return 0;
        }

        public boolean q0() {
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
                this.f1209a.q(i2);
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public boolean r0() {
            return this.f1217i;
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
                recyclerView.m1(i2, i3);
            }
            return true;
        }

        public int t(z zVar) {
            return 0;
        }

        public final boolean t0(RecyclerView recyclerView, int i2, int i3) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iE0 = e0();
            int iG0 = g0();
            int iO0 = o0() - f0();
            int iW = W() - d0();
            Rect rect = this.f1210b.s;
            P(focusedChild, rect);
            return rect.left - i2 < iO0 && rect.right - i2 > iE0 && rect.top - i3 < iW && rect.bottom - i3 > iG0;
        }

        public void t1() {
            RecyclerView recyclerView = this.f1210b;
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
            this.f1216h = true;
        }

        public int v(z zVar) {
            return 0;
        }

        public boolean v0(v vVar, z zVar) {
            return false;
        }

        public final void v1(v vVar, int i2, View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0.J()) {
                return;
            }
            if (c0VarH0.t() && !c0VarH0.v() && !this.f1210b.v.g()) {
                q1(i2);
                vVar.C(c0VarH0);
            } else {
                x(i2);
                vVar.D(view);
                this.f1210b.p.k(c0VarH0);
            }
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
            y yVar = this.f1215g;
            return yVar != null && yVar.h();
        }

        public void x1(int i2) {
        }

        public final void y(int i2, View view) {
            this.f1209a.d(i2);
        }

        public boolean y0(View view, boolean z, boolean z2) {
            boolean z3 = this.f1213e.b(view, 24579) && this.f1214f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public int y1(int i2, v vVar, z zVar) {
            return 0;
        }

        public void z(RecyclerView recyclerView) {
            this.f1217i = true;
            G0(recyclerView);
        }

        public void z0(View view, int i2, int i3, int i4, int i5) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f1225b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
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

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SparseArray<a> f1228a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1229b = 0;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final ArrayList<c0> f1230a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1231b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public long f1232c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public long f1233d = 0;
        }

        public void a() {
            this.f1229b++;
        }

        public void b() {
            for (int i2 = 0; i2 < this.f1228a.size(); i2++) {
                this.f1228a.valueAt(i2).f1230a.clear();
            }
        }

        public void c() {
            this.f1229b--;
        }

        public void d(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f1233d = j(aVarG.f1233d, j);
        }

        public void e(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f1232c = j(aVarG.f1232c, j);
        }

        public c0 f(int i2) {
            a aVar = this.f1228a.get(i2);
            if (aVar == null || aVar.f1230a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f1230a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public final a g(int i2) {
            a aVar = this.f1228a.get(i2);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f1228a.put(i2, aVar2);
            return aVar2;
        }

        public void h(g gVar, g gVar2, boolean z) {
            if (gVar != null) {
                c();
            }
            if (!z && this.f1229b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f1230a;
            if (this.f1228a.get(iL).f1231b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        public long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        public boolean k(int i2, long j, long j2) {
            long j3 = g(i2).f1233d;
            return j3 == 0 || j + j3 < j2;
        }

        public boolean l(int i2, long j, long j2) {
            long j3 = g(i2).f1232c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<c0> f1234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<c0> f1235b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<c0> f1236c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<c0> f1237d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1238e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1239f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public u f1240g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public a0 f1241h;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f1234a = arrayList;
            this.f1235b = null;
            this.f1236c = new ArrayList<>();
            this.f1237d = Collections.unmodifiableList(arrayList);
            this.f1238e = 2;
            this.f1239f = 2;
        }

        public void A(int i2) {
            a(this.f1236c.get(i2), true);
            this.f1236c.remove(i2);
        }

        public void B(View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (c0VarH0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (c0VarH0.w()) {
                c0VarH0.K();
            } else if (c0VarH0.L()) {
                c0VarH0.e();
            }
            C(c0VarH0);
            if (RecyclerView.this.W == null || c0VarH0.u()) {
                return;
            }
            RecyclerView.this.W.j(c0VarH0);
        }

        public void C(c0 c0Var) {
            boolean z;
            boolean z2 = true;
            if (c0Var.w() || c0Var.f1185b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f1185b.getParent() != null);
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
            if ((gVar != null && zH && gVar.p(c0Var)) || c0Var.u()) {
                if (this.f1239f <= 0 || c0Var.p(526)) {
                    z = false;
                } else {
                    int size = this.f1236c.size();
                    if (size >= this.f1239f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.f1170f && size > 0 && !RecyclerView.this.n0.d(c0Var.f1187d)) {
                        int i2 = size - 1;
                        while (i2 >= 0) {
                            if (!RecyclerView.this.n0.d(this.f1236c.get(i2).f1187d)) {
                                break;
                            } else {
                                i2--;
                            }
                        }
                        size = i2 + 1;
                    }
                    this.f1236c.add(size, c0Var);
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

        public void D(View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            if (!c0VarH0.p(12) && c0VarH0.y() && !RecyclerView.this.q(c0VarH0)) {
                if (this.f1235b == null) {
                    this.f1235b = new ArrayList<>();
                }
                c0VarH0.H(this, true);
                this.f1235b.add(c0VarH0);
                return;
            }
            if (!c0VarH0.t() || c0VarH0.v() || RecyclerView.this.v.g()) {
                c0VarH0.H(this, false);
                this.f1234a.add(c0VarH0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.Q());
            }
        }

        public void E(u uVar) {
            u uVar2 = this.f1240g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.f1240g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f1240g.a();
        }

        public void F(a0 a0Var) {
        }

        public void G(int i2) {
            this.f1238e = i2;
            K();
        }

        public final boolean H(c0 c0Var, int i2, int i3, long j) {
            c0Var.s = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.f1240g.k(iL, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.v.a(c0Var, i2);
            this.f1240g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.o0.e()) {
                return true;
            }
            c0Var.f1191h = i3;
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
            if (c0Var.p) {
                this.f1235b.remove(c0Var);
            } else {
                this.f1234a.remove(c0Var);
            }
            c0Var.o = null;
            c0Var.p = false;
            c0Var.e();
        }

        public void K() {
            o oVar = RecyclerView.this.w;
            this.f1239f = this.f1238e + (oVar != null ? oVar.m : 0);
            for (int size = this.f1236c.size() - 1; size >= 0 && this.f1236c.size() > this.f1239f; size--) {
                A(size);
            }
        }

        public boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.o0.e();
            }
            int i2 = c0Var.f1187d;
            if (i2 >= 0 && i2 < RecyclerView.this.v.c()) {
                if (RecyclerView.this.o0.e() || RecyclerView.this.v.e(c0Var.f1187d) == c0Var.l()) {
                    return !RecyclerView.this.v.g() || c0Var.k() == RecyclerView.this.v.d(c0Var.f1187d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.Q());
        }

        public void M(int i2, int i3) {
            int i4;
            int i5 = i3 + i2;
            for (int size = this.f1236c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1236c.get(size);
                if (c0Var != null && (i4 = c0Var.f1187d) >= i2 && i4 < i5) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        public void a(c0 c0Var, boolean z) {
            RecyclerView.s(c0Var);
            View view = c0Var.f1185b;
            b.r.d.l lVar = RecyclerView.this.v0;
            if (lVar != null) {
                b.h.m.a aVarN = lVar.n();
                b.h.m.t.k0(view, aVarN instanceof l.a ? ((l.a) aVarN).n(view) : null);
            }
            if (z) {
                g(c0Var);
            }
            c0Var.s = null;
            i().i(c0Var);
        }

        public final void b(c0 c0Var) {
            if (RecyclerView.this.u0()) {
                View view = c0Var.f1185b;
                if (b.h.m.t.x(view) == 0) {
                    b.h.m.t.u0(view, 1);
                }
                b.r.d.l lVar = RecyclerView.this.v0;
                if (lVar == null) {
                    return;
                }
                b.h.m.a aVarN = lVar.n();
                if (aVarN instanceof l.a) {
                    ((l.a) aVarN).o(view);
                }
                b.h.m.t.k0(view, aVarN);
            }
        }

        public void c() {
            this.f1234a.clear();
            z();
        }

        public void d() {
            int size = this.f1236c.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f1236c.get(i2).c();
            }
            int size2 = this.f1234a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                this.f1234a.get(i3).c();
            }
            ArrayList<c0> arrayList = this.f1235b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    this.f1235b.get(i4).c();
                }
            }
        }

        public void e() {
            this.f1234a.clear();
            ArrayList<c0> arrayList = this.f1235b;
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

        public void g(c0 c0Var) {
            w wVar = RecyclerView.this.x;
            if (wVar != null) {
                wVar.a(c0Var);
            }
            g gVar = RecyclerView.this.v;
            if (gVar != null) {
                gVar.s(c0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.o0 != null) {
                recyclerView.p.q(c0Var);
            }
        }

        public c0 h(int i2) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f1235b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    c0 c0Var = this.f1235b.get(i3);
                    if (!c0Var.L() && c0Var.m() == i2) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.v.g() && (iM = RecyclerView.this.n.m(i2)) > 0 && iM < RecyclerView.this.v.c()) {
                    long jD = RecyclerView.this.v.d(iM);
                    for (int i4 = 0; i4 < size; i4++) {
                        c0 c0Var2 = this.f1235b.get(i4);
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
            if (this.f1240g == null) {
                this.f1240g = new u();
            }
            return this.f1240g;
        }

        public int j() {
            return this.f1234a.size();
        }

        public List<c0> k() {
            return this.f1237d;
        }

        public c0 l(long j, int i2, boolean z) {
            for (int size = this.f1234a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1234a.get(size);
                if (c0Var.k() == j && !c0Var.L()) {
                    if (i2 == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.o0.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z) {
                        this.f1234a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f1185b, false);
                        y(c0Var.f1185b);
                    }
                }
            }
            int size2 = this.f1236c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f1236c.get(size2);
                if (c0Var2.k() == j && !c0Var2.r()) {
                    if (i2 == c0Var2.l()) {
                        if (!z) {
                            this.f1236c.remove(size2);
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

        public c0 m(int i2, boolean z) {
            View viewE;
            int size = this.f1234a.size();
            for (int i3 = 0; i3 < size; i3++) {
                c0 c0Var = this.f1234a.get(i3);
                if (!c0Var.L() && c0Var.m() == i2 && !c0Var.t() && (RecyclerView.this.o0.f1266h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z || (viewE = RecyclerView.this.o.e(i2)) == null) {
                int size2 = this.f1236c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    c0 c0Var2 = this.f1236c.get(i4);
                    if (!c0Var2.t() && c0Var2.m() == i2 && !c0Var2.r()) {
                        if (!z) {
                            this.f1236c.remove(i4);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarH0 = RecyclerView.h0(viewE);
            RecyclerView.this.o.s(viewE);
            int iM = RecyclerView.this.o.m(viewE);
            if (iM != -1) {
                RecyclerView.this.o.d(iM);
                D(viewE);
                c0VarH0.b(8224);
                return c0VarH0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarH0 + RecyclerView.this.Q());
        }

        public View n(int i2) {
            return this.f1234a.get(i2).f1185b;
        }

        public View o(int i2) {
            return p(i2, false);
        }

        public View p(int i2, boolean z) {
            return I(i2, z, Long.MAX_VALUE).f1185b;
        }

        public final void q(ViewGroup viewGroup, boolean z) {
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

        public final void r(c0 c0Var) {
            View view = c0Var.f1185b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        public void s() {
            int size = this.f1236c.size();
            for (int i2 = 0; i2 < size; i2++) {
                p pVar = (p) this.f1236c.get(i2).f1185b.getLayoutParams();
                if (pVar != null) {
                    pVar.f1226c = true;
                }
            }
        }

        public void t() {
            int size = this.f1236c.size();
            for (int i2 = 0; i2 < size; i2++) {
                c0 c0Var = this.f1236c.get(i2);
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

        public void u(int i2, int i3) {
            int size = this.f1236c.size();
            for (int i4 = 0; i4 < size; i4++) {
                c0 c0Var = this.f1236c.get(i4);
                if (c0Var != null && c0Var.f1187d >= i2) {
                    c0Var.A(i3, true);
                }
            }
        }

        public void v(int i2, int i3) {
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
            int size = this.f1236c.size();
            for (int i8 = 0; i8 < size; i8++) {
                c0 c0Var = this.f1236c.get(i8);
                if (c0Var != null && (i7 = c0Var.f1187d) >= i6 && i7 <= i5) {
                    if (i7 == i2) {
                        c0Var.A(i3 - i2, false);
                    } else {
                        c0Var.A(i4, false);
                    }
                }
            }
        }

        public void w(int i2, int i3, boolean z) {
            int i4 = i2 + i3;
            for (int size = this.f1236c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f1236c.get(size);
                if (c0Var != null) {
                    int i5 = c0Var.f1187d;
                    if (i5 >= i4) {
                        c0Var.A(-i3, z);
                    } else if (i5 >= i2) {
                        c0Var.b(8);
                        A(size);
                    }
                }
            }
        }

        public void x(g gVar, g gVar2, boolean z) {
            c();
            i().h(gVar, gVar2, z);
        }

        public void y(View view) {
            c0 c0VarH0 = RecyclerView.h0(view);
            c0VarH0.o = null;
            c0VarH0.p = false;
            c0VarH0.e();
            C(c0VarH0);
        }

        public void z() {
            for (int size = this.f1236c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f1236c.clear();
            if (RecyclerView.f1170f) {
                RecyclerView.this.n0.b();
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
            recyclerView.o0.f1265g = true;
            recyclerView.Q0(true);
            if (RecyclerView.this.n.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void c(int i2, int i3, Object obj) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.n.r(i2, i3, obj)) {
                e();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i2, int i3) {
            RecyclerView.this.p(null);
            if (RecyclerView.this.n.s(i2, i3)) {
                e();
            }
        }

        public void e() {
            if (RecyclerView.f1169e) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.C && recyclerView.B) {
                    b.h.m.t.c0(recyclerView, recyclerView.r);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.K = true;
            recyclerView2.requestLayout();
        }
    }

    public static abstract class y {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f1245b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public o f1246c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1247d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1248e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f1249f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1251h;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1244a = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final a f1250g = new a(0, 0);

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1252a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f1253b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1254c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1255d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public Interpolator f1256e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public boolean f1257f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            public int f1258g;

            public a(int i2, int i3) {
                this(i2, i3, Integer.MIN_VALUE, null);
            }

            public boolean a() {
                return this.f1255d >= 0;
            }

            public void b(int i2) {
                this.f1255d = i2;
            }

            public void c(RecyclerView recyclerView) {
                int i2 = this.f1255d;
                if (i2 >= 0) {
                    this.f1255d = -1;
                    recyclerView.x0(i2);
                    this.f1257f = false;
                } else {
                    if (!this.f1257f) {
                        this.f1258g = 0;
                        return;
                    }
                    e();
                    recyclerView.l0.f(this.f1252a, this.f1253b, this.f1254c, this.f1256e);
                    int i3 = this.f1258g + 1;
                    this.f1258g = i3;
                    if (i3 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f1257f = false;
                }
            }

            public void d(int i2, int i3, int i4, Interpolator interpolator) {
                this.f1252a = i2;
                this.f1253b = i3;
                this.f1254c = i4;
                this.f1256e = interpolator;
                this.f1257f = true;
            }

            public final void e() {
                if (this.f1256e != null && this.f1254c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f1254c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public a(int i2, int i3, int i4, Interpolator interpolator) {
                this.f1255d = -1;
                this.f1257f = false;
                this.f1258g = 0;
                this.f1252a = i2;
                this.f1253b = i3;
                this.f1254c = i4;
                this.f1256e = interpolator;
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
            return this.f1245b.w.C(i2);
        }

        public int c() {
            return this.f1245b.w.J();
        }

        public int d(View view) {
            return this.f1245b.f0(view);
        }

        public o e() {
            return this.f1246c;
        }

        public int f() {
            return this.f1244a;
        }

        public boolean g() {
            return this.f1247d;
        }

        public boolean h() {
            return this.f1248e;
        }

        public void i(PointF pointF) {
            float f2 = pointF.x;
            float f3 = pointF.y;
            float fSqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        public void j(int i2, int i3) {
            PointF pointFA;
            RecyclerView recyclerView = this.f1245b;
            if (this.f1244a == -1 || recyclerView == null) {
                r();
            }
            if (this.f1247d && this.f1249f == null && this.f1246c != null && (pointFA = a(this.f1244a)) != null) {
                float f2 = pointFA.x;
                if (f2 != 0.0f || pointFA.y != 0.0f) {
                    recyclerView.h1((int) Math.signum(f2), (int) Math.signum(pointFA.y), null);
                }
            }
            this.f1247d = false;
            View view = this.f1249f;
            if (view != null) {
                if (d(view) == this.f1244a) {
                    o(this.f1249f, recyclerView.o0, this.f1250g);
                    this.f1250g.c(recyclerView);
                    r();
                } else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.f1249f = null;
                }
            }
            if (this.f1248e) {
                l(i2, i3, recyclerView.o0, this.f1250g);
                boolean zA = this.f1250g.a();
                this.f1250g.c(recyclerView);
                if (zA && this.f1248e) {
                    this.f1247d = true;
                    recyclerView.l0.e();
                }
            }
        }

        public void k(View view) {
            if (d(view) == f()) {
                this.f1249f = view;
            }
        }

        public abstract void l(int i2, int i3, z zVar, a aVar);

        public abstract void m();

        public abstract void n();

        public abstract void o(View view, z zVar, a aVar);

        public void p(int i2) {
            this.f1244a = i2;
        }

        public void q(RecyclerView recyclerView, o oVar) {
            recyclerView.l0.g();
            if (this.f1251h) {
                Log.w("RecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            this.f1245b = recyclerView;
            this.f1246c = oVar;
            int i2 = this.f1244a;
            if (i2 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.o0.f1259a = i2;
            this.f1248e = true;
            this.f1247d = true;
            this.f1249f = b(f());
            m();
            this.f1245b.l0.e();
            this.f1251h = true;
        }

        public final void r() {
            if (this.f1248e) {
                this.f1248e = false;
                n();
                this.f1245b.o0.f1259a = -1;
                this.f1249f = null;
                this.f1244a = -1;
                this.f1247d = false;
                this.f1246c.f1(this);
                this.f1246c = null;
                this.f1245b = null;
            }
        }
    }

    public static class z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SparseArray<Object> f1260b;
        public int m;
        public long n;
        public int o;
        public int p;
        public int q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1259a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1261c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1262d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1263e = 1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1264f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f1265g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1266h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1267i = false;
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;

        public void a(int i2) {
            if ((this.f1263e & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f1263e));
        }

        public int b() {
            return this.f1266h ? this.f1261c - this.f1262d : this.f1264f;
        }

        public int c() {
            return this.f1259a;
        }

        public boolean d() {
            return this.f1259a != -1;
        }

        public boolean e() {
            return this.f1266h;
        }

        public void f(g gVar) {
            this.f1263e = 1;
            this.f1264f = gVar.c();
            this.f1266h = false;
            this.f1267i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f1259a + ", mData=" + this.f1260b + ", mItemCount=" + this.f1264f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f1261c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f1262d + ", mStructureChanged=" + this.f1265g + ", mInPreLayout=" + this.f1266h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f1167c = i2 == 18 || i2 == 19 || i2 == 20;
        f1168d = i2 >= 23;
        f1169e = i2 >= 16;
        f1170f = i2 >= 21;
        f1171g = i2 <= 15;
        f1172h = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        f1173i = new Class[]{Context.class, AttributeSet.class, cls, cls};
        j = new c();
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
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewW = W(viewGroup.getChildAt(i2));
            if (recyclerViewW != null) {
                return recyclerViewW;
            }
        }
        return null;
    }

    private b.h.m.k getScrollingChildHelper() {
        if (this.y0 == null) {
            this.y0 = new b.h.m.k(this);
        }
        return this.y0;
    }

    public static c0 h0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f1224a;
    }

    public static void i0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f1225b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    public static void s(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f1186c;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f1185b) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f1186c = null;
        }
    }

    public void A(View view) {
        c0 c0VarH0 = h0(view);
        G0(view);
        g gVar = this.v;
        if (gVar != null && c0VarH0 != null) {
            gVar.r(c0VarH0);
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
            this.o.f(i3).offsetLeftAndRight(i2);
        }
    }

    public final void B() {
        int i2 = this.J;
        this.J = 0;
        if (i2 == 0 || !u0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        b.h.m.c0.b.b(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    public void B0(int i2) {
        int iG = this.o.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.o.f(i3).offsetTopAndBottom(i2);
        }
    }

    public void C() {
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
        if (zVar.f1263e == 1) {
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

    public void C0(int i2, int i3) {
        int iJ = this.o.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            c0 c0VarH0 = h0(this.o.i(i4));
            if (c0VarH0 != null && !c0VarH0.J() && c0VarH0.f1187d >= i2) {
                c0VarH0.A(i3, false);
                this.o0.f1265g = true;
            }
        }
        this.l.u(i2, i3);
        requestLayout();
    }

    public final void D() {
        this.o0.a(1);
        R(this.o0);
        this.o0.j = false;
        r1();
        this.p.f();
        H0();
        P0();
        e1();
        z zVar = this.o0;
        zVar.f1267i = zVar.k && this.s0;
        this.s0 = false;
        this.r0 = false;
        zVar.f1266h = zVar.l;
        zVar.f1264f = this.v.c();
        V(this.x0);
        if (this.o0.k) {
            int iG = this.o.g();
            for (int i2 = 0; i2 < iG; i2++) {
                c0 c0VarH0 = h0(this.o.f(i2));
                if (!c0VarH0.J() && (!c0VarH0.t() || this.v.g())) {
                    this.p.e(c0VarH0, this.W.t(this.o0, c0VarH0, l.e(c0VarH0), c0VarH0.o()));
                    if (this.o0.f1267i && c0VarH0.y() && !c0VarH0.v() && !c0VarH0.J() && !c0VarH0.t()) {
                        this.p.c(d0(c0VarH0), c0VarH0);
                    }
                }
            }
        }
        if (this.o0.l) {
            f1();
            z zVar2 = this.o0;
            boolean z2 = zVar2.f1265g;
            zVar2.f1265g = false;
            this.w.X0(this.l, zVar2);
            this.o0.f1265g = z2;
            for (int i3 = 0; i3 < this.o.g(); i3++) {
                c0 c0VarH02 = h0(this.o.f(i3));
                if (!c0VarH02.J() && !this.p.i(c0VarH02)) {
                    int iE = l.e(c0VarH02);
                    boolean zP = c0VarH02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.W.t(this.o0, c0VarH02, iE, c0VarH02.o());
                    if (zP) {
                        S0(c0VarH02, cVarT);
                    } else {
                        this.p.a(c0VarH02, cVarT);
                    }
                }
            }
            t();
        } else {
            t();
        }
        I0();
        t1(false);
        this.o0.f1263e = 2;
    }

    public void D0(int i2, int i3) {
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
            c0 c0VarH0 = h0(this.o.i(i8));
            if (c0VarH0 != null && (i7 = c0VarH0.f1187d) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    c0VarH0.A(i3 - i2, false);
                } else {
                    c0VarH0.A(i6, false);
                }
                this.o0.f1265g = true;
            }
        }
        this.l.v(i2, i3);
        requestLayout();
    }

    public final void E() {
        r1();
        H0();
        this.o0.a(6);
        this.n.j();
        this.o0.f1264f = this.v.c();
        z zVar = this.o0;
        zVar.f1262d = 0;
        zVar.f1266h = false;
        this.w.X0(this.l, zVar);
        z zVar2 = this.o0;
        zVar2.f1265g = false;
        this.m = null;
        zVar2.k = zVar2.k && this.W != null;
        zVar2.f1263e = 4;
        I0();
        t1(false);
    }

    public void E0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.o.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            c0 c0VarH0 = h0(this.o.i(i5));
            if (c0VarH0 != null && !c0VarH0.J()) {
                int i6 = c0VarH0.f1187d;
                if (i6 >= i4) {
                    c0VarH0.A(-i3, z2);
                    this.o0.f1265g = true;
                } else if (i6 >= i2) {
                    c0VarH0.i(i2 - 1, -i3, z2);
                    this.o0.f1265g = true;
                }
            }
        }
        this.l.w(i2, i3, z2);
        requestLayout();
    }

    public final void F() {
        this.o0.a(4);
        r1();
        H0();
        z zVar = this.o0;
        zVar.f1263e = 1;
        if (zVar.k) {
            for (int iG = this.o.g() - 1; iG >= 0; iG--) {
                c0 c0VarH0 = h0(this.o.f(iG));
                if (!c0VarH0.J()) {
                    long jD0 = d0(c0VarH0);
                    l.c cVarS = this.W.s(this.o0, c0VarH0);
                    c0 c0VarG = this.p.g(jD0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.p.d(c0VarH0, cVarS);
                    } else {
                        boolean zH = this.p.h(c0VarG);
                        boolean zH2 = this.p.h(c0VarH0);
                        if (zH && c0VarG == c0VarH0) {
                            this.p.d(c0VarH0, cVarS);
                        } else {
                            l.c cVarN = this.p.n(c0VarG);
                            this.p.d(c0VarH0, cVarS);
                            l.c cVarM = this.p.m(c0VarH0);
                            if (cVarN == null) {
                                m0(jD0, c0VarH0, c0VarG);
                            } else {
                                n(c0VarG, c0VarH0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.p.o(this.E0);
        }
        this.w.l1(this.l);
        z zVar2 = this.o0;
        zVar2.f1261c = zVar2.f1264f;
        this.N = false;
        this.O = false;
        zVar2.k = false;
        zVar2.l = false;
        this.w.f1216h = false;
        ArrayList<c0> arrayList = this.l.f1235b;
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
        I0();
        t1(false);
        this.p.f();
        int[] iArr = this.x0;
        if (y(iArr[0], iArr[1])) {
            J(0, 0);
        }
        T0();
        c1();
    }

    public void F0(View view) {
    }

    public boolean G(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    public void G0(View view) {
    }

    public final void H(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public void H0() {
        this.P++;
    }

    public void I(int i2) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.e1(i2);
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

    public void I0() {
        J0(true);
    }

    public void J(int i2, int i3) {
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

    public void J0(boolean z2) {
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

    public void K() {
        int i2;
        for (int size = this.C0.size() - 1; size >= 0; size--) {
            c0 c0Var = this.C0.get(size);
            if (c0Var.f1185b.getParent() == this && !c0Var.J() && (i2 = c0Var.r) != -1) {
                b.h.m.t.u0(c0Var.f1185b, i2);
                c0Var.r = -1;
            }
        }
        this.C0.clear();
    }

    public final void K0(MotionEvent motionEvent) {
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

    public void L0(int i2) {
    }

    public void M() {
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

    public void M0(int i2, int i3) {
    }

    public void N() {
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

    public void N0() {
        if (this.u0 || !this.B) {
            return;
        }
        b.h.m.t.c0(this, this.D0);
        this.u0 = true;
    }

    public void O() {
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

    public final boolean O0() {
        return this.W != null && this.w.L1();
    }

    public void P() {
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

    public final void P0() {
        boolean z2;
        if (this.N) {
            this.n.w();
            if (this.O) {
                this.w.S0(this);
            }
        }
        if (O0()) {
            this.n.u();
        } else {
            this.n.j();
        }
        boolean z3 = false;
        boolean z4 = this.r0 || this.s0;
        this.o0.k = this.E && this.W != null && ((z2 = this.N) || z4 || this.w.f1216h) && (!z2 || this.v.g());
        z zVar = this.o0;
        if (zVar.k && z4 && !this.N && O0()) {
            z3 = true;
        }
        zVar.l = z3;
    }

    public String Q() {
        return " " + super.toString() + ", adapter:" + this.v + ", layout:" + this.w + ", context:" + getContext();
    }

    public void Q0(boolean z2) {
        this.O = z2 | this.O;
        this.N = true;
        z0();
    }

    public final void R(z zVar) {
        if (getScrollState() != 2) {
            zVar.p = 0;
            zVar.q = 0;
        } else {
            OverScroller overScroller = this.l0.f1179d;
            zVar.p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R0(float r7, float r8, float r9, float r10) {
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
            b.h.n.d.a(r1, r4, r9)
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
            b.h.n.d.a(r1, r4, r9)
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
            b.h.n.d.a(r9, r1, r7)
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
            b.h.n.d.a(r9, r1, r2)
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
            b.h.m.t.a0(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.R0(float, float, float, float):void");
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

    public void S0(c0 c0Var, l.c cVar) {
        c0Var.F(0, 8192);
        if (this.o0.f1267i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.p.c(d0(c0Var), c0Var);
        }
        this.p.e(c0Var, cVar);
    }

    public c0 T(View view) {
        View viewS = S(view);
        if (viewS == null) {
            return null;
        }
        return g0(viewS);
    }

    public final void T0() {
        View viewFindViewById;
        if (!this.k0 || this.v == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!f1172h || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
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
        if (c0VarZ != null && !this.o.n(c0VarZ.f1185b) && c0VarZ.f1185b.hasFocusable()) {
            viewX = c0VarZ.f1185b;
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

    public final boolean U(MotionEvent motionEvent) {
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

    public final void U0() {
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
            b.h.m.t.a0(this);
        }
    }

    public final void V(int[] iArr) {
        int iG = this.o.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iG; i4++) {
            c0 c0VarH0 = h0(this.o.f(i4));
            if (!c0VarH0.J()) {
                int iM = c0VarH0.m();
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

    public void V0() {
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

    public boolean W0(View view) {
        r1();
        boolean zR = this.o.r(view);
        if (zR) {
            c0 c0VarH0 = h0(view);
            this.l.J(c0VarH0);
            this.l.C(c0VarH0);
        }
        t1(!zR);
        return zR;
    }

    public final View X() {
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
            if (c0VarY2.f1185b.hasFocusable()) {
                return c0VarY2.f1185b;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (c0VarY = Y(iMin)) == null) {
                return null;
            }
        } while (!c0VarY.f1185b.hasFocusable());
        return c0VarY.f1185b;
    }

    public void X0(n nVar) {
        o oVar = this.w;
        if (oVar != null) {
            oVar.g("Cannot remove item decoration during a scroll  or layout");
        }
        this.y.remove(nVar);
        if (this.y.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        y0();
        requestLayout();
    }

    public c0 Y(int i2) {
        c0 c0Var = null;
        if (this.N) {
            return null;
        }
        int iJ = this.o.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarH0 = h0(this.o.i(i3));
            if (c0VarH0 != null && !c0VarH0.v() && c0(c0VarH0) == i2) {
                if (!this.o.n(c0VarH0.f1185b)) {
                    return c0VarH0;
                }
                c0Var = c0VarH0;
            }
        }
        return c0Var;
    }

    public void Y0(s sVar) {
        this.z.remove(sVar);
        if (this.A == sVar) {
            this.A = null;
        }
    }

    public c0 Z(long j2) {
        g gVar = this.v;
        c0 c0Var = null;
        if (gVar != null && gVar.g()) {
            int iJ = this.o.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                c0 c0VarH0 = h0(this.o.i(i2));
                if (c0VarH0 != null && !c0VarH0.v() && c0VarH0.k() == j2) {
                    if (!this.o.n(c0VarH0.f1185b)) {
                        return c0VarH0;
                    }
                    c0Var = c0VarH0;
                }
            }
        }
        return c0Var;
    }

    public void Z0(t tVar) {
        List<t> list = this.q0;
        if (list != null) {
            list.remove(tVar);
        }
    }

    public void a(int i2, int i3) {
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
        b.h.m.t.a0(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.recyclerview.widget.RecyclerView.c0 a0(int r6, boolean r7) {
        /*
            r5 = this;
            b.r.d.b r0 = r5.o
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            b.r.d.b r3 = r5.o
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$c0 r3 = h0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f1187d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            b.r.d.b r1 = r5.o
            android.view.View r4 = r3.f1185b
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

    public void a1() {
        c0 c0Var;
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.o.f(i2);
            c0 c0VarG0 = g0(viewF);
            if (c0VarG0 != null && (c0Var = c0VarG0.j) != null) {
                View view = c0Var.f1185b;
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

    public final void b1(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.s.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f1226c) {
                Rect rect = pVar.f1225b;
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

    public int c0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.n.e(c0Var.f1187d);
    }

    public final void c1() {
        z zVar = this.o0;
        zVar.n = -1L;
        zVar.m = -1;
        zVar.o = -1;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
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

    public long d0(c0 c0Var) {
        return this.v.g() ? c0Var.k() : c0Var.f1187d;
    }

    public final void d1() {
        VelocityTracker velocityTracker = this.Z;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        u1(0);
        U0();
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
            b.h.m.t.a0(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public int e0(View view) {
        c0 c0VarH0 = h0(view);
        if (c0VarH0 != null) {
            return c0VarH0.j();
        }
        return -1;
    }

    public final void e1() {
        View focusedChild = (this.k0 && hasFocus() && this.v != null) ? getFocusedChild() : null;
        c0 c0VarT = focusedChild != null ? T(focusedChild) : null;
        if (c0VarT == null) {
            c1();
            return;
        }
        this.o0.n = this.v.g() ? c0VarT.k() : -1L;
        this.o0.m = this.N ? -1 : c0VarT.v() ? c0VarT.f1188e : c0VarT.j();
        this.o0.o = j0(c0VarT.f1185b);
    }

    public int f0(View view) {
        c0 c0VarH0 = h0(view);
        if (c0VarH0 != null) {
            return c0VarH0.m();
        }
        return -1;
    }

    public void f1() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarH0 = h0(this.o.i(i2));
            if (!c0VarH0.J()) {
                c0VarH0.E();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewJ0;
        boolean z2;
        View viewQ0 = this.w.Q0(view, i2);
        if (viewQ0 != null) {
            return viewQ0;
        }
        boolean z3 = (this.v == null || this.w == null || v0() || this.H) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.w.l()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (f1171g) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.w.k()) {
                int i4 = (this.w.Z() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (f1171g) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                v();
                if (S(view) == null) {
                    return null;
                }
                r1();
                this.w.J0(view, i2, this.l, this.o0);
                t1(false);
            }
            viewJ0 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                v();
                if (S(view) == null) {
                    return null;
                }
                r1();
                viewJ0 = this.w.J0(view, i2, this.l, this.o0);
                t1(false);
            } else {
                viewJ0 = viewFindNextFocus;
            }
        }
        if (viewJ0 == null || viewJ0.hasFocusable()) {
            return w0(view, viewJ0, i2) ? viewJ0 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        b1(viewJ0, null);
        return view;
    }

    public final void g(c0 c0Var) {
        View view = c0Var.f1185b;
        boolean z2 = view.getParent() == this;
        this.l.J(g0(view));
        if (c0Var.x()) {
            this.o.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.o.k(view);
        } else {
            this.o.b(view, true);
        }
    }

    public c0 g0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return h0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public boolean g1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        v();
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
            if (motionEvent != null && !b.h.m.i.a(motionEvent, 8194)) {
                R0(motionEvent.getX(), i10, motionEvent.getY(), i11);
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

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
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
    public int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.w0;
        return jVar == null ? super.getChildDrawingOrder(i2, i3) : jVar.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.q;
    }

    public b.r.d.l getCompatAccessibilityDelegate() {
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

    public long getNanoTime() {
        if (f1170f) {
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

    public void h1(int i2, int i3, int[] iArr) {
        r1();
        H0();
        b.h.i.b.a("RV Scroll");
        R(this.o0);
        int iW1 = i2 != 0 ? this.w.w1(i2, this.l, this.o0) : 0;
        int iY1 = i3 != 0 ? this.w.y1(i3, this.l, this.o0) : 0;
        b.h.i.b.b();
        a1();
        I0();
        t1(false);
        if (iArr != null) {
            iArr[0] = iW1;
            iArr[1] = iY1;
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
            oVar.x1(i2);
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

    @Override // android.view.View, b.h.m.j
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(q qVar) {
        if (this.M == null) {
            this.M = new ArrayList();
        }
        this.M.add(qVar);
    }

    public final int j0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public final void j1(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.v;
        if (gVar2 != null) {
            gVar2.v(this.k);
            this.v.o(this);
        }
        if (!z2 || z3) {
            V0();
        }
        this.n.w();
        g gVar3 = this.v;
        this.v = gVar;
        if (gVar != null) {
            gVar.t(this.k);
            gVar.k(this);
        }
        o oVar = this.w;
        if (oVar != null) {
            oVar.E0(gVar3, this.v);
        }
        this.l.x(gVar3, this.v, z2);
        this.o0.f1265g = true;
    }

    public void k(s sVar) {
        this.z.add(sVar);
    }

    public final String k0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    public boolean k1(c0 c0Var, int i2) {
        if (!v0()) {
            b.h.m.t.u0(c0Var.f1185b, i2);
            return true;
        }
        c0Var.r = i2;
        this.C0.add(c0Var);
        return false;
    }

    public void l(t tVar) {
        if (this.q0 == null) {
            this.q0 = new ArrayList();
        }
        this.q0.add(tVar);
    }

    public Rect l0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f1226c) {
            return pVar.f1225b;
        }
        if (this.o0.e() && (pVar.b() || pVar.d())) {
            return pVar.f1225b;
        }
        Rect rect = pVar.f1225b;
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
        pVar.f1226c = false;
        return rect;
    }

    public boolean l1(AccessibilityEvent accessibilityEvent) {
        if (!v0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? b.h.m.c0.b.a(accessibilityEvent) : 0;
        this.J |= iA != 0 ? iA : 0;
        return true;
    }

    public void m(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.W.a(c0Var, cVar, cVar2)) {
            N0();
        }
    }

    public final void m0(long j2, c0 c0Var, c0 c0Var2) {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarH0 = h0(this.o.f(i2));
            if (c0VarH0 != c0Var && d0(c0VarH0) == j2) {
                g gVar = this.v;
                if (gVar == null || !gVar.g()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarH0 + " \n View Holder 2:" + c0Var + Q());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarH0 + " \n View Holder 2:" + c0Var + Q());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + Q());
    }

    public void m1(int i2, int i3) {
        n1(i2, i3, null);
    }

    public final void n(c0 c0Var, c0 c0Var2, l.c cVar, l.c cVar2, boolean z2, boolean z3) {
        c0Var.G(false);
        if (z2) {
            g(c0Var);
        }
        if (c0Var != c0Var2) {
            if (z3) {
                g(c0Var2);
            }
            c0Var.f1192i = c0Var2;
            g(c0Var);
            this.l.J(c0Var);
            c0Var2.G(false);
            c0Var2.j = c0Var;
        }
        if (this.W.b(c0Var, c0Var2, cVar, cVar2)) {
            N0();
        }
    }

    public boolean n0() {
        return !this.E || this.N || this.n.p();
    }

    public void n1(int i2, int i3, Interpolator interpolator) {
        o1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    public void o(c0 c0Var, l.c cVar, l.c cVar2) {
        g(c0Var);
        c0Var.G(false);
        if (this.W.c(c0Var, cVar, cVar2)) {
            N0();
        }
    }

    public final boolean o0() {
        int iG = this.o.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarH0 = h0(this.o.f(i2));
            if (c0VarH0 != null && !c0VarH0.J() && c0VarH0.y()) {
                return true;
            }
        }
        return false;
    }

    public void o1(int i2, int i3, Interpolator interpolator, int i4) {
        p1(i2, i3, interpolator, i4, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = 0;
        this.B = true;
        this.E = this.E && !isLayoutRequested();
        o oVar = this.w;
        if (oVar != null) {
            oVar.z(this);
        }
        this.u0 = false;
        if (f1170f) {
            ThreadLocal<b.r.d.e> threadLocal = b.r.d.e.f3087b;
            b.r.d.e eVar = threadLocal.get();
            this.m0 = eVar;
            if (eVar == null) {
                this.m0 = new b.r.d.e();
                Display displayT = b.h.m.t.t(this);
                float f2 = 60.0f;
                if (!isInEditMode() && displayT != null) {
                    float refreshRate = displayT.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                b.r.d.e eVar2 = this.m0;
                eVar2.f3091f = (long) (1.0E9f / f2);
                threadLocal.set(eVar2);
            }
            this.m0.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        b.r.d.e eVar;
        super.onDetachedFromWindow();
        l lVar = this.W;
        if (lVar != null) {
            lVar.k();
        }
        v1();
        this.B = false;
        o oVar = this.w;
        if (oVar != null) {
            oVar.A(this, this.l);
        }
        this.C0.clear();
        removeCallbacks(this.D0);
        this.p.j();
        if (!f1170f || (eVar = this.m0) == null) {
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
                u1(1);
            }
            int[] iArr = this.A0;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zK;
            if (zL) {
                i2 = (zK ? 1 : 0) | 2;
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
            K0(motionEvent);
        }
        return this.X == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        b.h.i.b.a("RV OnLayout");
        C();
        b.h.i.b.b();
        this.E = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
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
            if (this.o0.f1263e == 1) {
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
            r1();
            H0();
            P0();
            I0();
            z zVar = this.o0;
            if (zVar.l) {
                zVar.f1266h = true;
            } else {
                this.n.j();
                this.o0.f1266h = false;
            }
            this.K = false;
            t1(false);
        } else if (this.o0.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.v;
        if (gVar != null) {
            this.o0.f1264f = gVar.c();
        } else {
            this.o0.f1264f = 0;
        }
        r1();
        this.w.Z0(this.l, this.o0, i2, i3);
        t1(false);
        this.o0.f1266h = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (v0()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i2, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.m = savedState;
        super.onRestoreInstanceState(savedState.j());
        o oVar = this.w;
        if (oVar == null || (parcelable2 = this.m.f1174d) == null) {
            return;
        }
        oVar.c1(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.m;
        if (savedState2 != null) {
            savedState.k(savedState2);
        } else {
            o oVar = this.w;
            if (oVar != null) {
                savedState.f1174d = oVar.d1();
            } else {
                savedState.f1174d = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
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

    public void p(String str) {
        if (v0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + Q());
        }
        if (this.Q > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + Q()));
        }
    }

    public void p0() {
        this.n = new b.r.d.a(new f());
    }

    public void p1(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
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
            s1(i5, 1);
        }
        this.l0.f(i2, i3, i4, interpolator);
    }

    public boolean q(c0 c0Var) {
        l lVar = this.W;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    @SuppressLint({"InlinedApi"})
    public final void q0() {
        if (b.h.m.t.y(this) == 0) {
            b.h.m.t.v0(this, 8);
        }
    }

    public void q1(int i2) {
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

    public final void r() {
        d1();
        setScrollState(0);
    }

    public final void r0() {
        this.o = new b.r.d.b(new e());
    }

    public void r1() {
        int i2 = this.F + 1;
        this.F = i2;
        if (i2 != 1 || this.H) {
            return;
        }
        this.G = false;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z2) {
        c0 c0VarH0 = h0(view);
        if (c0VarH0 != null) {
            if (c0VarH0.x()) {
                c0VarH0.f();
            } else if (!c0VarH0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + c0VarH0 + Q());
            }
        }
        view.clearAnimation();
        A(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.w.b1(this, this.o0, view, view2) && view2 != null) {
            b1(view, view2);
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

    public void s0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new b.r.d.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(b.r.b.fastscroll_default_thickness), resources.getDimensionPixelSize(b.r.b.fastscroll_minimum_range), resources.getDimensionPixelOffset(b.r.b.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + Q());
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
        boolean zK = oVar.k();
        boolean zL = this.w.l();
        if (zK || zL) {
            if (!zK) {
                i2 = 0;
            }
            if (!zL) {
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

    public void setAccessibilityDelegateCompat(b.r.d.l lVar) {
        this.v0 = lVar;
        b.h.m.t.k0(this, lVar);
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
        b.h.l.g.c(kVar);
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
            if (oVar.f1210b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f1210b.Q());
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

    public void setScrollState(int i2) {
        if (i2 == this.X) {
            return;
        }
        this.X = i2;
        if (i2 != 2) {
            w1();
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

    @Override // android.view.View, b.h.m.j
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

    public void t() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarH0 = h0(this.o.i(i2));
            if (!c0VarH0.J()) {
                c0VarH0.c();
            }
        }
        this.l.d();
    }

    public void t0() {
        this.V = null;
        this.T = null;
        this.U = null;
        this.S = null;
    }

    public void t1(boolean z2) {
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

    public void u(int i2, int i3) {
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
            b.h.m.t.a0(this);
        }
    }

    public boolean u0() {
        AccessibilityManager accessibilityManager = this.L;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void u1(int i2) {
        getScrollingChildHelper().r(i2);
    }

    public void v() {
        if (!this.E || this.N) {
            b.h.i.b.a("RV FullInvalidate");
            C();
            b.h.i.b.b();
            return;
        }
        if (this.n.p()) {
            if (!this.n.o(4) || this.n.o(11)) {
                if (this.n.p()) {
                    b.h.i.b.a("RV FullInvalidate");
                    C();
                    b.h.i.b.b();
                    return;
                }
                return;
            }
            b.h.i.b.a("RV PartialInvalidate");
            r1();
            H0();
            this.n.u();
            if (!this.G) {
                if (o0()) {
                    C();
                } else {
                    this.n.i();
                }
            }
            t1(true);
            I0();
            b.h.i.b.b();
        }
    }

    public boolean v0() {
        return this.P > 0;
    }

    public void v1() {
        setScrollState(0);
        w1();
    }

    public final void w(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
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
                    constructor = clsAsSubclass.getConstructor(f1173i);
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

    public final boolean w0(View view, View view2, int i2) {
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

    public final void w1() {
        this.l0.g();
        o oVar = this.w;
        if (oVar != null) {
            oVar.K1();
        }
    }

    public void x(int i2, int i3) {
        setMeasuredDimension(o.n(i2, getPaddingLeft() + getPaddingRight(), b.h.m.t.B(this)), o.n(i3, getPaddingTop() + getPaddingBottom(), b.h.m.t.A(this)));
    }

    public void x0(int i2) {
        if (this.w == null) {
            return;
        }
        setScrollState(2);
        this.w.x1(i2);
        awakenScrollBars();
    }

    public void x1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.o.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.o.i(i6);
            c0 c0VarH0 = h0(viewI);
            if (c0VarH0 != null && !c0VarH0.J() && (i4 = c0VarH0.f1187d) >= i2 && i4 < i5) {
                c0VarH0.b(2);
                c0VarH0.a(obj);
                ((p) viewI.getLayoutParams()).f1226c = true;
            }
        }
        this.l.M(i2, i3);
    }

    public final boolean y(int i2, int i3) {
        V(this.x0);
        int[] iArr = this.x0;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    public void y0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((p) this.o.i(i2).getLayoutParams()).f1226c = true;
        }
        this.l.s();
    }

    public void z(View view) {
        c0 c0VarH0 = h0(view);
        F0(view);
        g gVar = this.v;
        if (gVar != null && c0VarH0 != null) {
            gVar.q(c0VarH0);
        }
        List<q> list = this.M;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.M.get(size).a(view);
            }
        }
    }

    public void z0() {
        int iJ = this.o.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarH0 = h0(this.o.i(i2));
            if (c0VarH0 != null && !c0VarH0.J()) {
                c0VarH0.b(6);
            }
        }
        y0();
        this.l.t();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.r.a.recyclerViewStyle);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = new x();
        this.l = new v();
        this.p = new b.r.d.q();
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
        this.W = new b.r.d.c();
        this.X = 0;
        this.Y = -1;
        this.i0 = Float.MIN_VALUE;
        this.j0 = Float.MIN_VALUE;
        boolean z2 = true;
        this.k0 = true;
        this.l0 = new b0();
        this.n0 = f1170f ? new e.b() : null;
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
        this.i0 = b.h.m.u.b(viewConfiguration, context);
        this.j0 = b.h.m.u.d(viewConfiguration, context);
        this.g0 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.h0 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.W.v(this.t0);
        p0();
        r0();
        q0();
        if (b.h.m.t.x(this) == 0) {
            b.h.m.t.u0(this, 1);
        }
        this.L = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new b.r.d.l(this));
        int[] iArr = b.r.c.RecyclerView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(b.r.c.RecyclerView_layoutManager);
        if (typedArrayObtainStyledAttributes.getInt(b.r.c.RecyclerView_android_descendantFocusability, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.q = typedArrayObtainStyledAttributes.getBoolean(b.r.c.RecyclerView_android_clipToPadding, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(b.r.c.RecyclerView_fastScrollEnabled, false);
        this.D = z3;
        if (z3) {
            s0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.r.c.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(b.r.c.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.r.c.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes.getDrawable(b.r.c.RecyclerView_fastScrollHorizontalTrackDrawable));
        }
        typedArrayObtainStyledAttributes.recycle();
        w(context, string, attributeSet, i2, 0);
        if (i3 >= 21) {
            int[] iArr2 = f1166b;
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Parcelable f1174d;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1174d = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        public void k(SavedState savedState) {
            this.f1174d = savedState.f1174d;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.f1174d, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c0 f1224a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f1225b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1226c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1227d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1225b = new Rect();
            this.f1226c = true;
            this.f1227d = false;
        }

        public int a() {
            return this.f1224a.m();
        }

        public boolean b() {
            return this.f1224a.y();
        }

        public boolean c() {
            return this.f1224a.v();
        }

        public boolean d() {
            return this.f1224a.t();
        }

        public p(int i2, int i3) {
            super(i2, i3);
            this.f1225b = new Rect();
            this.f1226c = true;
            this.f1227d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f1225b = new Rect();
            this.f1226c = true;
            this.f1227d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1225b = new Rect();
            this.f1226c = true;
            this.f1227d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f1225b = new Rect();
            this.f1226c = true;
            this.f1227d = false;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.w;
        if (oVar != null) {
            return oVar.F(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + Q());
    }
}
