package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.spotify.music.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import p.a52;
import p.ay2;
import p.bg2;
import p.ci1;
import p.cv2;
import p.du2;
import p.it2;
import p.j00;
import p.j6;
import p.jn;
import p.jt2;
import p.k52;
import p.kn;
import p.ln;
import p.lt2;
import p.m00;
import p.m2;
import p.mt2;
import p.o2;
import p.oc2;
import p.ot2;
import p.pf1;
import p.qf1;
import p.u12;
import p.u4;
import p.w2;
import p.xz1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends j00 {
    public final float A;
    public int B;
    public final float C;
    public boolean D;
    public boolean E;
    public final boolean F;
    public int G;
    public du2 H;
    public boolean I;
    public int J;
    public boolean K;
    public int L;
    public int M;
    public int N;
    public WeakReference O;
    public WeakReference P;
    public final ArrayList Q;
    public VelocityTracker R;
    public int S;
    public int T;
    public boolean U;
    public HashMap V;
    public int W;
    public final kn X;
    public final int a;
    public boolean b;
    public final float c;
    public int d;
    public boolean e;
    public int f;
    public final int g;
    public final boolean h;
    public qf1 i;
    public final int j;
    public final int k;
    public int l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f29p;
    public final boolean q;
    public int r;
    public int s;
    public oc2 t;
    public boolean u;
    public bg2 v;
    public final ValueAnimator w;
    public final int x;
    public int y;
    public int z;

    public BottomSheetBehavior() {
        this.a = 0;
        this.b = true;
        this.j = -1;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList();
        this.W = -1;
        this.X = new kn(this);
    }

    public static View v(View view) {
        WeakHashMap weakHashMap = zt2.a;
        if (ot2.p(view)) {
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

    public static int w(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    public final void A(int i) {
        if (this.G == i) {
            return;
        }
        this.G = i;
        if (i != 4 && i != 3 && i != 6) {
            boolean z = this.D;
        }
        WeakReference weakReference = this.O;
        if (weakReference == null || ((View) weakReference.get()) == null) {
            return;
        }
        if (i == 3) {
            H(true);
        } else if (i == 6 || i == 5 || i == 4) {
            H(false);
        }
        G(i);
        ArrayList arrayList = this.Q;
        if (arrayList.size() <= 0) {
            F();
        } else {
            u4.z(arrayList.get(0));
            throw null;
        }
    }

    public final void B(View view, int i) {
        int iX;
        int i2;
        if (i == 4) {
            iX = this.B;
        } else if (i == 6) {
            iX = this.z;
            if (this.b && iX <= (i2 = this.y)) {
                i = 3;
                iX = i2;
            }
        } else if (i == 3) {
            iX = x();
        } else if (!this.D || i != 5) {
            return;
        } else {
            iX = this.N;
        }
        E(view, i, iX, false);
    }

    public final void C(int i) {
        View view = (View) this.O.get();
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = zt2.a;
            if (lt2.b(view)) {
                view.post(new a52(i, 5, this, view));
                return;
            }
        }
        B(view, i);
    }

    public final boolean D(View view, float f) {
        if (this.E) {
            return true;
        }
        if (view.getTop() < this.B) {
            return false;
        }
        return Math.abs(((f * 0.1f) + ((float) view.getTop())) - ((float) this.B)) / ((float) s()) > 0.5f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        if (r5 != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        A(2);
        G(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (r2.v != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        r2.v = new p.bg2(r2, r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        r5 = r2.v;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        if (r5.e != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        r5.k = r4;
        r4 = p.zt2.a;
        p.it2.m(r3, r5);
        r2.v.e = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
    
        r5.k = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
    
        if (r0.o(r3.getLeft(), r5) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(android.view.View r3, int r4, int r5, boolean r6) {
        /*
            r2 = this;
            p.du2 r0 = r2.H
            if (r0 == 0) goto L56
            if (r6 == 0) goto L11
            int r6 = r3.getLeft()
            boolean r5 = r0.o(r6, r5)
            if (r5 == 0) goto L56
            goto L2e
        L11:
            int r6 = r3.getLeft()
            r0.r = r3
            r1 = -1
            r0.c = r1
            r1 = 0
            boolean r5 = r0.h(r6, r5, r1, r1)
            if (r5 != 0) goto L2c
            int r6 = r0.a
            if (r6 != 0) goto L2c
            android.view.View r6 = r0.r
            if (r6 == 0) goto L2c
            r6 = 0
            r0.r = r6
        L2c:
            if (r5 == 0) goto L56
        L2e:
            r5 = 2
            r2.A(r5)
            r2.G(r4)
            p.bg2 r5 = r2.v
            if (r5 != 0) goto L40
            p.bg2 r5 = new p.bg2
            r5.<init>(r2, r3, r4)
            r2.v = r5
        L40:
            p.bg2 r5 = r2.v
            boolean r6 = r5.e
            if (r6 != 0) goto L53
            r5.k = r4
            java.util.WeakHashMap r4 = p.zt2.a
            p.it2.m(r3, r5)
            p.bg2 r3 = r2.v
            r4 = 1
            r3.e = r4
            goto L59
        L53:
            r5.k = r4
            goto L59
        L56:
            r2.A(r4)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.E(android.view.View, int, int, boolean):void");
    }

    public final void F() {
        View view;
        int iA;
        WeakReference weakReference = this.O;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        zt2.h(view, 524288);
        zt2.f(view, 0);
        zt2.h(view, 262144);
        zt2.f(view, 0);
        zt2.h(view, 1048576);
        zt2.f(view, 0);
        int i = this.W;
        if (i != -1) {
            zt2.h(view, i);
            zt2.f(view, 0);
        }
        if (!this.b && this.G != 6) {
            String string = view.getResources().getString(R.string.bottomsheet_action_expand_halfway);
            j6 j6Var = new j6(6, this);
            ArrayList arrayListD = zt2.d(view);
            int i2 = 0;
            while (true) {
                if (i2 >= arrayListD.size()) {
                    int i3 = 0;
                    int i4 = -1;
                    while (true) {
                        int[] iArr = zt2.d;
                        if (i3 >= iArr.length || i4 != -1) {
                            break;
                        }
                        int i5 = iArr[i3];
                        boolean z = true;
                        for (int i6 = 0; i6 < arrayListD.size(); i6++) {
                            z &= ((w2) arrayListD.get(i6)).a() != i5;
                        }
                        if (z) {
                            i4 = i5;
                        }
                        i3++;
                    }
                    iA = i4;
                } else {
                    if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((w2) arrayListD.get(i2)).a).getLabel())) {
                        iA = ((w2) arrayListD.get(i2)).a();
                        break;
                    }
                    i2++;
                }
            }
            if (iA != -1) {
                w2 w2Var = new w2(null, iA, string, j6Var, null);
                View.AccessibilityDelegate accessibilityDelegateC = zt2.c(view);
                o2 o2Var = accessibilityDelegateC == null ? null : accessibilityDelegateC instanceof m2 ? ((m2) accessibilityDelegateC).a : new o2(accessibilityDelegateC);
                if (o2Var == null) {
                    o2Var = new o2();
                }
                zt2.k(view, o2Var);
                zt2.h(view, w2Var.a());
                zt2.d(view).add(w2Var);
                zt2.f(view, 0);
            }
            this.W = iA;
        }
        if (this.D && this.G != 5) {
            zt2.i(view, w2.j, new j6(5, this));
        }
        int i7 = this.G;
        if (i7 == 3) {
            zt2.i(view, w2.i, new j6(this.b ? 4 : 6, this));
            return;
        }
        if (i7 == 4) {
            zt2.i(view, w2.h, new j6(this.b ? 3 : 6, this));
        } else {
            if (i7 != 6) {
                return;
            }
            zt2.i(view, w2.i, new j6(4, this));
            zt2.i(view, w2.h, new j6(3, this));
        }
    }

    public final void G(int i) {
        ValueAnimator valueAnimator;
        if (i == 2) {
            return;
        }
        boolean z = i == 3;
        if (this.u != z) {
            this.u = z;
            if (this.i == null || (valueAnimator = this.w) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.w.reverse();
                return;
            }
            float f = z ? 0.0f : 1.0f;
            this.w.setFloatValues(1.0f - f, f);
            this.w.start();
        }
    }

    public final void H(boolean z) {
        WeakReference weakReference = this.O;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z) {
                if (this.V != null) {
                    return;
                } else {
                    this.V = new HashMap(childCount);
                }
            }
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (childAt != this.O.get() && z) {
                    this.V.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z) {
                return;
            }
            this.V = null;
        }
    }

    public final void I() {
        View view;
        if (this.O != null) {
            r();
            if (this.G != 4 || (view = (View) this.O.get()) == null) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // p.j00
    public final void c(m00 m00Var) {
        this.O = null;
        this.H = null;
    }

    @Override // p.j00
    public final void e() {
        this.O = null;
        this.H = null;
    }

    @Override // p.j00
    public final boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        du2 du2Var;
        if (!view.isShown() || !this.F) {
            this.I = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.S = -1;
            VelocityTracker velocityTracker = this.R;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.R = null;
            }
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.T = (int) motionEvent.getY();
            if (this.G != 2) {
                WeakReference weakReference = this.P;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.o(view2, x, this.T)) {
                    this.S = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.U = true;
                }
            }
            this.I = this.S == -1 && !coordinatorLayout.o(view, x, this.T);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.U = false;
            this.S = -1;
            if (this.I) {
                this.I = false;
                return false;
            }
        }
        if (!this.I && (du2Var = this.H) != null && du2Var.p(motionEvent)) {
            return true;
        }
        WeakReference weakReference2 = this.P;
        View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
        return (actionMasked != 2 || view3 == null || this.I || this.G == 1 || coordinatorLayout.o(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.H == null || Math.abs(((float) this.T) - motionEvent.getY()) <= ((float) this.H.b)) ? false : true;
    }

    @Override // p.j00
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        qf1 qf1Var;
        WeakHashMap weakHashMap = zt2.a;
        if (it2.b(coordinatorLayout) && !it2.b(view)) {
            view.setFitsSystemWindows(true);
        }
        if (this.O == null) {
            this.f = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            boolean z = (Build.VERSION.SDK_INT < 29 || this.m || this.e) ? false : true;
            if (this.n || this.o || this.f29p || z) {
                k52 k52Var = new k52(this, z);
                int iF = jt2.f(view);
                int paddingTop = view.getPaddingTop();
                int iE = jt2.e(view);
                int paddingBottom = view.getPaddingBottom();
                u12 u12Var = new u12();
                u12Var.a = iF;
                u12Var.b = paddingTop;
                u12Var.c = iE;
                u12Var.d = paddingBottom;
                ot2.u(view, new ay2(k52Var, u12Var));
                if (lt2.b(view)) {
                    mt2.c(view);
                } else {
                    view.addOnAttachStateChangeListener(new cv2());
                }
            }
            this.O = new WeakReference(view);
            if (this.h && (qf1Var = this.i) != null) {
                it2.q(view, qf1Var);
            }
            qf1 qf1Var2 = this.i;
            if (qf1Var2 != null) {
                float fI = this.C;
                if (fI == -1.0f) {
                    fI = ot2.i(view);
                }
                qf1Var2.j(fI);
                boolean z2 = this.G == 3;
                this.u = z2;
                qf1 qf1Var3 = this.i;
                float f = z2 ? 0.0f : 1.0f;
                pf1 pf1Var = qf1Var3.c;
                if (pf1Var.j != f) {
                    pf1Var.j = f;
                    qf1Var3.m = true;
                    qf1Var3.invalidateSelf();
                }
            }
            F();
            if (it2.c(view) == 0) {
                it2.s(view, 1);
            }
        }
        if (this.H == null) {
            this.H = new du2(coordinatorLayout.getContext(), coordinatorLayout, this.X);
        }
        int top = view.getTop();
        coordinatorLayout.q(view, i);
        this.M = coordinatorLayout.getWidth();
        this.N = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.L = height;
        int i2 = this.N;
        int i3 = i2 - height;
        int i4 = this.s;
        if (i3 < i4) {
            if (this.q) {
                this.L = i2;
            } else {
                this.L = i2 - i4;
            }
        }
        this.y = Math.max(0, i2 - this.L);
        this.z = (int) ((1.0f - this.A) * this.N);
        r();
        int i5 = this.G;
        if (i5 == 3) {
            view.offsetTopAndBottom(x());
        } else if (i5 == 6) {
            view.offsetTopAndBottom(this.z);
        } else if (this.D && i5 == 5) {
            view.offsetTopAndBottom(this.N);
        } else if (i5 == 4) {
            view.offsetTopAndBottom(this.B);
        } else if (i5 == 1 || i5 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        this.P = new WeakReference(v(view));
        return true;
    }

    @Override // p.j00
    public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(w(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, this.j, marginLayoutParams.width), w(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.k, marginLayoutParams.height));
        return true;
    }

    @Override // p.j00
    public final boolean i(View view) {
        WeakReference weakReference = this.P;
        return (weakReference == null || view != weakReference.get() || this.G == 3) ? false : true;
    }

    @Override // p.j00
    public final void j(View view, View view2, int i, int[] iArr, int i2) {
        if (i2 == 1) {
            return;
        }
        WeakReference weakReference = this.P;
        if (view2 != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = view.getTop();
        int i3 = top - i;
        if (i > 0) {
            if (i3 < x()) {
                int iX = top - x();
                iArr[1] = iX;
                WeakHashMap weakHashMap = zt2.a;
                view.offsetTopAndBottom(-iX);
                A(3);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i;
                WeakHashMap weakHashMap2 = zt2.a;
                view.offsetTopAndBottom(-i);
                A(1);
            }
        } else if (i < 0 && !view2.canScrollVertically(-1)) {
            int i4 = this.B;
            if (i3 > i4 && !this.D) {
                int i5 = top - i4;
                iArr[1] = i5;
                WeakHashMap weakHashMap3 = zt2.a;
                view.offsetTopAndBottom(-i5);
                A(4);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i;
                WeakHashMap weakHashMap4 = zt2.a;
                view.offsetTopAndBottom(-i);
                A(1);
            }
        }
        u(view.getTop());
        this.J = i;
        this.K = true;
    }

    @Override // p.j00
    public final void k(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
    }

    @Override // p.j00
    public final void m(View view, Parcelable parcelable) {
        ln lnVar = (ln) parcelable;
        int i = this.a;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.d = lnVar.l;
            }
            if (i == -1 || (i & 2) == 2) {
                this.b = lnVar.m;
            }
            if (i == -1 || (i & 4) == 4) {
                this.D = lnVar.n;
            }
            if (i == -1 || (i & 8) == 8) {
                this.E = lnVar.o;
            }
        }
        int i2 = lnVar.k;
        if (i2 == 1 || i2 == 2) {
            this.G = 4;
        } else {
            this.G = i2;
        }
    }

    @Override // p.j00
    public final Parcelable n(View view) {
        return new ln(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // p.j00
    public final boolean o(View view, int i, int i2) {
        this.J = 0;
        this.K = false;
        return (i & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    @Override // p.j00
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(android.view.View r4, android.view.View r5, int r6) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.p(android.view.View, android.view.View, int):void");
    }

    @Override // p.j00
    public final boolean q(View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i = this.G;
        if (i == 1 && actionMasked == 0) {
            return true;
        }
        du2 du2Var = this.H;
        if (du2Var != null && (this.F || i == 1)) {
            du2Var.j(motionEvent);
        }
        if (actionMasked == 0) {
            this.S = -1;
            VelocityTracker velocityTracker = this.R;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.R = null;
            }
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (this.H != null && ((this.F || this.G == 1) && actionMasked == 2 && !this.I)) {
            float fAbs = Math.abs(this.T - motionEvent.getY());
            du2 du2Var2 = this.H;
            if (fAbs > du2Var2.b) {
                du2Var2.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.I;
    }

    public final void r() {
        int iS = s();
        if (this.b) {
            this.B = Math.max(this.N - iS, this.y);
        } else {
            this.B = this.N - iS;
        }
    }

    public final int s() {
        int i;
        return this.e ? Math.min(Math.max(this.f, this.N - ((this.M * 9) / 16)), this.L) + this.r : (this.m || this.n || (i = this.l) <= 0) ? this.d + this.r : Math.max(this.d, i + this.g);
    }

    public final void t(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.h) {
            this.t = oc2.b(context, attributeSet, R.attr.bottomSheetStyle, R.style.Widget_Design_BottomSheet_Modal).a();
            qf1 qf1Var = new qf1(this.t);
            this.i = qf1Var;
            qf1Var.i(context);
            if (z && colorStateList != null) {
                this.i.k(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
            this.i.setTint(typedValue.data);
        }
    }

    public final void u(int i) {
        if (((View) this.O.get()) != null) {
            ArrayList arrayList = this.Q;
            if (arrayList.isEmpty()) {
                return;
            }
            int i2 = this.B;
            if (i <= i2 && i2 != x()) {
                x();
            }
            if (arrayList.size() <= 0) {
                return;
            }
            u4.z(arrayList.get(0));
            throw null;
        }
    }

    public final int x() {
        if (this.b) {
            return this.y;
        }
        return Math.max(this.x, this.q ? 0 : this.s);
    }

    public final void y(int i) {
        if (i == -1) {
            if (this.e) {
                return;
            } else {
                this.e = true;
            }
        } else {
            if (!this.e && this.d == i) {
                return;
            }
            this.e = false;
            this.d = Math.max(0, i);
        }
        I();
    }

    public final void z(int i) {
        if (i == this.G) {
            return;
        }
        if (this.O != null) {
            C(i);
            return;
        }
        if (i == 4 || i == 3 || i == 6 || (this.D && i == 5)) {
            this.G = i;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i;
        this.a = 0;
        this.b = true;
        this.j = -1;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList();
        this.W = -1;
        this.X = new kn(this);
        this.g = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.a);
        this.h = typedArrayObtainStyledAttributes.hasValue(17);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(3);
        if (zHasValue) {
            t(context, attributeSet, zHasValue, ci1.v(context, typedArrayObtainStyledAttributes, 3));
        } else {
            t(context, attributeSet, zHasValue, null);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.w = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.w.addUpdateListener(new jn(0, this));
        this.C = typedArrayObtainStyledAttributes.getDimension(2, -1.0f);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        }
        if (typedArrayObtainStyledAttributes.hasValue(1)) {
            this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, -1);
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(9);
        if (typedValuePeekValue != null && (i = typedValuePeekValue.data) == -1) {
            y(i);
        } else {
            y(typedArrayObtainStyledAttributes.getDimensionPixelSize(9, -1));
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(8, false);
        if (this.D != z) {
            this.D = z;
            if (!z && this.G == 5) {
                z(4);
            }
            F();
        }
        this.m = typedArrayObtainStyledAttributes.getBoolean(12, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(6, true);
        if (this.b != z2) {
            this.b = z2;
            if (this.O != null) {
                r();
            }
            A((this.b && this.G == 6) ? 3 : this.G);
            F();
        }
        this.E = typedArrayObtainStyledAttributes.getBoolean(11, false);
        this.F = typedArrayObtainStyledAttributes.getBoolean(4, true);
        this.a = typedArrayObtainStyledAttributes.getInt(10, 0);
        float f = typedArrayObtainStyledAttributes.getFloat(7, 0.5f);
        if (f > 0.0f && f < 1.0f) {
            this.A = f;
            if (this.O != null) {
                this.z = (int) ((1.0f - f) * this.N);
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(5);
            if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
                int i2 = typedValuePeekValue2.data;
                if (i2 < 0) {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
                this.x = i2;
            } else {
                int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(5, 0);
                if (dimensionPixelOffset < 0) {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
                this.x = dimensionPixelOffset;
            }
            this.n = typedArrayObtainStyledAttributes.getBoolean(13, false);
            this.o = typedArrayObtainStyledAttributes.getBoolean(14, false);
            this.f29p = typedArrayObtainStyledAttributes.getBoolean(15, false);
            this.q = typedArrayObtainStyledAttributes.getBoolean(16, true);
            typedArrayObtainStyledAttributes.recycle();
            this.c = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }
}
