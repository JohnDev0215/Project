package g.a.b.e0;

import g.a.b.j0.d0;
import g.a.b.j0.f0;
import g.a.b.j0.g0;
import g.a.b.j0.h0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m implements g.a.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d0 f4712g;

    @Override // g.a.b.c
    public g.a.b.b a() {
        e eVar = e.f4689a;
        f0 f0VarC = this.f4712g.c();
        g.a.b.j0.i iVar = new g.a.b.j0.i(f0VarC.c(), f0VarC.a(), null, f0VarC.b());
        BigInteger bigIntegerA = eVar.a(iVar, this.f4712g.a());
        return new g.a.b.b(new h0(eVar.b(iVar, bigIntegerA), f0VarC), new g0(bigIntegerA, f0VarC));
    }

    public void b(g.a.b.r rVar) {
        this.f4712g = (d0) rVar;
    }
}
