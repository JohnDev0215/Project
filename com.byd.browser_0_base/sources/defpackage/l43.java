package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ds2;
import defpackage.kw;
import defpackage.lr2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l43 extends y51 {
    public final ViewGroup A;
    public final ef2 B;
    public n1.c C;
    public n1.c D;
    public final m61 E;
    public boolean F;
    public float G;
    public int H;
    public long I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f44J;
    public long K;
    public int L;
    public boolean M;
    public AnimatorSet N;
    public AnimatorSet t;
    public boolean u;
    public j43 v;
    public g13 w;
    public final lr2 x;
    public final i31 y;
    public final a z;

    public l43(Context context, n61 n61Var, j61 j61Var, lr2 lr2Var, i31 i31Var, ViewGroup viewGroup, ef2 ef2Var) {
        super(context, n61Var, j61Var);
        m61 m61VarC = c(-1, false);
        this.E = m61VarC;
        m61VarC.l(m61.C, true);
        this.x = lr2Var;
        lr2Var.setOnTabSelectingListener(new gx(27, this));
        this.y = i31Var;
        this.A = viewGroup;
        this.B = ef2Var;
        a aVar = new a(context);
        this.z = aVar;
        lr2Var.p(aVar);
    }

    public static void P(l43 l43Var, boolean z) {
        int i = l43Var.H - l43Var.L;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - l43Var.I;
        int iB = (int) ((l43Var.x.C() == 1 ? l43Var.Q().b() : l43Var.R().b()) - l43Var.I);
        float f = (i * 1000.0f) / jElapsedRealtime;
        String str = z ? ".Shrink" : ".Expand";
        oj2.Q((int) f, "GridTabSwitcher.FramePerSecond".concat(str));
        oj2.b0(l43Var.K, "GridTabSwitcher.MaxFrameInterval".concat(str));
        oj2.b0(iB, "GridTabSwitcher.DirtySpan".concat(str));
    }

    @Override // defpackage.y51
    public final boolean D() {
        return this.t == null && !this.u;
    }

    @Override // defpackage.y51
    public final void G(y13 y13Var, TabContentManager tabContentManager) {
        super.G(y13Var, tabContentManager);
        g13 g13Var = this.w;
        if (g13Var != null) {
            g13Var.g = y13Var;
        }
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        if (this.x.x()) {
            TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceLayout.Show.StartSurface", null);
            try {
                T(j, z, true);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
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
        TraceEvent traceEventH02 = TraceEvent.H0("StartSurfaceLayout.Show.TabSwitcher", null);
        try {
            T(j, z, false);
            if (traceEventH02 != null) {
                traceEventH02.close();
            }
        } catch (Throwable th2) {
            if (traceEventH02 != null) {
                try {
                    traceEventH02.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    @Override // defpackage.y51
    public final void I(int i, boolean z) {
        int iT = this.x.t();
        ds2.a.a.g = false;
        if (iT == 1) {
            TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceLayout.Hide.StartSurface", null);
            try {
                U(i, z);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                    return;
                }
                return;
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
        TraceEvent traceEventH02 = TraceEvent.H0("StartSurfaceLayout.Hide.TabSwitcher", null);
        try {
            U(i, z);
            if (traceEventH02 != null) {
                traceEventH02.close();
            }
        } catch (Throwable th2) {
            if (traceEventH02 != null) {
                try {
                    traceEventH02.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    @Override // defpackage.y51
    public final void L() {
        if (this.w == null) {
            this.w = new g13();
        }
        m61[] m61VarArr = this.o;
        if (m61VarArr != null && O(m61VarArr[0])) {
            E();
        }
    }

    @Override // defpackage.y51
    public final void M(RectF rectF, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar) {
        n1.c cVarQ;
        if (this.w == null) {
            this.w = new g13();
        }
        lr2 lr2Var = this.x;
        if (lr2Var.t() == 0) {
            cVarQ = this.C;
            if (cVarQ == null) {
                cVarQ = this.D;
            }
        } else {
            cVarQ = lr2Var.x() ? Q() : R();
        }
        this.w.e(this.i, rectF, this, tabContentManager, resourceManager, fjVar, S() ? cVarQ.c() : 0, this.G, cVarQ.r());
        this.H++;
        if (this.f44J != 0) {
            this.K = Math.max(this.K, SystemClock.elapsedRealtime() - this.f44J);
        }
        this.f44J = SystemClock.elapsedRealtime();
    }

    public final n1.c Q() {
        if (this.D == null) {
            this.D = this.x.G();
        }
        return this.D;
    }

    public final n1.c R() {
        if (this.C == null) {
            this.C = this.x.A();
        }
        return this.C;
    }

    public final boolean S() {
        if (DeviceFormFactor.a(this.i)) {
            return false;
        }
        return oj2.H();
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [T, android.view.ViewGroup] */
    public final void T(long j, boolean z, boolean z2) {
        boolean zT;
        super.H(j, z);
        if (!StartSurfaceConfiguration.a()) {
            this.y.a(5);
        }
        lr2 lr2Var = this.x;
        lr2Var.o();
        m61 m61VarC = c(this.k.k(), this.k.r());
        m61VarC.v();
        this.o = new m61[]{m61VarC};
        if (z2) {
            zT = Q().t();
        } else {
            lr2Var.w();
            zT = R().t();
        }
        boolean z3 = (!z || (this.k.z().getCount() == 0) || z2) ? false : true;
        if (!DeviceFormFactor.a(this.i)) {
            if (z2) {
                lr2Var.r(z3);
                return;
            }
            if (lr2Var.t() != 4) {
                lr2Var.z();
            }
            this.v = new j43(this, z3, z2, zT);
            R().g(new d92(22, this));
            return;
        }
        h();
        ef2 ef2Var = this.B;
        if (ef2Var != null) {
            HashMap mapE = PropertyModel.e(jf2.m);
            PropertyModel.l<View> lVar = jf2.c;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = this.A;
            mapE.put(lVar, gVar);
            PropertyModel.h hVar = jf2.d;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = false;
            mapE.put(hVar, aVar);
            if (bq.f0.a()) {
                int iB = yp.b(this.i, this.k.r());
                PropertyModel.h hVar2 = jf2.b;
                PropertyModel.a aVar2 = new PropertyModel.a();
                aVar2.a = true;
                mapE.put(hVar2, aVar2);
                PropertyModel.p pVar = jf2.h;
                PropertyModel.d dVar = new PropertyModel.d();
                dVar.a = iB;
                mapE.put(pVar, dVar);
            }
            ef2Var.a(new PropertyModel(mapE));
        }
        if (!z3) {
            lr2Var.n().setVisibility(0);
            lr2Var.r(false);
            lr2Var.i(lr2Var.n());
            lr2Var.n().setY(0.0f);
            g();
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(lr2Var.n(), (Property<ViewGroup, Float>) View.TRANSLATION_Y, lr2Var.n().getHeight(), 0.0f);
        objectAnimatorOfFloat.setInterpolator(h21.c);
        objectAnimatorOfFloat.setDuration(300L);
        AnimatorSet animatorSet = new AnimatorSet();
        this.t = animatorSet;
        animatorSet.play(objectAnimatorOfFloat);
        this.t.addListener(new o43(this));
        this.t.start();
    }

    public final void U(int i, boolean z) {
        super.I(i, z);
        if (i == -1) {
            i = this.k.k();
        }
        m61 m61VarC = c(i, this.k.r());
        m61VarC.v();
        ArrayList arrayList = new ArrayList();
        arrayList.add(m61VarC);
        if (i != this.k.k()) {
            m61 m61VarC2 = c(this.k.k(), this.k.r());
            m61VarC2.m(m61.g, 0.0f);
            m61VarC2.v();
            arrayList.add(m61VarC2);
        }
        this.o = (m61[]) arrayList.toArray(new m61[0]);
        K(new LinkedList(Arrays.asList(Integer.valueOf(i))));
        this.u = true;
        boolean zA = DeviceFormFactor.a(this.i);
        lr2 lr2Var = this.x;
        if (!zA) {
            lr2Var.g(!S());
            return;
        }
        h();
        ef2 ef2Var = this.B;
        if (ef2Var != null) {
            if2 if2Var = ef2Var.b;
            if (if2Var.f != null) {
                if2Var.a(350, true);
            }
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(lr2Var.n(), (Property<ViewGroup, Float>) View.TRANSLATION_Y, 0.0f, lr2Var.n().getHeight());
        objectAnimatorOfFloat.setInterpolator(h21.b);
        objectAnimatorOfFloat.setDuration(300L);
        AnimatorSet animatorSet = new AnimatorSet();
        this.t = animatorSet;
        animatorSet.play(objectAnimatorOfFloat);
        this.t.addListener(new p43(this));
        this.t.start();
    }

    @Override // defpackage.y51
    public final boolean b() {
        return (oj2.C(this.i) && qo.f().c() && !DeviceFormFactor.a(this.i)) ? false : true;
    }

    @Override // defpackage.y51
    public final void d() {
        lr2 lr2Var = this.x;
        if (lr2Var != null) {
            lr2Var.H(this.z);
        }
    }

    @Override // defpackage.y51
    public final void f() {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceLayout.DoneHiding", null);
        try {
            super.f();
            n3.X0("MobileExitStackView");
            if (!StartSurfaceConfiguration.a()) {
                this.y.b(5);
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

    @Override // defpackage.y51
    public final void g() {
        TraceEvent traceEventH0 = TraceEvent.H0("StartSurfaceLayout.DoneShowing", null);
        try {
            if (!this.M) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } else {
                super.g();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
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

    @Override // defpackage.y51
    public final void h() {
        j43 j43Var = this.v;
        if (j43Var != null) {
            this.v = null;
            j43Var.run();
        }
        AnimatorSet animatorSet = this.t;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return;
        }
        this.t.end();
    }

    @Override // defpackage.y51
    public final EventFilter k() {
        return null;
    }

    @Override // defpackage.y51
    public final m61 l(int i) {
        return this.E;
    }

    @Override // defpackage.y51
    public final int m() {
        return 2;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.w;
    }

    @Override // defpackage.y51
    public final boolean p() {
        return true;
    }

    @Override // defpackage.y51
    public final boolean q() {
        return true;
    }

    @Override // defpackage.y51
    public final boolean t() {
        return this.x.a();
    }

    @Override // defpackage.y51
    public final void u() {
        if (this.F) {
            return;
        }
        this.F = true;
        this.x.d();
        if (this.w == null) {
            this.w = new g13();
        }
        this.w.g = this.k;
    }

    @Override // defpackage.y51
    public final void y(int i, int i2, boolean z, boolean z2, float f, float f2) {
        if (!z2 || z) {
            return;
        }
        lr2 lr2Var = this.x;
        if (lr2Var.t() != 1) {
            return;
        }
        TasksView tasksViewV = lr2Var.v();
        AnimatorSet animatorSet = this.N;
        if (animatorSet != null && animatorSet.isStarted()) {
            this.N.end();
        }
        AnimatorSet animatorSetC = ec.c(this, tasksViewV, f, f2, this.j == 1);
        this.N = animatorSetC;
        animatorSetC.start();
    }

    /* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
    public class a implements lr2.c {
        public final /* synthetic */ Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // lr2.c
        public final void d() {
            final l43 l43Var = l43.this;
            boolean zS = l43Var.S();
            lr2 lr2Var = l43Var.x;
            if (zS) {
                if (!(lr2Var.C() == 1)) {
                    Rect rectN = lr2Var.C() == 1 ? l43Var.Q().n(true) : l43Var.R().n(true);
                    m61 m61Var = l43Var.o[0];
                    l43Var.h();
                    ew ewVarJ = l43Var.j();
                    ArrayList arrayList = new ArrayList(5);
                    PropertyModel.o oVar = m61.g;
                    float fWidth = rectN.width();
                    float f = l43Var.e;
                    float f2 = l43Var.r;
                    jk0 jk0Var = h21.e;
                    arrayList.add(kw.j(ewVarJ, m61Var, oVar, fWidth / (f * f2), 1.0f, 300L, jk0Var));
                    arrayList.add(kw.j(ewVarJ, m61Var, m61.h, rectN.left / f2, 0.0f, 300L, jk0Var));
                    arrayList.add(kw.j(ewVarJ, m61Var, m61.i, rectN.top / f2, 0.0f, 300L, jk0Var));
                    arrayList.add(kw.j(ewVarJ, m61Var, m61.w, oj2.G() ? Math.min(l43Var.e / TabUtils.c(l43Var.i), m61Var.t()) : l43Var.e, m61Var.t(), 300L, jk0Var));
                    kw kwVarE = kw.e(ewVarJ, 1.0f, 0.0f, 150L, new kw.a() { // from class: i43
                        @Override // kw.a
                        public final void a(kw kwVar) {
                            l43 l43Var2 = l43Var;
                            l43Var2.getClass();
                            l43Var2.G = kwVar.b();
                        }
                    });
                    kwVarE.i = h21.d;
                    arrayList.add(kwVarE);
                    AnimatorSet animatorSet = new AnimatorSet();
                    l43Var.t = animatorSet;
                    animatorSet.playTogether(arrayList);
                    l43Var.t.addListener(new n43(l43Var));
                    l43Var.L = l43Var.H;
                    l43Var.I = SystemClock.elapsedRealtime();
                    l43Var.f44J = SystemClock.elapsedRealtime();
                    l43Var.K = 0L;
                    l43Var.t.start();
                    return;
                }
            }
            if (zb2.d(l43Var.i)) {
                lr2Var.e();
            } else {
                l43Var.R().p();
            }
            l43Var.u = false;
            l43Var.f();
        }

        @Override // lr2.c
        public final void e() {
            l43 l43Var = l43.this;
            l43Var.M = true;
            if (!DeviceFormFactor.a(this.a)) {
                l43Var.g();
            }
            if (l43Var.S()) {
                new Handler().postDelayed(new es3(2, this), 300L);
            } else {
                new Handler().postDelayed(new ve3(6, this), 300L);
            }
        }

        @Override // lr2.c
        public final void h() {
            l43.this.M = false;
        }

        @Override // lr2.c
        public final void f() {
        }
    }
}
