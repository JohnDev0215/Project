package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ZSignedDigitR2LMultiplier extends AbstractECMultiplier {
    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int iBitLength = bigInteger.bitLength();
        int lowestSetBit = bigInteger.getLowestSetBit();
        ECPoint eCPointTimesPow2 = eCPoint.timesPow2(lowestSetBit);
        while (true) {
            lowestSetBit++;
            if (lowestSetBit >= iBitLength) {
                return infinity.add(eCPointTimesPow2);
            }
            infinity = infinity.add(bigInteger.testBit(lowestSetBit) ? eCPointTimesPow2 : eCPointTimesPow2.negate());
            eCPointTimesPow2 = eCPointTimesPow2.twice();
        }
    }
}
