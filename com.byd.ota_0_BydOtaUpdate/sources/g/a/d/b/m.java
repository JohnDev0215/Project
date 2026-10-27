package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f5519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.a.d.b.b0.b f5520b;

    public m(d dVar, g.a.d.b.b0.b bVar) {
        if (dVar == null || dVar.x() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.f5519a = dVar;
        this.f5520b = bVar;
    }

    @Override // g.a.d.b.a
    public h c(h hVar, BigInteger bigInteger) {
        if (!this.f5519a.m(hVar.i())) {
            throw new IllegalStateException();
        }
        BigInteger[] bigIntegerArrB = this.f5520b.b(bigInteger.mod(hVar.i().x()));
        BigInteger bigInteger2 = bigIntegerArrB[0];
        BigInteger bigInteger3 = bigIntegerArrB[1];
        i iVarC = this.f5520b.c();
        return this.f5520b.a() ? b.d(hVar, bigInteger2, iVarC, bigInteger3) : b.c(hVar, bigInteger2, iVarC.a(hVar), bigInteger3);
    }
}
