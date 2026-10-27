package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.t0;
import g.a.b.j0.u0;
import g.a.b.j0.x;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class f implements g.a.b.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t0 f5206a;

    @Override // g.a.b.d
    public int a() {
        return (this.f5206a.c().b().a().u() + 7) / 8;
    }

    @Override // g.a.b.d
    public BigInteger b(i iVar) {
        if (g.a.f.g.d("org.bouncycastle.ec.disable_mqv")) {
            throw new IllegalStateException("ECMQV explicitly disabled");
        }
        u0 u0Var = (u0) iVar;
        b0 b0VarC = this.f5206a.c();
        x xVarB = b0VarC.b();
        if (!xVarB.equals(u0Var.b().b())) {
            throw new IllegalStateException("ECMQV public key components have wrong domain parameters");
        }
        g.a.d.b.h hVarA = c(xVarB, b0VarC, this.f5206a.a(), this.f5206a.b(), u0Var.b(), u0Var.a()).A();
        if (hVarA.u()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for MQV");
        }
        return hVarA.f().t();
    }

    public final g.a.d.b.h c(x xVar, b0 b0Var, b0 b0Var2, c0 c0Var, c0 c0Var2, c0 c0Var3) {
        BigInteger bigIntegerE = xVar.e();
        int iBitLength = (bigIntegerE.bitLength() + 1) / 2;
        BigInteger bigIntegerShiftLeft = g.a.d.b.c.f5471b.shiftLeft(iBitLength);
        g.a.d.b.d dVarA = xVar.a();
        g.a.d.b.h hVarA = g.a.d.b.b.a(dVarA, c0Var.c());
        g.a.d.b.h hVarA2 = g.a.d.b.b.a(dVarA, c0Var2.c());
        g.a.d.b.h hVarA3 = g.a.d.b.b.a(dVarA, c0Var3.c());
        BigInteger bigIntegerMod = b0Var.c().multiply(hVarA.f().t().mod(bigIntegerShiftLeft).setBit(iBitLength)).add(b0Var2.c()).mod(bigIntegerE);
        BigInteger bit = hVarA3.f().t().mod(bigIntegerShiftLeft).setBit(iBitLength);
        BigInteger bigIntegerMod2 = xVar.c().multiply(bigIntegerMod).mod(bigIntegerE);
        return g.a.d.b.b.q(hVarA2, bit.multiply(bigIntegerMod2).mod(bigIntegerE), hVarA3, bigIntegerMod2);
    }

    @Override // g.a.b.d
    public void init(i iVar) {
        this.f5206a = (t0) iVar;
    }
}
