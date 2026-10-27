package org.bouncycastle.jce.interfaces;

import g.a.c.c.b;
import g.a.c.e.i;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface ElGamalPrivateKey extends b, DHPrivateKey {
    @Override // g.a.c.c.b
    /* synthetic */ i getParameters();

    BigInteger getX();
}
