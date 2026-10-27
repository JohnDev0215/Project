package com.google.android.material.behavior;

import a.g.k.c0.c;
import a.g.k.c0.f;
import a.g.k.t;
import a.i.b.c;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a.i.b.c f2980a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f2981b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2982c;
    private boolean e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2983d = 0.0f;
    int f = 2;
    float g = 0.5f;
    float h = 0.0f;
    float i = 0.5f;
    private final c.AbstractC0020c j = new a();

    class a extends c.AbstractC0020c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2984a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2985b = -1;

        a() {
        }

        private boolean n(View view, float f) {
            if (f == 0.0f) {
                return Math.abs(view.getLeft() - this.f2984a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            }
            boolean z = t.y(view) == 1;
            int i = SwipeDismissBehavior.this.f;
            if (i == 2) {
                return true;
            }
            if (i == 0) {
                if (z) {
                    if (f >= 0.0f) {
                        return false;
                    }
                } else if (f <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i != 1) {
                return false;
            }
            if (z) {
                if (f <= 0.0f) {
                    return false;
                }
            } else if (f >= 0.0f) {
                return false;
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0012  */
        @Override // a.i.b.c.AbstractC0020c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int a(android.view.View r3, int r4, int r5) {
            /*
                r2 = this;
                int r5 = a.g.k.t.y(r3)
                r0 = 1
                if (r5 != r0) goto L9
                r5 = r0
                goto La
            L9:
                r5 = 0
            La:
                com.google.android.material.behavior.SwipeDismissBehavior r1 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r1 = r1.f
                if (r1 != 0) goto L24
                if (r5 == 0) goto L1c
            L12:
                int r5 = r2.f2984a
                int r3 = r3.getWidth()
                int r5 = r5 - r3
                int r3 = r2.f2984a
                goto L37
            L1c:
                int r5 = r2.f2984a
                int r3 = r3.getWidth()
                int r3 = r3 + r5
                goto L37
            L24:
                if (r1 != r0) goto L29
                if (r5 == 0) goto L12
                goto L1c
            L29:
                int r5 = r2.f2984a
                int r0 = r3.getWidth()
                int r5 = r5 - r0
                int r0 = r2.f2984a
                int r3 = r3.getWidth()
                int r3 = r3 + r0
            L37:
                int r3 = com.google.android.material.behavior.SwipeDismissBehavior.G(r5, r4, r3)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.a(android.view.View, int, int):int");
        }

        @Override // a.i.b.c.AbstractC0020c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // a.i.b.c.AbstractC0020c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // a.i.b.c.AbstractC0020c
        public void i(View view, int i) {
            this.f2985b = i;
            this.f2984a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // a.i.b.c.AbstractC0020c
        public void j(int i) {
            c cVar = SwipeDismissBehavior.this.f2981b;
            if (cVar != null) {
                cVar.b(i);
            }
        }

        @Override // a.i.b.c.AbstractC0020c
        public void k(View view, int i, int i2, int i3, int i4) {
            float width = this.f2984a + (view.getWidth() * SwipeDismissBehavior.this.h);
            float width2 = this.f2984a + (view.getWidth() * SwipeDismissBehavior.this.i);
            float f = i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f), 1.0f));
            }
        }

        @Override // a.i.b.c.AbstractC0020c
        public void l(View view, float f, float f2) {
            int i;
            boolean z;
            c cVar;
            this.f2985b = -1;
            int width = view.getWidth();
            if (n(view, f)) {
                int left = view.getLeft();
                int i2 = this.f2984a;
                i = left < i2 ? i2 - width : i2 + width;
                z = true;
            } else {
                i = this.f2984a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f2980a.F(i, view.getTop())) {
                t.Z(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f2981b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // a.i.b.c.AbstractC0020c
        public boolean m(View view, int i) {
            int i2 = this.f2985b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.E(view);
        }
    }

    class b implements f {
        b() {
        }

        @Override // a.g.k.c0.f
        public boolean a(View view, f.a aVar) {
            boolean z = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z2 = t.y(view) == 1;
            int i = SwipeDismissBehavior.this.f;
            if ((i == 0 && z2) || (i == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            t.S(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f2981b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i);
    }

    private class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f2988b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f2989c;

        d(View view, boolean z) {
            this.f2988b = view;
            this.f2989c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            a.i.b.c cVar2 = SwipeDismissBehavior.this.f2980a;
            if (cVar2 != null && cVar2.k(true)) {
                t.Z(this.f2988b, this);
            } else {
                if (!this.f2989c || (cVar = SwipeDismissBehavior.this.f2981b) == null) {
                    return;
                }
                cVar.a(this.f2988b);
            }
        }
    }

    static float F(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int G(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void H(ViewGroup viewGroup) {
        if (this.f2980a == null) {
            this.f2980a = this.e ? a.i.b.c.l(viewGroup, this.f2983d, this.j) : a.i.b.c.m(viewGroup, this.j);
        }
    }

    static float I(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    private void M(View view) {
        t.b0(view, 1048576);
        if (E(view)) {
            t.d0(view, c.a.u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        a.i.b.c cVar = this.f2980a;
        if (cVar == null) {
            return false;
        }
        cVar.z(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public void J(float f) {
        this.i = F(0.0f, f, 1.0f);
    }

    public void K(float f) {
        this.h = F(0.0f, f, 1.0f);
    }

    public void L(int i) {
        this.f = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zB = this.f2982c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zB = coordinatorLayout.B(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f2982c = zB;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f2982c = false;
        }
        if (!zB) {
            return false;
        }
        H(coordinatorLayout);
        return this.f2980a.G(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        boolean zL = super.l(coordinatorLayout, v, i);
        if (t.w(v) == 0) {
            t.q0(v, 1);
            M(v);
        }
        return zL;
    }
}
