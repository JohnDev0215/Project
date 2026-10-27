package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.kj;
import defpackage.z2;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.embedder_support.view.ContentView;

/* JADX INFO: compiled from: BrowserControlsManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class fj implements ApplicationStatus.c, jj {
    public ValueAnimator A;
    public boolean B;
    public boolean C;
    public final a D;
    public final Activity e;
    public final vj f;
    public final int g;
    public final mb3 h;
    public final aq1<Boolean> i;
    public c j;
    public e00 k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public float v;
    public b w;
    public final org.chromium.base.b<kj.a> x;
    public final qq0 y;
    public Tab z;

    /* JADX INFO: compiled from: BrowserControlsManager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            fj fjVar = fj.this;
            int i = fjVar.B() ? 0 : 4;
            e00 e00Var = fjVar.k;
            if (e00Var == null || e00Var.getView().getVisibility() == i) {
                return;
            }
            if (i == 0 && fjVar.C && cc3.b()) {
                return;
            }
            TraceEvent traceEventH0 = TraceEvent.H0("BrowserControlsManager.onAndroidVisibilityChanged", null);
            try {
                fjVar.k.getView().setVisibility(i);
                Iterator<kj.a> it = fjVar.x.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((kj.a) aVar.next()).j(i);
                    }
                }
                if (!cc3.b()) {
                    ao3.e(fjVar.k.getView(), "BrowserControlsManager.mUpdateVisibilityRunnable Runnable");
                }
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
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
    }

    /* JADX INFO: compiled from: BrowserControlsManager.java */
    public class b extends z2.b {
        public b(z2 z2Var) {
            super(z2Var, false);
        }

        @Override // z2.b
        public final void y1(Tab tab) {
            fj fjVar = fj.this;
            Tab tab2 = fjVar.z;
            fjVar.z = tab;
            vj vjVar = fjVar.f;
            if (tab2 != tab && tab != null) {
                vjVar.x();
                if (tab.isUserInteractable()) {
                    fjVar.m();
                }
            }
            if (tab != null || ((Integer) vjVar.f).intValue() == 2) {
                return;
            }
            fjVar.A();
        }
    }

    /* JADX INFO: compiled from: BrowserControlsManager.java */
    public class c extends j23 {
        public c(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D(TabImpl tabImpl, int i, int i2, int i3, int i4, int i5) {
            if (tabImpl == fj.this.z && tabImpl.isUserInteractable() && !tabImpl.isNativePage()) {
                fj.v(fj.this, i, i2, i3, i4, i5);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I(Tab tab, boolean z) {
            fj fjVar = fj.this;
            if (tab != fjVar.z || z) {
                return;
            }
            fjVar.n(false);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void S0(Tab tab, boolean z) {
            if (z && tab == fj.this.z) {
                qy2 qy2VarY1 = qy2.y1(tab);
                if (qy2VarY1.k) {
                    fj.v(fj.this, qy2VarY1.f, qy2VarY1.g, qy2VarY1.h, qy2VarY1.i, qy2VarY1.j);
                }
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void e(boolean z) {
            fj fjVar = fj.this;
            if (!z && cc3.b() && fjVar.B() && fjVar.k.getView().getVisibility() != 0) {
                fjVar.y();
            }
            fjVar.C = z;
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            if (tab.s()) {
                fj.this.n(false);
            }
        }
    }

    /* JADX INFO: compiled from: BrowserControlsManager.java */
    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            fj fjVar = fj.this;
            fjVar.C(false, 0, 0, fjVar.l, fjVar.m, fjVar.o);
            fj.this.A = null;
        }
    }

    public fj(Activity activity) {
        int i = 6;
        this.h = new mb3(new qm0(i, this));
        aq1<Boolean> aq1Var = new aq1<>();
        this.i = aq1Var;
        this.x = new org.chromium.base.b<>();
        this.D = new a();
        this.e = activity;
        this.g = 0;
        aq1Var.r(Boolean.FALSE);
        qq0 qq0Var = new qq0(activity, aq1Var);
        this.y = qq0Var;
        vj vjVar = new vj(qq0Var.g);
        this.f = vjVar;
        vjVar.p(new dm0(i, this));
    }

    public static void v(fj fjVar, int i, int i2, int i3, int i4, int i5) {
        if (fjVar.B) {
            ValueAnimator valueAnimator = fjVar.A;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            fjVar.B = false;
        }
        Tab tab = fjVar.z;
        if (hd2.z1(tab) || tab.isNativePage()) {
            fjVar.n(false);
        } else {
            fjVar.C(false, i, i2, i3, i4, i5);
        }
        if (n3.y <= 0) {
            return;
        }
        n3.A = true;
    }

    public final void A() {
        Tab tab = this.z;
        if (tab != null && tab.isInitialized()) {
            if ((py2.a(tab) == null ? 3 : py2.a(tab).b()) == 2) {
                int i = this.m;
                int i2 = i - this.l;
                int i3 = this.n;
                int i4 = this.o;
                z(i2, i3 - i4, i, i, i4);
                return;
            }
        }
        z(0, 0, this.l, this.m, this.o);
    }

    public final boolean B() {
        if (this.k == null || this.h.b()) {
            return false;
        }
        if (this.B) {
            return true;
        }
        boolean z = !(p() > 0.0f);
        Tab tab = this.z;
        ContentView contentViewD = tab != null ? tab.d() : null;
        if (contentViewD == null) {
            return z;
        }
        for (int i = 0; i < contentViewD.getChildCount(); i++) {
            View childAt = contentViewD.getChildAt(i);
            if ((childAt.getLayoutParams() instanceof FrameLayout.LayoutParams) && 48 == (((FrameLayout.LayoutParams) childAt.getLayoutParams()).gravity & 112)) {
                return true;
            }
        }
        return z;
    }

    public final void C(boolean z, int i, int i2, int i3, int i4, int i5) {
        if (z) {
            A();
        } else {
            z(i, i2, i3, i4, i5);
        }
    }

    @Override // defpackage.kj
    public final int a() {
        return this.m;
    }

    @Override // defpackage.kj
    public final int b() {
        return this.o;
    }

    @Override // defpackage.mj
    public final int c(int i) {
        mb3 mb3Var = this.h;
        int iA = mb3Var.a();
        mb3Var.c(i);
        return iA;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i != 2) {
            if (i == 6) {
                ApplicationStatus.i(this);
            }
        } else {
            d73 d73Var = rh3.a;
            vj vjVar = this.f;
            Objects.requireNonNull(vjVar);
            PostTask.b(d73Var, new tj(vjVar, 1), 100L);
        }
    }

    @Override // defpackage.kj
    public final int e() {
        return this.q;
    }

    @Override // defpackage.kj
    public final int f() {
        return this.s;
    }

    @Override // defpackage.kj
    public final int g() {
        return this.n;
    }

    @Override // defpackage.mj
    public final boolean h() {
        return this.B;
    }

    @Override // defpackage.mj
    public final vj i() {
        return this.f;
    }

    @Override // defpackage.kj
    public final boolean j() {
        return this.p;
    }

    @Override // defpackage.jj
    public final void k(int i, int i2) {
        if (this.n == i && this.o == i2) {
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("BrowserControlsManager.setBottomControlsHeight", null);
        try {
            this.n = i;
            this.o = i2;
            Iterator<kj.a> it = this.x.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((kj.a) aVar.next()).l(this.n);
                }
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
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

    @Override // defpackage.kj
    public final int l() {
        return this.l;
    }

    @Override // defpackage.mj
    public final void m() {
        if (this.B) {
            ValueAnimator valueAnimator = this.A;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.B = false;
        }
        Tab tab = this.z;
        qy2 qy2VarY1 = tab != null ? qy2.y1(tab) : null;
        if (qy2VarY1 == null || !qy2VarY1.k || tab == null || tab.isNativePage()) {
            n(false);
        } else {
            C(false, qy2VarY1.f, qy2VarY1.g, qy2VarY1.h, qy2VarY1.i, qy2VarY1.j);
        }
        if (tab == null || py2.a(tab) == null) {
            return;
        }
        py2.a(tab).e();
    }

    @Override // defpackage.mj
    public final void n(boolean z) {
        if (!z) {
            C(true, 0, 0, this.l, this.m, this.o);
            return;
        }
        if (this.A != null) {
            return;
        }
        this.B = true;
        float f = this.v;
        final int i = this.l;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.q, 0);
        this.A = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration((long) Math.abs(f * 200.0f));
        this.A.addListener(new gj(this, i));
        this.A.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: ej
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i2 = i;
                fj fjVar = this.e;
                fjVar.getClass();
                fjVar.C(false, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, i2, fjVar.m, fjVar.o);
            }
        });
        this.A.start();
    }

    @Override // defpackage.kj
    public final void o(kj.a aVar) {
        this.x.a(aVar);
    }

    @Override // defpackage.kj
    public final float p() {
        return this.v;
    }

    @Override // defpackage.mj
    public final void q(int i) {
        this.h.c(i);
    }

    @Override // defpackage.kj
    public final int r() {
        return this.t;
    }

    @Override // defpackage.kj
    public final void s(kj.a aVar) {
        this.x.c(aVar);
    }

    @Override // defpackage.kj
    public final int t() {
        e00 e00Var = this.k;
        if (e00Var == null) {
            return 4;
        }
        return e00Var.getView().getVisibility();
    }

    @Override // defpackage.kj
    public final int u() {
        return Math.min(this.r, this.n);
    }

    public final void w(e00 e00Var, z2 z2Var, y13 y13Var, int i) {
        qq0 qq0Var = this.y;
        ApplicationStatus.f(qq0Var, qq0Var.e);
        ApplicationStatus.h(qq0Var);
        qq0Var.r = new mq0(qq0Var, z2Var);
        qq0Var.s = new nq0(qq0Var, y13Var, y13Var);
        Activity activity = this.e;
        ApplicationStatus.f(this, activity);
        this.w = new b(z2Var);
        this.j = new c(y13Var);
        this.k = e00Var;
        int i2 = this.g;
        if (i2 == 0) {
            this.l = activity.getResources().getDimensionPixelSize(i);
        } else if (i2 == 1) {
            this.v = 1.0f;
        }
        int i3 = this.l;
        this.s = i3;
        if (i2 != 1) {
            if (i3 == 0) {
                this.v = 1.0f;
            } else {
                this.v = Math.abs(this.q / i3);
            }
        }
        y();
    }

    public final void x(final int i, final int i2) {
        if (this.A != null) {
            return;
        }
        this.B = true;
        final int i3 = this.l;
        final int i4 = this.m;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.A = valueAnimatorOfFloat;
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: dj
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                fj fjVar = this.e;
                fjVar.getClass();
                float fFloatValue = (((Float) valueAnimator.getAnimatedValue()).floatValue() * (i4 - r1)) + i2;
                int i5 = i;
                float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float f = (fFloatValue2 * (r7 - i5)) + i5;
                fjVar.C(false, (int) (f - i3), fjVar.u(), (int) f, (int) fFloatValue, fjVar.u);
            }
        });
        this.A.setDuration(200L);
        this.A.addListener(new d());
        this.A.start();
    }

    public final void y() {
        if (this.k == null) {
            return;
        }
        if (this.k.getView().getVisibility() == (B() ? 0 : 4)) {
            return;
        }
        View view = this.k.getView();
        a aVar = this.D;
        view.removeCallbacks(aVar);
        this.k.getView().postOnAnimation(aVar);
    }

    public final void z(int i, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(i, -this.l);
        int iMin = Math.min(i2, this.n);
        int iMin2 = Math.min(i3, this.l + iMax);
        if (iMax == this.q && iMin == this.r && iMin2 == this.s && i4 == this.t && i5 == this.u) {
            return;
        }
        this.q = iMax;
        this.r = iMin;
        this.t = i4;
        this.u = i5;
        this.s = iMin2;
        this.i.r(Boolean.valueOf(iMin2 == this.m && this.n - u() == this.o));
        if (this.g != 1) {
            int i6 = this.l;
            if (i6 == 0) {
                this.v = 1.0f;
            } else {
                this.v = Math.abs(this.q / i6);
            }
        }
        TraceEvent traceEventH0 = TraceEvent.H0("BrowserControlsManager.notifyControlOffsetChanged", null);
        try {
            y();
            if (B()) {
                this.k.getView().setTranslationY(this.q);
            }
            boolean zB = B();
            Iterator<kj.a> it = this.x.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((kj.a) aVar.next()).c(zB, this.q, this.t, u());
                }
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
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
}
