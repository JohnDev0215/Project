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
import b.f.m.b0.c;
import b.n.d.a;
import b.n.d.b;
import b.n.d.e;
import b.n.d.i;
import b.n.d.l;
import b.n.d.m;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements b.f.m.j {
    public static final boolean E3;
    public static final boolean F3;
    public static final Class<?>[] G3;
    public static final Interpolator H3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f573c = {R.attr.nestedScrollingEnabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f574d;
    public static final boolean s;
    public static final boolean x;
    public static final boolean y;
    public int A4;
    public int B4;
    public int C4;
    public r D4;
    public final int E4;
    public final int F4;
    public float G4;
    public float H4;
    public final x I3;
    public boolean I4;
    public final v J3;
    public final b0 J4;
    public SavedState K3;
    public b.n.d.e K4;
    public b.n.d.a L3;
    public e.b L4;
    public b.n.d.b M3;
    public final z M4;
    public final b.n.d.m N3;
    public t N4;
    public boolean O3;
    public List<t> O4;
    public final Runnable P3;
    public boolean P4;
    public final Rect Q3;
    public boolean Q4;
    public final Rect R3;
    public l.b R4;
    public final RectF S3;
    public boolean S4;
    public g T3;
    public b.n.d.i T4;
    public o U3;
    public j U4;
    public w V3;
    public final int[] V4;
    public final ArrayList<n> W3;
    public b.f.m.k W4;
    public final ArrayList<s> X3;
    public final int[] X4;
    public s Y3;
    public final int[] Y4;
    public boolean Z3;
    public final int[] Z4;
    public boolean a4;
    public final List<c0> a5;
    public boolean b4;
    public Runnable b5;
    public boolean c4;
    public final m.b c5;
    public int d4;
    public boolean e4;
    public boolean f4;
    public boolean g4;
    public int h4;
    public boolean i4;
    public final AccessibilityManager j4;
    public List<q> k4;
    public boolean l4;
    public boolean m4;
    public int n4;
    public int o4;
    public k p4;
    public EdgeEffect q4;
    public EdgeEffect r4;
    public EdgeEffect s4;
    public EdgeEffect t4;
    public l u4;
    public int v4;
    public int w4;
    public VelocityTracker x4;
    public int y4;
    public int z4;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.c4 || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.Z3) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.f4) {
                recyclerView2.e4 = true;
            } else {
                recyclerView2.t();
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
            l lVar = RecyclerView.this.u4;
            if (lVar != null) {
                lVar.u();
            }
            RecyclerView.this.S4 = false;
        }
    }

    public class b0 implements Runnable {
        public boolean E3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f577c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f578d;
        public OverScroller s;
        public Interpolator x;
        public boolean y;

        public b0() {
            Interpolator interpolator = RecyclerView.H3;
            this.x = interpolator;
            this.y = false;
            this.E3 = false;
            this.s = new OverScroller(RecyclerView.this.getContext(), interpolator);
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
            this.f578d = 0;
            this.f577c = 0;
            Interpolator interpolator = this.x;
            Interpolator interpolator2 = RecyclerView.H3;
            if (interpolator != interpolator2) {
                this.x = interpolator2;
                this.s = new OverScroller(RecyclerView.this.getContext(), interpolator2);
            }
            this.s.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            e();
        }

        public final void d() {
            RecyclerView.this.removeCallbacks(this);
            b.f.m.s.O(RecyclerView.this, this);
        }

        public void e() {
            if (this.y) {
                this.E3 = true;
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
                interpolator = RecyclerView.H3;
            }
            if (this.x != interpolator) {
                this.x = interpolator;
                this.s = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.f578d = 0;
            this.f577c = 0;
            RecyclerView.this.setScrollState(2);
            this.s.startScroll(0, 0, i2, i3, i5);
            if (Build.VERSION.SDK_INT < 23) {
                this.s.computeScrollOffset();
            }
            e();
        }

        public void g() {
            RecyclerView.this.removeCallbacks(this);
            this.s.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2;
            int i3;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.U3 == null) {
                g();
                return;
            }
            this.E3 = false;
            this.y = true;
            recyclerView.t();
            OverScroller overScroller = this.s;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i4 = currX - this.f577c;
                int i5 = currY - this.f578d;
                this.f577c = currX;
                this.f578d = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int[] iArr = recyclerView2.Z4;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView2.E(i4, i5, iArr, null, 1)) {
                    int[] iArr2 = RecyclerView.this.Z4;
                    i4 -= iArr2[0];
                    i5 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.s(i4, i5);
                }
                RecyclerView recyclerView3 = RecyclerView.this;
                if (recyclerView3.T3 != null) {
                    int[] iArr3 = recyclerView3.Z4;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView3.c1(i4, i5, iArr3);
                    RecyclerView recyclerView4 = RecyclerView.this;
                    int[] iArr4 = recyclerView4.Z4;
                    i3 = iArr4[0];
                    i2 = iArr4[1];
                    i4 -= i3;
                    i5 -= i2;
                    if (recyclerView4.U3.f610g != null) {
                        throw null;
                    }
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (!RecyclerView.this.W3.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                int[] iArr5 = recyclerView5.Z4;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView5.F(i3, i2, i4, i5, null, 1, iArr5);
                RecyclerView recyclerView6 = RecyclerView.this;
                int[] iArr6 = recyclerView6.Z4;
                int i6 = i4 - iArr6[0];
                int i7 = i5 - iArr6[1];
                if (i3 != 0 || i2 != 0) {
                    recyclerView6.H(i3, i2);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i6 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i7 != 0));
                RecyclerView recyclerView7 = RecyclerView.this;
                if (recyclerView7.U3.f610g != null) {
                    throw null;
                }
                if (z) {
                    if (recyclerView7.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i8 = i6 < 0 ? -currVelocity : i6 > 0 ? currVelocity : 0;
                        if (i7 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i7 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView.this.a(i8, currVelocity);
                    }
                    if (RecyclerView.y) {
                        RecyclerView.this.L4.b();
                    }
                } else {
                    e();
                    RecyclerView recyclerView8 = RecyclerView.this;
                    b.n.d.e eVar = recyclerView8.K4;
                    if (eVar != null) {
                        eVar.f(recyclerView8, i3, i2);
                    }
                }
            }
            RecyclerView recyclerView9 = RecyclerView.this;
            if (recyclerView9.U3.f610g != null) {
                throw null;
            }
            this.y = false;
            if (this.E3) {
                d();
            } else {
                recyclerView9.setScrollState(0);
                RecyclerView.this.n1(1);
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
        public static final List<Object> f579a = Collections.emptyList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public WeakReference<RecyclerView> f581c;
        public int k;
        public RecyclerView s;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f582d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f583e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f584f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f585g = -1;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f586h = -1;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public c0 f587i = null;
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
            this.f580b = view;
        }

        public void A(int i2, boolean z) {
            if (this.f583e == -1) {
                this.f583e = this.f582d;
            }
            if (this.f586h == -1) {
                this.f586h = this.f582d;
            }
            if (z) {
                this.f586h += i2;
            }
            this.f582d += i2;
            if (this.f580b.getLayoutParams() != null) {
                ((p) this.f580b.getLayoutParams()).f621c = true;
            }
        }

        public void B(RecyclerView recyclerView) {
            int i2 = this.r;
            if (i2 != -1) {
                this.q = i2;
            } else {
                this.q = b.f.m.s.t(this.f580b);
            }
            recyclerView.e1(this, 4);
        }

        public void C(RecyclerView recyclerView) {
            recyclerView.e1(this, this.q);
            this.q = 0;
        }

        public void D() {
            this.k = 0;
            this.f582d = -1;
            this.f583e = -1;
            this.f584f = -1L;
            this.f586h = -1;
            this.n = 0;
            this.f587i = null;
            this.j = null;
            d();
            this.q = 0;
            this.r = -1;
            RecyclerView.q(this);
        }

        public void E() {
            if (this.f583e == -1) {
                this.f583e = this.f582d;
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
            this.f583e = -1;
            this.f586h = -1;
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
            return (this.k & 16) == 0 && b.f.m.s.D(this.f580b);
        }

        public void i(int i2, int i3, boolean z) {
            b(8);
            A(i3, z);
            this.f582d = i2;
        }

        public final int j() {
            RecyclerView recyclerView = this.s;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.a0(this);
        }

        public final long k() {
            return this.f584f;
        }

        public final int l() {
            return this.f585g;
        }

        public final int m() {
            int i2 = this.f586h;
            return i2 == -1 ? this.f582d : i2;
        }

        public final int n() {
            return this.f583e;
        }

        public List<Object> o() {
            if ((this.k & 1024) != 0) {
                return f579a;
            }
            List<Object> list = this.l;
            return (list == null || list.size() == 0) ? f579a : this.m;
        }

        public boolean p(int i2) {
            return (i2 & this.k) != 0;
        }

        public boolean q() {
            return (this.k & 512) != 0 || t();
        }

        public boolean r() {
            return (this.f580b.getParent() == null || this.f580b.getParent() == this.s) ? false : true;
        }

        public boolean s() {
            return (this.k & 1) != 0;
        }

        public boolean t() {
            return (this.k & 4) != 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName()) + "{" + Integer.toHexString(hashCode()) + " position=" + this.f582d + " id=" + this.f584f + ", oldPos=" + this.f583e + ", pLpos:" + this.f586h);
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
            if (this.f580b.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean u() {
            return (this.k & 16) == 0 && !b.f.m.s.D(this.f580b);
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

    public class d implements m.b {
        public d() {
        }

        @Override // b.n.d.m.b
        public void a(c0 c0Var) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.U3.j1(c0Var.f580b, recyclerView.J3);
        }

        @Override // b.n.d.m.b
        public void b(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.k(c0Var, cVar, cVar2);
        }

        @Override // b.n.d.m.b
        public void c(c0 c0Var, l.c cVar, l.c cVar2) {
            RecyclerView.this.J3.J(c0Var);
            RecyclerView.this.m(c0Var, cVar, cVar2);
        }

        @Override // b.n.d.m.b
        public void d(c0 c0Var, l.c cVar, l.c cVar2) {
            c0Var.G(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.l4) {
                if (recyclerView.u4.b(c0Var, c0Var, cVar, cVar2)) {
                    RecyclerView.this.I0();
                }
            } else if (recyclerView.u4.d(c0Var, cVar, cVar2)) {
                RecyclerView.this.I0();
            }
        }
    }

    public class e implements b.InterfaceC0039b {
        public e() {
        }

        @Override // b.n.d.b.InterfaceC0039b
        public View a(int i2) {
            return RecyclerView.this.getChildAt(i2);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void b(View view) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0 != null) {
                c0VarD0.B(RecyclerView.this);
            }
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void c(int i2) {
            View childAt = RecyclerView.this.getChildAt(i2);
            if (childAt != null) {
                RecyclerView.this.y(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i2);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void d() {
            int iK = k();
            for (int i2 = 0; i2 < iK; i2++) {
                View viewA = a(i2);
                RecyclerView.this.y(viewA);
                viewA.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // b.n.d.b.InterfaceC0039b
        public c0 e(View view) {
            return RecyclerView.d0(view);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void f(int i2) {
            c0 c0VarD0;
            View viewA = a(i2);
            if (viewA != null && (c0VarD0 = RecyclerView.d0(viewA)) != null) {
                if (c0VarD0.x() && !c0VarD0.J()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + c0VarD0 + RecyclerView.this.O());
                }
                c0VarD0.b(256);
            }
            RecyclerView.this.detachViewFromParent(i2);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void g(View view) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0 != null) {
                c0VarD0.C(RecyclerView.this);
            }
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void h(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0 != null) {
                if (!c0VarD0.x() && !c0VarD0.J()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + c0VarD0 + RecyclerView.this.O());
                }
                c0VarD0.f();
            }
            RecyclerView.this.attachViewToParent(view, i2, layoutParams);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public void i(View view, int i2) {
            RecyclerView.this.addView(view, i2);
            RecyclerView.this.x(view);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public int j(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // b.n.d.b.InterfaceC0039b
        public int k() {
            return RecyclerView.this.getChildCount();
        }
    }

    public class f implements a.InterfaceC0038a {
        public f() {
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void a(int i2, int i3) {
            RecyclerView.this.y0(i2, i3);
            RecyclerView.this.P4 = true;
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void b(a.b bVar) {
            i(bVar);
        }

        @Override // b.n.d.a.InterfaceC0038a
        public c0 c(int i2) {
            c0 c0VarY = RecyclerView.this.Y(i2, true);
            if (c0VarY == null || RecyclerView.this.M3.n(c0VarY.f580b)) {
                return null;
            }
            return c0VarY;
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void d(int i2, int i3) {
            RecyclerView.this.z0(i2, i3, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.P4 = true;
            recyclerView.M4.f642d += i3;
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void e(int i2, int i3) {
            RecyclerView.this.z0(i2, i3, false);
            RecyclerView.this.P4 = true;
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void f(a.b bVar) {
            i(bVar);
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void g(int i2, int i3) {
            RecyclerView.this.x0(i2, i3);
            RecyclerView.this.P4 = true;
        }

        @Override // b.n.d.a.InterfaceC0038a
        public void h(int i2, int i3, Object obj) {
            RecyclerView.this.q1(i2, i3, obj);
            RecyclerView.this.Q4 = true;
        }

        public void i(a.b bVar) {
            int i2 = bVar.f2016a;
            if (i2 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.U3.P0(recyclerView, bVar.f2017b, bVar.f2019d);
                return;
            }
            if (i2 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.U3.S0(recyclerView2, bVar.f2017b, bVar.f2019d);
            } else if (i2 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.U3.U0(recyclerView3, bVar.f2017b, bVar.f2019d, bVar.f2018c);
            } else {
                if (i2 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.U3.R0(recyclerView4, bVar.f2017b, bVar.f2019d, 1);
            }
        }
    }

    public static abstract class g<VH extends c0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h f591a = new h();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f592b = false;

        public final void a(VH vh, int i2) {
            vh.f582d = i2;
            if (f()) {
                vh.f584f = d(i2);
            }
            vh.F(1, 519);
            b.f.i.a.a("RV OnBindView");
            j(vh, i2, vh.o());
            vh.d();
            ViewGroup.LayoutParams layoutParams = vh.f580b.getLayoutParams();
            if (layoutParams instanceof p) {
                ((p) layoutParams).f621c = true;
            }
            b.f.i.a.b();
        }

        public final VH b(ViewGroup viewGroup, int i2) {
            try {
                b.f.i.a.a("RV CreateView");
                VH vh = (VH) k(viewGroup, i2);
                if (vh.f580b.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.f585g = i2;
                return vh;
            } finally {
                b.f.i.a.b();
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
            return this.f592b;
        }

        public final void g() {
            this.f591a.a();
        }

        public void h(RecyclerView recyclerView) {
        }

        public abstract void i(VH vh, int i2);

        public void j(VH vh, int i2, List<Object> list) {
            i(vh, i2);
        }

        public abstract VH k(ViewGroup viewGroup, int i2);

        public void l(RecyclerView recyclerView) {
        }

        public boolean m(VH vh) {
            return false;
        }

        public void n(VH vh) {
        }

        public void o(VH vh) {
        }

        public void p(VH vh) {
        }

        public void q(i iVar) {
            this.f591a.registerObserver(iVar);
        }

        public void r(i iVar) {
            this.f591a.unregisterObserver(iVar);
        }
    }

    public static class h extends Observable<i> {
        public void a() {
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
        public EdgeEffect a(RecyclerView recyclerView, int i2) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    public static abstract class l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b f593a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f594b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f595c = 120;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f596d = 120;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f597e = 250;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f598f = 250;

        public interface a {
            void a();
        }

        public interface b {
            void a(c0 c0Var);
        }

        public static class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f599a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f600b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f601c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f602d;

            public c a(c0 c0Var) {
                return b(c0Var, 0);
            }

            public c b(c0 c0Var, int i2) {
                View view = c0Var.f580b;
                this.f599a = view.getLeft();
                this.f600b = view.getTop();
                this.f601c = view.getRight();
                this.f602d = view.getBottom();
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
            b bVar = this.f593a;
            if (bVar != null) {
                bVar.a(c0Var);
            }
        }

        public final void i() {
            int size = this.f594b.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f594b.get(i2).a();
            }
            this.f594b.clear();
        }

        public abstract void j(c0 c0Var);

        public abstract void k();

        public long l() {
            return this.f595c;
        }

        public long m() {
            return this.f598f;
        }

        public long n() {
            return this.f597e;
        }

        public long o() {
            return this.f596d;
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
            this.f593a = bVar;
        }
    }

    public class m implements l.b {
        public m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l.b
        public void a(c0 c0Var) {
            c0Var.G(true);
            if (c0Var.f587i != null && c0Var.j == null) {
                c0Var.f587i = null;
            }
            c0Var.j = null;
            if (c0Var.I() || RecyclerView.this.R0(c0Var.f580b) || !c0Var.x()) {
                return;
            }
            RecyclerView.this.removeDetachedView(c0Var.f580b, false);
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
        public b.n.d.b f604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView f605b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final l.b f606c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final l.b f607d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.n.d.l f608e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.n.d.l f609f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public y f610g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f611h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f612i;
        public boolean j;
        public boolean k;
        public boolean l;
        public int m;
        public boolean n;
        public int o;
        public int p;
        public int q;
        public int r;

        public class a implements l.b {
            public a() {
            }

            @Override // b.n.d.l.b
            public View a(int i2) {
                return o.this.H(i2);
            }

            @Override // b.n.d.l.b
            public int b() {
                return o.this.m0() - o.this.d0();
            }

            @Override // b.n.d.l.b
            public int c() {
                return o.this.c0();
            }

            @Override // b.n.d.l.b
            public int d(View view) {
                return o.this.S(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).rightMargin;
            }

            @Override // b.n.d.l.b
            public int e(View view) {
                return o.this.P(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).leftMargin;
            }
        }

        public class b implements l.b {
            public b() {
            }

            @Override // b.n.d.l.b
            public View a(int i2) {
                return o.this.H(i2);
            }

            @Override // b.n.d.l.b
            public int b() {
                return o.this.V() - o.this.b0();
            }

            @Override // b.n.d.l.b
            public int c() {
                return o.this.e0();
            }

            @Override // b.n.d.l.b
            public int d(View view) {
                return o.this.N(view) + ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).bottomMargin;
            }

            @Override // b.n.d.l.b
            public int e(View view) {
                return o.this.T(view) - ((ViewGroup.MarginLayoutParams) ((p) view.getLayoutParams())).topMargin;
            }
        }

        public interface c {
            void a(int i2, int i3);
        }

        public static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f615a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f616b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public boolean f617c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f618d;
        }

        public o() {
            a aVar = new a();
            this.f606c = aVar;
            b bVar = new b();
            this.f607d = bVar;
            this.f608e = new b.n.d.l(aVar);
            this.f609f = new b.n.d.l(bVar);
            this.f611h = false;
            this.f612i = false;
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
        public static int J(int r4, int r5, int r6, int r7, boolean r8) {
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.o.J(int, int, int, int, boolean):int");
        }

        public static d g0(Context context, AttributeSet attributeSet, int i2, int i3) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.n.c.f2004f, i2, i3);
            dVar.f615a = typedArrayObtainStyledAttributes.getInt(b.n.c.f2005g, 1);
            dVar.f616b = typedArrayObtainStyledAttributes.getInt(b.n.c.q, 1);
            dVar.f617c = typedArrayObtainStyledAttributes.getBoolean(b.n.c.p, false);
            dVar.f618d = typedArrayObtainStyledAttributes.getBoolean(b.n.c.r, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        public static int m(int i2, int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i3, i4) : size : Math.min(size, Math.max(i3, i4));
        }

        public static boolean u0(int i2, int i3, int i4) {
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

        public View A(View view) {
            View viewQ;
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null || (viewQ = recyclerView.Q(view)) == null || this.f604a.n(viewQ)) {
                return null;
            }
            return viewQ;
        }

        public void A0(int i2) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                recyclerView.v0(i2);
            }
        }

        public void A1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f605b = null;
                this.f604a = null;
                this.q = 0;
                this.r = 0;
            } else {
                this.f605b = recyclerView;
                this.f604a = recyclerView.M3;
                this.q = recyclerView.getWidth();
                this.r = recyclerView.getHeight();
            }
            this.o = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            this.p = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
        }

        public View B(int i2) {
            int I = I();
            for (int i3 = 0; i3 < I; i3++) {
                View viewH = H(i3);
                c0 c0VarD0 = RecyclerView.d0(viewH);
                if (c0VarD0 != null && c0VarD0.m() == i2 && !c0VarD0.J() && (this.f605b.M4.e() || !c0VarD0.v())) {
                    return viewH;
                }
            }
            return null;
        }

        public void B0(int i2) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                recyclerView.w0(i2);
            }
        }

        public boolean B1(View view, int i2, int i3, p pVar) {
            return (!view.isLayoutRequested() && this.k && u0(view.getWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && u0(view.getHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public abstract p C();

        public void C0(g gVar, g gVar2) {
        }

        public boolean C1() {
            return false;
        }

        public p D(Context context, AttributeSet attributeSet) {
            return new p(context, attributeSet);
        }

        public boolean D0(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i3) {
            return false;
        }

        public boolean D1(View view, int i2, int i3, p pVar) {
            return (this.k && u0(view.getMeasuredWidth(), i2, ((ViewGroup.MarginLayoutParams) pVar).width) && u0(view.getMeasuredHeight(), i3, ((ViewGroup.MarginLayoutParams) pVar).height)) ? false : true;
        }

        public p E(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof p ? new p((p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new p((ViewGroup.MarginLayoutParams) layoutParams) : new p(layoutParams);
        }

        public void E0(RecyclerView recyclerView) {
        }

        public void E1() {
            if (this.f610g != null) {
                throw null;
            }
        }

        public int F() {
            return -1;
        }

        @Deprecated
        public void F0(RecyclerView recyclerView) {
        }

        public boolean F1() {
            return false;
        }

        public int G(View view) {
            return ((p) view.getLayoutParams()).f620b.bottom;
        }

        public void G0(RecyclerView recyclerView, v vVar) {
            F0(recyclerView);
        }

        public View H(int i2) {
            b.n.d.b bVar = this.f604a;
            if (bVar != null) {
                return bVar.f(i2);
            }
            return null;
        }

        public View H0(View view, int i2, v vVar, z zVar) {
            return null;
        }

        public int I() {
            b.n.d.b bVar = this.f604a;
            if (bVar != null) {
                return bVar.g();
            }
            return 0;
        }

        public void I0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f605b;
            J0(recyclerView.J3, recyclerView.M4, accessibilityEvent);
        }

        public void J0(v vVar, z zVar, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.f605b.canScrollVertically(-1) && !this.f605b.canScrollHorizontally(-1) && !this.f605b.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            g gVar = this.f605b.T3;
            if (gVar != null) {
                accessibilityEvent.setItemCount(gVar.c());
            }
        }

        public final int[] K(View view, Rect rect) {
            int[] iArr = new int[2];
            int iC0 = c0();
            int iE0 = e0();
            int iM0 = m0() - d0();
            int iV = V() - b0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i2 = left - iC0;
            int iMin = Math.min(0, i2);
            int i3 = top - iE0;
            int iMin2 = Math.min(0, i3);
            int i4 = iWidth - iM0;
            int iMax = Math.max(0, i4);
            int iMax2 = Math.max(0, iHeight - iV);
            if (X() != 1) {
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

        public void K0(b.f.m.b0.c cVar) {
            RecyclerView recyclerView = this.f605b;
            L0(recyclerView.J3, recyclerView.M4, cVar);
        }

        public boolean L() {
            RecyclerView recyclerView = this.f605b;
            return recyclerView != null && recyclerView.O3;
        }

        public void L0(v vVar, z zVar, b.f.m.b0.c cVar) {
            if (this.f605b.canScrollVertically(-1) || this.f605b.canScrollHorizontally(-1)) {
                cVar.a(8192);
                cVar.h0(true);
            }
            if (this.f605b.canScrollVertically(1) || this.f605b.canScrollHorizontally(1)) {
                cVar.a(4096);
                cVar.h0(true);
            }
            cVar.V(c.b.a(i0(vVar, zVar), M(vVar, zVar), t0(vVar, zVar), j0(vVar, zVar)));
        }

        public int M(v vVar, z zVar) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null || recyclerView.T3 == null || !j()) {
                return 1;
            }
            return this.f605b.T3.c();
        }

        public void M0(View view, b.f.m.b0.c cVar) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0 == null || c0VarD0.v() || this.f604a.n(c0VarD0.f580b)) {
                return;
            }
            RecyclerView recyclerView = this.f605b;
            N0(recyclerView.J3, recyclerView.M4, view, cVar);
        }

        public int N(View view) {
            return view.getBottom() + G(view);
        }

        public void N0(v vVar, z zVar, View view, b.f.m.b0.c cVar) {
            cVar.W(c.C0030c.a(k() ? f0(view) : 0, 1, j() ? f0(view) : 0, 1, false, false));
        }

        public void O(View view, Rect rect) {
            RecyclerView.e0(view, rect);
        }

        public View O0(View view, int i2) {
            return null;
        }

        public int P(View view) {
            return view.getLeft() - Y(view);
        }

        public void P0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int Q(View view) {
            Rect rect = ((p) view.getLayoutParams()).f620b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public void Q0(RecyclerView recyclerView) {
        }

        public int R(View view) {
            Rect rect = ((p) view.getLayoutParams()).f620b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public void R0(RecyclerView recyclerView, int i2, int i3, int i4) {
        }

        public int S(View view) {
            return view.getRight() + h0(view);
        }

        public void S0(RecyclerView recyclerView, int i2, int i3) {
        }

        public int T(View view) {
            return view.getTop() - k0(view);
        }

        public void T0(RecyclerView recyclerView, int i2, int i3) {
        }

        public View U() {
            View focusedChild;
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f604a.n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        public void U0(RecyclerView recyclerView, int i2, int i3, Object obj) {
            T0(recyclerView, i2, i3);
        }

        public int V() {
            return this.r;
        }

        public void V0(v vVar, z zVar) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public int W() {
            return this.p;
        }

        public void W0(z zVar) {
        }

        public int X() {
            return b.f.m.s.v(this.f605b);
        }

        public void X0(v vVar, z zVar, int i2, int i3) {
            this.f605b.v(i2, i3);
        }

        public int Y(View view) {
            return ((p) view.getLayoutParams()).f620b.left;
        }

        @Deprecated
        public boolean Y0(RecyclerView recyclerView, View view, View view2) {
            return v0() || recyclerView.r0();
        }

        public int Z() {
            return b.f.m.s.w(this.f605b);
        }

        public boolean Z0(RecyclerView recyclerView, z zVar, View view, View view2) {
            return Y0(recyclerView, view, view2);
        }

        public void a(View view) {
            b(view, -1);
        }

        public int a0() {
            return b.f.m.s.x(this.f605b);
        }

        public void a1(Parcelable parcelable) {
        }

        public void b(View view, int i2) {
            e(view, i2, true);
        }

        public int b0() {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public Parcelable b1() {
            return null;
        }

        public void c(View view) {
            d(view, -1);
        }

        public int c0() {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public void c1(int i2) {
        }

        public void d(View view, int i2) {
            e(view, i2, false);
        }

        public int d0() {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public boolean d1(int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f605b;
            return e1(recyclerView.J3, recyclerView.M4, i2, bundle);
        }

        public final void e(View view, int i2, boolean z) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (z || c0VarD0.v()) {
                this.f605b.N3.b(c0VarD0);
            } else {
                this.f605b.N3.p(c0VarD0);
            }
            p pVar = (p) view.getLayoutParams();
            if (c0VarD0.L() || c0VarD0.w()) {
                if (c0VarD0.w()) {
                    c0VarD0.K();
                } else {
                    c0VarD0.e();
                }
                this.f604a.c(view, i2, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f605b) {
                int iM = this.f604a.m(view);
                if (i2 == -1) {
                    i2 = this.f604a.g();
                }
                if (iM == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f605b.indexOfChild(view) + this.f605b.O());
                }
                if (iM != i2) {
                    this.f605b.U3.z0(iM, i2);
                }
            } else {
                this.f604a.a(view, i2, false);
                pVar.f621c = true;
                if (this.f610g != null) {
                    throw null;
                }
            }
            if (pVar.f622d) {
                c0VarD0.f580b.invalidate();
                pVar.f622d = false;
            }
        }

        public int e0() {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public boolean e1(v vVar, z zVar, int i2, Bundle bundle) {
            int iV;
            int iM0;
            int i3;
            int i4;
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null) {
                return false;
            }
            if (i2 == 4096) {
                iV = recyclerView.canScrollVertically(1) ? (V() - e0()) - b0() : 0;
                if (this.f605b.canScrollHorizontally(1)) {
                    iM0 = (m0() - c0()) - d0();
                    i3 = iV;
                    i4 = iM0;
                }
                i3 = iV;
                i4 = 0;
            } else if (i2 != 8192) {
                i4 = 0;
                i3 = 0;
            } else {
                iV = recyclerView.canScrollVertically(-1) ? -((V() - e0()) - b0()) : 0;
                if (this.f605b.canScrollHorizontally(-1)) {
                    iM0 = -((m0() - c0()) - d0());
                    i3 = iV;
                    i4 = iM0;
                }
                i3 = iV;
                i4 = 0;
            }
            if (i3 == 0 && i4 == 0) {
                return false;
            }
            this.f605b.j1(i4, i3, null, Integer.MIN_VALUE, true);
            return true;
        }

        public void f(String str) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                recyclerView.n(str);
            }
        }

        public int f0(View view) {
            return ((p) view.getLayoutParams()).a();
        }

        public boolean f1(View view, int i2, Bundle bundle) {
            RecyclerView recyclerView = this.f605b;
            return g1(recyclerView.J3, recyclerView.M4, view, i2, bundle);
        }

        public void g(View view, int i2) {
            h(view, i2, (p) view.getLayoutParams());
        }

        public boolean g1(v vVar, z zVar, View view, int i2, Bundle bundle) {
            return false;
        }

        public void h(View view, int i2, p pVar) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0.v()) {
                this.f605b.N3.b(c0VarD0);
            } else {
                this.f605b.N3.p(c0VarD0);
            }
            this.f604a.c(view, i2, pVar, c0VarD0.v());
        }

        public int h0(View view) {
            return ((p) view.getLayoutParams()).f620b.right;
        }

        public void h1(v vVar) {
            for (int I = I() - 1; I >= 0; I--) {
                if (!RecyclerView.d0(H(I)).J()) {
                    k1(I, vVar);
                }
            }
        }

        public void i(View view, Rect rect) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.h0(view));
            }
        }

        public int i0(v vVar, z zVar) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView == null || recyclerView.T3 == null || !k()) {
                return 1;
            }
            return this.f605b.T3.c();
        }

        public void i1(v vVar) {
            int iJ = vVar.j();
            for (int i2 = iJ - 1; i2 >= 0; i2--) {
                View viewN = vVar.n(i2);
                c0 c0VarD0 = RecyclerView.d0(viewN);
                if (!c0VarD0.J()) {
                    c0VarD0.G(false);
                    if (c0VarD0.x()) {
                        this.f605b.removeDetachedView(viewN, false);
                    }
                    l lVar = this.f605b.u4;
                    if (lVar != null) {
                        lVar.j(c0VarD0);
                    }
                    c0VarD0.G(true);
                    vVar.y(viewN);
                }
            }
            vVar.e();
            if (iJ > 0) {
                this.f605b.invalidate();
            }
        }

        public boolean j() {
            return false;
        }

        public int j0(v vVar, z zVar) {
            return 0;
        }

        public void j1(View view, v vVar) {
            m1(view);
            vVar.B(view);
        }

        public boolean k() {
            return false;
        }

        public int k0(View view) {
            return ((p) view.getLayoutParams()).f620b.top;
        }

        public void k1(int i2, v vVar) {
            View viewH = H(i2);
            n1(i2);
            vVar.B(viewH);
        }

        public boolean l(p pVar) {
            return pVar != null;
        }

        public void l0(View view, boolean z, Rect rect) {
            Matrix matrix;
            if (z) {
                Rect rect2 = ((p) view.getLayoutParams()).f620b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f605b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f605b.S3;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean l1(Runnable runnable) {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        public int m0() {
            return this.q;
        }

        public void m1(View view) {
            this.f604a.p(view);
        }

        public void n(int i2, int i3, z zVar, c cVar) {
        }

        public int n0() {
            return this.o;
        }

        public void n1(int i2) {
            if (H(i2) != null) {
                this.f604a.q(i2);
            }
        }

        public void o(int i2, c cVar) {
        }

        public boolean o0() {
            int I = I();
            for (int i2 = 0; i2 < I; i2++) {
                ViewGroup.LayoutParams layoutParams = H(i2).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean o1(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return p1(recyclerView, view, rect, z, false);
        }

        public int p(z zVar) {
            return 0;
        }

        public boolean p0() {
            return this.f612i;
        }

        public boolean p1(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
            int[] iArrK = K(view, rect);
            int i2 = iArrK[0];
            int i3 = iArrK[1];
            if ((z2 && !r0(recyclerView, i2, i3)) || (i2 == 0 && i3 == 0)) {
                return false;
            }
            if (z) {
                recyclerView.scrollBy(i2, i3);
            } else {
                recyclerView.g1(i2, i3);
            }
            return true;
        }

        public int q(z zVar) {
            return 0;
        }

        public boolean q0() {
            return this.j;
        }

        public void q1() {
            RecyclerView recyclerView = this.f605b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public int r(z zVar) {
            return 0;
        }

        public final boolean r0(RecyclerView recyclerView, int i2, int i3) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iC0 = c0();
            int iE0 = e0();
            int iM0 = m0() - d0();
            int iV = V() - b0();
            Rect rect = this.f605b.Q3;
            O(focusedChild, rect);
            return rect.left - i2 < iM0 && rect.right - i2 > iC0 && rect.top - i3 < iV && rect.bottom - i3 > iE0;
        }

        public void r1() {
            this.f611h = true;
        }

        public int s(z zVar) {
            return 0;
        }

        public final boolean s0() {
            return this.l;
        }

        public final void s1(v vVar, int i2, View view) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0.J()) {
                return;
            }
            if (c0VarD0.t() && !c0VarD0.v() && !this.f605b.T3.f()) {
                n1(i2);
                vVar.C(c0VarD0);
            } else {
                w(i2);
                vVar.D(view);
                this.f605b.N3.k(c0VarD0);
            }
        }

        public int t(z zVar) {
            return 0;
        }

        public boolean t0(v vVar, z zVar) {
            return false;
        }

        public int t1(int i2, v vVar, z zVar) {
            return 0;
        }

        public int u(z zVar) {
            return 0;
        }

        public int u1(int i2, v vVar, z zVar) {
            return 0;
        }

        public void v(v vVar) {
            for (int I = I() - 1; I >= 0; I--) {
                s1(vVar, I, H(I));
            }
        }

        public boolean v0() {
            if (this.f610g == null) {
                return false;
            }
            throw null;
        }

        public void v1(RecyclerView recyclerView) {
            w1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        }

        public void w(int i2) {
            x(i2, H(i2));
        }

        public boolean w0(View view, boolean z, boolean z2) {
            boolean z3 = this.f608e.b(view, 24579) && this.f609f.b(view, 24579);
            return z ? z3 : !z3;
        }

        public void w1(int i2, int i3) {
            this.q = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            this.o = mode;
            if (mode == 0 && !RecyclerView.s) {
                this.q = 0;
            }
            this.r = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.p = mode2;
            if (mode2 != 0 || RecyclerView.s) {
                return;
            }
            this.r = 0;
        }

        public final void x(int i2, View view) {
            this.f604a.d(i2);
        }

        public void x0(View view, int i2, int i3, int i4, int i5) {
            p pVar = (p) view.getLayoutParams();
            Rect rect = pVar.f620b;
            view.layout(i2 + rect.left + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i3 + rect.top + ((ViewGroup.MarginLayoutParams) pVar).topMargin, (i4 - rect.right) - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 - rect.bottom) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
        }

        public void x1(int i2, int i3) {
            this.f605b.setMeasuredDimension(i2, i3);
        }

        public void y(RecyclerView recyclerView) {
            this.f612i = true;
            E0(recyclerView);
        }

        public void y0(View view, int i2, int i3) {
            p pVar = (p) view.getLayoutParams();
            Rect rectH0 = this.f605b.h0(view);
            int i4 = i2 + rectH0.left + rectH0.right;
            int i5 = i3 + rectH0.top + rectH0.bottom;
            int iJ = J(m0(), n0(), c0() + d0() + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i4, ((ViewGroup.MarginLayoutParams) pVar).width, j());
            int iJ2 = J(V(), W(), e0() + b0() + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin + i5, ((ViewGroup.MarginLayoutParams) pVar).height, k());
            if (B1(view, iJ, iJ2, pVar)) {
                view.measure(iJ, iJ2);
            }
        }

        public void y1(Rect rect, int i2, int i3) {
            x1(m(i2, rect.width() + c0() + d0(), a0()), m(i3, rect.height() + e0() + b0(), Z()));
        }

        public void z(RecyclerView recyclerView, v vVar) {
            this.f612i = false;
            G0(recyclerView, vVar);
        }

        public void z0(int i2, int i3) {
            View viewH = H(i2);
            if (viewH != null) {
                w(i2);
                g(viewH, i3);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i2 + this.f605b.toString());
            }
        }

        public void z1(int i2, int i3) {
            int I = I();
            if (I == 0) {
                this.f605b.v(i2, i3);
                return;
            }
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = 0; i8 < I; i8++) {
                View viewH = H(i8);
                Rect rect = this.f605b.Q3;
                O(viewH, rect);
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
            this.f605b.Q3.set(i5, i6, i4, i7);
            y1(this.f605b.Q3, i2, i3);
        }
    }

    public interface q {
        void a(View view);

        void b(View view);
    }

    public static abstract class r {
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
        public SparseArray<a> f623a = new SparseArray<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f624b = 0;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final ArrayList<c0> f625a = new ArrayList<>();

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f626b = 5;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public long f627c = 0;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public long f628d = 0;
        }

        public void a() {
            this.f624b++;
        }

        public void b() {
            for (int i2 = 0; i2 < this.f623a.size(); i2++) {
                this.f623a.valueAt(i2).f625a.clear();
            }
        }

        public void c() {
            this.f624b--;
        }

        public void d(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f628d = j(aVarG.f628d, j);
        }

        public void e(int i2, long j) {
            a aVarG = g(i2);
            aVarG.f627c = j(aVarG.f627c, j);
        }

        public c0 f(int i2) {
            a aVar = this.f623a.get(i2);
            if (aVar == null || aVar.f625a.isEmpty()) {
                return null;
            }
            ArrayList<c0> arrayList = aVar.f625a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!arrayList.get(size).r()) {
                    return arrayList.remove(size);
                }
            }
            return null;
        }

        public final a g(int i2) {
            a aVar = this.f623a.get(i2);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f623a.put(i2, aVar2);
            return aVar2;
        }

        public void h(g gVar, g gVar2, boolean z) {
            if (gVar != null) {
                c();
            }
            if (!z && this.f624b == 0) {
                b();
            }
            if (gVar2 != null) {
                a();
            }
        }

        public void i(c0 c0Var) {
            int iL = c0Var.l();
            ArrayList<c0> arrayList = g(iL).f625a;
            if (this.f623a.get(iL).f626b <= arrayList.size()) {
                return;
            }
            c0Var.D();
            arrayList.add(c0Var);
        }

        public long j(long j, long j2) {
            return j == 0 ? j2 : ((j / 4) * 3) + (j2 / 4);
        }

        public boolean k(int i2, long j, long j2) {
            long j3 = g(i2).f628d;
            return j3 == 0 || j + j3 < j2;
        }

        public boolean l(int i2, long j, long j2) {
            long j3 = g(i2).f627c;
            return j3 == 0 || j + j3 < j2;
        }
    }

    public final class v {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayList<c0> f629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<c0> f630b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<c0> f631c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<c0> f632d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f633e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f634f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public u f635g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public a0 f636h;

        public v() {
            ArrayList<c0> arrayList = new ArrayList<>();
            this.f629a = arrayList;
            this.f630b = null;
            this.f631c = new ArrayList<>();
            this.f632d = Collections.unmodifiableList(arrayList);
            this.f633e = 2;
            this.f634f = 2;
        }

        public void A(int i2) {
            a(this.f631c.get(i2), true);
            this.f631c.remove(i2);
        }

        public void B(View view) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (c0VarD0.x()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (c0VarD0.w()) {
                c0VarD0.K();
            } else if (c0VarD0.L()) {
                c0VarD0.e();
            }
            C(c0VarD0);
            if (RecyclerView.this.u4 == null || c0VarD0.u()) {
                return;
            }
            RecyclerView.this.u4.j(c0VarD0);
        }

        public void C(c0 c0Var) {
            boolean z;
            boolean z2 = true;
            if (c0Var.w() || c0Var.f580b.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(c0Var.w());
                sb.append(" isAttached:");
                sb.append(c0Var.f580b.getParent() != null);
                sb.append(RecyclerView.this.O());
                throw new IllegalArgumentException(sb.toString());
            }
            if (c0Var.x()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + c0Var + RecyclerView.this.O());
            }
            if (c0Var.J()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.O());
            }
            boolean zH = c0Var.h();
            g gVar = RecyclerView.this.T3;
            if ((gVar != null && zH && gVar.m(c0Var)) || c0Var.u()) {
                if (this.f634f <= 0 || c0Var.p(526)) {
                    z = false;
                } else {
                    int size = this.f631c.size();
                    if (size >= this.f634f && size > 0) {
                        A(0);
                        size--;
                    }
                    if (RecyclerView.y && size > 0 && !RecyclerView.this.L4.d(c0Var.f582d)) {
                        int i2 = size - 1;
                        while (i2 >= 0) {
                            if (!RecyclerView.this.L4.d(this.f631c.get(i2).f582d)) {
                                break;
                            } else {
                                i2--;
                            }
                        }
                        size = i2 + 1;
                    }
                    this.f631c.add(size, c0Var);
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
            RecyclerView.this.N3.q(c0Var);
            if (z || z2 || !zH) {
                return;
            }
            c0Var.s = null;
        }

        public void D(View view) {
            c0 c0VarD0 = RecyclerView.d0(view);
            if (!c0VarD0.p(12) && c0VarD0.y() && !RecyclerView.this.o(c0VarD0)) {
                if (this.f630b == null) {
                    this.f630b = new ArrayList<>();
                }
                c0VarD0.H(this, true);
                this.f630b.add(c0VarD0);
                return;
            }
            if (!c0VarD0.t() || c0VarD0.v() || RecyclerView.this.T3.f()) {
                c0VarD0.H(this, false);
                this.f629a.add(c0VarD0);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.O());
            }
        }

        public void E(u uVar) {
            u uVar2 = this.f635g;
            if (uVar2 != null) {
                uVar2.c();
            }
            this.f635g = uVar;
            if (uVar == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f635g.a();
        }

        public void F(a0 a0Var) {
        }

        public void G(int i2) {
            this.f633e = i2;
            K();
        }

        public final boolean H(c0 c0Var, int i2, int i3, long j) {
            c0Var.s = RecyclerView.this;
            int iL = c0Var.l();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j != Long.MAX_VALUE && !this.f635g.k(iL, nanoTime, j)) {
                return false;
            }
            RecyclerView.this.T3.a(c0Var, i2);
            this.f635g.d(c0Var.l(), RecyclerView.this.getNanoTime() - nanoTime);
            b(c0Var);
            if (!RecyclerView.this.M4.e()) {
                return true;
            }
            c0Var.f586h = i3;
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
                this.f630b.remove(c0Var);
            } else {
                this.f629a.remove(c0Var);
            }
            c0Var.o = null;
            c0Var.p = false;
            c0Var.e();
        }

        public void K() {
            o oVar = RecyclerView.this.U3;
            this.f634f = this.f633e + (oVar != null ? oVar.m : 0);
            for (int size = this.f631c.size() - 1; size >= 0 && this.f631c.size() > this.f634f; size--) {
                A(size);
            }
        }

        public boolean L(c0 c0Var) {
            if (c0Var.v()) {
                return RecyclerView.this.M4.e();
            }
            int i2 = c0Var.f582d;
            if (i2 >= 0 && i2 < RecyclerView.this.T3.c()) {
                if (RecyclerView.this.M4.e() || RecyclerView.this.T3.e(c0Var.f582d) == c0Var.l()) {
                    return !RecyclerView.this.T3.f() || c0Var.k() == RecyclerView.this.T3.d(c0Var.f582d);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + c0Var + RecyclerView.this.O());
        }

        public void M(int i2, int i3) {
            int i4;
            int i5 = i3 + i2;
            for (int size = this.f631c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f631c.get(size);
                if (c0Var != null && (i4 = c0Var.f582d) >= i2 && i4 < i5) {
                    c0Var.b(2);
                    A(size);
                }
            }
        }

        public void a(c0 c0Var, boolean z) {
            RecyclerView.q(c0Var);
            View view = c0Var.f580b;
            b.n.d.i iVar = RecyclerView.this.T4;
            if (iVar != null) {
                b.f.m.a aVarN = iVar.n();
                b.f.m.s.T(view, aVarN instanceof i.a ? ((i.a) aVarN).n(view) : null);
            }
            if (z) {
                g(c0Var);
            }
            c0Var.s = null;
            i().i(c0Var);
        }

        public final void b(c0 c0Var) {
            if (RecyclerView.this.q0()) {
                View view = c0Var.f580b;
                if (b.f.m.s.t(view) == 0) {
                    b.f.m.s.Y(view, 1);
                }
                b.n.d.i iVar = RecyclerView.this.T4;
                if (iVar == null) {
                    return;
                }
                b.f.m.a aVarN = iVar.n();
                if (aVarN instanceof i.a) {
                    ((i.a) aVarN).o(view);
                }
                b.f.m.s.T(view, aVarN);
            }
        }

        public void c() {
            this.f629a.clear();
            z();
        }

        public void d() {
            int size = this.f631c.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f631c.get(i2).c();
            }
            int size2 = this.f629a.size();
            for (int i3 = 0; i3 < size2; i3++) {
                this.f629a.get(i3).c();
            }
            ArrayList<c0> arrayList = this.f630b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    this.f630b.get(i4).c();
                }
            }
        }

        public void e() {
            this.f629a.clear();
            ArrayList<c0> arrayList = this.f630b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        public int f(int i2) {
            if (i2 >= 0 && i2 < RecyclerView.this.M4.b()) {
                return !RecyclerView.this.M4.e() ? i2 : RecyclerView.this.L3.m(i2);
            }
            throw new IndexOutOfBoundsException("invalid position " + i2 + ". State item count is " + RecyclerView.this.M4.b() + RecyclerView.this.O());
        }

        public void g(c0 c0Var) {
            w wVar = RecyclerView.this.V3;
            if (wVar != null) {
                wVar.a(c0Var);
            }
            g gVar = RecyclerView.this.T3;
            if (gVar != null) {
                gVar.p(c0Var);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.M4 != null) {
                recyclerView.N3.q(c0Var);
            }
        }

        public c0 h(int i2) {
            int size;
            int iM;
            ArrayList<c0> arrayList = this.f630b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    c0 c0Var = this.f630b.get(i3);
                    if (!c0Var.L() && c0Var.m() == i2) {
                        c0Var.b(32);
                        return c0Var;
                    }
                }
                if (RecyclerView.this.T3.f() && (iM = RecyclerView.this.L3.m(i2)) > 0 && iM < RecyclerView.this.T3.c()) {
                    long jD = RecyclerView.this.T3.d(iM);
                    for (int i4 = 0; i4 < size; i4++) {
                        c0 c0Var2 = this.f630b.get(i4);
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
            if (this.f635g == null) {
                this.f635g = new u();
            }
            return this.f635g;
        }

        public int j() {
            return this.f629a.size();
        }

        public List<c0> k() {
            return this.f632d;
        }

        public c0 l(long j, int i2, boolean z) {
            for (int size = this.f629a.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f629a.get(size);
                if (c0Var.k() == j && !c0Var.L()) {
                    if (i2 == c0Var.l()) {
                        c0Var.b(32);
                        if (c0Var.v() && !RecyclerView.this.M4.e()) {
                            c0Var.F(2, 14);
                        }
                        return c0Var;
                    }
                    if (!z) {
                        this.f629a.remove(size);
                        RecyclerView.this.removeDetachedView(c0Var.f580b, false);
                        y(c0Var.f580b);
                    }
                }
            }
            int size2 = this.f631c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                c0 c0Var2 = this.f631c.get(size2);
                if (c0Var2.k() == j && !c0Var2.r()) {
                    if (i2 == c0Var2.l()) {
                        if (!z) {
                            this.f631c.remove(size2);
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
            int size = this.f629a.size();
            for (int i3 = 0; i3 < size; i3++) {
                c0 c0Var = this.f629a.get(i3);
                if (!c0Var.L() && c0Var.m() == i2 && !c0Var.t() && (RecyclerView.this.M4.f646h || !c0Var.v())) {
                    c0Var.b(32);
                    return c0Var;
                }
            }
            if (z || (viewE = RecyclerView.this.M3.e(i2)) == null) {
                int size2 = this.f631c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    c0 c0Var2 = this.f631c.get(i4);
                    if (!c0Var2.t() && c0Var2.m() == i2 && !c0Var2.r()) {
                        if (!z) {
                            this.f631c.remove(i4);
                        }
                        return c0Var2;
                    }
                }
                return null;
            }
            c0 c0VarD0 = RecyclerView.d0(viewE);
            RecyclerView.this.M3.s(viewE);
            int iM = RecyclerView.this.M3.m(viewE);
            if (iM != -1) {
                RecyclerView.this.M3.d(iM);
                D(viewE);
                c0VarD0.b(8224);
                return c0VarD0;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + c0VarD0 + RecyclerView.this.O());
        }

        public View n(int i2) {
            return this.f629a.get(i2).f580b;
        }

        public View o(int i2) {
            return p(i2, false);
        }

        public View p(int i2, boolean z) {
            return I(i2, z, Long.MAX_VALUE).f580b;
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
            View view = c0Var.f580b;
            if (view instanceof ViewGroup) {
                q((ViewGroup) view, false);
            }
        }

        public void s() {
            int size = this.f631c.size();
            for (int i2 = 0; i2 < size; i2++) {
                p pVar = (p) this.f631c.get(i2).f580b.getLayoutParams();
                if (pVar != null) {
                    pVar.f621c = true;
                }
            }
        }

        public void t() {
            int size = this.f631c.size();
            for (int i2 = 0; i2 < size; i2++) {
                c0 c0Var = this.f631c.get(i2);
                if (c0Var != null) {
                    c0Var.b(6);
                    c0Var.a(null);
                }
            }
            g gVar = RecyclerView.this.T3;
            if (gVar == null || !gVar.f()) {
                z();
            }
        }

        public void u(int i2, int i3) {
            int size = this.f631c.size();
            for (int i4 = 0; i4 < size; i4++) {
                c0 c0Var = this.f631c.get(i4);
                if (c0Var != null && c0Var.f582d >= i2) {
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
            int size = this.f631c.size();
            for (int i8 = 0; i8 < size; i8++) {
                c0 c0Var = this.f631c.get(i8);
                if (c0Var != null && (i7 = c0Var.f582d) >= i6 && i7 <= i5) {
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
            for (int size = this.f631c.size() - 1; size >= 0; size--) {
                c0 c0Var = this.f631c.get(size);
                if (c0Var != null) {
                    int i5 = c0Var.f582d;
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
            c0 c0VarD0 = RecyclerView.d0(view);
            c0VarD0.o = null;
            c0VarD0.p = false;
            c0VarD0.e();
            C(c0VarD0);
        }

        public void z() {
            for (int size = this.f631c.size() - 1; size >= 0; size--) {
                A(size);
            }
            this.f631c.clear();
            if (RecyclerView.y) {
                RecyclerView.this.L4.b();
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
            RecyclerView.this.n(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.M4.f645g = true;
            recyclerView.L0(true);
            if (RecyclerView.this.L3.p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }
    }

    public static abstract class y {
    }

    public static class z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SparseArray<Object> f640b;
        public int m;
        public long n;
        public int o;
        public int p;
        public int q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f639a = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f641c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f642d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f643e = 1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f644f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f645g = false;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f646h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f647i = false;
        public boolean j = false;
        public boolean k = false;
        public boolean l = false;

        public void a(int i2) {
            if ((this.f643e & i2) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i2) + " but it is " + Integer.toBinaryString(this.f643e));
        }

        public int b() {
            return this.f646h ? this.f641c - this.f642d : this.f644f;
        }

        public int c() {
            return this.f639a;
        }

        public boolean d() {
            return this.f639a != -1;
        }

        public boolean e() {
            return this.f646h;
        }

        public void f(g gVar) {
            this.f643e = 1;
            this.f644f = gVar.c();
            this.f646h = false;
            this.f647i = false;
            this.j = false;
        }

        public boolean g() {
            return this.l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f639a + ", mData=" + this.f640b + ", mItemCount=" + this.f644f + ", mIsMeasuring=" + this.j + ", mPreviousLayoutItemCount=" + this.f641c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f642d + ", mStructureChanged=" + this.f645g + ", mInPreLayout=" + this.f646h + ", mRunSimpleAnimations=" + this.k + ", mRunPredictiveAnimations=" + this.l + '}';
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        f574d = i2 == 18 || i2 == 19 || i2 == 20;
        s = i2 >= 23;
        x = i2 >= 16;
        y = i2 >= 21;
        E3 = i2 <= 15;
        F3 = i2 <= 15;
        Class<?> cls = Integer.TYPE;
        G3 = new Class[]{Context.class, AttributeSet.class, cls, cls};
        H3 = new c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public static RecyclerView U(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerViewU = U(viewGroup.getChildAt(i2));
            if (recyclerViewU != null) {
                return recyclerViewU;
            }
        }
        return null;
    }

    public static c0 d0(View view) {
        if (view == null) {
            return null;
        }
        return ((p) view.getLayoutParams()).f619a;
    }

    public static void e0(View view, Rect rect) {
        p pVar = (p) view.getLayoutParams();
        Rect rect2 = pVar.f620b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) pVar).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) pVar).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
    }

    private b.f.m.k getScrollingChildHelper() {
        if (this.W4 == null) {
            this.W4 = new b.f.m.k(this);
        }
        return this.W4;
    }

    public static void q(c0 c0Var) {
        WeakReference<RecyclerView> weakReference = c0Var.f581c;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == c0Var.f580b) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            c0Var.f581c = null;
        }
    }

    public void A() {
        if (this.T3 == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (this.U3 == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        z zVar = this.M4;
        zVar.j = false;
        if (zVar.f643e == 1) {
            B();
            this.U3.v1(this);
            C();
        } else if (!this.L3.q() && this.U3.m0() == getWidth() && this.U3.V() == getHeight()) {
            this.U3.v1(this);
        } else {
            this.U3.v1(this);
            C();
        }
        D();
    }

    public void A0(View view) {
    }

    public final void B() {
        this.M4.a(1);
        P(this.M4);
        this.M4.j = false;
        k1();
        this.N3.f();
        C0();
        K0();
        Z0();
        z zVar = this.M4;
        zVar.f647i = zVar.k && this.Q4;
        this.Q4 = false;
        this.P4 = false;
        zVar.f646h = zVar.l;
        zVar.f644f = this.T3.c();
        T(this.V4);
        if (this.M4.k) {
            int iG = this.M3.g();
            for (int i2 = 0; i2 < iG; i2++) {
                c0 c0VarD0 = d0(this.M3.f(i2));
                if (!c0VarD0.J() && (!c0VarD0.t() || this.T3.f())) {
                    this.N3.e(c0VarD0, this.u4.t(this.M4, c0VarD0, l.e(c0VarD0), c0VarD0.o()));
                    if (this.M4.f647i && c0VarD0.y() && !c0VarD0.v() && !c0VarD0.J() && !c0VarD0.t()) {
                        this.N3.c(b0(c0VarD0), c0VarD0);
                    }
                }
            }
        }
        if (this.M4.l) {
            a1();
            z zVar2 = this.M4;
            boolean z2 = zVar2.f645g;
            zVar2.f645g = false;
            this.U3.V0(this.J3, zVar2);
            this.M4.f645g = z2;
            for (int i3 = 0; i3 < this.M3.g(); i3++) {
                c0 c0VarD02 = d0(this.M3.f(i3));
                if (!c0VarD02.J() && !this.N3.i(c0VarD02)) {
                    int iE = l.e(c0VarD02);
                    boolean zP = c0VarD02.p(8192);
                    if (!zP) {
                        iE |= 4096;
                    }
                    l.c cVarT = this.u4.t(this.M4, c0VarD02, iE, c0VarD02.o());
                    if (zP) {
                        N0(c0VarD02, cVarT);
                    } else {
                        this.N3.a(c0VarD02, cVarT);
                    }
                }
            }
            r();
        } else {
            r();
        }
        D0();
        m1(false);
        this.M4.f643e = 2;
    }

    public void B0(View view) {
    }

    public final void C() {
        k1();
        C0();
        this.M4.a(6);
        this.L3.j();
        this.M4.f644f = this.T3.c();
        z zVar = this.M4;
        zVar.f642d = 0;
        zVar.f646h = false;
        this.U3.V0(this.J3, zVar);
        z zVar2 = this.M4;
        zVar2.f645g = false;
        this.K3 = null;
        zVar2.k = zVar2.k && this.u4 != null;
        zVar2.f643e = 4;
        D0();
        m1(false);
    }

    public void C0() {
        this.n4++;
    }

    public final void D() {
        this.M4.a(4);
        k1();
        C0();
        z zVar = this.M4;
        zVar.f643e = 1;
        if (zVar.k) {
            for (int iG = this.M3.g() - 1; iG >= 0; iG--) {
                c0 c0VarD0 = d0(this.M3.f(iG));
                if (!c0VarD0.J()) {
                    long jB0 = b0(c0VarD0);
                    l.c cVarS = this.u4.s(this.M4, c0VarD0);
                    c0 c0VarG = this.N3.g(jB0);
                    if (c0VarG == null || c0VarG.J()) {
                        this.N3.d(c0VarD0, cVarS);
                    } else {
                        boolean zH = this.N3.h(c0VarG);
                        boolean zH2 = this.N3.h(c0VarD0);
                        if (zH && c0VarG == c0VarD0) {
                            this.N3.d(c0VarD0, cVarS);
                        } else {
                            l.c cVarN = this.N3.n(c0VarG);
                            this.N3.d(c0VarD0, cVarS);
                            l.c cVarM = this.N3.m(c0VarD0);
                            if (cVarN == null) {
                                i0(jB0, c0VarD0, c0VarG);
                            } else {
                                l(c0VarG, c0VarD0, cVarN, cVarM, zH, zH2);
                            }
                        }
                    }
                }
            }
            this.N3.o(this.c5);
        }
        this.U3.i1(this.J3);
        z zVar2 = this.M4;
        zVar2.f641c = zVar2.f644f;
        this.l4 = false;
        this.m4 = false;
        zVar2.k = false;
        zVar2.l = false;
        this.U3.f611h = false;
        ArrayList<c0> arrayList = this.J3.f630b;
        if (arrayList != null) {
            arrayList.clear();
        }
        o oVar = this.U3;
        if (oVar.n) {
            oVar.m = 0;
            oVar.n = false;
            this.J3.K();
        }
        this.U3.W0(this.M4);
        D0();
        m1(false);
        this.N3.f();
        int[] iArr = this.V4;
        if (w(iArr[0], iArr[1])) {
            H(0, 0);
        }
        O0();
        X0();
    }

    public void D0() {
        E0(true);
    }

    public boolean E(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return getScrollingChildHelper().d(i2, i3, iArr, iArr2, i4);
    }

    public void E0(boolean z2) {
        int i2 = this.n4 - 1;
        this.n4 = i2;
        if (i2 < 1) {
            this.n4 = 0;
            if (z2) {
                z();
                I();
            }
        }
    }

    public final void F(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        getScrollingChildHelper().e(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public final void F0(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.w4) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.w4 = motionEvent.getPointerId(i2);
            int x2 = (int) (motionEvent.getX(i2) + 0.5f);
            this.A4 = x2;
            this.y4 = x2;
            int y2 = (int) (motionEvent.getY(i2) + 0.5f);
            this.B4 = y2;
            this.z4 = y2;
        }
    }

    public void G(int i2) {
        o oVar = this.U3;
        if (oVar != null) {
            oVar.c1(i2);
        }
        G0(i2);
        t tVar = this.N4;
        if (tVar != null) {
            tVar.a(this, i2);
        }
        List<t> list = this.O4;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.O4.get(size).a(this, i2);
            }
        }
    }

    public void G0(int i2) {
    }

    public void H(int i2, int i3) {
        this.o4++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i2, scrollY - i3);
        H0(i2, i3);
        t tVar = this.N4;
        if (tVar != null) {
            tVar.b(this, i2, i3);
        }
        List<t> list = this.O4;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.O4.get(size).b(this, i2, i3);
            }
        }
        this.o4--;
    }

    public void H0(int i2, int i3) {
    }

    public void I() {
        int i2;
        for (int size = this.a5.size() - 1; size >= 0; size--) {
            c0 c0Var = this.a5.get(size);
            if (c0Var.f580b.getParent() == this && !c0Var.J() && (i2 = c0Var.r) != -1) {
                b.f.m.s.Y(c0Var.f580b, i2);
                c0Var.r = -1;
            }
        }
        this.a5.clear();
    }

    public void I0() {
        if (this.S4 || !this.Z3) {
            return;
        }
        b.f.m.s.O(this, this.b5);
        this.S4 = true;
    }

    public final boolean J(MotionEvent motionEvent) {
        s sVar = this.Y3;
        if (sVar == null) {
            if (motionEvent.getAction() == 0) {
                return false;
            }
            return S(motionEvent);
        }
        sVar.b(this, motionEvent);
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.Y3 = null;
        }
        return true;
    }

    public final boolean J0() {
        return this.u4 != null && this.U3.F1();
    }

    public void K() {
        if (this.t4 != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.p4.a(this, 3);
        this.t4 = edgeEffectA;
        if (this.O3) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void K0() {
        boolean z2;
        if (this.l4) {
            this.L3.u();
            if (this.m4) {
                this.U3.Q0(this);
            }
        }
        if (J0()) {
            this.L3.s();
        } else {
            this.L3.j();
        }
        boolean z3 = false;
        boolean z4 = this.P4 || this.Q4;
        this.M4.k = this.c4 && this.u4 != null && ((z2 = this.l4) || z4 || this.U3.f611h) && (!z2 || this.T3.f());
        z zVar = this.M4;
        if (zVar.k && z4 && !this.l4 && J0()) {
            z3 = true;
        }
        zVar.l = z3;
    }

    public void L() {
        if (this.q4 != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.p4.a(this, 0);
        this.q4 = edgeEffectA;
        if (this.O3) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void L0(boolean z2) {
        this.m4 = z2 | this.m4;
        this.l4 = true;
        u0();
    }

    public void M() {
        if (this.s4 != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.p4.a(this, 2);
        this.s4 = edgeEffectA;
        if (this.O3) {
            edgeEffectA.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectA.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void M0(float r7, float r8, float r9, float r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 1
            if (r1 >= 0) goto L21
            r6.L()
            android.widget.EdgeEffect r1 = r6.q4
            float r4 = -r8
            int r5 = r6.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            float r9 = r2 - r9
            b.f.n.d.a(r1, r4, r9)
        L1f:
            r9 = r3
            goto L3c
        L21:
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r1 <= 0) goto L3b
            r6.M()
            android.widget.EdgeEffect r1 = r6.s4
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r4 = r8 / r4
            int r5 = r6.getHeight()
            float r5 = (float) r5
            float r9 = r9 / r5
            b.f.n.d.a(r1, r4, r9)
            goto L1f
        L3b:
            r9 = 0
        L3c:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L56
            r6.N()
            android.widget.EdgeEffect r9 = r6.r4
            float r1 = -r10
            int r2 = r6.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            int r2 = r6.getWidth()
            float r2 = (float) r2
            float r7 = r7 / r2
            b.f.n.d.a(r9, r1, r7)
            goto L72
        L56:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 <= 0) goto L71
            r6.K()
            android.widget.EdgeEffect r9 = r6.t4
            int r1 = r6.getHeight()
            float r1 = (float) r1
            float r1 = r10 / r1
            int r4 = r6.getWidth()
            float r4 = (float) r4
            float r7 = r7 / r4
            float r2 = r2 - r7
            b.f.n.d.a(r9, r1, r2)
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
            b.f.m.s.N(r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.M0(float, float, float, float):void");
    }

    public void N() {
        if (this.r4 != null) {
            return;
        }
        EdgeEffect edgeEffectA = this.p4.a(this, 1);
        this.r4 = edgeEffectA;
        if (this.O3) {
            edgeEffectA.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectA.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void N0(c0 c0Var, l.c cVar) {
        c0Var.F(0, 8192);
        if (this.M4.f647i && c0Var.y() && !c0Var.v() && !c0Var.J()) {
            this.N3.c(b0(c0Var), c0Var);
        }
        this.N3.e(c0Var, cVar);
    }

    public String O() {
        return " " + super.toString() + ", adapter:" + this.T3 + ", layout:" + this.U3 + ", context:" + getContext();
    }

    public final void O0() {
        View viewFindViewById;
        if (!this.I4 || this.T3 == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!F3 || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.M3.n(focusedChild)) {
                    return;
                }
            } else if (this.M3.g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewV = null;
        c0 c0VarX = (this.M4.n == -1 || !this.T3.f()) ? null : X(this.M4.n);
        if (c0VarX != null && !this.M3.n(c0VarX.f580b) && c0VarX.f580b.hasFocusable()) {
            viewV = c0VarX.f580b;
        } else if (this.M3.g() > 0) {
            viewV = V();
        }
        if (viewV != null) {
            int i2 = this.M4.o;
            if (i2 != -1 && (viewFindViewById = viewV.findViewById(i2)) != null && viewFindViewById.isFocusable()) {
                viewV = viewFindViewById;
            }
            viewV.requestFocus();
        }
    }

    public final void P(z zVar) {
        if (getScrollState() != 2) {
            zVar.p = 0;
            zVar.q = 0;
        } else {
            OverScroller overScroller = this.J4.s;
            zVar.p = overScroller.getFinalX() - overScroller.getCurrX();
            zVar.q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public final void P0() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.q4;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.q4.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.r4;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.r4.isFinished();
        }
        EdgeEffect edgeEffect3 = this.s4;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.s4.isFinished();
        }
        EdgeEffect edgeEffect4 = this.t4;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.t4.isFinished();
        }
        if (zIsFinished) {
            b.f.m.s.N(this);
        }
    }

    public View Q(View view) {
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

    public void Q0() {
        l lVar = this.u4;
        if (lVar != null) {
            lVar.k();
        }
        o oVar = this.U3;
        if (oVar != null) {
            oVar.h1(this.J3);
            this.U3.i1(this.J3);
        }
        this.J3.c();
    }

    public c0 R(View view) {
        View viewQ = Q(view);
        if (viewQ == null) {
            return null;
        }
        return c0(viewQ);
    }

    public boolean R0(View view) {
        k1();
        boolean zR = this.M3.r(view);
        if (zR) {
            c0 c0VarD0 = d0(view);
            this.J3.J(c0VarD0);
            this.J3.C(c0VarD0);
        }
        m1(!zR);
        return zR;
    }

    public final boolean S(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.X3.size();
        for (int i2 = 0; i2 < size; i2++) {
            s sVar = this.X3.get(i2);
            if (sVar.a(this, motionEvent) && action != 3) {
                this.Y3 = sVar;
                return true;
            }
        }
        return false;
    }

    public void S0(n nVar) {
        o oVar = this.U3;
        if (oVar != null) {
            oVar.f("Cannot remove item decoration during a scroll  or layout");
        }
        this.W3.remove(nVar);
        if (this.W3.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        t0();
        requestLayout();
    }

    public final void T(int[] iArr) {
        int iG = this.M3.g();
        if (iG == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (int i4 = 0; i4 < iG; i4++) {
            c0 c0VarD0 = d0(this.M3.f(i4));
            if (!c0VarD0.J()) {
                int iM = c0VarD0.m();
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

    public void T0(s sVar) {
        this.X3.remove(sVar);
        if (this.Y3 == sVar) {
            this.Y3 = null;
        }
    }

    public void U0(t tVar) {
        List<t> list = this.O4;
        if (list != null) {
            list.remove(tVar);
        }
    }

    public final View V() {
        c0 c0VarW;
        z zVar = this.M4;
        int i2 = zVar.m;
        if (i2 == -1) {
            i2 = 0;
        }
        int iB = zVar.b();
        for (int i3 = i2; i3 < iB; i3++) {
            c0 c0VarW2 = W(i3);
            if (c0VarW2 == null) {
                break;
            }
            if (c0VarW2.f580b.hasFocusable()) {
                return c0VarW2.f580b;
            }
        }
        int iMin = Math.min(iB, i2);
        do {
            iMin--;
            if (iMin < 0 || (c0VarW = W(iMin)) == null) {
                return null;
            }
        } while (!c0VarW.f580b.hasFocusable());
        return c0VarW.f580b;
    }

    public void V0() {
        c0 c0Var;
        int iG = this.M3.g();
        for (int i2 = 0; i2 < iG; i2++) {
            View viewF = this.M3.f(i2);
            c0 c0VarC0 = c0(viewF);
            if (c0VarC0 != null && (c0Var = c0VarC0.j) != null) {
                View view = c0Var.f580b;
                int left = viewF.getLeft();
                int top = viewF.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    public c0 W(int i2) {
        c0 c0Var = null;
        if (this.l4) {
            return null;
        }
        int iJ = this.M3.j();
        for (int i3 = 0; i3 < iJ; i3++) {
            c0 c0VarD0 = d0(this.M3.i(i3));
            if (c0VarD0 != null && !c0VarD0.v() && a0(c0VarD0) == i2) {
                if (!this.M3.n(c0VarD0.f580b)) {
                    return c0VarD0;
                }
                c0Var = c0VarD0;
            }
        }
        return c0Var;
    }

    public final void W0(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.Q3.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof p) {
            p pVar = (p) layoutParams;
            if (!pVar.f621c) {
                Rect rect = pVar.f620b;
                Rect rect2 = this.Q3;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.Q3);
            offsetRectIntoDescendantCoords(view, this.Q3);
        }
        this.U3.p1(this, view, this.Q3, !this.c4, view2 == null);
    }

    public c0 X(long j2) {
        g gVar = this.T3;
        c0 c0Var = null;
        if (gVar != null && gVar.f()) {
            int iJ = this.M3.j();
            for (int i2 = 0; i2 < iJ; i2++) {
                c0 c0VarD0 = d0(this.M3.i(i2));
                if (c0VarD0 != null && !c0VarD0.v() && c0VarD0.k() == j2) {
                    if (!this.M3.n(c0VarD0.f580b)) {
                        return c0VarD0;
                    }
                    c0Var = c0VarD0;
                }
            }
        }
        return c0Var;
    }

    public final void X0() {
        z zVar = this.M4;
        zVar.n = -1L;
        zVar.m = -1;
        zVar.o = -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.recyclerview.widget.RecyclerView.c0 Y(int r6, boolean r7) {
        /*
            r5 = this;
            b.n.d.b r0 = r5.M3
            int r0 = r0.j()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            b.n.d.b r3 = r5.M3
            android.view.View r3 = r3.i(r2)
            androidx.recyclerview.widget.RecyclerView$c0 r3 = d0(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.v()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.f582d
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.m()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            b.n.d.b r1 = r5.M3
            android.view.View r4 = r3.f580b
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Y(int, boolean):androidx.recyclerview.widget.RecyclerView$c0");
    }

    public final void Y0() {
        VelocityTracker velocityTracker = this.x4;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        n1(0);
        P0();
    }

    public boolean Z(int i2, int i3) {
        o oVar = this.U3;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.f4) {
            return false;
        }
        boolean zJ = oVar.j();
        boolean zK = this.U3.k();
        if (!zJ || Math.abs(i2) < this.E4) {
            i2 = 0;
        }
        if (!zK || Math.abs(i3) < this.E4) {
            i3 = 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        float f2 = i2;
        float f3 = i3;
        if (!dispatchNestedPreFling(f2, f3)) {
            boolean z2 = zJ || zK;
            dispatchNestedFling(f2, f3, z2);
            if (this.D4 != null) {
                throw null;
            }
            if (z2) {
                int i4 = zJ ? 1 : 0;
                if (zK) {
                    i4 |= 2;
                }
                l1(i4, 1);
                int i5 = this.F4;
                int iMax = Math.max(-i5, Math.min(i2, i5));
                int i6 = this.F4;
                this.J4.c(iMax, Math.max(-i6, Math.min(i3, i6)));
                return true;
            }
        }
        return false;
    }

    public final void Z0() {
        View focusedChild = (this.I4 && hasFocus() && this.T3 != null) ? getFocusedChild() : null;
        c0 c0VarR = focusedChild != null ? R(focusedChild) : null;
        if (c0VarR == null) {
            X0();
            return;
        }
        this.M4.n = this.T3.f() ? c0VarR.k() : -1L;
        this.M4.m = this.l4 ? -1 : c0VarR.v() ? c0VarR.f583e : c0VarR.j();
        this.M4.o = f0(c0VarR.f580b);
    }

    public void a(int i2, int i3) {
        if (i2 < 0) {
            L();
            if (this.q4.isFinished()) {
                this.q4.onAbsorb(-i2);
            }
        } else if (i2 > 0) {
            M();
            if (this.s4.isFinished()) {
                this.s4.onAbsorb(i2);
            }
        }
        if (i3 < 0) {
            N();
            if (this.r4.isFinished()) {
                this.r4.onAbsorb(-i3);
            }
        } else if (i3 > 0) {
            K();
            if (this.t4.isFinished()) {
                this.t4.onAbsorb(i3);
            }
        }
        if (i2 == 0 && i3 == 0) {
            return;
        }
        b.f.m.s.N(this);
    }

    public int a0(c0 c0Var) {
        if (c0Var.p(524) || !c0Var.s()) {
            return -1;
        }
        return this.L3.e(c0Var.f582d);
    }

    public void a1() {
        int iJ = this.M3.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarD0 = d0(this.M3.i(i2));
            if (!c0VarD0.J()) {
                c0VarD0.E();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        o oVar = this.U3;
        if (oVar == null || !oVar.D0(this, arrayList, i2, i3)) {
            super.addFocusables(arrayList, i2, i3);
        }
    }

    public long b0(c0 c0Var) {
        return this.T3.f() ? c0Var.k() : c0Var.f582d;
    }

    public boolean b1(int i2, int i3, MotionEvent motionEvent) {
        int i4;
        int i5;
        int i6;
        int i7;
        t();
        if (this.T3 != null) {
            int[] iArr = this.Z4;
            iArr[0] = 0;
            iArr[1] = 0;
            c1(i2, i3, iArr);
            int[] iArr2 = this.Z4;
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
        if (!this.W3.isEmpty()) {
            invalidate();
        }
        int[] iArr3 = this.Z4;
        iArr3[0] = 0;
        iArr3[1] = 0;
        F(i5, i4, i6, i7, this.X4, 0, iArr3);
        int[] iArr4 = this.Z4;
        int i10 = i6 - iArr4[0];
        int i11 = i7 - iArr4[1];
        boolean z2 = (iArr4[0] == 0 && iArr4[1] == 0) ? false : true;
        int i12 = this.A4;
        int[] iArr5 = this.X4;
        this.A4 = i12 - iArr5[0];
        this.B4 -= iArr5[1];
        int[] iArr6 = this.Y4;
        iArr6[0] = iArr6[0] + iArr5[0];
        iArr6[1] = iArr6[1] + iArr5[1];
        if (getOverScrollMode() != 2) {
            if (motionEvent != null && !b.f.m.i.a(motionEvent, 8194)) {
                M0(motionEvent.getX(), i10, motionEvent.getY(), i11);
            }
            s(i2, i3);
        }
        if (i5 != 0 || i4 != 0) {
            H(i5, i4);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (!z2 && i5 == 0 && i4 == 0) ? false : true;
    }

    public c0 c0(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return d0(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    public void c1(int i2, int i3, int[] iArr) {
        k1();
        C0();
        b.f.i.a.a("RV Scroll");
        P(this.M4);
        int iT1 = i2 != 0 ? this.U3.t1(i2, this.J3, this.M4) : 0;
        int iU1 = i3 != 0 ? this.U3.u1(i3, this.J3, this.M4) : 0;
        b.f.i.a.b();
        V0();
        D0();
        m1(false);
        if (iArr != null) {
            iArr[0] = iT1;
            iArr[1] = iU1;
        }
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof p) && this.U3.l((p) layoutParams);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        o oVar = this.U3;
        if (oVar != null && oVar.j()) {
            return this.U3.p(this.M4);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        o oVar = this.U3;
        if (oVar != null && oVar.j()) {
            return this.U3.q(this.M4);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        o oVar = this.U3;
        if (oVar != null && oVar.j()) {
            return this.U3.r(this.M4);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        o oVar = this.U3;
        if (oVar != null && oVar.k()) {
            return this.U3.s(this.M4);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        o oVar = this.U3;
        if (oVar != null && oVar.k()) {
            return this.U3.t(this.M4);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        o oVar = this.U3;
        if (oVar != null && oVar.k()) {
            return this.U3.u(this.M4);
        }
        return 0;
    }

    public final void d1(g gVar, boolean z2, boolean z3) {
        g gVar2 = this.T3;
        if (gVar2 != null) {
            gVar2.r(this.I3);
            this.T3.l(this);
        }
        if (!z2 || z3) {
            Q0();
        }
        this.L3.u();
        g gVar3 = this.T3;
        this.T3 = gVar;
        if (gVar != null) {
            gVar.q(this.I3);
            gVar.h(this);
        }
        o oVar = this.U3;
        if (oVar != null) {
            oVar.C0(gVar3, this.T3);
        }
        this.J3.x(gVar3, this.T3, z2);
        this.M4.f645g = true;
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
        int size = this.W3.size();
        boolean z3 = false;
        for (int i2 = 0; i2 < size; i2++) {
            this.W3.get(i2).i(canvas, this, this.M4);
        }
        EdgeEffect edgeEffect = this.q4;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z2 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.O3 ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.q4;
            z2 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.r4;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.O3) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.r4;
            z2 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.s4;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.O3 ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.s4;
            z2 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.t4;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.O3) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.t4;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z3 = true;
            }
            z2 |= z3;
            canvas.restoreToCount(iSave4);
        }
        if ((z2 || this.u4 == null || this.W3.size() <= 0 || !this.u4.p()) ? z2 : true) {
            b.f.m.s.N(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j2) {
        return super.drawChild(canvas, view, j2);
    }

    public boolean e1(c0 c0Var, int i2) {
        if (!r0()) {
            b.f.m.s.Y(c0Var.f580b, i2);
            return true;
        }
        c0Var.r = i2;
        this.a5.add(c0Var);
        return false;
    }

    public final void f(c0 c0Var) {
        View view = c0Var.f580b;
        boolean z2 = view.getParent() == this;
        this.J3.J(c0(view));
        if (c0Var.x()) {
            this.M3.c(view, -1, view.getLayoutParams(), true);
        } else if (z2) {
            this.M3.k(view);
        } else {
            this.M3.b(view, true);
        }
    }

    public final int f0(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    public boolean f1(AccessibilityEvent accessibilityEvent) {
        if (!r0()) {
            return false;
        }
        int iA = accessibilityEvent != null ? b.f.m.b0.b.a(accessibilityEvent) : 0;
        this.h4 |= iA != 0 ? iA : 0;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i2) {
        View viewH0;
        boolean z2;
        View viewO0 = this.U3.O0(view, i2);
        if (viewO0 != null) {
            return viewO0;
        }
        boolean z3 = (this.T3 == null || this.U3 == null || r0() || this.f4) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z3 && (i2 == 2 || i2 == 1)) {
            if (this.U3.k()) {
                int i3 = i2 == 2 ? 130 : 33;
                z2 = focusFinder.findNextFocus(this, view, i3) == null;
                if (E3) {
                    i2 = i3;
                }
            } else {
                z2 = false;
            }
            if (!z2 && this.U3.j()) {
                int i4 = (this.U3.X() == 1) ^ (i2 == 2) ? 66 : 17;
                boolean z4 = focusFinder.findNextFocus(this, view, i4) == null;
                if (E3) {
                    i2 = i4;
                }
                z2 = z4;
            }
            if (z2) {
                t();
                if (Q(view) == null) {
                    return null;
                }
                k1();
                this.U3.H0(view, i2, this.J3, this.M4);
                m1(false);
            }
            viewH0 = focusFinder.findNextFocus(this, view, i2);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i2);
            if (viewFindNextFocus == null && z3) {
                t();
                if (Q(view) == null) {
                    return null;
                }
                k1();
                viewH0 = this.U3.H0(view, i2, this.J3, this.M4);
                m1(false);
            } else {
                viewH0 = viewFindNextFocus;
            }
        }
        if (viewH0 == null || viewH0.hasFocusable()) {
            return s0(view, viewH0, i2) ? viewH0 : super.focusSearch(view, i2);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i2);
        }
        W0(viewH0, null);
        return view;
    }

    public void g(n nVar) {
        h(nVar, -1);
    }

    public final String g0(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + '.' + str;
    }

    public void g1(int i2, int i3) {
        h1(i2, i3, null);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        o oVar = this.U3;
        if (oVar != null) {
            return oVar.C();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        o oVar = this.U3;
        if (oVar != null) {
            return oVar.D(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public g getAdapter() {
        return this.T3;
    }

    @Override // android.view.View
    public int getBaseline() {
        o oVar = this.U3;
        return oVar != null ? oVar.F() : super.getBaseline();
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        j jVar = this.U4;
        return jVar == null ? super.getChildDrawingOrder(i2, i3) : jVar.a(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.O3;
    }

    public b.n.d.i getCompatAccessibilityDelegate() {
        return this.T4;
    }

    public k getEdgeEffectFactory() {
        return this.p4;
    }

    public l getItemAnimator() {
        return this.u4;
    }

    public int getItemDecorationCount() {
        return this.W3.size();
    }

    public o getLayoutManager() {
        return this.U3;
    }

    public int getMaxFlingVelocity() {
        return this.F4;
    }

    public int getMinFlingVelocity() {
        return this.E4;
    }

    public long getNanoTime() {
        if (y) {
            return System.nanoTime();
        }
        return 0L;
    }

    public r getOnFlingListener() {
        return this.D4;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.I4;
    }

    public u getRecycledViewPool() {
        return this.J3.i();
    }

    public int getScrollState() {
        return this.v4;
    }

    public void h(n nVar, int i2) {
        o oVar = this.U3;
        if (oVar != null) {
            oVar.f("Cannot add item decoration during a scroll  or layout");
        }
        if (this.W3.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i2 < 0) {
            this.W3.add(nVar);
        } else {
            this.W3.add(i2, nVar);
        }
        t0();
        requestLayout();
    }

    public Rect h0(View view) {
        p pVar = (p) view.getLayoutParams();
        if (!pVar.f621c) {
            return pVar.f620b;
        }
        if (this.M4.e() && (pVar.b() || pVar.d())) {
            return pVar.f620b;
        }
        Rect rect = pVar.f620b;
        rect.set(0, 0, 0, 0);
        int size = this.W3.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.Q3.set(0, 0, 0, 0);
            this.W3.get(i2).e(this.Q3, view, this, this.M4);
            int i3 = rect.left;
            Rect rect2 = this.Q3;
            rect.left = i3 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        pVar.f621c = false;
        return rect;
    }

    public void h1(int i2, int i3, Interpolator interpolator) {
        i1(i2, i3, interpolator, Integer.MIN_VALUE);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().j();
    }

    public void i(s sVar) {
        this.X3.add(sVar);
    }

    public final void i0(long j2, c0 c0Var, c0 c0Var2) {
        int iG = this.M3.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarD0 = d0(this.M3.f(i2));
            if (c0VarD0 != c0Var && b0(c0VarD0) == j2) {
                g gVar = this.T3;
                if (gVar == null || !gVar.f()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + c0VarD0 + " \n View Holder 2:" + c0Var + O());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + c0VarD0 + " \n View Holder 2:" + c0Var + O());
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + c0Var2 + " cannot be found but it is necessary for " + c0Var + O());
    }

    public void i1(int i2, int i3, Interpolator interpolator, int i4) {
        j1(i2, i3, interpolator, i4, false);
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.Z3;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.f4;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().l();
    }

    public void j(t tVar) {
        if (this.O4 == null) {
            this.O4 = new ArrayList();
        }
        this.O4.add(tVar);
    }

    public boolean j0() {
        return !this.c4 || this.l4 || this.L3.p();
    }

    public void j1(int i2, int i3, Interpolator interpolator, int i4, boolean z2) {
        o oVar = this.U3;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f4) {
            return;
        }
        if (!oVar.j()) {
            i2 = 0;
        }
        if (!this.U3.k()) {
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
            l1(i5, 1);
        }
        this.J4.f(i2, i3, i4, interpolator);
    }

    public void k(c0 c0Var, l.c cVar, l.c cVar2) {
        c0Var.G(false);
        if (this.u4.a(c0Var, cVar, cVar2)) {
            I0();
        }
    }

    public final boolean k0() {
        int iG = this.M3.g();
        for (int i2 = 0; i2 < iG; i2++) {
            c0 c0VarD0 = d0(this.M3.f(i2));
            if (c0VarD0 != null && !c0VarD0.J() && c0VarD0.y()) {
                return true;
            }
        }
        return false;
    }

    public void k1() {
        int i2 = this.d4 + 1;
        this.d4 = i2;
        if (i2 != 1 || this.f4) {
            return;
        }
        this.e4 = false;
    }

    public final void l(c0 c0Var, c0 c0Var2, l.c cVar, l.c cVar2, boolean z2, boolean z3) {
        c0Var.G(false);
        if (z2) {
            f(c0Var);
        }
        if (c0Var != c0Var2) {
            if (z3) {
                f(c0Var2);
            }
            c0Var.f587i = c0Var2;
            f(c0Var);
            this.J3.J(c0Var);
            c0Var2.G(false);
            c0Var2.j = c0Var;
        }
        if (this.u4.b(c0Var, c0Var2, cVar, cVar2)) {
            I0();
        }
    }

    public void l0() {
        this.L3 = new b.n.d.a(new f());
    }

    public boolean l1(int i2, int i3) {
        return getScrollingChildHelper().p(i2, i3);
    }

    public void m(c0 c0Var, l.c cVar, l.c cVar2) {
        f(c0Var);
        c0Var.G(false);
        if (this.u4.c(c0Var, cVar, cVar2)) {
            I0();
        }
    }

    @SuppressLint({"InlinedApi"})
    public final void m0() {
        if (b.f.m.s.u(this) == 0) {
            b.f.m.s.Z(this, 8);
        }
    }

    public void m1(boolean z2) {
        if (this.d4 < 1) {
            this.d4 = 1;
        }
        if (!z2 && !this.f4) {
            this.e4 = false;
        }
        if (this.d4 == 1) {
            if (z2 && this.e4 && !this.f4 && this.U3 != null && this.T3 != null) {
                A();
            }
            if (!this.f4) {
                this.e4 = false;
            }
        }
        this.d4--;
    }

    public void n(String str) {
        if (r0()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + O());
        }
        if (this.o4 > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + O()));
        }
    }

    public final void n0() {
        this.M3 = new b.n.d.b(new e());
    }

    public void n1(int i2) {
        getScrollingChildHelper().r(i2);
    }

    public boolean o(c0 c0Var) {
        l lVar = this.u4;
        return lVar == null || lVar.g(c0Var, c0Var.o());
    }

    public void o0(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new b.n.d.d(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(b.n.b.f1996a), resources.getDimensionPixelSize(b.n.b.f1998c), resources.getDimensionPixelOffset(b.n.b.f1997b));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + O());
        }
    }

    public void o1() {
        setScrollState(0);
        p1();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.n4 = 0;
        this.Z3 = true;
        this.c4 = this.c4 && !isLayoutRequested();
        o oVar = this.U3;
        if (oVar != null) {
            oVar.y(this);
        }
        this.S4 = false;
        if (y) {
            ThreadLocal<b.n.d.e> threadLocal = b.n.d.e.f2080c;
            b.n.d.e eVar = threadLocal.get();
            this.K4 = eVar;
            if (eVar == null) {
                this.K4 = new b.n.d.e();
                Display displayP = b.f.m.s.p(this);
                float f2 = 60.0f;
                if (!isInEditMode() && displayP != null) {
                    float refreshRate = displayP.getRefreshRate();
                    if (refreshRate >= 30.0f) {
                        f2 = refreshRate;
                    }
                }
                b.n.d.e eVar2 = this.K4;
                eVar2.y = (long) (1.0E9f / f2);
                threadLocal.set(eVar2);
            }
            this.K4.a(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        b.n.d.e eVar;
        super.onDetachedFromWindow();
        l lVar = this.u4;
        if (lVar != null) {
            lVar.k();
        }
        o1();
        this.Z3 = false;
        o oVar = this.U3;
        if (oVar != null) {
            oVar.z(this, this.J3);
        }
        this.a5.clear();
        removeCallbacks(this.b5);
        this.N3.j();
        if (!y || (eVar = this.K4) == null) {
            return;
        }
        eVar.j(this);
        this.K4 = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.W3.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.W3.get(i2).g(canvas, this, this.M4);
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.U3
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r5.f4
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
            androidx.recyclerview.widget.RecyclerView$o r0 = r5.U3
            boolean r0 = r0.k()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r6.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r2
        L2d:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.U3
            boolean r3 = r3.j()
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
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.U3
            boolean r3 = r3.k()
            if (r3 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.RecyclerView$o r3 = r5.U3
            boolean r3 = r3.j()
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
            float r2 = r5.G4
            float r3 = r3 * r2
            int r2 = (int) r3
            float r3 = r5.H4
            float r0 = r0 * r3
            int r0 = (int) r0
            r5.b1(r2, r0, r6)
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        if (this.f4) {
            return false;
        }
        this.Y3 = null;
        if (S(motionEvent)) {
            p();
            return true;
        }
        o oVar = this.U3;
        if (oVar == null) {
            return false;
        }
        boolean zJ = oVar.j();
        boolean zK = this.U3.k();
        if (this.x4 == null) {
            this.x4 = VelocityTracker.obtain();
        }
        this.x4.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.g4) {
                this.g4 = false;
            }
            this.w4 = motionEvent.getPointerId(0);
            int x2 = (int) (motionEvent.getX() + 0.5f);
            this.A4 = x2;
            this.y4 = x2;
            int y2 = (int) (motionEvent.getY() + 0.5f);
            this.B4 = y2;
            this.z4 = y2;
            if (this.v4 == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                n1(1);
            }
            int[] iArr = this.Y4;
            iArr[1] = 0;
            iArr[0] = 0;
            int i2 = zJ;
            if (zK) {
                i2 = (zJ ? 1 : 0) | 2;
            }
            l1(i2, 0);
        } else if (actionMasked == 1) {
            this.x4.clear();
            n1(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.w4);
            if (iFindPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.w4 + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x3 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y3 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.v4 != 1) {
                int i3 = x3 - this.y4;
                int i4 = y3 - this.z4;
                if (!zJ || Math.abs(i3) <= this.C4) {
                    z2 = false;
                } else {
                    this.A4 = x3;
                    z2 = true;
                }
                if (zK && Math.abs(i4) > this.C4) {
                    this.B4 = y3;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            p();
        } else if (actionMasked == 5) {
            this.w4 = motionEvent.getPointerId(actionIndex);
            int x4 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.A4 = x4;
            this.y4 = x4;
            int y4 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.B4 = y4;
            this.z4 = y4;
        } else if (actionMasked == 6) {
            F0(motionEvent);
        }
        return this.v4 == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        b.f.i.a.a("RV OnLayout");
        A();
        b.f.i.a.b();
        this.c4 = true;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        o oVar = this.U3;
        if (oVar == null) {
            v(i2, i3);
            return;
        }
        boolean z2 = false;
        if (oVar.q0()) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.U3.X0(this.J3, this.M4, i2, i3);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z2 = true;
            }
            if (z2 || this.T3 == null) {
                return;
            }
            if (this.M4.f643e == 1) {
                B();
            }
            this.U3.w1(i2, i3);
            this.M4.j = true;
            C();
            this.U3.z1(i2, i3);
            if (this.U3.C1()) {
                this.U3.w1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.M4.j = true;
                C();
                this.U3.z1(i2, i3);
                return;
            }
            return;
        }
        if (this.a4) {
            this.U3.X0(this.J3, this.M4, i2, i3);
            return;
        }
        if (this.i4) {
            k1();
            C0();
            K0();
            D0();
            z zVar = this.M4;
            if (zVar.l) {
                zVar.f646h = true;
            } else {
                this.L3.j();
                this.M4.f646h = false;
            }
            this.i4 = false;
            m1(false);
        } else if (this.M4.l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        g gVar = this.T3;
        if (gVar != null) {
            this.M4.f644f = gVar.c();
        } else {
            this.M4.f644f = 0;
        }
        k1();
        this.U3.X0(this.J3, this.M4, i2, i3);
        m1(false);
        this.M4.f646h = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (r0()) {
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
        this.K3 = savedState;
        super.onRestoreInstanceState(savedState.a());
        o oVar = this.U3;
        if (oVar == null || (parcelable2 = this.K3.s) == null) {
            return;
        }
        oVar.a1(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.K3;
        if (savedState2 != null) {
            savedState.b(savedState2);
        } else {
            o oVar = this.U3;
            if (oVar != null) {
                savedState.s = oVar.b1();
            } else {
                savedState.s = null;
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
        p0();
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

    public final void p() {
        Y0();
        setScrollState(0);
    }

    public void p0() {
        this.t4 = null;
        this.r4 = null;
        this.s4 = null;
        this.q4 = null;
    }

    public final void p1() {
        this.J4.g();
        o oVar = this.U3;
        if (oVar != null) {
            oVar.E1();
        }
    }

    public boolean q0() {
        AccessibilityManager accessibilityManager = this.j4;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public void q1(int i2, int i3, Object obj) {
        int i4;
        int iJ = this.M3.j();
        int i5 = i2 + i3;
        for (int i6 = 0; i6 < iJ; i6++) {
            View viewI = this.M3.i(i6);
            c0 c0VarD0 = d0(viewI);
            if (c0VarD0 != null && !c0VarD0.J() && (i4 = c0VarD0.f582d) >= i2 && i4 < i5) {
                c0VarD0.b(2);
                c0VarD0.a(obj);
                ((p) viewI.getLayoutParams()).f621c = true;
            }
        }
        this.J3.M(i2, i3);
    }

    public void r() {
        int iJ = this.M3.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarD0 = d0(this.M3.i(i2));
            if (!c0VarD0.J()) {
                c0VarD0.c();
            }
        }
        this.J3.d();
    }

    public boolean r0() {
        return this.n4 > 0;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z2) {
        c0 c0VarD0 = d0(view);
        if (c0VarD0 != null) {
            if (c0VarD0.x()) {
                c0VarD0.f();
            } else if (!c0VarD0.J()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + c0VarD0 + O());
            }
        }
        view.clearAnimation();
        y(view);
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.U3.Z0(this, this.M4, view, view2) && view2 != null) {
            W0(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        return this.U3.o1(this, view, rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        int size = this.X3.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.X3.get(i2).c(z2);
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.d4 != 0 || this.f4) {
            this.e4 = true;
        } else {
            super.requestLayout();
        }
    }

    public void s(int i2, int i3) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.q4;
        if (edgeEffect == null || edgeEffect.isFinished() || i2 <= 0) {
            zIsFinished = false;
        } else {
            this.q4.onRelease();
            zIsFinished = this.q4.isFinished();
        }
        EdgeEffect edgeEffect2 = this.s4;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i2 < 0) {
            this.s4.onRelease();
            zIsFinished |= this.s4.isFinished();
        }
        EdgeEffect edgeEffect3 = this.r4;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i3 > 0) {
            this.r4.onRelease();
            zIsFinished |= this.r4.isFinished();
        }
        EdgeEffect edgeEffect4 = this.t4;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i3 < 0) {
            this.t4.onRelease();
            zIsFinished |= this.t4.isFinished();
        }
        if (zIsFinished) {
            b.f.m.s.N(this);
        }
    }

    public final boolean s0(View view, View view2, int i2) {
        int i3;
        if (view2 == null || view2 == this || Q(view2) == null) {
            return false;
        }
        if (view == null || Q(view) == null) {
            return true;
        }
        this.Q3.set(0, 0, view.getWidth(), view.getHeight());
        this.R3.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.Q3);
        offsetDescendantRectToMyCoords(view2, this.R3);
        byte b2 = -1;
        int i4 = this.U3.X() == 1 ? -1 : 1;
        Rect rect = this.Q3;
        int i5 = rect.left;
        Rect rect2 = this.R3;
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
        throw new IllegalArgumentException("Invalid direction: " + i2 + O());
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        o oVar = this.U3;
        if (oVar == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.f4) {
            return;
        }
        boolean zJ = oVar.j();
        boolean zK = this.U3.k();
        if (zJ || zK) {
            if (!zJ) {
                i2 = 0;
            }
            if (!zK) {
                i3 = 0;
            }
            b1(i2, i3, null);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (f1(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(b.n.d.i iVar) {
        this.T4 = iVar;
        b.f.m.s.T(this, iVar);
    }

    public void setAdapter(g gVar) {
        setLayoutFrozen(false);
        d1(gVar, false, true);
        L0(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(j jVar) {
        if (jVar == this.U4) {
            return;
        }
        this.U4 = jVar;
        setChildrenDrawingOrderEnabled(jVar != null);
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z2) {
        if (z2 != this.O3) {
            p0();
        }
        this.O3 = z2;
        super.setClipToPadding(z2);
        if (this.c4) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(k kVar) {
        b.f.l.f.b(kVar);
        this.p4 = kVar;
        p0();
    }

    public void setHasFixedSize(boolean z2) {
        this.a4 = z2;
    }

    public void setItemAnimator(l lVar) {
        l lVar2 = this.u4;
        if (lVar2 != null) {
            lVar2.k();
            this.u4.v(null);
        }
        this.u4 = lVar;
        if (lVar != null) {
            lVar.v(this.R4);
        }
    }

    public void setItemViewCacheSize(int i2) {
        this.J3.G(i2);
    }

    @Deprecated
    public void setLayoutFrozen(boolean z2) {
        suppressLayout(z2);
    }

    public void setLayoutManager(o oVar) {
        if (oVar == this.U3) {
            return;
        }
        o1();
        if (this.U3 != null) {
            l lVar = this.u4;
            if (lVar != null) {
                lVar.k();
            }
            this.U3.h1(this.J3);
            this.U3.i1(this.J3);
            this.J3.c();
            if (this.Z3) {
                this.U3.z(this, this.J3);
            }
            this.U3.A1(null);
            this.U3 = null;
        } else {
            this.J3.c();
        }
        this.M3.o();
        this.U3 = oVar;
        if (oVar != null) {
            if (oVar.f605b != null) {
                throw new IllegalArgumentException("LayoutManager " + oVar + " is already attached to a RecyclerView:" + oVar.f605b.O());
            }
            oVar.A1(this);
            if (this.Z3) {
                this.U3.y(this);
            }
        }
        this.J3.K();
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
    }

    @Deprecated
    public void setOnScrollListener(t tVar) {
        this.N4 = tVar;
    }

    public void setPreserveFocusAfterLayout(boolean z2) {
        this.I4 = z2;
    }

    public void setRecycledViewPool(u uVar) {
        this.J3.E(uVar);
    }

    public void setRecyclerListener(w wVar) {
        this.V3 = wVar;
    }

    public void setScrollState(int i2) {
        if (i2 == this.v4) {
            return;
        }
        this.v4 = i2;
        if (i2 != 2) {
            p1();
        }
        G(i2);
    }

    public void setScrollingTouchSlop(int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i2 != 0) {
            if (i2 == 1) {
                this.C4 = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + i2 + "; using default value");
        }
        this.C4 = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(a0 a0Var) {
        this.J3.F(a0Var);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return getScrollingChildHelper().o(i2);
    }

    @Override // android.view.View, b.f.m.j
    public void stopNestedScroll() {
        getScrollingChildHelper().q();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z2) {
        if (z2 != this.f4) {
            n("Do not suppressLayout in layout or scroll");
            if (z2) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.f4 = true;
                this.g4 = true;
                o1();
                return;
            }
            this.f4 = false;
            if (this.e4 && this.U3 != null && this.T3 != null) {
                requestLayout();
            }
            this.e4 = false;
        }
    }

    public void t() {
        if (!this.c4 || this.l4) {
            b.f.i.a.a("RV FullInvalidate");
            A();
            b.f.i.a.b();
            return;
        }
        if (this.L3.p()) {
            if (!this.L3.o(4) || this.L3.o(11)) {
                if (this.L3.p()) {
                    b.f.i.a.a("RV FullInvalidate");
                    A();
                    b.f.i.a.b();
                    return;
                }
                return;
            }
            b.f.i.a.a("RV PartialInvalidate");
            k1();
            C0();
            this.L3.s();
            if (!this.e4) {
                if (k0()) {
                    A();
                } else {
                    this.L3.i();
                }
            }
            m1(true);
            D0();
            b.f.i.a.b();
        }
    }

    public void t0() {
        int iJ = this.M3.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            ((p) this.M3.i(i2).getLayoutParams()).f621c = true;
        }
        this.J3.s();
    }

    public final void u(Context context, String str, AttributeSet attributeSet, int i2, int i3) {
        Constructor constructor;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String strG0 = g0(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = Class.forName(strG0, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(o.class);
                Object[] objArr = null;
                try {
                    constructor = clsAsSubclass.getConstructor(G3);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i2), Integer.valueOf(i3)};
                } catch (NoSuchMethodException e2) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException e3) {
                        e3.initCause(e2);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strG0, e3);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((o) constructor.newInstance(objArr));
            } catch (ClassCastException e4) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strG0, e4);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strG0, e5);
            } catch (IllegalAccessException e6) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strG0, e6);
            } catch (InstantiationException e7) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strG0, e7);
            } catch (InvocationTargetException e8) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strG0, e8);
            }
        }
    }

    public void u0() {
        int iJ = this.M3.j();
        for (int i2 = 0; i2 < iJ; i2++) {
            c0 c0VarD0 = d0(this.M3.i(i2));
            if (c0VarD0 != null && !c0VarD0.J()) {
                c0VarD0.b(6);
            }
        }
        t0();
        this.J3.t();
    }

    public void v(int i2, int i3) {
        setMeasuredDimension(o.m(i2, getPaddingLeft() + getPaddingRight(), b.f.m.s.x(this)), o.m(i3, getPaddingTop() + getPaddingBottom(), b.f.m.s.w(this)));
    }

    public void v0(int i2) {
        int iG = this.M3.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.M3.f(i3).offsetLeftAndRight(i2);
        }
    }

    public final boolean w(int i2, int i3) {
        T(this.V4);
        int[] iArr = this.V4;
        return (iArr[0] == i2 && iArr[1] == i3) ? false : true;
    }

    public void w0(int i2) {
        int iG = this.M3.g();
        for (int i3 = 0; i3 < iG; i3++) {
            this.M3.f(i3).offsetTopAndBottom(i2);
        }
    }

    public void x(View view) {
        c0 c0VarD0 = d0(view);
        A0(view);
        g gVar = this.T3;
        if (gVar != null && c0VarD0 != null) {
            gVar.n(c0VarD0);
        }
        List<q> list = this.k4;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.k4.get(size).a(view);
            }
        }
    }

    public void x0(int i2, int i3) {
        int iJ = this.M3.j();
        for (int i4 = 0; i4 < iJ; i4++) {
            c0 c0VarD0 = d0(this.M3.i(i4));
            if (c0VarD0 != null && !c0VarD0.J() && c0VarD0.f582d >= i2) {
                c0VarD0.A(i3, false);
                this.M4.f645g = true;
            }
        }
        this.J3.u(i2, i3);
        requestLayout();
    }

    public void y(View view) {
        c0 c0VarD0 = d0(view);
        B0(view);
        g gVar = this.T3;
        if (gVar != null && c0VarD0 != null) {
            gVar.o(c0VarD0);
        }
        List<q> list = this.k4;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.k4.get(size).b(view);
            }
        }
    }

    public void y0(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int iJ = this.M3.j();
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
            c0 c0VarD0 = d0(this.M3.i(i8));
            if (c0VarD0 != null && (i7 = c0VarD0.f582d) >= i5 && i7 <= i4) {
                if (i7 == i2) {
                    c0VarD0.A(i3 - i2, false);
                } else {
                    c0VarD0.A(i6, false);
                }
                this.M4.f645g = true;
            }
        }
        this.J3.v(i2, i3);
        requestLayout();
    }

    public final void z() {
        int i2 = this.h4;
        this.h4 = 0;
        if (i2 == 0 || !q0()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        b.f.m.b0.b.b(accessibilityEventObtain, i2);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    public void z0(int i2, int i3, boolean z2) {
        int i4 = i2 + i3;
        int iJ = this.M3.j();
        for (int i5 = 0; i5 < iJ; i5++) {
            c0 c0VarD0 = d0(this.M3.i(i5));
            if (c0VarD0 != null && !c0VarD0.J()) {
                int i6 = c0VarD0.f582d;
                if (i6 >= i4) {
                    c0VarD0.A(-i3, z2);
                    this.M4.f645g = true;
                } else if (i6 >= i2) {
                    c0VarD0.i(i2 - 1, -i3, z2);
                    this.M4.f645g = true;
                }
            }
        }
        this.J3.w(i2, i3, z2);
        requestLayout();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.n.a.f1995a);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.I3 = new x();
        this.J3 = new v();
        this.N3 = new b.n.d.m();
        this.P3 = new a();
        this.Q3 = new Rect();
        this.R3 = new Rect();
        this.S3 = new RectF();
        this.W3 = new ArrayList<>();
        this.X3 = new ArrayList<>();
        this.d4 = 0;
        this.l4 = false;
        this.m4 = false;
        this.n4 = 0;
        this.o4 = 0;
        this.p4 = new k();
        this.u4 = new b.n.d.c();
        this.v4 = 0;
        this.w4 = -1;
        this.G4 = Float.MIN_VALUE;
        this.H4 = Float.MIN_VALUE;
        boolean z2 = true;
        this.I4 = true;
        this.J4 = new b0();
        this.L4 = y ? new e.b() : null;
        this.M4 = new z();
        this.P4 = false;
        this.Q4 = false;
        this.R4 = new m();
        this.S4 = false;
        this.V4 = new int[2];
        this.X4 = new int[2];
        this.Y4 = new int[2];
        this.Z4 = new int[2];
        this.a5 = new ArrayList();
        this.b5 = new b();
        this.c5 = new d();
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.C4 = viewConfiguration.getScaledTouchSlop();
        this.G4 = b.f.m.t.b(viewConfiguration, context);
        this.H4 = b.f.m.t.d(viewConfiguration, context);
        this.E4 = viewConfiguration.getScaledMinimumFlingVelocity();
        this.F4 = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.u4.v(this.R4);
        l0();
        n0();
        m0();
        if (b.f.m.s.t(this) == 0) {
            b.f.m.s.Y(this, 1);
        }
        this.j4 = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new b.n.d.i(this));
        int[] iArr = b.n.c.f2004f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        }
        String string = typedArrayObtainStyledAttributes.getString(b.n.c.o);
        if (typedArrayObtainStyledAttributes.getInt(b.n.c.f2007i, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.O3 = typedArrayObtainStyledAttributes.getBoolean(b.n.c.f2006h, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(b.n.c.j, false);
        this.b4 = z3;
        if (z3) {
            o0((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.n.c.m), typedArrayObtainStyledAttributes.getDrawable(b.n.c.n), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(b.n.c.k), typedArrayObtainStyledAttributes.getDrawable(b.n.c.l));
        }
        typedArrayObtainStyledAttributes.recycle();
        u(context, string, attributeSet, i2, 0);
        if (i3 >= 21) {
            int[] iArr2 = f573c;
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
        public Parcelable s;

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
            this.s = parcel.readParcelable(classLoader == null ? o.class.getClassLoader() : classLoader);
        }

        public void b(SavedState savedState) {
            this.s = savedState.s;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeParcelable(this.s, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class p extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c0 f619a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f620b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f621c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f622d;

        public p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f620b = new Rect();
            this.f621c = true;
            this.f622d = false;
        }

        public int a() {
            return this.f619a.m();
        }

        public boolean b() {
            return this.f619a.y();
        }

        public boolean c() {
            return this.f619a.v();
        }

        public boolean d() {
            return this.f619a.t();
        }

        public p(int i2, int i3) {
            super(i2, i3);
            this.f620b = new Rect();
            this.f621c = true;
            this.f622d = false;
        }

        public p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f620b = new Rect();
            this.f621c = true;
            this.f622d = false;
        }

        public p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f620b = new Rect();
            this.f621c = true;
            this.f622d = false;
        }

        public p(p pVar) {
            super((ViewGroup.LayoutParams) pVar);
            this.f620b = new Rect();
            this.f621c = true;
            this.f622d = false;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        o oVar = this.U3;
        if (oVar != null) {
            return oVar.E(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + O());
    }
}
