package a.n;

import a.n.a;
import a.n.m;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends m {
    private static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    private int M = 3;

    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f550a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f551b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f552c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f550a = viewGroup;
            this.f551b = view;
            this.f552c = view2;
        }

        @Override // a.n.n, a.n.m.f
        public void a(m mVar) {
            x.a(this.f550a).c(this.f551b);
        }

        @Override // a.n.n, a.n.m.f
        public void b(m mVar) {
            if (this.f551b.getParent() == null) {
                x.a(this.f550a).a(this.f551b);
            } else {
                j0.this.f();
            }
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            this.f552c.setTag(j.f547b, null);
            x.a(this.f550a).c(this.f551b);
            mVar.P(this);
        }
    }

    private static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0023a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f554a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f555b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f556c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f557d;
        private boolean e;
        boolean f = false;

        b(View view, int i, boolean z) {
            this.f554a = view;
            this.f555b = i;
            this.f556c = (ViewGroup) view.getParent();
            this.f557d = z;
            g(true);
        }

        private void f() {
            if (!this.f) {
                c0.h(this.f554a, this.f555b);
                ViewGroup viewGroup = this.f556c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f557d || this.e == z || (viewGroup = this.f556c) == null) {
                return;
            }
            this.e = z;
            x.c(viewGroup, z);
        }

        @Override // a.n.m.f
        public void a(m mVar) {
            g(false);
        }

        @Override // a.n.m.f
        public void b(m mVar) {
            g(true);
        }

        @Override // a.n.m.f
        public void c(m mVar) {
        }

        @Override // a.n.m.f
        public void d(m mVar) {
        }

        @Override // a.n.m.f
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

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, a.n.a.InterfaceC0023a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f554a, this.f555b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, a.n.a.InterfaceC0023a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            c0.h(this.f554a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f558a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f559b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f560c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f561d;
        ViewGroup e;
        ViewGroup f;

        c() {
        }
    }

    private void c0(s sVar) {
        sVar.f590a.put("android:visibility:visibility", Integer.valueOf(sVar.f591b.getVisibility()));
        sVar.f590a.put("android:visibility:parent", sVar.f591b.getParent());
        int[] iArr = new int[2];
        sVar.f591b.getLocationOnScreen(iArr);
        sVar.f590a.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0075, code lost:
    
        if (r9 == 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007f, code lost:
    
        if (r0.e == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
    
        if (r0.f560c == 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private a.n.j0.c d0(a.n.s r8, a.n.s r9) {
        /*
            r7 = this;
            a.n.j0$c r0 = new a.n.j0$c
            r0.<init>()
            r1 = 0
            r0.f558a = r1
            r0.f559b = r1
            java.lang.String r2 = "android:visibility:parent"
            r3 = 0
            r4 = -1
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L33
            java.util.Map<java.lang.String, java.lang.Object> r6 = r8.f590a
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L33
            java.util.Map<java.lang.String, java.lang.Object> r6 = r8.f590a
            java.lang.Object r6 = r6.get(r5)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r0.f560c = r6
            java.util.Map<java.lang.String, java.lang.Object> r6 = r8.f590a
            java.lang.Object r6 = r6.get(r2)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.e = r6
            goto L37
        L33:
            r0.f560c = r4
            r0.e = r3
        L37:
            if (r9 == 0) goto L5a
            java.util.Map<java.lang.String, java.lang.Object> r6 = r9.f590a
            boolean r6 = r6.containsKey(r5)
            if (r6 == 0) goto L5a
            java.util.Map<java.lang.String, java.lang.Object> r3 = r9.f590a
            java.lang.Object r3 = r3.get(r5)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r0.f561d = r3
            java.util.Map<java.lang.String, java.lang.Object> r3 = r9.f590a
            java.lang.Object r2 = r3.get(r2)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f = r2
            goto L5e
        L5a:
            r0.f561d = r4
            r0.f = r3
        L5e:
            r2 = 1
            if (r8 == 0) goto L82
            if (r9 == 0) goto L82
            int r8 = r0.f560c
            int r9 = r0.f561d
            if (r8 != r9) goto L70
            android.view.ViewGroup r3 = r0.e
            android.view.ViewGroup r4 = r0.f
            if (r3 != r4) goto L70
            return r0
        L70:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L75
            goto L93
        L75:
            if (r9 != 0) goto L96
            goto L88
        L78:
            android.view.ViewGroup r8 = r0.f
            if (r8 != 0) goto L7d
            goto L93
        L7d:
            android.view.ViewGroup r8 = r0.e
            if (r8 != 0) goto L96
            goto L88
        L82:
            if (r8 != 0) goto L8d
            int r8 = r0.f561d
            if (r8 != 0) goto L8d
        L88:
            r0.f559b = r2
        L8a:
            r0.f558a = r2
            goto L96
        L8d:
            if (r9 != 0) goto L96
            int r8 = r0.f560c
            if (r8 != 0) goto L96
        L93:
            r0.f559b = r1
            goto L8a
        L96:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.n.j0.d0(a.n.s, a.n.s):a.n.j0$c");
    }

    @Override // a.n.m
    public String[] D() {
        return L;
    }

    @Override // a.n.m
    public boolean F(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f590a.containsKey("android:visibility:visibility") != sVar.f590a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(sVar, sVar2);
        if (cVarD0.f558a) {
            return cVarD0.f560c == 0 || cVarD0.f561d == 0;
        }
        return false;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator f0(ViewGroup viewGroup, s sVar, int i, s sVar2, int i2) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f591b.getParent();
            if (d0(t(view, false), E(view, false)).f558a) {
                return null;
            }
        }
        return e0(viewGroup, sVar2.f591b, sVar, sVar2);
    }

    @Override // a.n.m
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
    public android.animation.Animator h0(android.view.ViewGroup r18, a.n.s r19, int r20, a.n.s r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.n.j0.h0(android.view.ViewGroup, a.n.s, int, a.n.s, int):android.animation.Animator");
    }

    public void i0(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i;
    }

    @Override // a.n.m
    public void j(s sVar) {
        c0(sVar);
    }

    @Override // a.n.m
    public Animator n(ViewGroup viewGroup, s sVar, s sVar2) {
        c cVarD0 = d0(sVar, sVar2);
        if (!cVarD0.f558a) {
            return null;
        }
        if (cVarD0.e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.f559b ? f0(viewGroup, sVar, cVarD0.f560c, sVar2, cVarD0.f561d) : h0(viewGroup, sVar, cVarD0.f560c, sVar2, cVarD0.f561d);
    }
}
