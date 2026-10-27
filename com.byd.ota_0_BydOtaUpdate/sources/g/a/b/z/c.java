package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c implements g.a.b.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b0 f5203a;

    @Override // g.a.b.d
    public int a() {
        return (this.f5203a.b().a().u() + 7) / 8;
    }

    @Override // g.a.b.d
    public BigInteger b(i iVar) {
        c0 c0Var = (c0) iVar;
        x xVarB = this.f5203a.b();
        if (!xVarB.equals(c0Var.b())) {
            throw new IllegalStateException("ECDH public key has wrong domain parameters");
        }
        BigInteger bigIntegerC = this.f5203a.c();
        g.a.d.b.h hVarA = g.a.d.b.b.a(xVarB.a(), c0Var.c());
        if (hVarA.u()) {
            throw new IllegalStateException("Infinity is not a valid public key for ECDH");
        }
        BigInteger bigIntegerC2 = xVarB.c();
        if (!bigIntegerC2.equals(g.a.d.b.c.f5471b)) {
            bigIntegerC = xVarB.d().multiply(bigIntegerC).mod(xVarB.e());
            hVarA = g.a.d.b.b.p(hVarA, bigIntegerC2);
        }
        g.a.d.b.h hVarA2 = hVarA.y(bigIntegerC).A();
        if (hVarA2.u()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for ECDH");
        }
        return hVarA2.f().t();
    }

    @Override // g.a.b.d
    public void init(i iVar) {
        this.f5203a = (b0) iVar;
    }
}
