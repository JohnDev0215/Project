package e1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import e1.l;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f0 extends l {
    private static final String[] P = {"android:visibility:visibility", "android:visibility:parent"};
    private int O = 3;

    /* JADX INFO: compiled from: Visibility.java */
    class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f6098a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f6099b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f6100c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f6098a = viewGroup;
            this.f6099b = view;
            this.f6100c = view2;
        }

        @Override // e1.m, e1.l.f
        public void b(l lVar) {
            v.a(this.f6098a).c(this.f6099b);
        }

        @Override // e1.m, e1.l.f
        public void c(l lVar) {
            if (this.f6099b.getParent() == null) {
                v.a(this.f6098a).a(this.f6099b);
            } else {
                f0.this.cancel();
            }
        }

        @Override // e1.l.f
        public void e(l lVar) {
            this.f6100c.setTag(i.save_overlay_view, null);
            v.a(this.f6098a).c(this.f6099b);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class b extends AnimatorListenerAdapter implements l.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f6102a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f6103b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ViewGroup f6104c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f6105d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f6106e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f6107f = false;

        b(View view, int i7, boolean z6) {
            this.f6102a = view;
            this.f6103b = i7;
            this.f6104c = (ViewGroup) view.getParent();
            this.f6105d = z6;
            g(true);
        }

        private void f() {
            if (!this.f6107f) {
                y.h(this.f6102a, this.f6103b);
                ViewGroup viewGroup = this.f6104c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        private void g(boolean z6) {
            ViewGroup viewGroup;
            if (!this.f6105d || this.f6106e == z6 || (viewGroup = this.f6104c) == null) {
                return;
            }
            this.f6106e = z6;
            v.c(viewGroup, z6);
        }

        @Override // e1.l.f
        public void a(l lVar) {
        }

        @Override // e1.l.f
        public void b(l lVar) {
            g(false);
        }

        @Override // e1.l.f
        public void c(l lVar) {
            g(true);
        }

        @Override // e1.l.f
        public void d(l lVar) {
        }

        @Override // e1.l.f
        public void e(l lVar) {
            f();
            lVar.P(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f6107f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            if (this.f6107f) {
                return;
            }
            y.h(this.f6102a, this.f6103b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (this.f6107f) {
                return;
            }
            y.h(this.f6102a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f6108a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f6109b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f6110c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f6111d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ViewGroup f6112e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        ViewGroup f6113f;

        c() {
        }
    }

    private void c0(r rVar) {
        rVar.f6160a.put("android:visibility:visibility", Integer.valueOf(rVar.f6161b.getVisibility()));
        rVar.f6160a.put("android:visibility:parent", rVar.f6161b.getParent());
        int[] iArr = new int[2];
        rVar.f6161b.getLocationOnScreen(iArr);
        rVar.f6160a.put("android:visibility:screenLocation", iArr);
    }

    private c d0(r rVar, r rVar2) {
        c cVar = new c();
        cVar.f6108a = false;
        cVar.f6109b = false;
        if (rVar == null || !rVar.f6160a.containsKey("android:visibility:visibility")) {
            cVar.f6110c = -1;
            cVar.f6112e = null;
        } else {
            cVar.f6110c = ((Integer) rVar.f6160a.get("android:visibility:visibility")).intValue();
            cVar.f6112e = (ViewGroup) rVar.f6160a.get("android:visibility:parent");
        }
        if (rVar2 == null || !rVar2.f6160a.containsKey("android:visibility:visibility")) {
            cVar.f6111d = -1;
            cVar.f6113f = null;
        } else {
            cVar.f6111d = ((Integer) rVar2.f6160a.get("android:visibility:visibility")).intValue();
            cVar.f6113f = (ViewGroup) rVar2.f6160a.get("android:visibility:parent");
        }
        if (rVar != null && rVar2 != null) {
            int i7 = cVar.f6110c;
            int i8 = cVar.f6111d;
            if (i7 == i8 && cVar.f6112e == cVar.f6113f) {
                return cVar;
            }
            if (i7 != i8) {
                if (i7 == 0) {
                    cVar.f6109b = false;
                    cVar.f6108a = true;
                } else if (i8 == 0) {
                    cVar.f6109b = true;
                    cVar.f6108a = true;
                }
            } else if (cVar.f6113f == null) {
                cVar.f6109b = false;
                cVar.f6108a = true;
            } else if (cVar.f6112e == null) {
                cVar.f6109b = true;
                cVar.f6108a = true;
            }
        } else if (rVar == null && cVar.f6111d == 0) {
            cVar.f6109b = true;
            cVar.f6108a = true;
        } else if (rVar2 == null && cVar.f6110c == 0) {
            cVar.f6109b = false;
            cVar.f6108a = true;
        }
        return cVar;
    }

    @Override // e1.l
    public String[] D() {
        return P;
    }

    @Override // e1.l
    public boolean F(r rVar, r rVar2) {
        if (rVar == null && rVar2 == null) {
            return false;
        }
        if (rVar != null && rVar2 != null && rVar2.f6160a.containsKey("android:visibility:visibility") != rVar.f6160a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(rVar, rVar2);
        if (cVarD0.f6108a) {
            return cVarD0.f6110c == 0 || cVarD0.f6111d == 0;
        }
        return false;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2);

    @Override // e1.l
    public void f(r rVar) {
        c0(rVar);
    }

    public Animator f0(ViewGroup viewGroup, r rVar, int i7, r rVar2, int i8) {
        if ((this.O & 1) != 1 || rVar2 == null) {
            return null;
        }
        if (rVar == null) {
            View view = (View) rVar2.f6161b.getParent();
            if (d0(t(view, false), E(view, false)).f6108a) {
                return null;
            }
        }
        return e0(viewGroup, rVar2.f6161b, rVar, rVar2);
    }

    public abstract Animator g0(ViewGroup viewGroup, View view, r rVar, r rVar2);

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
    public android.animation.Animator h0(android.view.ViewGroup r18, e1.r r19, int r20, e1.r r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e1.f0.h0(android.view.ViewGroup, e1.r, int, e1.r, int):android.animation.Animator");
    }

    @Override // e1.l
    public void i(r rVar) {
        c0(rVar);
    }

    public void i0(int i7) {
        if ((i7 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.O = i7;
    }

    @Override // e1.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        c cVarD0 = d0(rVar, rVar2);
        if (!cVarD0.f6108a) {
            return null;
        }
        if (cVarD0.f6112e == null && cVarD0.f6113f == null) {
            return null;
        }
        return cVarD0.f6109b ? f0(viewGroup, rVar, cVarD0.f6110c, rVar2, cVarD0.f6111d) : h0(viewGroup, rVar, cVarD0.f6110c, rVar2, cVarD0.f6111d);
    }
}
