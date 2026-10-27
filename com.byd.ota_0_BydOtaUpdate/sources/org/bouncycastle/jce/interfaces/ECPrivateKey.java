package org.bouncycastle.jce.interfaces;

import g.a.c.c.a;
import g.a.c.e.e;
import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface ECPrivateKey extends a, PrivateKey {
    BigInteger getD();

    @Override // g.a.c.c.a
    /* synthetic */ e getParameters();
}
