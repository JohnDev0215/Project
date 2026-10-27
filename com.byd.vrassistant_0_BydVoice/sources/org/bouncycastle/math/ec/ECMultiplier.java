package org.bouncycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes4.dex */
interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger, PreCompInfo preCompInfo);
}
