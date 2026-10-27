package com.google.android.material.bottomsheet;

import a.g.k.b0;
import a.g.k.c0.c;
import a.g.k.c0.f;
import a.g.k.t;
import a.i.b.c;
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
import b.a.a.a.k;
import b.a.a.a.l;
import com.google.android.material.internal.j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f3006a = k.f;
    int A;
    a.i.b.c B;
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
    private final c.AbstractC0020c Q;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3009d;
    private float e;
    private int f;
    private boolean g;
    private int h;
    private int i;
    private boolean j;
    private b.a.a.a.b0.g k;
    private int l;
    private boolean m;
    private b.a.a.a.b0.k n;
    private boolean o;
    private BottomSheetBehavior<V>.h p;
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
        final /* synthetic */ View f3010b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3011c;

        a(View view, int i) {
            this.f3010b = view;
            this.f3011c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.m0(this.f3010b, this.f3011c);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.k != null) {
                BottomSheetBehavior.this.k.X(fFloatValue);
            }
        }
    }

    class c implements j.c {
        c() {
        }

        @Override // com.google.android.material.internal.j.c
        public b0 a(View view, b0 b0Var, j.d dVar) {
            BottomSheetBehavior.this.l = b0Var.d().e;
            BottomSheetBehavior.this.t0(false);
            return b0Var;
        }
    }

    class d extends c.AbstractC0020c {
        d() {
        }

        private boolean n(View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.H + bottomSheetBehavior.U()) / 2;
        }

        @Override // a.i.b.c.AbstractC0020c
        public int a(View view, int i, int i2) {
            return view.getLeft();
        }

        @Override // a.i.b.c.AbstractC0020c
        public int b(View view, int i, int i2) {
            int iU = BottomSheetBehavior.this.U();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return a.g.f.a.b(i, iU, bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v);
        }

        @Override // a.i.b.c.AbstractC0020c
        public int e(View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.x ? bottomSheetBehavior.H : bottomSheetBehavior.v;
        }

        @Override // a.i.b.c.AbstractC0020c
        public void j(int i) {
            if (i == 1 && BottomSheetBehavior.this.z) {
                BottomSheetBehavior.this.k0(1);
            }
        }

        @Override // a.i.b.c.AbstractC0020c
        public void k(View view, int i, int i2, int i3, int i4) {
            BottomSheetBehavior.this.S(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
        
            if (java.lang.Math.abs(r7.getTop() - r6.f3015a.r) < java.lang.Math.abs(r7.getTop() - r6.f3015a.t)) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0077, code lost:
        
            r8 = r6.f3015a.r;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00b1, code lost:
        
            if (java.lang.Math.abs(r8 - r6.f3015a.t) < java.lang.Math.abs(r8 - r6.f3015a.v)) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00d8, code lost:
        
            if (java.lang.Math.abs(r8 - r6.f3015a.s) < java.lang.Math.abs(r8 - r6.f3015a.v)) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00ea, code lost:
        
            if (r8 < java.lang.Math.abs(r8 - r9.v)) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00fc, code lost:
        
            if (java.lang.Math.abs(r8 - r0) < java.lang.Math.abs(r8 - r6.f3015a.v)) goto L39;
         */
        @Override // a.i.b.c.AbstractC0020c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void l(android.view.View r7, float r8, float r9) {
            /*
                Method dump skipped, instruction units count: 262
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.d.l(android.view.View, float, float):void");
        }

        @Override // a.i.b.c.AbstractC0020c
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
    }

    class e implements a.g.k.c0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f3016a;

        e(int i) {
            this.f3016a = i;
        }

        @Override // a.g.k.c0.f
        public boolean a(View view, f.a aVar) {
            BottomSheetBehavior.this.j0(this.f3016a);
            return true;
        }
    }

    public static abstract class f {
        public abstract void a(View view, float f);

        public abstract void b(View view, int i);
    }

    protected static class g extends a.i.a.a {
        public static final Parcelable.Creator<g> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f3018d;
        int e;
        boolean f;
        boolean g;
        boolean h;

        static class a implements Parcelable.ClassLoaderCreator<g> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new g(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        public g(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3018d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readInt() == 1;
            this.g = parcel.readInt() == 1;
            this.h = parcel.readInt() == 1;
        }

        public g(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f3018d = bottomSheetBehavior.A;
            this.e = ((BottomSheetBehavior) bottomSheetBehavior).f;
            this.f = ((BottomSheetBehavior) bottomSheetBehavior).f3008c;
            this.g = bottomSheetBehavior.x;
            this.h = ((BottomSheetBehavior) bottomSheetBehavior).y;
        }

        @Override // a.i.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3018d);
            parcel.writeInt(this.e);
            parcel.writeInt(this.f ? 1 : 0);
            parcel.writeInt(this.g ? 1 : 0);
            parcel.writeInt(this.h ? 1 : 0);
        }
    }

    private class h implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f3019b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f3020c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3021d;

        h(View view, int i) {
            this.f3019b = view;
            this.f3021d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.i.b.c cVar = BottomSheetBehavior.this.B;
            if (cVar == null || !cVar.k(true)) {
                BottomSheetBehavior.this.k0(this.f3021d);
            } else {
                t.Z(this.f3019b, this);
            }
            this.f3020c = false;
        }
    }

    public BottomSheetBehavior() {
        this.f3007b = 0;
        this.f3008c = true;
        this.f3009d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        this.f3007b = 0;
        this.f3008c = true;
        this.f3009d = false;
        this.p = null;
        this.u = 0.5f;
        this.w = -1.0f;
        this.z = true;
        this.A = 4;
        this.K = new ArrayList<>();
        this.Q = new d();
        this.i = context.getResources().getDimensionPixelSize(b.a.a.a.d.u);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.E);
        this.j = typedArrayObtainStyledAttributes.hasValue(l.Q);
        int i2 = l.G;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i2);
        if (zHasValue) {
            Q(context, attributeSet, zHasValue, b.a.a.a.y.c.a(context, typedArrayObtainStyledAttributes, i2));
        } else {
            P(context, attributeSet, zHasValue);
        }
        R();
        if (Build.VERSION.SDK_INT >= 21) {
            this.w = typedArrayObtainStyledAttributes.getDimension(l.F, -1.0f);
        }
        int i3 = l.M;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i3);
        if (typedValuePeekValue == null || (i = typedValuePeekValue.data) != -1) {
            f0(typedArrayObtainStyledAttributes.getDimensionPixelSize(i3, -1));
        } else {
            f0(i);
        }
        e0(typedArrayObtainStyledAttributes.getBoolean(l.L, false));
        c0(typedArrayObtainStyledAttributes.getBoolean(l.P, false));
        b0(typedArrayObtainStyledAttributes.getBoolean(l.J, true));
        i0(typedArrayObtainStyledAttributes.getBoolean(l.O, false));
        Z(typedArrayObtainStyledAttributes.getBoolean(l.H, true));
        h0(typedArrayObtainStyledAttributes.getInt(l.N, 0));
        d0(typedArrayObtainStyledAttributes.getFloat(l.K, 0.5f));
        int i4 = l.I;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i4);
        a0((typedValuePeekValue2 == null || typedValuePeekValue2.type != 16) ? typedArrayObtainStyledAttributes.getDimensionPixelOffset(i4, 0) : typedValuePeekValue2.data);
        typedArrayObtainStyledAttributes.recycle();
        this.e = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private void L(V v, c.a aVar, int i) {
        t.d0(v, aVar, null, new e(i));
    }

    private void M() {
        int iO = O();
        if (this.f3008c) {
            this.v = Math.max(this.H - iO, this.s);
        } else {
            this.v = this.H - iO;
        }
    }

    private void N() {
        this.t = (int) (this.H * (1.0f - this.u));
    }

    private int O() {
        int i;
        return this.g ? Math.min(Math.max(this.h, this.H - ((this.G * 9) / 16)), this.F) : (this.m || (i = this.l) <= 0) ? this.f : Math.max(this.f, i + this.i);
    }

    private void P(Context context, AttributeSet attributeSet, boolean z) {
        Q(context, attributeSet, z, null);
    }

    private void Q(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.j) {
            this.n = b.a.a.a.b0.k.e(context, attributeSet, b.a.a.a.b.f1644c, f3006a).m();
            b.a.a.a.b0.g gVar = new b.a.a.a.b0.g(this.n);
            this.k = gVar;
            gVar.M(context);
            if (z && colorStateList != null) {
                this.k.W(colorStateList);
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
        velocityTracker.computeCurrentVelocity(1000, this.e);
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

    private void Y(g gVar) {
        int i = this.f3007b;
        if (i == 0) {
            return;
        }
        if (i == -1 || (i & 1) == 1) {
            this.f = gVar.e;
        }
        if (i == -1 || (i & 2) == 2) {
            this.f3008c = gVar.f;
        }
        if (i == -1 || (i & 4) == 4) {
            this.x = gVar.g;
        }
        if (i == -1 || (i & 8) == 8) {
            this.y = gVar.h;
        }
    }

    private void l0(View view) {
        if (Build.VERSION.SDK_INT < 29 || W() || this.g) {
            return;
        }
        j.a(view, new c());
    }

    private void n0(int i) {
        V v = this.I.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && t.M(v)) {
            v.post(new a(v, i));
        } else {
            m0(v, i);
        }
    }

    private void q0() {
        V v;
        int i;
        c.a aVar;
        WeakReference<V> weakReference = this.I;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        t.b0(v, 524288);
        t.b0(v, 262144);
        t.b0(v, 1048576);
        if (this.x && this.A != 5) {
            L(v, c.a.u, 5);
        }
        int i2 = this.A;
        if (i2 == 3) {
            i = this.f3008c ? 4 : 6;
            aVar = c.a.t;
        } else {
            if (i2 != 4) {
                if (i2 != 6) {
                    return;
                }
                L(v, c.a.t, 4);
                L(v, c.a.s, 3);
                return;
            }
            i = this.f3008c ? 3 : 6;
            aVar = c.a.s;
        }
        L(v, aVar, i);
    }

    private void r0(int i) {
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

    private void s0(boolean z) {
        Map<View, Integer> map;
        int iIntValue;
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
                        if (this.f3009d) {
                            iIntValue = 4;
                            t.q0(childAt, iIntValue);
                        }
                    } else if (this.f3009d && (map = this.P) != null && map.containsKey(childAt)) {
                        iIntValue = this.P.get(childAt).intValue();
                        t.q0(childAt, iIntValue);
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
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.D = 0;
        this.E = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0078, code lost:
    
        if (r3 < java.lang.Math.abs(r3 - r2.v)) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0088, code lost:
    
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.v)) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a6, code lost:
    
        if (java.lang.Math.abs(r3 - r2.t) < java.lang.Math.abs(r3 - r2.v)) goto L47;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(androidx.coordinatorlayout.widget.CoordinatorLayout r3, V r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.U()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.k0(r0)
            return
        Lf:
            java.lang.ref.WeakReference<android.view.View> r3 = r2.J
            if (r3 == 0) goto Lb1
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb1
            boolean r3 = r2.E
            if (r3 != 0) goto L1f
            goto Lb1
        L1f:
            int r3 = r2.D
            r5 = 4
            r6 = 6
            if (r3 <= 0) goto L3c
            boolean r3 = r2.f3008c
            if (r3 == 0) goto L2d
        L29:
            int r3 = r2.s
            goto Lab
        L2d:
            int r3 = r4.getTop()
            int r5 = r2.t
            if (r3 <= r5) goto L38
            r3 = r5
            goto Laa
        L38:
            int r3 = r2.r
            goto Lab
        L3c:
            boolean r3 = r2.x
            if (r3 == 0) goto L4e
            float r3 = r2.V()
            boolean r3 = r2.o0(r4, r3)
            if (r3 == 0) goto L4e
            int r3 = r2.H
            r0 = 5
            goto Lab
        L4e:
            int r3 = r2.D
            if (r3 != 0) goto L8b
            int r3 = r4.getTop()
            boolean r1 = r2.f3008c
            if (r1 == 0) goto L6c
            int r6 = r2.s
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            int r1 = r2.v
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r6 >= r3) goto L8f
            goto L29
        L6c:
            int r1 = r2.t
            if (r3 >= r1) goto L7b
            int r5 = r2.v
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            if (r3 >= r5) goto La8
            goto L38
        L7b:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.v
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L8f
            goto La8
        L8b:
            boolean r3 = r2.f3008c
            if (r3 == 0) goto L93
        L8f:
            int r3 = r2.v
            r0 = r5
            goto Lab
        L93:
            int r3 = r4.getTop()
            int r0 = r2.t
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.v
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L8f
        La8:
            int r3 = r2.t
        Laa:
            r0 = r6
        Lab:
            r5 = 0
            r2.p0(r4, r0, r3, r5)
            r2.E = r5
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.C(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
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
        a.i.b.c cVar = this.B;
        if (cVar != null) {
            cVar.z(motionEvent);
        }
        if (actionMasked == 0) {
            X();
        }
        if (this.L == null) {
            this.L = VelocityTracker.obtain();
        }
        this.L.addMovement(motionEvent);
        if (this.B != null && actionMasked == 2 && !this.C && Math.abs(this.N - motionEvent.getY()) > this.B.u()) {
            this.B.b(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.C;
    }

    void S(int i) {
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

    View T(View view) {
        if (t.O(view)) {
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
        return this.f3008c ? this.s : this.r;
    }

    public boolean W() {
        return this.m;
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
        if (this.f3008c == z) {
            return;
        }
        this.f3008c = z;
        if (this.I != null) {
            M();
        }
        k0((this.f3008c && this.A == 6) ? 3 : this.A);
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
        this.f3007b = i;
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
        a.i.b.c cVar;
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
                if (view != null && coordinatorLayout.B(view, x, this.N)) {
                    this.M = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.O = true;
                }
            }
            this.C = this.M == -1 && !coordinatorLayout.B(v, x, this.N);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.O = false;
            this.M = -1;
            if (this.C) {
                this.C = false;
                return false;
            }
        }
        if (!this.C && (cVar = this.B) != null && cVar.G(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.J;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.C || this.A == 1 || coordinatorLayout.B(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.B == null || Math.abs(((float) this.N) - motionEvent.getY()) <= ((float) this.B.u())) ? false : true;
    }

    void k0(int i) {
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
        int iU;
        b.a.a.a.b0.g gVar;
        if (t.v(coordinatorLayout) && !t.v(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.I == null) {
            this.h = coordinatorLayout.getResources().getDimensionPixelSize(b.a.a.a.d.f1702b);
            l0(v);
            this.I = new WeakReference<>(v);
            if (this.j && (gVar = this.k) != null) {
                t.k0(v, gVar);
            }
            b.a.a.a.b0.g gVar2 = this.k;
            if (gVar2 != null) {
                float fT = this.w;
                if (fT == -1.0f) {
                    fT = t.t(v);
                }
                gVar2.V(fT);
                boolean z = this.A == 3;
                this.o = z;
                this.k.X(z ? 0.0f : 1.0f);
            }
            q0();
            if (t.w(v) == 0) {
                t.q0(v, 1);
            }
        }
        if (this.B == null) {
            this.B = a.i.b.c.m(coordinatorLayout, this.Q);
        }
        int top = v.getTop();
        coordinatorLayout.I(v, i);
        this.G = coordinatorLayout.getWidth();
        this.H = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.F = height;
        this.s = Math.max(0, this.H - height);
        N();
        M();
        int i2 = this.A;
        if (i2 == 3) {
            iU = U();
        } else if (i2 == 6) {
            iU = this.t;
        } else if (this.x && i2 == 5) {
            iU = this.H;
        } else {
            if (i2 != 4) {
                if (i2 == 1 || i2 == 2) {
                    t.T(v, top - v.getTop());
                }
                this.J = new WeakReference<>(T(v));
                return true;
            }
            iU = this.v;
        }
        t.T(v, iU);
        this.J = new WeakReference<>(T(v));
        return true;
    }

    void m0(View view, int i) {
        int iU;
        int i2;
        if (i == 4) {
            iU = this.v;
        } else if (i == 6) {
            int i3 = this.t;
            if (!this.f3008c || i3 > (i2 = this.s)) {
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

    void p0(View view, int i, int i2, boolean z) {
        a.i.b.c cVar = this.B;
        if (!(cVar != null && (!z ? !cVar.H(view, view.getLeft(), i2) : !cVar.F(view.getLeft(), i2)))) {
            k0(i);
            return;
        }
        k0(2);
        r0(i);
        if (this.p == null) {
            this.p = new h(view, i);
        }
        if (((h) this.p).f3020c) {
            this.p.f3021d = i;
            return;
        }
        BottomSheetBehavior<V>.h hVar = this.p;
        hVar.f3021d = i;
        t.Z(view, hVar);
        ((h) this.p).f3020c = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void q(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        int i4;
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.J;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i5 = top - i2;
        if (i2 > 0) {
            if (i5 < U()) {
                iArr[1] = top - U();
                t.T(v, -iArr[1]);
                i4 = 3;
                k0(i4);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i2;
                t.T(v, -i2);
                k0(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            int i6 = this.v;
            if (i5 > i6 && !this.x) {
                iArr[1] = top - i6;
                t.T(v, -iArr[1]);
                i4 = 4;
                k0(i4);
            } else {
                if (!this.z) {
                    return;
                }
                iArr[1] = i2;
                t.T(v, -i2);
                k0(1);
            }
        }
        S(v.getTop());
        this.D = i2;
        this.E = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void x(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        g gVar = (g) parcelable;
        super.x(coordinatorLayout, v, gVar.u());
        Y(gVar);
        int i = gVar.f3018d;
        if (i == 1 || i == 2) {
            i = 4;
        }
        this.A = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public Parcelable y(CoordinatorLayout coordinatorLayout, V v) {
        return new g(super.y(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }
}
