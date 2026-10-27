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
import com.google.android.material.internal.l;
import i0.c0;
import i0.u;
import j0.c;
import j0.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import m0.c;
import o2.j;
import o2.k;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {
    private static final int Y = k.Widget_Design_BottomSheet_Modal;
    float A;
    int B;
    float C;
    boolean D;
    private boolean E;
    private boolean F;
    int G;
    m0.c H;
    private boolean I;
    private int J;
    private boolean K;
    private int L;
    int M;
    int N;
    WeakReference<V> O;
    WeakReference<View> P;
    private final ArrayList<g> Q;
    private VelocityTracker R;
    int S;
    private int T;
    boolean U;
    private Map<View, Integer> V;
    private int W;
    private final c.AbstractC0122c X;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4190b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4191c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f4192d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4193e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4194f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4195g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4196h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f4197i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private e3.g f4198j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f4199k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f4200l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f4201m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4202n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f4203o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f4204p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4205q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f4206r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4207s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private e3.k f4208t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f4209u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private BottomSheetBehavior<V>.h f4210v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private ValueAnimator f4211w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    int f4212x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    int f4213y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    int f4214z;

    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f4220e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ ViewGroup.LayoutParams f4221g;

        a(View view, ViewGroup.LayoutParams layoutParams) {
            this.f4220e = view;
            this.f4221g = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4220e.setLayoutParams(this.f4221g);
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f4223e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ int f4224g;

        b(View view, int i7) {
            this.f4223e = view;
            this.f4224g = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.y0(this.f4223e, this.f4224g);
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.f4198j != null) {
                BottomSheetBehavior.this.f4198j.Y(fFloatValue);
            }
        }
    }

    class d implements l.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f4227a;

        d(boolean z6) {
            this.f4227a = z6;
        }

        @Override // com.google.android.material.internal.l.d
        public c0 a(View view, c0 c0Var, l.e eVar) {
            BottomSheetBehavior.this.f4207s = c0Var.i();
            boolean zD = l.d(view);
            int paddingBottom = view.getPaddingBottom();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            if (BottomSheetBehavior.this.f4202n) {
                BottomSheetBehavior.this.f4206r = c0Var.f();
                paddingBottom = eVar.f4672d + BottomSheetBehavior.this.f4206r;
            }
            if (BottomSheetBehavior.this.f4203o) {
                paddingLeft = (zD ? eVar.f4671c : eVar.f4669a) + c0Var.g();
            }
            if (BottomSheetBehavior.this.f4204p) {
                paddingRight = (zD ? eVar.f4669a : eVar.f4671c) + c0Var.h();
            }
            view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
            if (this.f4227a) {
                BottomSheetBehavior.this.f4200l = c0Var.e().f10025d;
            }
            if (BottomSheetBehavior.this.f4202n || this.f4227a) {
                BottomSheetBehavior.this.F0(false);
            }
            return c0Var;
        }
    }

    class e extends c.AbstractC0122c {
        e() {
        }

        private boolean n(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.N + bottomSheetBehavior.d0()) / 2;
        }

        @Override // m0.c.AbstractC0122c
        public int a(View view, int i7, int i8) {
            return view.getLeft();
        }

        @Override // m0.c.AbstractC0122c
        public int b(View view, int i7, int i8) {
            int iD0 = BottomSheetBehavior.this.d0();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return c0.a.b(i7, iD0, bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B);
        }

        @Override // m0.c.AbstractC0122c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B;
        }

        @Override // m0.c.AbstractC0122c
        public void j(int i7) {
            if (i7 == 1 && BottomSheetBehavior.this.F) {
                BottomSheetBehavior.this.w0(1);
            }
        }

        @Override // m0.c.AbstractC0122c
        public void k(View view, int i7, int i8, int i9, int i10) {
            BottomSheetBehavior.this.a0(i8);
        }

        @Override // m0.c.AbstractC0122c
        public void l(View view, float f7, float f8) {
            int iD0;
            int i7 = 4;
            if (f8 < 0.0f) {
                if (BottomSheetBehavior.this.f4190b) {
                    iD0 = BottomSheetBehavior.this.f4213y;
                } else {
                    int top = view.getTop();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    int i8 = bottomSheetBehavior.f4214z;
                    if (top > i8) {
                        iD0 = i8;
                        i7 = 6;
                    } else {
                        iD0 = bottomSheetBehavior.d0();
                    }
                }
                i7 = 3;
            } else {
                BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                if (bottomSheetBehavior2.D && bottomSheetBehavior2.A0(view, f8)) {
                    if ((Math.abs(f7) >= Math.abs(f8) || f8 <= 500.0f) && !n(view)) {
                        if (BottomSheetBehavior.this.f4190b) {
                            iD0 = BottomSheetBehavior.this.f4213y;
                        } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.d0()) < Math.abs(view.getTop() - BottomSheetBehavior.this.f4214z)) {
                            iD0 = BottomSheetBehavior.this.d0();
                        } else {
                            iD0 = BottomSheetBehavior.this.f4214z;
                            i7 = 6;
                        }
                        i7 = 3;
                    } else {
                        iD0 = BottomSheetBehavior.this.N;
                        i7 = 5;
                    }
                } else if (f8 == 0.0f || Math.abs(f7) > Math.abs(f8)) {
                    int top2 = view.getTop();
                    if (!BottomSheetBehavior.this.f4190b) {
                        BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                        int i9 = bottomSheetBehavior3.f4214z;
                        if (top2 < i9) {
                            if (top2 < Math.abs(top2 - bottomSheetBehavior3.B)) {
                                iD0 = BottomSheetBehavior.this.d0();
                                i7 = 3;
                            } else {
                                iD0 = BottomSheetBehavior.this.f4214z;
                            }
                        } else if (Math.abs(top2 - i9) < Math.abs(top2 - BottomSheetBehavior.this.B)) {
                            iD0 = BottomSheetBehavior.this.f4214z;
                        } else {
                            iD0 = BottomSheetBehavior.this.B;
                        }
                        i7 = 6;
                    } else if (Math.abs(top2 - BottomSheetBehavior.this.f4213y) < Math.abs(top2 - BottomSheetBehavior.this.B)) {
                        iD0 = BottomSheetBehavior.this.f4213y;
                        i7 = 3;
                    } else {
                        iD0 = BottomSheetBehavior.this.B;
                    }
                } else if (BottomSheetBehavior.this.f4190b) {
                    iD0 = BottomSheetBehavior.this.B;
                } else {
                    int top3 = view.getTop();
                    if (Math.abs(top3 - BottomSheetBehavior.this.f4214z) < Math.abs(top3 - BottomSheetBehavior.this.B)) {
                        iD0 = BottomSheetBehavior.this.f4214z;
                        i7 = 6;
                    } else {
                        iD0 = BottomSheetBehavior.this.B;
                    }
                }
            }
            BottomSheetBehavior.this.B0(view, i7, iD0, true);
        }

        @Override // m0.c.AbstractC0122c
        public boolean m(View view, int i7) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i8 = bottomSheetBehavior.G;
            if (i8 == 1 || bottomSheetBehavior.U) {
                return false;
            }
            if (i8 == 3 && bottomSheetBehavior.S == i7) {
                WeakReference<View> weakReference = bottomSheetBehavior.P;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.O;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    class f implements j0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f4230a;

        f(int i7) {
            this.f4230a = i7;
        }

        @Override // j0.f
        public boolean a(View view, f.a aVar) {
            BottomSheetBehavior.this.v0(this.f4230a);
            return true;
        }
    }

    public static abstract class g {
        public abstract void a(View view, float f7);

        public abstract void b(View view, int i7);
    }

    private class h implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final View f4232e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f4233g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f4234h;

        h(View view, int i7) {
            this.f4232e = view;
            this.f4234h = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            m0.c cVar = BottomSheetBehavior.this.H;
            if (cVar == null || !cVar.k(true)) {
                BottomSheetBehavior.this.w0(this.f4234h);
            } else {
                u.c0(this.f4232e, this);
            }
            this.f4233g = false;
        }
    }

    public BottomSheetBehavior() {
        this.f4189a = 0;
        this.f4190b = true;
        this.f4191c = false;
        this.f4199k = -1;
        this.f4210v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new e();
    }

    private void C0() {
        V v6;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v6 = weakReference.get()) == null) {
            return;
        }
        u.e0(v6, 524288);
        u.e0(v6, 262144);
        u.e0(v6, 1048576);
        int i7 = this.W;
        if (i7 != -1) {
            u.e0(v6, i7);
        }
        if (!this.f4190b && this.G != 6) {
            this.W = R(v6, j.bottomsheet_action_expand_halfway, 6);
        }
        if (this.D && this.G != 5) {
            h0(v6, c.a.f7133y, 5);
        }
        int i8 = this.G;
        if (i8 == 3) {
            h0(v6, c.a.f7132x, this.f4190b ? 4 : 6);
            return;
        }
        if (i8 == 4) {
            h0(v6, c.a.f7131w, this.f4190b ? 3 : 6);
        } else {
            if (i8 != 6) {
                return;
            }
            h0(v6, c.a.f7132x, 4);
            h0(v6, c.a.f7131w, 3);
        }
    }

    private void D0(int i7) {
        ValueAnimator valueAnimator;
        if (i7 == 2) {
            return;
        }
        boolean z6 = i7 == 3;
        if (this.f4209u != z6) {
            this.f4209u = z6;
            if (this.f4198j == null || (valueAnimator = this.f4211w) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.f4211w.reverse();
                return;
            }
            float f7 = z6 ? 0.0f : 1.0f;
            this.f4211w.setFloatValues(1.0f - f7, f7);
            this.f4211w.start();
        }
    }

    private void E0(boolean z6) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z6) {
                if (this.V != null) {
                    return;
                } else {
                    this.V = new HashMap(childCount);
                }
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                if (childAt != this.O.get()) {
                    if (z6) {
                        this.V.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.f4191c) {
                            u.t0(childAt, 4);
                        }
                    } else if (this.f4191c && (map = this.V) != null && map.containsKey(childAt)) {
                        u.t0(childAt, this.V.get(childAt).intValue());
                    }
                }
            }
            if (!z6) {
                this.V = null;
            } else if (this.f4191c) {
                this.O.get().sendAccessibilityEvent(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0(boolean z6) {
        V v6;
        if (this.O != null) {
            T();
            if (this.G != 4 || (v6 = this.O.get()) == null) {
                return;
            }
            if (z6) {
                z0(this.G);
            } else {
                v6.requestLayout();
            }
        }
    }

    private int R(V v6, int i7, int i8) {
        return u.b(v6, v6.getResources().getString(i7), W(i8));
    }

    private void T() {
        int iV = V();
        if (this.f4190b) {
            this.B = Math.max(this.N - iV, this.f4213y);
        } else {
            this.B = this.N - iV;
        }
    }

    private void U() {
        this.f4214z = (int) (this.N * (1.0f - this.A));
    }

    private int V() {
        int i7;
        return this.f4194f ? Math.min(Math.max(this.f4195g, this.N - ((this.M * 9) / 16)), this.L) + this.f4206r : (this.f4201m || this.f4202n || (i7 = this.f4200l) <= 0) ? this.f4193e + this.f4206r : Math.max(this.f4193e, i7 + this.f4196h);
    }

    private j0.f W(int i7) {
        return new f(i7);
    }

    private void X(Context context, AttributeSet attributeSet, boolean z6) {
        Y(context, attributeSet, z6, null);
    }

    private void Y(Context context, AttributeSet attributeSet, boolean z6, ColorStateList colorStateList) {
        if (this.f4197i) {
            this.f4208t = e3.k.e(context, attributeSet, o2.b.bottomSheetStyle, Y).m();
            e3.g gVar = new e3.g(this.f4208t);
            this.f4198j = gVar;
            gVar.M(context);
            if (z6 && colorStateList != null) {
                this.f4198j.X(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
            this.f4198j.setTint(typedValue.data);
        }
    }

    private void Z() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f4211w = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f4211w.addUpdateListener(new c());
    }

    public static <V extends View> BottomSheetBehavior<V> c0(V v6) {
        ViewGroup.LayoutParams layoutParams = v6.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.f)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.c cVarF = ((CoordinatorLayout.f) layoutParams).f();
        if (cVarF instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) cVarF;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private float f0() {
        VelocityTracker velocityTracker = this.R;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f4192d);
        return this.R.getYVelocity(this.S);
    }

    private void h0(V v6, c.a aVar, int i7) {
        u.g0(v6, aVar, null, W(i7));
    }

    private void i0() {
        this.S = -1;
        VelocityTracker velocityTracker = this.R;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.R = null;
        }
    }

    private void j0(SavedState savedState) {
        int i7 = this.f4189a;
        if (i7 == 0) {
            return;
        }
        if (i7 == -1 || (i7 & 1) == 1) {
            this.f4193e = savedState.f4216i;
        }
        if (i7 == -1 || (i7 & 2) == 2) {
            this.f4190b = savedState.f4217j;
        }
        if (i7 == -1 || (i7 & 4) == 4) {
            this.D = savedState.f4218k;
        }
        if (i7 == -1 || (i7 & 8) == 8) {
            this.E = savedState.f4219l;
        }
    }

    private void x0(View view) {
        boolean z6 = (Build.VERSION.SDK_INT < 29 || g0() || this.f4194f) ? false : true;
        if (this.f4202n || this.f4203o || this.f4204p || z6) {
            l.a(view, new d(z6));
        }
    }

    private void z0(int i7) {
        V v6 = this.O.get();
        if (v6 == null) {
            return;
        }
        ViewParent parent = v6.getParent();
        if (parent != null && parent.isLayoutRequested() && u.O(v6)) {
            v6.post(new b(v6, i7));
        } else {
            y0(v6, i7);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7, int i8) {
        this.J = 0;
        this.K = false;
        return (i7 & 2) != 0;
    }

    boolean A0(View view, float f7) {
        if (this.E) {
            return true;
        }
        if (view.getTop() < this.B) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f7 * 0.1f)) - ((float) this.B)) / ((float) V()) > 0.5f;
    }

    void B0(View view, int i7, int i8, boolean z6) {
        m0.c cVar = this.H;
        if (!(cVar != null && (!z6 ? !cVar.H(view, view.getLeft(), i8) : !cVar.F(view.getLeft(), i8)))) {
            w0(i7);
            return;
        }
        w0(2);
        D0(i7);
        if (this.f4210v == null) {
            this.f4210v = new h(view, i7);
        }
        if (((h) this.f4210v).f4233g) {
            this.f4210v.f4234h = i7;
            return;
        }
        BottomSheetBehavior<V>.h hVar = this.f4210v;
        hVar.f4234h = i7;
        u.c0(view, hVar);
        ((h) this.f4210v).f4233g = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void C(CoordinatorLayout coordinatorLayout, V v6, View view, int i7) {
        int iD0;
        int i8 = 3;
        if (v6.getTop() == d0()) {
            w0(3);
            return;
        }
        WeakReference<View> weakReference = this.P;
        if (weakReference != null && view == weakReference.get() && this.K) {
            if (this.J > 0) {
                if (this.f4190b) {
                    iD0 = this.f4213y;
                } else {
                    int top = v6.getTop();
                    int i9 = this.f4214z;
                    if (top > i9) {
                        iD0 = i9;
                        i8 = 6;
                    } else {
                        iD0 = d0();
                    }
                }
            } else if (this.D && A0(v6, f0())) {
                iD0 = this.N;
                i8 = 5;
            } else if (this.J == 0) {
                int top2 = v6.getTop();
                if (!this.f4190b) {
                    int i10 = this.f4214z;
                    if (top2 < i10) {
                        if (top2 < Math.abs(top2 - this.B)) {
                            iD0 = d0();
                        } else {
                            iD0 = this.f4214z;
                        }
                    } else if (Math.abs(top2 - i10) < Math.abs(top2 - this.B)) {
                        iD0 = this.f4214z;
                    } else {
                        iD0 = this.B;
                        i8 = 4;
                    }
                    i8 = 6;
                } else if (Math.abs(top2 - this.f4213y) < Math.abs(top2 - this.B)) {
                    iD0 = this.f4213y;
                } else {
                    iD0 = this.B;
                    i8 = 4;
                }
            } else {
                if (this.f4190b) {
                    iD0 = this.B;
                } else {
                    int top3 = v6.getTop();
                    if (Math.abs(top3 - this.f4214z) < Math.abs(top3 - this.B)) {
                        iD0 = this.f4214z;
                        i8 = 6;
                    } else {
                        iD0 = this.B;
                    }
                }
                i8 = 4;
            }
            B0(v6, i8, iD0, false);
            this.K = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
        if (!v6.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.G == 1 && actionMasked == 0) {
            return true;
        }
        m0.c cVar = this.H;
        if (cVar != null) {
            cVar.z(motionEvent);
        }
        if (actionMasked == 0) {
            i0();
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (this.H != null && actionMasked == 2 && !this.I && Math.abs(this.T - motionEvent.getY()) > this.H.u()) {
            this.H.b(v6, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.I;
    }

    public void S(g gVar) {
        if (this.Q.contains(gVar)) {
            return;
        }
        this.Q.add(gVar);
    }

    void a0(int i7) {
        float f7;
        float fD0;
        V v6 = this.O.get();
        if (v6 == null || this.Q.isEmpty()) {
            return;
        }
        int i8 = this.B;
        if (i7 > i8 || i8 == d0()) {
            int i9 = this.B;
            f7 = i9 - i7;
            fD0 = this.N - i9;
        } else {
            int i10 = this.B;
            f7 = i10 - i7;
            fD0 = i10 - d0();
        }
        float f8 = f7 / fD0;
        for (int i11 = 0; i11 < this.Q.size(); i11++) {
            this.Q.get(i11).a(v6, f8);
        }
    }

    View b0(View view) {
        if (u.Q(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View viewB0 = b0(viewGroup.getChildAt(i7));
            if (viewB0 != null) {
                return viewB0;
            }
        }
        return null;
    }

    public int d0() {
        if (this.f4190b) {
            return this.f4213y;
        }
        return Math.max(this.f4212x, this.f4205q ? 0 : this.f4207s);
    }

    public int e0() {
        return this.G;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void g(CoordinatorLayout.f fVar) {
        super.g(fVar);
        this.O = null;
        this.H = null;
    }

    public boolean g0() {
        return this.f4201m;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void j() {
        super.j();
        this.O = null;
        this.H = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
        m0.c cVar;
        if (!v6.isShown() || !this.F) {
            this.I = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            i0();
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x6 = (int) motionEvent.getX();
            this.T = (int) motionEvent.getY();
            if (this.G != 2) {
                WeakReference<View> weakReference = this.P;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.B(view, x6, this.T)) {
                    this.S = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.U = true;
                }
            }
            this.I = this.S == -1 && !coordinatorLayout.B(v6, x6, this.T);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.U = false;
            this.S = -1;
            if (this.I) {
                this.I = false;
                return false;
            }
        }
        if (!this.I && (cVar = this.H) != null && cVar.G(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.P;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.I || this.G == 1 || coordinatorLayout.B(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.H == null || Math.abs(((float) this.T) - motionEvent.getY()) <= ((float) this.H.u())) ? false : true;
    }

    public void k0(boolean z6) {
        this.F = z6;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        e3.g gVar;
        if (u.v(coordinatorLayout) && !u.v(v6)) {
            v6.setFitsSystemWindows(true);
        }
        if (this.O == null) {
            this.f4195g = coordinatorLayout.getResources().getDimensionPixelSize(o2.d.design_bottom_sheet_peek_height_min);
            x0(v6);
            this.O = new WeakReference<>(v6);
            if (this.f4197i && (gVar = this.f4198j) != null) {
                u.n0(v6, gVar);
            }
            e3.g gVar2 = this.f4198j;
            if (gVar2 != null) {
                float fT = this.C;
                if (fT == -1.0f) {
                    fT = u.t(v6);
                }
                gVar2.W(fT);
                boolean z6 = this.G == 3;
                this.f4209u = z6;
                this.f4198j.Y(z6 ? 0.0f : 1.0f);
            }
            C0();
            if (u.w(v6) == 0) {
                u.t0(v6, 1);
            }
            int measuredWidth = v6.getMeasuredWidth();
            int i8 = this.f4199k;
            if (measuredWidth > i8 && i8 != -1) {
                ViewGroup.LayoutParams layoutParams = v6.getLayoutParams();
                layoutParams.width = this.f4199k;
                v6.post(new a(v6, layoutParams));
            }
        }
        if (this.H == null) {
            this.H = m0.c.m(coordinatorLayout, this.X);
        }
        int top = v6.getTop();
        coordinatorLayout.I(v6, i7);
        this.M = coordinatorLayout.getWidth();
        this.N = coordinatorLayout.getHeight();
        int height = v6.getHeight();
        this.L = height;
        int i9 = this.N;
        int i10 = i9 - height;
        int i11 = this.f4207s;
        if (i10 < i11) {
            if (this.f4205q) {
                this.L = i9;
            } else {
                this.L = i9 - i11;
            }
        }
        this.f4213y = Math.max(0, i9 - this.L);
        U();
        T();
        int i12 = this.G;
        if (i12 == 3) {
            u.V(v6, d0());
        } else if (i12 == 6) {
            u.V(v6, this.f4214z);
        } else if (this.D && i12 == 5) {
            u.V(v6, this.N);
        } else if (i12 == 4) {
            u.V(v6, this.B);
        } else if (i12 == 1 || i12 == 2) {
            u.V(v6, top - v6.getTop());
        }
        this.P = new WeakReference<>(b0(v6));
        return true;
    }

    public void l0(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.f4212x = i7;
    }

    public void m0(boolean z6) {
        if (this.f4190b == z6) {
            return;
        }
        this.f4190b = z6;
        if (this.O != null) {
            T();
        }
        w0((this.f4190b && this.G == 6) ? 3 : this.G);
        C0();
    }

    public void n0(boolean z6) {
        this.f4201m = z6;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean o(CoordinatorLayout coordinatorLayout, V v6, View view, float f7, float f8) {
        WeakReference<View> weakReference = this.P;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.G != 3 || super.o(coordinatorLayout, v6, view, f7, f8);
    }

    public void o0(float f7) {
        if (f7 <= 0.0f || f7 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.A = f7;
        if (this.O != null) {
            U();
        }
    }

    public void p0(boolean z6) {
        if (this.D != z6) {
            this.D = z6;
            if (!z6 && this.G == 5) {
                v0(4);
            }
            C0();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int[] iArr, int i9) {
        if (i9 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.P;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v6.getTop();
        int i10 = top - i8;
        if (i8 > 0) {
            if (i10 < d0()) {
                iArr[1] = top - d0();
                u.V(v6, -iArr[1]);
                w0(3);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i8;
                u.V(v6, -i8);
                w0(1);
            }
        } else if (i8 < 0 && !view.canScrollVertically(-1)) {
            int i11 = this.B;
            if (i10 > i11 && !this.D) {
                iArr[1] = top - i11;
                u.V(v6, -iArr[1]);
                w0(4);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i8;
                u.V(v6, -i8);
                w0(1);
            }
        }
        a0(v6.getTop());
        this.J = i8;
        this.K = true;
    }

    public void q0(int i7) {
        this.f4199k = i7;
    }

    public void r0(int i7) {
        s0(i7, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s0(int r4, boolean r5) {
        /*
            r3 = this;
            r0 = 1
            r1 = 0
            r2 = -1
            if (r4 != r2) goto Lc
            boolean r4 = r3.f4194f
            if (r4 != 0) goto L15
            r3.f4194f = r0
            goto L1f
        Lc:
            boolean r2 = r3.f4194f
            if (r2 != 0) goto L17
            int r2 = r3.f4193e
            if (r2 == r4) goto L15
            goto L17
        L15:
            r0 = r1
            goto L1f
        L17:
            r3.f4194f = r1
            int r4 = java.lang.Math.max(r1, r4)
            r3.f4193e = r4
        L1f:
            if (r0 == 0) goto L24
            r3.F0(r5)
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.s0(int, boolean):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
    }

    public void t0(int i7) {
        this.f4189a = i7;
    }

    public void u0(boolean z6) {
        this.E = z6;
    }

    public void v0(int i7) {
        if (i7 == this.G) {
            return;
        }
        if (this.O != null) {
            z0(i7);
            return;
        }
        if (i7 == 4 || i7 == 3 || i7 == 6 || (this.D && i7 == 5)) {
            this.G = i7;
        }
    }

    void w0(int i7) {
        V v6;
        if (this.G == i7) {
            return;
        }
        this.G = i7;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v6 = weakReference.get()) == null) {
            return;
        }
        if (i7 == 3) {
            E0(true);
        } else if (i7 == 6 || i7 == 5 || i7 == 4) {
            E0(false);
        }
        D0(i7);
        for (int i8 = 0; i8 < this.Q.size(); i8++) {
            this.Q.get(i8).b(v6, i7);
        }
        C0();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v6, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.x(coordinatorLayout, v6, savedState.b());
        j0(savedState);
        int i7 = savedState.f4215h;
        if (i7 == 1 || i7 == 2) {
            this.G = 4;
        } else {
            this.G = i7;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v6) {
        return new SavedState(super.y(coordinatorLayout, v6), (BottomSheetBehavior<?>) this);
    }

    void y0(View view, int i7) {
        int iD0;
        int i8;
        if (i7 == 4) {
            iD0 = this.B;
        } else if (i7 == 6) {
            int i9 = this.f4214z;
            if (!this.f4190b || i9 > (i8 = this.f4213y)) {
                iD0 = i9;
            } else {
                i7 = 3;
                iD0 = i8;
            }
        } else if (i7 == 3) {
            iD0 = d0();
        } else {
            if (!this.D || i7 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i7);
            }
            iD0 = this.N;
        }
        B0(view, i7, iD0, false);
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final int f4215h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f4216i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f4217j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        boolean f4218k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        boolean f4219l;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4215h = parcel.readInt();
            this.f4216i = parcel.readInt();
            this.f4217j = parcel.readInt() == 1;
            this.f4218k = parcel.readInt() == 1;
            this.f4219l = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f4215h);
            parcel.writeInt(this.f4216i);
            parcel.writeInt(this.f4217j ? 1 : 0);
            parcel.writeInt(this.f4218k ? 1 : 0);
            parcel.writeInt(this.f4219l ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f4215h = bottomSheetBehavior.G;
            this.f4216i = ((BottomSheetBehavior) bottomSheetBehavior).f4193e;
            this.f4217j = ((BottomSheetBehavior) bottomSheetBehavior).f4190b;
            this.f4218k = bottomSheetBehavior.D;
            this.f4219l = ((BottomSheetBehavior) bottomSheetBehavior).E;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i7;
        super(context, attributeSet);
        this.f4189a = 0;
        this.f4190b = true;
        this.f4191c = false;
        this.f4199k = -1;
        this.f4210v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new e();
        this.f4196h = context.getResources().getDimensionPixelSize(o2.d.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o2.l.BottomSheetBehavior_Layout);
        this.f4197i = typedArrayObtainStyledAttributes.hasValue(o2.l.BottomSheetBehavior_Layout_shapeAppearance);
        int i8 = o2.l.BottomSheetBehavior_Layout_backgroundTint;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i8);
        if (zHasValue) {
            Y(context, attributeSet, zHasValue, b3.c.a(context, typedArrayObtainStyledAttributes, i8));
        } else {
            X(context, attributeSet, zHasValue);
        }
        Z();
        this.C = typedArrayObtainStyledAttributes.getDimension(o2.l.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        int i9 = o2.l.BottomSheetBehavior_Layout_android_maxWidth;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            q0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i9, -1));
        }
        int i10 = o2.l.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i10);
        if (typedValuePeekValue != null && (i7 = typedValuePeekValue.data) == -1) {
            r0(i7);
        } else {
            r0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1));
        }
        p0(typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_behavior_hideable, false));
        n0(typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        m0(typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        u0(typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        k0(typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_behavior_draggable, true));
        t0(typedArrayObtainStyledAttributes.getInt(o2.l.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        o0(typedArrayObtainStyledAttributes.getFloat(o2.l.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i11 = o2.l.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i11);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            l0(typedValuePeekValue2.data);
        } else {
            l0(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i11, 0));
        }
        this.f4202n = typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.f4203o = typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.f4204p = typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.f4205q = typedArrayObtainStyledAttributes.getBoolean(o2.l.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        typedArrayObtainStyledAttributes.recycle();
        this.f4192d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
