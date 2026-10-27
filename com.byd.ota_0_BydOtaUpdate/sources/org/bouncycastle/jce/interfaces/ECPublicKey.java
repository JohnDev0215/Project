package org.bouncycastle.jce.interfaces;

import g.a.c.c.a;
import g.a.c.e.e;
import g.a.d.b.h;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface ECPublicKey extends a, PublicKey {
    @Override // g.a.c.c.a
    /* synthetic */ e getParameters();

    h getQ();
}
