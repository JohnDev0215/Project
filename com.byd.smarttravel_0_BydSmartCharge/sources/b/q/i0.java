package b.q;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import b.q.a;
import b.q.l;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i0 extends l {
    public static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    public int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    public class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1472a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1473b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f1474c;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.f1472a = viewGroup;
            this.f1473b = view;
            this.f1474c = view2;
        }

        @Override // b.q.m, b.q.l.f
        public void a(l lVar) {
            w.a(this.f1472a).d(this.f1473b);
        }

        @Override // b.q.m, b.q.l.f
        public void b(l lVar) {
            if (this.f1473b.getParent() == null) {
                w.a(this.f1472a).c(this.f1473b);
            } else {
                i0.this.f();
            }
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            this.f1474c.setTag(R$id.save_overlay_view, null);
            w.a(this.f1472a).d(this.f1473b);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class b extends AnimatorListenerAdapter implements l.f, a.InterfaceC0038a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1476a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1477b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ViewGroup f1478c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f1479d;
        public boolean e;
        public boolean f = false;

        public b(View view, int i, boolean z) {
            this.f1476a = view;
            this.f1477b = i;
            this.f1478c = (ViewGroup) view.getParent();
            this.f1479d = z;
            g(true);
        }

        @Override // b.q.l.f
        public void a(l lVar) {
            g(false);
        }

        @Override // b.q.l.f
        public void b(l lVar) {
            g(true);
        }

        @Override // b.q.l.f
        public void c(l lVar) {
        }

        @Override // b.q.l.f
        public void d(l lVar) {
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            f();
            lVar.P(this);
        }

        public final void f() {
            if (!this.f) {
                b0.h(this.f1476a, this.f1477b);
                ViewGroup viewGroup = this.f1478c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f1479d || this.e == z || (viewGroup = this.f1478c) == null) {
                return;
            }
            this.e = z;
            w.c(viewGroup, z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.q.a.InterfaceC0038a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            b0.h(this.f1476a, this.f1477b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.q.a.InterfaceC0038a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            b0.h(this.f1476a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1480a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1481b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1482c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1483d;
        public ViewGroup e;
        public ViewGroup f;
    }

    @Override // b.q.l
    public String[] D() {
        return L;
    }

    @Override // b.q.l
    public boolean F(r rVar, r rVar2) {
        if (rVar == null && rVar2 == null) {
            return false;
        }
        if (rVar != null && rVar2 != null && rVar2.f1512a.containsKey("android:visibility:visibility") != rVar.f1512a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(rVar, rVar2);
        if (cVarD0.f1480a) {
            return cVarD0.f1482c == 0 || cVarD0.f1483d == 0;
        }
        return false;
    }

    public final void c0(r rVar) {
        rVar.f1512a.put("android:visibility:visibility", Integer.valueOf(rVar.f1513b.getVisibility()));
        rVar.f1512a.put("android:visibility:parent", rVar.f1513b.getParent());
        int[] iArr = new int[2];
        rVar.f1513b.getLocationOnScreen(iArr);
        rVar.f1512a.put("android:visibility:screenLocation", iArr);
    }

    public final c d0(r rVar, r rVar2) {
        c cVar = new c();
        cVar.f1480a = false;
        cVar.f1481b = false;
        if (rVar == null || !rVar.f1512a.containsKey("android:visibility:visibility")) {
            cVar.f1482c = -1;
            cVar.e = null;
        } else {
            cVar.f1482c = ((Integer) rVar.f1512a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) rVar.f1512a.get("android:visibility:parent");
        }
        if (rVar2 == null || !rVar2.f1512a.containsKey("android:visibility:visibility")) {
            cVar.f1483d = -1;
            cVar.f = null;
        } else {
            cVar.f1483d = ((Integer) rVar2.f1512a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) rVar2.f1512a.get("android:visibility:parent");
        }
        if (rVar != null && rVar2 != null) {
            int i = cVar.f1482c;
            int i2 = cVar.f1483d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.f1481b = false;
                    cVar.f1480a = true;
                } else if (i2 == 0) {
                    cVar.f1481b = true;
                    cVar.f1480a = true;
                }
            } else if (cVar.f == null) {
                cVar.f1481b = false;
                cVar.f1480a = true;
            } else if (cVar.e == null) {
                cVar.f1481b = true;
                cVar.f1480a = true;
            }
        } else if (rVar == null && cVar.f1483d == 0) {
            cVar.f1481b = true;
            cVar.f1480a = true;
        } else if (rVar2 == null && cVar.f1482c == 0) {
            cVar.f1481b = false;
            cVar.f1480a = true;
        }
        return cVar;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2);

    public Animator f0(ViewGroup viewGroup, r rVar, int i, r rVar2, int i2) {
        if ((this.M & 1) != 1 || rVar2 == null) {
            return null;
        }
        if (rVar == null) {
            View view = (View) rVar2.f1513b.getParent();
            if (d0(t(view, false), E(view, false)).f1480a) {
                return null;
            }
        }
        return e0(viewGroup, rVar2.f1513b, rVar, rVar2);
    }

    @Override // b.q.l
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
    public android.animation.Animator h0(android.view.ViewGroup r18, b.q.r r19, int r20, b.q.r r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.q.i0.h0(android.view.ViewGroup, b.q.r, int, b.q.r, int):android.animation.Animator");
    }

    public void i0(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i;
    }

    @Override // b.q.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // b.q.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        c cVarD0 = d0(rVar, rVar2);
        if (!cVarD0.f1480a) {
            return null;
        }
        if (cVarD0.e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.f1481b ? f0(viewGroup, rVar, cVarD0.f1482c, rVar2, cVarD0.f1483d) : h0(viewGroup, rVar, cVarD0.f1482c, rVar2, cVarD0.f1483d);
    }
}
