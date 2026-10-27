package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.in3;
import defpackage.om3;
import defpackage.qm3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public qm3 a;
    public boolean b;
    public int c = 2;
    public final float d = 0.5f;
    public float e = 0.0f;
    public float f = 0.5f;
    public final a g = new a();

    public class a extends qm3.c {
        public int a;
        public int b = -1;

        public a() {
        }

        @Override // qm3.c
        public final int a(View view, int i) {
            int width;
            int width2;
            int i2;
            int width3;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            boolean z = om3.e.c(view) == 1;
            int i3 = SwipeDismissBehavior.this.c;
            if (i3 == 0) {
                if (z) {
                    width = this.a - view.getWidth();
                    width2 = this.a;
                } else {
                    i2 = this.a;
                    width3 = view.getWidth();
                    width = i2;
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.a - view.getWidth();
                width2 = this.a + view.getWidth();
            } else if (z) {
                i2 = this.a;
                width3 = view.getWidth();
                width = i2;
                width2 = width3 + width;
            } else {
                width = this.a - view.getWidth();
                width2 = this.a;
            }
            return Math.min(Math.max(width, i), width2);
        }

        @Override // qm3.c
        public final int b(View view, int i) {
            return view.getTop();
        }

        @Override // qm3.c
        public final int c(View view) {
            return view.getWidth();
        }

        @Override // qm3.c
        public final void g(View view, int i) {
            this.b = i;
            this.a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // qm3.c
        public final void h(int i) {
            SwipeDismissBehavior.this.getClass();
        }

        @Override // qm3.c
        public final void i(View view, int i, int i2) {
            float f = this.a;
            float width = view.getWidth();
            SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
            float f2 = (width * swipeDismissBehavior.e) + f;
            float width2 = (view.getWidth() * swipeDismissBehavior.f) + this.a;
            float f3 = i;
            if (f3 <= f2) {
                view.setAlpha(1.0f);
            } else if (f3 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((f3 - f2) / (width2 - f2))), 1.0f));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
        @Override // qm3.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void j(android.view.View r9, float r10, float r11) {
            /*
                r8 = this;
                r11 = -1
                r8.b = r11
                int r11 = r9.getWidth()
                r0 = 0
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                r2 = 1
                com.google.android.material.behavior.SwipeDismissBehavior r3 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r4 = 0
                if (r1 == 0) goto L39
                java.util.WeakHashMap<android.view.View, in3> r5 = defpackage.om3.a
                int r5 = om3.e.c(r9)
                if (r5 != r2) goto L1a
                r5 = r2
                goto L1b
            L1a:
                r5 = r4
            L1b:
                int r6 = r3.c
                r7 = 2
                if (r6 != r7) goto L21
                goto L52
            L21:
                if (r6 != 0) goto L2d
                if (r5 == 0) goto L2a
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L54
                goto L52
            L2a:
                if (r1 <= 0) goto L54
                goto L52
            L2d:
                if (r6 != r2) goto L54
                if (r5 == 0) goto L34
                if (r1 <= 0) goto L54
                goto L52
            L34:
                int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r1 >= 0) goto L54
                goto L52
            L39:
                int r1 = r9.getLeft()
                int r5 = r8.a
                int r1 = r1 - r5
                int r5 = r9.getWidth()
                float r5 = (float) r5
                float r6 = r3.d
                float r5 = r5 * r6
                int r5 = java.lang.Math.round(r5)
                int r1 = java.lang.Math.abs(r1)
                if (r1 < r5) goto L54
            L52:
                r1 = r2
                goto L55
            L54:
                r1 = r4
            L55:
                if (r1 == 0) goto L6b
                int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
                if (r10 < 0) goto L66
                int r10 = r9.getLeft()
                int r0 = r8.a
                if (r10 >= r0) goto L64
                goto L66
            L64:
                int r0 = r0 + r11
                goto L6e
            L66:
                int r8 = r8.a
                int r0 = r8 - r11
                goto L6e
            L6b:
                int r0 = r8.a
                r2 = r4
            L6e:
                qm3 r8 = r3.a
                int r10 = r9.getTop()
                boolean r8 = r8.t(r0, r10)
                if (r8 == 0) goto L85
                com.google.android.material.behavior.SwipeDismissBehavior$c r8 = new com.google.android.material.behavior.SwipeDismissBehavior$c
                r8.<init>(r9, r2)
                java.util.WeakHashMap<android.view.View, in3> r10 = defpackage.om3.a
                om3.d.k(r9, r8)
                goto L8a
            L85:
                if (r2 == 0) goto L8a
                r3.getClass()
            L8a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.j(android.view.View, float, float):void");
        }

        @Override // qm3.c
        public final boolean k(View view, int i) {
            int i2 = this.b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.s(view);
        }
    }

    public interface b {
    }

    public class c implements Runnable {
        public final View e;
        public final boolean f;

        public c(View view, boolean z) {
            this.e = view;
            this.f = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            SwipeDismissBehavior swipeDismissBehavior = SwipeDismissBehavior.this;
            qm3 qm3Var = swipeDismissBehavior.a;
            View view = this.e;
            if (qm3Var != null && qm3Var.h()) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.k(view, this);
            } else if (this.f) {
                swipeDismissBehavior.getClass();
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean g(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zU = this.b;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zU = coordinatorLayout.u(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.b = zU;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.b = false;
        }
        if (!zU) {
            return false;
        }
        if (this.a == null) {
            this.a = new qm3(coordinatorLayout.getContext(), coordinatorLayout, this.g);
        }
        return this.a.u(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean h(CoordinatorLayout coordinatorLayout, V v, int i) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.d.b(v) == 0) {
            om3.d.p(v, 1);
            om3.i(v, 1048576);
            om3.f(v, 0);
            if (s(v)) {
                om3.j(v, AccessibilityNodeInfoCompat.a.w, null, new com.google.android.material.behavior.a(this));
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean r(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        qm3 qm3Var = this.a;
        if (qm3Var == null) {
            return false;
        }
        qm3Var.m(motionEvent);
        return true;
    }

    public boolean s(View view) {
        return true;
    }

    public void setListener(b bVar) {
    }
}
