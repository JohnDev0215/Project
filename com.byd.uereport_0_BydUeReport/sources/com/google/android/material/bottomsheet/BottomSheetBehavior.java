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
import e6.h;
import e6.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import o0.b0;
import o0.t;
import p0.c;
import p0.f;
import t0.c;
import ts.car.service.carplay.data.config.CarPlayConfig;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3673a = R$style.Widget_Design_BottomSheet_Modal;
    public int A;
    public t0.c B;
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
    public final c.AbstractC0123c Q;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3677e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3678f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3679g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3680h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3681i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3682j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h f3683k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3684l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3685m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f3686n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3687o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public BottomSheetBehavior<V>.g f3688p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ValueAnimator f3689q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3690r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3691s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3692t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f3693u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3694v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f3695w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3696x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f3697y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f3698z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f3704d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f3705e;

        public a(View view, int i8) {
            this.f3704d = view;
            this.f3705e = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.m0(this.f3704d, this.f3705e);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.f3683k != null) {
                BottomSheetBehavior.this.f3683k.Y(fFloatValue);
            }
        }
    }

    public class c implements p.d {
        public c() {
        }

        @Override // z5.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            BottomSheetBehavior.this.f3684l = b0Var.d().f4985e;
            BottomSheetBehavior.this.t0(false);
            return b0Var;
        }
    }

    public class d extends c.AbstractC0123c {
        public d() {
        }

        @Override // t0.c.AbstractC0123c
        public int a(View view, int i8, int i9) {
            return view.getLeft();
        }

        @Override // t0.c.AbstractC0123c
        public int b(View view, int i8, int i9) {
            int iU = BottomSheetBehavior.this.U();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return j0.a.b(i8, iU, bottomSheetBehavior.f3696x ? bottomSheetBehavior.H : bottomSheetBehavior.f3694v);
        }

        @Override // t0.c.AbstractC0123c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.f3696x ? bottomSheetBehavior.H : bottomSheetBehavior.f3694v;
        }

        @Override // t0.c.AbstractC0123c
        public void j(int i8) {
            if (i8 == 1 && BottomSheetBehavior.this.f3698z) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // t0.c.AbstractC0123c
        public void k(View view, int i8, int i9, int i10, int i11) {
            BottomSheetBehavior.this.S(i9);
        }

        @Override // t0.c.AbstractC0123c
        public void l(View view, float f8, float f9) {
            int i8;
            int i9 = 4;
            if (f9 < 0.0f) {
                if (BottomSheetBehavior.this.f3675c) {
                    i8 = BottomSheetBehavior.this.f3691s;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i10 = bottomSheetBehavior.f3692t;
                    if (top > i10) {
                        i8 = i10;
                        i9 = 6;
                    } else {
                        i8 = bottomSheetBehavior.f3690r;
                    }
                }
                i9 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.f3696x && bottomSheetBehavior2.o0(view, f9)) {
                    if ((Math.abs(f8) >= Math.abs(f9) || f9 <= 500.0f) && !n(view)) {
                        if (BottomSheetBehavior.this.f3675c) {
                            i8 = BottomSheetBehavior.this.f3691s;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.f3690r) < Math.abs(view.getTop() - BottomSheetBehavior.this.f3692t)) {
                            i8 = BottomSheetBehavior.this.f3690r;
                        } else {
                            i8 = BottomSheetBehavior.this.f3692t;
                            i9 = 6;
                        }
                        i9 = 3;
                    } else {
                        i8 = BottomSheetBehavior.this.H;
                        i9 = 5;
                    }
                } else if (f9 == 0.0f || Math.abs(f8) > Math.abs(f9)) {
                    int top2 = view.getTop();
                    if (!BottomSheetBehavior.this.f3675c) {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i11 = bottomSheetBehavior3.f3692t;
                        if (top2 < i11) {
                            if (top2 < Math.abs(top2 - bottomSheetBehavior3.f3694v)) {
                                i8 = BottomSheetBehavior.this.f3690r;
                                i9 = 3;
                            } else {
                                i8 = BottomSheetBehavior.this.f3692t;
                            }
                        } else if (Math.abs(top2 - i11) < Math.abs(top2 - BottomSheetBehavior.this.f3694v)) {
                            i8 = BottomSheetBehavior.this.f3692t;
                        } else {
                            i8 = BottomSheetBehavior.this.f3694v;
                        }
                        i9 = 6;
                    } else if (Math.abs(top2 - BottomSheetBehavior.this.f3691s) < Math.abs(top2 - BottomSheetBehavior.this.f3694v)) {
                        i8 = BottomSheetBehavior.this.f3691s;
                        i9 = 3;
                    } else {
                        i8 = BottomSheetBehavior.this.f3694v;
                    }
                } else if (BottomSheetBehavior.this.f3675c) {
                    i8 = BottomSheetBehavior.this.f3694v;
                } else {
                    int top3 = view.getTop();
                    if (Math.abs(top3 - BottomSheetBehavior.this.f3692t) < Math.abs(top3 - BottomSheetBehavior.this.f3694v)) {
                        i8 = BottomSheetBehavior.this.f3692t;
                        i9 = 6;
                    } else {
                        i8 = BottomSheetBehavior.this.f3694v;
                    }
                }
            }
            BottomSheetBehavior.this.p0(view, i9, i8, true);
        }

        @Override // t0.c.AbstractC0123c
        public boolean m(View view, int i8) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i9 = bottomSheetBehavior.A;
            if (i9 == 1 || bottomSheetBehavior.O) {
                return false;
            }
            if (i9 == 3 && bottomSheetBehavior.M == i8) {
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

    public class e implements p0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3710a;

        public e(int i8) {
            this.f3710a = i8;
        }

        @Override // p0.f
        public boolean perform(View view, f.a aVar) {
            BottomSheetBehavior.this.j0(this.f3710a);
            return true;
        }
    }

    public static abstract class f {
        public abstract void a(View view, float f8);

        public abstract void b(View view, int i8);
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f3712d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3713e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3714f;

        public g(View view, int i8) {
            this.f3712d = view;
            this.f3714f = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            t0.c cVar = BottomSheetBehavior.this.B;
            if (cVar == null || !cVar.n(true)) {
                BottomSheetBehavior.this.k0(this.f3714f);
            } else {
                t.d0(this.f3712d, this);
            }
            this.f3713e = false;
        }
    }

    public BottomSheetBehavior() {
        this.f3674b = 0;
        this.f3675c = true;
        this.f3676d = false;
        this.f3688p = null;
        this.f3693u = 0.5f;
        this.f3695w = -1.0f;
        this.f3698z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8, int i9) {
        this.D = 0;
        this.E = false;
        return (i8 & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void C(CoordinatorLayout coordinatorLayout, V v7, View view, int i8) {
        int i9;
        int i10 = 3;
        if (v7.getTop() == U()) {
            k0(3);
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (weakReference != null && view == weakReference.get() && this.E) {
            if (this.D > 0) {
                if (this.f3675c) {
                    i9 = this.f3691s;
                } else {
                    int top = v7.getTop();
                    int i11 = this.f3692t;
                    if (top > i11) {
                        i9 = i11;
                        i10 = 6;
                    } else {
                        i9 = this.f3690r;
                    }
                }
            } else if (this.f3696x && o0(v7, V())) {
                i9 = this.H;
                i10 = 5;
            } else if (this.D == 0) {
                int top2 = v7.getTop();
                if (!this.f3675c) {
                    int i12 = this.f3692t;
                    if (top2 < i12) {
                        if (top2 < Math.abs(top2 - this.f3694v)) {
                            i9 = this.f3690r;
                        } else {
                            i9 = this.f3692t;
                        }
                    } else if (Math.abs(top2 - i12) < Math.abs(top2 - this.f3694v)) {
                        i9 = this.f3692t;
                    } else {
                        i9 = this.f3694v;
                        i10 = 4;
                    }
                    i10 = 6;
                } else if (Math.abs(top2 - this.f3691s) < Math.abs(top2 - this.f3694v)) {
                    i9 = this.f3691s;
                } else {
                    i9 = this.f3694v;
                    i10 = 4;
                }
            } else {
                if (this.f3675c) {
                    i9 = this.f3694v;
                } else {
                    int top3 = v7.getTop();
                    if (Math.abs(top3 - this.f3692t) < Math.abs(top3 - this.f3694v)) {
                        i9 = this.f3692t;
                        i10 = 6;
                    } else {
                        i9 = this.f3694v;
                    }
                }
                i10 = 4;
            }
            p0(v7, i10, i9, false);
            this.E = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
        if (!v7.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.A == 1 && actionMasked == 0) {
            return true;
        }
        t0.c cVar = this.B;
        if (cVar != null) {
            cVar.F(motionEvent);
        }
        if (actionMasked == 0) {
            X();
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        if (this.B != null && actionMasked == 2 && !this.C && Math.abs(this.N - motionEvent.getY()) > this.B.z()) {
            this.B.c(v7, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.C;
    }

    public final void L(V v7, c.a aVar, int i8) {
        t.h0(v7, aVar, null, new e(i8));
    }

    public final void M() {
        int iO = O();
        if (this.f3675c) {
            this.f3694v = Math.max(this.H - iO, this.f3691s);
        } else {
            this.f3694v = this.H - iO;
        }
    }

    public final void N() {
        this.f3692t = (int) (this.H * (1.0f - this.f3693u));
    }

    public final int O() {
        int i8;
        return this.f3679g ? Math.min(Math.max(this.f3680h, this.H - ((this.G * 9) / 16)), this.F) : (this.f3685m || (i8 = this.f3684l) <= 0) ? this.f3678f : Math.max(this.f3678f, i8 + this.f3681i);
    }

    public final void P(Context context, AttributeSet attributeSet, boolean z7) {
        Q(context, attributeSet, z7, null);
    }

    public final void Q(Context context, AttributeSet attributeSet, boolean z7, ColorStateList colorStateList) {
        if (this.f3682j) {
            this.f3686n = m.e(context, attributeSet, R$attr.bottomSheetStyle, f3673a).m();
            h hVar = new h(this.f3686n);
            this.f3683k = hVar;
            hVar.N(context);
            if (z7 && colorStateList != null) {
                this.f3683k.X(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.f3683k.setTint(typedValue.data);
        }
    }

    public final void R() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f3689q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f3689q.addUpdateListener(new b());
    }

    public void S(int i8) {
        float f8;
        float fU;
        V v7 = this.I.get();
        if (v7 == null || this.K.isEmpty()) {
            return;
        }
        int i9 = this.f3694v;
        if (i8 > i9 || i9 == U()) {
            int i10 = this.f3694v;
            f8 = i10 - i8;
            fU = this.H - i10;
        } else {
            int i11 = this.f3694v;
            f8 = i11 - i8;
            fU = i11 - U();
        }
        float f9 = f8 / fU;
        for (int i12 = 0; i12 < this.K.size(); i12++) {
            this.K.get(i12).a(v7, f9);
        }
    }

    public View T(View view) {
        if (t.R(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View viewT = T(viewGroup.getChildAt(i8));
            if (viewT != null) {
                return viewT;
            }
        }
        return null;
    }

    public int U() {
        return this.f3675c ? this.f3691s : this.f3690r;
    }

    public final float V() {
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f3677e);
        return this.L.getYVelocity(this.M);
    }

    public boolean W() {
        return this.f3685m;
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
        int i8 = this.f3674b;
        if (i8 == 0) {
            return;
        }
        if (i8 == -1 || (i8 & 1) == 1) {
            this.f3678f = savedState.f3700g;
        }
        if (i8 == -1 || (i8 & 2) == 2) {
            this.f3675c = savedState.f3701h;
        }
        if (i8 == -1 || (i8 & 4) == 4) {
            this.f3696x = savedState.f3702i;
        }
        if (i8 == -1 || (i8 & 8) == 8) {
            this.f3697y = savedState.f3703j;
        }
    }

    public void Z(boolean z7) {
        this.f3698z = z7;
    }

    public void a0(int i8) {
        if (i8 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.f3690r = i8;
    }

    public void b0(boolean z7) {
        if (this.f3675c == z7) {
            return;
        }
        this.f3675c = z7;
        if (this.I != null) {
            M();
        }
        k0((this.f3675c && this.A == 6) ? 3 : this.A);
        q0();
    }

    public void c0(boolean z7) {
        this.f3685m = z7;
    }

    public void d0(float f8) {
        if (f8 <= 0.0f || f8 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f3693u = f8;
        if (this.I != null) {
            N();
        }
    }

    public void e0(boolean z7) {
        if (this.f3696x != z7) {
            this.f3696x = z7;
            if (!z7 && this.A == 5) {
                j0(4);
            }
            q0();
        }
    }

    public void f0(int i8) {
        g0(i8, false);
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
            boolean r4 = r3.f3679g
            if (r4 != 0) goto L15
            r3.f3679g = r0
            goto L1f
        Lc:
            boolean r2 = r3.f3679g
            if (r2 != 0) goto L17
            int r2 = r3.f3678f
            if (r2 == r4) goto L15
            goto L17
        L15:
            r0 = r1
            goto L1f
        L17:
            r3.f3679g = r1
            int r4 = java.lang.Math.max(r1, r4)
            r3.f3678f = r4
        L1f:
            if (r0 == 0) goto L24
            r3.t0(r5)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.g0(int, boolean):void");
    }

    public void h0(int i8) {
        this.f3674b = i8;
    }

    public void i0(boolean z7) {
        this.f3697y = z7;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void j() {
        super.j();
        this.I = null;
        this.B = null;
    }

    public void j0(int i8) {
        if (i8 == this.A) {
            return;
        }
        if (this.I != null) {
            n0(i8);
            return;
        }
        if (i8 == 4 || i8 == 3 || i8 == 6 || (this.f3696x && i8 == 5)) {
            this.A = i8;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
        t0.c cVar;
        if (!v7.isShown() || !this.f3698z) {
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
            int x7 = (int) motionEvent.getX();
            this.N = (int) motionEvent.getY();
            if (this.A != 2) {
                WeakReference<View> weakReference = this.J;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.F(view, x7, this.N)) {
                    this.M = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.O = true;
                }
            }
            this.C = this.M == -1 && !coordinatorLayout.F(v7, x7, this.N);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.O = false;
            this.M = -1;
            if (this.C) {
                this.C = false;
                return false;
            }
        }
        if (!this.C && (cVar = this.B) != null && cVar.O(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.J;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.C || this.A == 1 || coordinatorLayout.F(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.B == null || Math.abs(((float) this.N) - motionEvent.getY()) <= ((float) this.B.z())) ? false : true;
    }

    public void k0(int i8) {
        V v7;
        if (this.A == i8) {
            return;
        }
        this.A = i8;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v7 = weakReference.get()) == null) {
            return;
        }
        if (i8 == 3) {
            s0(true);
        } else if (i8 == 6 || i8 == 5 || i8 == 4) {
            s0(false);
        }
        r0(i8);
        for (int i9 = 0; i9 < this.K.size(); i9++) {
            this.K.get(i9).b(v7, i8);
        }
        q0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        h hVar;
        if (t.x(coordinatorLayout) && !t.x(v7)) {
            v7.setFitsSystemWindows(true);
        }
        if (this.I == null) {
            this.f3680h = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            l0(v7);
            this.I = new WeakReference<>(v7);
            if (this.f3682j && (hVar = this.f3683k) != null) {
                t.o0(v7, hVar);
            }
            h hVar2 = this.f3683k;
            if (hVar2 != null) {
                float fV = this.f3695w;
                if (fV == -1.0f) {
                    fV = t.v(v7);
                }
                hVar2.W(fV);
                boolean z7 = this.A == 3;
                this.f3687o = z7;
                this.f3683k.Y(z7 ? 0.0f : 1.0f);
            }
            q0();
            if (t.y(v7) == 0) {
                t.v0(v7, 1);
            }
        }
        if (this.B == null) {
            this.B = t0.c.p(coordinatorLayout, this.Q);
        }
        int top = v7.getTop();
        coordinatorLayout.M(v7, i8);
        this.G = coordinatorLayout.getWidth();
        this.H = coordinatorLayout.getHeight();
        int height = v7.getHeight();
        this.F = height;
        this.f3691s = Math.max(0, this.H - height);
        N();
        M();
        int i9 = this.A;
        if (i9 == 3) {
            t.W(v7, U());
        } else if (i9 == 6) {
            t.W(v7, this.f3692t);
        } else if (this.f3696x && i9 == 5) {
            t.W(v7, this.H);
        } else if (i9 == 4) {
            t.W(v7, this.f3694v);
        } else if (i9 == 1 || i9 == 2) {
            t.W(v7, top - v7.getTop());
        }
        this.J = new WeakReference<>(T(v7));
        return true;
    }

    public final void l0(View view) {
        if (Build.VERSION.SDK_INT < 29 || W() || this.f3679g) {
            return;
        }
        p.b(view, new c());
    }

    public void m0(View view, int i8) {
        int iU;
        int i9;
        if (i8 == 4) {
            iU = this.f3694v;
        } else if (i8 == 6) {
            int i10 = this.f3692t;
            if (!this.f3675c || i10 > (i9 = this.f3691s)) {
                iU = i10;
            } else {
                i8 = 3;
                iU = i9;
            }
        } else if (i8 == 3) {
            iU = U();
        } else {
            if (!this.f3696x || i8 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i8);
            }
            iU = this.H;
        }
        p0(view, i8, iU, false);
    }

    public final void n0(int i8) {
        V v7 = this.I.get();
        if (v7 == null) {
            return;
        }
        ViewParent parent = v7.getParent();
        if (parent != null && parent.isLayoutRequested() && t.P(v7)) {
            v7.post(new a(v7, i8));
        } else {
            m0(v7, i8);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v7, View view, float f8, float f9) {
        WeakReference<View> weakReference = this.J;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.A != 3 || super.o(coordinatorLayout, v7, view, f8, f9);
    }

    public boolean o0(View view, float f8) {
        if (this.f3697y) {
            return true;
        }
        if (view.getTop() < this.f3694v) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f8 * 0.1f)) - ((float) this.f3694v)) / ((float) O()) > 0.5f;
    }

    public void p0(View view, int i8, int i9, boolean z7) {
        t0.c cVar = this.B;
        if (!(cVar != null && (!z7 ? !cVar.P(view, view.getLeft(), i9) : !cVar.N(view.getLeft(), i9)))) {
            k0(i8);
            return;
        }
        k0(2);
        r0(i8);
        if (this.f3688p == null) {
            this.f3688p = new g(view, i8);
        }
        if (this.f3688p.f3713e) {
            this.f3688p.f3714f = i8;
            return;
        }
        BottomSheetBehavior<V>.g gVar = this.f3688p;
        gVar.f3714f = i8;
        t.d0(view, gVar);
        this.f3688p.f3713e = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int[] iArr, int i10) {
        if (i10 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v7.getTop();
        int i11 = top - i9;
        if (i9 > 0) {
            if (i11 < U()) {
                iArr[1] = top - U();
                t.W(v7, -iArr[1]);
                k0(3);
            } else {
                if (!this.f3698z) {
                    return;
                }
                iArr[1] = i9;
                t.W(v7, -i9);
                k0(1);
            }
        } else if (i9 < 0 && !view.canScrollVertically(-1)) {
            int i12 = this.f3694v;
            if (i11 > i12 && !this.f3696x) {
                iArr[1] = top - i12;
                t.W(v7, -iArr[1]);
                k0(4);
            } else {
                if (!this.f3698z) {
                    return;
                }
                iArr[1] = i9;
                t.W(v7, -i9);
                k0(1);
            }
        }
        S(v7.getTop());
        this.D = i9;
        this.E = true;
    }

    public final void q0() {
        V v7;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v7 = weakReference.get()) == null) {
            return;
        }
        t.f0(v7, CarPlayConfig.IapFeature.IAP_FEATURE_WIRELESS_CARPLAY_UPDATE);
        t.f0(v7, CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
        t.f0(v7, CarPlayConfig.IapFeature.IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY);
        if (this.f3696x && this.A != 5) {
            L(v7, c.a.f6988u, 5);
        }
        int i8 = this.A;
        if (i8 == 3) {
            L(v7, c.a.f6987t, this.f3675c ? 4 : 6);
            return;
        }
        if (i8 == 4) {
            L(v7, c.a.f6986s, this.f3675c ? 3 : 6);
        } else {
            if (i8 != 6) {
                return;
            }
            L(v7, c.a.f6987t, 4);
            L(v7, c.a.f6986s, 3);
        }
    }

    public final void r0(int i8) {
        ValueAnimator valueAnimator;
        if (i8 == 2) {
            return;
        }
        boolean z7 = i8 == 3;
        if (this.f3687o != z7) {
            this.f3687o = z7;
            if (this.f3683k == null || (valueAnimator = this.f3689q) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.f3689q.reverse();
                return;
            }
            float f8 = z7 ? 0.0f : 1.0f;
            this.f3689q.setFloatValues(1.0f - f8, f8);
            this.f3689q.start();
        }
    }

    public final void s0(boolean z7) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z7) {
                if (this.P != null) {
                    return;
                } else {
                    this.P = new HashMap(childCount);
                }
            }
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = coordinatorLayout.getChildAt(i8);
                if (childAt != this.I.get()) {
                    if (z7) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.P.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.f3676d) {
                            t.v0(childAt, 4);
                        }
                    } else if (this.f3676d && (map = this.P) != null && map.containsKey(childAt)) {
                        t.v0(childAt, this.P.get(childAt).intValue());
                    }
                }
            }
            if (z7) {
                return;
            }
            this.P = null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
    }

    public final void t0(boolean z7) {
        V v7;
        if (this.I != null) {
            M();
            if (this.A != 4 || (v7 = this.I.get()) == null) {
                return;
            }
            if (z7) {
                n0(this.A);
            } else {
                v7.requestLayout();
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v7, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.x(coordinatorLayout, v7, savedState.f());
        Y(savedState);
        int i8 = savedState.f3699f;
        if (i8 == 1 || i8 == 2) {
            this.A = 4;
        } else {
            this.A = i8;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v7) {
        return new SavedState(super.y(coordinatorLayout, v7), (BottomSheetBehavior<?>) this);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f3699f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3700g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f3701h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f3702i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f3703j;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3699f = parcel.readInt();
            this.f3700g = parcel.readInt();
            this.f3701h = parcel.readInt() == 1;
            this.f3702i = parcel.readInt() == 1;
            this.f3703j = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f3699f);
            parcel.writeInt(this.f3700g);
            parcel.writeInt(this.f3701h ? 1 : 0);
            parcel.writeInt(this.f3702i ? 1 : 0);
            parcel.writeInt(this.f3703j ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f3699f = bottomSheetBehavior.A;
            this.f3700g = bottomSheetBehavior.f3678f;
            this.f3701h = bottomSheetBehavior.f3675c;
            this.f3702i = bottomSheetBehavior.f3696x;
            this.f3703j = bottomSheetBehavior.f3697y;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i8;
        super(context, attributeSet);
        this.f3674b = 0;
        this.f3675c = true;
        this.f3676d = false;
        this.f3688p = null;
        this.f3693u = 0.5f;
        this.f3695w = -1.0f;
        this.f3698z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
        this.f3681i = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        this.f3682j = typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance);
        int i9 = R$styleable.BottomSheetBehavior_Layout_backgroundTint;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i9);
        if (zHasValue) {
            Q(context, attributeSet, zHasValue, b6.c.a(context, typedArrayObtainStyledAttributes, i9));
        } else {
            P(context, attributeSet, zHasValue);
        }
        R();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3695w = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i10 = R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i10);
        if (typedValuePeekValue != null && (i8 = typedValuePeekValue.data) == -1) {
            f0(i8);
        } else {
            f0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1));
        }
        e0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        c0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        b0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        h0(typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        d0(typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i11 = R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i11);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            a0(typedValuePeekValue2.data);
        } else {
            a0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i11, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f3677e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
