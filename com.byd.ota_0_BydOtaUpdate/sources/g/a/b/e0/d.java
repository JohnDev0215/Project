package g.a.b.e0;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class d implements g.a.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.j0.e f4688g;

    @Override // g.a.b.c
    public g.a.b.b a() {
        e eVar = e.f4689a;
        g.a.b.j0.i iVarC = this.f4688g.c();
        BigInteger bigIntegerA = eVar.a(iVarC, this.f4688g.a());
        return new g.a.b.b(new g.a.b.j0.k(eVar.b(iVarC, bigIntegerA), iVarC), new g.a.b.j0.j(bigIntegerA, iVarC));
    }

    public void b(g.a.b.r rVar) {
        this.f4688g = (g.a.b.j0.e) rVar;
    }
}
