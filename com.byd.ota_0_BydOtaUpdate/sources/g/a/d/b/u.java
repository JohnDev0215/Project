package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class u extends a {
    @Override // g.a.d.b.a
    public h c(h hVar, BigInteger bigInteger) {
        h hVarA;
        int iMax = Math.max(2, Math.min(16, d(bigInteger.bitLength())));
        v vVarM = w.m(hVar, iMax, true);
        h[] hVarArrA = vVarM.a();
        h[] hVarArrB = vVarM.b();
        int[] iArrD = w.d(iMax, bigInteger);
        h hVarV = hVar.i().v();
        int length = iArrD.length;
        if (length > 1) {
            length--;
            int i2 = iArrD[length];
            int i3 = i2 >> 16;
            int i4 = i2 & 65535;
            int iAbs = Math.abs(i3);
            h[] hVarArr = i3 < 0 ? hVarArrB : hVarArrA;
            if ((iAbs << 2) < (1 << iMax)) {
                byte b2 = n.E3[iAbs];
                int i5 = iMax - b2;
                hVarA = hVarArr[((1 << (iMax - 1)) - 1) >>> 1].a(hVarArr[(((iAbs ^ (1 << (b2 - 1))) << i5) + 1) >>> 1]);
                i4 -= i5;
            } else {
                hVarA = hVarArr[iAbs >>> 1];
            }
            hVarV = hVarA.I(i4);
        }
        while (length > 0) {
            length--;
            int i6 = iArrD[length];
            int i7 = i6 >> 16;
            hVarV = hVarV.K((i7 < 0 ? hVarArrB : hVarArrA)[Math.abs(i7) >>> 1]).I(i6 & 65535);
        }
        return hVarV;
    }

    public int d(int i2) {
        return w.j(i2);
    }
}
