package b.n;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import b.n.a;
import b.n.m;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends m {
    private static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    private int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1975b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1976c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f1974a = viewGroup;
            this.f1975b = view;
            this.f1976c = view2;
        }

        @Override // b.n.n, b.n.m.f
        public void a(m mVar) {
            x.a(this.f1974a).d(this.f1975b);
        }

        @Override // b.n.n, b.n.m.f
        public void b(m mVar) {
            if (this.f1975b.getParent() == null) {
                x.a(this.f1974a).c(this.f1975b);
            } else {
                j0.this.f();
            }
        }

        @Override // b.n.m.f
        public void e(m mVar) {
            this.f1976c.setTag(j.f1971b, null);
            x.a(this.f1974a).d(this.f1975b);
            mVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0052a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f1978a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1979b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f1980c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f1981d;
        private boolean e;
        boolean f = false;

        b(View view, int i, boolean z) {
            this.f1978a = view;
            this.f1979b = i;
            this.f1980c = (ViewGroup) view.getParent();
            this.f1981d = z;
            g(true);
        }

        private void f() {
            if (!this.f) {
                c0.h(this.f1978a, this.f1979b);
                ViewGroup viewGroup = this.f1980c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f1981d || this.e == z || (viewGroup = this.f1980c) == null) {
                return;
            }
            this.e = z;
            x.c(viewGroup, z);
        }

        @Override // b.n.m.f
        public void a(m mVar) {
            g(false);
        }

        @Override // b.n.m.f
        public void b(m mVar) {
            g(true);
        }

        @Override // b.n.m.f
        public void c(m mVar) {
        }

        @Override // b.n.m.f
        public void d(m mVar) {
        }

        @Override // b.n.m.f
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

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.n.a.InterfaceC0052a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f1978a, this.f1979b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.n.a.InterfaceC0052a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f1978a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1982a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1983b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1984c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1985d;
        ViewGroup e;
        ViewGroup f;

        c() {
        }
    }

    private void c0(s sVar) {
        sVar.f2014a.put("android:visibility:visibility", Integer.valueOf(sVar.f2015b.getVisibility()));
        sVar.f2014a.put("android:visibility:parent", sVar.f2015b.getParent());
        int[] iArr = new int[2];
        sVar.f2015b.getLocationOnScreen(iArr);
        sVar.f2014a.put("android:visibility:screenLocation", iArr);
    }

    private c d0(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f1982a = false;
        cVar.f1983b = false;
        if (sVar == null || !sVar.f2014a.containsKey("android:visibility:visibility")) {
            cVar.f1984c = -1;
            cVar.e = null;
        } else {
            cVar.f1984c = ((Integer) sVar.f2014a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) sVar.f2014a.get("android:visibility:parent");
        }
        if (sVar2 == null || !sVar2.f2014a.containsKey("android:visibility:visibility")) {
            cVar.f1985d = -1;
            cVar.f = null;
        } else {
            cVar.f1985d = ((Integer) sVar2.f2014a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) sVar2.f2014a.get("android:visibility:parent");
        }
        if (sVar != null && sVar2 != null) {
            int i = cVar.f1984c;
            int i2 = cVar.f1985d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.f1983b = false;
                    cVar.f1982a = true;
                } else if (i2 == 0) {
                    cVar.f1983b = true;
                    cVar.f1982a = true;
                }
            } else if (cVar.f == null) {
                cVar.f1983b = false;
                cVar.f1982a = true;
            } else if (cVar.e == null) {
                cVar.f1983b = true;
                cVar.f1982a = true;
            }
        } else if (sVar == null && cVar.f1985d == 0) {
            cVar.f1983b = true;
            cVar.f1982a = true;
        } else if (sVar2 == null && cVar.f1984c == 0) {
            cVar.f1983b = false;
            cVar.f1982a = true;
        }
        return cVar;
    }

    @Override // b.n.m
    public String[] D() {
        return L;
    }

    @Override // b.n.m
    public boolean F(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f2014a.containsKey("android:visibility:visibility") != sVar.f2014a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(sVar, sVar2);
        if (cVarD0.f1982a) {
            return cVarD0.f1984c == 0 || cVarD0.f1985d == 0;
        }
        return false;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator f0(ViewGroup viewGroup, s sVar, int i, s sVar2, int i2) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f2015b.getParent();
            if (d0(t(view, false), E(view, false)).f1982a) {
                return null;
            }
        }
        return e0(viewGroup, sVar2.f2015b, sVar, sVar2);
    }

    @Override // b.n.m
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
    public android.animation.Animator h0(android.view.ViewGroup r18, b.n.s r19, int r20, b.n.s r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.n.j0.h0(android.view.ViewGroup, b.n.s, int, b.n.s, int):android.animation.Animator");
    }

    public void i0(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i;
    }

    @Override // b.n.m
    public void j(s sVar) {
        c0(sVar);
    }

    @Override // b.n.m
    public Animator n(ViewGroup viewGroup, s sVar, s sVar2) {
        c cVarD0 = d0(sVar, sVar2);
        if (!cVarD0.f1982a) {
            return null;
        }
        if (cVarD0.e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.f1983b ? f0(viewGroup, sVar, cVarD0.f1984c, sVar2, cVarD0.f1985d) : h0(viewGroup, sVar, cVarD0.f1984c, sVar2, cVarD0.f1985d);
    }
}
