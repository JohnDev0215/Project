package org.bouncycastle.jce.interfaces;

import g.a.c.c.c;
import g.a.c.c.d;
import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface GOST3410PrivateKey extends c, PrivateKey {
    /* synthetic */ d getParameters();

    BigInteger getX();
}
