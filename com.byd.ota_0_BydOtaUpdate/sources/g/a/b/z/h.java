package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.j;
import g.a.b.j0.k;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class h implements g.a.b.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5210a = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.j0.g f5211b;

    @Override // g.a.b.d
    public int a() {
        return (this.f5211b.c().b().f().bitLength() + 7) / 8;
    }

    @Override // g.a.b.d
    public BigInteger b(i iVar) {
        g.a.b.j0.h hVar = (g.a.b.j0.h) iVar;
        j jVarC = this.f5211b.c();
        if (!this.f5211b.c().b().equals(hVar.b().b())) {
            throw new IllegalStateException("MQV public key components have wrong domain parameters");
        }
        if (this.f5211b.c().b().g() == null) {
            throw new IllegalStateException("MQV key domain parameters do not have Q set");
        }
        BigInteger bigIntegerC = c(jVarC.b(), jVarC, hVar.b(), this.f5211b.a(), this.f5211b.b(), hVar.a());
        if (bigIntegerC.equals(f5210a)) {
            throw new IllegalStateException("1 is not a valid agreement value for MQV");
        }
        return bigIntegerC;
    }

    public final BigInteger c(g.a.b.j0.i iVar, j jVar, k kVar, j jVar2, k kVar2, k kVar3) {
        BigInteger bigIntegerG = iVar.g();
        BigInteger bigIntegerPow = BigInteger.valueOf(2L).pow((bigIntegerG.bitLength() + 1) / 2);
        return kVar3.c().multiply(kVar.c().modPow(kVar3.c().mod(bigIntegerPow).add(bigIntegerPow), iVar.f())).modPow(jVar2.c().add(kVar2.c().mod(bigIntegerPow).add(bigIntegerPow).multiply(jVar.c())).mod(bigIntegerG), iVar.f());
    }

    @Override // g.a.b.d
    public void init(i iVar) {
        this.f5211b = (g.a.b.j0.g) iVar;
    }
}
