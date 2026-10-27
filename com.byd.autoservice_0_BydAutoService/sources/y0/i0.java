package y0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.R$id;
import y0.a;
import y0.l;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i0 extends l {
    public static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    public int M = 3;

    /* JADX INFO: compiled from: Visibility.java */
    public class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5914b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5915c;

        public a(ViewGroup viewGroup, View view, View view2) {
            this.f5913a = viewGroup;
            this.f5914b = view;
            this.f5915c = view2;
        }

        @Override // y0.m, y0.l.f
        public void a(l lVar) {
            w.a(this.f5913a).d(this.f5914b);
        }

        @Override // y0.m, y0.l.f
        public void b(l lVar) {
            if (this.f5914b.getParent() == null) {
                w.a(this.f5913a).c(this.f5914b);
            } else {
                i0.this.f();
            }
        }

        @Override // y0.l.f
        public void e(l lVar) {
            this.f5915c.setTag(R$id.save_overlay_view, null);
            w.a(this.f5913a).d(this.f5914b);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class b extends AnimatorListenerAdapter implements l.f, a.InterfaceC0075a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f5917a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f5918b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ViewGroup f5919c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f5920d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f5921e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5922f = false;

        public b(View view, int i3, boolean z3) {
            this.f5917a = view;
            this.f5918b = i3;
            this.f5919c = (ViewGroup) view.getParent();
            this.f5920d = z3;
            g(true);
        }

        @Override // y0.l.f
        public void a(l lVar) {
            g(false);
        }

        @Override // y0.l.f
        public void b(l lVar) {
            g(true);
        }

        @Override // y0.l.f
        public void c(l lVar) {
        }

        @Override // y0.l.f
        public void d(l lVar) {
        }

        @Override // y0.l.f
        public void e(l lVar) {
            f();
            lVar.P(this);
        }

        public final void f() {
            if (!this.f5922f) {
                b0.h(this.f5917a, this.f5918b);
                ViewGroup viewGroup = this.f5919c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z3) {
            ViewGroup viewGroup;
            if (!this.f5920d || this.f5921e == z3 || (viewGroup = this.f5919c) == null) {
                return;
            }
            this.f5921e = z3;
            w.c(viewGroup, z3);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5922f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, y0.a.InterfaceC0075a
        public void onAnimationPause(Animator animator) {
            if (this.f5922f) {
                return;
            }
            b0.h(this.f5917a, this.f5918b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, y0.a.InterfaceC0075a
        public void onAnimationResume(Animator animator) {
            if (this.f5922f) {
                return;
            }
            b0.h(this.f5917a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX INFO: compiled from: Visibility.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5923a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5924b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5925c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5926d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f5927e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ViewGroup f5928f;
    }

    @Override // y0.l
    public String[] D() {
        return L;
    }

    @Override // y0.l
    public boolean F(r rVar, r rVar2) {
        if (rVar == null && rVar2 == null) {
            return false;
        }
        if (rVar != null && rVar2 != null && rVar2.f5980a.containsKey("android:visibility:visibility") != rVar.f5980a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c cVarD0 = d0(rVar, rVar2);
        if (cVarD0.f5923a) {
            return cVarD0.f5925c == 0 || cVarD0.f5926d == 0;
        }
        return false;
    }

    public final void c0(r rVar) {
        rVar.f5980a.put("android:visibility:visibility", Integer.valueOf(rVar.f5981b.getVisibility()));
        rVar.f5980a.put("android:visibility:parent", rVar.f5981b.getParent());
        int[] iArr = new int[2];
        rVar.f5981b.getLocationOnScreen(iArr);
        rVar.f5980a.put("android:visibility:screenLocation", iArr);
    }

    public final c d0(r rVar, r rVar2) {
        c cVar = new c();
        cVar.f5923a = false;
        cVar.f5924b = false;
        if (rVar == null || !rVar.f5980a.containsKey("android:visibility:visibility")) {
            cVar.f5925c = -1;
            cVar.f5927e = null;
        } else {
            cVar.f5925c = ((Integer) rVar.f5980a.get("android:visibility:visibility")).intValue();
            cVar.f5927e = (ViewGroup) rVar.f5980a.get("android:visibility:parent");
        }
        if (rVar2 == null || !rVar2.f5980a.containsKey("android:visibility:visibility")) {
            cVar.f5926d = -1;
            cVar.f5928f = null;
        } else {
            cVar.f5926d = ((Integer) rVar2.f5980a.get("android:visibility:visibility")).intValue();
            cVar.f5928f = (ViewGroup) rVar2.f5980a.get("android:visibility:parent");
        }
        if (rVar != null && rVar2 != null) {
            int i3 = cVar.f5925c;
            int i4 = cVar.f5926d;
            if (i3 == i4 && cVar.f5927e == cVar.f5928f) {
                return cVar;
            }
            if (i3 != i4) {
                if (i3 == 0) {
                    cVar.f5924b = false;
                    cVar.f5923a = true;
                } else if (i4 == 0) {
                    cVar.f5924b = true;
                    cVar.f5923a = true;
                }
            } else if (cVar.f5928f == null) {
                cVar.f5924b = false;
                cVar.f5923a = true;
            } else if (cVar.f5927e == null) {
                cVar.f5924b = true;
                cVar.f5923a = true;
            }
        } else if (rVar == null && cVar.f5926d == 0) {
            cVar.f5924b = true;
            cVar.f5923a = true;
        } else if (rVar2 == null && cVar.f5925c == 0) {
            cVar.f5924b = false;
            cVar.f5923a = true;
        }
        return cVar;
    }

    public abstract Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2);

    public Animator f0(ViewGroup viewGroup, r rVar, int i3, r rVar2, int i4) {
        if ((this.M & 1) != 1 || rVar2 == null) {
            return null;
        }
        if (rVar == null) {
            View view = (View) rVar2.f5981b.getParent();
            if (d0(t(view, false), E(view, false)).f5923a) {
                return null;
            }
        }
        return e0(viewGroup, rVar2.f5981b, rVar, rVar2);
    }

    @Override // y0.l
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
    public android.animation.Animator h0(android.view.ViewGroup r18, y0.r r19, int r20, y0.r r21, int r22) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.i0.h0(android.view.ViewGroup, y0.r, int, y0.r, int):android.animation.Animator");
    }

    public void i0(int i3) {
        if ((i3 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.M = i3;
    }

    @Override // y0.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // y0.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        c cVarD0 = d0(rVar, rVar2);
        if (!cVarD0.f5923a) {
            return null;
        }
        if (cVarD0.f5927e == null && cVarD0.f5928f == null) {
            return null;
        }
        return cVarD0.f5924b ? f0(viewGroup, rVar, cVarD0.f5925c, rVar2, cVarD0.f5926d) : h0(viewGroup, rVar, cVarD0.f5925c, rVar2, cVarD0.f5926d);
    }
}
