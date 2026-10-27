package k6;

import K5.C0372e;
import N1.C0420a;
import N1.InterfaceC0428i;
import S8.AbstractC0727y;
import S8.E;
import V8.AbstractC0828q;
import V8.N;
import V8.V;
import com.stingray.companion.CompanionManager;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B6.b f18292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CompanionManager f18293b;
    public final K5.t c;
    public final X8.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final N f18294e;

    public r(B6.b bVar, CompanionManager companionManager, K5.t tVar) {
        A7.m.f(bVar, "featureLogger");
        A7.m.f(companionManager, "companionManager");
        A7.m.f(tVar, "dataStoreManager");
        this.f18292a = bVar;
        this.f18293b = companionManager;
        this.c = tVar;
        Z8.e eVar = E.f8084a;
        X8.c cVarA = AbstractC0727y.a(Z8.d.f11010n);
        this.d = cVarA;
        this.f18294e = AbstractC0828q.m(new C0372e(((InterfaceC0428i) ((C0420a) tVar.a(tVar.f3657a)).l).d(), 3), cVarA, V.a(), Boolean.FALSE);
    }
}
