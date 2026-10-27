package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import i0.u;

/* JADX INFO: compiled from: HeaderBehavior.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a<V extends View> extends c<V> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Runnable f4062d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    OverScroller f4063e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4064f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4065g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4066h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4067i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private VelocityTracker f4068j;

    /* JADX INFO: renamed from: com.google.android.material.appbar.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderBehavior.java */
    private class RunnableC0045a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final CoordinatorLayout f4069e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final V f4070g;

        RunnableC0045a(CoordinatorLayout coordinatorLayout, V v6) {
            this.f4069e = coordinatorLayout;
            this.f4070g = v6;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.f4070g == null || (overScroller = a.this.f4063e) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a.this.N(this.f4069e, this.f4070g);
                return;
            }
            a aVar = a.this;
            aVar.P(this.f4069e, this.f4070g, aVar.f4063e.getCurrY());
            u.c0(this.f4070g, this);
        }
    }

    public a() {
        this.f4065g = -1;
        this.f4067i = -1;
    }

    private void I() {
        if (this.f4068j == null) {
            this.f4068j = VelocityTracker.obtain();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean D(androidx.coordinatorlayout.widget.CoordinatorLayout r12, V r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = r2
            goto L1c
        L1b:
            r12 = r3
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.f4065g = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.f4066h = r12
            goto L4c
        L2d:
            int r0 = r11.f4065g
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.f4066h
            int r7 = r1 - r0
            r11.f4066h = r0
            int r8 = r11.K(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.O(r5, r6, r7, r8, r9)
        L4c:
            r12 = r3
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.f4068j
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.f4068j
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.f4068j
            int r4 = r11.f4065g
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.L(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.J(r6, r7, r8, r9, r10)
            r12 = r2
            goto L73
        L72:
            r12 = r3
        L73:
            r11.f4064f = r3
            r11.f4065g = r1
            android.view.VelocityTracker r13 = r11.f4068j
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.f4068j = r13
        L81:
            android.view.VelocityTracker r13 = r11.f4068j
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f4064f
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = r3
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.a.D(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    boolean H(V v6) {
        return false;
    }

    final boolean J(CoordinatorLayout coordinatorLayout, V v6, int i7, int i8, float f7) {
        Runnable runnable = this.f4062d;
        if (runnable != null) {
            v6.removeCallbacks(runnable);
            this.f4062d = null;
        }
        if (this.f4063e == null) {
            this.f4063e = new OverScroller(v6.getContext());
        }
        this.f4063e.fling(0, E(), 0, Math.round(f7), 0, 0, i7, i8);
        if (!this.f4063e.computeScrollOffset()) {
            N(coordinatorLayout, v6);
            return false;
        }
        RunnableC0045a runnableC0045a = new RunnableC0045a(coordinatorLayout, v6);
        this.f4062d = runnableC0045a;
        u.c0(v6, runnableC0045a);
        return true;
    }

    int K(V v6) {
        return -v6.getHeight();
    }

    int L(V v6) {
        return v6.getHeight();
    }

    int M() {
        return E();
    }

    void N(CoordinatorLayout coordinatorLayout, V v6) {
    }

    final int O(CoordinatorLayout coordinatorLayout, V v6, int i7, int i8, int i9) {
        return Q(coordinatorLayout, v6, M() - i7, i8, i9);
    }

    int P(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        return Q(coordinatorLayout, v6, i7, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int Q(CoordinatorLayout coordinatorLayout, V v6, int i7, int i8, int i9) {
        int iB;
        int iE = E();
        if (i8 == 0 || iE < i8 || iE > i9 || iE == (iB = c0.a.b(i7, i8, i9))) {
            return 0;
        }
        G(iB);
        return iE - iB;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.f4067i < 0) {
            this.f4067i = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f4064f) {
            int i7 = this.f4065g;
            if (i7 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i7)) == -1) {
                return false;
            }
            int y6 = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y6 - this.f4066h) > this.f4067i) {
                this.f4066h = y6;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f4065g = -1;
            int x6 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            boolean z6 = H(v6) && coordinatorLayout.B(v6, x6, y7);
            this.f4064f = z6;
            if (z6) {
                this.f4066h = y7;
                this.f4065g = motionEvent.getPointerId(0);
                I();
                OverScroller overScroller = this.f4063e;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f4063e.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f4068j;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4065g = -1;
        this.f4067i = -1;
    }
}
