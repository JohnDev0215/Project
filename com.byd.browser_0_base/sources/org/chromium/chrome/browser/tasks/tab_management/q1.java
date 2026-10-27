package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import defpackage.a53;
import defpackage.bq;
import defpackage.c01;
import defpackage.d92;
import defpackage.ef2;
import defpackage.eu1;
import defpackage.ew;
import defpackage.fj;
import defpackage.g13;
import defpackage.h21;
import defpackage.i31;
import defpackage.if2;
import defpackage.j61;
import defpackage.jf2;
import defpackage.jk0;
import defpackage.kw;
import defpackage.m61;
import defpackage.n3;
import defpackage.n61;
import defpackage.oj2;
import defpackage.p40;
import defpackage.qo;
import defpackage.y13;
import defpackage.y43;
import defpackage.y51;
import defpackage.yp;
import defpackage.zj;
import defpackage.zx1;
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
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TabSwitcherLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q1 extends y51 {
    public final a A;
    public final ViewGroup B;
    public final ef2 C;
    public final n1.c D;
    public final m61 E;
    public boolean F;
    public float G;
    public int H;
    public long I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f81J;
    public long K;
    public int L;
    public boolean M;
    public AnimatorSet t;
    public boolean u;
    public p40 v;
    public g13 w;
    public final n1 x;
    public final i31 y;
    public final n1.a z;

    /* JADX INFO: compiled from: TabSwitcherLayout.java */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ boolean e;

        public b(boolean z) {
            this.e = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            q1 q1Var = q1.this;
            q1Var.t = null;
            q1Var.z.n().setY(0.0f);
            q1Var.g();
            q1Var.getClass();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            q1 q1Var = q1.this;
            q1Var.z.n().setVisibility(0);
            q1Var.z.Z0(this.e);
            n1.a aVar = q1Var.z;
            aVar.i(aVar.n());
        }
    }

    /* JADX INFO: compiled from: TabSwitcherLayout.java */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            q1 q1Var = q1.this;
            q1Var.t = null;
            q1Var.z.g(true);
            q1Var.z.n().setVisibility(8);
            q1Var.getClass();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            q1 q1Var = q1.this;
            q1Var.z.t1();
            q1Var.z.i(null);
        }
    }

    public q1(Context context, n61 n61Var, j61 j61Var, n1 n1Var, i31 i31Var, ViewGroup viewGroup, ef2 ef2Var) {
        super(context, n61Var, j61Var);
        m61 m61VarC = c(-1, false);
        this.E = m61VarC;
        m61VarC.l(m61.C, true);
        this.x = n1Var;
        n1.a aVarE = n1Var.e();
        this.z = aVarE;
        n1Var.setOnTabSelectingListener(new c01(25, this));
        this.D = n1Var.s();
        this.y = i31Var;
        this.B = viewGroup;
        this.C = ef2Var;
        a aVar = new a(context);
        this.A = aVar;
        aVarE.u0(aVar);
    }

    public static void P(q1 q1Var, boolean z) {
        int i = q1Var.H - q1Var.L;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - q1Var.I;
        int iB = (int) (q1Var.D.b() - q1Var.I);
        float f = (i * 1000.0f) / jElapsedRealtime;
        String str = z ? ".Shrink" : ".Expand";
        oj2.Q((int) f, "GridTabSwitcher.FramePerSecond".concat(str));
        oj2.b0(q1Var.K, "GridTabSwitcher.MaxFrameInterval".concat(str));
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
        n1.c cVar = this.D;
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherLayout.Show.TabSwitcher", null);
        try {
            super.H(j, z);
            this.y.a(5);
            m61 m61VarC = c(this.k.k(), this.k.r());
            m61VarC.v();
            int i = 1;
            boolean z2 = false;
            this.o = new m61[]{m61VarC};
            boolean zT = cVar.t();
            boolean z3 = this.k.z().getCount() == 0;
            if (z && !z3) {
                z2 = true;
            }
            if (DeviceFormFactor.a(this.i)) {
                R(z2);
            } else {
                this.v = new p40(this, i, z2, zT);
                cVar.g(new eu1(24, this));
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
    public final void I(int i, boolean z) {
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherLayout.Hide.TabSwitcher", null);
        try {
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
            if (DeviceFormFactor.a(this.i)) {
                S();
            } else {
                this.z.g(!Q());
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
        if (this.w == null) {
            this.w = new g13();
        }
        g13 g13Var = this.w;
        Context context = this.i;
        boolean zQ = Q();
        n1.c cVar = this.D;
        g13Var.e(context, rectF, this, tabContentManager, resourceManager, fjVar, zQ ? cVar.c() : 0, this.G, cVar.r());
        this.H++;
        if (this.f81J != 0) {
            this.K = Math.max(this.K, SystemClock.elapsedRealtime() - this.f81J);
        }
        this.f81J = SystemClock.elapsedRealtime();
    }

    public final boolean Q() {
        if (DeviceFormFactor.a(this.i)) {
            return false;
        }
        return oj2.H();
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [T, android.view.ViewGroup] */
    public final void R(boolean z) {
        h();
        ef2 ef2Var = this.C;
        if (ef2Var != null) {
            HashMap mapE = PropertyModel.e(jf2.m);
            PropertyModel.l<View> lVar = jf2.c;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = this.B;
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
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.z.n(), (Property<ViewGroup, Float>) View.TRANSLATION_Y, r2.n().getHeight(), 0.0f);
        objectAnimatorOfFloat.setInterpolator(h21.c);
        objectAnimatorOfFloat.setDuration(300L);
        AnimatorSet animatorSet = new AnimatorSet();
        this.t = animatorSet;
        animatorSet.play(objectAnimatorOfFloat);
        this.t.addListener(new b(z));
        this.t.start();
    }

    public final void S() {
        h();
        ef2 ef2Var = this.C;
        if (ef2Var != null) {
            if2 if2Var = ef2Var.b;
            if (if2Var.f != null) {
                if2Var.a(350, true);
            }
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.z.n(), (Property<ViewGroup, Float>) View.TRANSLATION_Y, 0.0f, r2.n().getHeight());
        objectAnimatorOfFloat.setInterpolator(h21.b);
        objectAnimatorOfFloat.setDuration(300L);
        AnimatorSet animatorSet = new AnimatorSet();
        this.t = animatorSet;
        animatorSet.play(objectAnimatorOfFloat);
        this.t.addListener(new c());
        this.t.start();
    }

    @Override // defpackage.y51
    public final boolean b() {
        return (oj2.C(this.i) && qo.f().c() && !DeviceFormFactor.a(this.i)) ? false : true;
    }

    @Override // defpackage.y51
    public final void d() {
        this.z.b1(this.A);
    }

    @Override // defpackage.y51
    public final void f() {
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherLayout.DoneHiding", null);
        try {
            super.f();
            n3.X0("MobileExitStackView");
            this.y.b(5);
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
        TraceEvent traceEventH0 = TraceEvent.H0("TabSwitcherLayout.DoneShowing", null);
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
        p40 p40Var = this.v;
        if (p40Var != null) {
            this.v = null;
            p40Var.run();
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
        return this.z.a();
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

    /* JADX INFO: compiled from: TabSwitcherLayout.java */
    public class a implements n1.d {
        public final /* synthetic */ Context e;

        public a(Context context) {
            this.e = context;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void d() {
            q1 q1Var = q1.this;
            boolean zQ = q1Var.Q();
            int i = 0;
            n1.c cVar = q1Var.D;
            if (!zQ) {
                cVar.p();
                q1Var.u = false;
                q1Var.f();
                return;
            }
            Rect rectN = cVar.n(true);
            m61 m61Var = q1Var.o[0];
            q1Var.h();
            ew ewVarJ = q1Var.j();
            ArrayList arrayList = new ArrayList(5);
            PropertyModel.o oVar = m61.g;
            float fWidth = rectN.width();
            float f = q1Var.e;
            float f2 = q1Var.r;
            jk0 jk0Var = h21.e;
            arrayList.add(kw.j(ewVarJ, m61Var, oVar, fWidth / (f * f2), 1.0f, 300L, jk0Var));
            arrayList.add(kw.j(ewVarJ, m61Var, m61.h, rectN.left / f2, 0.0f, 300L, jk0Var));
            arrayList.add(kw.j(ewVarJ, m61Var, m61.i, rectN.top / f2, 0.0f, 300L, jk0Var));
            arrayList.add(kw.j(ewVarJ, m61Var, m61.w, oj2.G() ? Math.min(q1Var.e / TabUtils.c(q1Var.i), m61Var.t()) : q1Var.e, m61Var.t(), 300L, jk0Var));
            kw kwVarE = kw.e(ewVarJ, 1.0f, 0.0f, 150L, new y43(q1Var, i));
            kwVarE.i = h21.d;
            arrayList.add(kwVarE);
            AnimatorSet animatorSet = new AnimatorSet();
            q1Var.t = animatorSet;
            animatorSet.playTogether(arrayList);
            q1Var.t.addListener(new a53(q1Var));
            q1Var.L = q1Var.H;
            q1Var.I = SystemClock.elapsedRealtime();
            q1Var.f81J = SystemClock.elapsedRealtime();
            q1Var.K = 0L;
            q1Var.t.start();
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void e() {
            q1 q1Var = q1.this;
            q1Var.M = true;
            if (!DeviceFormFactor.a(this.e)) {
                q1Var.g();
            }
            if (q1Var.Q()) {
                new Handler().postDelayed(new d92(10, this), 300L);
            } else {
                new Handler().postDelayed(new zx1(20, this), 300L);
            }
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void s() {
            q1.this.M = false;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
        public final void k() {
        }
    }
}
