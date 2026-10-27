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
import defpackage.ba;
import defpackage.ea;
import defpackage.h8;
import defpackage.ha;
import defpackage.hk;
import defpackage.mk;
import defpackage.oj;
import defpackage.sj;
import defpackage.t9;
import defpackage.za;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {
    public static final int a = R$style.Widget_Design_BottomSheet_Modal;
    public int A;
    public za B;
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
    public final za.c Q;
    public int b;
    public boolean c;
    public boolean d;
    public float e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    public boolean j;
    public hk k;
    public int l;
    public boolean m;
    public mk n;
    public boolean o;
    public BottomSheetBehavior<V>.g p;
    public ValueAnimator q;
    public int r;
    public int s;
    public int t;
    public float u;
    public int v;
    public float w;
    public boolean x;
    public boolean y;
    public boolean z;

    public class a implements Runnable {
        public final /* synthetic */ View b;
        public final /* synthetic */ int c;

        public a(View view, int i) {
            this.b = view;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.m0(this.b, this.c);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.k != null) {
                BottomSheetBehavior.this.k.Y(fFloatValue);
            }
        }
    }

    public class c implements oj.d {
        public c() {
        }

        @Override // oj.d
        public ba a(View view, ba baVar, oj.e eVar) {
            BottomSheetBehavior.this.l = baVar.d().e;
            BottomSheetBehavior.this.t0(false);
            return baVar;
        }
    }

    public class d extends za.c {
        public d() {
        }

        @Override // za.c
        public int a(View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // za.c
        public int b(View view, int i, int i2) {
            int iU = BottomSheetBehavior.this.U();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return h8.b(i, iU, bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v);
        }

        @Override // za.c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v;
        }

        @Override // za.c
        public void j(int i) {
            if (i == 1 && BottomSheetBehavior.this.z) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // za.c
        public void k(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.S(i2);
        }

        @Override // za.c
        public void l(View view, float f, float f2) {
            int i;
            int i2 = 4;
            if (f2 < 0.0f) {
                if (BottomSheetBehavior.this.c) {
                    i = BottomSheetBehavior.this.s;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i3 = bottomSheetBehavior.t;
                    if (top > i3) {
                        i = i3;
                        i2 = 6;
                    } else {
                        i = bottomSheetBehavior.r;
                    }
                }
                i2 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.x && bottomSheetBehavior2.o0(view, f2)) {
                    if ((Math.abs(f) >= Math.abs(f2) || f2 <= 500.0f) && !n(view)) {
                        if (BottomSheetBehavior.this.c) {
                            i = BottomSheetBehavior.this.s;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.r) < Math.abs(view.getTop() - BottomSheetBehavior.this.t)) {
                            i = BottomSheetBehavior.this.r;
                        } else {
                            i = BottomSheetBehavior.this.t;
                            i2 = 6;
                        }
                        i2 = 3;
                    } else {
                        i = BottomSheetBehavior.this.H;
                        i2 = 5;
                    }
                } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                    int top2 = view.getTop();
                    if (!BottomSheetBehavior.this.c) {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i4 = bottomSheetBehavior3.t;
                        if (top2 < i4) {
                            if (top2 < Math.abs(top2 - bottomSheetBehavior3.v)) {
                                i = BottomSheetBehavior.this.r;
                                i2 = 3;
                            } else {
                                i = BottomSheetBehavior.this.t;
                            }
                        } else if (Math.abs(top2 - i4) < Math.abs(top2 - BottomSheetBehavior.this.v)) {
                            i = BottomSheetBehavior.this.t;
                        } else {
                            i = BottomSheetBehavior.this.v;
                        }
                        i2 = 6;
                    } else if (Math.abs(top2 - BottomSheetBehavior.this.s) < Math.abs(top2 - BottomSheetBehavior.this.v)) {
                        i = BottomSheetBehavior.this.s;
                        i2 = 3;
                    } else {
                        i = BottomSheetBehavior.this.v;
                    }
                } else if (BottomSheetBehavior.this.c) {
                    i = BottomSheetBehavior.this.v;
                } else {
                    int top3 = view.getTop();
                    if (Math.abs(top3 - BottomSheetBehavior.this.t) < Math.abs(top3 - BottomSheetBehavior.this.v)) {
                        i = BottomSheetBehavior.this.t;
                        i2 = 6;
                    } else {
                        i = BottomSheetBehavior.this.v;
                    }
                }
            }
            BottomSheetBehavior.this.p0(view, i2, i, true);
        }

        @Override // za.c
        public boolean m(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.A;
            if (i2 == 1 || bottomSheetBehavior.O) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.M == i) {
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

    public class e implements ha {
        public final /* synthetic */ int a;

        public e(int i) {
            this.a = i;
        }

        @Override // defpackage.ha
        public boolean a(View view, ha.a aVar) {
            BottomSheetBehavior.this.j0(this.a);
            return true;
        }
    }

    public static abstract class f {
        public abstract void a(View view, float f);

        public abstract void b(View view, int i);
    }

    public class g implements Runnable {
        public final View b;
        public boolean c;
        public int d;

        public g(View view, int i) {
            this.b = view;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            za zaVar = BottomSheetBehavior.this.B;
            if (zaVar == null || !zaVar.m(true)) {
                BottomSheetBehavior.this.k0(this.d);
            } else {
                t9.b0(this.b, this);
            }
            this.c = false;
        }
    }

    public BottomSheetBehavior() {
        this.b = 0;
        this.c = true;
        this.d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.D = 0;
        this.E = false;
        return (i & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void C(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        int i3 = 3;
        if (v.getTop() == U()) {
            k0(3);
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (weakReference != null && view == weakReference.get() && this.E) {
            if (this.D > 0) {
                if (this.c) {
                    i2 = this.s;
                } else {
                    int top = v.getTop();
                    int i4 = this.t;
                    if (top > i4) {
                        i2 = i4;
                        i3 = 6;
                    } else {
                        i2 = this.r;
                    }
                }
            } else if (this.x && o0(v, V())) {
                i2 = this.H;
                i3 = 5;
            } else if (this.D == 0) {
                int top2 = v.getTop();
                if (!this.c) {
                    int i5 = this.t;
                    if (top2 < i5) {
                        if (top2 < Math.abs(top2 - this.v)) {
                            i2 = this.r;
                        } else {
                            i2 = this.t;
                        }
                    } else if (Math.abs(top2 - i5) < Math.abs(top2 - this.v)) {
                        i2 = this.t;
                    } else {
                        i2 = this.v;
                        i3 = 4;
                    }
                    i3 = 6;
                } else if (Math.abs(top2 - this.s) < Math.abs(top2 - this.v)) {
                    i2 = this.s;
                } else {
                    i2 = this.v;
                    i3 = 4;
                }
            } else {
                if (this.c) {
                    i2 = this.v;
                } else {
                    int top3 = v.getTop();
                    if (Math.abs(top3 - this.t) < Math.abs(top3 - this.v)) {
                        i2 = this.t;
                        i3 = 6;
                    } else {
                        i2 = this.v;
                    }
                }
                i3 = 4;
            }
            p0(v, i3, i2, false);
            this.E = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.A == 1 && actionMasked == 0) {
            return true;
        }
        za zaVar = this.B;
        if (zaVar != null) {
            zaVar.E(motionEvent);
        }
        if (actionMasked == 0) {
            X();
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        if (this.B != null && actionMasked == 2 && !this.C && Math.abs(this.N - motionEvent.getY()) > this.B.y()) {
            this.B.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.C;
    }

    public final void L(V v, ea.a aVar, int i) {
        t9.f0(v, aVar, null, new e(i));
    }

    public final void M() {
        int iO = O();
        if (this.c) {
            this.v = Math.max(this.H - iO, this.s);
        } else {
            this.v = this.H - iO;
        }
    }

    public final void N() {
        this.t = (int) (this.H * (1.0f - this.u));
    }

    public final int O() {
        int i;
        return this.g ? Math.min(Math.max(this.h, this.H - ((this.G * 9) / 16)), this.F) : (this.m || (i = this.l) <= 0) ? this.f : Math.max(this.f, i + this.i);
    }

    public final void P(Context context, AttributeSet attributeSet, boolean z) {
        Q(context, attributeSet, z, null);
    }

    public final void Q(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.j) {
            this.n = mk.e(context, attributeSet, R$attr.bottomSheetStyle, a).m();
            hk hkVar = new hk(this.n);
            this.k = hkVar;
            hkVar.N(context);
            if (z && colorStateList != null) {
                this.k.X(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.k.setTint(typedValue.data);
        }
    }

    public final void R() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.q.addUpdateListener(new b());
    }

    public void S(int i) {
        float f2;
        float fU;
        V v = this.I.get();
        if (v == null || this.K.isEmpty()) {
            return;
        }
        int i2 = this.v;
        if (i > i2 || i2 == U()) {
            int i3 = this.v;
            f2 = i3 - i;
            fU = this.H - i3;
        } else {
            int i4 = this.v;
            f2 = i4 - i;
            fU = i4 - U();
        }
        float f3 = f2 / fU;
        for (int i5 = 0; i5 < this.K.size(); i5++) {
            this.K.get(i5).a(v, f3);
        }
    }

    public View T(View view) {
        if (t9.Q(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewT = T(viewGroup.getChildAt(i));
            if (viewT != null) {
                return viewT;
            }
        }
        return null;
    }

    public int U() {
        return this.c ? this.s : this.r;
    }

    public final float V() {
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.e);
        return this.L.getYVelocity(this.M);
    }

    public boolean W() {
        return this.m;
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
        int i = this.b;
        if (i == 0) {
            return;
        }
        if (i == -1 || (i & 1) == 1) {
            this.f = savedState.e;
        }
        if (i == -1 || (i & 2) == 2) {
            this.c = savedState.f;
        }
        if (i == -1 || (i & 4) == 4) {
            this.x = savedState.g;
        }
        if (i == -1 || (i & 8) == 8) {
            this.y = savedState.h;
        }
    }

    public void Z(boolean z) {
        this.z = z;
    }

    public void a0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.r = i;
    }

    public void b0(boolean z) {
        if (this.c == z) {
            return;
        }
        this.c = z;
        if (this.I != null) {
            M();
        }
        k0((this.c && this.A == 6) ? 3 : this.A);
        q0();
    }

    public void c0(boolean z) {
        this.m = z;
    }

    public void d0(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.u = f2;
        if (this.I != null) {
            N();
        }
    }

    public void e0(boolean z) {
        if (this.x != z) {
            this.x = z;
            if (!z && this.A == 5) {
                j0(4);
            }
            q0();
        }
    }

    public void f0(int i) {
        g0(i, false);
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
            boolean r4 = r3.g
            if (r4 != 0) goto L15
            r3.g = r0
            goto L1f
        Lc:
            boolean r2 = r3.g
            if (r2 != 0) goto L17
            int r2 = r3.f
            if (r2 == r4) goto L15
            goto L17
        L15:
            r0 = r1
            goto L1f
        L17:
            r3.g = r1
            int r4 = java.lang.Math.max(r1, r4)
            r3.f = r4
        L1f:
            if (r0 == 0) goto L24
            r3.t0(r5)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.g0(int, boolean):void");
    }

    public void h0(int i) {
        this.b = i;
    }

    public void i0(boolean z) {
        this.y = z;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void j() {
        super.j();
        this.I = null;
        this.B = null;
    }

    public void j0(int i) {
        if (i == this.A) {
            return;
        }
        if (this.I != null) {
            n0(i);
            return;
        }
        if (i == 4 || i == 3 || i == 6 || (this.x && i == 5)) {
            this.A = i;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        za zaVar;
        if (!v.isShown() || !this.z) {
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
            int x = (int) motionEvent.getX();
            this.N = (int) motionEvent.getY();
            if (this.A != 2) {
                WeakReference<View> weakReference = this.J;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.F(view, x, this.N)) {
                    this.M = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.O = true;
                }
            }
            this.C = this.M == -1 && !coordinatorLayout.F(v, x, this.N);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.O = false;
            this.M = -1;
            if (this.C) {
                this.C = false;
                return false;
            }
        }
        if (!this.C && (zaVar = this.B) != null && zaVar.N(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.J;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.C || this.A == 1 || coordinatorLayout.F(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.B == null || Math.abs(((float) this.N) - motionEvent.getY()) <= ((float) this.B.y())) ? false : true;
    }

    public void k0(int i) {
        V v;
        if (this.A == i) {
            return;
        }
        this.A = i;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i == 3) {
            s0(true);
        } else if (i == 6 || i == 5 || i == 4) {
            s0(false);
        }
        r0(i);
        for (int i2 = 0; i2 < this.K.size(); i2++) {
            this.K.get(i2).b(v, i);
        }
        q0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        hk hkVar;
        if (t9.w(coordinatorLayout) && !t9.w(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.I == null) {
            this.h = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            l0(v);
            this.I = new WeakReference<>(v);
            if (this.j && (hkVar = this.k) != null) {
                t9.m0(v, hkVar);
            }
            hk hkVar2 = this.k;
            if (hkVar2 != null) {
                float fU = this.w;
                if (fU == -1.0f) {
                    fU = t9.u(v);
                }
                hkVar2.W(fU);
                boolean z = this.A == 3;
                this.o = z;
                this.k.Y(z ? 0.0f : 1.0f);
            }
            q0();
            if (t9.x(v) == 0) {
                t9.t0(v, 1);
            }
        }
        if (this.B == null) {
            this.B = za.o(coordinatorLayout, this.Q);
        }
        int top = v.getTop();
        coordinatorLayout.M(v, i);
        this.G = coordinatorLayout.getWidth();
        this.H = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.F = height;
        this.s = Math.max(0, this.H - height);
        N();
        M();
        int i2 = this.A;
        if (i2 == 3) {
            t9.V(v, U());
        } else if (i2 == 6) {
            t9.V(v, this.t);
        } else if (this.x && i2 == 5) {
            t9.V(v, this.H);
        } else if (i2 == 4) {
            t9.V(v, this.v);
        } else if (i2 == 1 || i2 == 2) {
            t9.V(v, top - v.getTop());
        }
        this.J = new WeakReference<>(T(v));
        return true;
    }

    public final void l0(View view) {
        if (Build.VERSION.SDK_INT < 29 || W() || this.g) {
            return;
        }
        oj.b(view, new c());
    }

    public void m0(View view, int i) {
        int iU;
        int i2;
        if (i == 4) {
            iU = this.v;
        } else if (i == 6) {
            int i3 = this.t;
            if (!this.c || i3 > (i2 = this.s)) {
                iU = i3;
            } else {
                i = 3;
                iU = i2;
            }
        } else if (i == 3) {
            iU = U();
        } else {
            if (!this.x || i != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i);
            }
            iU = this.H;
        }
        p0(view, i, iU, false);
    }

    public final void n0(int i) {
        V v = this.I.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && t9.O(v)) {
            v.post(new a(v, i));
        } else {
            m0(v, i);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
        WeakReference<View> weakReference = this.J;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.A != 3 || super.o(coordinatorLayout, v, view, f2, f3);
    }

    public boolean o0(View view, float f2) {
        if (this.y) {
            return true;
        }
        if (view.getTop() < this.v) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.v)) / ((float) O()) > 0.5f;
    }

    public void p0(View view, int i, int i2, boolean z) {
        za zaVar = this.B;
        if (!(zaVar != null && (!z ? !zaVar.O(view, view.getLeft(), i2) : !zaVar.M(view.getLeft(), i2)))) {
            k0(i);
            return;
        }
        k0(2);
        r0(i);
        if (this.p == null) {
            this.p = new g(view, i);
        }
        if (this.p.c) {
            this.p.d = i;
            return;
        }
        BottomSheetBehavior<V>.g gVar = this.p;
        gVar.d = i;
        t9.b0(view, gVar);
        this.p.c = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i4 = top - i2;
        if (i2 > 0) {
            if (i4 < U()) {
                iArr[1] = top - U();
                t9.V(v, -iArr[1]);
                k0(3);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i2;
                t9.V(v, -i2);
                k0(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            int i5 = this.v;
            if (i4 > i5 && !this.x) {
                iArr[1] = top - i5;
                t9.V(v, -iArr[1]);
                k0(4);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i2;
                t9.V(v, -i2);
                k0(1);
            }
        }
        S(v.getTop());
        this.D = i2;
        this.E = true;
    }

    public final void q0() {
        V v;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        t9.d0(v, 524288);
        t9.d0(v, 262144);
        t9.d0(v, 1048576);
        if (this.x && this.A != 5) {
            L(v, ea.a.u, 5);
        }
        int i = this.A;
        if (i == 3) {
            L(v, ea.a.t, this.c ? 4 : 6);
            return;
        }
        if (i == 4) {
            L(v, ea.a.s, this.c ? 3 : 6);
        } else {
            if (i != 6) {
                return;
            }
            L(v, ea.a.t, 4);
            L(v, ea.a.s, 3);
        }
    }

    public final void r0(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.o != z) {
            this.o = z;
            if (this.k == null || (valueAnimator = this.q) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.q.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.q.setFloatValues(1.0f - f2, f2);
            this.q.start();
        }
    }

    public final void s0(boolean z) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z) {
                if (this.P != null) {
                    return;
                } else {
                    this.P = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.I.get()) {
                    if (z) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.P.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.d) {
                            t9.t0(childAt, 4);
                        }
                    } else if (this.d && (map = this.P) != null && map.containsKey(childAt)) {
                        t9.t0(childAt, this.P.get(childAt).intValue());
                    }
                }
            }
            if (z) {
                return;
            }
            this.P = null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    public final void t0(boolean z) {
        V v;
        if (this.I != null) {
            M();
            if (this.A != 4 || (v = this.I.get()) == null) {
                return;
            }
            if (z) {
                n0(this.A);
            } else {
                v.requestLayout();
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.x(coordinatorLayout, v, savedState.f());
        Y(savedState);
        int i = savedState.d;
        if (i == 1 || i == 2) {
            this.A = 4;
        } else {
            this.A = i;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.y(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int d;
        public int e;
        public boolean f;
        public boolean g;
        public boolean h;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt() == 1;
            this.g = parcel.readInt() == 1;
            this.h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.h ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.d = bottomSheetBehavior.A;
            this.e = bottomSheetBehavior.f;
            this.f = bottomSheetBehavior.c;
            this.g = bottomSheetBehavior.x;
            this.h = bottomSheetBehavior.y;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        this.b = 0;
        this.c = true;
        this.d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
        this.i = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        this.j = typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance);
        int i2 = R$styleable.BottomSheetBehavior_Layout_backgroundTint;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i2);
        if (zHasValue) {
            Q(context, attributeSet, zHasValue, sj.a(context, typedArrayObtainStyledAttributes, i2));
        } else {
            P(context, attributeSet, zHasValue);
        }
        R();
        if (Build.VERSION.SDK_INT >= 21) {
            this.w = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i3 = R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i3);
        if (typedValuePeekValue != null && (i = typedValuePeekValue.data) == -1) {
            f0(i);
        } else {
            f0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i3, -1));
        }
        e0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        c0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        b0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        h0(typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        d0(typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i4 = R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i4);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            a0(typedValuePeekValue2.data);
        } else {
            a0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i4, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
