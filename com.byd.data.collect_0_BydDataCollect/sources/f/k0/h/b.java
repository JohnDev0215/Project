package f.k0.h;

import f.b0;
import f.e0;
import f.g0;
import f.y;

/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b0 f5160a;

    public b(b0 b0Var) {
        this.f5160a = b0Var;
    }

    @Override // f.y
    public g0 a(y.a aVar) {
        f.k0.i.g gVar = (f.k0.i.g) aVar;
        e0 e0VarA = gVar.a();
        k kVarI = gVar.i();
        return gVar.h(e0VarA, kVarI, kVarI.k(aVar, !e0VarA.f().equals("GET")));
    }
}
