package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.h.m.c0.c;
import b.h.m.c0.f;
import b.h.m.t;
import b.j.a.c;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.j.a.c f4510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f4511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4512c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4514e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4513d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4515f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4516g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4517h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f4518i = 0.5f;
    public final c.AbstractC0050c j = new a();

    public class a extends c.AbstractC0050c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4519a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4520b = -1;

        public a() {
        }

        @Override // b.j.a.c.AbstractC0050c
        public int a(View view, int i2, int i3) {
            int width;
            int width2;
            int width3;
            boolean z = t.z(view) == 1;
            int i4 = SwipeDismissBehavior.this.f4515f;
            if (i4 == 0) {
                if (z) {
                    width = this.f4519a - view.getWidth();
                    width2 = this.f4519a;
                } else {
                    width = this.f4519a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i4 != 1) {
                width = this.f4519a - view.getWidth();
                width2 = view.getWidth() + this.f4519a;
            } else if (z) {
                width = this.f4519a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f4519a - view.getWidth();
                width2 = this.f4519a;
            }
            return SwipeDismissBehavior.G(width, i2, width2);
        }

        @Override // b.j.a.c.AbstractC0050c
        public int b(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // b.j.a.c.AbstractC0050c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // b.j.a.c.AbstractC0050c
        public void i(View view, int i2) {
            this.f4520b = i2;
            this.f4519a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // b.j.a.c.AbstractC0050c
        public void j(int i2) {
            c cVar = SwipeDismissBehavior.this.f4511b;
            if (cVar != null) {
                cVar.b(i2);
            }
        }

        @Override // b.j.a.c.AbstractC0050c
        public void k(View view, int i2, int i3, int i4, int i5) {
            float width = this.f4519a + (view.getWidth() * SwipeDismissBehavior.this.f4517h);
            float width2 = this.f4519a + (view.getWidth() * SwipeDismissBehavior.this.f4518i);
            float f2 = i2;
            if (f2 <= width) {
                view.setAlpha(1.0f);
            } else if (f2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f2), 1.0f));
            }
        }

        @Override // b.j.a.c.AbstractC0050c
        public void l(View view, float f2, float f3) {
            int i2;
            boolean z;
            c cVar;
            this.f4520b = -1;
            int width = view.getWidth();
            if (n(view, f2)) {
                int left = view.getLeft();
                int i3 = this.f4519a;
                i2 = left < i3 ? i3 - width : i3 + width;
                z = true;
            } else {
                i2 = this.f4519a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f4510a.N(i2, view.getTop())) {
                t.c0(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f4511b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // b.j.a.c.AbstractC0050c
        public boolean m(View view, int i2) {
            int i3 = this.f4520b;
            return (i3 == -1 || i3 == i2) && SwipeDismissBehavior.this.E(view);
        }

        public final boolean n(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f4519a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f4516g);
            }
            boolean z = t.z(view) == 1;
            int i2 = SwipeDismissBehavior.this.f4515f;
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
    }

    public class b implements f {
        public b() {
        }

        @Override // b.h.m.c0.f
        public boolean a(View view, f.a aVar) {
            boolean z = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z2 = t.z(view) == 1;
            int i2 = SwipeDismissBehavior.this.f4515f;
            if ((i2 == 0 && z2) || (i2 == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            t.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f4511b;
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

    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f4523b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f4524c;

        public d(View view, boolean z) {
            this.f4523b = view;
            this.f4524c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            b.j.a.c cVar2 = SwipeDismissBehavior.this.f4510a;
            if (cVar2 != null && cVar2.n(true)) {
                t.c0(this.f4523b, this);
            } else {
                if (!this.f4524c || (cVar = SwipeDismissBehavior.this.f4511b) == null) {
                    return;
                }
                cVar.a(this.f4523b);
            }
        }
    }

    public static float F(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    public static int G(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    public static float I(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        b.j.a.c cVar = this.f4510a;
        if (cVar == null) {
            return false;
        }
        cVar.F(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public final void H(ViewGroup viewGroup) {
        if (this.f4510a == null) {
            this.f4510a = this.f4514e ? b.j.a.c.o(viewGroup, this.f4513d, this.j) : b.j.a.c.p(viewGroup, this.j);
        }
    }

    public void J(float f2) {
        this.f4518i = F(0.0f, f2, 1.0f);
    }

    public void K(float f2) {
        this.f4517h = F(0.0f, f2, 1.0f);
    }

    public void L(int i2) {
        this.f4515f = i2;
    }

    public final void M(View view) {
        t.e0(view, 1048576);
        if (E(view)) {
            t.g0(view, c.a.u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean zF = this.f4512c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f4512c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f4512c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f4510a.O(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        boolean zL = super.l(coordinatorLayout, v, i2);
        if (t.x(v) == 0) {
            t.u0(v, 1);
            M(v);
        }
        return zL;
    }
}
