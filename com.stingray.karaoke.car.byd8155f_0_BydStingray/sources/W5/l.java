package W5;

import T.C0761q;
import c6.C1034a;
import n3.C1945j;
import w.C2576g;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class l implements InterfaceC2787p {
    public final /* synthetic */ f6.h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f6.g f9839m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ e6.h f9840n;
    public final /* synthetic */ C1034a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ f6.o f9841p;

    public l(f6.h hVar, f6.g gVar, e6.h hVar2, C1034a c1034a, f6.o oVar) {
        this.l = hVar;
        this.f9839m = gVar;
        this.f9840n = hVar2;
        this.o = c1034a;
        this.f9841p = oVar;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        ((Number) obj4).intValue();
        A7.m.f((C2576g) obj, "$this$composable");
        A7.m.f((C1945j) obj2, "it");
        e6.h hVar = this.f9840n;
        C1034a c1034a = this.o;
        P8.n.e(this.l, this.f9839m, hVar, c1034a, this.f9841p, (C0761q) obj3, 0);
        return m7.q.f19267a;
    }
}
