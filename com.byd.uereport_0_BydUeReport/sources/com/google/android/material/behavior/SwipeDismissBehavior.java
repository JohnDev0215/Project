package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import o0.t;
import p0.c;
import p0.f;
import t0.c;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes2.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t0.c f3565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3566b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3567c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3569e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3568d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3570f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f3571g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3572h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f3573i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final c.AbstractC0123c f3574j = new a();

    public class a extends c.AbstractC0123c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3575a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3576b = -1;

        public a() {
        }

        @Override // t0.c.AbstractC0123c
        public int a(View view, int i8, int i9) {
            int width;
            int width2;
            int width3;
            boolean z7 = t.A(view) == 1;
            int i10 = SwipeDismissBehavior.this.f3570f;
            if (i10 == 0) {
                if (z7) {
                    width = this.f3575a - view.getWidth();
                    width2 = this.f3575a;
                } else {
                    width = this.f3575a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i10 != 1) {
                width = this.f3575a - view.getWidth();
                width2 = view.getWidth() + this.f3575a;
            } else if (z7) {
                width = this.f3575a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f3575a - view.getWidth();
                width2 = this.f3575a;
            }
            return SwipeDismissBehavior.G(width, i8, width2);
        }

        @Override // t0.c.AbstractC0123c
        public int b(View view, int i8, int i9) {
            return view.getTop();
        }

        @Override // t0.c.AbstractC0123c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // t0.c.AbstractC0123c
        public void i(View view, int i8) {
            this.f3576b = i8;
            this.f3575a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // t0.c.AbstractC0123c
        public void j(int i8) {
            c cVar = SwipeDismissBehavior.this.f3566b;
            if (cVar != null) {
                cVar.b(i8);
            }
        }

        @Override // t0.c.AbstractC0123c
        public void k(View view, int i8, int i9, int i10, int i11) {
            float width = this.f3575a + (view.getWidth() * SwipeDismissBehavior.this.f3572h);
            float width2 = this.f3575a + (view.getWidth() * SwipeDismissBehavior.this.f3573i);
            float f8 = i8;
            if (f8 <= width) {
                view.setAlpha(1.0f);
            } else if (f8 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f8), 1.0f));
            }
        }

        @Override // t0.c.AbstractC0123c
        public void l(View view, float f8, float f9) {
            int i8;
            boolean z7;
            c cVar;
            this.f3576b = -1;
            int width = view.getWidth();
            if (n(view, f8)) {
                int left = view.getLeft();
                int i9 = this.f3575a;
                i8 = left < i9 ? i9 - width : i9 + width;
                z7 = true;
            } else {
                i8 = this.f3575a;
                z7 = false;
            }
            if (SwipeDismissBehavior.this.f3565a.N(i8, view.getTop())) {
                t.d0(view, new d(view, z7));
            } else {
                if (!z7 || (cVar = SwipeDismissBehavior.this.f3566b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // t0.c.AbstractC0123c
        public boolean m(View view, int i8) {
            int i9 = this.f3576b;
            return (i9 == -1 || i9 == i8) && SwipeDismissBehavior.this.E(view);
        }

        public final boolean n(View view, float f8) {
            if (f8 == 0.0f) {
                return Math.abs(view.getLeft() - this.f3575a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f3571g);
            }
            boolean z7 = t.A(view) == 1;
            int i8 = SwipeDismissBehavior.this.f3570f;
            if (i8 == 2) {
                return true;
            }
            if (i8 == 0) {
                if (z7) {
                    if (f8 >= 0.0f) {
                        return false;
                    }
                } else if (f8 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i8 != 1) {
                return false;
            }
            if (z7) {
                if (f8 <= 0.0f) {
                    return false;
                }
            } else if (f8 >= 0.0f) {
                return false;
            }
            return true;
        }
    }

    public class b implements f {
        public b() {
        }

        @Override // p0.f
        public boolean perform(View view, f.a aVar) {
            boolean z7 = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z8 = t.A(view) == 1;
            int i8 = SwipeDismissBehavior.this.f3570f;
            if ((i8 == 0 && z8) || (i8 == 1 && !z8)) {
                z7 = true;
            }
            int width = view.getWidth();
            if (z7) {
                width = -width;
            }
            t.V(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f3566b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i8);
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f3579d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f3580e;

        public d(View view, boolean z7) {
            this.f3579d = view;
            this.f3580e = z7;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            t0.c cVar2 = SwipeDismissBehavior.this.f3565a;
            if (cVar2 != null && cVar2.n(true)) {
                t.d0(this.f3579d, this);
            } else {
                if (!this.f3580e || (cVar = SwipeDismissBehavior.this.f3566b) == null) {
                    return;
                }
                cVar.a(this.f3579d);
            }
        }
    }

    public static float F(float f8, float f9, float f10) {
        return Math.min(Math.max(f8, f9), f10);
    }

    public static int G(int i8, int i9, int i10) {
        return Math.min(Math.max(i8, i9), i10);
    }

    public static float I(float f8, float f9, float f10) {
        return (f10 - f8) / (f9 - f8);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
        t0.c cVar = this.f3565a;
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
        if (this.f3565a == null) {
            this.f3565a = this.f3569e ? t0.c.o(viewGroup, this.f3568d, this.f3574j) : t0.c.p(viewGroup, this.f3574j);
        }
    }

    public void J(float f8) {
        this.f3573i = F(0.0f, f8, 1.0f);
    }

    public void K(float f8) {
        this.f3572h = F(0.0f, f8, 1.0f);
    }

    public void L(int i8) {
        this.f3570f = i8;
    }

    public final void M(View view) {
        t.f0(view, CarPlayConfig.IapFeature.IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY);
        if (E(view)) {
            t.h0(view, c.a.f6988u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v7, MotionEvent motionEvent) {
        boolean zF = this.f3567c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v7, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f3567c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3567c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f3565a.O(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        boolean zL = super.l(coordinatorLayout, v7, i8);
        if (t.y(v7) == 0) {
            t.v0(v7, 1);
            M(v7);
        }
        return zL;
    }
}
