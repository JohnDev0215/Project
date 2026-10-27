package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.cn3;
import defpackage.in3;
import defpackage.n3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    public a c;
    public OverScroller d;
    public boolean e;
    public int f;
    public int g;
    public int h;
    public VelocityTracker i;

    public class a implements Runnable {
        public final CoordinatorLayout e;
        public final V f;

        public a(CoordinatorLayout coordinatorLayout, V v) {
            this.e = coordinatorLayout;
            this.f = v;
        }

        @Override // java.lang.Runnable
        public final void run() {
            HeaderBehavior headerBehavior;
            OverScroller overScroller;
            V v = this.f;
            if (v == null || (overScroller = (headerBehavior = HeaderBehavior.this).d) == null) {
                return;
            }
            boolean zComputeScrollOffset = overScroller.computeScrollOffset();
            CoordinatorLayout coordinatorLayout = this.e;
            if (!zComputeScrollOffset) {
                headerBehavior.y(v, coordinatorLayout);
                return;
            }
            headerBehavior.A(coordinatorLayout, v, headerBehavior.d.getCurrY());
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.k(v, this);
        }
    }

    public HeaderBehavior() {
        this.f = -1;
        this.h = -1;
    }

    public final void A(CoordinatorLayout coordinatorLayout, View view, int i) {
        z(coordinatorLayout, view, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean g(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.h < 0) {
            this.h = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.e) {
            int i = this.f;
            if (i == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y - this.g) > this.h) {
                this.g = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            boolean z = u(v) && coordinatorLayout.u(v, x, y2);
            this.e = z;
            if (z) {
                this.g = y2;
                this.f = motionEvent.getPointerId(0);
                if (this.i == null) {
                    this.i = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.d;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.d.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.i;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00dc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean r(androidx.coordinatorlayout.widget.CoordinatorLayout r20, V r21, android.view.MotionEvent r22) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.r(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean u(V v) {
        return false;
    }

    public int v(V v) {
        return -v.getHeight();
    }

    public int w(V v) {
        return v.getHeight();
    }

    public int x() {
        return s();
    }

    public int z(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3) {
        int iW;
        int iS = s();
        if (i2 == 0 || iS < i2 || iS > i3 || iS == (iW = n3.w(i, i2, i3))) {
            return 0;
        }
        cn3 cn3Var = this.a;
        if (cn3Var != null) {
            cn3Var.b(iW);
        } else {
            this.b = iW;
        }
        return iS - iW;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = -1;
        this.h = -1;
    }

    public void y(View view, CoordinatorLayout coordinatorLayout) {
    }
}
