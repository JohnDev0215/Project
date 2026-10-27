package b6;

import A7.v;
import Q5.C0591l1;
import S8.AbstractC0727y;
import S8.E;
import T.C0735d;
import T.C0738e0;
import T.S;
import androidx.lifecycle.J;
import androidx.lifecycle.a0;
import androidx.lifecycle.f0;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import d2.C1063a;
import k6.C1705m;
import q7.InterfaceC2189c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends f0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final W5.e f12234b;
    public final J c;
    public NavigationType d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f12235e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final X8.c f12236f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1063a f12237g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0591l1 f12238h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public InterfaceC2772a f12239i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public InterfaceC2772a f12240j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f12241k;

    public f(C1705m c1705m, W5.e eVar) {
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        this.f12234b = eVar;
        AbstractC0727y.w(a0.i(this), null, null, new d(c1705m, new v(), this, null), 3);
        new J();
        this.c = new J();
        this.d = NavigationType.MAIN;
        LoadingInterface$LoadingState loadingInterface$LoadingState = LoadingInterface$LoadingState.Loading;
        S s6 = S.f8224q;
        this.f12235e = C0735d.O(loadingInterface$LoadingState, s6);
        Z8.e eVar2 = E.f8084a;
        this.f12236f = AbstractC0727y.a(Z8.d.f11010n);
        this.f12237g = a0.i(this);
        this.f12238h = new C0591l1(this, 2);
        this.f12241k = C0735d.O(new g(0, 0), s6);
    }

    @Override // androidx.lifecycle.f0
    public void d() {
        AbstractC0727y.d(this.f12236f, null);
        AbstractC0727y.d(this.f12237g, null);
    }

    public final LoadingInterface$LoadingState e() {
        return (LoadingInterface$LoadingState) this.f12235e.getValue();
    }

    public abstract Object f(InterfaceC2189c interfaceC2189c);

    public void g() {
    }

    public final void h() {
        if (this.d != NavigationType.PLAYER) {
            this.f12234b.k();
        }
    }

    public final void i(boolean z6) {
        if (e() != LoadingInterface$LoadingState.Done || z6) {
            AbstractC0727y.w(this.f12237g, null, null, new e(this, null), 3);
        }
    }

    public void j() {
        l(LoadingInterface$LoadingState.Loading);
    }

    public final void k(int i7, int i10) {
        this.f12241k.setValue(new g(i7, i10));
    }

    public final void l(LoadingInterface$LoadingState loadingInterface$LoadingState) {
        A7.m.f(loadingInterface$LoadingState, "<set-?>");
        this.f12235e.setValue(loadingInterface$LoadingState);
    }

    public final void m(NavigationType navigationType) {
        A7.m.f(navigationType, "<set-?>");
        this.d = navigationType;
    }
}
