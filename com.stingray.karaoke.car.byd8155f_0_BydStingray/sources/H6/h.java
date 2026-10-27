package h6;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import k6.C1691C;
import k6.C1705m;
import kotlin.Metadata;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lh6/h;", "Lb6/f;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class h extends b6.f {
    public final C1691C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final J5.c f15577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f0 f15578n;
    public final N o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(C1705m c1705m, C1691C c1691c, J5.c cVar, W5.e eVar) {
        super(c1705m, eVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(c1691c, "uxRestrictionManager");
        A7.m.f(cVar, "apiService");
        A7.m.f(eVar, "appNavigationManager");
        this.l = c1691c;
        this.f15577m = cVar;
        f0 f0VarC = AbstractC0828q.c("");
        this.f15578n = f0VarC;
        this.o = new N(f0VarC);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new g(this, null), 3);
        return m7.q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
    }
}
