package g.a.d.b.b0;

import g.a.d.b.i;
import g.a.d.b.q;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g.a.d.b.d f5458a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5459b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f5460c;

    public c(g.a.d.b.d dVar, d dVar2) {
        this.f5458a = dVar;
        this.f5459b = dVar2;
        this.f5460c = new q(dVar.n(dVar2.b()));
    }

    @Override // g.a.d.b.b0.a
    public boolean a() {
        return true;
    }

    @Override // g.a.d.b.b0.b
    public BigInteger[] b(BigInteger bigInteger) {
        int iC = this.f5459b.c();
        BigInteger bigIntegerD = d(bigInteger, this.f5459b.d(), iC);
        BigInteger bigIntegerD2 = d(bigInteger, this.f5459b.e(), iC);
        d dVar = this.f5459b;
        return new BigInteger[]{bigInteger.subtract(bigIntegerD.multiply(dVar.f()).add(bigIntegerD2.multiply(dVar.h()))), bigIntegerD.multiply(dVar.g()).add(bigIntegerD2.multiply(dVar.i())).negate()};
    }

    @Override // g.a.d.b.b0.a
    public i c() {
        return this.f5460c;
    }

    public BigInteger d(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        boolean z = bigInteger2.signum() < 0;
        BigInteger bigIntegerMultiply = bigInteger.multiply(bigInteger2.abs());
        boolean zTestBit = bigIntegerMultiply.testBit(i2 - 1);
        BigInteger bigIntegerShiftRight = bigIntegerMultiply.shiftRight(i2);
        if (zTestBit) {
            bigIntegerShiftRight = bigIntegerShiftRight.add(g.a.d.b.c.f5471b);
        }
        return z ? bigIntegerShiftRight.negate() : bigIntegerShiftRight;
    }
}
