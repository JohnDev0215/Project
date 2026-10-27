package com.google.android.material.behavior;

import a.h.k.q;
import a.h.k.x.b;
import a.j.b.e;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f2366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f2367b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2368c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2369d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2370e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2371f = 0.5f;
    public float g = 0.0f;
    public float h = 0.5f;
    public final e.c i = new a();

    public class a extends e.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2372a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2373b = -1;

        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
        @Override // a.j.b.e.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int a(android.view.View r3, int r4, int r5) {
            /*
                r2 = this;
                int r5 = a.h.k.q.p(r3)
                r0 = 1
                if (r5 != r0) goto L9
                r5 = r0
                goto La
            L9:
                r5 = 0
            La:
                com.google.android.material.behavior.SwipeDismissBehavior r1 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r1 = r1.f2370e
                if (r1 != 0) goto L24
                if (r5 == 0) goto L1c
            L12:
                int r5 = r2.f2372a
                int r3 = r3.getWidth()
                int r5 = r5 - r3
                int r2 = r2.f2372a
                goto L37
            L1c:
                int r5 = r2.f2372a
                int r2 = r3.getWidth()
                int r2 = r2 + r5
                goto L37
            L24:
                if (r1 != r0) goto L29
                if (r5 == 0) goto L12
                goto L1c
            L29:
                int r5 = r2.f2372a
                int r0 = r3.getWidth()
                int r5 = r5 - r0
                int r2 = r2.f2372a
                int r3 = r3.getWidth()
                int r2 = r2 + r3
            L37:
                int r3 = java.lang.Math.max(r5, r4)
                int r2 = java.lang.Math.min(r3, r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.a(android.view.View, int, int):int");
        }

        @Override // a.j.b.e.c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // a.j.b.e.c
        public int c(View view) {
            return view.getWidth();
        }

        @Override // a.j.b.e.c
        public void e(View view, int i) {
            this.f2373b = i;
            this.f2372a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // a.j.b.e.c
        public void f(int i) {
            b bVar = SwipeDismissBehavior.this.f2367b;
            if (bVar != null) {
                bVar.b(i);
            }
        }

        @Override // a.j.b.e.c
        public void g(View view, int i, int i2, int i3, int i4) {
            float width = (view.getWidth() * SwipeDismissBehavior.this.g) + this.f2372a;
            float width2 = (view.getWidth() * SwipeDismissBehavior.this.h) + this.f2372a;
            float f2 = i;
            if (f2 <= width) {
                view.setAlpha(1.0f);
            } else if (f2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.C(0.0f, 1.0f - ((f2 - width) / (width2 - width)), 1.0f));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x002c  */
        @Override // a.j.b.e.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void h(android.view.View r8, float r9, float r10) {
            /*
                r7 = this;
                r10 = -1
                r7.f2373b = r10
                int r10 = r8.getWidth()
                r0 = 0
                int r1 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L3a
                int r4 = a.h.k.q.p(r8)
                if (r4 != r2) goto L16
                r4 = r2
                goto L17
            L16:
                r4 = r3
            L17:
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r5 = r5.f2370e
                r6 = 2
                if (r5 != r6) goto L1f
                goto L2a
            L1f:
                if (r5 != 0) goto L2e
                if (r4 == 0) goto L28
                int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r9 >= 0) goto L2c
                goto L2a
            L28:
                if (r1 <= 0) goto L2c
            L2a:
                r9 = r2
                goto L56
            L2c:
                r9 = r3
                goto L56
            L2e:
                if (r5 != r2) goto L2c
                if (r4 == 0) goto L35
                if (r1 <= 0) goto L2c
                goto L39
            L35:
                int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r9 >= 0) goto L2c
            L39:
                goto L2a
            L3a:
                int r9 = r8.getLeft()
                int r0 = r7.f2372a
                int r9 = r9 - r0
                int r0 = r8.getWidth()
                float r0 = (float) r0
                com.google.android.material.behavior.SwipeDismissBehavior r1 = com.google.android.material.behavior.SwipeDismissBehavior.this
                float r1 = r1.f2371f
                float r0 = r0 * r1
                int r0 = java.lang.Math.round(r0)
                int r9 = java.lang.Math.abs(r9)
                if (r9 < r0) goto L2c
                goto L2a
            L56:
                if (r9 == 0) goto L64
                int r9 = r8.getLeft()
                int r0 = r7.f2372a
                if (r9 >= r0) goto L62
                int r0 = r0 - r10
                goto L67
            L62:
                int r0 = r0 + r10
                goto L67
            L64:
                int r0 = r7.f2372a
                r2 = r3
            L67:
                com.google.android.material.behavior.SwipeDismissBehavior r9 = com.google.android.material.behavior.SwipeDismissBehavior.this
                a.j.b.e r9 = r9.f2366a
                int r10 = r8.getTop()
                boolean r9 = r9.t(r0, r10)
                if (r9 == 0) goto L80
                com.google.android.material.behavior.SwipeDismissBehavior$c r9 = new com.google.android.material.behavior.SwipeDismissBehavior$c
                com.google.android.material.behavior.SwipeDismissBehavior r7 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r9.<init>(r8, r2)
                a.h.k.q.N(r8, r9)
                goto L8b
            L80:
                if (r2 == 0) goto L8b
                com.google.android.material.behavior.SwipeDismissBehavior r7 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$b r7 = r7.f2367b
                if (r7 == 0) goto L8b
                r7.a(r8)
            L8b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.h(android.view.View, float, float):void");
        }

        @Override // a.j.b.e.c
        public boolean i(View view, int i) {
            int i2 = this.f2373b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.B(view);
        }
    }

    public interface b {
        void a(View view);

        void b(int i);
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f2375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2376b;

        public c(View view, boolean z) {
            this.f2375a = view;
            this.f2376b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar;
            e eVar = SwipeDismissBehavior.this.f2366a;
            if (eVar != null && eVar.i(true)) {
                q.N(this.f2375a, this);
            } else {
                if (!this.f2376b || (bVar = SwipeDismissBehavior.this.f2367b) == null) {
                    return;
                }
                bVar.a(this.f2375a);
            }
        }
    }

    public static float C(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        e eVar = this.f2366a;
        if (eVar == null) {
            return false;
        }
        eVar.n(motionEvent);
        return true;
    }

    public boolean B(View view) {
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean j(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zJ = this.f2368c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zJ = coordinatorLayout.j(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f2368c = zJ;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f2368c = false;
        }
        if (!zJ) {
            return false;
        }
        if (this.f2366a == null) {
            this.f2366a = new e(coordinatorLayout.getContext(), coordinatorLayout, this.i);
        }
        return this.f2366a.u(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (q.n(v) != 0) {
            return false;
        }
        v.setImportantForAccessibility(1);
        q.P(v, 1048576);
        if (!B(v)) {
            return false;
        }
        q.R(v, b.a.j, null, new b.d.a.a.n.a(this));
        return false;
    }
}
