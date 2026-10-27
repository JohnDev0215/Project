package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import i0.u;
import j0.c;
import j0.f;
import m0.c;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    m0.c f4128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f4129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4130c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4132e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f4131d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f4133f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f4134g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f4135h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f4136i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final c.AbstractC0122c f4137j = new a();

    class a extends c.AbstractC0122c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f4138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4139b = -1;

        a() {
        }

        private boolean n(View view, float f7) {
            if (f7 == 0.0f) {
                return Math.abs(view.getLeft() - this.f4138a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f4134g);
            }
            boolean z6 = u.y(view) == 1;
            int i7 = SwipeDismissBehavior.this.f4133f;
            if (i7 == 2) {
                return true;
            }
            if (i7 == 0) {
                if (z6) {
                    if (f7 >= 0.0f) {
                        return false;
                    }
                } else if (f7 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i7 != 1) {
                return false;
            }
            if (z6) {
                if (f7 <= 0.0f) {
                    return false;
                }
            } else if (f7 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // m0.c.AbstractC0122c
        public int a(View view, int i7, int i8) {
            int width;
            int width2;
            int width3;
            boolean z6 = u.y(view) == 1;
            int i9 = SwipeDismissBehavior.this.f4133f;
            if (i9 == 0) {
                if (z6) {
                    width = this.f4138a - view.getWidth();
                    width2 = this.f4138a;
                } else {
                    width = this.f4138a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i9 != 1) {
                width = this.f4138a - view.getWidth();
                width2 = view.getWidth() + this.f4138a;
            } else if (z6) {
                width = this.f4138a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f4138a - view.getWidth();
                width2 = this.f4138a;
            }
            return SwipeDismissBehavior.G(width, i7, width2);
        }

        @Override // m0.c.AbstractC0122c
        public int b(View view, int i7, int i8) {
            return view.getTop();
        }

        @Override // m0.c.AbstractC0122c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // m0.c.AbstractC0122c
        public void i(View view, int i7) {
            this.f4139b = i7;
            this.f4138a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // m0.c.AbstractC0122c
        public void j(int i7) {
            c cVar = SwipeDismissBehavior.this.f4129b;
            if (cVar != null) {
                cVar.b(i7);
            }
        }

        @Override // m0.c.AbstractC0122c
        public void k(View view, int i7, int i8, int i9, int i10) {
            float width = this.f4138a + (view.getWidth() * SwipeDismissBehavior.this.f4135h);
            float width2 = this.f4138a + (view.getWidth() * SwipeDismissBehavior.this.f4136i);
            float f7 = i7;
            if (f7 <= width) {
                view.setAlpha(1.0f);
            } else if (f7 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f7), 1.0f));
            }
        }

        @Override // m0.c.AbstractC0122c
        public void l(View view, float f7, float f8) {
            int i7;
            boolean z6;
            c cVar;
            this.f4139b = -1;
            int width = view.getWidth();
            if (n(view, f7)) {
                int left = view.getLeft();
                int i8 = this.f4138a;
                i7 = left < i8 ? i8 - width : i8 + width;
                z6 = true;
            } else {
                i7 = this.f4138a;
                z6 = false;
            }
            if (SwipeDismissBehavior.this.f4128a.F(i7, view.getTop())) {
                u.c0(view, new d(view, z6));
            } else {
                if (!z6 || (cVar = SwipeDismissBehavior.this.f4129b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // m0.c.AbstractC0122c
        public boolean m(View view, int i7) {
            int i8 = this.f4139b;
            return (i8 == -1 || i8 == i7) && SwipeDismissBehavior.this.E(view);
        }
    }

    class b implements f {
        b() {
        }

        @Override // j0.f
        public boolean a(View view, f.a aVar) {
            boolean z6 = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z7 = u.y(view) == 1;
            int i7 = SwipeDismissBehavior.this.f4133f;
            if ((i7 == 0 && z7) || (i7 == 1 && !z7)) {
                z6 = true;
            }
            int width = view.getWidth();
            if (z6) {
                width = -width;
            }
            u.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f4129b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i7);
    }

    private class d implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final View f4142e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final boolean f4143g;

        d(View view, boolean z6) {
            this.f4142e = view;
            this.f4143g = z6;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            m0.c cVar2 = SwipeDismissBehavior.this.f4128a;
            if (cVar2 != null && cVar2.k(true)) {
                u.c0(this.f4142e, this);
            } else {
                if (!this.f4143g || (cVar = SwipeDismissBehavior.this.f4129b) == null) {
                    return;
                }
                cVar.a(this.f4142e);
            }
        }
    }

    static float F(float f7, float f8, float f9) {
        return Math.min(Math.max(f7, f8), f9);
    }

    static int G(int i7, int i8, int i9) {
        return Math.min(Math.max(i7, i8), i9);
    }

    private void H(ViewGroup viewGroup) {
        if (this.f4128a == null) {
            this.f4128a = this.f4132e ? m0.c.l(viewGroup, this.f4131d, this.f4137j) : m0.c.m(viewGroup, this.f4137j);
        }
    }

    static float I(float f7, float f8, float f9) {
        return (f9 - f7) / (f8 - f7);
    }

    private void M(View view) {
        u.e0(view, 1048576);
        if (E(view)) {
            u.g0(view, c.a.f7133y, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
        m0.c cVar = this.f4128a;
        if (cVar == null) {
            return false;
        }
        cVar.z(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public void J(float f7) {
        this.f4136i = F(0.0f, f7, 1.0f);
    }

    public void K(float f7) {
        this.f4135h = F(0.0f, f7, 1.0f);
    }

    public void L(int i7) {
        this.f4133f = i7;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v6, MotionEvent motionEvent) {
        boolean zB = this.f4130c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zB = coordinatorLayout.B(v6, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f4130c = zB;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f4130c = false;
        }
        if (!zB) {
            return false;
        }
        H(coordinatorLayout);
        return this.f4128a.G(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        boolean zL = super.l(coordinatorLayout, v6, i7);
        if (u.w(v6) == 0) {
            u.t0(v6, 1);
            M(v6);
        }
        return zL;
    }
}
