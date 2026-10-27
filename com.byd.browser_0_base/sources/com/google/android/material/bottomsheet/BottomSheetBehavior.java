package com.google.android.material.bottomsheet;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
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
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.fi;
import defpackage.gi;
import defpackage.gq2;
import defpackage.hi;
import defpackage.in3;
import defpackage.n3;
import defpackage.om3;
import defpackage.qm3;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uj2;
import defpackage.yn3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int c0 = R$style.Widget_Design_BottomSheet_Modal;
    public int A;
    public int B;
    public int C;
    public float D;
    public int E;
    public final float F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f28J;
    public qm3 K;
    public boolean L;
    public int M;
    public boolean N;
    public final float O;
    public int P;
    public int Q;
    public int R;
    public WeakReference<V> S;
    public WeakReference<View> T;
    public final ArrayList<c> U;
    public VelocityTracker V;
    public int W;
    public int X;
    public boolean Y;
    public HashMap Z;
    public int a;
    public int a0;
    public boolean b;
    public final b b0;
    public final float c;
    public int d;
    public boolean e;
    public int f;
    public final int g;
    public sc1 h;
    public final ColorStateList i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public int u;
    public int v;
    public final uj2 w;
    public boolean x;
    public final BottomSheetBehavior<V>.d y;
    public ValueAnimator z;

    public class a implements Runnable {
        public final /* synthetic */ View e;
        public final /* synthetic */ int f;

        public a(View view, int i) {
            this.e = view;
            this.f = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = BottomSheetBehavior.c0;
            BottomSheetBehavior.this.F(this.e, this.f, false);
        }
    }

    public class b extends qm3.c {
        public b() {
        }

        @Override // qm3.c
        public final int a(View view, int i) {
            return view.getLeft();
        }

        @Override // qm3.c
        public final int b(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return n3.w(i, bottomSheetBehavior.y(), bottomSheetBehavior.G ? bottomSheetBehavior.R : bottomSheetBehavior.E);
        }

        @Override // qm3.c
        public final int d() {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.G ? bottomSheetBehavior.R : bottomSheetBehavior.E;
        }

        @Override // qm3.c
        public final void h(int i) {
            if (i == 1) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.I) {
                    bottomSheetBehavior.D(1);
                }
            }
        }

        @Override // qm3.c
        public final void i(View view, int i, int i2) {
            BottomSheetBehavior.this.u(i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00d4  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00d6  */
        @Override // qm3.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void j(android.view.View r4, float r5, float r6) {
            /*
                Method dump skipped, instruction units count: 222
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.b.j(android.view.View, float, float):void");
        }

        @Override // qm3.c
        public final boolean k(View view, int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i2 = bottomSheetBehavior.f28J;
            if (i2 == 1 || bottomSheetBehavior.Y) {
                return false;
            }
            if (i2 == 3 && bottomSheetBehavior.W == i) {
                WeakReference<View> weakReference = bottomSheetBehavior.T;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            System.currentTimeMillis();
            WeakReference<V> weakReference2 = bottomSheetBehavior.S;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    public class d {
        public int a;
        public boolean b;
        public final a c = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                d dVar = d.this;
                dVar.b = false;
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                qm3 qm3Var = bottomSheetBehavior.K;
                if (qm3Var != null && qm3Var.h()) {
                    dVar.a(dVar.a);
                } else if (bottomSheetBehavior.f28J == 2) {
                    bottomSheetBehavior.D(dVar.a);
                }
            }
        }

        public d() {
        }

        public final void a(int i) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            WeakReference<V> weakReference = bottomSheetBehavior.S;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.a = i;
            if (this.b) {
                return;
            }
            V v = bottomSheetBehavior.S.get();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.k(v, this.c);
            this.b = true;
        }
    }

    public BottomSheetBehavior() {
        this.a = 0;
        this.b = true;
        this.j = -1;
        this.k = -1;
        this.y = new d();
        this.D = 0.5f;
        this.F = -1.0f;
        this.I = true;
        this.f28J = 4;
        this.O = 0.1f;
        this.U = new ArrayList<>();
        this.a0 = -1;
        this.b0 = new b();
    }

    public static View v(View view) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.i.i(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewV = v(viewGroup.getChildAt(i));
            if (viewV != null) {
                return viewV;
            }
        }
        return null;
    }

    public static BottomSheetBehavior w(FrameLayout frameLayout) {
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.e)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.e) layoutParams).a;
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    public static int x(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    public final void A(boolean z) {
        if (this.G != z) {
            this.G = z;
            if (!z && this.f28J == 5) {
                C(4);
            }
            G();
        }
    }

    public final void B(int i) {
        boolean z = false;
        if (i == -1) {
            if (!this.e) {
                this.e = true;
                z = true;
            }
        } else if (this.e || this.d != i) {
            this.e = false;
            this.d = Math.max(0, i);
            z = true;
        }
        if (z) {
            J();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C(int r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == r0) goto L64
            r1 = 2
            if (r4 != r1) goto L7
            goto L64
        L7:
            boolean r1 = r3.G
            if (r1 != 0) goto L16
            r1 = 5
            if (r4 != r1) goto L16
            java.lang.String r3 = "Cannot set state: "
            java.lang.String r0 = "BottomSheetBehavior"
            defpackage.C0175b.s(r3, r4, r0)
            return
        L16:
            r1 = 6
            if (r4 != r1) goto L27
            boolean r1 = r3.b
            if (r1 == 0) goto L27
            int r1 = r3.z(r4)
            int r2 = r3.B
            if (r1 > r2) goto L27
            r1 = 3
            goto L28
        L27:
            r1 = r4
        L28:
            java.lang.ref.WeakReference<V extends android.view.View> r2 = r3.S
            if (r2 == 0) goto L60
            java.lang.Object r2 = r2.get()
            if (r2 != 0) goto L33
            goto L60
        L33:
            java.lang.ref.WeakReference<V extends android.view.View> r4 = r3.S
            java.lang.Object r4 = r4.get()
            android.view.View r4 = (android.view.View) r4
            com.google.android.material.bottomsheet.BottomSheetBehavior$a r2 = new com.google.android.material.bottomsheet.BottomSheetBehavior$a
            r2.<init>(r4, r1)
            android.view.ViewParent r3 = r4.getParent()
            if (r3 == 0) goto L55
            boolean r3 = r3.isLayoutRequested()
            if (r3 == 0) goto L55
            java.util.WeakHashMap<android.view.View, in3> r3 = defpackage.om3.a
            boolean r3 = om3.g.b(r4)
            if (r3 == 0) goto L55
            goto L56
        L55:
            r0 = 0
        L56:
            if (r0 == 0) goto L5c
            r4.post(r2)
            goto L63
        L5c:
            r2.run()
            goto L63
        L60:
            r3.D(r4)
        L63:
            return
        L64:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "STATE_"
            r1.<init>(r2)
            if (r4 != r0) goto L72
            java.lang.String r4 = "DRAGGING"
            goto L74
        L72:
            java.lang.String r4 = "SETTLING"
        L74:
            java.lang.String r0 = " should not be set externally."
            java.lang.String r4 = defpackage.gq2.i(r1, r4, r0)
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.C(int):void");
    }

    public final void D(int i) {
        V v;
        if (this.f28J == i) {
            return;
        }
        this.f28J = i;
        WeakReference<V> weakReference = this.S;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        int i2 = 0;
        if (i == 3) {
            I(true);
        } else if (i == 6 || i == 5 || i == 4) {
            I(false);
        }
        H(i);
        while (true) {
            ArrayList<c> arrayList = this.U;
            if (i2 >= arrayList.size()) {
                G();
                return;
            } else {
                arrayList.get(i2).c(v, i);
                i2++;
            }
        }
    }

    public final boolean E(View view, float f) {
        if (this.H) {
            return true;
        }
        if (view.getTop() < this.E) {
            return false;
        }
        return Math.abs(((f * this.O) + ((float) view.getTop())) - ((float) this.E)) / ((float) t()) > 0.5f;
    }

    public final void F(View view, int i, boolean z) {
        int iZ = z(i);
        qm3 qm3Var = this.K;
        if (!(qm3Var != null && (!z ? !qm3Var.v(view, view.getLeft(), iZ) : !qm3Var.t(view.getLeft(), iZ)))) {
            D(i);
            return;
        }
        D(2);
        H(i);
        this.y.a(i);
    }

    public final void G() {
        V v;
        WeakReference<V> weakReference = this.S;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        om3.i(v, 524288);
        om3.f(v, 0);
        om3.i(v, 262144);
        om3.f(v, 0);
        om3.i(v, 1048576);
        om3.f(v, 0);
        int i = this.a0;
        if (i != -1) {
            om3.i(v, i);
            om3.f(v, 0);
        }
        if (!this.b && this.f28J != 6) {
            this.a0 = om3.a(v, v.getResources().getString(R$string.bottomsheet_action_expand_halfway), new hi(this, 6));
        }
        if (this.G && this.f28J != 5) {
            om3.j(v, AccessibilityNodeInfoCompat.a.w, null, new hi(this, 5));
        }
        int i2 = this.f28J;
        if (i2 == 3) {
            om3.j(v, AccessibilityNodeInfoCompat.a.v, null, new hi(this, this.b ? 4 : 6));
            return;
        }
        if (i2 == 4) {
            om3.j(v, AccessibilityNodeInfoCompat.a.u, null, new hi(this, this.b ? 3 : 6));
        } else {
            if (i2 != 6) {
                return;
            }
            om3.j(v, AccessibilityNodeInfoCompat.a.v, null, new hi(this, 4));
            om3.j(v, AccessibilityNodeInfoCompat.a.u, null, new hi(this, 3));
        }
    }

    public final void H(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.x != z) {
            this.x = z;
            if (this.h == null || (valueAnimator = this.z) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.z.reverse();
                return;
            }
            float f = z ? 0.0f : 1.0f;
            this.z.setFloatValues(1.0f - f, f);
            this.z.start();
        }
    }

    public final void I(boolean z) {
        WeakReference<V> weakReference = this.S;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.Z != null) {
                    return;
                } else {
                    this.Z = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.S.get() && z) {
                    this.Z.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z) {
                return;
            }
            this.Z = null;
        }
    }

    public final void J() {
        V v;
        if (this.S != null) {
            s();
            if (this.f28J != 4 || (v = this.S.get()) == null) {
                return;
            }
            v.requestLayout();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void c(CoordinatorLayout.e eVar) {
        this.S = null;
        this.K = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void f() {
        this.S = null;
        this.K = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean g(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        qm3 qm3Var;
        if (!v.isShown() || !this.I) {
            this.L = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.W = -1;
            VelocityTracker velocityTracker = this.V;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.V = null;
            }
        }
        if (this.V == null) {
            this.V = VelocityTracker.obtain();
        }
        this.V.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.X = (int) motionEvent.getY();
            if (this.f28J != 2) {
                WeakReference<View> weakReference = this.T;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.u(view, x, this.X)) {
                    this.W = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.Y = true;
                }
            }
            this.L = this.W == -1 && !coordinatorLayout.u(v, x, this.X);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.Y = false;
            this.W = -1;
            if (this.L) {
                this.L = false;
                return false;
            }
        }
        if (!this.L && (qm3Var = this.K) != null && qm3Var.u(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.T;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.L || this.f28J == 1 || coordinatorLayout.u(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.K == null || Math.abs(((float) this.X) - motionEvent.getY()) <= ((float) this.K.b)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean h(CoordinatorLayout coordinatorLayout, V v, int i) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.d.a(coordinatorLayout) && !om3.d.a(v)) {
            v.setFitsSystemWindows(true);
        }
        int i2 = 0;
        if (this.S == null) {
            this.f = coordinatorLayout.getResources().getDimensionPixelSize(R$dimen.design_bottom_sheet_peek_height_min);
            boolean z = (this.m || this.e) ? false : true;
            if (this.n || this.o || this.p || this.r || this.s || this.t || z) {
                yn3.a(v, new gi(this, z));
            }
            this.S = new WeakReference<>(v);
            sc1 sc1Var = this.h;
            if (sc1Var != null) {
                om3.d.n(v, sc1Var);
                sc1 sc1Var2 = this.h;
                float fE = this.F;
                if (fE == -1.0f) {
                    fE = om3.i.e(v);
                }
                sc1Var2.m(fE);
                boolean z2 = this.f28J == 3;
                this.x = z2;
                this.h.o(z2 ? 0.0f : 1.0f);
            } else {
                ColorStateList colorStateList = this.i;
                if (colorStateList != null) {
                    om3.i.j(v, colorStateList);
                }
            }
            G();
            if (om3.d.b(v) == 0) {
                om3.d.p(v, 1);
            }
        }
        if (this.K == null) {
            this.K = new qm3(coordinatorLayout.getContext(), coordinatorLayout, this.b0);
        }
        int top = v.getTop();
        coordinatorLayout.w(v, i);
        this.Q = coordinatorLayout.getWidth();
        this.R = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.P = height;
        int i3 = this.R;
        int i4 = i3 - height;
        int i5 = this.v;
        if (i4 < i5) {
            if (this.q) {
                this.P = i3;
            } else {
                this.P = i3 - i5;
            }
        }
        this.B = Math.max(0, i3 - this.P);
        this.C = (int) ((1.0f - this.D) * this.R);
        s();
        int i6 = this.f28J;
        if (i6 == 3) {
            v.offsetTopAndBottom(y());
        } else if (i6 == 6) {
            v.offsetTopAndBottom(this.C);
        } else if (this.G && i6 == 5) {
            v.offsetTopAndBottom(this.R);
        } else if (i6 == 4) {
            v.offsetTopAndBottom(this.E);
        } else if (i6 == 1 || i6 == 2) {
            v.offsetTopAndBottom(top - v.getTop());
        }
        this.T = new WeakReference<>(v(v));
        while (true) {
            ArrayList<c> arrayList = this.U;
            if (i2 >= arrayList.size()) {
                return true;
            }
            arrayList.get(i2).a(v);
            i2++;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean i(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(x(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.j, marginLayoutParams.width), x(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + 0, this.k, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean j(View view) {
        WeakReference<View> weakReference = this.T;
        return (weakReference == null || view != weakReference.get() || this.f28J == 3) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void k(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.T;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i4 = top - i2;
        if (i2 > 0) {
            if (i4 < y()) {
                int iY = top - y();
                iArr[1] = iY;
                int i5 = -iY;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                v.offsetTopAndBottom(i5);
                D(3);
            } else {
                if (!this.I) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                v.offsetTopAndBottom(-i2);
                D(1);
            }
        } else if (i2 < 0 && !view.canScrollVertically(-1)) {
            int i6 = this.E;
            if (i4 > i6 && !this.G) {
                int i7 = top - i6;
                iArr[1] = i7;
                int i8 = -i7;
                WeakHashMap<View, in3> weakHashMap3 = om3.a;
                v.offsetTopAndBottom(i8);
                D(4);
            } else {
                if (!this.I) {
                    return;
                }
                iArr[1] = i2;
                WeakHashMap<View, in3> weakHashMap4 = om3.a;
                v.offsetTopAndBottom(-i2);
                D(1);
            }
        }
        u(v.getTop());
        this.M = i2;
        this.N = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void n(View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.a;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.d = savedState.h;
            }
            if (i == -1 || (i & 2) == 2) {
                this.b = savedState.i;
            }
            if (i == -1 || (i & 4) == 4) {
                this.G = savedState.j;
            }
            if (i == -1 || (i & 8) == 8) {
                this.H = savedState.k;
            }
        }
        int i2 = savedState.g;
        if (i2 == 1 || i2 == 2) {
            this.f28J = 4;
        } else {
            this.f28J = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable o(View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean p(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.M = 0;
        this.N = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ad  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q(androidx.coordinatorlayout.widget.CoordinatorLayout r2, V r3, android.view.View r4, int r5) {
        /*
            r1 = this;
            int r2 = r3.getTop()
            int r5 = r1.y()
            r0 = 3
            if (r2 != r5) goto Lf
            r1.D(r0)
            return
        Lf:
            java.lang.ref.WeakReference<android.view.View> r2 = r1.T
            if (r2 == 0) goto Lb4
            java.lang.Object r2 = r2.get()
            if (r4 != r2) goto Lb4
            boolean r2 = r1.N
            if (r2 != 0) goto L1f
            goto Lb4
        L1f:
            int r2 = r1.M
            if (r2 <= 0) goto L33
            boolean r2 = r1.b
            if (r2 == 0) goto L29
            goto Lae
        L29:
            int r2 = r3.getTop()
            int r4 = r1.C
            if (r2 <= r4) goto Lae
            goto Lab
        L33:
            boolean r2 = r1.G
            if (r2 == 0) goto L54
            android.view.VelocityTracker r2 = r1.V
            if (r2 != 0) goto L3d
            r2 = 0
            goto L4c
        L3d:
            r4 = 1000(0x3e8, float:1.401E-42)
            float r5 = r1.c
            r2.computeCurrentVelocity(r4, r5)
            android.view.VelocityTracker r2 = r1.V
            int r4 = r1.W
            float r2 = r2.getYVelocity(r4)
        L4c:
            boolean r2 = r1.E(r3, r2)
            if (r2 == 0) goto L54
            r0 = 5
            goto Lae
        L54:
            int r2 = r1.M
            if (r2 != 0) goto L91
            int r2 = r3.getTop()
            boolean r4 = r1.b
            if (r4 == 0) goto L72
            int r4 = r1.B
            int r4 = r2 - r4
            int r4 = java.lang.Math.abs(r4)
            int r5 = r1.E
            int r2 = r2 - r5
            int r2 = java.lang.Math.abs(r2)
            if (r4 >= r2) goto Lad
            goto Lae
        L72:
            int r4 = r1.C
            if (r2 >= r4) goto L81
            int r4 = r1.E
            int r4 = r2 - r4
            int r4 = java.lang.Math.abs(r4)
            if (r2 >= r4) goto Lab
            goto Lae
        L81:
            int r4 = r2 - r4
            int r4 = java.lang.Math.abs(r4)
            int r5 = r1.E
            int r2 = r2 - r5
            int r2 = java.lang.Math.abs(r2)
            if (r4 >= r2) goto Lad
            goto Lab
        L91:
            boolean r2 = r1.b
            if (r2 == 0) goto L96
            goto Lad
        L96:
            int r2 = r3.getTop()
            int r4 = r1.C
            int r4 = r2 - r4
            int r4 = java.lang.Math.abs(r4)
            int r5 = r1.E
            int r2 = r2 - r5
            int r2 = java.lang.Math.abs(r2)
            if (r4 >= r2) goto Lad
        Lab:
            r0 = 6
            goto Lae
        Lad:
            r0 = 4
        Lae:
            r2 = 0
            r1.F(r3, r0, r2)
            r1.N = r2
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.q(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean r(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = false;
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = this.f28J;
        if (i == 1 && actionMasked == 0) {
            return true;
        }
        qm3 qm3Var = this.K;
        if (qm3Var != null && (this.I || i == 1)) {
            qm3Var.m(motionEvent);
        }
        if (actionMasked == 0) {
            this.W = -1;
            VelocityTracker velocityTracker = this.V;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.V = null;
            }
        }
        if (this.V == null) {
            this.V = VelocityTracker.obtain();
        }
        this.V.addMovement(motionEvent);
        if (this.K != null && (this.I || this.f28J == 1)) {
            z = true;
        }
        if (z && actionMasked == 2 && !this.L) {
            float fAbs = Math.abs(this.X - motionEvent.getY());
            qm3 qm3Var2 = this.K;
            if (fAbs > qm3Var2.b) {
                qm3Var2.c(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.L;
    }

    public final void s() {
        int iT = t();
        if (this.b) {
            this.E = Math.max(this.R - iT, this.B);
        } else {
            this.E = this.R - iT;
        }
    }

    public final int t() {
        int iMin;
        int i;
        int i2;
        if (this.e) {
            iMin = Math.min(Math.max(this.f, this.R - ((this.Q * 9) / 16)), this.P);
            i = this.u;
        } else {
            if (!this.m && !this.n && (i2 = this.l) > 0) {
                return Math.max(this.d, i2 + this.g);
            }
            iMin = this.d;
            i = this.u;
        }
        return iMin + i;
    }

    public final void u(int i) {
        V v = this.S.get();
        if (v != null) {
            ArrayList<c> arrayList = this.U;
            if (arrayList.isEmpty()) {
                return;
            }
            int i2 = this.E;
            if (i <= i2 && i2 != y()) {
                y();
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList.get(i3).b(v);
            }
        }
    }

    public final int y() {
        if (this.b) {
            return this.B;
        }
        return Math.max(this.A, this.q ? 0 : this.v);
    }

    public final int z(int i) {
        if (i == 3) {
            return y();
        }
        if (i == 4) {
            return this.E;
        }
        if (i == 5) {
            return this.R;
        }
        if (i == 6) {
            return this.C;
        }
        throw new IllegalArgumentException(gq2.e("Invalid state to get top offset: ", i));
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int g;
        public final int h;
        public final boolean i;
        public final boolean j;
        public final boolean k;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.k = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeInt(this.k ? 1 : 0);
        }

        public SavedState(android.view.AbsSavedState absSavedState, BottomSheetBehavior bottomSheetBehavior) {
            super(absSavedState);
            this.g = bottomSheetBehavior.f28J;
            this.h = bottomSheetBehavior.d;
            this.i = bottomSheetBehavior.b;
            this.j = bottomSheetBehavior.G;
            this.k = bottomSheetBehavior.H;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        super(context, attributeSet);
        this.a = 0;
        this.b = true;
        this.j = -1;
        this.k = -1;
        this.y = new d();
        this.D = 0.5f;
        this.F = -1.0f;
        this.I = true;
        this.f28J = 4;
        this.O = 0.1f;
        this.U = new ArrayList<>();
        this.a0 = -1;
        this.b0 = new b();
        this.g = context.getResources().getDimensionPixelSize(R$dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        int i2 = R$styleable.BottomSheetBehavior_Layout_backgroundTint;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            this.i = rc1.a(context, typedArrayObtainStyledAttributes, i2);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.w = new uj2(uj2.b(context, attributeSet, R$attr.bottomSheetStyle, c0));
        }
        uj2 uj2Var = this.w;
        if (uj2Var != null) {
            sc1 sc1Var = new sc1(uj2Var);
            this.h = sc1Var;
            sc1Var.k(context);
            ColorStateList colorStateList = this.i;
            if (colorStateList != null) {
                this.h.n(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.h.setTint(typedValue.data);
            }
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.z = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.z.addUpdateListener(new fi(this));
        this.F = typedArrayObtainStyledAttributes.getDimension(R$styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        int i3 = R$styleable.BottomSheetBehavior_Layout_android_maxWidth;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(i3, -1);
        }
        int i4 = R$styleable.BottomSheetBehavior_Layout_android_maxHeight;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(i4, -1);
        }
        int i5 = R$styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i5);
        if (typedValuePeekValue != null && (i = typedValuePeekValue.data) == -1) {
            B(i);
        } else {
            B(typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, -1));
        }
        A(typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        this.m = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (this.b != z) {
            this.b = z;
            if (this.S != null) {
                s();
            }
            D((this.b && this.f28J == 6) ? 3 : this.f28J);
            G();
        }
        this.H = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        this.I = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_behavior_draggable, true);
        this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0);
        float f = typedArrayObtainStyledAttributes.getFloat(R$styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f);
        if (f > 0.0f && f < 1.0f) {
            this.D = f;
            if (this.S != null) {
                this.C = (int) ((1.0f - f) * this.R);
            }
            int i6 = R$styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i6);
            if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
                int i7 = typedValuePeekValue2.data;
                if (i7 >= 0) {
                    this.A = i7;
                } else {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
            } else {
                int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(i6, 0);
                if (dimensionPixelOffset >= 0) {
                    this.A = dimensionPixelOffset;
                } else {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
            }
            this.n = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
            this.o = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
            this.p = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
            this.q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
            this.r = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
            this.s = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
            this.t = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
            typedArrayObtainStyledAttributes.recycle();
            this.c = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }

    public static abstract class c {
        public abstract void b(View view);

        public abstract void c(View view, int i);

        public void a(View view) {
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }
}
