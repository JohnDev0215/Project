package b.o;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import b.o.a;
import b.o.m;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends m {
    private static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    private int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2869a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2870b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f2871c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f2869a = viewGroup;
            this.f2870b = view;
            this.f2871c = view2;
        }

        @Override // b.o.n, b.o.m.f
        public void a(m mVar) {
            x.a(this.f2869a).d(this.f2870b);
        }

        @Override // b.o.n, b.o.m.f
        public void b(m mVar) {
            if (this.f2870b.getParent() == null) {
                x.a(this.f2869a).c(this.f2870b);
            } else {
                j0.this.cancel();
            }
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            this.f2871c.setTag(j.save_overlay_view, null);
            x.a(this.f2869a).d(this.f2870b);
            mVar.T(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0054a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f2873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f2875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f2876d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2877e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2878f = false;

        b(View view, int i2, boolean z) {
            this.f2873a = view;
            this.f2874b = i2;
            this.f2875c = (ViewGroup) view.getParent();
            this.f2876d = z;
            g(true);
        }

        private void f() {
            if (!this.f2878f) {
                c0.h(this.f2873a, this.f2874b);
                ViewGroup viewGroup = this.f2875c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f2876d || this.f2877e == z || (viewGroup = this.f2875c) == null) {
                return;
            }
            this.f2877e = z;
            x.c(viewGroup, z);
        }

        @Override // b.o.m.f
        public void a(m mVar) {
            g(false);
        }

        @Override // b.o.m.f
        public void b(m mVar) {
            g(true);
        }

        @Override // b.o.m.f
        public void c(m mVar) {
        }

        @Override // b.o.m.f
        public void d(m mVar) {
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            f();
            mVar.T(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2878f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.o.a.InterfaceC0054a
        public void onAnimationPause(Animator animator) {
            if (this.f2878f) {
                return;
            }
            c0.h(this.f2873a, this.f2874b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.o.a.InterfaceC0054a
        public void onAnimationResume(Animator animator) {
            if (this.f2878f) {
                return;
            }
            c0.h(this.f2873a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2879a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2880b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2881c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2882d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ViewGroup f2883e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        ViewGroup f2884f;

        c() {
        }
    }

    private void g0(s sVar) {
        sVar.f2919a.put("android:visibility:visibility", Integer.valueOf(sVar.f2920b.getVisibility()));
        sVar.f2919a.put("android:visibility:parent", sVar.f2920b.getParent());
        int[] iArr = new int[2];
        sVar.f2920b.getLocationOnScreen(iArr);
        sVar.f2919a.put("android:visibility:screenLocation", iArr);
    }

    private c h0(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f2879a = false;
        cVar.f2880b = false;
        if (sVar == null || !sVar.f2919a.containsKey("android:visibility:visibility")) {
            cVar.f2881c = -1;
            cVar.f2883e = null;
        } else {
            cVar.f2881c = ((Integer) sVar.f2919a.get("android:visibility:visibility")).intValue();
            cVar.f2883e = (ViewGroup) sVar.f2919a.get("android:visibility:parent");
        }
        if (sVar2 == null || !sVar2.f2919a.containsKey("android:visibility:visibility")) {
            cVar.f2882d = -1;
            cVar.f2884f = null;
        } else {
            cVar.f2882d = ((Integer) sVar2.f2919a.get("android:visibility:visibility")).intValue();
            cVar.f2884f = (ViewGroup) sVar2.f2919a.get("android:visibility:parent");
        }
        if (sVar != null && sVar2 != null) {
            int i2 = cVar.f2881c;
            int i3 = cVar.f2882d;
            if (i2 == i3 && cVar.f2883e == cVar.f2884f) {
                return cVar;
            }
            if (i2 != i3) {
                if (i2 == 0) {
                    cVar.f2880b = false;
                    cVar.f2879a = true;
                } else if (i3 == 0) {
                    cVar.f2880b = true;
                    cVar.f2879a = true;
                }
            } else if (cVar.f2884f == null) {
                cVar.f2880b = false;
                cVar.f2879a = true;
            } else if (cVar.f2883e == null) {
                cVar.f2880b = true;
                cVar.f2879a = true;
            }
        } else if (sVar == null && cVar.f2882d == 0) {
            cVar.f2880b = true;
            cVar.f2879a = true;
        } else if (sVar2 == null && cVar.f2881c == 0) {
            cVar.f2880b = false;
            cVar.f2879a = true;
        }
        return cVar;
    }

    @Override // b.o.m
    public String[] H() {
        return L;
    }

    @Override // b.o.m
    public boolean J(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f2919a.containsKey("android:visibility:visibility") != sVar.f2919a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarH0 = h0(sVar, sVar2);
        if (cVarH0.f2879a) {
            return cVarH0.f2881c == 0 || cVarH0.f2882d == 0;
        }
        return false;
    }

    @Override // b.o.m
    public void h(s sVar) {
        g0(sVar);
    }

    public abstract Animator i0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator j0(ViewGroup viewGroup, s sVar, int i2, s sVar2, int i3) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f2920b.getParent();
            if (h0(v(view, false), I(view, false)).f2879a) {
                return null;
            }
        }
        return i0(viewGroup, sVar2.f2920b, sVar, sVar2);
    }

    public abstract Animator k0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    @Override // b.o.m
    public void l(s sVar) {
        g0(sVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008f A[PHI: r8
  0x008f: PHI (r8v3 android.view.View) = 
  (r8v2 android.view.View)
  (r8v2 android.view.View)
  (r8v2 android.view.View)
  (r8v2 android.view.View)
  (r8v2 android.view.View)
  (r8v2 android.view.View)
  (r8v6 android.view.View)
 binds: [B:26:0x0048, B:31:0x0057, B:36:0x007c, B:38:0x007f, B:40:0x0085, B:42:0x0089, B:34:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.Animator l0(android.view.ViewGroup r18, b.o.s r19, int r20, b.o.s r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.o.j0.l0(android.view.ViewGroup, b.o.s, int, b.o.s, int):android.animation.Animator");
    }

    public void m0(int i2) {
        if ((i2 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i2;
    }

    @Override // b.o.m
    public Animator p(ViewGroup viewGroup, s sVar, s sVar2) {
        c cVarH0 = h0(sVar, sVar2);
        if (!cVarH0.f2879a) {
            return null;
        }
        if (cVarH0.f2883e == null && cVarH0.f2884f == null) {
            return null;
        }
        return cVarH0.f2880b ? j0(viewGroup, sVar, cVarH0.f2881c, sVar2, cVarH0.f2882d) : l0(viewGroup, sVar, cVarH0.f2881c, sVar2, cVarH0.f2882d);
    }
}
