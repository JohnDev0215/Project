package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import defpackage.nd;
import defpackage.yd;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ve extends yd {
    public static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    public int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    public class a extends zd {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ View b;
        public final /* synthetic */ View c;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.a = viewGroup;
            this.b = view;
            this.c = view2;
        }

        @Override // defpackage.zd, yd.f
        public void a(yd ydVar) {
            je.a(this.a).d(this.b);
        }

        @Override // defpackage.zd, yd.f
        public void b(yd ydVar) {
            if (this.b.getParent() == null) {
                je.a(this.a).c(this.b);
            } else {
                ve.this.f();
            }
        }

        @Override // yd.f
        public void e(yd ydVar) {
            this.c.setTag(R$id.save_overlay_view, null);
            je.a(this.a).d(this.b);
            ydVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class b extends AnimatorListenerAdapter implements yd.f, nd.a {
        public final View a;
        public final int b;
        public final ViewGroup c;
        public final boolean d;
        public boolean e;
        public boolean f = false;

        public b(View view, int i, boolean z) {
            this.a = view;
            this.b = i;
            this.c = (ViewGroup) view.getParent();
            this.d = z;
            g(true);
        }

        @Override // yd.f
        public void a(yd ydVar) {
            g(false);
        }

        @Override // yd.f
        public void b(yd ydVar) {
            g(true);
        }

        @Override // yd.f
        public void c(yd ydVar) {
        }

        @Override // yd.f
        public void d(yd ydVar) {
        }

        @Override // yd.f
        public void e(yd ydVar) {
            f();
            ydVar.P(this);
        }

        public final void f() {
            if (!this.f) {
                oe.h(this.a, this.b);
                ViewGroup viewGroup = this.c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.d || this.e == z || (viewGroup = this.c) == null) {
                return;
            }
            this.e = z;
            je.c(viewGroup, z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, nd.a
        public void onAnimationPause(Animator animator) {
            if (this.f) {
                return;
            }
            oe.h(this.a, this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, nd.a
        public void onAnimationResume(Animator animator) {
            if (this.f) {
                return;
            }
            oe.h(this.a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class c {
        public boolean a;
        public boolean b;
        public int c;
        public int d;
        public ViewGroup e;
        public ViewGroup f;
    }

    @Override // defpackage.yd
    public String[] D() {
        return L;
    }

    @Override // defpackage.yd
    public boolean F(ee eeVar, ee eeVar2) {
        if (eeVar == null && eeVar2 == null) {
            return false;
        }
        if (eeVar != null && eeVar2 != null && eeVar2.a.containsKey("android:visibility:visibility") != eeVar.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(eeVar, eeVar2);
        if (cVarD0.a) {
            return cVarD0.c == 0 || cVarD0.d == 0;
        }
        return false;
    }

    public final void c0(ee eeVar) {
        eeVar.a.put("android:visibility:visibility", Integer.valueOf(eeVar.b.getVisibility()));
        eeVar.a.put("android:visibility:parent", eeVar.b.getParent());
        int[] iArr = new int[2];
        eeVar.b.getLocationOnScreen(iArr);
        eeVar.a.put("android:visibility:screenLocation", iArr);
    }

    public final c d0(ee eeVar, ee eeVar2) {
        c cVar = new c();
        cVar.a = false;
        cVar.b = false;
        if (eeVar == null || !eeVar.a.containsKey("android:visibility:visibility")) {
            cVar.c = -1;
            cVar.e = null;
        } else {
            cVar.c = ((Integer) eeVar.a.get("android:visibility:visibility")).intValue();
            cVar.e = (ViewGroup) eeVar.a.get("android:visibility:parent");
        }
        if (eeVar2 == null || !eeVar2.a.containsKey("android:visibility:visibility")) {
            cVar.d = -1;
            cVar.f = null;
        } else {
            cVar.d = ((Integer) eeVar2.a.get("android:visibility:visibility")).intValue();
            cVar.f = (ViewGroup) eeVar2.a.get("android:visibility:parent");
        }
        if (eeVar != null && eeVar2 != null) {
            int i = cVar.c;
            int i2 = cVar.d;
            if (i == i2 && cVar.e == cVar.f) {
                return cVar;
            }
            if (i != i2) {
                if (i == 0) {
                    cVar.b = false;
                    cVar.a = true;
                } else if (i2 == 0) {
                    cVar.b = true;
                    cVar.a = true;
                }
            } else if (cVar.f == null) {
                cVar.b = false;
                cVar.a = true;
            } else if (cVar.e == null) {
                cVar.b = true;
                cVar.a = true;
            }
        } else if (eeVar == null && cVar.d == 0) {
            cVar.b = true;
            cVar.a = true;
        } else if (eeVar2 == null && cVar.c == 0) {
            cVar.b = false;
            cVar.a = true;
        }
        return cVar;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, ee eeVar, ee eeVar2);

    public Animator f0(ViewGroup viewGroup, ee eeVar, int i, ee eeVar2, int i2) {
        if ((this.M & 1) != 1 || eeVar2 == null) {
            return null;
        }
        if (eeVar == null) {
            View view = (View) eeVar2.b.getParent();
            if (d0(t(view, false), E(view, false)).a) {
                return null;
            }
        }
        return e0(viewGroup, eeVar2.b, eeVar, eeVar2);
    }

    @Override // defpackage.yd
    public void g(ee eeVar) {
        c0(eeVar);
    }

    public abstract Animator g0(ViewGroup viewGroup, View view, ee eeVar, ee eeVar2);

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
    public android.animation.Animator h0(android.view.ViewGroup r18, defpackage.ee r19, int r20, defpackage.ee r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ve.h0(android.view.ViewGroup, ee, int, ee, int):android.animation.Animator");
    }

    public void i0(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i;
    }

    @Override // defpackage.yd
    public void j(ee eeVar) {
        c0(eeVar);
    }

    @Override // defpackage.yd
    public Animator n(ViewGroup viewGroup, ee eeVar, ee eeVar2) {
        c cVarD0 = d0(eeVar, eeVar2);
        if (!cVarD0.a) {
            return null;
        }
        if (cVarD0.e == null && cVarD0.f == null) {
            return null;
        }
        return cVarD0.b ? f0(viewGroup, eeVar, cVarD0.c, eeVar2, cVarD0.d) : h0(viewGroup, eeVar, cVarD0.c, eeVar2, cVarD0.d);
    }
}
