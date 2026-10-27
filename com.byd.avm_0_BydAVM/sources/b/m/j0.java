package b.m;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import b.m.a;
import b.m.m;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends m {
    private static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    private int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1870a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1871b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1872c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f1870a = viewGroup;
            this.f1871b = view;
            this.f1872c = view2;
        }

        @Override // b.m.n, b.m.m.f
        public void a(m mVar) {
            x.a(this.f1870a).d(this.f1871b);
        }

        @Override // b.m.n, b.m.m.f
        public void b(m mVar) {
            if (this.f1871b.getParent() == null) {
                x.a(this.f1870a).c(this.f1871b);
            } else {
                j0.this.f();
            }
        }

        @Override // b.m.m.f
        public void e(m mVar) {
            this.f1872c.setTag(j.f1867b, null);
            x.a(this.f1870a).d(this.f1871b);
            mVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0047a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f1874a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1875b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f1876c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f1877d;
        private boolean e;
        boolean f = false;

        b(View view, int i, boolean z) {
            this.f1874a = view;
            this.f1875b = i;
            this.f1876c = (ViewGroup) view.getParent();
            this.f1877d = z;
            g(true);
        }

        private void f() {
            if (!this.f) {
                c0.h(this.f1874a, this.f1875b);
                ViewGroup viewGroup = this.f1876c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f1877d || this.e == z || (viewGroup = this.f1876c) == null) {
                return;
            }
            this.e = z;
            x.c(viewGroup, z);
        }

        @Override // b.m.m.f
        public void a(m mVar) {
            g(false);
        }

        @Override // b.m.m.f
        public void b(m mVar) {
            g(true);
        }

        @Override // b.m.m.f
        public void c(m mVar) {
        }

        @Override // b.m.m.f
        public void d(m mVar) {
        }

        @Override // b.m.m.f
        public void e(m mVar) {
            f();
            mVar.P(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.m.a.InterfaceC0047a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f1874a, this.f1875b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.m.a.InterfaceC0047a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f1874a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1878a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1879b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1880c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1881d;
        ViewGroup e;
        ViewGroup f;

        c() {
        }
    }

    private void c0(s sVar) {
        sVar.f1910a.put("android:visibility:visibility", Integer.valueOf(sVar.f1911b.getVisibility()));
        sVar.f1910a.put("android:visibility:parent", sVar.f1911b.getParent());
        int[] iArr = new int[2];
        sVar.f1911b.getLocationOnScreen(iArr);
        sVar.f1910a.put("android:visibility:screenLocation", iArr);
    }

    private c d0(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f1878a = false;
        cVar.f1879b = false;
        if (sVar == null || !sVar.f1910a.containsKey("android:visibility:visibility")) {
            cVar.f1880c = -1;
            cVar.e = null;
        } else {
            cVar.f1880c = ((Integer) sVar.f1910a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) sVar.f1910a.get("android:visibility:parent");
        }
        if (sVar2 == null || !sVar2.f1910a.containsKey("android:visibility:visibility")) {
            cVar.f1881d = -1;
            cVar.f = null;
        } else {
            cVar.f1881d = ((Integer) sVar2.f1910a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) sVar2.f1910a.get("android:visibility:parent");
        }
        if (sVar != null && sVar2 != null) {
            int i = cVar.f1880c;
            int i2 = cVar.f1881d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.f1879b = false;
                    cVar.f1878a = true;
                } else if (i2 == 0) {
                    cVar.f1879b = true;
                    cVar.f1878a = true;
                }
            } else if (cVar.f == null) {
                cVar.f1879b = false;
                cVar.f1878a = true;
            } else if (cVar.e == null) {
                cVar.f1879b = true;
                cVar.f1878a = true;
            }
        } else if (sVar == null && cVar.f1881d == 0) {
            cVar.f1879b = true;
            cVar.f1878a = true;
        } else if (sVar2 == null && cVar.f1880c == 0) {
            cVar.f1879b = false;
            cVar.f1878a = true;
        }
        return cVar;
    }

    @Override // b.m.m
    public String[] D() {
        return L;
    }

    @Override // b.m.m
    public boolean F(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f1910a.containsKey("android:visibility:visibility") != sVar.f1910a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(sVar, sVar2);
        if (cVarD0.f1878a) {
            return cVarD0.f1880c == 0 || cVarD0.f1881d == 0;
        }
        return false;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator f0(ViewGroup viewGroup, s sVar, int i, s sVar2, int i2) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f1911b.getParent();
            if (d0(t(view, false), E(view, false)).f1878a) {
                return null;
            }
        }
        return e0(viewGroup, sVar2.f1911b, sVar, sVar2);
    }

    @Override // b.m.m
    public void g(s sVar) {
        c0(sVar);
    }

    public abstract Animator g0(ViewGroup viewGroup, View view, s sVar, s sVar2);

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
    public android.animation.Animator h0(android.view.ViewGroup r18, b.m.s r19, int r20, b.m.s r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.m.j0.h0(android.view.ViewGroup, b.m.s, int, b.m.s, int):android.animation.Animator");
    }

    public void i0(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i;
    }

    @Override // b.m.m
    public void j(s sVar) {
        c0(sVar);
    }

    @Override // b.m.m
    public Animator n(ViewGroup viewGroup, s sVar, s sVar2) {
        c cVarD0 = d0(sVar, sVar2);
        if (!cVarD0.f1878a) {
            return null;
        }
        if (cVarD0.e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.f1879b ? f0(viewGroup, sVar, cVarD0.f1880c, sVar2, cVarD0.f1881d) : h0(viewGroup, sVar, cVarD0.f1880c, sVar2, cVarD0.f1881d);
    }
}
