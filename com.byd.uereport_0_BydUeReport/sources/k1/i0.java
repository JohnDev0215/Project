package k1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import k1.a;
import k1.l;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i0 extends l {
    public static final String[] N = {"android:visibility:visibility", "android:visibility:parent"};
    public int O = 3;

    /* JADX INFO: compiled from: Visibility.java */
    public class a extends m {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5757d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f5758e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f5759f;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.f5757d = viewGroup;
            this.f5758e = view;
            this.f5759f = view2;
        }

        @Override // k1.m, k1.l.f
        public void c(l lVar) {
            w.a(this.f5757d).d(this.f5758e);
        }

        @Override // k1.m, k1.l.f
        public void d(l lVar) {
            if (this.f5758e.getParent() == null) {
                w.a(this.f5757d).c(this.f5758e);
            } else {
                i0.this.f();
            }
        }

        @Override // k1.l.f
        public void e(l lVar) {
            this.f5759f.setTag(R$id.save_overlay_view, null);
            w.a(this.f5757d).d(this.f5758e);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class b extends AnimatorListenerAdapter implements l.f, a.InterfaceC0069a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f5761d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f5762e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final ViewGroup f5763f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final boolean f5764g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5765h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f5766i = false;

        public b(View view, int i8, boolean z7) {
            this.f5761d = view;
            this.f5762e = i8;
            this.f5763f = (ViewGroup) view.getParent();
            this.f5764g = z7;
            g(true);
        }

        @Override // k1.l.f
        public void a(l lVar) {
        }

        @Override // k1.l.f
        public void b(l lVar) {
        }

        @Override // k1.l.f
        public void c(l lVar) {
            g(false);
        }

        @Override // k1.l.f
        public void d(l lVar) {
            g(true);
        }

        @Override // k1.l.f
        public void e(l lVar) {
            f();
            lVar.P(this);
        }

        public final void f() {
            if (!this.f5766i) {
                b0.h(this.f5761d, this.f5762e);
                ViewGroup viewGroup = this.f5763f;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z7) {
            ViewGroup viewGroup;
            if (!this.f5764g || this.f5765h == z7 || (viewGroup = this.f5763f) == null) {
                return;
            }
            this.f5765h = z7;
            w.c(viewGroup, z7);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5766i = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, k1.a.InterfaceC0069a
        public void onAnimationPause(Animator animator) {
            if (this.f5766i) {
                return;
            }
            b0.h(this.f5761d, this.f5762e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, k1.a.InterfaceC0069a
        public void onAnimationResume(Animator animator) {
            if (this.f5766i) {
                return;
            }
            b0.h(this.f5761d, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5767a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5769c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5770d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f5771e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ViewGroup f5772f;
    }

    @Override // k1.l
    public String[] D() {
        return N;
    }

    @Override // k1.l
    public boolean F(r rVar, r rVar2) {
        if (rVar == null && rVar2 == null) {
            return false;
        }
        if (rVar != null && rVar2 != null && rVar2.f5822a.containsKey("android:visibility:visibility") != rVar.f5822a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(rVar, rVar2);
        if (cVarD0.f5767a) {
            return cVarD0.f5769c == 0 || cVarD0.f5770d == 0;
        }
        return false;
    }

    public final void c0(r rVar) {
        rVar.f5822a.put("android:visibility:visibility", Integer.valueOf(rVar.f5823b.getVisibility()));
        rVar.f5822a.put("android:visibility:parent", rVar.f5823b.getParent());
        int[] iArr = new int[2];
        rVar.f5823b.getLocationOnScreen(iArr);
        rVar.f5822a.put("android:visibility:screenLocation", iArr);
    }

    public final c d0(r rVar, r rVar2) {
        c cVar = new c();
        cVar.f5767a = false;
        cVar.f5768b = false;
        if (rVar == null || !rVar.f5822a.containsKey("android:visibility:visibility")) {
            cVar.f5769c = -1;
            cVar.f5771e = null;
        } else {
            cVar.f5769c = ((Integer) rVar.f5822a.get("android:visibility:visibility")).intValue();
            cVar.f5771e = (ViewGroup) rVar.f5822a.get("android:visibility:parent");
        }
        if (rVar2 == null || !rVar2.f5822a.containsKey("android:visibility:visibility")) {
            cVar.f5770d = -1;
            cVar.f5772f = null;
        } else {
            cVar.f5770d = ((Integer) rVar2.f5822a.get("android:visibility:visibility")).intValue();
            cVar.f5772f = (ViewGroup) rVar2.f5822a.get("android:visibility:parent");
        }
        if (rVar != null && rVar2 != null) {
            int i8 = cVar.f5769c;
            int i9 = cVar.f5770d;
            if (i8 == i9 && cVar.f5771e == cVar.f5772f) {
                return cVar;
            }
            if (i8 != i9) {
                if (i8 == 0) {
                    cVar.f5768b = false;
                    cVar.f5767a = true;
                } else if (i9 == 0) {
                    cVar.f5768b = true;
                    cVar.f5767a = true;
                }
            } else if (cVar.f5772f == null) {
                cVar.f5768b = false;
                cVar.f5767a = true;
            } else if (cVar.f5771e == null) {
                cVar.f5768b = true;
                cVar.f5767a = true;
            }
        } else if (rVar == null && cVar.f5770d == 0) {
            cVar.f5768b = true;
            cVar.f5767a = true;
        } else if (rVar2 == null && cVar.f5769c == 0) {
            cVar.f5768b = false;
            cVar.f5767a = true;
        }
        return cVar;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2);

    public Animator f0(ViewGroup viewGroup, r rVar, int i8, r rVar2, int i9) {
        if ((this.O & 1) != 1 || rVar2 == null) {
            return null;
        }
        if (rVar == null) {
            View view = (View) rVar2.f5823b.getParent();
            if (d0(t(view, false), E(view, false)).f5767a) {
                return null;
            }
        }
        return e0(viewGroup, rVar2.f5823b, rVar, rVar2);
    }

    @Override // k1.l
    public void g(r rVar) {
        c0(rVar);
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
    public android.animation.Animator h0(android.view.ViewGroup r18, k1.r r19, int r20, k1.r r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k1.i0.h0(android.view.ViewGroup, k1.r, int, k1.r, int):android.animation.Animator");
    }

    public void i0(int i8) {
        if ((i8 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.O = i8;
    }

    @Override // k1.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // k1.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        c cVarD0 = d0(rVar, rVar2);
        if (!cVarD0.f5767a) {
            return null;
        }
        if (cVarD0.f5771e == null && cVarD0.f5772f == null) {
            return null;
        }
        return cVarD0.f5768b ? f0(viewGroup, rVar, cVarD0.f5769c, rVar2, cVarD0.f5770d) : h0(viewGroup, rVar, cVarD0.f5769c, rVar2, cVarD0.f5770d);
    }
}
