package W2;

import B2.E;
import B2.F;
import B2.G;
import h2.AbstractC1343A;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class a implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f9716a;

    public a(b bVar) {
        this.f9716a = bVar;
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // B2.F
    public final E j(long j3) {
        b bVar = this.f9716a;
        BigInteger bigIntegerValueOf = BigInteger.valueOf((((long) bVar.o.f9746i) * j3) / 1000000);
        long j10 = bVar.f9718n;
        long j11 = bVar.f9717m;
        G g7 = new G(j3, AbstractC1343A.j((bigIntegerValueOf.multiply(BigInteger.valueOf(j10 - j11)).divide(BigInteger.valueOf(bVar.f9720q)).longValue() + j11) - 30000, bVar.f9717m, j10 - 1));
        return new E(g7, g7);
    }

    @Override // B2.F
    public final long l() {
        b bVar = this.f9716a;
        return (bVar.f9720q * 1000000) / ((long) bVar.o.f9746i);
    }
}
