package defpackage;

import android.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import defpackage.o23;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.customtabs.TranslucentCustomTabActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.webapps.SameTaskWebApkActivity;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SplashController.java */
/* JADX INFO: loaded from: classes.dex */
public final class sq2 extends o23.b implements sz0, v80 {
    public final Activity e;
    public final q2 f;
    public final o23 g;
    public final vg3 h;
    public final a30 i;
    public final p61<CompositorViewHolder> j;
    public tq2 k;
    public ViewGroup l;
    public View m;
    public ViewPropertyAnimator n;
    public long o;
    public final int p;
    public boolean q;
    public boolean r;
    public long s;
    public boolean t;
    public final b<vq2> u = new b<>();

    /* JADX INFO: compiled from: SplashController.java */
    public static class a implements ViewTreeObserver.OnDrawListener {
        public final View e;
        public final Runnable f;
        public boolean g;

        public a(ViewGroup viewGroup, Runnable runnable) {
            this.e = viewGroup;
            this.f = runnable;
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.f.run();
            this.e.post(new aw(14, this));
        }
    }

    public sq2(Activity activity, q2 q2Var, o23 o23Var, c40 c40Var, vg3 vg3Var, a30 a30Var, p61<CompositorViewHolder> p61Var) {
        this.e = activity;
        this.f = q2Var;
        this.g = o23Var;
        this.p = 0;
        this.h = vg3Var;
        this.i = a30Var;
        this.j = p61Var;
        int i = BaseCustomTabActivity.o1;
        this.p = (activity instanceof TranslucentCustomTabActivity) || (activity instanceof SameTaskWebApkActivity) ? bq.Y.a() ? 2 : 1 : 0;
        c40Var.getClass();
        q2Var.f(this);
        o23Var.c(this);
    }

    public final void B1(Tab tab) {
        if (this.r) {
            return;
        }
        this.r = true;
        this.g.e(this);
        TraceEvent.I0(hashCode(), "SplashScreen.hidingAnimation");
        this.e.findViewById(R$id.coordinator).setVisibility(0);
        if (this.o == 0) {
            D1(tab);
        } else {
            this.n = this.m.animate().alpha(0.0f).setDuration(this.o).withEndAction(new z8(this, 6, tab));
        }
    }

    public final void C1(Tab tab, boolean z) {
        if (this.f.d()) {
            return;
        }
        if (this.p == 2 && !this.t) {
            E1();
            this.e.getWindow().setFormat(-2);
            this.l.invalidate();
        }
        if (z) {
            B1(tab);
        } else {
            this.j.get().getCompositorView().i(new l91(this, 8, tab));
        }
    }

    public final void D1(Tab tab) {
        this.l.removeView(this.m);
        SystemClock.elapsedRealtime();
        TraceEvent.F(hashCode(), "SplashScreen.hidingAnimation");
        ViewGroup viewGroup = this.l;
        viewGroup.getViewTreeObserver().addOnDrawListener(new a(viewGroup, new androidx.appcompat.widget.a(17, this)));
        this.k.a(tab);
        long j = this.s;
        b<vq2> bVar = this.u;
        Iterator<vq2> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                bVar.clear();
                this.h.d = false;
                this.f.e(this);
                this.k = null;
                this.m = null;
                this.n = null;
                return;
            }
            ((vq2) aVar.next()).a(j);
        }
    }

    public final void E1() {
        this.t = true;
        try {
            Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.e, new Object[0]);
        } catch (ReflectiveOperationException unused) {
        }
        Iterator<vq2> it = this.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((vq2) aVar.next()).b();
            }
        }
    }

    public final void F1() {
        this.s = SystemClock.elapsedRealtime();
        TraceEvent traceEventH0 = TraceEvent.H0("SplashScreen.build", null);
        try {
            this.m = this.k.b();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            View view = this.m;
            int i = this.p;
            if (view == null) {
                this.g.e(this);
                this.f.e(this);
                if (i != 0) {
                    E1();
                    return;
                }
                return;
            }
            ViewGroup viewGroup = (ViewGroup) this.e.findViewById(R.id.content);
            this.l = viewGroup;
            viewGroup.addView(this.m);
            ViewGroup viewGroup2 = this.l;
            viewGroup2.getViewTreeObserver().addOnDrawListener(new a(viewGroup2, new wq0(19, this)));
            if (i == 1) {
                E1();
            }
            this.h.d = true;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        C1(tabImpl, true);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i, Tab tab) {
        if (!this.k.c()) {
            C1(tab, true);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void S0(Tab tab, boolean z) {
        if (!tab.k() && z && this.i.c == 2 && (!this.k.c())) {
            C1(tab, false);
        }
    }

    @Override // defpackage.sz0
    public final void Y0() {
        if (this.p == 1) {
            this.e.findViewById(R$id.coordinator).setVisibility(4);
        }
        View view = this.m;
        if (view == null) {
            return;
        }
        if (view.getParent() != null) {
            this.l.removeView(this.m);
        }
        this.l.addView(this.m);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Z(TabImpl tabImpl) {
        if (!this.k.c()) {
            C1(tabImpl, false);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        if (!this.k.c()) {
            C1(tab, false);
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        ViewPropertyAnimator viewPropertyAnimator = this.n;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // defpackage.sz0
    public final void q() {
        this.q = true;
        if (this.k != null) {
            F1();
        }
    }
}
