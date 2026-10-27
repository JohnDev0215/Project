package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.k.c0.c;
import b.g.k.c0.f;
import b.g.k.t;
import b.i.a.c;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b.i.a.c f3337a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f3338b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3339c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3341e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3340d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f3342f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f3343g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f3344h = 0.0f;
    float i = 0.5f;
    private final c.AbstractC0046c j = new a();

    class a extends c.AbstractC0046c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3346b = -1;

        a() {
        }

        private boolean n(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f3345a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f3343g);
            }
            boolean z = t.z(view) == 1;
            int i = SwipeDismissBehavior.this.f3342f;
            if (i == 2) {
                return true;
            }
            if (i == 0) {
                if (z) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i != 1) {
                return false;
            }
            if (z) {
                if (f2 <= 0.0f) {
                    return false;
                }
            } else if (f2 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // b.i.a.c.AbstractC0046c
        public int a(View view, int i, int i2) {
            int width;
            int width2;
            int width3;
            boolean z = t.z(view) == 1;
            int i3 = SwipeDismissBehavior.this.f3342f;
            if (i3 == 0) {
                if (z) {
                    width = this.f3345a - view.getWidth();
                    width2 = this.f3345a;
                } else {
                    width = this.f3345a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.f3345a - view.getWidth();
                width2 = view.getWidth() + this.f3345a;
            } else if (z) {
                width = this.f3345a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f3345a - view.getWidth();
                width2 = this.f3345a;
            }
            return SwipeDismissBehavior.G(width, i, width2);
        }

        @Override // b.i.a.c.AbstractC0046c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // b.i.a.c.AbstractC0046c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // b.i.a.c.AbstractC0046c
        public void i(View view, int i) {
            this.f3346b = i;
            this.f3345a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // b.i.a.c.AbstractC0046c
        public void j(int i) {
            c cVar = SwipeDismissBehavior.this.f3338b;
            if (cVar != null) {
                cVar.b(i);
            }
        }

        @Override // b.i.a.c.AbstractC0046c
        public void k(View view, int i, int i2, int i3, int i4) {
            float width = this.f3345a + (view.getWidth() * SwipeDismissBehavior.this.f3344h);
            float width2 = this.f3345a + (view.getWidth() * SwipeDismissBehavior.this.i);
            float f2 = i;
            if (f2 <= width) {
                view.setAlpha(1.0f);
            } else if (f2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f2), 1.0f));
            }
        }

        @Override // b.i.a.c.AbstractC0046c
        public void l(View view, float f2, float f3) {
            int i;
            boolean z;
            c cVar;
            this.f3346b = -1;
            int width = view.getWidth();
            if (n(view, f2)) {
                int left = view.getLeft();
                int i2 = this.f3345a;
                i = left < i2 ? i2 - width : i2 + width;
                z = true;
            } else {
                i = this.f3345a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f3337a.M(i, view.getTop())) {
                t.b0(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f3338b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // b.i.a.c.AbstractC0046c
        public boolean m(View view, int i) {
            int i2 = this.f3346b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.E(view);
        }
    }

    class b implements f {
        b() {
        }

        @Override // b.g.k.c0.f
        public boolean a(View view, f.a aVar) {
            boolean z = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z2 = t.z(view) == 1;
            int i = SwipeDismissBehavior.this.f3342f;
            if ((i == 0 && z2) || (i == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            t.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f3338b;
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
        private final View f3349b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f3350c;

        d(View view, boolean z) {
            this.f3349b = view;
            this.f3350c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            b.i.a.c cVar2 = SwipeDismissBehavior.this.f3337a;
            if (cVar2 != null && cVar2.m(true)) {
                t.b0(this.f3349b, this);
            } else {
                if (!this.f3350c || (cVar = SwipeDismissBehavior.this.f3338b) == null) {
                    return;
                }
                cVar.a(this.f3349b);
            }
        }
    }

    static float F(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    static int G(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void H(ViewGroup viewGroup) {
        if (this.f3337a == null) {
            this.f3337a = this.f3341e ? b.i.a.c.n(viewGroup, this.f3340d, this.j) : b.i.a.c.o(viewGroup, this.j);
        }
    }

    static float I(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    private void M(View view) {
        t.d0(view, 1048576);
        if (E(view)) {
            t.f0(view, c.a.u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        b.i.a.c cVar = this.f3337a;
        if (cVar == null) {
            return false;
        }
        cVar.E(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public void J(float f2) {
        this.i = F(0.0f, f2, 1.0f);
    }

    public void K(float f2) {
        this.f3344h = F(0.0f, f2, 1.0f);
    }

    public void L(int i) {
        this.f3342f = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zF = this.f3339c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f3339c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3339c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f3337a.N(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        boolean zL = super.l(coordinatorLayout, v, i);
        if (t.x(v) == 0) {
            t.t0(v, 1);
            M(v);
        }
        return zL;
    }
}
