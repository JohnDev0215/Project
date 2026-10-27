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
import b.g.k.b0;
import b.g.k.c0.c;
import b.g.k.c0.f;
import b.g.k.t;
import b.i.a.c;
import com.google.android.material.internal.q;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.x.h;
import d.b.a.a.x.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f3797a = k.Widget_Design_BottomSheet_Modal;
    int A;
    b.i.a.c B;
    private boolean C;
    private int D;
    private boolean E;
    private int F;
    int G;
    int H;
    WeakReference<V> I;
    WeakReference<View> J;
    private final ArrayList<f> K;
    private VelocityTracker L;
    int M;
    private int N;
    boolean O;
    private Map<View, Integer> P;
    private final c.AbstractC0052c Q;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3798b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3799c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3800d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3802f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3803g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3804h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3805i;
    private boolean j;
    private h k;
    private int l;
    private boolean m;
    private m n;
    private boolean o;
    private BottomSheetBehavior<V>.g p;
    private ValueAnimator q;
    int r;
    int s;
    int t;
    float u;
    int v;
    float w;
    boolean x;
    private boolean y;
    private boolean z;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f3811b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3812c;

        a(View view, int i2) {
            this.f3811b = view;
            this.f3812c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.m0(this.f3811b, this.f3812c);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.k != null) {
                BottomSheetBehavior.this.k.Y(fFloatValue);
            }
        }
    }

    class c implements q.e {
        c() {
        }

        @Override // com.google.android.material.internal.q.e
        public b0 a(View view, b0 b0Var, q.f fVar) {
            BottomSheetBehavior.this.l = b0Var.d().f2533e;
            BottomSheetBehavior.this.t0(false);
            return b0Var;
        }
    }

    class d extends c.AbstractC0052c {
        d() {
        }

        private boolean n(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.H + bottomSheetBehavior.U()) / 2;
        }

        @Override // b.i.a.c.AbstractC0052c
        public int a(View view, int i2, int i3) {
            return view.getLeft();
        }

        @Override // b.i.a.c.AbstractC0052c
        public int b(View view, int i2, int i3) {
            int iU = BottomSheetBehavior.this.U();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return b.g.f.a.b(i2, iU, bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v);
        }

        @Override // b.i.a.c.AbstractC0052c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v;
        }

        @Override // b.i.a.c.AbstractC0052c
        public void j(int i2) {
            if (i2 == 1 && BottomSheetBehavior.this.z) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // b.i.a.c.AbstractC0052c
        public void k(View view, int i2, int i3, int i4, int i5) {
            BottomSheetBehavior.this.S(i3);
        }

        @Override // b.i.a.c.AbstractC0052c
        public void l(View view, float f2, float f3) {
            int i2;
            int i3 = 4;
            if (f3 < 0.0f) {
                if (BottomSheetBehavior.this.f3799c) {
                    i2 = BottomSheetBehavior.this.s;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i4 = bottomSheetBehavior.t;
                    if (top > i4) {
                        i2 = i4;
                        i3 = 6;
                    } else {
                        i2 = bottomSheetBehavior.r;
                    }
                }
                i3 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.x && bottomSheetBehavior2.o0(view, f3)) {
                    if ((Math.abs(f2) >= Math.abs(f3) || f3 <= 500.0f) && !n(view)) {
                        if (BottomSheetBehavior.this.f3799c) {
                            i2 = BottomSheetBehavior.this.s;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.r) < Math.abs(view.getTop() - BottomSheetBehavior.this.t)) {
                            i2 = BottomSheetBehavior.this.r;
                        } else {
                            i2 = BottomSheetBehavior.this.t;
                            i3 = 6;
                        }
                        i3 = 3;
                    } else {
                        i2 = BottomSheetBehavior.this.H;
                        i3 = 5;
                    }
                } else if (f3 == 0.0f || Math.abs(f2) > Math.abs(f3)) {
                    int top2 = view.getTop();
                    if (!BottomSheetBehavior.this.f3799c) {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i5 = bottomSheetBehavior3.t;
                        if (top2 < i5) {
                            if (top2 < Math.abs(top2 - bottomSheetBehavior3.v)) {
                                i2 = BottomSheetBehavior.this.r;
                                i3 = 3;
                            } else {
                                i2 = BottomSheetBehavior.this.t;
                            }
                        } else if (Math.abs(top2 - i5) < Math.abs(top2 - BottomSheetBehavior.this.v)) {
                            i2 = BottomSheetBehavior.this.t;
                        } else {
                            i2 = BottomSheetBehavior.this.v;
                        }
                        i3 = 6;
                    } else if (Math.abs(top2 - BottomSheetBehavior.this.s) < Math.abs(top2 - BottomSheetBehavior.this.v)) {
                        i2 = BottomSheetBehavior.this.s;
                        i3 = 3;
                    } else {
                        i2 = BottomSheetBehavior.this.v;
                    }
                } else if (BottomSheetBehavior.this.f3799c) {
                    i2 = BottomSheetBehavior.this.v;
                } else {
                    int top3 = view.getTop();
                    if (Math.abs(top3 - BottomSheetBehavior.this.t) < Math.abs(top3 - BottomSheetBehavior.this.v)) {
                        i2 = BottomSheetBehavior.this.t;
                        i3 = 6;
                    } else {
                        i2 = BottomSheetBehavior.this.v;
                    }
                }
            }
            BottomSheetBehavior.this.p0(view, i3, i2, true);
        }

        @Override // b.i.a.c.AbstractC0052c
        public boolean m(View view, int i2) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i3 = bottomSheetBehavior.A;
            if (i3 == 1 || bottomSheetBehavior.O) {
                return false;
            }
            if (i3 == 3 && bottomSheetBehavior.M == i2) {
                WeakReference<View> weakReference = bottomSheetBehavior.J;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.I;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    class e implements b.g.k.c0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3817a;

        e(int i2) {
            this.f3817a = i2;
        }

        @Override // b.g.k.c0.f
        public boolean a(View view, f.a aVar) {
            BottomSheetBehavior.this.j0(this.f3817a);
            return true;
        }
    }

    public static abstract class f {
        public abstract void a(View view, float f2);

        public abstract void b(View view, int i2);
    }

    private class g implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f3819b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f3820c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3821d;

        g(View view, int i2) {
            this.f3819b = view;
            this.f3821d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.i.a.c cVar = BottomSheetBehavior.this.B;
            if (cVar == null || !cVar.m(true)) {
                BottomSheetBehavior.this.k0(this.f3821d);
            } else {
                t.b0(this.f3819b, this);
            }
            this.f3820c = false;
        }
    }

    public BottomSheetBehavior() {
        this.f3798b = 0;
        this.f3799c = true;
        this.f3800d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
    }

    private void L(V v, c.a aVar, int i2) {
        t.f0(v, aVar, null, new e(i2));
    }

    private void M() {
        int iO = O();
        if (this.f3799c) {
            this.v = Math.max(this.H - iO, this.s);
        } else {
            this.v = this.H - iO;
        }
    }

    private void N() {
        this.t = (int) (this.H * (1.0f - this.u));
    }

    private int O() {
        int i2;
        return this.f3803g ? Math.min(Math.max(this.f3804h, this.H - ((this.G * 9) / 16)), this.F) : (this.m || (i2 = this.l) <= 0) ? this.f3802f : Math.max(this.f3802f, i2 + this.f3805i);
    }

    private void P(Context context, AttributeSet attributeSet, boolean z) {
        Q(context, attributeSet, z, null);
    }

    private void Q(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.j) {
            this.n = m.e(context, attributeSet, d.b.a.a.b.bottomSheetStyle, f3797a).m();
            h hVar = new h(this.n);
            this.k = hVar;
            hVar.N(context);
            if (z && colorStateList != null) {
                this.k.X(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.k.setTint(typedValue.data);
        }
    }

    private void R() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.q.addUpdateListener(new b());
    }

    private float V() {
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f3801e);
        return this.L.getYVelocity(this.M);
    }

    private void X() {
        this.M = -1;
        VelocityTracker velocityTracker = this.L;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.L = null;
        }
    }

    private void Y(SavedState savedState) {
        int i2 = this.f3798b;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.f3802f = savedState.f3807e;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.f3799c = savedState.f3808f;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.x = savedState.f3809g;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.y = savedState.f3810h;
        }
    }

    private void l0(View view) {
        if (Build.VERSION.SDK_INT < 29 || W() || this.f3803g) {
            return;
        }
        q.b(view, new c());
    }

    private void n0(int i2) {
        V v = this.I.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && t.O(v)) {
            v.post(new a(v, i2));
        } else {
            m0(v, i2);
        }
    }

    private void q0() {
        V v;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        t.d0(v, 524288);
        t.d0(v, 262144);
        t.d0(v, 1048576);
        if (this.x && this.A != 5) {
            L(v, c.a.u, 5);
        }
        int i2 = this.A;
        if (i2 == 3) {
            L(v, c.a.t, this.f3799c ? 4 : 6);
            return;
        }
        if (i2 == 4) {
            L(v, c.a.s, this.f3799c ? 3 : 6);
        } else {
            if (i2 != 6) {
                return;
            }
            L(v, c.a.t, 4);
            L(v, c.a.s, 3);
        }
    }

    private void r0(int i2) {
        ValueAnimator valueAnimator;
        if (i2 == 2) {
            return;
        }
        boolean z = i2 == 3;
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

    private void s0(boolean z) {
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
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt != this.I.get()) {
                    if (z) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.P.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.f3800d) {
                            t.t0(childAt, 4);
                        }
                    } else if (this.f3800d && (map = this.P) != null && map.containsKey(childAt)) {
                        t.t0(childAt, this.P.get(childAt).intValue());
                    }
                }
            }
            if (z) {
                return;
            }
            this.P = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t0(boolean z) {
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
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i3) {
        this.D = 0;
        this.E = false;
        return (i2 & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void C(CoordinatorLayout coordinatorLayout, V v, View view, int i2) {
        int i3;
        int i4 = 3;
        if (v.getTop() == U()) {
            k0(3);
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (weakReference != null && view == weakReference.get() && this.E) {
            if (this.D > 0) {
                if (this.f3799c) {
                    i3 = this.s;
                } else {
                    int top = v.getTop();
                    int i5 = this.t;
                    if (top > i5) {
                        i3 = i5;
                        i4 = 6;
                    } else {
                        i3 = this.r;
                    }
                }
            } else if (this.x && o0(v, V())) {
                i3 = this.H;
                i4 = 5;
            } else if (this.D == 0) {
                int top2 = v.getTop();
                if (!this.f3799c) {
                    int i6 = this.t;
                    if (top2 < i6) {
                        if (top2 < Math.abs(top2 - this.v)) {
                            i3 = this.r;
                        } else {
                            i3 = this.t;
                        }
                    } else if (Math.abs(top2 - i6) < Math.abs(top2 - this.v)) {
                        i3 = this.t;
                    } else {
                        i3 = this.v;
                        i4 = 4;
                    }
                    i4 = 6;
                } else if (Math.abs(top2 - this.s) < Math.abs(top2 - this.v)) {
                    i3 = this.s;
                } else {
                    i3 = this.v;
                    i4 = 4;
                }
            } else {
                if (this.f3799c) {
                    i3 = this.v;
                } else {
                    int top3 = v.getTop();
                    if (Math.abs(top3 - this.t) < Math.abs(top3 - this.v)) {
                        i3 = this.t;
                        i4 = 6;
                    } else {
                        i3 = this.v;
                    }
                }
                i4 = 4;
            }
            p0(v, i4, i3, false);
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
        b.i.a.c cVar = this.B;
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
            this.B.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.C;
    }

    void S(int i2) {
        float f2;
        float fU;
        V v = this.I.get();
        if (v == null || this.K.isEmpty()) {
            return;
        }
        int i3 = this.v;
        if (i2 > i3 || i3 == U()) {
            int i4 = this.v;
            f2 = i4 - i2;
            fU = this.H - i4;
        } else {
            int i5 = this.v;
            f2 = i5 - i2;
            fU = i5 - U();
        }
        float f3 = f2 / fU;
        for (int i6 = 0; i6 < this.K.size(); i6++) {
            this.K.get(i6).a(v, f3);
        }
    }

    View T(View view) {
        if (t.Q(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View viewT = T(viewGroup.getChildAt(i2));
            if (viewT != null) {
                return viewT;
            }
        }
        return null;
    }

    public int U() {
        return this.f3799c ? this.s : this.r;
    }

    public boolean W() {
        return this.m;
    }

    public void Z(boolean z) {
        this.z = z;
    }

    public void a0(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.r = i2;
    }

    public void b0(boolean z) {
        if (this.f3799c == z) {
            return;
        }
        this.f3799c = z;
        if (this.I != null) {
            M();
        }
        k0((this.f3799c && this.A == 6) ? 3 : this.A);
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

    public void f0(int i2) {
        g0(i2, false);
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
            boolean r4 = r3.f3803g
            if (r4 != 0) goto L15
            r3.f3803g = r0
            goto L1f
        Lc:
            boolean r2 = r3.f3803g
            if (r2 != 0) goto L17
            int r2 = r3.f3802f
            if (r2 == r4) goto L15
            goto L17
        L15:
            r0 = r1
            goto L1f
        L17:
            r3.f3803g = r1
            int r4 = java.lang.Math.max(r1, r4)
            r3.f3802f = r4
        L1f:
            if (r0 == 0) goto L24
            r3.t0(r5)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.g0(int, boolean):void");
    }

    public void h0(int i2) {
        this.f3798b = i2;
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

    public void j0(int i2) {
        if (i2 == this.A) {
            return;
        }
        if (this.I != null) {
            n0(i2);
            return;
        }
        if (i2 == 4 || i2 == 3 || i2 == 6 || (this.x && i2 == 5)) {
            this.A = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        b.i.a.c cVar;
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
        if (!this.C && (cVar = this.B) != null && cVar.P(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.J;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.C || this.A == 1 || coordinatorLayout.F(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.B == null || Math.abs(((float) this.N) - motionEvent.getY()) <= ((float) this.B.z())) ? false : true;
    }

    void k0(int i2) {
        V v;
        if (this.A == i2) {
            return;
        }
        this.A = i2;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            s0(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            s0(false);
        }
        r0(i2);
        for (int i3 = 0; i3 < this.K.size(); i3++) {
            this.K.get(i3).b(v, i2);
        }
        q0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        h hVar;
        if (t.w(coordinatorLayout) && !t.w(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.I == null) {
            this.f3804h = coordinatorLayout.getResources().getDimensionPixelSize(d.b.a.a.d.design_bottom_sheet_peek_height_min);
            l0(v);
            this.I = new WeakReference<>(v);
            if (this.j && (hVar = this.k) != null) {
                t.m0(v, hVar);
            }
            h hVar2 = this.k;
            if (hVar2 != null) {
                float fU = this.w;
                if (fU == -1.0f) {
                    fU = t.u(v);
                }
                hVar2.W(fU);
                boolean z = this.A == 3;
                this.o = z;
                this.k.Y(z ? 0.0f : 1.0f);
            }
            q0();
            if (t.x(v) == 0) {
                t.t0(v, 1);
            }
        }
        if (this.B == null) {
            this.B = b.i.a.c.o(coordinatorLayout, this.Q);
        }
        int top = v.getTop();
        coordinatorLayout.M(v, i2);
        this.G = coordinatorLayout.getWidth();
        this.H = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.F = height;
        this.s = Math.max(0, this.H - height);
        N();
        M();
        int i3 = this.A;
        if (i3 == 3) {
            t.V(v, U());
        } else if (i3 == 6) {
            t.V(v, this.t);
        } else if (this.x && i3 == 5) {
            t.V(v, this.H);
        } else if (i3 == 4) {
            t.V(v, this.v);
        } else if (i3 == 1 || i3 == 2) {
            t.V(v, top - v.getTop());
        }
        this.J = new WeakReference<>(T(v));
        return true;
    }

    void m0(View view, int i2) {
        int iU;
        int i3;
        if (i2 == 4) {
            iU = this.v;
        } else if (i2 == 6) {
            int i4 = this.t;
            if (!this.f3799c || i4 > (i3 = this.s)) {
                iU = i4;
            } else {
                i2 = 3;
                iU = i3;
            }
        } else if (i2 == 3) {
            iU = U();
        } else {
            if (!this.x || i2 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i2);
            }
            iU = this.H;
        }
        p0(view, i2, iU, false);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v, View view, float f2, float f3) {
        WeakReference<View> weakReference = this.J;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.A != 3 || super.o(coordinatorLayout, v, view, f2, f3);
    }

    boolean o0(View view, float f2) {
        if (this.y) {
            return true;
        }
        if (view.getTop() < this.v) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.v)) / ((float) O()) > 0.5f;
    }

    void p0(View view, int i2, int i3, boolean z) {
        b.i.a.c cVar = this.B;
        if (!(cVar != null && (!z ? !cVar.Q(view, view.getLeft(), i3) : !cVar.O(view.getLeft(), i3)))) {
            k0(i2);
            return;
        }
        k0(2);
        r0(i2);
        if (this.p == null) {
            this.p = new g(view, i2);
        }
        if (((g) this.p).f3820c) {
            this.p.f3821d = i2;
            return;
        }
        BottomSheetBehavior<V>.g gVar = this.p;
        gVar.f3821d = i2;
        t.b0(view, gVar);
        ((g) this.p).f3820c = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i5 = top - i3;
        if (i3 > 0) {
            if (i5 < U()) {
                iArr[1] = top - U();
                t.V(v, -iArr[1]);
                k0(3);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i3;
                t.V(v, -i3);
                k0(1);
            }
        } else if (i3 < 0 && !view.canScrollVertically(-1)) {
            int i6 = this.v;
            if (i5 > i6 && !this.x) {
                iArr[1] = top - i6;
                t.V(v, -iArr[1]);
                k0(4);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i3;
                t.V(v, -i3);
                k0(1);
            }
        }
        S(v.getTop());
        this.D = i3;
        this.E = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.x(coordinatorLayout, v, savedState.a());
        Y(savedState);
        int i2 = savedState.f3806d;
        if (i2 == 1 || i2 == 2) {
            this.A = 4;
        } else {
            this.A = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.y(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f3806d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f3807e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f3808f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f3809g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f3810h;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3806d = parcel.readInt();
            this.f3807e = parcel.readInt();
            this.f3808f = parcel.readInt() == 1;
            this.f3809g = parcel.readInt() == 1;
            this.f3810h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f3806d);
            parcel.writeInt(this.f3807e);
            parcel.writeInt(this.f3808f ? 1 : 0);
            parcel.writeInt(this.f3809g ? 1 : 0);
            parcel.writeInt(this.f3810h ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f3806d = bottomSheetBehavior.A;
            this.f3807e = ((BottomSheetBehavior) bottomSheetBehavior).f3802f;
            this.f3808f = ((BottomSheetBehavior) bottomSheetBehavior).f3799c;
            this.f3809g = bottomSheetBehavior.x;
            this.f3810h = ((BottomSheetBehavior) bottomSheetBehavior).y;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i2;
        super(context, attributeSet);
        this.f3798b = 0;
        this.f3799c = true;
        this.f3800d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
        this.f3805i = context.getResources().getDimensionPixelSize(d.b.a.a.d.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.BottomSheetBehavior_Layout);
        this.j = typedArrayObtainStyledAttributes.hasValue(l.BottomSheetBehavior_Layout_shapeAppearance);
        int i3 = l.BottomSheetBehavior_Layout_backgroundTint;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i3);
        if (zHasValue) {
            Q(context, attributeSet, zHasValue, d.b.a.a.u.c.a(context, typedArrayObtainStyledAttributes, i3));
        } else {
            P(context, attributeSet, zHasValue);
        }
        R();
        if (Build.VERSION.SDK_INT >= 21) {
            this.w = typedArrayObtainStyledAttributes.getDimension(l.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i4 = l.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i4);
        if (typedValuePeekValue != null && (i2 = typedValuePeekValue.data) == -1) {
            f0(i2);
        } else {
            f0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        e0(typedArrayObtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_hideable, false));
        c0(typedArrayObtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        b0(typedArrayObtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(l.BottomSheetBehavior_Layout_behavior_draggable, true));
        h0(typedArrayObtainStyledAttributes.getInt(l.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        d0(typedArrayObtainStyledAttributes.getFloat(l.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i5 = l.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i5);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            a0(typedValuePeekValue2.data);
        } else {
            a0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i5, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f3801e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
