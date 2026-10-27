package org.bouncycastle.jce.interfaces;

import g.a.c.c.c;
import g.a.c.c.d;
import java.math.BigInteger;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface GOST3410PublicKey extends c, PublicKey {
    /* synthetic */ d getParameters();

    BigInteger getY();
}
