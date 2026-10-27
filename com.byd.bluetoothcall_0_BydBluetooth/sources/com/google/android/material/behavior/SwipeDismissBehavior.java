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
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b.i.a.c f3624a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c f3625b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3626c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3628e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3627d = 0.0f;
    int f = 2;
    float g = 0.5f;
    float h = 0.0f;
    float i = 0.5f;
    private final c.AbstractC0059c j = new a();

    class a extends c.AbstractC0059c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3629a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3630b = -1;

        a() {
        }

        private boolean n(View view, float f) {
            if (f == 0.0f) {
                return Math.abs(view.getLeft() - this.f3629a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            }
            boolean z = t.z(view) == 1;
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

        @Override // b.i.a.c.AbstractC0059c
        public int a(View view, int i, int i2) {
            int width;
            int width2;
            int width3;
            boolean z = t.z(view) == 1;
            int i3 = SwipeDismissBehavior.this.f;
            if (i3 == 0) {
                if (z) {
                    width = this.f3629a - view.getWidth();
                    width2 = this.f3629a;
                } else {
                    width = this.f3629a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.f3629a - view.getWidth();
                width2 = view.getWidth() + this.f3629a;
            } else if (z) {
                width = this.f3629a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f3629a - view.getWidth();
                width2 = this.f3629a;
            }
            return SwipeDismissBehavior.G(width, i, width2);
        }

        @Override // b.i.a.c.AbstractC0059c
        public int b(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // b.i.a.c.AbstractC0059c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // b.i.a.c.AbstractC0059c
        public void i(View view, int i) {
            this.f3630b = i;
            this.f3629a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // b.i.a.c.AbstractC0059c
        public void j(int i) {
            c cVar = SwipeDismissBehavior.this.f3625b;
            if (cVar != null) {
                cVar.b(i);
            }
        }

        @Override // b.i.a.c.AbstractC0059c
        public void k(View view, int i, int i2, int i3, int i4) {
            float width = this.f3629a + (view.getWidth() * SwipeDismissBehavior.this.h);
            float width2 = this.f3629a + (view.getWidth() * SwipeDismissBehavior.this.i);
            float f = i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f), 1.0f));
            }
        }

        @Override // b.i.a.c.AbstractC0059c
        public void l(View view, float f, float f2) {
            int i;
            boolean z;
            c cVar;
            this.f3630b = -1;
            int width = view.getWidth();
            if (n(view, f)) {
                int left = view.getLeft();
                int i2 = this.f3629a;
                i = left < i2 ? i2 - width : i2 + width;
                z = true;
            } else {
                i = this.f3629a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f3624a.N(i, view.getTop())) {
                t.c0(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f3625b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // b.i.a.c.AbstractC0059c
        public boolean m(View view, int i) {
            int i2 = this.f3630b;
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
            int i = SwipeDismissBehavior.this.f;
            if ((i == 0 && z2) || (i == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            t.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f3625b;
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
        private final View f3633b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f3634c;

        d(View view, boolean z) {
            this.f3633b = view;
            this.f3634c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            b.i.a.c cVar2 = SwipeDismissBehavior.this.f3624a;
            if (cVar2 != null && cVar2.n(true)) {
                t.c0(this.f3633b, this);
            } else {
                if (!this.f3634c || (cVar = SwipeDismissBehavior.this.f3625b) == null) {
                    return;
                }
                cVar.a(this.f3633b);
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
        if (this.f3624a == null) {
            this.f3624a = this.f3628e ? b.i.a.c.o(viewGroup, this.f3627d, this.j) : b.i.a.c.p(viewGroup, this.j);
        }
    }

    static float I(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    private void M(View view) {
        t.e0(view, CarPlayConfig.IapFeature.IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY);
        if (E(view)) {
            t.g0(view, c.a.u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        b.i.a.c cVar = this.f3624a;
        if (cVar == null) {
            return false;
        }
        cVar.F(motionEvent);
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
        boolean zF = this.f3626c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f3626c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3626c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f3624a.O(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        boolean zL = super.l(coordinatorLayout, v, i);
        if (t.x(v) == 0) {
            t.u0(v, 1);
            M(v);
        }
        return zL;
    }
}
