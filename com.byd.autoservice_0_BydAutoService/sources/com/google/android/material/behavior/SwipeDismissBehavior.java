package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import l0.t;
import m0.c;
import m0.f;
import p0.c;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p0.c f1982a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f1983b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1984c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1986e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1985d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1987f = 2;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1988g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1989h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1990i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final c.AbstractC0049c f1991j = new a();

    public class a extends c.AbstractC0049c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1992a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1993b = -1;

        public a() {
        }

        @Override // p0.c.AbstractC0049c
        public int a(View view, int i3, int i4) {
            int width;
            int width2;
            int width3;
            boolean z3 = t.z(view) == 1;
            int i5 = SwipeDismissBehavior.this.f1987f;
            if (i5 == 0) {
                if (z3) {
                    width = this.f1992a - view.getWidth();
                    width2 = this.f1992a;
                } else {
                    width = this.f1992a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i5 != 1) {
                width = this.f1992a - view.getWidth();
                width2 = view.getWidth() + this.f1992a;
            } else if (z3) {
                width = this.f1992a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f1992a - view.getWidth();
                width2 = this.f1992a;
            }
            return SwipeDismissBehavior.G(width, i3, width2);
        }

        @Override // p0.c.AbstractC0049c
        public int b(View view, int i3, int i4) {
            return view.getTop();
        }

        @Override // p0.c.AbstractC0049c
        public int d(View view) {
            return view.getWidth();
        }

        @Override // p0.c.AbstractC0049c
        public void i(View view, int i3) {
            this.f1993b = i3;
            this.f1992a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // p0.c.AbstractC0049c
        public void j(int i3) {
            c cVar = SwipeDismissBehavior.this.f1983b;
            if (cVar != null) {
                cVar.b(i3);
            }
        }

        @Override // p0.c.AbstractC0049c
        public void k(View view, int i3, int i4, int i5, int i6) {
            float width = this.f1992a + (view.getWidth() * SwipeDismissBehavior.this.f1989h);
            float width2 = this.f1992a + (view.getWidth() * SwipeDismissBehavior.this.f1990i);
            float f3 = i3;
            if (f3 <= width) {
                view.setAlpha(1.0f);
            } else if (f3 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.F(0.0f, 1.0f - SwipeDismissBehavior.I(width, width2, f3), 1.0f));
            }
        }

        @Override // p0.c.AbstractC0049c
        public void l(View view, float f3, float f4) {
            int i3;
            boolean z3;
            c cVar;
            this.f1993b = -1;
            int width = view.getWidth();
            if (n(view, f3)) {
                int left = view.getLeft();
                int i4 = this.f1992a;
                i3 = left < i4 ? i4 - width : i4 + width;
                z3 = true;
            } else {
                i3 = this.f1992a;
                z3 = false;
            }
            if (SwipeDismissBehavior.this.f1982a.M(i3, view.getTop())) {
                t.b0(view, new d(view, z3));
            } else {
                if (!z3 || (cVar = SwipeDismissBehavior.this.f1983b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // p0.c.AbstractC0049c
        public boolean m(View view, int i3) {
            int i4 = this.f1993b;
            return (i4 == -1 || i4 == i3) && SwipeDismissBehavior.this.E(view);
        }

        public final boolean n(View view, float f3) {
            if (f3 == 0.0f) {
                return Math.abs(view.getLeft() - this.f1992a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.f1988g);
            }
            boolean z3 = t.z(view) == 1;
            int i3 = SwipeDismissBehavior.this.f1987f;
            if (i3 == 2) {
                return true;
            }
            if (i3 == 0) {
                if (z3) {
                    if (f3 >= 0.0f) {
                        return false;
                    }
                } else if (f3 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i3 != 1) {
                return false;
            }
            if (z3) {
                if (f3 <= 0.0f) {
                    return false;
                }
            } else if (f3 >= 0.0f) {
                return false;
            }
            return true;
        }
    }

    public class b implements f {
        public b() {
        }

        @Override // m0.f
        public boolean a(View view, f.a aVar) {
            boolean z3 = false;
            if (!SwipeDismissBehavior.this.E(view)) {
                return false;
            }
            boolean z4 = t.z(view) == 1;
            int i3 = SwipeDismissBehavior.this.f1987f;
            if ((i3 == 0 && z4) || (i3 == 1 && !z4)) {
                z3 = true;
            }
            int width = view.getWidth();
            if (z3) {
                width = -width;
            }
            t.U(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f1983b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    public interface c {
        void a(View view);

        void b(int i3);
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final View f1996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f1997c;

        public d(View view, boolean z3) {
            this.f1996b = view;
            this.f1997c = z3;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            p0.c cVar2 = SwipeDismissBehavior.this.f1982a;
            if (cVar2 != null && cVar2.m(true)) {
                t.b0(this.f1996b, this);
            } else {
                if (!this.f1997c || (cVar = SwipeDismissBehavior.this.f1983b) == null) {
                    return;
                }
                cVar.a(this.f1996b);
            }
        }
    }

    public static float F(float f3, float f4, float f5) {
        return Math.min(Math.max(f3, f4), f5);
    }

    public static int G(int i3, int i4, int i5) {
        return Math.min(Math.max(i3, i4), i5);
    }

    public static float I(float f3, float f4, float f5) {
        return (f5 - f3) / (f4 - f3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean D(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
        p0.c cVar = this.f1982a;
        if (cVar == null) {
            return false;
        }
        cVar.E(motionEvent);
        return true;
    }

    public boolean E(View view) {
        return true;
    }

    public final void H(ViewGroup viewGroup) {
        if (this.f1982a == null) {
            this.f1982a = this.f1986e ? p0.c.n(viewGroup, this.f1985d, this.f1991j) : p0.c.o(viewGroup, this.f1991j);
        }
    }

    public void J(float f3) {
        this.f1990i = F(0.0f, f3, 1.0f);
    }

    public void K(float f3) {
        this.f1989h = F(0.0f, f3, 1.0f);
    }

    public void L(int i3) {
        this.f1987f = i3;
    }

    public final void M(View view) {
        t.d0(view, 1048576);
        if (E(view)) {
            t.f0(view, c.a.f3822u, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v3, MotionEvent motionEvent) {
        boolean zF = this.f1984c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zF = coordinatorLayout.F(v3, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f1984c = zF;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f1984c = false;
        }
        if (!zF) {
            return false;
        }
        H(coordinatorLayout);
        return this.f1982a.N(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        boolean zL = super.l(coordinatorLayout, v3, i3);
        if (t.x(v3) == 0) {
            t.t0(v3, 1);
            M(v3);
        }
        return zL;
    }
}
