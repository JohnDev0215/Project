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
        final /* synthetic */ ViewGroup f2478a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2479b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f2480c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f2478a = viewGroup;
            this.f2479b = view;
            this.f2480c = view2;
        }

        @Override // b.m.n, b.m.m.f
        public void a(m mVar) {
            x.a(this.f2478a).d(this.f2479b);
        }

        @Override // b.m.n, b.m.m.f
        public void b(m mVar) {
            if (this.f2479b.getParent() == null) {
                x.a(this.f2478a).c(this.f2479b);
            } else {
                j0.this.f();
            }
        }

        @Override // b.m.m.f
        public void e(m mVar) {
            this.f2480c.setTag(j.f2475b, null);
            x.a(this.f2478a).d(this.f2479b);
            mVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0061a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f2482a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2483b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f2484c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f2485d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2486e;
        boolean f = false;

        b(View view, int i, boolean z) {
            this.f2482a = view;
            this.f2483b = i;
            this.f2484c = (ViewGroup) view.getParent();
            this.f2485d = z;
            g(true);
        }

        private void f() {
            if (!this.f) {
                c0.h(this.f2482a, this.f2483b);
                ViewGroup viewGroup = this.f2484c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f2485d || this.f2486e == z || (viewGroup = this.f2484c) == null) {
                return;
            }
            this.f2486e = z;
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

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.m.a.InterfaceC0061a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f2482a, this.f2483b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.m.a.InterfaceC0061a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f2482a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2487a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2488b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2489c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2490d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ViewGroup f2491e;
        ViewGroup f;

        c() {
        }
    }

    private void c0(s sVar) {
        sVar.f2522a.put("android:visibility:visibility", Integer.valueOf(sVar.f2523b.getVisibility()));
        sVar.f2522a.put("android:visibility:parent", sVar.f2523b.getParent());
        int[] iArr = new int[2];
        sVar.f2523b.getLocationOnScreen(iArr);
        sVar.f2522a.put("android:visibility:screenLocation", iArr);
    }

    private c d0(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f2487a = false;
        cVar.f2488b = false;
        if (sVar == null || !sVar.f2522a.containsKey("android:visibility:visibility")) {
            cVar.f2489c = -1;
            cVar.f2491e = null;
        } else {
            cVar.f2489c = ((Integer) sVar.f2522a.get("android:visibility:visibility")).intValue();
            cVar.f2491e = (ViewGroup) sVar.f2522a.get("android:visibility:parent");
        }
        if (sVar2 == null || !sVar2.f2522a.containsKey("android:visibility:visibility")) {
            cVar.f2490d = -1;
            cVar.f = null;
        } else {
            cVar.f2490d = ((Integer) sVar2.f2522a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) sVar2.f2522a.get("android:visibility:parent");
        }
        if (sVar != null && sVar2 != null) {
            int i = cVar.f2489c;
            int i2 = cVar.f2490d;
            if (i == i2 && cVar.f2491e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.f2488b = false;
                    cVar.f2487a = true;
                } else if (i2 == 0) {
                    cVar.f2488b = true;
                    cVar.f2487a = true;
                }
            } else if (cVar.f == null) {
                cVar.f2488b = false;
                cVar.f2487a = true;
            } else if (cVar.f2491e == null) {
                cVar.f2488b = true;
                cVar.f2487a = true;
            }
        } else if (sVar == null && cVar.f2490d == 0) {
            cVar.f2488b = true;
            cVar.f2487a = true;
        } else if (sVar2 == null && cVar.f2489c == 0) {
            cVar.f2488b = false;
            cVar.f2487a = true;
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
        if (sVar != null && sVar2 != null && sVar2.f2522a.containsKey("android:visibility:visibility") != sVar.f2522a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(sVar, sVar2);
        if (cVarD0.f2487a) {
            return cVarD0.f2489c == 0 || cVarD0.f2490d == 0;
        }
        return false;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator f0(ViewGroup viewGroup, s sVar, int i, s sVar2, int i2) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f2523b.getParent();
            if (d0(t(view, false), E(view, false)).f2487a) {
                return null;
            }
        }
        return e0(viewGroup, sVar2.f2523b, sVar, sVar2);
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
        if (!cVarD0.f2487a) {
            return null;
        }
        if (cVarD0.f2491e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.f2488b ? f0(viewGroup, sVar, cVarD0.f2489c, sVar2, cVarD0.f2490d) : h0(viewGroup, sVar, cVarD0.f2489c, sVar2, cVarD0.f2490d);
    }
}
