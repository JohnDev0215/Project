package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes4.dex */
public interface ElGamalPublicKey extends ElGamalKey, PublicKey {
    BigInteger getY();
}
