package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class j extends a {
    @Override // g.a.d.b.a
    public h c(h hVar, BigInteger bigInteger) {
        d dVarI = hVar.i();
        if (bigInteger.bitLength() > l.a(dVarI)) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        k kVarB = l.b(hVar);
        f fVarA = kVarB.a();
        int iC = kVarB.c();
        int i2 = ((r1 + iC) - 1) / iC;
        h hVarV = dVarI.v();
        int i3 = iC * i2;
        int[] iArrO = g.a.d.d.m.o(i3, bigInteger);
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i2; i5++) {
            int iP = 0;
            for (int i6 = i4 - i5; i6 >= 0; i6 -= i2) {
                iP = (iP << 1) | g.a.d.d.m.p(iArrO, i6);
            }
            hVarV = hVarV.K(fVarA.a(iP));
        }
        return hVarV.a(kVarB.b());
    }
}
