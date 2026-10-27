package com.google.android.material.bottomsheet;

import A.C0050y0;
import B2.C0064d;
import C1.j;
import F1.A;
import F1.AbstractC0265y;
import F1.C0242a;
import F1.C0243b;
import F1.E;
import F1.H;
import L1.d;
import M.t;
import P4.b;
import P4.c;
import P4.e;
import U4.l;
import Y4.f;
import Y4.g;
import Y4.k;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.hardware.bydauto.ac.tsimpl.a;
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
import com.stingray.drivemodedetector.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import t1.AbstractC2425a;
import t1.C2428d;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends AbstractC2425a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f12751A;
    public final float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f12752C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f12753D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final boolean f12754E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f12755F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public d f12756G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f12757H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f12758I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f12759J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f12760K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f12761L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f12762M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public WeakReference f12763N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public WeakReference f12764O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final ArrayList f12765P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public VelocityTracker f12766Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public int f12767R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public int f12768S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f12769T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public HashMap f12770U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f12771V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final c f12772W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f12774b;
    public final float c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f12775e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f12776f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f12777g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f12778h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g f12779i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f12780j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f12781k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f12782m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f12783n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f12784p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f12785q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f12786r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public k f12787s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f12788t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public e f12789u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ValueAnimator f12790v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f12791w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f12792x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f12793y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final float f12794z;

    public BottomSheetBehavior() {
        this.f12773a = 0;
        this.f12774b = true;
        this.f12780j = -1;
        this.f12789u = null;
        this.f12794z = 0.5f;
        this.B = -1.0f;
        this.f12754E = true;
        this.f12755F = 4;
        this.f12765P = new ArrayList();
        this.f12771V = -1;
        this.f12772W = new c(this);
    }

    public static View v(View view) {
        int[] iArr = H.f2303a;
        if (A.g(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View viewV = v(viewGroup.getChildAt(i7));
            if (viewV != null) {
                return viewV;
            }
        }
        return null;
    }

    public final void A(View view, int i7) {
        int iW;
        int i10;
        if (i7 == 4) {
            iW = this.f12751A;
        } else if (i7 == 6) {
            iW = this.f12793y;
            if (this.f12774b && iW <= (i10 = this.f12792x)) {
                i7 = 3;
                iW = i10;
            }
        } else if (i7 == 3) {
            iW = w();
        } else {
            if (!this.f12752C || i7 != 5) {
                throw new IllegalArgumentException(a.e(i7, "Illegal state argument: "));
            }
            iW = this.f12762M;
        }
        C(view, i7, iW, false);
    }

    public final boolean B(View view, float f10) {
        if (this.f12753D) {
            return true;
        }
        if (view.getTop() < this.f12751A) {
            return false;
        }
        return Math.abs(((f10 * 0.1f) + ((float) view.getTop())) - ((float) this.f12751A)) / ((float) s()) > 0.5f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
    
        if (r5 != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002e, code lost:
    
        z(2);
        E(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (r2.f12789u != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        r2.f12789u = new P4.e(r2, r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        r5 = r2.f12789u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        if (r5.f5339m != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        r5.f5340n = r4;
        r4 = F1.H.f2303a;
        r3.postOnAnimation(r5);
        r2.f12789u.f5339m = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
    
        r5.f5340n = r4;
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
    public final void C(android.view.View r3, int r4, int r5, boolean r6) {
        /*
            r2 = this;
            L1.d r0 = r2.f12756G
            if (r0 == 0) goto L56
            if (r6 == 0) goto L11
            int r6 = r3.getLeft()
            boolean r5 = r0.o(r6, r5)
            if (r5 == 0) goto L56
            goto L2e
        L11:
            int r6 = r3.getLeft()
            r0.f3994r = r3
            r1 = -1
            r0.c = r1
            r1 = 0
            boolean r5 = r0.h(r6, r5, r1, r1)
            if (r5 != 0) goto L2c
            int r6 = r0.f3981a
            if (r6 != 0) goto L2c
            android.view.View r6 = r0.f3994r
            if (r6 == 0) goto L2c
            r6 = 0
            r0.f3994r = r6
        L2c:
            if (r5 == 0) goto L56
        L2e:
            r5 = 2
            r2.z(r5)
            r2.E(r4)
            P4.e r5 = r2.f12789u
            if (r5 != 0) goto L40
            P4.e r5 = new P4.e
            r5.<init>(r2, r3, r4)
            r2.f12789u = r5
        L40:
            P4.e r5 = r2.f12789u
            boolean r6 = r5.f5339m
            if (r6 != 0) goto L53
            r5.f5340n = r4
            int[] r4 = F1.H.f2303a
            r3.postOnAnimation(r5)
            P4.e r2 = r2.f12789u
            r3 = 1
            r2.f5339m = r3
            goto L59
        L53:
            r5.f5340n = r4
            goto L59
        L56:
            r2.z(r4)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.C(android.view.View, int, int, boolean):void");
    }

    public final void D() {
        View view;
        int iA;
        WeakReference weakReference = this.f12763N;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        H.e(view, 524288);
        H.c(view, 0);
        H.e(view, 262144);
        H.c(view, 0);
        H.e(view, 1048576);
        H.c(view, 0);
        int i7 = this.f12771V;
        if (i7 != -1) {
            H.e(view, i7);
            H.c(view, 0);
        }
        if (!this.f12774b && this.f12755F != 6) {
            String string = view.getResources().getString(R.string.bottomsheet_action_expand_halfway);
            j jVar = new j(this, 6);
            ArrayList arrayListA = H.a(view);
            int i10 = 0;
            while (true) {
                if (i10 >= arrayListA.size()) {
                    int i11 = -1;
                    for (int i12 = 0; i12 < 32 && i11 == -1; i12++) {
                        int i13 = H.f2303a[i12];
                        boolean z6 = true;
                        for (int i14 = 0; i14 < arrayListA.size(); i14++) {
                            z6 &= ((G1.d) arrayListA.get(i14)).a() != i13;
                        }
                        if (z6) {
                            i11 = i13;
                        }
                    }
                    iA = i11;
                } else {
                    if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((G1.d) arrayListA.get(i10)).f2644a).getLabel())) {
                        iA = ((G1.d) arrayListA.get(i10)).a();
                        break;
                    }
                    i10++;
                }
            }
            if (iA != -1) {
                G1.d dVar = new G1.d(null, iA, string, jVar, null);
                View.AccessibilityDelegate accessibilityDelegateA = E.a(view);
                C0243b c0243b = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0242a ? ((C0242a) accessibilityDelegateA).f2323a : new C0243b(accessibilityDelegateA);
                if (c0243b == null) {
                    c0243b = new C0243b();
                }
                H.g(view, c0243b);
                H.e(view, dVar.a());
                H.a(view).add(dVar);
                H.c(view, 0);
            }
            this.f12771V = iA;
        }
        if (this.f12752C && this.f12755F != 5) {
            H.f(view, G1.d.l, new j(this, 5));
        }
        int i15 = this.f12755F;
        if (i15 == 3) {
            H.f(view, G1.d.f2640k, new j(this, this.f12774b ? 4 : 6));
            return;
        }
        if (i15 == 4) {
            H.f(view, G1.d.f2639j, new j(this, this.f12774b ? 3 : 6));
        } else {
            if (i15 != 6) {
                return;
            }
            H.f(view, G1.d.f2640k, new j(this, 4));
            H.f(view, G1.d.f2639j, new j(this, 3));
        }
    }

    public final void E(int i7) {
        ValueAnimator valueAnimator = this.f12790v;
        if (i7 == 2) {
            return;
        }
        boolean z6 = i7 == 3;
        if (this.f12788t != z6) {
            this.f12788t = z6;
            if (this.f12779i == null || valueAnimator == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                valueAnimator.reverse();
                return;
            }
            float f10 = z6 ? 0.0f : 1.0f;
            valueAnimator.setFloatValues(1.0f - f10, f10);
            valueAnimator.start();
        }
    }

    public final void F(boolean z6) {
        WeakReference weakReference = this.f12763N;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z6) {
                if (this.f12770U != null) {
                    return;
                } else {
                    this.f12770U = new HashMap(childCount);
                }
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                if (childAt != this.f12763N.get() && z6) {
                    this.f12770U.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z6) {
                return;
            }
            this.f12770U = null;
        }
    }

    public final void G() {
        View view;
        if (this.f12763N != null) {
            r();
            if (this.f12755F != 4 || (view = (View) this.f12763N.get()) == null) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // t1.AbstractC2425a
    public final void c(C2428d c2428d) {
        this.f12763N = null;
        this.f12756G = null;
    }

    @Override // t1.AbstractC2425a
    public final void e() {
        this.f12763N = null;
        this.f12756G = null;
    }

    @Override // t1.AbstractC2425a
    public final boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        d dVar;
        if (!view.isShown() || !this.f12754E) {
            this.f12757H = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f12767R = -1;
            VelocityTracker velocityTracker = this.f12766Q;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f12766Q = null;
            }
        }
        if (this.f12766Q == null) {
            this.f12766Q = VelocityTracker.obtain();
        }
        this.f12766Q.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x8 = (int) motionEvent.getX();
            this.f12768S = (int) motionEvent.getY();
            if (this.f12755F != 2) {
                WeakReference weakReference = this.f12764O;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.o(view2, x8, this.f12768S)) {
                    this.f12767R = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.f12769T = true;
                }
            }
            this.f12757H = this.f12767R == -1 && !coordinatorLayout.o(view, x8, this.f12768S);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f12769T = false;
            this.f12767R = -1;
            if (this.f12757H) {
                this.f12757H = false;
                return false;
            }
        }
        if (!this.f12757H && (dVar = this.f12756G) != null && dVar.p(motionEvent)) {
            return true;
        }
        WeakReference weakReference2 = this.f12764O;
        View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
        return (actionMasked != 2 || view3 == null || this.f12757H || this.f12755F == 1 || coordinatorLayout.o(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.f12756G == null || Math.abs(((float) this.f12768S) - motionEvent.getY()) <= ((float) this.f12756G.f3982b)) ? false : true;
    }

    @Override // t1.AbstractC2425a
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        g gVar;
        int i10 = this.f12780j;
        int i11 = 2;
        int[] iArr = H.f2303a;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        if (this.f12763N == null) {
            this.f12776f = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            boolean z6 = (this.l || this.f12775e) ? false : true;
            if (this.f12782m || this.f12783n || this.o || z6) {
                C0050y0 c0050y0 = new C0050y0(3, this, z6);
                int paddingStart = view.getPaddingStart();
                view.getPaddingTop();
                int paddingEnd = view.getPaddingEnd();
                int paddingBottom = view.getPaddingBottom();
                C0064d c0064d = new C0064d(i11);
                c0064d.f556b = paddingStart;
                c0064d.c = paddingEnd;
                c0064d.d = paddingBottom;
                A.k(view, new t(c0050y0, c0064d));
                if (view.isAttachedToWindow()) {
                    AbstractC0265y.b(view);
                } else {
                    view.addOnAttachStateChangeListener(new l());
                }
            }
            this.f12763N = new WeakReference(view);
            if (this.f12778h && (gVar = this.f12779i) != null) {
                view.setBackground(gVar);
            }
            g gVar2 = this.f12779i;
            if (gVar2 != null) {
                float fE = this.B;
                if (fE == -1.0f) {
                    fE = A.e(view);
                }
                gVar2.g(fE);
                boolean z10 = this.f12755F == 3;
                this.f12788t = z10;
                g gVar3 = this.f12779i;
                float f10 = z10 ? 0.0f : 1.0f;
                f fVar = gVar3.l;
                if (fVar.f10714i != f10) {
                    fVar.f10714i = f10;
                    gVar3.f10727p = true;
                    gVar3.invalidateSelf();
                }
            }
            D();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
            if (view.getMeasuredWidth() > i10 && i10 != -1) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = i10;
                view.post(new h5.t(view, 7, layoutParams));
            }
        }
        if (this.f12756G == null) {
            this.f12756G = new d(coordinatorLayout.getContext(), coordinatorLayout, this.f12772W);
        }
        int top = view.getTop();
        coordinatorLayout.q(view, i7);
        this.f12761L = coordinatorLayout.getWidth();
        this.f12762M = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.f12760K = height;
        int i12 = this.f12762M;
        int i13 = i12 - height;
        int i14 = this.f12786r;
        if (i13 < i14) {
            if (this.f12784p) {
                this.f12760K = i12;
            } else {
                this.f12760K = i12 - i14;
            }
        }
        this.f12792x = Math.max(0, i12 - this.f12760K);
        this.f12793y = (int) ((1.0f - this.f12794z) * this.f12762M);
        r();
        int i15 = this.f12755F;
        if (i15 == 3) {
            view.offsetTopAndBottom(w());
        } else if (i15 == 6) {
            view.offsetTopAndBottom(this.f12793y);
        } else if (this.f12752C && i15 == 5) {
            view.offsetTopAndBottom(this.f12762M);
        } else if (i15 == 4) {
            view.offsetTopAndBottom(this.f12751A);
        } else if (i15 == 1 || i15 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        this.f12764O = new WeakReference(v(view));
        return true;
    }

    @Override // t1.AbstractC2425a
    public final boolean i(View view) {
        WeakReference weakReference = this.f12764O;
        return (weakReference == null || view != weakReference.get() || this.f12755F == 3) ? false : true;
    }

    @Override // t1.AbstractC2425a
    public final void j(CoordinatorLayout coordinatorLayout, View view, View view2, int i7, int i10, int[] iArr, int i11) {
        boolean z6 = this.f12754E;
        if (i11 == 1) {
            return;
        }
        WeakReference weakReference = this.f12764O;
        if (view2 != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = view.getTop();
        int i12 = top - i10;
        if (i10 > 0) {
            if (i12 < w()) {
                int iW = top - w();
                iArr[1] = iW;
                int[] iArr2 = H.f2303a;
                view.offsetTopAndBottom(-iW);
                z(3);
            } else {
                if (!z6) {
                    return;
                }
                iArr[1] = i10;
                int[] iArr3 = H.f2303a;
                view.offsetTopAndBottom(-i10);
                z(1);
            }
        } else if (i10 < 0 && !view2.canScrollVertically(-1)) {
            int i13 = this.f12751A;
            if (i12 > i13 && !this.f12752C) {
                int i14 = top - i13;
                iArr[1] = i14;
                int[] iArr4 = H.f2303a;
                view.offsetTopAndBottom(-i14);
                z(4);
            } else {
                if (!z6) {
                    return;
                }
                iArr[1] = i10;
                int[] iArr5 = H.f2303a;
                view.offsetTopAndBottom(-i10);
                z(1);
            }
        }
        u(view.getTop());
        this.f12758I = i10;
        this.f12759J = true;
    }

    @Override // t1.AbstractC2425a
    public final void k(CoordinatorLayout coordinatorLayout, View view, int i7, int i10, int i11, int[] iArr) {
    }

    @Override // t1.AbstractC2425a
    public final void m(View view, Parcelable parcelable) {
        P4.d dVar = (P4.d) parcelable;
        int i7 = this.f12773a;
        if (i7 != 0) {
            if (i7 == -1 || (i7 & 1) == 1) {
                this.d = dVar.o;
            }
            if (i7 == -1 || (i7 & 2) == 2) {
                this.f12774b = dVar.f5336p;
            }
            if (i7 == -1 || (i7 & 4) == 4) {
                this.f12752C = dVar.f5337q;
            }
            if (i7 == -1 || (i7 & 8) == 8) {
                this.f12753D = dVar.f5338r;
            }
        }
        int i10 = dVar.f5335n;
        if (i10 == 1 || i10 == 2) {
            this.f12755F = 4;
        } else {
            this.f12755F = i10;
        }
    }

    @Override // t1.AbstractC2425a
    public final Parcelable n(View view) {
        return new P4.d(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // t1.AbstractC2425a
    public final boolean o(View view, int i7, int i10) {
        this.f12758I = 0;
        this.f12759J = false;
        return (i7 & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    @Override // t1.AbstractC2425a
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

    @Override // t1.AbstractC2425a
    public final boolean q(View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f12755F == 1 && actionMasked == 0) {
            return true;
        }
        d dVar = this.f12756G;
        if (dVar != null) {
            dVar.j(motionEvent);
        }
        if (actionMasked == 0) {
            this.f12767R = -1;
            VelocityTracker velocityTracker = this.f12766Q;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f12766Q = null;
            }
        }
        if (this.f12766Q == null) {
            this.f12766Q = VelocityTracker.obtain();
        }
        this.f12766Q.addMovement(motionEvent);
        if (this.f12756G != null && actionMasked == 2 && !this.f12757H) {
            float fAbs = Math.abs(this.f12768S - motionEvent.getY());
            d dVar2 = this.f12756G;
            if (fAbs > dVar2.f3982b) {
                dVar2.b(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.f12757H;
    }

    public final void r() {
        int iS = s();
        if (this.f12774b) {
            this.f12751A = Math.max(this.f12762M - iS, this.f12792x);
        } else {
            this.f12751A = this.f12762M - iS;
        }
    }

    public final int s() {
        int iMin;
        int i7;
        int i10;
        if (this.f12775e) {
            iMin = Math.min(Math.max(this.f12776f, this.f12762M - ((this.f12761L * 9) / 16)), this.f12760K);
            i7 = this.f12785q;
        } else {
            if (!this.l && !this.f12782m && (i10 = this.f12781k) > 0) {
                return Math.max(this.d, i10 + this.f12777g);
            }
            iMin = this.d;
            i7 = this.f12785q;
        }
        return iMin + i7;
    }

    public final void t(Context context, AttributeSet attributeSet, boolean z6, ColorStateList colorStateList) {
        if (this.f12778h) {
            this.f12787s = k.a(context, attributeSet, R.attr.bottomSheetStyle, R.style.Widget_Design_BottomSheet_Modal).a();
            g gVar = new g(this.f12787s);
            this.f12779i = gVar;
            gVar.f(context);
            if (z6 && colorStateList != null) {
                this.f12779i.h(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
            this.f12779i.setTint(typedValue.data);
        }
    }

    public final void u(int i7) {
        if (((View) this.f12763N.get()) != null) {
            ArrayList arrayList = this.f12765P;
            if (arrayList.isEmpty()) {
                return;
            }
            int i10 = this.f12751A;
            if (i7 <= i10 && i10 != w()) {
                w();
            }
            if (arrayList.size() <= 0) {
                return;
            }
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public final int w() {
        if (this.f12774b) {
            return this.f12792x;
        }
        return Math.max(this.f12791w, this.f12784p ? 0 : this.f12786r);
    }

    public final void x(int i7) {
        if (i7 == -1) {
            if (this.f12775e) {
                return;
            } else {
                this.f12775e = true;
            }
        } else {
            if (!this.f12775e && this.d == i7) {
                return;
            }
            this.f12775e = false;
            this.d = Math.max(0, i7);
        }
        G();
    }

    public final void y(int i7) {
        if (i7 == this.f12755F) {
            return;
        }
        if (this.f12763N == null) {
            if (i7 == 4 || i7 == 3 || i7 == 6 || (this.f12752C && i7 == 5)) {
                this.f12755F = i7;
                return;
            }
            return;
        }
        View view = (View) this.f12763N.get();
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            int[] iArr = H.f2303a;
            if (view.isAttachedToWindow()) {
                view.post(new P4.a(this, view, i7));
                return;
            }
        }
        A(view, i7);
    }

    public final void z(int i7) {
        if (this.f12755F == i7) {
            return;
        }
        this.f12755F = i7;
        WeakReference weakReference = this.f12763N;
        if (weakReference == null || ((View) weakReference.get()) == null) {
            return;
        }
        if (i7 == 3) {
            F(true);
        } else if (i7 == 6 || i7 == 5 || i7 == 4) {
            F(false);
        }
        E(i7);
        ArrayList arrayList = this.f12765P;
        if (arrayList.size() <= 0) {
            D();
        } else {
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i7;
        int i10 = 0;
        this.f12773a = 0;
        this.f12774b = true;
        this.f12780j = -1;
        this.f12789u = null;
        this.f12794z = 0.5f;
        this.B = -1.0f;
        this.f12754E = true;
        this.f12755F = 4;
        this.f12765P = new ArrayList();
        this.f12771V = -1;
        this.f12772W = new c(this);
        this.f12777g = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.f3585a);
        this.f12778h = typedArrayObtainStyledAttributes.hasValue(16);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(2);
        if (zHasValue) {
            t(context, attributeSet, zHasValue, com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 2));
        } else {
            t(context, attributeSet, zHasValue, null);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f12790v = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.f12790v.addUpdateListener(new b(this, i10));
        this.B = typedArrayObtainStyledAttributes.getDimension(1, -1.0f);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            this.f12780j = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        }
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(8);
        if (typedValuePeekValue != null && (i7 = typedValuePeekValue.data) == -1) {
            x(i7);
        } else {
            x(typedArrayObtainStyledAttributes.getDimensionPixelSize(8, -1));
        }
        boolean z6 = typedArrayObtainStyledAttributes.getBoolean(7, false);
        if (this.f12752C != z6) {
            this.f12752C = z6;
            if (!z6 && this.f12755F == 5) {
                y(4);
            }
            D();
        }
        this.l = typedArrayObtainStyledAttributes.getBoolean(11, false);
        boolean z10 = typedArrayObtainStyledAttributes.getBoolean(5, true);
        if (this.f12774b != z10) {
            this.f12774b = z10;
            if (this.f12763N != null) {
                r();
            }
            z((this.f12774b && this.f12755F == 6) ? 3 : this.f12755F);
            D();
        }
        this.f12753D = typedArrayObtainStyledAttributes.getBoolean(10, false);
        this.f12754E = typedArrayObtainStyledAttributes.getBoolean(3, true);
        this.f12773a = typedArrayObtainStyledAttributes.getInt(9, 0);
        float f10 = typedArrayObtainStyledAttributes.getFloat(6, 0.5f);
        if (f10 > 0.0f && f10 < 1.0f) {
            this.f12794z = f10;
            if (this.f12763N != null) {
                this.f12793y = (int) ((1.0f - f10) * this.f12762M);
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(4);
            if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
                int i11 = typedValuePeekValue2.data;
                if (i11 >= 0) {
                    this.f12791w = i11;
                } else {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
            } else {
                int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(4, 0);
                if (dimensionPixelOffset >= 0) {
                    this.f12791w = dimensionPixelOffset;
                } else {
                    throw new IllegalArgumentException("offset must be greater than or equal to 0");
                }
            }
            this.f12782m = typedArrayObtainStyledAttributes.getBoolean(12, false);
            this.f12783n = typedArrayObtainStyledAttributes.getBoolean(13, false);
            this.o = typedArrayObtainStyledAttributes.getBoolean(14, false);
            this.f12784p = typedArrayObtainStyledAttributes.getBoolean(15, true);
            typedArrayObtainStyledAttributes.recycle();
            this.c = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
            return;
        }
        throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
    }
}
