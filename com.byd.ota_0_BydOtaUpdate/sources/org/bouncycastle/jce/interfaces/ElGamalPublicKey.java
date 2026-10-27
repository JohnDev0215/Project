package org.bouncycastle.jce.interfaces;

import g.a.c.c.b;
import g.a.c.e.i;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface ElGamalPublicKey extends b, DHPublicKey {
    @Override // g.a.c.c.b
    /* synthetic */ i getParameters();

    BigInteger getY();
}
