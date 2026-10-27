package com.google.android.material.bottomsheet;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import l0.b0;
import l0.t;
import m0.c;
import m0.f;
import n2.h;
import n2.m;
import p0.c;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f2090a = R$style.Widget_Design_BottomSheet_Modal;
    public int A;
    public p0.c B;
    public boolean C;
    public int D;
    public boolean E;
    public int F;
    public int G;
    public int H;
    public WeakReference<V> I;
    public WeakReference<View> J;
    public final ArrayList<f> K;
    public VelocityTracker L;
    public int M;
    public int N;
    public boolean O;
    public Map<View, Integer> P;
    public final c.AbstractC0049c Q;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2091b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2092c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2093d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2094e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2095f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2096g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2097h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2098i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2099j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h f2100k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2101l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f2103n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2104o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public BottomSheetBehavior<V>.g f2105p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f2106q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2107r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2108s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2109t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f2110u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2111v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f2112w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f2113x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2114y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2115z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2121b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2122c;

        public a(View view, int i3) {
            this.f2121b = view;
            this.f2122c = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.m0(this.f2121b, this.f2122c);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.f2100k != null) {
                BottomSheetBehavior.this.f2100k.Y(fFloatValue);
            }
        }
    }

    public class c implements p.d {
        public c() {
        }

        @Override // i2.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            BottomSheetBehavior.this.f2101l = b0Var.d().f2651e;
            BottomSheetBehavior.this.t0(false);
            return b0Var;
        }
    }

    public class d extends c.AbstractC0049c {
        public d() {
        }

        @Override // p0.c.AbstractC0049c
        public int a(View view, int i3, int i4) {
            return view.getLeft();
        }

        @Override // p0.c.AbstractC0049c
        public int b(View view, int i3, int i4) {
            int iU = BottomSheetBehavior.this.U();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return g0.a.b(i3, iU, bottomSheetBehavior.f2113x ? bottomSheetBehavior.H : bottomSheetBehavior.f2111v);
        }

        @Override // p0.c.AbstractC0049c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.f2113x ? bottomSheetBehavior.H : bottomSheetBehavior.f2111v;
        }

        @Override // p0.c.AbstractC0049c
        public void j(int i3) {
            if (i3 == 1 && BottomSheetBehavior.this.f2115z) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // p0.c.AbstractC0049c
        public void k(View view, int i3, int i4, int i5, int i6) {
            BottomSheetBehavior.this.S(i4);
        }

        @Override // p0.c.AbstractC0049c
        public void l(View view, float f3, float f4) {
            int i3;
            int i4 = 4;
            if (f4 < 0.0f) {
                if (BottomSheetBehavior.this.f2092c) {
                    i3 = BottomSheetBehavior.this.f2108s;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i5 = bottomSheetBehavior.f2109t;
                    if (top > i5) {
                        i3 = i5;
                        i4 = 6;
                    } else {
                        i3 = bottomSheetBehavior.f2107r;
                    }
                }
                i4 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.f2113x && bottomSheetBehavior2.o0(view, f4)) {
                    if ((Math.abs(f3) >= Math.abs(f4) || f4 <= 500.0f) && !n(view)) {
                        if (BottomSheetBehavior.this.f2092c) {
                            i3 = BottomSheetBehavior.this.f2108s;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.f2107r) < Math.abs(view.getTop() - BottomSheetBehavior.this.f2109t)) {
                            i3 = BottomSheetBehavior.this.f2107r;
                        } else {
                            i3 = BottomSheetBehavior.this.f2109t;
                            i4 = 6;
                        }
                        i4 = 3;
                    } else {
                        i3 = BottomSheetBehavior.this.H;
                        i4 = 5;
                    }
                } else if (f4 == 0.0f || Math.abs(f3) > Math.abs(f4)) {
                    int top2 = view.getTop();
                    if (!BottomSheetBehavior.this.f2092c) {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i6 = bottomSheetBehavior3.f2109t;
                        if (top2 < i6) {
                            if (top2 < Math.abs(top2 - bottomSheetBehavior3.f2111v)) {
                                i3 = BottomSheetBehavior.this.f2107r;
                                i4 = 3;
                            } else {
                                i3 = BottomSheetBehavior.this.f2109t;
                            }
                        } else if (Math.abs(top2 - i6) < Math.abs(top2 - BottomSheetBehavior.this.f2111v)) {
                            i3 = BottomSheetBehavior.this.f2109t;
                        } else {
                            i3 = BottomSheetBehavior.this.f2111v;
                        }
                        i4 = 6;
                    } else if (Math.abs(top2 - BottomSheetBehavior.this.f2108s) < Math.abs(top2 - BottomSheetBehavior.this.f2111v)) {
                        i3 = BottomSheetBehavior.this.f2108s;
                        i4 = 3;
                    } else {
                        i3 = BottomSheetBehavior.this.f2111v;
                    }
                } else if (BottomSheetBehavior.this.f2092c) {
                    i3 = BottomSheetBehavior.this.f2111v;
                } else {
                    int top3 = view.getTop();
                    if (Math.abs(top3 - BottomSheetBehavior.this.f2109t) < Math.abs(top3 - BottomSheetBehavior.this.f2111v)) {
                        i3 = BottomSheetBehavior.this.f2109t;
                        i4 = 6;
                    } else {
                        i3 = BottomSheetBehavior.this.f2111v;
                    }
                }
            }
            BottomSheetBehavior.this.p0(view, i4, i3, true);
        }

        @Override // p0.c.AbstractC0049c
        public boolean m(View view, int i3) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i4 = bottomSheetBehavior.A;
            if (i4 == 1 || bottomSheetBehavior.O) {
                return false;
            }
            if (i4 == 3 && bottomSheetBehavior.M == i3) {
                WeakReference<View> weakReference = bottomSheetBehavior.J;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.I;
            return weakReference2 != null && weakReference2.get() == view;
        }

        public final boolean n(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.H + bottomSheetBehavior.U()) / 2;
        }
    }

    public class e implements m0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2127a;

        public e(int i3) {
            this.f2127a = i3;
        }

        @Override // m0.f
        public boolean a(View view, f.a aVar) {
            BottomSheetBehavior.this.j0(this.f2127a);
            return true;
        }
    }

    public static abstract class f {
        public abstract void a(View view, float f3);

        public abstract void b(View view, int i3);
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f2129b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2130c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2131d;

        public g(View view, int i3) {
            this.f2129b = view;
            this.f2131d = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            p0.c cVar = BottomSheetBehavior.this.B;
            if (cVar == null || !cVar.m(true)) {
                BottomSheetBehavior.this.k0(this.f2131d);
            } else {
                t.b0(this.f2129b, this);
            }
            this.f2130c = false;
        }
    }

    public BottomSheetBehavior() {
        this.f2091b = 0;
        this.f2092c = true;
        this.f2093d = false;
        this.f2105p = null;
        this.f2110u = 0.5f;
        this.f2112w = -1.0f;
        this.f2115z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3, int i4) {
        this.D = 0;
        this.E = false;
        return (i3 & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void C(CoordinatorLayout coordinatorLayout, V v3, View view, int i3) {
        int i4;
        int i5 = 3;
        if (v3.getTop() == U()) {
            k0(3);
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (weakReference != null && view == weakReference.get() && this.E) {
            if (this.D > 0) {
                if (this.f2092c) {
                    i4 = this.f2108s;
                } else {
                    int top = v3.getTop();
                    int i6 = this.f2109t;
                    if (top > i6) {
                        i4 = i6;
                        i5 = 6;
                    } else {
                        i4 = this.f2107r;
                    }
                }
            } else if (this.f2113x && o0(v3, V())) {
                i4 = this.H;
                i5 = 5;
            } else if (this.D == 0) {
                int top2 = v3.getTop();
                if (!this.f2092c) {
                    int i7 = this.f2109t;
                    if (top2 < i7) {
                        if (top2 < Math.abs(top2 - this.f2111v)) {
                            i4 = this.f2107r;
                        } else {
                            i4 = this.f2109t;
                        }
                    } else if (Math.abs(top2 - i7) < Math.abs(top2 - this.f2111v)) {
                        i4 = this.f2109t;
                    } else {
                        i4 = this.f2111v;
                        i5 = 4;
                    }
                    i5 = 6;
                } else if (Math.abs(top2 - this.f2108s) < Math.abs(top2 - this.f2111v)) {
                    i4 = this.f2108s;
                } else {
                    i4 = this.f2111v;
                    i5 = 4;
                }
            } else {
                if (this.f2092c) {
                    i4 = this.f2111v;
                } else {
                    int top3 = v3.getTop();
                    if (Math.abs(top3 - this.f2109t) < Math.abs(top3 - this.f2111v)) {
                        i4 = this.f2109t;
                        i5 = 6;
                    } else {
                        i4 = this.f2111v;
                    }
                }
                i5 = 4;
            }
            p0(v3, i5, i4, false);
            this.E = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
        if (!v3.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.A == 1 && actionMasked == 0) {
            return true;
        }
        p0.c cVar = this.B;
        if (cVar != null) {
            cVar.E(motionEvent);
        }
        if (actionMasked == 0) {
            X();
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        if (this.B != null && actionMasked == 2 && !this.C && Math.abs(this.N - motionEvent.getY()) > this.B.y()) {
            this.B.b(v3, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.C;
    }

    public final void L(V v3, c.a aVar, int i3) {
        t.f0(v3, aVar, null, new e(i3));
    }

    public final void M() {
        int iO = O();
        if (this.f2092c) {
            this.f2111v = Math.max(this.H - iO, this.f2108s);
        } else {
            this.f2111v = this.H - iO;
        }
    }

    public final void N() {
        this.f2109t = (int) (this.H * (1.0f - this.f2110u));
    }

    public final int O() {
        int i3;
        return this.f2096g ? Math.min(Math.max(this.f2097h, this.H - ((this.G * 9) / 16)), this.F) : (this.f2102m || (i3 = this.f2101l) <= 0) ? this.f2095f : Math.max(this.f2095f, i3 + this.f2098i);
    }

    public final void P(Context context, AttributeSet attributeSet, boolean z3) {
        Q(context, attributeSet, z3, null);
    }

    public final void Q(Context context, AttributeSet attributeSet, boolean z3, ColorStateList colorStateList) {
        if (this.f2099j) {
            this.f2103n = m.e(context, attributeSet, R$attr.bottomSheetStyle, f2090a).m();
            h hVar = new h(this.f2103n);
            this.f2100k = hVar;
            hVar.N(context);
            if (z3 && colorStateList != null) {
                this.f2100k.X(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.f2100k.setTint(typedValue.data);
        }
    }

    public final void R() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f2106q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f2106q.addUpdateListener(new b());
    }

    public void S(int i3) {
        float f3;
        float fU;
        V v3 = this.I.get();
        if (v3 == null || this.K.isEmpty()) {
            return;
        }
        int i4 = this.f2111v;
        if (i3 > i4 || i4 == U()) {
            int i5 = this.f2111v;
            f3 = i5 - i3;
            fU = this.H - i5;
        } else {
            int i6 = this.f2111v;
            f3 = i6 - i3;
            fU = i6 - U();
        }
        float f4 = f3 / fU;
        for (int i7 = 0; i7 < this.K.size(); i7++) {
            this.K.get(i7).a(v3, f4);
        }
    }

    public View T(View view) {
        if (t.Q(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View viewT = T(viewGroup.getChildAt(i3));
            if (viewT != null) {
                return viewT;
            }
        }
        return null;
    }

    public int U() {
        return this.f2092c ? this.f2108s : this.f2107r;
    }

    public final float V() {
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f2094e);
        return this.L.getYVelocity(this.M);
    }

    public boolean W() {
        return this.f2102m;
    }

    public final void X() {
        this.M = -1;
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.L = null;
        }
    }

    public final void Y(SavedState savedState) {
        int i3 = this.f2091b;
        if (i3 == 0) {
            return;
        }
        if (i3 == -1 || (i3 & 1) == 1) {
            this.f2095f = savedState.f2117e;
        }
        if (i3 == -1 || (i3 & 2) == 2) {
            this.f2092c = savedState.f2118f;
        }
        if (i3 == -1 || (i3 & 4) == 4) {
            this.f2113x = savedState.f2119g;
        }
        if (i3 == -1 || (i3 & 8) == 8) {
            this.f2114y = savedState.f2120h;
        }
    }

    public void Z(boolean z3) {
        this.f2115z = z3;
    }

    public void a0(int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.f2107r = i3;
    }

    public void b0(boolean z3) {
        if (this.f2092c == z3) {
            return;
        }
        this.f2092c = z3;
        if (this.I != null) {
            M();
        }
        k0((this.f2092c && this.A == 6) ? 3 : this.A);
        q0();
    }

    public void c0(boolean z3) {
        this.f2102m = z3;
    }

    public void d0(float f3) {
        if (f3 <= 0.0f || f3 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f2110u = f3;
        if (this.I != null) {
            N();
        }
    }

    public void e0(boolean z3) {
        if (this.f2113x != z3) {
            this.f2113x = z3;
            if (!z3 && this.A == 5) {
                j0(4);
            }
            q0();
        }
    }

    public void f0(int i3) {
        g0(i3, false);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        super.g(fVar);
        this.I = null;
        this.B = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0(int r4, boolean r5) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            r2 = -1
            if (r4 != r2) goto Lc
            boolean r4 = r3.f2096g
            if (r4 != 0) goto L15
            r3.f2096g = r0
            goto L1f
        Lc:
            boolean r2 = r3.f2096g
            if (r2 != 0) goto L17
            int r2 = r3.f2095f
            if (r2 == r4) goto L15
            goto L17
        L15:
            r0 = r1
            goto L1f
        L17:
            r3.f2096g = r1
            int r4 = java.lang.Math.max(r1, r4)
            r3.f2095f = r4
        L1f:
            if (r0 == 0) goto L24
            r3.t0(r5)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.g0(int, boolean):void");
    }

    public void h0(int i3) {
        this.f2091b = i3;
    }

    public void i0(boolean z3) {
        this.f2114y = z3;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void j() {
        super.j();
        this.I = null;
        this.B = null;
    }

    public void j0(int i3) {
        if (i3 == this.A) {
            return;
        }
        if (this.I != null) {
            n0(i3);
            return;
        }
        if (i3 == 4 || i3 == 3 || i3 == 6 || (this.f2113x && i3 == 5)) {
            this.A = i3;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
        p0.c cVar;
        if (!v3.isShown() || !this.f2115z) {
            this.C = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            X();
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x3 = (int) motionEvent.getX();
            this.N = (int) motionEvent.getY();
            if (this.A != 2) {
                WeakReference<View> weakReference = this.J;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.F(view, x3, this.N)) {
                    this.M = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.O = true;
                }
            }
            this.C = this.M == -1 && !coordinatorLayout.F(v3, x3, this.N);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.O = false;
            this.M = -1;
            if (this.C) {
                this.C = false;
                return false;
            }
        }
        if (!this.C && (cVar = this.B) != null && cVar.N(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.J;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.C || this.A == 1 || coordinatorLayout.F(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.B == null || Math.abs(((float) this.N) - motionEvent.getY()) <= ((float) this.B.y())) ? false : true;
    }

    public void k0(int i3) {
        V v3;
        if (this.A == i3) {
            return;
        }
        this.A = i3;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v3 = weakReference.get()) == null) {
            return;
        }
        if (i3 == 3) {
            s0(true);
        } else if (i3 == 6 || i3 == 5 || i3 == 4) {
            s0(false);
        }
        r0(i3);
        for (int i4 = 0; i4 < this.K.size(); i4++) {
            this.K.get(i4).b(v3, i3);
        }
        q0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        h hVar;
        if (t.w(coordinatorLayout) && !t.w(v3)) {
            v3.setFitsSystemWindows(true);
        }
        if (this.I == null) {
            this.f2097h = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            l0(v3);
            this.I = new WeakReference<>(v3);
            if (this.f2099j && (hVar = this.f2100k) != null) {
                t.m0(v3, hVar);
            }
            h hVar2 = this.f2100k;
            if (hVar2 != null) {
                float fU = this.f2112w;
                if (fU == -1.0f) {
                    fU = t.u(v3);
                }
                hVar2.W(fU);
                boolean z3 = this.A == 3;
                this.f2104o = z3;
                this.f2100k.Y(z3 ? 0.0f : 1.0f);
            }
            q0();
            if (t.x(v3) == 0) {
                t.t0(v3, 1);
            }
        }
        if (this.B == null) {
            this.B = p0.c.o(coordinatorLayout, this.Q);
        }
        int top = v3.getTop();
        coordinatorLayout.M(v3, i3);
        this.G = coordinatorLayout.getWidth();
        this.H = coordinatorLayout.getHeight();
        int height = v3.getHeight();
        this.F = height;
        this.f2108s = Math.max(0, this.H - height);
        N();
        M();
        int i4 = this.A;
        if (i4 == 3) {
            t.V(v3, U());
        } else if (i4 == 6) {
            t.V(v3, this.f2109t);
        } else if (this.f2113x && i4 == 5) {
            t.V(v3, this.H);
        } else if (i4 == 4) {
            t.V(v3, this.f2111v);
        } else if (i4 == 1 || i4 == 2) {
            t.V(v3, top - v3.getTop());
        }
        this.J = new WeakReference<>(T(v3));
        return true;
    }

    public final void l0(View view) {
        if (Build.VERSION.SDK_INT < 29 || W() || this.f2096g) {
            return;
        }
        p.b(view, new c());
    }

    public void m0(View view, int i3) {
        int iU;
        int i4;
        if (i3 == 4) {
            iU = this.f2111v;
        } else if (i3 == 6) {
            int i5 = this.f2109t;
            if (!this.f2092c || i5 > (i4 = this.f2108s)) {
                iU = i5;
            } else {
                i3 = 3;
                iU = i4;
            }
        } else if (i3 == 3) {
            iU = U();
        } else {
            if (!this.f2113x || i3 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i3);
            }
            iU = this.H;
        }
        p0(view, i3, iU, false);
    }

    public final void n0(int i3) {
        V v3 = this.I.get();
        if (v3 == null) {
            return;
        }
        ViewParent parent = v3.getParent();
        if (parent != null && parent.isLayoutRequested() && t.O(v3)) {
            v3.post(new a(v3, i3));
        } else {
            m0(v3, i3);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v3, View view, float f3, float f4) {
        WeakReference<View> weakReference = this.J;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.A != 3 || super.o(coordinatorLayout, v3, view, f3, f4);
    }

    public boolean o0(View view, float f3) {
        if (this.f2114y) {
            return true;
        }
        if (view.getTop() < this.f2111v) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f3 * 0.1f)) - ((float) this.f2111v)) / ((float) O()) > 0.5f;
    }

    public void p0(View view, int i3, int i4, boolean z3) {
        p0.c cVar = this.B;
        if (!(cVar != null && (!z3 ? !cVar.O(view, view.getLeft(), i4) : !cVar.M(view.getLeft(), i4)))) {
            k0(i3);
            return;
        }
        k0(2);
        r0(i3);
        if (this.f2105p == null) {
            this.f2105p = new g(view, i3);
        }
        if (this.f2105p.f2130c) {
            this.f2105p.f2131d = i3;
            return;
        }
        BottomSheetBehavior<V>.g gVar = this.f2105p;
        gVar.f2131d = i3;
        t.b0(view, gVar);
        this.f2105p.f2130c = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int[] iArr, int i5) {
        if (i5 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v3.getTop();
        int i6 = top - i4;
        if (i4 > 0) {
            if (i6 < U()) {
                iArr[1] = top - U();
                t.V(v3, -iArr[1]);
                k0(3);
            } else {
                if (!this.f2115z) {
                    return;
                }
                iArr[1] = i4;
                t.V(v3, -i4);
                k0(1);
            }
        } else if (i4 < 0 && !view.canScrollVertically(-1)) {
            int i7 = this.f2111v;
            if (i6 > i7 && !this.f2113x) {
                iArr[1] = top - i7;
                t.V(v3, -iArr[1]);
                k0(4);
            } else {
                if (!this.f2115z) {
                    return;
                }
                iArr[1] = i4;
                t.V(v3, -i4);
                k0(1);
            }
        }
        S(v3.getTop());
        this.D = i4;
        this.E = true;
    }

    public final void q0() {
        V v3;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v3 = weakReference.get()) == null) {
            return;
        }
        t.d0(v3, 524288);
        t.d0(v3, 262144);
        t.d0(v3, 1048576);
        if (this.f2113x && this.A != 5) {
            L(v3, c.a.f3822u, 5);
        }
        int i3 = this.A;
        if (i3 == 3) {
            L(v3, c.a.f3821t, this.f2092c ? 4 : 6);
            return;
        }
        if (i3 == 4) {
            L(v3, c.a.f3820s, this.f2092c ? 3 : 6);
        } else {
            if (i3 != 6) {
                return;
            }
            L(v3, c.a.f3821t, 4);
            L(v3, c.a.f3820s, 3);
        }
    }

    public final void r0(int i3) {
        ValueAnimator valueAnimator;
        if (i3 == 2) {
            return;
        }
        boolean z3 = i3 == 3;
        if (this.f2104o != z3) {
            this.f2104o = z3;
            if (this.f2100k == null || (valueAnimator = this.f2106q) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.f2106q.reverse();
                return;
            }
            float f3 = z3 ? 0.0f : 1.0f;
            this.f2106q.setFloatValues(1.0f - f3, f3);
            this.f2106q.start();
        }
    }

    public final void s0(boolean z3) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z3) {
                if (this.P != null) {
                    return;
                } else {
                    this.P = new HashMap(childCount);
                }
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = coordinatorLayout.getChildAt(i3);
                if (childAt != this.I.get()) {
                    if (z3) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.P.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.f2093d) {
                            t.t0(childAt, 4);
                        }
                    } else if (this.f2093d && (map = this.P) != null && map.containsKey(childAt)) {
                        t.t0(childAt, this.P.get(childAt).intValue());
                    }
                }
            }
            if (z3) {
                return;
            }
            this.P = null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
    }

    public final void t0(boolean z3) {
        V v3;
        if (this.I != null) {
            M();
            if (this.A != 4 || (v3 = this.I.get()) == null) {
                return;
            }
            if (z3) {
                n0(this.A);
            } else {
                v3.requestLayout();
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v3, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.x(coordinatorLayout, v3, savedState.f());
        Y(savedState);
        int i3 = savedState.f2116d;
        if (i3 == 1 || i3 == 2) {
            this.A = 4;
        } else {
            this.A = i3;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v3) {
        return new SavedState(super.y(coordinatorLayout, v3), (BottomSheetBehavior<?>) this);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f2116d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2117e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2118f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f2119g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f2120h;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
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
            this.f2116d = parcel.readInt();
            this.f2117e = parcel.readInt();
            this.f2118f = parcel.readInt() == 1;
            this.f2119g = parcel.readInt() == 1;
            this.f2120h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f2116d);
            parcel.writeInt(this.f2117e);
            parcel.writeInt(this.f2118f ? 1 : 0);
            parcel.writeInt(this.f2119g ? 1 : 0);
            parcel.writeInt(this.f2120h ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f2116d = bottomSheetBehavior.A;
            this.f2117e = bottomSheetBehavior.f2095f;
            this.f2118f = bottomSheetBehavior.f2092c;
            this.f2119g = bottomSheetBehavior.f2113x;
            this.f2120h = bottomSheetBehavior.f2114y;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i3;
        super(context, attributeSet);
        this.f2091b = 0;
        this.f2092c = true;
        this.f2093d = false;
        this.f2105p = null;
        this.f2110u = 0.5f;
        this.f2112w = -1.0f;
        this.f2115z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
        this.f2098i = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        this.f2099j = typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance);
        int i4 = R$styleable.BottomSheetBehavior_Layout_backgroundTint;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i4);
        if (zHasValue) {
            Q(context, attributeSet, zHasValue, k2.c.a(context, typedArrayObtainStyledAttributes, i4));
        } else {
            P(context, attributeSet, zHasValue);
        }
        R();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2112w = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i5 = R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i5);
        if (typedValuePeekValue != null && (i3 = typedValuePeekValue.data) == -1) {
            f0(i3);
        } else {
            f0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, -1));
        }
        e0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        c0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        b0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        h0(typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        d0(typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i6 = R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i6);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            a0(typedValuePeekValue2.data);
        } else {
            a0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i6, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f2094e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
