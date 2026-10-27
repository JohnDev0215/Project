package b.x;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import b.x.a;
import b.x.m;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends m {
    public static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    public int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    public class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f3265a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f3266b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f3267c;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.f3265a = viewGroup;
            this.f3266b = view;
            this.f3267c = view2;
        }

        @Override // b.x.n, b.x.m.f
        public void a(m mVar) {
            x.a(this.f3265a).d(this.f3266b);
        }

        @Override // b.x.n, b.x.m.f
        public void b(m mVar) {
            if (this.f3266b.getParent() == null) {
                x.a(this.f3265a).c(this.f3266b);
            } else {
                j0.this.cancel();
            }
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            this.f3267c.setTag(j.save_overlay_view, null);
            x.a(this.f3265a).d(this.f3266b);
            mVar.Q(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0064a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f3269a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f3270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ViewGroup f3271c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f3272d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3273e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3274f = false;

        public b(View view, int i2, boolean z) {
            this.f3269a = view;
            this.f3270b = i2;
            this.f3271c = (ViewGroup) view.getParent();
            this.f3272d = z;
            g(true);
        }

        @Override // b.x.m.f
        public void a(m mVar) {
            g(false);
        }

        @Override // b.x.m.f
        public void b(m mVar) {
            g(true);
        }

        @Override // b.x.m.f
        public void c(m mVar) {
        }

        @Override // b.x.m.f
        public void d(m mVar) {
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            f();
            mVar.Q(this);
        }

        public final void f() {
            if (!this.f3274f) {
                c0.h(this.f3269a, this.f3270b);
                ViewGroup viewGroup = this.f3271c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f3272d || this.f3273e == z || (viewGroup = this.f3271c) == null) {
                return;
            }
            this.f3273e = z;
            x.c(viewGroup, z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3274f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.x.a.InterfaceC0064a
        public void onAnimationPause(Animator animator) {
            if (this.f3274f) {
                return;
            }
            c0.h(this.f3269a, this.f3270b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, b.x.a.InterfaceC0064a
        public void onAnimationResume(Animator animator) {
            if (this.f3274f) {
                return;
            }
            c0.h(this.f3269a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3278d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f3279e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ViewGroup f3280f;
    }

    @Override // b.x.m
    public String[] E() {
        return L;
    }

    @Override // b.x.m
    public boolean G(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f3315a.containsKey("android:visibility:visibility") != sVar.f3315a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarE0 = e0(sVar, sVar2);
        if (cVarE0.f3275a) {
            return cVarE0.f3277c == 0 || cVarE0.f3278d == 0;
        }
        return false;
    }

    public final void d0(s sVar) {
        sVar.f3315a.put("android:visibility:visibility", Integer.valueOf(sVar.f3316b.getVisibility()));
        sVar.f3315a.put("android:visibility:parent", sVar.f3316b.getParent());
        int[] iArr = new int[2];
        sVar.f3316b.getLocationOnScreen(iArr);
        sVar.f3315a.put("android:visibility:screenLocation", iArr);
    }

    public final c e0(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f3275a = false;
        cVar.f3276b = false;
        if (sVar == null || !sVar.f3315a.containsKey("android:visibility:visibility")) {
            cVar.f3277c = -1;
            cVar.f3279e = null;
        } else {
            cVar.f3277c = ((Integer) sVar.f3315a.get("android:visibility:visibility")).intValue();
            cVar.f3279e = (ViewGroup) sVar.f3315a.get("android:visibility:parent");
        }
        if (sVar2 == null || !sVar2.f3315a.containsKey("android:visibility:visibility")) {
            cVar.f3278d = -1;
            cVar.f3280f = null;
        } else {
            cVar.f3278d = ((Integer) sVar2.f3315a.get("android:visibility:visibility")).intValue();
            cVar.f3280f = (ViewGroup) sVar2.f3315a.get("android:visibility:parent");
        }
        if (sVar != null && sVar2 != null) {
            int i2 = cVar.f3277c;
            int i3 = cVar.f3278d;
            if (i2 == i3 && cVar.f3279e == cVar.f3280f) {
                return cVar;
            }
            if (i2 != i3) {
                if (i2 == 0) {
                    cVar.f3276b = false;
                    cVar.f3275a = true;
                } else if (i3 == 0) {
                    cVar.f3276b = true;
                    cVar.f3275a = true;
                }
            } else if (cVar.f3280f == null) {
                cVar.f3276b = false;
                cVar.f3275a = true;
            } else if (cVar.f3279e == null) {
                cVar.f3276b = true;
                cVar.f3275a = true;
            }
        } else if (sVar == null && cVar.f3278d == 0) {
            cVar.f3276b = true;
            cVar.f3275a = true;
        } else if (sVar2 == null && cVar.f3277c == 0) {
            cVar.f3276b = false;
            cVar.f3275a = true;
        }
        return cVar;
    }

    @Override // b.x.m
    public void f(s sVar) {
        d0(sVar);
    }

    public abstract Animator f0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public Animator g0(ViewGroup viewGroup, s sVar, int i2, s sVar2, int i3) {
        if ((this.M & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f3316b.getParent();
            if (e0(s(view, false), F(view, false)).f3275a) {
                return null;
            }
        }
        return f0(viewGroup, sVar2.f3316b, sVar, sVar2);
    }

    public abstract Animator h0(ViewGroup viewGroup, View view, s sVar, s sVar2);

    @Override // b.x.m
    public void i(s sVar) {
        d0(sVar);
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
    public android.animation.Animator i0(android.view.ViewGroup r18, b.x.s r19, int r20, b.x.s r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.x.j0.i0(android.view.ViewGroup, b.x.s, int, b.x.s, int):android.animation.Animator");
    }

    public void j0(int i2) {
        if ((i2 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i2;
    }

    @Override // b.x.m
    public Animator m(ViewGroup viewGroup, s sVar, s sVar2) {
        c cVarE0 = e0(sVar, sVar2);
        if (!cVarE0.f3275a) {
            return null;
        }
        if (cVarE0.f3279e == null && cVarE0.f3280f == null) {
            return null;
        }
        return cVarE0.f3276b ? g0(viewGroup, sVar, cVarE0.f3277c, sVar2, cVarE0.f3278d) : i0(viewGroup, sVar, cVarE0.f3277c, sVar2, cVarE0.f3278d);
    }
}
