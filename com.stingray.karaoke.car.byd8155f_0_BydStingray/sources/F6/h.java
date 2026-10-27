package f6;

import K5.C0372e;
import K5.t;
import K5.u;
import N1.C0420a;
import N1.InterfaceC0428i;
import N5.t0;
import V8.AbstractC0828q;
import V8.J;
import V8.N;
import V8.V;
import V8.c0;
import V8.f0;
import X5.C0864i0;
import androidx.lifecycle.a0;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.LoginInfo;
import d2.C1063a;
import java.util.List;
import java.util.Locale;
import k6.C1704l;
import k6.C1705m;
import kotlin.Metadata;
import m7.q;
import n7.AbstractC1999n;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lf6/h;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class h extends b6.f {
    public final W5.e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1704l f14617m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u f14618n;
    public final J5.h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final J5.c f14619p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final t0 f14620q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final N f14621r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final List f14622s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final f0 f14623t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final N f14624u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(C1705m c1705m, W5.e eVar, C1704l c1704l, u uVar, J5.h hVar, J5.c cVar, t0 t0Var, t tVar) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(c1704l, "driveModeManager");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(hVar, "loginManager");
        A7.m.f(cVar, "kcaService");
        A7.m.f(t0Var, "songQueueManagerCompanionListener");
        A7.m.f(tVar, "dataStoreManager");
        this.l = eVar;
        this.f14617m = c1704l;
        this.f14618n = uVar;
        this.o = hVar;
        this.f14619p = cVar;
        this.f14620q = t0Var;
        J j3 = new J(new C0372e(((InterfaceC0428i) ((C0420a) tVar.a(tVar.f3657a)).l).d(), 0), c1704l.f18277h, new C0864i0(3, null, 1));
        C1063a c1063aI = a0.i(this);
        c0 c0VarA = V.a();
        Boolean bool = Boolean.FALSE;
        this.f14621r = AbstractC0828q.m(j3, c1063aI, c0VarA, bool);
        this.f14622s = AbstractC1999n.D(StingrayKaraokeTabScreen.Homepage, StingrayKaraokeTabScreen.ExplorePage, StingrayKaraokeTabScreen.MyKaraokePage, StingrayKaraokeTabScreen.CompanionPage);
        f0 f0VarC = AbstractC0828q.c(bool);
        this.f14623t = f0VarC;
        this.f14624u = new N(f0VarC);
        m(NavigationType.MAIN);
        t0Var.c.addListener(t0Var);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        this.f14618n.f3659a.clear();
        this.f14619p.a();
        J5.h hVar = this.o;
        LoginInfo loginInfoA = hVar.a();
        hVar.d(loginInfoA != null ? loginInfoA.f13163a : null, false);
        CompanionManager.Companion.getClass();
        ((f0) CompanionManager._languageFlow).l(Locale.getDefault().getLanguage());
    }

    @Override // b6.f
    public final void j() {
        super.j();
        f0 f0Var = this.f14623t;
        Boolean bool = Boolean.FALSE;
        f0Var.getClass();
        f0Var.m(null, bool);
    }
}
