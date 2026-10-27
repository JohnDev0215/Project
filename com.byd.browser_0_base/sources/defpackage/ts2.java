package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import defpackage.nw;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: StaticLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class ts2 extends y51 {
    public final Handler A;
    public boolean B;
    public y13 C;
    public rs2 D;
    public ss2 E;
    public final kj F;
    public final TabContentManager G;
    public final ew H;
    public final nw2<xd3> I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f98J;
    public final float K;
    public final boolean t;
    public final Context u;
    public final g61 v;
    public final PropertyModel w;
    public nw x;
    public zs2 y;
    public final a z;

    /* JADX INFO: compiled from: StaticLayout.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: ts2$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: StaticLayout.java */
        public class C0168a extends vl {
            public C0168a() {
            }

            @Override // defpackage.vl
            public final void b() {
                ts2.this.R();
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ts2 ts2Var = ts2.this;
            ts2Var.B = false;
            ew ewVar = ts2Var.H;
            PropertyModel propertyModel = ts2Var.w;
            PropertyModel.o oVar = m61.q;
            kw.i(ewVar, propertyModel, oVar, propertyModel.g(oVar), 1.0f, 500L).start();
            ew ewVar2 = ts2Var.H;
            PropertyModel.o oVar2 = m61.x;
            kw kwVarI = kw.i(ewVar2, propertyModel, oVar2, propertyModel.g(oVar2), 0.0f, 500L);
            kwVarI.addListener(new C0168a());
            kwVarI.start();
            propertyModel.l(m61.A, false);
        }
    }

    public ts2(Context context, h61 h61Var, j61 j61Var, g61 g61Var, nw.a aVar, y13 y13Var, TabContentManager tabContentManager, kj kjVar, nw2 nw2Var) {
        super(context, h61Var, j61Var);
        this.u = context;
        this.t = DeviceFormFactor.a(context);
        this.v = g61Var;
        this.G = tabContentManager;
        this.C = y13Var;
        this.D = new rs2(this, y13Var);
        this.E = new ss2(this, y13Var);
        PropertyModel.b bVar = new PropertyModel.b(m61.R);
        bVar.c(m61.e, -1);
        bVar.f(m61.g, 1.0f);
        bVar.f(m61.h, 0.0f);
        bVar.f(m61.i, 0.0f);
        bVar.f(m61.j, 0.0f);
        bVar.f(m61.k, 0.0f);
        bVar.f(m61.q, 1.0f);
        bVar.f(m61.x, 0.0f);
        bVar.f(m61.y, 1.0f);
        PropertyModel propertyModelA = bVar.a();
        this.w = propertyModelA;
        this.H = h61Var.G;
        this.I = nw2Var;
        this.A = new Handler();
        this.z = new a();
        this.B = false;
        this.K = 1.0f / context.getResources().getDisplayMetrics().density;
        this.F = kjVar;
        propertyModelA.m(m61.Q, kjVar.f());
        kjVar.o(new qs2(this));
        zs2 zs2Var = new zs2();
        this.y = zs2Var;
        N.M9WjiX4q(zs2Var.f, zs2Var, tabContentManager);
        this.x = new nw(propertyModelA, this.y, new zf(2), aVar);
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        super.H(j, z);
        this.f98J = true;
        Tab tabI = this.C.i();
        if (tabI == null) {
            return;
        }
        P(tabI);
    }

    @Override // defpackage.y51
    public final void J() {
        if (this.w.k(m61.A) && this.B) {
            Handler handler = this.A;
            a aVar = this.z;
            handler.removeCallbacks(aVar);
            aVar.run();
        }
    }

    @Override // defpackage.y51
    public final void L() {
        O(this.w);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void P(Tab tab) {
        PropertyModel propertyModel = this.w;
        PropertyModel.p pVar = m61.e;
        int iH = propertyModel.h(pVar);
        int id = tab.getId();
        a aVar = this.z;
        Handler handler = this.A;
        if (iH == id && !propertyModel.k(m61.A)) {
            handler.removeCallbacks(aVar);
            propertyModel.m(m61.x, 0.0f);
            propertyModel.m(m61.q, 1.0f);
            this.B = false;
            R();
            return;
        }
        propertyModel.n(pVar, tab.getId());
        propertyModel.l(m61.f, tab.isIncognito());
        PropertyModel.o oVar = m61.t;
        float width = this.v.getWidth();
        float f = this.K;
        propertyModel.m(oVar, width * f);
        propertyModel.m(m61.u, r2.getHeight() * f);
        propertyModel.m(m61.v, r2.getWidth() * f);
        propertyModel.m(m61.w, r2.getHeight() * f);
        Q(tab);
        if (!propertyModel.k(m61.A)) {
            handler.removeCallbacks(aVar);
            propertyModel.m(m61.x, 0.0f);
            propertyModel.m(m61.q, 1.0f);
            this.B = false;
            return;
        }
        handler.removeCallbacks(aVar);
        propertyModel.m(m61.x, 1.0f);
        propertyModel.m(m61.q, 0.0f);
        this.B = true;
        handler.postDelayed(aVar, 2000L);
    }

    public final void Q(Tab tab) {
        PropertyModel propertyModel = this.w;
        PropertyModel.p pVar = m61.e;
        if (propertyModel.h(pVar) != tab.getId()) {
            return;
        }
        nw2<xd3> nw2Var = this.I;
        xd3 xd3Var = nw2Var.get();
        PropertyModel.p pVar2 = m61.M;
        xd3Var.getClass();
        propertyModel.n(pVar2, xd3.e(tab));
        propertyModel.n(m61.N, xd3Var.f(tab));
        propertyModel.m(m61.P, nw2Var.get().g(tab));
        PropertyModel.n nVar = m61.A;
        propertyModel.l(nVar, (tab.isFrozen() || tab.f()) && !jk1.h(tab.getUrl(), tab.isIncognito()));
        propertyModel.n(m61.O, n3.v0(this.u, tab, nw2Var.get().c(tab.j(), tab)));
        propertyModel.l(m61.B, (tab.c() == null || hd2.z1(tab) || (tab.isNativePage() || tab.getUrl().i().equals("chrome-native"))) ? false : true);
        if (!propertyModel.k(nVar)) {
            R();
            return;
        }
        int iH = propertyModel.h(pVar);
        List listSingletonList = Collections.singletonList(Integer.valueOf(iH));
        TabContentManager tabContentManager = this.G;
        if (tabContentManager != null) {
            tabContentManager.g(iH, listSingletonList);
        }
    }

    public final void R() {
        PropertyModel propertyModel = this.w;
        boolean zK = propertyModel.k(m61.B);
        TabContentManager tabContentManager = this.G;
        if (zK) {
            List listEmptyList = Collections.emptyList();
            int iH = propertyModel.h(m61.e);
            if (tabContentManager != null) {
                tabContentManager.g(iH, listEmptyList);
                return;
            }
            return;
        }
        int iH2 = propertyModel.h(m61.e);
        List listSingletonList = Collections.singletonList(Integer.valueOf(iH2));
        if (tabContentManager != null) {
            tabContentManager.g(iH2, listSingletonList);
        }
    }

    @Override // defpackage.y51
    public final void d() {
        zs2 zs2Var = this.y;
        if (zs2Var != null) {
            zs2Var.destroy();
            this.y = null;
        }
        nw nwVar = this.x;
        if (nwVar != null) {
            nwVar.b.d(nwVar.e);
            nwVar.d.d(nwVar.f);
            this.x = null;
        }
        if (this.C != null) {
            this.D.a();
            this.E.destroy();
        }
    }

    @Override // defpackage.y51
    public final void f() {
        super.f();
        this.f98J = false;
    }

    @Override // defpackage.y51
    public final void g() {
        super.g();
        Tab tabI = this.C.i();
        if (tabI == null || !this.f98J || tabI.getView() == null) {
            return;
        }
        tabI.getView().requestFocus();
    }

    @Override // defpackage.y51
    public final EventFilter k() {
        return null;
    }

    @Override // defpackage.y51
    public final int m() {
        return 1;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.y;
    }

    @Override // defpackage.y51
    public final int o() {
        return 2;
    }

    @Override // defpackage.y51
    public final boolean p() {
        return this.t;
    }

    @Override // defpackage.y51
    public final boolean q() {
        super.q();
        return this.t;
    }

    @Override // defpackage.y51
    public final void G(y13 y13Var, TabContentManager tabContentManager) {
    }

    @Override // defpackage.y51
    public final void M(RectF rectF, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar) {
    }
}
