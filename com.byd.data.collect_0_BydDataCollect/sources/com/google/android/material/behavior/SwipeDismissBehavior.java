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
    b.i.a.c f3720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f3721b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3722c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3724e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3723d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f3725f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f3726g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f3727h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f3728i = 0.5f;
    private final c.AbstractC0052c j = new a();

    class a extends c.AbstractC0052c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3730b = -1;

        a() {
        }

        private boolean n(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f3729a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f3726g);
            }
            boolean z = t.z(view) == 1;
            int i2 = SwipeDismissBehavior.this.f3725f;
            if (i2 == 2) {
                return true;
            }
            if (i2 == 0) {
                if (z) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i2 != 1) {
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

        @Override // b.i.a.c.AbstractC0052c
        public int a(View view, int i2, int i3) {
            int width;
            int width2;
            int width3;
            boolean z = t.z(view) == 1;
            int i4 = SwipeDismissBehavior.this.f3725f;
            if (i4 == 0) {
                if (z) {
                    width = this.f3729a - view.getWidth();
                    width2 = this.f3729a;
                } else {
                    width = this.f3729a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i4 != 1) {
                width = this.f3729a - view.getWidth();
                width2 = view.getWidth() + this.f3729a;
            } else if (z) {
                width = this.f3729a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f3729a - view.getWidth();
                width2 = this.f3729a;
            }
            return SwipeDismissBehavior.G(width, i2, width2);
        }

        @Override // b.i.a.c.AbstractC0052c
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // b.i.a.c.AbstractC0052c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // b.i.a.c.AbstractC0052c
        public void i(View view, int i2) {
            this.f3730b = i2;
            this.f3729a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // b.i.a.c.AbstractC0052c
        public void j(int i2) {
            c cVar = SwipeDismissBehavior.this.f3721b;
            if (cVar != null) {
                cVar.b(i2);
            }
        }

        @Override // b.i.a.c.AbstractC0052c
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = this.f3729a + (view.getWidth() * SwipeDismissBehavior.this.f3727h);
            float width2 = this.f3729a + (view.getWidth() * SwipeDismissBehavior.this.f3728i);
            float f2 = i2;
            if (f2 <= width) {
                view.setAlpha(1.0f);
            } else if (f2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f2), 1.0f));
            }
        }

        @Override // b.i.a.c.AbstractC0052c
        public void l(View view, float f2, float f3) {
            int i2;
            boolean z;
            c cVar;
            this.f3730b = -1;
            int width = view.getWidth();
            if (n(view, f2)) {
                int left = view.getLeft();
                int i3 = this.f3729a;
                i2 = left < i3 ? i3 - width : i3 + width;
                z = true;
            } else {
                i2 = this.f3729a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f3720a.O(i2, view.getTop())) {
                t.b0(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f3721b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // b.i.a.c.AbstractC0052c
        public boolean m(View view, int i2) {
            int i3 = this.f3730b;
            return (i3 == -1 || i3 == i2) && SwipeDismissBehavior.this.E(view);
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
            int i2 = SwipeDismissBehavior.this.f3725f;
            if ((i2 == 0 && z2) || (i2 == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            t.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f3721b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i2);
    }

    private class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f3733b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f3734c;

        d(View view, boolean z) {
            this.f3733b = view;
            this.f3734c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            b.i.a.c cVar2 = SwipeDismissBehavior.this.f3720a;
            if (cVar2 != null && cVar2.m(true)) {
                t.b0(this.f3733b, this);
            } else {
                if (!this.f3734c || (cVar = SwipeDismissBehavior.this.f3721b) == null) {
                    return;
                }
                cVar.a(this.f3733b);
            }
        }
    }

    static float F(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    static int G(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    private void H(ViewGroup viewGroup) {
        if (this.f3720a == null) {
            this.f3720a = this.f3724e ? b.i.a.c.n(viewGroup, this.f3723d, this.j) : b.i.a.c.o(viewGroup, this.j);
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
        b.i.a.c cVar = this.f3720a;
        if (cVar == null) {
            return false;
        }
        cVar.F(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public void J(float f2) {
        this.f3728i = F(0.0f, f2, 1.0f);
    }

    public void K(float f2) {
        this.f3727h = F(0.0f, f2, 1.0f);
    }

    public void L(int i2) {
        this.f3725f = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zF = this.f3722c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f3722c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3722c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f3720a.P(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        boolean zL = super.l(coordinatorLayout, v, i2);
        if (t.x(v) == 0) {
            t.t0(v, 1);
            M(v);
        }
        return zL;
    }
}
